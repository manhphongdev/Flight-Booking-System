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
        <h1>Xác thực thất bại!</h1>
        <c:if test="${not empty errorRegister}">
            <p>${errorRegister}</p> 
        </c:if>
        <a href="${pageContext.request.contextPath}/home">Trở về trang chủ</a>
    </body>
</html>
