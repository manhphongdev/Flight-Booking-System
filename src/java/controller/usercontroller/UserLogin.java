package controller.usercontroller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.UserEntity;
import service.UserService;
import service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserLogin
 * @author manhphong
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class UserLogin extends HttpServlet {

    private UserService userService = new UserServiceImpl();
    
    /**
     * Default constructor.
     */
    public UserLogin() {
        super();
    }

    /**
     * @param req
     * @param res
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
       
        HttpSession session =req.getSession();
        
        UserEntity u = new UserEntity();
        u.setEmail(email);
        u.setPassword(pass);
        
        if(userService.login(u)){
            session.setAttribute("loggedIn", true);
            session.setAttribute("email", email);
            session.setAttribute("password", pass);
            
            res.sendRedirect("home.jsp");
        }
        else{
             res.sendRedirect("loginfaild.jsp");
        }
    }

    /**
     * @param req
     * @param res
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}