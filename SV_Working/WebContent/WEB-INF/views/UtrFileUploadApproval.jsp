<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>UTR Approval</title>
<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>


<style type="text/css">
.claim h1, .claim h2 {
	color: #4b8abf;
	text-align: center;
	margin: 25px 0;
	font-size: 20px;
}
.hiddenField{
display: none;
}

</style>
<!-- <script>

$(document).on('click','.clearBtn',function() {
	$('select#mliName').val('000000000000');
	$('#claimNumber').val('');
	$('#fromDate').val('');
	$('#toDate').val('');
});

$(document).on('click', '.saveBtn', function(){
	saveUpdateClaimPayment('Do you want to save ?','CGC_M','SAVE');
});

$(document).on('click', '.approveBtn', function(){
	saveUpdateClaimPayment('Once this Pay Id is approved same will not be available for modification/cancellation ,want to proceed!','CG_A','APPROVE');
});

$(document).on('click', '.returnBtn', function(){
	saveUpdateClaimPayment('Do you want to return ?','CG_R','RETURN');
});

function saveUpdateClaimPayment(msg,approvalCode,actionBtn){
	var arrayObj = [];

	$("table tbody").find('input[name="checkedValue"]').each(function(){
        if($(this).is(":checked")){
        	  var mliId     =     $(this).closest('tr').find('td:eq(17)').text();
	     	  var claimNumber =    $(this).closest('tr').find('td:eq(3)').text();
	     	  var claimLodgeDt =   $(this).closest('tr').find('td:eq(4)').text();
	     	  var uploadedOSAC =   $(this).closest('tr').find('td:eq(5)').text();
	    	  var claimSettelAC =  $(this).closest('tr').find('td:eq(6)').text();
	    	  var accountNo =      $(this).closest('tr').find('td:eq(7)').text();
		      var claimSettelDt =  $(this).closest('tr').find('td:eq(8)').text();
		      var userNameId =      $('.userName').val();
		      var isUtrUploaded =  $(this).closest('tr').find('td:eq(14)').text();
		      var userType =      $('.userRole').val();
		      debugger;
		    
		      
	     	  var obj = {
		     			"mliId" :mliId,
		     			"claimNumber" :claimNumber,
		     			"claimLogdeDt":claimLodgeDt,
		     			"uploadedOSAmount" :uploadedOSAC,
		     			"claimSettledAmount" :claimSettelAC,
		     			"acountNumber":accountNo,
		     			"claimSettledDt":claimSettelDt,
		     			"userName":userNameId,
		     			"userRole":userType,
		     			"approvalStatus":approvalCode,
		     			"isUtrUploaded":'Y',
		     			"approvalAction":actionBtn
	     	          };
	     	 arrayObj.push(obj);
          }
    });
	 	var fData = new FormData();
		fData.append('obj', JSON.stringify(arrayObj));
	 
	 if(confirm(msg)){
		 if(arrayObj.length==0){
			   alert("Please select at least one value."); 
			   return false;
		}
		 $.ajax({
				type : 'POST',
				url : '${pageContext.request.contextPath}/save-cgtmse-checker-approval.html',
				processData : false, // tell jQuery not to process the data
				contentType : false, // tell jQuery not to set contentType
				cache : false,
				crossDomain : true,
				data : fData,
				success : function(data) {
					alert(data);
					paymentDetailsData();
				},
				error : function(xhr, httpStatusMessage,customErrorMessage) {
					alert(xhr.responseText);
				}
			});

	 }else{
	      return false;
	  } 
}
 


</script>-->
<script>
utrApprovaltDetailsData();
function utrApprovaltDetailsData(){

	$.ajax({
		url : '${pageContext.request.contextPath}/getUploadUtrApproval.html',
		cache : false,
		crossDomain : true,
		type : 'GET',
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		success : function(result) {
			console.log('utrApprovaltDetailsData',result);
				if(result.length>0){
				$("#myTablePayment tbody tr").remove(); 
				$('#myTablePayment').show();
				
   			}else{
   				$("#myTablePayment tbody tr").remove();
   				$(".approveBtn").prop('disabled', true);
   				$(".returnBtn").prop('disabled', true);
   				var markup = '<tr>';
   				    markup += '<td style="color: red;font-size: larger;text-align: center;" colspan="14">No record found...!</td>';
   				    markup += '</tr>';
   					$("table#myTablePayment tbody").append(markup);  
   				}
							
			$.each(result, function(key, value) {
				
				var rowCount = $('#myTablePayment tr').length;
				var voucherNumber = (value.voucherNumber!=null)?value.voucherNumber :'';
				var approvalStatus = (value.approvalStatus!=null)?value.approvalStatus :'';
				var remark = (value.remark!=null)?value.remark :'';
				var utrNumber = (value.utrNumber!=null)?value.utrNumber :'';
				var paymentDt = (value.paymentDt!=null)?value.paymentDt :'';
				var markup = '<tr>';
		   				markup += '<td>'+rowCount +'</td>';
		   				var checkboxId = 'checkboxId'+key;
		   				markup += '<td><input type="checkbox" id="'+checkboxId+'" class="checkedValue" name="checkedValue" value="" /></td>';
                        markup += '<td>'+ value.mliName +'</td>';
		   			 	markup += '<td>'+ value.financialYear +'</td>';
		   				markup += '<td>'+ value.claimNumber +'</td>';
		   				markup += '<td>'+ value.claimSettlementAmt +'</td>';
		   				markup += '<td>'+ value.claimPaymentDate +'</td>';
		   				markup += '<td>'+ value.utrNo +'</td>';
		   				markup += '<td>'+ value.claimPaymentVoucher +'</td>';
		   			    markup += '<td>'+ approvalStatus +'</td>';
                        markup += '<td class="remarkTd"><textarea rows="3" cols="" value="" class="remark">'+remark+'</textarea></td>';
		   				markup += '<td class="hiddenField">'+ value.isUtrUploaded +'</td>';
		   				markup += '<td class="hiddenField">'+ value.userRole +'</td>';
		   				markup += '<td class="hiddenField">'+ value.userName +'</td>';
		   				markup += '<td class="hiddenField">'+ value.mliId +'</td>';
		   				markup += '<td class="hiddenField">'+ value.approvalCode +'</td>';
		   			    markup += '</tr>';
		   			$("table#myTablePayment tbody").append(markup); 
		   			if(value.approvalCode=='CG_R'){
						
						 $('.remark').prop('disabled', true);
						 $(".approveBtn").prop('disabled', true);
			   			 $(".returnBtn").prop('disabled', true);

						}else if($(this).is(":checked") && value.approvalCode=='CGC_M'){
							alert('CGC_M');
							$(".approveBtn").prop('disabled', false);
				   			 $(".returnBtn").prop('disabled', false);
			   			}
			
		   		});
			
						   
			
		},
		error : function(xhr, httpStatusMessage, customErrorMessage) {
			alert(xhr.responseText);
		}
	});
}

$(document).on('click','#checkedValueAll',function() {
	if($(this).prop("checked")) {
	    $(".checkedValue").prop("checked", true);
	} else {
	    $(".checkedValue").prop("checked", false);
	}  
});
$(document).on('click', '.approveBtn', function(){
	saveUpdateUtrApproval('Once this Pay Id is approved same will not be available for modification/cancellation ,want to proceed!','CG_A','APPROVE');
});

$(document).on('click', '.returnBtn', function(){
	saveUpdateUtrApproval('Do you want to return ?','CG_R','RETURN');
});

function saveUpdateUtrApproval(msg,approvalCode,actionBtn){
	var arrayObj = [];

	$("table tbody").find('input[name="checkedValue"]').each(function(){
        if($(this).is(":checked")){
        	/* markup += '<td>'+rowCount +'</td>';
				var checkboxId = 'checkboxId'+key;
				markup += '<td><input type="checkbox" id="'+checkboxId+'" class="checkedValue" name="checkedValue" value="" /></td>';
            markup += '<td>'+ value.mliName +'</td>';
			 	markup += '<td>'+ value.financialYear +'</td>';
				markup += '<td>'+ value.claimNumber +'</td>';
				markup += '<td>'+ value.claimSettlementAmt +'</td>';
				markup += '<td>'+ value.claimPaymentDate +'</td>';
				markup += '<td>'+ value.utrNo +'</td>';
				markup += '<td>'+ value.claimPaymentVoucher +'</td>';
			    markup += '<td>'+ approvalStatus +'</td>';
            markup += '<td class="remarkTd"><textarea rows="3" cols="" value="" class="remark">'+remark+'</textarea></td>';
				markup += '<td class="hiddenField">'+ value.isUtrUploaded +'</td>';
				markup += '<td class="hiddenField">'+ value.userRole +'</td>';
				markup += '<td class="hiddenField">'+ value.userName +'</td>';
				markup += '<td class="hiddenField">'+ value.mliId +'</td>';
				markup += '<td class="hiddenField">'+ value.approvalCode +'</td>';
			    markup += '</tr>'; */
			    
        	  var claimNumber =    $(this).closest('tr').find('td:eq(4)').text();
        	  var claimPaymentDate =  $(this).closest('tr').find('td:eq(6)').text();
        	  var utrNo =  $(this).closest('tr').find('td:eq(7)').text();
        	  var claimPaymentVoucher =   $(this).closest('tr').find('td:eq(8)').text();
        	  var approvalStatus =      $(this).closest('tr').find('td:eq(9)').text();
        	  var remark =  	   $(this).closest('tr').find('td:eq(10) .remark').val();
        	  var mliId     =     $(this).closest('tr').find('td:eq(14)').text();
	     	  var userNameId =      $('.userName').val();
		      var userType =      $('.userRole').val();
		     debugger;
		    
		     var obj = {
		     			"mliId" :mliId,
		     			"claimNumber" :claimNumber,
		     			"claimPaymentVoucher":claimPaymentVoucher,
		     			"utrNo" :utrNo,
		     			"approvalStatus" :approvalStatus,
		     			"claimPaymentDate":claimPaymentDate,
		     			"userId":userNameId,
		     			"userType":userType,
		     			"remark":remark,
		     			"approvalAction":actionBtn,
		     			"approvalCode" :approvalCode
	     	          };
	     	 arrayObj.push(obj);
          }
    });
	 	var fData = new FormData();
		fData.append('obj', JSON.stringify(arrayObj));
	 
	 if(confirm(msg)){
		 if(arrayObj.length==0){
			   alert("Please select at least one value."); 
			   return false;
		}
		 $.ajax({
				type : 'POST',
				url : '${pageContext.request.contextPath}/save-cgtmse-checker-approval.html',
				processData : false, // tell jQuery not to process the data
				contentType : false, // tell jQuery not to set contentType
				cache : false,
				crossDomain : true,
				data : fData,
				success : function(data) {
					
					utrApprovaltDetailsData();
					alert(data);
		
				},
				error : function(xhr, httpStatusMessage,customErrorMessage) {
					alert(xhr.responseText);
				}
			});

	 }else{
	      return false;
	  } 
}

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
							<h1>UTR Approval</h1>

						</div>
				
					
						
					<div class="d-inlineblock mt-25">
					<%
					String userRoleType= (String)session.getAttribute("userRoleClaimPayment");
					%>
						<input type="hidden" class="userRole" value="<%=userRoleType.substring(1)%>" />
						<!-- <input type="text" class="userRole" value="CHECKER" /> -->
						<!-- <input type="text" class="userRole" value="MAKER" /> -->
						<input type="hidden" class="userName" value="<%=(String) session.getAttribute("userId") %>" />
					</div>

				 		<table id="myTablePayment" class="table table-bordered table-hover cus-table mt-10 mb-0" style="margin-bottom: 45px;display: block;" >
							<thead>
								<tr>
									<th>SR.No.</th>
									<th>SELECT<input type="checkbox" name="checkedValueAll" id="checkedValueAll" value=""></th>
									<th>MLI Name</th>
									<th>Financial Year</th>
									<th>Claim Number</th>
									<th>Claim Settlement Amt</th>
									<th>Payment date</th>
									<th>UTR No</th>
									<th>Claim Payment Voucher</th>
									<th>Status</th>
									<th>Remarks</th>

								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>

							<div style="margin-left: 81%" >
								
									<div class="d-inlineblock mt-25">
									<input value="Approve" type="button"
										class="btn btn-success approveBtn" style="display: block" />
								</div>
								
								<div class="d-inlineblock mt-25">
								<input value="Return" type="button"
									class="btn btn-danger returnBtn" style="display: block" />
									
								</div>
								
							
								
									
				   </div>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>


