<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kết quả tìm kiếm vé máy bay</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
        <style>
            body {
                background-color: #f4f4f9;
                font-family: Arial, sans-serif;
            }
            .search-summary {
                text-align: center;
                margin-bottom: 20px;
                font-size: 18px;
                color: #333;
            }
            .flight-card {
                background: white;
                border-radius: 8px;
                padding: 15px;
                margin-bottom: 15px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }
            .flight-info h3 {
                margin: 0;
                font-size: 18px;
                color: #2c3e50;
            }
            .flight-info p {
                margin: 5px 0;
                color: #7f8c8d;
            }
            .flight-price {
                font-size: 20px;
                color: #e74c3c;
                font-weight: bold;
                text-align: center;
            }
            .btn-book {
                background-color: #3498db;
                border: none;
                padding: 10px 20px;
                font-size: 16px;
            }
            .btn-book:hover {
                background-color: #2980b9;
            }
            .filter-section {
                background: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
                position: sticky;
                top: 20px;
                max-height: calc(100vh - 40px);
                overflow-y: auto;
            }
            .filter-section h4 {
                margin-bottom: 15px;
                color: #2c3e50;
            }
            .results-section {
                padding-left: 20px;
            }
            @media (max-width: 768px) {
                .filter-section {
                    position: static;
                    margin-bottom: 20px;
                }
                .results-section {
                    padding-left: 0;
                }
            }
        </style>
    </head>
    <body>

        <jsp:include page="header.jsp"></jsp:include>

            <div class="container-fluid mb-5">

                <div class="row">
                    <!-- Bộ lọc bên trái -->
                    <div class="col-md-3">
                        <div class="filter-section">
                            <h4><i class="fas fa-filter"></i> Bộ lọc</h4>
                            <form action="${pageContext.request.contextPath}/flight/result" method="GET">
                            <div class="mb-3">
                                <label for="departureTime" class="form-label">Thời gian cất cánh</label>
                                <select class="form-select" id="departureTime" name="departureTime">
                                    <option value="">Tất cả</option>
                                    <option value="00:00">00:00</option>
                                    <option value="06:00">06:00</option>
                                    <option value="12:00">12:00</option>
                                    <option value="18:00">18:00</option>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label for="arrivalTime" class="form-label">Thời gian hạ cánh</label>
                                <select class="form-select" id="arrivalTime" name="arrivalTime">
                                    <option value="">Tất cả</option>
                                    <option value="00:00">00:00</option>
                                    <option value="06:00">06:00</option>
                                    <option value="12:00">12:00</option>
                                    <option value="18:00">18:00</option>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label for="airline" class="form-label">Hãng hàng không</label>
                                <select class="form-select" id="airline" name="airline">
                                    <option value="">Tất cả</option>
                                    <option value="vn">Vietnam Airlines</option>
                                    <option value="qh">Bamboo Airways</option>
                                    <option value="vj">Vietjet Air</option>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label for="minPrice" class="form-label">Giá tối thiểu (VND)</label>
                                <input type="number" class="form-control" id="minPrice" name="minPrice" min="0" placeholder="0">
                            </div>

                            <div class="mb-3">
                                <label for="maxPrice" class="form-label">Giá tối đa (VND)</label>
                                <input type="number"  class="form-control" id="maxPrice" name="maxPrice" min="0"  placeholder="Không giới hạn">
                            </div>

                            <hr>
                            <h5><i class="fas fa-sort"></i> Sắp xếp</h5>
                            <div class="mb-3">
                                <label for="sortPrice" class="form-label">Theo giá</label>
                                <select class="form-select" id="sortPrice" name="sortPrice">
                                    <option value="low-to-high">Thấp đến cao</option>
                                    <option value="high-to-low">Cao đến thấp</option>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label for="sortDeparture" class="form-label">Theo thời gian</label>
                                <select class="form-select" id="sortDeparture" name="sortDeparture">
                                    <option value="early-to-late">Sớm đến muộn</option>
                                    <option value="late-to-early">Muộn đến sớm</option>
                                </select>
                            </div>

                            <button type="submit" class="btn btn-primary w-100">
                                <i class="fas fa-search"></i> Áp dụng
                            </button>
                        </form>
                    </div>
                </div>

                <!-- Kết quả tìm kiếm bên phải -->
                <div class="col-md-9">
                    <h1 class="text-center mb-4">Kết quả tìm kiếm chuyến bay</h1>

                    <div class="results-section">
                        <!-- Danh sách chuyến bay từ flightSearch -->
                        <c:forEach var="flight" items="${sessionScope.flightSearch}">
                            <div class="flight-card d-flex align-items-center">
                                <div class="flight-info flex-grow-1">
                                    <h3><i class="fas fa-plane"></i> ${flight.airlineName} - ${flight.airlineCode}</h3>
                                    <p><i class="fas fa-map-marker-alt"></i> ${flight.departureAirportName} (${flight.departureAirportCode}) → ${flight.arrivalAirportName} (${flight.arrivalAirportCode})</p>
                                    <p><i class="fas fa-clock"></i> ${flight.departureTime} - ${flight.arrivalTime}</p>
                                </div>
                                <div class="flight-price flex-shrink-0 mx-3">
                                    ${flight.price} VND
                                </div>
                                <div class="flex-shrink-0">
                                    <a href="#" class="btn btn-book text-white">
                                        <i class="fas fa-ticket-alt"></i> Đặt vé
                                    </a>
                                </div>
                            </div>
                        </c:forEach>

                        <!-- Danh sách chuyến bay từ flightSearchOneWay -->
                        <c:forEach var="flight" items="${sessionScope.flightSearchOneWay}">

                            <form action="${pageContext.request.contextPath}/flight/search/result" method="GET">
                                <div class="flight-card d-flex align-items-center">
                                    <input type="hidden" name="flightIdSelect" value="${flight.flightId}">
                                    <div class="flight-info flex-grow-1">
                                        <h3><i class="fas fa-plane"></i> ${flight.airlineName} - ${flight.airlineCode}</h3>
                                        <p><i class="fas fa-map-marker-alt"></i> ${flight.departureAirportName} (${flight.departureAirportCode}) → ${flight.arrivalAirportName} (${flight.arrivalAirportCode})</p>
                                        <p><i class="fas fa-clock"></i> ${flight.departureTime} - ${flight.arrivalTime}</p>
                                    </div>
                                    <div class="flight-price flex-shrink-0 mx-3">
                                        ${flight.price} VND
                                    </div>
                                    <div class="flex-shrink-0">
                                        <a href="${pageContext.request.contextPath}/flight/booking/create" class="btn btn-book text-white">
                                            <i class="fas fa-ticket-alt"></i> Đặt vé
                                        </a>
                                    </div>
                                </div>

                            </form>
                        </c:forEach>

                        <!-- Hiển thị thông báo nếu không có kết quả -->
                        <c:if test="${empty sessionScope.flightSearch and empty sessionScope.flightSearchOneWay}">
                            <div class="text-center mt-5">
                                <i class="fas fa-search fa-3x text-muted mb-3"></i>
                                <h4>Không tìm thấy chuyến bay phù hợp</h4>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

        <script>
            window.addEventListener("beforeunload", function () {
                fetch("CancelSearchServlet", {method: "POST"});
            });
        </script>   

        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
