<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html >
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script  src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
		<!--  ${recordNotExitKey3}-->
		<!--  ${statusCMAKey}-->
		<script type="text/javascript">
$(window).load(function() {
	$(".loader").fadeOut("slow");
})
</script>
	</head>
	<body>
	
 <div class="main-section">
<div class="container-fluid">
<!-- <div class="row"> -->
	<div>	
		<div class="tbl-details">
		<div class="col-md-12">
	
		<div class="d-inlineblock float-right  ">
		<!-- <label style="padding-top:8px; font-weight:100;">Search :</label> -->
		<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px; background-color: #ffffff;" placeholder="Search Data.." title="Type in a name">    		
    		<button style="border:none !important; cursor: not-allowed;">
    			<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
    		</button>			
		</div>
		
			<form:form  method="POST" action="" id="A">
			<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0">
				<thead>
				<tr>
	   				<th> SR.NO.</th>
	   				<th> DESCRIPTIONS</th>
	   				<th> TOTAL NUMBERS</th>
	   				<th> DETAILS</th>
	   			</tr>
	   			</thead>
			   <tr>
			        <td>1</td>
			        <td>No. of Batch Uploads pending for approval</td>
			        <td>${statusCMACountKey}</td>
			       	<td> <a href="cgtmseCheckerBatchUploadsPendingForApprovalRM.html?paramID=${statusCMAKey}" class="btn-edit">Details</a></td>
			    </tr>
			    <tr>
			    	<td>2</td>
			        <td>No. of Gurantee fee generation pending for approval</td>
			        <td>0</td>
			        <td class="btn-edit not-allow">Details</td>
			    </tr>
			    <tr>
			    	<td>3</td>
			        <td>No. of Gurantee fee aoorioruation pending for approval</td>
			        <td>0</td>
			        <td class="btn-edit not-allow">Details</td>
			    </tr>
			    <tr>
			     	<td>4</td>
			        <td>No. of Update files pending for approval</td>
			        <td>0</td>
			        <td class="btn-edit not-allow">Details</td>
			    </tr>
			    <tr>
			    	<td>5</td>
			        <td>No. of Claim Files pending for approval</td>
			        <td>0</td>
			        <td class="btn-edit not-allow">Details</td>
			    </tr>			    			    						
			</table>
		</form:form>	
		</div>
		</div>
	</div>
</div>
</div> 
<div id="disPlaySuccessMsg"></div>
</body>
<script type="text/javascript">
 function exitMLIDetails() {
	//alert('Exit');
	document.getElementById('A').action = "/SV/cgtmseCheckerHomeBack.html";
	document.getElementById('A').submit();
 }

</script>
</html>


