<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MLI Registration Form</title>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/nbfc.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">



<script type="text/javascript">
	$(function() {
		$('.ratingDate')
				.datepicker(
						{
							changeMonth : true,
							changeYear : true,
							showButtonPanel : true,
							dateFormat : 'MM yy',
							onClose : function(dateText, inst) {
								var month = $(
										"#ui-datepicker-div .ui-datepicker-month :selected")
										.val();
								var year = $(
										"#ui-datepicker-div .ui-datepicker-year :selected")
										.val();
								$(this).datepicker('setDate',
										new Date(year, month, 1));
							}
						});
	});
</script>
<%
	String s=(String)request.getAttribute("SName");
%>

<script type="text/javascript">
	$(window).load(function() {
		$(".loader").fadeOut("slow");
	})
</script>

</head>
<body onload="test()">
<!-- <div class="main-section">
<div class="container-fluid">
<div class="row">
	<div>	
		<div class="frm-section">
		<div class="col-md-12">

		</div>
		</div>
	</div>
</div>
</div> -->
	<h2 align="center">MLI Registration Form</h2>
	<font color="green" size="3"><b>${message}</b></font>
	<hr />
	<div
		STYLE="height: 340px; width: auto; font-size: 12px; overflow: auto;">

		<form:form method="GET" action="/SV/saveMLIDetails.html">
			<b><table align="center" style="color: #5c4324;">
					<tr>
						<td><form:label path="longName">Long Name <span
									style="color: red;">*</span>
							</form:label></td>
						<td><form:input path="longName" size="28" /></td>
						<td><form:errors path="longName" cssClass="error" /></td>
						<td><form:input path="" size="28" type="hidden" /></td>

						<td><form:label path="shortName">Short Name <span
									style="color: red;">*</span>
							</form:label></td>
						<td><form:input path="shortName" size="28" /></td>
						<td><form:errors path="shortName" cssClass="error" /></td>
					</tr>
					<tr>
						<td><form:label path="companyAddress">Registered Address of the Company<span
									style="color: red;">*</span>
							</form:label></td>
						<td><form:input path="companyAddress" size="28" /></td>
						<td><form:errors path="companyAddress" cssClass="error" /></td>
						<td><form:input path="" size="28" type="hidden" /></td>
						<td><form:label path="rbiReggistrationNumber">RBI registration No <span
									style="color: red;">*</span>
							</form:label></td>
						<td><form:input path="rbiReggistrationNumber" size="28"
								maxlength="15" /></td>
						<td><form:errors path="rbiReggistrationNumber"
								cssClass="error" /></td>

					</tr>
					<tr>
						<td><form:label path="city">City <span
									style="color: red;">*</span>
							</form:label></td>
						<td><form:input path="city" size="28" /></td>
						<td><form:errors path="city" cssClass="error" /></td>
						<td><form:input path="" size="28" type="hidden" /></td>
						<td><form:label path="companyCIN">CIN of the company <span
									style="color: red;">*</span>
							</form:label></td>
						<td><form:input path="companyCIN" size="28" maxlength="21" /></td>
						<td><form:errors path="companyCIN" cssClass="error" /></td>

					</tr>
					<tr>
						<td><form:label path="state">State <span
									style="color: red;">*</span>
							</form:label></td>
						<td><form:select path="state" id="state"
								onchange="doAjaxPost4()">
								<form:option value=""
									label="-------------Select State----------" />
								<c:forEach var="state" items="${stateList}">
									<form:option id="${state.ste_code}" value="${state.ste_code}">${state.ste_name}</form:option>
								</c:forEach>
							</form:select></td>

						<td><form:errors path="state" cssClass="error" /></td>
						<td><form:input path="" size="28" type="hidden" /></td>

						<td><form:label path="companyPAN">PAN of the company<span
									style="color: red;">*</span>
							</form:label></td>
						<td><form:input path="companyPAN" size="28" maxlength="11" /></td>
						<td><form:errors path="companyPAN" cssClass="error" /></td>
					</tr>
					<tr>
						<td><form:label path="district">District <span
									style="color: red;">*</span>
							</form:label></td>
						<td><form:select id="district" path="district">
								<form:option value=""
									label="----------Select District------------" />
							</form:select></td>
						<td><form:errors path="district" cssClass="error" /></td>
						<td><form:input path="" size="28" type="hidden" /></td>
						<td><form:label path="gstinNumber">GSTIN  No<span
									style="color: red;">*</span>
							</form:label></td>
						<td><form:input path="gstinNumber" size="28" maxlength="15" /></td>
						<td><form:errors path="gstinNumber" cssClass="error" /></td>
					</tr>
					<tr>
						<td><form:label path="pincode">Pincode <span
									style="color: red;">*</span>
							</form:label></td>
						<td><form:input path="pincode" size="28" maxlength="6" /></td>
						<td><form:errors path="pincode" cssClass="error" /></td>
						<td><form:input path="" size="28" type="hidden" /></td>
						<td><form:label path="ratingAgency">Rating Agency<span
									style="color: red;">*</span>
							</form:label></td>
						<td><form:input path="ratingAgency" size="28" /></td>
						<td><form:errors path="ratingAgency" cssClass="error" /></td>

					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td><form:label path="landlineNumber">Land line Number <span
									style="color: red;">*</span>
							</form:label></td>
						<td><form:input path="landlineNumber" size="28"
								maxlength="11" /></td>
						<td><form:errors path="landlineNumber" cssClass="error" /></td>
						<td><form:input path="" size="28" type="hidden" /></td>
						<td><form:label path="rating">Rating <span
									style="color: red;">*</span>
							</form:label></td>
						<td><form:input path="rating" size="28" /></td>
						<td><form:errors path="rating" cssClass="error" /></td>

					</tr>
					<tr>
						<td><form:label path="emailId">Email ID <span
									style="color: red;">*</span>
							</form:label></td>
						<td><form:input path="emailId" size="28" /></td>
						<td><form:errors path="emailId" cssClass="error" /></td>
						<td><form:input path="" size="28" type="hidden" /></td>
						<td><form:label path="ratingDate">Date of Rating<span
									style="color: red;">*</span>
							</form:label></td>
						<td><form:input path="ratingDate" class="date-picker"/></td>
						<td><form:errors path="ratingDate" cssClass="error" /></td>

					</tr>
					<tr>
						<td><form:label path="contactPerson">Contact Person <span
									style="color: red;">*</span>
							</form:label></td>
						<td><form:input path="contactPerson" size="28" /></td>
						<td><form:errors path="contactPerson" cssClass="error" /></td>

					</tr>
					<tr>
						<td><form:label path="mobileNUmber">Mobile No<span
									style="color: red;">*</span>
							</form:label></td>
						<td><form:input path="mobileNUmber" size="28" maxlength="10" /></td>
						<td><form:errors path="mobileNUmber" cssClass="error" /></td>

					</tr>

					<tr>
						<td><form:label path="faxNumber">Fax Number
					</form:label></td>
						<td><form:input path="faxNumber" size="28" maxlength="11" /></td>
						<td><form:errors path="faxNumber" cssClass="error" /></td>

					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="Submit"
							class="button" name="save" /></td>
						<td colspan="2"><input type="submit" value="Reset"
							name="reset" class="button" /></td>
						<td></td>
						<td colspan="2"><input type="submit" value="Exit" name="Exit"
							class="button" /></td>
					</tr>
				</table></b>
		</form:form>
	</div>
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<div class="loader"></div>

	<script type="text/javascript">
		/* function doAjaxPost4() {
			var state = document.getElementById("state").value;
			try {
				$
						.ajax({
							type : "POST",
							url : "getDistrict.html",
							data : "state=" + state,
							success : function(response) {
								var select2 = document
										.getElementById('district');
								if (response.status == "SUCCESS") {
									document.getElementById('district').options.length = 0;
									for (var i = 0; i < response.result.length; i++) {
										option = document
												.createElement('option');
										option.text = response.result[i].dst_name;
										select2.add(option);
									}
								}
							},
							error : function(e) {
								alert('Server Error : ' + e);
							}
						});

			} catch (err) {
				alert('Error is : ' + err);
			}
		}
		 */
	</script>

</body>
</html>