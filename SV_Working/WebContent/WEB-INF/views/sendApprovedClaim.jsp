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
				<div class="frm-section" style="margin: 15px 0;" >
					<div class="col-md-12">
						<div class="claim">
							<h2>Send Approved Claim Data</h2>
						</div>
						<div class="col-md-2 prl-4">
						     <label>Status :</label>
				  			 <select name="submitStatus" id="submitStatus" class="form-control cus-control" onchange="getClaimData()">
							 	<option value="PENDING">PENDING DATA</option>
							 	<option value="SUBMITTED">SENT APPROVED DATA</option>
					         </select>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<table id="claimDtlTbl" class="table table-bordered table-hover cus-table mt-9 mb-0 danRpDataTable">
		<thead>
				<tr>
						<th>Sr. No.</th>
						<th>MLI Name</th>
						<th>Member Id</th>
						<th>Record Count</th>
						<th>Quaterly</th>
						<th>Financial Year</th>
						<th>Claim Number</th>
						<th>Upload ID</th>
						<th>Checker Submit Date</th>
						<th>Checker Approved Date</th>
						<th>Action</th>
				</tr>
		</thead>
	</table>
</body>

<script type="text/javascript">
    getClaimData();
	function getClaimData()
	{
		 $("#claimDtlTbl tbody tr").remove();
		var submitStatus = $("#submitStatus").val();
		$.ajax({
			url : '${pageContext.request.contextPath}/getApprovedSubmittedData.html?submitStatus='+submitStatus,
			cache : false,
			crossDomain : true,
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success : function(result)
			{
				 console.log("Data is ::",JSON.stringify(result));
				 if(result != [] && result.length >0)
				 {
					var assignedMilestoneTable = $("#claimDtlTbl").DataTable({
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
								data: "mliName",
								defaultContent: 'NA'
							},
							{
								data: "mliId",
								defaultContent: 'NA'
							},
							{
								data: "recordCount",
								defaultContent: 'NA'
							},
							{
								data: "quaterly",
								defaultContent: 'NA'
							},
							{
								data: "financialYears",
								defaultContent: 'NA'
							},
							{
								data: "claimNumber",
								defaultContent: 'NA'
							},
							{
								render: function ( data, type, row ) {
								if(submitStatus == 'SUBMITTED'){		
									return '<a herf="#" class="uploadFileLink">' + row.uploadId + '</a>';
								}
									},
								defaultContent: 'NA'
							}, 
							{
								data: "claimSubmitToCkrDt",
								type: "date",
								defaultContent: 'NA'
							},
							{
								data: "claimPaymentDt",
								defaultContent: 'NA'
							},
			                { 
							    data: null, 
								wrap: true, 
								"render": function (item) 
								{ 
									if(submitStatus == 'PENDING'){
										return '<div class="btn-group"> <button type="button" onclick="sendApiData('+ item.uploadId+')" class="btn btn-approve approveBtn">Submit Data</button></div>' 
									}else {
										return '<div class="btn-group"> <input type="button" class="btn btn-reset returnBtn" value="Submitted Data" disabled/></div>'
									}
								} },
							],
							"fnCreatedRow": function (row, data, index) 
							{
								$('td', row).eq(0).html(index + 1);
								$('td', row).eq(1).attr('mliName', 'mliName');
								$('td', row).eq(2).attr('mliId', 'mliId');
								$('td', row).eq(3).attr('recordCount', 'recordCount');
								$('td', row).eq(4).attr('quaterly', 'quaterly');
								$('td', row).eq(5).attr('financialYears', 'financialYears');
								$('td', row).eq(6).attr('claimNumber', 'claimNumber');
								$('td', row).eq(7).attr('uploadId', 'uploadId');
								$('td', row).eq(8).attr('claimSubmitToCkrDt', 'claimSubmitToCkrDt');
								$('td', row).eq(9).attr('claimPaymentDt', 'claimPaymentDt');
							},
					});
				 }
				 else
				 {
					 var assignedMilestoneTable = $("#claimDtlTbl").DataTable({
							data: result,
							destroy: true,
							autoWidth: false,
							bSort: false,
							deferRender: true,
					 });
				}
			},
			error : function(xhr, httpStatusMessage, customErrorMessage) {
				alert(xhr.responseText);
			}
		});
	}
	
	function sendApiData(uploadId)
	{
		$.ajax({
			url : '${pageContext.request.contextPath}/sendclaimdatathroughapi.html?uploadId='+uploadId,
			cache : false,
			crossDomain : true,
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success : function(result)
			{
				alert("Successs....");
				window.location.reload();
			},
			error : function(xhr, httpStatusMessage, customErrorMessage) 
			{
				alert("Error.." + xhr.responseText);
				window.location.reload();
			}
		});
	}
	
	$(document).on('click','.uploadFileLink',function() {
		var uploadId = $(this).text();
		 if (uploadId != null) {
			window.location.href = '${pageContext.request.contextPath}/download-claimapi-data.html?uploadId='+ uploadId;
		}
	});
</script>
</html>
