<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">

<%
	String s = (String) request.getAttribute("SName");
%>
<style>
.tbl-porfolio > thead > tr >th{min-width:100px;}
.tbl-porfolio > thead > tr >th:nth-child(7){min-width:200px;}
</style>

</head>
<body bgcolor="#E0FFFF">
<div class="main-section">
<div class="container-fluid">
<!-- <div class="row"> -->
	<div>	
	
	<div class="tbl-details">
		<div class="col-md-12">
		<h5 class="notification-message"><strong>${message}</strong></h5>
		
		<div class="d-inlineblock">
		<input onclick="window.open('', '_self', ''); window.close();" class="btn btn-reset" style="width:100px;" value="Close"/>
		</div>
		<div class="d-inlineblock float-right">
		<!-- <label style="padding-top:8px; font-weight:100;">Search :</label> -->
		<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px;
  		  background-color: #ffffff;" placeholder="Search Data.." title="Type in a name">
    		
    		<button style="border:none !important; cursor: not-allowed;">
    			<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
    		</button>			
		</div>
			<form:form method="GET" action="/SV/mliChecker.html">
			
			<c:if test="${!empty appRefNoList}">
			<div class="table-reposnvie">
			<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0 tbl-porfolio">
				<thead>
					<tr>
				    <th>SR.NO.</th>
					<th>LOAN NUMBER</th>
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
				<% int counter=0;%>
				<c:forEach items="${appRefNoList}" var="employee">
					<%-- <tr <% if(counter%2==0){%>bgcolor="silver" <%}%>> --%>
					<tr>
					    <td><%=counter+1%></td>
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
						<td><c:out value="${employee.RETAIL_TRADE}" /></td>
				
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
						<td><c:out value="${employee.CUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE}" /></td>
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
						<td><c:out value="${employee.partialSecurityFlag}" /></td>
						<td><c:out value="${employee.guaranteeAmount}" /></td>
						<td><c:out value="${employee.colletralSecurityAmount}" /></td>
						<td><c:out value="${employee.outstandingAmount}" /></td>
						<td><c:out value="${employee.AADHAR_NUMBER}" /></td>
						
					<%counter+=1;%></tr>
				</c:forEach>
				
			</table>
			</div>
			</c:if>
			</form:form>
		</div>
	</div>
	
	</div>
</div>
</div>	


	<%-- <form:form method="GET" action="/SV/mliChecker.html">
		<h1 align="center"><u>Portfolio Successful Records</u></h1>		
		<h3 class="h3">${message}</h3>
<div STYLE=" height: 450px; width: 1300px; font-size: 12px; overflow: auto;">

		<c:if test="${!empty appRefNoList}">
			<table align="left" border="1">
				<tr>
				    <td class="tableHeader1">Sr.No.</td>
					<td class="tableHeader1">Loan Type</td>
					<td class="tableHeader1">Portfolio No</td>
					<td class="tableHeader1">Portfolio Base Year</td>
					<td class="tableHeader1">Business Product</td>
					<td class="tableHeader1">Loan A/C No.</td>
					<td class="tableHeader1">Constitution</td>
					<td class="tableHeader1">UNIT NAME</td>
					<td class="tableHeader1">Sanction Date</td>
					<td class="tableHeader1">Sanctioned Amount</td>
					<td class="tableHeader1">First Disbursement Date</td>
					<td class="tableHeader1">Interest Rate</td>
					<td class="tableHeader1">Retail Trade</td>
					<td class="tableHeader1">Micro/Small</td>
					<td class="tableHeader1">Tenor in Months</td>
					<td class="tableHeader1">UNIT Address</td>
					<td class="tableHeader1">City</td>
					<td class="tableHeader1">District</td>
					<td class="tableHeader1">Pincode</td>
					<td class="tableHeader1">State</td>
					<td class="tableHeader1">MSE IT PAn</td>
					<td class="tableHeader1">Udyog Aadhar No</td>
					<td class="tableHeader1">MSME Registration NO</td>
					<td class="tableHeader1">Industry Nature</td>
					<td class="tableHeader1">Industry Sector</td>
					<td class="tableHeader1">No of Employees</td>
					<td class="tableHeader1">Projected Sales</td>
					<td class="tableHeader1">Projected Exports</td>
					<td class="tableHeader1">New Existing Unit</td>
					<td class="tableHeader1">Previous Banking Experience</td>
					<td class="tableHeader1">First Time Customer</td>
					<td class="tableHeader1">Chief Promoter First Name</td>
					<td class="tableHeader1">Chief Promoter Middle Name</td>
					<td class="tableHeader1">Chief Promoter Last Name</td>
					<td class="tableHeader1">Chief Promoter IT PAN</td>
					<td class="tableHeader1">Chief Promoter Mail Id</td>
					<td class="tableHeader1">Chief Promoter Contact Number</td>
					<td class="tableHeader1">Minority Community</td>
					<td class="tableHeader1">Handicapped</td>
					<td class="tableHeader1">Women</td>
					<td class="tableHeader1">Category</td>
					<td class="tableHeader1">Partial Security Flag</td>
					<td class="tableHeader1">Guarantee Amount</td>
					<td class="tableHeader1">Colletral Security Amount</td>
					<td class="tableHeader1">Outstanding Amount</td>	
					<td class="tableHeader1">Aadhar Number</td>						
				</tr>
<% int counter=0;%>
				<c:forEach items="${appRefNoList}" var="employee">
					<tr <% if(counter%2==0){%>bgcolor="silver" <%}%>>
					    <td><%=counter+1%></td>
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
						<td><c:out value="${employee.RETAIL_TRADE}" /></td>
				
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
						<td><c:out value="${employee.CUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE}" /></td>
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
						<td><c:out value="${employee.partialSecurityFlag}" /></td>
						<td><c:out value="${employee.guaranteeAmount}" /></td>
						<td><c:out value="${employee.colletralSecurityAmount}" /></td>
						<td><c:out value="${employee.outstandingAmount}" /></td>
						<td><c:out value="${employee.AADHAR_NUMBER}" /></td>
						
					<%counter+=1;%></tr>
				</c:forEach>
				 
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