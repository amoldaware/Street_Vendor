<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Quarterly File Upload-MLI Maker</title>
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">

<%
	String s=(String)request.getAttribute("SName");
%>

<script type="text/javascript">
	
</script>
</head>
<body onload="errorMessage()" bgcolor="#E0FFFF">
	<h2 align="center">Quarterly File Upload-MLI Maker</h2>
	<font color="green" size="3"><b>${message}</b></font>
	<form:form method="POST" action="/SV/fileUploaded.html"
		enctype="multipart/form-data" id="A">
		<table cellpadding=5 cellspacing=5 align=center width=80% border=0   >
			<tr>
				<td class="tableHeader1" ><form:label path="PORTFOLIO_BASE_YER">Portfolio Base Year  <span	style="color: red;">*</span>
					</form:label></td>
				<td class="DataElement" ><form:select path="PORTFOLIO_BASE_YER"
						onchange="doAjaxPost4()" id="PORTFOLIO_BASE_YER">
						<form:option value="" label="--Select Portfolio Base Year--"/>
						<form:option value="${fYrs}" label="${fYrs}" />
					</form:select></td>				
			</tr>			<tr>
				<td class="tableHeader1" ><form:label path="PORTFOLIO_NAME">Portfolio Name <span
							style="color: red;">*</span>
					</form:label></td>
				<td class="DataElement"><form:select id="PORTFOLIO_NAME" path="PORTFOLIO_NAME" value=""
						onchange="onchangeApp();onchange2()">5
						<form:option label="-Select Portfolio Number-" value="" />
					</form:select>
					</td>				
			</tr>
			<tr>
				<td class="tableHeader1">Portfolio Details :</td>
			<tr id="step1">
				<td><form:input path="interfaceUploadID" type="hidden"></form:input></td>

				<td class="tableHeader1"><form:label path="SENCTIONED_PORTFOLIO"> Max Portfolio Size(Rs.): </form:label></td>
				<td class="DataElement"><form:input path="SENCTIONED_PORTFOLIO" size="28" id="SENCTIONED_PORTFOLIO" disabled="true" style="text-align: right"/></td>				
			</tr>

			<tr id="step2">
				<td></td>
				<td class="tableHeader1" ><form:label path="GUARANTEE_COMMISSION">Guarantee Fee(% p.a):</form:label></td>
				<td class="DataElement" ><form:input path="GUARANTEE_COMMISSION" value="" size="28"
						id="GUARANTEE_COMMISSION" disabled="true" style="text-align: right"/></td>				
			</tr>
			<tr id="step4">
				<td><form:label path="PORTFOLIO_PERIOD" type="hidden"></form:label></td>
				<td class="tableHeader1"><form:label path="PORTFOLIO_PERIOD">Portfolio life(months):</form:label></td>
				<td class="DataElement"><form:input path="PORTFOLIO_PERIOD" value="" size="28"
						id="PORTFOLIO_PERIOD" disabled="true" style="text-align: right" /></td>				
			</tr>

			<tr id="step5">
				<td ><form:label path="PORTFOLIO_START_DATE" type="hidden"></form:label></td>
				<td class="tableHeader1"><form:label path="PORTFOLIO_START_DATE">Portfolio Start Date(dd/mm/yyyy):</form:label></td>
				<td class="DataElement"><form:input path="PORTFOLIO_START_DATE" value="" size="28"	id="PORTFOLIO_START_DATE" disabled="true" style="text-align: right"/></td>				
			</tr>
			<tr id="step3">
				<td class="tableHeader1" ><form:label path="PAYOUT_CAP" type="hidden"></form:label></td>
				<td  class="tableHeader1" ><form:label path="PAYOUT_CAP">Pay-out Cap(% of the Cystalized Portfolio ):</form:label></td>
				<td class="DataElement" ><form:input path="PAYOUT_CAP" value="" size="28" id="PAYOUT_CAP" disabled="true" style="text-align: right"/></td>				
			</tr>
			<tr>
				<td class="tableHeader1"><form:label path="QUARTER_NO">Select Quarter <span
							style="color: red;">*</span>
					</form:label></td>
				<td class="DataElement"><form:select path="QUARTER_NO" id="QUARTER_NO"
						onchange="unableDisable()">
						<form:option value="" label="----Select Quarter Number----" />
					</form:select></td>				
			</tr>		
			<tr>
				<td class="tableHeader1">Upload File:</td>
				<td class="DataElement"><input type="file" name="file" size="28" id="upload"
					disabled /></td>
				<td><form:errors path="file" cssStyle="color: #ff0000;" />
				<input type="submit" value="Upload" class="button" id="uploadBt" onclick="$('#loading').show();" disabled="true" /> 
				<input type="submit" value=" Exit "	onclick="exitMLIDetails()" class="button" />
				<td colspan="2" align="center"></td>
			</tr>
			<tr>
				<form:errors path="file" cssClass="error" />
			</tr>
		</table>

	</form:form> 
	<div id="loading"></div>
	<div id="content"></div>

	<div id="loading"
		style="position: absolute; top: 100px; left: 100px; z-index: 10; display: none">
		<img src="C:/Users/koteswararao.CGTMSE/Desktop/image/Preloader_2.gif"
			alt="Uploading..." /> <br /> wait we are processing your request...
	</div>

	<script type="text/javascript">
	function exitMLIDetails() {
		alert('Exit');
		document.getElementById('A').action = "/SV/nbfcMakerHomeBack.html";
		document.getElementById('A').submit();
	}
		function doAjaxPost4() {
			var portfoBaseYr = document.getElementById("PORTFOLIO_BASE_YER").value;

			try {
				$
						.ajax({
							type : "POST",
							url : "getPortfolioNumber.html",
							data : "portfoBaseYr=" + portfoBaseYr,
							success : function(response) {
								var select2 = document
										.getElementById('PORTFOLIO_NAME');
								if (response.status == "SUCCESS") {
									document.getElementById('PORTFOLIO_NAME').options.length = 0;
									var value = 'Please select Portfolio Number';
									for (var i = 0; i < response.result.length; i++) {
										option = document
												.createElement('option');

										option.text = response.result[i].portfolio_Number;
										option.value = response.result[i].portfolio_Number;
										select2.add(option);
									}
								}
							},
							error : function(e) {
								/* alert('Server Error : ' + e); */
							}
						});

			} catch (err) {
				alert('Error is : ' + err);
			}

		}
		function onchangeApp() {
			var portfolioNum = document.getElementById("PORTFOLIO_NAME").value;
			//alert(portfolioNum);
			try {
				$
						.ajax({
							type : "POST",
							url : "getPortfolioDetails.html",
							data : "portfolioNum=" + portfolioNum,
							success : function(data) {
								var select2 = document
										.getElementById('SENCTIONED_PORTFOLIO');

								var portfolio_max_size = data.result.senctioned_portfolio;
								var guarantee_commission = data.result.guarantee_commission;
								var payout_cap = data.result.payout_cap;
								var portfolio_start_date = data.result.portfolio_start_date;
								var portfolio_period = data.result.portfolio_period;
								$('#SENCTIONED_PORTFOLIO').val(
										portfolio_max_size);
								$('#GUARANTEE_COMMISSION').val(
										guarantee_commission);
								$('#PAYOUT_CAP').val(payout_cap);
								$('#PORTFOLIO_PERIOD').val(portfolio_period);
								$('#PORTFOLIO_START_DATE').val(
										portfolio_start_date);
								//alert(portfolioNum+' '+portfolio_base_yer+' '+senctioned_portfolio+''+guarantee_commission+''+payout_cap+''+portfolio_start_date+''+typeOfLone+''+portfolio_period);

							},
							error : function(e) {
								alert('Server Error : ' + e);
							}
						});

			} catch (err) {
				alert('Error is : ' + err);
			}

		}
		function onchange2() {
			var portfolioNumber = document.getElementById("PORTFOLIO_NAME").value;
            
			try {
				$
						.ajax({
							type : "POST",
							url : "getQuarterNumber.html",
							data : "portfolioNumber=" + portfolioNumber,
							success : function(response) {
								var select2 = document
										.getElementById('QUARTER_NO');
								if (response.status == "SUCCESS") {
									document.getElementById('QUARTER_NO').options.length = 0;
									var value = 'Please select Portfolio Number';
									for (var i = 0; i < response.result.length; i++) {
										option = document
												.createElement('option');

										option.text = response.result[i].portfolioQuarter;
										option.value = response.result[i].sub_portfolio_dt_no;
										select2.add(option);
									}
								}
							},
							error : function(e) {
								//alert('Server Error : ' + e);
							}
						});

			} catch (err) {
				//alert('Error is : ' + err);
			}

		}
		function unableDisable() {
			document.getElementById("upload").disabled = false;
			document.getElementById("uploadBt").disabled = false;
		}
		function preloader() {
			document.getElementById("loading").style.display = "none";
			document.getElementById("content").style.display = "block";
		}//preloader
		window.onload = preloader;
	</script>
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<div class="loader"></div>
	<script type="text/javascript">
		$(window).load(function() {
			$(".loader").fadeOut("slow");
		});
	</script>
</body>
</html>