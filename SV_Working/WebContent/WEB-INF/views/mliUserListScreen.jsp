<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MLI User Registration Form</title>
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<%
	String s = (String) request.getAttribute("SName");
%>

</head>
<body bgcolor="#E0FFFF">
<form:form method="GET" action="/SV/add.html">
	<div STYLE=" height: 450px; width: 1030px; font-size: 12px; overflow: auto;">
	
<c:if test="${!empty employees}">
<br>
		<h2 align="center"><u>All Registered MLI User List</u></h2>
		<br>
		<table align="center" border="1">
			<tr>
			    <th>MLI NAME</th>
				<th>FIRST NAME</th>
				<th>MIDDLE</th>
				<th>LAST NAME</th>
				<th>USER ID</th>
				<th>MOBILE NUMBER</th>
				<th>PHONE NUMBER</th>
				<th>EMAIL ID</th>
				<th>USER TYPE</th>
			</tr>

			<c:forEach items="${employees}" var="employee">
				<tr>
				    <td><c:out value="${employee.mliName}" /></td>
					<td><c:out value="${employee.fName}" /></td>
					<td><c:out value="${employee.middalName}" /></td>
					<td><c:out value="${employee.lName}" /></td>
					<td><c:out value="${employee.userID}" /></td>
					<td><c:out value="${employee.mobileNumber}" /></td>
					<td><c:out value="${employee.phoneNumber}" /></td>
					<td><c:out value="${employee.email}" /></td>
					<td><c:out value="${employee.userType}" /></td>
					

				</tr>
			</c:forEach>
		</table>
	</c:if>
	</div>
	<div align="center"><br>
	<br>
	<input type="submit" value="Back" class="button" align="middle"/>
	</div>
	</form:form>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<div class="loader"></div>
	<script type="text/javascript">
		$(window).load(function() {
			$(".loader").fadeOut("slow");
		});
	</script>
</body>
</html>