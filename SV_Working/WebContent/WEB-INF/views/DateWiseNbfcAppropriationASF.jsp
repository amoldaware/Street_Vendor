<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<title>Portfolio Creation</title>
		<link href="<%=request.getContextPath()%>/js/jquery-ui.css" rel="stylesheet">
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
		<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
		<!--<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>-->
		<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
		<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Date wise GF Batch Application</title>
	</head>
<script type="text/javascript">
$(function() {
  	$("#txt").datepicker({ dateFormat: 'dd/mm/yy' });
	
	
});




function getDataDateWise() {
	var date=document.getElementById('txt').value;
	//if(date===""){
	//	alert("Date of Realisation is Mandatory.. !");
		
	//}else{
	var hidder=document.getElementById('thisField').value=date;
	document.getElementById('submitFormId').action = "/SV/dateWiseGfBatchApplicaitonASF.html";
	//document.getElementById('submitFormId').submit();
	document.deliveryForm.submit();
	
	//}
}
</script>
<body>
	<div class="main-section">
		<div class="container-fluid">
			<div>
				<div class="frm-section">
					<div class="col-md-12">
						<h2 align="center" class="heading">Search Appropriation ASF for Approval/Rejection</h2>
						<form:form method="Post" action="" id="submitFormId" >
							<div class="col-md-2 col-sm-4 col-xs-12">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Date of Realisation :	</label>
											<form:input path = "toDate" id="txt" placeholder="mm/dd/yyyy" class="form-control cus-control"/>
											 <input type="hidden" id="thisField" name="inputName" value="hiddenValue" placeholder="dd/mm/yyyy" class="form-control cus-control">
											 <form:errors path="toDate" cssClass="error" />
			  							</div>
			 						 </div>
 		 					</div>
 		 <div class="d-inlineblock">
 		 	<input type="submit" value="Search" onclick="getDataDateWise();" class="btn btn-reset mt-25"/>
 		 </div>
		</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>	
</body>
</html>


