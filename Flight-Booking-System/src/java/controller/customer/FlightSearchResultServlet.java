
package controller.customer;

import dao.daoimpl.FlightDAOImpl;
import dao.interfaces.IFlightDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Logger;
import utils.SessionUtil;

/**
 *
 * @author Administrator
 */
@WebServlet(name="FlightSearchResultServlet", urlPatterns={"/flight/search/result"})
public class FlightSearchResultServlet extends HttpServlet {

    private IFlightDAO flightDAO = new FlightDAOImpl();
    private static final Logger LOG = Logger.getLogger(FlightSearchResultServlet.class.getName());
    private SessionUtil session = SessionUtil.getInstance();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String flightId = req.getParameter("flightIdSelect");
        //find the flight has this flightID
        // find tickettype, base price, from, to
  
        req.getRequestDispatcher("/views/customer/booking-search-result.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

}
