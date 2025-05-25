<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Dashboard - Roles & Permissions</title>
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
        </style>
    </head>
    <body>
        <!-- Sidebar -->
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
                            <a href="admin_dashboard.jsp"><i class="bi bi-speedometer2 me-2"></i> Dashboard</a>
                        </div>
                    </div>
                </div>
                <div class="accordion-item bg-transparent border-0">
                    <h2 class="accordion-header">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#userCollapse">
                            <i class="bi bi-people me-2"></i> User Management
                        </button>
                    </h2>
                    <div id="userCollapse" class="accordion-collapse collapse show" data-bs-parent="#sidebarAccordion">
                        <div class="accordion-body p-0">
                            <a href="admin_users.jsp"><i class="bi bi-person me-2"></i> Users</a>
                            <a href="/flights/admin/access-manager" class="active"><i class="bi bi-shield-lock me-2"></i> Roles & Permissions</a>
                        </div>
                    </div>
                </div>
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

        <!-- Main Content -->
        <div class="content">
            <h1>Roles & Permissions Management</h1>

            <!-- Roles Table -->
            <div class="col-12 mt-4">
                <div class="row mb-3">
                    <div class="col">
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addRoleModal">
                            <i class="bi bi-plus-circle me-2"></i> Add Role
                        </button>
                    </div>

                    <!-- Add Role Modal -->
                    <div class="modal fade" id="addRoleModal" tabindex="-1" aria-labelledby="addRoleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="addRoleModalLabel">Add role</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>

                                <div class="modal-body">
                                    <form action="/flights/admin/access-manager" method="POST" name="role-permission">
                                        <input type="hidden" name="action" value="addRole">

                                        <c:if test="${not empty addRoleMsg}">
                                            <div class="alert alert-danger" style="color: red"> ${addRoleMsg}</div>
                                        </c:if>
                                        <div class="mb-3">
                                            <label for="role_name" class="form-label">Role</label>
                                            <input type="text" class="form-control" id="role_name" name="roleName" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="description" class="form-label">Description</label>
                                            <textarea class="form-control" id="description" name="description" required></textarea>
                                        </div>
                                        <button type="submit" class="btn btn-primary">Add</button>
                                    </form>

                                    <c:if test="${not empty addRoleMsg}">
                                        <script>
                                            document.addEventListener('DOMContentLoaded', function () {
                                                var addRoleModal = new bootstrap.Modal(document.getElementById('addRoleModal'));
                                                addRoleModal.show();
                                            });
                                        </script>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <h3>Roles List</h3>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th style="width: 4vw">ID</th>
                            <th style="width: 20vw">Role</th>
                            <th>Description</th>
                            <th style="width: 15vw">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="role" items="${roles}">
                            <tr>
                                <td><c:out value="${role.roleId}"></c:out> </td>
                                <td><c:out value="${role.roleName}"></c:out> </td>
                                <td><c:out value="${role.description}"></c:out> </td>
                                    <td>
                                        <!-- Button edit role -->
                                        <button class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#editRoleModal-${role.roleName}">
                                        <i class="bi bi-pencil"></i> Edit
                                    </button>

                                    <!-- Button delete role -->
                                    <button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal-${role.roleId}">
                                        Delete
                                    </button>

                                    <!-- form modal confirm delete -->
                                    <div class="form-confirm-delete">
                                        <form method="POST" action="/flights/admin/access-manager">
                                            <input type="hidden" name="action" value="deleteRole">
                                            <input type="hidden" name="roleDelete" value="${role.roleId}">

                                            <!-- Modal -->
                                            <div class="modal fade" id="deleteConfirmModal-${role.roleId}" tabindex="-1" aria-labelledby="deleteConfirmModal" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">

                                                        <div class="modal-header">
                                                            <h1 class="modal-title fs-5" id="deleteConfirmModal">Yes</h1>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div>Do you want to delete this role: "${role.roleName}"</div>
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

                                    <!-- Form edit -->
                                    <div class="modal fade" id="editRoleModal-${role.roleName}" tabindex="-1" aria-labelledby="editRoleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="editRoleModalLabel">Edit Role</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <form name="/flights/admin/access-manager" method="POST">
                                                        <input type="hidden" name="action" value="updateRole">
                                                        <input type="hidden" name="roleNameEdit" value="${role.roleName}">
                                                        <c:if test="${not empty updateFaild }">
                                                            <div class="alert alert-danger">${updateFaild}</div>
                                                        </c:if>
                                                        <div class="mb-3">
                                                            <label for="editRoleName" class="form-label">Role Name</label>
                                                            <input type="text" class="form-control" id="editRoleName" name="newRoleName" value="${role.roleName}" required>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="editDescription1" class="form-label">Description</label>
                                                            <textarea class="form-control" id="editDescription" name="newDescription" required>${role.description}</textarea>
                                                        </div>
                                                        <button type="submit" class="btn btn-primary">Save Changes</button>
                                                    </form>

                                                    <c:if test="${not empty updateFaild}">
                                                        <script>
                                                            document.addEventListener('DOMContentLoaded', function () {
                                                                var editRole = new bootstrap.Modal(document.getElementById('editRoleModal-${role.roleName}'));
                                                                editRole.show();
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


            <!-- Permissions Table -->
            <div class="col-12 mt-4">
                <div class="row mb-3">
                    <div class="col">
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addPermissionModal">
                            <i class="bi bi-plus-circle me-2"></i> Add Permission
                        </button>
                    </div>
                </div>
                <h3>Permissions List</h3>

                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th style="width: 4vw">ID</th>
                            <th style="width: 20vw">Permission</th>
                            <th>Description</th>
                            <th style="width: 15vw">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="permission" items="${permissions}">
                            <tr>
                                <td><c:out value="${permission.permissionId}"></c:out> </td>
                                <td><c:out value="${permission.permissonName}"></c:out> </td>
                                <td><c:out value="${permission.description}"></c:out> </td>
                                    <td>
                                        <!-- Button edit permission -->
                                        <button class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#editPermissionModal-${permission.permissonName}">
                                        <i class="bi bi-pencil"></i> Edit
                                    </button>

                                    <!-- Button delete permission -->
                                    <button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal-${permission.permissionId}">
                                        Delete
                                    </button>

                                    <!-- form modal confirm delete -->
                                    <div class="form-confirm-delete">
                                        <form method="POST" action="/flights/admin/access-manager">
                                            <input type="hidden" name="action" value="deletePermission">
                                            <input type="hidden" name="permissionDelete" value="${permission.permissionId}">

                                            <!-- Modal -->
                                            <div class="modal fade" id="deleteConfirmModal-${permission.permissionId}" tabindex="-1" aria-labelledby="deleteConfirmModal" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">

                                                        <div class="modal-header">
                                                            <h1 class="modal-title fs-5" id="deleteConfirmModal">Yes</h1>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div>Do you want to delete this permission: "${permission.permissonName}"</div>
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

                                    <!-- Form edit -->
                                    <div class="modal fade" id="editPermissionModal-${permission.permissonName}" tabindex="-1" aria-labelledby="editPermissionModalLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="editPermissionModal-${permission.permissonName}">Edit Role</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <form action="/flights/admin/access-manager"  method="POST">
                                                        <input type="hidden" name="action" value="updatePermission">
                                                        <input type="hidden" name="permissionNameEdit" value="${permission.permissonName}">
                                                        <c:if test="${not empty updatePermissionFaield }">
                                                            <div class="alert alert-danger">${updatePermissionFaield}</div>
                                                        </c:if>
                                                        <div class="mb-3">
                                                            <label for="editPermissionName" class="form-label">Permission Name</label>
                                                            <input type="text" class="form-control" id="editRoleName" name="newPermissionName" value="${permission.permissonName}" required>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="editDescription" class="form-label">Description</label>
                                                            <textarea class="form-control" id="editDescription" name="newPermissionDescription" required>${permission.description}</textarea>
                                                        </div>
                                                        <button type="submit" class="btn btn-primary">Save Changes</button>
                                                    </form>

                                                    <c:if test="${not empty updatePermissionFaield}">
                                                        <script>
                                                            document.addEventListener('DOMContentLoaded', function () {
                                                                var editPermission = new bootstrap.Modal(document.getElementById('editPermissionModal-${permission.permissonName}'));
                                                                editPermission.show();
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

            <!-- Add Permission Modal -->
            <div class="modal fade" id="addPermissionModal" tabindex="-1" aria-labelledby="addPermissionModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addPermissionModalLabel">Add New Permission</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="/flights/admin/access-manager" method="POST" name="role-permission">
                                <input type="hidden" name="action" value="addPermission">

                                <c:if test="${not empty addPermissionFailed}">
                                    <div class="aler alert-danger" style="color: red">${addPermissionFailed}</div>
                                </c:if>

                                <div class="mb-3">
                                    <label for="permission_name" class="form-label">Permission Name</label>
                                    <input type="text" class="form-control" id="permission_name" name="permissionName" required>
                                </div>
                                <div class="mb-3">
                                    <label for="description" class="form-label">Description</label>
                                    <textarea class="form-control" id="description" name="permissionDescription" required></textarea>
                                </div>
                                <button type="submit" class="btn btn-primary">Add Permission</button>
                            </form>
                            <c:if test="${not empty addPermissionFailed}">
                                <script>
                                    document.addEventListener('DOMContentLoaded', function () {
                                        var addPermissionModal = new bootstrap.Modal(document.getElementById('addPermissionModal'));
                                        addPermissionModal.show();
                                    });
                                </script>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>


            










            <!-- Role-Permission Mapping Table -->
            <div class="col-12 mt-4">
                <h3>Role-Permission Mapping</h3>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Role Name</th>
                            <th>Permissions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Admin</td>
                            <td>View Users, Edit Users, Manage Bookings</td>
                        </tr>
                        <tr>
                            <td>Staff</td>
                            <td>View Users, Manage Bookings</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <!-- Bootstrap JS -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>