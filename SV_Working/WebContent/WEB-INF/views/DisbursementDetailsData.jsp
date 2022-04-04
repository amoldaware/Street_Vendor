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
	<div class="main-section">
		<div class="container-fluid">
		<div class="frm-section">
		<div class="col-md-12">
			<div class="claim">
			<h1>Disbursement Details Data</h1>
			</div>
		<form method="post" enctype="multipart/form-data">
			<div class="col-md-6 prl-10">
			<div class="form-group row" id="mkrUpload">
			<label class="col-md-4">Upload File</label>
				<div class="custom-file">
					<input  type="file" id="file" class="form-control-file">
					</div>
					<button type="button" class="btn btn-success btn-sm"
						id="uploadMackerBtn">
						<i></i> Upload
					</button>
					<button type="button" class="btn btn-success btn-sm"
							id="Clear">
							<i></i> Clear
					</button>
								
				<div class="form-group row">
				     <label class="col-md-4"> &nbsp;&nbsp; &nbsp;Download Excel Format </label>
					 <a href="${pageContext.request.contextPath}/Download/DisburseData_Upload_Excel_Format.xlsx" target="_blank" download>&nbsp;Click here to download
					 Disburse file format</a>
				</div>
			</div>
			</div>
			</form>
			
			<table id="disbDtlTbl" class="table table-bordered table-hover cus-table mt-9 mb-0 danRpDataTable" style="display: none">
			<thead>
				<tr>
				    <th>Sr. No.</th>
				    <th>Lender Name</th>
					<th>Application No</th>
					<th>Loan Account Number</th>
					<th>Applicant Name</th>
					<th>Disbursed Amount</th>
					<th>Disbursed Date(dd/mm/yyyy)</th>
					<th>Sanction Amount</th>
					<th>Sanction Date(dd/mm/yyyy)</th>
					<th>Loan Term</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
	</table>
	<div class="d-inlineblock mt-25">
		<button type="button" style="border: none !important; cursor: allowed;">
			<img src="images/excel.png" alt="" data-toggle="tooltip"
			     class="downloadExel" style="display: none"
		title="Export To Excel" onclick="return downloadExelFile()">
		</button>
	</div>
	</div>
	</div>
	</div>
	</div>
</body>
<script type="text/javascript">
$(document).on('click','#uploadMackerBtn',function() 
{
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
				alert(fileExtType+ " : Unsupported file type.");
				return false
			}
		}

		var fData = new FormData();
		fData.append('uploadfile', $('#file')[0].files[0]);
		
		$.ajax({
			type : "POST",
			url : "uploadDisburseFile.html",
			processData : false, 
			contentType : false, 
			cache : false,
			crossDomain : true,
			data : fData,
			success : function(result) 
			{
				console.log("Data Is :::" + JSON.stringify(result));
				if(result != [] && result.length >0)
				{
					$("#disbDtlTbl").show();
					$(".downloadExel").show();
					var assignedMilestoneTable = $("#disbDtlTbl").DataTable({
						data: result,
						destroy: true,
						autoWidth: false,
						bSort: false,
						deferRender: true,
						columns: [
						{
							data: null
						},
						{
							data: "lenderName",
							defaultContent: 'NA'
						},
						{
							data: "applicationNo",
							defaultContent: 'NA'
						},
						{
							data: "loanAccountNumber",
							defaultContent: 'NA'
						},
						{
							data: "personalDetails.applicant_Name",
							defaultContent: 'NA'
						},
						{
							data: "disbursedAmount",
							defaultContent: 'NA'
						},
						{
							data: "disbursedDt",
							defaultContent: 'NA'
						},
						{
							data: "sanctionedAmount",
							defaultContent: 'NA'
						},
						{
							data: "sanctionedDt",
							defaultContent: 'NA'
						},
						{
							data: "status",
							defaultContent: 'NA'
						},
						{
							data: "loanTerm",
							defaultContent: 'NA'
						}],
							"fnCreatedRow": function (row, data, index) 
							{
							    $('td', row).eq(0).html(index + 1); 
							},
					});
				}
				else
				{
					 var rowCount = $('#disbDtlTbl tr').length;
		   			 var markup = '<tr>';
		   			 markup += '<td align="center" colspan="11">'+ "No Data Found" +'</td>';
		   		     markup += '</tr>';
		   			$("table#disbDtlTbl tbody").append(markup);
				} 
			},
			error : function(xhr, httpStatusMessage,customErrorMessage) {
				alert(xhr.responseText);
				$('#uploadMackerBtn').children('i').removeClass('fa fa-refresh fa-spin');
				$('#uploadMackerBtn').prop('disabled', false);
				$('#file').prop('disabled', false);
			}
		});
	} 
});

$(document).on('click','#Clear',function() {
	location.reload();
});

$(document).on('click', '.downloadExel', function() {
	window.location.href = '${pageContext.request.contextPath}/download-pms-data.html';
});
</script>
</html>
