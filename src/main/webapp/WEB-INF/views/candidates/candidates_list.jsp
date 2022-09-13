<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<html>
    <head>
    <title>CANDIDATES</title>
    <link href="<c:url value='/static/css/table_style.css' />" rel="stylesheet"></link>
    </head>
    
<body>
    <h4><font color=red> ${message}</font> </h4>
    <h2>CANDIDATES</h2>
    <table border="1" BGCOLOR="#FFFAFA"  cellspacing="0" cellpadding="5">
        <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Age</th>
            <th>Voices</th>
            <th>Vote</th>
            <th>Details</th><!-- comment -->
            <th>Edit</th><!-- comment -->
            <th>Delete</th>
        </tr>
        <c:forEach var="candidates" items="${candidates}">
            <tr>
                <td> <b>${candidates.id} </b></td>
                <td>${candidates.firstName}</td>
                <td>${candidates.lastName}</td>
                <td>${candidates.age}</td> 
                <td>${candidates.voices}</td> 
                <td align="center">   
                   <form action="/Candidates/voting/${candidates.id}" method="post">  
                        <input type="submit" formaction="/Candidates/voting/${candidates.id}" value ="Vote" class="btn btn-white btn-animate" />
                    </form>             
                 </td>  
                <td align="center">
                    <form action="/Candidates/${candidates.id}" method="get">  
                        <input type="submit" formaction="/Candidates/${candidates.id}" value ="Show details" class="btn btn-white btn-animate" />
                    </form>   
                </td>
                <td align="center"> 
                    <form action="/Candidates/edit/${candidates.id}" method="get">  
                        <input type="submit" formaction="/Candidates/edit/${candidates.id}" value ="EDIT" class="btn btn-white btn-animate" />
                    </form> 
                </td>
                
                <td align="center">   
                   <form action="/Candidates/delete/${candidates.id}" method="post">  
                        <input type="submit" formaction="/Candidates/delete/${candidates.id}" value ="Delete" class="btn btn-white btn-animate" />
                    </form>   
                </td> 
            </tr>
        </c:forEach>
    </table>
    <br><br>
    <a href="/Candidates/results" class="red_btn">Voting results</a>
    <br><br><br><br>
    
    <a href="/Candidates/add" class="btn">Add new candidate</a>
    
    <br><br><br><br>
    <a href="/" class="btn">Exit</a>
</body>
</html>
