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
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<title>Portfolio Creation</title>
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
		<script type="text/javascript" src="jquery-ui-1.10.3/ui/jquery.ui.datepicker.js"></script>
		<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
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
	<h1 align="center">DAN Allocation Page</h1>
		<hr>
			<form:form method="GET" id="A">
				<table  cellpadding=5 cellspacing=5 align=center width=90%  >			
					<tr>
						<td colspan=5><table border="0">							
				</table>
			</td></tr>
		<c:if test="${!empty danDetailList}">
				<tr>
				    <td class="tableHeader1">SR.NO.</td>
					<td class="tableHeader1">PORTFOLIO NAME</td>
					<td class="tableHeader1">FILE NAME</td>
					<td class="tableHeader1">TOTAL FEE</td>
					<td class="tableHeader1">STATUS</td>
				</tr>	
	  <% int counter=0;%>
				<c:forEach items="${danDetailList}" var="mliList" varStatus="loopStatus">
					<tr <% if(counter%2==0){%>bgcolor="silver" <%}%>>
					<td><%=counter+1%></td>
						<td><c:out value="${mliList.portfolioName}" /></td>
						<td><c:out value="${mliList.loanAccountNo}" /></td>		
						<td><c:out value="${mliList.calculatedAmount}" /></td>
						<td align="center"><c:out value="Y" /></td>
					    <%  counter+=1;%></tr>   		
				</c:forEach>
		</c:if>
				<tr>
					<td>&nbsp;</td>
				</tr>
		<tr><td></td><td></td><td class="tableHeader1">Total Amount</td><td><c:out value="${totalOfAmount}" /></td></tr>
		<tr>
			<td>&nbsp;</td>
		</tr>	
		</table>
	<div align="center">
		<input type="submit" value="Back" onclick="buckToHome()" class="button" />
	</div>
</form:form>
</body>
	<script type="text/javascript">
			function buckToHome() {
				//alert('Back');
				document.getElementById('A').action = "/SV/genrateRPNumberVirtualAC.html";
				document.getElementById('A').submit();
			}

			function submitForApproval() {
				//alert('Back');
				document.getElementById('A').action = "/SV/genrateRPNumberVirtualAC.html";
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



