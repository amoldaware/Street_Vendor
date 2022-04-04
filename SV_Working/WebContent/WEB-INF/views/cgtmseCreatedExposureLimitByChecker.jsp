<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script  src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<title>Exposure Limit Approval/Rejection By Checker</title>
		<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<script type="text/javascript" src="jquery-ui-1.10.3/ui/jquery.ui.datepicker.js"></script>
		<link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel = "stylesheet">
	    <script src = "https://code.jquery.com/jquery-3.5.1.min.js"></script>
	   	<script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	   	<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<div class="main-section">
			<div class="container-fluid">
				<div>
				 <nav aria-label="breadcrumb">
  <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/ExposureListingChecker.html">Exposure Approval & Rejection</a></li>  
    <li class="breadcrumb-item active" aria-current="page">Exposure Approval/Rejection</li>
  </ol> 
</nav> 
					<div class="frm-section">
						<div class="col-md-12">		
							<div class="notification-message" id="successMsg"><strong>${checkerApproveKey}</strong></div>
							<div class="notification-message" id="successMsg1"><strong>${checkerRejectKey}</strong></div>
							<form:form  method="POST" action="saveExposureMasterCheckerDetails.html" class="form-horizontal" id="exposureMasterCheckerDetailsFormId">
								<c:forEach items="${CheckerApprovalList}" var="CheckerApprovalList">
									<div class="col-md-3 col-sm-4 col-xs-12">
										<div class="form-group">
											<div class="col-md-12 prl-10">
												<label>MLI Long Name:<span style="color:red">*</span>	</label>
												<form:input path="mliLongName" value="${CheckerApprovalList.mliLongName}" size="20" class="form-control cus-control" id="mliLongName" readonly="true"/>
	  										</div>
	  									</div>
									</div>
		 
 									<div class="col-md-3 col-sm-4 col-xs-12">
										<div class="form-group">
											<div class="col-md-12 prl-10">
												<label>MLI Short Name:<span style="color:red">*</span>	</label>
												<form:input path="mliShortName" value="${CheckerApprovalList.mliShortName}" size="20"  class="form-control cus-control" id="mliShortName" readonly="true"/>
	  										</div>
	  									</div>
 									</div>
		 
  									<div class="col-md-3 col-sm-4 col-xs-12">
										<div class="form-group">
											<div class="col-md-12 prl-10">
												<label>Exposure Limit (Rs):<span style="color:red">*</span></label>
												<form:input path="mliExposureLimit" value="${CheckerApprovalList.mliExposureLimit}" size="20"  class="form-control cus-control" id="mliExposureLimit" readonly="true"/>
	  										</div>
	  									</div>
 									</div>
		 
 									<div class="col-md-3 col-sm-4 col-xs-12">
										<div class="form-group">
											<div class="col-md-12 prl-10">
												<label>Date of Sanction of Exposure:<span style="color:red">*</span></label>
												<form:input path="mliDateOfSanctionOfExposure"  value="${CheckerApprovalList.mliDateOfSanctionOfExposure}" size="20" id="mliDateOfSanctionOfExposure" class="form-control cus-control" readonly="true"/>
	  										</div>
	  									</div>
 									</div>
		 
 									<div class="col-md-3 col-sm-4 col-xs-12">
										<div class="form-group">
											<div class="col-md-12 prl-10">
												<label class="d-block text-purple" >Validity of Exposure Limit(dd/mm/yyyy):</label>
												<div class="col-md-6 prl-10">
													<label>Start Date:<span style="color:red">*</span></label>
													<form:input path="mliValidityOfExposureLimitStartDate" value="${CheckerApprovalList.fromDate}" size="20"  id="mliValidityOfExposureLimitStartDate" class="form-control cus-control" readonly="true" />
												</div>
												<div class="col-md-6 prl-10">
													<label>End Date:<span style="color:red">*</span></label>
													<form:input path="mliValidityOfExposureLimitEndDate"  value="${CheckerApprovalList.toDate}"   size="20"   id="mliValidityOfExposureLimitEndDate" class="form-control cus-control" readonly="true"/>
												</div>
											</div>						  
 										</div>
 									</div>
								 	<div class="col-md-3 col-sm-4 col-xs-12 mt-25">
										<div class="form-group">
											<div class="col-md-12 prl-10">
												<label>Guarantee Fee(% p.a):<span style="color:red">*</label>												 
										 		<form:input path="gurantee_fee" value="${CheckerApprovalList.gurantee_fee}" size="20"  id="gurantee_fee" class="form-control cus-control" readonly="true"/>
										 	</div>
										</div>
									</div>
						 
									 <div class="col-md-3 col-sm-4 col-xs-12 mt-25">
										<div class="form-group">
											<div class="col-md-12 prl-10">
												<label>Pay-Out Cap (Times):<span style="color:red">*</label>												 
										 		<form:input path="pay_out_cap" value="${CheckerApprovalList.pay_out_cap}" size="20"  id="pay_out_cap" class="form-control cus-control" readonly="true"/>
										 	</div>
										</div>
									 </div>
									 <div class="col-md-3 col-sm-4 col-xs-12">
										<div class="form-group">
											<label class="d-block text-purple" style="visibility: hidden";> 1</label>
											<div class="col-md-12 prl-10">
												<label>Gurantee Coverage:<span style="color:red">*</span>	</label>
												<form:input path="guranteeCoverage" value="${CheckerApprovalList.guranteeCoverage}" size="20"  class="form-control cus-control" id="guranteeCoverage" readonly="true"/>
			  								</div>
			  							</div>
		 							</div>
	  								<div class="col-md-3 col-sm-4 col-xs-12 mt-25">
										<div class="form-group">
											<div class="col-md-12 prl-10">
												<label>Return Remarks:<span style="color:red">*</span></label>
												<form:textarea  path="remarks"  row="6" id="mliRemarks" class="form-control cus-control"  onKeyUp="enforceMaxLength(this);" />
											</div>						  
		 								</div>
	 								</div>
	 								<div class="d-inlineblock">		
						 				<input type="submit" value="Back" class="btn btn-reset"  id="back1"name="action2"/>
										<input type="submit" value="Approve" class="btn btn-reset"  id="approve1" name="action1"/>
										<input type="submit" value="Return" class="btn btn-reset" id="reject1" name="action3" onclick="return validateFormData()"/>		
									</div>
							</c:forEach>
						</form:form>
					</div>
				</div>		
			</div>
		</div>
	</body>
</html>
<script language="javascript">
document.getElementById("mliRemarks").disabled=true; 
	//Date Picker UI
	/* $(function() {
	  	$("#mliDateOfSanctionOfExposure").datepicker({ dateFormat: 'dd/mm/yy' });
		$("#mliValidityOfExposureLimitStartDate").datepicker({ dateFormat: 'dd/mm/yy' });
		$("#mliValidityOfExposureLimitEndDate").datepicker({ dateFormat: 'dd/mm/yy' });
	}); */
	
	var maxLength =500;
	function enforceMaxLength(obj){
		if (obj.value.length > maxLength) {
			obj.value = obj.value.substring(0, maxLength);
		}
	}
	
	var text = document.getElementById('mliRemarks');
	var spanID= document.getElementById('count');
	spanID.innerHTML = maxLength;
	text.onkeyup = function (){
	  document.getElementById('count').innerHTML = (maxLength - this.value.length);
	};
	
	document.getElementById("mliDateOfSanctionOfExposure").disabled = true;
	document.getElementById("mliValidityOfExposureLimitStartDate").disabled = true;
	document.getElementById("mliValidityOfExposureLimitEndDate").disabled = true;
	function validateFormData(){
		document.getElementById("mliRemarks").disabled=false; 
	    if(document.getElementById('mliRemarks').value==null || document.getElementById('mliRemarks').value==""){
		     alert("Please enter the Remarks.");
		     document.getElementById("mliRemarks").focus();
		     document.getElementById("mliRemarks").disabled=false;
		     document.getElementById("approve1").disabled=true;
		     //document.getElementById('approve1').style.visibility = 'hidden';
		     
	     	return false;
	    }else{
	    	return true;
	    }   
	}    

</script>