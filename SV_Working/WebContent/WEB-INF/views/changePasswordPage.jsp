<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>NBFC Login Page</title>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all">
<!-- <link rel="icon" type="image/ico" href="image/google.ico"/> -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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

<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<style type="text/css">
.footer1{position:relative !important}
h1,h5{padding:8px 0;}
	label.text-info {
    padding-bottom: 5px !important;
    font-size: 14px !important;
    font-weight: 600;
    color: #4c4c4c !important;}
</style>
</head>
<body>
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
              <form:form method="POST" action="nbfcLoginSubmitForm.html">
                <span Class="error">${message}</span>
                <h1 align="center">
						<font face="Arial">Change Password</font>
					</h1>
				 
                
                <div class="form-group">
                  <label for="User Id" class="text-info">User Id</label>					
					<form:input path="usr_id" value="" placeholder="User ID" readonly="true" />
						<form:errors path="usr_id" Class="error"/>
                                    
                </div>
              
                <div class="form-group">
                 
                  	<label for="new password" class="text-info">New Password</label>					
						<form:input path="usr_password" value="" type="password" placeholder="Password"/>
						<form:errors path="usr_password" Class="error"/>
                </div>    
                <div class="form-group">
                 
                  	<label for="new password" class="text-info">Reenter New Password</label>					
					<form:input path="usr_password" value="" type="password"placeholder="Password" />
						<form:errors path="usr_password" Class="error"/>
						<span Class="error">${InvalidCredencialKey}</span>
                </div>                   
               <div class="form-group">
               	<input type="submit" value="Sign In" class="btn btn-md btn-save" />
               
               </div>
                

                <!-- <div class="form-group mb-10" style="text-align: center;">
                 <a href="#" type="button" class="btn btn-md btn-save" onclick="encoded();">Submit</a>
                   
                </div> -->
				</form:form>
               <!-- <div class="form-group line pt-10" style="text-align:center;">                                                          
            <label class="info-btn">  
                <a href="#" onclick="callForgot()"><i class="fa fa-lock" aria-hidden="true"></i> Forgot Password ? </a>
               
             </label>
               
                </div> -->

              </div>
            </div>
          </div>
        </div>
      </div>
</div>
	 
</body>
</html>