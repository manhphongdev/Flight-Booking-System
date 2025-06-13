package controller.flightmanager;

import dto.response.FlightResponse;
import dto.request.FlightRequest;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;
import service.serviceimpl.FlightServiceImpl;
import service.interfaces.IFlightService;
import utils.SessionUtil;

/**
 *
 * @author Administrator
 * @version 1.0
 */
@WebServlet(name = "FlightManagement", urlPatterns = {"/flightmanager/flights"})
public class FlightManagement extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(FlightManagement.class.getName());
    private final IFlightService fService = new FlightServiceImpl();
    private final SessionUtil session = SessionUtil.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            LOG.warning("Action is null. Redirecting...");
            resp.sendRedirect(req.getContextPath() + "/flightmanager/flights");
            return;
        }
        switch (action) {
            case "addFlight" ->
                addFlight(req, resp);
            case "delete" ->
                deleteAirline(req, resp);
            case "updateFlight" ->
                updateAirline(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/flightmanager/flights");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getAllFlight(req, resp);
        req.getRequestDispatcher("/views/admin/flight-management.jsp").forward(req, resp);
    }

    public void getAllFlight(HttpServletRequest req, HttpServletResponse resp) {
        List<FlightResponse> flights = fService.getAllFlight();
        req.setAttribute("flights", flights);
    }

    public void addFlight(HttpServletRequest req, HttpServletResponse resp) {
        String airlineName = req.getParameter("airlineName");
        String economyPrice = req.getParameter("economyPrice");
        String businessPrice = req.getParameter("businessPrice");

        try {
            Double economyPriceNumber = Double.valueOf(economyPrice);
            Double businessPriceNumber = Double.valueOf(businessPrice);
            if (economyPriceNumber < 0 || businessPriceNumber < 0) {
                LOG.info("Input must be greater than 0");
                session.putValue(req, "invalidValueNumber", "Number must be greater than 0");
                return;
            }
            fService.addFlight(new FlightRequest(airlineName, economyPriceNumber, businessPriceNumber));
            session.putValue(req, "addSusscess", "Add successful!");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            session.putValue(req, "airlineNameNotExists", "Airline name not exists!");
        }
    }

    public void deleteAirline(HttpServletRequest req, HttpServletResponse resp) {
        String airlineId = req.getParameter("idDelete");
        Long id = null;
        try {
            id = Long.valueOf(airlineId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        fService.deleteById(id);
        session.putValue(req, "deleteSuccess", "ID: " + id + " deleted");
    }

    public void updateAirline(HttpServletRequest req, HttpServletResponse resp) {
        String airlineName = req.getParameter("airlineName");
        String economyPrice = req.getParameter("economyPrice");
        String businessPrice = req.getParameter("businessPrice");
        String flightId = req.getParameter("flightId");
        try {
            Double economyPriceNumber = Double.valueOf(economyPrice);
            Double businessPriceNumber = Double.valueOf(businessPrice);
            Long id = Long.valueOf(flightId);
            if (economyPriceNumber < 0 || businessPriceNumber < 0) {
                LOG.info("Input must be greater than 0");
                session.putValue(req, "invalidValueNumber", "Number must be greater than 0");
                return;
            }
            fService.updateById(id, new FlightRequest(airlineName, economyPriceNumber, businessPriceNumber));
            session.putValue(req, "updateSuccess", "Update successful!");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
