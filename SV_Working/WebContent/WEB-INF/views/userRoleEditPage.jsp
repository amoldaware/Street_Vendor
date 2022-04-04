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
<title>User Edit Details</title>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
<script type="text/javascript"
	src="jquery-ui-1.10.3/ui/jquery.ui.datepicker.js"></script>
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">

<script>



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
</script>

</head>

<body  oncontextmenu="return false;" onload="onload()">
<div class="main-section">

<div class="container-fluid">
<!-- <div class="row"> -->
	<div >
	
	<nav aria-label="breadcrumb">
  <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/add.html">User Registration</a></li>  
    <li class="breadcrumb-item active" aria-current="page">Modify User Details</li>
  </ol>
</nav>
	<div class="frm-section">
		<div class="col-md-12">
		<h2 align="center" class="heading">Modify User Details</h2>
	 
		
		<h5 class="notification-message"><strong>${message}</strong></h5>
	<h5 class="error1">${Errormessage}</h5>
		<form:form method="POST" id="A" action="/SV/updateUserRoleDetails.html" class="form-horizontal">
		
		
		<div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
			<form:label path="mliName">MLI Name <span style="color: red;">*</span></form:label>
			<form:input path="mliName" size="28" value="${mliDetail.mliName}" class="form-control cus-control" readonly="true" />
			<form:errors path="mliName" cssClass="error" />
			<form:input path="" size="28" type="hidden" />
		  </div>
		  </div>
 	 </div>
 	 
 	 <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="userID">User ID <span style="color: red;">*</span> </form:label>
		<form:input path="userID" size="28" value="${mliDetail.userID}" class="form-control cus-control" readonly="true" />
		<form:errors path="userID" cssClass="error" />
		  </div>
		  </div>
 	 </div>
 	 
 	 <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
			<form:label path="fName">First Name<span style="color: red;">*</span> </form:label>
			<form:input path="fName" size="28" value="${mliDetail.fName}" class="form-control cus-control" readonly="true" />
			<form:errors path="fName" cssClass="error" />
		  </div>
		  </div>
 	 </div>
	
	<div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="middalName">Middle Name </form:label>
		<form:input path="middalName" size="28" maxlength="15" value="${mliDetail.middalName}" class="form-control cus-control" readonly="true"  />
		<form:errors path="middalName" cssClass="error" />
		  </div>
		  </div>
 	 </div> 
 	 
 	 <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="lName">Last Name <span style="color: red;">*</span> </form:label>
		<form:input path="lName" size="28" value="${mliDetail.lName}" readonly="true" class="form-control cus-control" />
		<form:errors path="lName" cssClass="error" />
		<form:input path="" size="28" type="hidden" />
		  </div>
		  </div>
 	 </div> 
 	 
 	 <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="mobileNumber">Mobile Number <span style="color: red;">*</span> </form:label>
		<form:input path="mobileNumber" size="28" maxlength="10" value="${mliDetail.mobileNumber}"  class="form-control cus-control" />
		<form:errors path="mobileNumber" cssClass="error" />
		  </div>
		  </div>
 	 </div>
 	 
 	  <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="email">Email ID <span style="color: red;">*</span> </form:label>
		<form:input path="email" size="28" maxlength="21" value="${mliDetail.email}" class="form-control cus-control" />
				<form:errors path="email" cssClass="error" />
				<form:input path="" size="28" type="hidden" />
		  </div>
		  </div>
 	 </div>
 	 	 <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">	
		<form:label path="phoneNumber" class="d-block">Phone Number <span style="color:red;">*</span></form:label>
		<form:input path="phoneCode" value="${mliDetail.phoneCode}" class="form-control cus-control  w-28 d-inline" size="5" maxlength="5" placeholder="e.g:022"/>
	    <form:input path="phoneNumber" value="${mliDetail.phoneNumber}"  class="form-control cus-control w-69 d-inline" size="19" maxlength="8" placeholder="e.g:67834562"/>
	    <form:errors path="phoneNumber" cssClass="error" /><form:errors path="phoneCode" cssClass="error" /> 			
		  </div>
		  </div>
 	 </div>	
 	
<%--  	  <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
			<form:label path="status">Status<span style="color: red;">*</span></form:label>
		<form:select path="status" id="status" class="form-control cus-control" readonly="true">	
			          <form:option value="${mliDetail.status}" readonly="true"/>  

			      </form:select>
			      <form:errors path="status" cssClass="error" />
		  </div>
		  </div>
 	 </div> --%>
 	  <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
			<form:label path="userType">User Type<span style="color: red;">*</span></form:label>
	<%-- 		<form:select path="userType" id="userType" class="form-control cus-control">
										
			      <form:option value="${mliDetail.userType}"></form:option>

				  <c:forEach items="${UserTypelist1}" var="listNumber">
                                                  
                                              	<form:option value="${listNumber}" />                
                                   
                                               </c:forEach>
				</form:select>
 --%>
		       <form:select path="userType" id="userType"	class="form-control cus-control">	
			          <form:option value="${mliDetail.userType}"/>  
			      <c:choose>
			        <c:when test="${mliDetail.userType=='Maker'}">                              
                                 <form:option value="Checker"/>
                     </c:when>
                         <c:when test="${mliDetail.userType=='Checker'}">                               
                                 <form:option value="Maker"/>
                     </c:when>                         
			      </c:choose>		
				</form:select> 
				<form:errors path="userType" cssClass="error" />
		  </div>
		  </div>
 	 </div>
 	   <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="hint_question">Hint Question <span
												style="color: red;"></span>
										</form:label>
										<form:input path="hint_question" value="${mliDetail.hint_question}" class="form-control cus-control"
											size="28" placeholder="Question Hint" />
										<form:errors path="hint_question" cssClass="error" />
		  </div>
		  </div>
 	 </div>
 	   <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="hint_ans">Hint Answer<span
		style="color: red;"></span>
		</form:label>
		<form:input path="hint_ans" value="${mliDetail.hint_ans}" class="form-control cus-control"
		size="28" placeholder="Answer Hint" />
		<form:errors path="hint_ans" cssClass="error" />
		  </div>
		  </div>
 	 </div>
 	  	
 	   <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		
		<form:label path="isActive">Status<span style="color: red;">*</span></form:label><br/>
         <form:radiobutton checked="${mliDetail.isActive== '1' ? 'checked':''}"  path="isActive" value="1"/> Active 
		 <form:radiobutton checked="${mliDetail.isActive== '0' ? 'checked':''}"  path="isActive" value="0"/> InActive 
  
		  </div>
		  </div>
 	 </div>
 	  	
 	 <div class="d-inlineblock mt-25">
 	 	<input type="submit" value="Update" name="save"  class="btn btn-reset" />
 	 	<input type="submit" value="Back" name="exit" class="btn btn-reset"  />		
 	 </div>
 	  
		
	</form:form>
	
		</div>
	</div>	
	</div>
</div>
</div>



<%-- 
	<h2 align="center">Edit MLI Details</h2>
	<font color="green" size="3"><b>${message}</b></font>
	<hr />
	<form:form method="POST" id="A" action="/SV/updateUserRoleDetails.html">
		<table align="center" style="color: #5c4324;">
			<tr>
				<td><form:label path="mliName">MLI Name <span
							style="color: red;">*</span>
					</form:label></td>
				<td><form:input path="mliName" size="28"
						value="${mliDetail.mliName}" readonly="true" /></td>
				<td><form:errors path="mliName" cssClass="error" /></td>
				<td><form:input path="" size="28" type="hidden" /></td>

				<td><form:label path="userID">User ID <span
							style="color: red;">*</span>
					</form:label></td>
				<td><form:input path="userID" size="28"
						value="${mliDetail.userID}" readonly="true" /></td>
				<td><form:errors path="userID" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="fName">First Name<span
							style="color: red;">*</span>
					</form:label></td>
				<td><form:input path="fName" size="28"
						value="${mliDetail.fName}" readonly="true" /></td>
				<td><form:errors path="fName" cssClass="error" /></td>
				
				<td><form:input path="" size="28" type="hidden" /></td>
				<td><form:label path="middalName">Middle Name 
					</form:label></td>
				<td><form:input path="middalName" size="28"
						maxlength="15" value="${mliDetail.middalName}" readonly="true"  /></td>
				<td><form:errors path="middalName" cssClass="error" /></td>

			</tr>
			<tr>
				<td><form:label path="lName">Last Name <span
							style="color: red;">*</span>
					</form:label></td>
				<td><form:input path="lName" size="28" value="${mliDetail.lName}" readonly="true" /></td>
				<td><form:errors path="lName" cssClass="error" /></td>
				<td><form:input path="" size="28" type="hidden" /></td>
				<td><form:label path="mobileNumber">Mobile Number <span
							style="color: red;">*</span>
					</form:label></td>
				<td><form:input path="mobileNumber" size="28" maxlength="10"
						value="${mliDetail.mobileNumber}" /></td>
				<td><form:errors path="mobileNumber" cssClass="error" /></td>

			</tr>
			<tr>
				<td><form:label path="email">Email ID <span
							style="color: red;">*</span>
					</form:label></td>
				<td><form:input path="email" size="28" maxlength="21"
						value="${mliDetail.email}" /></td>


				<td><form:errors path="email" cssClass="error" /></td>
				<td><form:input path="" size="28" type="hidden" /></td>

				<td><form:label path="phoneNumber">Phone Number<span
							style="color: red;">*</span>
					</form:label></td>
				<td><form:input path="phoneNumber" size="28" maxlength="11"
						value="${mliDetail.phoneNumber}" /></td>
				<td><form:errors path="phoneNumber" cssClass="error" /></td>
			</tr>
			<!--  <tr>
				<td><form:label path="status">Status<span
							style="color: red;">*</span>
					</form:label></td>
				<td><form:input path="status" size="28" maxlength="11"
						value="${mliDetail.status}" readonly="true"/></td>
						
				<tr>		
		<!--		<td><form:label path="status">Status<span
							style="color: red;">*</span>
					</form:label></td>	
	           <td><form:select path="status" id="status">
                
                  <c:forEach items="${Statuslist}">
                       <form:option value="${Statuslist}"/>
                  </c:forEach>   
                
                   </form:select></td>
			</tr>
			<tr>
						<td><form:label path="status">Status<span
							style="color: red;">*</span>
					</form:label></td>
				<td><form:input path="status" size="28" maxlength="11"
						value="${mliDetail.status}" readonly="true"/></td>-->
				<td><form:label path="status">Status<span
							style="color: red;">*</span>
					</form:label></td>			
			<td><br><form:select path="status" id="status">	
			          <form:option value="${mliDetail.status}"/>  
			      <c:choose>
			        <c:when test="${mliDetail.status=='Active'}">
                               
                                 <form:option value="Deactive"/>
                     </c:when>
                         <c:when test="${mliDetail.status=='Deactive'}">
                                
                                 <form:option value="Active"/>
                     </c:when>
                     
			      </c:choose>
			      </form:select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><form:errors path="status" cssClass="error" /></td>
				
						
                <td><form:input path="" size="28" type="hidden" /></td>
				<td><form:label path="userType">User Type<span
							style="color: red;">*</span></form:label></td>
		         <td><br><form:select path="userType" id="userType">	
			          <form:option value="${mliDetail.userType}"/>  
			      <c:choose>
			        <c:when test="${mliDetail.userType=='MK'}">
                              
                                 <form:option value="CK"/>
                     </c:when>
                         <c:when test="${mliDetail.userType=='CK'}">
                               
                                 <form:option value="MK"/>
                     </c:when>
                         
			      </c:choose>
		
				</form:select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			
				<td><form:errors path="userType" cssClass="error" /></td>
				</tr>
		
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" name="save" class="button" /></td>
				<td colspan="2"><input type="submit" value="Back" name="exit" class="button" /></td>							
			</tr>
		</table>
	</form:form> --%>

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



