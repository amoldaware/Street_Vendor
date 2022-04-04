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
/* $(function() {
	$(".appropriationDt").datepicker({
		dateFormat : 'dd-mm-yy',
		maxDate: new Date()
	});
});

$(function(){
    $('.datepickerclass').on('click', function() {
        $(this).datepicker({showOn:'focus'}).focus();
    });
});
 */
 $(function(){
	    $('.appropriationDt').on('click', function() {
	        $(this).datepicker({dateFormat : 'dd-mm-yy'});
	    });
	});
	
$(function() {
	$("#fromDate").datepicker({
		dateFormat : 'dd-mm-yy',
		maxDate: new Date()
	});
	
	$("#toDate").datepicker({
		dateFormat : 'dd-mm-yy',
		maxDate: new Date()
		
	});
});



getMLiName();
function getMLiName(){
	var selHTML = '';
	debugger;
	$.ajax({
		url : '${pageContext.request.contextPath}/mli-list-manual.html',
		cache : false,
		crossDomain: true,
		type : 'GET',
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		success : function(result) {
			console.log('mlidrop',result);
	   			  $.each(result, function(key, value) {
		   			/*   if(key=='0'){
		   					selHTML+= "<option value='000000000000'>All</option>";
			   			  }
		   			 */
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
	appropriationDetailsData();

	
});


function appropriationDetailsData(){

	var mliId =$('#mliName').val();
	var fromDt  =$('#fromDate').val();
	var toDt    =$('#toDate').val();
	var appropriationCase =$('#appropriationCase').val();
	

	var fData = {
			"mliId" : mliId,
			"fromDt" : dateFormate(fromDt),
			"toDate" : dateFormate(toDt),
			"appropriatedCase":appropriationCase
		};
debugger;

	$.ajax({
		url : '${pageContext.request.contextPath}/search-appropriation.html',
		cache : false,
		crossDomain : true,
		type : 'POST',
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		data : JSON.stringify(fData),
		success : function(result) {
			$('.searchBtn').val('Search');
			if(result.length>0){
				$("#appropriationManualTBL tbody tr").remove(); 
				$('#appropriationManualTBL').show();
				
   			}else{
   				$("#appropriationManualTBL tbody tr").remove();
   				var markup = '<tr>';
   				    markup += '<td style="color: red;font-size: larger;text-align: center;" colspan="14">No record found...!</td>';
   				    markup += '</tr>';
   					$("table#appropriationManualTBL tbody").append(markup);  
   	   			}	
			$.each(result, function(key, value) 
			{
				var rowCount = $('#appropriationManualTBL tr').length;
					var markup = '<tr>';
		   				markup += '<td>'+rowCount +'</td>';
		   				if(value.utrNumber != '' && value.appropriatedCase == 'Y'){
		   				var checkboxId = 'checkboxId'+key;
				   		markup += '<td><input type="checkbox" id="'+checkboxId+'" class="checkedValue" name="checkedValue" checked="checked" disabled="disabled" value=""/></td>';	
		   				}
		   				else{
		   				var checkboxId = 'checkboxId'+key;
			   			markup += '<td><input type="checkbox" id="'+checkboxId+'" class="checkedValue" name="checkedValue" value=""/></td>';	
		   				}
		   			 	markup += '<td>'+ value.mliId +'</td>';
		   			    markup += '<td>'+ value.mliName +'</td>';
		   			 	markup += '<td>'+ value.rpNumber +'</td>';
		   				markup += '<td>'+ value.uploadedDate +'</td>';
		   				markup += '<td>'+ value.virtualAccountNo +'</td>';
		   				markup += '<td>'+ value.initiatedDt +'</td>';
		   				if(value.utrNumber != '' && value.appropriatedCase == 'Y'){
		   				markup += '<td>'+ value.utrNumber +'</td>';	
		   				}
		   				else{
		   				markup += '<td class="remarkTd"><textarea rows="3" cols="" value="" class="utrNo"></textarea></td>';	
		   				}
		   				if(value.appropriationDate != '' && value.appropriatedCase == 'Y'){
		   				markup += '<td>'+ value.appropriationDate +'</td>';		
		   				}
		   				else{
		   					markup += '<td><input value="" size="28" class="form-control cus-control appropriationDt" style="text-align: left" placeholder="eg. dd-mm-yyyy" autocomplete="off" /></td>';	
		   				}
		   			    markup += '</tr>';
		   			$("table#appropriationManualTBL tbody").append(markup);  
		   });
		},
		error : function(xhr, httpStatusMessage, customErrorMessage) {
			$('.searchBtn').val('Search');
			alert(xhr.responseText);
		}
	});
}

$(document).on('click','.clearBtn',function() {
	$('select#mliName').val('000000000000');
	$('#fromDate').val('');
	$('#toDate').val('');
});

$(document).on('click','#checkedValueAll',function() {
	if($(this).prop("checked")) {

			$("table#appropriationManualTBL tbody tr").each(function(index,val){
				debugger;
				var checkboxId='#checkboxId'+index;
				$(checkboxId).prop("checked", true);				

			});
			
		} else {
				$("table#appropriationManualTBL tbody tr").each(function(index,val){
					var checkboxId='#checkboxId'+index;
				 	$(checkboxId).prop("checked", false);
		});
	}  
});

			
$(document).on('click', '.saveBtn', function(){
	saveUpdateClaimPayment('Do you want to save ?');
});


function saveUpdateClaimPayment(msg){
	var arrayObj = [];
	var isRemark=false;
	 debugger;
	$("table tbody").find('input[name="checkedValue"]').each(function(){
        if($(this).is(":checked")){
        	 debugger;
				  var isChecked=false; 
		          var mliId     =      $(this).closest('tr').find('td:eq(2)').text();
	   	     	  var mliName =    $(this).closest('tr').find('td:eq(3)').text();
	   	     	  var rpNumber =   $(this).closest('tr').find('td:eq(4)').text();
	   	     	  var uploadedDt =   $(this).closest('tr').find('td:eq(5)').text();
	   	    	  var virtualACNo =  $(this).closest('tr').find('td:eq(6)').text();
	   	    	  var initiatedDate =      $(this).closest('tr').find('td:eq(7)').text();
	   		   	  var utr =  	   $(this).closest('tr').find('td:eq(8) .utrNo').val();
	   		      var appropriationDate  = $(this).closest('tr').find('td:eq(9) .appropriationDt').val();
	   		      var userNameId =      $('.userNameId').val();
	   		      var userType =      $('.userRole').val();
	   		      if(utr=='' || utr==null){
		   		      alert('Please fill utr...!');
		   		 	  isRemark=true;
		   		      return false;
		   		   }
	   		   if(appropriationDate=='' || appropriationDate==null){
		   		      alert('Please fill appropriation Date...!');
		   		 	  isRemark=true;
		   		      return false;
		   		   }
	   		if(appropriationDate!='' && appropriationDate!=null){
 		    	  var todayDate = new Date();
 		    	  var date = todayDate.getDate()+'-'+(todayDate.getMonth()+1)+'-'+todayDate.getFullYear();
 		    	  if(appropriationDate<=date){
 		    		  
 		    	  }
 		    	  else{
 		    		  alert("Appropriation Date cannot be future date!!");
 		    		  isRemark=true;
 		    		  return false;
 		    	  }
 		      }
	   		 	  isChecked=true;
	   		 	  if(isChecked){
   	     	  	var obj = {
	   		     			"mliId" :mliId,
	   		     			"mliName" :mliName,
	   		     			"rpNumber":rpNumber,
	   		     			"uploadedDate" :uploadedDt,
	   		     			"virtualAccountNo" :virtualACNo,
	   		     			"initiatedDt":initiatedDate,
	   		     			"utrNumber":utr,
	   		     			"appropriationDate":dateFormate(appropriationDate),
	   		     			"userId":userNameId,
	   		     			"userRole":userType
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
				url : '${pageContext.request.contextPath}/save-appropriation.html',
				processData : false, // tell jQuery not to process the data
				contentType : false, // tell jQuery not to set contentType
				cache : false,
				crossDomain : true,
				data : fData,
				success : function(data) {
					alert(data);
					appropriationDetailsData();
				},
				error : function(xhr, httpStatusMessage,customErrorMessage) {
					alert(xhr.responseText);
				}
			});

	 }else{
	      return false;
	  } 
}


$(document).on('click', '.cancelBtn', function(){
	window.location.href = '${pageContext.request.contextPath}/claim-upload.html';
	
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
							<h1>Appropriation Manual</h1>

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
							
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										
											<div class="col-md-6" style="padding-left: 0;"><label style="  display: block; ">FROM DATE:
											<input value="" size="28" id="fromDate"
											class="form-control cus-control fromDate" style="text-align: left;margin-top: 5px"
											placeholder="eg. dd-mm-yyyy" autocomplete="off" />
											
											</label></div>
											
											<div class="col-md-6" style="padding-left: 0;"><label style="  display: block; ">TO DATE:
											 <input value="" size="28" id="toDate"
											class="form-control cus-control" style="text-align: left;margin-top: 5px"
											placeholder="eg. dd-mm-yyyy" autocomplete="off" />
											
											</label></div>
											
											 
									</div>
								</div>
							</div> 

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>APPROPRIATED:<span style="color: red">*</span></label> 
										<select id="appropriationCase" class="form-control cus-control">
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
						</form>
						
					<font color="red" id="errorMsg" size="3"><b></b></font>
					<div class="d-inlineblock mt-25">
					<%
					String userRoleType= (String)session.getAttribute("userRoleClaimPayment");
					String userNameId= (String)session.getAttribute("userNameId");
					%>
						<input type="hidden" class="userRole" value="<%=userRoleType.substring(1)%>" />
						<input type="hidden" class="userRoleType1" value="<%=userRoleType%>" />
						<input type="hidden" class="userNameId" value="<%=userNameId%>" />
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
				 		<table id="appropriationManualTBL" class="table table-bordered table-hover cus-table mt-10 mb-0" style="margin-bottom: 45px;" >
							<thead>
								<tr>
									<th>SR.No.</th>
									<th>SELECT<input type="checkbox" name="checkedValueAll" id="checkedValueAll" value=""></th>
									<th>MLI ID</th>
									<th>MLI NAME</th>
									<th>RP NUMBER</th>
									<th>UPLOADED DATE</th>
									<th>VIRTUAL ACCOUNT NO.</th>
									<th>INITIATED DATE</th>
									<th>UTR</th>
									<th>APPROPRIATION DATE</th>
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
								<input value="Save" type="button" id="saveBtn"
									class="btn btn-success saveBtn" style="display: block" />
							</div>
							
							<div class="d-inlineblock mt-25">
								<input value="Cancel" type="button" id="cancelBtn"
									class="btn btn-reset  cancelBtn" onclick="homePage" />
							</div>

							<%	
								}else if(userRoleType.equalsIgnoreCase("CMAKER")){
									 %>
							
							<div class="d-inlineblock mt-25">
								<input value="Save" type="button"
									class="btn btn-success saveBtn" style="display: block" />
							</div>
							
							<div class="d-inlineblock mt-25">
								<input value="Cancel" type="button"
									class="btn btn-reset  cancelBtn" onclick="homePage" />
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


