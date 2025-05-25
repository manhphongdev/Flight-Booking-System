<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
<style>
    .sidebar {
        height: 100vh;
        width: 250px;
        position: fixed;
        top: 0;
        left: 0;
        background-color: #1a2b4f;
        padding-top: 20px;
        box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
    }
    .sidebar .accordion-button {
        background-color: transparent;
        color: #fff;
        font-weight: 600;
        padding: 15px 20px;
    }
    .sidebar .accordion-button::after {
        filter: invert(1);
    }
    .sidebar .accordion-button:not(.collapsed) {
        background-color: #17a2b8;
        color: #fff;
    }
    .sidebar a {
        color: #d1d4da;
        display: block;
        padding: 10px 30px;
        text-decoration: none;
        transition: background-color 0.3s;
    }
    .sidebar a:hover {
        background-color: #17a2b8;
        color: #fff;
    }
    .sidebar a.active {
        background-color: #17a2b8;
        color: #fff;
    }
</style>

<!-- Sidebar-Menu -->
<div class="sidebar">
    <h3 class="text-white text-center mb-4">Admin Dashboard</h3>
    <div class="accordion" id="sidebarAccordion">
        <!-- Overview -->
        <div class="accordion-item bg-transparent border-0">
            <h2 class="accordion-header">
                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#overviewCollapse">
                    <i class="bi bi-house me-2"></i> Overview
                </button>
            </h2>
            <div id="overviewCollapse" class="accordion-collapse collapse show" data-bs-parent="#sidebarAccordion">
                <div class="accordion-body p-0">
                    <a href="admin_dashboard.jsp" class="active"><i class="bi bi-speedometer2 me-2"></i> Dashboard</a>
                </div>
            </div>
        </div>
        <!-- User Management -->
        <div class="accordion-item bg-transparent border-0">
            <h2 class="accordion-header">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#userCollapse">
                    <i class="bi bi-people me-2"></i> User Management
                </button>
            </h2>
            <div id="userCollapse" class="accordion-collapse collapse" data-bs-parent="#sidebarAccordion">
                <div class="accordion-body p-0">
                    <a href="admin_users.jsp"><i class="bi bi-person me-2"></i> Users</a>
                    <a href="admin_roles_permissions.jsp"><i class="bi bi-shield-lock me-2"></i> Roles & Permissions</a>
                </div>
            </div>
        </div>
        <!-- Flight Operations -->
        <div class="accordion-item bg-transparent border-0">
            <h2 class="accordion-header">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flightCollapse">
                    <i class="bi bi-airplane me-2"></i> Flight Operations
                </button>
            </h2>
            <div id="flightCollapse" class="accordion-collapse collapse" data-bs-parent="#sidebarAccordion">
                <div class="accordion-body p-0">
                    <a href="admin_flights.jsp"><i class="bi bi-airplane-fill me-2"></i> Flights</a>
                    <a href="admin_flight_schedule.jsp"><i class="bi bi-calendar me-2"></i> Flight Schedule</a>
                    <a href="admin_seats.jsp"><i class="bi bi-grid me-2"></i> Seats</a>
                    <a href="admin_booking.jsp"><i class="bi bi-ticket me-2"></i> Booking</a>
                    <a href="admin_booking_seats.jsp"><i class="bi bi-grid-3x3 me-2"></i> Booking Seats</a>
                    <a href="admin_passenger.jsp"><i class="bi bi-person-circle me-2"></i> Passenger</a>
                    <a href="admin_airlines.jsp"><i class="bi bi-building me-2"></i> Airlines</a>
                    <a href="admin_airport.jsp"><i class="bi bi-geo-alt me-2"></i> Airport</a>
                </div>
            </div>
        </div>
        <!-- Financial & Analytics -->
        <div class="accordion-item bg-transparent border-0">
            <h2 class="accordion-header">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#financeCollapse">
                    <i class="bi bi-wallet me-2"></i> Financial & Analytics
                </button>
            </h2>
            <div id="financeCollapse" class="accordion-collapse collapse" data-bs-parent="#sidebarAccordion">
                <div class="accordion-body p-0">
                    <a href="admin_coupons.jsp"><i class="bi bi-ticket-perforated me-2"></i> Coupons</a>
                    <a href="admin_payments.jsp"><i class="bi bi-currency-dollar me-2"></i> Payments</a>
                    <a href="admin_logs.jsp"><i class="bi bi-journal-text me-2"></i> Logs</a>
                </div>
            </div>
        </div>
        <!-- Account -->
        <div class="accordion-item bg-transparent border-0">
            <h2 class="accordion-header">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#accountCollapse">
                    <i class="bi bi-person-circle me-2"></i> Account
                </button>
            </h2>
            <div id="accountCollapse" class="accordion-collapse collapse" data-bs-parent="#sidebarAccordion">
                <div class="accordion-body p-0">
                    <a href="logout.jsp"><i class="bi bi-box-arrow-right me-2"></i> Logout</a>
                </div>
            </div>
        </div>
    </div>
</div>