package controller.admin.auth;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Logger;
import service.IUserService;
import service.serviceimpl.UserServiceImpl;
import utils.ValidatorUtils;

/**
 *
 * @author Administrator
 */
@WebServlet(urlPatterns={"/admin/login"})
public class AdminLogin extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(AdminLogin.class.getName());
    private IUserService uService;

    public AdminLogin() {
        this.uService = new UserServiceImpl();
    }
    
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
        HttpSession session = req.getSession();
        
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if(!ValidatorUtils.isValidEmail(email)){
            req.setAttribute("invalidEmail", "Email invalid format!");
        }
        if(ValidatorUtils.isStringEmpty(password)){
            LOG.warning("Password is empty!");
        }
        
        req.getRequestDispatcher("views.admin/admin-login.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   
    }
   
    

}
