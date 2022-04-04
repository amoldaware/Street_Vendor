<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NPA Detail</title>

<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<LINK href="<%=request.getContextPath()%>/css/stylesheet.css"
	rel="stylesheet" type="text/css">

<link href="css/jquery-ui-css.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<%
	String s = (String) request.getAttribute("SName");

     
%>
</head>
<body>
	<div class="main-section">
 <nav aria-label="breadcrumb">
   <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/ClaimSettledReport.html">Claim Settled Report</a></li>  
    <li class="breadcrumb-item active" aria-current="page">Claim Settled Report Data</li>
  </ol> 
</nav>
		<div class="container-fluid">
			<div>
				<div class="frm-section">
					<div class="col-md-12"><h2 align="center" class="heading">Claim Settled Report</h2>
						<h5 class="notification-message">${message}</h5>
						<h5 class="error1">${Errormessage}</h5>
						<div class="loader"></div>
						<div class="d-inlineblock float-right  mt-25">
				<!-- <label style="padding-top:8px; font-weight:100;">Search :</label> -->
			<!-- 	<input type="text" id="myInput"  class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px;
		  		  background-color: #ffffff;" placeholder="Search Data111.." title="Type in a name">    		
		    		<button style="border:none !important;"> -->
		    		<a href="ClaimSettledReportDownload.html">
		    			<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
		    		</a>
		    		</button>			
				</div>
						<form:form method="POST" action="" id="A" class="form-horizontal">

							<table id="myTable"
								class="table table-bordered table-hover cus-table mt-10 mb-0">
								<c:if test="${!empty ClaimSettledDataReport}">
									<thead>
										<tr>
											<th>SR.No.</th>
											<th>Bank Name</th>
											<th>Member Id</th>
											<th>Unit Name</th>
										   <th>CGPAN Number</th>
											<th>Approved Amt </th> 
											<th>Latest O/S guaranteed Amount </th> 
											<th>O/S Amt as on NPA </th>
											<th>(Repayment/ Recovery) after  NPA </th>
											<th>Subsidy amount</th>
											<th>O/S as on lodgment of claim</th>
											<th>Latest O/S Amt as on ASF payment</th>
											<th>Eligible Amt </th>
											<th>Payable as First Installment</th> 
											<th>CLAIM APPROVED DATE</th> 
											<th>Loan Account No</th>
										
										</tr>
									</thead>
									<%
										int counter = 0;
									%>
									<c:forEach items="${ClaimSettledDataReport}" var="ClaimSettledDataReport">
									<c:if test="${counter % 2 == 0}">
										<tr bgcolor="silver">
											<td><%=counter + 1%></td>
											<td><c:out value="${ClaimSettledDataReport.bankName}" /></td>
											<td><c:out value="${ClaimSettledDataReport.memberId}" /></td>
											<td><c:out value="${ClaimSettledDataReport.borrowerName}" /></td>
											<td><c:out value="${ClaimSettledDataReport.cgpan}" /></td>
											<td><c:out value="${ClaimSettledDataReport.guarantee_Amt}" /></td>
											<td><c:out value="${ClaimSettledDataReport.latestOsAmt}" /></td>
											<td><c:out value="${ClaimSettledDataReport.npaEligibleAmt}" /></td>
											<td><c:out value="${ClaimSettledDataReport.recovery}" /></td>
											<td><c:out value="${ClaimSettledDataReport.subsidyAmt}" /></td>
											<td><c:out value="${ClaimSettledDataReport.osAmtClaim}" /></td>
											<td><c:out value="${ClaimSettledDataReport.totalOsAmt}" /></td>
											<td><c:out value="${ClaimSettledDataReport.eligableAmtClaim}" /></td>
											<td><c:out value="${ClaimSettledDataReport.firstInstallClaim}" /></td>
											<td><c:out value="${ClaimSettledDataReport.cgtCheckerDate}" /></td>
											<td><c:out value="${ClaimSettledDataReport.loanAccountNo}" /></td>
											<%
												counter += 1;
											%>
										</tr>
										</c:if>
									</c:forEach>
								</c:if>
							</table>
							<div></div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="js/jquery-3.5.1.min.js" type="text/javascript"></script>

	<div class="loader"></div>
	<script type="text/javascript">
		$(window).load(function() {
			$(".loader").fadeOut("slow");
		});
	</script>
</body>
</html>