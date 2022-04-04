<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Quarterly File Upload Approval-MLI Checker</title>
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">

<%
	String s = (String) request.getAttribute("SName");
%>

</head>
<body onload="linkFunction()" bgcolor="#E0FFFF">
<div class="main-section">
<div class="container-fluid">
<!-- <div class="row"> -->
	<div>	
		<div class="frm-section">
		<div class="col-md-12">
		<h5 class="notification-message"><strong>${message}</strong></h5>
			<form:form method="POST" id="A" action="/nbfc/mliCheckerUpdateFirst.html" class="form-horizontal">			
			<div class="col-md-3 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			   <%-- <form:label path="PORTFOLIO_START_DATE">Portfolio Start Date(dd/mm/yyyy):</form:label> --%>
			   <form:label path="EXP_NO" type="hidden"></form:label>
			   <form:label path="EXP_NO">Exposure Name:</form:label>
			    <!-- <div class="col-sm-8"> -->			    
			    <form:input path="EXP_NO" value="" size="28" id="EXP_NO" disabled="true" class="form-control cus-control" style="text-align: right"/>	      
			    <!-- </div> -->		
			    </div>	    			   
			  </div>
			</div>
			<div class="col-md-3 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			<form:label path="PORTFOLIO_NO">Select Portfolio Name <span style="color: red;">*</span> </form:label>				
			    <!-- <div class="col-sm-8"> -->
			 <form:select path="PORTFOLIO_NO" id="PORTFOLIO_NO" class="form-control cus-control"
							name="PORTFOLIO_NO" onchange="onchangeApp();disableUnable()">
							<form:option value="" label="--Select Portfolio Name--"/>
					<c:forEach var="PORTFOLIO_NO" items="${appRefNoList}">
						<form:option id="${PORTFOLIO_NO.arp_ref_no}"
							value="${PORTFOLIO_NO.arp_ref_no}">${PORTFOLIO_NO.arp_ref_no}</form:option>
					</c:forEach>
				</form:select>  
			    <!-- </div> -->	
			    </div>		    			   
			  </div>
			</div>

	<div class="clearfix"></div>
	<hr style=" margin: 5px 0 10px;  border: 1px solid #d8d8d8;">
	
	<div class="col-md-2 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			   <form:label path="TOT_EXP_SIZE" >Total Exposure Size(Rs.): </form:label>
			   <!--  <div class="col-sm-8"> -->
			    <form:input path="TOT_EXP_SIZE" type="hidden"></form:input>
			    <form:input path="TOT_EXP_SIZE" size="28" id="TOT_EXP_SIZE" class="form-control cus-control" disabled="true" style="text-align: right"/>	      
			  <!--   </div>	 -->	
			  </div>	    			   
			  </div>
			</div>		
			
			<div class="col-md-2 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			   <form:label path="UTIL_EXP">Utilized Exposure(Rs.):</form:label>
			    <!-- <div class="col-sm-8"> -->
			    <form:input path="UTIL_EXP"  size="28" class="form-control cus-control" id="UTIL_EXP" disabled="true" style="text-align: right" />	      
			    <!-- </div> -->	
			    </div>		    			   
			  </div>
			</div> 								
			<div class="col-md-2 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			   <form:label path="PENDING_EXP">Pending Exposure(Rs.):</form:label>
			    <!-- <div class="col-sm-8"> -->
			    <form:input path="PENDING_EXP" value="" size="28" class="form-control cus-control" id="PENDING_EXP" disabled="true" style="text-align: right" />	      
			    <!-- </div> -->	
			    </div>		    			   
			  </div>
			</div> 
					
			
							
			<div class="clearfix"></div>
			<hr style=" margin: 5px 0;  border: 1px solid #d8d8d8;">
			<div class="col-md-3 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			   <%-- <form:label path="PORTFOLIO_START_DATE">Portfolio Start Date(dd/mm/yyyy):</form:label> --%>
			   <form:label path="PAYOUT_CAP" type="hidden"></form:label>
			   <form:label path="PAYOUT_CAP">Pay-out Cap:</form:label>
			    <!-- <div class="col-sm-8"> -->			    
			    <form:input path="PAYOUT_CAP" value="" size="28" id="PAYOUT_CAP" disabled="true" class="form-control cus-control" style="text-align: right"/>	      
			    <!-- </div> -->		
			    </div>	    			   
			  </div>
			</div>
			<div class="col-md-3 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			   <%-- <form:label path="PORTFOLIO_START_DATE">Portfolio Start Date(dd/mm/yyyy):</form:label> --%>
			   <form:label path="GURANTEE_COVERAGE" type="hidden"></form:label>
			   <form:label path="GURANTEE_COVERAGE">GURANTEE COVERAGE:</form:label>
			    <!-- <div class="col-sm-8"> -->			    
			    <form:input path="GURANTEE_COVERAGE" value="" size="28" id="GURANTEE_COVERAGE" disabled="true" class="form-control cus-control" style="text-align: right"/>	      
			    <!-- </div> -->		
			    </div>	    			   
			  </div>
			</div>
			<div class="col-md-3 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			   <%-- <form:label path="PORTFOLIO_START_DATE">Portfolio Start Date(dd/mm/yyyy):</form:label> --%>
			   <form:label path="GURANTEE_FEE" type="hidden"></form:label>
			   <form:label path="GURANTEE_FEE">GURANTEE FEE:</form:label>
			    <!-- <div class="col-sm-8"> -->			    
			    <form:input path="GURANTEE_FEE" value="" size="28" id="GURANTEE_FEE" disabled="true" class="form-control cus-control" style="text-align: right"/>	      
			    <!-- </div> -->		
			    </div>	    			   
			  </div>
			</div>							
			
			<% String portfolioNo="dd"; %>
			
			<div class="d-inlineblock mt-25">
				<input type="button" value="Accept" id="acceptRecords" class="btn btn-reset"
						 onclick="linkFunctionShow()" disabled="true">
				<input type="button" value="Reject"  class="btn btn-reset" name="action2" id="rejectRecords" disabled="true" 
						onclick="disableUnableRemarks()" />												
			</div>
			<div class="clearfix"></div>
			<div class="d-inlineblock">
				<input value="Success Records"  type="submit" class="btn btn-reset"	name="action3" disabled="true" id="showSuccessRecords" onclick="showSuccess()" />
				<input value="Failed Records"  type="submit" class="btn btn-reset" name="action4" disabled="true" id="ShowFailedRecords" onclick="showFailed()"/>
			</div>
			
			<div class="clearfix"></div>
			<hr style=" margin: 5px 0 10px;  border: 1px solid #d8d8d8;">
			
			<div class="col-md-4 prl-5">
			<c:set var="contextPath" value="${pageContext.request.contextPath}" />
			<span style="color: red;">*</span><form:checkbox path="acceptStatus" value="Y" id="acceptStatus"/>
			<form:errors path="acceptStatus" cssClass="error" />	      
			We the MLI certify and forward the Management Certificate.</br> <a href="${contextPath}/nbfcCertificate.html" >
			Click here to certify and forward the Management Certificate</a>			
			</div>
			
			<div class="d-inlineblock">
				<input value="Forward to CGTMSE" class="btn btn-reset" id="accept" name="action1" disabled="true" onclick="clickToForwordCGTMSE()">
				<input type="submit" value=" Exit "	onclick="exitMLIDetails()" class="btn btn-reset" />											
			</div>
			
			</form:form>				
		</div>
		</div>
		
	</div>	
</div>
</div>		


	<%-- <h2 align="center">Quarterly File Upload Approval-MLI Checker</h2>
	<font color="green" size="3"><b>${message}</b></font>
	<div>
		<form:form method="POST" id="A" action="/nbfc/mliCheckerUpdateFirst.html">
			<table align="center">
				<tr>
					<td><form:label path="PORTFOLIO_NO">Select Portfolio No.. <span style="color: red;">*</span>
						</form:label></td>
				      <td><form:select path="PORTFOLIO_NO" id="PORTFOLIO_NO"
							name="PORTFOLIO_NO" onchange="onchangeApp();disableUnable()">
							<form:option value="" label="--Select Portfolio Number--"
								/>
							<c:forEach var="PORTFOLIO_NO" items="${appRefNoList}">
								<form:option id="${PORTFOLIO_NO.arp_ref_no}"
									value="${PORTFOLIO_NO.arp_ref_no}">${PORTFOLIO_NO.arp_ref_no}</form:option>
							</c:forEach>
						</form:select></td> 
						
						
					<td><form:errors path="PORTFOLIO_NO" cssClass="error" /></td>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr id="step1">
					<td><form:label path="SENCTIONED_PORTFOLIO" type="hidden"></form:label></td>

					<td><form:label path="SENCTIONED_PORTFOLIO">Max Portfolio Size(Rs.) :</form:label></td>
					<td><form:input path="SENCTIONED_PORTFOLIO" value="" size="28"
							id="SENCTIONED_PORTFOLIO" disabled="true" style="text-align: right" /></td>
					<td><form:errors path="SENCTIONED_PORTFOLIO" cssClass="error" /></td>
				</tr>
				<tr id="step2">
					<td><form:label path="GUARANTEE_COMMISSION" type="hidden"></form:label></td>
					<td><form:label path="GUARANTEE_COMMISSION">Guarantee Fee (% p.a) :</form:label></td>
					<td><form:input path="GUARANTEE_COMMISSION" value="" size="28"
							id="GUARANTEE_COMMISSION" disabled="true" style="text-align: right"/></td>
					<td><form:errors path="GUARANTEE_COMMISSION" cssClass="error" /></td>
				</tr>
				<tr id="step4">
					<td><form:label path="PORTFOLIO_PERIOD" type="hidden"></form:label></td>
					<td><form:label path="PORTFOLIO_PERIOD">Portfolio in Months :</form:label></td>
					<td><form:input path="PORTFOLIO_PERIOD" value="" size="28"
							id="PORTFOLIO_PERIOD" disabled="true" style="text-align: right"/></td>
					<td><form:errors path="PORTFOLIO_PERIOD" cssClass="error" /></td>
				</tr>

				<tr id="step5">
					<td><form:label path="PORTFOLIO_START_DATE" type="hidden"></form:label></td>
					<td><form:label path="PORTFOLIO_START_DATE">Portfolio Start Date :</form:label></td>
					<td><form:input path="PORTFOLIO_START_DATE" value="" size="28"
							id="PORTFOLIO_START_DATE" disabled="true" style="text-align: right"/></td>
					<td><form:errors path="PORTFOLIO_START_DATE" cssClass="error" /></td>
				</tr>
				<tr id="step3">
					<td><form:label path="PAYOUT_CAP" type="hidden"></form:label></td>
					<td><form:label path="PAYOUT_CAP">Pay-Out Cap (%) :</form:label></td>
					<td><form:input path="PAYOUT_CAP" value="" size="28"
							id="PAYOUT_CAP" disabled="true" style="text-align: right"/></td>
					<td><form:errors path="PAYOUT_CAP" cssClass="error" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td></td>

					<td><input value=""  type="submit" class="button12"	name="action3" disabled="true" id="showSuccessRecords" onclick="showSuccess()" target="_blank"/></td>
					<td><input value=""  type="submit" class="button12" name="action4" disabled="true" id="ShowFailedRecords" onclick="showFailed()" target="_blank"/></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>
				<%
					String portfolioNo="dd";
				%>
				<tr>
					<td colspan="2">
					<input type="button" value="Accept" id="acceptRecords"
						class="button" onclick="linkFunctionShow()" disabled="true">
						<input type="button" value="Reject" class="button" name="action2"
						id="rejectRecords" disabled="true" 
						onclick="disableUnableRemarks()" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr id="remarks1" class="toshow" style="display: none">
					<td><form:label path="REMARKS" id="REMARKS">Remarks :</form:label></td>
					<td><form:input path="REMARKS" size="50"/></td>
					<td><form:errors path="REMARKS" cssClass="error" /></td>
				</tr>
				<tr id="REMARKS1" class="toshow" style="display: none" >
					<td><form:label path="REMARKS" >Remarks :</form:label></td>
					<td><form:input path="REMARKS" id="REMARKS" size="50"/></td>
					<td><form:errors path="REMARKS" cssClass="error" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr id="link">
					<td></td>
					<c:set var="contextPath" value="${pageContext.request.contextPath}" />
					<td><span style="color: red;">*</span><form:checkbox path="acceptStatus" value="Y" id="acceptStatus"/>We the MLI certify and forward the Management Certificate.</br> <a
						href="${contextPath}/nbfcCertificate.html" target="_blank">Click here to certify and forward the Management Certificate</a></td>
					<td></td>
				</tr>
				<tr>
					<td><form:errors path="acceptStatus" cssClass="error" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr align="center">
					<td colspan="2"><input value="Forward to CGTMSE" class="button" id="accept" name="action1" disabled="true" onclick="clickToForwordCGTMSE()"></td>
				<td colspan="2" align="center"><input type="submit" value=" Exit "	onclick="exitMLIDetails()" class="button" /></td>
				
				</tr>
	
				<tr></tr>
			</table>
		</form:form>
	</div> --%>


	<script type="text/javascript">
	function exitMLIDetails() {
		//alert('Exit');
		document.getElementById('A').action = "/SV/nbfcCheckerHomeBack.html";
		document.getElementById('A').submit();
	}
		function disableUnableRemarks() {
		
			var diveForReject = document.getElementById("REMARKS1");
			diveForReject.style.display = "block";
			var REMARKS = document.getElementById("REMARKS").value;
			alert(REMARKS);
		if(REMARKS!=''){
		 document.getElementById("A").submit();
		}else{
			diveForReject.style.display = "block";
			alert('Please fill the Remarks');
			}
		}
function clickToForwordCGTMSE(){
	//alert('save');
	var acceptStatus = document.getElementById("acceptStatus").value;
	//alert(acceptStatus);
	//form.target='_parent';
	form=document.getElementById('A');
	form.target='_parent';
	//form.target='_blank';
	document.getElementById('A').action = "/SV/mliCheckerUpdateFirstForwordToCGTMSC.html";
	document.getElementById('A').submit();
}
function showSuccess(){
	/* alert('success');
	form=document.getElementById('A');
	form.target='_blank';
	form.action="/nbfc/mliCheckerUpdateFirstShowSuccessRecord.html";
	form.submit(); */
	//alert('success');
	form=document.getElementById('A');
	form.target='_blank';
	form.action="/SV/mliCheckerUpdateFirstShowSuccessRecord.html";
	form.submit();
	/* document.getElementById('A').action = "/nbfc/mliCheckerUpdateFirstShowSuccessRecord.html";
	document.getElementById('A').submit(); */
}
function showFailed(){
	//alert('success');
	form=document.getElementById('A');
	form.target='_blank';
	form.action="/SV/mliCheckerUpdateFirstShowFailedRecord.html";
	form.submit();
	/* document.getElementById('A').action = "/nbfc/mliCheckerUpdateFirstShowSuccessRecord.html";
	document.getElementById('A').submit(); */
}
		function onchangeApp() {
			var portfolioNum = document.getElementById("PORTFOLIO_NO").value;
		//	alert(portfolioNum);
			try {
				$
						.ajax({
							type : "POST",
							url : "getPortfolioDetails.html",
							data : "portfolioNum=" + portfolioNum,
							success : function(data) {
								var select2 = document
										.getElementById('SENCTIONED_PORTFOLIO');

								var portfolioNum = data.result.portfolio_Number;

								var portfolio_max_size = data.result.senctioned_portfolio;
								var guarantee_commission = data.result.guarantee_commission;
								var payout_cap = data.result.payout_cap;
								var portfolio_start_date = data.result.portfolio_start_date;
								var dout = Date.parse(portfolio_start_date);
								var portfolioFailedRecords = data.result.portfolioFailedRecords;
								var portfolioSuccessRecords = data.result.portfolioSuccessRecords;
								var portfolio_period = data.result.portfolio_period;
								$('#SENCTIONED_PORTFOLIO').val(
										portfolio_max_size);
								$('#GUARANTEE_COMMISSION').val(
										guarantee_commission);
								$('#PAYOUT_CAP').val(payout_cap);
								$('#PORTFOLIO_PERIOD').val(portfolio_period);
								$('#PORTFOLIO_START_DATE').val(
										portfolio_start_date);
								$('#showSuccessRecords').val(
										portfolioSuccessRecords);

								$('#ShowFailedRecords').val(
										portfolioFailedRecords);

								//alert(portfolioNum+' '+portfolio_base_yer+' '+senctioned_portfolio+''+guarantee_commission+''+payout_cap+''+portfolio_start_date+''+typeOfLone+''+portfolio_period);

							},
							error : function(e) {

							}
						});

			} catch (err) {
				//alert('Error is : ' + err);
			}

		} 

		
		function linkFunction() {
			var diveForReject = document.getElementById("link");
			diveForReject.style.display = "none";
			
 
		}

		function linkFunctionShow() {
			var diveForReject = document.getElementById("link");
			document.getElementById("accept").disabled = false;
			diveForReject.style.display = "block";

		}
		function viewRemarks() {
			alert('Please fill the remarks');
			var diveForReject = document.getElementById("remark");

			if (diveForReject.style.display !== "none") {
				diveForReject.style.display = "none";

			} else {
				diveForReject.style.display = "block";

			}

		}
		function disableUnable() {
			document.getElementById("acceptRecords").disabled = false;
			document.getElementById("rejectRecords").disabled = false;
			document.getElementById("ShowFailedRecords").disabled = false;
			document.getElementById("showSuccessRecords").disabled = false;
		}
		
	</script>
</body>
</html>