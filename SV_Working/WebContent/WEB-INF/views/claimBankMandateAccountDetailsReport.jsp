<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<title>Claim Bank Mandate Account Details Report</title>
		<link href="css/jquery-ui-css.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
		
	
	</head>
	<body onload="clearField()">
		<div class="main-section">
			<div class="frm-section">
			<nav aria-label="breadcrumb">
					  <ol class="breadcrumb cus-breadcrumb">
					    <li class="breadcrumb-item active" aria-current="page">Reports of NBFC Account Details</li>
					  </ol>
				</nav>
			<div>
				<div class="col-md-12">
					<form:form method="POST" action=""   class="form-horizontal" id="">
						<h5><strong>Claim Bank Mandate Account Details Report</strong></h5>
							<table id="myTable"  class="table table-bordered table-hover cus-table mt-10 mb-0">
							<% int counter=0;%>
										<c:if test="${!empty claimBankMandateBeanList}">
												<thead>
													<tr>
														<th>Sr.No.</th>
														<th>NBFC MLI Name</th>
														<th>MLI Id</th>
														<th>NBFC Maker  Mobile No.</th>
														<th>MLI Name ( Beneficiary)</th>
														<th>Beneficiary's Bank Name</th>
														<th>Branch Name</th>
														<th>Account Type</th>
														<th>Branch Code</th>
														<th>MICR Code</th>
														<th>Account No</th>
														<!-- <th>RTGS No</th> -->
														<th>IFSC Code(RTGS/NEFT)</th>
														<th>Status</th>
														<th>NBFC Maker ID</th>
														<th>NBFC Maker Modified Date</th>
														<th>NBFC Checker ID</th>
														<th>NBFC Checker Modified Date</th>
														<th>CGTMSE Checker</th>
														<th>CGTMSE Modified Date </th>
														<th>Email ID </th>
														<th>Bank Mandate </th>
													</tr>
													<c:forEach items="${claimBankMandateBeanList}" var="claimBankMandateBeanList">	
														<tr <% if(counter%2==0){%>bgcolor="silver" <%}%>>
															<td><%=counter+1%></td>
															<td>${claimBankMandateBeanList.mliName}</td>
															<td>${claimBankMandateBeanList.memberId}</td>
															<td>${claimBankMandateBeanList.mobileNo}</td>
															<td>${claimBankMandateBeanList.mliBeneficiaryName}</td>
															<td>${claimBankMandateBeanList.mliBeneficiaryBankName}</td>
															<td>${claimBankMandateBeanList.branchName}<</td>
															<td>${claimBankMandateBeanList.accountType}</td>
															<td>${claimBankMandateBeanList.branchCode}</td>
															<td>${claimBankMandateBeanList.micrCode}</td>
															<td>${claimBankMandateBeanList.accountNo}</td>
															<!--<td>${claimBankMandateBeanList.rtgsNo}</td>-->
															<td>${claimBankMandateBeanList.ifscCode}</td>
															<td>${claimBankMandateBeanList.status}</td>
															<td>${claimBankMandateBeanList.nbfcMakerId}</td>
															<td>${claimBankMandateBeanList.nbfcMakerDate}</td>
															<td>${claimBankMandateBeanList.nbfcCheckerId}</td>
															<td>${claimBankMandateBeanList.nbfcCheckerDate}</td>
															<td>${claimBankMandateBeanList.cgtmseCheckerId}</td>
															<td>${claimBankMandateBeanList.cgtmeCheckerDate}</td>
															<td>${claimBankMandateBeanList.emailId}</td>
															<td><a href="getUploadedBankMandateForm.html?memberId=${claimBankMandateBeanList.memberId}">View</a></td>
														</tr>
													</c:forEach>	
												</thead>
										</c:if>
										<tr>
											<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
											<div class="form-group">
												<div class="col-md-2 prl-10">
													<div id="requiredRtgsNo" Class="displayErrorMessageInRedColor"></div>
													<div class="col-md-2 prl-10">
														<table>
															<tr>
																<td></td><td></td><td></td><td></td>
																<td><input type="button" value="Print" class="btn btn-reset" name="print" id="print"   onclick="window.print()"/></td>
															</tr>
														</table>
													</div>
												</div>
											</div>									
										</div>
										</tr>
							</table>
					</form:form>
				</div>
			</div>
		</div>
	</body>
</html>
