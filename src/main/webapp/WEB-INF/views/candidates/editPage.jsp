<%-- 
    Document   : editPage
    Created on : 23 июл. 2022 г., 21:18:21
    Author     : Dima
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value='/static/css/myregistration_style.css' />" rel="stylesheet"></link>
    </head>
    <body>
        <div class="registration-form"> 
            <h2>Edit Candidate</h2>
            <br>
             <h4><font color=red> ${message}</font> </h4>
              <p class="heading">${message}</p>
            <form:form  method="POST" modelAttribute="candidate" class="form-horizontal ">     
                <div class="row justify-content-center">
                    <div class="form-group col-md-12"> 
                        <div class="col-md-7">
                            <%-- Элемент для ввода данных, path=> copy-paste из Entity --%>
                            <label class="col-md-2 control-lable" for="firstName">First Name</label>
                            <form:input type="text" path="firstName" id="firstName" placeholder="Enter candidate's First Name"  class="form-control input-sm"/>
                            <div class="has-error">
				<form:errors path="firstName" class="help-inline"/>
			    </div>   
                        </div>
                    </div>
                </div>             
                <div class="row justify-content-center">
                    <div class="form-group col-md-12">         
                        <div class="col-md-7">
                            <label class="col-md-2 control-lable" for="lastName">Last Name</label>
                            <form:input type="text" path="lastName" id="lastName" placeholder="Enter candidate's Last Name" class="form-control input-sm"/>
                             <div class="has-error">
				<form:errors path="lastName" class="help-inline"/>
			    </div>  
                        </div>
                    </div>
                </div>               
                 <div class="row justify-content-center">
                    <div class="form-group col-md-12">        
                        <div class="col-md-7">
                            <label class="col-md-2 control-lable" for="age">Age</label>
                            <form:input type="text" path="age" id="age" placeholder="Enter candidate's Age" class="form-control input-sm"/>
                             <div class="has-error">
				<form:errors path="age" class="help-inline"/>
			    </div>  
                        </div>
                    </div>
                </div> 
                <div class="row justify-content-center">
                    <div class="form-group col-md-12">        
                        <div class="col-md-7">
                            <label class="col-md-2 control-lable" for="voices">Voices</label>
                            <form:input type="text" path="voices" id="voices" placeholder="Enter candidate's voices" class="form-control input-sm"/>
                             <div class="has-error">
				<form:errors path="voices" class="help-inline"/>
			    </div>  
                        </div>
                    </div>
                </div>   
               
                <div class="row justify-content-end ">
                    <div class="form-actions floatRight">  
                        <input type="submit" value ="Save" class="btn btn-white btn-animate" /> 
                    </div>
                </div>                 
            </form:form>
             
        </div> 
    </body>
</html>
