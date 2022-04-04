<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<title>Portfolio Creation</title>
<link href="<%=request.getContextPath()%>/js/jquery-ui.css"
	rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">



<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring MVC Form Handling</title>


<script type="text/javascript">
  	function refreshTable(){
			alert(" hello  ");
	  	  	
  		//$("#searchDisburseData").click(function(){
  			 $("#sanctionDataTable").find("tr:not(:first)").remove();
  			 
  			var portfolioNo = $('#portfolioNo').val();
  			var loanAccountNumber = $('#loanAccountNumber').val();

  			alert(portfolioNo+"   "+loanAccountNumber);
  	        var tableCre ;
  				
  				         $.ajax({			 
  				         type: "POST",		 
  				         url: "./searchCheckerDataForSanction.html",
  				         data: "portfolioNo=" + portfolioNo + "&loanAccountNumber=" + loanAccountNumber,
  				         success: function(response){
  				        	 var s1 = [];
  				        	 var row = [];
  				        	 var modifyAmount='0';
  						          $.each(response, function (key,value) {
  										for (var i in value) {	
  											 if (key=='result') {	
  	  										if(value[i].modifySanctionAmount==null){
  	  											modifyAmount='0';
  	  	  									}else{
  	  	  										modifyAmount=value[i].modifySanctionAmount;
  	  	  	  								}
  										  tableCre='<tr><td>'+value[i].loanAccountNumber+			            	 
											'</td><td>'+value[i].amountOfDisbursement+			            	 
											'</td><td>'+value[i].dateOfDisbursement+
											'</td><td align="right">'+modifyAmount+
											'</td><td align="center">'+'<input type="checkbox"  id="chcktbl'+i+'"class="chcktbl"  />'+
											'</td></tr>';	

  												$("#sanctionDataTable").append(tableCre);
  												//$("#datePicker"+i).datepicker({ dateFormat: 'dd/mm/yy' });		

  													$('.chcktbl').click(function () {
  											        if (this.checked == false) {
  											            $('#selectAll:checked').attr('checked', false);
  											        }
  											       
  											    });
  											}
  										}
  										});
  						       
  									
  				         },
  				 
  				         error: function(e){
  				         //alert('Error: ' + e.responseText);
  				         alert('Error: ' + e);
  						 
  				         }
  				 
  				         });
  				            $('#selectAll:checked').attr('checked', false);
  					        
  		//	});
  	}
	//var dd=document.getElementById("name").value;
$(document).ready(function(){
	
		$('#selectAll').click(function () {
	        if (this.checked == false) {
	            $('.chcktbl:checked').attr('checked', false);
	        }
	        else {
	            $('.chcktbl:not(:checked)').attr('checked', true);
	        }
	    });
	    
		$('#onSubmit').click(function () {
			var dataList = [],
	        $selectedCBs = $('.sanctionDataTable input[type="checkbox"]:checked');
			
	    if ($selectedCBs.length === 0) {
	        alert("No rows selected to Approve.");
	        return;
	    }else{
	       
	    	if($('#selectAll:checked').is(':checked') ){		    	
		    	$selectedCBs.each(function(key) {
			    	if(key===0){

				    	}else{
				    		var loanAccountNumber=$(this).closest('tr').children(':first').text();
				        	 dataList.push(loanAccountNumber);	
				    	}      
		        	                  	   
		   		 });
	        	}else{
		  	  	  $selectedCBs.each(function(key) {      
		        	var loanAccountNumber=$(this).closest('tr').children(':first').text();
		        	 dataList.push(loanAccountNumber);	                  	   
		   		  });
	       }
	   if(dataList.length>0 ){
	    $.ajax({			 
	         type: "POST",		 
	         url: "./sanctionCheckerApproval.html",
	         data: "disbursedData=" + dataList,
	         success: function(response){
		    	if(response.result===1){
		        	refreshTable();
		        	clearField();
		        	alert("Data Update Successfull!!!");
		        	
	        	}
	        	
			     	
	         },
	 
	         error: function(e){
	        	 if(response.success==0){
	    	         alert('Error: ' + e);
		        	}
			 
	         }
	 
	         });
	    	     }
	    	}
       	    
    	});
		$('#onReject').click(function () {
			
			var rejectDataList = [],
	        $selectedCBs = $('.sanctionDataTable input[type="checkbox"]:checked');
			
	    if ($selectedCBs.length === 0) {
	        alert("No rows selected to delete.");
	        return;
	    }else{
	    	var remarkDetails = $('#remark').val();
			if(remarkDetails==''){
				alert('Remark is mandatory');
			}else{

				if($('#selectAll:checked').is(':checked') ){		    	
			    	$selectedCBs.each(function(key) {
				    	if(key===0){

					    	}else{
					    		var loanAccountNumber=$(this).closest('tr').children(':first').text();
					    		rejectDataList.push(loanAccountNumber);	
					    	}      
			        	                  	   
			   		 });
		        	}else{
			  	  	  $selectedCBs.each(function(key) {      
			        	var loanAccountNumber=$(this).closest('tr').children(':first').text();
			        	rejectDataList.push(loanAccountNumber);	                  	   
			   		  });
		       }

				
					   if(rejectDataList.length>0 && remarkDetails!==''){
			         $.ajax({			 
				         type: "POST",		 
				         url: "./sanctionCheckerRejection.html",
				         data: "rejectDataList=" + rejectDataList+"&remark="+remarkDetails,
				         success: function(response){
				       
				        	if(response.result===1){
					        	refreshTable();
					        	clearField();
					        	alert("Data Rejected Successfull!!!");
					        	
				        	}
						     	
				         },
				 
				         error: function(e){
				         alert('Error: ' + e);
						 
				         }
				 
				         });
			   }
			}
		    }
	 
		});
	   
		
    
	});
	
function clearField(){
	var remarkDetails = $('#remark').val('');
	
}
	
</script>

</head>



<body bgcolor="#E0FFFF">
<form:form method="POST" action=""
	id="submitFormId">
	<!-- <div
		STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;"> -->
	
	<hr>
	<table cellpadding=5 cellspacing=5 align=center width=90%>
		<tr>
			<td align="center"><b>Approval of Sanction Modification</b></td>
		</tr>
		<tr>
			<td colspan=5 align="center">
			<table border="0">
				<tr>
					<td>Portfolio No :</td>
					<td><form:select path="portfolioNo" id="portfolioNo">
						<form:option value="NONE" label="--------Select-------" />
						<form:options items="${list}" itemValue="portfolioNo" />
					</form:select></td>
					<td>&nbsp;</td>
					<td>Loan Account No:</td>
					<td>&nbsp;<form:input path="loanAccountNumber"
						id="loanAccountNumber" value="" /></td>
					<td>&nbsp;</td>
					<td><input type="button" value="Search"
						onclick="refreshTable()" id="searchDisburseData"></td>
					<td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<div align="center" id="scrollDiv"
				 font-size: 15px; overflow: auto;">
			<table border="1" id="sanctionDataTable" class="sanctionDataTable"
				height="5px" width="65%">
				<tr>
					<th>Loan Account No</th>
					<th>Sanctioned Amount</th>
					<th>Amount of Disbursement till Date</th>
					<th>Modify Sanction Amount</th>
					<th>For Modification<br>
					<div align="center"><input type="checkbox" id="selectAll" /></div>
					</th>
				</tr>

			</table>
			</div>
			</td>
		</tr>

		<tr>
			<td align="center" valign="top">Remark : <textarea id="remark" name="remark" rows="2" cols="50"></textarea></td>
		</tr>
		<tr>
			<td align="center"><input class="button" id="onSubmit" value="Approve"
				align="left">&nbsp;&nbsp;<input class="button" id="onReject"
				value="Reject" align="left">&nbsp;&nbsp;<input type="submit" value=" Exit "
				onclick="exitMLIDetails()" class="button" /></td>
		</tr>

	</table>

</form:form>
</body>






<script type="text/javascript">
function exitMLIDetails() {
	alert('Exit');
	document.getElementById('submitFormId').action = "/SV/nbfcCheckerHomeBack.html";
	document.getElementById('submitFormId').submit();
}
</script>
</html>

