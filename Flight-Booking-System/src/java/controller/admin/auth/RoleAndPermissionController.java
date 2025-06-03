package controller.admin.auth;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PermissionEntity;
import model.RoleEntity;
import service.IPermissionService;
import service.IRoleService;
import service.serviceimpl.PermissionServiceImpl;
import service.serviceimpl.RoleServiceImpl;
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
        this.rService = new RoleServiceImpl();
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
        getPermissionOfRole(req, resp);
        req.getRequestDispatcher("/views/admin/admin_roles_permissions.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
        String action = req.getParameter("action");

        switch (action) {
            case "addRole" ->
                addRole(req, resp);
            case "deleteRole" ->
                deleteRole(req, resp);
            case "updateRole" ->
                updateRole(req, resp);
            case "addPermission" ->
                addPermission(req, resp);
            case "deletePermission" ->
                deletePermission(req, resp);
            case "updatePermission" ->
                updatePermission(req, resp);
            case "addRolePermission" ->
                addRoleHasPermission(req, resp);
            case "deletePermissionOfRole" ->
                deletePermissionOfRole(req, resp);
        }

        if (req.getAttribute("addRoleMsg") != null
                || req.getAttribute("updateFaild") != null
                || req.getAttribute("addPermissionFailed") != null
                || req.getAttribute("updatePermissionFaield") != null) {
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

        if (ValidatorUtils.isStringEmpty(roleName)
                || ValidatorUtils.isStringEmpty(description)) {
            LOG.warning("input is empty");
            throw new IllegalArgumentException("Input is empty!");
        }

        boolean inserted = rService.addRole(new RoleEntity(roleName.trim(), description.trim()));
        if (!inserted) {
            String addRoleMsg = roleName + " is exist";
            req.setAttribute("addRoleMsg", addRoleMsg);
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
            Long id = Long.valueOf(roleDelete);
            rService.deleteRoleById(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid number format!");
        }
    }

    private void getAllPermission(HttpServletRequest req, HttpServletResponse res) {

        List<PermissionEntity> permissions = pService.getAllPermission();
        req.setAttribute("permissions", permissions);
    }

    private void addPermission(HttpServletRequest req, HttpServletResponse res) {
        String permissionName = req.getParameter("permissionName").trim();
        String description = req.getParameter("permissionDescription").trim();

        if (ValidatorUtils.isStringEmpty(permissionName) || ValidatorUtils.isStringEmpty(description)) {
            LOG.warning("Input permission is empty");
        }

        boolean inserted = pService.addPermission(new PermissionEntity(permissionName, description));

        if (!inserted) {
            String msg = permissionName + " is exist";
            req.setAttribute("addPermissionFailed", msg);
        } else {
            LOG.log(Level.INFO, "Permission {0} added", permissionName);
        }
    }

    public void deletePermission(HttpServletRequest req, HttpServletResponse res) {
        String permissionDelete = req.getParameter("permissionDelete");

        try {
            Long id = Long.valueOf(permissionDelete);
            pService.deletePermissionById(id);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid format!");
        }
    }

    public void updatePermission(HttpServletRequest req, HttpServletResponse res) {

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
            LOG.log(Level.INFO, "Permission updated: {0}", permission);
        }
    }

    public void addRoleHasPermission(HttpServletRequest req, HttpServletResponse res) {
        String roleName = req.getParameter("roleName");
        String[] permission = req.getParameterValues("permission");

        for (String permission1 : permission) {
            rService.addRoleHasPermission(roleName, permission1);
        }

    }

    public void getPermissionOfRole(HttpServletRequest req, HttpServletResponse res) {
        String roleName = req.getParameter("roleName");

        LOG.log(Level.INFO, "ROLE: {0}", roleName);
        List<String> permissionNames = pService.getPermissionOfRole(roleName);

        req.setAttribute("permissionNames", permissionNames);
    }

    public void deletePermissionOfRole(HttpServletRequest req, HttpServletResponse res) {
        String roleName = req.getParameter("roleNameDelete");
        String permissionName = req.getParameter("permissionNameDelete");

        boolean isDeleted = pService.deletePermissionOfRole(roleName, permissionName);
        if (isDeleted == false) {
            LOG.log(Level.WARNING, "Delete failed: {0} {1}", new Object[]{roleName, permissionName});
        } else {
            LOG.log(Level.INFO, "{0}Delete permission of role successfull, role: {1} ", new Object[]{permissionName, roleName});
        }
    }
}
