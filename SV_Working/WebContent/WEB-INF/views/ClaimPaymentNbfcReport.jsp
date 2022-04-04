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
							<h1>Claim Payment Report</h1>

						</div>
					</div>
				</div>
			
		<div class="frm-section" style="margin: 15px 0;" >
			<div class="col-md-12">
			<table id="cgmtseTbl" class="table table-bordered table-hover cus-table mb-0 dataTable no-footer" style="margin-top:80px;">
				<thead>
					<tr>
							<th>Sr. No.</th>
							<th>MLI Name</th>
							<th>Financial Year</th>
							<th>Claim Number</th>
							<th>Claim Upload Date</th>
							<th>Claim Payment Date</th>
							<th>Records Count</th>
							<th>Uploaded OS Amount</th>
							<th>Crystallised Portfolio Amount</th>
							<th>Recovery (if Any)</th>
							<th>Eligible Claim Amount*</th>
							<th>Uploaded File Link</th>
							<th>Management Certificate</th>
							<th>Remarks</th>
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
getAllNBFCEData();

function getAllNBFCEData() {
	$.ajax({
		url : '${pageContext.request.contextPath}/getAllNbfcData.html',
		cache : false,
		crossDomain : true,
		type : 'GET',
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		success : function(result) 
		{
			if(result.length>0){
				$("#cgmtseTbl tbody tr").remove(); 
   			}
			$.each(result, function(key, value) {
				var managementCertificate= (value.managementCertificate!=null) ? value.managementCertificate:'';
				var remark=(value.remark!=null)? value.remark:"";
				//var recovery=(value.recovery==null)?"0":value.recovery;
				
				 var rowCount = $('#cgmtseTbl tr').length;
		   			var markup = '<tr>';
		   				markup += '<td>'+rowCount +'</td>';
		   				markup += '<td class="mliID" style="display: none;">'+ value.mliId +'</td>';
		   				markup += '<td style="display: none;">'+ value.userId +'</td>';
		   			 	markup += '<td>'+ value.mliName +'</td>';
		   			 	markup += '<td>'+ value.financialYears +'</td>';
		   				markup += '<td>'+ value.claimNumber +'</td>';
		   				markup += '<td>'+ value.claimUploadDt +'</td>';
		   				markup += '<td>'+ value.claimPaymentDt +'</td>';
		   				markup += '<td>'+ value.recordCount +'</td>';
		   				markup += '<td>'+ value.uploadedOSAmmount +'</td>';
		   				markup += '<td>'+ value.crystallisedPortfolioAmount +'</td>';
		   				markup += '<td>'+ value.recovery +'</td>';
		   				markup += '<td>'+ value.eligibleClaimAmount +'</td>';
		   			 	markup += '<td><a herf="#" class="uploadFileLink">'+value.uploadId +'</a></td>';
		   			    markup += '<td><a herf="#" class="mgmtCertificate">'+managementCertificate +'</a></td>';
		   			    markup += '<td>'+ value.remark +'</td>';
		   			    markup += '</tr>';
		   			   $("table#cgmtseTbl tbody").append(markup);  

		   			   var MLIID =$('.mliID').text();
		   });
			
		},
		error : function(xhr, httpStatusMessage, customErrorMessage) {
			alert(xhr.responseText);
		}
	});
}

$(document).on('click','.uploadFileLink',function() {
	var uploadId = $(this).text();
	if (uploadId != null) {
		window.location.href = '${pageContext.request.contextPath}/download-nbfc-data.html?uploadId='+ uploadId;
	}
});

$(document).on('click','.mgmtCertificate',function() {
	var claimNumber= $(this).closest('tr').find('td:eq(5)').text();
	if (claimNumber != null) {
	window.location.href = '${pageContext.request.contextPath}/downloadFile-mgmt-certificate-nbfc.html?claimNumber='+ claimNumber;
	}
});
</script>
</html>
