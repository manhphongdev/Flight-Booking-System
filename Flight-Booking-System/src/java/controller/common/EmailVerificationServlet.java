package controller.common;

import exception.EntityNotFoundException;
import exception.InvalidTokenException;
import exception.TokenHasExpireException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserEntity;
import service.serviceimpl.EmailService;
import service.serviceimpl.UserServiceImpl;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "EmailVerificationServlet", urlPatterns = {"/authentication"})
public class EmailVerificationServlet extends HttpServlet {

    private EmailService emailService = new EmailService();
    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String userIDVerify = req.getParameter("id");
        String token = req.getParameter("token");

        if (action.equals("verify")) {
            Long userID = Long.valueOf(userIDVerify);
            UserEntity user = userService.getUserById(userID);

            if (user == null) {
                req.getRequestDispatcher("/views/customer/error-verify.jsp");
                //resp.sendRedirect(req.getContextPath() + "/failed?message=User not found");
                return;
            }

            try {
                emailService.hanlderRequestVerify(token, user);
                req.getRequestDispatcher("/views/customer/error-verify.jsp");
            } catch (EntityNotFoundException | InvalidTokenException | TokenHasExpireException e) {
                req.getRequestDispatcher("/views/customer/error-verify.jsp");
            }
        }
    }

}
