<%-- 
    Document   : registration
    Created on : 6 июл. 2022 г., 19:52:52
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
            <h2>Registration Form</h2>
            <br>
            <h4><font color=red> ${failed_registration}</font> </h4>
            <h4><font color=red> ${wrongid}</font> </h4>
            <form:form  method="POST" modelAttribute="elector" class="form-horizontal ">     
                <div class="row justify-content-center">
                    <div class="form-group col-md-12">         
                        <div class="col-md-7">
                            <label class="col-md-2 control-lable" for="login">Username</label>
                            <form:input type="text" path="login" id="login" placeholder="Enter your Username" class="form-control input-sm"/>
                             <div class="has-error">
				<form:errors path="login" class="help-inline"/>
			    </div>  
                        </div>
                    </div>
                </div>                             
                <div class="row justify-content-center ">
                    <div class="form-group col-md-12">        
                        <div class="col-md-7">
                            <label class="col-md-2 control-lable" for="password">Password</label>
                            <form:input type="text" path="password" id="password" placeholder="Enter your Password" class="form-control input-sm"/>
                             <div class="has-error">
				<form:errors path="password" class="help-inline"/>
			    </div>  
                        </div>
                    </div>
                </div>  
                <div class="row justify-content-center">
                    <div class="form-group col-md-12"> 
                        <div class="col-md-7">
                            <%-- Элемент для ввода данных, path=> copy-paste из Entity --%>
                            <label class="col-md-2 control-lable" for="firstName">First Name</label>
                            <form:input type="text" path="firstName" id="firstName" placeholder="Enter your First Name"  class="form-control input-sm"/>
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
                            <form:input type="text" path="lastName" id="lastName" placeholder="Enter your Last Name" class="form-control input-sm"/>
                             <div class="has-error">
				<form:errors path="lastName" class="help-inline"/>
			    </div>  
                        </div>
                    </div>
                </div>               
                 <div class="row justify-content-center">
                    <div class="form-group col-md-12">        
                        <div class="col-md-7">
                            <label class="col-md-2 control-lable" for="lastName">Age</label>
                            <form:input type="text" path="age" id="age" placeholder="Enter your Age" class="form-control input-sm"/>
                             <div class="has-error">
				<form:errors path="age" class="help-inline"/>
			    </div>  
                        </div>
                    </div>
                </div>                           
                <div class="row justify-content-center">
                    <div class="form-group col-md-12">                              
                        <div class="col-md-7">
                             <label class="col-md-3 control-lable" for="passportSeries">Passport Series</label>
                            <form:input type="text" path="passportSeries" id="passportSeries" placeholder="Enter your Passport Series" class="form-control input-sm"/>
                             <div class="has-error">
				<form:errors path="passportSeries" class="help-inline"/>
			    </div>  
                        </div>
                    </div>
                </div>                                                                           
                <div class="row justify-content-center">
                    <div class="form-group col-md-12">         
                        <div class="col-md-7">
                            <label class="col-md-3 control-lable" for="passportNum">Passport Number</label>
                            <form:input type="text" path="passportNum" id="passportNum" placeholder="Enter your Passport Number" class="form-control input-sm"/>
                             <div class="has-error">
				<form:errors path="passportNum" class="help-inline"/>
			    </div>  
                        </div>
                    </div>
                </div>             
                <div class="row justify-content-end ">
                    <div class="form-actions floatRight">  
                        <input type="submit" value ="Save" class="btn btn-white btn-animate" /> 
                    </div>
                </div>  
                
               
                 
                <%--   
                 <form  action="/Elector/edit/${electors_list.id}" method="post">  
                    <input type="submit" formaction="/Elector/edit/${electors_list.id}" value ="UPDATE" class="btn btn-white btn-animate" />
                </form> 
                <div class="row justify-content-center">
                    <a href="/registration">Registration</a>   
                </div> 
                            
                <a href="/registration" class="btn">Registration</a>          
            --%>                
            </form:form>
        </div> 
    </body>
</html>
