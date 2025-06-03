<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Dashboard - Airport</title>
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
        <c:set var="activePage" value="airport" scope="request"/>
        <%@ include file="sidebar.jsp" %>

        <!-- Main Content -->
        <div class="content">
            <h1>Airport Management</h1>

            <!-- Airport Table -->
            <div class="col-12 mt-4">
                <div class="row mb-3">
                    <div class="col">
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addAirportModal">
                            <i class="bi bi-plus-circle me-2"></i> Add Airport
                        </button>
                    </div>
                </div>
                <div class="search-header-container">
                    <h3 class="search-header-title">Airport List</h3>
                    <form method="GET" action="/flights/admin/flight-operator/airport" class="custom-search-form">
                        <input type="hidden" name="action" value="findByCode">
                        <input type="search" id="custom-search-input" name="airportCode" placeholder="Search by airport code" class="custom-search-input" value="${param.airportCode}">
                        <button type="submit" class="custom-search-button"><i class="bi bi-search me-2"></i>Search</button>
                    </form>
                </div>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th style="width: 7vw">
                                Airport Code
                                <a href="/flights/admin/flights?action=sort&sortBy=flight_id&sortOrder=${param.sortBy == 'flight_id' && param.sortOrder == 'ASC' ? 'DESC' : 'ASC'}&airlineId=${param.airlineId}" class="sort-icon ${param.sortBy == 'flight_id' ? (param.sortOrder == 'ASC' ? 'asc' : 'desc') : ''}"></a>
                            </th>
                            <th style="width: 20vw">
                                Airport Name
                                <a href="/flights/admin/flights?action=sort&sortBy=airline_id&sortOrder=${param.sortBy == 'airline_id' && param.sortOrder == 'ASC' ? 'DESC' : 'ASC'}&airlineId=${param.airlineId}" class="sort-icon ${param.sortBy == 'airline_id' ? (param.sortOrder == 'ASC' ? 'asc' : 'desc') : ''}"></a>
                            </th>
                            <th style="width: 14vw">
                                City
                                <a href="/flights/admin/flights?action=sort&sortBy=economyPrice&sortOrder=${param.sortBy == 'economyPrice' && param.sortOrder == 'ASC' ? 'DESC' : 'ASC'}&airlineId=${param.airlineId}" class="sort-icon ${param.sortBy == 'economyPrice' ? (param.sortOrder == 'ASC' ? 'asc' : 'desc') : ''}"></a>
                            </th>
                            <th style="width: 14vw">
                                Country
                                <a href="/flights/admin/flights?action=sort&sortBy=businessPrice&sortOrder=${param.sortBy == 'businessPrice' && param.sortOrder == 'ASC' ? 'DESC' : 'ASC'}&airlineId=${param.airlineId}" class="sort-icon ${param.sortBy == 'businessPrice' ? (param.sortOrder == 'ASC' ? 'asc' : 'desc') : ''}"></a>
                            </th>
                            <th style="width: 14vw">
                                Created At
                                <a href="/flights/admin/flights?action=sort&sortBy=created_at&sortOrder=${param.sortBy == 'created_at' && param.sortOrder == 'ASC' ? 'DESC' : 'ASC'}&airlineId=${param.airlineId}" class="sort-icon ${param.sortBy == 'created_at' ? (param.sortOrder == 'ASC' ? 'asc' : 'desc') : ''}"></a>
                            </th>
                            <th style="width: 10vw">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="airport" items="${airports}">
                            <tr>
                                <td><c:out value="${airport.airportCode}"></c:out></td>
                                <td><c:out value="${airport.airportName}"></c:out></td>
                                <td><c:out value="${airport.city}"></c:out></td>
                                <td><c:out value="${airport.country}"></c:out></td>
                                <td><c:out value="${airport.createAt}"></c:out></td>

                                    <td>
                                        <button class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#editAirportModal-${airport.airportCode}">
                                        <i class="bi bi-pencil"></i> Edit
                                    </button>
                                    <button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal-${airport.airportCode}">
                                        Delete
                                    </button>

                                    <!-- Delete Confirmation Modal -->
                                    <div class="form-confirm-delete">
                                        <form method="POST" action="/flights/admin/flight-operator/airport">
                                            <input type="hidden" name="action" value="deleteAirport">
                                            <input type="hidden" name="codeDelete" value="${airport.airportCode}">
                                            <div class="modal fade" id="deleteConfirmModal-${airport.airportCode}" tabindex="-1" aria-labelledby="deleteConfirmModalLabel" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h1 class="modal-title fs-5" id="deleteConfirmModalLabel">Confirm</h1>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div>Do you want to delete this airport with code: ${airport.airportCode}?</div>
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
                                    <div class="modal fade" id="editAirportModal-${airport.airportCode}" tabindex="-1" aria-labelledby="editAirportModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="editAirportModalLabel">Edit Airport</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <form action="/flights/admin/flight-operator/airport" method="POST">
                                                        <input type="hidden" name="action" value="editAirport">
                                                        <input type="hidden" name="codeUpdate"  value="${airport.airportCode} ">
                                                        <c:if test="${not empty updateFailed}">
                                                            <div class="alert alert-danger">${updateFailed}</div>
                                                        </c:if>
                                                        <div class="mb-3">
                                                            <label for="editAirportCode-${airport.airportCode}"  class="form-label">Airport Code</label>
                                                            <input type="text" maxlength="5" class="form-control" id="editAirportCode-${airport.airportCode}" name="newAirportCode" readonly="" value="${airport.airportCode}" required>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="editName-${airport.airportName}" class="form-label">Name</label>
                                                            <input type="text" maxlength="250" class="form-control" id="editName-${airport.airportName}" name="newAirportName" value="${airport.airportName}" required>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="editCity-${airport.city}" class="form-label">City</label>
                                                            <input type="text" maxlength="250" class="form-control" id="editCity-${airport.city}" name="newCity" value="${airport.city}" required>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="editCountry-${airport.country}" class="form-label">Country</label>
                                                            <input type="text" maxlength="250" class="form-control" id="editCountry-${airport.country}" name="newCountry" value="${airport.country}" required>
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

            <!-- Add Airport Modal -->
            <div class="modal fade" id="addAirportModal" tabindex="-1" aria-labelledby="addAirportModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addAirportModalLabel">Add New Airport</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="/flights/admin/flight-operator/airport" method="POST" name="add-airport">
                                <input type="hidden" name="action" value="addAirport">
                                <c:if test="${not empty addAirportFailed}">
                                    <div class="alert alert-danger" style="color: red">${addAirportFailed}</div>
                                </c:if>
                                <div class="mb-3">
                                    <label for="airportCode" class="form-label">Code</label>
                                    <input type="text" maxlength="4" class="form-control" id="airportCode" name="airportCode" required>
                                </div>
                                <div class="mb-3">
                                    <label for="airportName" class="form-label">Airport</label>
                                    <input type="text" maxlength="250" class="form-control" id="airportName" name="airportName" required>
                                </div>
                                <div class="mb-3">
                                    <label for="city" class="form-label">City</label>
                                    <input type="text" maxlength="250" class="form-control" id="city" name="city" required>
                                </div>
                                <div class="mb-3">
                                    <label for="country" class="form-label">Country</label>
                                    <input type="text" maxlength="250" class="form-control" id="country" name="country" required>
                                </div>   
                                <button type="submit" class="btn btn-primary">Add</button>
                            </form>
                            <c:if test="${not empty addAirportFailed}">
                                <script>
                                    document.addEventListener('DOMContentLoaded', function () {
                                        var addAirportModal = new bootstrap.Modal(document.getElementById('addAirportModal'));
                                        addAirportModal.show();
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