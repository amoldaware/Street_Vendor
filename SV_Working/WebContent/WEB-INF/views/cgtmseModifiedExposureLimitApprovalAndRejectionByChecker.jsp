<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
   <script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		
		<title>Exposure Limit Approval And Rejection By Checker</title>
		<script type="text/javascript" src="jquery-ui-1.10.3/ui/jquery.ui.datepicker.js"></script>
		<link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel = "stylesheet">
		<script src = "https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	
	
    <style type="text/css">
      .form-group{margin-bottom:10px;}
    </style>
    
    <style type="text/css">
      .form-group{margin-bottom:10px;}
      .form-control{	height:30px !important;	}
      
      .line	{	position:relative; width:100%;	}
      .line:after{	content:"";	position:absolute;	width:2px; height:400px; backgound-color:gray;		}
      </style>
    <script  type="text/javascript">
    $(document).ready(function()
    		{
    			 checkFilled();
    		});
    function checkFilled() 
    {
    	    var longName1 = document.getElementById("auditLongName").value;
    	    var longName2 = document.getElementById("mliLongName").value;
    	    
    	    if (longName1!=longName2)
       	    { 
    	    	mliLongName.style.backgroundColor = "#FFDAB9";
        	}
    	    var shortName1 = document.getElementById("auditShortName").value;
    	    var shortName2 = document.getElementById("mliShortName").value;
    	    
    	    if (shortName1!=shortName2)
       	    { 
    	    	mliShortName.style.backgroundColor = "#FFDAB9";
        	}
    	    var expolimit1 = document.getElementById("auditExposureLimit").value;
    	    var expolimit2 = document.getElementById("mliExposureLimit").value;
    	    
    	    if (expolimit1!=expolimit2)
       	    { 
    	    	mliExposureLimit.style.backgroundColor = "#FFDAB9";
        	}
    	    var sanctionDate1 = document.getElementById("auditDateOfSanctionOfExposure").value;
    	    var sanctionDate2 = document.getElementById("mliDateOfSanctionOfExposure").value;
    	    
    	    if (sanctionDate1!=sanctionDate2)
       	    { 
    	    	mliDateOfSanctionOfExposure.style.backgroundColor = "#FFDAB9";
        	}
    	    var startDate1 = document.getElementById("auditFromDate").value;
    	    var startDate2 = document.getElementById("mliValidityOfExposureLimitStartDate").value;
    	    
    	    if (startDate1!=startDate2)
       	    { 
    	    	mliValidityOfExposureLimitStartDate.style.backgroundColor = "#FFDAB9";
        	}
    	    var endDate1 = document.getElementById("auditToDate").value;
    	    var endDate2 = document.getElementById("mliValidityOfExposureLimitEndDate").value;
    	    
    	    if (endDate1!=endDate2)
       	    { 
    	    	mliValidityOfExposureLimitEndDate.style.backgroundColor = "#FFDAB9";
        	}
    	    var gurantee_fee1 = document.getElementById("audGuranteeFee").value;
    	    var gurantee_fee2 = document.getElementById("gurantee_fee").value;
    	    
    	    if (gurantee_fee1!=gurantee_fee2)
       	    { 
    	    	gurantee_fee.style.backgroundColor = "#FFDAB9";
        	}
    	    var pay_out_cap1 = document.getElementById("audPayOutCap").value;
    	    var pay_out_cap2 = document.getElementById("pay_out_cap").value;
    	    
    	    if (pay_out_cap1!=pay_out_cap2)
       	    { 
    	    	pay_out_cap.style.backgroundColor = "#FFDAB9";
        	}
    	    var guranteeCoverage1 = document.getElementById("audGuranteeCoverage").value;
    	    var guranteeCoverage2 = document.getElementById("guranteeCoverage").value;
    	    
    	    if (guranteeCoverage1!=guranteeCoverage2)
       	    { 
    	    	guranteeCoverage.style.backgroundColor = "#FFDAB9";
        	}
    }
  
    </script>
</head>
	<body  bgcolor="#E0FFFF">
		<div class="main-section">
			<div class="container-fluid">
				<div>	
					<div class="frm-section">
						<div class="col-md-12">
							<div id="successMsg"><h5 class="notification-message"><strong>${checkerApproveKey}</strong></h5></div>
							<div id="successMsg1"><h5 class="notification-message"><strong>${checkerRejectKey}</strong></h5></div>
							<form:form  method="POST" action="saveExposureMasterCheckerDetails.html" id="exposureMasterCheckerDetailsFormId" class="form-horizontal">
								<c:forEach items="${CheckerUserList}" var="CheckerUserList">
									<div class="col-md-8 mt-10 col-md-offset-2">    
										<div class="col-md-12 prl-10">
						 					<div class="form-group mb-0">
	   									 		<label class="control-label col-sm-4 prl-10"></label>
											    <div class="col-sm-4">
												 <h5 style="    color: #ffffff;    background-color: #a641a9 !important;    text-align: center;    margin: 0 0 10px 0; padding: 10px 0;"><strong>Old Data</strong></h5>			    				    	    
											    </div>			    			   
												<div class="col-md-4">
												<h5 style=" color: #ffffff;    background-color: #a641a9 !important;    text-align: center;    margin: 0 0 10px 0; padding: 10px 0;"><strong>Updated Data</strong></h5>	
												</div>
			  								</div>
			  
			  								<div class="form-group">
											    <label class="control-label col-sm-4 prl-10">MLI Long Name:<span style="color:red">*</span></label>
											    <div class="col-sm-4">
											    	<form:input path="auditLongName" value="${CheckerUserList.auditLongName}" size="35" class="form-control" id="auditLongName" readonly="true"/>			    	    
											    </div>			    			   
											    <div class="col-md-4">
											     	<form:input path="mliLongName" value="${CheckerUserList.longName}" size="35" class="form-control" id="mliLongName" readonly="true"/>
											    </div>
			  								</div>
			    							<tr>
												<div class="form-group">
												  <label class="control-label col-sm-4 prl-10">MLI Short Name:<span style="color:red">*</span></label>
												 	<div class="col-sm-4">
												  	 	<form:input path="auditShortName" value="${CheckerUserList.auditShortName}" size="35" class="form-control" id="auditShortName" readonly="true"/>			    	     
												  	</div>	
												  	<div class="col-md-4">
												   		<form:input path="mliShortName" value="${CheckerUserList.shortName}" size="35" class="form-control" id="mliShortName" readonly="true"/>
												  	</div>		    			   
												</div>
			
												<div class="form-group">
													<label class="control-label col-sm-4 prl-10">Exposure Limit (Rs):<span style="color:red">*</span></label>
													<div class="col-sm-4">
												 		<form:input path="auditExposureLimit" value="${CheckerUserList.auditExposureLimit}" size="20" class="form-control" id="auditExposureLimit" readonly="true"/>			    	     
													</div>	
												 	<div class="col-md-4">
												  		<form:input path="mliExposureLimit" value="${CheckerUserList.exposureLimit}" size="20" class="form-control" id="mliExposureLimit" readonly="true"/>
												 	</div>		    			   
												</div>
			
												<div class="form-group">
												 	<label class="control-label col-sm-4 prl-10">Date of Sanction of Exposure:<span style="color:red">*</span></label>
												 	<div class="col-sm-4">
												 		<form:input path="auditDateOfSanctionOfExposure"  value="${CheckerUserList.auditDateOfSanctionOfExposure}" size="20" id="auditDateOfSanctionOfExposure"  class="form-control"  readonly="true"/>			    	     
												  	</div>	
												  	<div class="col-md-4">
												   		<form:input path="mliDateOfSanctionOfExposure" value="${CheckerUserList.dateOfSanctionOfExposure}" size="20" class="form-control" id="mliDateOfSanctionOfExposure" readonly="true"/>
												  	</div>		    			   
												</div>
												
												<div class="form-group mb-0">
													<label class="control-label col-sm-4 prl-10">Validity of Exposure Limit(dd/mm/yyyy):</label>			   	    			  
												</div>
												
												<div class="form-group">
										  			<label class="control-label col-sm-4 prl-10">Start Date:<span style="color:red">*</span></label>
										 			<div class="col-sm-4">
										 				<form:input path="auditFromDate" value="${CheckerUserList.auditFromDate}" size="20"  id="auditFromDate"  class="form-control"  readonly="true"/>			    	     
										  			</div>	
									  				<div class="col-md-4">
										   				<form:input path="mliValidityOfExposureLimitStartDate" value="${CheckerUserList.fromDate}" size="20" class="form-control" id="mliValidityOfExposureLimitStartDate" readonly="true"/>
										  			</div>		    			   
												</div>
												
												<div class="form-group">
													<label class="control-label col-sm-4 prl-10">End Date:<span style="color:red">*</span></label>
												 	<div class="col-sm-4">
												 		<form:input path="auditToDate"  value="${CheckerUserList.auditToDate}"   size="20" id="auditToDate" class="form-control" readonly="true"/>			    	     
												  	</div>	
												  	<div class="col-md-4">
												 		<form:input path="mliValidityOfExposureLimitEndDate" value="${CheckerUserList.toDate}" size="20" id="mliValidityOfExposureLimitEndDate" class="form-control" readonly="true"/>
												  	</div>		    			   
												</div>
												
												<div class="form-group">
													<label class="control-label col-sm-4 prl-10">Ggurantee Fee:<span style="color:red">*</span></label>
												 	<div class="col-sm-4">
												  		<form:input path="audGuranteeFee" value="${CheckerUserList.audGuranteeFee}" size="20" class="form-control" id="audGuranteeFee" readonly="true"/>			    	     
												  	</div>	
												  	<div class="col-md-4">
												   		<form:input path="gurantee_fee" value="${CheckerUserList.gurantee_fee}" size="20" class="form-control" id="gurantee_fee" readonly="true"/>
												  	</div>		    			   
												</div>
												
												<div class="form-group">
													<label class="control-label col-sm-4 prl-10">Pay-Out Cap (Times):<span style="color:red">*</span></label>
												 	<div class="col-sm-4">
												  		<form:input path="audPayOutCap" value="${CheckerUserList.audPayOutCap}" size="20" class="form-control" id="audPayOutCap" readonly="true"/>			    	     
												  	</div>	
												  	<div class="col-md-4">
												   		<form:input path="pay_out_cap" value="${CheckerUserList.pay_out_cap}" size="20" class="form-control" id="pay_out_cap" readonly="true"/>
												  	</div>		    			   
												</div>
												<div class="form-group">
													<label class="control-label col-sm-4 prl-10">Gurantee Coverage:<span style="color:red">*</span></label>
												 	<div class="col-sm-4">
												  		<form:input path="audGuranteeCoverage" value="${CheckerUserList.audGuranteeCoverage}" size="20" class="form-control" id="audGuranteeCoverage" readonly="true"/>			    	     
												  	</div>	
												  	<div class="col-md-4">
												   		<form:input path="guranteeCoverage" value="${CheckerUserList.guranteeCoverage}" size="20" class="form-control" id="guranteeCoverage" readonly="true"/>
												  	</div>		    			   
												</div>
												<div class="form-group">
													<label class="control-label col-sm-4 prl-10">Remarks:<span style="color:red">*</span></label>
												 	<div class="col-sm-4">
												 		<textarea  row="6"  readOnly="true" class="form-control" >${CheckerUserList.auditRemarks} </textarea> 		    	     
												  	</div>	
												  	<div class="col-md-4">
														<form:textarea  path="remarks"  row="6" id="mliRemarks" class="form-control"  onKeyUp="enforceMaxLength(this);"/> 
												  	</div>		    			   
												</div>
												
												<div class="d-block" style="margin:0 auto; text-align:center;">
													<div class="d-inlineblock">
												 		<input type="submit" value="Approve" class="btn btn-reset"  id="approve1" name="action1"/>
												 		<input type="submit" value="Return" class="btn btn-reset" id="reject1" name="action3" onclick="return validateFormData()"/>
												 		<input type="submit" value="Back" class="btn btn-reset"  id="back1"name="action2"/>
												 	</div>
												</div>
											</tr>
									</div>
								</div>
							</c:forEach>
						</form:form>
						</div>
					</div>
				</div>
			</div>	
		</div>
	</body>
</html>
<script language="javascript">
	
	//Date Picker UI
	$(function() {
	  	$("#mliDateOfSanctionOfExposure").datepicker({ dateFormat: 'dd/mm/yy' });
		$("#mliValidityOfExposureLimitStartDate").datepicker({ dateFormat: 'dd/mm/yy' });
		$("#mliValidityOfExposureLimitEndDate").datepicker({ dateFormat: 'dd/mm/yy' });
		
	});
	
	var maxLength =500;
	function enforceMaxLength(obj){
		if (obj.value.length > maxLength) {
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
	
	document.getElementById("mliRemarks").disabled=true; 
	document.getElementById("mliRemarks1").disabled=true; 
	function validateFormData(){
		if(document.getElementById('mliRemarks').value==null || document.getElementById('mliRemarks').value==""){
		     alert("Please enter the Remarks!");
		     document.getElementById("mliRemarks").focus();
		     document.getElementById("mliRemarks").disabled=false;
		     //document.getElementById("approve1").readOnly = false;
		     document.getElementById("approve1").disabled=true;
		    // document.getElementById('approve1').style.visibility = 'false';
		     return false;
	    }
	}    

</script>