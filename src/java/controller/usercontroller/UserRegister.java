package controller.usercontroller;

import dto.UserRegisterRequest;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import service.UserService;
import service.impl.UserServiceImpl;
import util.ResourseMSG;
import validator.ValidatorUtils;

/**
 *
 * @author manhphong
 */
@WebServlet(name = "UserResgister", urlPatterns = {"/register"})
public class UserRegister extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserRegister.class.getName());
    private final UserService userService;

    public UserRegister() {
        this.userService = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String lastName = req.getParameter("lastName");
        String firstName = req.getParameter("firstName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String rePassword = req.getParameter("rePassword");

        //a list about infor of error
        Map<String, String> errors = new HashMap<>();

        if (ValidatorUtils.isStringEmpty(lastName) || ValidatorUtils.isStringEmpty(firstName)) {
            errors.put("firstName", ResourseMSG.IS_EMPTY);
        }

        if (!email.matches("^[A-Za-z0-9+_.-]{1,64}@[A-Za-z0-9.-]{1,255}\\.[A-Za-z]{2,6}$")) {
            errors.put("email", ResourseMSG.ERRORS_EMAIL_INVALID);
        }

        if (password.length() < 6) {
            errors.put("password", ResourseMSG.ERRORS_PASSWORD);
        }

        if (!checkPasswordAndRePassword(password, rePassword)) {
            errors.put("rePassword", ResourseMSG.ERRORS_RE_PASSWORD);
        }

        if (userService.isEmailExist(email)) {
            errors.put("email", ResourseMSG.ERRORS_EMAIL_EXIST);
        }

        String url = "";
        // register successfull
        if (errors.isEmpty()) {

            UserRegisterRequest userRequest = new UserRegisterRequest(email.trim(), password, firstName.trim(), lastName.trim());

            try {
                long userId = userService.register(userRequest);

                if (userId > 0) {
                    req.setAttribute("successRegisterMsg", ResourseMSG.SUCCESS_REGISTER_MSG);
                    url = "home.jsp";
                } else {
                    errors.put("registerFailed", ResourseMSG.FAILED_REGISTER);
                    url = "home.jsp";
                }
            } catch (Exception e) {
                e.printStackTrace();
                url = "home.jsp";
            }

        } else {
            errors.put("registerFailed", ResourseMSG.FAILED_REGISTER);
            url = "home.jsp";
        }
        req.setAttribute("errors", errors);
        req.getRequestDispatcher(url).forward(req, resp);
    }

    /**
     * check re-password
     *
     * @param pass
     * @param confPass
     * @return true if password and re-password is same
     */
    public boolean checkPasswordAndRePassword(String pass, String confPass) {
        return pass.equals(confPass);
    }
}
