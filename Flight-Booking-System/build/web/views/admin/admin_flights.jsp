<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Dashboard - Flights</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Bootstrap Icons -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
        <!-- Google Fonts: Poppins -->
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
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
                padding: 30px;
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
                max-width: 400px;
            }
            .custom-search-input {
                flex: 1;
                padding: 10px 15px;
                border: 1px solid #ccc;
                border-radius: 6px;
                font-size: 16px;
                outline: none;
                transition: border-color 0.3s ease, box-shadow 0.3s ease;
            }
            .custom-search-input:focus {
                border-color: #28a745;
                box-shadow: 0 0 5px rgba(40, 167, 69, 0.3);
            }
            .custom-search-button {
                padding: 10px 20px;
                background-color: #138496;
                color: #fff;
                border: none;
                border-radius: 6px;
                font-size: 16px;
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
            .sort-icon {
                cursor: pointer;
                margin-left: 5px;
                text-decoration: none;
                color: #fff;
            }
            .sort-icon.asc::after {
                content: '\25B2'; /* Up arrow */
            }
            .sort-icon.desc::after {
                content: '\25BC'; /* Down arrow */
            }
            .sort-icon:hover {
                color: #e0e0e0;
            }
            @media (max-width: 768px) {
                .search-header-container {
                    flex-direction: column;
                    align-items: flex-start;
                    gap: 15px;
                }
                .custom-search-form {
                    width: 100%;
                    max-width: none;
                }
                .custom-search-button {
                    width: 100%;
                    padding: 10px;
                    font-size: 14px;
                }
            }
        </style>
    </head>
    <body>
        <!-- Sidebar -->
        <c:set var="activePage" value="flights" scope="request"/>
        <%@ include file="sidebar.jsp" %>

        <!-- Main Content -->
        <div class="content">
            <h1>Flights Management</h1>

            <!-- Flights Table -->
            <div class="col-12 mt-4">
                <div class="row mb-3">
                    <div class="col">
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addFlightModal">
                            <i class="bi bi-plus-circle me-2"></i> Add Flight
                        </button>
                    </div>
                </div>
                <div class="search-header-container">
                    <h3 class="search-header-title">Flights List</h3>
                    <form method="GET" action="/flights/admin/flights" class="custom-search-form">
                        <input type="hidden" name="action" value="findByAirlineId">
                        <input type="search" id="custom-search-input" name="airlineId" placeholder="Search by airline ID" class="custom-search-input" value="${param.airlineId}">
                        <button type="submit" class="custom-search-button"><i class="bi bi-search me-2"></i>Search</button>
                    </form>
                </div>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th style="width: 4vw">
                                ID
                                <a href="/flights/admin/flights?action=sort&sortBy=flight_id&sortOrder=${param.sortBy == 'flight_id' && param.sortOrder == 'ASC' ? 'DESC' : 'ASC'}&airlineId=${param.airlineId}" class="sort-icon ${param.sortBy == 'flight_id' ? (param.sortOrder == 'ASC' ? 'asc' : 'desc') : ''}"></a>
                            </th>
                            <th style="width: 10vw">
                                Airline ID
                                <a href="/flights/admin/flights?action=sort&sortBy=airline_id&sortOrder=${param.sortBy == 'airline_id' && param.sortOrder == 'ASC' ? 'DESC' : 'ASC'}&airlineId=${param.airlineId}" class="sort-icon ${param.sortBy == 'airline_id' ? (param.sortOrder == 'ASC' ? 'asc' : 'desc') : ''}"></a>
                            </th>
                            <th>
                                Economy Price
                                <a href="/flights/admin/flights?action=sort&sortBy=economyPrice&sortOrder=${param.sortBy == 'economyPrice' && param.sortOrder == 'ASC' ? 'DESC' : 'ASC'}&airlineId=${param.airlineId}" class="sort-icon ${param.sortBy == 'economyPrice' ? (param.sortOrder == 'ASC' ? 'asc' : 'desc') : ''}"></a>
                            </th>
                            <th>
                                Business Price
                                <a href="/flights/admin/flights?action=sort&sortBy=businessPrice&sortOrder=${param.sortBy == 'businessPrice' && param.sortOrder == 'ASC' ? 'DESC' : 'ASC'}&airlineId=${param.airlineId}" class="sort-icon ${param.sortBy == 'businessPrice' ? (param.sortOrder == 'ASC' ? 'asc' : 'desc') : ''}"></a>
                            </th>
                            <th>
                                Created At
                                <a href="/flights/admin/flights?action=sort&sortBy=created_at&sortOrder=${param.sortBy == 'created_at' && param.sortOrder == 'ASC' ? 'DESC' : 'ASC'}&airlineId=${param.airlineId}" class="sort-icon ${param.sortBy == 'created_at' ? (param.sortOrder == 'ASC' ? 'asc' : 'desc') : ''}"></a>
                            </th>
                            <th>
                                Updated At
                                <a href="/flights/admin/flights?action=sort&sortBy=updated_at&sortOrder=${param.sortBy == 'updated_at' && param.sortOrder == 'ASC' ? 'DESC' : 'ASC'}&airlineId=${param.airlineId}" class="sort-icon ${param.sortBy == 'updated_at' ? (param.sortOrder == 'ASC' ? 'asc' : 'desc') : ''}"></a>
                            </th>
                            <th style="width: 15vw">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="flight" items="${flights}">
                            <tr>
                                <td><c:out value="${flight.flightId}"></c:out></td>
                                <td><c:out value="${flight.airlineId}"></c:out></td>
                                <td><c:out value="${flight.economyPrice}"></c:out></td>
                                <td><c:out value="${flight.businessPrice}"></c:out></td>
                                <td><c:out value="${flight.createdAt}"></c:out></td>
                                <td><c:out value="${flight.updatedAt}"></c:out></td>
                                <td>
                                    <button class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#editFlightModal-${flight.flightId}">
                                        <i class="bi bi-pencil"></i> Edit
                                    </button>
                                    <button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal-${flight.flightId}">
                                        Delete
                                    </button>

                                    <!-- Delete Confirmation Modal -->
                                    <div class="form-confirm-delete">
                                        <form method="POST" action="/flights/admin/flights">
                                            <input type="hidden" name="action" value="deleteFlight">
                                            <input type="hidden" name="flightIdDelete" value="${flight.flightId}">
                                            <div class="modal fade" id="deleteConfirmModal-${flight.flightId}" tabindex="-1" aria-labelledby="deleteConfirmModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h1 class="modal-title fs-5" id="deleteConfirmModalLabel">Confirm</h1>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div>Do you want to delete this flight: ID ${flight.flightId}?</div>
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
                                    <div class="modal fade" id="editFlightModal-${flight.flightId}" tabindex="-1" aria-labelledby="editFlightModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="editFlightModalLabel">Edit Flight</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <form action="/flights/admin/flights" method="POST">
                                                        <input type="hidden" name="action" value="updateFlight">
                                                        <input type="hidden" name="flightId" value="${flight.flightId}">
                                                        <c:if test="${not empty updateFailed}">
                                                            <div class="alert alert-danger">${updateFailed}</div>
                                                        </c:if>
                                                        <div class="mb-3">
                                                            <label for="editAirlineId-${flight.flightId}" class="form-label">Airline ID</label>
                                                            <input type="number" class="form-control" id="editAirlineId-${flight.flightId}" name="newAirlineId" value="${flight.airlineId}" required>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="editEconomyPrice-${flight.flightId}" class="form-label">Economy Price</label>
                                                            <input type="number" step="0.01" class="form-control" id="editEconomyPrice-${flight.flightId}" name="newEconomyPrice" value="${flight.economyPrice}" required>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="editBusinessPrice-${flight.flightId}" class="form-label">Business Price</label>
                                                            <input type="number" step="0.01" class="form-control" id="editBusinessPrice-${flight.flightId}" name="newBusinessPrice" value="${flight.businessPrice}" required>
                                                        </div>
                                                        <button type="submit" class="btn btn-primary">Save Changes</button>
                                                    </form>
                                                    <c:if test="${not empty updateFailed}">
                                                        <script>
                                                            document.addEventListener('DOMContentLoaded', function () {
                                                                var editFlightModal = new bootstrap.Modal(document.getElementById('editFlightModal-${flight.flightId}'));
                                                                editFlightModal.show();
                                                            });
                                                        </script>
                                                    </c:if>
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

            <!-- Add Flight Modal -->
            <div class="modal fade" id="addFlightModal" tabindex="-1" aria-labelledby="addFlightModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addFlightModalLabel">Add New Flight</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="/flights/admin/flights" method="POST" name="add-flight">
                                <input type="hidden" name="action" value="addFlight">
                                <c:if test="${not empty addFlightFailed}">
                                    <div class="alert alert-danger" style="color: red">${addFlightFailed}</div>
                                </c:if>
                                <div class="mb-3">
                                    <label for="airlineId" class="form-label">Airline ID</label>
                                    <input type="number" class="form-control" id="airlineId" name="airlineId" required>
                                </div>
                                <div class="mb-3">
                                    <label for="economyPrice" class="form-label">Economy Price</label>
                                    <input type="number" step="0.01" class="form-control" id="economyPrice" name="economyPrice" required>
                                </div>
                                <div class="mb-3">
                                    <label for="businessPrice" class="form-label">Business Price</label>
                                    <input type="number" step="0.01" class="form-control" id="businessPrice" name="businessPrice" required>
                                </div>
                                <button type="submit" class="btn btn-primary">Add Flight</button>
                            </form>
                            <c:if test="${not empty addFlightFailed}">
                                <script>
                                    document.addEventListener('DOMContentLoaded', function () {
                                        var addFlightModal = new bootstrap.Modal(document.getElementById('addFlightModal'));
                                        addFlightModal.show();
                                    });
                                </script>
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