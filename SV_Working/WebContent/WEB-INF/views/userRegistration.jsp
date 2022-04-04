<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MLI User Registration Form</title>

<%-- <script src="<%=request.getContextPath()%>/js/jquery.js"></script> --%>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<%-- <script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script> --%>
<%-- <LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css"> --%>





<%
	String s = (String) request.getAttribute("SName");
%>

<script type="text/javascript">
	function confirm_click() {
		return confirm("Are you sure want to Delete this User ?");
	}
</script>

<script type="text/javascript">
	$(window).load(function() {
		$(".loader").fadeOut("slow");
	})
</script>

</head>
<body oncontextmenu="return false;">
	<div class="main-section">

		<div class="container-fluid">
			<!-- <div class="row"> -->
			<div>
				<div class="frm-section">
					<div class="col-md-12">
						<!-- 	<h1>MLI User Registration Form</h1> -->
						<h5 class="notification-message">${message}</h5>
						<h5 class="error1">${Errormessage}</h5>
						<div class="loader"></div>
						<form:form method="POST" action="/SV/save.html"
							class="form-horizontal"  autocomplete="off">

						<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="state">MLI Name <span
												style="color: red;">*</span>
										</form:label>
										<input type="text" class="form-control cus-control d-none"
											placeholder="MLI Name" autocomplete="off">
										<form:select path="state" class="form-control cus-control "
											id="state" onchange="onchangeApp()">
											<form:option value="" label="--Select MLI ID---" />
											<c:forEach var="state" items="${stateList}">
												<form:option id="${state.ste_code}"
													value="${state.ste_name}">${state.ste_name}</form:option>
											</c:forEach>
										</form:select>
										<form:errors path="state" cssClass="error" />
									</div>
								</div>
							</div>
                         <div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="mliId">MLI ID <span
												style="color: red;">*</span>
										</form:label>
										<form:input path="mliId" class="form-control cus-control"
											id="mliId" disabled="true" />
										<form:errors path="mliId" cssClass="error" />
									</div>
								</div>
							</div>

						<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="fName">First Name <span
												style="color: red;">*</span>
										</form:label>
										<form:input path="fName" class="form-control cus-control"
											id="fName" value="${employee.fName}" placeholder="First Name" autocomplete="off"/>
										<form:errors path="fName" cssClass="error" />
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="middalName">Middle Name </form:label>
										<form:input path="middalName" class="form-control cus-control" placeholder="Middle Name" autocomplete="off"/>
										<form:errors path="middalName" cssClass="error" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="lName">Last Name <span
												style="color: red;">*</span>
										</form:label>
										<form:input path="lName" class="form-control cus-control" placeholder="Last Name" autocomplete="off"/>
										<form:errors path="lName" cssClass="error" />
									</div>
								</div>
							</div>
							<div class="clearfix"></div>
						<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="userType">Role Name <span
												style="color: red;">*</span>
										</form:label>

										<form:select path="userType" class="form-control cus-control"
											id="userType">
											<form:option value="" label="--Select--" />
											<c:forEach items="${UserTypelist1}" var="listNumber">
												<c:choose>
													<c:when test="${listNumber eq 'Maker' }">
													<form:option value="${listNumber}" selected="selected"/>
												</c:when>
												<c:otherwise>
													<form:option value="${listNumber}" />
												</c:otherwise>																
												</c:choose>
												
											</c:forEach>
										</form:select>
										<%-- 	<form:option value="" label="Select" />
											<form:option value="" />
											<form:option value="MK" label="Maker" /> --%>

										<form:errors path="userType" cssClass="error" />
									</div>
								</div>
							</div>
							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="uDesignation">User Designation <span
												style="color: red;">*</span>
										</form:label>
										<form:input path="uDesignation"
											class="form-control cus-control" 
											placeholder="Manager" autocomplete="off"/>
										<form:errors path="uDesignation" cssClass="error" />
									</div>
								</div>
							</div>
							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="mobileNumber">Mobile Number <span
												style="color: red;">*</span>
										</form:label>
										<form:input path="mobileNumber"
											class="form-control cus-control" maxlength="10"
											placeholder="8655530236"  autocomplete="off"/>
										<form:errors path="mobileNumber" cssClass="error" />
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="phoneNumber" class="d-block">Phone Number <span
												style="color: red;">*</span>
										</form:label>
										<form:input path="phoneCode"
											class="form-control cus-control  w-28 d-inline" size="5"
											maxlength="5" placeholder="e.g:022" autocomplete="off" />
										<form:input path="phoneNumber"
											class="form-control cus-control w-69 d-inline" size="19"
											maxlength="8" placeholder="e.g:67834562" autocomplete="off"/>
										<form:errors path="phoneCode" cssClass="error" />
										<form:errors path="phoneNumber" cssClass="error" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="email">Offical email id <span
												style="color: red;">*</span>
										</form:label>
										<form:input path="email" class="form-control cus-control"
											size="28" placeholder="abcd@xxxx.com" autocomplete="off" />
										<form:errors path="email" cssClass="error" />
									</div>
								</div>
							</div>
							<div class="clearfix"></div>
							<div class="d-inlineblock">
								<input type="submit" value="Save" name="submit"
									class="btn btn-reset " name="save" />
								<!   -- mt-25 -->
								<input type="reset" value="Reset"
									name="reset" class="btn btn-reset" />
								<!-- <input type="submit" value="Close" onclick="resetForm()" class="btn btn-reset" /> -->

							</div>

						</form:form>

					</div>
				</div>

			</div>
		</div>

		<div id="split-sec">
			<div class="container-fluid">
				<!-- <div class="row">  -->
				<div>
					<div class="tbl-details">
						<div class="col-md-12">
							<div class="d-inlineblock float-right">
								<!-- <label style="padding-top:8px; font-weight:100;">Search :</label> -->
								<input type="text" id="myInput" onkeyup="myFunction()"
									class="form-control cus-control"
									style="width: 280px; display: inline; height: 34px; border-radius: 4px; background-color: #ffffff;"
									placeholder="Search Data.." title="Type in a name">

								<button style="border: none !important;">
									<a href="userRoleCreationdownLoad.html"> <img
										src="images/excel.png" alt="" data-toggle="tooltip"
										title="Export To Excel">
									</a>
								</button>
								<!-- <input type="submit" value="Reset" onclick="resetForm()" class="btn btn-reset mt-25" /> -->
							</div>
							<c:if test="${!empty employees}">

								<!-- <div class="col-md-3 mb-10 float-right">
		<label style="width:30%; float:left; text-align:center;">Search :</label>
		<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control"  style="width:70%; float:left;" placeholder="Search for names.." title="Type in a name">
		</div> -->

								<table id="myTable"
									class="table table-bordered table-hover cus-table mb-0">
									<thead>
										<tr>
											<th>SR.NO.</th>
											<th>MLI NAME</th>
											<th>USER ID</th>
											<th>FIRST NAME</th>
											<th>MIDDLE NAME</th>
											<th>LAST NAME</th>
											<th>MOBILE NUMBER</th>
											<th>PHONE NUMBER</th>
											<th>EMAIL ID</th>
											<th>USER TYPE</th>
											<th>USER DESIGNATION</th>

											<th>Action</th>
										</tr>
									</thead>
									<%
										int counter = 0;
									%>
									<c:forEach items="${employees}" var="employee">
										<tr>
											<td><%=counter + 1%></td>

											<td><c:out value="${employee.mliName}" /></td>
											<td><c:out value="${employee.userID}" /></td>
											<td><c:out value="${employee.fName}" /></td>
											<td><c:out value="${employee.middalName}" /></td>
											<td><c:out value="${employee.lName}" /></td>
											<td><c:out value="${employee.mobileNumber}" /></td>
											<td><c:out value="${employee.phoneCode}" />-<c:out
													value="${employee.phoneNumber}" /></td>
											<td><c:out value="${employee.email}" /></td>
												<td><c:out value="${employee.userType}" /></td>
												<td><c:out value="${employee.uDesignation}" /></td>

											<%-- <c:choose>

												<c:when test="${employee.userType=='MK'}">
													<c:out value="Maker" />
												</c:when>
												<c:when test="${employee.userType=='CK'}">
													<c:out value="Checker" />
												</c:when>
											</c:choose>
 --%>
											
											<td><c:out value="" /><a
												href="getUserDetailsForEdit.html?userId=${employee.userID}"
												class="btn-edit">Edit </a> <!--<c:out value="" /><a onclick="return confirm('Do you want to delete this record.?')"
												href="getDeleteUserDetails.html?userId=${employee.userID}"
												class="btn-edit">Delete</a></td>-->
											<!--  <td> <c:out value="" /><span onclick="confirm_click();"><a href="deleteUser.html?userId=${employee.userID}">Delete</a></span>
			<!--	 <td><input type="button" value="Delete" onclick="deleteuser('${employee.userID}')"/></td> 
			<td><input type="submit" value="Delete" name="delete"/></td>-->
											<%
												counter += 1;
											%>
										</tr>

									</c:forEach>
								</table>
								<!-- <ul class="pagination mtb-0"  >
			 <li><a href="#">Previous</a></li>
				  <li class="active"><a href="#">1</a></li>
				  <li ><a href="#">2</a></li>
				  <li><a href="#">3</a></li>
				  <li><a href="#">4</a></li>
				  <li><a href="#">5</a></li>
				  <li><a href="#">Next</a></li>
				</ul> -->

							</c:if>



						</div>
					</div>
				</div>

			</div>
		</div>

	</div>

	<script src="js/jquery-3.5.1.min.js" type="text/javascript"></script>

	<div class="loader"></div>
	<script type="text/javascript">
		$(window).load(function() {
			$(".loader").fadeOut("slow");
		});
		
		function onchangeApp() {
			var mliName = document.getElementById("state").value;
			//alert(portfolioNum);
			try {
				$
						.ajax({
							type : "POST",
							url : "getMliId.html",
							data : "mliName=" + mliName,
							success : function(data) {
								var select2 = document.getElementById('bankId');
							
								var mliId = data.result.mliId;
								$('#mliId').val(mliId);
								
								
							},
							error : function(e) {
								alert('Server Error : ' + e);
							}
						});

			} catch (err) {
				alert('Error is : ' + err);
			}

		}
	</script>
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