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
    <li class="breadcrumb-item"><a href="/SV/NPAApproval.html">NPA Approval/Return</a></li>  
    <li class="breadcrumb-item active" aria-current="page">NPA Details</li>
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
                           <form:hidden path="intrestRate" id="intrestRate"/>
							<h5 class="sub-head">
								<strong> General Details</strong>
							</h5>
							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>CGPAN:</label>
										<form:input path="CGPAN" value="${npaDetailsBean.CGPAN}" size="20" readonly="true"
											class="form-control cus-control" id="CGPAN" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Guarantee Start Date :</label>
										<form:input path="guarStartDt1"
											value="${npaDetailsBean.guarStartDt1}" class="date-picker form-control cus-control" size="28"
											placeholder="e.g:dd-mm-yyyy" readonly="true"/>
										<form:errors path="guarStartDt1" cssClass="error" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Sanction Date:</label>
										<form:input path="sanctionDt1"
											value="${npaDetailsBean.sanctionDt1}" class="date-picker form-control cus-control" size="28"
											placeholder="e.g:dd-mm-yyyy" readonly="true"/>
										<form:errors path="sanctionDt1" cssClass="error" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>First Disbursement Date :</label>
										<form:input path="firstDisbDt1"
											value="${npaDetailsBean.firstDisbDt1}" class="date-picker form-control cus-control" size="28"
											placeholder="e.g:dd-mm-yyyy" readonly="true"/>
										<form:errors path="firstDisbDt1" cssClass="error" />
									</div>
								</div>
							</div>

						
					<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>First Installment Date :</label>
										<form:input path="firstInstDt1"
											value="${npaDetailsBean.firstInstDt1}" class="date-picker form-control cus-control" size="28" readonly="true"
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
										<form:input path="npaDt"
											value="${npaDetailsBean.npaDt}" class="date-picker form-control cus-control" size="28" readonly="true"
											placeholder="e.g:dd/mm/yyyy" />
										<form:errors path="npaDt" cssClass="error" />
									</div>
								</div>
							</div>

						<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label class="d-block"> 
										<c:if test="${npaDetailsBean.isAsPerRBI=='Y'}">
										<input type="checkbox" path="isAsPerRBI" checked> 
										</c:if>
									   <c:if test="${npaDetailsBean.isAsPerRBI=='N'}">                               
                                         <input type="checkbox" path="isAsPerRBI"> 
                                      </c:if>     
										
										 Is the NPA date as per the
											RBI Guidelines:<span style="color: red">*</span></label>

										<form:errors path="isAsPerRBI" cssClass="error" readonly="true"/>
									</div>
								</div>
							</div>

						<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-5">
										<label class="d-block">NPA Reason:<span
											style="color: red">*</span></label>
										<form:select path="npaReason" class="form-control cus-control" readonly="true">
										 <form:option value="${npaDetailsBean.npaReason}"></form:option>
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
											value="${npaDetailsBean.lasInspDt}" class="date-picker form-control cus-control" id="lasInspDt" readonly="true"
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
											value="${npaDetailsBean.totalGuaranteeAmt}" class="form-control cus-control" style="text-align: right" 
											id="totalGuaranteeAmt" readonly="true" />
										<form:errors path="totalDisbAmt1Error" cssClass="error" />
									</div>
								</div>
							</div>
								<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-5">
										<label class="d-block" style="font-size: 11px">Latest O/S Guaranteed Amt:<span style="color: red">*</span> <!--  Principle in Rs.:<span
											style="color: red">*</span> -->
										</label>
										<form:input path="latestOsAmt" size="20"
											value="${npaDetailsBean.latestOsAmt}" class="form-control cus-control" id="latestOsAmt"
											readonly="true" />
										<form:errors path="repayPrincipalError" cssClass="error" />
									</div>
								</div>
							</div>



							<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-5">
									
										<label class="d-block" style="font-size: 11px">Principal Outstanding Amt(As on NPA Date):<span style="color: red">*</span>
										</label>
										<form:input path="outstandingPrincipal1" size="20"
											value="${npaDetailsBean.outstandingPrincipal1}" class="form-control cus-control" id="outstandingPrincipal1" readonly="true"
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
										<label class="d-block" style="font-size: 11px">Interest Outstanding as on the date of NPA: <span style="color: red">*</span>
										</label>
										<form:input path="outstandingInterest1" size="20"
												value="${npaDetailsBean.outstandingInterest1}"  class="form-control cus-control" id="outstandingInterest1"
											onchange="calOutstanding(this);" readonly="true" />
										<form:errors path="outstandingInterest1Error" cssClass="error" />
										<p id="outstandingInterest1Error"
											Class="displayErrorMessageInRedColor"></p>
									</div>
								</div>
							</div>
							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-5">
										<label class="d-block" style="font-size: 11px">Total OutstandingS Amt(As On NPA): <span
											style="color: red">*</span></label>
										<form:input path="claimEligibityAmt" size="20"
											value="${npaDetailsBean.claimEligibityAmt}" class="form-control cus-control" id="claimEligibityAmt" readonly="true" />
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
							<table
								class="table table-bordered table-hover cus-table mb-0">
								<thead>
								<tr>
										<th>Particulars</th>
										<th>Security Nature Value <br/>(in &#8377;)
										</th>
										<th>Networth of Guarantor/Promoter(in &#8377;):</th>
										<th>Reasons for Reduction in the value of Security, if
											any:</th>
									</tr>
								</thead>
								<tr>
									<td><label class="d-block">As on Date of Sanction
											of Credit</label></td>
									<td><label class="d-block">Land:</label> <form:input
											path="landValue" size="20" style="text-align: right"
											value="${npaDetailsBean.landValue}"  id="landValue" class="form-control cus-control" readonly="true"
											onchange="caltotalSecurity(this);" /></br> <label class="d-block">Building:</label>
										<form:input path="buildingValue" size="10"
											style="text-align: right" id="buildingValue" value="${npaDetailsBean.buildingValue}" readonly="true"
											class="form-control cus-control"
											onchange="caltotalSecurity(this);" /></br> <label class="d-block">Plant
											and Machinery/Equipments:</label> <form:input path="planetValue" size="10"
											style="text-align: right" id="planetValue" value="${npaDetailsBean.planetValue}" readonly="true"
											class="form-control cus-control"
											onchange="caltotalSecurity(this);" /></br> <label class="d-block">
											Other Fixed/Movable Assets:</label> <form:input path="otherAssetValue"
											size="20" style="text-align: right" id="otherAssetValue" value="${npaDetailsBean.otherAssetValue}" readonly="true"
											class="form-control cus-control"
											onchange="caltotalSecurity(this);" /></br> <label class="d-block">
											Current Assets:</label> <form:input path="currentAssetValue"
											size="10" style="text-align: right" id="currentAssetValue" value="${npaDetailsBean.currentAssetValue}" readonly="true"
											class="form-control cus-control"
											onchange="caltotalSecurity(this);" /></br> <label class="d-block">
											Others:</label> <form:input path="othersValue" size="10"
											style="text-align: right" id="othersValue" value="${npaDetailsBean.othersValue}" readonly="true"
											class="form-control cus-control"
											onchange="caltotalSecurity(this);" /></br></td>
									<td><form:input path="networthOfPromotor" size="10"
											style="text-align: right" id="networthOfPromotor" value="${npaDetailsBean.networthOfPromotor}" readonly="true"
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
											style="text-align: right" id="totalSecuritydetails" value="${npaDetailsBean.totalSecuritydetails}" readonly="true"/></td>
									<td><form:errors path="totalSecuritydetailsError"
											cssClass="error" /></td>
									<td></td>
								</tr>
								<tr>
									<td><label class="d-block">As on Date of NPA:</label></td>
									<td><label class="d-block">Land:</label> <form:input
											path="landValue1" size="10" style="text-align: right"
											id="landValue1" class="form-control cus-control" value="${npaDetailsBean.landValue1}" readonly="true"
											onchange="caltotalSecurity1(this);" /></br> <label
										class="d-block">Building:</label> <form:input
											path="buildingValue1" size="10" style="text-align: right"
											id="buildingValue1" value="${npaDetailsBean.buildingValue1}" class="form-control cus-control" readonly="true"
											onchange="caltotalSecurity1(this);" /></br> <label
										class="d-block"> Plant and Machinery/Equipments:</label> <form:input
											path="planetValue1" size="10" style="text-align: right"
											id="planetValue1" value="${npaDetailsBean.planetValue1}" class="form-control cus-control" readonly="true"
											onchange="caltotalSecurity1(this);" /></br> <label
										class="d-block"> Other Fixed/Movable Assets:</label> <form:input
											path="otherAssetValue1" size="10" style="text-align: right"
											id="otherAssetValue1" value="${npaDetailsBean.otherAssetValue1}" class="form-control cus-control" readonly="true"
											onchange="caltotalSecurity1(this);" /></br> <label
										class="d-block"> Current Assets:</label> <form:input
											path="currentAssetValue1" size="10" style="text-align: right"
											id="currentAssetValue1" value="${npaDetailsBean.currentAssetValue1}" class="form-control cus-control" readonly="true"
											onchange="caltotalSecurity1(this);" /></br> <label
										class="d-block"> Others:</label> <form:input
											path="othersValue1" size="20" style="text-align: right"
											id="othersValue1" value="${npaDetailsBean.othersValue1}" class="form-control cus-control" readonly="true"
											onchange="caltotalSecurity1(this);" /></br></td>
									<td><form:input path="networthOfPromotor1" size="20"
											style="text-align: right" id="networthOfPromotor1" value="${npaDetailsBean.networthOfPromotor1}" readonly="true"
											class="form-control cus-control" /></td>
									<td><form:select path="reductionReason1"
											id="reductionReason1" class="form-control cus-control">
											<form:option value="" label="----------Select---------" />
											<c:forEach var="value" items="${npaResion}">
											<form:option value="${npaDetailsBean.reductionReason1}"></form:option>
											
											</c:forEach>

										</form:select></span></td>
								</tr>
								<tr>
									<td></td>
									<td><label class="d-block"> Total:</label> <form:input
											path="totalSecuritydetails1" size="20"
											style="text-align: right" id="totalSecuritydetails1" value="${npaDetailsBean.totalSecuritydetails1}" readonly="true"/></td>
									<td><form:errors path="totalSecuritydetails1Error"
											cssClass="error" /></td>
									<td></td>
								</tr>
							</table>

						<!--	<div class="d-inlineblock">
								<input class="btn btn-reset" type="button" value="Submit"
									onclick="updateNPAMarking()" /> <input class="btn btn-reset"
									type="button" value="Exit" onclick="" /> <span
									id="datepicker_14_Error" Class="error"></span>

								<div Class="displayErrorMessageInRedColor"></div>
							</div>
  -->

						</form:form>
					</div>
				</div>

			</div>
		</div>
	</div>

</body>

</html>


