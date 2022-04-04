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
							<h2>CGTMSE</h2>
						</div>
						<div class="col-md-2 prl-4">
						                <label>Status :</label>
										<select name="apprvStatus" id="apprvStatus" class="form-control cus-control" onchange="getAllCGTMSEData()">
										    <option value="">ALL</option>
										    <option value="CG_A">APPROVE</option>
										    <option value="CG_R">REJECT</option>
										    <option value="MC_A">PENDING</option>
										</select>
						</div>
									
				               <table id="cgmtseMandantTbl" class="table table-bordered table-hover cus-table mb-0 dataTable no-footer" style="margin-top:80px;">
							<thead>
								<tr>
									<th>Sr. No.</th>
									<th>Select Record</th>
									<th>MEMBER ID</th>
									<th>MLI NAME</th>
									<th>BANK NAME</th>
									<th>BRANCH NAME</th>
									<th>ACCOUNT NUMBER</th>
									<th>TYPE OF ACCOUNT</th>
									<th>IFSC CODE</th>
									<th>Uploaded File Link</th>
									<th>Remarks</th>
									<th>Action (Approve / Return)</th>
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


	getAllCGTMSEData();
	 function getAllCGTMSEData() {
		 var apprvStatus = $("#apprvStatus").val();
		 var apprvStatusJSON = {"apprvStatus" : apprvStatus};
		$.ajax({
			url : '${pageContext.request.contextPath}/getAllCgtmseApproval.html?apprvStatus='+apprvStatus,
			cache : false,
			crossDomain : true,
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			 success : function(result) {
				 if(result != [] && result.length >0){
					$("#cgmtseMandantTbl tbody tr").remove(); 
	   			}
				$.each(result, function(key, value) {
					var remark=(value.cRemarks!=null)? value.cRemarks:"";
					var rowCount = $('#cgmtseMandantTbl tr').length;
			   			var markup = '<tr>';
			   				markup += '<td>'+rowCount +'</td>';
			   				if(rowCount=='1'){
			   					markup += '<td><input class="form-check-input cgtmseBtn" type="radio" name="cgtmseBtn" checked value="'+rowCount +'"></td>';
				   				}else{
				   					markup += '<td><input class="form-check-input cgtmseBtn" type="radio" name="cgtmseBtn" value="'+rowCount +'"></td>';
					   		}
			   			 	markup += '<td>'+ value.milId +'</td>';
			   			 	markup += '<td>'+ value.mlIName +'</td>';
			   				markup += '<td>'+ value.bankName +'</td>';
			   				markup += '<td>'+ value.branch +'</td>';
			   				markup += '<td>'+ value.accNum +'</td>';
			   				markup += '<td>'+ value.typeOfAcc +'</td>';
			   				markup += '<td>'+ value.ifscCode +'</td>';
			   				markup += '<td><a herf="#" class="uploadFileLink">'+value.uploadDoc +'</a></td>';
			   			 	markup += '<td class="remarkTd"><textarea rows="3" cols="" value="" class="remark">'+remark+'</textarea></td>';
			   		 if(value.approveStatus=='MC_A' || value.approveStatus=='MM' || value.approveStatus==null){ 
				   			 	markup += '<td><button type="button" class="btn btn-approve approveBtn" value="Approve"><i></i> Approve</button><button type="button" class="btn btn-reset returnBtn" value="Return" ><i></i> Return</button></td>'; 	
					   		 } else{
					   			markup += '<td><button type="button" disabled class="btn btn-approve approveBtn" value="Approve"><i></i> Approve</button><button type="button" class="btn btn-reset returnBtn" value="Return" disabled><i></i> Return</button></td>';
						}  
			   			    markup += '</tr>';
			   			$("table#cgmtseMandantTbl tbody").append(markup);  
			   });	
			},
			error : function(xhr, httpStatusMessage, customErrorMessage) {
				alert(xhr.responseText);
			}
		});
	} 


	$(document).on('click', '.approveBtn', function() {

		var approvalStatus = $(this).val();
		var mliId=  $(this).closest('tr').find('td:eq(2)').text();
	    var remark=  $(this).closest('tr').find('textarea').val();
		var accountNo=  $(this).closest('tr').find('td:eq(6)').text();
		var ifscCode=  $(this).closest('tr').find('td:eq(8)').text();
		
		
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
				"milId" : mliId,
				"approveStatus" : approvalStatus,
				"cRemarks" : remark,
				"accNum":accountNo,
				"ifscCode":ifscCode
			};
		console.log("approve",jsonData);
/* 		var fData = new FormData();
		fData.append('objField', JSON.stringify(jsonData));
 */
		if (confirm("Mandate Form once approved, cannot be modified. Do you want to continue?")) {
			$(this).closest('tr').find('td:eq(11) .approveBtn').prop('disabled', true);
			$(this).closest('tr').find('td:eq(11) .returnBtn').prop('disabled', true);
			checkersAprrovalStatus(jsonData, 'approveBtn');
		} else {
			return false;
		}
	});

	$(document).on('click', '.returnBtn', function() {
		var approvalStatus = $(this).val();
		var mliId=  $(this).closest('tr').find('td:eq(2)').text();
	    var remark=  $(this).closest('tr').find('textarea').val();
		var accountNo=  $(this).closest('tr').find('td:eq(6)').text();
		var ifscCode=  $(this).closest('tr').find('td:eq(8)').text();
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
				"milId" : mliId,
				"approveStatus" : approvalStatus,
				"cRemarks" : remark,
				"accNum":accountNo,
				"ifscCode":ifscCode
			};
		
		console.log("retrun",jsonData);
		//var fData = new FormData();
		//fData.append('objField', JSON.stringify(jsonData));

		if (confirm("Do you want to Return?")) {
			$(this).closest('tr').find('td:eq(11) .approveBtn').prop('disabled', true);
			$(this).closest('tr').find('td:eq(11) .returnBtn').prop('disabled', true);
			checkersAprrovalStatus(jsonData, 'returnBtn');
		} else {
			$(this).closest('tr').find('td:eq(11) .approveBtn').prop('disabled', false);
			$(this).closest('tr').find('td:eq(11) .returnBtn').prop('disabled', false);
			return false;
		}
	});

function checkersAprrovalStatus(fData, type) {
	//alert("ajaxcall");
	$.ajax({
		url : '${pageContext.request.contextPath}/update-cgtmse-mli-approval.html',
		cache : false,
		crossDomain : true,
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : JSON.stringify(fData),
		success : function(data) {
			getAllCGTMSEData();
			alert(data);
		},
		error : function(xhr, httpStatusMessage, customErrorMessage) {
			$(this).closest('tr').find('td:eq(11) .approveBtn').prop('disabled', false);
			$(this).closest('tr').find('td:eq(11) .returnBtn').prop('disabled', false);
			alert(xhr.responseText);
		}
	});
}



$(document).on('click','.uploadFileLink',function() {
//	alert("sd");
	var mliId=  $(this).closest('tr').find('td:eq(2)').text();
	if (mliId != null) {
		window.location.href = '${pageContext.request.contextPath}/download-cgtmse-mli-approval.html?mliId='+ mliId;
	}
});



</script>

</html>
