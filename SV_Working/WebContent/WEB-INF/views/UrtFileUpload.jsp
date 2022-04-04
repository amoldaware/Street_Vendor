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
							<h1>UTR Upload</h1>

				</div>
				</div>
				</div>
			<div id="mandantApproval" ><!-- style="display: none" -->
			  
				<div class="frm-section" style="margin: 15px 0;" id="makerView">
					<div class="col-md-12">
						<div class="claim">
							<h2>Maker</h2>
							
						</div>
						<!-- <div class="info">
							<h5 style="color:red;text-align:right">Note - Claim file can be uploaded once in a quarter.</h5>
							
						</div> -->
						<div class="claim">
							<h5 style="color:red;text-align:center" id="fileStatusMsg"></h5>
						</div>
						
						<div class="col-md-6 prl-10">
							<div class="form-group row" id="mkrUpload">
								<label class="col-md-4">UTR File Upload</label>
								<div class="custom-file">
								<input  type="file" id="file" class="form-control-file">
									<!-- <input type="file" id="file"
										class="custom-file-input custom-file-input1" id="customFile"> -->
									<!-- <label class="custom-file-label custom-file-label1"
										for="customFile">No file chosen</label> -->
								</div>
								<button type="button" class="btn btn-success btn-sm"
									id="uploadUrtMackerBtn">
									<i></i> Upload
								</button>
								
								<div class="form-group row">
								     <label class="col-md-4"> &nbsp;&nbsp; &nbsp;Download Excel Format </label>
								 <a href="${pageContext.request.contextPath}/Download/UTRUpload_Format.xlsx" target="_blank" download>&nbsp;Click here to download
								 UTR file format</a>
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
						
						<table id="utrTbl"
							class="table table-bordered table-hover cus-table mb-0 dataTable no-footer" style="margin-top:80px;">
							<thead>
								<tr>
								     <th>Sr.No</th>
									 <th>MLI Name</th>
									<th>Financial Year</th>
									<th>Claim Number</th>
									<th>Claim Settlement Amt</th>
									<th>Payment date</th>
									<th>UTR No</th>
									<th>Claim Payment Voucher</th>
									<th>Status</th>
								    <th>Remark</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
							<!-- <tr>
								<td><input value="S" type="button"
								class="btn btn-danger returnBtn" style="display: none"/></td>
							</tr> -->
						</table>
							<div>
							<input value="Submit to Cgtmse" type="button" class="btn btn-success save" style="margin-left: 1031px;" display: none">
							<input value="Cancel" type="button" id="cancelBtn" class="btn btn-reset  cancelBtn" display: none">
							</div>
							<div>
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
$(document).on('click','#uploadUrtMackerBtn',function() {
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
		$('#uploadUrtMackerBtn').prop('disabled', true);
		$('#uploadUrtMackerBtn').children('i').addClass('fa fa-refresh fa-spin');
		$('#fileStatusMsg').text('');

		$.ajax({
			type : "POST",
			url : "uploadUrtFile.html",
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
				$('#uploadUrtMackerBtn').prop('disabled', true);
				$('#uploadUrtMackerBtn').children('i').removeClass('fa fa-refresh fa-spin');
			//	getUtrUploadedFileProcessStatus();
				getUtrSuccessErrorUploadedFileCount();
			},
			error : function(xhr, httpStatusMessage,customErrorMessage) {
				alert(xhr.responseText);
			    $('#uploadUrtMackerBtn').children('i').removeClass('fa fa-refresh fa-spin');
				$('#uploadUrtMackerBtn').prop('disabled', false);
				$('#file').prop('disabled', false);
			}
		});

} else {
	return false;
}
});

getUtrSuccessErrorUploadedFileCount();
function getUtrSuccessErrorUploadedFileCount() {
	
	$.ajax({
		url : '${pageContext.request.contextPath}/success-error-count-utruploadfile.html',
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
			jobFile = setInterval(getUtrUploadedRecordStatusBar,5000); 
		},
		error : function(xhr, httpStatusMessage, customErrorMessage) {
			alert(xhr.responseText);
		}
	});
}

function getUtrUploadedRecordStatusBar() {
	
	$.ajax({
		url : '${pageContext.request.contextPath}/getUtrUploadedRecordStatusBar.html',
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
				     getLodgeFreshUtr();
					console.log("getUploadedRecordStatusBars");
				  /* clearInterval(jobFile); */
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
	getUtrUploadedRecordStatusBar();
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
		window.location.href = '${pageContext.request.contextPath}/download-error-utrUploadedfile-count.html?uploadId='+ uploadId+'&flag=T';
	}

});


$(document).on('click','#cancelBtn',function() {
	
	window.location.href = '${pageContext.request.contextPath}/nbfcMakerHome.html';


});

$(document).on('click','#unsuccesscount',function() {
	var uploadId = $('#uploadId').text();
	var unSuccessCount = $(this).text();
	if (uploadId != null && unSuccessCount!='0') {
		window.location.href = '${pageContext.request.contextPath}/download-error-utrUploadedfile-count.html?uploadId='+ uploadId+'&flag=E';
	}

});

$(document).on('click','#successCount',function() {
	var uploadId = $('#uploadId').text();
	var successCount = $(this).text();
	if (uploadId != null && successCount!='0') {
		window.location.href = '${pageContext.request.contextPath}/download-error-utrUploadedfile-count.html?uploadId='+ uploadId+'&flag=S';
	}

});


function getLodgeFreshUtr() {
	var isDisable=true;
	$.ajax({
		url : '${pageContext.request.contextPath}/lodge-fresh-utr.html',
		cache : false,
		crossDomain : true,
		type : 'GET',
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		success : function(result) {
			console.log("getLodgeFreshUtr",result.length);
			console.log("getLodgeFreshUtr",result);
			if(result.length>0){
				$("#utrTbl tbody tr").remove(); 
				
				
   			}else{
   				$("#utrTbl tbody tr").remove();
   	   		}	
			
			
		
			$.each(result, function(key, value) {
				console.log("getLodgeFreshUtr2",result.length);
				var rowCount = $('#utrTbl tr').length;
				if(rowCount=='0'){
					//alert(rowCount);
					$('#uploadUrtMackerBtn').attr('disabled',false);
					$('#file').attr('disabled',false);
					$('.save').hide();
					$('.cancelBtn').hide();
					
				}else if(rowCount=='1'){
					//alert(rowCount);
					$('.save').show();
					$('.cancelBtn').show();
				}/* if(isDisable){
					alert("isDisable",value.approvalCode);
					$('#uploadUrtMackerBtn').attr('disabled',true);
					$('#file').attr('disabled',true);
				}  */
				
				
						
		   			var markup = '<tr>';
		   				markup += '<td>'+rowCount +'</td>';
		   				markup += '<td class="mliID" style="display: none;">'+ value.mliId +'</td>';
		   			    markup += '<td>'+ value.mliName +'</td>';
		   			 	markup += '<td>'+ value.financialYear +'</td>';
		   				markup += '<td>'+ value.claimNumber +'</td>';
		   				markup += '<td>'+ value.claimSettlementAmt +'</td>';
		   				markup += '<td>'+ value.claimPaymentDate +'</td>';
		   				markup += '<td>'+ value.utrNo +'</td>';
		   		        markup += '<td>'+ value.claimPaymentVoucher +'</td>';
		   			    markup += '<td>'+ value.approvalStatus +'</td>';
		   			    markup += '<td>'+ value.remark +'</td>';
		   			    markup += '<td class="approvalCode" style="display: none;">'+ value.approvalCode +'</td>'
		   				markup += '</tr>';
		   			$("table#utrTbl tbody").append(markup);  
		   			if(value.approvalCode==null){
						//alert('g'+value.approvalCode);
						$('.save').attr('disabled',false);

				    }else if(value.approvalCode=='CG_R'){
				    	//alert('h'+value.approvalCode);
						$('#uploadUrtMackerBtn').attr('disabled',false);
						$('#file').attr('disabled',false);
						$('.save').attr('disabled',true);
						
						
				    }

		   			
		   });

		},
		error : function(xhr, httpStatusMessage, customErrorMessage) {
			alert(xhr.responseText);
		}
	});
}

$(document).on('click','.save',function() {
	if (confirm("Do you want to Save?")) {
	 var arrayObj = [];
	 debugger;
        $("table#utrTbl tbody tr").each(function(){
                var mliId=$(this).closest('tr').find('td:eq(1)').text();
                var claimNumber=$(this).closest('tr').find('td:eq(4)').text();
                var claimPaymentVoucher=$(this).closest('tr').find('td:eq(8)').text();
                var utrNo=$(this).closest('tr').find('td:eq(7)').text();
               //alert("claimNumber"+claimNumber);
        var obj = {
       "mliId" :mliId,
       "claimNumber" :claimNumber,
       "claimPaymentVoucher":claimPaymentVoucher,
       "utrNo":utrNo
                };
       arrayObj.push(obj);
        });
		
		var fData = new FormData();
		fData.append('obj', JSON.stringify(arrayObj));
		 $.ajax({
				type : 'POST',
				url : '${pageContext.request.contextPath}/saveUtrUpload.html',
				processData : false, // tell jQuery not to process the data
				contentType : false, // tell jQuery not to set contentType
				cache : false,
				crossDomain : true,
				data : fData,
				success : function(data) {
					alert(data);
				   $('.save').prop('disabled', true);
				},
				error : function(xhr, httpStatusMessage,customErrorMessage) {
					alert(xhr.responseText);
				}
			});
		/* 
		$.ajax({
			type : "POST",
			url : "saveUtrUpload.html",
			contentType :"application/json; charset=utf-8",
			crossDomain: true,
			dataType : "json",
			data : fData,
			success : function(data) {
			alert(data);			

			},
			error : function(xhr, httpStatusMessage,customErrorMessage) {
				alert(xhr.responseText);
			   
			}
		});
 */
} else {
	return false;
}
});
	
	
</script>

</html>
