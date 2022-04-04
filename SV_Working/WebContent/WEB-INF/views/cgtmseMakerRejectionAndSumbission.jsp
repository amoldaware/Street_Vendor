<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String excelRecordNotFound1=(String)request.getAttribute("excelRecordNotFound");
    //String rSaveSucessFully=(String)request.getAttribute("recordSaveSucessfully");
   
%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script  src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
	</head>
<body>
<div class="main-section">
<div class="container-fluid">
<!-- <div class="row"> -->
	<div>
<nav aria-label="breadcrumb">
  <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/cgtmseMakerForBacthApprovalRM.html">Batch Approval</a></li>
    <li class="breadcrumb-item"><a href="/SV/cgtmseMakerForBatchApprovalUploadedBatchFileRM.html?subPortfolioDtlNumber=${subPortfolioDtlNoKey}&statusNCA=${statusKey}&totalNoOfUploadBatchFile=${totalNoOfUploadedFileKey}">Pending For Approval</a></li>  
    <li class="breadcrumb-item active" aria-current="page">Pending For Approval or Rejection </li>
  </ol>
</nav>
	
	<div class="frm-section">
		<div class="col-md-12">
		<span class="notification-message">${recordApproveByCGTMSEMaker}</span>
		<span class="notification-message"><strong>${recordRejectedByCGTMSEMacker}</strong></span>
		<span class="notification-message">${excelFileDownLoadSuccessfully}</span>
		<span></span>
		<form:form  method="POST" action="storeCgtmseDataRM.html" id="A"  class="form-horizontal" >
	

<div class="col-md-2 col-sm-4 col-xs-12">
	<div class="form-group">
		<div class="col-md-12 prl-10">
			<label>Portfolio No.</label>
		 	<form:input path="portfolioNo" value="${lists}" size="15" class="form-control cus-control" disabled="true" id="portfolioNo"/>
	    </div>
	</div>
  </div>

 	 <div class="col-md-2 col-sm-4 col-xs-12" >
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<label>Return Reason</label>
		<form:input path="rejection_reason"  id="mliRemarks" value="" size="50" class="form-control cus-control"/>  
		</div>
		</div>
 	 </div>
 
 	 <div class="d-inlineblock mt-25">


 			     <input type="submit" value=" Approve " id="Submit" name="action1"  class="btn btn-reset"/>  
                 <input type="button" value=" Return " onclick="return validateFormData()" name="action2" id="reject"  class="btn btn-reset"/>
                <a href="cgtmseMakerForBatchApprovalUploadedBatchFileRM.html?subPortfolioDtlNumber=${subPortfolioDtlNoKey}&statusNCA=${statusKey}&totalNoOfUploadBatchFile=${totalNoOfUploadedFileKey}" class="btn btn-reset">Close</a>
       
     </div>
  
	
	<div class="d-inlineblock float-right">
		<!-- <label style="padding-top:8px; font-weight:100;">Search :</label> -->
		<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px;
  		  background-color: #ffffff;" placeholder="Search Data.." title="Type in a name">    		
    		<button style="border:none !important; cursor: not-allowed;">
    				 <a href="cgtmseMakerRejectionAndSumbissionRMdownLoad.html"> <img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel"></a>   
    		</button>			
	</div>
		
		</form:form>
		</div>
	</div>
	
		<div class="tbl-details">
		<div class="col-md-12">
	<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0">
			<thead>
				<tr>
					<th >Date of Upload</th>
	   				<th >MLI Name</th>	   				
	   				<th >Portfolio Name</th>	   				
	   				<!--<th >Quarter File detail</th>-->
	   				<!-- <th >File Id/File Name</th> -->
	   				<th >No. of records</th>
	   				<th >Total Guarantee Amount</th><!--
	   				<th >Total Sanctioned Amount</th>
				--></tr>
			</thead>
			
				<tr>
			  	 	<td>${dateOfUploadKey}</td>			  	 	
			   		<td  >${shortNameKey}</td>			   		
			   		<td  >${portfolioNoKey}</td>			        
			        <!--<td  >${quaterIdKey}</td>			        
			        <td  >${fileIdKey}</td>		-->	        
			        <td  >${noOfRecordsOfExcelFile1Key}</td>
			        <td  >${outstandingAmount}</td><!--			        
			        <td  >${sanctionAmountKey}</td>			        

				--></tr>
	</table>
		</div>
		</div>		
	
	</div>	
</div>
</div>			

<script  type="text/javascript">
	$(document).ready(function(){
		
		   $("#reject").click(function(){			
		    $("#trial").removeAttr('disabled');
		    $("#Save").removeAttr('disabled');
		  });
	});	
	 document.getElementById("mliRemarks").disabled=true; 
	 function validateFormData()
	 {
	     if(document.getElementById('mliRemarks').value==null || document.getElementById('mliRemarks').value=="")
	     {
	      alert("Please enter the Remarks!");
	      document.getElementById("mliRemarks").focus();
	      document.getElementById("mliRemarks").disabled=false;
	   //   document.getElementById('approve1').style.visibility = 'hidden';
	      return false;
	     }
	     else
	    {
	    	    document.getElementById('A').action = "/SV/storeCgtmseDataReject.html";
	    	    document.getElementById('A').submit();	 
	    }
	 }    
	 
	 var maxLength =500;
	 function enforceMaxLength(obj)
	 {
	 	if (obj.value.length > maxLength)
	 	{
	 		obj.value = obj.value.substring(0, maxLength);
	 	}
	 }

	 var text = document.getElementById('mliRemarks');
	 var spanID= document.getElementById('count');
	 spanID.innerHTML = maxLength;
	 text.onkeyup = function ()
	 {
	   document.getElementById('count').innerHTML = (maxLength - this.value.length);
	 };



		function checkForm(form)
		  {
		    form.myButton.disabled = true;
		    return true;
		  }
	</script>
</body>
<!--<script type="text/javascript">
function exitMLIDetails() 
{
	document.getElementById('A').action = "/SV/cgtmseMakerForBatchApprovalUploadedBatchFileRM.html?subPortfolioDtlNumber=${subPortfolioDtlNoKey}&statusNCA=${statusKey}&totalNoOfUploadBatchFile=${totalNoOfUploadedFileKey}";
	document.getElementById('A').submit()
}
</script>
--></html>

	


