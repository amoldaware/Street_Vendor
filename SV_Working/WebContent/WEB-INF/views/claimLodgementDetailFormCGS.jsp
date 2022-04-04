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
	 
		<style>
			.securityValue{display:inline; float:left;  padding:0 1px;}
			.securityValue:nth-child(1),.securityValue:nth-child(2),.securityValue:nth-child(5)
			,.securityValue:nth-child(6){width: 130px;}
			.securityValue:nth-child(3){width: 220px;}
			.securityValue:nth-child(4){width: 200px;}
		</style>
	



	
		<script type="text/javascript">
		function myFunction(id) {
		  var x = document.getElementById(id);
		  if (x.className.indexOf("w3-show") == -1) {
		    x.className += " w3-show";
		  } else {
		    x.className = x.className.replace(" w3-show", "");
		  }
		}
			function calTotOutstandingAsOnDateOfLodgementOfClaim(recoveryMadeAmt){
				var totalOsAmount=document.getElementById('totalOsAmt').value;
				var latestOsAmount=document.getElementById('latestOsAmt').value;
				if(parseInt(totalOsAmount)>parseInt(latestOsAmount)){
					document.getElementById('osAmtClaim').value=parseInt(latestOsAmount)-parseInt(recoveryMadeAmt);
				}else if(parseInt(latestOsAmount)>parseInt(totalOsAmount)){
					document.getElementById('osAmtClaim').value=parseInt(totalOsAmount)-parseInt(recoveryMadeAmt);
				}else{
					document.getElementById('osAmtClaim').value=parseInt(totalOsAmount)-parseInt(recoveryMadeAmt);
				}
				document.getElementById('osAmtClaimGCP').value=document.getElementById('osAmtClaim').value;
				var calTotElaimEligibilityAmount=(parseInt(document.getElementById('osAmtClaimGCP').value)*parseInt(document.getElementById('guaranteeCov').value))/100;
				document.getElementById('eligableAmtClaim').value=calTotElaimEligibilityAmount;
			
				document.getElementById('firstInstallClaim').value=(parseInt(document.getElementById('eligableAmtClaim').value)*75)/100;

			}		
		</script>
	</head>
	<body >
	
		<div class="main-section">
		
		
		<%String userRole = (String) session.getAttribute("uRole"); 
	
		if (userRole.equals("CCHECKER")) {
						%>

		<nav aria-label="breadcrumb">
		<ol class="breadcrumb cus-breadcrumb">
			<li class="breadcrumb-item">
			<a href="/SV/claimLoadgementApprovalCGTMSE.html?MLIName=${MLIID}&CNT=${CNT}">Claim Lodgement
					Approval</a></li>
			<li class="breadcrumb-item active" aria-current="page">Claim CheckList Details</li>
		</ol>
		</nav>
		<%}else if(userRole.equals("NCHECKER")) {%>
		<nav aria-label="breadcrumb">
		<ol class="breadcrumb cus-breadcrumb">
				<li><a href="/SV/claimLoadgementApprovalMLI.html">Claim Lodgement
					Approval</a></li>
			<li class="breadcrumb-item active" aria-current="page">Claim CheckList Details</li>
		</ol>
		</nav>
		<%}%>
		<input type="hidden" path="mliName" id ="mli" value="${MLIID}"/> 
	  <input type="hidden" path="claimCnt" id ="claimCnt" value="${CNT}"/>
		
			
  	<div class="container-fluid">
				<c:if test="${!empty cgpenList}">
		<div id="split-sec">

			<div class="container-fluid">
		<!-- 		<h2 align="center" class="sub-head">
					<span style="background-color: white;">Application History</span>
				</h2>
 -->
				<div>
					<div class="tbl-details">
           <table class="table table-bordered table-hover cus-table mt-10 mb-0">
           <thead>
										<tr>
											<th colspan="2">Cgpan History </th>
											</tr>
												</thead>
								<c:if test="${!empty cgpenList}">
										<thead>
										<tr>
											<th>SR.No.</th>
											<th>Member ID</th>
											<th>CGPAN</th>
											<th>Unit NAme</th>
											<th>DAN ID</th>
											<th>Approved Amount</th>
										    <th>Outstanding Amount</th>
										     <th>Crystalization Date</th>
											<th>Dan Amount</th>
											<th>Payment ID</th>
											<th>Realised date</th>
											<th>Expiry date</th>
											<th>Appropriation date</th>
											<th>Appropriation By</th>
											<th>UTR No</th>
											<th>Remarks</th>
										</tr>
									</thead>
									<% int counter=0;%>
									<c:forEach items="${cgpenList}" var="cgpenList">
										<tr <% if(counter%2==0){%>bgcolor="silver" <%}%>>
											<td><%=counter+1%></td>
											<td><c:out value="${cgpenList.mliId}"/></td>
											<td><c:out value="${cgpenList.displayCgpen}"/></td>
											<td><c:out value="${cgpenList.ssiName}"/></td>
											<td><c:out value="${cgpenList.danId}"/></td>
											<td><c:out value="${cgpenList.guaranteeAmt}"/></td>
										  <td><c:out value="${cgpenList.outstandingAmt}"/></td>
										  	 <td><c:out value="${cgpenList.crystalizationDate}"/></td>  
										  	<td><c:out value="${cgpenList.dan_Amt}"/></td>  
											<td><c:out value="${cgpenList.payId}"/></td>
											<td><c:out value="${cgpenList.dciAppropriationDate}"/></td>
											<td><c:out value="${cgpenList.expiryDate}"/></td>
											<td><c:out value="${cgpenList.dciAppropriationDate}"/></td>
											<td><c:out value="${cgpenList.appropriation_by}"/></td>
										<td></td>
										<td></td>
											<%-- <td><a href="claimLoadgementApprovalCGTMSE.html?MLIName=${danDetailList.mliName}"><form:input path="claimCnt" value="${danDetailList.claimCnt}" class="form-control cus-control" readonly="true"/></td> --%>
																						  <%counter+=1;%>
										</tr>
									</c:forEach>
								</c:if>
						
							</table>

					

	<div align="center">
										
									
									</div>
						</div>
					</div>
				</div>
				
			</div>


		</div>
		
	</c:if>
			<div class="container-fluid">
				<c:if test="${!empty applicationDetails}">
		<div id="split-sec">

			<div class="container-fluid">
		<!-- 		<h2 align="center" class="sub-head">
					<span style="background-color: white;">Application History</span>
				</h2>
 -->
				<div>
					<div class="tbl-details">
               <table cellpadding=3
						class="table table-bordered table-hover cus-table mt-10 mb-0"
						cellspacing=3 align=center width=70%>
						<thead>
										<tr>
											<th colspan="2">Status Wise Application Details for CGPAN :${applicationDetails.CGPAN}</th>
											</tr>
											</thead>
						<tr>
							<td><label>CGPAN <span">:</span></label>
									${applicationDetails.CGPAN}</td>
						<td><label>Member ID<span>:</span></label>
									${applicationDetails.MEMBER_ID}</td>
						
						</tr>
						<tr>
							<td><label>Application Number <span>:</span></label>
									${applicationDetails.LOAN_ACCOUNT_NO}</td>
						<td><label>Unit Name <span>:</span></label>
						 ${applicationDetails.MSE_NAME}</td>
						
						</tr><tr>
							<td><label>Lone Type<span>:</span></label>
									${applicationDetails.LONE_TYPE}</td>
						<td><label>CONSTITUTION<span>:</span></label>
									${applicationDetails.CONSTITUTION}</td>
						
						</tr>
						<tr>
							<td><label>SANCTION DATE<span>:</span></label>
									${applicationDetails.SANCTION_DATE}</td>
						<td><label>SANCTIONED AMOUNT<span>:</span></label>
									${applicationDetails.SANCTIONED_AMOUNT}</td>
						
						</tr>
						<tr>
							<td><label>FIRST DISBURSEMENT DATE<span>:</span></label>
									${applicationDetails.FIRST_DISBURSEMENT_DATE}</td>
						<td><label>INTEREST RATE(%)<span>:</span></label>
									${applicationDetails.INTEREST_RATE}</td>
						
						</tr>
						<tr>
							<td><label>MICRO SMALL<span>:</span></label>
									${applicationDetails.MICRO_SMALL}</td>
						<td><label>TENOR IN MONTHS<span>:</span></label>
									${applicationDetails.TENOR_IN_MONTHS}</td>
						
						</tr>
						<tr>
							<td><label>UNIT ADDRESS<span>:</span></label>
									${applicationDetails.MSE_ADDRESS}</td>
						<td><label>CITY<span>:</span></label> ${applicationDetails.CITY}</td>
						
						</tr>
						<tr>
							<td><label>DISTRICT<span>:</span></label>
									${applicationDetails.DISTRICT}</td>
						<td><label>PINCODE<span>:</span></label>
									${applicationDetails.PINCODE}</td>
						
						</tr>
						<tr>
							<td><label>STATE<span>:</span></label> ${applicationDetails.STATE}</td>
						<td><label>MSE ITPAN<span>:</span></label>
									${applicationDetails.MSE_ITPAN}</td>
						
						</tr>
						<tr>
							<td><label>UDYOG AADHAR NO<span>:</span></label>
									${applicationDetails.UDYOG_AADHAR_NO}</td>
						<td><label>INDUSTRY NATURE<span>:</span></label>
									${applicationDetails.INDUSTRY_NATURE}</td>
						
						</tr>
						<tr>
							<td><label>INDUSTRY SECTOR<span>:</span></label>
									${applicationDetails.INDUSTRY_SECTOR}</td>
						<td><label>NO OF EMPLOYEES<span>:</span></label>
									${applicationDetails.NO_OF_EMPLOYEES}</td>
						
						</tr>
						<tr>
							<td><label>NEW EXISTING UNIT<span>:</span></label>
									${applicationDetails.NEW_EXISTING_UNIT}</td>
						<td><label>PREVIOUS BANKING EXPERIENCE<span>:</span></label>
									${applicationDetails.PREVIOUS_BANKING_EXPERIENCE}</td>
						
						</tr>
							<tr>
							<td><label>CHIEF PROMOTER FIRST NAME<span>:</span></label>
									${applicationDetails.CHIEF_PROMOTER_FIRST_NAME}</td>
						<td><label>CHIEF PROMOTER MIDDLE NAME<span>:</span></label>
									${applicationDetails.CHIEF_PROMOTER_MIDDLE_NAME}</td>
						
						</tr>
							<tr>
							<td><label>CHIEF PROMOTER LAST NAME<span>:</span></label>
									${applicationDetails.CHIEF_PROMOTER_LAST_NAME}</td>
						<td><label>CHIEF PROMOTER IT PAN<span>:</span></label>
									${applicationDetails.CHIEF_PROMOTER_IT_PAN}</td>
						
						</tr>
							<tr>
							<td><label>CHIEF PROMOTER MAIL ID<span>:</span></label>
									${applicationDetails.CHIEF_PROMOTER_MAIL_ID}</td>
						<td><label>CHIEF PROMOTER CONTACT NUMBER<span>:</span></label>
									${applicationDetails.CHIEF_PROMOTER_CONTACT_}</td>
						
						</tr>
							<tr>
							<td><label>HANDICAPPED<span>:</span></label>
									${applicationDetails.HANDICAPPED}</td>
						<td><label>WOMEN<span>:</span></label> ${applicationDetails.WOMEN}</td>
						
						</tr>
							<tr>
							<td><label>CATEGORY<span>:</span></label>
									${applicationDetails.CATEGORY}</td>
						<td><label>PORTFOLIO NAME<span>:</span></label>
									${applicationDetails.PORTFOLIO_NAME}</td>
						
						</tr>
							<tr>
							<td><label>DAN ID<span>:</span></label>
									${applicationDetails.DAN_ID}</td>
						<td><label>GUARANTEE AMOUNT<span>:</span></label>
									${applicationDetails.GUARANTEE_AMOUNT}</td>
						
						</tr>
							<tr>
							<td><label>COLLETRAL SECURITY AMOUNT<span>:</span></label>
									${applicationDetails.COLLETRAL_SECURITY_AMOUNT}</td>
						<td><label>RETAIL TRADE<span>:</span></label>
									${applicationDetails.RETAIL_TRADE}</td>
						
						</tr>
							<tr>
							<td><label>AADHAR NUMBER<span>:</span></label>
									${applicationDetails.AADHAR_}</td>
						<td><label>OUTSTANDING AMOUNT<span>:</span></label>
									${applicationDetails.OUTSTANDING_AMOUNT}</td>
						
						</tr>
						
						
						</table>

	

	<div align="center">
										
									
									</div>
						</div>
					</div>
				</div>
				
			</div>


		</div>
		
	</c:if>
	<div></div>
				<div>
					<div class="frm-section">
	                      <input type="hidden" value="${formData.claimRefNo}"/>
						<div class="col-md-12">
							<table cellpadding=3
						class="table table-bordered table-hover cus-table mt-10 mb-0"
						cellspacing=3 align=center width=70%>
							<thead>
										<tr>
											<th colspan="2">Claim Form Details</th>
											</tr>
												</thead>
								</table>		
							<form:form  action="saveClaimLodgmentDtls.html" method="POST" class="form-horizontal" >
							<div class="clearfix"></div>
								<h5 class="sub-head"><strong> Details of Operating Office and Lending Branch: </strong></h5>
								<hr style="margin: 5px 0; border: 1px solid #d8d8d8">
								<div class="clearfix"></div>
	
									<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Member Id :</label>
										<form:input path="memberId" value="${formData.memberId}" size="20"
											style="text-align: right" id="memberId"
											class="form-control cus-control" readonly="true" />
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>CGPAN :</label>
										<form:input path="cgpan" value="${formData.cgpan}" size="20"
											id="cgpan" class="form-control cus-control" readonly="true" />
									</div>
								</div>
							</div>

	
	
								<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Lending NBFC Name :</label>
											<form:input path="lendingNbfcName" id="lendingNbfcName" value="${formData.lendingNbfcName}" size="20" readonly="true"  class="form-control cus-control"  />
										</div>
									</div>
								</div>
								
								<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>City :</label>
											<form:input path="city" id="city" value="${formData.city}" size="20"  readonly="true" class="form-control cus-control"  />
										</div>
									</div>
								</div>
	
								<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Complete Address : </label>
											<form:input path="address" id="address" value="${formData.address}" size="20"  readonly="true" class="form-control cus-control"/>
										</div>
									</div>
								</div>
	
								<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>District :</label>
											<form:input path="district" id="district" value="${formData.district}" size="20"  readonly="true" class="form-control cus-control" />
										</div>
									</div>
								</div>
	
								<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
	
										<div class="col-md-12 prl-10">
											<label>State :</label>
											<form:input path="state" id="state" value="${formData.state}" size="20"  readonly="true" class="form-control cus-control"/>
										</div>
									</div>
								</div>
	
								<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
	
										<div class="col-md-12 prl-10">
											<label>Email :</label>
											<form:input path="email" id="email" value="${formData.email}" size="20"  readonly="true" class="form-control cus-control"/>
										</div>
									</div>
								</div>
	
								<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
	
										<div class="col-md-12 prl-10">
											<label>Telephone No :</label>
											<form:input path="telephoneNo" id="telephoneNo" value="${formData.telephoneNo}" size="20" readonly="true"  class="form-control cus-control"/>
										</div>
									</div>
								</div>
	
								<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>GSTIN No :</label>
											<form:input path="gstinNo" id="gstinNo" value="${formData.gstinNo}" size="20"  readonly="true" class="form-control cus-control"/>
										</div>
									</div>
								</div>
	
								<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Dealing Officer Name<span style="color: red">*</span> :</label>
											<form:input path="dealingOfficerName" id="dealingOfficerName" value="${formData.dealingOfficerName}" size="20"   class="form-control cus-control" readonly="true"/>
										</div>
									</div>
								</div>
									<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Dealing Officer No<span style="color: red">*</span> :</label>
											<form:input path="dealingOfficerNO" id="dealingOfficerNO" value="${formData.dealingOfficerNO}" size="20"   class="form-control cus-control" readonly="true"/>
										</div>
									</div>
								</div>
							
								<div class="clearfix"></div>
	
								<h5 class="sub-head"><strong> Borrower's /Unit's Details: </strong></h5>
								<hr style="margin: 5px 0; border: 1px solid #d8d8d8">
								
								<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Name of the Borrower/Unit :</label>
											<form:input path="nameOfBorrower" id="nameOfBorrower" value="${formData.nameOfBorrower}" size="20"  readonly="true" class="form-control cus-control" />
										</div>
									</div>
								</div>
								
								<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Complete Address :</label>
											<form:input path="adressOfBorrower" id="adressOfBorrower" value="${formData.adressOfBorrower}" size="20"  readonly="true" class="form-control cus-control"/>
										</div>
									</div>
								</div>
								
								<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>City :</label>
											<form:input path="cityOfBorrower" id="cityOfBorrower"  value="${formData.cityOfBorrower}" size="20" readonly="true" class="form-control cus-control"/>
										</div>
									</div>
								</div>
								
								<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>District :</label>
											<form:input path="districtOfBorrower" id="districtOfBorrower" value="${formData.districtOfBorrower}" size="20"  readonly="true" class="form-control cus-control"/>
										</div>
									</div>
								</div>
								
								<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>State :</label>
											<form:input path="stateOfBorrower" id="stateOfBorrower" value="${formData.stateOfBorrower}" size="20"  readonly="true" class="form-control cus-control" />
										</div>
									</div>
								</div>
								
								<div class="col-md-1 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>PinCode :</label>
											<form:input path="pincodeOfBorrower" id="pincodeOfBorrower" value="${formData.pincodeOfBorrower}" size="20"  readonly="true" class="form-control cus-control" />
										</div>
									</div>
								</div>
								
								<div class="clearfix"></div>
								<h5 class="sub-head"><strong> Status of Account(s): </strong></h5>
								<hr style="margin: 5px 0; border: 1px solid #d8d8d8">
								
								<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Latest Outstanding Guaranteed Amount :</label>
											<form:input path="latestOsGuranteeAmt" id="latestOsGuranteeAmt" value="${formData.latestOsGuranteeAmtVStr}" size="20"  readonly="true" class="form-control cus-control" />
										</div>
									</div>
								</div>
								
								<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Date on which Account was Classified as NPA :</label>
											<form:input path="dateOfNPA" id="dateOfNPA" value="${formData.dateOfNPA}" size="20"  readonly="true" class="form-control cus-control" />
										</div>
									</div>
								</div>
								<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Wilful defaulter<span style="color: red">*</span>:</label> 
											<label class="d-block"> 
												<c:choose>
											 		<c:when test="${formData.wilfulDefaulter=='Y'}">
											 			<form:radiobutton path="wilfulDefaulter" value="Y"  checked="checked"/>Yes 
											 			<form:radiobutton path="wilfulDefaulter" value="N" disabled="true"/>No
											 		</c:when>
											 		<c:when test="${formData.wilfulDefaulter=='N'}">
											 			<form:radiobutton path="wilfulDefaulter" value="Y" disabled="true"/>Yes 
											 			<form:radiobutton path="wilfulDefaulter" value="N" checked="checked"/>No
											 		</c:when>
											 	
											 		<c:otherwise>
											 			<form:radiobutton path="wilfulDefaulter" value="Y" disabled="true"/>Yes 
											 			<form:radiobutton path="wilfulDefaulter" value="N" disabled="true"/>No
											 		</c:otherwise>
												 </c:choose>
											</label>
										</div>
									</div>
								</div>
								
								<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Has the account been classified as fraud<span style="color: red">*</span>:</label>
											 <label class="d-block"> 
											 	<c:choose>
											 		<c:when test="${formData.fraudAcc=='Y'}">
											 			<form:radiobutton path="fraudAcc" value="Y"  checked="checked"/>Yes 
											 			<form:radiobutton path="fraudAcc" value="N" disabled="true"/>No
											 		</c:when>
											 		<c:when test="${formData.fraudAcc=='N'}">
											 			<form:radiobutton path="fraudAcc" value="Y"  disabled="true"/>Yes 
											 			<form:radiobutton path="fraudAcc" value="N" checked="checked"/>No
											 		</c:when>
											 	
											 		<c:otherwise>
											 			<form:radiobutton path="fraudAcc" value="Y" disabled="true"/>Yes 
											 			<form:radiobutton path="fraudAcc" value="N" disabled="true"/>No
											 		</c:otherwise>
												 </c:choose>
											 </label>
										</div>
									</div>
								</div>
								
								<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Internal and/or external enquiry has been concluded<span style="color: red">*</span>:</label>
											<label class="d-block"> 
												<c:choose>
											 		<c:when test="${formData.enquiryConcluded=='Y'}">
											 			<form:radiobutton path="enquiryConcluded" value="Y"  checked="checked"/>Yes 
											 			<form:radiobutton path="enquiryConcluded" value="N" disabled="true"/>No
											 		</c:when>
											 		<c:when test="${formData.enquiryConcluded=='N'}">
											 			<form:radiobutton path="enquiryConcluded" value="Y" disabled="true"/>Yes 
											 			<form:radiobutton path="enquiryConcluded" value="N" checked="checked"/>No
											 		</c:when>
											 	
											 		<c:otherwise>
											 			<form:radiobutton path="enquiryConcluded" value="Y" disabled="true"/>Yes 
											 			<form:radiobutton path="enquiryConcluded" value="N" disabled="true"/>No
											 		</c:otherwise>
											 	</c:choose>
											</label>
										</div>
									</div>
								</div>
								<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Involvement of staff of MLI has been reported<span style="color: red">*</span>:</label>
											<label class="d-block"> 
												<c:choose>
											 		<c:when test="${formData.mliReported=='Y'}">
											 			<form:radiobutton path="mliReported" value="Y"  checked="checked"/>Yes 
											 			<form:radiobutton path="mliReported" value="N" disabled="true"/>No
											 		</c:when>
											 		<c:when test="${formData.mliReported=='N'}">
											 			<form:radiobutton path="mliReported" value="Y" disabled="true" />Yes 
											 			<form:radiobutton path="mliReported" value="N" checked="checked"/>No
											 		</c:when>
											 	
											 		<c:otherwise>
											 			<form:radiobutton path="mliReported" value="Y" disabled="true"/>Yes 
											 			<form:radiobutton path="mliReported" value="N" disabled="true"/>No
											 		</c:otherwise>
											 	</c:choose>
											</label>
										</div>
									</div>
								</div>
								 <div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div id="internalRating" Class="displayErrorMessageInRedColor"></div>
										<label class="d-block">Internal Rating :</label>
										<form:input path="internalRating1" id="internalRating1"
											value="${formData.internalRating1}"  
											 class="form-control cus-control" onkeypress="return validateKeypress(alphanumeric);" readonly="true"/>
									</div>
								</div>
							</div>
								<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Reasons for Account turning NPA<span style="color: red">*</span>:</label>
											<label class="d-block"> <form:input path="reasonOfNPA" value="${formData.reasonOfNPA}" size="20" id="reasonOfNPA" class="form-control cus-control" placeholder="" readonly="true"/></label>
										</div>
									</div>
								</div>
								
								<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Date of issue of Recall Notice/Demand Notice<span style="color: red">*</span>:</label>
											<label class="d-block"> <form:input style="font-size: 12px;" path="dateOfNotice"  id="dateOfNotice" value="${formData.dateOfNotice}" size="20" class="date-picker form-control cus-control" placeholder="e.g:dd/mm/yyyy" readonly="true"/></label>
											<form:errors path="dateOfNotice" cssClass="error" />
										</div>
									</div>
								</div>
							
	
								<div class="clearfix"></div>
								<h5 class="sub-head"><strong> Details of Legal Proceedings: </strong></h5>
								<hr style="margin: 5px 0; border: 1px solid #d8d8d8">
	
								<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label class="d-block">Forum through which legal proceedings were initiated against borrower :<span style="color: red">*</span> </label>
											<c:choose>
												<c:when test="${formData.forum=='NONE'}">
													<form:select path="forum"   id="forum"  disabled="false" class="form-control cus-control" >
														<form:option value="NONE" label="---select---" />
														<form:option value="Forum1" label="Forum1" />
														<form:option value="Forum2" label="Forum2" />
														<form:option value="Forum3" label="Forum3" />
													</form:select>
												</c:when>
												<c:otherwise>
													<form:select path="Forum"   id="Forum"  disabled="false" class="form-control cus-control" readonly="true">
														<form:option id="${formData.forum}" value="${formData.forum}"></form:option>
													</form:select>
												 </c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>																
								
								<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Provide satisfactory reason for filing suit before NPA date(if applicable)<span style="color: red">*</span>:</label>
											<label class="d-block"> <form:input path="resonFillingSuit" id="resonFillingSuit" value="${formData.resonFillingSuit}" size="20"  class="form-control cus-control"  readonly="true"/></label>
										</div>
									</div>
								</div>
								<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Suit / Case Registration No.<span style="color: red">*</span>:</label>
											<label class="d-block"> <form:input path="suitNo" id="suitNo" value="${formData.suitNo}" size="20"  class="form-control cus-control"  readonly="true"/></label>
										</div>
									</div>
								</div>
								<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Date of legal file/ Date of possession of assets under SARFAESI act/Demand Notice*:<span style="color: red">*</span>:</label>
											<label class="d-block"> <form:input path="dateOfSarfaesi" id="dateOfSarfaesi" size="20" value="${formData.dateOfSarfaesi}"   class="date-picker form-control cus-control" placeholder="e.g:dd/mm/yyyy" readonly="true"/></label>
											<form:errors path="dateOfNotice" cssClass="error" />
										</div>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Location of Legal forum<span style="color: red">*</span>:</label>
											<label class="d-block"> <form:input path="locationOfForum" id="locationOfForum" value="${formData.locationOfForum}" size="20"  class="form-control cus-control"  readonly="true"/></label>
										</div>
									</div>
								</div>
								
								<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Amount Claimed in the Demand Notice/Suit(in&#8377;): <span style="color: red">*</span> </label>
											<label class="d-block"> <form:input path="claimAmt" id="claimAmt" value="${formData.claimAmt}" size="20"  class="form-control cus-control" readonly="true" /> </label>
										</div>
									</div>
								</div>
								
								<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Any Attachments(Demand Notice/legal documents:<span style="color: red">*</span> 
											<a href="DownloadBlobDoc.html?claimRefNo=${formData.claimRefNo}"><img title="Download this document" style="width: 11%;" src="images/pdficon.png" border="0"></a>
											</label> 
										</div>
									</div>
								</div>
								
								<div class="clearfix"></div>
								<h5 class="sub-head"><strong> Subsidy Details : </strong></h5>
								<hr style="margin: 5px 0; border: 1px solid #d8d8d8">
	
								<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Does the project covered under  CGTMSE guarantee, involve any subsidy?</label>
											<label class="d-block"> 
												<c:choose>
											 		<c:when test="${formData.anySubsidyInvolved=='Y'}">
											 			<form:radiobutton path="anySubsidyInvolved" value="Y"  checked="checked"/>Yes 
											 			<form:radiobutton path="anySubsidyInvolved" value="N" disabled="true"/>No
											 		</c:when>
											 		<c:when test="${formData.anySubsidyInvolved=='N'}">
											 			<form:radiobutton path="anySubsidyInvolved" value="Y" disabled="true"/>Yes 
											 			<form:radiobutton path="anySubsidyInvolved" value="N" checked="checked"/>No
											 		</c:when>
											 		<c:otherwise>
											 			<form:radiobutton path="anySubsidyInvolved" value="Y" disabled="true"/>Yes 
											 			<form:radiobutton path="anySubsidyInvolved" value="N" disabled="true"/>No
											 		</c:otherwise>
											 	</c:choose>
											</label>
										</div>
									</div>
								</div>
								
								<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Has the subsidy been received after NPA?</label>
											<label class="d-block"> 
												<c:choose>
											 		<c:when test="${formData.subsidyReceived=='Y'}">
											 			<form:radiobutton path="subsidyReceived" value="Y"  checked="checked"/>Yes 
											 			<form:radiobutton path="subsidyReceived" value="N" disabled="true"/>No
											 		</c:when>
											 		<c:when test="${formData.subsidyReceived=='N'}">
											 			<form:radiobutton path="subsidyReceived" value="Y" disabled="true"/>Yes 
											 			<form:radiobutton path="subsidyReceived" value="N" checked="checked"/>No
											 		</c:when>
											 		<c:otherwise>
											 			<form:radiobutton path="subsidyReceived" value="Y" disabled="true"/>Yes 
											 			<form:radiobutton path="subsidyReceived" value="N" disabled="true"/>No
											 		</c:otherwise>
											 	</c:choose>
											</label>
										</div>
									</div>
								</div>
								
								<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Has the subsidy been adjusted against  principal/interest over dues?</label>
											<label class="d-block"> 
												<c:choose>
											 		<c:when test="${formData.subsidyAdjust=='Y'}">
											 			<form:radiobutton path="subsidyAdjust" value="Y"  checked="checked"/>Yes 
											 			<form:radiobutton path="subsidyAdjust" value="N" disabled="true"/>No
											 		</c:when>
											 		<c:when test="${formData.subsidyAdjust=='N'}">
											 			<form:radiobutton path="subsidyAdjust" value="Y" disabled="true"/>Yes 
											 			<form:radiobutton path="subsidyAdjust" value="N" checked="checked"/>No
											 		</c:when>
											 		<c:otherwise>
											 			<form:radiobutton path="subsidyAdjust" value="Y" disabled="true"/>Yes 
											 			<form:radiobutton path="subsidyAdjust" value="N" disabled="true"/>No
											 		</c:otherwise>
											 	</c:choose>
											 </label>
										</div>
									</div>
								</div>
								
								<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Subsidy Credit Date:</label>
											<label class="d-block"> <form:input path="dateOfSubsidy" id="dateOfSubsidy" value="${formData.dateOfSubsidy}" size="20"  class="date-picker form-control cus-control" placeholder="e.g:dd/mm/yyyy" readonly="true"/></label>
											<form:errors path="dateOfSubsidy" cssClass="error" />
										</div>
									</div>
									
								</div>
								
								<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Subsidy Amount(in&#8377;): <span style="color: red">*</span>:</label>
											<label class="d-block"> <form:input path="subsidyAmt" id="subsidyAmt" value="${formData.subsidyAmt}"   size="20" class="form-control cus-control" readonly="true"/></label>
										</div>
									</div>
								</div>
								
								<div class="clearfix"></div>
								<h5 class="sub-head"><strong> Term Loan (TL) : </strong></h5>
								<hr style="margin: 5px 0; border: 1px solid #d8d8d8">
								<table class="table table-bordered table-hover cus-table mb-0">
									<thead>
										<tr>
										<th style="width: 150px;">CGPAN</th>
										<th>Latest Outstanding Guaranteed Amount (in &#8377;)<br>
											A
										</th>
										<th>Total outstanding as on date of NPA(in &#8377;)<br>
											B
										</th>
										<th>Repayment/Recovery mode from Borrower / Unit after account
											classified as NPA(in &#8377;)<span style="color: red">*</span>
											<br>C
										</th>
										<th>Subsidy
											Amount(in &#8377;)<span style="color: red">*</span>
											<br>E
										</th>
										<th>Mode of recovery<span style="color: red">*</span></th>
										<th>Outstanding As On Date of Lodgement of Claim (in
											&#8377;)<br> D=(Least of A/B)-C-E
										</th>
									</thead>
									<tr>
											<!--  Anand-->
										<td><form:input path="cgPanTL" id="cgPanTL" value="${formData.cgpan}" size="20"  readonly="true" style="padding:2px;" class="form-control cus-control"  /></td>
										<td><form:input path="latestOsAmt" id="latestOsAmt" value="${formData.latestOsGuranteeAmtVStr}"   readonly="true" class="form-control cus-control"  /></td>
										<td><form:input path="totalOsAmt"  id="totalOsAmt" value="${formData.totalOsAmtStr}"  size="20"  readonly="true"  class="form-control cus-control" /></td>
										<td><form:input path="recovery" id="recovery" value="${formData.recovery}" size="20"  class="form-control cus-control" onchange="calTotOutstandingAsOnDateOfLodgementOfClaim(this.value);"/></td>
										<td><form:input path="subsidyAmt" id="subsidyAmt1" value="${formData.subsidyAmt}" size="20" class="form-control cus-control" readonly="true" /></td>
										
										<td>
											<c:choose>
											 	<c:when test="${formData.modeRecovery=='NONE'}">
											 		<form:select path="modeRecovery"   id="modeRecovery"  disabled="false" class="form-control cus-control" readonly="true">
											 			<form:option value="NONE" label="---select---" />
														<form:option value="modeRecovery1" label="modeRecovery1" />
														<form:option value="modeRecovery2" label="modeRecovery2" />
														<form:option value="modeRecovery3" label="modeRecovery3" />
													</form:select>
											 	</c:when>
											 	<c:otherwise>
											 		<form:select path="modeRecovery"   id="modeRecovery"  disabled="false" class="form-control cus-control" readonly="true">
											 			<form:option id="${formData.modeRecovery}" value="${formData.modeRecovery}"></form:option>
											 		</form:select>
											 </c:otherwise>
											</c:choose>
										</td>
										<td><form:input path="osAmtClaim" id="osAmtClaim" value="${formData.osAmtClaim}" size="20"  readonly="true" class="form-control cus-control"/></td>
									</tr>
								</table>
								<div class="clearfix"></div>
								<h5 class="sub-head"><strong> </strong></h5>
								<hr style="margin: 5px 0; border: 1px solid #d8d8d8">
								<div class="col-md-6 col-sm-4 col-xs-12">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label class="d-block"> Have you ensured inclusion of unappropriated receipts also in the amount of recovery after NPA indicated above?:<span style="color: red">*</span></label>
												<c:choose>
											 		<c:when test="${formData.npaRecoveryFlag=='Y'}">
														<form:radiobutton path="npaRecoveryFlag" value="Y" checked="checked"/>Yes 
											 			<form:radiobutton path="npaRecoveryFlag" value="N" disabled="true"/>No
											 			<form:radiobutton path="npaRecoveryFlag" value="NA" disabled="true"/>NA
											 		</c:when>
											 		<c:when test="${formData.npaRecoveryFlag=='N'}">
											 			<form:radiobutton path="npaRecoveryFlag" value="Y" disabled="true"/>Yes
														<form:radiobutton path="npaRecoveryFlag" value="N" checked="checked"/>No
														<form:radiobutton path="npaRecoveryFlag" value="NA" disabled="true"/>NA
											 		</c:when>
											 		<c:when test="${formData.npaRecoveryFlag=='NA'}">
											 			<form:radiobutton path="npaRecoveryFlag" value="Y" disabled="true"/>Yes
														<form:radiobutton path="npaRecoveryFlag" value="N" disabled="true"/>No
														<form:radiobutton path="npaRecoveryFlag" value="NA" checked="checked"/>NA
											 		</c:when>
											 		<c:otherwise>
											 			<form:radiobutton path="npaRecoveryFlag" value="Y" disabled="true"/>Yes
														<form:radiobutton path="npaRecoveryFlag" value="N" disabled="true"/>No
														<form:radiobutton path="npaRecoveryFlag" value="NA" disabled="true"/>NA
											 		</c:otherwise>
											 	</c:choose>
											 	
										</div>
									</div>
								</div>
								
								<div class="col-md-6 col-sm-4 col-xs-12">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label class="d-block"> Do you confirm feeding of correct value as recoveries after NPA?:<span style="color: red">*</span></label>
												<c:choose>
											 		<c:when test="${formData.confirmRecoveryFlag=='Y'}">
											 			<form:radiobutton path="confirmRecoveryFlag" value="Y" checked="checked"/>Yes
														<form:radiobutton path="confirmRecoveryFlag" value="N" disabled="true"/>No
											 		</c:when>
											 		<c:when test="${formData.confirmRecoveryFlag=='N'}">
											 			<form:radiobutton path="confirmRecoveryFlag" value="Y" disabled="true"/>Yes
														<form:radiobutton path="confirmRecoveryFlag" value="N" checked="checked"/>No
											 		</c:when>
											 		<c:otherwise>
											 			<form:radiobutton path="confirmRecoveryFlag" value="Y" disabled="true"/>Yes
														<form:radiobutton path="confirmRecoveryFlag" value="N" disabled="true"/>No
											 		</c:otherwise>
											 	</c:choose>
										</div>
									</div>
								</div>
										
								<div class="clearfix"></div>
								<hr style="margin: 5px 0; border: 1px solid #d8d8d8">
								<h5 class="sub-head"><strong>Primary Security Details:</strong></h5>
									
								<table class="table table-bordered table-hover cus-table mb-0">
									<thead>
										<tr>
											<th style="    min-width: 100px">Particulars</th>
											<th style="min-width:970px;">Security Nature Value  (in &#8377;)<span style="color: red">*</span></th>
											<th style="min-width:160px;">Networth of Guarantor/Promoter(in &#8377;)<span style="color: red">*</span></th>
											<th style="min-width:200px;">Reasons for Reduction in the value of Security, if any<span style="color: red">*</span></th>
										</tr>
										<tr>
											
										</tr>
									</thead>
									<tr> 
										<td><label class="d-block">As on Date of Sanction of Credit:</label></td> 
										<td style="padding:0 !important">
										<table class="table table-bordered table-hover cus-table mb-0">
											<tr>
												<th>Land:</th>
												<th>Building:</th>
												<th>Plant and Machinery/Equipments:</th>
												<th>Other Fixed/Movable Assets:</th>
												<th>Current Assets:</th>
												<th>Others:</th>
											</tr>
											<tr>
												<td><form:input  path="landValue" id="landValue" size="10" value="${formData.landValueStr}"  readonly="true" style="text-align: right"   class="form-control cus-control" />
										<p id="landValueerror" Class="displayErrorMessageInRedColor"></p></td>
												<td><form:input  path="buildingValue" id="buildingValue" value="${formData.buildingValueStr}" size="10" readonly="true" style="text-align: right" class="form-control cus-control" onchange="caltotalSecurity(this);" oninput="validity.valid||(value='');" /></td>
												<td> <form:input path="planetValue" id="planetValue" value="${formData.planetValueStr}" size="10" readonly="true" style="text-align: right"    class="form-control cus-control" onchange="caltotalSecurity(this);" oninput="validity.valid||(value='');" /></td>
												<td> <form:input  path="otherAssetValue" id="otherAssetValue" value="${formData.otherAssetValueStr}" size="10" readonly="true" style="text-align: right"    class="form-control cus-control" onchange="caltotalSecurity(this);" oninput="validity.valid||(value='');" /></td>
												<td><form:input path="currentAssetValue" id="currentAssetValue" value="${formData.currentAssetValueStr}" size="10" readonly="true" style="text-align: right"  class="form-control cus-control" onchange="caltotalSecurity(this);" oninput="validity.valid||(value='');"/></td>
												<td><form:input  path="othersValue" size="10"  id="othersValue" value="${formData.othersValueStr}"  readonly="true" class="form-control cus-control" style="text-align: right" onchange="caltotalSecurity(this);" oninput="validity.valid||(value='');" /></td>
											</tr>
										</table>
									    </td>									    
										<td><form:input path="networthOfPromotor" id="networthOfPromotor" value="${formData.networthOfPromotorStr}" size="10" readonly="true" style="text-align: right" class="form-control cus-control"/></td>										
										<td>
											<form:select  path="reductionReason" id="reductionReason"  class="form-control cus-control" disabled="false">
						
												<form:option value="" label="select" />
											</form:select>
										</td>
									</tr>
									
									<tr>
										<td></td>
										<td>											 
										<label>	Total:	</label>	<form:input  path="totalSecuritydetails" size="10" style="margin-left:5px; text-align: right; display:inline; width:50%;" class="form-control cus-control" value="${formData.totalSecuritydetailsStr}"  id="totalSecuritydetails" oninput="validity.valid||(value='');" readonly="true"/>
										</td>
										<td><form:errors path="totalSecuritydetailsError" cssClass="error" /></td>
										<td></td>
									</tr>
									
									<tr>
										<td><label class="d-block">As on Date of NPA:</label></td>
										
										<td style="padding:0 !important">
										<table class="table table-bordered table-hover cus-table mb-0">
											<tr style=" ">
												<th>Land:</th>
												<th>Building:</th>
												<th>Plant and Machinery/Equipments:</th>
												<th>Other Fixed/Movable Assets:</th>
												<th>Current Assets:</th>
												<th>Others:</th>
											</tr>
											<tr>
												<td><form:input  path="landValue1" id="landValue1" value="${formData.landValue1Str}" size="10" readonly="true" style="text-align: right"  onchange="caltotalSecurity1(this);" class="form-control cus-control" oninput="validity.valid||(value='');" />
										<p id="landValueerror" Class="displayErrorMessageInRedColor"></p></td>
												<td><form:input  path="buildingValue1" id="buildingValue1" value="${formData.buildingValue1Str}" size="10" readonly="true" style="text-align: right"   onchange="caltotalSecurity1(this);" class="form-control cus-control" oninput="validity.valid||(value='');" /></td>
												<td> <form:input path="planetValue1" id="planetValue1" value="${formData.planetValue1Str}" size="10" readonly="true" style="text-align: right"   onchange="caltotalSecurity1(this);" class="form-control cus-control" oninput="validity.valid||(value='');" /></td>
												<td> <form:input  path="otherAssetValue1" id="otherAssetValue1" value="${formData.otherAssetValue1Str}" size="10" readonly="true" style="text-align: right"   onchange="caltotalSecurity1(this);" class="form-control cus-control" oninput="validity.valid||(value='');" /></td>
												<td> <form:input  path="currentAssetValue1" id="currentAssetValue1" value="${formData.currentAssetValue1Str}"  size="10" readonly="true" style="text-align: right"  onchange="caltotalSecurity1(this);" class="form-control cus-control" oninput="validity.valid||(value='');" /></td>
												<td><form:input  path="othersValue1" id="othersValue1" value="${formData.othersValue1Str}"  size="20" style="text-align: right"   readonly="true" onchange="caltotalSecurity1(this);" class="form-control cus-control" oninput="validity.valid||(value='');" /></td>
											</tr>
										</table>	
										 </td>
											 
										    <td><form:input  path="networthOfPromotor1"  id="networthOfPromotor1" value="${formData.networthOfPromotor1Str}" size="20"   readonly="true" style="text-align: right" class="form-control cus-control" oninput="validity.valid||(value='');" /></td>
										    
										    <td>
										    	<form:select path="reductionReason1" id="reductionReason1" class="form-control cus-control" readonly="true">
													<form:option value="${formData.reductionReason1}" />
												</form:select>
											</td>
										</tr>
										<tr>
											<td></td>
											<td> 	<label>Total:</label>
											<form:input  path="totalSecuritydetails1" id="totalSecuritydetails1" value="${formData.totalSecuritydetails1Str}"  size="20" style="display:inline; margin-left:5px; text-align: right; width:50%;"  class="form-control cus-control" oninput="validity.valid||(value='');" readonly="true"/></td>
											<td><form:errors path="totalSecuritydetails1Error" cssClass="error" /></td>
											<td></td>
										</tr>
										<tr>
										<td><label class="d-block">As on Date of Preferment of Claim:</label></td>
										
										<td style="padding:0 !important">
										<table class="table table-bordered table-hover cus-table mb-0">
											<tr style=" ">
												<th>Land:</th>
												<th>Building:</th>
												<th>Plant and Machinery/Equipments:</th>
												<th>Other Fixed/Movable Assets:</th>
												<th>Current Assets:</th>
												<th>Others:</th>
											</tr>
											<tr>
												<td><form:input  path="landValue2" id="landValue2" value="${formData.landValue2Str}" size="10" readonly="true" style="text-align: right"  onchange="caltotalSecurity1(this);" class="form-control cus-control" oninput="validity.valid||(value='');" /> </td>
												<td><form:input  path="buildingValue2" id="buildingValue2" value="${formData.buildingValue2Str}"  size="10" readonly="true" style="text-align: right"   onchange="caltotalSecurity1(this);" class="form-control cus-control" oninput="validity.valid||(value='');" /></td>
												<td><form:input path="planetValue2" id="planetValue2" value="${formData.planetValue2Str}" size="10" readonly="true" style="text-align: right"   onchange="caltotalSecurity1(this);" class="form-control cus-control" oninput="validity.valid||(value='');" /> </td>
												<td><form:input  path="otherAssetValue2" id="otherAssetValue2" value="${formData.otherAssetValue2Str}" size="10" readonly="true" style="text-align: right"   onchange="caltotalSecurity1(this);" class="form-control cus-control" oninput="validity.valid||(value='');" /></td>
												<td><form:input  path="currentAssetValue2" id="currentAssetValue2" value="${formData.currentAssetValue2Str}"  size="10" readonly="true" style="text-align: right"  onchange="caltotalSecurity1(this);" class="form-control cus-control" oninput="validity.valid||(value='');" /></td>
												<td><form:input  path="othersValue2" id="othersValue2" value="${formData.othersValue2Str}"  size="20" style="text-align: right"   readonly="true" onchange="caltotalSecurity1(this);" class="form-control cus-control" oninput="validity.valid||(value='');" /></td>
											</tr>
										</table>	
										 </td>
											 
										    <td><form:input  path="networthOfPromotor2"  id="networthOfPromotor2" value="${formData.networthOfPromotor2Str}" size="20"   readonly="true" style="text-align: right" class="form-control cus-control" oninput="validity.valid||(value='');" /></td>
										    
										    <td>
												<form:select path="reductionReason2"   id="reductionReason2"  disabled="false" class="form-control cus-control" >
											 			<form:option value="${formData.reductionReason2}" />
												</form:select>
											</td>
										</tr>
										<tr>
											<td></td>
											<td>	<label>Total:</label> 
											<form:input  path="totalSecuritydetails2" id="totalSecuritydetails2" value="${formData.totalSecuritydetails2Str}" size="20" style="display:inline; margin-left:5px; width:50%; text-align: right"  class="form-control cus-control" oninput="validity.valid||(value='');" readonly="true"/></td>
											<td><form:errors path="totalSecuritydetails2Error" cssClass="error" /></td>
											<td></td>
										</tr>
								</table>
									<div class="clearfix"></div>
								<h5 class="sub-head"><strong> Total amount for which guarantee claim is being preferred: </strong></h5>
								<hr style="margin: 5px 0; border: 1px solid #d8d8d8">
								
								<table class="table table-bordered table-hover cus-table mb-0">
									<thead>
											<tr>
										<th>CGPAN</th>
										<th>Loan / Limit Covered under CGTMSE (in &#8377;):</th>
										<th>Outstanding As On Date of Lodgement of Claim (in
											&#8377;)<br> A
										</th>
										<th>Guarantee Coverage<br> B
										</th>
										<th>Claim eligibility amount(in &#8377;)<br> A * B =
											C
										</th>
										<th>First installment of claim (in &#8377;)<br> 75%
											of C (Rs.)
										</th>
									</tr>
									</thead>
									<tr>
										<td><form:input path="cgPanGCP" id="cgPanGCP" value="${formData.cgpan}" size="20"  readonly="true" class="form-control cus-control"/></td>
										<td><form:input path="loanLimitCovered" id="loanLimitCovered"  value="${formData.guarantee_Amt_str}" size="20" readonly="true" class="form-control cus-control" /></td>
										<td><form:input path="osAmtClaimGCP" id="osAmtClaimGCP" value="${formData.osAmtClaimGCPStr}" size="20"  readonly="true"  class="form-control cus-control" /></td>
										<td><form:input path="guaranteeCov" id="guaranteeCov" value="${formData.guaranteeCovStr}" size="20"  readonly="true" class="form-control cus-control"  /></td>
										<td><form:input path="eligableAmtClaim" id="eligableAmtClaim" value="${formData.eligableAmtClaimStr}" size="20"  readonly="true" class="form-control cus-control" /></td>
										<td><form:input path="firstInstallClaim" id="firstInstallClaim" value="${formData.firstInstallClaim}" size="20"  readonly="true" class="form-control cus-control"/></td>
									
									</tr>
								</table>
								
								
								<div class="clearfix"></div>
								<h5 class="sub-head"><strong> General Information: </strong></h5>
								<hr style="margin: 5px 0; border: 1px solid #d8d8d8">
								
								<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>MLI's Comment on financial position of Borrower/Unit : <span style="color: red">*</span>
											</label> <label class="d-block"> <form:input path="financialPositionComments" id="financialPositionComments" value="${formData.financialPositionComments}" size="20"  class="form-control cus-control" readonly="true" />
	
											</label>
	
										</div>
									</div>
								</div>
								
								<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Details of Financial Assistance provided/being considered by MLI to minimize default : <span style="color: red">*</span></label>
											<label class="d-block"> <form:input path="financialAssistanceDtls" id="financialAssistanceDtls" value="${formData.financialAssistanceDtls}" size="20"  class="form-control cus-control" readonly="true"/></label>
										</div>
									</div>
								</div>
								
								<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Does the MLI propose to provide credit support to Chief Promoter/Borrower for any other project :<span style="color: red">*</span></label>
											<label class="d-block"> 
												<c:choose>
											 		<c:when test="${formData.creditSupport=='Y'}">
											 			<form:radiobutton path="creditSupport" value="Y" checked="checked"/>Yes
														<form:radiobutton path="creditSupport" value="N" disabled="true"/>No
											 		</c:when>
											 		<c:when test="${formData.creditSupport=='N'}">
											 			<form:radiobutton path="creditSupport" value="Y" disabled="true"/>Yes
														<form:radiobutton path="creditSupport" value="N" checked="checked"/>No
											 		</c:when>
											 		<c:otherwise>
											 			<form:radiobutton path="creditSupport" value="Y" disabled="true"/>Yes
														<form:radiobutton path="creditSupport" value="N" disabled="true"/>No
											 		</c:otherwise>
											 	</c:choose>
											</label>
										</div>
									</div>
								</div>
								
								<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Details Of Bank Facility already provided to Borrower  : <span style="color: red">*</span>:</label>
											<label class="d-block"> <form:input path="bankFacilityDtls" id="bankFacilityDtls" value="${formData.bankFacilityDtls}" size="20"  class="form-control cus-control" readonly="true"/></label>
										</div>
									</div>
								</div>
								<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Remarks  :</label>
											<label class="d-block"> <form:input path="nbfcMakerClaimLodgRemarks" id="remarks" value="${formData.nbfcMakerClaimLodgRemarks}" size="20"  class="form-control cus-control" readonly="true"/> </label>
										</div>
									</div>
								</div>
								<div align="left" id="successMsg">
									<font color="green" size="5">${message}</font>
								</div>
								<div align="left" id="error">
									<font color="red" size="5">${error}</font>
								</div>
								
								
							 </form:form>
						</div>
					</div>
				</div>
			</div>
			
			
			
		</div>
		
		
	</body>
</html>
