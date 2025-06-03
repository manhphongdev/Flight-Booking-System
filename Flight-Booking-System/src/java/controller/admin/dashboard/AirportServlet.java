package controller.admin.dashboard;

import exception.EntityExistExeption;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import model.AirportEntity;
import service.IAirportService;
import service.serviceimpl.AirportServiceImpl;
import utils.ValidatorUtils;

/**
 * Servlet quản lý sân bay trong dashboard của admin.
 */
@WebServlet(name = "AirportServlet", urlPatterns = {"/admin/flight-operator/airport"})
public class AirportServlet extends HttpServlet {

    private final IAirportService airportService;
    private static final Logger LOG = Logger.getLogger(AirportServlet.class.getName());

    public AirportServlet() {
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

        resp.sendRedirect("/flights/admin/flight-operator/airport");
    }

    private void getAllAirport(HttpServletRequest req) {
        List<AirportEntity> airports = airportService.getAll();
        req.setAttribute("airports", airports);
    }

    public void addAirport(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();

        String airportCode = req.getParameter("airportCode").toUpperCase();
        String airportName = req.getParameter("airportName");
        String city = req.getParameter("city");
        String country = req.getParameter("country");

        if (ValidatorUtils.isStringEmpty(airportCode) || ValidatorUtils.isStringEmpty(airportName)
                || ValidatorUtils.isStringEmpty(city) || ValidatorUtils.isStringEmpty(country)) {
            errors.add("Vui lòng điền đầy đủ thông tin");
        }

        AirportEntity airport = new AirportEntity(airportCode, airportName, city, country, LocalDateTime.now());

        try {
            airportService.save(airport);
            LOG.info("Airport added successfully!");
        } catch (EntityExistExeption e) {
            req.setAttribute("addAirportFailed", "Mã sân bay đã tồn tại: " + airportCode);
            getAllAirport(req);
            req.getRequestDispatcher("/views/admin/airport_dashboard.jsp").forward(req, resp);
        }
        resp.sendRedirect("/flights/admin/flight-operator/airport");
    }

    public void updateAirport(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String codeUpdate = req.getParameter("codeUpdate");
        String newAirportName = req.getParameter("newAirportName");
        String newCode = req.getParameter("newAirportCode").toUpperCase();
        String newCity = req.getParameter("newCity");
        String newCountry = req.getParameter("newCountry");

        if (ValidatorUtils.isStringEmpty(newAirportName) || ValidatorUtils.isStringEmpty(newCode)
                || ValidatorUtils.isStringEmpty(newCity) || ValidatorUtils.isStringEmpty(newCountry)) {
            req.setAttribute("updateFailed", "Không được để trống thông tin cập nhật!");
            doGet(req, resp);
            req.getRequestDispatcher("/views/admin/airport_dashboard.jsp").forward(req, resp);
            return;
        }

        AirportEntity airport = new AirportEntity();
        airport.setAirportName(newAirportName);
        airport.setAirportCode(codeUpdate);
        airport.setCity(newCity);
        airport.setCountry(newCountry);
        airport.setUpdateAt(LocalDateTime.now());

        boolean isUpdate = airportService.updateByCode(airport);
        if (!isUpdate) {
            req.setAttribute("updateFailed", "Cập nhật thất bại!");
            doGet(req, resp);
            req.getRequestDispatcher("/views/admin/airport_dashboard.jsp").forward(req, resp);
            return;
        }

        LOG.info("Airport updated successfully!");
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
