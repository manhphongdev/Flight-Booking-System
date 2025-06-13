<%-- 
    Document   : flyeasy-footer
    Created on : May 11, 2025, 10:36:20 AM
    Author     : manhphong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<style>
    .flyeasy-custom-footer {
        background: linear-gradient(135deg, #1a3c6e, #2c5282);
        color: #fff;
        padding: 40px 0 20px;
        font-family: 'Arial', sans-serif;
        box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.3);
        animation: fadeIn 1.2s ease-in-out;
    }

    @keyframes fadeIn {
        from {
            opacity: 0;
            transform: translateY(20px);
        }
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }

    .flyeasy-custom-footer h5 {
        font-weight: 700;
        color: #ff6f61;
        margin-bottom: 15px;
        font-size: 18px;
    }

    .flyeasy-custom-footer a {
        color: #d1e0f0;
        text-decoration: none;
        transition: color 0.4s ease, transform 0.3s ease-in-out;
    }

    .flyeasy-custom-footer a:hover {
        color: #ff6f61;
        transform: scale(1.1);
    }

    .flyeasy-custom-footer p, .flyeasy-custom-footer li {
        color: #d1e0f0;
        font-size: 14px;
        line-height: 1.6;
        margin-bottom: 8px;
    }

    .flyeasy-custom-footer ul {
        padding-left: 0;
    }

    .flyeasy-custom-footer li {
        list-style: none;
        margin-bottom: 6px;
    }

    .flyeasy-custom-footer-social-icons a {
        font-size: 20px;
        margin-right: 15px;
        color: #fff;
        transition: color 0.3s ease, transform 0.3s ease-in-out;
    }

    .flyeasy-custom-footer-social-icons a:hover {
        color: #ff6f61;
        transform: rotate(10deg);
    }

    .flyeasy-custom-footer-content {
        max-width: 1200px;
        margin: 0 auto;
        display: flex;
        justify-content: space-around;
        flex-wrap: wrap;
        padding: 0 20px;
    }

    .flyeasy-custom-footer-column {
        flex: 1;
        min-width: 200px;
        padding: 0 15px;
        box-sizing: border-box;
        text-align: center;
    }

    @media (max-width: 768px) {
        .flyeasy-custom-footer-content {
            flex-direction: column;
            align-items: center;
        }
        .flyeasy-custom-footer-column {
            margin-bottom: 20px;
        }
    }
</style>

<footer class="flyeasy-custom-footer">
    <div class="flyeasy-custom-footer-content">
        <!-- Thông tin liên hệ -->
        <div class="flyeasy-custom-footer-column">
            <h5>Liên hệ</h5>
            <p>Email: support@flyeasy.vn</p>
            <p>Hotline: 1900 1234</p>
            <p>Địa chỉ: 123 Đường Bay, TP. Hồ Chí Minh</p>
            <div class="flyeasy-custom-footer-social-icons">
                <a href="#"><i class="bi bi-facebook"></i></a>
                <a href="#"><i class="bi bi-instagram"></i></a>
                <a href="#"><i class="bi bi-twitter"></i></a>
            </div>
        </div>

        <!-- Liên kết nhanh -->
        <div class="flyeasy-custom-footer-column">
            <h5>Liên kết nhanh</h5>
            <ul>
                <li><a href="#">Trang chủ</a></li>
                <li><a href="#">Đặt vé</a></li>
                <li><a href="#">Khuyến mãi</a></li>
            </ul>
        </div>

        <!-- Giới thiệu -->
        <div class="flyeasy-custom-footer-column">
            <h5>Về Chúng tôi</h5>
            <p>Chúng tôi mang đến trải nghiệm đặt vé máy bay dễ dàng, nhanh chóng và giá tốt nhất. Hãy cùng chúng tôi khám phá thế giới!</p>
        </div>
    </div>
</footer>