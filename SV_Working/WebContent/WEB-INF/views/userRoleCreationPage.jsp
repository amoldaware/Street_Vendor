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
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<%-- <script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script> --%>



<%
	String s=(String)request.getAttribute("SName");
%>

</head>
<body  oncontextmenu="return false;">

<div class="main-section">
<div class="container-fluid">
<!-- <div class="row"> -->
	<div>	
		<div class="frm-section">
		<div class="col-md-12">
			<%-- <h1> <i class="fa fa-user-circle-o" aria-hidden="true"></i>  User Role Creation Page</h1>--%>
			<h5 class="notification-message">${message}</h5> 
			<div class="loader"></div>
	<form:form method="POST" action="/SV/saveNewMLIRoles.html" class="form-horizontal" id="saveForm">
		
		<div class="col-md-2 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		   <form:label path="mliName"> MLI Name<span style="color: red;">*</span></form:label>
		   <input type="text" class="form-control cus-control d-none" placeholder="MLI Name" >
		   
		   <form:select path="mliName" id="mliName" class="form-control cus-control" onchange="doAjaxPost4()">
				<form:option value="" label="Select" />
				<c:forEach var="state" items="${mlisList}">
					<form:option id="${state.mliLongName}" value="${state.mliLongName}">${state.mliLongName}</form:option>
				</c:forEach>
			</form:select>
			<form:errors path="mliName" cssClass="error" />
		  </div>
		  </div>
	 	 </div>
	 	 
	 	 <div class="col-md-2 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
			<form:label path="userName">User Name <span style="color: red;">*</span></form:label>
			 <input type="text" class="form-control cus-control d-none" placeholder="User Name" >
			<form:select id="userName" class="form-control cus-control" path="userName">
						<form:option value=""
							label="Select" />
					</form:select>
					<form:errors path="userName" cssClass="error" />
		</div>
		</div>
		</div>
		 <div class="col-md-2 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="roleName">Role Name<span style="color: red;">*</span></form:label>
		 <input type="text" class="form-control cus-control d-none" placeholder="Role Name" >
		<form:select path="roleName" class="form-control cus-control "  id="roleName">			
						<form:option value="" label="Select" />
						<form:option value="Checker" label="Checker" />
						<form:option value="Maker" label="Maker" />
					</form:select>	
					<form:errors path="roleName" cssClass="error" />		
		</div>
		</div>
		</div>
		<div class="d-inlineblock mt-25">
			<input type="submit" value="Search" name="submit"  class="btn btn-reset " name="save" />		<!-- mt-25 -->	
			<input type="submit" value="Reset" onclick="resetForm()" class="btn btn-reset" />
			<input type="submit" value="Close" onclick="resetForm()" class="btn btn-reset" />
			
			
    		
		</div>
		<div class="d-inlineblock mt-25 float-right">
		<!-- <label style="padding-top:8px; font-weight:100;">Search :</label> -->
		<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px;
  		  background-color: #ffffff;" placeholder="Search Data.." title="Type in a name">
    		
    		<button style="border:none !important; cursor: not-allowed;">
    			<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
    		</button>
			<!-- <input type="submit" value="Reset" onclick="resetForm()" class="btn btn-reset mt-25" /> -->
		</div>
			
			<!-- <div class="col-md-3 mb-10 float-right">
		<label style="width:30%; float:left; text-align:center; padding-top:8px">Search :</label>
		<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control"  style="width:70%; float:left;" placeholder="Search for names.." title="Type in a name">
		</div> -->
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
		
		<c:if test="${!empty employees}">
	<!-- <div class="col-md-9">
		<h3><i class="fa fa-list-alt" aria-hidden="true"></i> All Registered MLI User List</h3>
	</div>-->
	<!-- <div class="col-md-3 mb-10 float-right">
	<label style="width:30%; float:left; text-align:center; padding-top:8px"">Search :</label>
	<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control"  style="width:70%; float:left;" placeholder="Search for names.." title="Type in a name">
	</div>  -->
		
<table id="myTable" class="table table-bordered table-hover cus-table mb-0" >
		<thead>
			<tr>
			    <th>S.NO.</th>
				<th>First Name</th>
				<th>Middle Name</th>
				<th>Last Name</th>
				<th>User ID</th>
				<th>MLI Name</th>
				<th>Mobile Number</th>
				<th>Phone Number</th>
				<th>Email ID</th>
				<th>User Type</th>
				<th>Status</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<% int counter=0;%>
			<c:forEach items="${employees}" var="employee">
				<%-- <tr <% if(counter%2==0){%>bgcolor="silver" <%}%>> --%>
				<tr>
					<td><%=counter+1%></td>
				    
					<td><c:out value="${employee.fName}" /></td>
					<td><c:out value="${employee.middalName}" /></td>
					<td><c:out value="${employee.lName}" /></td>
					<td><c:out value="${employee.userID}" /></td>
					<td><c:out value="${employee.mliName}" /></td>
					<td><c:out value="${employee.mobileNumber}" /></td>
					<td><c:out value="${employee.phoneNumber}" /></td>
					<td><c:out value="${employee.email}" /></td>
					<td><c:out value="${employee.userType}" /></td>
					<td><c:out value="${employee.status}" /></td>
					<td><c:out value="" /><a href="getUserDetailsForEdit.html?userId=${employee.userID}" class="btn-edit">Edit</a></td>
					<!--<td><c:out value="" /><a href="deleteUser.html?userId=${employee.userID}">Delete</a>  --> 
					
					<% counter+=1;%>
				</tr>
				
			</c:forEach>
			</tbody>
		</table>
		
	</c:if>
	</div>
	
		
		</div>
	 </div>
</div>
</div>
</div>	<!--main section  -->
	<%-- <h2 align="center">User Role Creation Page</h2>
	<font color="green" size="3"><b>${message}</b></font>
	<div class="loader"></div> --%>
	
		
	
	
</body>
<script src="js/jquery-3.5.1.min.js" type="text/javascript"></script>

<!-- <script src="js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="js/dataTables.bootstrap.min.js" type="text/javascript"></script>
<script src="js/jquery.dataTables.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
    $('#example').DataTable();
} );

</script>
 -->
 
 

<script type="text/javascript">


function resetForm() {
//	alert('Exit');
	document.getElementById('saveForm').action = "/SV/Reset.html";
	document.getElementById('saveForm').submit();
}
//Added by say 22 nov 2018-----------------------
function validate()
{

if(document.getElementById("mliName").value!='')
{
	if(document.getElementById("userName").value!='')

	{
		//alert(document.getElementById("roleName").value);
		if(document.getElementById("roleName").value!='')
		{
			//alert("1");
			document.getElementById('saveForm').action = "/SV/saveNewMLIRoles.html";
			document.getElementById('saveForm').submit();
		}else
			alert("Please select User Type");
		   document.getElementById("roleName").focus(); //set focus back to control
	      return false
     }else
	    alert("Please select User Name");
	    document.getElementById("userName").focus(); //set focus back to control
        return false
	    
}else
    alert("Please select MLI Name");
    document.getElementById("mliName").focus(); //set focus back to control
     return false	
}

function doAjaxPost4() {
	var mliName = document.getElementById("mliName").value;
	try {
		$.ajax({
			type : "POST",
			url : "getUserName.html",
			data : "mliName=" + mliName,
			success : function(response) {
				var select2 = document.getElementById('userName');
				if (response.status == "SUCCESS") {
					document.getElementById('userName').options.length = 0;
					for (var i = 0; i < response.result.length; i++) {
						option = document.createElement('option');
						option.text = response.result[i];
						select2.add(option);
					}
				}
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
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<div class="loader"></div>
	<script type="text/javascript">
		$(window).load(function() {
			$(".loader").fadeOut("slow");
		});
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