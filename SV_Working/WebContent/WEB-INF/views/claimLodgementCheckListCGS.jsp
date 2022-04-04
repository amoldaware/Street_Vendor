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
.Decl-msg{	width:100%; display:block;	}
.Decl-msg h4{font-size: 14px;}
	.Decl-msg h2{	font-size: 16px; margin: 10px 0;	color: #da294d;	}
	.Decl-msg h3:first-child {    font-size: 22px; margin: 5px 0; padding:0 0 15px;  position:relative;}
	.Decl-msg h3:first-child  a{ color:#da294d !important;}
	.Decl-msg h3:first-child:after{	content:'';	position:absolute;	width: 150px;
   	 bottom: 0px;   height: 2px;  left: 44%; background-color:gray; }	
	.Decl-msg h3:nth-child(2){	    font-size: 16px; margin: 6px 0; font-style:italic;	}
	.Decl-msg p{ font-size: 14px; margin: 6px 0;     }
	.under_msg{	width:100%; display:block;	border-bottom: 1px dotted gray;	}
	.under_msg  h2{	font-size: 20px; font-weight: 500; padding: 15px 0 15px; margin: 0 0; position:relative; 	}
	.under_msg  h2:after{	content:'';	position:absolute;	width: 150px;
    bottom: 0px;   height: 2px;  left: 44%; background-color:gray; }
 	 ol.under_list{	width:100%; display:block;	}
	 ol.under_list li{	font-size: 14px; padding:3px 0;	}
	.sign_details{width:100%; display:block;  padding-top: 15px;}
	.sign_details h4{	font-size:16px;font-style: italic;  font-weight: 700; margin:5px 0;	}
	.sign_details p{  	font-size:16px; }
	.decl-form{margin-top:20px; padding:15px 10px; border:1px solid lightgray; border-radius:5px; 
	box-shadow:1px 3px 5px rgba(0,0,0,0.2); float: left;}
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
			<a href="/SV/claimLoadgementApprovalCGTMSE.html?MLIName=${MLIID}&CNT=${CNT}">claimLoadgement
					Approval</a></li>
			<li class="breadcrumb-item active" aria-current="page">Claim CheckList Details</li>
		</ol>
		</nav>
		<%}else if(userRole.equals("NCHECKER")) {%>
		<nav aria-label="breadcrumb">
		<ol class="breadcrumb cus-breadcrumb">
			<li class="breadcrumb-item"><a
				href="/SV/claimLoadgementApprovalMLI.html">claimLodgement
					Approval</a></li>
			<li class="breadcrumb-item active" aria-current="page">Claim CheckList Details</li>
		</ol>
		</nav>
		<%}%>
	
	
	

		<div class="container-fluid">
			<div class="frm-section">
			
			
				
					<form:form action="saveClaimLodgementCheckListData.html"
						method="POST" class="table-responsive">
						<div class="col-md-12">
						<table
							class="table table-bordered table-hover cus-table mt-10 mb-0">
							<tr>
								<th style="min-width: 70px;">Sr. No.</th>
								<th style="min-width: 70px;">Claim Ref. No.</th>
								<th style="min-width: 90px;">CGPAN</th>
								<th style="min-width: 50px;">Unit Name</th>
								<th style="min-width: 50px;">Approved Amount</th>
							</tr>
							<tr>
							<td>1</td>
							<td><c:out value="${claimDetailList.claimRefNo}"/></td>
						
							<td><c:out value="${claimDetailList.cgpan}"/></td>
								<td><c:out value="${claimDetailList.nameOfBorrower}"/></td>
							<td><c:out value="${AMOUNT}"/></td>
							</tr>
							
							</table>
					
					<!-- <input type ="hidden" path="mliName" value="${MLIID}"/> -->	
						<table
							class="table table-bordered table-hover cus-table mt-10 mb-0">
							<tr>
								<th style="max-width: 70px;">Sr. No.</th>
								<th style="max-width: 700px;">Description</th>
								<th style="max-width: 90px;">Yes / No</th>
								<th style="max-width: 500px;">Comments/observations</th>
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
						 
						</table>
							</div>					
												
					<div class="col-md-12 col-xs-12">
					<div class="decl-form">
						<div class="Decl-msg">
							<h3 class="text-center "><a href="#">Declaration and Undertaking by MLI (NBFCs)</a></h3>
						<!-- 	<h3 class="text-center">(To be signed by the officer not below the rank of Assistant General Manager or of equivalent rank of NBFC ).</h3> 	 -->					
							<p class=" text-center pb-6">
								<strong> Declaration :</strong> We declare that the information given above is true and correct in every respect. 
								We further declare that there has been no fault or negligence on  the part of the MLI or any of its officers in conducting the account. 
								We also declare that the officer preferring the claim on behalf of MLI is having the authority to do so.
							</p>						
							<h4 class="text-center">
								We hereby declare that no fault or negligence has been pointed out by internal / external auditors, inspectors of CGTMSE or its agency in respect of the account(s) for which claim is being preferred.
								
							</h4>
						</div> 
						<div class="under_msg">
							<h4 class=" text-center" ><b>Undertaking- We hereby undertake:</b> </h4>
							<ol class="under_list">
								<li>To pursue all recovery steps including legal proceedings.</li>
								<li>	To report to CGTMSE the position of outstanding dues from the borrower on half-yearly basis (as on 31st March and 30th September of 
									each year till final settlement of guarantee claim by CGTMSE).</li>
								<li>To refund to CGTMSE the claim amount settled along with interest, if in the view of CGTMSE we fail or neglect to take any action for 
									recovery of the guaranteed debt from the borrower or any other person from whom the amount is to be recovered.</li>
								<li>On payment of claim by CGTMSE, to remit to CGTMSE all such recoveries, after adjusting towards the legal expenses incurred for recovery 
									of the amount, which we or our agents acting on our behalf, may make from the person or persons responsible for the administration of debt, or otherwise, in respect of the debt due from him / them to us.</li>
							 </ol>												
						</div>
						<div class="sign_details" style="border-bottom:1px dotted gray; margin-bottom:15px;">									
							<ol class="under_list">
								 <li>	CGTMSE reserves the right to ask for any additional information, if required.</li>
								 <li>	GTMSE reserves the right to initiate any appropriate action / appoint any person / institution etc. 
									 	to verify the facts as mentioned above and if found contrary to the declaration, reserves the right to treat the claim under CGTMSE invalid.
								</li>
							</ol>
							</div>									
						
						
						<div class="col-md-2 col-sm-4 col-xs-12 prl-10">
								<div class="form-group">
									
							 	
										<label>Name of Official:</label>
										<form:input path="userName" id="userName" value="${claimDetailList1.userName}" size="20" readonly="true" class="form-control cus-control" /> 
										 
									
								</div>
							</div>
							<div class="col-md-2 col-sm-4 col-xs-12 prl-10">
								<div class="form-group">
									
										<label>MLI(NBFC) Name:</label>
										 <form:input path="mliName" id="mliName" value="${MLIID}" size="20" readonly="true" class="form-control cus-control" /> 
									 
								</div>
							</div>
							<div class="col-md-2 col-sm-4 col-xs-12 prl-10">
								<div class="form-group">
									
										<label>Member Id:</label>
										<form:input path="mliID" id="mliID" value="${claimDetailList1.mliID}" size="20" readonly="true" class="form-control cus-control" /> 
									
								</div>
							</div>
							<div class="col-md-2 col-sm-4 col-xs-12 prl-10">
								<div class="form-group">
									
										<label>Date Of Claim Submission:</label>
										<form:input path="dateOfClaimLoadge" id="dateOfClaimLoadge" value="${claimDetailList1.dateOfClaimLoadge}" size="20" readonly="true" class="form-control cus-control" /> 
									
								</div>
							</div>
							</div>  </div>
					</form:form>
				</div>
			</div>
		</div>
	

</body>
</html>