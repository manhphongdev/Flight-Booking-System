<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Coupon Management</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        .container-fluid {
            margin-top: 20px;
        }
        .search-header-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
        }
        .custom-search-form {
            display: flex;
            gap: 10px;
        }
    </style>
</head>
<body>
    <div class="container-fluid">
        <h1>Coupon Management</h1>

        <div class="col-12 mt-4">
            <div class="row mb-3">
                <div class="col">
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addCouponModal">
                        <i class="bi bi-plus-circle me-2"></i> Add Coupon
                    </button>
                </div>
            </div>
            <div class="search-header-container">
                <h3 class="search-header-title">Coupon List</h3>
                <form method="GET" action="${pageContext.request.contextPath}/bookingmanager/coupon" class="custom-search-form">
                    <input type="hidden" name="action" value="search">
                    <input type="search" id="custom-search-input" name="searchTerm" placeholder="Search by coupon code" class="custom-search-input" value="${param.searchTerm}">
                    <button type="submit" class="custom-search-button"><i class="bi bi-search me-2"></i>Search</button>
                </form>
            </div>
            
            <c:if test="${not empty sessionScope.success}">
                <div class="alert alert-success" role="alert">
                    ${sessionScope.success}
                    <c:remove var="success" scope="session"/>
                </div>
            </c:if>
             <c:if test="${not empty sessionScope.deleteError}">
                <div class="alert alert-danger" role="alert">
                    ${sessionScope.deleteError}
                    <c:remove var="deleteError" scope="session"/>
                </div>
            </c:if>
             <c:if test="${not empty sessionScope.searchError}">
                <div class="alert alert-warning" role="alert">
                    ${sessionScope.searchError}
                    <c:remove var="searchError" scope="session"/>
                </div>
            </c:if>
            
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Code</th>
                        <th>Discount (%)</th>
                        <th>Max Usage</th>
                        <th>Used Count</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Status</th>
                        <th>Created At</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="coupon" items="${coupons}">
                        <tr>
                            <td>${coupon.couponId}</td>
                            <td>${coupon.couponCode}</td>
                            <td>${coupon.discountPercentage}</td>
                            <td>${coupon.maxUsage}</td>
                            <td>${coupon.usedCount}</td>
                            <td><fmt:formatDate value="${coupon.startDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td><fmt:formatDate value="${coupon.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>${coupon.status}</td>
                             <td><fmt:formatDate value="${coupon.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>
                                <button class="btn btn-sm btn-warning" data-bs-toggle="modal" data-bs-target="#editCouponModal" 
                                        data-coupon-id="${coupon.couponId}"
                                        data-coupon-code="${coupon.couponCode}"
                                        data-discount-percentage="${coupon.discountPercentage}"
                                        data-max-usage="${coupon.maxUsage}"
                                        data-end-date="<fmt:formatDate value='${coupon.endDate}' pattern='yyyy-MM-dd' defaulting to=''/>"
                                        data-status="${coupon.status}">
                                    Edit
                                </button>
                                 <a href="${pageContext.request.contextPath}/bookingmanager/coupon?action=delete&couponCode=${coupon.couponCode}" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure you want to delete this coupon?')">
                                    Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty coupons}">
                        <tr>
                            <td colspan="10" class="text-center">No coupons found.</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
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
                             <input type="hidden" name="action" value="add">
                            <c:if test="${not empty sessionScope.errorNull}">
                                <div class="alert alert-danger" style="color: red">${sessionScope.errorNull}</div>
                                 <c:remove var="errorNull" scope="session"/>
                            </c:if>
                            <c:if test="${not empty sessionScope.addError}">
                                <div class="alert alert-danger" style="color: red">${sessionScope.addError}</div>
                                 <c:remove var="addError" scope="session"/>
                            </c:if>
                            <div class="mb-3">
                                <label for="couponCode" class="form-label">Code</label>
                                <input type="text" class="form-control" id="couponCode" name="couponCode" required>
                            </div>
                            <div class="mb-3">
                                <label for="discountPercentage" class="form-label">Discount Percentage (%)</label>
                                <input type="number" step="0.01" class="form-control" id="discountPercentage" name="discountPercentage" required>
                            </div>
                             <div class="mb-3">
                                <label for="maxUsage" class="form-label">Max Usage</label>
                                <input type="number" class="form-control" id="maxUsage" name="maxUsage" required>
                            </div>
                            <div class="mb-3">
                                <label for="startDate" class="form-label">Start Date</label>
                                <input type="datetime-local" class="form-control" id="startDate" name="startDate" required>
                            </div>
                            <div class="mb-3">
                                <label for="endDate" class="form-label">End Date</label>
                                <input type="datetime-local" class="form-control" id="endDate" name="endDate" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Add Coupon</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Edit Coupon Modal -->
        <div class="modal fade" id="editCouponModal" tabindex="-1" aria-labelledby="editCouponModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editCouponModalLabel">Edit Coupon</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="${pageContext.request.contextPath}/bookingmanager/coupon" method="POST">
                            <input type="hidden" name="action" value="update">
                             <input type="hidden" name="couponId" id="edit-couponId">
                             <c:if test="${not empty sessionScope.updateError}">
                                <div class="alert alert-danger" style="color: red">${sessionScope.updateError}</div>
                                <c:remove var="updateError" scope="session"/>
                            </c:if>
                            <div class="mb-3">
                                <label for="edit-couponCode" class="form-label">Code</label>
                                <input type="text" class="form-control" id="edit-couponCode" name="couponCode" required>
                            </div>
                            <div class="mb-3">
                                <label for="edit-discountPercentage" class="form-label">Discount Percentage (%)</label>
                                <input type="number" step="0.01" class="form-control" id="edit-discountPercentage" name="discountPercentage" required>
                            </div>
                             <div class="mb-3">
                                <label for="edit-maxUsage" class="form-label">Max Usage</label>
                                <input type="number" class="form-control" id="edit-maxUsage" name="maxUsage" required>
                            </div>
                            <div class="mb-3">
                                <label for="edit-endDate" class="form-label">End Date</label>
                                <input type="date" class="form-control" id="edit-endDate" name="endDate" required>
                            </div>
                             <div class="mb-3">
                                <label for="edit-status" class="form-label">Status</label>
                                <select class="form-select" id="edit-status" name="status" required>
                                    <option value="Active">Active</option>
                                    <option value="Inactive">Inactive</option>
                                     <option value="Expired">Expired</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Update Coupon</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
     <script>
        var editCouponModal = document.getElementById('editCouponModal');
        editCouponModal.addEventListener('show.bs.modal', function (event) {
            var button = event.relatedTarget;
            var couponId = button.getAttribute('data-coupon-id');
            var couponCode = button.getAttribute('data-coupon-code');
            var discountPercentage = button.getAttribute('data-discount-percentage');
            var maxUsage = button.getAttribute('data-max-usage');
            var endDate = button.getAttribute('data-end-date'); // Assuming date format from data attribute is compatible with input type='date'
            var status = button.getAttribute('data-status');

            var modalTitle = editCouponModal.querySelector('.modal-title');
            var modalBodyInputId = editCouponModal.querySelector('#edit-couponId');
            var modalBodyInputCode = editCouponModal.querySelector('#edit-couponCode');
            var modalBodyInputDiscount = editCouponModal.querySelector('#edit-discountPercentage');
            var modalBodyInputMaxUsage = editCouponModal.querySelector('#edit-maxUsage');
            var modalBodyInputEndDate = editCouponModal.querySelector('#edit-endDate');
             var modalBodySelectStatus = editCouponModal.querySelector('#edit-status');

            modalTitle.textContent = 'Edit Coupon: ' + couponCode;
            modalBodyInputId.value = couponId;
            modalBodyInputCode.value = couponCode;
            modalBodyInputDiscount.value = discountPercentage;
            modalBodyInputMaxUsage.value = maxUsage;
            modalBodyInputEndDate.value = endDate;
             modalBodySelectStatus.value = status;
        });
    </script>
</body>
</html> 