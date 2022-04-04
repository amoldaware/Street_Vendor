<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.Random"%>
<%
Random randomNumber = new Random();
int intRandomNum = randomNumber.nextInt(9999);
session.setAttribute("saltValue", intRandomNum+"");
Random randomNumberOne = new Random();
int intRandomNumOne = randomNumber.nextInt(9999);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
 		<title>Street Vendor ( CGTMSE)</title>
    <!-- Bootstrap CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/login.css" rel="stylesheet">
   <link href="css/responsive.css" rel="stylesheet">    
     <link href="css/font-awesome.css" rel="stylesheet">        
    <link href="css/reset.css" rel="stylesheet">
	 <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,400i,600,600i|Raleway:400,400i,500,500i,600|Roboto:400,400i,500,500i&display=swap" rel="stylesheet"> <link href="https://fonts.googleapis.com/css2?family=Arvo:wght@400;700&display=swap" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
   <style type="text/css">
   	/*.header-login p a:nth-child(1){border-right: none;}*/
  /* 	#dashboardModal >.modal-dialog{    width: 100% !important;   margin: 0 auto;  max-width: 100% !important;} 
   	marquee{
         font-size: 17px;
	    font-weight: 600;
	    color: #ff0000;
	    padding: 5px;
	    background-color: #eac29a;
	    margin: 0;
      }*/
   </style>
		<%-- <title>Forgot Password Page</title>
		<link href="css/custom.css" type="text/css" rel="stylesheet">
		<link href="css/style.css" rel="stylesheet" type="text/css" media="all">
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script> --%>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
	h1,h5{padding:8px 0;}
	label.text-info {
    padding-bottom: 5px !important;
    font-size: 14px !important;
    font-weight: 600;
    color: #4c4c4c !important;}
</style>
</head>
<body oncontextmenu="return false;">
<div id="login">
      <div class="container-fluid">
        <div id="login-row" class="row">
          <div class="col-md-7 col-xs-12">
            
          </div>
          <div id="login-column" class="col-md-5 col-xs-12">
            <div id="login-box" class="">
              <div id="login-form" class="form">
                <div class="logo">
                  <img src="images/udaanlogo.svg" alt="cgtmse street Vendor">
                </div>
               <form:form method="POST" action="OTPSubmissions.html" id="A">
                <span Class="error">${message}</span>
                <h1 align="center">
						<font face="Arial">Forgot Password</font>
					</h1>
					<h5>Enter The User ID and We'll Send verification code to
						reset your password to email address.</h5>
                <!--  <h3 class="text-center text-info">Login</h3> -->
               
                
                <div class="form-group">
                  <form:label path="" cssStyle="text-info" for="userId">User Id</form:label><br>                 
				  <form:errors path="usr_id" Class="error"/>
				  <form:input path="usr_id" value="" placeholder="User ID" class="form-control" readonly="true" />
				  <input type="hidden" name="csrfPreventionSalt" value="${csrfPreventionSalt}"/>
					<form:errors path="usr_id" Class="error" />
					<span Class="error">${InvalidCredencialKey}</span>
					
                </div>
              
                <div class="form-group">
                 
                 <c:choose>
						<c:when test="${success==true || Resend==true}">
						 <form:label path="" cssStyle="text-info" for="OTP">OTP</form:label><br>
						 	<input type="hidden" name="csrfPreventionSalt" value="${csrfPreventionSalt}"/>
							<form:input path="otp" value="" placeholder="OTP" id="otp" class="form-control" readonly="" autocomplete="off" maxlength="30"/>
							<form:errors path="otp" Class="error" />
							<span Class="error">${InvalidCredencialKeyOTP}</span>
							 <input type="hidden" name="addedValue" id="addedValue" value="<%=intRandomNum%>">
							<input type="submit" name="action1" id="but1" value="Resend" class="btn btn-md btn-save" style="width: 45%; float: left; margin-top: 20px;" onclick="show()" />
							<input type="submit" name="action2" id="but2" value="Submit" class="btn btn-md btn-save" style="width: 45%; margin-top: 20px; margin-left: 20px; display: inline-block; margin-left: 18px;" onclick="encoded()"/>
						</c:when>
						<c:when test="${success!=true}">
							<input type="submit" name="action1" id="but1" value="Send Varification Code On Email" class="button" onclick="show()" />
						</c:when>
						<c:when test="${Resend==true}">
							<input type="submit" name="action1" id="but1" value="Resend" class="btn btn-md btn-save" onclick="show()" />
						</c:when>
					</c:choose>
                   
                </div>                   
               
                

                <!-- <div class="form-group mb-10" style="text-align: center;">
                 <a href="#" type="button" class="btn btn-md btn-save" onclick="encoded();">Submit</a>
                   
                </div> -->
				</form:form>
               

              </div>
            </div>
          </div>
        </div>
      </div>
</div>
	 
	<script type="text/javascript">
		function show() {
			document.getElementById("but1").style.visibility = "hidden";
			document.getElementById("but2").style.visibility = "visible";
		}
		function encoded()
		{	
				var newPSWD=window.btoa(document.getElementById('otp').value+document.getElementById('addedValue').value);
				document.getElementById('otp').value=document.getElementById('addedValue').value+window.btoa(newPSWD)+":"+window.btoa(newPSWD);
				document.getElementById('A').submit();
			
		}
	</script>
</body>
</html>