

package controller.flightmanager;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Logger;
import service.interfaces.IFlightScheduleService;
import service.serviceimpl.FlightScheduleServiceImpl;

/**
 *
 * @author Administrator
 * @version 1.0
 */
@WebServlet(name="FlightScheduleManagement", urlPatterns={"/flightmanager/flight-schedule"})
public class FlightScheduleManagement extends HttpServlet {

    private final IFlightScheduleService fsService = new FlightScheduleServiceImpl();
    private static final Logger LOG = Logger.getLogger(FlightScheduleManagement.class.getName());
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
   
    private void getAll(HttpServletRequest req, HttpServletResponse resp){
        
    }
   
}
