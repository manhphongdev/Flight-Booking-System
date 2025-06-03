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
                max-width: 600px;
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
        <c:set var="activePage" value="user-management" scope="request"/>
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
                <div class="search-header-container">
                    <h3 class="search-header-title">User List</h3>
                    <form method="GET" action="${pageContext.request.contextPath}/admin/user-management" class="custom-search-form">
                        <input type="hidden" name="sortBy" value="${sortBy}">
                        <input type="hidden" name="sortOrder" value="${sortOrder}">
                        <input type="search" name="search" placeholder="Search by email or name" class="custom-search-input" value="${search}">
                        <select name="role" class="custom-search-input">
                            <option value="">All Roles</option>
                            <option value="ADMIN" ${roleFilter == 'ADMIN' ? 'selected' : ''}>Admin</option>
                            <option value="CUSTOMER" ${roleFilter == 'CUSTOMER' ? 'selected' : ''}>Customer</option>
                        </select>
                        <select name="status" class="custom-search-input">
                            <option value="">All Statuses</option>
                            <option value="ACTIVE" ${statusFilter == 'ACTIVE' ? 'selected' : ''}>Active</option>
                            <option value="INACTIVE" ${statusFilter == 'INACTIVE' ? 'selected' : ''}>Inactive</option>
                        </select>
                        <button type="submit" class="custom-search-button"><i class="bi bi-search me-2"></i>Search</button>
                    </form>
                </div>
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
                                <th style="width: 10%">
                                    ID
                                    <button class="sort-button" onclick="sortTable('id')">
                                        <i class="bi ${sortBy == 'id' ? (sortOrder == 'ASC' ? 'bi-arrow-up' : 'bi-arrow-down') : 'bi-arrow-up-down'}"></i>
                                    </button>
                                </th>
                                <th style="width: 25%">
                                    Email
                                    <button class="sort-button" onclick="sortTable('email')">
                                        <i class="bi ${sortBy == 'email' ? (sortOrder == 'ASC' ? 'bi-arrow-up' : 'bi-arrow-down') : 'bi-arrow-up-down'}"></i>
                                    </button>
                                </th>
                                <th style="width: 20%">
                                    Name
                                    <button class="sort-button" onclick="sortTable('name')">
                                        <i class="bi ${sortBy == 'name' ? (sortOrder == 'ASC' ? 'bi-arrow-up' : 'bi-arrow-down') : 'bi-arrow-up-down'}"></i>
                                    </button>
                                </th>
                                <th style="width: 15%">
                                    Role
                                    <button class="sort-button" onclick="sortTable('role')">
                                        <i class="bi ${sortBy == 'role' ? (sortOrder == 'ASC' ? 'bi-arrow-up' : 'bi-arrow-down') : 'bi-arrow-up-down'}"></i>
                                    </button>
                                </th>
                                <th style="width: 15%">
                                    Status
                                    <button class="sort-button" onclick="sortTable('status')">
                                        <i class="bi ${sortBy == 'status' ? (sortOrder == 'ASC' ? 'bi-arrow-up' : 'bi-arrow-down') : 'bi-arrow-up-down'}"></i>
                                    </button>
                                </th>
                                <th style="width: 15%">
                                    Created At
                                    <button class="sort-button" onclick="sortTable('createdAt')">
                                        <i class="bi ${sortBy == 'createdAt' ? (sortOrder == 'ASC' ? 'bi-arrow-up' : 'bi-arrow-down') : 'bi-arrow-up-down'}"></i>
                                    </button>
                                </th>
                                <th style="width: 15%">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${users}">
                                <tr>
                                    <td><c:out value="${user.id}"/></td>
                                    <td><c:out value="${user.email}"/></td>
                                    <td><c:out value="${user.name}"/></td>
                                    <td><c:out value="${user.role}"/></td>
                                    <td><c:out value="${user.status}"/></td>
                                    <td><c:out value="${user.createdAt}"/></td>
                                    <td>
                                        <button class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#editUserModal-${user.id}">
                                            <i class="bi bi-pencil"></i> Edit
                                        </button>
                                        <button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal-${user.id}">
                                            <i class="bi bi-trash"></i> Delete
                                        </button>

                                        <!-- Delete Confirmation Modal -->
                                        <div class="form-confirm-delete">
                                            <form method="POST" action="${pageContext.request.contextPath}/admin/user-management">
                                                <input type="hidden" name="action" value="delete">
                                                <input type="hidden" name="id" value="${user.id}">
                                                <div class="modal fade" id="deleteConfirmModal-${user.id}" tabindex="-1" aria-labelledby="deleteConfirmModalLabel-${user.id}" aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h1 class="modal-title fs-5" id="deleteConfirmModalLabel-${user.id}">Confirm</h1>
                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div>Do you want to delete this user with ID: ${user.id}?</div>
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

                                        <!-- Edit User Modal -->
                                        <div class="modal fade" id="editUserModal-${user.id}" tabindex="-1" aria-labelledby="editUserModalLabel-${user.id}" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="editUserModalLabel-${user.id}">Edit User</h5>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form action="${pageContext.request.contextPath}/admin/user-management" method="POST">
                                                            <input type="hidden" name="action" value="update">
                                                            <input type="hidden" name="id" value="${user.id}">
                                                            <c:if test="${not empty error}">
                                                                <div class="alert alert-danger">${error}</div>
                                                            </c:if>
                                                            <div class="mb-3">
                                                                <label for="editEmail-${user.id}" class="form-label">Email</label>
                                                                <input type="email" class="form-control" id="editEmail-${user.id}" name="email" value="${user.email}" readonly>
                                                            </div>
                                                            <div class="mb-3">
                                                                <label for="editName-${user.id}" class="form-label">Name</label>
                                                                <input type="text" maxlength="250" class="form-control" id="editName-${user.id}" name="name" value="${user.name}" required>
                                                            </div>
                                                            <div class="mb-3">
                                                                <label for="editRole-${user.id}" class="form-label">Role</label>
                                                                <select class="form-control" id="editRole-${user.id}" name="role" required>
                                                                    <option value="ADMIN" ${user.role == 'ADMIN' ? 'selected' : ''}>Admin</option>
                                                                    <option value="CUSTOMER" ${user.role == 'CUSTOMER' ? 'selected' : ''}>Customer</option>
                                                                </select>
                                                            </div>
                                                            <div class="mb-3">
                                                                <label for="editStatus-${user.id}" class="form-label">Status</label>
                                                                <select class="form-control" id="editStatus-${user.id}" name="status" required>
                                                                    <option value="ACTIVE" ${user.status == 'ACTIVE' ? 'selected' : ''}>Active</option>
                                                                    <option value="INACTIVE" ${user.status == 'INACTIVE' ? 'selected' : ''}>Inactive</option>
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

                <!-- Pagination -->
                <nav>
                    <ul class="pagination justify-content-center">
                        <c:forEach begin="1" end="${totalPages}" var="i">
                            <li class="page-item ${currentPage == i ? 'active' : ''}">
                                <a class="page-link" href="${pageContext.request.contextPath}/admin/user-management?page=${i}&search=${search}&role=${roleFilter}&status=${statusFilter}&sortBy=${sortBy}&sortOrder=${sortOrder}">${i}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </nav>
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
                            <form action="${pageContext.request.contextPath}/admin/user-management" method="POST" name="add-user">
                                <input type="hidden" name="action" value="create">
                                <c:if test="${not empty error}">
                                    <div class="alert alert-danger">${error}</div>
                                </c:if>
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email</label>
                                    <input type="email" maxlength="250" class="form-control" id="email" name="email" required>
                                </div>
                                <div class="mb-3">
                                    <label for="password" class="form-label">Password</label>
                                    <input type="password" maxlength="250" class="form-control" id="password" name="password" required>
                                </div>
                                <div class="mb-3">
                                    <label for="name" class="form-label">Name</label>
                                    <input type="text" maxlength="250" class="form-control" id="name" name="name" required>
                                </div>
                                <div class="mb-3">
                                    <label for="role" class="form-label">Role</label>
                                    <select class="form-control" id="role" name="role" required>
                                        <option value="ADMIN">Admin</option>
                                        <option value="CUSTOMER">Customer</option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="status" class="form-label">Status</label>
                                    <select class="form-control" id="status" name="status" required>
                                        <option value="ACTIVE">Active</option>
                                        <option value="INACTIVE">Inactive</option>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-primary">Add</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Bootstrap JS and Sort Script -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
            <script>
                function sortTable(column) {
                    const currentSortBy = '${sortBy}';
                    const currentSortOrder = '${sortOrder}';
                    const newSortOrder = (currentSortBy === column && currentSortOrder === 'ASC') ? 'DESC' : 'ASC';
                    const url = `${pageContext.request.contextPath}/admin/user-management?sortBy=${column}&sortOrder=${newSortOrder}&search=${encodeURIComponent('${search}')}&role=${encodeURIComponent('${roleFilter}')}&status=${encodeURIComponent('${statusFilter}')}&page=${'${currentPage}'}`;
                    window.location.href = url;
                }
            </script>
        </div>
    </body>
</html>