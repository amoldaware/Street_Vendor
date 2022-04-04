<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script  src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<title>Create Exposure Limit</title>		
		 <link href = "css/jquery-ui-css.css" rel="stylesheet" type="text/css">
		 <script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		 <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		 <LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
		 <title>Create Exposure Limit</title>
	</head>
	<body onload="clearField()">
		<div class="main-section">
			<div class="container-fluid">
<nav aria-label="breadcrumb">
  <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/ExposureListingMaker.html">Exposure creation & Modification</a></li>  
    <li class="breadcrumb-item active" aria-current="page">Create Exposure Limit</li>
  </ol>
</nav>
				<div>	
					<div class="frm-section">
						<div class="col-md-12">
							<form:form  method="POST" action="createExposureLimitByMakerAndSendForApprovalToChecker.html" class="form-horizontal" id="exposureMasterMakerDetailsFormId" onload="hideDiv()">
								<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>MLI Long Name : <span style="color:red">*</span></label>								
											  	<input type="text" class="form-control cus-control d-none" placeholder="MLI Long Name" >
											 	<form:select path="mliLongName" id="mliLongName" onchange="getMliShortName()" class="form-control cus-control">
												<form:option value="NONE" label="---Select---"/>	
												<form:options items="${mliLongName}" />	
											</form:select>
											<div id="requiredMliLongName" Class="displayErrorMessageInRedColor"></div>
										</div>
									</div>
								</div>
								<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>MLI Short Name : <span style="color:red">*</span></label>								
									 		<input type="text" class="form-control cus-control d-none" placeholder="MLI Short Name" >
									  		<form:select path="mliShortName" id="mliShortName" disabled="false" class="form-control cus-control">
												<form:option value="NONE" label="---Select---" /> 
											</form:select>
											<div id="requiredMliShortName" Class="displayErrorMessageInRedColor"></div>
										</div>
									</div>
								</div>
		 	 
							 	<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Exposure Limit : <span style="color:red">*</span></label>												 
									 		<form:input path="mliExposureLimit" value="" size="20"  style="text-align: right" id="mliExposureLimit" class="form-control cus-control" placeholder="eg.20000000"/>
											<div id="requiredExposureLimit" Class="displayErrorMessageInRedColor"></div>
									  	</div>
									</div>
							 	</div>
							 	 
								<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Date of Sanction of Exposure : <span style="color:red">*</span></label>												 
								 			<form:input path="mliDateOfSanctionOfExposure"  value="" size="20" id="mliDateOfSanctionOfExposure" class="form-control cus-control" placeholder="eg.dd/mm/yyyy"/>
											<div id="requiredExposureLimitMliDateOfSanctionOfExposure" Class="displayErrorMessageInRedColor"></div>
										</div>
									</div>
								</div>
							<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label class="d-block text-purple" >Validity of Exposure Limit(dd/mm/yyyy):</label>
										<div class="col-md-6 prl-10">
											<label>Start Date : <span style="color:red">*</span></label>										
											<form:input path="mliValidityOfExposureLimitStartDate" value=""   size="20"    id="mliValidityOfExposureLimitStartDate" class="form-control cus-control" style="text-align: left" placeholder="eg.dd/mm/yyyy"/>
											<div id="requiredMliValidityOfExposureLimitStartDate" Class="displayErrorMessageInRedColor"></div>
											<div id="startDateShouldBeGreaterThanSanctionDate" Class="displayErrorMessageInRedColor"></div>
										</div>
										
										<div class="col-md-6 prl-10">
											<label>End Date : <span style="color:red">*</span></label>										 
											<form:input path="mliValidityOfExposureLimitEndDate" type=""   size="20"   id="mliValidityOfExposureLimitEndDate"  class="form-control cus-control" placeholder="eg.dd/mm/yyyy" />
											<div id="requiredMliValidityOfExposureLimitEndDate" Class="displayErrorMessageInRedColor"></div>
											
										</div>
									</div>						  
				 				</div>
	 		 				</div>
	 		 					 
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<label class="d-block text-purple" style="visibility: hidden";> 1</label>
									<div class="col-md-12 prl-10">
										<label>Guarantee Fee(% p.a) :<span style="color:red">*</label>												 
								 		<form:input path="gurantee_fee"  value="" size="20" id="gurantee_fee" class="form-control cus-control" placeholder=""/>
								 		<div id="requiredGuranteeFee" Class="displayErrorMessageInRedColor"></div>
								 	</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
								<label class="d-block text-purple" style="visibility: hidden";> 4</label>
									<div class="col-md-12 prl-10">
										<label>Pay-Out Cap( Times ) :<span style="color:red">*</label>												 
								 		<form:input path="pay_out_cap"  value="" size="20" id="pay_out_cap" class="form-control cus-control" placeholder=""/>
								 		<div id="requiredPayOutCap" Class="displayErrorMessageInRedColor"></div>
								 	</div>
								</div>
							 </div>
							 <div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
									<label class="d-block text-purple" style="visibility: hidden";> 5</label>
										<div class="col-md-12 prl-10">
											<label>Gurantee Coverage : <span style="color:red">*</span></label>								
									 		<input type="text" class="form-control cus-control d-none"  >
									  		<form:select path="guranteeCoverage" id="guranteeCoverage" disabled="false" class="form-control cus-control">
												<form:option value="NONE" label="---Select Gurantee Coverage---"/> 
												<form:option value="50%" label="50%"/>
												<form:option value="60%" label="60%"/>
												<form:option value="75%" label="75%"/>
											</form:select>
											<div id="requiredGuranteeCoverage" Class="displayErrorMessageInRedColor"></div>
										</div>
									</div>
									<div class="d-inlineblock mt-35">
										<input type="button" value="Create Exposure Limit" class="btn btn-reset"  class="btn btn-reset" onclick="return validateFormData()"/>		<!-- mt-25 -->	
										<!-- <input type="button" value="Back" class="btn btn-reset" onclick="return exitMLIDetails()"/> -->						
									</div>
								</div>
								<div></div>
						 
							</form:form>	
						</div>
					</div>
				</div>
			</div>
		</div>
		<div align="left" id="successMsg"><font color="green" size="5">${message}</font></div>
		<div align="left" id="error"><font color="red" size="5">${error}</font></div>
	</body>
</html>
<script language="javascript">
	function exitMLIDetails() {
		document.getElementById('exposureMasterMakerDetailsFormId').action = "/SV/cgtmseMakerHomeBack.html";
		document.getElementById('exposureMasterMakerDetailsFormId').submit();
	}

	$(document).ready(function() {
	    $('#id1').hide();
	    $('#preview').on('click', function() {
	    $('#div1').toggle(300);
	    });
	});

	//Ajax Call
	function getMliShortName(){
		var mliLName=document.getElementById("mliLongName").value;
		try {
			$.ajax({
						type : "POST",
						url : "fetchMliShortName.html",
						data : "mliLName=" + mliLName,
						success : function(response) {
							var select2 = document.getElementById('mliShortName');
							if (response.status == "SUCCESS") {
								document.getElementById('mliShortName').options.length=0;
								document.getElementById("mliShortName").value=response.result[0].mliShortName;
								option = document.createElement('option');
								option.text =response.result[0].mliShortName;
								select2.add(option);
							}
						},
						error : function(e) {
							/* alert('Server Error : ' + e); */
						}
					});
	
		} catch (err) {
			alert('Error is : ' + err);
		}
	}  

	//Date Picker UI
	$(function() {
	  	$("#mliDateOfSanctionOfExposure").datepicker({ dateFormat: 'dd/mm/yy' });
		$("#mliValidityOfExposureLimitStartDate").datepicker({ dateFormat: 'dd/mm/yy' });
		$("#mliValidityOfExposureLimitEndDate").datepicker({ dateFormat: 'dd/mm/yy' });
		
	});

//Form Validation
function validateFormData(){
	var long_name1 = document.getElementById('mliLongName');
    var long_name2 = long_name1.options[long_name1.selectedIndex].value;
    var shortName1 = document.getElementById('mliShortName');
    var shortName2 = shortName1.options[shortName1.selectedIndex].value;
    var today = new Date();
    var chkflag=5;
    var long_name1 = document.getElementById('mliLongName');
    var long_name2 = long_name1.options[long_name1.selectedIndex].value;
    var shortName1 = document.getElementById('mliShortName');
    var shortName2 = shortName1.options[shortName1.selectedIndex].value;
    if(long_name2==null || long_name2=="" || long_name2=='NONE'){
		document.getElementById("requiredMliLongName").innerHTML="Select Long Name.";
		chkflag=chkflag-1; 
	}else{
	    document.getElementById("requiredMliLongName").innerHTML="";
	}

    if(shortName2==null || shortName2=="" || shortName2=='NONE'){
		document.getElementById("requiredMliShortName").innerHTML="Select Short Name.";
		chkflag=chkflag-1;  
	}else{
	    document.getElementById("requiredMliShortName").innerHTML="";
	}
	if(document.getElementById('mliExposureLimit').value==null || document.getElementById('mliExposureLimit').value==""){
	    document.getElementById("requiredExposureLimit").innerHTML="Enter Exposure Limit.";
	    chkflag=chkflag-1;
	}else{
		document.getElementById("requiredExposureLimit").innerHTML="";
	}
	if(document.getElementById('mliDateOfSanctionOfExposure').value==null || document.getElementById('mliDateOfSanctionOfExposure').value==""){
	    document.getElementById("requiredExposureLimitMliDateOfSanctionOfExposure").innerHTML="Enter Date of Sanction.";
	    chkflag=chkflag-1;
	}else{
		document.getElementById("requiredExposureLimitMliDateOfSanctionOfExposure").innerHTML="";
	}
	if(document.getElementById('mliValidityOfExposureLimitStartDate').value==null || document.getElementById('mliValidityOfExposureLimitStartDate').value==""){
	    document.getElementById("requiredMliValidityOfExposureLimitStartDate").innerHTML="Enter Validity Start Date.";
	    chkflag=chkflag-1;
	}else{
		document.getElementById("requiredMliValidityOfExposureLimitStartDate").innerHTML="";
	}
	if(document.getElementById('mliValidityOfExposureLimitEndDate').value==null || document.getElementById('mliValidityOfExposureLimitEndDate').value==""){
	    document.getElementById("requiredMliValidityOfExposureLimitEndDate").innerHTML="Enter Validity End Date.";
	    chkflag=chkflag-1;
	}else{
		document.getElementById("requiredMliValidityOfExposureLimitEndDate").innerHTML="";
	}
	if(document.getElementById('gurantee_fee').value==null || document.getElementById('gurantee_fee').value==""){
	    document.getElementById("requiredGuranteeFee").innerHTML="Enter Gurantee Fee.";
	    chkflag=chkflag-1;
	}else{
		document.getElementById("requiredGuranteeFee").innerHTML="";
	}
	if(document.getElementById('pay_out_cap').value==null || document.getElementById('pay_out_cap').value==""){
	    document.getElementById("requiredPayOutCap").innerHTML="Enter Pay Out Cap.";
	    chkflag=chkflag-1;
	} else{
		document.getElementById("requiredPayOutCap").innerHTML="";
	}
	
	var gurCoveObj = document.getElementById('guranteeCoverage');
	var gurCVal = gurCoveObj.options[gurCoveObj.selectedIndex].value;    
	if(gurCVal=='NONE' || gurCVal=="" ||gurCVal==null){
	   document.getElementById("requiredGuranteeCoverage").innerHTML="Select Gurantee Coverage.";
	   chkflag=chkflag-1;
	}else{
	   document.getElementById("requiredGuranteeCoverage").innerHTML=""; 
	}
	//---Exposure Limit Field Validation Start
	//Only Numberic Number Allowed,Decimal Number not allowed. 
	var re = /^[-+]?[0-9]+\.[0-9]+$/;
	if(document.getElementById('mliExposureLimit').value!=""){
		var strEx=document.getElementById('mliExposureLimit').value;
		if(strEx.match(re)){
			document.getElementById("requiredExposureLimit").innerHTML="";
			document.getElementById("requiredExposureLimit").innerHTML="Decimal Not Allowed.";
			chkflag=chkflag-1;
		}
	}
	
	//Only Digit Allowed, Alpha Character not allowed .
	var letters =  /^[a-zA-Z]+$/; 
	if(document.getElementById('mliExposureLimit').value!=""){
		var strEx1=document.getElementById('mliExposureLimit').value;
		if(strEx1.match(letters)){
			document.getElementById("requiredExposureLimit").innerHTML="";
			document.getElementById("requiredExposureLimit").innerHTML="Enter Digit Only.";
			chkflag=chkflag-1;
		}
	}
	if(document.getElementById('mliExposureLimit').value!=""){
		if(document.getElementById('mliExposureLimit').value<=1000000){
			document.getElementById("requiredExposureLimit").innerHTML="";
		    document.getElementById("requiredExposureLimit").innerHTML="It should be 10 lakh & above.";
		    chkflag=chkflag-1;
		}
	}
	//---Exposure Limit Field Validation End
	
	//---Gurantee Fee Field Validation Start
	//Only Digit Allowed Alpha Character not allowed . 
	var letters2 =  /^[a-zA-Z]+$/; 
	if(document.getElementById('gurantee_fee').value!=""){
		var strGuranteeFee=document.getElementById('gurantee_fee').value;
		if(strGuranteeFee.match(letters2)){
			document.getElementById("requiredGuranteeFee").innerHTML="";
			document.getElementById("requiredGuranteeFee").innerHTML="Enter Digit Only.";
			chkflag=chkflag-1;
		}
	}
	
	//Guarantee Fee Can not be greater than 10%
	var strGuranteeFee=document.getElementById('gurantee_fee').value;
	if(document.getElementById('gurantee_fee').value!=""){
		if(strGuranteeFee >10){
			document.getElementById("requiredGuranteeFee").innerHTML="";
			document.getElementById("requiredGuranteeFee").innerHTML="Guarantee Fee Can not be greater than 10 %";
			chkflag=chkflag-1;
		}
	}
	//---Gurantee Fee Field Validation End
	
	//---Pay Out Cap Field Validation Start
	//Only Numberic Number Allowed,Decimal Number not allowed. 
	var strPayOutCap=document.getElementById('pay_out_cap').value;
	var re1 = /^[-+]?[0-9]+\.[0-9]+$/;
	if(document.getElementById('pay_out_cap').value!=""){
		if(strPayOutCap.match(re1)){
			document.getElementById("requiredPayOutCap").innerHTML="";
			document.getElementById("requiredPayOutCap").innerHTML="Decimal not Allowed.";
			chkflag=chkflag-1;
		}
	}
	//Pay-Out Cap Can not be greater than 10 Times
	var strPayOutCap=document.getElementById('pay_out_cap').value;
	if(document.getElementById('pay_out_cap').value!=""){
		if(strPayOutCap >10){
			document.getElementById("requiredPayOutCap").innerHTML="";
			document.getElementById("requiredPayOutCap").innerHTML="Pay-Out Cap Can not be greater than 10 Times";
			chkflag=chkflag-1;
		}
	}

	//Only Digit Allowed Alpha Character not allowed . 
	
	var letters1 =  /^[a-zA-Z]+$/; 
	if(document.getElementById('pay_out_cap').value!=""){
		var strPayOutCap1=document.getElementById('pay_out_cap').value;
		if(strPayOutCap1.match(letters1)){
			document.getElementById("requiredPayOutCap").innerHTML="";
			document.getElementById("requiredPayOutCap").innerHTML="Enter Digit Only.";
			chkflag=chkflag-1;
		}
	} 
	//---Pay Out Cap Field Validation End
	if(document.getElementById('mliDateOfSanctionOfExposure').value!=null && document.getElementById('mliDateOfSanctionOfExposure').value!=null) {
		var strMliDateOfSanctionOfExposure = document.getElementById('mliDateOfSanctionOfExposure').value;
		var array1 = strMliDateOfSanctionOfExposure.split("/");
		var mlisanExpDate=mlisanExpDate=array1[2]+array1[1]+array1[0];
		var strMliValidityOfExposureLimitStartDate = document.getElementById('mliValidityOfExposureLimitStartDate').value;
		var array2 = strMliValidityOfExposureLimitStartDate.split("/");
		var mliExposureLimitStartDate=mliExposureLimitStartDate=array2[2]+array2[1]+array2[0];
		if (mliExposureLimitStartDate >= mlisanExpDate) {
			document.getElementById("requiredMliValidityOfExposureLimitStartDate").innerHTML="";
		}else{
			document.getElementById("mliValidityOfExposureLimitStartDate").innerHTML="";
	     	document.getElementById("requiredMliValidityOfExposureLimitStartDate").innerHTML="StartDate date should be greater than Sanction Date.";
	    	chkflag=chkflag-1;
		}
	}
	if(chkflag==5){
		document.getElementById('exposureMasterMakerDetailsFormId').submit();
	}else{
		return false;
	}
}

	function clearField(){
		var clearLongName = document.getElementById('mliLongName');
		clearLongName.selectedIndex = 0;
		var clearShortName = document.getElementById('mliShortName');
		clearShortName.selectedIndex = 0;
		document.getElementById('mliExposureLimit').value="";
		document.getElementById('mliDateOfSanctionOfExposure').value="";
		document.getElementById('mliValidityOfExposureLimitStartDate').value="";
		document.getElementById('mliValidityOfExposureLimitEndDate').value="";
	}

</script>