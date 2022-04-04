<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%-- <script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>  --%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/datatables.min.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datatables.min.js"></script>
<title>Disbursement Details Application</title>

<style type="text/css">
.ui-datepicker select.ui-datepicker-month,
.ui-datepicker select.ui-datepicker-year{
    color:blue!important;
}
.paging-nav {
	text-align: right;
	padding-top: 2px;
}

.paging-nav a {
	margin: auto 1px;
	text-decoration: none;
	display: inline-block;
	padding: 1px 7px;
	background: #91b9e6;
	color: white;
	border-radius: 3px;
}

.paging-nav .selected-page {
	background: #187ed5;
	font-weight: bold;
}

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
</head>

<body bgcolor="#E0FFFF">
    <div id="loading" style="display: none">
		<img src="images/loader.gif" height="1200px" width="1550px"  style="margin:auto;"/>
	</div>
	<div class="main-section">
		<div class="container-fluid">
			<!-- <div class="row"> -->
			<div>
				<div class="tbl-details">
				  <div class="col-md-12">
					<div class="claim"><h1>Disbursement Details</h1></div>
						<form method="GET" id="A" class="form-horizontal">
							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Bank Name :</label><span style="color: red;">*</span>
										<select name="bankName" id="bankName" class="form-control cus-control">
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
								<div class="col-md-12 prl-10">
									<div class="d-inlineblock mt-25">
										<input value="Fetch" type="button" id="Fetch"
										class="btn btn-reset searchBtn"/>
									</div>

									<div class="d-inlineblock mt-25">
										<input value="Clear" type="button" id="Clear"
											class="btn btn-reset clearBtn" />
									</div>
								</div>
								</div>
							</div>
							<div class="col-md-2 col-sm-4 col-xs-12"></div>
								<div class="form-group">
									<div class="col-md-12 prl-10">
									   <div class="col-md-2">
										<label>Disbursement Start Date  
										    <input type="text" id="fromDate" class="form-control clsDate"
											name="fromDate" aria-describedby="emailHelp" autocomplete="off"
											placeholder="Disbursement Start Date" onclick="setDateValue(id)"
											style="background-color: #fff;">
										</label></div>
											
											<div class="col-md-2">
											<label style="  display: block; ">Disbursement End Date
											<input type="text" id="toDate" class="form-control"
											name="toDate" aria-describedby="emailHelp" autocomplete="off"
											placeholder="Disbursement End Date" onclick="setDateValue(id)"
											style="background-color: #fff;">
											</label></div>
											
											<div class="col-md-2">
												<label style="  display: block; ">Application No. :
											 	<input type="text" id="applicationNo" class="form-control"
												name="applicationNo" aria-describedby="emailHelp" autocomplete="off">
												</label>
											</div>
											<div class="col-md-2">
												<label style="  display: block; ">Status :
												<select name="status" id="status" class="form-control cus-control">
												   <option value="0">Select</option>
												   <option value="Live">Live</option>
												   <option value="Closed">Closed</option>
												</select></label>
											</div>
											<div class="col-md-2">
												<label style="  display: block; ">Guarantee Issued :
											 	<select name="guaranteeIssue" id="guaranteeIssue" class="form-control cus-control">
												   <option value="0">Select</option>
												   <option value="Yes">Yes</option>
												   <option value="No">No</option>
												</select></label>
											</div>
											<div class="col-md-2 col-sm-4 col-xs-12">
											<div class="form-group">
												<div class="col-md-12 prl-10">
													<div style="margin-top: 14px !important">
														<input value="Search" type="button" id="Search"
										                class="btn btn-reset searchBtn" onclick="$('#loading').show()"/>
													</div>

													<div style="margin-top: 14px !important">
													<input value="Export To Excel" type="button" id="downloadExel"
													class="btn btn-reset downloadExel" />
													</div>
										       </div>
									      </div>
							              </div>
											 
									</div>
								</div>
							</div> 
						</form>
				 		<table id="disbDtlTbl" class="table table-bordered table-hover cus-table mt-9 mb-0 danRpDataTable" style="display: none">
							<thead>
								<tr>
								    <th>Sr. No.</th>
								    <th>Member Id</th>
								    <th>Bank and Branch Name</th>
								    <th>Lender Name and Branch</th>
									<th>Application No</th>
									<th>Loan Account Number</th>
									<th>Applicant Name</th>
									<th>Application Date</th>
									<th>Applicant Gender</th>
									<th>Mobile Number</th>
									<th>Category</th>
									<th>Minority Community</th>
									<th>Account Number</th>
									<th>Disbursed Amount</th>
									<th>Disbursed Date</th>
									<th>Sanction Date</th>
									<th>Sanction Amount</th>
									<th>Loan Tenure</th>
									<th>Moratorium Months</th>
									<th>Rate of Interest</th>
									<th>Name of CIGCLG</th>
									<th>Member of CIGCLG</th>
									<th>SVA Vending Activity Name</th>
									<th>Loan Term</th>
									<th>Status</th>
									<th>Guarantee Ready</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
</body>

<script type="text/javascript">
$('#downloadExel').hide();
getLenderDetails();
function setDateValue(id) {
	var start = new Date("2020-06-01"), end = new Date(), diff = new Date(
			end - start), days = diff / 1000 / 60 / 60 / 24;
	var d = "#" + id;
	$(d).datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat : 'dd/mm/yy',
		minDate : days * -1
	}).attr('readonly', 'readonly').focus();
}

function getLenderDetails()
{
	var selHTML = "<option value='0'>Select</option>";
	$.ajax({
		url : '${pageContext.request.contextPath}/lender-details.html',
		cache : false,
		crossDomain: true,
		type : 'GET',
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		success : function(response) {
	   			    $.each(response, function(key, value) {
	   					selHTML+= "<option value='"+value.lenderCode+"'>"+value.bankName+"</option>";
	   			  }); 
	   					$('#bankName').html(selHTML);   
			},
			error : function(xhr, httpStatusMessage, customErrorMessage) {
				alert(xhr.responseText);
			}
		});
}

   $(document).on('click','#Clear',function() {
	    $("#bankName").val('0');
	    $('#fromDate').val('');
	    $('#toDate').val('');
	    $('#applicationNo').val('');
	    $('#status').val('0');
	    $('#guaranteeIssue').val('0');
	    $('#downloadExel').hide();
	    $("#disbDtlTbl").hide();
   });
   $(document).on('click', '#downloadExel', function() {
	   var bankName = $('#bankName').find(":selected").text(); 
	   var lenderCode = $("#bankName").val();
	   var fromDate = $("#fromDate").val();
	   var toDate = $("#toDate").val();
	   var applicationNo = $("#applicationNo").val();
	   var status = $("#status").val();
		var guaranteeIssue = $("#guaranteeIssue").val();
		if(lenderCode == "0"){
			alert("Please Select Proper Bank Name!!!");
			return false;
		}
		$.ajax({
			url : '${pageContext.request.contextPath}/exportDisbursementDataToExcel.html?lenderCode='+lenderCode+'&fromDate='+fromDate+'&toDate='+toDate+'&applicationNo='+applicationNo+
				    '&bankName='+bankName+'&status='+status+'&guaranteeIssue='+guaranteeIssue,
			cache : false,
			crossDomain : true,
			type : 'GET',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			success : function(result) {
				alert(result);
			},
			error : function(xhr, httpStatusMessage, customErrorMessage) {
				alert(xhr.responseText);
			}
		});
   });

   $(document).on('click', '#Search', function() {
	    $("#disbDtlTbl tbody tr").remove();
	    $("#disbDtlTbl").show();
	    var bankName = $("#bankName").val();
		var fromDate = $("#fromDate").val();
		var toDate = $("#toDate").val();
		var applicationNo = $("#applicationNo").val();
		var status = $("#status").val();
		var guaranteeIssue = $("#guaranteeIssue").val();
		if(bankName == "0"){
			alert("Please Select Proper Bank Name!!!");
			$('#loading').hide();
			return false;
		}
		if(bankName == "1"){
			if(fromDate == "" && toDate == ""){
				alert("Please Select Disbursement Start & End Date!!!");
				$('#loading').hide();
				return false;
			}
		}
		var lenderCode = {"lenderCode":bankName,"fromDate":fromDate,"toDate":toDate,"applicationNo":applicationNo,"status":status,"guaranteeIssue":guaranteeIssue};
		$.ajax({
			url : '${pageContext.request.contextPath}/get-disbursed-detailsData.html',
			cache : false,
			crossDomain : true,
			type : 'POST',
			dataType : 'json',
			contentType : 'application/json; charset=utf-8',
			data : JSON.stringify(lenderCode),
			success : function(result) {
				console.log("Data Is :::" + JSON.stringify(result));
				$('#loading').hide();
				if(result != [] && result.length >0)
				{
					$('#downloadExel').show();
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
							data: "lenderBean.mli_id",
							defaultContent: 'NA'
						},
						{
							data: "lenderBean.lender_bankName",
							defaultContent: 'NA'
						},
						{
							render: function ( data, type, row ) {
							return row.bankAccountDetails.bankName + ',' + row.bankAccountDetails.branchName;
							},
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
							data: "applicationDate",
							defaultContent: 'NA'
						}, 
						{
							data: "personalDetails.applicant_Gender",
							defaultContent: 'NA'
						},
						{
							data: "personalDetails.mobileNo",
							defaultContent: 'NA'
						},
						{
							data: "category",
							defaultContent: 'NA'
						},
						{
							data: "personalDetails.minorityCommunity",
							defaultContent: 'NA'
						},
						{
							data: "bankAccountDetails.accountNo",
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
							data: "sanctionedDt",
							defaultContent: 'NA'
						},
						{
							data: "sanctionedAmount",
							defaultContent: 'NA'
						},
						{
							data: "loanTenure",
							defaultContent: 'NA'
						},
						{
							data: "moratoriumMonths",
							defaultContent: 'NA'
						},
						{
							data: "rateOfInterest",
							defaultContent: 'NA'
						},
						{
							data: "nameOfCIGGLG",
							defaultContent: 'NA'
						},
						{
							data: "memberOfCIGGLG",
							defaultContent: 'NA'
						},
						{
							data: "svaVendingActivityName",
							defaultContent: 'NA'
						},
						{
							data: "loanTerm",
							defaultContent: 'NA'
						},
						{
							data: "status",
							defaultContent: 'NA'
						},
						{
							data: "guaranteeReady",
							defaultContent: 'NA'
						}
						],
							"fnCreatedRow": function (row, data, index) 
							{
							    $('td', row).eq(0).html(index + 1);
								$('td', row).eq(1).attr('mli_id', 'mli_id');
								$('td', row).eq(2).attr('lenderData', 'lenderData');
								$('td', row).eq(3).attr('lender_bankName', 'lender_bankName');
								$('td', row).eq(4).attr('applicationNo', 'applicationNo');
								$('td', row).eq(5).attr('loanAccountNumber', 'loanAccountNumber');
								$('td', row).eq(6).attr('applicant_Name', 'applicant_Name');
								$('td', row).eq(7).attr('applicationDate', 'applicationDate').html(ReplaceCellContent(data.applicationDate));
								$('td', row).eq(8).attr('applicant_Gender', 'applicant_Gender');
								$('td', row).eq(9).attr('mobileNo', 'mobileNo');
								$('td', row).eq(10).attr('category', 'category');
								$('td', row).eq(11).attr('minorityCommunity', 'minorityCommunity');
								$('td', row).eq(12).attr('accountNo', 'accountNo');
								$('td', row).eq(13).attr('disbursedAmount', 'disbursedAmount');
								$('td', row).eq(14).attr('disbursedDt', 'disbursedDt').html(ReplaceCellContent(data.disbursedDt));
								$('td', row).eq(15).attr('sanctionedDt', 'sanctionedDt').html(ReplaceCellContent(data.sanctionedDt)); 
								$('td', row).eq(16).attr('sanctionedAmount', 'sanctionedAmount');
								$('td', row).eq(17).attr('loanTenure', 'loanTenure');
								$('td', row).eq(18).attr('moratoriumMonths', 'moratoriumMonths');
								$('td', row).eq(19).attr('rateOfInterest', 'rateOfInterest');
								$('td', row).eq(20).attr('nameOfCIGGLG', 'nameOfCIGGLG');
								$('td', row).eq(21).attr('memberOfCIGGLG', 'memberOfCIGGLG');
								$('td', row).eq(22).attr('svaVendingActivityName', 'svaVendingActivityName');
								$('td', row).eq(23).attr('loanTerm', 'loanTerm');
								$('td', row).eq(24).attr('status', 'status'); 
								$('td', row).eq(25).attr('guaranteeReady', 'guaranteeReady'); 
							},
					});
				}
				else
				{
					$('#loading').hide();
					$('#downloadExel').hide();
					 var rowCount = $('#disbDtlTbl tr').length;
		   			 var markup = '<tr>';
		   			 markup += '<td align="center" colspan="43">'+ "No Data Found" +'</td>';
		   		     markup += '</tr>';
		   			$("table#disbDtlTbl tbody").append(markup);
				}    
			},
			error : function(xhr, httpStatusMessage, customErrorMessage) {
				 $('#loading').hide();
				 $('#downloadExel').hide();
				 var rowCount = $('#disbDtlTbl tr').length;
	   			 var markup = '<tr>';
	   			 markup += '<td align="center" colspan="43">'+ "No New Data Found To Show...." +'</td>';
	   		     markup += '</tr>';
	   			$("table#disbDtlTbl tbody").append(markup);
			}
		});
	});
 
  $(document).on('click', '#Fetch', function() 
	{
	    $("#disbDtlTbl tbody tr").remove();
	    $("#disbDtlTbl").show();
		var lenderCode = $("#bankName").val();
		var bankName = $('#bankName').find(":selected").text(); 
		if(bankName != "ALL"){
			alert("Please Select ALL to Fetch Data!!!");
			 return false;
		}
		$('#loading').show();
		 var lenderCode = JSON.stringify({"lenderCode":lenderCode,"bankName":bankName});
		 alert("lenderCode" + lenderCode);
		 $.ajax({
			    type : 'GET',
				url : '${pageContext.request.contextPath}/fetch-disbursed-details-data.html',
				cache : false,
				crossDomain : true,
				dataType : 'json',
				contentType : 'application/json; charset=utf-8',
				/*data : lenderCode,
				dataType : 'json',
				processData : false, // tell jQuery not to process the data
				cache : false,
				crossDomain : true, */
				success : function(result) {
					$('#loading').hide();
					alert("Data Fetched  Successfully!!!");
				},
				error : function(xhr, httpStatusMessage,customErrorMessage) {
					alert("Data Fetched Successfully!!!");
					 $('#loading').hide();$('#downloadExel').hide();
					 $("#disbDtlTbl tbody tr").remove();
				}
		});
	});
  
  function ReplaceCellContent(find)
  {
	  find = find.substring(0,find.indexOf('T'));
	  return find;
  }

</script>
</html>