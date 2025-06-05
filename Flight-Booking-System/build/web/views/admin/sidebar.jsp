<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="sidebar">
    <h3 class="text-white text-center mb-4">Admin Dashboard</h3>
    <div class="accordion" id="sidebarAccordion">
        <div class="accordion-item bg-transparent border-0">
            <h2 class="accordion-header">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#overviewCollapse">
                    <i class="bi bi-house me-2"></i> Overview
                </button>
            </h2>
            <div id="overviewCollapse" class="accordion-collapse collapse" data-bs-parent="#sidebarAccordion">
                <div class="accordion-body p-0">
                    <a href="admin_dashboard.jsp" class="${activePage == 'dashboard' ? 'active' : ''}"><i class="bi bi-speedometer2 me-2"></i> Dashboard</a>
                </div>
            </div>
        </div>
        <div class="accordion-item bg-transparent border-0">
            <h2 class="accordion-header">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#userCollapse">
                    <i class="bi bi-people me-2"></i> User Management
                </button>
            </h2>
            <div id="userCollapse" class="accordion-collapse collapse" data-bs-parent="#sidebarAccordion">
                <div class="accordion-body p-0">
                    <a href="${pageContext.request.contextPath}/admin/users" class="${activePage == 'users' ? 'active' : ''}"><i class="bi bi-person me-2"></i> Users</a>
                    <a href="/flights/admin/access-manager" class="${activePage == 'access-manager' ? 'active' : ''}"><i class="bi bi-shield-lock me-2"></i> Roles & Permissions</a>
                </div>
            </div>
        </div>
        <div class="accordion-item bg-transparent border-0">
            <h2 class="accordion-header">
                <button class="accordion-button ${activePage == 'flights' || activePage == 'flight-schedule' || activePage == 'seats' || activePage == 'booking' || activePage == 'booking-seats' || activePage == 'passenger' || activePage == 'airlines' || activePage == 'airport' ? '' : 'collapsed'}" type="button" data-bs-toggle="collapse" data-bs-target="#flightCollapse">
                    <i class="bi bi-airplane me-2"></i> Flight Operations
                </button>
            </h2>
            <div id="flightCollapse" class="accordion-collapse collapse ${activePage == 'flights' || activePage == 'flight-schedule' || activePage == 'seats' || activePage == 'booking' || activePage == 'booking-seats' || activePage == 'passenger' || activePage == 'airlines' || activePage == 'airport' ? 'show' : ''}" data-bs-parent="#sidebarAccordion">
                <div class="accordion-body p-0">
                    <a href="/flights/admin/flights" class="${activePage == 'flights' ? 'active' : ''}"><i class="bi bi-airplane-fill me-2"></i> Flights</a>
                    <a href="/flights/admin/flight-schedule" class="${activePage == 'flight-schedule' ? 'active' : ''}"><i class="bi bi-calendar me-2"></i> Flight Schedule</a>
                    <a href="/flights/admin/seats" class="${activePage == 'seats' ? 'active' : ''}"><i class="bi bi-grid me-2"></i> Seats</a>
                    <a href="/flights/admin/booking" class="${activePage == 'booking' ? 'active' : ''}"><i class="bi bi-ticket me-2"></i> Booking</a>
                    <a href="/flights/admin/booking-seats" class="${activePage == 'booking-seats' ? 'active' : ''}"><i class="bi bi-grid-3x3 me-2"></i> Booking Seats</a>
                    <a href="/flights/admin/passenger" class="${activePage == 'passenger' ? 'active' : ''}"><i class="bi bi-person-circle me-2"></i> Passenger</a>
                    <a href="${pageContext.request.contextPath}/flightmanager/airline" class="${activePage == 'airlines' ? 'active' : ''}"><i class="bi bi-building me-2"></i> Airlines</a>
                    <a href="${pageContext.request.contextPath}/flightmanager/airport" class="${activePage == 'airport' ? 'active' : ''}"><i class="bi bi-geo-alt me-2"></i> Airport</a>
                </div>
            </div>
        </div>
        <div class="accordion-item bg-transparent border-0">
            <h2 class="accordion-header">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#financeCollapse">
                    <i class="bi bi-wallet me-2"></i> Financial & Analytics
                </button>
            </h2>
            <div id="financeCollapse" class="accordion-collapse collapse" data-bs-parent="#sidebarAccordion">
                <div class="accordion-body p-0">
                    <a href="admin_coupons.jsp" class="${activePage == 'coupons' ? 'active' : ''}"><i class="bi bi-ticket-perforated me-2"></i> Coupons</a>
                    <a href="admin_payments.jsp" class="${activePage == 'payments' ? 'active' : ''}"><i class="bi bi-currency-dollar me-2"></i> Payments</a>
                    <a href="admin_logs.jsp" class="${activePage == 'logs' ? 'active' : ''}"><i class="bi bi-journal-text me-2"></i> Logs</a>
                </div>
            </div>
        </div>
        <div class="accordion-item bg-transparent border-0">
            <h2 class="accordion-header">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#accountCollapse">
                    <i class="bi bi-person-circle me-2"></i> Account
                </button>
            </h2>
            <div id="accountCollapse" class="accordion-collapse collapse" data-bs-parent="#sidebarAccordion">
                <div class="accordion-body p-0">
                    <a href="logout.jsp" class="${activePage == 'logout' ? 'active' : ''}"><i class="bi bi-box-arrow-right me-2"></i> Logout</a>
                </div>
            </div>
        </div>
    </div>
</div>