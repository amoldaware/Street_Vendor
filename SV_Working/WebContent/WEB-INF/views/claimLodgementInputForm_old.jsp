<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<LINK href="<%=request.getContextPath()%>/css/stylesheet.css"
	rel="stylesheet" type="text/css">

<style>
.securityValue {
	display: inline;
	float: left;
	padding: 0 1px;
}

.securityValue:nth-child(1),.securityValue:nth-child(2),.securityValue:nth-child(5),.securityValue:nth-child(6)
	{
	width: 130px;
}

.securityValue:nth-child(3) {
	width: 220px;
}

.securityValue:nth-child(4) {
	width: 200px;
}
</style>
<script type="text/javascript">
	$(function() {
		$("#dateOfNotice").datepicker({
			// showAnim : "fold"
			dateFormat : "dd/mm/yy"
		});
	});

	$(function() {
		$("#dateOfSarfaesi").datepicker({
			// showAnim : "fold"
			dateFormat : "dd/mm/yy"
		});
	});

	$(function() {
		$("#dateOfSubsidy").datepicker({
			// showAnim : "fold"
			dateFormat : "dd/mm/yy"
		});
	});
</script>

<script type="text/javascript">
	function myFunction(id) {
		var x = document.getElementById(id);
		if (x.className.indexOf("w3-show") == -1) {
			x.className += " w3-show";
		} else {
			x.className = x.className.replace(" w3-show", "");
		}
	}
	function calTotOutstandingAsOnDateOfLodgementOfClaim(recoveryMadeAmt) {
		var totalOsAmount = document.getElementById('totalOsAmt').value;
		var latestOsAmount = document.getElementById('latestOsAmt').value;
		if (parseInt(totalOsAmount) > parseInt(latestOsAmount)) {
			document.getElementById('osAmtClaim').value = parseInt(latestOsAmount)
					- parseInt(recoveryMadeAmt);
		} else if (parseInt(latestOsAmount) > parseInt(totalOsAmount)) {
			document.getElementById('osAmtClaim').value = parseInt(totalOsAmount)
					- parseInt(recoveryMadeAmt);
		} else {
			document.getElementById('osAmtClaim').value = parseInt(totalOsAmount)
					- parseInt(recoveryMadeAmt);
		}
		document.getElementById('osAmtClaimGCP').value = document
				.getElementById('osAmtClaim').value;
		var calTotElaimEligibilityAmount = (parseInt(document
				.getElementById('osAmtClaimGCP').value) * parseInt(document
				.getElementById('guaranteeCov').value)) / 100;
		document.getElementById('eligableAmtClaim').value = calTotElaimEligibilityAmount;

		document.getElementById('firstInstallClaim').value = (parseInt(document
				.getElementById('eligableAmtClaim').value) * 75) / 100;

	}
</script>
</head>
<body>

	<div class="main-section">
		<div class="container-fluid">
			<div>
				<div class="frm-section">
					<div class="col-md-12">
						<form:form action="saveClaimLodgmentDtls.html" method="POST" class="form-horizontal" enctype="multipart/form-data" >
							<div class="clearfix"></div>
							<h5 class="sub-head">
								<strong> Details of Operating Office and Lending
									Branch: </strong>
							</h5>
							<hr style="margin: 5px 0; border: 1px solid #d8d8d8">
							<div class="clearfix"></div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Member Id :</label>
										<form:input path="memberId" id="memberId" value="${memberId}"
											size="20" readonly="true" style="text-align: right"
											class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>CGPAN :</label>
										<form:input path="cgpan" id="cgpan" value="" size="20"
											readonly="true" class="form-control cus-control" />
									</div>
								</div>
							</div>


							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Lending NBFC Name :</label>
										<form:input path="lendingNbfcName" id="lendingNbfcName"
											value="${formData.lendingNbfcName}" size="20" readonly="true"
											class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>City :</label>
										<form:input path="city" id="city" value="${formData.city}"
											size="20" readonly="true" class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Complete Address : </label>
										<form:input path="address" id="address"
											value="${formData.address}" size="20" readonly="true"
											class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>District :</label>
										<form:input path="district" id="district"
											value="${formData.district}" size="20" readonly="true"
											class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">

									<div class="col-md-12 prl-10">
										<label>State :</label>
										<form:input path="state" id="state" value="${formData.state}"
											size="20" readonly="true" class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">

									<div class="col-md-12 prl-10">
										<label>Email :</label>
										<form:input path="email" id="email" value="${formData.email}"
											size="20" readonly="true" class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">

									<div class="col-md-12 prl-10">
										<label>Telephone No :</label>
										<form:input path="telephoneNo" id="telephoneNo"
											value="${formData.telephoneNo}" size="20" readonly="true"
											class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>GSTIN No :</label>
										<form:input path="gstinNo" id="gstinNo"
											value="${formData.gstinNo}" size="20" readonly="true"
											class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Dealing Officer Name<span style="color: red">*</span>
											:
										</label>
										<form:input path="dealingOfficerName" id="dealingOfficerName"
											value="${formData1.dealingOfficerName}" size="20"
											class="form-control cus-control" />
									</div>
								</div>
							</div>
							<div class="clearfix"></div>

							<h5 class="sub-head">
								<strong> Borrower's /Unit's Details: </strong>
							</h5>
							<hr style="margin: 5px 0; border: 1px solid #d8d8d8">

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Name of the Borrower/Unit :</label>
										<form:input path="nameOfBorrower" id="nameOfBorrower"
											value="${formData.nameOfBorrower}" size="20" readonly="true"
											class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Complete Address :</label>
										<form:input path="adressOfBorrower" id="adressOfBorrower"
											value="${formData.adressOfBorrower}" size="20"
											readonly="true" class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>City :</label>
										<form:input path="cityOfBorrower" id="cityOfBorrower"
											value="${formData.cityOfBorrower}" size="20" readonly="true"
											class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>District :</label>
										<form:input path="districtOfBorrower" id="districtOfBorrower"
											value="${formData.districtOfBorrower}" size="20"
											readonly="true" class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>State :</label>
										<form:input path="stateOfBorrower" id="stateOfBorrower"
											value="${formData.stateOfBorrower}" size="20" readonly="true"
											class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-1 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>PinCode :</label>
										<form:input path="pincodeOfBorrower" id="pincodeOfBorrower"
											value="${formData.pincodeOfBorrower}" size="20"
											readonly="true" class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="clearfix"></div>
							<h5 class="sub-head">
								<strong> Status of Account(s): </strong>
							</h5>
							<hr style="margin: 5px 0; border: 1px solid #d8d8d8">

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Latest Outstanding Guaranteed Amount :</label>
										<form:input path="latestOsGuranteeAmt"
											id="latestOsGuranteeAmt"
											value="${formData.latestOsGuranteeAmtVStr}" size="20"
											readonly="true" class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Date on which Account was Classified as NPA :</label>
										<form:input path="dateOfNPA" id="dateOfNPA"
											value="${formData.dateOfNPA}" size="20" readonly="true"
											class="form-control cus-control" />
									</div>
								</div>
							</div>
							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Wilful defaulter<span style="color: red">*</span>:
										</label> <label class="d-block"> <c:choose>
												<c:when test="${formData1.wilfulDefaulter=='Y'}">
													<form:radiobutton path="wilfulDefaulter" value="Y"
														checked="checked" />Yes 
											 			<form:radiobutton path="wilfulDefaulter" value="N" />No
											 		</c:when>
												<c:when test="${formData1.wilfulDefaulter=='N'}">
													<form:radiobutton path="wilfulDefaulter" value="Y" />Yes 
											 			<form:radiobutton path="wilfulDefaulter" value="N"
														checked="checked" />No
											 		</c:when>

												<c:otherwise>
													<form:radiobutton path="wilfulDefaulter" value="Y" />Yes 
											 			<form:radiobutton path="wilfulDefaulter" value="N" />No
											 		</c:otherwise>
											</c:choose>
										</label>
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Has the account been classified as fraud<span
											style="color: red">*</span>:
										</label> <label class="d-block"> <c:choose>
												<c:when test="${formData1.fraudAcc=='Y'}">
													<form:radiobutton path="fraudAcc" value="Y"
														checked="checked" />Yes 
											 			<form:radiobutton path="fraudAcc" value="N" />No
											 		</c:when>
												<c:when test="${formData1.fraudAcc=='N'}">
													<form:radiobutton path="fraudAcc" value="Y" />Yes 
											 			<form:radiobutton path="fraudAcc" value="N"
														checked="checked" />No
											 		</c:when>

												<c:otherwise>
													<form:radiobutton path="fraudAcc" value="Y" />Yes 
											 			<form:radiobutton path="fraudAcc" value="N" />No
											 		</c:otherwise>
											</c:choose>
										</label>
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Internal and/or external enquiry has been
											concluded<span style="color: red">*</span>:
										</label> <label class="d-block"> <c:choose>
												<c:when test="${formData1.enquiryConcluded=='Y'}">
													<form:radiobutton path="enquiryConcluded" value="Y"
														checked="checked" />Yes 
											 			<form:radiobutton path="enquiryConcluded" value="N" />No
											 		</c:when>
												<c:when test="${formData1.enquiryConcluded=='N'}">
													<form:radiobutton path="enquiryConcluded" value="Y" />Yes 
											 			<form:radiobutton path="enquiryConcluded" value="N"
														checked="checked" />No
											 		</c:when>

												<c:otherwise>
													<form:radiobutton path="enquiryConcluded" value="Y" />Yes 
											 			<form:radiobutton path="enquiryConcluded" value="N" />No
											 		</c:otherwise>
											</c:choose>
										</label>
									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Involvement of staff of MLI has been reported<span
											style="color: red">*</span>:
										</label> <label class="d-block"> <c:choose>
												<c:when test="${formData1.mliReported=='Y'}">
													<form:radiobutton path="mliReported" value="Y"
														checked="checked" />Yes 
											 			<form:radiobutton path="mliReported" value="N" />No
											 		</c:when>
												<c:when test="${formData1.mliReported=='N'}">
													<form:radiobutton path="mliReported" value="Y" />Yes 
											 			<form:radiobutton path="mliReported" value="N"
														checked="checked" />No
											 		</c:when>

												<c:otherwise>
													<form:radiobutton path="mliReported" value="Y" />Yes 
											 			<form:radiobutton path="mliReported" value="N" />No
											 		</c:otherwise>
											</c:choose>
										</label>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Reasons for Account turning NPA<span
											style="color: red">*</span>:
										</label> <label class="d-block"> <form:input
												path="reasonOfNPA" value="${formData.reasonOfNPA}" size="20"
												id="reasonOfNPA" class="form-control cus-control"
												placeholder="" readonly="true" /></label>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Date of issue of Recall Notice/Demand Notice<span
											style="color: red">*</span>:
										</label> <label class="d-block"> <form:input
												style="font-size: 12px;" path="dateOfNotice"
												id="dateOfNotice" value="${formData1.dateOfNotice}"
												size="20" class="date-picker form-control cus-control"
												placeholder="e.g:dd/mm/yyyy" /></label>
										<form:errors path="dateOfNotice" cssClass="error" />
									</div>
								</div>
							</div>

							<div class="clearfix"></div>
							<h5 class="sub-head">
								<strong> Details of Legal Proceedings: </strong>
							</h5>
							<hr style="margin: 5px 0; border: 1px solid #d8d8d8">

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label class="d-block">Forum through which legal
											proceedings were initiated against borrower :<span
											style="color: red">*</span>
										</label>
										<c:choose>
											<c:when test="${formData1.forum=='NONE'}">
												<form:select path="forum" id="forum" disabled="false"
													class="form-control cus-control">
													<form:option value="NONE" label="---select---" />
													<form:option value="Forum1" label="Forum1" />
													<form:option value="Forum2" label="Forum2" />
													<form:option value="Forum3" label="Forum3" />
												</form:select>
											</c:when>
											<c:otherwise>
												<form:select path="Forum" id="Forum" disabled="false"
													class="form-control cus-control">
													<form:option id="${formData1.forum}"
														value="${formData1.forum}"></form:option>
												</form:select>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Provide satisfactory reason for filing suit
											before NPA date(if applicable)<span style="color: red">*</span>:
										</label> <label class="d-block"> <form:input
												path="resonFillingSuit" id="resonFillingSuit"
												value="${formData1.resonFillingSuit}" size="20"
												class="form-control cus-control" /></label>
									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Suit / Case Registration No.<span
											style="color: red">*</span>:
										</label> <label class="d-block"> <form:input path="suitNo"
												id="suitNo" value="${formData1.suitNo}" size="20"
												class="form-control cus-control" /></label>
									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Date of possession of assets under SARFAESI act<span
											style="color: red">*</span>:
										</label> <label class="d-block"> <form:input
												path="dateOfSarfaesi" id="dateOfSarfaesi" size="20"
												value="${formData1.dateOfSarfaesi}"
												class="date-picker form-control cus-control"
												placeholder="e.g:dd/mm/yyyy" /></label>
										<form:errors path="dateOfNotice" cssClass="error" />
									</div>
								</div>
							</div>
							<div class="clearfix"></div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Location of Legal forum<span style="color: red">*</span>:
										</label> <label class="d-block"> <form:input
												path="locationOfForum" id="locationOfForum"
												value="${formData1.locationOfForum}" size="20"
												class="form-control cus-control" /></label>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Amount Claimed in the Demand
											Notice/Suit(in&#8377;): <span style="color: red">*</span>
										</label> <label class="d-block"> <form:input path="claimAmt"
												id="claimAmt" value="${formData1.claimAmt}" size="20"
												class="form-control cus-control" />
										</label>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Any Attachments(Demand Notice/legal documents:<span
											style="color: red">*</span>
										</label> <label class="d-block">
										<td><input type="file" name="file" id="file" class="form-control cus-control"></input></td>
										</label>
									</div>
								</div>
							</div>

							<div class="clearfix"></div>
							<h5 class="sub-head">
								<strong> Subsidy Details : </strong>
							</h5>
							<hr style="margin: 5px 0; border: 1px solid #d8d8d8">

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Does the project covered under CGTMSE
											guarantee, involve any subsidy?</label> <label class="d-block">
											<c:choose>
												<c:when test="${formData1.anySubsidyInvolved=='Y'}">
													<form:radiobutton path="anySubsidyInvolved" value="Y"
														checked="checked" />Yes 
											 			<form:radiobutton path="anySubsidyInvolved" value="N" />No
											 		</c:when>
												<c:when test="${formData1.anySubsidyInvolved=='N'}">
													<form:radiobutton path="anySubsidyInvolved" value="Y" />Yes 
											 			<form:radiobutton path="anySubsidyInvolved" value="N"
														checked="checked" />No
											 		</c:when>
												<c:otherwise>
													<form:radiobutton path="anySubsidyInvolved" value="Y" />Yes 
											 			<form:radiobutton path="anySubsidyInvolved" value="N" />No
											 		</c:otherwise>
											</c:choose>
										</label>
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Has the subsidy been received after NPA?</label> <label
											class="d-block"> <c:choose>
												<c:when test="${formData1.subsidyReceived=='Y'}">
													<form:radiobutton path="subsidyReceived" value="Y"
														checked="checked" />Yes 
											 			<form:radiobutton path="subsidyReceived" value="N" />No
											 		</c:when>
												<c:when test="${formData1.subsidyReceived=='N'}">
													<form:radiobutton path="subsidyReceived" value="Y" />Yes 
											 			<form:radiobutton path="subsidyReceived" value="N"
														checked="checked" />No
											 		</c:when>
												<c:otherwise>
													<form:radiobutton path="subsidyReceived" value="Y" />Yes 
											 			<form:radiobutton path="subsidyReceived" value="N" />No
											 		</c:otherwise>
											</c:choose>
										</label>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Has the subsidy been adjusted against
											principal/interest over dues?</label> <label class="d-block">
											<c:choose>
												<c:when test="${formData1.subsidyAdjust=='Y'}">
													<form:radiobutton path="subsidyAdjust" value="Y"
														checked="checked" />Yes 
											 			<form:radiobutton path="subsidyAdjust" value="N" />No
											 		</c:when>
												<c:when test="${formData1.subsidyAdjust=='N'}">
													<form:radiobutton path="subsidyAdjust" value="Y" />Yes 
											 			<form:radiobutton path="subsidyAdjust" value="N"
														checked="checked" />No
											 		</c:when>
												<c:otherwise>
													<form:radiobutton path="subsidyAdjust" value="Y" />Yes 
											 			<form:radiobutton path="subsidyAdjust" value="N" />No
											 		</c:otherwise>
											</c:choose>
										</label>
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Subsidy Credit Date:</label> <label class="d-block">
											<form:input path="dateOfSubsidy" id="dateOfSubsidy"
												value="${formData1.dateOfSubsidy}" size="20"
												class="date-picker form-control cus-control"
												placeholder="e.g:dd/mm/yyyy" />
										</label>
										<form:errors path="dateOfSubsidy" cssClass="error" />
									</div>
								</div>

							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Subsidy Amount(in&#8377;): <span
											style="color: red">*</span>:
										</label> <label class="d-block"> <form:input path="subsidyAmt"
												id="subsidyAmt" value="${formData1.subsidyAmt}" size="20"
												class="form-control cus-control" /></label>
									</div>
								</div>
							</div>

							<div class="clearfix"></div>
							<h5 class="sub-head">
								<strong> Term Loan (TL) : </strong>
							</h5>
							<hr style="margin: 5px 0; border: 1px solid #d8d8d8">
							<table class="table table-bordered table-hover cus-table mb-0">
								<thead>
									<tr>
										<th>CGPAN</th>
										<th>Latest Outstanding Guaranteed Amount (in &#8377;)</th>
										<th>Total outstanding as on date of NPA(in &#8377;)</th>
										<th>Recovery made from Borrower / Unit after account
											classified as NPA(in &#8377;)</th>
										<th>Mode of recovery</th>
										<th>Outstanding As On Date of Lodgement of Claim (in
											&#8377;)</th>
									</tr>
								</thead>
								<tr>
									<!--  Anand-->
									<td><form:input path="cgPanTL" id="cgPanTL"
											value="${formData.cgpan}" size="20" readonly="true"
											class="form-control cus-control" /></td>
									<td><form:input path="latestOsAmt" id="latestOsAmt"
											value="${formData.latestOsGuranteeAmtVStr}" readonly="true"
											class="form-control cus-control" /></td>
									<td><form:input path="totalOsAmt" id="totalOsAmt"
											value="${formData.totalOsAmtStr}" size="20" readonly="true"
											class="form-control cus-control" /></td>
									<td><form:input path="recovery" id="recovery"
											value="${formData1.recovery}" size="20"
											class="form-control cus-control"
											onchange="calTotOutstandingAsOnDateOfLodgementOfClaim(this.value);" /></td>
									<td><c:choose>
											<c:when test="${formData1.modeRecovery=='NONE'}">
												<form:select path="modeRecovery" id="modeRecovery"
													disabled="false" class="form-control cus-control">
													<form:option value="NONE" label="---select---" />
													<form:option value="modeRecovery1" label="modeRecovery1" />
													<form:option value="modeRecovery2" label="modeRecovery2" />
													<form:option value="modeRecovery3" label="modeRecovery3" />
												</form:select>
											</c:when>
											<c:otherwise>
												<form:select path="modeRecovery" id="modeRecovery"
													disabled="false" class="form-control cus-control">
													<form:option id="${formData1.modeRecovery}"
														value="${formData1.modeRecovery}"></form:option>
												</form:select>
											</c:otherwise>
										</c:choose></td>
									<td><form:input path="osAmtClaim" id="osAmtClaim"
											value="${formData1.osAmtClaim}" size="20" readonly="true"
											class="form-control cus-control" /></td>
								</tr>
							</table>
							<div class="clearfix"></div>
							<h5 class="sub-head">
								<strong> </strong>
							</h5>
							<hr style="margin: 5px 0; border: 1px solid #d8d8d8">
							<div class="col-md-6 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label class="d-block"> Have you ensured inclusion of
											unappropriated receipts also in the amount of recovery after
											NPA indicated above?:<span style="color: red">*</span>
										</label>
										<c:choose>
											<c:when test="${formData1.npaRecoveryFlag=='Y'}">
												<form:radiobutton path="npaRecoveryFlag" value="Y"
													checked="checked" />Yes 
											 			<form:radiobutton path="npaRecoveryFlag" value="N" />No
											 			<form:radiobutton path="npaRecoveryFlag" value="NA"
													checked="checked" />NA
											 		</c:when>
											<c:when test="${formData1.npaRecoveryFlag=='N'}">
												<form:radiobutton path="npaRecoveryFlag" value="Y" />Yes
														<form:radiobutton path="npaRecoveryFlag" value="N"
													checked="checked" />No
														<form:radiobutton path="npaRecoveryFlag" value="NA" />NA
											 		</c:when>
											<c:when test="${formData1.npaRecoveryFlag=='NA'}">
												<form:radiobutton path="npaRecoveryFlag" value="Y" />Yes
														<form:radiobutton path="npaRecoveryFlag" value="N" />No
														<form:radiobutton path="npaRecoveryFlag" value="NA"
													checked="checked" />NA
											 		</c:when>
											<c:otherwise>
												<form:radiobutton path="npaRecoveryFlag" value="Y" />Yes
														<form:radiobutton path="npaRecoveryFlag" value="N" />No
														<form:radiobutton path="npaRecoveryFlag" value="NA" />NA
											 		</c:otherwise>
										</c:choose>

									</div>
								</div>
							</div>

							<div class="col-md-6 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label class="d-block"> Do you confirm feeding of
											correct value as recoveries after NPA?:<span
											style="color: red">*</span>
										</label>
										<c:choose>
											<c:when test="${formData1.confirmRecoveryFlag=='Y'}">
												<form:radiobutton path="confirmRecoveryFlag" value="Y"
													checked="checked" />Yes
														<form:radiobutton path="confirmRecoveryFlag" value="N" />No
											 		</c:when>
											<c:when test="${formData1.confirmRecoveryFlag=='N'}">
												<form:radiobutton path="confirmRecoveryFlag" value="Y" />Yes
														<form:radiobutton path="confirmRecoveryFlag" value="N"
													checked="checked" />No
											 		</c:when>
											<c:otherwise>
												<form:radiobutton path="confirmRecoveryFlag" value="Y" />Yes
														<form:radiobutton path="confirmRecoveryFlag" value="N" />No
											 		</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>

							<div class="clearfix"></div>
							<hr style="margin: 5px 0; border: 1px solid #d8d8d8">
							<h5 class="sub-head">
								<strong>Primary Security Details:</strong>
							</h5>

							<table class="table table-bordered table-hover cus-table mb-0">
								<thead>
									<tr>
										<th style="min-width: 100px">Particulars</th>
										<th style="min-width: 970px;">Security Nature Value (in
											&#8377;)<span style="color: red">*</span>
										</th>
										<th style="min-width: 160px;">Networth of
											Guarantor/Promoter(in &#8377;)<span style="color: red">*</span>
										</th>
										<th style="min-width: 200px;">Reasons for Reduction in
											the value of Security, if any<span style="color: red">*</span>
										</th>
									</tr>
									<tr>

									</tr>
								</thead>
								<tr>
									<td><label class="d-block">As on Date of Sanction
											of Credit:</label></td>
									<td style="padding: 0 !important">
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
												<td><form:input path="landValue" id="landValue"
														size="10" value="${formData.landValueStr}" readonly="true"
														style="text-align: right" class="form-control cus-control" />
													<p id="landValueerror"
														Class="displayErrorMessageInRedColor"></p></td>
												<td><form:input path="buildingValue" id="buildingValue"
														value="${formData.buildingValueStr}" size="10"
														readonly="true" style="text-align: right"
														class="form-control cus-control"
														onchange="caltotalSecurity(this);"
														oninput="validity.valid||(value='');" /></td>
												<td><form:input path="planetValue" id="planetValue"
														value="${formData.planetValueStr}" size="10"
														readonly="true" style="text-align: right"
														class="form-control cus-control"
														onchange="caltotalSecurity(this);"
														oninput="validity.valid||(value='');" /></td>
												<td><form:input path="otherAssetValue"
														id="otherAssetValue"
														value="${formData.otherAssetValueStr}" size="10"
														readonly="true" style="text-align: right"
														class="form-control cus-control"
														onchange="caltotalSecurity(this);"
														oninput="validity.valid||(value='');" /></td>
												<td><form:input path="currentAssetValue"
														id="currentAssetValue"
														value="${formData.currentAssetValueStr}" size="10"
														readonly="true" style="text-align: right"
														class="form-control cus-control"
														onchange="caltotalSecurity(this);"
														oninput="validity.valid||(value='');" /></td>
												<td><form:input path="othersValue" size="10"
														id="othersValue" value="${formData.othersValueStr}"
														readonly="true" class="form-control cus-control"
														style="text-align: right"
														onchange="caltotalSecurity(this);"
														oninput="validity.valid||(value='');" /></td>
											</tr>
										</table>
									</td>
									<td><form:input path="networthOfPromotor"
											id="networthOfPromotor"
											value="${formData.networthOfPromotorStr}" size="10"
											readonly="true" style="text-align: right"
											class="form-control cus-control" /></td>
									<td><form:select path="reductionReason"
											id="reductionReason" class="form-control cus-control"
											disabled="false">

											<form:option value="" label="select" />
										</form:select></td>
								</tr>

								<tr>
									<td></td>
									<td><label> Total: </label> <form:input
											path="totalSecuritydetails" size="10"
											style="margin-left:5px; text-align: right; display:inline; width:50%;"
											class="form-control cus-control"
											value="${formData.totalSecuritydetailsStr}"
											id="totalSecuritydetails"
											oninput="validity.valid||(value='');" readonly="true" /></td>
									<td><form:errors path="totalSecuritydetailsError"
											cssClass="error" /></td>
									<td></td>
								</tr>

								<tr>
									<td><label class="d-block">As on Date of NPA:</label></td>

									<td style="padding: 0 !important">
										<table class="table table-bordered table-hover cus-table mb-0">
											<tr style="">
												<th>Land:</th>
												<th>Building:</th>
												<th>Plant and Machinery/Equipments:</th>
												<th>Other Fixed/Movable Assets:</th>
												<th>Current Assets:</th>
												<th>Others:</th>
											</tr>
											<tr>
												<td><form:input path="landValue1" id="landValue1"
														value="${formData.landValue1Str}" size="10"
														readonly="true" style="text-align: right"
														onchange="caltotalSecurity1(this);"
														class="form-control cus-control"
														oninput="validity.valid||(value='');" />
													<p id="landValueerror"
														Class="displayErrorMessageInRedColor"></p></td>
												<td><form:input path="buildingValue1"
														id="buildingValue1" value="${formData.buildingValue1Str}"
														size="10" readonly="true" style="text-align: right"
														onchange="caltotalSecurity1(this);"
														class="form-control cus-control"
														oninput="validity.valid||(value='');" /></td>
												<td><form:input path="planetValue1" id="planetValue1"
														value="${formData.planetValue1Str}" size="10"
														readonly="true" style="text-align: right"
														onchange="caltotalSecurity1(this);"
														class="form-control cus-control"
														oninput="validity.valid||(value='');" /></td>
												<td><form:input path="otherAssetValue1"
														id="otherAssetValue1"
														value="${formData.otherAssetValue1Str}" size="10"
														readonly="true" style="text-align: right"
														onchange="caltotalSecurity1(this);"
														class="form-control cus-control"
														oninput="validity.valid||(value='');" /></td>
												<td><form:input path="currentAssetValue1"
														id="currentAssetValue1"
														value="${formData.currentAssetValue1Str}" size="10"
														readonly="true" style="text-align: right"
														onchange="caltotalSecurity1(this);"
														class="form-control cus-control"
														oninput="validity.valid||(value='');" /></td>
												<td><form:input path="othersValue1" id="othersValue1"
														value="${formData.othersValue1Str}" size="20"
														style="text-align: right" readonly="true"
														onchange="caltotalSecurity1(this);"
														class="form-control cus-control"
														oninput="validity.valid||(value='');" /></td>
											</tr>
										</table>
									</td>

									<td><form:input path="networthOfPromotor1"
											id="networthOfPromotor1"
											value="${formData.networthOfPromotor1Str}" size="20"
											readonly="true" style="text-align: right"
											class="form-control cus-control"
											oninput="validity.valid||(value='');" /></td>

									<td><form:select path="reductionReason1"
											id="reductionReason1" class="form-control cus-control"
											readonly="true">
											<form:option value="${formData.reductionReason1}" />
										</form:select></td>
								</tr>
								<tr>
									<td></td>
									<td><label>Total:</label> <form:input
											path="totalSecuritydetails1" id="totalSecuritydetails1"
											value="${formData.totalSecuritydetails1Str}" size="20"
											style="display:inline; margin-left:5px; text-align: right; width:50%;"
											class="form-control cus-control"
											oninput="validity.valid||(value='');" readonly="true" /></td>
									<td><form:errors path="totalSecuritydetails1Error"
											cssClass="error" /></td>
									<td></td>
								</tr>
								<tr>
									<td><label class="d-block">As on Date of
											Preferment of Claim:</label></td>

									<td style="padding: 0 !important">
										<table class="table table-bordered table-hover cus-table mb-0">
											<tr style="">
												<th>Land:</th>
												<th>Building:</th>
												<th>Plant and Machinery/Equipments:</th>
												<th>Other Fixed/Movable Assets:</th>
												<th>Current Assets:</th>
												<th>Others:</th>
											</tr>
											<tr>
												<td><form:input path="landValue2" id="landValue2"
														value="${formData1.landValue2Str}" size="10"
														readonly="false" style="text-align: right"
														onchange="caltotalSecurity1(this);"
														class="form-control cus-control"
														oninput="validity.valid||(value='');" /></td>
												<td><form:input path="buildingValue2"
														id="buildingValue2" value="${formData1.buildingValue2Str}"
														size="10" readonly="false" style="text-align: right"
														onchange="caltotalSecurity1(this);"
														class="form-control cus-control"
														oninput="validity.valid||(value='');" /></td>
												<td><form:input path="planetValue2" id="planetValue2"
														value="${formData1.planetValue2Str}" size="10"
														readonly="false" style="text-align: right"
														onchange="caltotalSecurity1(this);"
														class="form-control cus-control"
														oninput="validity.valid||(value='');" /></td>
												<td><form:input path="otherAssetValue2"
														id="otherAssetValue2"
														value="${formData1.otherAssetValue2Str}" size="10"
														readonly="false" style="text-align: right"
														onchange="caltotalSecurity1(this);"
														class="form-control cus-control"
														oninput="validity.valid||(value='');" /></td>
												<td><form:input path="currentAssetValue2"
														id="currentAssetValue2"
														value="${formData1.currentAssetValue2Str}" size="10"
														readonly="false" style="text-align: right"
														onchange="caltotalSecurity1(this);"
														class="form-control cus-control"
														oninput="validity.valid||(value='');" /></td>
												<td><form:input path="othersValue2" id="othersValue2"
														value="${formData1.othersValue2Str}" size="20"
														style="text-align: right" readonly="false"
														onchange="caltotalSecurity1(this);"
														class="form-control cus-control"
														oninput="validity.valid||(value='');" /></td>
											</tr>
										</table>
									</td>

									<td><form:input path="networthOfPromotor2"
											id="networthOfPromotor2"
											value="${formData1.networthOfPromotor2Str}" size="20"
											readonly="false" style="text-align: right"
											class="form-control cus-control"
											oninput="validity.valid||(value='');" /></td>

									<td><form:select path="reductionReason2"
											id="reductionReason2" disabled="false"
											class="form-control cus-control">
											<form:option value="NONE" label="---select---" />
										</form:select></td>
								</tr>
								<tr>
									<td></td>
									<td><label>Total:</label> <form:input
											path="totalSecuritydetails2" id="totalSecuritydetails2"
											value="${formData1.totalSecuritydetails2Str}" size="20"
											style="display:inline; margin-left:5px; width:50%; text-align: right"
											class="form-control cus-control"
											oninput="validity.valid||(value='');" readonly="false" /></td>
									<td><form:errors path="totalSecuritydetails2Error"
											cssClass="error" /></td>
									<td></td>
								</tr>
							</table>
							<div class="clearfix"></div>
							<h5 class="sub-head">
								<strong> Total amount for which guarantee claim is
									being preferred: </strong>
							</h5>
							<hr style="margin: 5px 0; border: 1px solid #d8d8d8">

							<table class="table table-bordered table-hover cus-table mb-0">
								<thead>
									<tr>
										<th>CGPAN</th>
										<th>Loan / Limit Covered under CGTMSE (in &#8377;):</th>
										<th>Guarantee Coverage(50/60/75)</th>
										<th>Outstanding Amount for Claim (in &#8377;)</th>
										<th>Claim eligibility amount(in &#8377;)</th>
										<th>First installment of claim</th>
									</tr>
								</thead>
								<tr>
									<td><form:input path="cgPanGCP" id="cgPanGCP"
											value="${formData.cgpan}" size="20" readonly="true"
											class="form-control cus-control" /></td>
									<td><form:input path="loanLimitCovered"
											id="loanLimitCovered"
											value="${formData.latestOsGuranteeAmtVStr}" size="20"
											readonly="true" class="form-control cus-control" /></td>
									<td><form:input path="guaranteeCov" id="guaranteeCov"
											value="${formData.guaranteeCovStr}" size="20" readonly="true"
											class="form-control cus-control" /></td>
									<!--<td><form:input path="osAmtClaimGCP" id="osAmtClaimGCP" value="" size="20"  readonly="true"  class="form-control cus-control" /></td>
										<td><form:input path="eligableAmtClaim" id="eligableAmtClaim" value="" size="20"  readonly="true" class="form-control cus-control" /></td>
										-->
									<td><form:input path="osAmtClaimGCP" id="osAmtClaimGCP"
											value="${formData1.osAmtClaimGCPStr}" size="20"
											readonly="true" class="form-control cus-control" /></td>
									<td><form:input path="eligableAmtClaim"
											id="eligableAmtClaim"
											value="${formData1.eligableAmtClaimStr}" size="20"
											readonly="true" class="form-control cus-control" /></td>
									<td><form:input path="firstInstallClaim"
											id="firstInstallClaim" value="${formData1.firstInstallClaim}"
											size="20" readonly="true" class="form-control cus-control" /></td>

								</tr>
							</table>
							<div class="clearfix"></div>
							<h5 class="sub-head">
								<strong> Claim Lodgement Check List: </strong>
							</h5>
							<div class="clearfix"></div>
							<div class="table-responsive">
								<table
									class="table table-bordered table-hover cus-table mt-10 mb-0">
									<tr>
										<th style="min-width: 70px;">Sr. No.</th>
										<th style="min-width: 500px;">Description</th>
										<th style="min-width: 90px;">Yes / No</th>
										<th style="min-width: 650px;">Comments/observations</th>
									</tr>
									<tr>
										<td>1</td>
										<td>Activity is eligible as per Credit guarantee Scheme.</td>
										<td><c:choose>
												<c:when test="${formData2.activityEligible=='Y'}">
													<form:radiobutton path="activityEligible" value="Y"
														checked="checked" />Yes
														<form:radiobutton path="activityEligible" value="N" />No
											 		</c:when>
												<c:when test="${formData2.activityEligible=='N'}">
													<form:radiobutton path="activityEligible" value="Y" />Yes
														<form:radiobutton path="activityEligible" value="N"
														checked="checked" />No
											 		</c:when>
												<c:otherwise>
													<form:radiobutton path="activityEligible" value="Y" />Yes
														<form:radiobutton path="activityEligible" value="N" />No
											 		</c:otherwise>
											</c:choose></td>
										<td><form:input path="activityEligibleRemarks"
												id="activityEligibleRemarks"
												value="${formData2.activityEligibleRemarks}"
												readonly="false" class="form-control cus-control" /></td>
									</tr>
									<tr>
										<td>2</td>
										<td>Whether CIBIL done/CIR/KYC obtained and findings are
											satisfactory.</td>
										<td><c:choose>
												<c:when test="${formData2.cibil=='Y'}">
													<form:radiobutton path="cibil" value="Y" checked="checked" />Yes
														<form:radiobutton path="cibil" value="N" />No
											 		</c:when>
												<c:when test="${formData2.cibil=='N'}">
													<form:radiobutton path="cibil" value="Y" />Yes
														<form:radiobutton path="cibil" value="N" checked="checked" />No
											 		</c:when>
												<c:otherwise>
													<form:radiobutton path="cibil" value="Y" />Yes
														<form:radiobutton path="cibil" value="N" />No
											 		</c:otherwise>
											</c:choose></td>
										<td><form:input path="cibilRemarks" id="cibilRemarks"
												value="${formData2.cibilRemarks}" readonly="false"
												class="form-control cus-control" /></td>
									</tr>
									<tr>
										<td>3</td>
										<td>Rate charged on loan is as per CGS guidelines..</td>
										<td><c:choose>
												<c:when test="${formData2.rateCharge=='Y'}">
													<form:radiobutton path="rateCharge" value="Y"
														checked="checked" />Yes
														<form:radiobutton path="rateCharge" value="N" />No
											 		</c:when>
												<c:when test="${formData1.rateCharge=='N'}">
													<form:radiobutton path="rateCharge" value="Y" />Yes
														<form:radiobutton path="rateCharge" value="N"
														checked="checked" />No
											 		</c:when>
												<c:otherwise>
													<form:radiobutton path="rateCharge" value="Y" />Yes
														<form:radiobutton path="rateCharge" value="N" />No
											 		</c:otherwise>
											</c:choose></td>
										<td><form:input path="rateChargeRemarks"
												id="rateChargeRemarks"
												value="${formData2.rateChargeRemarks}" readonly="false"
												class="form-control cus-control" /></td>
									</tr>
									<tr>
										<td>4</td>
										<td>Date of NPA as fed in the system is as per RBI
											guidelines.</td>
										<td><c:choose>
												<c:when test="${formData2.dateOfNPAAsRBI=='Y'}">
													<form:radiobutton path="dateOfNPAAsRBI" value="Y"
														checked="checked" />Yes
														<form:radiobutton path="dateOfNPAAsRBI" value="N" />No
											 		</c:when>
												<c:when test="${formData2.dateOfNPAAsRBI=='N'}">
													<form:radiobutton path="dateOfNPAAsRBI" value="Y" />Yes
														<form:radiobutton path="dateOfNPAAsRBI" value="N"
														checked="checked" />No
											 		</c:when>
												<c:otherwise>
													<form:radiobutton path="dateOfNPAAsRBI" value="Y" />Yes
														<form:radiobutton path="dateOfNPAAsRBI" value="N" />No
											 		</c:otherwise>
											</c:choose></td>
										<td><form:input path="dateOfNPAAsRBIRemarks"
												id="dateOfNPAAsRBIRemarks"
												value="${formData2.dateOfNPAAsRBIRemarks}" readonly="false"
												class="form-control cus-control" /></td>
									</tr>
									<tr>
										<td>5</td>
										<td>Whether outstanding amount mentioned in the claim
											application form is with respect to the NPA date as reported
											in the claim form.</td>
										<td><c:choose>
												<c:when test="${formData2.whetherOutstanding=='Y'}">
													<form:radiobutton path="whetherOutstanding" value="Y"
														checked="checked" />Yes
														<form:radiobutton path="whetherOutstanding" value="N" />No
											 		</c:when>
												<c:when test="${formData2.whetherOutstanding=='N'}">
													<form:radiobutton path="whetherOutstanding" value="Y" />Yes
														<form:radiobutton path="whetherOutstanding" value="N"
														checked="checked" />No
											 		</c:when>
												<c:otherwise>
													<form:radiobutton path="whetherOutstanding" value="Y" />Yes
														<form:radiobutton path="whetherOutstanding" value="N" />No
											 		</c:otherwise>
											</c:choose></td>
										<td><form:input path="whetherOutstandingRemarks"
												id="whetherOutstandingRemarks"
												value="${formData2.whetherOutstandingRemarks}"
												readonly="false" class="form-control cus-control" /></td>
									</tr>
									<tr>
										<td>6</td>
										<td>Whether serious deficiencies /irregularities observed
											in the matter of appraisal/renewal/disbursement/follow
											up/conduct of the account.</td>
										<td><c:choose>
												<c:when test="${formData2.apprisalDisbursement=='Y'}">
													<form:radiobutton path="apprisalDisbursement" value="Y"
														checked="checked" />Yes
														<form:radiobutton path="apprisalDisbursement" value="N" />No
											 		</c:when>
												<c:when test="${formData2.apprisalDisbursement=='N'}">
													<form:radiobutton path="apprisalDisbursement" value="Y" />Yes
														<form:radiobutton path="apprisalDisbursement" value="N"
														checked="checked" />No
											 		</c:when>
												<c:otherwise>
													<form:radiobutton path="apprisalDisbursement" value="Y" />Yes
														<form:radiobutton path="apprisalDisbursement" value="N" />No
											 		</c:otherwise>
											</c:choose></td>

										<td><form:input path="apprisalDisbursementRemarks"
												id="apprisalDisbursementRemarks"
												value="${formData2.apprisalDisbursementRemarks}"
												readonly="false" class="form-control cus-control" /></td>
									</tr>
									<tr>
										<td>7</td>
										<td>Major deficiencies observed in Pre-sanction/Post
											disbursement inspections.</td>
										<td><c:choose>
												<c:when test="${formData2.postDisbursement=='Y'}">
													<form:radiobutton path="postDisbursement" value="Y"
														checked="checked" />Yes
														<form:radiobutton path="postDisbursement" value="N" />No
											 		</c:when>
												<c:when test="${formData2.postDisbursement=='N'}">
													<form:radiobutton path="postDisbursement" value="Y" />Yes
														<form:radiobutton path="postDisbursement" value="N"
														checked="checked" />No
											 		</c:when>
												<c:otherwise>
													<form:radiobutton path="postDisbursement" value="Y" />Yes
														<form:radiobutton path="postDisbursement" value="N" />No
											 		</c:otherwise>
											</c:choose></td>
										<td><form:input path="postDisbursementRemarks"
												id="postDisbursementRemarks"
												value="${formData2.postDisbursementRemarks}"
												readonly="false" class="form-control cus-control" /></td>
									</tr>
									<tr>
										<td>8</td>
										<td>Whether deficiencies observed on part of internal
											staff as per Staff accountability exercise carried out.</td>
										<td><c:choose>
												<c:when test="${formData2.exerciseCarried=='Y'}">
													<form:radiobutton path="exerciseCarried" value="Y"
														checked="checked" />Yes
														<form:radiobutton path="exerciseCarried" value="N" />No
											 		</c:when>
												<c:when test="${formData2.exerciseCarried=='N'}">
													<form:radiobutton path="exerciseCarried" value="Y" />Yes
														<form:radiobutton path="exerciseCarried" value="N"
														checked="checked" />No
											 		</c:when>
												<c:otherwise>
													<form:radiobutton path="exerciseCarried" value="Y" />Yes
														<form:radiobutton path="exerciseCarried" value="N" />No
											 		</c:otherwise>
											</c:choose></td>
										<td><form:input path="exerciseCarriedRemarks"
												id="exerciseCarriedRemarks"
												value="${formData2.exerciseCarriedRemarks}" readonly="false"
												class="form-control cus-control" /></td>
									</tr>
									<tr>
										<td>9</td>
										<td>Internal rating was carried out and proposal was
											found of Investment Grade. (applicable for Loans sanctioned
											above 50 Lakh).</td>
										<td><c:choose>
												<c:when test="${formData2.internalRating=='Y'}">
													<form:radiobutton path="internalRating" value="Y"
														checked="checked" />Yes
														<form:radiobutton path="internalRating" value="N" />No
											 		</c:when>
												<c:when test="${formData2.internalRating=='N'}">
													<form:radiobutton path="internalRating" value="Y" />Yes
														<form:radiobutton path="internalRating" value="N"
														checked="checked" />No
											 		</c:when>
												<c:otherwise>
													<form:radiobutton path="internalRating" value="Y" />Yes
														<form:radiobutton path="internalRating" value="N" />No
											 		</c:otherwise>
											</c:choose></td>
										<td><form:input path="internalRatingRemarks"
												id="internalRatingRemarks"
												value="${formData2.internalRatingRemarks}" readonly="false"
												class="form-control cus-control" /></td>
									</tr>
									<tr>
										<td>10</td>
										<td>Whether all the recoveries pertaining to the account
											after the date of NPA and before the claim lodgement have
											been duly incorporated in the claim form.</td>
										<td><c:choose>
												<c:when test="${formData2.recoverPertaining=='Y'}">
													<form:radiobutton path="recoverPertaining" value="Y"
														checked="checked" />Yes
														<form:radiobutton path="recoverPertaining" value="N" />No
											 		</c:when>
												<c:when test="${formData2.recoverPertaining=='N'}">
													<form:radiobutton path="recoverPertaining" value="Y" />Yes
														<form:radiobutton path="recoverPertaining" value="N"
														checked="checked" />No
											 		</c:when>
												<c:otherwise>
													<form:radiobutton path="recoverPertaining" value="Y" />Yes
														<form:radiobutton path="recoverPertaining" value="N" />No
											 		</c:otherwise>
											</c:choose></td>
										<td><form:input path="recoverPertainingRemarks"
												id="recoverPertainingRemarks"
												value="${formData2.recoverPertainingRemarks}"
												style="width:500px; height: 50px;"
												class="form-control cus-control" /></td>

									</tr>

								</table>
							</div>

							<div class="clearfix"></div>
							<h5 class="sub-head">
								<strong> General Information: </strong>
							</h5>
							<hr style="margin: 5px 0; border: 1px solid #d8d8d8">

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>MLI's Comment on financial position of
											Borrower/Unit : <span style="color: red">*</span>
										</label> <label class="d-block"> <form:input
												path="financialPositionComments"
												id="financialPositionComments"
												value="${formData1.financialPositionComments}" size="20"
												class="form-control cus-control" />

										</label>

									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Details of Financial Assistance provided/being
											considered by MLI to minimize default : <span
											style="color: red">*</span>
										</label> <label class="d-block"> <form:input
												path="financialAssistanceDtls" id="financialAssistanceDtls"
												value="${formData1.financialAssistanceDtls}" size="20"
												class="form-control cus-control" /></label>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Does the MLI propose to provide credit support
											to Chief Promoter/Borrower for any other project :<span
											style="color: red">*</span>
										</label> <label class="d-block"> <c:choose>
												<c:when test="${formData1.creditSupport=='Y'}">
													<form:radiobutton path="creditSupport" value="Y"
														checked="checked" />Yes
														<form:radiobutton path="creditSupport" value="N" />No
											 		</c:when>
												<c:when test="${formData1.creditSupport=='N'}">
													<form:radiobutton path="creditSupport" value="Y" />Yes
														<form:radiobutton path="creditSupport" value="N"
														checked="checked" />No
											 		</c:when>
												<c:otherwise>
													<form:radiobutton path="creditSupport" value="Y" />Yes
														<form:radiobutton path="creditSupport" value="N" />No
											 		</c:otherwise>
											</c:choose>
										</label>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Details Of Bank Facility already provided to
											Borrower : <span style="color: red">*</span>:
										</label> <label class="d-block"> <form:input
												path="bankFacilityDtls" id="bankFacilityDtls"
												value="${formData1.bankFacilityDtls}" size="20"
												class="form-control cus-control" /></label>
									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Remarks :</label> <label class="d-block"> <form:input
												path="remarks" id="remarks" value="${formData1.remarks}"
												size="20" class="form-control cus-control" />
										</label>
									</div>
								</div>
							</div>
							<div align="left" id="successMsg">
								<font color="green" size="5">${message}</font>
							</div>
							<div align="left" id="error">
								<font color="red" size="5">${error}</font>
							</div>
							<div class="col-md-4 col-sm-4 col-xs-12 pt-20">
								<c:choose>

									<c:when test="${formData2.claimCheckListFlag=='Y'}">
										<span><form:checkbox path="claimCheckListFlag"
												id="claimCheckListFlag" value="Y" checked="checked" /><a
											style="padding: 5px;" href="claimLodgementCheckList.html">
												View CheckList </a></span>
									</c:when>
									<c:otherwise>
										<span><form:checkbox path="claimCheckListFlag"
												id="claimCheckListFlag" value="N" /><a
											style="padding: 5px;" href="claimLodgementCheckList.html">
												View CheckList </a></span>
									</c:otherwise>
								</c:choose>
								<c:choose>

									<c:when test="${formData1.claimLodgementCertificate=='Y'}">
										<form:checkbox path="claimLodgementCertificate"
											id="claimLodgementCertificate" value="Y" checked="checked" />
										<a style="padding: 5px;" href="claimLodgementCertificate.html">
											View Certificate </a>
									</c:when>
									<c:otherwise>
										<form:checkbox path="claimLodgementCertificate"
											id="claimLodgementCertificate" value="N" />
										<a style="padding: 5px;" href="claimLodgementCertificate.html">
											View Certificate </a>
									</c:otherwise>
								</c:choose>
								<input type="submit" value="Claim Lodgement"
									class="btn btn-reset" />
							</div>

						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>
