<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String sN = (String) request.getAttribute("sName");
	String expLim = (String) request.getAttribute("eXposureLimit");
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
<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">

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
	<h1 align="center">Confirmation Screen for RP Generate</h1>
	<hr>
	<form:form method="GET" id="A">
		
		
		<table cellpadding=5 cellspacing=5 align=center width=90% >
		<tr><td class="tableHeader1">RP Number :</td>
		<td><b>${rpNumberList.cgPanList}</b></td>
		
		<td align="center" style="visibility: hidden;"><form:input
						path="cgPanList" value="${rpNumberList.cgPanList}" /></td>
		</tr>
		<tr>
		<td class="tableHeader1">Virtual Accout no :</td>
		<td><b>${rpNumberList.virtualAccountNumber}</b></td>
						
						<td align="center" style="visibility: hidden;"> <form:input
						path="virtualAccountNumber" value="${rpNumberList.virtualAccountNumber}" /></td>
		
		</tr>
		<tr><td class="tableHeader1">Amount :</td>
		<td><b>${rpNumberList.totalAmount}</b></td>
		<td align="center" style="visibility: hidden;"><form:input
						path="totalAmount" value="${rpNumberList.totalAmount}" /></td>
		
		</tr>
		<tr><td class="tableHeader1">Gentated Date :</td>
		<td><b>${rpNumberList.currentDate}</b></td>
		<td align="center" style="visibility: hidden;"><form:input
						path="currentDate" value="${rpNumberList.currentDate}" /></td>
		
		</tr>
		<tr><td align="center" style="visibility: hidden;"><form:input
						path="danIDList" value="${rpNumberList.danIDList}" /></td></tr>
		<tr>
					<td>&nbsp;</td>
				</tr>
		</table>

		
	<div align="center">
	<input type="submit" value="Sent for Approval" onclick="submitToProcess()" class="button" />
	<input type="submit" value="Back" onclick="buckToHome()" class="button" />
	</div>
	<!-- </div> -->
	</form:form>
</body>
<script type="text/javascript">
function buckToHome() {
	//alert('Back');
	document.getElementById('A').action = "/SV/genrateRPNumberVirtualAC.html";
	document.getElementById('A').submit();
}

function submitToProcess() {
	//alert('Back');
	document.getElementById('A').action = "/SV/rpNumberConfirmationScreen.html";
	document.getElementById('A').submit();
}
function searchRecord(){
	var nameSearch = document.getElementById("nameSearch").value;
	var searchValue = document.getElementById("searchValue").value;
	alert('search  :'+nameSearch+' searchValue  :'+searchValue);
	if(nameSearch!=null || searchValue!=null){
		document.getElementById('A').action = "/SV/mlidetailsByIndex.html";
		document.getElementById('A').submit();
	}
	
}

</script>

</html>



