<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">

<%
	String s = (String) request.getAttribute("SName");
%>

</head>
<body bgcolor="#E0FFFF">

<div class="main-section">
<div class="container-fluid">
<!-- <div class="row"> -->
	<div>	
	<div class="tbl-details">
		<div class="col-md-12">
		<form:form method="GET" action="/SV/mliChecker.html">
		<h3 class="heading text-center pb-15">Portfolio Failed Records</h3>		
		<h5 class="notification-message"><strong>${message}</strong></h5>
		
			<c:if test="${!empty appRefNoList}">
			<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0 ">
			<thead>
				<tr>
					<th>LOAN TYPE</th>
					<th>PORTFOLIO NO</th>
					<th>PORTFOLIO BASE YEAR</th>
					<th>BUISENESS PRODUCT</th>
					<th>LOAN ACCOUNT NO.</th>
					<th>CONSTITUTION</th>
					<th>UNIT NAME</th>
					<th>SANCTION DATE</th>
					<th>SANCTION AMOUNT</th>
					<th>FIRST DISBURSMENT DATE</th>
					<th>INTEREST RATE</th>
					<th>RETAIL TRADE</th>
					<th>MICRO /SMALL</th>
					<th>TENURE IN MONTHS</th>
					<th>UNIT Address</th>
					<th>CITY</th>
					<th>DISTRICT</th>
					<th>PINCODE</th>
					<th>STATE</th>
					<th>MSE IT PAN</th>
					<th>UDYOG ADHAR NO</th>
					<th>MSME REGISTRATION NO</th>
					<th>INDUSTRY NATURE</th>
					<th>INDUSTRY SECTOR</th>
					<th>NO OF EMPLOYEES</th>
					<th>PROJECTED SALES</th>
					<th>PROJECTED EXPORTS</th>
					<th>NEW EXISTING UNIT</th>
					<th>PREVIOUS BANKING EXPERIANCE</th>
					<th>FIRST TIME CUSTOMER</th>
					<th>CHIEF PROMOTER FIRST NAME</th>
					<th>CHIEF PROMOTER MIDDLE NAME</th>
					<th>CHIEF PROMOTOR LAST NAME</th>
					<th>CHIEF PROMOTOR IT PAN</th>
					<th>CHIEF PROMOTOR MAIL ID</th>
					<th>CHIEF PROMOTOR CONTACT NUMBER</th>
					<th>MINORITY COMMUNITY</th>
					<th>HANDICAAPED</th>
					<th>WOMEN</th>
					<th>CATEGORY</th>
					<th>PARTIALY SECURITY FLAG</th>
					<th>GUARANTEE AMOUNT</th>
					<th>COLLATRAL SECURITY AMOUNT</th>
					<th>OUTSTANDING AMOUNT</th>	
					<th>AADHAR NUMBER</th>					
					
				</tr>
				</thead>
				<c:forEach items="${appRefNoList}" var="employee">
					<tr>
						<td><c:out value="${employee.LONE_TYPE}" /></td>
						<td><c:out value="${employee.PORTFOLIO_NO}" /></td>
						<td><c:out value="${employee.PORTFOLIO_BASE_YER}" /></td>
						<td><c:out value="${employee.BUSINESS_PRODUCT}" /></td>
						<td><c:out value="${employee.LOAN_ACCOUNT_NO}" /></td>
						<td><c:out value="${employee.CONSTITUTION}" /></td>
						<td><c:out value="${employee.MSE_NAME}" /></td>
						<td><c:out value="${employee.SNCTION_DATE}" /></td>
						<td><c:out value="${employee.SANCTIONED_AMOUNT}" /></td>
						<td><c:out value="${employee.FIRST_DISBURSEMENT_DATE}" /></td>
						<td><c:out value="${employee.INSERT_RATE}" /></td>
						<td><c:out value="${employee.MICRO_SMALL}" /></td>
						<td><c:out value="${employee.TENOR_IN_MONTHS}" /></td>												
						<td><c:out value="${employee.MSE_ADDRESS}" /></td>
						<td><c:out value="${employee.CITY}" /></td>
						<td><c:out value="${employee.DISTRICT}" /></td>
						<td><c:out value="${employee.PINCODE}" /></td>
						<td><c:out value="${employee.STATE}" /></td>
						<td><c:out value="${employee.MSE_ITPAN}" /></td>
						<td><c:out value="${employee.UDYOG_AADHAR_NO}" /></td>
						<td><c:out value="${employee.MSME_REGISTRATION_NO}" /></td>
						<td><c:out value="${employee.INDUSTRY_NATURE}" /></td>
						<td><c:out value="${employee.INDUSTRY_SECTOR}" /></td>
						<td><c:out value="${employee.NO_OF_EMPLOYEES}" /></td>
						<td><c:out value="${employee.PROJECTED_SALES}" /></td>
						<td><c:out value="${employee.PROJECTED_EXPORTS}" /></td>
						<td><c:out value="${employee.NEW_EXISTING_UNIT}" /></td>
						<td><c:out
								value="${employee.CUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE}" /></td>
						<td><c:out value="${employee.FIRST_TIME_CUSTOMER}" /></td>
						<td><c:out value="${employee.CHIEF_PROMOTER_FIRST_NAME}" /></td>
						<td><c:out value="${employee.CHIEF_PROMOTER_MIDDLE_NAME}" /></td>
						<td><c:out value="${employee.CHIEF_PROMOTER_LAST_NAME}" /></td>
						<td><c:out value="${employee.CHIEF_PROMOTER_IT_PAN}" /></td>
						<td><c:out value="${employee.CHIEF_PROMOTER_MAIL_ID}" /></td>
						<td><c:out value="${employee.CHIEF_PROMOTER_CONTACT_NUMBER}" /></td>
						<td><c:out value="${employee.MINORITY_COMMUNITY}" /></td>
						<td><c:out value="${employee.HANDICAPPED}" /></td>
						<td><c:out value="${employee.WOMEN}" /></td>
						<td><c:out value="${employee.CATEGORY}" /></td>
						<td><c:out value="${employee.AADHAR_NUMBER}" /></td>											
					</tr>
				</c:forEach>			
			</table>
		</c:if>
		
		<div class="d-inlineblock">
		<input onclick="window.open('', '_self', ''); window.close();" class="btn btn-reset" style="width:100px;" value="Close"/>
		</div>
		
		</form:form>
		</div>
	</div>	
	
	
	
	</div>
</div>
</div>	

	<%-- <form:form method="GET" action="/SV/mliChecker.html">

		<h1 align="center"><u>Portfolio Failed Records</u></h1>
		
		<h3 class="h3">${message}</h3>
<div STYLE=" height: 450px; width: 1300px; font-size: 12px; overflow: auto;">

		<c:if test="${!empty appRefNoList}">
			<table align="left" border="1">
				<tr>
					<th>Loan Type</th>
					<th>PORTFOLIO_NO</th>
					<th>PORTFOLIO_BASE_YEAR</th>
					<th>Business Product</th>
					<th>Loan A/C No.</th>
					<th>Constitution</th>
					<th>MSE NAME</th>
					<th>Sanction Date</th>
					<th>Sanctioned Amount</th>
					<th>First Disbursement Date</th>
					<th>Interest Rate</th>
					<th>Micro/Small</th>
					<th>Tenor in Months</th>
					<th>MSE Address</th>
					<th>City</th>
					<th>District</th>
					<th>Pincode</th>
					<th>State</th>
					<th>MSE IT PAn</th>
					<th>Udyog Aadhar No</th>
					<th>MSME REGISTRATION NO</th>
					<th>INDUSTRY NATURE</th>
					<th>INDUSTRY_SECTOR</th>
					<th>NO OF EMPLOYEES</th>
					<th>PROJECTED SALES</th>
					<th>PROJECTED EXPORTS</th>
					<th>NEW EXISTING UNIT</th>
					<th>PREVIOUS_BANKING_EXPERIENCE</th>
					<th>FIRST_TIME_CUSTOMER</th>
					<th>CHIEF_PROMOTER_FIRST_NAME</th>
					<th>CHIEF_PROMOTER_MIDDLE_NAME</th>
					<th>CHIEF_PROMOTER_LAST_NAME</th>
					<th>CHIEF_PROMOTER_IT_PAN</th>
					<th>CHIEF_PROMOTER_MAIL_ID</th>
					<th>CHIEF_PROMOTER_CONTACT_NUMBER</th>
					<th>MINORITY_COMMUNITY</th>
					<th>HANDICAPPED</th>
					<th>WOMEN</th>
					<th>CATEGORY</th>
					<th>AADHAR NUMBER</th>
					
				</tr>

				<c:forEach items="${appRefNoList}" var="employee">
					<tr>
						<td><c:out value="${employee.LONE_TYPE}" /></td>
						<td><c:out value="${employee.PORTFOLIO_NO}" /></td>
						<td><c:out value="${employee.PORTFOLIO_BASE_YER}" /></td>
						<td><c:out value="${employee.BUSINESS_PRODUCT}" /></td>
						<td><c:out value="${employee.LOAN_ACCOUNT_NO}" /></td>
						<td><c:out value="${employee.CONSTITUTION}" /></td>
						<td><c:out value="${employee.MSE_NAME}" /></td>
						<td><c:out value="${employee.SNCTION_DATE}" /></td>
						<td><c:out value="${employee.SANCTIONED_AMOUNT}" /></td>
						<td><c:out value="${employee.FIRST_DISBURSEMENT_DATE}" /></td>
						<td><c:out value="${employee.INSERT_RATE}" /></td>
						<td><c:out value="${employee.MICRO_SMALL}" /></td>
						<td><c:out value="${employee.TENOR_IN_MONTHS}" /></td>
						
						
						<td><c:out value="${employee.MSE_ADDRESS}" /></td>
						<td><c:out value="${employee.CITY}" /></td>
						<td><c:out value="${employee.DISTRICT}" /></td>
						<td><c:out value="${employee.PINCODE}" /></td>
						<td><c:out value="${employee.STATE}" /></td>
						<td><c:out value="${employee.MSE_ITPAN}" /></td>
						<td><c:out value="${employee.UDYOG_AADHAR_NO}" /></td>
						<td><c:out value="${employee.MSME_REGISTRATION_NO}" /></td>
						<td><c:out value="${employee.INDUSTRY_NATURE}" /></td>
						<td><c:out value="${employee.INDUSTRY_SECTOR}" /></td>
						<td><c:out value="${employee.NO_OF_EMPLOYEES}" /></td>
						<td><c:out value="${employee.PROJECTED_SALES}" /></td>
						<td><c:out value="${employee.PROJECTED_EXPORTS}" /></td>
						<td><c:out value="${employee.NEW_EXISTING_UNIT}" /></td>
						<td><c:out
								value="${employee.CUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE}" /></td>
						<td><c:out value="${employee.FIRST_TIME_CUSTOMER}" /></td>
						<td><c:out value="${employee.CHIEF_PROMOTER_FIRST_NAME}" /></td>
						<td><c:out value="${employee.CHIEF_PROMOTER_MIDDLE_NAME}" /></td>
						<td><c:out value="${employee.CHIEF_PROMOTER_LAST_NAME}" /></td>
						<td><c:out value="${employee.CHIEF_PROMOTER_IT_PAN}" /></td>
						<td><c:out value="${employee.CHIEF_PROMOTER_MAIL_ID}" /></td>
						<td><c:out value="${employee.CHIEF_PROMOTER_CONTACT_NUMBER}" /></td>
						<td><c:out value="${employee.MINORITY_COMMUNITY}" /></td>
						<td><c:out value="${employee.HANDICAPPED}" /></td>
						<td><c:out value="${employee.WOMEN}" /></td>
						<td><c:out value="${employee.CATEGORY}" /></td>
						<td><c:out value="${employee.AADHAR_NUMBER}" /></td>
						
						
					</tr>
				</c:forEach>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
				</tr>
			</table>
		</c:if>
		
		
		</div>
		<div align="center"><input onclick="window.open('', '_self', ''); window.close();" class="button1" value="Close"/>
		</div>
		
	</form:form> --%>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<div class="loader"></div>
	<script type="text/javascript">
		$(window).load(function() {
			$(".loader").fadeOut("slow");
		});
	</script>
</body>
</html>