<%-- 
    Document   : home
    Created on : May 15, 2025, 7:12:46 PM
    Author     : manhphong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang chủ</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="CSS/home.css"/>
    </head>
    <body>

        <jsp:include page="header.jsp"></jsp:include>

        <!-- Hero Section -->
        <div class="hero-section">
            <div>
                <h1>Bay đến mọi nơi cùng AirTicket</h1>
                <p>Đặt vé dễ dàng, giá tốt nhất, hành trình an toàn!</p>
                <a href="#" class="btn btn-primary mt-3">Đặt vé ngay</a>
            </div>
        </div>

        <!-- Promotions Section -->
        <section class="promo-section">
            <div class="container">
                <h2 class="text-center mb-5" style="color: #1a3c6e;">Ưu đãi nổi bật</h2>
                <div class="row">
                    <div class="col-md-4 mb-4">
                        <div class="promo-card">
                            <img src="https://images.unsplash.com/photo-1507525428034-b723cf961d3e?q=80&w=2070&auto=format&fit=crop" alt="Ưu đãi 1">
                            <h3>Giảm 20% vé đi Đà Nẵng</h3>
                            <p>Khám phá Đà Nẵng với ưu đãi đặc biệt, chỉ từ 15/05/2025 đến 30/06/2025!</p>
                        </div>
                    </div>
                    <div class="col-md-4 mb-4">
                        <div class="promo-card">
                            <img src="https://images.unsplash.com/photo-1506748686214-e9df14d4d9d0?q=80&w=2070&auto=format&fit=crop" alt="Ưu đãi 2">
                            <h3>Bay Hà Nội chỉ từ 599k</h3>
                            <p>Trải nghiệm thủ đô với giá cực sốc, đặt ngay hôm nay!</p>
                        </div>
                    </div>
                    <div class="col-md-4 mb-4">
                        <div class="promo-card">
                            <img src="https://images.unsplash.com/photo-1508736793122-f516e3ba4e96?q=80&w=2070&auto=format&fit=crop" alt="Ưu đãi 3">
                            <h3>Khuyến mãi quốc tế</h3>
                            <p>Bay Singapore, Bangkok với giá ưu đãi, tiết kiệm đến 30%!</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Footer -->
        <jsp:include page="footer.jsp"></jsp:include>
        
    </body>
</html>
