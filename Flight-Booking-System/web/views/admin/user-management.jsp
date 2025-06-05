<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Management - Admin Dashboard</title>
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
            <h1>User Management</h1>

            <!-- User Table -->
            <div class="col-12 mt-4">
                <div class="row mb-3">
                    <div class="col">
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addUserModal">
                            <i class="bi bi-plus-circle me-2"></i> Add User
                        </button>
                    </div>
                </div>

                <div class="search-filters-container mb-4 p-3 bg-light border rounded">
                    <form method="GET" action="${pageContext.request.contextPath}/admin/users" class="row g-3 align-items-end">
                        <%-- Input tìm kiếm --%>
                        <div class="col-md-6 col-lg-4">
                            <label for="searchTermInput" class="form-label fw-semibold">Keyword</label>
                            <input type="text" class="form-control" id="searchTermInput" name="search" placeholder="Search by email, name" value="${param.search}">
                        </div>

                        <%-- Chọn Role --%>
                        <div class="col-md-6 col-lg-3">
                            <label for="roleSelectFilter" class="form-label fw-semibold">Role</label>
                            <select class="form-select" id="roleSelectFilter" name="roleFilter">
                                <option value="allRole">All</option>
                                <c:if test="${not empty roles}">
                                    <c:forEach var="role" items="${roles}">
                                        <option value="${role.roleName}" ${param.roleFilter eq role.roleName ? 'selected' : ''}>${role.roleName}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>

                        <%-- Sắp xếp theo (Sort By) --%>
                        <div class="col-md-6 col-lg-3">
                            <label for="sortBySelectFilter" class="form-label fw-semibold">Order By</label>
                            <select class="form-select" id="sortBySelectFilter" name="sortBy">
                                <option value="fullNameASC" ${param.sortBy == 'fullNameASC' ? 'selected' : ''}>Name (A-Z)</option>
                                <option value="fullNameDESC" ${param.sortBy == 'fullNameDESC' ? 'selected' : ''}>Name (Z-A)</option>
                                <option value="emailASC" ${param.sortBy == 'emailASC' ? 'selected' : ''}>Email (A-Z)</option>
                                <option value="emailDESC" ${param.sortBy == 'emailASC' ? 'selected' : ''}>Email (Z-A)</option>
                            </select>
                        </div>

                        <%-- Nút Tìm kiếm --%>
                        <div class="col-md-6 col-lg-2">
                            <button type="submit" class="btn btn-primary w-100">
                                <i class="bi bi-search me-1"></i> Search
                            </button>
                        </div>
                    </form>
                </div>
                <h3 class="search-header-title">User List</h3>

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
                                <th >Email</th>
                                <th >Full Name</th>
                                <th >Role</th>
                                <th >Status</th>
                                <th >Last Login</th>
                                <th >Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${users}">
                                <tr>
                                    <td><c:out value="${user.userId}"/></td>
                                    <td><c:out value="${user.email}"/></td>
                                    <td><c:out value="${user.lastName} ${user.firstName}"/></td>
                                    <td><c:out value="${user.userRole}"/></td>
                                    <td><c:out value="${user.status}"/></td>
                                    <td><c:out value="${user.lastLoginedAt}"/></td>
                                    <td>
                                        <button class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#editUserModal-${user.userId}">
                                            <i class="bi bi-pencil"></i> Edit
                                        </button>
                                        <button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal-${user.userId}">
                                            <i class="bi bi-trash"></i> Delete
                                        </button>
                                        <!-- Delete Confirmation Modal -->
                                        <div class="form-confirm-delete">
                                            <form method="POST" action="${pageContext.request.contextPath}/admin/users">
                                                <input type="hidden" name="action" value="delete">
                                                <input type="hidden" name="idDelete" value="${user.userId}">
                                                <div class="modal fade" id="deleteConfirmModal-${user.userId}" tabindex="-1" aria-labelledby="deleteConfirmModalLabel-${user.userId}" aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h1 class="modal-title fs-5" id="deleteConfirmModalLabel-${user.userId}">Confirm</h1>
                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div>Do you want to delete this user with ID: ${user.userId}?</div>
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

                                        <!-- edit user -->
                                        <div class="modal fade" id="editUserModal-${user.userId}" tabindex="-1" aria-labelledby="editUserModalLabel" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="editUserModal-${user.userId}">Edit User</h5>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form action="${pageContext.request.contextPath}/admin/users"  method="POST">
                                                            <input type="hidden" name="action" value="updateUser">
                                                            <input type="hidden" name="userIdEdit" value="${user.userId}">
                                                            <c:if test="${not empty errorNull }">
                                                                <div class="alert alert-danger">${errorNull}</div>
                                                            </c:if>
                                                            <div class="mb-3">
                                                                <label for="editFirstName" class="form-label">First Name</label>
                                                                <input type="text" class="form-control" id="editFirstName" name="newFirstName" value="${user.firstName}" required>
                                                            </div>
                                                            <div class="mb-3">
                                                                <label for="editLastName" class="form-label">Last Name</label>
                                                                <input type="text" class="form-control" id="editLastName" name="newLastName" value="${user.lastName}" required>
                                                            </div>

                                                            <div class="mb-3">
                                                                <label for="editRole" class="form-label">Select role</label>
                                                                <select class="form-control" id="editRole" name="roleName" required>
                                                                    <c:if test="${not empty roles}">
                                                                        <c:forEach var="role" items="${roles}">
                                                                            <option value="${role.roleName}">${role.roleName}</option>
                                                                        </c:forEach>
                                                                    </c:if>
                                                                </select>
                                                            </div>
                                                            <div class="mb-3">
                                                                <label for="editStatus" class="form-label">Status</label>
                                                                <select class="form-control" id="editStatus" name="status" required>
                                                                    <option>Active</option>
                                                                    <option value="IN_ACTIVE">Inactive</option>
                                                                </select>
                                                            </div>
                                                            <button type="submit" class="btn btn-primary">Save Changes</button>
                                                        </form>

                                                        <c:if test="${not empty errorNull}">
                                                            <script>
                                                                document.addEventListener('DOMContentLoaded', function () {
                                                                    var editPermission = new bootstrap.Modal(document.getElementById('editUserModal-${user.userId}'));
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

            </div>

            <!-- Add User Modal -->
            <div class="modal fade" id="addUserModal" tabindex="-1" aria-labelledby="addUserModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addUserModalLabel">Add New User</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="${pageContext.request.contextPath}/admin/users" method="POST">
                                <input type="hidden" name="action" value="addUser">
                                <c:if test="${not empty sessionScope.errorNull}">
                                    <div class="alert alert-danger">${sessionScope.errorNull}</div>
                                </c:if>
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email</label>
                                    <input type="email" class="form-control" id="email" name="email" >
                                </div>
                                <c:if test="${not empty sessionScope.errorEmailExists}">
                                    <div class="alert alert-danger">${sessionScope.errorEmailExists}</div>
                                </c:if>
                                <c:if test="${not empty sessionScope.errorEmail}">
                                    <div class="alert alert-danger">${sessionScope.errorEmail}</div>
                                </c:if>
                                <div class="mb-3">
                                    <label for="firstName" class="form-label">First Name</label>
                                    <input type="text" class="form-control" id="firstName" name="firstName" >
                                </div>
                                <div class="mb-3">
                                    <label for="lastName" class="form-label">Last Name</label>
                                    <input type="text" class="form-control" id="lastName" name="lastName" >
                                </div>
                                <div class="mb-3">
                                    <label for="password" class="form-label">Password</label>
                                    <input type="password" class="form-control" id="password" name="password" required>
                                </div>
                                <c:if test="${not empty sessionScope.errorPassword}">
                                    <div class="alert alert-danger">${sessionScope.errorPassword}</div>
                                </c:if>
                                <div class="mb-3">
                                    <label for="password" class="form-label">Confirm Password</label>
                                    <input type="password" class="form-control" id="confPassword" name="confPassword" required>
                                </div>
                                <div class="mb-3">
                                    <label for="role" class="form-label">Select role</label>
                                    <select class="form-control" id="role" name="role" required>
                                        <c:if test="${not empty roles}">
                                            <c:forEach var="role" items="${roles}">
                                                <option value="${role.roleName}">${role.roleName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-primary">Add User</button>
                            </form>
                            <c:choose>
                                <c:when test="${not empty sessionScope.errorEmailExists}">
                                    <script>
                                        document.addEventListener('DOMContentLoaded', function () {
                                            var addUserModal = new bootstrap.Modal(document.getElementById('addUserModal'));
                                            addUserModal.show();
                                        });
                                    </script>
                                    <c:remove var="errorEmailExists" scope="session"/>
                                </c:when>

                                <c:when test="${not empty sessionScope.errorEmail}">
                                    <script>
                                        document.addEventListener('DOMContentLoaded', function () {
                                            var addUserModal = new bootstrap.Modal(document.getElementById('addUserModal'));
                                            addUserModal.show();
                                        });
                                    </script>
                                    <c:remove var="errorEmail" scope="session"/>
                                </c:when>

                                <c:when test="${not empty sessionScope.errorNull}">
                                    <script>
                                        document.addEventListener('DOMContentLoaded', function () {
                                            var addUserModal = new bootstrap.Modal(document.getElementById('addUserModal'));
                                            addUserModal.show();
                                        });
                                    </script>
                                    <c:remove var="errorNull" scope="session"/>
                                </c:when>

                                <c:when test="${not empty sessionScope.errorPassword}">
                                    <script>
                                        document.addEventListener('DOMContentLoaded', function () {
                                            var addUserModal = new bootstrap.Modal(document.getElementById('addUserModal'));
                                            addUserModal.show();
                                        });
                                    </script>
                                    <c:remove var="errorPassword" scope="session"/>
                                </c:when>

                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Bootstrap JS and Sort Script -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

        </div>
    </body>
</html>