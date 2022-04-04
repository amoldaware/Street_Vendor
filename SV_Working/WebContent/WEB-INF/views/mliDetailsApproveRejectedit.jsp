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
<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<%
	String s = (String) request.getAttribute("SName");
%>
</head>

<body bgcolor="#E0FFFF">

	<h2 align="center">Approve MLI Details</h2>
	<font color="green" size="3"><b>${message}</b></font>
	<div>
		<form:form method="POST" id="A"
			action="/SV/approveRejectMLIDetails.html">
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
						<td><input type="submit" value="View" class="button"
							id="viewDetails" name="view" onclick="viewFunction()"
							disabled="true" /></td>
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
							<th></th>
							<th>Old Data</th>
							<th></th>
							<th></th>
							<th>Updated Data</th>
						</tr>

						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><form:label path="longName">Long Name <span
										style="color: red;">*</span>
								</form:label></td>
								<td><form:input path="editLongName" id="editLongName" size="28"
									disabled="true" /></td>
							
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>

							<td><form:input path="longName" id="longName" size="28"
									disabled="true" /></td>
						</tr>
						<tr>
							<td><form:label path="shortName">Short Name <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editShortName" id="editShortName" size="28"
									disabled="true" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							
									<td><form:input path="shortName" id="shortName" size="28"
									disabled="true" /></td>
						</tr>
						<tr>
							<td><form:label path="companyAddress">Registered Address of the Company<span
										style="color: red;">*</span>
								</form:label></td>
							
									<td><form:input path="editCompanyAddress" id="editCompanyAddress"
									size="28" disabled="true" /></td>

							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="companyAddress" id="companyAddress"
									size="28" disabled="true" /></td>

						</tr>
						<tr>
							<td><form:label path="rbiReggistrationNumber">RBI registration No <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editRBIReggistrationNumber" size="28"
									maxlength="15" disabled="true" id="editRBIReggistrationNumber" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							
									<td><form:input path="rbiReggistrationNumber" size="28"
									maxlength="15" disabled="true" id="rbiReggistrationNumber" /></td>
						</tr>
						<tr>
							<td><form:label path="city">City <span
										style="color: red;">*</span>
								</form:label></td>
							
									<td><form:input path="editCity" id="editCity" size="28"
									disabled="true" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="city" id="city" size="28"
									disabled="true" /></td>

						</tr>
						<tr>
							<td><form:label path="companyCIN">CIN of the company <span
										style="color: red;">*</span>
								</form:label></td>
							
									<td><form:input path="editCompanyCIN" id="editCompanyCIN" size="28"
									maxlength="21" disabled="true" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="companyCIN" id="companyCIN" size="28"
									maxlength="21" disabled="true" /></td>

						</tr>
						<tr>
							<td><form:label path="state">State <span
										style="color: red;">*</span>
								</form:label></td>
							
							<td><form:input path="editState" disabled="true" id="editState" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="state" disabled="true" id="state" /></td>

						</tr>
						<tr>
							<td><form:label path="companyPAN">PAN of the company<span
										style="color: red;">*</span>
								</form:label></td>
							
									<td><form:input path="editCompanyPAN" disabled="true"
									id="editCompanyPAN" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="companyPAN" disabled="true"
									id="companyPAN" /></td>
						</tr>
						<tr>
							<td><form:label path="district">District <span
										style="color: red;">*</span>
								</form:label></td>
							
									<td><form:input path="editDistrict" disabled="true"
									id="editDistrict" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="district" disabled="true"
									id="district" /></td>
						</tr>
						<tr></tr>
						<tr>
							<td><form:label path="pincode">Pincode <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editPincode" disabled="true" id="editPincode" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="pincode" disabled="true" id="pincode" /></td>

						</tr>
						<tr>
							<td><form:label path="gstinNumber">GSTIN  No<span
										style="color: red;">*</span>
								</form:label></td>
							
									<td><form:input path="editGstinNumber" disabled="true"
									id="editGstinNumber" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="gstinNumber" disabled="true"
									id="gstinNumber" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><form:label path="landlineNumber">Land line Number <span
										style="color: red;">*</span>
								</form:label></td>
								<td><form:input path="editLandlineNumber" disabled="true"
									id="editLandlineNumber" /></td>
							
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="landlineNumber" disabled="true"
									id="landlineNumber" /></td>
						</tr>
						<tr>
							<td><form:label path="ratingAgency">Rating Agency<span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editRatingAgency" disabled="true"
									id="editRatingAgency" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							
									<td><form:input path="ratingAgency" disabled="true"
									id="ratingAgency" /></td>

						</tr>
						<tr>
							<td><form:label path="emailId">Email ID <span
										style="color: red;">*</span>
								</form:label></td>
							
									<td><form:input path="editEmailId" size="28" disabled="true"
									id="editEmailId" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="emailId" size="28" disabled="true"
									id="emailId" /></td>
						</tr>
						<tr>
							<td><form:label path="rating">Rating <span
										style="color: red;">*</span>
								</form:label></td>
							
							<td><form:input path="editRating" disabled="true" id="editRating" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="rating" disabled="true" id="rating" /></td>
							
						</tr>
						<tr>
							<td><form:label path="contactPerson">Contact Person <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editContactPerson" disabled="true"
									id="editContactPerson" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="contactPerson" disabled="true"
									id="contactPerson" /></td>
						</tr>
						<tr>
						<td><form:label path="ratingDate">Date of Rating<span
										style="color: red;">*</span>
								</form:label></td>
							
							<td><form:input path="editRatingDate" disabled="true" id="editRatingDate"/></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="ratingDate" disabled="true" id="ratingDate"/></td>
						</tr>
						<tr>
							<td><form:label path="mobileNUmber">Mobile No<span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editMobileNUmber" disabled="true"
									id="editMobileNUmber" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							
									<td><form:input path="mobileNUmber" disabled="true"
									id="mobileNUmber" /></td>

						</tr>
						<tr>
							<td><form:label path="faxNumber">Fax Number
					</form:label></td>
							<td><form:input path="editFaxNumber" disabled="true"
									id="editFaxNumber" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="faxNumber" disabled="true"
									id="faxNumber" /></td>

						</tr>
						<tr></tr>
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
							<td colspan="2"><input type="submit" value="approve"
								class="button" name="approve" /></td>
							<td colspan="2"><input value="Reject" name="reject"
								class="button" onclick="disableUnableRemarks()" /></td>
							<td></td>
							<td colspan="2"><input type="submit" value="Close"
								name="close" class="button" /></td>
						</tr>



					</table>

				</div>

				
			</div>
		</form:form>
	</div>



</body>
<script type="text/javascript">
	function disableUnableRemarks() {
		alert('REMARKS1');
		var diveForReject = document.getElementById("REMARKS1");
		diveForReject.style.display = "block";
		var REMARKS = document.getElementById("REMARKS").value;
		alert(REMARKS);
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
		document.getElementById('editableDetailId').style.display = 'none';
		document.getElementById('detailId').style.display = '';
	}
	function editFunction() {
		//alert('edit');
		onchangeApp();
		document.getElementById("viewDetails").disabled = true;
		document.getElementById('detailId').style.display = 'none';
		document.getElementById('editableDetailId').style.display = '';
	}
	function myFunction() {
		document.getElementById('detailId').style.display = 'none';
		document.getElementById('editableDetailId').style.display = 'none';
	}
	function onchangeApp() {
		var mliLongName = document.getElementById("mliLongName").value;
		//alert(portfolioNum);
		try {
			$
					.ajax({
						type : "POST",
						url : "getmliDetailsForApprovalRejection.html",
						data : "mliLongName=" + mliLongName,
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

							var editLongName = data.result.editLongName;
							var editShortName = data.result.editShortName;
							var editCompanyAddress = data.result.editCompanyAddress;
							var editCity = data.result.editCity;
							var editState = data.result.editState;
							var editDistrict = data.result.editDistrict;
							var editPincode = data.result.editPincode;
							var editLandlineNumber = data.result.editLandlineNumber;
							var editEmailId = data.result.editEmailId;
							var editContactPerson = data.result.editContactPerson;
							var editMobileNUmber = data.result.editMobileNUmber;
							var editRBIReggistrationNumber = data.result.editRBIReggistrationNumber;
							var editCompanyCIN = data.result.editCompanyCIN;
							var editCompanyPAN = data.result.editCompanyPAN;
							var editGstinNumber = data.result.editGstinNumber;
							var editRatingAgency = data.result.editRatingAgency;
							var editRating = data.result.editRating;
							var editRatingDate = data.result.editRatingDate;
							var editFaxNumber = data.result.editFaxNumber;

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

							$('#editLongName').val(editLongName);
							$('#editShortName').val(editShortName);
							$('#editCompanyAddress').val(editCompanyAddress);
							$('#editCity').val(editCity);
							$('#editState').val(editState);
							$('#editDistrict').val(editDistrict);
							$('#editPincode').val(editPincode);
							$('#editLandlineNumber').val(editLandlineNumber);
							$('#editEmailId').val(editEmailId);
							$('#editContactPerson').val(editContactPerson);
							$('#editMobileNUmber').val(editMobileNUmber);
							$('#editRBIReggistrationNumber').val(
									editRBIReggistrationNumber);
							$('#editCompanyCIN').val(editCompanyCIN);
							$('#editCompanyPAN').val(editCompanyPAN);
							$('#editGstinNumber').val(editGstinNumber);
							$('#editRatingAgency').val(editRatingAgency);
							$('#editRating').val(editRating);
							$('#editRatingDate').val(editRatingDate);
							$('#editFaxNumber').val(editFaxNumber);

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