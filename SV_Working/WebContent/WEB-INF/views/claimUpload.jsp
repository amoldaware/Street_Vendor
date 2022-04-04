<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/datatables.min.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datatables.min.js"></script>

<style type="text/css">
#body-section {
	background-color: white !important;
	paing-bottom: 0px !important;
	height: auto !important;
}

.footer1 {
	margin: 0 0 0 0 !important;
}

.btn.btn-reset {
	paing: 2px 9px;
	border-radius: 4px;
}

.btn.btn-reset:hover, .btn.btn-reset:focus {
	paing: 2px 9px;
	border-radius: 4px;
}

.btn.btn-approve {
	transition: all 0.2s;
	background-color: #47b900;
	color: white;
	paing: 2px 9px;
	border-radius: 4px;
}

.btn.btn-approve:hover {
	color: white;
	box-shadow: 1px 3px 3px #4a4a4a9e;
}

.claim h1, .claim h2 {
	color: #4b8abf;
	text-align: center;
	margin: 10px 0;
	font-size: 20px;
}

table.dataTable.no-footer {
	border-bottom: 0px;
}

.tbl-checker tr td {
	text-align: center;
}

.tbl-checker tbody tr td textarea {
	width: 100%;
	border: 1px solid #cacaca
}

.tbl-checker thead tr th:nth-child(1) {
	width: 20%
}

.tbl-checker thead tr th:nth-child(2) {
	width: 25%
}

.tbl-checker thead tr th:nth-child(3) {
	width: 40%
}

.tbl-checker thead tr th:nth-child(4) {
	width: 15%
}

.custom-file, .custom-file1 {
	position: relative;
	display: inline-block;
	width: auto;
	height: calc(1.5em + .75rem + 2px);
}

.custom-file-input, .custom-file-input1 {
	position: relative;
	z-index: 2;
	width: 100%;
	cursor: pointer;
	height: calc(1.5em + .75rem + 2px);
	margin: 0;
	opacity: 0;
}

.frm-box>.form-group input {
	background-color: rgb(245, 245, 245);
	font-family: 'Open Sans', sans-serif;
	font-size: 14px;
	border: none;
	box-shadow: 1px 3px 3px rgba(8, 198, 255, 0.3);
}

.custom-file-label.custom-file-label1 {
	position: absolute;
	top: 0;
	right: 0;
	left: 0;
	z-index: 1;
	text-align: left;
	height: calc(1.5em + .75rem + 2px);
	paing: .375rem .55rem .375rem 3rem;
	font-weight: 400;
	line-height: 1.5;
	color: rgb(176, 176, 176);
	background-color: #fff;
	border: 1px solid #ced4da;
	border-radius: 0.2rem !important;
	font-size: 14px;
}

.custom-file-label::after, .custom-file-label1::after {
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	z-index: 3;
	display: block;
	height: calc(1.5em + .75rem);
	paing: .375rem 1rem;
	text-align: center;
	line-height: 1.5;
	color: #fff;
	content: "Upload File";
	background-color: rgb(193, 193, 193);
	border-left: inherit;
	border-radius: 0.2rem;
	width: 114px;
}

.dataTables_length label select, .dataTables_wrapper .dataTables_filter input,
	.dataTables_wrapper .dataTables_filter input:focus {
	border: 1px solid #b3b3b3;
}
</style>

</head>
<body>
	<form method="post" enctype="multipart/form-data">
		<div class="main-section">
			<div class="container-fluid">
				<div class="frm-section">
					<div class="col-md-12">
						<div class="claim">
							<h1>Claim Upload</h1>

						</div>
						<div class="claim errorMandantApprove" style="display: none;text-align:center;">
							<h4 style="color:red">*Please submit the Bank Mandate to enable the Claim Module.</h4>

						</div>
						<table class="table table-bordered table-hover cus-table mb-0">
						<div class="claim" style="text-align: right; color: blue;">Amt In Rs</div>
							<thead>
								<tr>
								
								    <th style="border-bottom: 1px;">Financial Year</th>
									<th style="border-bottom: 1px;">Total Exposure</th>
									<th style="border-bottom: 1px;">Maximum Eligible NPA Amount (15%)</th>
									<th style="border-bottom: 1px;">Actual Claim Settled</th>
									<th style="border-bottom: 1px;">Recovery</th>
									<th style="border-bottom: 1px;">Balance Eligible NPA Amount</th>
									<th style="border-bottom: 1px;">Claim submitted</th>
								
								
									<!-- <th rowspan="2">Financial Year</th>
									<th style="border-bottom: 1px;">Total Exposure</th>
									<th rowspan="2">Maximum Eligible Claim Amount</th>
									<th rowspan="2">Actual Claim Settled</th>
									<th style="border-bottom: 1px;">Recovery</th>
									<th rowspan="2">Balance Eligible Claim Amount</th>
									<th style="border-bottom: 1px;">Claim submitted</th>
 -->								</tr>
								<tr>
									<!-- <th>Amt In Rs</th>
									<th>Amt In Rs</th>
									<th>Amt In Rs</th>
									<th>Amt In Rs</th>
									<th>Amt In Rs</th>
									<th>Amt In Rs</th> -->
									
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<select name="fy" id="fy"></select>
									</td>
									<td id="totalEx"></td>
									<td id="claimAc"></td>
									<td id="actualClaim"></td>
									<td id="recovery"></td>
									<td id="balanceClaimAc"></td>
									<td id="claimSubmitted"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			<div id="mandantApproval" style="display: none">
			  
				<div class="frm-section" style="margin: 15px 0;" id="makerView">
					<div class="col-md-12">
						<div class="claim">
							<h2>Maker</h2>
							
						</div>
						<div class="info">
							<h5 style="color:red;text-align:right">Note - Claim file can be uploaded once in a quarter.</h5>
							
						</div>
						<div class="claim">
							<h5 style="color:red;text-align:center" id="fileStatusMsg"></h5>
						</div>
						
						<div class="col-md-6 prl-10">
							<div class="form-group row" id="mkrUpload">
								<label class="col-md-4">Lodge a Fresh Claim </label>
								<div class="custom-file">
								<input  type="file" id="file" class="form-control-file">
									<!-- <input type="file" id="file"
										class="custom-file-input custom-file-input1" id="customFile"> -->
									<!-- <label class="custom-file-label custom-file-label1"
										for="customFile">No file chosen</label> -->
								</div>
								<button type="button" class="btn btn-success btn-sm"
									id="uploadMackerBtn">
									<i></i> Upload
								</button>
								
								<div class="form-group row">
								     <label class="col-md-4"> &nbsp;&nbsp; &nbsp;Download Excel Format </label>
								 <a href="${pageContext.request.contextPath}/Download/Claim_Upload_Excel_Format.xlsx" target="_blank" download>&nbsp;Click here to download
								 claim file format</a>
								</div>
							</div>
						</div>
						
						<!-- <div class="col-md-6 prl-10">
						<button type="button" class="btn btn-reset" id="refreshBtn">Refresh</button>
						</div> -->
					 
						
						<div class="col-md-6 prl-10">
							 <table id="" class="table table-bordered table-hover cus-table mb-0 dataTable no-footer" style="
    margin: 34px 0px 0px 10px;">
							<thead>
								<tr>
									<th>Upload Id</th>
									<th>Total Uploaded Records</th>
									<th>File Upload Status</th>
									<th>Success Count </th>
									<th>Unsuccess Count</th>
									
									
								</tr>
							</thead>
							<tbody>
							
								 <tr>
									<td id="uploadId"></td>
									<td><a href="#" id="totalRecords"></a></td>
									<td id="fileStatus"></td>
									<td><a href="#" id="successCount"></a></td> 
									<td><a href="#" id="unsuccesscount"></a></td>
									
									
								</tr>
							</tbody>
							</table>		
						</div>
						
						<!-- <div class="col-md-6 prl-10">
						<button type="button" class="btn btn-success btn-sm" id="refreshBtn" style="margin:-80px 0px 70px 565px;"><i></i> Refresh</button>
						</div> -->
							<div class="col-md-6 prl-10">
						<button type="button" class="btn btn-success btn-sm" id="refreshBtn" style="margin:-100px 0px 79px 1195px; display: none"><i></i> Refresh</button>
						</div>
						
						<table id=""
							class="table table-bordered table-hover cus-table mb-0 dataTable no-footer" style="margin-top:80px;">
							<thead>
								<tr>
									<!-- <th>Claim Details</th> -->
									<th>Claim Number</th>
									<th>MLI Name</th>
									<th>Claim Submission Date</th>
									<th>Records Count</th>
									<th>Amount</th>
									<th>Eligibility Check</th>
									<th>Status</th>
									<th>Remark</th>
									<th>Action</th>
									
								</tr>
							</thead>
							<tbody>
								<tr>
									<!-- <td style="width: 10px;"><i
										style="font-size: 26px; color: red" id="claim-Number"></i></td> -->
									<td id="claimNo"></td>
									<td id="mliname"></td>
									<td id="claimDate"></td>
									<td id="recordCount"></td>
									<td id="amount"></td>
									<td id="elgibilityCheck"></td>
									<td id="apStatus"></td>
									<td id="apRemark"></td>
									<td id="currentQuater" style="display: none"></td>
									<td>
										<button type="button" class="btn btn-reset"
											id="freshClaimSaveBtn" style="display: none">
											<i></i> Submit to MLI Checker
										</button>

									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

				<div id="claim-no-div" style="display: none">
					<div class="frm-section">
						<div class="col-md-12">
							<div class="claim">
								<h1>Claim Account Details</h1>
							</div>
							<div class="row justify-content-between" style="margin-bottom: 10px;">
								<div class="col-md-4" style="float: right; text-align: right;">
									<button type="button" class="btn btn-reset" id="excelDwPms">
										Export to Excel</button>
									<button type="button" id="resetClaimForm" class="btn btn-reset">Exit</button>

								</div>
							</div>
							<table id="claimTbl"
								class="table table-bordered table-hover cus-table mb-0" style="width: 100%;">
								<thead>
									<tr>

										<th>Sr.No</th>
										<th>PMS</th>
										<th>Loan Account</th>
										<th>Borrower Name</th>
										<th>Sanction Date</th>
										<th>Sanction Amount</th>
										<th>NPA Date</th>
										<th>Claim_Amt</th>

									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<div class="frm-section" id="checkerhide">
					<div class="col-md-12">
						<div class="claim">
							<h2>Checker</h2>
						</div>

						<table id=""
							class="table table-bordered table-hover cus-table mb-0 tbl-checker">
							<thead>
								<tr>

									<th>Management Certificate</th>
									<th>Signed Management Certificate</th>
									<th>Remark</th>
									<th>Action</th>

								</tr>
							</thead>
							<tbody>
								<tr>

									<td><a href="#" id="managedownloadPdf"
										class="btn btn-reset">Generate</a></td>
									<td>
										<div class="custom-file">
										<input  type="file" id="files" class="form-control-file">
											<!-- <input type="file" id="files"
												class="custom-file-input custom-file-input1" id="customFile"> -->
											<!-- <label class="custom-file-label custom-file-label1"
												for="customFile">No file chosen</label> -->
										</div> <!--    <button type="button" class="btn btn-success btn-sm">Upload</button> -->
									</td>
									<td><textarea rows="3" cols="" id="remarks"></textarea></td>
									<td>
										<button type="button" class="btn btn-approve" value="Approve"
											id="approveBtn" disabled="disabled">
											<i></i> Approve
										</button>
										<button type="button" class="btn btn-reset" value="Return"
											id="returnBtn" disabled="disabled">
											<i></i> Return
										</button>
										
								     
										<!-- <A href="bankMandateForm.html">Bank Mandate Form</A>
										<A href="bankMandateFormMli.html">Bank Mandate Form MLI</A>
										<A href="claim-upload-cgtmse.html">CGTMSC Approvale</A> -->
									</td>

								</tr>
							</tbody>
						</table>


					</div>
				</div>
				</div>
				<div class="frm-section" style="color:red;">
					<ul>
						
						<li>If eligibility check failed, please re-check your file
							referring below conditions and re-upload the file:
							<ul>
								<li>First Loss Default (Up to 5%): 100%</li>
								<li>Second Loss (beyond 5% up to 15%): 75% of default
									portfolio</li>
								<li>Maximum guarantee coverage will be 15% of the year
									portfolio</li>
							</ul>
						</li>
						<li>If file rejected by Checker, please re-upload your file
							as per given remarks.</li>
					</ul>
				</div>
			</div>
		</div>
	</form>
</body>

<script type="text/javascript">


	getMandantFormAprroveStatus();
	getUploadedFileProcessStatus();
	getFinancialYears();

	function getFinancialYears(){
		var selHTML = '';
		
		$.ajax({
			url : '${pageContext.request.contextPath}/financial-years.html',
			cache : false,
			crossDomain: true,
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success : function(result) {
				
		   			  $.each(result, function(key, value) {
		   						selHTML+= "<option value='"+value.id+"'>"+value.financeYears+"</option>";
		   			  });
		   					$('tbody #fy').html(selHTML);   
				},
				error : function(xhr, httpStatusMessage, customErrorMessage) {
					alert(xhr.responseText);
				}
			});
		}

	
	getClaimData();
	function getClaimData() {
		$.ajax({
			url : 'upload-details.html',
			cache : false,
			crossDomain : true,
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success : function(result) {
					if(result.roleType.toUpperCase()=='NMAKER'){
						$('#checkerhide').hide();
					}
					if(result.roleType.toUpperCase()=='NCHECKER'){
						$('#mkrUpload').hide();
						$('#freshClaimSaveBtn').prop('disabled', true);
						
					}
					//$('#fy').text(result.financeYears);
					$('#totalEx').text(result.totalExposure);
					$('#claimAc').text(result.maximumClaimAmount);
					$('#claimSubmitted').text(result.claimSubmitted);
					$('#recovery').text(result.recoveryAmount);
					$('#balanceClaimAc').text(result.balanceClaimAmount);
					$('#actualClaim').text(result.actualClaimSetteled);
					var totalExp= $('#totalEx').text();
					if(totalExp==''){
						$('#mkrUpload').hide();
					}
			},
			error : function(xhr, httpStatusMessage, customErrorMessage) {
				alert(xhr.responseText);
			}
		});
	}

		
	
	$(document).on('click','#uploadMackerBtn',function() {
		if (confirm("Do you want to Save?")) {

			var filenameLen = $('#file')[0].files.length;
			if (filenameLen == 0) {
				alert("No file selected");
				return false
			}
			var filename = $('#file')[0].files[0].name;
			var fileExtType = $('#file')[0].files[0].type;

			if (fileExtType != '') {
				switch (fileExtType) {
				case 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet':
					break;
				case 'application/vnd.ms-excel':
					break;
				default:
					alert(fileExtType
							+ " : Unsupported file type.");
					return false
				}
			}
			var elgibilityCheck =$('#elgibilityCheck').text();
			var fy = $('#fy').text();
			var currentQuater = $('#currentQuater').text();
			var obj={
					"elgibilityCheck":elgibilityCheck,
					"financialYears":fy,
					"currentQuater":currentQuater
					};
			
			var fData = new FormData();
			fData.append('uploadfile', $('#file')[0].files[0]);
			fData.append('obj', JSON.stringify(obj));
			$('#file').prop('disabled', true);
			$('#uploadMackerBtn').prop('disabled', true);
			$('#uploadMackerBtn').children('i').addClass('fa fa-refresh fa-spin');
			$('#fileStatusMsg').text('');

			$.ajax({
				type : "POST",
				url : "uploadFile.html",
				processData : false, // tell jQuery not to process the data
				contentType : false, // tell jQuery not to set contentType
				cache : false,
				crossDomain : true,
				data : fData,
				success : function(data) {
					alert(data);
					$('#fileStatusMsg').text('Your file is under process,may be It will take more time.');
					$('#file').val('');
					$('#file').prop('disabled', true);
					$('#uploadMackerBtn').prop('disabled', true);
					$('#uploadMackerBtn').children('i').removeClass('fa fa-refresh fa-spin');
					getUploadedFileProcessStatus();
					getSuccessErrorUploadedFileCount();
				},
				error : function(xhr, httpStatusMessage,customErrorMessage) {
					alert(xhr.responseText);
					$('#uploadMackerBtn').children('i').removeClass('fa fa-refresh fa-spin');
					$('#uploadMackerBtn').prop('disabled', false);
					$('#file').prop('disabled', false);
				}
			});

	} else {
		return false;
	}
});

	$(document).on('click','#refreshBtn',function() {
		$('#refreshBtn').children('i').addClass('fa fa-refresh fa-spin');
		getUploadedRecordStatusBar();
	});

	
	var jobFile ;
	getSuccessErrorUploadedFileCount();
	function getSuccessErrorUploadedFileCount() {
		
		$.ajax({
			url : '${pageContext.request.contextPath}/success-error-count-uploadfile.html',
			cache : false,
			crossDomain : true,
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success : function(result) {
				$('#uploadId').text(result.uploadId);
				$('#successCount').text(result.successCount);
				$('#unsuccesscount').text(result.unSuccessCount);
				if(result.status=='I'){
					$('#fileStatus').text('Process In-Progress');
					$('#fileStatusMsg').text('Your file is under process,may be It will take more time.');
					$('#file').prop('disabled', true);
					$('#uploadMackerBtn').prop('disabled', true);
				}
				
				$('#totalRecords').text(result.cout);
				$('#refreshBtn').show();
				jobFile = setInterval(getUploadedRecordStatusBar,5000); 
			},
			error : function(xhr, httpStatusMessage, customErrorMessage) {
				alert(xhr.responseText);
			}
		});
	}

function getUploadedRecordStatusBar() {
		
		$.ajax({
			url : '${pageContext.request.contextPath}/getUploadedRecordStatusBar.html',
			cache : false,
			crossDomain : true,
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success : function(result) {
				console.log("job call");
				if(result.status=="C"){
					
					$('#uploadId').text(result.uploadId);
					$('#successCount').text(result.successCount);
					$('#unsuccesscount').text(result.unSuccessCount);
					if(result.status=='C'){
						$('#fileStatus').text('Process Completed');
						$('#fileStatusMsg').text('Your file successfully processed');
						$('#uploadMackerBtn').prop('disabled', false);
						$('#file').prop('disabled', false);
					}
					$('#totalRecords').text(result.cout); 
						getLodgeFreshClaim();
						console.log("getUploadedRecordStatusBars");
					  clearInterval(jobFile);
				}
				$('#refreshBtn').children('i').removeClass('fa fa-refresh fa-spin');
			},
			error : function(xhr, httpStatusMessage, customErrorMessage) {
				alert(xhr.responseText);
				$('#refreshBtn').children('i').removeClass('fa fa-refresh fa-spin');
			}
		});
	}
	
	function getLodgeFreshClaim() {
		$.ajax({
			url : '${pageContext.request.contextPath}/lodge-fresh-Claim.html',
			cache : false,
			crossDomain : true,
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success : function(result) {
				console.log("getLodgeFreshClaim",result);
				if (result.claimNumber != null) {
					console.log("result.claimNumber",result.claimNumber);
					$("#claim-Number").addClass("fa fa-plus-circle");
					$('#claimNo').text(result.claimNumber);
					$('#mliname').text(result.mliName);
					$('#claimDate').text(result.freshClaimDate);
					$('#recordCount').text(result.totalRecordCount);
					$('#amount').text(result.totalAmount);
					$('#elgibilityCheck').text(result.elgibilityCheck);
					$('#apStatus').text(result.approvalStatus);
					$('#apRemark').text(result.remark);
					$('#currentQuater').text(result.currentQuater);
					$('#freshClaimSaveBtn').show();

					 if(result.approvalCode=='MM'){
						 $('#returnBtn').prop('disabled', false);
							$('#approveBtn').prop('disabled', false);
							$('#freshClaimSaveBtn').prop('disabled', false);
					 }
					
					if(result.elgibilityCheck.toLowerCase() =='Pass'.toLowerCase()) {
						$('#freshClaimSaveBtn').attr('disabled', false);
					}else{
						//alert("hello");
						$('#freshClaimSaveBtn').attr('disabled', true);
					}
					if (result.disableSaveBtn=='yes') {
						$('#freshClaimSaveBtn').attr('disabled', true);
					}
					if($('#apRemark').text()!=''){
						$('#freshClaimSaveBtn').text('Submit to CGTMSE');
					}

					if(result.approvalStatus==null || result.approvalStatus==''){
						$('#returnBtn').prop('disabled', true);
						$('#approveBtn').prop('disabled', true);
						$('#freshClaimSaveBtn').prop('disabled', false);
					}
					if(result.approvalCode==null || result.approvalCode==''){ //added dt 12-02-021 uk
						$('#freshClaimSaveBtn').text('Submit to MLI Checker');
					}else if(result.approvalCode=='MC_R'){
						$('#returnBtn').prop('disabled', true);
						$('#approveBtn').prop('disabled', true);
						$('#freshClaimSaveBtn').prop('disabled', true);
						$('#freshClaimSaveBtn').text('Submit to MLI Checker');
					}else if(result.approvalCode=='CG_R'){
						$('#returnBtn').prop('disabled', true);
						$('#approveBtn').prop('disabled', true);
						$('#freshClaimSaveBtn').prop('disabled', true);
						$('#freshClaimSaveBtn').text('Submit to MLI Checker');
						}else if(result.approvalCode=='MC_A' || result.approvalCode=='CG_A'){
							$('#returnBtn').prop('disabled', true);
							$('#approveBtn').prop('disabled', true);
							$('#freshClaimSaveBtn').prop('disabled', true);
							$('#freshClaimSaveBtn').text('Submit to CGTMSE');
						}
					//getClaimNumberDetails();
				}
			},
			error : function(xhr, httpStatusMessage, customErrorMessage) {
				alert(xhr.responseText);
			}
		});
	}

	$(document).on('click','#freshClaimSaveBtn',function() {
			
		if (confirm("Do you want submit to MLI checker?")) {

			var claimNo = $('#claimNo').text();
			var mliName = $('#mliname').text();
			var claimDt = $('#claimDate').text();
			var recordCount = $('#recordCount').text();
			var totalAmount = $('#amount').text();
			var elgibilityCheck = $('#elgibilityCheck').text();
			var financialYears = $('#fy').text();
			
			var jsonData = {
				"claimNumber" : claimNo,
				"mliName" : mliName,
				"freshClaimDate" : claimDt,
				"totalRecordCount" : recordCount,
				"totalAmount" : totalAmount,
				"elgibilityCheck" : elgibilityCheck,
				"financialYears":financialYears
			};

			var fData = new FormData();
			fData.append('objField', JSON.stringify(jsonData));

			$('#freshClaimSaveBtn').children('i').addClass(
					'fa fa-refresh fa-spin');

			$.ajax({
				type : 'POST',
				url : 'save-lodge-fresh-Claim.html',
				processData : false, // tell jQuery not to process the data
				contentType : false, // tell jQuery not to set contentType
				cache : false,
				crossDomain : true,
				data : fData,
				success : function(data) {
					getLodgeFreshClaim();
					alert(data);
					$('#freshClaimSaveBtn').children('i').removeClass('fa fa-refresh fa-spin');
				},
				error : function(xhr, httpStatusMessage,customErrorMessage) {
					alert(xhr.responseText);
					$('#freshClaimSaveBtn').children('i').removeClass('fa fa-refresh fa-spin');
				}
			});

		} else {
			return false;
		}
	});

	$(document).on('click', '#approveBtn', function() {
		var claimNo = $('#claimNo').text();
		var remark = $('#remarks').val();
		var approvalStatus = $(this).val();
		var financialYr = $('#fy').text();
		
		
		if (claimNo == '') {
			alert("Claim number is not generated for this user.");
			return false
		}

		var filenameLen = $('#files')[0].files.length;
		if (filenameLen == 0) {
			alert("No file selected");
			return false;
		}
		var filename = $('#files')[0].files[0].name;
		var fileExtType = $('#files')[0].files[0].type;

		if (fileExtType != '') {
			switch (fileExtType) {
			case 'application/pdf':
				break;
			default:
				alert(fileExtType + " : Unsupported file type.");
				return false
			}
		}

		if (remark == '') {
			alert('Please fill the Remark.');
			return false;
		}
		var jsonData = {
			"claimNumber" : claimNo,
			"mgmtCertificatefileName" : filename,
			"approvalStatus" : approvalStatus,
			"remark" : remark,
			"financialYears":financialYr
		};

		var fData = new FormData();
		fData.append('uploadfile', $('#files')[0].files[0]);
		fData.append('objField', JSON.stringify(jsonData));

		if (confirm("File once approved cannot be edited, do you want to continue?")) {
			$('#approveBtn').children('i').addClass('fa fa-refresh fa-spin');
			$('#returnBtn').prop('disabled', true);
			$('#approveBtn').prop('disabled', true);
			checkersAprrovalStatus(fData, 'approveBtn');
		} else {
			$('#returnBtn').prop('disabled', false);
			$('#approveBtn').prop('disabled', false);
			return false;
		}
	});

	$(document).on('click', '#returnBtn', function() {

		var claimNo = $('#claimNo').text();
		var remark = $('#remarks').val();
		var approvalStatus = $(this).val();
		var financialYr = $('#fy').text();
		
		
		
		if (claimNo == '') {
			alert("Claim number is not generated for this user.");
			return false
		}
		if (remark == '') {
			alert('Please fill the Remark.');
			return false;
		}

		var filenameLen = $('#files')[0].files.length;
		if (filenameLen == 0) {
			alert("No file selected");
			return false
		}
		var filename = $('#files')[0].files[0].name;
		var fileExtType = $('#files')[0].files[0].type;

		if (fileExtType != '') {
			switch (fileExtType) {
			case 'application/pdf':
				break;
			default:
				alert(fileExtType + " : Unsupported file type.");
				return false;
			}
		}


		var jsonData = {
			"claimNumber" : claimNo,
			"mgmtCertificatefileName" : filename,
			"approvalStatus" : approvalStatus,
			"remark" : remark,
			"financialYears":financialYr
		};

		var fData = new FormData();
		fData.append('uploadfile', $('#files')[0].files[0]);
		fData.append('objField', JSON.stringify(jsonData));

		if (confirm("Do you want to Return.")) {
			$('#returnBtn').children('i').addClass('fa fa-refresh fa-spin');
			$('#returnBtn').prop('disabled', true);
			$('#approveBtn').prop('disabled', true);
			checkersAprrovalStatus(fData, 'returnBtn');
		} else {
			$('#returnBtn').prop('disabled', false);
			$('#approveBtn').prop('disabled', false);
			return false;
		}
	});

function checkersAprrovalStatus(fData, type) {
	$.ajax({
		url : 'save-lodge-fresh-Claim-checker.html',
		type : "POST",
		processData : false, // tell jQuery not to process the data
		contentType : false, // tell jQuery not to set contentType
		cache : false,
		crossDomain : true,
		data : fData,
		success : function(fData) {
			alert(fData);
			getLodgeFreshClaim();
			if (type == 'approveBtn') {
				$('#approveBtn').children('i').removeClass(
						'fa fa-refresh fa-spin');
			} else {
				$('#returnBtn').children('i').removeClass(
						'fa fa-refresh fa-spin');
			}
		},
		error : function(xhr, httpStatusMessage, customErrorMessage) {
			alert(xhr.responseText);
			if (type == 'approveBtn') {
				$('#approveBtn').children('i').removeClass('fa fa-refresh fa-spin');
			} else {
				$('#returnBtn').children('i').removeClass('fa fa-refresh fa-spin');
			}
			$('#returnBtn').prop('disabled', false);
			$('#approveBtn').prop('disabled', false);
		}
	});
}

	var clrChange = true;
	$(document).on('click', '#claim-Number', function() {
		$("#claim-no-div").toggle();
		if (clrChange) {
			$("i").css("color", "green");
			clrChange = false;
		} else {
			$("i").css("color", "red");
			clrChange = true;
		}

	});
	$(document).on('click', '#resetClaimForm', function() {
		$("#claim-no-div").toggle();
		

	});
	
	
	var table;
	function getClaimNumberDetails() {
		var claimNo = $('#claimNo').text();
		if (claimNo != null) {
			table = $('#claimTbl').DataTable( {
				"lengthMenu":[10,25,50,100,200,1000,5000,10000],
				destroy:true,
				ajax : "${pageContext.request.contextPath}/claimPmsDetail.html?claimNo="+claimNo,
				sAjaxDataProp : "",
		        "columns": [
		        	{
		            		data : function(row) {
								return row.sno; 												
							},
							defaultContent : '-'
		            },
		            { 
		            		data : function(row) {
								return row.pms;  												
							},
							defaultContent : '-'
		            },
		            { 
		            	data : function(row) {
							return row.loanAccount; 												
						},
						defaultContent : '-'
		            },
		            { 
		            		data : function(row) {
								return row.borrowerName;  												
							},
							defaultContent : '-'
		            },
		            { 
	            		data : function(row) {
							return row.sanctionDate;  												
						},
						defaultContent : '-'
	           		},
	           		{ 
		            	data : function(row) {
							return row.sanctionAmt; 												
						},
						defaultContent : '-'
		            },
		            { 
		            		data : function(row) {
								return row.claimDate;  												
							},
							defaultContent : '-'
		            },
		            { 
	            		data : function(row) {
							return row.claimAmt;  												
						},
						defaultContent : '-'
	           		}
		        ]
		    } );
		}
	}
	
	$(document).on('click','#excelDwPms',function() {
						var claimNo = $('#claimNo').text();
						if (claimNo != null) {
							window.location.href = '${pageContext.request.contextPath}/excelPmsDetail.html?claimNo='
									+ claimNo;
						}
					});

	$(document).on('click','#managedownloadPdf',function() {
						var claimNo = $('#claimNo').text();
						if (claimNo != null) {
							$('#approveBtn').attr('disabled', false);
							$('#returnBtn').attr('disabled', false);
							window.location.href = '${pageContext.request.contextPath}/downloadMangementCertifi.html?claimNo='
									+ claimNo;
						}

					});

	$(document).on('click','#totalRecords',function() {
		var uploadId = $('#uploadId').text();
		var totalRecords = $(this).text();
		if (uploadId != null && totalRecords!='0') {
			window.location.href = '${pageContext.request.contextPath}/download-error-uploadedfile-count.html?uploadId='+ uploadId+'&flag=T';
		}

	});


	$(document).on('click','#unsuccesscount',function() {
		var uploadId = $('#uploadId').text();
		var unSuccessCount = $(this).text();
		if (uploadId != null && unSuccessCount!='0') {
			window.location.href = '${pageContext.request.contextPath}/download-error-uploadedfile-count.html?uploadId='+ uploadId+'&flag=E';
		}

	});

	$(document).on('click','#successCount',function() {
		var uploadId = $('#uploadId').text();
		var successCount = $(this).text();
		if (uploadId != null && successCount!='0') {
			window.location.href = '${pageContext.request.contextPath}/download-error-uploadedfile-count.html?uploadId='+ uploadId+'&flag=S';
		}

	});
	


	
	function getMandantFormAprroveStatus() {
		$.ajax({
			url : '${pageContext.request.contextPath}/check-bank-mandate-approve.html',
			cache : false,
			crossDomain : true,
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success : function(result) {
				console.log("log",result);

				if(result.approveStatus=='Approved'){
					$('#mandantApproval').show();
				}else if(result.approveStatus=='NotApproved'){
					$('.errorMandantApprove').show();
				} 

				
			},
			error : function(xhr, httpStatusMessage, customErrorMessage) {
				alert(xhr.responseText);
			}
		});
	}



	function getUploadedFileProcessStatus() {
		$.ajax({
			url : '${pageContext.request.contextPath}/file-upload-process-status.html',
			cache : false,
			crossDomain : true,
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success : function(result) {
				console.log("result dis",result);
				if(result=='1'){
					$('#file').attr('disabled', true);
					$('#uploadMackerBtn').attr('disabled', true);
				}else if(result=='0'){
					$('#file').attr('disabled', false);
					$('#uploadMackerBtn').attr('disabled', false);
				}
			},
			error : function(xhr, httpStatusMessage, customErrorMessage) {
				alert(xhr.responseText);
			}
		});
	}
</script>

</html>
