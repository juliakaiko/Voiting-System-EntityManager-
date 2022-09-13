<%-- 
    Document   : electors_list
    Created on : 18 ???. 2022 ?., 15:19:48
    Author     : Dima
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<html>
    <head>
        <title>ELECTORS</title>
        <link href="<c:url value='/static/css/table_style.css' />" rel="stylesheet"></link>
    </head>
    <body>

    <h2>ELECTORS</h2>
    <h4><font color=red> ${message}</font> </h4>
    <table border="1" BGCOLOR="#FFFAFA"  cellspacing="0" cellpadding="5">
        <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Age</th>
            <th>Passport Series</th>
            <th>Passport Num</th>
            <th>Login</th><!-- comment -->
           <!-- <th>Passworld</th> comment -->
            <th>Voted</th>
            <th>Edit</th><!-- <th>Voted</th> -->
            <th>Delete</th>
        </tr>
        <c:forEach var="electors_list" items="${electors_list}">
            <tr>
                <td>${electors_list.id}</td>
                <td>${electors_list.firstName}</td>
                <td>${electors_list.lastName}</td>
                <td>${electors_list.age}</td> 
                <td>${electors_list.passportSeries}</td> 
                <td>${electors_list.passportNum}</td> 
                <td>${electors_list.login}</td> 
               <!-- <td>${electors_list.password}</td>  -->
                <td>${electors_list.voted}</td>  
                
                 <td align="center"> 
                    <form action="/Electors/edit/${electors_list.id}" method="get">  
                        <input type="submit" formaction="/Electors/edit/${electors_list.id}" value ="Edit" class="btn btn-white btn-animate" />
                    </form> 
                </td>
                <%--   <td align="center"><a href="/Electors/edit/${electors_list.id}" class="btn">Edit</a></td> --%>
                <td align="center">   
                   <form action="/Electors/delete/${electors_list.id}" method="post">  
                        <input type="submit" formaction="/Electors/delete/${electors_list.id}" value ="Delete" class="btn btn-white btn-animate" />
                    </form>   
                    <%-- <td align="center"> <a href="/Electors/delete/${electors_list.id}" class="btn">Delete</a> </td>  --%>
                </td> 
                   
            </tr>
        </c:forEach>
    </table>
    <br> <br>
    <form action="/?login=admin&password=admin123" method="post">  
        <input type="submit" formaction="/?login=admin&password=admin123" value ="Admin Control Panel" class="btn btn-white btn-animate" />
    </form>   
    <br> <br>
    <form action="/" method="get">  
        <input type="submit" formaction="/" value ="EXIT" class="btn btn-white btn-animate" />
    </form>
    
    
    </body>
</html>

