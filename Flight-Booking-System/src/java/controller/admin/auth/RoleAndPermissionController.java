package controller.admin.auth;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;
import model.PermissionEntity;
import model.RoleEntity;
import service.IPermissionService;
import service.IRoleService;
import service.serviceimpl.PermissionServiceImpl;
import service.serviceimpl.RoleSerrviceImpl;
import utils.ValidatorUtils;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "RoleController", urlPatterns = {"/admin/access-manager"})
public class RoleAndPermissionController extends HttpServlet {

    private final IRoleService rService;
    private final IPermissionService pService;
    private static final Logger LOG = Logger.getLogger(RoleAndPermissionController.class.getName());

    public RoleAndPermissionController() {
        this.rService = new RoleSerrviceImpl();
        this.pService = new PermissionServiceImpl();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getAllRole(req, resp);
        getAllPermission(req, resp);
        req.getRequestDispatcher("/views/admin/admin_roles_permissions.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
        String action = req.getParameter("action");

        switch (action) {
            case "addRole" -> addRole(req, resp);
            case "deleteRole" -> deleteRole(req, resp);
            case "updateRole" -> updateRole(req, resp);
            case "addPermission" -> addPermission(req, resp);
            case "deletePermission" -> deletePermission(req, resp);
            case "updatePermission" -> updatePermission(req, resp);
        }

        if (req.getAttribute("addRoleMsg") != null
                || req.getAttribute("updateFaild") != null 
                || req.getAttribute("addPermissionFailed")!=null
                || req.getAttribute("updatePermissionFaield") !=null) {
            getAllRole(req, resp);
            getAllPermission(req, resp);
            req.getRequestDispatcher("/views/admin/admin_roles_permissions.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect("/flights/admin/access-manager");

    }

    private void addRole(HttpServletRequest req, HttpServletResponse resp) {
        String roleName = req.getParameter("roleName").toUpperCase();
        String description = req.getParameter("description").toUpperCase();

        if (ValidatorUtils.isStringEmpty(roleName.trim())
                || ValidatorUtils.isStringEmpty(description.trim())) {
            LOG.warning("input is empty");
        }

        Long id = rService.addRole(new RoleEntity(roleName, description));
        String addRoleMsg = "";
        if (id == null) {
            addRoleMsg = roleName + " is exist";
            req.setAttribute("addRoleMsg", addRoleMsg);
            req.setAttribute("openAddRoleModal", "addRoleModal");
        }
    }

    private void getAllRole(HttpServletRequest req, HttpServletResponse resp) {

        List<RoleEntity> roles = rService.getAllRole();

        req.setAttribute("roles", roles);
    }

    private void updateRole(HttpServletRequest req, HttpServletResponse resp) {
        String roleNameEdit = req.getParameter("roleNameEdit");
        String newRoleName = req.getParameter("newRoleName");
        String newDescription = req.getParameter("newDescription");

        if (newRoleName != null && newDescription != null) {
            boolean f = rService.updateByRoleName(new RoleEntity(newRoleName.trim().toUpperCase(),
                    newDescription), roleNameEdit);
            if (f == false) {
                req.setAttribute("updateFaild", "Name of role is exist");
            }
        }
    }

    private void deleteRole(HttpServletRequest req, HttpServletResponse resp) {
        String roleDelete = req.getParameter("roleDelete");

        try {
            Long id = Long.parseLong(roleDelete);
            rService.deleteRoleById(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
    
    private void getAllPermission(HttpServletRequest req, HttpServletResponse res){
        
        List<PermissionEntity> permissions = pService.getAllPermission();
        req.setAttribute("permissions", permissions);
    }
    
    private void addPermission(HttpServletRequest req, HttpServletResponse res){
        
        String permissionName = req.getParameter("permissionName").trim();
        String description = req.getParameter("permissionDescription").trim();
        
        if(ValidatorUtils.isStringEmpty(permissionName) || ValidatorUtils.isStringEmpty(description)){
            LOG.warning("Input permission is empty");
        }
        
        Long id = pService.addPermission(new PermissionEntity(permissionName, description));
        
        if(id == null){
            String msg = permissionName + " is exist";
            req.setAttribute("addPermissionFailed", msg);
        }
        else{
            LOG.info("Permission " + permissionName + " added");
        }
    }
    
    public void deletePermission(HttpServletRequest req, HttpServletResponse res){
        String permissionDelete = req.getParameter("permissionDelete");
        
        try {
            Long id = Long.parseLong(permissionDelete);
            pService.deletePermissionById(id);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
    
    public void updatePermission(HttpServletRequest req, HttpServletResponse res){
        
        String permission = req.getParameter("permissionNameEdit");
        String newPermissionName = req.getParameter("newPermissionName");
        String newPermissionDescription = req.getParameter("newPermissionDescription");
        
        
        if (newPermissionName != null && newPermissionDescription != null) {
            boolean f = pService.updateByPermissionName(new PermissionEntity(
                    newPermissionName.trim().toLowerCase(),
                    newPermissionDescription), permission);
            if (f == false) {
                req.setAttribute("updatePermissionFaield", "Name of permission is exist");
            }
        }
    }
}
