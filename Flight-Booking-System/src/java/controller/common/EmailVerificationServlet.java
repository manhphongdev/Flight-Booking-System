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
import model.User;
import service.serviceimpl.EmailService;
import service.serviceimpl.UserServiceImpl;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "EmailVerificationServlet", urlPatterns = {"/authentication"})
public class EmailVerificationServlet extends HttpServlet {

    private final EmailService emailService = new EmailService();
    private final UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String userIDVerify = req.getParameter("id");
        String token = req.getParameter("token");

        if (action.equals("verify")) {
            Long userID = Long.valueOf(userIDVerify);
            User user = userService.getUserById(userID);
            if (user == null) {
                req.getRequestDispatcher("/views/customer/error-verify.jsp").forward(req, resp);
                //resp.sendRedirect(req.getContextPath() + "/failed?message=User not found");
                return;
            }
            try {
                emailService.hanlderRequestVerify(token, user);
                req.getRequestDispatcher("/views/customer/success-verify.jsp").forward(req, resp);
            } catch (EntityNotFoundException e) {
                req.setAttribute("errorRegister", "Người dùng không tồn tại!");
                req.getRequestDispatcher("/views/customer/error-verify.jsp").forward(req, resp);
            } catch (InvalidTokenException | TokenHasExpireException e) {
                req.setAttribute("errorRegister", "Link xác thực có thể hết hạn hoặc không tồn tại! Vui lòng đăng ký lại tài khoản!");
                req.getRequestDispatcher("/views/customer/error-verify.jsp").forward(req, resp);
            }
        }
    }

}
