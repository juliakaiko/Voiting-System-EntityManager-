<%-- 
    Document   : candidate_details
    Created on : 17 июл. 2022 г., 20:38:57
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
      <h4><font color=red> ${message}</font> </h4>
      <h2>Candidate Information</h2>
        <table border="1" cellspacing="0" cellpadding="4" var="candidate" items="${candidate}"
               var="details" items="${details}"
               >
                <tr>
                    <td><b>ID</b></td>
                    <td>${candidate.id}</td>
                </tr>
                <tr>
                    <td><b>First Name</b></td>
                    <td>${candidate.firstName}</td>
                </tr>
                <tr>
                    <td><b>Last Name</b></td>
                    <td>${candidate.lastName}</td>
                </tr>
                <tr>
                    <td><b>Age</b></td>
                    <td>${candidate.age}</td> 
                </tr>

                <tr>
                    <td><b>Education</b></td>
                    <td>${details.education}</td> 
                </tr>
                <tr>
                    <td><b>Annual Income</b></td>
                    <td>${details.annualIncome}</td>
                </tr>
                <tr>
                    <td><b>Property</b></td>
                    <td>${details.property}</td>
                </tr>
                <tr>
                    <td><b>Election Promises</b></td>
                    <td>${details.electionPromises}</td>
                </tr>
        </table>   
            <br> <br>
            <form action="/CandidateDetails/edit/${candidate.id}" method="get">  
                <input type="submit" formaction="/CandidateDetails/edit/${candidate.id}" 
                       value ="EDIT" class="btn btn-white btn-animate" />
            </form> 
            <br> <br> 
            <a href="/Candidates" class="btn btn-white btn-animate" action="/Candidates">Back</a>            
    </body>
</html>
