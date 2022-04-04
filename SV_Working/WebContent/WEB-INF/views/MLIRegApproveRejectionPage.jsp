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

	<h2 align="center">MLI Approval/Reject Screen</h2>
	<font color="green" size="3"><b>${message}</b></font>
	<div>
		<form:form method="POST" id="A"	>
			<div
				STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;">

				<table align="center">
					<tr>
						<td><form:label path="mliLongName">Select MLI Name :<span
									style="color: red;">*</span>
							</form:label></td>
						<td><form:select path="mliLongName" id="ViewFileList"
								name="mliLongName" onchange="onchangeApp()">
								<form:option value="" label="-------------Select Portfolio Name------------" />
								<c:forEach var="mliLongName" items="${mliNameList}">
									<form:option id="${mliLongName.longName}"
										value="${mliLongName.longName}">${mliLongName.longName}</form:option>
								</c:forEach>
							</form:select></td>
							
						<td><form:errors path="mliLongName" cssClass="error" /></td>
						<td><form:input path="" type="hidden"></form:input></td>
						
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
							<td><form:input path="longName" id="longName" size="28" disabled="true" /></td>
							<td><form:errors path="longName" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>

							<td><form:label path="shortName">Short Name <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="shortName" id="shortName" size="28" disabled="true" /></td>
							<td><form:errors path="shortName" cssClass="error" /></td>
						</tr>
						<tr>
							<td><form:label path="companyAddress">Registered Address of the Company<span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="companyAddress" id="companyAddress" size="28" disabled="true" /></td>
							<td><form:errors path="companyAddress" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:label path="rbiReggistrationNumber">RBI registration No <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="rbiReggistrationNumber" size="28"
									maxlength="15" disabled="true" id="rbiReggistrationNumber"/></td>
							<td><form:errors path="rbiReggistrationNumber"
									cssClass="error" /></td>

						</tr>
						<tr>
							<td><form:label path="city">City <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="city" id="city" size="28" disabled="true"/></td>
							<td><form:errors path="city" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:label path="companyCIN">CIN of the company <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="companyCIN" id="companyCIN" size="28" maxlength="21" disabled="true"/></td>
							<td><form:errors path="companyCIN" cssClass="error" /></td>

						</tr>
						<tr>
							<td><form:label path="state">State <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="state" disabled="true" id="state"/></td>

							<td><form:errors path="state" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>

							<td><form:label path="companyPAN">PAN of the company<span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="companyPAN" disabled="true" id="companyPAN"/></td>
							<td><form:errors path="companyPAN" cssClass="error" /></td>
						</tr>
						<tr>
							<td><form:label path="district">District <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="district" disabled="true" id="district"/></td>
							<td><form:errors path="district" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:label path="gstinNumber">GSTIN  No<span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="gstinNumber" disabled="true" id="gstinNumber"/></td>
							<td><form:errors path="gstinNumber" cssClass="error" /></td>
						</tr>
						<tr>
							<td><form:label path="pincode">Pincode <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="pincode"  disabled="true" id="pincode"/></td>
							<td><form:errors path="pincode" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:label path="ratingAgency">Rating Agency<span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="ratingAgency" disabled="true" id="ratingAgency"/></td>
							<td><form:errors path="ratingAgency" cssClass="error" /></td>

						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><form:label path="landlineNumber">Land line Number <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="phoneCode"  disabled="true" id="phoneCode" size="3"/><form:input path="landlineNumber"  disabled="true" id="landlineNumber"/></td>
							<td><form:errors path="landlineNumber" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:label path="rating">Rating <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="rating"  disabled="true" id="rating" /></td>
							<td><form:errors path="rating" cssClass="error" /></td>

						</tr>
						<tr>
							<td><form:label path="emailId">Email ID <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="emailId" size="28" disabled="true" id="emailId"/></td>
							<td><form:errors path="emailId" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:label path="ratingDate">Date of Rating<span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="ratingDate" disabled="true" id="ratingDate"/></td>
							<td><form:errors path="ratingDate" cssClass="error" /></td>

						</tr>
						<tr>
							<td><form:label path="contactPerson">Contact Person <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="contactPerson"  disabled="true" id="contactPerson"/></td>
							<td><form:errors path="contactPerson" cssClass="error" /></td>

						</tr>
						<tr>
							<td><form:label path="mobileNUmber">Mobile No<span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="mobileNUmber" disabled="true" id="mobileNUmber" /></td>
							<td><form:errors path="mobileNUmber" cssClass="error" /></td>

						</tr>

						<tr>
							<td><form:label path="faxNumber">Fax Number
					</form:label></td>
							<td><form:input path="faxCode" disabled="true" id="faxCode" size="3"/><form:input path="faxNumber" disabled="true" id="faxNumber"/></td>
							<td><form:errors path="faxNumber" cssClass="error" /></td>

						</tr>
						
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr id="remarks1" class="toshow" style="display: none">
					<td><form:label path="remarks" id="remarksLable">Remarks :</form:label></td>
					<td><form:input path="remarks" size="40"/></td>
					<td><form:errors path="remarks" cssClass="error" /></td>
				</tr>
				<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2"><input value="Approve"
								class="button" onclick="approveMLiDetails()"/></td>
							<td colspan="2"><input value="Reject"
								class="button" onclick="rejectMLIDetails()"/></td>
							<td></td>
							<td colspan="2"><input value="Exit"
								 class="button" onclick="exitMLIDetails()"/></td>
						</tr>



					</table>
        
				</div>
			</div>
		</form:form>
	</div>


</body>
<script type="text/javascript">

function approveMLiDetails() {
	alert('Approve');
	var mliName = document.getElementById("mliLongName");
	if(mliName!=''){
		 document.getElementById('A').action = "/SV/approveMLIDeatails.html";
		    document.getElementById('A').submit();
	}else{
		alert('Please Select Remarks');
		}
	
	//alert(mliName);
   
}

function rejectMLIDetails() {
	
	var diveForReject = document.getElementById("REMARKS1");
	diveForReject.style.display = "block";
	var REMARKS = document.getElementById("REMARKS").value;
	
if(REMARKS!=''){
	document.getElementById('A').action = "/SV/rejectMLIDetails.html";
    document.getElementById('A').submit();
}else{
	diveForReject.style.display = "block";
	alert('Please fill the Remarks');
	}
	
}

function exitMLIDetails() {
	alert('Exit');
    document.getElementById('A').action = "/SV/exitPage.html";
    document.getElementById('A').submit();
}
function disableUnableRemarks() {
	
	var diveForReject = document.getElementById("REMARKS1");
	diveForReject.style.display = "block";
	var REMARKS = document.getElementById("REMARKS").value;
	//alert(REMARKS);
if(REMARKS!=''){
	
	 document.getElementById("A").submit();
}else{
	diveForReject.style.display = "block";
	alert('Please fill the Remarks');
	}
}
function myFunction(){
	alert('view');
	document.getElementById('detailId').style.display = '';
	}
function myFunction1(){
	document.getElementById('detailId').style.display = 'none';
	}
	function onchangeApp() {
		var portfolioNum = document.getElementById("mliLongName").value;
		alert(portfolioNum);
		try {
			$.ajax({
				type : "POST",
				url : "getmliDetails.html",
				data : "portfolioNum=" + portfolioNum,
				success : function(data) {

					var   longName	=	data.result.longName;
					var   shortName	=	data.result.shortName;
					var   companyAddress	=	data.result.companyAddress;
					var   city	=	data.result.city;
					var   state	=	data.result.state;
					var   district	=	data.result.district;
					var   pincode	=	data.result.pincode;
					var   landlineNumber	=	data.result.landlineNumber;
					var   emailId	=	data.result.emailId;
					var   contactPerson	=	data.result.contactPerson;
					var   mobileNUmber	=	data.result.mobileNUmber;
					var   faxNumber	=	data.result.faxNumber;
					var   rbiReggistrationNumber	=	data.result.rbiReggistrationNumber;
					var   companyCIN	=	data.result.companyCIN;
					var   companyPAN	=	data.result.companyPAN;
					var   gstinNumber	=	data.result.gstinNumber;
					var   ratingAgency	=	data.result.ratingAgency;
					var   rating	=	data.result.rating;
					var   ratingDate	=	data.result.ratingDate;
					var   phoneCode	=	data.result.phone_code;
					var   faxCode	=	data.result.faxCode;
										
					$('#phoneCode').val(phoneCode);
					$('#faxCode').val(faxCode);
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
					$('#rbiReggistrationNumber').val(rbiReggistrationNumber);
					$('#companyCIN').val(companyCIN);
					$('#companyPAN').val(companyPAN);
					$('#gstinNumber').val(gstinNumber);
					$('#ratingAgency').val(ratingAgency);
					$('#rating').val(rating);
					$('#ratingDate').val(ratingDate);
					$('#faxNumber').val(faxNumber);
					
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
</script>
</html>