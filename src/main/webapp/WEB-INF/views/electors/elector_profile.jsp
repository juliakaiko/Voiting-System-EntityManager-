<%-- 
    Document   : test
    Created on : 16 июл. 2022 г., 22:12:50
    Author     : Dima
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value='/static/css/table_style.css' />" rel="stylesheet"></link> 
    </head>

    <body>
         <h4><font color=red> ${novoting}</font> </h4>
         <h2>My Profile</h2>    
        <table border="1" BGCOLOR="#FFFAFA"  cellspacing="0" cellpadding="5" 
                   var="electorProfile" items="${electorProfile}"  >
            <tr>
                <td><b>  ID  </b></td>
                <td><b>  First Name </b></td>
                <td><b>  Last Name  </b></td>
                <td><b>  Age  </b></td>
                <td><b>  Passport Series  </i></b></td>
                <td><b>  Passport Number </b></td>
                <td><b>  Voted </b></td>
                <td><b>  Edit data  </b></td>
            </tr>
            <%--<c:forEach var="electorProfile" items="${electorProfile}"">--%>
                <tr>
                    <td><b>${electorProfile.id}</b></td>
                    <td>${electorProfile.firstName}</td>
                    <td>${electorProfile.lastName}</td>
                    <td>${electorProfile.age}</td>
                    <td>${electorProfile.passportSeries}</td>
                    <td>${electorProfile.passportNum}</td>
                    <td>${electorProfile.voted}</td>
                    <td align="center"> 
                        <form action="/Electors/edit/${electorProfile.id}" method="get">  
                            <input type="submit" formaction="/Electors/edit/${electorProfile.id}" value ="EDIT" class="btn btn-white btn-animate" />
                        </form> 
                    </td>
                </tr>
            <%-- </c:forEach> --%>        
        </table>
        <br><br>
        <a href="/Candidates" class="btn btn-white btn-animate">VOTING SYSTEM</a>
    </body>
</html>
