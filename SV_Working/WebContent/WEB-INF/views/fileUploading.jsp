<%@ page language="java" contentType="text/html; charset=ISO-8859-1"pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
		<title>Insert title here</title>
	</head>
<body onload="processRecords()">
	<table align="center">
		<h2>NBFC File Uploading</h2>
		<form:form method="POST" action="fileUploaded.html" enctype="multipart/form-data">
			<tr>
				<td><form:label path="QUARTER_NO">Select Quarter :</form:label></td>
				<td><form:select path="QUARTER_NO" id="QUARTER">
						<form:option value="" label="----Select Quarter Number----" />
						<form:option value="Q1">Quarter One</form:option>
						<form:option value="Q2">Quarter Two</form:option>
						<form:option value="Q3">Quarter Three</form:option>
						<form:option value="Q4">Quarter Four</form:option>
					</form:select>
				</td>
			</tr>
			<tr>
				<td>Upload your file please:</td>
				<td><input type="file" name="file" /></td>
				<td><input type="submit" value="upload" id="id" /></td>
				<td><form:errors path="file" cssStyle="color: #ff0000;" /></td>
			</tr>
		</form:form>
	</table>
	<div class="loader"></div>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(window).load(function() {
			$(".loader").fadeOut("slow");
		})
	</script>
</body>
</html>