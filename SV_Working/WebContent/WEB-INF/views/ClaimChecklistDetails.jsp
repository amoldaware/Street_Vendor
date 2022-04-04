<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Management Certificate</title>
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css"
	rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<%
	String s = (String) request.getAttribute("SName");
%>
<script type="text/javascript">
	$(document).ready(function() {
		$(".form-control").removeClass("cus-control");
		$(".form-control").addClass("txtarea");
		// $("p:first").addClass("intro");
	});
</script>
<style>
.form-control.txtarea {
	min-height: 31px !important;
	height: 31px;
	width: 100%;
	resize: vertical;
	transition: all 0.5s;
	border-top: 0px;
	border-right: 0px;
	border-left: 0px;
	border-radius: 5px;
	/*     border: 1px solid #92c7e5; box-shadow: 1px 1px 2px #47a0d4d9; 	  border-color: #66afe970;}		blue shades*/
	border: 1px solid #d4d4d4 !important;
	box-shadow: 1px 2px 3px #be54c180; /* purple shade        
  /*  box-shadow: 1px 2px 3px #47a0d4;	box-shadow: 1px 1px 2px #47a0d4ba;*/
}

.form-control.txtarea:hover,.form-control.txtarea:focus {
	/*  box-shadow: 1px 3px 3px #66afe970; */
	/* box-shadow: 1px 3px 3px #47d4c0;  blue shades*/
	/*   background-color: #a641a936;  pruple shade1*/
	background-color: #fa61ff42;
	box-shadow: 1px 3px 3px #be54c1; /* purple shade */
}

input[type=radio] {
	margin: 4px 2px 0;
}
</style>
</head>
<body bgcolor="#E0FFFF">

	<div class="main-section">
	<%String userRole = (String) session.getAttribute("uRole"); 
	
		if (userRole.equals("CCHECKER")) {
						%>

		<nav aria-label="breadcrumb">
		<ol class="breadcrumb cus-breadcrumb">
			<li class="breadcrumb-item">
			<a href="/SV/claimLoadgementApprovalCGTMSE.html?MLIName=${MLIID}">claim Lodgement Approval</a></li>
			<li class="breadcrumb-item active" aria-current="page">Claim CheckList Details</li>
		</ol>
		</nav>
		<%}else if(userRole.equals("NCHECKER")) {%>
		<nav aria-label="breadcrumb">
		<ol class="breadcrumb cus-breadcrumb">
			<li class="breadcrumb-item"><a
				href="/SV/claimLoadgementApprovalMLI.html">claim Lodgement Approval</a></li>
			<li class="breadcrumb-item active" aria-current="page">Claim CheckList Details</li>
		</ol>
		</nav>
		<%}%>
	
	
	

		<div class="container-fluid">
			<div class="frm-section">
				<div class="col-md-12">
					<form:form action="saveClaimLodgementCheckListData.html"
						method="POST" class="table-responsive">
						
					
					<!-- <input type ="hidden" path="mliName" value="${MLIID}"/> -->	
						<table
							class="table table-bordered table-hover cus-table mt-10 mb-0">
							<tr>
								<th style="min-width: 70px;">Sr. No.</th>
								<th style="min-width: 700px;">Description</th>
								<th style="min-width: 90px;">Yes / No</th>
								<th style="min-width: 500px;">Comments/observations</th>
							</tr>
							<tr>
								<td>1</td>
								<td>Activity is eligible as per CGS-II.</td>
								<td><c:choose>
										<c:when test="${danDetailList.activityEligible=='Y'}">
									<form:radiobutton path="activityEligible" value="Y" checked="checked" />Yes
							        <form:radiobutton path="activityEligible" value="N"  disabled="true"/>No
											 		</c:when>
										<c:when test="${danDetailList.activityEligible=='N'}">
											<form:radiobutton path="activityEligible" value="Y"  disabled="true"/>Yes
														<form:radiobutton path="activityEligible" value="N"
												checked="checked" />No
											 		</c:when>
										<c:otherwise>
											<form:radiobutton path="activityEligible" value="Y"  disabled="true"/>Yes
														<form:radiobutton path="activityEligible" value="N"  disabled="true"/>No
											 		</c:otherwise>
									</c:choose></td>
								<td><form:input path="activityEligibleRemarks" id="activityEligibleRemarks" value="${danDetailList.activityEligibleRemarks}" size="20"
										readonly="true" class="form-control cus-control" /></td>

							</tr>
							<tr>
								<td>2</td>
								<td>Whether Credit bureau check done/CIR/KYC obtained and findings are
									satisfactory.</td>

								<td><c:choose>
										<c:when test="${danDetailList.cibil=='Y'}">
											<form:radiobutton path="cibil" value="Y" checked="checked" />Yes
														<form:radiobutton path="cibil" value="N"  disabled="true"/>No
											 		</c:when>
										<c:when test="${danDetailList.cibil=='N'}">
											<form:radiobutton path="cibil" value="Y"  disabled="true"/>Yes
														<form:radiobutton path="cibil" value="N" checked="checked" />No
											 		</c:when>
										<c:otherwise>
											<form:radiobutton path="cibil" value="Y"  disabled="true"/>Yes
														<form:radiobutton path="cibil" value="N"  disabled="true"/>No
											 		</c:otherwise>
									</c:choose></td>
								<td><form:input path="cibilRemarks" id="cibilRemarks"
										value="${danDetailList.cibilRemarks}" size="20"
										readonly="true" class="form-control cus-control" /></td>

							</tr>
							<tr>
								<td>3</td>
								<td>Rate charged on loan is as per RBI guidelines..</td>
								<td><c:choose>
										<c:when test="${danDetailList.rateCharge=='Y'}">
											<form:radiobutton path="rateCharge" value="Y"
												checked="checked" />Yes
														<form:radiobutton path="rateCharge" value="N"  disabled="true"/>No
											 		</c:when>
										<c:when test="${danDetailList.rateCharge=='N'}">
											<form:radiobutton path="rateCharge" value="Y" />Yes
														<form:radiobutton path="rateCharge" value="N"
												checked="checked" />No
											 		</c:when>
										<c:otherwise>
											<form:radiobutton path="rateCharge" value="Y" />Yes
														<form:radiobutton path="rateCharge" value="N"  disabled="true"/>No
											 		</c:otherwise>
									</c:choose></td>
								<td><form:input path="rateChargeRemarks"
										id="rateChargeRemarks"
										value="${danDetailList.rateChargeRemarks}" size="20"
										readonly="true" class="form-control cus-control" /></td>

							</tr>
							<tr>
								<td>4</td>
								<td>Date of NPA as fed in the system is as per RBI
									guidelines.</td>
								<td><c:choose>
										<c:when test="${danDetailList.dateOfNPAAsRBI=='Y'}">
											<form:radiobutton path="dateOfNPAAsRBI" value="Y"
												checked="checked" />Yes
														<form:radiobutton path="dateOfNPAAsRBI" value="N" disabled="true"/>No
											 		</c:when>
										<c:when test="${danDetailList.dateOfNPAAsRBI=='N'}">
											<form:radiobutton path="dateOfNPAAsRBI" value="Y" disabled="true"/>Yes
														<form:radiobutton path="dateOfNPAAsRBI" value="N"
												checked="checked" />No
											 		</c:when>
										<c:otherwise>
											<form:radiobutton path="dateOfNPAAsRBI" value="Y" disabled="true"/>Yes
														<form:radiobutton path="dateOfNPAAsRBI" value="N" disabled="true"/>No
											 		</c:otherwise>
									</c:choose></td>
								<td><form:input path="dateOfNPAAsRBIRemarks"
										id="dateOfNPAAsRBIRemarks"
										value="${danDetailList.dateOfNPAAsRBIRemarks}" size="20"
										readonly="true" class="form-control cus-control" /></td>

							</tr>
							<%-- <tr>
								<td>5</td>
								<td>Whether outstanding amount mentioned in the claim
									application form is with respect to the NPA date as reported in
									the claim form.</td>

								<td><c:choose>
										<c:when test="${danDetailList.whetherOutstanding=='Y'}">
											<form:radiobutton path="whetherOutstanding" value="Y"
												checked="checked" />Yes
														<form:radiobutton path="whetherOutstanding" value="N" disabled="true"/>No
											 		</c:when>
										<c:when test="${danDetailList.whetherOutstanding=='N'}">
											<form:radiobutton path="whetherOutstanding" value="Y" disabled="true"/>Yes
														<form:radiobutton path="whetherOutstanding" value="N"
												checked="checked" />No
											 		</c:when>
										<c:otherwise>
											<form:radiobutton path="whetherOutstanding" value="Y" disabled="true"/>Yes
														<form:radiobutton path="whetherOutstanding" value="N" disabled="true"/>No
											 		</c:otherwise>
									</c:choose></td>
								<td><form:input path="whetherOutstandingRemarks"
										id="whetherOutstandingRemarks"
										value="${danDetailList.whetherOutstandingRemarks}" size="20"
										readonly="true" class="form-control cus-control" /></td>


							</tr> --%>
							<tr>
								<td>5</td>
								<td>Whether serious deficiencies /irregularities observed
									in the matter of appraisal/renewal/disbursement/follow
									up/conduct of the account.</td>
								<td><c:choose>
										<c:when test="${danDetailList.apprisalDisbursement=='Y'}">
											<form:radiobutton path="apprisalDisbursement" value="Y"
												checked="checked" />Yes
														<form:radiobutton path="apprisalDisbursement" value="N" disabled="true"/>No
											 		</c:when>
										<c:when test="${danDetailList.apprisalDisbursement=='N'}">
											<form:radiobutton path="apprisalDisbursement" value="Y" disabled="true"/>Yes
														<form:radiobutton path="apprisalDisbursement" value="N"
												checked="checked" />No
											 		</c:when>
										<c:otherwise>
											<form:radiobutton path="apprisalDisbursement" value="Y" disabled="true"/>Yes
														<form:radiobutton path="apprisalDisbursement" value="N" disabled="true"/>No
											 		</c:otherwise>
									</c:choose></td>
								<td><form:input path="apprisalDisbursementRemarks"
										id="apprisalDisbursementRemarks"
										value="${danDetailList.apprisalDisbursementRemarks}" size="20"
										readonly="true" class="form-control cus-control" /></td>


							</tr>
							<tr>
								<td>6</td>
								<td>Major deficiencies observed in Pre-sanction/Post
									disbursement inspections.</td>
								<td><c:choose>
										<c:when test="${danDetailList.postDisbursement=='Y'}">
											<form:radiobutton path="postDisbursement" value="Y"
												checked="checked" />Yes
														<form:radiobutton path="postDisbursement" value="N" disabled="true"/>No
											 		</c:when>
										<c:when test="${danDetailList.postDisbursement=='N'}">
											<form:radiobutton path="postDisbursement" value="Y" disabled="true"/>Yes
														<form:radiobutton path="postDisbursement" value="N"
												checked="checked" />No
											 		</c:when>
										<c:otherwise>
											<form:radiobutton path="postDisbursement" value="Y" disabled="true"/>Yes
														<form:radiobutton path="postDisbursement" value="N" disabled="true"/>No
											 		</c:otherwise>
									</c:choose></td>
								<td><form:input path="postDisbursementRemarks"
										id="postDisbursementRemarks"
										value="${danDetailList.postDisbursementRemarks}" size="20"
										readonly="true" class="form-control cus-control" /></td>


							</tr>
							<tr>
								<td>7</td>
								<td>Whether deficiencies observed on part of internal staff
									as per Staff accountability exercise carried out.</td>

								<td><c:choose>
										<c:when test="${danDetailList.exerciseCarried=='Y'}">
											<form:radiobutton path="exerciseCarried" value="Y"
												checked="checked" />Yes
														<form:radiobutton path="exerciseCarried" value="N" disabled="true"/>No
											 		</c:when>
										<c:when test="${danDetailList.exerciseCarried=='N'}">
											<form:radiobutton path="exerciseCarried" value="Y" disabled="true"/>Yes
														<form:radiobutton path="exerciseCarried" value="N"
												checked="checked" />No
											 		</c:when>
										<c:otherwise>
											<form:radiobutton path="exerciseCarried" value="Y" disabled="true"/>Yes
														<form:radiobutton path="exerciseCarried" value="N" disabled="true"/>No
											 		</c:otherwise>
									</c:choose></td>
								<td><form:input path="exerciseCarriedRemarks"
										id="exerciseCarriedRemarks"
										value="${danDetailList.exerciseCarriedRemarks}" size="20"
										readonly="true" class="form-control cus-control" /></td>


							</tr>
							<tr>
								<td>8</td>
								<td>Internal rating was carried out and proposal was found
									of Investment Grade. (applicable for Loans sanctioned above 50
									Lakh).</td>

								<td><c:choose>
										<c:when test="${danDetailList.internalRating=='Y'}">
											<form:radiobutton path="internalRating" value="Y"
												checked="checked" />Yes
														<form:radiobutton path="internalRating" value="N" disabled="true"/>No
														<form:radiobutton path="internalRating" value="NA" disabled="true"/>NA
											 		</c:when>
										<c:when test="${danDetailList.internalRating=='N'}">
											<form:radiobutton path="internalRating" value="Y" disabled="true"/>Yes
														<form:radiobutton path="internalRating" value="N"
												checked="checked" />No
												<form:radiobutton path="internalRating" value="NA" disabled="true"/>NA
											 		</c:when>
											 		<c:when test="${danDetailList.internalRating=='NA'}">
											<form:radiobutton path="internalRating" value="Y"
												disabled="true" />Yes
														<form:radiobutton path="internalRating" value="N" disabled="true"/>No
														<form:radiobutton path="internalRating" value="NA" checked="checked"/>NA
											 		</c:when>
										<c:otherwise>
											<form:radiobutton path="internalRating" value="Y" disabled="true"/>Yes
														<form:radiobutton path="internalRating" value="N" disabled="true"/>No
														<form:radiobutton path="internalRating" value="NA" disabled="true"/>NA
											 		</c:otherwise>
									</c:choose></td>
								<td><form:input path="internalRatingRemarks"
										id="internalRatingRemarks"
										value="${danDetailList.internalRatingRemarks}" size="20"
										readonly="true" class="form-control cus-control" /></td>



							</tr>
							<tr>
								<td>9</td>
								<td>Whether all the recoveries pertaining to the account
									after the date of NPA and before the claim lodgement have been
									duly incorporated in the claim form.</td>
									
									<td><c:choose>
										<c:when test="${danDetailList.recoverPertaining=='Y'}">
											<form:radiobutton path="recoverPertaining" value="Y"
												checked="checked" />Yes
														<form:radiobutton path="recoverPertaining" value="N" disabled="true"/>No
											 		</c:when>
										<c:when test="${danDetailList.recoverPertaining=='N'}">
											<form:radiobutton path="recoverPertaining" value="Y" disabled="true"/>Yes
														<form:radiobutton path="recoverPertaining" value="N"
												checked="checked" />No
											 		</c:when>
										<c:otherwise>
											<form:radiobutton path="recoverPertaining" value="Y" disabled="true"/>Yes
														<form:radiobutton path="recoverPertaining" value="N" disabled="true"/>No
											 		</c:otherwise>
									</c:choose></td>
								<td><form:input path="recoverPertainingRemarks"
										id="recoverPertainingRemarks"
										value="${danDetailList.recoverPertainingRemarks}" size="20"
										readonly="true" class="form-control cus-control" /></td>

							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								
							</tr>
						</table>
					</form:form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>