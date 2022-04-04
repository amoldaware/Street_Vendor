<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>
<body bgcolor="#E0FFFF">

	<font color="green" size="3"><b>${message}</b></font>
	<form:form method="POST" id="A" action="/nbfc/testAction.html">
		<input value="Test One" type="submit" name="testOne" />
		<input value="Test Second" type="submit" name="testSecond" />
		<input value="Test Third" type="submit" name="testThird" />
		<input value="Test Forht" type="submit" name="testForth" />
	</form:form>
</body>
</html>