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
	<form  enctype="multipart/form-data">
		<div class="main-section">
			<div class="container-fluid">
				<div class="frm-section">
					<div class="col-md-12">
						<div class="claim">
							<h1>Recovery Upload</h1>

				</div>
				</div>
				</div>
			<div id="makerApproval" ><!-- style="display: none" -->
			  
				<div class="frm-section" style="margin: 15px 0;" id="makerView">
					<div class="col-md-12">
						<div class="claim">
							<h2 id="roleheader"><%=session.getAttribute("uRole").toString().substring(1)%></h2>
					
						</div>
				
						<!-- <div class="info">
							<h5 style="color:red;text-align:right">Note - Claim file can be uploaded once in a quarter.</h5>
							
						</div> -->
						<div class="claim">
							<h5 style="color:red;text-align:center" id="fileStatusMsg"></h5>
						</div>
						
						<div class="col-md-6 prl-10">
							<div class="form-group row" id="mkrUpload">
								<label class="col-md-4">Recovery File Upload</label>
								<div class="custom-file">
								<input  type="file" id="file" class="form-control-file">
									<!-- <input type="file" id="file"
										class="custom-file-input custom-file-input1" id="customFile"> -->
									<!-- <label class="custom-file-label custom-file-label1"
										for="customFile">No file chosen</label> -->
								</div>
								<button type="button" class="btn btn-success btn-sm"
									id="uploadRecoveryMackerBtn">
									<i></i> Upload
								</button>
								
								<div class="form-group row">
								     <label class="col-md-4"> &nbsp;&nbsp; &nbsp;Download Excel Format </label>
								 <a href="${pageContext.request.contextPath}/Download/Recovery_Upload_Format.xlsx" target="_blank" download>&nbsp;Click here to download
								 Recovery file format</a>
								</div>
							</div>
						</div>
						
						<!-- <div class="col-md-6 prl-10">
						<button type="button" class="btn btn-reset" id="refreshBtn">Refresh</button>
						</div> -->
					 
						
						<div class="col-md-6 prl-10">
							 <table id="" class="table table-bordered table-hover cus-table mb-0 dataTable no-footer" style="margin: 34px 0px 0px 10px;">
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
						<div class="clearfix"></div>
						<div class="d-inlineblock mt-25">
							<button type="button" style="border: none !important; cursor: allowed;">
								<img src="images/excel.png" alt="" data-toggle="tooltip"
									class="downloadExel" style="display: none"
									title="Export To Excel" onclick="return downloadExelFile()">
							</button>
						</div>
						<table id="utrTbl"
							class="table table-bordered table-hover cus-table mb-0 dataTable no-footer" style="margin-top:60px;">
							<thead>
								<tr>
								   <!--  <th>Sr.No</th> -->
									<!-- <th>MLI Name</th> -->
									<th>RP Number</th>
									<th>Upload Date</th>
									<th>Total Records</th>
									<th>Recovery Amount</th>
									<th>Virtual Account No.</th>
									<th>Approval Date</th>
									<th>Status</th>
								    <th>Remark</th>
								</tr>
							</thead>
							<tbody>
							<tr>
		   				<td id="recmliName" style="display: none;"></td>
		   			    <td id="recUploadId" style="display: none;"></td>
		   			    <td id="repNo"></td>
		   				<td id="uploadDate"></td>
		   				<td id="totalRecodCount"></td>
		   			    <td id="recRecoveryAmt"></td>
		   				<td id="vertualAccNo"></td>
		   				<td id="recApprovalDate"></td>
		   				<td id="recApprovalStatus"></td>
		   			    <td id="recRemarkTd"><textarea rows="0" cols="" id="recRemark" name="recRemark" style="display: none"></textarea></td>
		   			    <td id="recStatus" style="display: none;"></td>
		   			    </tr>
		   			
							</tbody>
							<!-- <tr>
								<td><input value="S" type="button"
								class="btn btn-danger returnBtn" style="display: none"/></td>
							</tr> -->
						</table>
							<div>
							<input value="Submit to MLI Checker"  type="button" class="btn btn-success save saveBtn" style="margin-left: 1010px;display: none">
							<input value="Cancel" type="button" id="cancelBtn" class="btn btn-reset  cancelBtn" style="display: none">
							<input value="Return" type="button" id="rejectBtn" class="btn btn-reset  rejectBtn saveBtn" style="display: none">
							</div> 
						<div class="col-md-4 prl-10" id="recoveryPaymentdtl">
						<!-- <table id="recoveryTbl"
							class="table table-bordered table-hover cus-table mb-0 dataTable no-footer" style="margin-top:60px;display: none">
							<thead>
								<tr>
								    <th>RP Number</th>
									<th>Virtual Account No.</th>
									<th>Recovery Amount</th>
									<th>Payment Initiation Date</th>
							
								</tr>
							</thead>
							<tbody>
							<tr>
						       <td id="rpNumber"></td>
		   				       <td id="virtualAccNumber"></td>
		   			           <td id="recRecoveryAmount"></td>
		   				       <td id="PaymentIntiationDate"></td>
							</tr>
							</tbody>
							</table> -->
							<div class="col-lg-12"><h5 style="width: 706px;color: red;font-size: 14px;font-weight: 800;">Virtual A/C No,Amount and IFSC Code should match exactly for successful payment.<br>Benficiary Name:CGTMSE,Account Branch:FCS Center Bangalore
							
							</h5></div>
							<table id="recoveryTbl"
							class="table table-bordered table-hover cus-table mb-0 dataTable no-footer" style="margin-top:60px;display: block">
							<thead>
								<tr>
								    <th>RP Number</th>
									<th>Virtual Account No.</th>
									<th>Recovery Amount</th>
									<th>IFSC Code</th>
							
								</tr>
							</thead>
							<tbody>
							<tr>
						       <td id="rpNumber"></td>
		   				       <td id="virtualAccNumber"></td>
		   			           <td id="recRecoveryAmount"></td>
		   				       <td id="PaymentIntiationDate"></td>
							</tr>
							</tbody>
							</table>
							<button type="button" style="border: none !important; cursor: allowed;">
								<img src="images/excel.png" alt="" data-toggle="tooltip"
									class="downloadExelInside" style="display: block"
									title="Export To Excel" onclick="return downloadExelFile()">
							</button>
						
							</div>
						
								
						</div>
				</div>

				
				
				</div>
								
			</div>
		</div>
	</form>
</body>

<script type="text/javascript">
var jobFile ;
var jobFile1;
$(document).on('click','#uploadRecoveryMackerBtn',function() {
	if (confirm("Do you want to Upload?")) {

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
		
		var fData = new FormData();
		fData.append('uploadfile', $('#file')[0].files[0]);
		//fData.append('obj', JSON.stringify(obj));
		$('#file').prop('disabled', true);
		$('#uploadRecoveryMackerBtn').prop('disabled', true);
		$('#uploadRecoveryMackerBtn').children('i').addClass('fa fa-refresh fa-spin');
		$('#fileStatusMsg').text('');

		$.ajax({
			type : "POST",
			url : "uploadRecoveryFile.html",
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
				$('#uploadRecoveryMackerBtn').prop('disabled', true);
				$('#uploadRecoveryMackerBtn').children('i').removeClass('fa fa-refresh fa-spin');
			//	getUtrUploadedFileProcessStatus();
				getUtrSuccessErrorUploadedFileCount();
			},
			error : function(xhr, httpStatusMessage,customErrorMessage) {
				alert(xhr.responseText);
			    $('#uploadRecoveryMackerBtn').children('i').removeClass('fa fa-refresh fa-spin');
				$('#uploadRecoveryMackerBtn').prop('disabled', false);
				$('#file').prop('disabled', false);
			}
		});

} else {
	return false;
}
});
/* getXMlRecoveryData(); */ 
getUtrSuccessErrorUploadedFileCount();
function getUtrSuccessErrorUploadedFileCount() {
	
	$.ajax({
		url : '${pageContext.request.contextPath}/success-error-count-recoveryuploadfile.html',
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
			jobFile = setInterval(getRecoveryUploadedRecordStatusBar,5000); 
		},
		error : function(xhr, httpStatusMessage, customErrorMessage) {
			alert(xhr.responseText);
		}
	});
}

function getRecoveryUploadedRecordStatusBar() {
	
	$.ajax({
		url : '${pageContext.request.contextPath}/getRecoveryUploadedRecordStatusBar.html',
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
				     gatLodgeFreashRecovery();
				     getPaymentRecoveryData();
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
$(document).on('click','#refreshBtn',function() {
	$('#refreshBtn').children('i').addClass('fa fa-refresh fa-spin');
	getRecoveryUploadedRecordStatusBar();
});

function getUtrUploadedFileProcessStatus() {
	$.ajax({
		url : '${pageContext.request.contextPath}/file-utrUpload-process-status.html',
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
$(document).on('click','#totalRecords',function() {
	var uploadId = $('#uploadId').text();
	var totalRecords = $(this).text();
	if (uploadId != null && totalRecords!='0') {
		window.location.href = '${pageContext.request.contextPath}/download-error-recoveryUploadedfile-count.html?uploadId='+ uploadId+'&flag=T';
	}

});


$(document).on('click','#cancelBtn',function() {
	
	window.location.href = '${pageContext.request.contextPath}/nbfcMakerHome.html';


});

$(document).on('click','#unsuccesscount',function() {
	var uploadId = $('#uploadId').text();
	var unSuccessCount = $(this).text();
	if (uploadId != null && unSuccessCount!='0') {
		window.location.href = '${pageContext.request.contextPath}/download-error-recoveryUploadedfile-count.html?uploadId='+ uploadId+'&flag=E';
	}

});

$(document).on('click','#successCount',function() {
	var uploadId = $('#uploadId').text();
	var successCount = $(this).text();
	if (uploadId != null && successCount!='0') {
		window.location.href = '${pageContext.request.contextPath}/download-error-recoveryUploadedfile-count.html?uploadId='+ uploadId+'&flag=S';
	}

});


function gatLodgeFreashRecovery() {
	var isDisable=true;
	var roleHeaderVal=$('#roleheader').text();
	
	$.ajax({
		url : '${pageContext.request.contextPath}/lodge-fresh-recovery.html',
		cache : false,
		crossDomain : true,
		type : 'GET',
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		success : function(result) {
			console.log("gatLodgeFreashRecoveryvivek",result);			
		   if(result.length=='0'){
				$('#uploadRecoveryMackerBtn').attr('disabled',false);
				$('#file').prop('disabled',false);
				
				isDisable=false;
			}
		   $('#recmliName').text(result.mliName);
			$('#recUploadId').text(result.uploadId);
			$('#repNo').text(result.rp_no);
			$('#uploadDate').text(result.uploadedDate);
			$('#totalRecodCount').text(result.totalRecordCount);
		    $('#recRecoveryAmt').text(result.recoveryAmt);
			$('#vertualAccNo').text(result.vertualAccNo);
			$('#recApprovalDate').text(result.checkerApprovalDt);
			$('#recApprovalStatus').text(result.statusDesc);
			$('#recRemark').text(result.remark);
			$('#recStatus').text(result.approvalCode);		
	   		
			if(roleHeaderVal=='CHECKER'){
			/* $('textarea[name="recRemark"]').prop('disabled', false); */
			$('#recRemark').show();
			$('.save').val('Approve');
			$('.cancelBtn').hide();
			$('.rejectBtn').show();
			//$('.downloadExel').show();
			$('.save').show();
			
			}else if(roleHeaderVal=='MAKER'){

				$('.save').val('Submit to MLI Checker');
				$('.cancelBtn').val('Cancel');
				$('.rejectBtn').hide();
				$('.save').show();
				$('.cancelBtn').show();
				$('#recoveryPaymentdtl').hide();
			}
		var rpnumber=$('#repNo').text();
		console.log('ABCrr',rpnumber);
		     if(result.approvalCode==null && roleHeaderVal=='MAKER'){
			     if(rpnumber==''){
			    	 $('.save').hide();
					 $('.cancelBtn').hide();
					 console.log('ABC');
                
			    	 }else{
			    		    $('.save').prop('disabled', false);
							$('.cancelBtn').prop('disabled', false);
							$('.save').show();
							$('.cancelBtn').show();
							$('#recRemark').attr('disabled',true);
		                    console.log('RP');
			    	 } 						
					
				
			 } 
			 
		     else if(result.approvalCode=='CG_R' && roleHeaderVal=='CHECKER'){
					$('#uploadRecoveryMackerBtn').attr('disabled',true);
					$('#file').attr('disabled',true);
					$('.rejectBtn').attr('disabled',true);
					$('.save').attr('disabled',true);
					$('#recRemark').attr('disabled',true);
					
				}else if(result.approvalCode=='MM' && roleHeaderVal=='MAKER'){
					$('.save').prop('disabled', true);
					$('#rejectBtn').prop('disabled', true);
					$('#uploadRecoveryMackerBtn').attr('disabled',true);
					$('#file').prop('disabled',true);
					
					
	        }
				else if(result.approvalCode=='MM' && roleHeaderVal=='CHECKER'){
					$('.save').prop('disabled', false);
					$('#rejectBtn').prop('disabled', false);
					$('#uploadRecoveryMackerBtn').attr('disabled',true);
					$('#file').prop('disabled',true);
					
					
					
					
				}
				else if(result.approvalCode=='CG_R' && roleHeaderVal=='MAKER'){
					$('.save').prop('disabled', true);
					$('#rejectBtn').prop('disabled', true);
					$('#uploadRecoveryMackerBtn').attr('disabled',false);
					$('#file').prop('disabled',false);
					$('#recRemark').show();
					
					
				}
				else if(result.approvalCode=='CG_A' && roleHeaderVal=='CHECKER'){
					$('.save').prop('disabled', true);
					$('#rejectBtn').prop('disabled', true);
					$('#recRemark').hide();
					//getXMlRecoveryData();//after approval calling to dispay data
					
				
				}else if(result.approvalCode==null && roleHeaderVal=='CHECKER'){
					$('#uploadRecoveryMackerBtn').attr('disabled',true);
					$('#file').prop('disabled',true);
					
						$('.rejectBtn').hide();
						$('.save').hide();
						$('#recRemark').hide();
						//$('.downloadExel').hide();
					
				 }
				
			   				
					

		},
		error : function(xhr, httpStatusMessage, customErrorMessage) {
			alert(xhr.responseText);
		}
	});
}

	
$(document).on('click','.saveBtn',function() {
	var approvalType=$(this).val();
    var msg='';
    var status='';
  if(approvalType=='Submit to MLI Checker'){
	  msg="Do you want submit to MLI checker?";
      status='MM';
  }else if(approvalType=='Approve'){
	msg="Please note that after this the Pay Id, same WILL NOT be available for modification/cancellation ,want to proceed!";
	status='CG_A';
	
  }else if(approvalType=='Return'){
    msg="Do you want to reject?";
    status='CG_R';
	}
  
	if (confirm(msg)) {
	 var arrayObj = [];
	 debugger;
        $("table#utrTbl tbody tr").each(function(){
                var mliName=$(this).closest('tr').find('td:eq(0)').text();
                var uploadId=$(this).closest('tr').find('td:eq(1)').text();
                var rpNumber=$(this).closest('tr').find('td:eq(2)').text();
                var totalRecord=$(this).closest('tr').find('td:eq(4)').text();
                var uploadedDate=$(this).closest('tr').find('td:eq(3)').text();
                var approvalRemark=$(this).closest('tr').find('textarea').val();
                var recoveryAmt=$(this).closest('tr').find('td:eq(5)').text();
               
               //alert("remark"+approvalRemark);
        var obj = {
       "mliName" :mliName,
       "uploadId" :uploadId,
       "rp_no":rpNumber,
       "totalRecordCount":totalRecord,
       "uploadedDate" :uploadedDate,
       "remark" :approvalRemark,
       "recoveryAmt" :recoveryAmt,
       "approvalStatus":status
        };
       arrayObj.push(obj);
        });
		
		var fData = new FormData();
		fData.append('obj', JSON.stringify(arrayObj));
		console.log('fDatasaveRecovery',arrayObj);
		 $.ajax({
				type : 'POST',
				url : '${pageContext.request.contextPath}/saveRecoveryUpload.html',
				processData : false, // tell jQuery not to process the data
				contentType : false, // tell jQuery not to set contentType
				cache : false,
				crossDomain : true,
				data : fData,
				success : function(data) {
					alert(data);
					gatLodgeFreashRecovery();
					getPaymentRecoveryData();
				   
				   
				},
				error : function(xhr, httpStatusMessage,customErrorMessage) {
					alert(xhr.responseText);
				}
			});
		
} else {
	return false;
}
});

/* $(document).on('click','#downloadRecoveryExcel',function() {
	alert('hii');
	var uploadId = $('#uploadId').text();
	var successCount = $(this).text();
	/* if (uploadId != null && successCount!='0') { */
		//alert('helo');
		//window.location.href = '${pageContext.request.contextPath}/download-recoveryUploadExcelData.html';
	/* } */
//}); */
/*  function downloadExelFile(){
		alert('excel file');
	    window.location.href = '${pageContext.request.contextPath}/download-recoveryUploadExcelData.html';
}
 */
 
 function downloadExelFile() {
				window.location.href = '${pageContext.request.contextPath}/download-recoveryUploadExcelData.html';
	}
 $(document).on('click','.saveBtn',function(){
	 var approvalType=$(this).val();
	 //alert(approvalType);
	 var roleHeaderVal=$('#roleheader').text();
	 if(approvalType=='Approve' && roleHeaderVal=='CHECKER'){
		/*  $('.downloadExel').show(); */
		 /* $('#recoveryPaymentdtl').show(); */
	 $.ajax({
			url : '${pageContext.request.contextPath}/get-Xml-recoveryData.html',
			cache : false,
			crossDomain : true,
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success : function(result) {
				console.log("getXMlRecoveryData",result);
				/* jobFile1 = setInterval(getPaymentRecoveryData,3000);  */			
				/* getPaymentRecoveryData(); */
				   

			},
			error : function(xhr, httpStatusMessage, customErrorMessage) {
				alert(xhr.responseText);
			}
		});
		 
	 }
});

 getPaymentRecoveryData();
 function getPaymentRecoveryData(){

	 $.ajax({
			url : '${pageContext.request.contextPath}/get-payment-recoveryData.html',
			cache : false,
			crossDomain : true,
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success : function(result) {
				console.log("getPaymentRecoveryData",result);			
			   
			    
				$('#rpNumber').text(result.rp_no);
				$('#virtualAccNumber').text(result.virtualAccNo);
				$('#recRecoveryAmount').text(result.recoveryAmt);
				$('#PaymentIntiationDate').text(result.ifscCode);		
		   		

			},
			error : function(xhr, httpStatusMessage, customErrorMessage) {
				alert(xhr.responseText);
			}
		});
	 }
	
</script>

</html>
