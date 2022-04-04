<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Portfolio Creation</title>
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
 <script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/datatables.min.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datatables.min.js"></script>
 --%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/datatables.min.css" />
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/datatables.min.js"></script>

<style type="text/css">
.claim h1, .claim h2 {
	color: #4b8abf;
	text-align: center;
	margin: 25px 0;
	font-size: 20px;
}

.hiddenField {
	display: none;
}
</style>
<script>
	$(function() {
		$("#toDate").datepicker({
			dateFormat : 'dd-mm-yy',
			maxDate : new Date()

		});
		$("#fromDate").datepicker({
			dateFormat : 'dd-mm-yy',
			maxDate : new Date()
		});
	});

	getMLiName();
	function getMLiName() {
		var selHTML = '';

		$.ajax({
			url : '${pageContext.request.contextPath}/mli-list-report.html',
			cache : false,
			crossDomain : true,
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success : function(result) {
				console.log('mlidrop', result);
				$.each(result, function(key, value) {
					selHTML += "<option value='"+value.mliId+"'>"
							+ value.mliName + "</option>";
				});
				$('#mliName').html(selHTML);
			},
			error : function(xhr, httpStatusMessage, customErrorMessage) {
				alert(xhr.responseText);
			}
		});
	}

	function dateFormate(date) {
		debugger;
		if (date == "") {
			return "";
		} else {
			var dateParts = date.split("-");
			return dateParts[2] + '-' + dateParts[1] + '-' + dateParts[0];
		}

	}

	$(document).on('click', '.searchBtn', function() {
		claimRecoveryReport();
	});

	var table;
	function claimRecoveryReport() {
		debugger;
		var mliId = $('#mliName').val();
		var pmsLoanAccount = $('#pmsLoanAccount').val();
		var loanAccount =$('#loanAccount').val();
		var misMatch = $('#misMatch').val();
		var fromDt = $('#fromDate').val();
		var toDt = $('#toDate').val();
		var userRole = $('.userRole').val();
		if(toDt=='' || toDt==null){
			 $('#toDate').focus();
			return false;
			}
		if(pmsLoanAccount=='' || pmsLoanAccount==null){
			pmsLoanAccount="N";
			}
		if(loanAccount=='' || loanAccount==null){
			loanAccount='N';
			}
		if(toDt=='' || toDt==null){
			 $('#toDate').focus();
			return false;
			}
		var dataBo=mliId+','+pmsLoanAccount+','+misMatch+','+fromDt+','+toDt+','+userRole+','+loanAccount;
		$('.searchBtn').val('Wait...');
		table = $('#claimTbl')
				.DataTable(
						{
							"lengthMenu" : [2, 10, 25, 50, 100, 200, 1000, 5000,
									10000 ],
							destroy : true,
							ajax : "${pageContext.request.contextPath}/search-recovery-report.html?dataBo="+dataBo,
							sAjaxDataProp : "",
							"columns" : [ {
								data : function(row) {
									return row.serialNo;
								},
								defaultContent : '-'
							}, {
								data : function(row) {
									return row.mliId;
								},
								defaultContent : '-'
							}, {
								data : function(row) {
									return row.mliName;
								},
								defaultContent : '-'
							}, {
								data : function(row) {
									return row.rpNumber;
								},
								defaultContent : '-'
							}, {
								data : function(row) {
									return row.uploadedDate;
								},
								defaultContent : '-'
							}, {
								data : function(row) {
									return row.totalRecord;
								},
								defaultContent : '-'
							},
							 {
								data : function(row) {
									return row.status;
								},
								defaultContent : '-'
							},
							{
								data : function(row) {
									return row.remark;
								},
								defaultContent : '-'
							},
							{
								data : function(row) {
									return row.virtualAccountNo;
								},
								defaultContent : '-'
							},
							{
								data : function(row) {
									return row.approvalDate;
								},
								defaultContent : '-'
							},
							{
								data : function(row) {
									return row.uploadedBy;
								},
								defaultContent : '-'
							},
							{
								data : function(row) {
									return row.approvedBy;
								},
								defaultContent : '-'
							},
							{
								data : function(row) {
									return row.utr;
								},
								defaultContent : '-'
							},
							{
								data : function(row) {
									return row.appropriationDate;
								},
								defaultContent : '-'
							},
							{
								data : function(row) {
									return row.ftpResponse;
								},
								defaultContent : '-'
							},
							{
								data : function(row) {
									return row.misMatch;
								},
								
								defaultContent : '-'
							},
							{
								data : function(row) {
									return row.recoveryAmt;
								},
								
								defaultContent : '-'
							}
							
							 ]
						});

		check_data_count();
	}

	function check_data_count() {
		debugger;
        var table = $('#claimTbl').DataTable();
        setTimeout(function () {
            var isEmpty = table.rows().count() === 0;
            if (!isEmpty){
        		$('.downloadExel').show();
				$('.searchBtn').val('Search');
				//alert("data");
               }else{
            	$('.downloadExel').hide();
				$('.searchBtn').val('Search');
				//alert("No");
              }
        }, 6000);
   }

	$(document).on('click', '.clearBtn', function() {
		$('select#mliName').val('0');
		$('#pmsLoanAccount').val('');
		$('#loanAccount').val('');
		$('#fromDate').val('');
		$('#toDate').val('');
	});

	function downloadExelFile() {
		var mliId = $('#mliName').val();
		var pmsLoanAccount = $('#pmsLoanAccount').val();
		var misMatch = $('#misMatch').val();
		var fromDt = $('#fromDate').val();
		var toDt = $('#toDate').val();
		var userRole = $('.userRole').val();
		var loanAccount =$('#loanAccount').val();

		if(toDt=='' || toDt==null){
			 $('#toDate').focus();
			return false;
			}
		if(pmsLoanAccount=='' || pmsLoanAccount==null){
			pmsLoanAccount="N";
			}
		if(loanAccount=='' || loanAccount==null){
			loanAccount='N';
			}
		if(toDt=='' || toDt==null){
			 $('#toDate').focus();
			return false;
		}

		var dataBo=mliId+','+pmsLoanAccount+','+misMatch+','+fromDt+','+toDt+','+userRole+','+loanAccount;
		window.location.href = '${pageContext.request.contextPath}/download-claim-recovery.html?dataBo='+dataBo;
	}

	$(document)
			.on(
					'click',
					'.cancelBtn',
					function() {
						window.location.href = '${pageContext.request.contextPath}/claim-upload.html';

					});
</script>
</head>

<body bgcolor="#E0FFFF">
	<div class="main-section">
		<div class="container-fluid">
			<!-- <div class="row"> -->
			<div>
				<div class="tbl-details">
					<div class="col-md-12">
						<div class="claim">
							<h1>Claim Recovery Report</h1>

						</div>
						<form method="GET" id="A" class="form-horizontal">

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>MLI NAME:<span style="color: red">*</span></label> <select
											id="mliName" class="form-control cus-control">
										</select>
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>PMS NUMBER</label> <input type="text" value=""
											placeholder="Type Here.." class="form-control cus-control"
											id="pmsLoanAccount" style="top-margine: 50" />
									</div>
								</div>
							</div>
							
							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>LOAN ACCOUNT</label> <input type="text" value=""
											placeholder="Type Here.." class="form-control cus-control"
											id="loanAccount" style="top-margine: 50" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>MISMATCH CASE:<span style="color: red">*</span></label>
										<select id="misMatch" class="form-control cus-control">
											<option value="N" label="NO" />
											<option value="Y" label="YES" />
										</select>
									</div>
								</div>
							</div>

<!-- 							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>From Date:<span style="color: red">*</span></label> <input
											value="" size="28" id="fromDate"
											class="form-control cus-control" style="text-align: left"
											placeholder="eg. dd-mm-yyyy" autocomplete="off" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>To Date:<span style="color: red">*</span></label> <input
											value="" size="28" id="toDate"
											class="form-control cus-control" style="text-align: left"
											placeholder="eg. dd-mm-yyyy" autocomplete="off" />
									</div>
								</div>
							</div>
 -->
							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div class="d-inlineblock mt-25">
											<input value="Search" type="button"
												class="btn btn-reset searchBtn" />
										</div>

										<div class="d-inlineblock mt-25">
											<input value="Clear" type="button"
												class="btn btn-reset clearBtn" />
										</div>
									</div>
								</div>
							</div>
							
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<!-- <label style="padding-right:20px;">FROM DATE</label> 
											<label style=""> 
											  To DATE</label> -->
											
											<div class="col-md-6" style="padding-left: 0;"><label style="  display: block; ">FROM DATE:<span style="color: red">*</span>
											<input value="" size="28" id="fromDate"
											class="form-control cus-control" style="text-align: left"
											placeholder="eg. dd-mm-yyyy" autocomplete="off" />
											
											</label></div>
											
											<div class="col-md-6" style="padding-left: 0;"><label style="  display: block; ">TO DATE<span style="color: red">*</span>
											 <input value="" size="28" id="toDate"
											class="form-control cus-control" style="text-align: left"
											placeholder="eg. dd-mm-yyyy" autocomplete="off" />
											
											</label></div>
											
											 
									</div>
								</div>
							</div> 
							

						</form>
						<div class="d-inlineblock mt-25">
							<%
								String userRoleType = (String) session.getAttribute("userRoleClaimPayment");
							%>
							<input type="hidden" class="userRole"
								value="<%=userRoleType.substring(1)%>" /> <input type="hidden"
								class="userRoleType1" value="<%=userRoleType%>" /> <input
								type="hidden" class="userName"
								value="<%=(String) session.getAttribute("userId")%>" />
						</div>

						<div class="clearfix"></div>
						<div class="d-inlineblock mt-25">
							<button style="border: none !important; cursor: allowed;">
								<img src="images/excel.png" alt="" data-toggle="tooltip"
									class="downloadExel" style="display: none"
									title="Export To Excel" onclick="return downloadExelFile()">
							</button>
						</div>
						<table id="claimTbl"
							class="table table-bordered table-hover cus-table mt-10 mb-0"
							style="margin-bottom: 45px; display: block;">
							<thead>
								<tr>
									<th>SR.No.</th>
									<th>MLI ID</th>
									<th>MLI NAME</th>
									<th>RP NUMBER</th>
									<th>UPLOADED DATE</th>
									<th>TOTAL RECORD</th>
									<th>STATUS</th>
									<th>REMARK</th>
									<th>VIRTUAL ACCOUNT NUMBER</th>
									<th>APPROVAL DATE</th>
									<th>UPLOADED BY</th>
									<th>APPROVED BY</th>
									<th>UTR NUMBER</th>
									<th>APPROPRIATION DATE</th>
									<th>FTP RESPONSE</th>
									<th>MISMATCH AMOUNT</th>
									<th>RECOVERY AMOUNT</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>

						<%-- 						<div style="margin-left: 81%">
							<%
								if (userRoleType.equalsIgnoreCase("CCHECKER")) {
							%>
							<div class="d-inlineblock mt-25">
								<input value="Cancel" type="button"
									class="btn btn-reset  cancelBtn" onclick="fuhomePage" />
							</div>

							<div class="d-inlineblock mt-25">
								<input value="Approve" type="button"
									class="btn btn-success approveBtn" style="display: block" />
							</div>

							<div class="d-inlineblock mt-25">
								<input value="Return" type="button"
									class="btn btn-danger returnBtn" style="display: block" />
							</div>
							<%
								} else if (userRoleType.equalsIgnoreCase("CMAKER")) {
							%>
							<div class="d-inlineblock mt-25">
								<input value="Cancel" type="button"
									class="btn btn-reset  cancelBtn" onclick="homePage" />
							</div>
							<div class="d-inlineblock mt-25">
								<input value="Save" type="button"
									class="btn btn-success saveBtn" style="display: block" />
							</div>
							<%
								}
							%>

						</div>
 --%>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>


