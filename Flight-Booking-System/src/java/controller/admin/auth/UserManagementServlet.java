package controller.admin.auth;

import enums.UserStatus;
import exception.EntityExistExeption;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;
import model.Role;
import model.User;
import service.interfaces.IRoleService;
import service.interfaces.IUserService;
import service.serviceimpl.RoleServiceImpl;
import service.serviceimpl.UserServiceImpl;
import utils.ResourseMSG;
import utils.SessionUtil;
import utils.ValidatorUtils;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "UserController", urlPatterns = {"/admin/users"})
public class UserManagementServlet extends HttpServlet {

    private final IUserService uService = new UserServiceImpl();
    private final IRoleService rService = new RoleServiceImpl();
    private final SessionUtil sessionUtil = new SessionUtil();
    private static final Logger LOG = Logger.getLogger(UserManagementServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //getAllUser(req, resp);
        List<Role> roles = rService.getAllRole();
        List<User> users = uService.getAll();

        String key = req.getParameter("search");
        String role = req.getParameter("roleFilter");
        String sortBy = req.getParameter("sortBy");

        if (null != sortBy) {
            switch (sortBy) {
                case "fullNameASC" -> {
                    if (role.equals("allRole")) {
                        users = uService.searchByNameASC(key);
                    } else {
                        users = uService.searchByNameAndRoleAndOrderByNameASC(key, role);
                    }
                }
                case "fullNameDESC" -> {
                    if (role.equals("allRole")) {
                        users = uService.searchByNameDESC(key);
                    } else {
                        users = uService.searchByNameAndRoleAndOrderByNameDESC(key, role);
                    }
                }
                case "emailASC" -> {
                    if (role.equals("allRole")) {
                        users = uService.searchByEmailASC(key);
                    } else {
                        users = uService.searchByEmailAndRoleAndOrderByNameASC(key, role);
                    }
                }
                case "emailDESC" -> {
                    if (role.equals("allRole")) {
                        users = uService.searchByEmailDESC(key);
                    } else {
                        users = uService.searchByEmailAndRoleAndOrderByNameDESC(key, role);
                    }
                }
            }
        }
        req.setAttribute("roles", roles);
        req.setAttribute("users", users);
        // resp.sendRedirect(req.getContextPath() + "/admin/users");
        req.getRequestDispatcher("/views/admin/user-management.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        //add user
        if ("addUser".equals(action)) {
            addUser(req, resp);
        }
        if ("delete".equals(action)) {
            deleteUser(req, resp);
        }
        if ("updateUser".equals(action)) {
            updateUser(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/users");
        //req.getRequestDispatcher("/views/admin/user-management.jsp").forward(req, resp);
    }

    public void getAllUser(HttpServletRequest req, HttpServletResponse resp) {
        List<User> users = uService.getAll();
        req.setAttribute("users", users);
    }

    public void addUser(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email").trim();
        String firstName = req.getParameter("firstName").trim();
        String lastName = req.getParameter("lastName").trim();
        String password = req.getParameter("password").trim();
        String role = req.getParameter("role");

        if (ValidatorUtils.isStringEmpty(firstName) || ValidatorUtils.isStringEmpty(lastName)) {
            sessionUtil.putValue(req, "errorNull", ResourseMSG.IS_EMPTY);
            return;
        }
        if (!ValidatorUtils.isValidEmail(email)) {
            sessionUtil.putValue(req, "errorEmail", ResourseMSG.ERRORS_EMAIL_INVALID);
            return;
        }
        if (password.length() < 8) {
            sessionUtil.putValue(req, "errorPassword", "Password must be at least 8 characters long.");
            return;
        }
        User user = new User(email, password, firstName, lastName, role);
        try {
            uService.addUser(user);
        } catch (EntityExistExeption e) {
            sessionUtil.putValue(req, "errorEmailExists", ResourseMSG.ERRORS_EMAIL_EXIST);
        }
    }

    public void deleteUser(HttpServletRequest req, HttpServletResponse resp) {
        var idDelete = req.getParameter("idDelete");
        Long id = null;
        try {
            id = Long.valueOf(idDelete);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        uService.deleteUser(id);
    }

    public void updateUser(HttpServletRequest req, HttpServletResponse resp) {
        var userIdEdit = req.getParameter("userIdEdit");
        var firstName = req.getParameter("newFirstName").trim();
        var lastName = req.getParameter("newLastName").trim();
        var roleName = req.getParameter("roleName");
        var status = req.getParameter("status");

        if (ValidatorUtils.isStringEmpty(firstName) || ValidatorUtils.isStringEmpty(lastName)) {
            sessionUtil.putValue(req, "errorNull", ResourseMSG.IS_EMPTY);
            return;
        }

        Long userId = null;
        try {
            userId = Long.valueOf(userIdEdit);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        User newUser = new User();
        newUser.setUserId(userId);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        LOG.info("beforeUpdate: " + roleName);
        newUser.setUserRole(roleName);
        LOG.info("afterSetRole: " + newUser.getUserRole());
        newUser.setStatus(UserStatus.valueOf(status.toUpperCase()));

        uService.adminUpdateUser(newUser);
        LOG.info("afterUpdate: " + newUser.getUserRole());

    }

}
