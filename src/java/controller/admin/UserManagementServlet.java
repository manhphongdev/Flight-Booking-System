package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import model.UserEntity;
import service.UserService;
import service.impl.UserServiceImpl;
import util.ResourseMSG;

/**
 * Servlet implementation class UserManagementServlet
 */
@WebServlet(name = "UserManagementServlet", urlPatterns = {"/admin/dashboard"})
public class UserManagementServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserManagementServlet.class.getName());
    private UserService userService;

    /**
     * Default constructor.
     */
    public UserManagementServlet() {
        userService = new UserServiceImpl();
    }

    /**
     * @param req
     * @param res
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        getAllUser(req, res);

        req.getRequestDispatcher("/views/admin/admin_dashboard.jsp").forward(req, res);

    }

    /**
     * @param req
     * @param res
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * res)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }

    public void getAllUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<UserEntity> lists = userService.getAllUser();
        if (lists.isEmpty()) {
            req.setAttribute("listEmpty", ResourseMSG.LIST_EMPTY);
        }
        req.setAttribute("allUser", lists);
    }
}
