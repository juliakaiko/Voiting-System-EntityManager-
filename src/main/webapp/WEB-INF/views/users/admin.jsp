<%-- 
    Document   : admin
    Created on : 23 июл. 2022 г., 17:59:04
    Author     : Dima
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value='/static/css/mylogin_style.css' />" rel="stylesheet"></link>
    </head>
    <body>
        <h1>Admin Control Panel</h1>
        <h4><font color=red>${message}</font></h4>
        <!-- comment -->
            <a href="/Electors" class="btn btn-white btn-animate">Electors </a>
            <br><br><br><br><br>
            <a href="/Candidates" class="btn btn-white btn-animate">Candidates</a>
    </body>
</html>
