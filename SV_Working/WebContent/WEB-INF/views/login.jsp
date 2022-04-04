<%@page import="java.util.Random"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
	Random randomNumber = new Random();
	int intRandomNum = randomNumber.nextInt(9999);
	session.setAttribute("saltValue", intRandomNum+"");
	Random randomNumberOne = new Random();
	int intRandomNumOne = randomNumber.nextInt(9999);
%>
<%-- <jsp:include page="include.jsp" /> --%>  
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
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
   
   <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
   <script>
	function encoded()
	{	
		var chkBox = document.getElementById("checkBoxId");
		var itext=document.getElementById("j_captcha_response").value;
		/* if(document.getElementById("memberId").value==""){
			document.getElementById("memberIdError").innerHTML="Member Id is required";
			//alert("Member Id is required");
			return;
		}else */ if( document.getElementById("usr_id").value==""){
			//document.getElementById("usr_id").innerHTML="UserId is required";
			alert("User Id is required");
			return;
		}else if(document.getElementById("usr_password").value==""){
			//document.getElementById("usr_password").innerHTML="Password is required";
			alert("Password is required");
			return;
		}else if(itext==""){
			alert("Captcha is required");
		   return;
		}else if (chkBox.checked == false){
			alert("Kindly accept Terms & Conditions.");
			return;
		}else{
			var newPSWD=window.btoa(document.getElementById('usr_password').value+document.getElementById('addedValue').value);
			document.getElementById('usr_password').value=document.getElementById('addedValue').value+window.btoa(newPSWD)+":"+window.btoa(newPSWD);
			document.getElementById('A').submit();
		}
	}
		
	$(document).ready(function() {
		 $.ajaxSetup({
		      cache: false
		    });
		    var timestamp = (new Date()).getTime();
		    $("#btnCaptcha").click(function() {
		        var timestamp = (new Date()).getTime();
		        var newSrc = $("#imgCaptcha").attr("src").split("?");
		        newSrc = newSrc[0] + "?" + timestamp;
		        $("#imgCaptcha").attr("src", newSrc);
		        $("#imgCaptcha").slideDown("fast");

		     });
	});
	function changeAutoComplete(){
		if (document.getElementsByTagName) {
			var inputElements = document.getElementsByTagName("input");
			for (i=0; inputElements[i]; i++) {
			if (inputElements[i].className && (inputElements[i].className.indexOf("disableAutoComplete") != -1)) {
				inputElements[i].setAttribute("autocomplete","off");
			}
		}
	  }
	}
	function callForgot(){
		document.getElementById('A').action = "<%=request.getContextPath()%>/ForgotPassword.html";
		document.getElementById('A').submit();
	}
	
    function preventBack() { window.history.forward(); }  
    setTimeout("preventBack()", 0);  
    window.onunload = function () { null };
    
    /*
     window.history.forward(); 
        function noBack() { 
            window.history.forward(); 
        } 
        */
        /*
        function preventBack() { 
            window.history.forward();  
        } 
          
        setTimeout("preventBack()", 0); 
          
        window.onunload = function () { null }; 
        */
</script>
</script>
  </head> 
<body oncontextmenu="return false;" onload="changeAutoComplete()">
<c:if test="${not empty loginMSG }">
<script type="text/javascript">
alert('<c:out value="${loginMSG }"/>');
window.location.replace("<%=request.getContextPath()%>/Login.html");
</script>
</c:if>
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
                <form:form method="POST" action="nbfcLoginSubmitForm.html" id="A" autocomplete="off">
                <input type="hidden" name="csrfPreventionSalt" value="${csrfPreventionSalt}"/>
                <span Class="error">${attemptMessage}</span>
                <span Class="error">${message}</span>
                <!--  <h3 class="text-center text-info">Login</h3> -->
                <div class="form-group" style="display:none">
                  <label for="username" class="text-info"> Member Id</label><br>

                  <input type="text" class="form-control" name="memberId" id="memberId"
                    size="14" value="" autocomplete="off" placeholder="Enter Member Id" /> 
                  
                </div>
                
                <div class="form-group">
                  <form:label path="" cssStyle="text-info" for="userId">User Id</form:label><br>
                  <input type="text" name="usr_id" id="usr_id" placeholder="Enter User ID" class="form-control" autocomplete="off" maxlength="30"/>
				  <form:errors path="usr_id" Class="error"/>
                </div>
              
                <div class="form-group">
                  <form:label path="" for="password" cssStyle="text-info">Password</form:label><br>
                  <input type="password" name="usr_password" id="usr_password" placeholder="Enter Password" class="form-control" autocomplete="new-password<%=intRandomNumOne%>" maxlength="30"/>
				  <form:errors path="usr_password" Class="error"/>
				  <span Class="error">${InvalidCredencialKey}</span>
            	  <input type="hidden" name="addedValue" id="addedValue" value="<%=intRandomNum%>">
                </div>                   
                <div style="width:100%">
                <table><tr>
                  <td><img src="./CaptchaImg.jpg" alt="Captcha Code" style="margin-left:0px;margin-top:0px" id="imgCaptcha"></td>
                  <td>&nbsp;<input type="button" value="Refresh" id="btnCaptcha"></td>
                 </tr>
                 </table> 
                </div>  
                <br>
                <div class="form-group">
                  <input type="text" name="j_captcha_response" autocomplete="off"  class="form-control" placeholder="Captcha Code"  id="j_captcha_response" maxlength="6" />
				  <form:errors path="j_captcha_response" Class="error"/>
                </div>
                <div class="form-group mb-10">
                   <input type="checkbox" name="checkBoxId" id="checkBoxId" value="Y"  style="displau:inline"/>
                  <label class="text-info d-inline" style="display:inline">
                  	We agree to the terms & conditions of CGTMSE Guidelines and <a href="${pageContext.request.contextPath}/Download/E-Undertaking.pdf" target="_blank">Undertaking</a> under the Scheme. We also agree to other <a href="${pageContext.request.contextPath}/disclaimer.html" target="_blank">Terms and Conditions.</a>
                 </label>
           <br/><form:errors path="checkBoxId" Class="error"/>	
                </div>
                

                <div class="form-group mb-10" style="text-align: center;">
                 <a href="#" type="button" class="btn btn-md btn-save" onclick="encoded();">Submit</a>
                   
                </div>
				</form:form>
                 <div class="form-group line pt-10" style="text-align:center;">                                                          
            <label class="info-btn">  
                <a href="#" onclick="callForgot()"><i class="fa fa-lock" aria-hidden="true"></i> Forgot Password ? </a>
               
             </label>
               
                </div>

              </div>
            </div>
          </div>
        </div>
      </div>
</div>

</body>
<script>
document.onkeydown = function(e) {
	  if(event.keyCode == 123) {
	     return false;
	  }
	  if(e.ctrlKey && e.shiftKey && e.keyCode == 'I'.charCodeAt(0)) {
	     return false;
	  }
	  if(e.ctrlKey && e.shiftKey && e.keyCode == 'C'.charCodeAt(0)) {
	     return false;
	  }
	  if(e.ctrlKey && e.shiftKey && e.keyCode == 'J'.charCodeAt(0)) {
	     return false;
	  }
	  if(e.ctrlKey && e.keyCode == 'U'.charCodeAt(0)) {
	     return false;
	  }
	}
</script>
</html>