<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<title>Modify Exposure Limit By Maker</title>
		<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<script type="text/javascript" src="js/jquery-ui-1.10.2/ui/jquery.ui.datepicker.js"></script>
		<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
		<style type="text/css">
			.mar-top21{margin-top:21px;}
		</style>
	</head>
	
	<body bgcolor="#E0FFFF">
		<div class="main-section">
			<div class="container-fluid">
			<nav aria-label="breadcrumb">
  <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/ExposureListingMaker.html">Exposure creation & Modification</a></li>  
    <li class="breadcrumb-item active" aria-current="page">Modify Exposure Limit</li>
  </ol>
</nav>
				<div>	
					<div class="frm-section">
						<div class="col-md-12">
							<div class="notification-bar" id="successMsg"><!-- <font color="green" size="5"> -->${Modifymessage}<!-- </font> --></div>
							<div class="error1" id="error"><!-- <font color="red" size="5"> -->${modifyerror}<!-- </font> --></div>
							<form:form method="POST" action="modifyExposureLimitByMakerAndSendForApproval.html"  class="form-horizontal" onload="hideDiv()" id="A">
								<c:forEach items="${modifyList}" var="modifyList">
									<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
										<div class="form-group">
											<div class="col-md-12 prl-10">
												<label>MLI Long Name: <span style="color:red">*</span></label>								
												<form:input path="mliLongName" class="form-control cus-control" value="${modifyList.mliLongName}" size="20" style="text-align: right" id="mliLongName" readOnly="true" />
													<div id="requiredMliLongName" Class="displayErrorMessageInRedColor"></div>
											</div>
										</div>
									</div>
									<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
										<div class="form-group">
											<div class="col-md-12 prl-10">
												<label>MLI Short Name: <span style="color:red">*</span></label>								
										 		<form:input path="mliShortName" class="form-control cus-control" value="${modifyList.mliShortName}" size="20" style="text-align: right" id="mliShortName" readOnly="true" />
													<div id="requiredMliShortName" Class="displayErrorMessageInRedColor"></div>
										  	</div>
										</div>
									</div>
									<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
										<div class="form-group">
											<div class="col-md-12 prl-10">
												<label>Exposure Limit (Rs):<span style="color: red">*</span></label>												 
												<form:input path="mliExposureLimit" class="form-control cus-control" value="${modifyList.mliExposureLimit}" size="20" style="text-align: right" id="mliExposureLimit" onblur="myFunction()" />
												<div id="requiredExposureLimit" Class="displayErrorMessageInRedColor"></div>
											</div>
									  	</div>
									</div>
									<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
										<div class="form-group">
											<div class="col-md-12 prl-10">
												<label>Date of Sanction of Exposure: <span style="color:red">*</span></label>												 
												<form:input class="form-control cus-control" path="mliDateOfSanctionOfExposure" value="${modifyList.exposureSanctionDate}" size="20" id="mliDateOfSanctionOfExposure" style="text-align: left" />
												<div id="requiredExposureLimitMliDateOfSanctionOfExposure" Class="displayErrorMessageInRedColor"></div>
											</div>
										</div>
									</div>
									<div class="col-md-4 col-sm-4 col-xs-12 ">
										<div class="form-group">
											<label class="d-inlineblock text-purple">Validity of Exposure Limit(dd/mm/yyyy): </label>	
											<div class="col-md-6 prl-10">					
												<label>Start Date: <span style="color:red">*</span></label>										
												<form:input class="form-control cus-control" path="mliValidityOfExposureLimitStartDate" value="${modifyList.fromDate}" size="20" id="mliValidityOfExposureLimitStartDate" style="text-align: left" />
													<div id="requiredMliValidityOfExposureLimitStartDate" Class="displayErrorMessageInRedColor"></div>
													<div id="requiredMliValidityOfExposureLimitStartDate1" Class="displayErrorMessageInRedColor"></div>
													
											</div>
											<div class="col-md-6 prl-10">				
												<label>End Date: <span style="color:red">*</span></label>										 
												<form:input class="form-control cus-control" path="mliValidityOfExposureLimitEndDate" value="${modifyList.toDate}" size="20" id="mliValidityOfExposureLimitEndDate" style="text-align: left" />
													<div id="requiredMliValidityOfExposureLimitEndDate" Class="displayErrorMessageInRedColor"></div>
											</div>
										 </div>
									</div>
									<div class="clearfix"></div>									
									<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
										<div class="form-group">
											<div class="col-md-12 prl-10">
												<label>Guarantee Fee(% p.a):<span style="color: red">*</span></label>												 
												<form:input path="gurantee_fee" class="form-control cus-control" value="${modifyList.gurantee_fee}" size="20" style="text-align: right" id="gurantee_fee" />
									  			<div id="requiredGuranteeFee" Class="displayErrorMessageInRedColor"></div>
									  		
									  		</div>
										</div>
									</div>
									<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
											<div class="form-group">
												<div class="col-md-12 prl-10">
										<label>Pay-Out Cap (Times):<span style="color: red">*</span></label>												 
											<form:input path="pay_out_cap" class="form-control cus-control"	value="${modifyList.pay_out_cap}" size="20"	style="text-align: right" id="pay_out_cap" />
											<div id="requiredPayOutCap" Class="displayErrorMessageInRedColor"></div>
											</div>
										</div>
									</div>
									 <div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Gurantee Coverage: <span style="color:red">*</span></label>								
									 		<input type="text" class="form-control cus-control d-none"  >
									  		<form:select path="guranteeCoverage" id="guranteeCoverage" disabled="false" class="form-control cus-control">
												<form:option value="${modifyList.guranteeCoverage}" label="${modifyList.guranteeCoverage}"/>
												<form:option value="50%" label="50%"/>
												<form:option value="60%" label="60%"/>
												<form:option value="75%" label="75%"/> 
											</form:select>
											<div id="requiredGuranteeCoverage" Class="displayErrorMessageInRedColor"></div>
										</div>
									</div>
								</div>
									<div class="col-md-2 col-sm-4 col-xs-12">
										<div class="form-group">
											<div class="col-md-12 prl-10">
												<label>Return Remark:<span style="color: red">*</span></label>												 
												<textarea readOnly="true" class="form-control cus-control" style="height:60px !important;">${modifyList.remarks} </textarea>
												<div id="remarks" Class="displayErrorMessageInRedColor"></div>
											</div>
										</div>
									</div>
								 	<div class="d-inlineblock mt-25">			
									 	<input type="button" value="Modify Exposure Limit" name="action5" class="btn btn-reset" id="approveAndModify" onclick="return validateFormData()" />						
						    			<input type="submit" value="Back" name="action4" class="btn btn-reset"  id="back" onclick="return backFormData()"/>
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

<script language="javascript"><!--
	 //Ajax Call
	 function getMliShortName(){
		var mliLName=document.getElementById("mliLongName").value;
		try {
			$.ajax({
				type : "POST",
				url : "getMliExposureMasterMakerShortName.html",
				data : "mliLName=" + mliLName,
				success : function(response) {
					var select2 = document.getElementById('mliShortName');
					if (response.status == "SUCCESS") {
						document.getElementById('mliShortName').options.length=0;
						document.getElementById("mliShortName").value=response.result[0].mliShortName;
						document.getElementById("mliExposureLimit").value=response.result[0].mliExposureLimit;
						document.getElementById("mliDateOfSanctionOfExposure").value=response.result[0].exposureSanctionDate;
						document.getElementById("mliValidityOfExposureLimitStartDate").value=response.result[0].fromDate;
						document.getElementById("mliValidityOfExposureLimitEndDate").value=response.result[0].toDate;
						document.getElementById("mliRemarks").value=response.result[0].remarks;
						option = document.createElement('option');
						option.text =response.result[0].mliShortName;
						select2.add(option);
					}
				},
				error : function(e) {
					 alert('Server Error : ' + e); 
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
	
	var x = document.getElementById("mliExposureLimit").value;
	/*
	Commented By Parmanand 21June2019
	function myFunction() { 
		var y = document.getElementById("mliExposureLimit").value;
		var y1=parseInt(y);
	    if(x<y1){
	    	 document.getElementById("requiredExposureLimit").innerHTML="Once Exposure gets Approve or Rejected,you can't increase the limit of Exposure Amount.";
	    }
	    else{
	    	 document.getElementById("requiredExposureLimit").innerHTML="";	
	    }
	}*/

	//Form Validation
	function backFormData(){
		document.getElementById('A').action = "/SV/ExposureListMaker.html";
		document.getElementById('A').submit();
	}
	function validateFormData(){
		var today = new Date();
		var chkflag=5;
		
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
			document.getElementById("requiredMliValidityOfExposureLimitStartDate").innerHTML="";
			document.getElementById("requiredMliValidityOfExposureLimitStartDate1").innerHTML="Enter Validity Start Date.";
		    chkflag=chkflag-1;
		}else{
			document.getElementById("requiredMliValidityOfExposureLimitStartDate1").innerHTML="";
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
		
		//Only Numberic Number Allowed,Decimal Number not allowed. 
		var re = /^[-+]?[0-9]+\.[0-9]+$/;
		if(document.getElementById('mliExposureLimit').value!=""){
			var strEx=document.getElementById('mliExposureLimit').value;
			if(strEx.match(re)){
				document.getElementById("requiredExposureLimit").innerHTML="";
				document.getElementById("requiredExposureLimit").innerHTML="Decimal Not Allowed";
				chkflag=chkflag-1;
			}
		}
		
		//Only Digit Allowed Alpha Character not allowed . 
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
			    document.getElementById("requiredExposureLimit").innerHTML="It should be 10 lakh and above.";
			    chkflag=chkflag-1;
			}
		}

		//Only Digit Allowed Alpha Character not allowed . 
		var strGuranteeFee=document.getElementById('gurantee_fee').value;
		var letters2 =  /^[a-zA-Z]+$/; 
		if(document.getElementById('gurantee_fee').value!=""){
			if(strGuranteeFee.match(letters2)){
				document.getElementById("requiredGuranteeFee").innerHTML="Enter Digit Only.";
				chkflag=chkflag-1;
			}
		}
		//Only Digit Allowed Alpha Character not allowed . 
		var strPayOutCap1=document.getElementById('pay_out_cap').value;
		var letters1 =  /^[a-zA-Z]+$/; 
		if(document.getElementById('pay_out_cap').value!=""){
			if(strPayOutCap1.match(letters1)){
				document.getElementById("requiredPayOutCap").innerHTML="";
				document.getElementById("requiredPayOutCap").innerHTML="Enter Digit Only.";
				chkflag=chkflag-1;
			}
		} 
		//Guarantee Fee Can not be greater than 10%
		if(document.getElementById('gurantee_fee').value!=""){
			var strGuranteeFee=document.getElementById('gurantee_fee').value;
			if(strGuranteeFee >10){
				document.getElementById("requiredGuranteeFee").innerHTML="Guarantee Fee Can not be greater than 10 %";
				chkflag=chkflag-1;
			}
		}

		//Only Numberic Number Allowed,Decimal Number not allowed. 
		var strPayOutCap=document.getElementById('pay_out_cap').value;
		var re1 = /^[-+]?[0-9]+\.[0-9]+$/;
		if(document.getElementById('pay_out_cap').value!=""){
			if(strPayOutCap.match(re1)){
				document.getElementById("requiredPayOutCap").innerHTML="Decimal not allowed.";
				chkflag=chkflag-1;
			}
		}
		
		//Pay-Out Cap Can not be greater than 10 Times
		if(document.getElementById('pay_out_cap').value!=""){
			var strPayOutCap=document.getElementById('pay_out_cap').value;
			if(strPayOutCap >10){
				document.getElementById("requiredPayOutCap").innerHTML="Pay-Out Cap Can not be greater than 10 Times";
				chkflag=chkflag-1;
			}
		}


		//Only Digit Allowed Alpha Character not allowed . 
		
		var letters1 =  /^[a-zA-Z]+$/; 
		if(document.getElementById('pay_out_cap').value!=""){
			if(strPayOutCap1.match(letters1)){
				var strPayOutCap1=document.getElementById('pay_out_cap').value;
				document.getElementById("requiredPayOutCap").innerHTML="Enter Digit Only.";
				chkflag=chkflag-1;
			}
		} 
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
			document.getElementById('A').submit();
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
	
--></script>