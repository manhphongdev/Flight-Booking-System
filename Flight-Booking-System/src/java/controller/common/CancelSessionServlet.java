
package controller.common;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@WebServlet(name="CancelSessionServlet", urlPatterns={"/CancelSessionServlet"})
public class CancelSessionServlet extends HttpServlet {
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        session.removeAttribute("flightSearchOneWay");  
        session.removeAttribute("flightSearchRoundTrip"); 
        response.getWriter().println("Session cleared");
    }

}
