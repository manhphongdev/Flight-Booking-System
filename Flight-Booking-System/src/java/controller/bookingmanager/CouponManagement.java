package controller.bookingmanager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import model.Coupon;
import service.interfaces.ICouponService;
import service.serviceimpl.CouponServiceImpl;
import utils.ResourseMSG;
import utils.SessionUtil;

/**
 * Servlet for managing coupons.
 */
@WebServlet(name = "CouponManagement", urlPatterns = {"/bookingmanager/coupon"})
public class CouponManagement extends HttpServlet {

    private final ICouponService couponService;
    private final SessionUtil session = SessionUtil.getInstance();
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public CouponManagement() {
        this.couponService = new CouponServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            listCoupons(req);
        } else {
            switch (action) {
                case "search" -> searchCoupons(req);
                case "edit" -> showEditCouponForm(req);
                case "delete" -> deleteCoupon(req, resp);
                default -> listCoupons(req);
            }
        }
        req.getRequestDispatcher("/views/admin/coupon-management.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "addCoupon" -> addCoupon(req, resp);
            case "updateCoupon" -> updateCoupon(req, resp);
            default -> {
                session.putValue(req, "error", "Invalid action.");
                resp.sendRedirect(req.getContextPath() + "/bookingmanager/coupon");
            }
        }
    }

    private void listCoupons(HttpServletRequest req) {
        List<Coupon> coupons = couponService.getAll();
        req.setAttribute("coupons", coupons);
    }

    private void searchCoupons(HttpServletRequest req) {
        String search = req.getParameter("search");
        String statusFilter = req.getParameter("statusFilter");
        String sortBy = req.getParameter("sortBy");

        List<Coupon> coupons = couponService.getByCondition(search, statusFilter, sortBy);
        req.setAttribute("coupons", coupons);
        if (coupons.isEmpty() && (search != null && !search.trim().isEmpty())) {
            session.putValue(req, "error", "No coupons found for the search criteria.");
        }
    }

    private void addCoupon(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String couponCode = req.getParameter("couponCode");
        String discountPercentageStr = req.getParameter("discountPercentage");
        String maxUsageStr = req.getParameter("maxUsage");
        String startDateStr = req.getParameter("startDate");
        String endDateStr = req.getParameter("endDate");
        String status = req.getParameter("status");

        if (isEmpty(couponCode, discountPercentageStr, startDateStr, endDateStr, status)) {
            session.putValue(req, "errorNull", ResourseMSG.IS_EMPTY);
            resp.sendRedirect(req.getContextPath() + "/bookingmanager/coupon");
            return;
        }

        try {
            Integer discountPercentage = Integer.valueOf(discountPercentageStr);
            Integer maxUsage = maxUsageStr.isEmpty() ? null : Integer.valueOf(maxUsageStr);
            LocalDateTime startDate = LocalDateTime.parse(startDateStr, DATE_TIME_FORMATTER);
            LocalDateTime endDate = LocalDateTime.parse(endDateStr, DATE_TIME_FORMATTER);
            if (!status.equals("active") && !status.equals("inactive")) {
                throw new IllegalArgumentException("Invalid status: " + status);
            }

            Coupon coupon = new Coupon();
            coupon.setCouponCode(couponCode);
            coupon.setDiscountPercentage(discountPercentage);
            coupon.setMaxUsage(maxUsage);
            coupon.setUsedCount(0);
            coupon.setStartDate(startDate);
            coupon.setEndDate(endDate);
            coupon.setStatus(status.toLowerCase());
            coupon.setCreatedAt(LocalDateTime.now());

            couponService.save(coupon);
            session.putValue(req, "success", "Coupon added successfully!");

        } catch (NumberFormatException e) {
            session.putValue(req, "errorNull", "Invalid number format for discount or max usage.");
        } catch (DateTimeParseException e) {
            session.putValue(req, "errorNull", "Invalid date format.");
        } catch (IllegalArgumentException e) {
            session.putValue(req, "errorCouponCodeExists", e.getMessage());
        } catch (Exception e) {
            session.putValue(req, "errorNull", "Error adding coupon: " + e.getMessage());
        }
        resp.sendRedirect(req.getContextPath() + "/bookingmanager/coupon");
    }

    private void updateCoupon(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String couponIdStr = req.getParameter("couponIdEdit");
        String couponCode = req.getParameter("couponCode");
        String discountPercentageStr = req.getParameter("discountPercentage");
        String maxUsageStr = req.getParameter("maxUsage");
        String startDateStr = req.getParameter("startDate");
        String endDateStr = req.getParameter("endDate");
        String status = req.getParameter("status");

        if (isEmpty(couponIdStr, couponCode, discountPercentageStr, startDateStr, endDateStr, status)) {
            session.putValue(req, "errorNull", ResourseMSG.IS_EMPTY);
            resp.sendRedirect(req.getContextPath() + "/bookingmanager/coupon");
            return;
        }

        try {
            Long couponId = Long.valueOf(couponIdStr);
            Integer discountPercentage = Integer.valueOf(discountPercentageStr);
            Integer maxUsage = maxUsageStr.isEmpty() ? null : Integer.valueOf(maxUsageStr);
            LocalDateTime startDate = LocalDateTime.parse(startDateStr, DATE_TIME_FORMATTER);
            LocalDateTime endDate = LocalDateTime.parse(endDateStr, DATE_TIME_FORMATTER);
            if (!status.equals("active") && !status.equals("inactive")) {
                throw new IllegalArgumentException("Invalid status: " + status);
            }

            Coupon coupon = couponService.getByID(couponId);
            if (coupon == null) {
                session.putValue(req, "error", "Coupon not found.");
                resp.sendRedirect(req.getContextPath() + "/bookingmanager/coupon");
                return;
            }

            coupon.setCouponCode(couponCode);
            coupon.setDiscountPercentage(discountPercentage);
            coupon.setMaxUsage(maxUsage);
            coupon.setStartDate(startDate);
            coupon.setEndDate(endDate);
            coupon.setStatus(status.toLowerCase());

            couponService.updateByCode(coupon);
            session.putValue(req, "success", "Coupon updated successfully!");

        } catch (NumberFormatException e) {
            session.putValue(req, "errorNull", "Invalid number format for discount or max usage.");
        } catch (DateTimeParseException e) {
            session.putValue(req, "errorNull", "Invalid date format.");
        } catch (IllegalArgumentException e) {
            session.putValue(req, "error", e.getMessage());
        } catch (Exception e) {
            session.putValue(req, "error", "Error updating coupon: " + e.getMessage());
        }
        resp.sendRedirect(req.getContextPath() + "/bookingmanager/coupon");
    }

    private void deleteCoupon(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String couponIdStr = req.getParameter("idDelete");
        if (couponIdStr == null || couponIdStr.trim().isEmpty()) {
            session.putValue(req, "error", "Coupon ID is required for deletion.");
            listCoupons(req);
            req.getRequestDispatcher("/views/admin/coupon-management.jsp").forward(req, resp);
            return;
        }

        try {
            Long couponId = Long.valueOf(couponIdStr);
            Coupon coupon = couponService.getByID(couponId);
            if (coupon == null) {
                session.putValue(req, "error", "Coupon not found.");
            } else {
                couponService.deleteByCode(coupon.getCouponCode());
                session.putValue(req, "success", "Coupon deleted successfully!");
            }
        } catch (NumberFormatException e) {
            session.putValue(req, "error", "Invalid coupon ID format.");
        } catch (IllegalArgumentException e) {
            session.putValue(req, "error", e.getMessage());
        } catch (Exception e) {
            session.putValue(req, "error", "Error deleting coupon: " + e.getMessage());
        }
        listCoupons(req);
        req.getRequestDispatcher("/views/admin/coupon-management.jsp").forward(req, resp);
    }

    private void showEditCouponForm(HttpServletRequest req) {
        String couponIdStr = req.getParameter("couponId");
        if (couponIdStr == null || couponIdStr.trim().isEmpty()) {
            session.putValue(req, "error", "Coupon ID is required for editing.");
            listCoupons(req);
            return;
        }

        try {
            Long couponId = Long.parseLong(couponIdStr);
            Coupon coupon = couponService.getByID(couponId);
            if (coupon != null) {
                // Format dates for datetime-local input
                req.setAttribute("formattedStartDate", coupon.getStartDate().format(DATE_TIME_FORMATTER));
                req.setAttribute("formattedEndDate", coupon.getEndDate().format(DATE_TIME_FORMATTER));
                req.setAttribute("editCoupon", coupon);
            } else {
                session.putValue(req, "error", "Coupon not found for editing.");
            }
        } catch (NumberFormatException e) {
            session.putValue(req, "error", "Invalid coupon ID format.");
        } catch (Exception e) {
            session.putValue(req, "error", "Error retrieving coupon for editing: " + e.getMessage());
        }
        listCoupons(req);
    }

    private boolean isEmpty(String... values) {
        for (String value : values) {
            if (value == null || value.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}