package controller.common;

import exception.EntityNotFoundException;
import exception.UserInActiveException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Logger;
import model.UserEntity;
import service.IUserService;
import service.serviceimpl.UserServiceImpl;
import utils.ResourseMSG;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/auth/login"})
public class LoginServlet extends HttpServlet {

    private final IUserService uService;
    private static final Logger LOG = Logger.getLogger(LoginServlet.class.getName());

    public LoginServlet() {
        this.uService = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String loginType = req.getParameter("loginType");
        try {
            UserEntity user = uService.login(email, password);
            String role = uService.getUserRole(user);

            HttpSession session = req.getSession();
            session.setAttribute("sessionUser", user);
            session.setAttribute("role", role);

            if (loginType.equals("dashboard")) {
                resp.sendRedirect(req.getContextPath() + "/views/admin/admin_dashboard.jsp");
            } else {
                resp.sendRedirect(req.getContextPath() + "/home");
            }
            LOG.info("login success");
        } catch (EntityNotFoundException e) {
            req.setAttribute("errorLogin", ResourseMSG.LOGIN_FAILED);
            if (loginType.equals("dashboard")) {
                req.getRequestDispatcher("/views/admin/admin-login.jsp").forward(req, resp);
            } else {
                LOG.warning(e.getMessage());
                req.getRequestDispatcher("/views/customer/home.jsp").forward(req, resp);
            }
        } catch (UserInActiveException e) {
            req.setAttribute("errorLogin", ResourseMSG.IN_ACTIVE_MSG);
            if (loginType.equals("dashboard")) {
                req.getRequestDispatcher("/views/admin/admin-login.jsp").forward(req, resp);
            } else {
                LOG.warning(e.getMessage());
                req.getRequestDispatcher("/views/customer/home.jsp").forward(req, resp);
            }
        }
    }
}
