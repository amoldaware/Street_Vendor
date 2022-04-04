<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
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

<style>
.claim-settle thead tr th:nth-child(3) {
	min-width: 80px;
}

.claim-settle thead tr th:nth-child(4) {
	min-width: 100px;
}

.claim-settle thead tr th:nth-child(6) {
	min-width: 80px;
}

.claim-settle thead tr th:nth-child(7) {
	min-width: 150px;
}

.btn-view {
	padding: 3px 5px;
	border-radius: 3px;
	background-color: rgba(146, 59, 149, 1);
	color: white;
}

.clmAmt {
	cursor: pointer;
	border-right: none;
	border-left: none;
	border-top: none;
}

.clmref {
	cursor: pointer;
	border-right: none;
	border-left: none;
	border-top: none;
	border-bottom: 1px solid rgba(51, 122, 182, 1);
}

.clmref:hover {
	color: rgba(146, 59, 149, 1);
}

.modal-dialog {
	width: 80%;
}

.modal-header,.modal-footer {
	padding: 10px 15px;
}

.modal-body {
	padding: 5px 15px;
}

.declarebtn:hover {
	cursor: pointer;
}

.btn-popup:hover {
	cursor: pointer;
}

div.dataTables_wrapper {
	width: 100%;
	margin: 0 auto;
}
.highlight{background-color:rgb(255,255,0)!important;}
</style>

<script>
$(function() {
    $('#myTable').on('click', 'tbody tr', function(event) {
        //alert("chck");
      $(this).addClass('highlight').siblings().removeClass('highlight');
    });

    var getHighlightRow = function() {
        return $('table > tbody > tr.highlight');
      };

    });
	$(function() {
		$("#ratingDate").datepicker({
			dateFormat : 'dd-mm-yy'
		});
	});

	function validatedata() {
		var flag = "";
		var submit = "";
		var str = '';
		var elem = document.getElementById('mliApproveOrRejectClaimCGSID').elements;
		for (var i = 0; i < elem.length; i++) {
			if (elem[i].name == "AcceptReturn") {

				if (elem[i].value == "Accept" || elem[i].value == "Return") {

					flag = true;
				} else {

					flag = false;
				}
			}
			if (flag == true) {
				if (elem[i].name == "remarks") {
					if (elem[i].value == "" || elem[i].value == null) {
						alert("Remarks is mandetory");
						//document.getElementById("requiredAcceptReturn").value="Enter Remarks";
					} else {
						submit = "1";
						//document.getElementById("requiredAcceptReturn").value="";
					}
				}
			} else {
				if (elem[i].name == "remarks") {
					if (elem[i].value == "" || elem[i].value == null) {
						elem[i].value = "Enter Remarks";
					} else {
						elem[i].value;
						submit = "1";
					}
				}
			}
		}

		if (submit == 1) {

			document.getElementById('mliApproveOrRejectClaimCGSID').action = "/SV/mliApproveOrRejectClaim.html?mli="
					+ document.getElementById("mli").value;
			document.getElementById('mliApproveOrRejectClaimCGSID').submit();
		} else {
			flag = false;
		}
	}
</script>
<style>
table.dataTable thead .sorting {
	background-image: none;
}

.cus-table thead tr th,.cus-table tbody tr td {
	vertical-align: middle;
	border-bottom: 1px solid #ddd !important;
}

.cus-table thead tr th:nth-child(5),.cus-table thead tr th:nth-child(12),.cus-table thead tr th:nth-child(22),.cus-table thead tr th:nth-child(19),.cus-table thead tr th:nth-child(25)
	{
	min-width: 150px !important;
}

.cus-table thead tr th:nth-child(26) {
	min-width: 200px !important;
}

.cus-table thead tr th:nth-child(1),.cus-table thead tr th:nth-child(3)
	{
	min-width: 50px !important;
}

.cus-table thead tr th:nth-child(9) {
	min-width: 450px !important;
}

.cus-table thead tr th:nth-child(6),.cus-table thead tr th:nth-child(16),.cus-table thead tr th:nth-child(17),.cus-table thead tr th:nth-child(18)
	{
	min-width: 180px !important;
}

.cus-table thead tr th:nth-child(2),.cus-table thead tr th:nth-child(7),.cus-table thead tr th:nth-child(8),.cus-table thead tr th:nth-child(10),.cus-table thead tr th:nth-child(11),.cus-table thead tr th:nth-child(13),.cus-table thead tr th:nth-child(14),.cus-table thead tr th:nth-child(15),.cus-table thead tr th:nth-child(20),.cus-table thead tr th:nth-child(21),.cus-table thead tr th:nth-child(23),.cus-table thead tr th:nth-child(24)
	{
	min-width: 150px !important;
}

table.dataTable.no-footer {
	border-bottom: 0 !important;
}

.dataTables_wrapper.no-footer .dataTables_scrollBody {
	border-bottom: 0 !important;
}
</style>
</head>

<body bgcolor="#E0FFFF">
	<form:form action="" method="POST" id="mliApproveOrRejectClaimCGSID"
		class="form-horizontal">
		<div class="main-section">
			<nav aria-label="breadcrumb">
			<ol class="breadcrumb cus-breadcrumb">
				<li class="breadcrumb-item"><a
					href="/SV/claimLoadgementApprovalCGTMSEMLIWise..html">claimLodgement
						Approval</a></li>
				<li class="breadcrumb-item active" aria-current="page">Claim
					Details</li>
			</ol>
			</nav>
			<div class="container-fluid">

				<div>
					<div class="tbl-details">
						<div class="col-md-12">
							<div class="d-inlineblock float-right ">
								<input type="hidden" path="mliName" id="mli" value="${MLIID}" />
								<input type="hidden" path="claimCnt" id="claimCnt"
									value="${CNT}" /> <input type="text" id="myInput"
									onkeyup="myFunction()" class="form-control cus-control"
									style="width: 200px; display: inline; height: 34px; border-radius: 4px; background-color: #ffffff;"
									placeholder="Search Data.." title="Type in a name">
								<button style="border: none !important; cursor: not-allowed;">
									<img src="images/excel.png" alt="" data-toggle="tooltip"
										title="Export To Excel">
								</button>
							</div>
						</div>
						<div class="col-md-12">
							<h5 class="notification-message">${msg}</h5>
							<div class="table-responsive">
								<c:if test="${!empty danDetailList}">
									<table id="myTable"
										class="table table-bordered order-column table-hover cus-table claim-settle mb-0">
										<thead>
											<tr>
												<th>SR.No.</th>
												<th>Member ID</th>
												<th>Claim Status.</th>
												<th>ASF Received or Not</th>
												<th>Claim Reference No.</th>
												<th>D/U updated @</th>
												<th>Claim Submission Date</th>
												<th>Unit Name</th>
												<th>CGPAN</th>
												<th>Application Remark.</th>
												<th>Return Remark.</th>
												<th>Return Remark Date</th>
												<th>TC Approved Amount(&#8377;)</th>
												<th>TC Latest O/S Amount(&#8377;)</th>
												<th>TC O/S Amount As on NPA(&#8377;)</th>
												<th>TC Recovery As on NPA(&#8377;)</th>
												<th>Subsidy Amount(&#8377;)</th>
												<th>TC o/s as on lodgment of claim
													G=MIN(B,C)-(D+E)(&#8377;)</th>
												<th>Latest O/s as on ASF paid date(post claim
													lodgment)(&#8377;)</th>

												<th>TC Eligible Amt [(50%/60%/75%) of (F/G whichever is
													less)](&#8377;)</th>
												<th>Payable as First Installment (H*75%)(&#8377;)</th>
												<th>State</th>
												<th>GST No</th>
												<th>Recommendation(s)</th>
												<th>Return Reason(s)</th>
												<th>Decision</th>
												<th>Comments</th>

											</tr>
										</thead>
										<%
											int counter = 0;
										%>
										<tbody>
											<c:forEach items="${danDetailList}" var="danDetailList">
												<tr <%if (counter % 2 == 0) {%> bgcolor="silver" <%}%>>
													<td><%=counter + 1%></td>
													<td><c:out value="${danDetailList.memberId}" /></td>
													<td><c:out value="${danDetailList.claimStatus}" /></td>
													<td><c:out value="${danDetailList.receiveASF}" /></td>
													<td><a
														href="getclaimLoadgeFormCGS.html?claimRefNo=${danDetailList.claimRefNo}&MLIID=${MLIID}&CNT=${CNT}"
														data-toggle="tooltip" target="_blank"
														title="Click Here To Open Claim form!"> <form:input
																path="claimRefNo" value="${danDetailList.claimRefNo}"
																class="clmref" id="claimRefNo" readonly="true" />
													</a></td>
													<td><a
														href="getChecklistDeclarationCGS.html?claimRefNo=${danDetailList.claimRefNo}&MLIID=${MLIID}&CNT=${CNT}&AMOUNT=${danDetailList.guarantee_Amt}"
													 target="_blank"	value="View" class="btn btn-view">View</a></td>
													 <td><c:out value="${danDetailList.dateOfClaim}" /></td>
													<td><c:out value="${danDetailList.unitName}" /></td>
													<td><c:out value="${danDetailList.cgpan}" /></td>
													<td><c:out value="${danDetailList.appRemarks}" /></td>
													<td><c:out value="${danDetailList.remarks1}" /></td>
													<td><c:out value="${danDetailList.returnDate}" /></td>
													
													<td><c:out value="${danDetailList.guarantee_Amt}" /></td>
													<td><c:out value="${danDetailList.latestOsAmt}" /></td>													<td><c:out value="${danDetailList.osAmtAsonNPA}" /></td>
													<td><c:out value="${danDetailList.recovery}" /></td>
													<td><c:out value="${danDetailList.subsidyAmt}" /></td>
													<td><c:out value="${danDetailList.osAmtClaim}" /></td>
													<td><c:out value="${danDetailList.latestOsAmt}" /></td>

													<%-- <td><c:out value="${danDetailList.claimAmt}" /></td> --%>
													<td><a href="#" target="_blank"><form:input path="claimAmountStr" value="${danDetailList.claimAmountStr}"
														    class="clmAmt" readonly="true"/> </a></td>
														<td><a href="#" target="_blank"><form:input path="firstInstallClaimStr" value="${danDetailList.firstInstallClaimStr}" 
														    class="clmAmt" readonly="true"/> </a></td>
										
													<td><c:out value="${danDetailList.state}" /></td>
													<td><c:out value="${danDetailList.gstNo}" /></td>
													<td><b> <c:if
																test="${danDetailList.final_status eq 'PASS'}">
																<font color="green" size="2"> <c:out
																		value="APPROVE" /></font>
															</c:if> <c:if test="${danDetailList.final_status eq 'FAIL'}">
																<font color="red" size="2"> <c:out value="RETURN" /></font>
															</c:if>
													</b><a
														href="getRecommandationCGS.html?claimRefNo=${danDetailList.claimRefNo}&MLIID=${MLIID}&CNT=${CNT}"
														value="View" class="btn-view"  target="_blank">View</a></td>
													<td><a
														href="getReturnreasons.html?claimRefNo=${danDetailList.claimRefNo}&MLIID=${MLIID}&CNT=${CNT}"
														value="View" class="btn-view" target="_blank">View</a></td>
													<td><form:select path="AcceptReturn"
															class="form-control cus-control" id="AcceptReturn"
															readonly="true">
															<form:option value="Select" />
															<form:option value="Accept" />
															<form:option value="Return" />

														</form:select></td>
													<td><form:input path="remarks"
															value="${danDetailList.remarks}" id="remarks"
															class="form-control cus-control" /></td>


													<%
														counter += 1;
													%>
												</tr>
											</c:forEach>
										</tbody>

										<%-- <c:if test="${empty danDetailList}">
								<thead>
												<tr>
											<th>SR.No.</th>
											<th>Member ID</th>
											<th>Claim Status.</th>
											<th>ASF Received or Not</th>
											<th>Claim Reference No.</th>
											<th>D/U updated @</th>
											<th>Unit Name</th>
											<th>CGPAN</th>
											<th>Application Remark.</th>
										    <th>Return Remark.</th>
										    <th>Return Remark Date</th>
										    <th>TC Approved Amount(&#8377;)</th>
										     <th>TC Latest O/S Amount(&#8377;)</th>
										    <th>TC O/S Amount As on NPA(&#8377;)</th>
										    <th>TC Recovery As on NPA(&#8377;)</th>
										    <th>Subsidy Amount(&#8377;)</th>
										    <th>TC o/s as on lodgment of claim G=MIN(B,C)-(D+E)(&#8377;)</th>
										    <th>Latest O/s as on ASF paid date(post claim lodgment)(&#8377;)</th>
										  
										    <th>TC Eligible Amt [(50%/60%/75%) of  (F/G whichever is less)](&#8377;)</th>
										    <th>Payable as First Installment (H*75%)(&#8377;)</th>
										    <th>State</th>
										    <th>GST No</th>
										    <th>Recommendation(s)</th>
											<th>Return Reason(s)</th>
											<th>Decision</th>
											<th>Comments</th>
											
										</tr>
									</thead>
									</tbody>
									<tr>Record Not Found</tr>
									</tbody>
								</c:if> --%>
									</table>
								</c:if>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21" align="center">
								<div class="form-group">
									<div class="col-md-12 prl-10">

										<input type="submit" value="Submit" onclick="validatedata();"
											class="btn btn-reset" />

									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</form:form>
</body>
</html>



