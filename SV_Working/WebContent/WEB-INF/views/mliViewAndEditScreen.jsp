<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>MLI Approval/Reject Screen</title>
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<script src="js/jquery-1.11.1.js" type="text/javascript"></script>

<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script type="text/javascript"
	src="jquery-ui-1.10.3/ui/jquery.ui.datepicker.js"></script>
	<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
	
<%
	String s = (String) request.getAttribute("SName");
%>

</head>
<!-- <body onload="myFunction1()" bgcolor="#E0FFFF"> -->
<body onload="" bgcolor="#E0FFFF" onload="myFunction1()">

	<h2 align="center">Edit MLI Details</h2>
	<font color="green" size="3"><b>${message}</b></font>
	<div>
		<form:form method="POST" id="A" action="/SV/viewEditMLIDetails.html">
			<div
				STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;">

				<table align="center">
					<tr>
						<td><form:label path="mliLongName">Select MLI Name :<span
									style="color: red;">*</span>
							</form:label></td>
						<td><form:select path="mliLongName" id="ViewFileList"
								name="mliLongName" onchange="onchangeApp(),disableUnable()">
								<form:option value=""
									label="-------------Select Portfolio Name------------" />
								<c:forEach var="mliLongName" items="${mliNameList}">
									<form:option id="${mliLongName.longName}"
										value="${mliLongName.longName}">${mliLongName.longName}</form:option>
								</c:forEach>
							</form:select></td>

						<td><form:errors path="mliLongName" cssClass="error" /></td>
						<td><form:input path="" type="hidden"></form:input></td>
						<td><input value="View" class="button" id="viewDetails"
							onclick="testing()" disabled="true" /></td>
						<!-- <td><input value="Edit" class="button"
							id="editDetails" onclick="editFunction()" disabled="true"/></td> -->

					</tr>
				</table>
				<div id="detailId">
					<table align="center" style="color: #5c4324;">
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><form:label path="longName">Long Name <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="longName" id="longName" size="28"
									disabled="true" /></td>
							<td><form:errors path="longName" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>

							<td><form:label path="shortName">Short Name <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="shortName" id="shortName" size="28"
									disabled="true" /></td>
							<td><form:errors path="shortName" cssClass="error" /></td>
						</tr>
						<tr>
							<td><form:label path="companyAddress">Registered Address of the Company<span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="companyAddress" id="companyAddress"
									size="28" disabled="true" /></td>
							<td><form:errors path="companyAddress" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:label path="rbiReggistrationNumber">RBI registration No <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="rbiReggistrationNumber" size="28"
									maxlength="15" disabled="true" id="rbiReggistrationNumber" /></td>
							<td><form:errors path="rbiReggistrationNumber"
									cssClass="error" /></td>

						</tr>
						<tr>
							<td><form:label path="city">City <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="city" id="city" size="28"
									disabled="true" /></td>
							<td><form:errors path="city" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:label path="companyCIN">CIN of the company <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="companyCIN" id="companyCIN" size="28"
									maxlength="21" disabled="true" /></td>
							<td><form:errors path="companyCIN" cssClass="error" /></td>

						</tr>
						<tr>
							<td><form:label path="state">State <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="state" disabled="true" id="state" /></td>

							<td><form:errors path="state" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>

							<td><form:label path="companyPAN">PAN of the company<span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="companyPAN" disabled="true"
									id="companyPAN" /></td>
							<td><form:errors path="companyPAN" cssClass="error" /></td>
						</tr>
						<tr>
							<td><form:label path="district">District <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="district" disabled="true"
									id="district" /></td>
							<td><form:errors path="district" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:label path="gstinNumber">GSTIN  No<span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="gstinNumber" disabled="true"
									id="gstinNumber" /></td>
							<td><form:errors path="gstinNumber" cssClass="error" /></td>
						</tr>
						<tr>
							<td><form:label path="pincode">Pincode <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="pincode" disabled="true" id="pincode" maxlength="6"/></td>
							<td><form:errors path="pincode" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:label path="ratingAgency">Rating Agency<span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="ratingAgency" disabled="true"
									id="ratingAgency" /></td>
							<td><form:errors path="ratingAgency" cssClass="error" /></td>

						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><form:label path="landlineNumber">Land line Number <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="phoneCode" id="phoneCode"
									disabled="true" size="3" maxlength="3"/> <form:input path="landlineNumber"
									disabled="true" id="landlineNumber" maxlength="8"/></td>
							<td><form:errors path="landlineNumber" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:label path="rating">Rating <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="rating" disabled="true" id="rating" /></td>
							<td><form:errors path="rating" cssClass="error" /></td>

						</tr>
						<tr>
							<td><form:label path="emailId">Email ID <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="emailId" size="28" disabled="true"
									id="emailId" /></td>
							<td><form:errors path="emailId" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:label path="ratingDate">Date of Rating<span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="ratingDate" disabled="true"
									id="ratingDate" class="date-picker"/></td>
							<td><form:errors path="ratingDate" cssClass="error" /></td>

						</tr>
						<tr>
							<td><form:label path="contactPerson">Contact Person <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="contactPerson" disabled="true"
									id="contactPerson" /></td>
							<td><form:errors path="contactPerson" cssClass="error" /></td>

						</tr>
						<tr>
							<td><form:label path="mobileNUmber">Mobile No<span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="mobileNUmber" disabled="true"
									id="mobileNUmber" maxlength="10" /></td>
							<td><form:errors path="mobileNUmber" cssClass="error" /></td>

						</tr>

						<tr>
							<td><form:label path="faxNumber">Fax Number
					</form:label></td>
							<td><form:input path="faxCode" disabled="true" id="faxCode" size="3" maxlength="3" />
								<form:input path="faxNumber" disabled="true" id="faxNumber" /></td>
							<td><form:errors path="faxNumber" cssClass="error" /></td>

						</tr>

						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr id="remarks1" class="toshow" style="display: none">
							<td><form:label path="remarks" id="remarksLable">Remarks :</form:label></td>
							<td><form:input path="remarks" size="40" /></td>
							<td><form:errors path="remarks" cssClass="error" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2" id="resetButton"><input
								onClick="window.location.reload()" value="Reset" class="button"
								name="mliApprove" /></td>
							<td colspan="2" id="submitButton"><input type="submit"
								value="Submit" class="button" name="editSave" onclick="field()" /></td>
							<td colspan="2"><input value="Edit" class="button"
								onclick="editDetails()" /></td>
							<td></td>
							<td colspan="2"><input type="submit" value="Close"
								name="close" class="button" /></td>
						</tr>



					</table>

				</div>

				<%-- <div id="editableDetailId">
					<table align="center" style="color: #5c4324;">
					<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><form:label path="editLongName">Long Name <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editLongName" id="editLongName" size="28" disabled="true"/></td>
							<td><form:errors path="editLongName" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>

							<td><form:label path="editShortName">Short Name <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editShortName" id="editShortName" size="28" disabled="true"/></td>
							<td><form:errors path="editShortName" cssClass="error" /></td>
						</tr>
						<tr>
							<td><form:label path="editCompanyAddress">Registered Address of the Company<span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editCompanyAddress" id="editCompanyAddress" size="28" /></td>
							<td><form:errors path="editCompanyAddress" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:label path="editRBIReggistrationNumber">RBI registration No <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editRBIReggistrationNumber" size="28"
									maxlength="15" id="editRBIReggistrationNumber"/></td>
							<td><form:errors path="editRBIReggistrationNumber"
									cssClass="error" /></td>

						</tr>
						<tr>
							<td><form:label path="editCity">City <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editCity" id="editCity" size="28" /></td>
							<td><form:errors path="editCity" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:label path="editCompanyCIN">CIN of the company <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editCompanyCIN" id="editCompanyCIN" size="28" maxlength="21" /></td>
							<td><form:errors path="editCompanyCIN" cssClass="error" /></td>

						</tr>
						<tr>
							<td><form:label path="editState">State <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editState" id="editState"/></td>

							<td><form:errors path="editState" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>

							<td><form:label path="editCompanyPAN">PAN of the company<span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editCompanyPAN" id="editCompanyPAN"/></td>
							<td><form:errors path="editCompanyPAN" cssClass="error" /></td>
						</tr>
						<tr>
							<td><form:label path="editDistrict">District <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editDistrict" id="editDistrict"/></td>
							<td><form:errors path="editDistrict" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:label path="editGstinNumber">GSTIN  No<span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editGstinNumber" id="editGstinNumber"/></td>
							<td><form:errors path="editGstinNumber" cssClass="error" /></td>
						</tr>
						<tr>
							<td><form:label path="editPincode">Pincode <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editPincode"  id="editPincode"/></td>
							<td><form:errors path="editPincode" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:label path="editRatingAgency">Rating Agency<span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editRatingAgency" id="editRatingAgency"/></td>
							<td><form:errors path="editRatingAgency" cssClass="error" /></td>

						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><form:label path="editLandlineNumber">Land line Number <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editLandlineNumber"  id="editLandlineNumber"/></td>
							<td><form:errors path="editLandlineNumber" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:label path="editRating">Rating <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editRating"  id="editRating" /></td>
							<td><form:errors path="editRating" cssClass="error" /></td>

						</tr>
						<tr>
							<td><form:label path="editEmailId">Email ID <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editEmailId" size="28" id="editEmailId"/></td>
							<td><form:errors path="editEmailId" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:label path="editRatingDate">Date of Rating<span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editRatingDate" id="editRatingDate"/></td>
							<td><form:errors path="editRatingDate" cssClass="error" /></td>

						</tr>
						<tr>
							<td><form:label path="editContactPerson">Contact Person <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editContactPerson"  id="editContactPerson"/></td>
							<td><form:errors path="editContactPerson" cssClass="error" /></td>

						</tr>
						<tr>
							<td><form:label path="editMobileNUmber">Mobile No<span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editMobileNUmber" id="editMobileNUmber" /></td>
							<td><form:errors path="editMobileNUmber" cssClass="error" /></td>

						</tr>

						<tr>
							<td><form:label path="editFaxNumber">Fax Number
					</form:label></td>
							<td><form:input path="editFaxNumber" id="editFaxNumber"/></td>
							<td><form:errors path="editFaxNumber" cssClass="error" /></td>

						</tr>
						
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr id="remarks1" class="toshow" style="display: none">
					<td><form:label path="remarks" id="remarksLable">Remarks :</form:label></td>
					<td><form:input path="remarks" size="100"/></td>
					<td><form:errors path="remarks" cssClass="error" /></td>
				</tr>
				<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2"><input onClick="window.location.reload()" value="Reset"
								class="button" name="mliApprove" /></td>
							<td colspan="2"><input type="submit" value="Submit"
								class="button" name="editSave"/></td>
							<td></td>
							<td colspan="2"><input type="submit" value="Close"
								name="close" class="button" /></td>
						</tr>



					</table>

				</div> --%>
			</div>
		</form:form>
	</div>



</body>
<script type="text/javascript">
	function hide() {
		document.getElementById('submitButton').style.display = 'none';
	}
	function disableUnableRemarks() {

		var diveForReject = document.getElementById("REMARKS1");
		diveForReject.style.display = "block";
		var REMARKS = document.getElementById("REMARKS").value;
		//alert(REMARKS);
		if (REMARKS != '') {

			document.getElementById("A").submit();
		} else {
			diveForReject.style.display = "block";
			alert('Please fill the Remarks');
		}
	}
	function viewFunction() {
		//	alert('view');
		//document.getElementById("editDetails").disabled = true;
		//document.getElementById('editableDetailId').style.display = 'none';
		document.getElementById('detailId').style.display = '';
	}
	function testing() {
		document.getElementById('detailId').style.display = '';
		document.getElementById('submitButton').style.display = 'none';
		document.getElementById('resetButton').style.display = '';
	}
	function myFunction1() {
		document.getElementById('submitButton').style.display = 'none';
	}
	function editFunction() {
		//alert('edit');
		onchangeApp();
		document.getElementById("viewDetails").disabled = true;
		document.getElementById('detailId').style.display = 'none';
		document.getElementById('editableDetailId').style.display = '';
	}
	function field() {
		document.getElementById("longName").setAttribute("disabled", false);
		document.getElementById("shortName").setAttribute("disabled", false);
	}
	function editDetails() {

		document.getElementById("companyAddress").setAttribute("disabled",
				false);
		/* document.getElementById("rbiReggistrationNumber").setAttribute(
				"disabled", false); */
		document.getElementById("city").setAttribute("disabled", false);
		//document.getElementById("companyCIN").setAttribute("disabled", false);
		document.getElementById("state").setAttribute("disabled", false);
		//document.getElementById("companyPAN").setAttribute("disabled", false);
		document.getElementById("district").setAttribute("disabled", false);
		//document.getElementById("gstinNumber").setAttribute("disabled", false);
		document.getElementById("pincode").setAttribute("disabled", false);
		document.getElementById("ratingAgency").setAttribute("disabled", false);
		document.getElementById("landlineNumber").setAttribute("disabled",
				false);
		document.getElementById("rating").setAttribute("disabled", false);
		document.getElementById("emailId").setAttribute("disabled", false);
		document.getElementById("ratingDate").setAttribute("disabled", false);
		document.getElementById("contactPerson")
				.setAttribute("disabled", false);
		document.getElementById("mobileNUmber").setAttribute("disabled", false);
		document.getElementById("faxNumber").setAttribute("disabled", false);
		document.getElementById("faxCode").setAttribute("disabled", false);
		document.getElementById("phoneCode").setAttribute("disabled", false);
		document.getElementById('submitButton').style.display = '';
		document.getElementById('resetButton').style.display = 'none';
	}
	function myFunction() {
		document.getElementById('detailId').style.display = 'none';
		document.getElementById('editableDetailId').style.display = 'none';
	}
	function onchangeApp() {
		var portfolioNum = document.getElementById("mliLongName").value;
		//alert(portfolioNum);
		try {
			$
					.ajax({
						type : "POST",
						url : "getmliDetails.html",
						data : "portfolioNum=" + portfolioNum,
						success : function(data) {

							var longName = data.result.longName;
							var shortName = data.result.shortName;
							var companyAddress = data.result.companyAddress;
							var city = data.result.city;
							var state = data.result.state;
							var district = data.result.district;
							var pincode = data.result.pincode;
							var landlineNumber = data.result.landlineNumber;
							var emailId = data.result.emailId;
							var contactPerson = data.result.contactPerson;
							var mobileNUmber = data.result.mobileNUmber;
							var faxNumber = data.result.faxNumber;
							var rbiReggistrationNumber = data.result.rbiReggistrationNumber;
							var companyCIN = data.result.companyCIN;
							var companyPAN = data.result.companyPAN;
							var gstinNumber = data.result.gstinNumber;
							var ratingAgency = data.result.ratingAgency;
							var rating = data.result.rating;
							var ratingDate = data.result.ratingDate;

							var phoneCode = data.result.phone_code;
							var phone_Code = data.result.phoneCode;
							alert(phoneCode);
							alert(phone_Code);
							var faxCode = data.result.faxCode;

							$('#faxCode').val(faxCode);
							$('#phoneCode').val(phoneCode);
							$('#longName').val(longName);
							$('#shortName').val(shortName);
							$('#companyAddress').val(companyAddress);
							$('#city').val(city);
							$('#state').val(state);
							$('#district').val(district);
							$('#pincode').val(pincode);
							$('#landlineNumber').val(landlineNumber);
							$('#emailId').val(emailId);
							$('#contactPerson').val(contactPerson);
							$('#mobileNUmber').val(mobileNUmber);
							$('#rbiReggistrationNumber').val(
									rbiReggistrationNumber);
							$('#companyCIN').val(companyCIN);
							$('#companyPAN').val(companyPAN);
							$('#gstinNumber').val(gstinNumber);
							$('#ratingAgency').val(ratingAgency);
							$('#rating').val(rating);
							$('#ratingDate').val(ratingDate);
							$('#faxNumber').val(faxNumber);

							/* $('#editLongName').val(longName);
							$('#editShortName').val(shortName);
							$('#editCompanyAddress').val(companyAddress);
							$('#editCity').val(city);
							$('#editState').val(state);
							$('#editDistrict').val(district);
							$('#editPincode').val(pincode);
							$('#editLandlineNumber').val(landlineNumber);
							$('#editEmailId').val(emailId);
							$('#editContactPerson').val(contactPerson);
							$('#editMobileNUmber').val(mobileNUmber);
							$('#editRBIReggistrationNumber').val(rbiReggistrationNumber);
							$('#editCompanyCIN').val(companyCIN);
							$('#editCompanyPAN').val(companyPAN);
							$('#editGstinNumber').val(gstinNumber);
							$('#editRatingAgency').val(ratingAgency);
							$('#editRating').val(rating);
							$('#editRatingDate').val(ratingDate);
							$('#editFaxNumber').val(faxNumber);
							 */
							//myFunction();			
						},
						error : function(e) {
							alert('Server Error : ' + e);
						}
					});

		} catch (err) {
			alert('Error is : ' + err);
		}

	}
	function disableUnable() {
		//document.getElementById("editDetails").disabled = false;
		document.getElementById("viewDetails").disabled = false;
	}
</script>
</html>