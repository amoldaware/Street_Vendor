<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
		<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">		
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<%
			String s = (String) request.getAttribute("SName");
		%>
		<script type="text/javascript">
			$(document).ready(function(){
				$(".form-control").removeClass("cus-control");
				$(".form-control").addClass("txtarea");
				// $("p:first").addClass("intro");
			});
		</script>
 <style>
	.form-control.txtarea{ 		min-height:31px !important; height:31px; width:100%; resize:vertical;
	   	transition: all 0.5s;   border-top: 0px;  border-right: 0px;
    border-left: 0px;  border-radius: 5px;
    /*     border: 1px solid #92c7e5; box-shadow: 1px 1px 2px #47a0d4d9; 	  border-color: #66afe970;}		blue shades*/       
    border: 1px solid #d4d4d4 !important; box-shadow: 1px 2px 3px #be54c180;	/* purple shade        
  /*  box-shadow: 1px 2px 3px #47a0d4;	box-shadow: 1px 1px 2px #47a0d4ba;*/		}
.form-control.txtarea:hover, .form-control.txtarea:focus {    /*  box-shadow: 1px 3px 3px #66afe970; */
    /* box-shadow: 1px 3px 3px #47d4c0;  blue shades*/		  /*   background-color: #a641a936;  pruple shade1*/
    background-color: #fa61ff42;  box-shadow: 1px 3px 3px #be54c1; /* purple shade */}   
    input[type=radio]{margin: 4px 2px 0;}
</style>
	</head>
	<body bgcolor="#E0FFFF">
		
<div class="main-section">
	<div class="container-fluid"> 
	 	<div class="frm-section">
			<div class="col-md-12">
				<form:form  action="saveClaimLodgementCheckListData.html" method="POST" class="table-responsive" >
				<table class="table table-bordered table-hover cus-table mt-10 mb-0">
					<tr>
						<th style="min-width: 70px;">Sr. No.</th>
						<th style="min-width: 700px;">Description</th>
						<th style="min-width: 90px;">Yes / No</th>
						<th style="min-width: 500px;">Comments/observations</th>
					</tr>
						 <tr>
							<td>1</td>
							<td>Activity is eligible as per Credit guarantee Scheme.</td>
							<td><form:radiobutton path="activityEligible" value="Y" />Yes<form:radiobutton path="activityEligible" value="N" />No</td>
							<td><form:textarea path="activityEligibleRemarks" id="activityEligibleRemarks" value="" size="20"  readonly="false" class="form-control cus-control"/></td>
						</tr>
						<tr>
							<td>2</td>
							<td>Whether CIBIL done/CIR/KYC obtained and findings are satisfactory.</td>
							<td><form:radiobutton path="cibil" value="Y" />Yes<form:radiobutton path="cibil" value="N" />No</td>
							<td><form:textarea path="cibilRemarks" id="cibilRemarks" value="" size="20"  readonly="false" class="form-control cus-control"/></td>
						</tr>
						<tr>
							<td>3</td>
							<td>Rate charged on loan is as per CGS guidelines..</td>
							<td><form:radiobutton path="rateCharge" value="Y" />Yes<form:radiobutton path="rateCharge" value="N" />No</td>
							<td><form:textarea path="rateChargeRemarks" id="rateChargeRemarks" value="" size="20"  readonly="false" class="form-control cus-control"/></td>
						</tr> 
						<tr>
							<td>4</td>
							<td>Date of NPA as fed in the system is as per RBI guidelines.</td>
							<td><form:radiobutton path="dateOfNPAAsRBI" value="Y" />Yes<form:radiobutton path="dateOfNPAAsRBI" value="N" />No</td>
							<td><form:textarea path="dateOfNPAAsRBIRemarks" id="dateOfNPAAsRBIRemarks" value="" size="20"  readonly="false" class="form-control cus-control"/></td>
						</tr> 
						<tr>
							<td>5</td>
							<td>Whether outstanding amount mentioned in the claim application form is with respect to the NPA date as reported in the claim form.</td>
							<td><form:radiobutton path="whetherOutstanding" value="Y" />Yes<form:radiobutton path="whetherOutstanding" value="N" />No</td>
							<td><form:textarea path="whetherOutstandingRemarks" id="whetherOutstandingRemarks" value="" size="20"  readonly="false" class="form-control cus-control"/></td>
						</tr>
						<tr>
							<td>6</td>
							<td>Whether serious deficiencies /irregularities observed in the matter of appraisal/renewal/disbursement/follow up/conduct of the account.</td>
							<td><form:radiobutton path="apprisalDisbursement" value="Y" />Yes<form:radiobutton path="apprisalDisbursement" value="N" />No</td>
							<td><form:textarea path="apprisalDisbursementRemarks" id="apprisalDisbursementRemarks" value="" size="20"  readonly="false" class="form-control cus-control"/></td>
						</tr> 
						<tr>
							<td>7</td>
							<td>Major deficiencies observed in Pre-sanction/Post disbursement inspections.</td>
							<td><form:radiobutton path="postDisbursement" value="Y" />Yes<form:radiobutton path="postDisbursement" value="N" />No</td>
							<td><form:textarea path="postDisbursementRemarks" id="postDisbursementRemarks" value="" size="20"  readonly="false" class="form-control cus-control"/></td>
						</tr>
						 <tr>
							<td>8</td>
							<td>Whether deficiencies observed on part of internal staff as per Staff accountability exercise carried out.</td>
							<td><form:radiobutton path="exerciseCarried" value="Y" />Yes<form:radiobutton path="exerciseCarried" value="N" />No</td>
							<td><form:textarea path="exerciseCarriedRemarks" id="exerciseCarriedRemarks" value="" size="20"  readonly="false" class="form-control cus-control"/></td>
						</tr> 
						<tr>
							<td>9</td>
							<td>Internal rating was carried out and proposal was found of Investment Grade. (applicable for Loans sanctioned above 50 Lakh).</td>
							<td><form:radiobutton path="internalRating" value="Y" />Yes<form:radiobutton path="internalRating" value="N" />No</td>
							<td><form:textarea path="internalRatingRemarks" id="internalRatingRemarks" value="" size="20"  readonly="false" class="form-control cus-control"/></td>
						</tr>
						<tr>
							<td>10</td>
							<td>Whether all the recoveries pertaining to the account after the date of NPA and before the claim lodgement have been duly incorporated in the claim form. </td>
							<td><form:radiobutton path="recoverPertaining" value="Y" />Yes<form:radiobutton path="recoverPertaining" value="N" />No</td>
							<td><form:textarea path="recoverPertainingRemarks" id="recoverPertainingRemarks" value=""  rows="10" cols="200"  style="width:500px; height: 50px;" class="form-control cus-control"/></td>
							
						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><div class="d-inlineblock" align="center"><input type="submit" value="save"  class="btn btn-reset" /></div></td>
						</tr> 		
						</table>
					 </form:form>
				 </div>
			 </div> 
		</div>
	</div>
		
	</body>
</html>