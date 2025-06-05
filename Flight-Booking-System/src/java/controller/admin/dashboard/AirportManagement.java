package controller.admin.dashboard;

import exception.EntityExistExeption;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import model.Airport;
import service.interfaces.IAirportService;
import service.serviceimpl.AirportServiceImpl;
import utils.ResourseMSG;
import utils.SessionUtil;
import utils.ValidatorUtils;

/**
 * Servlet quản lý sân bay trong dashboard của admin.
 */
@WebServlet(name = "AirportServlet", urlPatterns = {"/flightmanager/airport"})
public class AirportManagement extends HttpServlet {

    private final IAirportService airportService;
    private static final Logger LOG = Logger.getLogger(AirportManagement.class.getName());
    private final SessionUtil session = SessionUtil.getInstance();

    public AirportManagement() {
        this.airportService = new AirportServiceImpl();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getAllAirport(req);
        req.getRequestDispatcher("/views/admin/airport_dashboard.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "addAirport" ->
                addAirport(req, resp);
            case "deleteAirport" ->
                deleteAirport(req, resp);
            case "editAirport" ->
                updateAirport(req, resp);
        }

        resp.sendRedirect(req.getContextPath() + "/flightmanager/airport");
    }

    private void getAllAirport(HttpServletRequest req) {
        List<Airport> airports = airportService.getAll();
        req.setAttribute("airports", airports);
    }

    public void addAirport(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String airportCode = req.getParameter("airportCode").trim().toUpperCase();
        String airportName = req.getParameter("airportName").trim();
        String city = req.getParameter("city").trim();
        String country = req.getParameter("country").trim();

        if (ValidatorUtils.isStringEmpty(airportCode) || ValidatorUtils.isStringEmpty(airportName)
                || ValidatorUtils.isStringEmpty(city) || ValidatorUtils.isStringEmpty(country)) {
            session.putValue(req, "errorNull", ResourseMSG.IS_EMPTY);
        }

        if(airportCode.length() <3 || airportCode.length() >4){
            session.putValue(req, "errorCode", "Airport code must contain 3 or more characters!");
        }
        
        Airport airport = new Airport(airportCode, airportName, city, country, LocalDateTime.now());

        try {
            airportService.save(airport);
            LOG.info("Airport added!");
        } catch (EntityExistExeption e) {
            session.putValue(req, "addAirportFailed", "This airport is exists " );
        }
    }

    public void updateAirport(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String codeUpdate = req.getParameter("codeUpdate");
        String newAirportName = req.getParameter("newAirportName");
        String newCode = req.getParameter("newAirportCode").toUpperCase();
        String newCity = req.getParameter("newCity");
        String newCountry = req.getParameter("newCountry");

        if (ValidatorUtils.isStringEmpty(newAirportName) || ValidatorUtils.isStringEmpty(newCode)
                || ValidatorUtils.isStringEmpty(newCity) || ValidatorUtils.isStringEmpty(newCountry)) {
            
            session.putValue(req, "errorNull", ResourseMSG.IS_EMPTY);
        }

        Airport airport = new Airport();
        airport.setAirportName(newAirportName);
        airport.setAirportCode(codeUpdate);
        airport.setCity(newCity);
        airport.setCountry(newCountry);
        airport.setUpdateAt(LocalDateTime.now());

        boolean isUpdate = airportService.updateByCode(airport);
        if (!isUpdate) {
            session.putValue(req, "updateFailed", "Cập nhật thất bại!");
        }

        LOG.info("Airport updated!");
    }

    public void deleteAirport(HttpServletRequest req, HttpServletResponse resp) {
        String codeDelete = req.getParameter("codeDelete");
        boolean isDeleted = airportService.deleteByCode(codeDelete);
        
        if (!isDeleted) {
            LOG.warning("Airport delete failed!");
            return;
        }
        LOG.info("Airport deleted successfully!");
    }
}
