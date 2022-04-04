<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html >
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script  src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
		
<script type="text/javascript">
$(window).load(function() {
	$(".loader").fadeOut("slow");
})
</script>
<!--  ${DataNotExitKey}-->
	</head>
	
	<body bgcolor="#E0FFFF">
	<div class="main-section">
<div class="container-fluid">
<!-- <div class="row"> -->
	<div>	
		<div class="tbl-details">
		<div class="col-md-12">
		
		<div class="d-inlineblock float-right  ">
		<!-- <label style="padding-top:8px; font-weight:100;">Search :</label> -->
		<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px;
  		  background-color: #ffffff;" placeholder="Search Data.." title="Type in a name">    		
    		<button style="border:none !important; cursor: not-allowed;">
    			<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
    		</button>			
		</div>
		
			<form:form  method="POST" action="test.html" id="A" >
			<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0">
				<thead>
				<tr>
	   				<th >SR.NO.</th>
	   				<th >DESCRIPTION</th>
	   				<th >TOTAL NUMBERS</th>
	   				<th >DETAILS</th>
	   			</tr>
	   			</thead>
	   			<tbody>
			   <tr>
			        <td>1</td>
			        <td>Number of valid batch files forwarded by NBFC MLIs & Rejected batches</td>
			        <td align="center" >${countTotalNoOfUploadedFileKey}</td>
			    	<td align="center" class="btn-edit"><a href="cgtmseMakerForBatchApprovalUploadedBatchFileRM.html?subPortfolioDtlNumber=${subPortfolioDtlNoKey}&statusNCA=${statusKey}&totalNoOfUploadBatchFile=${totalNoOfUploadedFileKey}" class="btn-edit">Details</a> </td>
			    	
			    </tr>

			<%-- 	<tr>
					<td>2</td>
			        <td>Number of batches has been rejected</td>
			        <td align="center">${rejectedNoOfFileByCGTMSEKey}</td>
			    	<td align="center" class="btn-edit not-allow"><a href="cgtmseMakerForBatchApprovalUploadedBatchFileCCRDetailsRM.html?subPortfolioDtlNumber=${subPortfolioDtlNoKey}&statusNCA=${statusKey}&totalNoOfUploadBatchFile=${totalNoOfUploadedFileKey}" class="btn-edit">Details</a> </td>
			    </tr> --%>
			    <tr>
			    	<td>2</td>
			        <td >Number of batches/Portfolio for which fee is required to be generated</td>
			        <td align="center">0</td>
			    	<td align="center" class="btn-edit not-allow">Details </td>
			    </tr>
			    <tr>
			    	<td>3</td>
			        <td>Number of batches/Portfolio for which fee generation has been rejected by the CGTMSE Checker</td>
			        <td align="center">0</td>
			    	<td align="center" class="btn-edit not-allow">Details </td>
			    </tr>
			    <tr>
			     	<td>4</td>
			        <td>Number of batches/Portfolio for which fee is required to be appropriated</td>
			        <td align="center">0</td>
			    	<td align="center"  class="btn-edit not-allow">Details </td>
			    </tr>
			    <tr>
			    	<td>5</td>
			        <td>Number of batches/Portfolio for which fee appropriation has been rejected by the CGTMSE Checker</td>
			        <td align="center">0</td>
			        <td align="center" class="btn-edit not-allow">Details</td>
			    </tr>
			    <tr>
			    	<td>6</td>
			        <td>Number of update files forwarded by NBFC MLIs</td>
			        <td align="center">0</td>
			    	<td align="center" class="btn-edit not-allow">Details</td>
			    </tr>
			    <tr >
			    	<td>7</td>
			        <td>Number of update files rejected by the Approver</td>
			        <td align="center">0</td>
			    	<td align="center" class="btn-edit not-allow">Details</td>
			    </tr>
			    <tr>
			    	<td>8</td>
			        <td>Number of claim files forwarded by NBFC MLIs</td>
			        <td align="center">0</td>
			    	<td align="center" class="btn-edit not-allow">Details</td>
			    </tr>
			     <tr>
			     	<td>9</td>
			        <td>Number of claim rejected by the CGTMSE Checker</td>
			        <td align="center">0</td>
			    	<td align="center" class="btn-edit not-allow">Details </td>
			    </tr>
			  
			     </tbody>
			    <tr class=" d-none"><td colspan="2" align="center"><input type="submit" value=" Exit "	onclick="exitMLIDetails()" class="button" /></td></tr>
								
			
			</table>
			</form:form>
			
		</div>
		</div>
	</div>
</div>
</div>	
</body>
<script type="text/javascript">
function exitMLIDetails() {
	alert('Exit');
	document.getElementById('A').action = "/SV/cgtmseMakerHomeBack.html";
	document.getElementById('A').submit();
}
</script>
</html>




