<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<title>Approval/Rejection from CGTMSE</title>
		<link href="css/jquery-ui-css.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
		
		<script type="text/javascript">
		function approveBankMandate(){
			try {
				document.getElementById('approvalOrRejectionBankMandateFromCGTMSEFormId').action = "/SV/approveBankMandateByCGTMSEChecker.html";
				document.getElementById('approvalOrRejectionBankMandateFromCGTMSEFormId').submit();
			}catch(err) {
				alert("Error in approveBankMandate function=="+err.message);
			}	
		}	

		function returnBankMandate(){
			try {
				document.getElementById('approvalOrRejectionBankMandateFromCGTMSEFormId').action = "/SV/returnBankMandateByCGTMSEChecker.html";
				document.getElementById('approvalOrRejectionBankMandateFromCGTMSEFormId').submit();
			}catch(err) {
				alert("Error in returnBankMandate function=="+err.message);
			}	
		}		
		</script>
	</head>
	<body onload="clearField()">
		<div class="main-section">
			<div class="frm-section">
				<div class="col-md-12">
					<form:form method="POST" action=""   class="form-horizontal" id="approvalOrRejectionBankMandateFromCGTMSEFormId">
						<h5><strong>Get Bank Details for Approval</strong></h5>
							<table id="myTable"  class="table table-bordered table-hover cus-table mt-10 mb-0">
							<% int counter=0;%>
										<c:if test="${!empty bankmanDateDataList}">
												<thead>
													<tr>
														<th>Sr.No.</th>
														<th>MLI Name</th>
														<th>Member Id</th>
														<th>MLI Name ( Beneficiary)</th>
														<th>Beneficiary's Bank Name</th>
														<th>Branch Name</th>
														<th>Branch Code</th>
														<th>Account No</th>
														<th>Action</th>
													</tr>
													<c:forEach items="${bankmanDateDataList}" var="bankmanDateDataList">	
														<tr <% if(counter%2==0){%>bgcolor="silver" <%}%>>
															<td><%=counter+1%></td>
															<td>${bankmanDateDataList.mliName}</td>
															<td>${bankmanDateDataList.memberId}</td>
															<td>${bankmanDateDataList.mliBeneficiaryName}</td>
															<td>${bankmanDateDataList.mliBeneficiaryBankName}</td>
															<td>${bankmanDateDataList.branchName}</td>
															<td>${bankmanDateDataList.branchCode}</td>
															<td>${bankmanDateDataList.accountNo}</td>
															<td><a href="getBankMandateDataForApprovalOrRejectionFromCGTMSE.html?memberId=${bankmanDateDataList.memberId}">Approve | Return</a></td>
														</tr>
													</c:forEach>	
												</thead>
										</c:if>
										<c:if test="${empty bankmanDateDataList}">
												<thead>
													<tr>
														<th>Sr.No.</th>
														<th>MLI Name</th>
														<th>Member Id</th>
														<th>MLI Name ( Beneficiary)</th>
														<th>Beneficiary's Bank Name</th>
														<th>Branch Name</th>
														<th>Branch Code</th>
														<th>Account No</th>
														<th>Action</th>
													</tr>
													
												</thead>
										</c:if>
							</table>
					</form:form>
				</div>
			</div>
		</div>
	</body>
</html>
