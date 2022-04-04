<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


  <meta charset="utf-8">
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
   	 .text-info {
    padding-bottom: 5px !important;
    font-size: 14px !important;
    font-weight: 600;
    color: #4c4c4c !important;}
   </style>
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
	if(document.getElementById('newPassword').value!="" && document.getElementById('reEnterPassword').value!=""){
		var newPSWD=window.btoa(document.getElementById('newPassword').value+document.getElementById('addedValue').value);
		var newPSWD1=window.btoa(document.getElementById('reEnterPassword').value+document.getElementById('addedValue').value);
		//alert("calling1");
		//console.log(newPSWD);
		document.getElementById('newPassword').value=document.getElementById('addedValue').value+window.btoa(newPSWD)+":"+window.btoa(newPSWD);
		document.getElementById('reEnterPassword').value=document.getElementById('addedValue').value+window.btoa(newPSWD1)+":"+window.btoa(newPSWD1);
		document.getElementById('A').submit();
	}else{
		alert('Please check required values');
		return false;
	}				
}
</script>			

 

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
               <form:form method="POST" action="changePassword.html" id="A">				
			<h1 align="center">Reset Password</h1>
			<span Class="error">${message}</span>             
                
                <div class="form-group">
                <label for="usr_id" class="">User ID</label>					
					<form:input path="usr_id"  value="" placeholder="usr_id" class="form-control" readonly="true"/>
					<form:errors path="usr_id" Class="error"/>
					                     
                </div>
                 <div class="form-group">
                 	<label for="new password" class="text-info">New Password</label>
					          
					<form:input path="newPassword" id="newPassword" value="" placeholder="******" class="form-control" type="password"/>
						<form:errors path="newPassword" Class="error"/>
						<input type="hidden" name="addedValue" id="addedValue" value="<%=intRandomNum%>">                     
                </div>
                 <div class="form-group">                 	
						
						<label for="reEnter password" class="text-info">ReEnter Password</label>					
					<form:input path="reEnterPassword" id="reEnterPassword" value="" placeholder="******"  class="form-control"  type="password"/>
					<form:errors path="reEnterPassword" Class="error"/>	
					<input type="hidden" name="addedValue" id="addedValue" value="<%=intRandomNum%>">                     
                </div>
              
                  
                <div class="form-group mb-10" style="text-align: center;">
                 
                    <input type="button" id="but2" value="Submit" class="btn btn-md btn-save" onclick="return encoded();" />
                </div>
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