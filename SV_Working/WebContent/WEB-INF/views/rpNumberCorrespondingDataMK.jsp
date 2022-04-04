<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	//String sN = (String) request.getAttribute("sName");
	//String expLim = (String) request.getAttribute("eXposureLimit");
%>
<html>
<head>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<title>Portfolio Creation</title>
<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
<script type="text/javascript"
	src="jquery-ui-1.10.3/ui/jquery.ui.datepicker.js"></script>
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<LINK href="<%=request.getContextPath()%>/css/stylesheet.css"
	rel="stylesheet" type="text/css">
<script>
	$(function() {

		$("#ratingDate").datepicker({
			dateFormat : 'dd-mm-yy'
		});

	});
</script>
</head>

<body bgcolor="#E0FFFF">
	<!-- <div
		STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;"> -->
	<h3>Portfolio Details</h3>
	<hr>
	<form:form method="GET" id="A">
		<div
			STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;">
			<table cellpadding=5 class="danRpDataTable" cellspacing=5
				align=center width=90%>
				<thead>
					<tr>
						<c:forEach items="${rows[0]}" var="column">
							<b> </b>
							<td class="tableHeader1"><c:out value="${column.key}" /></td>
						</c:forEach>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${rows}" var="columns">
						<tr>
							<c:forEach items="${columns}" var="column">
								<td align="center">${column.value}</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
	</table>
		</div>
		<input type="submit" value="Back"
					class="button" onclick="backToHomePage()" />
	</form:form>


</body>
<script type="text/javascript">
function backToHomePage() {
	document.getElementById('A').action = "/SV/nbfcUploadedFileReportMK.html";
	document.getElementById('A').submit();

}
</script>

</html>



