<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Approval/Rejection from CGTMSE</title>
		<link href="css/jquery-ui-css.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
		
		<script type="text/javascript">
			$(function() {
				disbaleEnableSubmitButton();
			});
		</script>
		
		<script type="text/javascript">
		function disbaleEnableSubmitButton(){
			try{
				if(document.getElementById('status').value==""){
					document.getElementById('approveBankMandate').disabled=true;//Disable
					document.getElementById('returnbankMandate').disabled=true;//Disable
				}else if(document.getElementById('status').value=='CA'){
					document.getElementById('approveBankMandate').disabled=false;//Enable
					document.getElementById('returnbankMandate').disabled=false;//Enable
				}else if(document.getElementById('status').value=='CR'){
					document.getElementById('approveBankMandate').disabled=true;//Disable
					document.getElementById('returnbankMandate').disabled=true;//Disable
				}else if(document.getElementById('status').value=='CCA'){
					document.getElementById('approveBankMandate').disabled=true;//Disable
					document.getElementById('returnbankMandate').disabled=true;//Disable
				}

			}catch(err) {
				alert("Error in disbaleEnableSubmitButton function =="+err.message);
			}

		}
		
		function approveBankMandateFormFromCGTMSEChecker(){
			var chkflag=5;
			try {
				
				if(document.getElementById('cgtmseCheckerRemarks').value=="" || document.getElementById('cgtmseCheckerRemarks').value==null){
					document.getElementById("requiredCgtmseCheckerRemarks").innerHTML="";
				}else{
					document.getElementById("requiredCgtmseCheckerRemarks").innerHTML="";
					document.getElementById("requiredCgtmseCheckerRemarks").innerHTML="Please Remove  CGTMSE Return Remarks";
					chkflag=chkflag-1;
					
				}
				if(chkflag==5){
					document.getElementById('approvalOrRejectionBankMandateFromCGTMSEFormId').action = "/SV/approveBankMandateByCGTMSEChecker.html";
					document.getElementById('approvalOrRejectionBankMandateFromCGTMSEFormId').submit();
				}
			}catch(err) {
				alert("Error in approveBankMandate function=="+err.message);
			}		
		}	

		function returnBankMandateFormFromCGTMSEChecker(){
		
			var chkflag=5;
			try {
				if(document.getElementById('cgtmseCheckerRemarks').value=="" || document.getElementById('cgtmseCheckerRemarks').value==null){
					document.getElementById("requiredCgtmseCheckerRemarks").innerHTML=" CGTMSE Checker Return Remark  is mandatory";
					chkflag=chkflag-1;
				}else{
					document.getElementById("requiredCgtmseCheckerRemarks").innerHTML="";
				}
				if(chkflag==5){
					document.getElementById('approvalOrRejectionBankMandateFromCGTMSEFormId').action = "/SV/returnBankMandateByCGTMSEChecker.html";
					document.getElementById('approvalOrRejectionBankMandateFromCGTMSEFormId').submit();
				}
			}catch(err) {
				alert("Error in returnBankMandate function=="+err.message);
			}	
		}		
		</script>
	</head>
	<body onload="clearField()">
		<div class="main-section">
			<div class="frm-section">
				<nav aria-label="breadcrumb">
					<ol class="breadcrumb cus-breadcrumb">
						<li class="breadcrumb-item"><a
							href="/SV/ClaimBankMandate.html">Bank Details</a></li>
						<li class="breadcrumb-item active" aria-current="page">Bank Details for Approval</li>
					</ol>
				</nav>
				<div class="col-md-12">
					<form:form method="POST" action=""  enctype="multipart/form-data" class="form-horizontal" id="approvalOrRejectionBankMandateFromCGTMSEFormId">
						<h5><strong>Bank Mandate Details</strong></h5>
						<h4 align="center"><font style="color: green; font-size: 20px;" >${recordApproved}</font></h4>
						<h4 align="center"><font style="color: red; font-size: 20px;">${recordApproved1}</font></h4>
						<h4 align="center"><font style="color: green; font-size: 20px;" >${recordReturn1}</font></h4>
						<h4 align="center"><font style="color: red; font-size: 20px;">${recordReturn2}</font></h4>
						<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								
								<div class="col-md-2 prl-10">
									<!-- <input type="hidden" name="status" id="status" value="${bankmanDateData.status}"/> -->
									<div id="requiredMliName" Class="displayErrorMessageInRedColor"></div>
									<label><span style="color: red">*</span>MLI Name :</label>
								</div>	
								<div class="col-md-2 prl-10">
									<form:input path="mliName"  id="mliName" value="${bankmanDateData.mliName}" size="20" cssClass="form-control cus-control" readonly="true"/>
								</div>
							</div>
						</div>
						
						<input type="hidden" value="${bankmanDateData.memberId}" name="memberIdHiddenField" id="memberIdHiddenField"/>
						
						<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-2 prl-10">
								 	<div id="requiredMemberId" Class="displayErrorMessageInRedColor"></div>
									<label><span style="color: red">*</span>Member Id :</label>
									</div>
									<div class="col-md-2 prl-10">
									<form:input path="memberId"  id="memberId" value="${bankmanDateData.memberId}" size="20"  class="form-control cus-control" readonly="true"/>
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
									<form:input path="contactNo"  id="contactNo" value="${bankmanDateData.contactNo}" size="20"   class="form-control cus-control" readonly="true"  maxlength="12" onkeypress="return isNumberKey(event,this)"  />
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
									<form:input path="mobileNo"  id="mobileNo" value="${bankmanDateData.mobileNo}" size="20"   class="form-control cus-control" readonly="true" maxlength="10" onkeypress="return isNumberKey(event,this)" />
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
									<form:input path="emailId"  id="emailId" value="${bankmanDateData.emailId}" size="20"   class="form-control cus-control" readonly="true"/>
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
									<form:input path="mliBeneficiaryName"  id="mliBeneficiaryName" value="${bankmanDateData.mliBeneficiaryName}" size="20"   class="form-control cus-control" readonly="true"/>
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
									<form:input path="mliBeneficiaryBankName"  id="mliBeneficiaryBankName" value="${bankmanDateData.mliBeneficiaryBankName}" size="20"   class="form-control cus-control" readonly="true"/>
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
									<form:input path="branchName"  id="branchName" value="${bankmanDateData.branchName}" size="20"   class="form-control cus-control" readonly="true"/>
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
													<c:when test="${bankmanDateData.accountType=='Saving'}">
													 	<form:radiobutton path="accountType" id="accountType1" value="Saving" checked="checked" />Saving
													 	<form:radiobutton path="accountType" id="accountType2" value="Current" />Current
													</c:when>
													<c:when test="${bankmanDateData.accountType=='Current'}">
														<form:radiobutton path="accountType" id="accountType1" value="Saving" />Saving 
													 	<form:radiobutton path="accountType" id="accountType" value="Current" checked="checked" />Current
													</c:when>
													<c:otherwise>
														<form:radiobutton path="accountType" id="accountType1" value="Y" />Saving 
													 	<form:radiobutton path="accountType" id="accountType2" value="N" />Current
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
									<form:input path="branchCode"  id="branchCode" value="${bankmanDateData.branchCode}" size="20"   class="form-control cus-control" readonly="true"/>
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
									<form:input path="micrCode"  id="micrCode" value="${bankmanDateData.micrCode}" size="20"   class="form-control cus-control" readonly="true"/>
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
									<form:input path="accountNo"  id="accountNo" value="${bankmanDateData.accountNo}" size="20"   class="form-control cus-control" readonly="true"/>
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
									<form:input path="ifscCode"  id="ifscCode" value="${bankmanDateData.ifscCode}" size="20"   class="form-control cus-control" readonly="true"/>
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
											<form:input path="rtgsNo"  id="rtgsNo" value="${bankmanDateData.rtgsNo}" size="20"   class="form-control cus-control" readonly="true"/>
										</div>
							</div>
						</div> -->
						<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-2 prl-10">
									<label>NBFC Maker Remark:</label> 
								</div>
								<div class="col-md-4 prl-10">
									<form:input path="nbfcMakerRemarks"  id="nbfcMakerRemarks" value="${bankmanDateData.nbfcMakerRemarks}" size="200"   class="form-control cus-control" readonly="true"/>
								</div>
							</div>
						</div>
						
						<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-2 prl-10">
									<label><span style="color: red">*</span>NBFC Checker Return Remark:</span></label> 
								</div>
								<div class="col-md-4 prl-10">
									<div id="requiredNbfcCheckerRemarks" Class="displayErrorMessageInRedColor"></div>
									<form:input path="nbfcCheckerRemarks"  id="nbfcCheckerRemarks" value="${bankmanDateData.nbfcCheckerRemarks}" size="200"   class="form-control cus-control" readonly="true"/>
								</div>
							</div>
						</div>
						
						<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-2 prl-10">
									
									<label><span style="color: red">*</span>CGTMSE Checker Return Remark:</span></label> 
								</div>
								<div class="col-md-4 prl-10">
									<div id="requiredCgtmseCheckerRemarks" Class="displayErrorMessageInRedColor"></div>
									<form:input path="cgtmseCheckerRemarks"  id="cgtmseCheckerRemarks" value="${bankmanDateData.cgtmseCheckerRemarks}" size="200"   class="form-control cus-control" readonly="false"/>
								</div>
							</div>
						</div>
							<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-2 prl-10">
									<label><span style="color: red">*</span>Status:</span></label> 
								</div>
								<div class="col-md-2 prl-10">
									<form:input path="status"  id="status" value="${bankmanDateData.status}" size="20"   class="form-control cus-control" readonly="true"/>
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>Download Bank Mandate Form:<span style="color: red">*</span> 
									<a href="downLoadBankMandateFormCheckerSide.html?memberId=${bankmanDateData.memberId}"><img title="Download this document" style="width: 11%;" src="images/BankManDatePdfImage.png" border="0"></a>
									</label> 
								</div>
							</div>
						</div>
						
						<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-2 prl-10">
									<!--<div id="requiredRtgsNo" Class="displayErrorMessageInRedColor"></div>-->
									<div class="col-md-2 prl-10">
										<table>
											<tr>
												<td><input type="button" value="Approve" class="btn btn-reset" name="approveBankMandate" id="approveBankMandate" onclick="approveBankMandateFormFromCGTMSEChecker()"/></td>
												<td></td><td></td><td></td><td></td>
												<td><input type="button" value="Return" class="btn btn-reset" name="returnbankMandate" id="returnbankMandate" onclick="returnBankMandateFormFromCGTMSEChecker()"/></td>
											</tr>
										</table>
									</div>
								</div>
							</div>									
						</div>
						<div class="col-md-12 col-sm-4 col-xs-12 mar-top21">
							<div class="form-group">
								<div class="col-md-2 prl-10">
									<!--<div id="requiredRtgsNo" Class="displayErrorMessageInRedColor"></div>	-->
								<div class="col-md-2 prl-10">
									</div>
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
