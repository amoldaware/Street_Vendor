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
	<form method="post">
		<div class="main-section">
			<div class="container-fluid">
				<div class="frm-section">
					<div class="col-md-12">
						<div class="claim">
							<h1>Claim Upload CGTMSE</h1>

						</div>
						<table class="table table-bordered table-hover cus-table mb-0">
						<div class="claim" style="text-align: right; color: blue;">Amt In Rs</div>
							<thead>
								<tr>
									<th style="border-bottom: 1px;">Financial Year</th>
									<th style="border-bottom: 1px;">Total Exposure</th>
									<th style="border-bottom: 1px;">Maximum Eligible NPA Amount (15%)</th>
									<th style="border-bottom: 1px;">Actual Claim Setteled</th>
									<th style="border-bottom: 1px;">Recovery</th>
									<th style="border-bottom: 1px;">Balance Eligible NPA Amount</th>
									<th style="border-bottom: 1px;">Claim submitted</th>
									<!-- <th rowspan="2">Financial Year</th>
									<th style="border-bottom: 1px;">Total Exposure</th>
									<th rowspan="2">Maximum Eligible Claim Amount</th>
									<th rowspan="2">Actual Claim Setteled</th>
									<th style="border-bottom: 1px;">Recovery</th>
									<th rowspan="2">Balance Eligible Claim Amount</th>
									<th style="border-bottom: 1px;">Claim submitted</th> -->
								</tr>
								<tr>
									<!-- <th>Amt In Rs</th>
									<th>Amt In Rs</th>
									<th>Amt In Rs</th> -->
								</tr>
							</thead>
							<tbody>
								<tr>
									<td id="fy">
										<!-- <select name="fy" id="fy"></select> -->
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
			
			  
				<div class="frm-section" style="margin: 15px 0;" >
					<div class="col-md-12">
						<div class="claim">
							<h2>CGTMSE</h2>
						</div>
						<div class="col-md-2 prl-4">
						     <label>Status :</label>
				  			 <select name="apprvStatus" id="apprvStatus" class="form-control cus-control" onchange="getAllCGTMSEData()">
							 <option value="">ALL</option>
							 <option value="CG_A">APPROVE</option>
							 <option value="MC_A">PENDING</option>
					         </select>
						</div>
				<table id="cgmtseTbl" class="table table-bordered table-hover cus-table mb-0 dataTable no-footer" style="margin-top:80px;">
							<thead>
								<tr>
									<th>Sr. No.</th>
									<th>Select Record</th>
									<th>MLI Name</th>
									<th>Financial Year</th>
									<th>Claim Number</th>
									<th>Claim Upload Date</th>
									<th>Records Count</th>
									<th>Uploaded OS Amount</th>
									<th>Crystallised Portfolio Amount</th>
									<th>Recovery (if Any)</th>
									<th>Eligible Claim Amount*</th>
									<th>Uploaded File Link</th>
									<th>Management Certificate</th>
									<th>Remarks</th>
									<th>Action(Approve / Return)</th>
								</tr>
							</thead>

							<tbody>
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
									<button type="button" id="resetClaimForm" class="btn btn-reset" >Exit</button>
									
								</div>
							</div>
							
						</div>
					</div>
				</div>
				
				
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">

		$(document).on('change','.cgtmseBtn',function() {
		
			var mliId=  $(this).closest('tr').find('td:eq(2)').text();
			getClaimData(mliId);
		});


		
	function getClaimData(mliId) {
		$.ajax({
			url : '${pageContext.request.contextPath}/getfinancial-data.html?mliId='+mliId,
			cache : false,
			crossDomain : true,
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success : function(result) {
				console.log("fin",result);
					 $('#fy').text(result.financeYears);
					$('#totalEx').text(result.totalExposure);
					$('#claimAc').text(result.maximumClaimAmount);
					$('#claimSubmitted').text(result.claimSubmitted);
					$('#recovery').text(result.recoveryAmount);
					$('#balanceClaimAc').text(result.balanceClaimAmount);
					$('#actualClaim').text(result.actualClaimSetteled); 
			},
			error : function(xhr, httpStatusMessage, customErrorMessage) {
				alert(xhr.responseText);
			}
		});
	}
	getAllCGTMSEData();
	function getAllCGTMSEData() {
		var apprvStatus = $("#apprvStatus").val();
		$.ajax({
			url : '${pageContext.request.contextPath}/getAll.html?apprvStatus='+apprvStatus,
			cache : false,
			crossDomain : true,
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success : function(result) {
				if(result.length>0){
					$("#cgmtseTbl tbody tr").remove(); 
	   			}
				$.each(result, function(key, value) {
					var managementCertificate= (value.managementCertificate!=null) ? value.managementCertificate:'';
					var remark=(value.remark!=null)? value.remark:"";
					
					var rowCount = $('#cgmtseTbl tr').length;
			   			var markup = '<tr>';
			   				markup += '<td>'+rowCount +'</td>';
			   				if(rowCount=='1'){
			   					markup += '<td><input class="form-check-input cgtmseBtn" type="radio" name="cgtmseBtn" checked value="'+rowCount +'"></td>';
				   				}else{
				   					markup += '<td><input class="form-check-input cgtmseBtn" type="radio" name="cgtmseBtn" value="'+rowCount +'"></td>';
					   			}
			   				markup += '<td class="mliID" style="display: none;">'+ value.mliId +'</td>';
			   				markup += '<td style="display: none;">'+ value.userId +'</td>';
			   			 	markup += '<td>'+ value.mliName +'</td>';
			   			 	markup += '<td>'+ value.financialYears +'</td>';
			   				markup += '<td>'+ value.claimNumber +'</td>';
			   				markup += '<td>'+ value.claimUploadDt +'</td>';
			   				markup += '<td>'+ value.recordCount +'</td>';
			   				markup += '<td>'+ value.uploadedOSAmmount +'</td>';
			   				markup += '<td>'+ value.crystallisedPortfolioAmount +'</td>';
			   				markup += '<td>'+ value.recovery +'</td>';
			   				markup += '<td>'+ value.eligibleClaimAmount +'</td>';
			   			 	markup += '<td><a herf="#" class="uploadFileLink">'+value.uploadId +'</a></td>';
			   			    markup += '<td><a herf="#" class="mgmtCertificate">'+managementCertificate +'</a></td>';
			   			 	markup += '<td class="remarkTd"><textarea rows="3" cols="" value="" class="remark">'+remark+'</textarea></td>';
			   	 	if(value.approvalStatus=='MC_A' || value.approvalStatus=='MM' || value.approvalStatus==null){ 
			   			 	markup += '<td><button type="button" class="btn btn-approve approveBtn" value="Approve"><i></i> Approve</button><button type="button" class="btn btn-reset returnBtn" value="Return" ><i></i> Return</button></td>'; 	
				   		} else{
				   			markup += '<td><button type="button" disabled class="btn btn-approve approveBtn" value="Approve"><i></i> Approve</button><button type="button" class="btn btn-reset returnBtn" value="Return" disabled><i></i> Return</button></td>';
					} 
			   			    markup += '</tr>';
			   			$("table#cgmtseTbl tbody").append(markup);  

			   			var MLIID =$('.mliID').text();
			   			getClaimData(MLIID);
			   });
				
			},
			error : function(xhr, httpStatusMessage, customErrorMessage) {
				alert(xhr.responseText);
			}
		});
	}

	$(document).on('click', '.approveBtn', function() {
		var mliId=  $(this).closest('tr').find('td:eq(2)').text();
	    var remark=  $(this).closest('tr').find('textarea').val();
		var approvalStatus = $(this).val();
		
		var radioCkecked=$(this).closest('tr').find('input:radio');

		 if(!radioCkecked.is(":checked")){
			 alert("Please check radio button.");
			 return false;
		 }
		
		if (remark == '') {
			alert('Please fill the Remark.');
			return false;
		}
		var jsonData = {
				"mliId" : mliId,
				"approvalStatus" : approvalStatus,
				"remark" : remark
			};
		console.log("approve",jsonData);
		var fData = new FormData();
		fData.append('objField', JSON.stringify(jsonData));

		if (confirm("File once approved cannot be edited, do you want to continue?")) 
		{
			//$(this).prop('disabled', true);
			$(this).closest('tr').find('td:eq(16) .approveBtn').prop('disabled', true);
			$(this).closest('tr').find('td:eq(16) .returnBtn').prop('disabled', true);
			checkersAprrovalStatus(fData, 'approveBtn');
		} else {
			//$(this).prop('disabled', false);
				$(this).closest('tr').find('td:eq(16) .approveBtn').prop('disabled', false);
			$(this).closest('tr').find('td:eq(16) .returnBtn').prop('disabled', false);
			return false;
		}
	});

	$(document).on('click', '.returnBtn', function() {
		var mliId=  $(this).closest('tr').find('td:eq(2)').text();
		var remark=  $(this).closest('tr').find('textarea').val();
		var approvalStatus = $(this).val();
		var radioCkecked=$(this).closest('tr').find('input:radio');

		 if(!radioCkecked.is(":checked")){
			 alert("please check radio button.");
			 return false;
		 }
		if (remark == '') {
			alert('Please fill the Remark.');
			return false;
		}
		var jsonData = {
			"mliId" : mliId,
			"approvalStatus" : approvalStatus,
			"remark" : remark
		};
		console.log("retrun",jsonData);
		var fData = new FormData();
		fData.append('objField', JSON.stringify(jsonData));

		if (confirm("Do you want to Return?")) {
			//	$(this).prop('disabled', true);
					$(this).closest('tr').find('td:eq(16) .approveBtn').prop('disabled', true);
			$(this).closest('tr').find('td:eq(16) .returnBtn').prop('disabled', true);
			checkersAprrovalStatus(fData, 'returnBtn');
		} else {
		//	$(this).prop('disabled', false);
				$(this).closest('tr').find('td:eq(16) .approveBtn').prop('disabled', false);
			$(this).closest('tr').find('td:eq(16) .returnBtn').prop('disabled', false);
			return false;
		}
	});

function checkersAprrovalStatus(fData, type) {
	$.ajax({
		url : '${pageContext.request.contextPath}/update-cgtmse-mli.html',
		type : "POST",
		processData : false, // tell jQuery not to process the data
		contentType : false, // tell jQuery not to set contentType
		cache : false,
		crossDomain : true,
		data : fData,
		success : function(fData) {
			alert(fData);
			getAllCGTMSEData();
		},
		error : function(xhr, httpStatusMessage, customErrorMessage) {
		//	$(this).prop('disabled', false);
				$(this).closest('tr').find('td:eq(16) .approveBtn').prop('disabled', false);
			   $(this).closest('tr').find('td:eq(16) .returnBtn').prop('disabled', false);
			alert(xhr.responseText);
		}
	});
}








$(document).on('click','.uploadFileLink',function() {
	var uploadId = $(this).text();
	if (uploadId != null) {
		window.location.href = '${pageContext.request.contextPath}/download-cgtmse-data.html?uploadId='+ uploadId;

		/* $.ajax({
			url : '${pageContext.request.contextPath}/download-cgtmse-data.html?uploadId='+ uploadId,
			cache : false,
			crossDomain : true,
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success : function(result) {
				console.log("fin",result);
					
			},
			error : function(xhr, httpStatusMessage, customErrorMessage) {
				//alert(xhr.responseText);
			}
		}); */
	}
});



$(document).on('click','.mgmtCertificate',function() {
	//var mliId=  $(this).closest('tr').find('td:eq(2)').text();
	var claimNumber= $(this).closest('tr').find('td:eq(6)').text();
	if (claimNumber != null) {
	window.location.href = '${pageContext.request.contextPath}/downloadFile-mgmt-certificate.html?claimNumber='+ claimNumber;

		/* $.ajax({
			url : '${pageContext.request.contextPath}/downloadFile-mgmt-certificate.html?mliId='+ mliId,
			cache : false,
			crossDomain : true,
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success : function(result) {
				console.log("fin",result);
					
			},
			error : function(xhr, httpStatusMessage, customErrorMessage) {
				//alert(xhr.responseText);
			}
		});
 */	}
});


</script>

</html>
