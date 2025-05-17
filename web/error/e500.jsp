<%-- 
    Document   : e404
    Created on : Apr 27, 2025, 11:23:55 PM
    Author     : manhphong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>500 - Sự cố máy chủ</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(180deg, #004aad 0%, #87ceeb 100%);
            font-family: 'Segoe UI', Arial, sans-serif;
            color: #fff;
            overflow: hidden;
            position: relative;
        }
        .container {
            text-align: center;
            position: relative;
            z-index: 2;
            padding: 20px;
        }
        h1 {
            font-size: 8rem;
            font-weight: 900;
            color: #FFD700; /* Màu vàng nhạt cho 404 */
            text-shadow: 0 2px 8px rgba(0, 0, 0, 0.4); /* Shadow nhẹ để nổi bật */
            animation: glow 2s ease-in-out infinite;
        }
        p {
            font-size: 1.6rem;
            margin: 20px auto;
            max-width: 600px;
            line-height: 1.8;
            color: #F5F5F5; /* Màu trắng đục cho đoạn văn */
            text-shadow: 0 1px 3px rgba(0, 0, 0, 0.3); /* Shadow nhẹ */
            font-weight: 500;
            text-align: center;
        }
        a {
            display: inline-block;
            text-decoration: none;
            color: #fff;
            background: linear-gradient(45deg, #ff4500, #ff8c00);
            padding: 15px 30px;
            border-radius: 50px;
            font-size: 1.3rem;
            font-weight: 600;
            box-shadow: 0 5px 15px rgba(255, 69, 0, 0.4);
            transition: all 0.3s ease;
        }
        a:hover {
            background: linear-gradient(45deg, #e63900, #ff7000);
            transform: translateY(-5px);
            box-shadow: 0 8px 20px rgba(255, 69, 0, 0.6);
        }
        .plane {
            position: absolute;
            top: 15%;
            left: 50%;
            transform: translateX(-50%) rotate(10deg);
            font-size: 5rem;
            filter: drop-shadow(0 5px 10px rgba(0, 0, 0, 0.3));
            animation: soar 12s ease-in-out infinite;
        }
        .clouds {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            pointer-events: none;
        }
        .cloud {
            position: absolute;
            background: rgba(255, 255, 255, 0.9);
            border-radius: 50%;
            box-shadow: 0 0 20px rgba(255, 255, 255, 0.5);
            animation: drift linear infinite;
        }
        .cloud:nth-child(1) {
            width: 200px;
            height: 60px;
            top: 20%;
            left: 10%;
            animation-duration: 25s;
        }
        .cloud:nth-child(2) {
            width: 150px;
            height: 50px;
            top: 50%;
            left: 70%;
            animation-duration: 20s;
        }
        .cloud:nth-child(3) {
            width: 180px;
            height: 55px;
            top: 70%;
            left: 30%;
            animation-duration: 30s;
        }
        .sky-overlay {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: radial-gradient(circle at center, rgba(255, 255, 255, 0.2), transparent 70%);
            z-index: 1;
        }
        @keyframes glow {
            0%, 100% {
                text-shadow: 0 2px 8px rgba(0, 0, 0, 0.4);
            }
            50% {
                text-shadow: 0 3px 12px rgba(0, 0, 0, 0.5);
            }
        }
        @keyframes soar {
            0%, 100% {
                transform: translateX(-50%) translateY(0) rotate(10deg);
            }
            50% {
                transform: translateX(-50%) translateY(30px) rotate(15deg);
            }
        }
        @keyframes drift {
            0% {
                transform: translateX(-100%);
            }
            100% {
                transform: translateX(100vw);
            }
        }
        @media (max-width: 768px) {
            h1 {
                font-size: 5rem;
            }
            p {
                font-size: 1.2rem;
            }
            a {
                padding: 12px 25px;
                font-size: 1.1rem;
            }
            .plane {
                font-size: 3rem;
                top: 10%;
            }
        }
    </style>
</head>
<body>
    <div class="sky-overlay"></div>
    <div class="clouds">
        <div class="cloud"></div>
        <div class="cloud"></div>
        <div class="cloud"></div>
    </div>
    <div class="plane">✈️</div>
    <div class="container">
        <h1>500</h1>
        <p>Rất tiếc! Đã xảy ra sự cố trên máy chủ của chúng tôi.</p>
        <p>Chúng tôi đang cố gắng khắc phục lỗi này nhanh nhất có thể.</p>
        <p>Vui lòng thử lại sau.</p>
        <a href="#" class="button">Quay lại trang chủ</a>
    </div>
</body>
</html>
