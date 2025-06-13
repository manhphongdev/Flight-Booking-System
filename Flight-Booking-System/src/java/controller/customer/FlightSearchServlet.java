package controller.customer;

import dto.request.FlightSearchRequest;
import dto.response.FlightSearchResponse;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;
import service.interfaces.IFlightService;
import service.serviceimpl.FlightServiceImpl;
import utils.SessionUtil;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "FlightSearchServlet", urlPatterns = {"/flight/search"})
public class FlightSearchServlet extends HttpServlet {

    private final IFlightService fService = new FlightServiceImpl();
    private final SessionUtil session = SessionUtil.getInstance();
    private static final Logger LOG = Logger.getLogger(FlightSearchServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeOption = req.getParameter("typeOption");
        if ("oneWay".equals(typeOption)) {
            doSerachForOneWay(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/flight/search/result");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/customer/booking-search.jsp").forward(req, resp);
    }

    private void doSerachForOneWay(HttpServletRequest req, HttpServletResponse resp) {
        String departure = req.getParameter("departure");
        String arrival = req.getParameter("arrival");
        String dateStart = req.getParameter("dayStart");
        String ticketType = req.getParameter("ticketType");
        int adultNumber =(int) Integer.valueOf(req.getParameter("adultNumber"));
        int childNumber = (int) Integer.valueOf(req.getParameter("childNumber"));

        FlightSearchRequest searchRequest = new FlightSearchRequest(
                departure,
                arrival,
                dateStart,
                "",
                ticketType,
                adultNumber,
                childNumber);
        List<FlightSearchResponse> flights = fService.searchFlightForBooking(searchRequest);
        session.putValue(req, "flightSearchOneWay", flights);
    }
}
