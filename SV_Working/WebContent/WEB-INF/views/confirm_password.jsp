<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
<%
	Random randomNumber = new Random();
	int intRandomNum = randomNumber.nextInt(9999);
	session.setAttribute("saltValue", intRandomNum+"");
%>
<script>
function encoded()
{
	//alert("calling")
	if(document.getElementById('newPassword').value!="" && document.getElementById('confirm_password').value!=""){
		var newPSWD=window.btoa(document.getElementById('newPassword').value+document.getElementById('addedValue').value);
		var newPSWD1=window.btoa(document.getElementById('confirm_password').value+document.getElementById('addedValue').value);
		//alert("calling1");
		//console.log(newPSWD);
		document.getElementById('newPassword').value=document.getElementById('addedValue').value+window.btoa(newPSWD)+":"+window.btoa(newPSWD);
		document.getElementById('confirm_password').value=document.getElementById('addedValue').value+window.btoa(newPSWD1)+":"+window.btoa(newPSWD1);
		document.getElementById('A').submit();
	}else{
		alert('Please check required values');
		return false;
	}				
}
</script>				
 <style>
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
               <form:form method="POST" action="ResetPassword.html" id="A">
                <span Class="error">${message}</span>
                <h1 align="center">
						<font face="Arial">Reset Password</font>
					</h1>
				 
                
                <div class="form-group">
                  <label for="new password" class="text-info">New Password</label>					
					<form:input path="newPassword" id="newPassword" value="" placeholder="******"  class="form-control" maxlength="8" type="password" autocomplete="off" />
					<form:errors path="newPassword" Class="error"/>	
					<input type="hidden" name="addedValue" id="addedValue" value="<%=intRandomNum%>">
                                    
                </div>
              
                <div class="form-group">
                 
                  	<label for="new password" class="text-info">Confirm Password</label>					
					<form:input path="confirm_password" id="confirm_password" value="" placeholder="******" class="form-control" maxlength="8" type="password" autocomplete="off"  />
						<form:errors path="confirm_password" Class="error"/>
						<input type="hidden" name="addedValue" id="addedValue" value="<%=intRandomNum%>">
                   
                </div>                   
               <div class="form-group">
               	<input type="button" id="but2" value="Submit" class="btn btn-md btn-save" onclick="return encoded();" />
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
 
<%-- 	<div class="main">
				<div class="login-card">				
					
					<div class="banner">
				    	<h1 align="center"><font face="Arial">NEW Password</font></h1>
			        </div>
			
					<br>
					<span Class="error">${message}</span>
					<br>
					<form:form method="POST" action="ResetPassword.html" id="A">
                       
                        <div></div>
                     
						<form:input path="newPassword" value="" placeholder="New_Password" maxlength="8" type="password"/>
						<form:errors path="newPassword" Class="error"/>
					   
					
				    	<form:input path="confirm_password" value="" placeholder="Confirm_Password" maxlength="8" type="password"/>
						<form:errors path="confirm_password" Class="error"/>
			          
			       
					   <input type="submit" id="but2" value="Submit" class="login login-submit" />
						
					</form:form>
					
				</div>				
			</div> --%>

<script type="text/javascript">

function show(){
	
	document.getElementById("but1").style.visibility = "hidden";
	document.getElementById("but2").style.visibility = "visible";
	
}
</script>
</body>
</html>