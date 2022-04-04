<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<title>Application Status</title>
<link href="css/jquery-ui-css.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<LINK href="<%=request.getContextPath()%>/css/stylesheet.css"
	rel="stylesheet" type="text/css">
<title>Application Status</title>
</head>
<body onload="clearField()">
	<div class="main-section">
		<div class="container-fluid">
	 <nav aria-label="breadcrumb">
 <!--  <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/appstatus.html">Application Status</a></li>  
    <li class="breadcrumb-item"><a href="/SV/searchappstatus.html">Application Status1</a></li>
    <li class="breadcrumb-item active" aria-current="page">Application Status2</li>
  </ol> -->
</nav> 
<%
 String file_id=(String)session.getAttribute("FILE_ID");
String f_date=(String)session.getAttribute("FDate");
String s_date=(String)session.getAttribute("TDate");
String status=(String)session.getAttribute("AppStatus");
%>
			<div>
				<div class="frm-section">
					<div class="col-md-12">
						<c:if test="${!empty noDataFound}">
							<span style="color: red"><b>${noDataFound}</b></span>
						</c:if>
						<form:form method="POST" action="" class="form-horizontal" id="A">
						
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<label class="d-block text-purple" style="visibility: hidden";>
										1</label>
									<div class="col-md-6 prl-10">
										<label>Start Date : <span style="color: red">*</span></label>
										<form:input path="toDate" value="" size="28" id="toDate"
											class="form-control cus-control" style="text-align: left"
											placeholder="eg.dd/mm/yyyy" />
										<form:errors path="toDate" cssClass="error" />
										<div id="requiredMliValidityOfExposureLimitStartDate"
											Class="displayErrorMessageInRedColor"></div>
										<div id="startDateShouldBeGreaterThanSanctionDate"
											Class="displayErrorMessageInRedColor"></div>
									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<label class="d-block text-purple" style="visibility: hidden";>
										4</label>
									<div class="col-md-6 prl-10">
										<label>End Date : <span style="color: red">*</span></label>
										<form:input path="fromDate" type="" size="28" id="fromDate"
											class="form-control cus-control" placeholder="eg.dd/mm/yyyy" />
										<form:errors path="fromDate" cssClass="error" />
										<div id="requiredMliValidityOfExposureLimitEndDate"
											Class="displayErrorMessageInRedColor"></div>

									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<label class="d-block text-purple" style="visibility: hidden";>
										5</label>
									<div class="col-md-12 prl-10">
										<label>Application Status : <span style="color: red">*</span></label>
										<input type="text" class="form-control cus-control d-none">
										<form:select path="appStatus" id="appStatus" disabled="false"
											class="form-control cus-control">
											<form:option value="" label="-Select Application Status-" />
											<form:option value="Approved" label="Approved" />
											<form:option value="Pending" label="Pending" />
											<form:option value="Return" label="Returned" />
										</form:select>
										<form:errors path="appStatus" cssClass="error" />
										<div id="requiredGuranteeCoverage"
											Class="displayErrorMessageInRedColor"></div>
									</div>
								</div>

							</div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">

									<div class="d-inlineblock mt-35">
										<!-- <input type="button" value="Create Exposure Limit"
											class="btn btn-reset" class="btn btn-reset"
											onclick="return searchFun()" /> -->
										<input type="submit" value="Search/Download" class="btn btn-reset"
											class="btn btn-reset" onclick="return searchFun()" />
										<!-- mt-25 -->
										<!-- <input type="button" value="Back" class="btn btn-reset" onclick="return exitMLIDetails()"/> -->
									</div>
								</div>
							</div>
							<div></div>

						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<c:if test="${!empty rows}">
		<!-- <div
			STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;"> -->
		<div class="container-fluid">
			<div class="frm-section">
				<div class="col-md-12">
					<!-- <div class="tbl-details"> -->
					<!-- 	<table cellpadding=5 class="danRpDataTable" cellspacing=5
				align=center width=90%> -->
					<table id="myTable" cellpadding=5
						class="table table-bordered table-hover cus-table mt-10 mb-0"
						cellspacing=5 align=center width=90%>
						<thead>
							<tr>
								<c:forEach items="${rows[0]}" var="column">
									<b> </b>
									<th><c:out value="${column.key}" /></th>
								</c:forEach>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${rows}" var="columns">
								<tr>
									<c:forEach items="${columns}" var="column">
										<td align="center">${column.value}</td>
									</c:forEach>
								</tr>
							</c:forEach>
						</tbody>
					</table>
						<div class="d-inlineblock mt-35">
										
										<input type="button" value="Back" class="btn btn-reset"
											class="btn btn-reset" onclick="Back2()" />
										<!-- mt-25 -->
										<!-- <input type="button" value="Back" class="btn btn-reset" onclick="return exitMLIDetails()"/> -->
									</div>
				</div>
			</div>
		</div>
	</c:if>
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
						
						<tr>
							<td><label>Application Remarks:<span>:</span></label>
									${applicationDetails.REMARKS}</td>
						
							<td><label>Application Status:<span>:</span></label>
									${applicationDetails.status}</td>
						
						
						</table>
						
						

						<%-- <div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>Unit Name <span>:</span></label>
									${applicationDetails.MSE_NAME}
								</div>
							</div>
						</div>

						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>CGPAN <span">:</span></label>
									${applicationDetails.CGPAN}
								</div>
							</div>
						</div>
						<div class="col-md-6 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>Member ID<span>:</span></label>
									${applicationDetails.MEMBER_ID}
								</div>
							</div>
						</div>
						<h4 class="detail_sp1">Application Details</h4>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>Application Number <span>:</span></label>
									${applicationDetails.LOAN_ACCOUNT_NO}
								</div>
							</div>
						</div>

						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>Lone Type<span>:</span></label>
									${applicationDetails.LONE_TYPE}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>CONSTITUTION<span>:</span></label>
									${applicationDetails.CONSTITUTION}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>SANCTION DATE<span>:</span></label>
									${applicationDetails.SANCTION_DATE}
								</div>
							</div>
						</div>

						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>SANCTIONED AMOUNT<span>:</span></label>
									${applicationDetails.SANCTIONED_AMOUNT}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>FIRST DISBURSEMENT DATE<span>:</span></label>
									${applicationDetails.FIRST_DISBURSEMENT_DATE}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>INTEREST RATE(%)<span>:</span></label>
									${applicationDetails.INTEREST_RATE}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>MICRO SMALL<span>:</span></label>
									${applicationDetails.MICRO_SMALL}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>TENOR IN MONTHS<span>:</span></label>
									${applicationDetails.TENOR_IN_MONTHS}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>UNIT ADDRESS<span>:</span></label>
									${applicationDetails.MSE_ADDRESS}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>CITY<span>:</span></label> ${applicationDetails.CITY}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>DISTRICT<span>:</span></label>
									${applicationDetails.DISTRICT}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>PINCODE<span>:</span></label>
									${applicationDetails.PINCODE}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>STATE<span>:</span></label> ${applicationDetails.STATE}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>MSE ITPAN<span>:</span></label>
									${applicationDetails.MSE_ITPAN}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>UDYOG AADHAR NO<span>:</span></label>
									${applicationDetails.UDYOG_AADHAR_NO}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>INDUSTRY NATURE<span>:</span></label>
									${applicationDetails.INDUSTRY_NATURE}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>INDUSTRY SECTOR<span>:</span></label>
									${applicationDetails.INDUSTRY_SECTOR}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>NO OF EMPLOYEES<span>:</span></label>
									${applicationDetails.NO_OF_EMPLOYEES}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>NEW EXISTING UNIT<span>:</span></label>
									${applicationDetails.NEW_EXISTING_UNIT}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>PREVIOUS BANKING EXPERIENCE<span>:</span></label>
									${applicationDetails.PREVIOUS_BANKING_EXPERIENCE}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>CHIEF PROMOTER FIRST NAME<span>:</span></label>
									${applicationDetails.CHIEF_PROMOTER_FIRST_NAME}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>CHIEF PROMOTER MIDDLE NAME<span>:</span></label>
									${applicationDetails.CHIEF_PROMOTER_MIDDLE_NAME}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>CHIEF PROMOTER LAST NAME<span>:</span></label>
									${applicationDetails.CHIEF_PROMOTER_LAST_NAME}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>CHIEF PROMOTER IT PAN<span>:</span></label>
									${applicationDetails.CHIEF_PROMOTER_IT_PAN}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>CHIEF PROMOTER MAIL ID<span>:</span></label>
									${applicationDetails.CHIEF_PROMOTER_MAIL_ID}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>CHIEF PROMOTER CONTACT NUMBER<span>:</span></label>
									${applicationDetails.CHIEF_PROMOTER_CONTACT_}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>HANDICAPPED<span>:</span></label>
									${applicationDetails.HANDICAPPED}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>WOMEN<span>:</span></label> ${applicationDetails.WOMEN}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>CATEGORY<span>:</span></label>
									${applicationDetails.CATEGORY}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>PORTFOLIO NAME<span>:</span></label>
									${applicationDetails.PORTFOLIO_NAME}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>DAN ID<span>:</span></label>
									${applicationDetails.DAN_ID}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>GUARANTEE AMOUNT<span>:</span></label>
									${applicationDetails.GUARANTEE_AMOUNT}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>COLLETRAL SECURITY AMOUNT<span>:</span></label>
									${applicationDetails.COLLETRAL_SECURITY_AMOUNT}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>RETAIL TRADE<span>:</span></label>
									${applicationDetails.RETAIL_TRADE}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>AADHAR NUMBER<span>:</span></label>
									${applicationDetails.AADHAR_}
								</div>
							</div>
						</div>
						<div class="col-md-3 col-sm-4 col-xs-12">
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>CGPAN<span>:</span></label> ${applicationDetails.CGPAN}
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12 prl-10">
									<label>OUTSTANDING AMOUNT<span>:</span></label>
									${applicationDetails.OUTSTANDING_AMOUNT}
								</div>
							</div> --%>

	<div align="center">
										
										<input type="button" value="Back" class="btn btn-reset"
											class="btn btn-reset" onclick="Back1()" />
										<!-- mt-25 -->
										<!-- <input type="button" value="Back" class="btn btn-reset" onclick="return exitMLIDetails()"/> -->
									</div>
						</div>
					</div>
				</div>
				
			</div>


		</div>
		
	</c:if>


	<div align="left" id="successMsg">
		<font color="green" size="5">${message}</font>
	</div>
	<div align="left" id="error">
		<font color="red" size="5">${error}</font>
	</div>
	
<div class="overlay" id="load">
    <div class="overlay__inner">
        <div class="overlay__content"><span class="spinner"></span></div>
    </div>
</div>
	<script >


document.onreadystatechange = function () {
	  var state = document.readyState;
	  if (state == 'interactive') {
	      // document.getElementById('contents').style.visibility="hidden";
	       document.getelementsbyclassname('main-section').style.visibility="hidden";
	       
	  } else if (state == 'complete') {
	      setTimeout(function(){
	         document.getElementById('interactive');
	         document.getElementById('load').style.visibility="hidden";
	         document.getElementById('main-section').style.visibility="visible";
	      },1000);
	  }
	}


	function searchFun() {
		debugger;
		document.getElementById('A').action = "/SV/searchappstatus.html";

		document.deliveryForm.submit();

	}
	function Back1() {

		document.getElementById('A').action = "/SV/uploadedFileDataApplicationHistory.html?fileId="+'<%=file_id%>';

		document.getElementById('A').submit();

	}
	/*  function Back2() {

		document.getElementById('A').action = "/SV/searchappstatus.html?fdate=" +f_date"&sdate=" +s_date "&status=" +status;

		document.getElementById('A').submit();

	}  */
	$(document).ready(function() {
		$('#id1').hide();
		$('#preview').on('click', function() {
			$('#div1').toggle(300);
		});
	});

	//Ajax Call

	//Date Picker UI
	$(function() {
		$("#mliDateOfSanctionOfExposure").datepicker({
			dateFormat : 'dd/mm/yy'
		});
		$("#toDate").datepicker({
			dateFormat : 'dd/mm/yy'
		});
		$("#fromDate").datepicker({
			dateFormat : 'dd/mm/yy'
		});

	});

	//Form Validation
	//Form Validation
	//Form Validation
</script>
</body>
</html>
