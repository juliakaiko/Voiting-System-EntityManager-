<%-- 
    Document   : EditPageForDetails
    Created on : 23 июл. 2022 г., 21:41:59
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
            <form:form  method="POST" modelAttribute="details" class="form-horizontal ">               
                <div class="row justify-content-center">
                    <div class="form-group col-md-12">                              
                        <div class="col-md-7">
                             <label class="col-md-3 control-lable" for="education">Education</label>
                            <form:input type="text" path="education" id="education" placeholder="Enter candidate's Education" class="form-control input-sm"/>
                             <div class="has-error">
				<form:errors path="education" class="help-inline"/>
			    </div>  
                        </div>
                    </div>
                </div>                                                                           
                <div class="row justify-content-center">
                    <div class="form-group col-md-12">         
                        <div class="col-md-7">
                            <label class="col-md-3 control-lable" for="annualIncome">Annual Income</label>
                            <form:input type="text" path="annualIncome" id="annualIncome" placeholder="Enter candidate's annual income" class="form-control input-sm"/>
                             <div class="has-error">
				<form:errors path="annualIncome" class="help-inline"/>
			    </div>  
                        </div>
                    </div>
                </div> 
               <div class="row justify-content-center">
                    <div class="form-group col-md-12">         
                        <div class="col-md-7">
                            <label class="col-md-3 control-lable" for="property">Property</label>
                            <form:input type="text" path="property" id="property" placeholder="Enter candidate's property" class="form-control input-sm"/>
                             <div class="has-error">
				<form:errors path="property" class="help-inline"/>
			    </div>  
                        </div>
                    </div>
                </div> 
               <div class="row justify-content-center">
                    <div class="form-group col-md-12">         
                        <div class="col-md-7">
                            <label class="col-md-3 control-lable" for="electionPromises">Election Promises</label>
                            <form:input type="text" path="electionPromises" id="electionPromises" placeholder="Enter candidate's election promises" class="form-control input-sm"/>
                             <div class="has-error">
				<form:errors path="electionPromises" class="help-inline"/>
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