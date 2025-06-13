package controller.flightmanager;

import exception.EntityExistExeption;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import model.Airline;
import service.interfaces.IAirlineService;
import service.serviceimpl.AirlineServiceImpl;
import utils.ResourseMSG;
import utils.SessionUtil;
import utils.ValidatorUtils;

@WebServlet(name = "AirlineServlet", urlPatterns = {"/flightmanager/airline"})
public class AirlineManagement extends HttpServlet {

    private final IAirlineService airlineService;
    private static final Logger LOG = Logger.getLogger(AirlineManagement.class.getName());
    private final SessionUtil session = SessionUtil.getInstance();

    public AirlineManagement() {
        this.airlineService = new AirlineServiceImpl();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getAllAirline(req);
        req.getRequestDispatcher("/views/admin/airline_dashboard.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "addAirline" ->
                addAirline(req, resp);
            case "deleteAirline" ->
                deleteAirline(req, resp);
        }

        resp.sendRedirect(req.getContextPath() + "/flightmanager/airline");
    }

    private void getAllAirline(HttpServletRequest req) {
        List<Airline> airlines = airlineService.getAll();
        req.setAttribute("airlines", airlines);
    }

    public void addAirline(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String airlineCode = req.getParameter("airlineCode").trim().toUpperCase();
        String airlineName = req.getParameter("airlineName").trim();

        if (ValidatorUtils.isStringEmpty(airlineCode) || ValidatorUtils.isStringEmpty(airlineName)) {
            session.putValue(req, "errorNull", ResourseMSG.IS_EMPTY);
        }

        if (airlineCode.length() < 2 || airlineCode.length() > 3) {
            session.putValue(req, "errorCode", "Airline code must contain 2 or 3 characters!");
        }

        Airline airline = new Airline(airlineName, airlineCode, Date.from(Instant.MIN));

        try {
            airlineService.save(airline);
            LOG.info("Airline added!");
        } catch (EntityExistExeption e) {
            session.putValue(req, "addAirlineFailed", "This airline is exists");
        }
    }

    public void deleteAirline(HttpServletRequest req, HttpServletResponse resp) {
        String codeDelete = req.getParameter("codeDelete");
        boolean isDeleted = airlineService.deleteByCode(codeDelete);

        if (!isDeleted) {
            LOG.warning("Airline delete failed!");
            return;
        }
        LOG.info("Airline deleted successfully!");
    }
}
