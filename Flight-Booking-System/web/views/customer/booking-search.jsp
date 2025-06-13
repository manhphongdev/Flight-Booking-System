<%-- 
    Document   : booking
    Created on : May 13, 2025, 7:23:52 AM
    Author     : manhphong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            /* Custom styles for the booking page */
            body {
                background: linear-gradient(180deg, #f5f7fa 0%, #e9ecef 100%); /* Gradient xám nhạt, dễ nhìn */
                font-family: 'Inter', sans-serif;
                margin: 0;
                min-height: 100vh;
                background-attachment: fixed;
            }

            /* Container styling */
            .container {
                max-width: 800px;
                background: linear-gradient(135deg, #d1e7ff 0%, #c1d7ff 100%); /* Gradient xanh nhạt dịu */
                border-radius: 16px;
                box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
                padding: 2rem;
                margin-top: 2rem;
                margin-bottom: 2rem;
                position: relative;
                overflow: hidden;
                border: 1px solid rgba(0, 0, 0, 0.05);
            }

            /* Heading */
            h2.text-center {
                color: #1e3a8a; /* Xanh dương đậm, dễ đọc */
                font-weight: 700;
                margin-bottom: 2rem;
                font-size: 2rem;
            }

            /* Tabs styling */
            .nav-tabs {
                border-bottom: 2px solid #e0e7ff;
                margin-bottom: 2rem;
            }

            .nav-tabs .nav-link {
                color: #1e40af; /* Xanh dương đậm */
                font-weight: 500;
                padding: 0.75rem 1.5rem;
                border: none;
                border-radius: 8px 8px 0 0;
                background: #f1f5f9;
                transition: all 0.3s ease;
            }

            .nav-tabs .nav-link.active {
                background: #ffffff;
                color: #1e3a8a;
                font-weight: 600;
                border-bottom: 3px solid #22c55e; /* Xanh lá nổi bật */
            }

            .nav-tabs .nav-link:hover {
                background: #e0e7ff;
                color: #1e40af;
            }

            /* Form styling */
            .tab-content {
                background: #ffffff; /* Nền trắng, dễ đọc */
                border-radius: 12px;
                padding: 1.5rem;
                border: 1px solid #e0e7ff;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
            }

            .form-label {
                color: #1e293b; /* Chữ xám đậm */
                font-weight: 600;
                margin-bottom: 0.5rem;
            }

            .form-control, .form-select {
                border: 1px solid #d1d5db; /* Viền xám nhạt */
                border-radius: 8px;
                padding: 0.75rem;
                font-size: 1rem;
                background: #f9fafb;
                color: #1e293b;
                transition: border-color 0.3s ease, box-shadow 0.3s ease;
            }

            .form-control:focus, .form-select:focus {
                border-color: #3b82f6; /* Xanh dương focus */
                box-shadow: 0 0 8px rgba(59, 130, 246, 0.2);
                outline: none;
            }

            .form-control::placeholder {
                color: #9ca3af; /* Placeholder xám nhạt */
            }

            /* Button styling */
            .btn-primary {
                background: linear-gradient(90deg, #22c55e 0%, #16a34a 100%); /* Xanh lá gradient */
                border: none;
                border-radius: 8px;
                padding: 0.75rem 2rem;
                font-weight: 700;
                font-size: 1.1rem;
                color: #ffffff;
                text-transform: uppercase;
                transition: background 0.3s ease, transform 0.2s ease, box-shadow 0.3s ease;
            }

            .btn-primary:hover {
                background: linear-gradient(90deg, #16a34a 0%, #15803d 100%); /* Xanh lá đậm hơn */
                transform: translateY(-2px);
                box-shadow: 0 4px 12px rgba(34, 197, 94, 0.3);
            }

            .btn-primary:active {
                transform: translateY(0);
                box-shadow: none;
            }

            /* Responsive adjustments */
            @media (max-width: 576px) {
                .container {
                    padding: 1.5rem;
                    margin-top: 1.5rem;
                    margin-bottom: 1.5rem;
                }

                h2.text-center {
                    font-size: 1.5rem;
                }

                .nav-tabs .nav-link {
                    padding: 0.5rem 1rem;
                    font-size: 0.9rem;
                }

                .btn-primary {
                    padding: 0.5rem 1.5rem;
                    font-size: 1rem;
                }

                .tab-content {
                    padding: 1rem;
                }
            }
        </style>
    </head>
    <body>
        <jsp:include page="../customer/header.jsp"></jsp:include>

            <div class="container mt-5 mb-5">
                <h2 class="text-center mb-4">Đặt vé máy bay</h2>
                <!-- Nav tabs -->
                <ul class="nav nav-tabs justify-content-center mb-4" id="flightTab" role="tablist">
                    <li class="nav-item" role="presentation">
                        <button class="nav-link active" id="roundTrip-tab" data-bs-toggle="tab" data-bs-target="#roundTrip"
                                type="button" role="tab" aria-controls="roundTrip" aria-selected="true">
                            Khứ hồi
                        </button>
                    </li>
                    <li class="nav-item" role="presentation">
                        <button class="nav-link" id="oneWay-tab" data-bs-toggle="tab" data-bs-target="#oneWay"
                                type="button" role="tab" aria-controls="oneWay" aria-selected="false">
                            Một chiều
                        </button>
                    </li>
                </ul>
                <!-- Tab panes -->
                <div class="tab-content">
                    <!-- Một chiều -->
                    <div class="tab-pane fade" id="oneWay" role="tabpanel" aria-labelledby="oneWay-tab">
                        <form action="${pageContext.request.contextPath}/flight/search" method="POST">
                        <input type="hidden" name="typeOption" value="oneWay" >

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label class="form-label">Điểm đi</label>
                                <input type="text" name="departure" class="form-control" placeholder="Nhập điểm đi">
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Điểm đến</label>
                                <input type="text" name="arrival" class="form-control" placeholder="Nhập điểm đến">
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Ngày đi</label>
                            <input type="date" name="dayStart" class="form-control">
                        </div>
                        <div class="row mb-3">
                            <div class="col-md-4">
                                <label class="form-label">Người lớn</label>
                                <select name="adultNumber" class="form-select">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <label class="form-label">Trẻ em</label>
                                <select name="childNumber" class="form-select">
                                    <option value="0">0</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                </select>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Hạng vé</label>
                            <select class="form-select"  name="ticketType">
                                <option value="economyClass">Phổ thông</option>
                                <option value="businessClass">Thương gia</option>
                            </select>
                        </div>
                        <div class="text-center">
                            <button class="btn btn-primary">Tìm chuyến bay</button>
                        </div>
                    </form>
                </div>

                <!-- Khứ hồi -->
                <div class="tab-pane fade show active" id="roundTrip" role="tabpanel" aria-labelledby="roundTrip-tab">
                    <form action="${pageContext.request.contextPath}/flight/search" method="POST">
                        <input type="hidden" name="typeOption" value="roundTrip" >
                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label class="form-label">Điểm đi</label>
                                <input type="text" name="departure" class="form-control" placeholder="Nhập điểm đi">
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Điểm đến</label>
                                <input type="text" name="arrival" class="form-control" placeholder="Nhập điểm đến">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label class="form-label">Ngày đi</label>
                                <input type="date" name="dayStart" class="form-control">
                            </div>
                            <div class="col-md-6">
                                <label class="form-label">Ngày về</label>
                                <input type="date" name="dayReturn" class="form-control">
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Hạng vé</label>
                            <select class="form-select" name="ticketType">
                                <option value="economyClass">Phổ thông</option>
                                <option value="businessClass">Thương gia</option>
                            </select>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Tìm chuyến bay</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="../customer/footer.jsp"></jsp:include>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>