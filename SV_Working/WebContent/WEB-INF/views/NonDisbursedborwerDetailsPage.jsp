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
	<h1 align="center">Borrower/Unit details</h1>
	<hr>
	<form:form method="GET" id="A">
		<table  cellpadding=5 cellspacing=5 align=center width=90%  >			
				<tr>
					<td colspan=5><table border="0">							
							
				</table>
				</td></tr>
				<h1>Non Disbursed Cases</h1>
		<c:if test="${!empty danDetailList}">
		<tr><td bgcolor="#dd6f00"><a href="/SV/disbursedborwerDetails.html?fileName=${fileName}&portfolioRate=${portfolioRate}">Disbursed Cases</a></td> <td bgcolor="#dd6f00"><a href="/nbfc/nonDisbursedborwerDetails.html?fileName=${fileName}&portfolioRate=${portfolioRate}">NonDisbursed Cases</a></td></tr>
				<tr>
				    <td class="tableHeader1">SR.NO.</td>
				    <td class="tableHeader1">PORTFOLIO NAME</td>
					<td class="tableHeader1">BORROWER NAME</td>
					<td class="tableHeader1">SANCTION AMOUNT</td>
					<td class="tableHeader1">RATE OF INTEREST</td>
					<td class="tableHeader1">LOAN ACCOUNT NO</td>
					<td class="tableHeader1">SANCTION DATE</td>
					<td class="tableHeader1">DISBURSMENT DATE</td>
					</tr>
				
	  <% int counter=0;%>
				<c:forEach items="${danDetailList}" var="mliList" varStatus="loopStatus">
					<tr <% if(counter%2==0){%>bgcolor="silver" <%}%>>
					<td><%=counter+1%></td>
					
						<td><c:out value="${mliList.portfoliName}" /></td>
						<td><c:out value="${mliList.borwerName}" /></td>
						<td><c:out value="${mliList.sanctionAmount}" /></td>
						<td><c:out value="${mliList.rateOfInterest}" /></td>
						<td><c:out value="${mliList.loneAccountNo}" /></td>
						<td><c:out value="${mliList.sanctionDate}" /></td>
						<td><c:out value="${mliList.disbursermentDate}" /></td>
					   		<%  counter+=1;%></tr>
				</c:forEach>
				</c:if>
			
		</table>
	<div align="center">
	<input type="submit" value="Back" onclick="buckToHome()" class="button" />
	</div>
	<!-- </div> -->
	</form:form>
</body>
<script type="text/javascript">
function buckToHome() {
	//alert('Back');
	document.getElementById('A').action = "/SV/danAllocation.html";
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



