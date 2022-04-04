<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>

<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String sN=(String)request.getAttribute("sName");
    String expLim=(String)request.getAttribute("eXposureLimit");
%>
<html>
<head>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%-- <script src="<%=request.getContextPath()%>/js/jquery.js"></script> --%>
<script src="<%=request.getContextPath()%>/js/mli_validation.js"></script>
<title>Portfolio Creation</title>
<script src="js/jquery-3.5.1.min.js" type="text/javascript"></script>

<!-- <link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel = "stylesheet"> -->
<link href="css/jquery-ui-css.css" rel="stylesheet" type="text/css">

<!-- <link rel="stylesheet" href="/resources/demos/style.css"> -->
<!--   <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
   <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->

<style type="text/css">
/* .ui-datepicker{	top:210px !important;	} */
</style>
<script>
	$(function() {

		$("#ratingDate").datepicker({
			dateFormat : 'dd/mm/yy'
		});

	});
</script>


</head>

<body  oncontextmenu="return false;" onload="clearField()">

	<div class="main-section">
		<div class="container-fluid">
			<!-- <div class="row"> -->
			<div>
				<nav aria-label="breadcrumb">
				<ol class="breadcrumb cus-breadcrumb">
					<li class="breadcrumb-item"><a
						href="/SV/mliRegistrationPage.html">MLI Registration</a></li>
					<li class="breadcrumb-item active" aria-current="page">New MLI
						Registration</li>
				</ol>
				</nav>
				<form:form method="POST" id="A" class="form-horizontal" enctype="multipart/form-data"  autocomplete="off"> 
				<input type="hidden" name="csrfPreventionSalt" value="${csrfPreventionSalt}"/>				
					<%-- <div id="" class="frm-section mb-10 d-none">
		<div class="col-md-12">
		<ul class="col-md-3">
			<li> <form:errors path="longName" Class="error" />
		 <span>${longNameerror}</span>	</li>
		 <li>  <form:errors path="shortName" cssClass="error" />
		<span>${shortNameerror}</span>	</li>
		<li>	 <form:errors path="companyAddress" cssClass="error" />
			  <form:input path="" size="28" type="hidden" />
		</li>
		<li>	 <form:errors path="rbiReggistrationNumber" cssClass="error" />
				<span>${rbiREGerror}</span>	
		</li>
		<li>	<form:errors path="city" cssClass="error" />
				<span>${rbiREGerror}</span>
				<form:input path="" size="28" type="hidden" />	
		</li>
		<li>	
		<form:errors path="companyCIN" cssClass="error" />
				<span>${companyCINNoGerror}	</span>
				<form:input path="" size="28" type="hidden" />			
		</li>
		<li>	state
		<form:errors path="state" cssClass="error" />				
				<form:input path="" size="28" type="hidden" />		
		</li>
		</ul>
		<ul	class="col-md-3" >
		<li>	<form:errors path="companyPAN" cssClass="error" />
		 <span>${panOFCompanyerror}</span> 
				</li>
		<li><form:errors path="district" cssClass="error" />				
			<form:input path="" size="28" type="hidden" /> 	  	</li>
	<li>	<form:errors path="gstinNumber" cssClass="error" />
		 <span>${gstinNOerror}</span>  
			<form:input path="" size="28" type="hidden" /> 	</li>
	<li>	<form:errors path="pincode" cssClass="error" />		 
		<form:input path="" size="28" type="hidden" /> 	 </li>
	<li><form:errors path="ratingAgency" cssClass="error" />
			
	</li>
	<li>	<form:errors path="phoneCode" cssClass="error" />
					    <form:errors path="landlineNumber" cssClass="error" />
					    <form:input path="" size="28" type="hidden" />
				</li>
					
		</ul>
		<ul	class="col-md-3" >
			<li>	<form:errors path="rating" cssClass="error" />
				</li>
			<li><form:errors path="emailId" cssClass="error" />						
			<form:input path="" size="28" type="hidden" /> 	  </li>
			<li><form:errors path="ratingDate" cssClass="error" />					
					<span>${ratDateMsg}</span>  					</li>
			<li><form:errors path="contactPerson" cssClass="error" />					
				</li>
			<li><form:errors path="mobileNUmber" cssClass="error" />
									</li>
				<li>	<form:errors path="faxNumber" cssClass="error" />					
					<span>${ratDateMsg}</span>  
					<form:input path="" size="28" type="hidden" /> 	  </li>
		</ul>		
		
		</div>
	</div> --%>

					<div class="frm-section">
						<div class="col-md-12">
							<h2 align="center" class="heading">New MLI Registration</h2>
							<h5 class="notification-message">
								<strong>${message}</strong>
							</h5>
							<h5 class="error1">
								<strong>${error}</strong>
							</h5>
							<%-- <span>${message}</span>
			<span>${error}</span> --%>
							<h4 class="detail_sp1">MLI Basic Detail</h4>
							<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="mliType">MLI Type <span
												style="color: red;">*</span>
										</form:label>
										<form:select path="mliType" id="mliType" class="form-control cus-control">
											<form:option value="" label="--Select Type--" />
											<form:option value="CO-OP Bank" label="CO-OP Bank" />
											<form:option value="FI" label="FI" />
											<form:option value="Foreign Bank" label="Foreign Bank" />
											<form:option value="MFI" label="MFI" />
											<form:option value="NBFC" label="NBFC" />
											<form:option value="PSU Bank" label="PSU Bank" />
											<form:option value="Pvt Bank" label="Pvt Bank" />
											<form:option value="RRB" label="RRB" />
											<form:option value="SFB" label="SFB" />
											<form:option value="SHG Bank" label="SHG Bank" />
											<form:option value="Others" label="Others" />
										</form:select>
										<form:errors path="mliType" cssClass="error" />
										<span Class="error" id="mliTypeerror"></span>

									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="longName">Long Name <span
												style="color: red;">*</span>
										</form:label>
										<form:input path="longName" size="28" id="longName"
											placeholder="e.g:ABC Steel Company"
											class="form-control cus-control"  autocomplete="off"/>
										<form:input path="" size="28" type="hidden" />
										<form:errors path="longName" Class="error" />
										<span Class="error" id="longNameerror">${longNameerror}</span>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="shortName">Short Name <span
												style="color: red;">*</span>
										</form:label>
										<form:input path="shortName" size="28" id="shortName"
											placeholder="e.g:ABC Steel " class="form-control cus-control"  autocomplete="off"/>
										<form:errors path="shortName" cssClass="error" />
										<span Class="error" id="shortNameerror">${shortNameerror}</span>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="companyAddress">Registered Address<span
												style="color: red;">*</span>
										</form:label>
										<form:input path="companyAddress" size="28" id="companyAddress"
											class="form-control cus-control"
											placeholder="e.g:1402 9 10,AJA Compound,Bharat Nagar,Mumbai-400007 "  autocomplete="off"/>
										<form:errors path="companyAddress" cssClass="error" />
										<form:input path="" size="28" type="hidden" />
										<span Class="error" id="companyAddresserror">${companyAddresserror}</span>
									</div>
								</div>
							</div>

							<div class="clearfix"></div>
							<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="state">State <span
												style="color: red;">*</span>
										</form:label>
										<form:select path="state" id="state" class="form-control cus-control" onchange="doAjaxPost4()">
											<form:option value="" label="--Select State--" />
											<c:forEach var="state" items="${stateList}">
												<form:option id="${state.ste_code}"
													value="${state.ste_code}">${state.ste_name}</form:option>
											</c:forEach>
										</form:select>
										<form:errors path="state" cssClass="error" />
										<form:input path="" size="28" type="hidden" />
										<span Class="error" id="stateerror">${stateerror}</span>

									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="district">District <span
												style="color: red;">*</span>
										</form:label>
										<form:select id="district" path="district"
											class="form-control cus-control">
											<form:option value="" label="--Select District--" />
											<form:option id="${district}" value="${district}">${district}</form:option>
										</form:select>
										<form:errors path="district" cssClass="error" />
										<form:input path="" size="28" type="hidden" />
										<span Class="error" id="districterror">${districterror}</span>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="city">City <span
												style="color: red;">*</span>
										</form:label>
										<form:input path="city" size="28" id="city"
											class="form-control cus-control" placeholder="e.g:Mumbai"  autocomplete="off" />
										<form:errors path="city" cssClass="error" />
										<%-- <span>${rbiREGerror}</span> --%>
										<form:input path="" size="28" type="hidden" />
										<span Class="error" id="cityerror">${cityerror}</span>
									</div>
								</div>
							</div>


							<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="pincode">Pincode <span
												style="color: red;">*</span>
										</form:label>
										<form:input path="pincode" size="28" maxlength="6" id="pincode"
											class="form-control cus-control" placeholder="e.g:421405"  autocomplete="off"/>
										<form:errors path="pincode" cssClass="error" />
										<form:input path="" size="28" type="hidden" />
										<span Class="error" id="pincodeerror">${pincodeerror}</span>
									</div>
								</div>
							</div>
						<div class="clearfix"></div>
								<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="companyPAN">PAN of Institution<span
												style="color: red;">*</span>
										</form:label>
										<form:input path="companyPAN" size="28" maxlength="10" autocomplete="off"
											class="form-control cus-control" placeholder="e.g:AACCK6354L" id="companyPAN" />
										<form:errors path="companyPAN" cssClass="error" />
										<span Class="error" id="panOFCompanyerror">${panOFCompanyerror}</span>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="gstinNumber">GSTIN No.<span
												style="color: red;">*</span>
										</form:label>
										<form:input path="gstinNumber" size="28" autocomplete="off" id="gstinNumber"
											class="form-control cus-control" maxlength="15"
											placeholder="e.g:26AAACB1534F1Z8" />
										<form:errors path="gstinNumber" cssClass="error" />
										<span Class="error" id="gstinNOerror">${gstinNOerror}</span>
									</div>
								</div>
							</div>
							
							<div class="clearfix"></div>
							<h4 class="detail_sp1">MLI Contact Details</h4>
							<!-- <hr class="split"> -->

							<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="landlineNumber" class="d-block"> Land Line Number <span
												style="color: red;">*</span>
										</form:label>
										<form:input path="phoneCode" id="phoneCode"
											class="form-control cus-control  w-28 d-inline" size="5"
											maxlength="5" placeholder="e.g:022" />
										<form:input path="landlineNumber" size="20" id="landlineNumber"
											class="form-control cus-control w-69 d-inline" maxlength="8"
											placeholder="e.g:67834562" />
										<form:errors path="phoneCode" cssClass="error" />
										<form:errors path="landlineNumber" cssClass="error" />
										<form:input path="" size="28" type="hidden" />
										<span Class="error" id="phoneCodeerror">${phoneCodeerror}</span>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="emailId">Official Email Id <span
												style="color: red;">*</span>
										</form:label>
										<form:input path="emailId" size="28" autocomplete="off" id="emailId"
											placeholder="akshay_kumar@xxxx.com"
											class="form-control cus-control" />
										<form:errors path="emailId" cssClass="error" />
										<form:input path="" size="28" type="hidden" />
										<span Class="error" id="emailIderror">${emailIderror}</span>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="contactPerson">Contact Person <span
												style="color: red;">*</span>
										</form:label>
										<form:input path="contactPerson" size="28" id="contactPerson"
											class="form-control cus-control"
											placeholder="e.g:Mr Akshay Kumar "  autocomplete="off" />
										<form:errors path="contactPerson" cssClass="error" />
										<span Class="error" id="contactPersonerror">${contactPersonerror}</span>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="mobileNUmber">Mobile No.<span
												style="color: red;">*</span>
										</form:label>
										<form:input path="mobileNUmber" size="28" maxlength="10" id="mobileNUmber"
											class="form-control cus-control"
											placeholder="e.g:9890190283 "  autocomplete="off"/>
										<form:errors path="mobileNUmber" cssClass="error" />
										<span Class="error" id="mobileNUmbererror">${mobileNUmbererror}</span>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12 d-none">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="faxNumber" class="d-block">Fax Number</form:label>
										<form:input path="faxCode" size="3" maxlength="3"
											class="form-control cus-control  w-28 d-inline"
											placeholder="e.g:022" />
										<form:input path="faxNumber" size="20" maxlength="8"
											class="form-control cus-control  w-69 d-inline"
											placeholder="e.g:658322182 "  autocomplete="off" />
										<form:errors path="faxNumber" cssClass="error" />
									</div>
								</div>
							</div>

							<!-- <div class="clearfix"></div>
							<h4 class="detail_sp2">MLI Registration Detail</h4>
 -->							<!-- <hr class="split"> -->

							<div class="col-md-3 col-sm-4 col-xs-12" style="display:none">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="rbiReggistrationNumber">RBI Registration No. <span
												style="color: red;"></span>
										</form:label>
										<form:input path="rbiReggistrationNumber" size="28"
											class="form-control cus-control" maxlength="10"
											placeholder="e.g:N-09.00425"  autocomplete="off"/>
										<form:errors path="rbiReggistrationNumber" cssClass="error" />
										<span Class="error">${rbiREGerror}</span>

									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12" style="display:none">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="companyCIN">CIN of the Company <span
												style="color: red;"></span>
										</form:label>
										<form:input path="companyCIN" size="28" maxlength="21"
											class="form-control cus-control"
											placeholder="e.g:U67190MH2001PLC227379"  autocomplete="off" />
										<form:errors path="companyCIN" cssClass="error" />
										<span Class="error">${companyCINNoGerror} </span>
										<%-- <form:input path="" size="28" type="hidden" />	 --%>
										

									</div>
								</div>
							</div>


<%-- 
							<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="companyPAN">PAN of Institution<span
												style="color: red;">*</span>
										</form:label>
										<form:input path="companyPAN" size="28" maxlength="10"
											class="form-control cus-control" placeholder="e.g:AACCK6354L" />
										<form:errors path="companyPAN" cssClass="error" />
										<span Class="error">${panOFCompanyerror}</span>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="gstinNumber">GSTIN No.<span
												style="color: red;">*</span>
										</form:label>
										<form:input path="gstinNumber" size="28"
											class="form-control cus-control" maxlength="15"
											placeholder="e.g:26AAACB1534F1Z8" />
										<form:errors path="gstinNumber" cssClass="error" />
										<span Class="error">${gstinNOerror}</span>
									</div>
								</div>
							</div>

--%>

<!-- 
							
					 		<h4 class="detail_sp3">Rating Detail</h4>  -->
							<!-- <hr class="split"> -->
<div class="clearfix"></div>
							<div class="col-md-3 col-sm-4 col-xs-12" style="display:none">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="ratingAgency">Rating Agency<span
												style="color: red;"></span>
										</form:label>
										<form:select path="ratingAgency" class="form-control cus-control" id="ratingAgency">
											<form:option value="" label="---Select Rating Agency---" />
											<form:option value="CARE RATINGS" label="CARE RATINGS" />
											<form:option value="INDIA RATINGS AND RESEARCH"
												label="INDIA RATINGS AND RESEARCH" />
											<form:option value="CRISIL" label="CRISIL" />
											<form:option value="ICRA" label="ICRA" />
											<form:option value="Fitch Ratings" label="Fitch Ratings" />
											<form:option value="NA" label="NA" />
											<%-- <form:option value="" label="---------Select Rating Agency-------" />
						<c:forEach var="ratingAgency" items="${ratingAgencyList}">
							<form:option id="${ratingAgency.mliReating}"
								value="${ratingAgency.mliReating}">${ratingAgency.mliReating}</form:option>
						</c:forEach> --%>
						
	
										</form:select>
										<form:errors path="ratingAgency" cssClass="error" />
									</div>
								</div>
							</div>



							<div class="col-md-3 col-sm-4 col-xs-12" style="display:none">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="rating">Credit Rating<span
												style="color: red;"></span>
										</form:label>
										<form:select path="rating" id="rating"
											class="form-control cus-control">
											<form:option value="" label="--Select Rating--" />
											<c:forEach var="ratingAgency" items="${ratingAgencyList}">
												<form:option id="${ratingAgency.mliReating}"
													value="${ratingAgency.mliReating}">${ratingAgency.mliReating}</form:option>
											</c:forEach>
											<%-- <form:option value=""
							label="----------Select MLI rating------------" />
						<form:option value="CARE RATINGS" label="CARE RATINGS" />
						<form:option value="INDIA RATINGS AND RESEARCH"
							label="INDIA RATINGS AND RESEARCH" />
						<form:option value="CRISIL" label="CRISIL" />
						<form:option value="ICRA" label="ICRA" />
						<form:option value="Fitch Ratings" label="Fitch Ratings" /> --%>
						
		
										</form:select>
										<form:errors path="rating" cssClass="error" />
									</div>
								</div>
							</div>



							<div class="col-md-3 col-sm-4 col-xs-12" style="display:none">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="ratingDate">Date of Rating<span
												style="color: red;"></span>
										</form:label>
										<form:input path="ratingDate"
											class="date-picker form-control cus-control" size="28"
											placeholder="e.g:dd/mm/yyyy" id="ratingDate" />
										<form:errors path="ratingDate" cssClass="error" />
										<span Class="error">${ratDateMsg}</span>
									</div>
								</div>
							</div>
								<div class="clearfix"></div>
							<h4 class="detail_sp1">MLI Undertaking - Upload</h4>
							<div class="col-md-3 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="mlifile">Upload File <span
												style="color: red;">*</span>
										</form:label>
										File to upload: <input type="file" name="mlifile" accept="application/pdf" id="mlifile"
											placeholder="Upload File" class="form-control cus-control"  />
											<form:errors path="mlifile" cssClass="error" />
											<span Class="error" id="mlifileerror">${mlifileerror}</span>
									</div>
								</div>
							</div>	
						<div class="clearfix"></div>
							<div class="d-inlineblock mt-25">
								<input type="submit" value="Save" class="btn btn-reset" id="btn_save"
									onclick="return updateMLIDetails()" /> 
									 <input type="reset"
									value=" Reset "
									class="btn btn-reset" />
									 <input type="submit" value=" Close " id="btn_close"
									onclick="exitMLIDetails()" class="btn btn-reset" />
							</div>

							<!--  <div class="clearfix"></div> -->







						</div>
					</div>

				</form:form>
			</div>
		</div>
	</div>




</body>
<script type="text/javascript">
	function updateMLIDetails() {

/* 		var dateString = document.getElementById("ratingDate").value;
		var elem1 = dateString.split('/');
		var day = elem1[0];
		alert(day);
		var month = elem1[1];
		alert(month);
		var year = elem1[2];
		alert(year);
		
		var today = new Date();
		var currentDate = today.getDate() + '/' + today.getMonth() + 1 + '/'+ today.getFullYear();
		alert(currentDate);
		

		if ((mydate) < Date.now()) {
			alert("greater");
		} else {
			alert("smaller")
		} 
 */
 		   var result=validateMLIRegistration();
 		if(result!=false)
 			{   
		document.getElementById('A').action = "/SV/updateNewMLIDetails.html";
	     document.getElementById('A').submit();
 			}
 		return false;
 		
	}

	function resetMLIDetails() {
		//alert('Reset');
		document.getElementById('A').reset();
		document.getElementById('A').submit();
	}

	/* function exitMLIDetails() {
		alert('Exit');
		document.getElementById('A').action = "/nbfc/cgtmseMakerHome.html";
		document.getElementById('A').submit();
	}
	 */
	function exitMLIDetails() {
		//alert('Exit');
		document.getElementById('A').action = "/SV/mliRegistrationPageClose.html";
		document.getElementById('A').submit();
	}

	function doAjaxPost4() {
		
		var state = document.getElementById("state").value;
		try {
			$.ajax({
				type : "POST",
				url : "getNewDistrict.html",
				data : "state=" + state,
				success : function(response) {
					var select2 = document.getElementById('district');
					if (response.status == "SUCCESS") {
						document.getElementById('district').options.length = 0;
						for (var i = 0; i < response.result.length; i++) {
							option = document.createElement('option');
							option.text = response.result[i].dst_name;
							select2.add(option);
						}
					}
				},
				error : function(e) {
					alert('Server Error : ' + eval(e));
				}
			});

		} catch (err) {
			alert('Error is : ' + err);
		}
	}
	
	document.onkeydown = function(e) {
		  if(event.keyCode == 123) {
		     return false;
		  }
		  if(e.ctrlKey && e.shiftKey && e.keyCode == 'I'.charCodeAt(0)) {
		     return false;
		  }
		  if(e.ctrlKey && e.shiftKey && e.keyCode == 'C'.charCodeAt(0)) {
		     return false;
		  }
		  if(e.ctrlKey && e.shiftKey && e.keyCode == 'J'.charCodeAt(0)) {
		     return false;
		  }
		  if(e.ctrlKey && e.keyCode == 'U'.charCodeAt(0)) {
		     return false;
		  }
		}
</script>

</html>



