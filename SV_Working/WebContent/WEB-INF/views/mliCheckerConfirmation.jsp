<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<title>Insert title here</title>
</head>
<body bgcolor="#E0FFFF">
	<form:form method="POST" action="/SV/mliCheckerApprove.html">
		<table>
			<tr>
				<td><form:input path="APP_REF_NO" value="${approvalDetails.APP_REF_NO}" /></td>
				<td><form:input path="USR_ID" value="${approvalDetails.USR_ID}" /></td>
				<td><form:input path="LONE_TYPE" value="${approvalDetails.LONE_TYPE}" /></td>
				<td><form:input path="BUSINESS_PRODUCT" value="${approvalDetails.BUSINESS_PRODUCT}" /></td>
				<td><form:input path="CONSTITUTION" value="${approvalDetails.CONSTITUTION}" /></td>
			
			</tr>
			<tr>
				<td><form:input path="MSE_NAME" value="${approvalDetails.MSE_NAME}" /></td>
				<td><form:input path="SNCTION_DATE" value="${approvalDetails.SNCTION_DATE}" /></td>
				<td><form:input path="SANCTIONED_AMOUNT" value="${approvalDetails.SANCTIONED_AMOUNT}" /></td>
				<td><form:input path="FIRST_DISBURSEMENT_DATE" value="${approvalDetails.FIRST_DISBURSEMENT_DATE}" /></td>
				<td><form:input path="INSERT_RATE" value="${approvalDetails.INSERT_RATE}" /></td>
			
			</tr>
			<tr>
				<td><form:input path="MICRO_SMALL" value="${approvalDetails.MICRO_SMALL}" /></td>
				<td><form:input path="MSE_ADDRESS" value="${approvalDetails.MSE_ADDRESS}" /></td>
				<td><form:input path="CITY" value="${approvalDetails.CITY}" /></td>
				<td><form:input path="DISTRICT" value="${approvalDetails.DISTRICT}" /></td>
				<td><form:input path="PINCODE" value="${approvalDetails.PINCODE}" /></td>
			
			</tr>
			<tr>
				<td><form:input path="STATE" value="${approvalDetails.STATE}" /></td>
				<td><form:input path="ITPAN" value="${approvalDetails.ITPAN}" /></td>
				<td><form:input path="UDYOG_AADHAR_NO" value="${approvalDetails.UDYOG_AADHAR_NO}" /></td>
				<td><form:input path="INDUSTRY_NATURE" value="${approvalDetails.INDUSTRY_NATURE}" /></td>
				<td><form:input path="INDUSTRY_SECTOR" value="${approvalDetails.INDUSTRY_SECTOR}" /></td>
			
			</tr>
			<tr>
				<td><form:input path="NO_OF_EMPLOYEES" value="${approvalDetails.NO_OF_EMPLOYEES}" /></td>
				<td><form:input path="PROJECTED_SALES" value="${approvalDetails.PROJECTED_SALES}" /></td>
				<td><form:input path="PROJECTED_EXPORTS" value="${approvalDetails.PROJECTED_EXPORTS}" /></td>
				<td><form:input path="NEW_EXISTING_UNIT" value="${approvalDetails.NEW_EXISTING_UNIT}" /></td>
				<td><form:input path="UBBANKED_COUSTOMER" value="${approvalDetails.UBBANKED_COUSTOMER}" /></td>
			
			</tr>
			<tr>
				<td><form:input path="NEW_CUSTOMER" value="${approvalDetails.NEW_CUSTOMER}" /></td>
				<td><form:input path="CHIEF_PROMOTER_NAME" value="${approvalDetails.CHIEF_PROMOTER_NAME}" /></td>
				<td><form:input path="MINORITY_COMMUNITY" value="${approvalDetails.MINORITY_COMMUNITY}" /></td>
				<td><form:input path="HANDICAPPED" value="${approvalDetails.HANDICAPPED}" /></td>
				<td><form:input path="WOMEN" value="${approvalDetails.WOMEN}" /></td>
			
			</tr>
			<tr>
				<td><form:input path="CATEGORY" value="${approvalDetails.CATEGORY}" /></td>
				<td><form:input path="PORTFOLIO_BASE_YER" value="${approvalDetails.PORTFOLIO_BASE_YER}" /></td>
				<td><form:input path="PORTFOLIO_NO" value="${approvalDetails.PORTFOLIO_NO}" /></td>
				<td><form:input path="SENCTIONED_PORTFOLIO" value="${approvalDetails.SENCTIONED_PORTFOLIO}" /></td>
				<td><form:input path="GUARANTEE_COMMISSION" value="${approvalDetails.GUARANTEE_COMMISSION}" /></td>
			
			</tr>
			<td><form:input path="PAYOUT_CAP" value="${approvalDetails.PAYOUT_CAP}" /></td>
				<td><form:input path="PORTFOLIO_PERIOD" value="${approvalDetails.PORTFOLIO_PERIOD}" /></td>
				<td><form:input path="PORTFOLIO_START_DATE" value="${approvalDetails.PORTFOLIO_START_DATE}" /></td>
				<td><form:input path="TYPE_OF_LONE" value="${approvalDetails.TYPE_OF_LONE}" /></td>
				<td><form:input path="STATUS" value="${approvalDetails.STATUS}" /></td>
			
			</tr>
			<td><form:input path="REMARKS" value="${approvalDetails.REMARKS}" /></td>
				<td><form:input path="FLAG" value="${approvalDetails.FLAG}" /></td>
			
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>

	</form:form>
</body>
</html>