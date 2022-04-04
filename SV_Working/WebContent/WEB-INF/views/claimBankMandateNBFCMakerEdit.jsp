<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<link href="css/jquery-ui-css.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
		<title>Update Bank Mandate Details</title>
		<script type="text/javascript">
			$(function() {
				disbaleEnableSubmitButton();
			});
		</script>
		
		<script type="text/javascript">

			function disbaleEnableSubmitButton(){
				try{
					if(document.getElementById('status').value=="" || document.getElementById('status').value=='CR' || document.getElementById('status').value=='CCR'){
						document.getElementById('Update').disabled=false;//Enable
						
					}else{
						document.getElementById('Update').disabled=true;//Disable
					}
				}catch(err) {
					alert("Error in disbaleEnableSubmitButton function =="+err.message);
				}
	
			}
		
		function validateFormOnclickOfSubmitButton(){
			try {
				var chkflag=5;
				if(document.getElementById('mliName').value=="" || document.getElementById('mliName').value==null){
					   document.getElementById("requiredMliName").innerHTML=" Field is mandatory";
					   chkflag=chkflag-1;
				}else{
					document.getElementById("requiredMliName").innerHTML="";
				}
					
				if(document.getElementById('memberId').value=="" || document.getElementById('memberId').value==null){
					document.getElementById("requiredMemberId").innerHTML=" Field is mandatory";
					chkflag=chkflag-1;
				}else{
					document.getElementById("requiredMemberId").innerHTML="";
				}

				if(document.getElementById('contactNo').value=="" || document.getElementById('contactNo').value==null){
					document.getElementById("requiredContactNo").innerHTML=" Field is mandatory";
					chkflag=chkflag-1;
				}else{
					document.getElementById("requiredContactNo").innerHTML="";
					var contactNoRegExp = /^\d{12}$/;
					if(!(document.getElementById('contactNo').value.match(contactNoRegExp))){
						document.getElementById("requiredContactNo").innerHTML="Contact Number Must be 12 Digit.";
					    chkflag=chkflag-1;  
					}else{
						document.getElementById("requiredContactNo").innerHTML=""; 
					}
				}
				
				
				if(document.getElementById('mobileNo').value=="" || document.getElementById('mobileNo').value==null){
					document.getElementById("requiredMobileNo").innerHTML=" Field is mandatory";
					chkflag=chkflag-1;
				}else{
					document.getElementById("requiredMobileNo").innerHTML="";
					var mobileNoRegExp = /^\d{10}$/;
					if(!(document.getElementById('mobileNo').value.match(mobileNoRegExp))){
						document.getElementById("requiredMobileNo").innerHTML="Mobile Number Must be 10 Digit.";
					    chkflag=chkflag-1;  
					}else{
						document.getElementById("requiredMobileNo").innerHTML=""; 
					}
				}


				if(document.getElementById('emailId').value=="" || document.getElementById('emailId').value==null){
					document.getElementById("requiredEmailId").innerHTML=" Field is mandatory";
					chkflag=chkflag-1;
				}else{
					document.getElementById("requiredEmailId").innerHTML="";
					var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
					if(document.getElementById('emailId').value.match(mailformat)){
						document.getElementById("requiredEmailId").innerHTML="";
					}else{
						document.getElementById("requiredEmailId").innerHTML="Invalid Email Id.";
					    chkflag=chkflag-1;  
						
					}
				}
				
				if(document.getElementById('mliBeneficiaryName').value=="" || document.getElementById('mliBeneficiaryName').value==null){
					document.getElementById("requiredMliBeneficiaryName").innerHTML=" Field is mandatory";
					chkflag=chkflag-1;
				}else{
					document.getElementById("requiredMliBeneficiaryName").innerHTML="";
				}

				if(document.getElementById('mliBeneficiaryBankName').value=="" || document.getElementById('mliBeneficiaryBankName').value==null){
					document.getElementById("requiredMliBeneficiaryBankName").innerHTML=" Field is mandatory";
					chkflag=chkflag-1;
				}else{
					document.getElementById("requiredMliBeneficiaryBankName").innerHTML="";
				}
				

				if(document.getElementById('branchName').value=="" || document.getElementById('branchName').value==null){
					document.getElementById("requiredBranchName").innerHTML=" Field is mandatory";
					chkflag=chkflag-1;
				}else{
					document.getElementById("requiredBranchName").innerHTML="";
				}

				var option1=document.getElementsByName('accountType');
				if (!(option1[0].checked || option1[1].checked)) {
				     document.getElementById("requiredAccountType").innerHTML="Field is mandatory";
				     chkflag=chkflag-1; 
				}else{
					document.getElementById("requiredAccountType").innerHTML="";
				}	

				if(document.getElementById('branchCode').value=="" || document.getElementById('branchCode').value==null){
					document.getElementById("requiredBranchCode").innerHTML=" Field is mandatory";
					chkflag=chkflag-1;
				}else{
					document.getElementById("requiredBranchCode").innerHTML="";
				}

				if(document.getElementById('micrCode').value=="" || document.getElementById('micrCode').value==null){
					document.getElementById("requiredMicrCode").innerHTML=" Field is mandatory";
					chkflag=chkflag-1;
				}else{
					document.getElementById("requiredMicrCode").innerHTML="";
					var micrCodeV = /^\d{9}$/;
					if(!(document.getElementById('micrCode').value.match(micrCodeV))){
						document.getElementById("requiredMicrCode").innerHTML="MICR code should be 9 digits only.";
					    chkflag=chkflag-1;  
					}else{
						document.getElementById("requiredMicrCode").innerHTML=""; 
					}
				}
				
				if(document.getElementById('accountNo').value=="" || document.getElementById('accountNo').value==null){
					document.getElementById("requiredAccountNo").innerHTML=" Field is mandatory";
					chkflag=chkflag-1;
				}else{
					document.getElementById("requiredAccountNo").innerHTML="";
					if(document.getElementById('accountNo').value.length>=21){
						document.getElementById("requiredAccountNo").innerHTML="Account no should be 20 digits or alphanumeric only";
						chkflag=chkflag-1;  
					}else{
						document.getElementById("requiredAccountNo").innerHTML="";
					}
				}
				
				if(document.getElementById('ifscCode').value=="" || document.getElementById('ifscCode').value==null){
					document.getElementById("requiredIfscCode").innerHTML=" Field is mandatory";
					chkflag=chkflag-1;
				}else{
					document.getElementById("requiredIfscCode").innerHTML="";
					if(document.getElementById('ifscCode').value.length !=11 ){
						document.getElementById("requiredIfscCode").innerHTML="IFSC Code should be 11 digit only or alphanumeric only";
						chkflag=chkflag-1;  
					}else{
						document.getElementById("requiredIfscCode").innerHTML="";
					}
				}
				/*if(document.getElementById('rtgsNo').value=="" || document.getElementById('rtgsNo').value==null){
					document.getElementById("requiredRtgsNo").innerHTML=" Field is mandatory";
					chkflag=chkflag-1;
				}else{
					document.getElementById("requiredRtgsNo").innerHTML="";
				}

				if(document.getElementById('rtgsNo').value!=""||document.getElementById('rtgsNo').value!=null){
					if(document.getElementById('rtgsNo').value.length >11 ){
						document.getElementById("requiredRtgsNo").innerHTML="RTGS No. should be 11 digit only or alphanumeric only";
						chkflag=chkflag-1;  
					}
				}*/
				

				if(document.getElementById('file').value=="" || document.getElementById('file').value==null){
					document.getElementById("requiredFile").innerHTML=" Field is mandatory";
					chkflag=chkflag-1;
				}else{
					document.getElementById("requiredFile").innerHTML="";
				}
				
				var inp = document.getElementById('file');
				if(inp.files.length === 0){
					document.getElementById("requiredFile").innerHTML="Attachment of PDF is mandatory";
			        inp.focus();
			        chkflag=chkflag-1; 
			    }else{
			    	if(checkextension()==true){
						document.getElementById("requiredFile").innerHTML="";
					}else{
						document.getElementById("requiredFile").innerHTML="Only .PDF format";
						chkflag=chkflag-1; 
					} 
			    	
			    	document.getElementById("requiredFile").innerHTML="";
			    }
				    
				
				if(chkflag==5){
					document.getElementById('updateBankMandateByNbfcMkFormId').action = "/SV/updateBankMandateByNbfcMk.html";
					document.getElementById('updateBankMandateByNbfcMkFormId').submit();
				}else{
					return false;
				}
			
			}catch(err) {
				alert("Error Massage=="+err.message);
			}
		}
		function checkextension() {
			try{
				 var file = document.querySelector("#file");
				 if ( /\.(pdf)$/i.test(file.files[0].name) === false ){ 
				 return false;
				 }else{
				  return true;  
				 }
			}catch(err) {
				alert("Error Massage=="+err.message);
		
			}
		}
		function isNumberKey(evt, element) {
			  var charCode = (evt.which) ? evt.which : event.keyCode
			  if (charCode > 31 && (charCode < 48 || charCode > 57) && !(charCode == 46 || charCode == 8))
			    return false;
			  else {
			  var len = $(element).val().length;
			  var index = $(element).val().indexOf('.');
			  if (index > 0 && charCode == 46) {
			      return false;
			   }
			    if (index > 0) {
			      var CharAfterdot = (len + 1) - index;
			      if (CharAfterdot > 3) {
			        return false;
			      }
			    }
			
			  }
			  return true;
		}

					
		</script>
	</head>
	<body onload="clearField()">
		<div class="main-section">
			<div class="frm-section">
				<div class="col-md-12">
					<form:form method="POST" action="" enctype="multipart/form-data" class="form-horizontal" id="updateBankMandateByNbfcMkFormId">
						<h5><strong>Update Claim Bank Mandate Form</strong></h5>
						<h4 align="center"><font style="color: green; font-size: 20px;" >${displayErrorMsg1}</font></h4>
						<h4 align="center"><font style="color: black; font-size: 20px;" >${displayErrorMsg2}</font></h4>
						<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
							<div class="as">
								<div class="col-md-2 prl-10">
									<div id="requiredMliName" Class="displayErrorMessageInRedColor"></div>
									<label><span style="color: red">*</span>MLI Name :</label>
								</div>	
								<div class="col-md-2 prl-10">
									<form:input path="mliName"  id="mliName" value="${bankmanDateData1.mliName}" size="20" cssClass="form-control cus-control" readonly="true"/>
								</div>
								</div>
							</div>
						</div>
						
						
						
							<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-2 prl-10">
								 	<div id="requiredMemberId" Class="displayErrorMessageInRedColor"></div>
									<label><span style="color: red">*</span>Member Id :</label>
									</div>
									<div class="col-md-2 prl-10">
									<form:input path="memberId"  id="memberId" value="${bankmanDateData1.memberId}" size="20"  class="form-control cus-control" readonly="true"/>
								</div>
							</div>
						</div>
						
						<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-2 prl-10">
									<div id="requiredContactNo" Class="displayErrorMessageInRedColor"></div>
									<div id="requiredMaxlength" Class="displayErrorMessageInRedColor"></div>
									<label><span style="color: red">*</span>Contact No:</label>
									</div>
									<div class="col-md-2 prl-10">
									<form:input path="contactNo"  id="contactNo" value="${bankmanDateData1.contactNo}" size="20"   class="form-control cus-control" readonly="false"  maxlength="12" onkeypress="return isNumberKey(event,this)"  />
								</div>
							</div>
						</div>
						
						<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
									<div class="col-md-2 prl-10">
									<div id="requiredMobileNo" Class="displayErrorMessageInRedColor"></div>
									<label><span style="color: red">*</span>Mobile No:</label>
									</div>
									<div class="col-md-2 prl-10">
									<form:input path="mobileNo"  id="mobileNo" value="${bankmanDateData1.mobileNo}" size="20"   class="form-control cus-control" readonly="false" maxlength="10" onkeypress="return isNumberKey(event,this)" />
								</div>
							</div>
						</div>

							<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-2 prl-10">
									<div id="requiredEmailId" Class="displayErrorMessageInRedColor"></div>
									<label><span style="color: red">*</span>Email Id:</label>
									</div>
									<div class="col-md-2 prl-10">
									<form:input path="emailId"  id="emailId" value="${bankmanDateData1.emailId}"  size="20"   class="form-control cus-control" readonly="false"/>
								</div>
							</div>
						</div>
						
						<h5><strong>BANK DETAILS</strong></h5>
						
							<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-2 prl-10">
									<div id="requiredMliBeneficiaryName" Class="displayErrorMessageInRedColor"></div>
									<label><span style="color: red">*</span>MLI Name ( Beneficiary):</label>
									</div>
										<div class="col-md-2 prl-10">
									<form:input path="mliBeneficiaryName"  id="mliBeneficiaryName" value="${bankmanDateData1.mliBeneficiaryName}" size="20"   class="form-control cus-control" readonly="false"/>
								</div>
							</div>
						</div>
						
						<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-2 prl-10">
									<div id="requiredMliBeneficiaryBankName" Class="displayErrorMessageInRedColor"></div>
									<label><span style="color: red">*</span>Beneficiary's Bank Name:</label>
										</div>
										<div class="col-md-2 prl-10">
									<form:input path="mliBeneficiaryBankName"  id="mliBeneficiaryBankName" value="${bankmanDateData1.mliBeneficiaryBankName}" size="20"   class="form-control cus-control" readonly="false"/>
								</div>
							</div>
						</div>
						
						<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-2 prl-10">
									<div id="requiredBranchName" Class="displayErrorMessageInRedColor"></div>
									<label><span style="color: red">*</span>Branch Name:</label>
									</div>
										<div class="col-md-2 prl-10">
									<form:input path="branchName"  id="branchName" value="${bankmanDateData1.branchName}" size="20"   class="form-control cus-control" readonly="false"/>
								</div>
							</div>
						</div>
						
						<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-2 prl-10">
									<div id="requiredAccountType" Class="displayErrorMessageInRedColor"></div>
									<label><span style="color: red">*</span>Account Type:</label>
									</div>
									<div class="col-md-2 prl-10">
									  <c:choose>
											<c:when test="${bankmanDateData1.accountType=='Saving'}">
												<form:radiobutton path="accountType" id="accountType1" value="Saving" checked="checked" />Saving
											 	<form:radiobutton path="accountType" id="accountType2" value="Current" />Current
											 </c:when>
											<c:when test="${bankmanDateData1.accountType=='Current'}">
												<form:radiobutton path="accountType" id="accountType1" value="Saving" />Saving 
											 	<form:radiobutton path="accountType" id="accountType2" value="Current" checked="checked" />Current
											 </c:when>
											<c:otherwise>
												<form:radiobutton path="accountType" id="accountType1" value="Saving" />Saving 
											 	<form:radiobutton path="accountType" id="accountType2" value="Current" />Current
											</c:otherwise>
										</c:choose>
									</div>
								</div>
						</div>
						<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-2 prl-10">
									<div id="requiredBranchCode" Class="displayErrorMessageInRedColor"></div>
									<label><span style="color: red">*</span>Branch Code:</label>
									</div>
										<div class="col-md-2 prl-10">
									<form:input path="branchCode"  id="branchCode" value="${bankmanDateData1.branchCode}" size="20"   class="form-control cus-control" readonly="false"/>
								</div>
							</div>
						</div>
						
						<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-2 prl-10">
								<div id="requiredMicrCode" Class="displayErrorMessageInRedColor"></div>
									<label><span style="color: red">*</span>MICR Code:</label>
									</div>
										<div class="col-md-2 prl-10">
									<form:input path="micrCode"  id="micrCode" value="${bankmanDateData1.micrCode}" size="20"    maxlength="9" class="form-control cus-control" readonly="false"  onkeypress="return isNumberKey(event,this)"/>
								</div>
							</div>
						</div>
						
						<div class="col-md-12 col-sm-4 col-xs-12 mar-top21" align="cen">
							<div class="form-group">
								<div class="col-md-2 prl-10">
									<div id="requiredAccountNo" Class="displayErrorMessageInRedColor"></div>
									<label><span style="color: red">*</span>Account No:</label>
									</div>
										<div class="col-md-2 prl-10">
									<form:input path="accountNo"  id="accountNo" value="${bankmanDateData1.accountNo}" size="20"    maxlength="20" class="form-control cus-control" readonly="false"/>
								</div>
							</div>
						</div>
						
							<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-2 prl-10">
									<div id="requiredIfscCode" Class="displayErrorMessageInRedColor"></div>
									<label><span style="color: red">*</span>IFSC Code(RTGS/NEFT):</label>
									</div>
										<div class="col-md-2 prl-10">
									<form:input path="ifscCode"  id="ifscCode" value="${bankmanDateData1.ifscCode}" size="20"   maxlength="11" class="form-control cus-control" readonly="false"/>
								</div>
							</div>
						</div>
						
						<!-- <div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-2 prl-10">
									<div id="requiredRtgsNo" Class="displayErrorMessageInRedColor"></div>
									<label><span style="color: red">*</span>RTGS No:</label>
									</div>
										<div class="col-md-2 prl-10">
									<form:input path="rtgsNo"  id="rtgsNo" value="${bankmanDateData1.rtgsNo}" size="20"   maxlength="11" class="form-control cus-control" readonly="false"/>
								</div>
							</div>
						</div> -->
						<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-2 prl-10">
									<label>NBFC Maker Remark:</label> 
								</div>
								<div class="col-md-4 prl-15">
									<form:input path="nbfcMakerRemarks"  id="nbfcMakerRemarks" value="${bankmanDateData1.nbfcMakerRemarks}" size="200"   class="form-control cus-control" readonly="false"/>
								</div>
							</div>
						</div>
						
						
						<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-2 prl-10">
									<label><span style="color: red">*</span>NBFC Checker Return Remark:</span></label> 
								</div>
								<div class="col-md-4 prl-15">
									<div id="requiredNbfcCheckerRemarks" Class="displayErrorMessageInRedColor"></div>
									<form:input path="nbfcCheckerRemarks"  id="nbfcCheckerRemarks" value="${bankmanDateData1.nbfcCheckerRemarks}" size="200"   class="form-control cus-control" readonly="true"/>
								</div>
							</div>
						</div>
						
						<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-2 prl-10">
									<label><span style="color: red">*</span>CGTMSE Checker Return Remark:</span></label> 
								</div>
								<div class="col-md-4 prl-15">
									<div id="requiredCgtmseCheckerRemarks" Class="displayErrorMessageInRedColor"></div>
									<form:input path="cgtmseCheckerRemarks"  id="cgtmseCheckerRemarks" value="${bankmanDateData1.cgtmseCheckerRemarks}" size="200"   class="form-control cus-control" readonly="true"/>
								</div>
						</div>
					
						<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-2 prl-10">
									<label>Status:</label> 
								</div>
								<div class="col-md-2 prl-10">
									<form:input path="status"  id="status" value="${bankmanDateData1.status}" size="20"   class="form-control cus-control" readonly="true"/>
								</div>
							</div>
						</div>
						
						<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-2 prl-10">
									<div id="requiredFile" Class="displayErrorMessageInRedColor"></div>
									<label><span style="color: red">*</span>Bank Mandate Form:</label> 
									</div>
										<div class="col-md-2 prl-10">
											<input type="file" name="file" id="file" onchange="checkextension()"
											class="form-control cus-control"></input>
									</div>
									<div class="col-md-2 prl-10">
										<input type="button" value="Update" name="Update" id="Update" class="btn btn-reset" onclick="validateFormOnclickOfSubmitButton();" />
									</div>
							</div>
						</div>
					</div>
				
					</form:form>
				</div>
			</div>
		</div>
	</body>
</html>
