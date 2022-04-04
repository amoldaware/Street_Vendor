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


</head>

<body bgcolor="#E0FFFF">
	<!-- <div
		STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;"> -->
	<h3>MLI DAN Generated RP Number Checker Approval</h3>
	<hr>
	<form:form method="POST" id="A">
		<div
			"STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;">
			<table cellpadding=5 cellspacing=5 class="danRpDataTable"
				align=center width=90%>


				<c:if test="${!empty dataList}">
					<tr>
						<td class="tableHeader1">SR.NO.</td>
						<td class="tableHeader1">RP NUMBER</td>
						<td class="tableHeader1">AMOUNT</td>
						<td class="tableHeader1">GENERATE DATE</td>
						<td class="tableHeader1">STATUS</td>
						<%-- <td class="tableHeader1">Select for Payment</br><form:checkbox path="selectAll" id="select_all" onchange="selectAllCheckBox();" cssClass="select_all"/></td> --%>
					</tr>

					<%
						int counter = 0;
					%>
					<c:forEach items="${dataList}" var="dataList">
						<tr <%if (counter % 2 == 0) {%> bgcolor="silver" <%}%>>
							<td><c:out value="<%=counter + 1%>" /></td>
							<td><a
								href="/SV/rpNumberCorrespondingData.html?rpNumber=${dataList.rpNumber}"><c:out
										value="${dataList.rpNumber}" /></a></td>
							<td><c:out value="${dataList.amount}" /></td>
							<td><c:out value="${dataList.date}" /></td>
							<td><c:out value="${dataList.status}" /></td>
							<%-- <td align="center"><form:checkbox path="chcktbl" value="${dataList.rpNumber}" onchange="uncheckSelectAll();" cssClass="chcktbl"/></td>
  					 --%>
							<!--<input type="checkbox" id="chcktbl" class="chcktbl"  />
						-->


							<%-- <td align="center"><a
							href="getDetails.html?mliLongName=${mliList.mliLongName}">Edit</a>
							| <a href="deleteMLIDetails.html?id=${employee.mliLongName}">Delete</a></td> <%  counter+=1;%>--%>
						</tr>
						<%
							counter += 1;
						%>
					</c:forEach>
					<tr>
						<td class="tableHeader1"></td>
						<td class="tableHeader1"></td>
						<td class="tableHeader1"></td>
						<td class="tableHeader1"></td>
						<td class="tableHeader1"></td>
						
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td><input class="button" readonly="true" id="Back"
							onclick="backToHome();" value="Back" align=""></td>

					</tr>
				</c:if>
				<c:if test="${empty dataList}">
					<tr>
					<td class="tableHeader1">SR.NO.</td>
						<td class="tableHeader1">RP NUMBER</td>
						<td class="tableHeader1">AMOUNT</td>
						<td class="tableHeader1">GENERATE DATE</td>
						<td class="tableHeader1">STATUS</td>
					</tr>
					<tr>
					<tr>

						<td colspan="6" align="center">Data not available</td>

					</tr>
				</c:if>









			</table>
		</div>
	</form:form>
	<!-- </div> -->

</body>
<script type="text/javascript">
	function backToHome() {
alert('hello');
		document.getElementById('A').action = "/SV/nbfcCheckerBackHome.html";
		document.getElementById('A').submit();

	}
</script>

</html>



