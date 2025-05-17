<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Dashboard - Airline Booking</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Chart.js -->
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

        <!-- admin.js -->
        <script src="${pageContext.request.contextPath}/Resource/js/admin_dashboard.js"></script>
        <!-- CSS -->
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f6f9;
            }
            .sidebar {
                height: 100vh;
                position: fixed;
                width: 250px;
                background-color: #343a40;
                padding-top: 20px;
            }
            .sidebar a {
                color: #ffffff;
                padding: 15px;
                display: block;
                text-decoration: none;
            }
            .sidebar a:hover {
                background-color: #495057;
            }
            .content {
                margin-left: 260px;
                padding: 20px;
            }
            .card {
                border: none;
                border-radius: 10px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }
            .card-header {
                background-color: #007bff;
                color: white;
                border-radius: 10px 10px 0 0;
            }
            .table th, .table td {
                vertical-align: middle;
            }
            .chart-container {
                position: relative;
                height: 350px;
                padding: 15px;
                background-color: #ffffff;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.05);
            }
            .chart-container canvas {
                transition: all 0.3s ease;
            }
            .chart-container:hover canvas {
                transform: scale(1.02);
            }
            .search-filter {
                max-width: 300px;
            }
        </style>
    </head>
    <body>
        <!-- Sidebar -->
        <div class="sidebar">
            <h3 class="text-white text-center">Admin Panel</h3>
            <a href="#dashboard">Dashboard</a>
            <a href="#users">Quản lý người dùng</a>
            <a href="#coupons">Quản lý mã giảm giá</a>
            <a href="#bookings">Quản lý đặt vé</a>
            <a href="#flights">Quản lý chuyến bay</a>
            <a href="#seats">Quản lý ghế</a>
            <a href="#payments">Quản lý thanh toán</a>
            <a href="#exchange-rates">Quản lý tỷ giá</a>
            <a href="#airlines">Quản lý hãng hàng không</a>
            <a href="#airports">Quản lý sân bay</a>
            <a href="#reports">Báo cáo & Thống kê</a>
            <a href="#activity">Nhật ký hoạt động</a>
        </div>

        <!-- Main Content -->
        <div class="content">
            <!-- Dashboard Overview -->
            <section id="dashboard">
                <h2>Dashboard</h2>
                <div class="row">
                    <div class="col-md-3">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Tổng người dùng</h5>
                                <p class="card-text"></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Tổng đặt vé</h5>
                                <p class="card-text"></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Tổng chuyến bay</h5>
                                <p class="card-text"></p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Thanh toán hoàn tất</h5>
                                <p class="card-text"></p>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- User Management -->
            <section id="users" class="mt-5 container-fluid">
                <h2>Quản lý người dùng</h2>
                <div class="mb-3">
                    <input type="text" class="form-control search-filter d-inline" placeholder="Tìm kiếm người dùng..." id="userSearch">
                </div>
                <button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#addUser" name="getAllUser">Thêm người dùng</button>
                <div class="card" >
                    <div class="card-header">Danh sách người dùng</div>
                    <div class="card-body">
                        <table class="table table-striped" >
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Họ tên</th>
                                    <th>Email</th>
                                    <th>Vai trò</th>
                                    <th>Đăng nhập cuối</th>
                                    <th>Hành động</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="user" items="${allUser}">
                                    <tr>
                                        <td><c:out value="${user.userId}"></c:out></td>
                                        <td><c:out value="${user.lastName} ${user.firstName}"></c:out></td>
                                        <td><c:out value="${user.email}"></c:out></td>
                                        <td><c:out value="${user.role}"></c:out></td>
                                        <td><c:out value="${user.lastLogin}"></c:out></td>
                                            <td><div class="dropdown">
                                                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                                        Dropdown button
                                                    </button>
                                                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                                        <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#editUserModal">Chỉnh sửa</a></li>
                                                        <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#inActiveUser">Xoá người dùng</a></li>
                                                        <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#deleteInDatabase">Xoá vĩnh viễn</a></li>
                                                    </ul>
                                                </div></td>
                                        </tr>
                                </c:forEach>

                            </tbody>
                        </table>

                    </div>
                </div>

                <!-- Add new user -->
                <div class="modal fade" id="addUser" tabindex="-1" aria-labelledby="addUserModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="addUserModalLabel">Nhập thông tin người dùng</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form action="${pageContext.request.contextPath}/admin/addUser" method="post">
                                    <input type="hidden" name="id" id="editUserId">

                                    <div class="mb-3">
                                        <label class="form-label">Họ</label>
                                        <input type="text" class="form-control" name="lastName" id="lastName" required>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Tên</label>
                                        <input type="text" class="form-control" name="firstName" id="firstName" required>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Email</label>
                                        <input type="email" class="form-control" name="email" id="email" required>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Mật khẩu</label>
                                        <input type="password" class="form-control" name="password" id="password" required onkeyup="checkPassword()">

                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Nhập lại mật khẩu</label>
                                        <input type="password" class="form-control" name="rePassword" id="rePassword" required onkeyup="checkPassword()">
                                        <p class="error" id="errorPassword" style="margin-top: 3px"></p>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Vai trò</label>
                                        <select class="form-select" name="role" id="editUserRole" required>
                                            <option value="customer">Customer</option>
                                            <option value="admin">Admin</option>
                                        </select>
                                    </div>


                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                        <button type="submit" class="btn btn-primary">Lưu</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Modal for Editing User -->
                <div class="modal fade" id="editUserModal" tabindex="-1" aria-labelledby="editUserModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="editUserModalLabel">Sửa người dùng</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form action="${pageContext.request.contextPath}/admin/editUser" method="post">
                                    <input type="hidden" name="id" id="editUserId">
                                    <div class="mb-3">
                                        <label class="form-label">Email</label>
                                        <input type="email" class="form-control" name="email" id="editUserEmail" required>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Vai trò</label>
                                        <select class="form-select" name="role" id="editUserRole" required>
                                            <option value="customer">Khách hàng</option>
                                            <option value="admin">Admin</option>
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Họ</label>
                                        <input type="text" class="form-control" name="lastName" id="editUserLastName" required>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Tên</label>
                                        <input type="text" class="form-control" name="firstName" id="editUserFirstName" required>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Ngày sinh</label>
                                        <input type="date" class="form-control" name="dob" id="editUserDob">
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Giới tính</label>
                                        <select class="form-select" name="gender" id="editUserGender">
                                            <option value="male">Nam</option>
                                            <option value="female">Nữ</option>
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Số điện thoại</label>
                                        <input type="text" class="form-control" name="phone" id="editUserPhone">
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Quốc tịch</label>
                                        <input type="text" class="form-control" name="nationality" id="editUserNationality">
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                        <button type="submit" class="btn btn-primary">Lưu</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Modal for in_active user-->
                <div class="modal fade" id="inActiveUser" tabindex="-1" aria-labelledby="inActiveUserLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="inActiveUserLabel">Modal title</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                Bạn muốn xoá người dùng?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                                <button type="button" class="btn btn-primary">Yes</button>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Modal for delete user in database-->
                <div class="modal fade" id="deleteInDatabase" tabindex="-1" aria-labelledby="deleteInDbUserLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="deleteInDbUserLabel">Modal title</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                Bạn muốn xoá vĩnh viễn người dùng khỏi hệ thống?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                                <button type="button" class="btn btn-primary">Yes</button>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <!-- Coupon Management -->
            <section id="coupons" class="mt-5">
                <h2>Quản lý mã giảm giá</h2>
                <button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#addCouponModal">Thêm mã giảm giá</button>
                <div class="card">
                    <div class="card-header">Danh sách mã giảm giá</div>
                    <div class="card-body">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Mã</th>
                                    <th>Mô tả</th>
                                    <th>Giảm giá</th>
                                    <th>Trạng thái</th>
                                    <th>Sử dụng</th>
                                    <th>Hết hạn</th>
                                    <th>Hành động</th>
                                </tr>
                            </thead>

                        </table>
                    </div>
                </div>
            </section>

            <!-- Booking Management -->
            <section id="bookings" class="mt-5">
                <h2>Quản lý đặt vé</h2>
                <div class="mb-3">
                    <input type="text" class="form-control search-filter d-inline" placeholder="Tìm kiếm đặt vé..." id="bookingSearch">
                    <select class="form-select search-filter d-inline">
                        <option value="">Lọc theo trạng thái</option>
                        <option>Pending</option>
                        <option>Confirmed</option>
                        <option>Cancelled</option>
                    </select>
                </div>
                <div class="card">
                    <div class="card-header">Danh sách đặt vé</div>
                    <div class="card-body">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Mã đặt vé</th>
                                    <th>Khách hàng</th>
                                    <th>Mã giảm giá</th>
                                    <th>Tổng tiền</th>
                                    <th>Loại tiền</th>
                                    <th>Trạng thái</th>
                                    <th>Hành động</th>
                                </tr>
                            </thead>

                        </table>
                    </div>
                </div>
            </section>

            <!-- Flight Management -->
            <section id="flights" class="mt-5">
                <h2>Quản lý chuyến bay</h2>
                <button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#addFlightModal">Thêm chuyến bay</button>
                <button class="btn btn-success mb-3" data-bs-toggle="modal" data-bs-target="#importFlightsModal">Nhập từ file Excel</button>
                <div class="card">
                    <div class="card-header">Danh sách chuyến bay</div>
                    <div class="card-body">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Mã chuyến</th>
                                    <th>Hãng</th>
                                    <th>Điểm đi</th>
                                    <th>Điểm đến</th>
                                    <th>Thời gian đi</th>
                                    <th>Giá phổ thông</th>
                                    <th>Trạng thái</th>
                                    <th>Hành động</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </section>

            <!-- Seat Management -->
            <section id="seats" class="mt-5">
                <h2>Quản lý ghế</h2>
                <div class="card">
                    <div class="card-header">Danh sách ghế</div>
                    <div class="card-body">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Chuyến bay</th>
                                    <th>Số ghế</th>
                                    <th>Hạng ghế</th>
                                    <th>Trạng thái</th>
                                    <th>Hành khách</th>
                                    <th>Hành động</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </section>

            <!-- Payment Management -->
            <section id="payments" class="mt-5">
                <h2>Quản lý thanh toán</h2>
                <div class="card">
                    <div class="card-header">Danh sách thanh toán</div>
                    <div class="card-body">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Mã thanh toán</th>
                                    <th>Mã đặt vé</th>
                                    <th>Số tiền</th>
                                    <th>Phương thức</th>
                                    <th>Trạng thái</th>
                                    <th>Thời gian</th>
                                    <th>Hành động</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </section>

            <!-- Exchange Rate Management -->
            <section id="exchange-rates" class="mt-5">
                <h2>Quản lý tỷ giá hối đoái</h2>
                <button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#addExchangeRateModal">Thêm tỷ giá</button>
                <div class="card">
                    <div class="card-header">Danh sách tỷ giá</div>
                    <div class="card-body">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Từ</th>
                                    <th>Đến</th>
                                    <th>Tỷ giá</th>
                                    <th>Cập nhật</th>
                                    <th>Hành động</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </section>

            <!-- Airline Management -->
            <section id="airlines" class="mt-5">
                <h2>Quản lý hãng hàng không</h2>
                <button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#addAirlineModal">Thêm hãng</button>
                <div class="card">
                    <div class="card-header">Danh sách hãng hàng không</div>
                    <div class="card-body">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Mã hãng</th>
                                    <th>Tên hãng</th>
                                    <th>Liên hệ</th>
                                    <th>Hành động</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="airline" items="${airlines}">
                                    <tr>

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </section>

            <!-- Airport Management -->
            <section id="airports" class="mt-5">
                <h2>Quản lý sân bay</h2>
                <button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#addAirportModal">Thêm sân bay</button>
                <div class="card">
                    <div class="card-header">Danh sách sân bay</div>
                    <div class="card-body">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Mã sân bay</th>
                                    <th>Tên sân bay</th>
                                    <th>Vị trí</th>
                                    <th>Hành động</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </section>

            <!-- Activity Log -->
            <section id="activity" class="mt-5">
                <h2>Nhật ký hoạt động</h2>
                <p class="text-muted">Phân tích tần suất hành động của khách hàng (đặt vé, hủy vé, sử dụng mã giảm giá).</p>
                <div class="mb-3">
                    <input type="text" class="form-control search-filter d-inline" placeholder="Tìm kiếm log..." id="logSearch">
                    <select class="form-select search-filter d-inline" id="logActionFilter">
                        <option value="">Lọc theo hành động</option>
                        <option value="create_booking">Tạo đặt vé</option>
                        <option value="cancel_booking">Hủy đặt vé</option>
                        <option value="apply_coupon">Sử dụng mã giảm giá</option>
                    </select>
                    <select class="form-select search-filter d-inline" id="logEntityFilter">
                        <option value="">Lọc theo thực thể</option>
                        <option value="Booking">Đặt vé</option>
                        <option value="Coupon">Mã giảm giá</option>
                    </select>
                </div>
                <div class="card">
                    <div class="card-header">Danh sách log khách hàng</div>
                    <div class="card-body">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Người thực hiện</th>
                                    <th>Hành động</th>
                                    <th>Thực thể</th>
                                    <th>ID thực thể</th>
                                    <th>Mô tả</th>
                                    <th>Thời gian</th>
                                    <th>IP</th>
                                </tr>
                            </thead>

                        </table>
                    </div>
                </div>
                <div class="card mt-4">
                    <div class="card-header">Phân tích tần suất hành động của khách hàng</div>
                    <div class="card-body">
                        <div class="chart-container">
                            <canvas id="activityChart"></canvas>
                        </div>
                    </div>
                </div>
            </section>

            <!-- Reports & Analytics -->
            <section id="reports" class="mt-5">
                <h2>Báo cáo & Thống kê</h2>
                <div class="row">
                    <div class="col-md-6">
                        <div class="card">
                            <div class="card-header">Doanh thu theo tháng</div>
                            <div class="card-body">
                                <div class="chart-container">
                                    <canvas id="revenueChart"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="card">
                            <div class="card-header">Tỷ lệ đặt vé</div>
                            <div class="card-body">
                                <div class="chart-container">
                                    <canvas id="bookingChart"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 mt-4">
                        <div class="card">
                            <div class="card-header">Đặt vé theo điểm đến</div>
                            <div class="card-body]">
                                <div class="chart-container">
                                    <canvas id="destinationChart"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 mt-4">
                        <div class="card">
                            <div class="card-header">Tỷ lệ sử dụng mã giảm giá</div>
                            <div class="card-body">
                                <div class="chart-container">
                                    <canvas id="couponChart"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>

    </body>
</html>