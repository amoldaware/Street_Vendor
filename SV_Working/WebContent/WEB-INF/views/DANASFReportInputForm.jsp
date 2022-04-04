<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NPA Report Form</title>

<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<LINK href="<%=request.getContextPath()%>/css/stylesheet.css"
	rel="stylesheet" type="text/css">

<link href="css/jquery-ui-css.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<%
	String s = (String) request.getAttribute("SName");
%>
<script language="javascript">
	function submitMLIForm() {
		document.getElementById('A').action = "/SV/DANASFReportDetailList.html";
		document.deliveryForm.submit();
	}
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
</script>
</head>
<body>
	<div class="main-section">
		<div class="container-fluid">
			<div>
			
				<div class="frm-section">
				 
					<div class="col-md-12">
						 <h2 align="center" class="heading">DAN ASF Report</h2>
						<h5 class="notification-message">${message}</h5>
						<h5 class="error1">${Errormessage}</h5>
						<div class="loader"></div>
						<form:form method="POST" action="" id="A" class="form-horizontal">
							<%
								String userRole = (String) session.getAttribute("uRole");
							%>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<label class="d-block text-purple" style="visibility: hidden";>
										1</label>
									<div class="col-md-12 prl-8">
										<label>From Date : <span style="color: red">*</span></label>
										<form:input path="toDate" value="" size="28" id="toDate"
											class="form-control cus-control" style="text-align: left"
											placeholder="eg.dd/mm/yyyy" autocomplete="off" />
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
									<div class="col-md-12 prl-8">
										<label>To Date : <span style="color: red">*</span></label>
										<form:input path="fromDate" type="" size="28" id="fromDate"
											class="form-control cus-control" placeholder="eg.dd/mm/yyyy"
											autocomplete="off" />
										<form:errors path="fromDate" cssClass="error" />
										<div id="requiredMliValidityOfExposureLimitEndDate"
											Class="displayErrorMessageInRedColor"></div>

									</div>
								</div>
							</div>

							<%-- <%
								if (userRole.equals("NMAKER") || (userRole.equals("NCHECKER"))) {
							%>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<label class="d-block text-purple" style="visibility: hidden";>
										5</label>
									<div class="col-md-12 prl-10">
										<label>Member Id : <span style="color: red">*</span></label>
										<form:input path="MLIID" value="${MLIID}" size="28" id="MLIID"
											readonly="true" class="form-control cus-control"
											placeholder="eg.mliId" />
										<form:errors path="MLIID" cssClass="error" />
										<div id="requiredGuranteeCoverage"
											Class="displayErrorMessageInRedColor"></div>
									</div>
								</div>

							</div>

							<%
								}
							%> --%>
								<%-- <%
								if (userRole.equals("CMAKER") || userRole.equals("CCHECKER")) {
							%> --%>
                             <div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<label class="d-block text-purple" style="visibility: hidden";>
										5</label>
								<div class="col-md-12 prl-8">
											<label>MLI Long Name : <span style="color:red">*</span></label>								
											  	<input type="text" class="form-control cus-control d-none" placeholder="MLI Long Name" >
											 	   <form:select path="mliLongName" id="mliLongName" onchange="getMliShortName()" class="form-control cus-control">
												  <form:option value="" label="---Select---"/>	
												<%-- <form:option value="ALL" label="ALL MLI WISE REPORT"/> --%>
												<form:options items="${mliLongName}" />	
											</form:select>  
											<form:errors path="mliLongName" cssClass="error" />
											<div id="requiredMliLongName" Class="displayErrorMessageInRedColor"></div>
										</div>
								</div>

							</div>
							
							
							<%-- <div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<label class="d-block text-purple" style="visibility: hidden";>
										5</label>
								<div class="col-md-12 prl-8">
											<label>Name of SSI Unit :</label>								
											   
											 <form:input path="nameOffSsiUnit" type="text" size="28" id="nameOffSsiUnit"
											class="form-control cus-control" placeholder="Name of SSI Unit"
											autocomplete="off" />	 
											<form:errors path="mliLongName" cssClass="error" />
											<div id="requiredMliLongName" Class="displayErrorMessageInRedColor"></div>
										</div>
								</div>

							</div> --%>


<%-- 
							<%
								}
							%> --%>
							 <!-- <input type="submit" value="Submit" class="btn btn-reset"
											class="btn btn-reset" onclick="submitMLIForm()" />  -->

							  <div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">

									<div class="d-inlineblock mt-35">
										<center><input type="submit" value="Submit" class="btn btn-reset"
											class="btn btn-reset" onclick="submitMLIForm()" /></center>
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
	
	<c:if test="${!empty rows1}">
		<!-- <div
			STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;"> -->
		<div class="container-fluid">
			<div class="frm-section">
				<div class="col-md-12">
				 <div style="text-align: left;">
				<!-- <button style="border:none !important;" > -->
		    		<a href="DANASFReportDetailDownload.html">
		    			<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel"  >
		    		</a>
		    		<!-- </button> -->
		    		 </div>
			            <table id="myTable" cellpadding=5
						class="table table-bordered table-hover cus-table mt-10 mb-0"
						cellspacing=5 align=center width=90%>
						<thead>
							<tr>
								     <%-- <c:forEach items="${rows[0]}" var="column">  
									 <b> </b>
									 <th><c:out value="${column.key}" /></th>  
								  </c:forEach>  --%>   
								     <th>SNo.</th>
									<th>Demand Advice Number</th>
									<!-- <th>Ssi Name</th> -->
									<th>Member Id</th>
									<th>Generated On Date</th>
									<th>No Of Application</th>
									<th>Base Amount</th>
									<th>Dan Amount</th>
									<th>IGST Amount</th>
									<th>CGST Amount</th>
									<th>SGST Amount</th>
									 
								     
							</tr>
						</thead>
						<tbody>
						
						  <%
										int counter = 0;
									%>
						  <c:forEach items="${rows1}" var="item" varStatus="status">
									<tr>
									    <td><%=counter + 1%></td>
									  <!--  // <a href="a.html?fruit=apple" id="a">Hello</a> -->
										<td align="center"> <a href="/SV/DimandAdvaceNo.html?DemandAdvaceNO=${item[2]}">${item[2]} </a></td>
										<%-- <td align="center">${item[1]}</td> --%>
										<td align="center">${item[0]}</td>
										<td align="center">${item[3]}</td>
										<td align="center">${item[4]}</td>
										<td align="center">${item[5]}</td>
										<td align="center">${item[6]}</td>
										<td align="center">${item[7]}</td>
										<td align="center">${item[8]}</td>
										<td align="center">${item[9]}</td>  
										<%
												counter += 1;
										%>
									</tr>
								</c:forEach>    
						
						
						
						   <%--  <c:forEach items="${rows}" var="columns">
								<tr>
									<c:forEach items="${columns}" var="column">
										<td align="center">${column.value}</td>
									</c:forEach>
								</tr>
							</c:forEach>  --%>   
						</tbody>
					</table>
					<center>
						<div class="d-inlineblock mt-35">
										<form:form action="/SV/DANASFbackButton.html">
										<input type="submit" value="Back" class="btn btn-reset"
											class="btn btn-reset" />
											</form:form>
									 </div>
									 </center>
				</div>
			</div>
		</div>
	</c:if>
	
	
	
	<c:if test="${!empty rows3}">
		<!-- <div
			STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;"> -->
		<div class="container-fluid">
			<div class="frm-section">
				<div class="col-md-12">
				 <div style="text-align: left;">
				<!-- <button style="border:none !important;" > -->
		    		<a href="DemandAdviceNumberASFDetailDownload.html">
		    			<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel"  >
		    		</a>
		    		<!-- </button> -->
		    		 </div>
			            <table id="myTable" cellpadding=5
						class="table table-bordered table-hover cus-table mt-10 mb-0"
						cellspacing=5 align=center width=90%>
						<thead>
							<tr>
								     <%-- <c:forEach items="${rows[0]}" var="column">  
									 <b> </b>
									 <th><c:out value="${column.key}" /></th>  
								  </c:forEach>  --%>   
								    <th>SNo.</th>
									<th>CGPAN</th>
									<th>MSE NAME</th>
									<th>GUARANTEE AMOUNT</th>
									<th>OutStanding AMOUNT</th>
									<th>NBFC UPLOADED DATE</th>
									<th>PORTFOLIO RATE</th>
									<th>LOAN ACCOUNT NO</th>
									<th>STATE</th>
									<th>BASE AMOUNT</th>
									<th>IGST RATE</th>
									<th>IGST AMT</th>
									<th> CGST RATE</th>
									<th> CGST AMT</th>
									<th> SGST RATE</th>
									<th> SGST AMT</th>
									<th> DAN AMOUNT</th>
								     
							</tr>
						</thead>
						<tbody>
						
						  <%
										int counter = 0;
									%>
						  <c:forEach items="${rows3}" var="item" varStatus="status">
									<tr>
									    <td><%=counter + 1%></td>
									    <td align="center"> <a href="/SV/ASFCGPNDetails.html?ASFcgpnNo=${item[6]}">${item[0]} </a></td>  
										<td align="center">${item[1]}</td>
										<td align="center">${item[2]}</td>
										 <td align="center">${item[3]}</td>
										<td align="center">${item[4]}</td>
										<td align="center">${item[5]}</td>  
										<td align="center">${item[6]}</td>
										<td align="center">${item[7]}</td>
										<td align="center">${item[8]}</td>
										<td align="center">${item[9]}</td> 
										<td align="center">${item[10]}</td>
										<td align="center">${item[11]}</td>
										<td align="center">${item[12]}</td>
										<td align="center">${item[13]}</td>
										<td align="center">${item[14]}</td> 
										 <td align="center">${item[15]}</td> 
										<%
												counter += 1;
										%>
									</tr>
								</c:forEach>    
						
						
						
						   <%--  <c:forEach items="${rows}" var="columns">
								<tr>
									<c:forEach items="${columns}" var="column">
										<td align="center">${column.value}</td>
									</c:forEach>
								</tr>
							</c:forEach>  --%>   
						</tbody>
					</table>
					<div align="center">
						<!-- <div class="d-inlineblock mt-35"> -->
										<form:form action="/SV/DANASFbackButton1.html">
										<input type="submit" value="Back" class="btn btn-reset"
											class="btn btn-reset" />
											</form:form>
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
						
						
 

	                     <div align="center">
	                     
	                     <form:form action="/SV/DANASFbackButton2.html">
										<input type="submit" value="Back" class="btn btn-reset"
											class="btn btn-reset" />
											</form:form>
										
										<!-- <input type="button" value="Back" class="btn btn-reset"
											class="btn btn-reset" onclick="Back1()" /> -->
										<!-- mt-25 -->
										<!-- <input type="button" value="Back" class="btn btn-reset" onclick="return exitMLIDetails()"/> -->
									</div>
						</div>
					</div>
				</div>
				
			</div>


		</div>
		
	</c:if>
	
	<script src="js/jquery-3.5.1.min.js" type="text/javascript"></script>
	<div class="loader"></div>
	<script type="text/javascript">
		$(window).load(function() {
			$(".loader").fadeOut("slow");
		});
	</script>
</body>
</html>