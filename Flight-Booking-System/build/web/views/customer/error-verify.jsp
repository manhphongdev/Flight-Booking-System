<%-- 
    Document   : error-verify
    Created on : Jun 3, 2025, 7:18:55 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify failed</title>
    </head>
    <body>
        <h1>Verify failed!</h1>
        <c:if test="${param.message == 'id not found!'}">
            <p>Người dùng không tồn tại! Vui lòng click vào đây để trở về trang chủ</p>
            <a href="${pageContext.request.contextPath}/home">Trở về trang chủ</a>
        </c:if>
        <c:if test="${param.message == 'token not exist!'}">
            <p>Link xác nhận không tồn tại hoặc đã hết hạn! Vui lòng đăng ký lại</p>
            <a href="${pageContext.request.contextPath}/home">Trở về trang chủ</a>
        </c:if>   
        <c:if test="${param.message == 'token has expired!'}">
            <p>Link xác nhận không tồn tại hoặc đã hết hạn! Vui lòng đăng ký lại</p>
            <a href="${pageContext.request.contextPath}/home">Trở về trang chủ</a>
        </c:if> 
            <c:if test="${param.message == null}">
            <p>ERROR</p>
            <a href="${pageContext.request.contextPath}/home">Trở về trang chủ</a>
        </c:if> 
    </body>
</html>
