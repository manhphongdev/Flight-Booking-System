/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.common;

import exception.EntityExistExeption;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Logger;
import model.UserEntity;
import service.IUserService;
import service.serviceimpl.UserServiceImpl;
import utils.ResourseMSG;
import utils.ValidatorUtils;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/auth/register"})
public class RegisterServlet extends HttpServlet {

    private final IUserService uService;
    private static final Logger LOG = Logger.getLogger(RegisterServlet.class.getName());
    
    public RegisterServlet() {
        this.uService = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("rePassword");

        if (!password.equals(rePassword)) {
            req.setAttribute("errorRePass", ResourseMSG.ERRORS_RE_PASSWORD);
        }
        if (ValidatorUtils.isStringEmpty(firstName)
                || ValidatorUtils.isStringEmpty(lastName)
                || ValidatorUtils.isValidEmail(email)) {
            LOG.warning(ResourseMSG.IS_EMPTY);
        }
        
        if(password.length() <6){
           req.setAttribute("errorPassword", ResourseMSG.ERRORS_PASSWORD); 
        }
        UserEntity user = new UserEntity(email, password, firstName, lastName);
        try {
            uService.register(user);
            resp.sendRedirect(req.getContextPath() + "/home");
        } catch (EntityExistExeption e) {
            req.setAttribute("errorRegister", ResourseMSG.ERRORS_EMAIL_EXIST);
            req.getRequestDispatcher("/views/customer/home.jsp").forward(req, resp);
        }
        
    }
    
}
