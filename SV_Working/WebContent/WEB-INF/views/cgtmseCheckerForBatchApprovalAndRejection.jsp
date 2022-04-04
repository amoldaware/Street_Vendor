<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <%
    //String excelRecordNotFoundNotFoundInDataBase=(String)request.getAttribute("excelRecordNotFoundNotFoundInDB");
    //String approveSucessMsg1=(String)request.getAttribute("approveSucessMsg");
	
    %>   
       

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html >
	<head>
		<!--  ${lists}-->
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script  src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
        <script  type="text/javascript">
		$(window).load(function() {
			$(".loader").fadeOut("slow");
		})
		
    function hideComments(){
	document.getElementById('rejectionReasonlbl').style.display ="none";
	document.getElementById('rejection_reason').style.display ="none";
	document.getElementById('rejected').style.display ="none";
	
	//document.getElementById('rejection_reason').style.height="100px";
      }
   function showComments(){
	document.getElementById('rejectionReasonlbl').style.display =""; 
	document.getElementById('rejection_reason').style.display =""; 
	document.getElementById('rejected').style.display ="";
	
   }
   function showapp(value){
   // alert(value);
	 document.getElementById('RE').style.display="block";
   }


      function BlankRemarkAlert(){
	
      return_reason =  document.getElementById("rejection_reason").value;
      
      if(return_reason.length<1){
    	  alert(" !! Please enter rejected remarks !! ");    
    	  return false;
       }
     
      
      
}

     function backBatchApproval()
    {
	//alert("");
         document.getElementById('A').action = "/SV/cgtmseCheckerHomeBack1.html";
         document.getElementById('A').submit();
     }


</script>


	<script type="text/javascript">
$(window).load(function() {
	$(".loader").fadeOut("slow");
})
</script>	
	</head>

<body onload="hideComments()">
	
	
 <div class="main-section">
 <nav aria-label="breadcrumb">
  <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/cgtmseCheckerForBacthApprovalRM.html">Batch Approval By Checker</a></li>
    <li class="breadcrumb-item"><a href="/SV/cgtmseCheckerBatchUploadsPendingForApprovalRM.html?paramID=CMA">Pending For Approval</a></li>    
    <li class="breadcrumb-item active" aria-current="page">Pending For Approval & Rejection</li>
  </ol>
</nav>
<div class="container-fluid">
<!-- <div class="row"> -->
	<div>					
		<div class="tbl-details">
		<h2 align="center" class="heading">Pending For Approval & Rejection</h2>
		<h5 class="notification-message"><strong>${recordApprovedByCGTMSEChecker}</strong></h5>
		<h5 class="notification-message"><strong>${recordRejectedByCGTMSEChecker}</strong></h5>
		<h5 class="notification-message"><strong>${cgtmseCheckerexcelFileDonloadedSuccessfully}</strong></h5>
		
<form:form  method="POST" action="storeCgtmseCheckerDataRM.html" id="A" class="form-horizontal">
			
		<div class="col-md-12">
			<div class="d-inlineblock float-right ">
				<!-- <label style="padding-top:8px; font-weight:100;">Search :</label> -->
				<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px; background-color: #ffffff;" placeholder="Search Data.." title="Type in a name">    		
		    		<!-- <button style="border:none !important; cursor: not-allowed;"> -->
		    			 <a href="cgtmseCheckerRejectionAndSumbissionRMdownLoad.html?portfolioID=${lists}">
		    				 <img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
		    			 </a>
		    	<!-- 	</button> -->			
			</div>
			
		
<c:choose>
 <c:when test="${(success!=true)and(Reject!=true)}">
 		 <div class="col-md-5 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			     <label class="control-label col-sm-3 prl-10">Return Reason:</label>	
			     <div class="col-md-9">		    
			   <form:input path="rejection_reason" value="" size="20" class="form-control cus-control" id="rejection_reason"/>
			   </div>	      
			    </div>			    			   
			  </div>
		</div>
    	<div class="d-inlineblock">     			    					
			 <input type="submit" value="Return" id="RE" name="action2" class="btn btn-reset" onclick="return BlankRemarkAlert();"/>	   			       
             <input type="submit" id="AP" value="Approve" name="action1" class="btn btn-reset" onclick="showapp(this.value);" />
           <a href="cgtmseCheckerBatchUploadsPendingForApprovalRM.html?paramID=CMA" class="btn btn-reset" align="right">Back</a>	   			      
	   			      <!--   <td>
	   			           <input type="button" id="RE" value="REJECT" onclick="showComments()" />
	   			        </td>	-->   
		</div>      
      </c:when> 
    <c:otherwise>     
    <div class="col-md-5 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			     <label class="control-label col-sm-3 prl-10" id="rejectionReasonlbl">Return Reason:</label>	
			     <div class="col-md-9">	
			      <form:input path="rejection_reason" value="" size="20" class="form-control cus-control"  id="rejection_reason"/>		    			   
			     </div>	      
			  </div>			    			   
			  </div>
	</div>
    <div class="d-inlineblock">     			    					
		   <input type="submit" value="Return" id="RE" name="action2" class="btn btn-reset" onclick="return BlankRemarkAlert();" disabled="true" />  			       
           <input type="submit" id="AP" value="APPROVE" name="action1" class="btn btn-reset" onclick="showapp()" disabled="true"/>
           <a href="cgtmseCheckerBatchUploadsPendingForApprovalRM.html?paramID=CMA" class="btn btn-reset" align="right">Back</a>	   			      	   			    
	</div>        	   			   	  	   			  
	   			         
    </c:otherwise>
	</c:choose>
	
			
			<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0">
			
				<thead>
					<tr>
					<th >DATE OF UPLOAD</th>	   							
					<th >MLI NAME</th>	   							
				<!-- 	<th >Portfolio No</th>	 -->   							
				<!-- <th >Quarter File detail</th>	 -->   							
			<!-- 		<th >File Id/File Name</th>	   		 -->					
					<th >NO.OF RECORDS</th>	   		
					<th >TOTAL GUARANTEE AMOUNT</th>					
					<!-- <th >Total Sanctioned Amount</th> -->
					</tr>
				</thead>
				<tr>
  	 				<td >${uploadedDateKey}</td>			  	 				
   					<td >${shortNameKey}</td>			   					
       		<%-- 		<td >${portfolioIDKey1}</td>		 --%>	       			 	
       	 			<%-- <td >${quaterNameKey}</td>	 --%>		        			
        	<%-- 		<td >${excelFileIdKey}</td>	 --%>		        			
        			<td >${totNoOfRecordsUploadedInExcelFileKey}</td>			        			
       	 			<td >${outstandingAmount}</td>
       	 			<%-- <td >${totalSanctionAmtKey}</td> --%>
    			</tr> 								
			</table>	

		           </div>
           </form:form>		
		</div>
	</div>
</div>
</div>	

</body>
</html>
<style type="text/css">
.loader {
	position: fixed;
	left: 0px;
	top: 0px;
	width: 100%;
	height: 100%;
	z-index: 9999;
	
}

.error {
	color: red;
	font-weight: bold;
}

.h3 {
	color: red;
	font-weight: bold;
}

.button {
	background-color: #6495ED;
	border: none;
	color: white;
	padding: 12px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: -02px 0px;
	cursor: pointer;
}
.form-group.required .control-label:before{
   color: red;
   content: "*";
   position: absolute;
   margin-left: -15px;
}


</style>

