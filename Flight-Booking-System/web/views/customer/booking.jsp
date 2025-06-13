<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đặt vé máy bay</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f4f4f9;
                font-family: Arial, sans-serif;
            }
            .booking-container {
                background: white;
                border-radius: 8px;
                padding: 20px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                margin-bottom: 20px;
            }
            .flight-details h3 {
                color: #2c3e50;
                margin-bottom: 15px;
            }
            .flight-details p {
                color: #7f8c8d;
                margin: 5px 0;
            }
            .passenger-form, .coupon-section {
                margin-top: 20px;
            }
            .passenger-form label, .coupon-section label {
                font-weight: bold;
                color: #2c3e50;
            }
            .btn-confirm {
                background-color: #3498db;
                border: none;
                padding: 10px 20px;
                font-size: 16px;
            }
            .btn-confirm:hover {
                background-color: #2980b9;
            }
            .total-price {
                font-size: 20px;
                font-weight: bold;
                color: #e74c3c;
                text-align: right;
            }
            .error-message {
                color: #e74c3c;
                font-size: 14px;
            }
            .passenger-section {
                border: 1px solid #ddd;
                padding: 15px;
                border-radius: 8px;
                margin-bottom: 15px;
            }
            .passenger-section h5 {
                margin-bottom: 10px;
                color: #3498db;
            }
            @media (max-width: 768px) {
                .booking-container {
                    padding: 15px;
                }
                .total-price {
                    text-align: center;
                }
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>

        <div class="container-fluid mt-4 mb-5">
            <div class="booking-container">
                <h1 class="text-center mb-4">Đặt vé máy bay</h1>

                <!-- Thông tin chuyến bay -->
                <div class="flight-details">
                    <h3><i class="fas fa-plane"></i> Thông tin chuyến bay</h3>
                    <p><strong>Hãng hàng không:</strong> ${flight.airlineName} (${flight.airlineCode})</p>
                    <p><strong>Từ:</strong> ${flight.departureAirportName} (${flight.departureAirportCode})</p>
                    <p><strong>Đến:</strong> ${flight.arrivalAirportName} (${flight.arrivalAirportCode})</p>
                    <p><strong>Thời gian:</strong> ${flight.departureTime} - ${flight.arrivalTime}</p>
                    <p><strong>Giá vé người lớn (phổ thông):</strong> ${flight.economyPrice} VND</p>
                    <p><strong>Giá vé người lớn (thương gia):</strong> ${flight.businessPrice} VND</p>
                    <p><strong>Giá vé trẻ em (phổ thông):</strong> ${flight.economyPrice * 0.75} VND</p>
                    <p><strong>Giá vé trẻ em (thương gia):</strong> ${flight.businessPrice * 0.75} VND</p>
                </div>

                <!-- Form nhập thông tin hành khách -->
                <form action="${pageContext.request.contextPath}/booking/confirm" method="POST">
                    <div class="passenger-form">
                        <h4><i class="fas fa-user"></i> Thông tin hành khách (Tổng: ${requestScope.adultCount + requestScope.childCount})</h4>

                        <!-- Hành khách người lớn -->
                        <c:if test="${requestScope.adultCount > 0}">
                            <h5>Người lớn</h5>
                            <c:forEach var="i" begin="0" end="${requestScope.adultCount - 1}">
                                <div class="passenger-section">
                                    <h5>Người lớn ${i + 1}</h5>
                                    <div class="row">
                                        <div class="col-md-6 mb-3">
                                            <label for="adultFirstName-${i}" class="form-label">Họ</label>
                                            <input type="text" class="form-control" id="adultFirstName-${i}" name="passengers[${i}].firstName" required>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="adultLastName-${i}" class="form-label">Tên</label>
                                            <input type="text" class="form-control" id="adultLastName-${i}" name="passengers[${i}].lastName" required>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="adultDateOfBirth-${i}" class="form-label">Ngày sinh</label>
                                            <input type="date" class="form-control" id="adultDateOfBirth-${i}" name="passengers[${i}].dateOfBirth" required>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="adultGender-${i}" class="form-label">Giới tính</label>
                                            <select class="form-select" id="adultGender-${i}" name="passengers[${i}].gender" required>
                                                <option value="">Chọn giới tính</option>
                                                <option value="male">Nam</option>
                                                <option value="female">Nữ</option>
                                                <option value="other">Khác</option>
                                            </select>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="adultNationality-${i}" class="form-label">Quốc tịch</label>
                                            <input type="text" class="form-control" id="adultNationality-${i}" name="passengers[${i}].nationality" required>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="adultIdentityNumber-${i}" class="form-label">Số CMND/Hộ chiếu</label>
                                            <input type="text" class="form-control" id="adultIdentityNumber-${i}" name="passengers[${i}].identityNumber" required>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="adultSeatClass-${i}" class="form-label">Loại ghế</label>
                                            <select class="form-select" id="adultSeatClass-${i}" name="passengers[${i}].seatClass" required>
                                                <option value="">Chọn loại ghế</option>
                                                <option value="economyClass">Phổ thông</option>
                                                <option value="businessClass">Thương gia</option>
                                            </select>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="adultSeatNumber-${i}" class="form-label">Số ghế</label>
                                            <select class="form-select" id="adultSeatNumber-${i}" name="passengers[${i}].seatNumber" required>
                                                <option value="">Chọn số ghế</option>
                                                <c:forEach var="seat" items="${requestScope.availableSeats}">
                                                    <option value="${seat.seatNumber}">${seat.seatNumber} (${seat.seatClass})</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <input type="hidden" name="passengers[${i}].passengerType" value="adult">
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>

                        <!-- Hành khách trẻ em -->
                        <c:if test="${requestScope.childCount > 0}">
                            <h5>Trẻ em</h5>
                            <c:forEach var="i" begin="${requestScope.adultCount}" end="${requestScope.adultCount + requestScope.childCount - 1}">
                                <div class="passenger-section">
                                    <h5>Trẻ em ${i - requestScope.adultCount + 1}</h5>
                                    <div class="row">
                                        <div class="col-md-6 mb-3">
                                            <label for="childFirstName-${i}" class="form-label">Họ</label>
                                            <input type="text" class="form-control" id="childFirstName-${i}" name="passengers[${i}].firstName" required>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="childLastName-${i}" class="form-label">Tên</label>
                                            <input type="text" class="form-control" id="childLastName-${i}" name="passengers[${i}].lastName" required>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="childDateOfBirth-${i}" class="form-label">Ngày sinh</label>
                                            <input type="date" class="form-control" id="childDateOfBirth-${i}" name="passengers[${i}].dateOfBirth" required>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="childGender-${i}" class="form-label">Giới tính</label>
                                            <select class="form-select" id="childGender-${i}" name="passengers[${i}].gender" required>
                                                <option value="">Chọn giới tính</option>
                                                <option value="male">Nam</option>
                                                <option value="female">Nữ</option>
                                                <option value="other">Khác</option>
                                            </select>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="childNationality-${i}" class="form-label">Quốc tịch</label>
                                            <input type="text" class="form-control" id="childNationality-${i}" name="passengers[${i}].nationality" required>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="childSeatClass-${i}" class="form-label">Loại ghế</label>
                                            <select class="form-select" id="childSeatClass-${i}" name="passengers[${i}].seatClass" required>
                                                <option value="">Chọn loại ghế</option>
                                                <option value="economyClass">Phổ thông</option>
                                                <option value="businessClass">Thương gia</option>
                                            </select>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="childSeatNumber-${i}" class="form-label">Số ghế</label>
                                            <select class="form-select" id="childSeatNumber-${i}" name="passengers[${i}].seatNumber" required>
                                                <option value="">Chọn số ghế</option>
                                                <c:forEach var="seat" items="${requestScope.availableSeats}">
                                                    <option value="${seat.seatNumber}">${seat.seatNumber} (${seat.seatClass})</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <input type="hidden" name="passengers[${i}].passengerType" value="child">
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>

                        <!-- Thông báo nếu không có hành khách -->
                        <c:if test="${requestScope.adultCount + requestScope.childCount == 0}">
                            <div class="text-center mt-3">
                                <p class="error-message">Vui lòng chọn ít nhất một hành khách!</p>
                            </div>
                        </c:if>
                    </div>

                    <!-- Áp dụng mã giảm giá -->
                    <div class="coupon-section">
                        <h4><i class="fas fa-ticket-alt"></i> Mã giảm giá</h4>
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" id="couponCode" name="couponCode" placeholder="Nhập mã giảm giá">
                            <span class="input-group-text"><i class="fas fa-ticket-alt"></i></span>
                        </div>
                        <c:if test="${not empty requestScope.couponError}">
                            <p class="error-message">${requestScope.couponError}</p>
                        </c:if>
                    </div>

                    <!-- Tổng giá (mặc định) -->
                    <div class="total-price mt-4">
                        <c:set var="totalPrice" value="${(requestScope.adultCount * flight.economyPrice) + (requestScope.childCount * flight.economyPrice * 0.75)}" />
                        <p>Tổng giá (ước tính): <span>${totalPrice}</span> VND</p>
                        <p class="text-muted">Giá cuối cùng sẽ được tính sau khi áp dụng mã giảm giá (nếu có).</p>
                    </div>

                    <!-- Nút xác nhận -->
                    <div class="text-center mt-4">
                        <input type="hidden" name="flightId" value="${flight.flightId}">
                        <input type="hidden" name="adultCount" value="${requestScope.adultCount}">
                        <input type="hidden" name="childCount" value="${requestScope.childCount}">
                        <button type="submit" class="btn btn-confirm text-white" <c:if test="${requestScope.adultCount + requestScope.childCount == 0}">disabled</c:if>>
                            <i class="fas fa-check-circle"></i> Xác nhận đặt vé
                        </button>
                    </div>
                </form>

                <!-- Thông báo lỗi nếu có -->
                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-danger mt-3" role="alert">
                        ${errorMessage}
                    </div>
                </c:if>
            </div>
        </div>

        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>