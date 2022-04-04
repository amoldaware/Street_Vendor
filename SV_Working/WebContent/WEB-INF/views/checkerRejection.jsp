<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
<%
	String s = (String) request.getAttribute("SName");
%>
</head>
<body>
	<form:form method="POST" action="/nbfc/mliCheckerUpdateFirst.html">
			<table align="center">
				<tr>
					<td><form:label path="APP_REF_NO">View Uploaded Batch File :</form:label></td>
					<td><form:select path="APP_REF_NO" id="ViewFileList">
							<form:option value="" label="--Select Uploaded Batch File--" />
							<c:forEach var="APP_REF_NO" items="${appRefNoList}">
								<form:option id="${APP_REF_NO.arp_ref_no}" value="${APP_REF_NO.arp_ref_no}">${APP_REF_NO.arp_ref_no}</form:option>
							</c:forEach>
						</form:select></td>
					<td><form:errors path="APP_REF_NO" cssClass="error" /></td>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><form:label path="REMARKS" id="remarksLable">Remarks :</form:label></td>
					<td><form:input path="REMARKS" value="${employee.city}" size="28" id="remarksInput" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2"><input type="accept" value="Accept" class="button" id="accept" /></td>

					<td colspan="2"><input type="submit" value="Reject" class="button" onclick="myFunction()" id="showRemarks"  /></td>
				</tr>
			</table>
		</form:form>
</body>
</html>