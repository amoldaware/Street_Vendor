<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<title>Portfolio Creation</title>

<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
 <link href = "css/jquery-ui-css.css" rel="stylesheet" type="text/css">
 <script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
 
<%-- <link href="<%=request.getContextPath()%>/js/jquery-ui.css"
	rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
<!--<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css"
	rel="stylesheet" type="text/css">--> --%>




<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Date wise GF Batch Application</title>

 <style type="text/css">
 /*  div.ui-datepicker{	    top: 70px !important;
    z-index: 16 !important;	} */
  </style>

<script type="text/javascript">
function getDataDateWise() {
	var fromDate=document.getElementById('fromDate').value;
	var toDate=document.getElementById('toDate').value;
	//alert("Testing");
	//if(fromDate==="" && toDate===""){
	//	alert("From date and To Date is Mandatory.. !");
	//	return false;
	//}else if(fromDate===""){
	//	alert("From date  is Mandatory.. !");

	//}else if(toDate===""){
	//	alert("To Date is Mandatory.. !");
		
	//}else{
	var fromDateHidden=document.getElementById('fromDateHidden').value=fromDate;
	var toDateHidden=document.getElementById('toDateHidden').value=toDate;
			
	document.getElementById('submitFormId').action = "/SV/betweenTwoDateAppropriationASF.html";
	document.getElementById('submitFormId').submit();
		//}
}

$(function() {
  	$("#fromDate").datepicker({ dateFormat: 'dd/mm/yy' });
	$("#toDate").datepicker({ dateFormat: 'dd/mm/yy' });
	$("#mliValidityOfExposureLimitEndDate").datepicker({ dateFormat: 'dd/mm/yy' });
	
});



</script>


</head>
<body>
<div class="main-section">
<div class="container-fluid">
<!-- <div class="row"> -->
	<div>
	 <nav aria-label="breadcrumb">
  <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/getAppropriationDataBetweenDates.html">Appropriation Payment for Guarantee</a></li>  
    <li class="breadcrumb-item active" aria-current="page">Appropriation Payment</li>
  </ol> 
</nav> 	
	<div class="frm-section">	
		<div class="col-md-12">
			<form:form method="Post" action="" id="submitFormId" class="form-horizontal" >
			
	<div class="col-md-2 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<label>From Date :	</label>
		<form:input path = "fromDate" id="fromDate" class="form-control cus-control" placeholder="dd/MM/yyyy"/>
			 <input type="hidden" id="fromDateHidden" name="fromDateHidden" value="hiddenValue">
			 <form:errors path="fromDate" cssClass="error" />
			  </div>
			  </div>
 		 </div>
 		 
 		 	<div class="col-md-2 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<label>To Date :	</label>
		<form:input path = "toDate" id="toDate"  class="form-control cus-control" placeholder="dd/MM/yyyy"/>
			 <input type="hidden" id="toDateHidden" name="toDateHidden" value="hiddenValue1">
			 <form:errors path="toDate" cssClass="error" />
			  </div>
			  </div>
 		 </div>
 		 
 		<div class="d-inlineblock mt-25">		
 		<input type="submit" value="Submit" onclick="getDataDateWise();"  class="btn btn-reset"/>					
			</div>
 		 
 	 
			</form:form>	
			
		</div>
	
	</div>	
	</div>
</div>
</div>	




<!-- 	<table align=center border="1" margin>
		<tr>
			<td></td>
			<td></b></td>
			<td>To Date :</td>
			<td></b></td>
		</tr>
		<tr><td colspan="2" align="center"></td></tr>
		<tr>
			
			<td colspan="4" align="center">
			
			</b></td>
		</tr>

	</table> -->

</body>


</html>


