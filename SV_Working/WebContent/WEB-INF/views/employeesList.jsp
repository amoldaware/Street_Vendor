<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>All MLI List</title>
</head>
<body>
<h1>List Of MLI</h1>
<c:if test="${!empty employees}">
		<h2>List Employees</h2>
		<table align="left" border="1">
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Address</th>
				<th>District</th>
				<th>City</th>
				<th>State</th>
				<th>Pincode</th>
				<th>Fax Number</th>
				<th>Email ID</th>
				<th>Delivery Of DAN</th>
				<th>User Type</th>
			</tr>

			<c:forEach items="${employees}" var="employee">
				<tr>
					<td><c:out value="${employee.fName}" /></td>
					<td><c:out value="${employee.lName}" /></td>
					<td><c:out value="${employee.address}" /></td>
					<td><c:out value="${employee.district}" /></td>
					<td><c:out value="${employee.city}" /></td>
					<td><c:out value="${employee.state}" /></td>
					<td><c:out value="${employee.pin}" /></td>
					<td><c:out value="${employee.fax}" /></td>
					<td><c:out value="${employee.email}" /></td>
					<td><c:out value="${employee.danDelivery}" /></td>
					<td><c:out value="${employee.userType}" /></td>

					<td align="center"><a href="edit.html?id=${employee.fName}">Edit</a>
						| <a href="delete.html?id=${employee.fName}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>