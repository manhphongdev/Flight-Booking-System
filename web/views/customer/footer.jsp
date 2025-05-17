<%-- 
    Document   : footer
    Created on : May 11, 2025, 10:36:20 AM
    Author     : manhphong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<style>
    .flyeasy-footer {
        background: linear-gradient(135deg, #1a3c6e, #2c5282);
        color: #fff;
        padding: 40px 0 20px;
        font-family: 'Arial', sans-serif;
    }
    .flyeasy-footer h5 {
        font-weight: 700;
        color: #ff6f61;
        margin-bottom: 15px;
        font-size: 18px;
    }
    .flyeasy-footer a {
        color: #d1e0f0;
        text-decoration: none;
        transition: color 0.3s ease;
    }
    .flyeasy-footer a:hover {
        color: #ff6f61;
    }
    .flyeasy-footer p, .flyeasy-footer li {
        color: #d1e0f0;
        font-size: 14px;
        line-height: 1.6;
        margin-bottom: 8px;
    }
    .flyeasy-footer ul {
        padding-left: 0;
    }
    .flyeasy-footer li {
        list-style: none;
        margin-bottom: 6px;
    }
    .flyeasy-footer-social-icons a {
        font-size: 20px;
        margin-right: 15px;
        color: #fff;
        transition: color 0.3s ease;
    }
    .flyeasy-footer-social-icons a:hover {
        color: #ff6f61;
    }
    .flyeasy-footer-column {
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        min-height: 100px;
    }
</style>

<footer class="flyeasy-footer">
    <div class="container">
        <div class="row">
            <!-- Thông tin liên hệ -->
            <div class="col-md-4 mb-4">
                <div class="flyeasy-footer-column">
                    <h5>Liên hệ</h5>
                    <p>Email: support@flyeasy.vn</p>
                    <p>Hotline: 1900 1234</p>
                    <p>Địa chỉ: 123 Đường Bay, TP. Hồ Chí Minh</p>
                    <div class="flyeasy-footer-social-icons">
                        <a href="#"><i class="bi bi-facebook"></i></a>
                        <a href="#"><i class="bi bi-instagram"></i></a>
                        <a href="#"><i class="bi bi-twitter"></i></a>
                    </div>
                </div>
            </div>

            <!-- Liên kết nhanh -->
            <div class="col-md-4 mb-4">
                <div class="flyeasy-footer-column">
                    <h5>Liên kết nhanh</h5>
                    <ul class="list-unstyled">
                        <li><a href="#">Trang chủ</a></li>
                        <li><a href="#">Đặt vé</a></li>
                        <li><a href="#">Khuyến mãi</a></li>
                        <li><a href="#">Chính sách</a></li>
                    </ul>
                </div>
            </div>

            <!-- Giới thiệu -->
            <div class="col-md-4 mb-4">
                <div class="flyeasy-footer-column">
                    <h5>Về Chúng tôi</h5>
                    <p>Chúng tôi mang đến trải nghiệm đặt vé máy bay dễ dàng, nhanh chóng và giá tốt nhất. Hãy cùng chúng tôi khám phá thế giới!</p>
                </div>
            </div>
        </div>
    </div>
</footer>

