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

            /* Container cho tiêu đề và form tìm kiếm */
            .search-header-container {
                display: flex;
                align-items: center;
                justify-content: space-between;
                gap: 20px;
                margin-bottom: 20px;
            }

            /* Tiêu đề */
            .search-header-title {
                margin: 0;
                font-size: 24px;
                font-weight: 600;
                color: #333;
            }

            /* Form tìm kiếm */
            .custom-search-form {
                display: flex;
                align-items: center;
                gap: 10px;
                flex: 1;
                max-width: 400px;
            }

            /* Input tìm kiếm */
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

            /* Nút tìm kiếm */
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

            /* Responsive cho màn hình nhỏ */
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
         <!-- Sidebar -->
        <c:set var="activePage" value="admin-roles-permissions" scope="request"/>
        <%@ include file="sidebar.jsp" %>

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
                                        <button class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#editRoleModal-${role.roleId}">
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
                                    <div class="modal fade" id="editRoleModal-${role.roleId}" tabindex="-1" aria-labelledby="editRoleModalLabel" aria-hidden="true">
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
                                                                var editRole = new bootstrap.Modal(document.getElementById('#editRoleModal-${role.roleId}'));
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


            <!-- Role-Permission Mapping Section -->
            <div class="col-12 mt-4">
                <div class="row mb-3">
                    <div class="col">
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addRolePermissionModal">
                            <i class="bi bi-plus-circle me-2"></i> Add Role-Permission Mapping
                        </button>
                    </div>
                </div>
                <div class="search-header-container">
                    <h3 class="search-header-title">Role Has Permission</h3>
                    <form method="GET" action="/flights/admin/access-manager" class="custom-search-form" >
                        <input type="hidden" name="action" value="findByRoleName">
                        <input type="search" id="custom-search-input" name="roleName" placeholder="Search by role" class="custom-search-input">
                        <button type="submit" class="custom-search-button" ><i class="bi bi-search me-2"></i>Search</button>
                    </form>
                </div>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th style="width: 24vw">Role</th>
                            <th>Role Has Permission</th>
                            <th style="width: 15vw">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="permissionName" items="${permissionNames}">
                            <tr>
                                <td>${param.roleName}</td>
                                <td><c:out value="${permissionName}"></c:out></td>
                                    <td>

                                    <!-- Button delete role -->
                                    <button type="button" class="btn btn-sm btn-danger" data-bs-toggle="modal" data-bs-target="#deleteConfirmModal-${permissionName}-${param.roleName}">
                                        Delete
                                    </button>

                                    <!-- form modal confirm delete -->
                                    <div class="form-confirm-delete">
                                        <form method="POST" action="/flights/admin/access-manager">
                                            <input type="hidden" name="action" value="deletePermissionOfRole">
                                            <input type="hidden" name="permissionNameDelete" value="${permissionName}">
                                            <input type="hidden" name="roleNameDelete" value="${param.roleName}">
                                            <!-- Modal -->
                                            <div class="modal fade" id="deleteConfirmModal-${permissionName}-${param.roleName}" tabindex="-1" aria-labelledby="deleteConfirmModal" aria-hidden="true">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">

                                                        <div class="modal-header">
                                                            <h1 class="modal-title fs-5" id="deleteConfirmModal">Confirm</h1>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
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

            <!-- Add Role-Permission Mapping Modal -->
            <div class="modal fade" id="addRolePermissionModal" tabindex="-1" aria-labelledby="addRolePermissionModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addRolePermissionModalLabel">Add Role-Permission Mapping</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="/flights/admin/access-manager" method="POST">
                                <input type="hidden" name="action" value="addRolePermission">

                                <!-- Hiển thị lỗi nếu có -->
                                <c:if test="${not empty addMappingFailed}">
                                    <div class="alert alert-danger">${addMappingFailed}</div>
                                </c:if>

                                <!-- Chọn Role -->
                                <div class="mb-3">
                                    <label for="roleName" class="form-label">Role Name</label>
                                    <select class="form-control" id="roleName" name="roleName" required>
                                        <c:forEach var="role" items="${roles}">
                                            <option value="${role.roleName}">${role.roleName}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!-- Chọn Permission -->
                                <div class="mb-3 container-fluid">
                                    <div class="row">
                                        <label for="permissionName" class="form-label">Permission Name</label> <br>
                                        <c:forEach var="permission" items="${permissions}">
                                            <label class="col-md-6">
                                                <input type="checkbox" name="permission" value="${permission.permissonName}"> 
                                                ${permission.permissonName}
                                            </label><br>
                                        </c:forEach>
                                    </div>
                                </div>

                                <button type="submit" class="btn btn-primary">Add Mapping</button>
                            </form>
                            <c:if test="${not empty addMappingFailed}">
                                <script>
                                    document.addEventListener('DOMContentLoaded', function () {
                                        var addRolePermissionModal = new bootstrap.Modal(document.getElementById('addRolePermissionModal'));
                                        addRolePermissionModal.show();
                                    });
                                </script>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Bootstrap JS -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>