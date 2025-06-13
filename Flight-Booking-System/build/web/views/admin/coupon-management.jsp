<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.ZoneId" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="model.Coupon" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Coupon Management - Admin Dashboard</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap Icons -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
        <!-- Google Fonts: Poppins -->
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="../resource/css/dashboard.css"/>
        <style>
            body {
                font-family: 'Poppins', sans-serif;
                background-color: #f8f9fa;
            }
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
            .content {
                margin-left: 250px;
                padding: 20px;
            }
            .card {
                border: none;
                border-radius: 10px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                margin-bottom: 20px;
            }
            .card-title {
                font-weight: 600;
                color: #1a2b4f;
            }
            .table {
                border-radius: 10px;
                overflow: hidden;
            }
            .table thead {
                background-color: #17a2b8;
                color: #fff;
            }
            h1, h3 {
                color: #1a2b4f;
                font-weight: 600;
            }
            .btn-primary {
                background-color: #17a2b8;
                border-color: #17a2b8;
            }
            .btn-primary:hover {
                background-color: #138496;
                border-color: #138496;
            }
            .btn-danger {
                background-color: #dc3545;
                border-color: #dc3545;
            }
            .btn-danger:hover {
                background-color: #c82333;
                border-color: #c82333;
            }
            .search-header-container {
                display: flex;
                align-items: center;
                justify-content: space-between;
                gap: 20px;
                margin-bottom: 20px;
                flex-wrap: wrap;
            }
            .search-header-title {
                margin: 0;
                font-size: 24px;
                font-weight: 600;
                color: #333;
            }
            .custom-search-form {
                display: flex;
                align-items: center;
                gap: 10px;
                flex: 1;
                max-width: 300px;
                flex-wrap: wrap;
            }
            .custom-search-input {
                flex: 1;
                padding: 10px 15px;
                border: 1px solid #ccc;
                border-radius: 6px;
                font-size: 14px;
                outline: none;
                transition: border-color 0.3s ease, box-shadow 0.3s ease;
                min-width: 150px;
            }
            .custom-search-input:focus {
                border-color: #28a745;
                box-shadow: 0 0 5px rgba(40, 167, 69, 0.3);
            }
            .custom-search-button {
                padding: 8px 16px;
                background-color: #138496;
                color: #fff;
                border: none;
                border-radius: 6px;
                font-size: 14px;
                font-weight: 500;
                cursor: pointer;
                display: flex;
                align-items: center;
                gap: 8px;
                transition: all 0.3s ease;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            .custom-search-button:hover {
                background-color: #218838;
                transform: translateY(-1px);
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
            }
            .custom-search-button:active {
                transform: translateY(0);
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }
            .sort-button {
                background-color: transparent;
                border: none;
                color: #fff;
                font-size: 14px;
                padding: 0 5px;
                line-height: 1;
            }
            .sort-button:hover {
                color: #e0e0e0;
            }
            @media (max-width: 768px) {
                .content {
                    margin-left: 0;
                    padding: 15px;
                }
                .sidebar {
                    display: none; /* Hide sidebar on mobile */
                }
                .search-header-container {
                    flex-direction: column;
                    align-items: stretch;
                    gap: 10px;
                }
                .custom-search-form {
                    flex-direction: column;
                    max-width: none;
                }
                .custom-search-input, .custom-search-button {
                    width: 100%;
                    font-size: 13px;
                }
                .table th, .table td {
                    font-size: 12px;
                    padding: 8px;
                }
                .btn-sm {
                    font-size: 12px;
                    padding: 4px 8px;
                }
            }
            @media (max-width: 576px) {
                .pagination {
                    flex-wrap: wrap;
                }
                .pagination .page-item {
                    margin-bottom: 5px;
                }
            }
        </style>
    </head>
    <body>
        <!-- Sidebar -->
        <%@ include file="sidebar.jsp" %>

        <!-- Main Content -->
        <div class="content">
            <h1>Coupon Management</h1>

            <!-- Coupon Table -->
            <div class="col-12 mt-4">
                <div class="row mb-3">
                    <div class="col">
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addCouponModal">
                            <i class="bi bi-plus-circle me-2"></i> Add Coupon
                        </button>
                    </div>
                </div>

                <div class="search-filters-container mb-4 p-3 bg-light border rounded">
                    <form method="GET" action="${pageContext.request.contextPath}/bookingmanager/coupon" class="row g-3 align-items-end">
                        <!-- Search Input -->
                        <div class="col-md-6 col-lg-4">
                            <label for="searchTermInput" class="form-label fw-semibold">Keyword</label>
                            <input type="text" class="form-control" id="searchTermInput" name="search" placeholder="Search by coupon code" value="${param.search}">
                        </div>

                        <!-- Status Filter -->
                        <div class="col-md-6 col-lg-3">
                            <label for="statusSelectFilter" class="form-label fw-semibold">Status</label>
                            <select class="form-select" id="statusSelectFilter" name="statusFilter">
                                <option value="all">All</option>
                                <option value="active" ${param.statusFilter eq 'active' ? 'selected' : ''}>Active</option>
                                <option value="inactive" ${param.statusFilter eq 'inactive' ? 'selected' : ''}>Inactive</option>
                            </select>
                        </div>

                        <!-- Sort By -->
                        <div class="col-md-6 col-lg-3">
                            <label for="sortBySelectFilter" class="form-label fw-semibold">Order By</label>
                            <select class="form-select" id="sortBySelectFilter" name="sortBy">
                                <option value="couponCodeASC" ${param.sortBy == 'couponCodeASC' ? 'selected' : ''}>Coupon Code (A-Z)</option>
                                <option value="couponCodeDESC" ${param.sortBy == 'couponCodeDESC' ? 'selected' : ''}>Coupon Code (Z-A)</option>
                                <option value="createdAtASC" ${param.sortBy == 'createdAtASC' ? 'selected' : ''}>Created At (Oldest)</option>
                                <option value="createdAtDESC" ${param.sortBy == 'createdAtDESC' ? 'selected' : ''}>Created At (Newest)</option>
                            </select>
                        </div>

                        <!-- Search Button -->
                        <div class="col-md-6 col-lg-2">
                            <button type="submit" class="btn btn-primary w-100">
                                <i class="bi bi-search me-1"></i> Search
                            </button>
                        </div>
                    </form>
                </div>
                <h3 class="search-header-title">Coupon List</h3>

                <c:if test="${not empty success}">
                    <div class="alert alert-success">${success}</div>
                </c:if>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger">${error}</div>
                </c:if>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th style="width: 7%">ID</th>
                                <th>Coupon Code</th>
                                <th>Discount (%)</th>
                                <th>Max Usage</th>
                                <th>Used Count</th>
                                <th>Start Date</th>
                                <th>End Date</th>
                                <th>Status</th>
                                <th>Created At</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="coupon" items="${coupons}">
                                <tr>
                                    <td><c:out value="${coupon.couponId}"/></td>
                                    <td><c:out value="${coupon.couponCode}"/></td>
                                    <td><c:out value="${coupon.discountPercentage}"/></td>
                                    <td><c:out value="${coupon.maxUsage}"/></td>
                                    <td><c:out value="${coupon.usedCount}"/></td>
                                    <td><c:out value="${coupon.startDate}"/></td>
                                    <td><c:out value="${coupon.endDate}"/></td>
                                    <td><c:out value="${coupon.status}"/></td>
                                    <td><c:out value="${coupon.createdAt}"/></td>
                                    <td>
                                        <button class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#editCouponModal-${coupon.couponId}">
                                            <i class="bi bi-pencil"></i> Edit
                                        </button>
                                        <button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal-${coupon.couponId}">
                                            <i class="bi bi-trash"></i> Delete
                                        </button>
                                        <!-- Delete Confirmation Modal -->
                                        <div class="form-confirm-delete">
                                            <form method="POST" action="${pageContext.request.contextPath}/bookingmanager/coupon">
                                                <input type="hidden" name="action" value="delete">
                                                <input type="hidden" name="idDelete" value="${coupon.couponId}">
                                                <div class="modal fade" id="deleteConfirmModal-${coupon.couponId}" tabindex="-1" aria-labelledby="deleteConfirmModalLabel-${coupon.couponId}" aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h1 class="modal-title fs-5" id="deleteConfirmModalLabel-${coupon.couponId}">Confirm</h1>
                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div>Do you want to delete this coupon with ID: ${coupon.couponId}?</div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                                                                <button type="submit" class="btn btn-primary">Yes</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>

                                        <!-- Edit Coupon Modal -->
                                        <div class="modal fade" id="editCouponModal-${coupon.couponId}" tabindex="-1" aria-labelledby="editCouponModalLabel" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="editCouponModal-${coupon.couponId}">Edit Coupon</h5>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form action="${pageContext.request.contextPath}/bookingmanager/coupon" method="POST">
                                                            <input type="hidden" name="action" value="updateCoupon">
                                                            <input type="hidden" name="couponIdEdit" value="${coupon.couponId}">
                                                            <c:if test="${not empty errorNull}">
                                                                <div class="alert alert-danger">${errorNull}</div>
                                                            </c:if>
                                                            <div class="mb-3">
                                                                <label for="editCouponCode" class="form-label">Coupon Code</label>
                                                                <input type="text" class="form-control" id="editCouponCode" name="couponCode" value="${coupon.couponCode}" required>
                                                            </div>
                                                            <div class="mb-3">
                                                                <label for="editDiscountPercentage" class="form-label">Discount Percentage</label>
                                                                <input type="number" step="0.01" class="form-control" id="editDiscountPercentage" name="discountPercentage" value="${coupon.discountPercentage}" required>
                                                            </div>
                                                            <div class="mb-3">
                                                                <label for="editMaxUsage" class="form-label">Max Usage</label>
                                                                <input type="number" class="form-control" id="editMaxUsage" name="maxUsage" value="${coupon.maxUsage}">
                                                            </div>
                                                            <div class="mb-3">
                                                                <label for="editStartDate" class="form-label">Start Date</label>
                                                                <input type="datetime-local" class="form-control" id="editStartDate" name="startDate" value="${coupon.startDate}" required>
                                                            </div>
                                                            <div class="mb-3">
                                                                <label for="editEndDate" class="form-label">End Date</label>
                                                                <input type="datetime-local" class="form-control" id="editEndDate" name="endDate" value="${coupon.endDate}" required>
                                                            </div>
                                                            <div class="mb-3">
                                                                <label for="editStatus" class="form-label">Status</label>
                                                                <select class="form-control" id="editStatus" name="status" required>
                                                                    <option value="active" ${coupon.status eq 'active' ? 'selected' : ''}>Active</option>
                                                                    <option value="inactive" ${coupon.status eq 'inactive' ? 'selected' : ''}>Inactive</option>
                                                                </select>
                                                            </div>
                                                            <button type="submit" class="btn btn-primary">Save Changes</button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- Add Coupon Modal -->
            <div class="modal fade" id="addCouponModal" tabindex="-1" aria-labelledby="addCouponModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addCouponModalLabel">Add New Coupon</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="${pageContext.request.contextPath}/bookingmanager/coupon" method="POST">
                                <input type="hidden" name="action" value="addCoupon">
                                <c:if test="${not empty sessionScope.errorNull}">
                                    <div class="alert alert-danger">${sessionScope.errorNull}</div>
                                </c:if>
                                <div class="mb-3">
                                    <label for="couponCode" class="form-label">Coupon Code</label>
                                    <input type="text" class="form-control" id="couponCode" name="couponCode" required>
                                </div>
                                <c:if test="${not empty sessionScope.errorCouponCodeExists}">
                                    <div class="alert alert-danger">${sessionScope.errorCouponCodeExists}</div>
                                </c:if>
                                <div class="mb-3">
                                    <label for="discountPercentage" class="form-label">Discount Percentage</label>
                                    <input type="number" step="0.01" class="form-control" id="discountPercentage" name="discountPercentage" required>
                                </div>
                                <div class="mb-3">
                                    <label for="maxUsage" class="form-label">Max Usage</label>
                                    <input type="number" class="form-control" id="maxUsage" name="maxUsage">
                                </div>
                                <div class="mb-3">
                                    <label for="startDate" class="form-label">Start Date</label>
                                    <input type="datetime-local" class="form-control" id="startDate" name="startDate" required>
                                </div>
                                <div class="mb-3">
                                    <label for="endDate" class="form-label">End Date</label>
                                    <input type="datetime-local" class="form-control" id="endDate" name="endDate" required>
                                </div>
                                <div class="mb-3">
                                    <label for="status" class="form-label">Status</label>
                                    <select class="form-control" id="status" name="status" required>
                                        <option value="active">Active</option>
                                        <option value="inactive">Inactive</option>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-primary">Add Coupon</button>
                            </form>
                            <c:if test="${not empty sessionScope.errorCouponCodeExists || not empty sessionScope.errorNull}">
                                <script>
                                    document.addEventListener('DOMContentLoaded', function () {
                                        var addCouponModal = new bootstrap.Modal(document.getElementById('addCouponModal'));
                                        addCouponModal.show();
                                    });
                                </script>
                                <c:remove var="errorCouponCodeExists" scope="session"/>
                                <c:remove var="errorNull" scope="session"/>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Bootstrap JS -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        </div>
    </body>
</html>

<%!
    // Custom function to format LocalDateTime
    String formatLocalDateTime(LocalDateTime dateTime) {
        if (dateTime == null) return "";
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
%>