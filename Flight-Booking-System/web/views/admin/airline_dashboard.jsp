<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Airline Management</title>
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
        <%@ include file="sidebar.jsp" %>

        <!-- Main Content -->
        <div class="content">
            <h1>Airline Management</h1>

            <!-- Airline Table -->
            <div class="col-12 mt-4">
                <div class="row mb-3">
                    <div class="col">
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addAirlineModal">
                            <i class="bi bi-plus-circle me-2"></i> Add Airline
                        </button>
                    </div>
                </div>
                <div class="search-header-container">
                    <h3 class="search-header-title">Airline List</h3>
                    <form method="GET" action="${pageContext.request.contextPath}/flightmanager/airline" class="custom-search-form">
                        <input type="hidden" name="action" value="findByCode">
                        <input type="search" id="custom-search-input" name="airlineCode" placeholder="Search by airline code" class="custom-search-input" value="${param.airlineCode}">
                        <button type="submit" class="custom-search-button"><i class="bi bi-search me-2"></i>Search</button>
                    </form>
                </div>

                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Airline Name</th>
                                <th>Code</th>
                                <th>Created At</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="airline" items="${airlines}">
                                <tr>

                                    <td>${airline.airlineName}</td>
                                    <td>${airline.airlineCode}</td>
                                    <td>${airline.createAt}</td>
                                    <td style="width: 7vw">
                                        <div class="btn-group" role="group">
                                            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal-${airline.airlineCode}">
                                                <i class="bi bi-trash">DELETE</i>
                                            </button>
                                        </div>

                                        <!-- Delete Confirmation Modal -->
                                        <div class="form-confirm-delete">
                                            <form method="POST" action="${pageContext.request.contextPath}/flightmanager/airline">
                                                <input type="hidden" name="action" value="deleteAirline">
                                                <input type="hidden" name="codeDelete" value="${airline.airlineCode}">
                                                <div class="modal fade" id="deleteConfirmModal-${airline.airlineCode}" tabindex="-1" aria-labelledby="deleteConfirmModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h1 class="modal-title fs-5" id="deleteConfirmModalLabel">Confirm</h1>
                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div>Do you want to delete this airline with code: ${airline.airlineCode}?</div>
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
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- Add Airline Modal -->
            <div class="modal fade" id="addAirlineModal" tabindex="-1" aria-labelledby="addAirlineModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addAirlineModalLabel">Add New Airline</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="${pageContext.request.contextPath}/flightmanager/airline" method="POST" name="add-airline">
                                <input type="hidden" name="action" value="addAirline">
                                <c:if test="${not empty sessionScope.errorNull}">
                                    <div class="alert alert-danger" style="color: red">${sessionScope.errorNull}</div>
                                </c:if>
                                <c:if test="${not empty sessionScope.addAirlineFailed}">
                                    <div class="alert alert-danger" style="color: red">${sessionScope.addAirlineFailed}</div>
                                </c:if>
                                <div class="mb-3">
                                    <label for="airlineCode" class="form-label">Code</label>
                                    <input type="text" maxlength="3" class="form-control" id="airlineCode" name="airlineCode" required>
                                </div>
                                <c:if test="${not empty sessionScope.errorCode}">
                                    <div class="alert alert-danger" style="color: red">${sessionScope.errorCode}</div>
                                </c:if>
                                <div class="mb-3">
                                    <label for="airlineName" class="form-label">Airline Name</label>
                                    <input type="text" maxlength="250" class="form-control" id="airlineName" name="airlineName" required>
                                </div>
                                <button type="submit" class="btn btn-primary">Add</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:if test="${not empty sessionScope.errorNull}">
            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    var addAirlineModal = new bootstrap.Modal(document.getElementById('addAirlineModal'));
                    addAirlineModal.show();
                });
            </script>
            <c:remove var="errorNull" scope="session"/>
        </c:if>
        <c:if test="${not empty sessionScope.addAirlineFailed}">
            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    var addAirlineModal = new bootstrap.Modal(document.getElementById('addAirlineModal'));
                    addAirlineModal.show();
                });
            </script>
            <c:remove var="addAirlineFailed" scope="session"/>
        </c:if>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html> 