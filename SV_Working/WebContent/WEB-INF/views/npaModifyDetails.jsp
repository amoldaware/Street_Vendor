<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<link href="<%=request.getContextPath()%>/js/jquery-ui.css"
	rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<!--<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>-->
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css"
	rel="stylesheet" type="text/css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
	$(function() {
		$("#txt").datepicker({
			dateFormat : 'dd/mm/yy'
		});

	});


	$(function() {
		$("#lastDisbDt1").datepicker({
			// showAnim : "fold"
			dateFormat : "dd/mm/yy"
		});
	});
	$(function() {
		$("#npaDt").datepicker({
			// showAnim : "fold"
			dateFormat : "dd/mm/yy"
		});
	});

	$(function() {
		$("#subsidyLastRcvdDt").datepicker({
			// showAnim : "fold"
			dateFormat : "dd/mm/yy"
		});
	});

	$(function() {
		$("#firstInstDt1").datepicker({
			// showAnim : "fold"
			dateFormat : "dd/mm/yy"
		});
	});
	$(function() {
		$("#lasInspDt").datepicker({
			// showAnim : "fold"
			dateFormat : "dd/mm/yy"
		});
	});
</script>

<script type="text/javascript">
window.onload = function() {
	checkflag();
	}
	
	function checkflag(){
		var cboxes = (document.getElementsByName("chk")[0].value);	
		//  var len = cboxes.length;
		  alert(cboxes);
		   // for (var i=0; i<len; i++) {
		   //     alert(i + (cboxes[i].checked?' checked ':' unchecked ') + cboxes[i].value);
		  //  }
	}
</script>
<style>
h2.a {
  visibility: visible;
}

h2.b {
  visibility: hidden;
}
</style>

</head>
<body>
	<%
		String uesrName = (String) session.getAttribute("userId");
		if (uesrName == null) {
			response.sendRedirect("login");
		}
		System.out.println("uesrName....." + uesrName);
	%>


	<div class="main-section">
		 <nav aria-label="breadcrumb">
   <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/NPADetails.html">NPA Details</a></li>  
    <li class="breadcrumb-item active" aria-current="page">Modify NPA Details</li>
  </ol> 
</nav>
		<div class="container-fluid">
			<!-- <div class="row"> -->
			<div>
				<div class="frm-section">
					<div class="col-md-12">
						<h5 class="error1 mtb-0">
							<strong>${message}</strong>
						</h5>

						<div id="successMsgId">
							<h5 class="notification-message">
								<strong>${SuccessMsg} ${showPortfolioNoKey} </strong>
							</h5>
						</div>

						<form:form method="POST" action="npaMark.html" id="A"
							class="form-horizontal">
						<h2 class="b"><form:input path="intrestRate" id="intrestRate" value="${npaDetailsBean.intrestRate}" /></h2>
							
							<h5 class="sub-head">
								<strong> General Details</strong>
							</h5>
							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>CGPAN:<span style="color: red">*</span></label>
										<form:input path="CGPAN" value="${npaDetailsBean.CGPAN}"
											size="20" readonly="true" class="form-control cus-control"
											id="CGPAN" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Guarantee Start Date :<span style="color: red">*</span></label>
										<form:input path="guarStartDt1"
											value="${npaDetailsBean.guarStartDt1}"
											class="date-picker form-control cus-control" size="28"
											placeholder="e.g:dd-mm-yyyy" readonly="true" />
										<form:errors path="guarStartDt1" cssClass="error" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Sanction Date:<span style="color: red">*</span></label>
										<form:input path="sanctionDt1"
											value="${npaDetailsBean.sanctionDt1}"
											class="date-picker form-control cus-control" size="28"
											placeholder="e.g:dd-mm-yyyy" readonly="true" />
										<form:errors path="sanctionDt1" cssClass="error" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>First Disbursement Date :<span
											style="color: red">*</span></label>
										<form:input path="firstDisbDt1"
											value="${npaDetailsBean.firstDisbDt1}"
											class="date-picker form-control cus-control" size="28"
											placeholder="e.g:dd-mm-yyyy" />
										<form:errors path="firstDisbDt1" cssClass="error" />
									</div>
								</div>
							</div>


							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>First Installment Date :</label>
										<form:input path="firstInstDt1"
											value="${npaDetailsBean.firstInstDt1}"
											class="date-picker form-control cus-control" size="28"
											 placeholder="e.g:dd/mm/yyyy" />
										<form:errors path="firstInstDt1" cssClass="error" />
									</div>
								</div>
							</div>

							<div class="clearfix"></div>
							<hr style="margin: 0; border: 1px solid #d8d8d8">
							<h5 class="sub-head">
								<strong> NPA Details: </strong>
							</h5>

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>NPA Date : <span style="color: red">*</span></label>
										<form:input path="npaDt" value="${npaDetailsBean.npaDt}"
											class="date-picker form-control cus-control" size="28"
											 placeholder="e.g:dd/mm/yyyy" />
										<form:errors path="npaDt" cssClass="error" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label class="d-block"> <c:if
												test="${npaDetailsBean.isAsPerRBI=='Y'}">
												<input type="checkbox" id="isAsPerRBI" value="${npaDetailsBean.isAsPerRBI}" checked>
											</c:if> <c:if test="${npaDetailsBean.isAsPerRBI=='N'}">
												<input type="checkbox" id="isAsPerRBI" value="${npaDetailsBean.isAsPerRBI}">
											</c:if> Is the NPA date as per the RBI Guidelines:<span
											style="color: red">*</span></label>

										<form:errors path="isAsPerRBI" cssClass="error"
											readonly="true" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-5">
										<label class="d-block">NPA Reason:<span
											style="color: red">*</span></label>

										<form:select path="npaReason" class="form-control cus-control"
											id="npaReason">
											<form:option value="${npaDetailsBean.npaReason}" />
											<c:forEach var="value" items="${npaResion}">
												<form:option id="${value}" value="${value}">${value}</form:option>
											</c:forEach>
										</form:select>
										<form:errors path="npaReason" cssClass="error" />

									</div>
								</div>
							</div>


							<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-5">
										<label class="d-block">Last Unit Visit Date : </label>
										<form:input path="lasInspDt"
											value="${npaDetailsBean.lasInspDt}"
											class="date-picker form-control cus-control" id="lasInspDt"
											 placeholder="e.g:dd/mm/yyyy" />

										<form:errors path="lasInspDt" cssClass="error" />

									</div>
								</div>
							</div>

							<div class="clearfix"></div>
							<hr style="margin: 0; border: 1px solid #d8d8d8">
							<h5 class="sub-head">
								<strong> </strong>
							</h5>

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-5">
										<label class="d-block">Total Guaranteed Amount:<span
											style="color: red">*</span>
										</label>
										<form:input path="totalGuaranteeAmt" size="20"
											value="${npaDetailsBean.totalGuaranteeAmt}"
											class="form-control cus-control" style="text-align: right" readonly="true"
											id="totalGuaranteeAmt" />
										<form:errors path="totalDisbAmt1Error" cssClass="error" />
									</div>
								</div>
							</div>
							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-5">
										<label class="d-block" style="font-size: 11px">Latest
											O/S Guaranteed Amt:<span style="color: red">*</span> <!--  Principle in Rs.:<span
											style="color: red">*</span> -->
										</label>
										<form:input path="latestOsAmt" size="20"
											value="${npaDetailsBean.latestOsAmt}"
											class="form-control cus-control" id="latestOsAmt" readonly="true"
											/>
										<form:errors path="repayPrincipalError" cssClass="error" />
									</div>
								</div>
							</div>



							<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-5">

										<label class="d-block" style="font-size: 11px">Principal
											Outstanding Amt(As on NPA Date):<span style="color: red">*</span>
										</label>
										<form:input path="outstandingPrincipal1" size="20"
											value="${npaDetailsBean.outstandingPrincipal1}"
											class="form-control cus-control" id="outstandingPrincipal1"
											 onchange="calOutstanding(this);" />
										<form:errors path="outstandingPrincipal1Error"
											cssClass="error" />
										<p id="outstandingPrincipal1Error1"
											Class="displayErrorMessageInRedColor"></p>

										<p id="outstandingPrincipal1Error"
											Class="displayErrorMessageInRedColor"></p>
									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-5">
										<label class="d-block" style="font-size: 11px">Interest
											Outstanding as on the date of NPA: <span style="color: red">*</span>
										</label>
										<form:input path="outstandingInterest1" size="20"
											value="${npaDetailsBean.outstandingInterest1}"
											class="form-control cus-control" id="outstandingInterest1"
											onchange="calOutstanding(this);" />
										<form:errors path="outstandingInterest1Error" cssClass="error" />
										<p id="outstandingInterest1Error"
											Class="displayErrorMessageInRedColor"></p>
									</div>
								</div>
							</div>
							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-5">
										<label class="d-block" style="font-size: 11px">Total
											OutstandingS Amt(As On NPA): <span style="color: red">*</span>
										</label>
										<form:input path="claimEligibityAmt" size="20"
											value="${npaDetailsBean.claimEligibityAmt}"
											class="form-control cus-control" id="claimEligibityAmt"
											/>
										<form:errors path="claimEligibityAmtError" cssClass="error" />
									</div>
								</div>
							</div>
							<!--  	<h5 class="error1">
								<strong>Note: </strong> The Outstanding Amount for Term Loan as
								on Date of NPA Has Been Derived By Deducting Principal Repayment
								From Total Disbursement/Guarantee Amount Whichever is Lower.
							</h5>
-->
							<div class="clearfix"></div>
							<hr style="margin: 0; border: 1px solid #d8d8d8">
							<h5 class="sub-head">
								<strong>Primary Security Details:</strong>
							</h5>
							<table class="table table-bordered table-hover cus-table mb-0">
								<thead>
									<tr>
										<th>Particulars</th>
										<th>Security Nature Value <br />(in &#8377;)
										</th>
										<th>Networth of Guarantor/Promoter(in &#8377;):</th>
										<th>Reasons for Reduction in the value of Security, if
											any:</th>
									</tr>
								</thead>
								<tr>
								<td><label class="d-block">As on Date of Sanction
											of Credit</label></td>
								<td><label class="d-block">Land:</label>
									<form:input type="number" path="landValue" size="10" style="text-align: right" id="landValue" value="${npaDetailsBean.landValue}" class="form-control cus-control" onchange="caltotalSecurity(this);" min="0" oninput="validity.valid||(value='');"/>
									<p id="landValueerror" Class="displayErrorMessageInRedColor"></p></br>
									
								    <label class="d-block">Building:</label>
								    <form:input type="number" path="buildingValue" size="10" style="text-align: right" id="buildingValue" value="${npaDetailsBean.buildingValue}"  class="form-control cus-control" onchange="caltotalSecurity(this);" oninput="validity.valid||(value='');"/></br>
								    
								    <label class="d-block">Plant and Machinery/Equipments:</label> 
								    <form:input type="number" path="planetValue" size="10" style="text-align: right" id="planetValue" value="${npaDetailsBean.planetValue}" class="form-control cus-control" onchange="caltotalSecurity(this);" oninput="validity.valid||(value='');"/></br>
								    
								    <label class="d-block"> Other Fixed/Movable Assets:</label>
								    <form:input type="number" path="otherAssetValue" size="10" style="text-align: right" id="otherAssetValue" value="${npaDetailsBean.otherAssetValue}" class="form-control cus-control" onchange="caltotalSecurity(this);" oninput="validity.valid||(value='');"/></br>
								    
								    <label class="d-block"> Current Assets:</label>
								    <form:input type="number" path="currentAssetValue" size="10" style="text-align: right" id="currentAssetValue" value="${npaDetailsBean.currentAssetValue}" class="form-control cus-control" onchange="caltotalSecurity(this);" oninput="validity.valid||(value='');"/></br> 
								    
								    <label class="d-block"> Others:</label> 
								    <form:input type="number" path="othersValue" size="10" style="text-align: right" id="othersValue" value="${npaDetailsBean.othersValue}" class="form-control cus-control" onchange="caltotalSecurity(this);" oninput="validity.valid||(value='');"/></br></td>
								    
									<td><form:input type="number" path="networthOfPromotor" size="10" style="text-align: right" value="${npaDetailsBean.networthOfPromotor}" class="form-control cus-control" id="networthOfPromotor" oninput="validity.valid||(value='');"/></td>
									
									<td><form:select  path="reductionReason" id="reductionReason" readonly="true" class="form-control cus-control" disabled="true">
										<form:option value="" label="----------Select---------" />
										<c:forEach var="value" items="${npaResion}"> 
												<form:option id="${value}" value="${value}">${value}</form:option>
										</c:forEach>

									</form:select></span></td>


								</tr>
								<tr>
									<td></td>
									<td><label class="d-block">Total:</label> 
									<form:input type="number" path="totalSecuritydetails" size="10" style="text-align: right" value="${npaDetailsBean.totalSecuritydetails}" class="form-control cus-control" id="totalSecuritydetails" oninput="validity.valid||(value='');"/></td>
									<td><form:errors path="totalSecuritydetailsError" cssClass="error" /></td>
									<td></td>
								</tr>
								<tr>
									<td><label class="d-block">As on Date of NPA:</label></td>
									
									<td><label class="d-block">Land:</label> <form:input type="number" path="landValue1" size="10" style="text-align: right" id="landValue1" onchange="caltotalSecurity1(this);" value="${npaDetailsBean.landValue1}" class="form-control cus-control" oninput="validity.valid||(value='');"/></br> 
										<label class="d-block">Building:</label> 
										<form:input type="number" path="buildingValue1" size="10" style="text-align: right" id="buildingValue1" onchange="caltotalSecurity1(this);" value="${npaDetailsBean.buildingValue1}" class="form-control cus-control" oninput="validity.valid||(value='');"/></br>
										
										<label class="d-block"> Plant and Machinery/Equipments:</label>
										<form:input type="number" path="planetValue1" size="10" style="text-align: right" id="planetValue1" onchange="caltotalSecurity1(this);" value="${npaDetailsBean.planetValue}" class="form-control cus-control" oninput="validity.valid||(value='');"/></br>
										
										<label class="d-block"> Other Fixed/Movable Assets:</label>
									    <form:input type="number" path="otherAssetValue1" size="10" style="text-align: right" id="otherAssetValue1" onchange="caltotalSecurity1(this);" value="${npaDetailsBean.otherAssetValue}" class="form-control cus-control" oninput="validity.valid||(value='');"/></br>
									    
									    <label class="d-block"> Current Assets:</label> 
									    <form:input type="number" path="currentAssetValue1" size="10" style="text-align: right" id="currentAssetValue1" onchange="caltotalSecurity1(this);" value="${npaDetailsBean.currentAssetValue}" class="form-control cus-control" oninput="validity.valid||(value='');"/></br>
									    
										<label class="d-block"> Others:</label>
										<form:input type="number" path="othersValue1" size="20" style="text-align: right" id="othersValue1"  onchange="caltotalSecurity1(this);" value="${npaDetailsBean.othersValue}" class="form-control cus-control" oninput="validity.valid||(value='');"/></br></td>
										 
									    <td><form:input type="number" path="networthOfPromotor1" size="20" style="text-align: right" id="networthOfPromotor1" value="${npaDetailsBean.networthOfPromotor}" class="form-control cus-control" oninput="validity.valid||(value='');"/></td>
									    
									    <td><form:select path="reductionReason1" id="reductionReason1" class="form-control cus-control">
											<form:option value="" label="----------Select---------" />
											<c:forEach var="value" items="${npaResion}">
												<form:option id="${value}" value="${value}">${value}</form:option>
											</c:forEach>

										</form:select></span></td>
								</tr>
								<tr>
									<td></td>
									<td><label class="d-block"> Total:</label>
									<form:input type="number" path="totalSecuritydetails1" size="20" style="text-align: right" id="totalSecuritydetails1" value="${npaDetailsBean.totalSecuritydetails1}" class="form-control cus-control" oninput="validity.valid||(value='');"/></td>
									<td><form:errors path="totalSecuritydetails1Error" cssClass="error" /></td>
									<td></td>
								</tr>
							</table>
								
					
									<!-- <td><label class="d-block">As on Date of Sanction
											of Credit</label></td>
									<td><label class="d-block">Land:</label> <form:input
											path="landValue" size="20" style="text-align: right"
											value="${npaDetailsBean.landValue}" id="landValue"
											class="form-control cus-control"
											onchange="caltotalSecurity(this);" /></br> <label class="d-block">Building:</label>
										<form:input path="buildingValue" size="10"
											style="text-align: right" id="buildingValue"
											value="${npaDetailsBean.buildingValue}" 
											class="form-control cus-control"
											onchange="caltotalSecurity(this);" /></br> <label class="d-block">Plant
											and Machinery/Equipments:</label> <form:input path="planetValue" size="10"
											style="text-align: right" id="planetValue"
											value="${npaDetailsBean.planetValue}"
											class="form-control cus-control"
											onchange="caltotalSecurity(this);" /></br> <label class="d-block">
											Other Fixed/Movable Assets:</label> <form:input path="otherAssetValue"
											size="20" style="text-align: right" id="otherAssetValue"
											value="${npaDetailsBean.otherAssetValue}"
											class="form-control cus-control"
											onchange="caltotalSecurity(this);" /></br> <label class="d-block">
											Current Assets:</label> <form:input path="currentAssetValue"
											size="10" style="text-align: right" id="currentAssetValue"
											value="${npaDetailsBean.currentAssetValue}"
											class="form-control cus-control"
											onchange="caltotalSecurity(this);" /></br> <label class="d-block">
											Others:</label> <form:input path="othersValue" size="10"
											style="text-align: right" id="othersValue"
											value="${npaDetailsBean.othersValue}"
											class="form-control cus-control"
											onchange="caltotalSecurity(this);" /></br></td>
									<td><form:input path="networthOfPromotor" size="10"
											style="text-align: right" id="networthOfPromotor"
											value="${npaDetailsBean.networthOfPromotor}" 
											class="form-control cus-control" /></td>
									<td><form:select path="reductionReason"
											id="reductionReason" readonly="true"
											class="form-control cus-control" disabled="true">
											<form:option value="" label="----------Select---------" />
											<c:forEach var="value" items="${npaResion}">

												<form:option value="${npaDetailsBean.reductionReason}"></form:option>

											</c:forEach>

										</form:select></span></td>


								</tr>
								<tr>
									<td></td>
									<td><label class="d-block">Total:</label> <form:input
											path="totalSecuritydetails" size="10"
											style="text-align: right" id="totalSecuritydetails"
											value="${npaDetailsBean.totalSecuritydetails}"
											readonly="true" /></td>
									<td><form:errors path="totalSecuritydetailsError"
											cssClass="error" /></td>
									<td></td>
								</tr>
								<tr>
									<td><label class="d-block">As on Date of NPA:</label></td>
									<td><label class="d-block">Land:</label> <form:input
											path="landValue1" size="10" style="text-align: right"
											id="landValue1" class="form-control cus-control"
											value="${npaDetailsBean.landValue1}"
											onchange="caltotalSecurity1(this);" /></br> <label
										class="d-block">Building:</label> <form:input
											path="buildingValue1" size="10" style="text-align: right"
											id="buildingValue1" value="${npaDetailsBean.buildingValue1}"
											class="form-control cus-control" 
											onchange="caltotalSecurity1(this);" /></br> <label
										class="d-block"> Plant and Machinery/Equipments:</label> <form:input
											path="planetValue1" size="10" style="text-align: right"
											id="planetValue1" value="${npaDetailsBean.planetValue1}"
											class="form-control cus-control"
											onchange="caltotalSecurity1(this);" /></br> <label
										class="d-block"> Other Fixed/Movable Assets:</label> <form:input
											path="otherAssetValue1" size="10" style="text-align: right"
											id="otherAssetValue1"
											value="${npaDetailsBean.otherAssetValue1}"
											class="form-control cus-control"
											onchange="caltotalSecurity1(this);" /></br> <label
										class="d-block"> Current Assets:</label> <form:input
											path="currentAssetValue1" size="10" style="text-align: right"
											id="currentAssetValue1"
											value="${npaDetailsBean.currentAssetValue1}"
											class="form-control cus-control" 
											onchange="caltotalSecurity1(this);" /></br> <label
										class="d-block"> Others:</label> <form:input
											path="othersValue1" size="20" style="text-align: right"
											id="othersValue1" value="${npaDetailsBean.othersValue1}"
											class="form-control cus-control" 
											onchange="caltotalSecurity1(this);" /></br></td>
									<td><form:input path="networthOfPromotor1" size="20"
											style="text-align: right" id="networthOfPromotor1"
											value="${npaDetailsBean.networthOfPromotor1}" 
											class="form-control cus-control" /></td>
									<td><form:select path="reductionReason1"
											id="reductionReason1" class="form-control cus-control">
											<form:option value="${npaDetailsBean.reductionReason1}" />
											<c:forEach var="value" items="${npaResion}">
												<form:option id="${value}" value="${value}">${value}</form:option>
											</c:forEach>

										</form:select></span></td>
								</tr>
								<tr>
									<td></td>
									<td><label class="d-block"> Total:</label> <form:input
											path="totalSecuritydetails1" size="20"
											style="text-align: right" id="totalSecuritydetails1"
											value="${npaDetailsBean.totalSecuritydetails1}"
											 /></td>
									<td><form:errors path="totalSecuritydetails1Error"
											cssClass="error" /></td>
									<td></td>
								</tr>
							</table>
 -->
							<div class="d-inlineblock">
								<input class="btn btn-reset" type="button" value="Submit"
									onclick="updateNPA()" /> <input class="btn btn-reset"
									type="button" value="Exit" onclick="" /> <span
									id="datepicker_14_Error" Class="error"></span>

								<div Class="displayErrorMessageInRedColor"></div>
							</div>
 

						</form:form>
					</div>
				</div>

			</div>
		</div>
	</div>

</body>
<script type="text/javascript">
	function updateNPA() {
		
		var a=document.getElementById("isAsPerRBI").value;
		document.getElementById('A').action = "/SV/npaDataModify.html?flag="+a;
		document.getElementById('A').submit();

	}
	function calOutstanding(field) {
		var os = 0;
		var quarter =90;
		var year =365;
		var osIntrest =0;
		var claimAmt = 0;
		var outstandingPricipal = document.getElementById("outstandingPrincipal1").value;
	
		var intrestRate=document.getElementById("intrestRate").value;
		alert(intrestRate);
		var latestOutstanding = document.getElementById("latestOsAmt").value;
		//Only Digit Allowed Alpha Character not allowed . 

		var letters1 = /^[a-zA-Z]+$/;
		if (outstandingPricipal != "") {

			var strOutpri = document.getElementById('outstandingPrincipal1').value;
			if (strOutpri.match(letters1)) {
			
				document.getElementById("outstandingPrincipal1Error1").innerHTML = "Enter Digit Only.";

			} else {
				document.getElementById("outstandingPrincipal1Error1").innerHTML = "";
			}
		}
		
		if ((isNaN(outstandingPricipal)) || outstandingPricipal == "") {
			outstandingPricipal = 0;
		}
		

		if (parseInt(outstandingPricipal) > parseInt(latestOutstanding)) {

			document.getElementById("outstandingPrincipal1Error").innerHTML = "Principle Outstanding as on the date of NPA It should not be greater than latest outstanding as on the date of NPA";
		} else {
			//calculation for claim eligable amount
			document.getElementById("outstandingPrincipal1Error").innerHTML = "";
		
			
		//Intrest rATE CALCULATIONS
	        total=(outstandingPricipal)*(quarter/year)*(intrestRate)/100;
	        osIntrest=Math.round(total);
	       
	    	claimAmt = parseInt(outstandingPricipal)+ parseInt(osIntrest);
	        claimAmt = Math.round(claimAmt);
	        document.getElementById("outstandingInterest1").value = osIntrest;
	        document.getElementById("claimEligibityAmt").value = claimAmt;
			
			
		}

	}
	function caltotalSecurity(field) {

		var land = document.getElementById("landValue").value;
	
		var building = document.getElementById("buildingValue").value;
		var planet = document.getElementById("planetValue").value;
		var otherAsset = document.getElementById("otherAssetValue").value;
		var currentasset = document.getElementById("currentAssetValue").value;
		var other = document.getElementById("othersValue").value;
		var networthOfPromotor = document.getElementById("networthOfPromotor").value;
		var total = 0;
		if ((isNaN(land)) || land == "0.0" || land == "") {
			land = 0;
	       document.getElementById("landValue").value =land;
		
		}

		if ((isNaN(building)) || building == "0.0" || building == "" ) {
			building = 0;
		    document.getElementById("buildingValue").value =building ;
		}
		if ((isNaN(planet)) || planet == "0.0" || planet == "") {
			planet = 0;
		    document.getElementById("planetValue").value =planet ;
		}

		if ((isNaN(otherAsset)) || otherAsset == "0.0" || otherAsset == "") {
			otherAsset = 0;
			document.getElementById("otherAssetValue").value =otherAsset ;
		}
		if ((isNaN(currentasset)) || currentasset == "0.0" || currentasset == "") {
			currentasset = 0;
			document.getElementById("currentAssetValue").value =currentasset ;
		}
		if ((isNaN(other)) || other == "0.0"|| other == "") {
			other = 0;
			document.getElementById("othersValue").value =other ;
		}
		if ((isNaN(networthOfPromotor)) || networthOfPromotor == "0.0"|| networthOfPromotor == "") {
			networthOfPromotor = 0;
			document.getElementById("networthOfPromotor").value =networthOfPromotor ;
		}
		total = Number(land) + Number(building) + Number(planet)
				+ Number(otherAsset) + Number(currentasset) + Number(other);

		document.getElementById("totalSecuritydetails").value = total;

	}
	function caltotalSecurity1(field) {

		var land1 = document.getElementById("landValue1").value;
		var building1 = document.getElementById("buildingValue1").value;
		var planet1 = document.getElementById("planetValue1").value;
		var otherAsset1 = document.getElementById("otherAssetValue1").value;
		var currentasset1 = document.getElementById("currentAssetValue1").value;
		var other1 = document.getElementById("othersValue1").value;
		var total1 = 0;

		if ((isNaN(land1)) || land1 == "0.0" || land1 == "") {
			land1 = 0;
			 document.getElementById("landValue1").value =land1;
		}

		if ((isNaN(building1)) || building1 == "0.0" || building1 == "") {
			building1 = 0;
			document.getElementById("buildingValue1").value =building1;
		}
		if ((isNaN(planet1)) || planet1 == "0.0" || planet1=="") {
			planet1 = 0;
			document.getElementById("planetValue1").value =planet1;
		}

		if ((isNaN(otherAsset1)) || otherAsset1 == "0.0" || otherAsset1 == "") {
			otherAsset1 = 0;
			document.getElementById("otherAssetValue1").value =otherAsset1;
		}
		if ((isNaN(currentasset1)) || currentasset1 == "0.0" ||currentasset1 == "") {
			currentasset1 = 0;
			document.getElementById("currentAssetValue1").value =currentasset1;
		}
		if ((isNaN(other1)) || other1 == "0.0" || other1== "") {
			other1 = 0;
			document.getElementById("othersValue1").value =other1;
		}
		if ((isNaN(networthOfPromotor1)) || networthOfPromotor1 == "0.0"|| networthOfPromotor1 == "") {
			networthOfPromotor1 = 0;
			document.getElementById("networthOfPromotor1").value =networthOfPromotor1 ;
		}
		total1 = Number(land1) + Number(building1) + Number(planet1)
				+ Number(otherAsset1) + Number(currentasset1) + Number(other1);

		document.getElementById("totalSecuritydetails1").value = total1;

	}
	</script>
</html>


