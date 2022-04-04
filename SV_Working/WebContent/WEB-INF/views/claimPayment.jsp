<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%-- <script src="<%=request.getContextPath()%>/js/jquery.js"></script> --%>
<title>Portfolio Creation</title>
<!-- <script src="js/jquery-1.11.1.js" type="text/javascript"></script> -->
<%-- <script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script> --%>
<!-- <script type="text/javascript"
	src="jquery-ui-1.10.3/ui/jquery.ui.datepicker.js"></script> -->
<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">

 <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- <script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script> -->
<%-- <link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css"> --%>

<style type="text/css">
/* 
.btn.btn-primary {
    transition: all 0.2s;
    color: white;
    border-radius: 2px;
}
.btn.btn-primary :hover {
    background-color: #f05926;
    color: white;
    box-shadow: 1px 3px 3px #4a4a4a9e;
}

.btn.btn-success {
    transition: all 0.2s;
    color: white;
    border-radius: 2px;
}
.btn.btn-success :hover {
    background-color: #f05926;
    color: white;
    box-shadow: 1px 3px 3px #ca7676;
} */

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
<script>
$(function() {
	$("#toDate").datepicker({
		dateFormat : 'dd-mm-yy',
		maxDate: new Date()
		
	});
	$("#fromDate").datepicker({
		dateFormat : 'dd-mm-yy',
		maxDate: new Date()
	});
});

getMLiName();
function getMLiName(){
	var selHTML = '';
	
	$.ajax({
		url : '${pageContext.request.contextPath}/mli-list.html',
		cache : false,
		crossDomain: true,
		type : 'GET',
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		success : function(result) {
			console.log('mlidrop',result);
	   			  $.each(result, function(key, value) {
		   			  
		   			selHTML+= "<option value='"+value.mliId+"'>"+value.mliName+"</option>";
	   			  });
	   					$('#mliName').html(selHTML);   
			},
			error : function(xhr, httpStatusMessage, customErrorMessage) {
				alert(xhr.responseText);
			}
		});
	}

function dateFormate(date){
	debugger;
	if(date==""){
		 return "";
		}else{
			 var dateParts = date.split("-");
			 return dateParts[2]+'-'+dateParts[1]+'-'+dateParts[0];
	}
	
}

$(document).on('click','.searchBtn',function() {
	$(this).val('Wait...');
	paymentDetailsData();

	
});


function paymentDetailsData(){

	var mliId =$('#mliName').val();
	var claimNo =$('#claimNumber').val();
	var utrUpdated    =$('#utrUpdated').val();
	var fromDt  =$('#fromDate').val();
	var toDt    =$('#toDate').val();
	var claimRadioDate = $("input[type='radio'][name='claim']:checked").val();
	var userRole = $('.userRole').val();
	var claimStatus =   $('#claimStatus').val();
	var btnFlag=true;
	

	var fData = {
			"mliId" : mliId,
			"claimNumber" : claimNo,
			"utrUpdated" : utrUpdated,
			"fromDt" : dateFormate(fromDt),
			"toDate" : dateFormate(toDt),
			"claimDateFlag":claimRadioDate,
			"userRole": userRole,
			"approvalStatus":claimStatus
		};


	$.ajax({
		url : '${pageContext.request.contextPath}/search-claim-payment-data.html',
		cache : false,
		crossDomain : true,
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : JSON.stringify(fData),
		success : function(result) {
			$('.searchBtn').val('Search');
			if(result.length>0){
				$("#myTablePayment tbody tr").remove(); 
				$('#myTablePayment').show();
				
   			}else{
   				$("#myTablePayment tbody tr").remove();
   				var markup = '<tr>';
   				    markup += '<td style="color: red;font-size: larger;text-align: center;" colspan="14">No record found...!</td>';
   				    markup += '</tr>';
   					$("table#myTablePayment tbody").append(markup);  
   					$('.downloadExel').hide();
   	   			}	
	   			var countGA=0;
			$.each(result, function(key, value) {

				debugger;
				var rowCount = $('#myTablePayment tr').length;
				var voucherNumber = (value.voucherNumber!=null)?value.voucherNumber :'';
				var approvalStatus = (value.approvalStatus!=null)?value.approvalStatus :'';
				var remark = (value.remark!=null)?value.remark :'';
				var utrNumber = (value.utrNumber!=null)?value.utrNumber :'';
				var paymentDt = (value.paymentDt!=null)?value.paymentDt :'';

					
				
				if(value.approvalCode=='CG_A'){
					if($('.userRoleType1').val()=='CMAKER' || $('.userRoleType1').val()=='CCHECKER'){
						$('.downloadExel').show();
						//added by Vivek
						countGA++;
					}
					}else{
						$('.downloadExel').hide();
						
					}


					var markup = '<tr>';
		   				markup += '<td>'+rowCount +'</td>';
		   				var checkboxId = 'checkboxId'+key;

		   				if($('.userRole').val()=='MAKER'  && value.approvalCode=='CGC_M'){
		   					markup += '<td><input type="checkbox" id="'+checkboxId+'" class="checkedValue" name="checkedValue" value="" checked disabled /></td>';

				   		}else if($('.userRole').val()=='MAKER'  && value.approvalCode=='CG_R'){
			   				markup += '<td><input type="checkbox" id="'+checkboxId+'" class="checkedValue" name="checkedValue" value="" checked /></td>';

					   	}else if($('.userRole').val()=='MAKER'  && value.approvalCode==null){
		   					markup += '<td><input type="checkbox" id="'+checkboxId+'" class="checkedValue" name="checkedValue" value="" /></td>';

					   	}else if($('.userRole').val()=='MAKER'  && value.approvalCode=='CG_A'){
		   					markup += '<td><input type="checkbox" id="'+checkboxId+'" class="checkedValue" name="checkedValue" value="" checked disabled/></td>';

					   	}
					   	else if($('.userRole').val()=='CHECKER'  && value.approvalCode=='CG_A'){
		   					markup += '<td><input type="checkbox" id="'+checkboxId+'" class="checkedValue" name="checkedValue" value="" checked disabled /></td>';

					   	}else if($('.userRole').val()=='CHECKER'  && value.approvalCode=='CGC_M'){
		   					markup += '<td><input type="checkbox" id="'+checkboxId+'" class="checkedValue" name="checkedValue" value="" checked /></td>';

					   	}else if($('.userRole').val()=='CHECKER'  && value.approvalCode=='CG_R'){
			   				markup += '<td><input type="checkbox" id="'+checkboxId+'" class="checkedValue" name="checkedValue" value="" checked disabled  /></td>';

					   	}else if($('.userRole').val()=='CHECKER'  && value.approvalCode==null){
		   					markup += '<td><input type="checkbox" id="'+checkboxId+'" class="checkedValue" name="checkedValue" value="" disabled/></td>';

					   	}
					   	 
					   	
		   				
		   			 	markup += '<td>'+ value.mliName +'</td>';
		   			 	markup += '<td>'+ value.claimNumber +'</td>';
		   				markup += '<td>'+ value.claimLogdeDt +'</td>';
		   				markup += '<td>'+ value.uploadedOSAmount +'</td>';
		   				markup += '<td>'+ value.claimSettledAmount +'</td>';
		   				markup += '<td>'+ value.acountNumber +'</td>';
		   				markup += '<td>'+ value.claimSettledDt +'</td>';
		   				markup += '<td>'+ voucherNumber +'</td>';
		   				markup += '<td>'+ approvalStatus +'</td>';

						if($('.userRole').val()=='MAKER'  && value.approvalCode=='CG_R'){
				   			markup += '<td class="remarkTd"><textarea rows="3" cols="" value="" class="remark" readOnly>'+remark+'</textarea></td>';

					   	}else if($('.userRole').val()=='MAKER'  && value.approvalCode==null){
					   		markup += '<td class="remarkTd"><textarea rows="3" cols="" value="" class="remark" readOnly>'+remark+'</textarea></td>';

					   	}else if($('.userRole').val()=='MAKER'  && value.approvalCode=='CGC_M'){
					   		markup += '<td class="remarkTd"><textarea rows="3" cols="" value="" class="remark" readOnly>'+remark+'</textarea></td>';

					   	}else if($('.userRole').val()=='MAKER'  && value.approvalCode=='CG_A'){
					   		markup += '<td class="remarkTd"><textarea rows="3" cols="" value="" class="remark" readOnly>'+remark+'</textarea></td>';

					   	}
					   	else if($('.userRole').val()=='CHECKER'  && value.approvalCode=='CG_A'){
					   		markup += '<td class="remarkTd"><textarea rows="3" cols="" value="" class="remark" readOnly>'+remark+'</textarea></td>';

					   	}else if($('.userRole').val()=='CHECKER'  && value.approvalCode=='CGC_M'){
					   		markup += '<td class="remarkTd"><textarea rows="3" cols="" value="" class="remark">'+remark+'</textarea></td>';

					   	}else if($('.userRole').val()=='CHECKER'  && value.approvalCode=='CG_R'){
					   		markup += '<td class="remarkTd"><textarea rows="3" cols="" value="" class="remark" readOnly>'+remark+'</textarea></td>';

					   	}else if($('.userRole').val()=='CHECKER'  && value.approvalCode==null){
					   		markup += '<td class="remarkTd"><textarea rows="3" cols="" value="" class="remark" readOnly>'+remark+'</textarea></td>';

					   	}

		   				markup += '<td>'+ utrNumber +'</td>';
		   				markup += '<td>'+ paymentDt +'</td>';
		   				markup += '<td class="hiddenField">'+ value.isUtrUploaded +'</td>';
		   				markup += '<td class="hiddenField">'+ value.userRole +'</td>';
		   				markup += '<td class="hiddenField">'+ value.userName +'</td>';
		   				markup += '<td class="hiddenField">'+ value.mliId +'</td>';
		   				markup += '<td class="hiddenField">'+ value.approvalCode +'</td>';
		   			    markup += '</tr>';
		   			$("table#myTablePayment tbody").append(markup);  
		   });
			   
			if(countGA==result.length){
				$('.approveBtn').attr("disabled", true);
				$('.returnBtn').attr("disabled", true);
				$('.saveBtn').attr("disabled", true);
				$('.cancelBtn').attr("disabled", true);
				
				}else{
					$('.approveBtn').attr("disabled", false);
					$('.returnBtn').attr("disabled", false);
					$('.saveBtn').attr("disabled", false);
					$('.cancelBtn').attr("disabled", false);
				}
			nMakerAndNChecker();
		},
		error : function(xhr, httpStatusMessage, customErrorMessage) {
			$('.searchBtn').val('Search');
			alert(xhr.responseText);
		}
	});
}

$(document).on('click','.clearBtn',function() {
	$('select#mliName').val('000000000000');
	$('#claimNumber').val('');
	$('#fromDate').val('');
	$('#toDate').val('');
});

function nMakerAndNChecker(){
	
	if($('.userRoleType1').val()=='NMAKER' || $('.userRoleType1').val()=='NCHECKER'){
		$('#checkedValueAll').attr("disabled", true);
		$('.checkedValue').attr("disabled", true);

	}
	
}
$(document).on('click','#checkedValueAll',function() {
	if($(this).prop("checked")) {

			$("table#myTablePayment tbody tr").each(function(index,val){
				debugger;
				var approvalCode= $(this).closest('tr').find('td:eq(18)').text();
				var checkboxId='#checkboxId'+index;
				
				
					if(approvalCode=='null' && $('.userRole').val()=='MAKER'){
					 	$(checkboxId).prop("checked", true);

					}else if(approvalCode=='CG_R' && $('.userRole').val()=='MAKER'){
						$(checkboxId).prop("checked", true);
					 
					}else if(approvalCode=='CGC_M' && $('.userRole').val()=='CHECKER'){
						$(checkboxId).prop("checked", true);
					}
			});
			} else {
				$("table#myTablePayment tbody tr").each(function(index,val){
					var approvalCode= $(this).closest('tr').find('td:eq(18)').text();
					var checkboxId='#checkboxId'+index;
		
					if(approvalCode=='null' && $('.userRole').val()=='MAKER'){
					 	$(checkboxId).prop("checked", false);
		
					}else if(approvalCode=='CG_R' && $('.userRole').val()=='MAKER'){
					 	$(checkboxId).prop("checked", false);
					 
					}else if(approvalCode=='CGC_M' && $('.userRole').val()=='CHECKER'){
						 $(checkboxId).prop("checked", false);
					}
		});
	}  
});

			
$(document).on('click', '.saveBtn', function(){
	saveUpdateClaimPayment('Do you want to save ?','CGC_M','SAVE');
});

$(document).on('click', '.approveBtn', function(){
	saveUpdateClaimPayment('Once this Claim is approved for Payment Initiation, same will not be available for modification/cancellation ,want to proceed!','CG_A','APPROVE');
});

$(document).on('click', '.returnBtn', function(){
	saveUpdateClaimPayment('Do you want to return ?','CG_R','RETURN');
});


function saveUpdateClaimPayment(msg,approvalCodeAction,actionBtn){
	var arrayObj = [];
	var isRemark=false;

	$("table tbody").find('input[name="checkedValue"]').each(function(){
        if($(this).is(":checked")){
        	 debugger;
		 var isChecked=false; 
       	 var approvalCode     =  $(this).closest('tr').find('td:eq(18)').text();

             if((approvalCode=='null' || approvalCode=='CG_R' ) &&  $('.userRole').val()=='MAKER'){
            	 isChecked=true;                
	          	  var mliId     =     $(this).closest('tr').find('td:eq(17)').text();
	  	     	  var claimNumber =    $(this).closest('tr').find('td:eq(3)').text();
	  	     	  var claimLodgeDt =   $(this).closest('tr').find('td:eq(4)').text();
	  	     	  var uploadedOSAC =   $(this).closest('tr').find('td:eq(5)').text();
	  	    	  var claimSettelAC =  $(this).closest('tr').find('td:eq(6)').text();
	  	    	  var accountNo =      $(this).closest('tr').find('td:eq(7)').text();
	  		      var claimSettelDt =  $(this).closest('tr').find('td:eq(8)').text();
	  		      var remark =  	   $(this).closest('tr').find('td:eq(11) .remark').val();
	  		      var userNameId =      $('.userName').val();
	  		      var isUtrUploaded =  $(this).closest('tr').find('td:eq(14)').text();
	  		      var userType =      $('.userRole').val();

              }else if(approvalCode=='CGC_M' &&  $('.userRole').val()=='CHECKER'){
            	 
	           	  var mliId     =     $(this).closest('tr').find('td:eq(17)').text();
	   	     	  var claimNumber =    $(this).closest('tr').find('td:eq(3)').text();
	   	     	  var claimLodgeDt =   $(this).closest('tr').find('td:eq(4)').text();
	   	     	  var uploadedOSAC =   $(this).closest('tr').find('td:eq(5)').text();
	   	    	  var claimSettelAC =  $(this).closest('tr').find('td:eq(6)').text();
	   	    	  var accountNo =      $(this).closest('tr').find('td:eq(7)').text();
	   		      var claimSettelDt =  $(this).closest('tr').find('td:eq(8)').text();
	   		   	  var remark =  	   $(this).closest('tr').find('td:eq(11) .remark').val();
	   		      var userNameId =      $('.userName').val();
	   		      var isUtrUploaded =  $(this).closest('tr').find('td:eq(14)').text();
	   		      var userType =      $('.userRole').val();
	   		      if(remark=='' || remark==null){
		   		      alert('Please fill remark...!');
		   		 	  isRemark=true;
		   		      return false;
		   		   }
	   		 	  isChecked=true;
             }
             if(isChecked){
   	     	  	var obj = {
	   		     			"mliId" :mliId,
	   		     			"claimNumber" :claimNumber,
	   		     			"claimLogdeDt":claimLodgeDt,
	   		     			"uploadedOSAmount" :uploadedOSAC,
	   		     			"claimSettledAmount" :claimSettelAC,
	   		     			"acountNumber":accountNo,
	   		     			"claimSettledDt":claimSettelDt,
	   		     			"remark":remark,
	   		     			"userName":userNameId,
	   		     			"userRole":userType,
	   		     			"approvalStatus":approvalCodeAction,
	   		     			"isUtrUploaded":'Y',
	   		     			"approvalAction":actionBtn
   	     	          };
		   	     	 arrayObj.push(obj);
                 }
          }
    });
    		if(isRemark){
	   		      return false;
        		}
    		
	 	var fData = new FormData();
		fData.append('obj', JSON.stringify(arrayObj));
	 
	 if(confirm(msg)){
		 if(arrayObj.length==0){
			   alert("Please select at least one checkbox...!"); 
			   return false;
		}
		 $.ajax({
				type : 'POST',
				url : '${pageContext.request.contextPath}/save-cgtmse-maker-checker.html',
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


$(document).on('click', '#saveBtn', function(){
	var termsCondition = document.getElementById("terms");
		alert(termsCondition);
	
	if(termsCondition.checked==false){
		alert("Please indicate that you accept the Terms and Conditions.");
		return false;
	}
	 var arrayObj = [];
	 
	 $("table tbody").find('input[name="checkedValue"]').each(function(){
         if($(this).is(":checked")){
	        	 
	     	  var riskId =  $(this).closest('tr').find('td:eq(1)').text();
	     	  var riskProfile =  $(this).closest('tr').find('td:eq(2)').text();
	     	  var riskParameter =  $(this).closest('tr').find('td:eq(3)').text();
	     	  var riskKycId = $('.kycId').val();
	     	  var obj = {
		     			"riskId" :parseInt(riskId),
		     			"riskProfile" :riskProfile,
		     			"riskDesc":riskParameter,
		     			"kycId":riskKycId
	     	          };
	     	 arrayObj.push(obj);
           }
        
     });
	 
	 if(confirm("Are you sure you want to close this ?")){
		 if(arrayObj.length==0){
			   alert("Please select at least one value."); 
			   return false;
		 }
	    //var riskTypes = $('.riskTypes').val();
	    // alert("riskTypes "+riskTypes);
	     
	 $.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}/saveRiskCat/"+$('.riskTypes').val(),
			contentType :"application/json; charset=utf-8",
			crossDomain: true,
			dataType : "json",
			data : JSON.stringify(arrayObj),
			success : function(data) {
						if(data.status){
							//alert(data.msg);
							 window.close();
						}else{
							alert("Something went wrong...! ");
						}
						
			},
			error : function(data) {
				alert("Something went wrong...! ");
			}
		});
	   }else{
	        return false;
	    } 
});



function downloadExelFile(){
	 var arrayObj = [];
	 $("table#myTablePayment tbody").find('input[name="checkedValue"]').each(function(){

		 if($(this).is(":checked")){
       	 var mliId= $(this).closest('tr').find('td:eq(17)').text();
	     	 arrayObj.push(mliId);
          }
    });
	    console.log(arrayObj);
	    if(arrayObj.length>0){
	    	window.location.href = '${pageContext.request.contextPath}/download-claim-payment.html?memberId='+ arrayObj;
		}
}

$(document).on('click', '.cancelBtn', function(){
	window.location.href = '${pageContext.request.contextPath}/claim-payment.html';
	
});



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
							<h1>Claim Payment</h1>

						</div>
						<form method="GET" id="A" class="form-horizontal">

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>MLI NAME:<span style="color: red">*</span></label> 
										<select id="mliName" class="form-control cus-control">
										</select>
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label> CLAIM NUMBER</label> <input type="text" value=""
											placeholder="Type Here.." class="form-control cus-control"
											id="claimNumber" style="top-margine: 50" />
									</div>
								</div>
							</div>
							
							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>CLAIM STATUS:<span style="color: red">*</span></label> 
										<select id="claimStatus" class="form-control cus-control">
											<option value="ALL" label="ALL" />
											<option value="A" label="APPROVE" />
											<option value="P" label="PENDING" />
										</select>
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>UTR UPDATED:<span style="color: red">*</span></label> 
										<select id="utrUpdated" class="form-control cus-control">
											<option value="N" label="NO" />
											<option value="Y" label="YES" />
										</select>
									</div>
								</div>
							</div>
							
							<div class="col-md-2 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<div class="d-inlineblock mt-25">
										<input value="Search" type="button"
											class="btn btn-reset searchBtn" />
									</div>

									<div class="d-inlineblock mt-25">
										<input value="Clear" type="button"
											class="btn btn-reset clearBtn" />
									</div>
								</div>
							</div>
						</div>

							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label style="padding-right:20px;">CLAIM LODGEMENT DATE  <input type="radio"
											name="claim" value="CL" checked="checked" id="claimLodgementDate"
											style="margin-left:5px;   margin-top: unset;"></label> 
											<label style=""> 
											  CLAIM PAYMENT DATE   <input type="radio" name="claim" value="CP" id="claimPaymentDate"
											style="margin-left:5px; top-margine: 50; margin-top: unset;"></label>
											
											<div class="col-md-6" style="padding-left: 0;"><label style="  display: block; ">From Date
											<input value="" size="28" id="fromDate"
											class="form-control cus-control" style="text-align: left"
											placeholder="eg. dd-mm-yyyy" autocomplete="off" />
											
											</label></div>
											
											<div class="col-md-6" style="padding-left: 0;"><label style="  display: block; ">To Date
											 <input value="" size="28" id="toDate"
											class="form-control cus-control" style="text-align: left"
											placeholder="eg. dd-mm-yyyy" autocomplete="off" />
											
											</label></div>
											
											 
									</div>
								</div>
							</div> 

						</form>
						<!-- <div class="d-inlineblock mt-25">
							<input value="Search" type="button" class="btn btn-reset searchBtn" />
						</div>
						
						<div class="d-inlineblock mt-25">
							<input value="Clear" type="button" class="btn btn-reset clearBtn" />
						</div> -->
						<font color="red" id="errorMsg" size="3"><b></b></font>
						<%-- <c:if test="${!empty mlisList}"> --%>
					<div class="d-inlineblock mt-25">
					<%
					String userRoleType= (String)session.getAttribute("userRoleClaimPayment");
					%>
						<input type="hidden" class="userRole" value="<%=userRoleType.substring(1)%>" />
						<input type="hidden" class="userRoleType1" value="<%=userRoleType%>" />
						<!-- <input type="text" class="userRole" value="CHECKER" /> -->
						<!-- <input type="text" class="userRole" value="MAKER" /> -->
						<input type="hidden" class="userName" value="<%=(String) session.getAttribute("userId") %>" />
					</div>

	<div class="clearfix"></div>
	<div class="d-inlineblock mt-25" >
	<button style="border:none !important; cursor: allowed;">
		    						<img src="images/excel.png" alt="" data-toggle="tooltip" class="downloadExel" style="display: none" title="Export To Excel" onclick="return downloadExelFile()">
		    					</button>	
	</div>
				 		<table id="myTablePayment" class="table table-bordered table-hover cus-table mt-10 mb-0" style="margin-bottom: 45px;display: block;" >
							<thead>
								<tr>
									<th>SR.No.</th>
									<th>SELECT<input type="checkbox" name="checkedValueAll" id="checkedValueAll" value=""></th>
									<th>MLI NAME</th>
									<th>CLAIM NUMBER</th>
									<th>CLAIM LODGMENT DATE</th>
									<th>UPLOADED OS AMOUNT</th>
									<th>CLAIM SETTLED AMOUNT</th>
									<th>ACCOUNT NO.</th>
									<th>CLAIM SETTLEMENT DATE</th>
									<th>VOUCHER NUMBER</th>
									<th>APPROVAL STATUS</th>
									<th>REMARK</th>
									<th>UTR NUMBER</th>
									<th>PAYMENT DATE / BANK VALUE DATE</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>

							<div style="margin-left: 81%" >
							<%
								if(userRoleType.equalsIgnoreCase("CCHECKER")){
								%>
								<div class="d-inlineblock mt-25">
									<input value="Cancel" type="button"
										class="btn btn-reset  cancelBtn"  onclick="fuhomePage" />
								</div>
									
									<div class="d-inlineblock mt-25">
									<input value="Approve" type="button"
										class="btn btn-success approveBtn" style="display: block" />
								</div>
								
								<div class="d-inlineblock mt-25">
								<input value="Return" type="button"
									class="btn btn-danger returnBtn" style="display: block" />
								</div>
								<%	
								}else if(userRoleType.equalsIgnoreCase("CMAKER")){
									 %>
									 <div class="d-inlineblock mt-25">
									<input value="Cancel" type="button"
										class="btn btn-reset  cancelBtn" onclick="homePage"/>
								  </div>
									<div class="d-inlineblock mt-25">
									<input value="Save" type="button"
										class="btn btn-success saveBtn" style="display: block" />
								</div>
							<%		
								}
							%>
									
							</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>


