<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>List Of MLI</h1>
	<c:if test="${!empty listProduct}">
		<h2>List of product</h2>
		<table align="left" border="1">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Address</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Status</th>
				<th>Creation Date</th>
			</tr>

			<c:forEach items="${listProduct}" var="listProduct">
				<tr>
					<td><c:out value="${listProduct.id}" /></td>
					<td><c:out value="${listProduct.name}" /></td>
					<td><c:out value="${listProduct.address}" /></td>
					<td><c:out value="${listProduct.price}" /></td>
					<td><c:out value="${listProduct.quantity}" /></td>
					<td><c:out value="${listProduct.status}" /></td>
					<td><c:out value="${listProduct.creationDate}" /></td>


					<td align="center"><a href="edit.html?id=${employee.fName}">Edit</a>
						| <a href="delete.html?id=${employee.fName}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>