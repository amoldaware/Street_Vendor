<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script  src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
	</head>
	
	<body bgcolor="#E0FFFF">
	<div class="main-section">
<nav aria-label="breadcrumb">
    <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/cgtmseMakerForBacthApprovalRM.html">Batch Approval</a></li>  
    <li class="breadcrumb-item active" aria-current="page">Pending For Approval</li>
  </ol>
</nav>
<div class="container-fluid">
<!-- <div class="row"> -->
	<div>	
	<div class="tbl-details">
		<div class="col-md-12">
		<h2 align="center" class="heading">Pending For Approval </h2><!--
		<div class="d-inlineblock">
		<input type="submit" value="Back" class="btn btn-reset"	onclick="exitMLIDetails()"   />
		</div>
		
		--><div class="d-inlineblock float-right ">
		<!-- <label style="padding-top:8px; font-weight:100;">Search :</label> -->
		<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px; background-color: #ffffff;" placeholder="Search Data.." title="Type in a name">    		
    		<button style="border:none !important; cursor: not-allowed;">
    			<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
    		</button>			
		</div>
		<form:form  method="POST" action="test2.html" id="A" >
			<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0">
			<thead>
			<tr>
	   			    <th>SR.NO</th>
	   				<th>DATE OF UPLOAD</th>	   				
	   				<th>MLI NAME</th>	   				
	   				<!-- <th>Portfolio No</th>	   				
	   				<th>Quarter File detail</th>	   				
	   				<th>File Id/File Name</th>	    -->				
	   				<th>NO.OF RECORDS</th>
	   				<th>TOTAL GUARANTEE AMOUNT</th>	   				
	   				<!-- <th>Total Sanctioned Amount</th>	   --> 				
	   				<th>DETAILS</th>
	   			</tr>
			 	</thead>
			  		<% int counter=0; int srno=1;%>
			   <c:forEach items="${lists}" var="listValue" varStatus="loop" > 
			    	<%-- <tr <% if(counter%2==0){%>bgcolor="silver" <%}%>> --%>
			    	<tr>
			    	<td  align="left"><%=srno++%></td>
			    	<td  align="left">${lists[loop.index].uploadDate}</td>			  	 
			  	 	<td  align="left">${lists[loop.index].shortName}</td>			  	
			   		<%-- <td  align="right">${lists[loop.index].portfolioNo}</td>			    
			        <td  align="right">${lists[loop.index].portfolioQuarter}</td>			        
			        <td  align="right">${lists[loop.index].filePath}</td>		 --%>	        
			        <td  align="right">${lists[loop.index].noOfRecords}</td>	
			        <td  align="right">${lists[loop.index].outstandingAmount}</td>		        
			     <%--    <td  align="right">${lists[loop.index].sanctionAmount}</td>			 --%>        
			    	<td align="right"  class="btn-edit"><a href="cgtmseMakerRejectionAndSumbissionRM.html?paramID=${lists[loop.index].portFolioName}&fileId=${lists[loop.index].fileName}&quaterId=${lists[loop.index].portfolioQuarter}&shortName=${lists[loop.index].shortName}&sanctionAmount=${lists[loop.index].sanctionAmount}&outstandingAmount=${lists[loop.index].outstandingAmount}&fullFileName=${lists[loop.index].fileName}&uploadedDate=${lists[loop.index].uploadDate}&noOfRecordsOfExcelFile=${lists[loop.index].noOfRecords}" class="btn-edit">Details</a> </td>
			    	<td  align="right" id="excelFileNameId" style="display:none">${lists[loop.index].fileName}</td>			       
			    <%  counter+=1;%></tr>
			    </c:forEach>			    	  			    			   							
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
//	alert('Back');
	document.getElementById('A').action = "/SV/cgtmseMakerForBacthApprovalRMBack.html";
	document.getElementById('A').submit();
}
</script>
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
	
