<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Flight Management - Admin Dashboard</title>
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
                    display: none;
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
            <h1>Flight Management</h1>

            <!-- Flight Table -->
            <div class="col-12 mt-4">
                <div class="row mb-3">
                    <div class="col">
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addFlightModal">
                            <i class="bi bi-plus-circle me-2"></i> Add Flight
                        </button>
                    </div>
                </div>
                <c:if test="${not empty sessionScope.addSusscess}">
                    <div class="alert alert-danger">${sessionScope.addSusscess}</div>
                    <c:remove var="addSusscess" scope="session"/>
                </c:if>
                <c:if test="${not empty sessionScope.airlineNameNotExists}">
                    <div class="alert alert-danger">${sessionScope.airlineNameNotExists}</div>
                    <c:remove var="airlineNameNotExists" scope="session"></c:remove>
                </c:if>

                <c:if test="${not empty sessionScope.invalidValueNumber}">
                    <div class="alert alert-danger">${sessionScope.invalidValueNumber}</div>
                    <c:remove var="invalidValueNumber" scope="session"></c:remove>
                </c:if>

                <c:if test="${not empty sessionScope.deleteSuccess}">
                    <div class="alert alert-danger">${sessionScope.deleteSuccess}</div>
                    <c:remove var="deleteSuccess" scope="session"/>
                </c:if>
                <c:if test="${not empty sessionScope.updateSuccess}">
                    <div class="alert alert-danger">${sessionScope.updateSuccess}</div>
                    <c:remove var="updateSuccess" scope="session"/>
                </c:if>
                <div class="search-filters-container mb-4 p-3 bg-light border rounded">
                    <form method="GET" action="${pageContext.request.contextPath}/flightmanager/flights" class="row g-3 align-items-end">
                        <!-- Input tìm kiếm -->
                        <div class="col-md-6 col-lg-4">
                            <label for="searchTermInput" class="form-label fw-semibold">Keyword</label>
                            <input type="text" class="form-control" id="searchTermInput" name="search" placeholder="Search by flight ID, airline ID" value="${param.search}">
                        </div>

                        <!-- Sắp xếp theo -->
                        <div class="col-md-6 col-lg-3">
                            <label for="sortBySelectFilter" class="form-label fw-semibold">Order By</label>
                            <select class="form-select" id="sortBySelectFilter" name="sortBy">
                                <option value="flightIdASC" ${param.sortBy == 'flightIdASC' ? 'selected' : ''}>Flight ID (A-Z)</option>
                                <option value="flightIdDESC" ${param.sortBy == 'flightIdDESC' ? 'selected' : ''}>Flight ID (Z-A)</option>
                                <option value="economyPriceASC" ${param.sortBy == 'economyPriceASC' ? 'selected' : ''}>Economy Price (Low-High)</option>
                                <option value="economyPriceDESC" ${param.sortBy == 'economyPriceDESC' ? 'selected' : ''}>Economy Price (High-Low)</option>
                            </select>
                        </div>

                        <!-- Nút Tìm kiếm -->
                        <div class="col-md-6 col-lg-2">
                            <button type="submit" class="btn btn-primary w-100">
                                <i class="bi bi-search me-1"></i> Search
                            </button>
                        </div>
                    </form>
                </div>
                <h3 class="search-header-title">Flight List</h3>

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
                                <th style="width: 7%">Flight ID</th>
                                <th>Airline</th>
                                <th>Economy Price(VNĐ)</th>
                                <th>Business Price(VNĐ)</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="flight" items="${flights}">
                                <tr>
                                    <td><c:out value="${flight.flightId}"/></td>
                                    <td><c:out value="${flight.airlineName}"/></td>
                                    <td><c:out value="${flight.economyPrice}" /></td>
                                    <td><c:out value="${flight.businessPrice}" /></td>
                                    <td>
                                        <button class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#editFlightModal-${flight.flightId}">
                                            <i class="bi bi-pencil"></i> Edit
                                        </button>
                                        <button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal-${flight.flightId}">
                                            <i class="bi bi-trash"></i> Delete
                                        </button>
                                        <!-- Delete Confirmation Modal -->
                                        <div class="form-confirm-delete">
                                            <form method="POST" action="${pageContext.request.contextPath}/flightmanager/flights">
                                                <input type="hidden" name="action" value="delete">
                                                <input type="hidden" name="idDelete" value="${flight.flightId}">
                                                <div class="modal fade" id="deleteConfirmModal-${flight.flightId}" tabindex="-1" aria-labelledby="deleteConfirmModalLabel-${flight.flightId}" aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h1 class="modal-title fs-5" id="deleteConfirmModalLabel-${flight.flightId}">Confirm</h1>
                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div>Do you want to delete this flight with ID: ${flight.flightId}?</div>
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
                                        <!-- Edit Flight Modal -->
                                        <div class="modal fade" id="editFlightModal-${flight.flightId}" tabindex="-1" aria-labelledby="editFlightModalLabel-${flight.flightId}" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="editFlightModalLabel-${flight.flightId}">Edit Flight</h5>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form action="${pageContext.request.contextPath}/flightmanager/flights" method="POST">
                                                            <input type="hidden" name="action" value="updateFlight">
                                                            <input type="hidden" name="flightId" value="${flight.flightId}">
                                                            <c:if test="${not empty errorNull}">
                                                                <div class="alert alert-danger">${errorNull}</div>
                                                            </c:if>
                                                            <div class="mb-3">
                                                                <label for="editAirlineName-${flight.flightId}" class="form-label">Airline</label>
                                                                <input type="text" class="form-control" id="editAirlineName-${flight.flightId}" name="airlineName" value="${flight.airlineName}" required>
                                                            </div>
                                                            <div class="mb-3">
                                                                <label for="editEconomyPrice-${flight.flightId}" class="form-label">Economy Price(VNĐ)</label>
                                                                <input type="number" step="10000" min="0" class="form-control" id="editEconomyPrice-${flight.flightId}" name="economyPrice" value="${flight.economyPrice}" required>
                                                            </div>
                                                            <div class="mb-3">
                                                                <label for="editBusinessPrice-${flight.flightId}" class="form-label">Business Price(VNĐ)</label>
                                                                <input type="number" step="10000" min="0" class="form-control" id="editBusinessPrice-${flight.flightId}" name="businessPrice" value="${flight.businessPrice}" required>
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

            <!-- Add Flight Modal -->
            <div class="modal fade" id="addFlightModal" tabindex="-1" aria-labelledby="addFlightModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addFlightModalLabel">Add New Flight</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="${pageContext.request.contextPath}/flightmanager/flights" method="POST">
                                <input type="hidden" name="action" value="addFlight">


                                <div class="mb-3">
                                    <label for="airlineName" class="form-label">Airline Name</label>
                                    <input type="text" class="form-control" id="airlineName" name="airlineName" required>
                                </div>
                                <div class="mb-3">
                                    <label for="economyPrice" class="form-label">Economy Price(VNĐ)</label>
                                    <input type="number" step="10000" min="0" class="form-control" id="economyPrice" name="economyPrice" required>
                                </div>
                                <div class="mb-3">
                                    <label for="businessPrice" class="form-label">Business Price(VNĐ)</label>
                                    <input type="number" step="10000" min="0" class="form-control" id="businessPrice" name="businessPrice" required>
                                </div>
                                <button type="submit" class="btn btn-primary">Add Flight</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <c:if test="${not empty sessionScope.airlineNameNotExists || not empty sessionScope.invalidValueNumber} ">
                <script>
                    document.addEventListener('DOMContentLoaded', function () {
                        var addFlightModal = new bootstrap.Modal(document.getElementById('addFlightModal'));
                        addFlightModal.show();
                    });
                </script>  

            </c:if>


            <!-- Bootstrap JS -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        </div>
    </body>
</html>