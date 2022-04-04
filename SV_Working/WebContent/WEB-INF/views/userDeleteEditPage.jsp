<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String sN=(String)request.getAttribute("sName");
    String expLim=(String)request.getAttribute("eXposureLimit");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<title>Portfolio Creation</title>
<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<script type="text/javascript"
	src="jquery-ui-1.10.3/ui/jquery.ui.datepicker.js"></script>
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
<style>
#notification_bar {

  visibility: hidden;
  min-width: 250px;
  margin-left: -125px;
  background-color: #333;
  color: #fff;
  text-align: center;
  border-radius: 2px;
  padding: 16px;
  position: fixed;
  z-index: 99;
  left: 50%;
  bottom: 30px;
  font-size: 17px;
}

#notification_bar.show {
  visibility: visible;
  -webkit-animation: fadein 0.5s, fadeout 0.5s 2.5s;
  animation: fadein 0.5s, fadeout 0.5s 2.5s;
}

@-webkit-keyframes fadein {
  from {bottom: 0; opacity: 0;} 
  to {bottom: 30px; opacity: 1;}
}

@keyframes fadein {
  from {bottom: 0; opacity: 0;}
  to {bottom: 30px; opacity: 1;}
}

@-webkit-keyframes fadeout {
  from {bottom: 30px; opacity: 1;} 
  to {bottom: 0; opacity: 0;}
}

@keyframes fadeout {
  from {bottom: 30px; opacity: 1;}
  to {bottom: 0; opacity: 0;}
}
</style>
<script>

function Deleteuser(){
	/*alert("${message}");
	  var x = document.getElementById("notification_bar");
	  x.className = "show";
	  setTimeout(function(){ x.className = x.className.replace("show", ""); }, 5000); */
	var agree=confirm("Are you sure you want to delete this User?");
	if(agree){
		document.getElementById('A').action = "/SV/deleteUser.html";
		document.getElementById('A').submit();
		document.getElementById('A').Reset();  // Reset all form data
		
	}
	else{
	     
	}
	  
	   //return false;
	
}
function BackUser(){
	document.getElementById('A').action = "/SV/BackUser.html";
	document.getElementById('A').submit();
	
}
function exitMLIDetails(){
	//alert('Exit');
	document.getElementById('saveForm').action = "/SV/Reset.html";
	document.getElementById('saveForm').submit();
	
}

	$(function() {

		$("#ratingDate").datepicker({
			dateFormat : 'dd-mm-yy'
		});

	});
	
	function myFunction() {
		  var x = document.getElementById("notification_bar");
		  x.className = "show";
		  setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
		}
		
		
</script>

</head>

<body onload="onload()">
<div class="main-section">

<div class="container-fluid">
<!-- <div class="row"> -->
	<div >
	<nav aria-label="breadcrumb">
  <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/add.html">User Registration</a></li>  
    <li class="breadcrumb-item active" aria-current="page">Delete User Details</li>
  </ol>
</nav>
	<div class="frm-section">
		<div class="col-md-12">
		<h2 align="center" class="heading">Delete User Details</h2>
		 <div id="notification_bar">${message}</div>
		
		<h5 class="notification-message"> <!-- <i class="fa fa-bell" aria-hidden="true"></i> --> <strong>${message}</strong></h5>
		<form:form method="POST" id="A" action="" class="form-horizontal">
		
		
		<div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="deleteMliName">MLI Name<span style="color: red;">*</span> </form:label>
		 <form:input path="deleteMliName" size="28" value="${UserDetails.deleteMliName}" class="form-control cus-control" readonly="true" />
		 <form:errors path="deleteMliName" cssClass="error" />	
		 <form:input path="" size="28" type="hidden" />	
		  </div>
		  </div>
 	 </div>
 	 
 	 <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="deleteuserID">User Id<span style="color: red;">*</span></form:label>
		<form:input path="userID" size="28" value="${UserDetails.userID}" readonly="true" class="form-control cus-control"/>
		<form:errors path="userID" cssClass="error" />	
		  </div>
		  </div>
 	 </div>
 	 
 	 <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="deleteFName">First Name<span style="color: red;">*</span></form:label>
		<form:input path="deleteFName" size="28" value="${UserDetails.deleteFName}" class="form-control cus-control" readonly="true" />
		<form:errors path="deleteFName" cssClass="error" />
		<form:input path="" size="28" type="hidden" />
		  </div>
		  </div>
 	 </div>
	
	<div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="deleteMiddalName">Middle Name </form:label>
		<form:input path="deleteMiddalName" size="28" maxlength="15" value="${UserDetails.deleteMiddalName}" class="form-control cus-control"  readonly="true" />
		<form:errors path="deleteMiddalName" cssClass="error" />
		  </div>
		  </div>
 	 </div> 
 	 
 	 <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="deleteLName">Last Name<span style="color: red;">*</span>  </form:label>
		<form:input path="deleteLName" size="28" value="${UserDetails.deleteLName}" class="form-control cus-control" readonly="true"/>
		<form:errors path="deleteLName" cssClass="error" />
		<form:input path="" size="28" type="hidden" />
		  </div>
		  </div>
 	 </div> 
 	 
 	 <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="deletemobileNumber">Mobile Number<span style="color: red;">*</span> </form:label>
		<form:input path="deletemobileNumber" size="28" maxlength="21" value="${UserDetails.deletemobileNumber}" class="form-control cus-control"  readonly="true"/>
		<form:errors path="deletemobileNumber" cssClass="error" />
		  </div>
		  </div>
 	 </div>
 	 
 	  <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="email">Email ID <span style="color: red;">*</span> </form:label>
		<form:input path="deleteemail" size="28" maxlength="21" value="${UserDetails.deleteemail}" class="form-control cus-control" readonly="true"/>
		<form:errors path="deleteemail" cssClass="error" />
			<form:input path="" size="28" type="hidden" />
		  </div>
		  </div>
 	 </div>
 	 
 	  <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="phoneNumber">Phone Number<span style="color: red;">*</span> </form:label>
		<form:input path="deletephoneNumber" size="28" maxlength="11" value="${UserDetails.deletephoneNumber}"  class="form-control cus-control" readonly="true" />
		<form:errors path="deletephoneNumber" cssClass="error" />
		  </div>
		  </div>
 	 </div>
 	  	
 	 <div class="d-inlineblock">
 	 	<input type="button" value="Delete" class="btn btn-reset" onclick="Deleteuser();" />
	<!--<input type="submit" value="Back" onclick="BackUser();"  class="btn btn-reset" />  -->	
 	 </div>
 	  
		
	</form:form>
	
		</div>
	</div>	
	</div>
</div>
</div>


<%-- <h2 align="center">MLI Details </h2>
	<font color="green" size="3"><b>${message}</b></font>
	<hr />
	<form:form method="POST" id="A" action="">
		<table align="center" style="color: #5c4324;">
			<tr>
				<td></td>
				<td class="DataElement">
			<td></td>
				<td></td>
				<td class="DataElement"></td>
			</tr>
			<tr>
				<td></td>
				<td class="DataElement"></td>
				
				<td></td>
				<td></td>
				<td class="DataElement"></td>

			</tr>
			<tr>
				<td></td>
				<td class="DataElement"></td>
				<td></td>
				<td class="DataElement"></td>

			</tr>
			<tr>
				<td></td>
				<td class="DataElement"></td>

				<td></td>
				<td class="DataElement"></td>
			</tr>
		
			
			
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2"></td>						
			</tr>
		</table> -
	</form:form> --%>

</body>


</html>



