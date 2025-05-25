/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.UserEntity;
import service.IUserService;
import service.serviceimpl.UserServiceImpl;
import utils.ValidatorUtils;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "AdminRegister", urlPatterns = {"/admin-register"})
public class AdminRegister extends HttpServlet {

    private IUserService uService;

    public AdminRegister() {
        this.uService = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confPassword = req.getParameter("confPassword");

        if (ValidatorUtils.isStringEmpty(firstName)
                || ValidatorUtils.isStringEmpty(lastName)
                || ValidatorUtils.isStringEmpty(email)
                || ValidatorUtils.isStringEmpty(password)) {
            req.setAttribute("inputEmpty", "Please fill in all the required information!");
        } else if (!password.equals(confPassword)) {
            req.setAttribute("confPassIncorrect", "The confirmed password does not match!");
        } else {
            Long id = uService.register(new UserEntity(email, password, firstName, lastName));
            if (id != null) {
                req.setAttribute("registerFailed", "Email used");
            }
        }
    }

}
