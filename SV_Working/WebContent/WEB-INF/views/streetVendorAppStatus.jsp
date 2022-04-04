<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
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

String userRole = (String) session.getAttribute("uRole");
String isSummaryReportPage = (String) session.getAttribute("summaryReportPage");
session.setAttribute("summaryReportPage",isSummaryReportPage);
System.out.println("summaryReportPage"+isSummaryReportPage);

%>



			<div>
				<div class="frm-section">
					<div class="col-md-12">
					<input type="hidden" id="reporttype" value="<%=isSummaryReportPage%>">
						<c:if test="${!empty noDataFound}">
							<span style="color: red"><b>${noDataFound}</b></span>
						</c:if>
						<form:form method="POST" action="" class="form-horizontal" id="A" autocomplete="off">
						
						<%
						if(isSummaryReportPage.equalsIgnoreCase("streetVendorReport")){
						%>
						
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<label class="d-block text-purple" style="visibility: hidden";>
										1</label>
									<div class="col-md-6 prl-10">
										<label>Start Date : <span style="color: red">*</span></label>
										<form:input path="fromDate" value="" size="28" id="fromDate"
											class="form-control cus-control" style="text-align: left"
											placeholder="eg. dd/mm/yyyy" autocomplete="off"/>
										<form:errors path="toDate" cssClass="error" />
										
									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<label class="d-block text-purple" style="visibility: hidden";>
										4</label>
									<div class="col-md-6 prl-10">
										<label>End Date : <span style="color: red">*</span></label>
										<form:input path="toDate" type="" size="28" id="toDate"
											class="form-control cus-control" placeholder="eg. dd/mm/yyyy" autocomplete="off"/>
										<form:errors path="fromDate" cssClass="error" />
										

									</div>
								</div>
							</div>
							
							<%
								if (userRole.equals("NMAKER") || (userRole.equals("NCHECKER"))) {
							%>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<label class="d-block text-purple" style="visibility: hidden";>
										5</label>
									<div class="col-md-12 prl-10">
										<label>MLI ID : <span style="color: red">*</span></label>
										<form:input path="mliLongName" value="${MLIID}" size="28" id="MLIID"
											readonly="true" class="form-control cus-control"
											placeholder="eg. 123456789" autocomplete="off" maxlength="50"/>
									</div>
									</div>
									<div class="form-group">
									<div class="col-md-12 prl-10"s>
									<label>PMS No : </label>
										    <form:input path="pmsNo" size="28" id="pmsNo"
											readonly="false" class="form-control cus-control"
											placeholder="eg. PMS123456789" autocomplete="off" maxlength="50"/>
									</div>
								</div>

							</div>

							<%
								}
							%>
								<%
								if (userRole.equals("CMAKER") || userRole.equals("CCHECKER")) {
							%>
                             <div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<label class="d-block text-purple" style="visibility: hidden";>
										5</label>
								<div class="col-md-12 prl-10">
											<%-- <label>MLI ID : <span style="color:red">*</span></label>								
											  	<input type="text" class="form-control cus-control d-none" placeholder="MLI Long Name" >
											 	<form:select path="mliLongName" id="mliLongName" onchange="getMliShortName()" class="form-control cus-control">
												<form:option value="ALL" label="---Select all---"/>	
												<form:options items="${mliLongName}" />	
											</form:select> --%>
											<label>MLI ID : <span style="color: red">*</span></label>
										    <form:input path="mliLongName" value="${MLIID}" size="28" id="MLIID"
											readonly="false" class="form-control cus-control"
											placeholder="eg. 123456789" autocomplete="off" maxlength="50"/>
											<div id="requiredMliLongName" Class="displayErrorMessageInRedColor"></div>
									
										</div>
										</div>
										<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>PMS No :</label>
										    <form:input path="pmsNo" size="28" id="pmsNo"
											readonly="false" class="form-control cus-control"
											placeholder="eg. PMS123456789" autocomplete="off" maxlength="50"/>
									</div>
								</div>

							</div>
							<%
								}
							%>
							
							<%	
						}else{
							%>
							<!-- this logic for summary report -->
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<label class="d-block text-purple" style="visibility: hidden";>
										1</label>
									<div class="col-md-6 prl-10">
										<label>Start Date : <span style="color: red">*</span></label>
										<form:input path="fromDate" value="" size="28" id="fromDate"
											class="form-control cus-control" style="text-align: left"
											placeholder="eg. dd/mm/yyyy" autocomplete="off"/>
										<form:errors path="toDate" cssClass="error" />
										
									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<label class="d-block text-purple" style="visibility: hidden";>
										4</label>
									<div class="col-md-6 prl-10">
										<label>End Date : <span style="color: red">*</span></label>
										<form:input path="toDate" type="" size="28" id="toDate"
											class="form-control cus-control" placeholder="eg. dd/mm/yyyy" autocomplete="off"/>
										<form:errors path="fromDate" cssClass="error" />
										

									</div>
								</div>
							</div>
						
						<%						
						}
						%>
						

							
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">

									<div class="d-inlineblock mt-35">
										<!-- <input type="button" value="Create Exposure Limit"
											class="btn btn-reset" class="btn btn-reset"
											onclick="return searchFun()" /> -->
										<input type="submit" value="Search" class="btn btn-reset"
											class="btn btn-reset" onclick="return searchFun();" />
										<!-- mt-25 -->
										<!-- <input type="button" value="Back" class="btn btn-reset" onclick="return exitMLIDetails()"/> -->
									</div>
									
									<div class="d-inlineblock mt-35">
										<!-- <input type="button" value="Create Exposure Limit"
											class="btn btn-reset" class="btn btn-reset"
											onclick="return searchFun()" /> -->
										<input type="submit" value="Download Report" class="btn btn-reset"
											class="btn btn-reset" onclick="return searchFunAndDownload();" />
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
										
										<!-- <input type="button" value="Back" class="btn btn-reset"
											class="btn btn-reset" onclick="Back2()" /> -->
										<!-- mt-25 -->
										<!-- <input type="button" value="Back" class="btn btn-reset" onclick="return exitMLIDetails()"/> -->
									</div>
									<div class="d-inlineblock mt-35">
									
										<form:form method="GET" action="/SV/streetVendorReportDownload.html" class="form-horizontal" id="B">
										<button style="border:none !important; cursor: allowed;">
		    						<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel" onclick="return excelDownload()">
		    					</button>	
		    					</form:form>
										<!-- mt-25 -->
										<!-- <input type="button" value="Back" class="btn btn-reset" onclick="return exitMLIDetails()"/> -->
									</div>
				</div>
			</div>
		</div>
	</c:if>
	
	<div align="center">
										
										<input type="button" value="Back" class="btn btn-reset"
											class="btn btn-reset" onclick="Back1()" />
										<!-- mt-25 -->
										<!-- <input type="button" value="Back" class="btn btn-reset" onclick="return exitMLIDetails()"/> -->
									</div>
				
		



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
		//isSummaryReportPage
			var reportType = document.getElementById("reporttype").value;
			if(reportType=='isSummaryReportPage'){
				var fromDate=document.getElementById("fromDate").value;
				
				if(fromDate==''){
					//document.getElementById("usr_password").innerHTML="Password is required";
					alert("Start date required");
					return false;

				}
				
			}else{
					var toDate = document.getElementById("toDate").value;
					var fromDate=document.getElementById("fromDate").value;
					if(fromDate==''){
						alert("Start date required");
						return false;

					}
					 if(toDate ==''){
						alert("End date required");
						return false;
					}
				}
		document.getElementById('A').action = "/SV_Working/searchStreetVedor.html";

		document.deliveryForm.submit();
		
		/*  var v=alertDate();
		 
		 if(v!=false)
			 {
			
		document.getElementById('A').action = "/SV_Working/searchStreetVedor.html";

		document.deliveryForm.submit();}
		 else{
			 return false;
		 } */

	}
	
	function searchFunAndDownload() {

		var reportType = document.getElementById("reporttype").value;
		if(reportType=='isSummaryReportPage'){
			var fromDate=document.getElementById("fromDate").value;
			var toDate = document.getElementById("toDate").value;
			
			if(fromDate==''){
				//document.getElementById("usr_password").innerHTML="Password is required";
				alert("Start date required");
				return false;

			}
			if(toDate ==''){
				alert("End date required");
				return false;
			}
			
		}else{
				var toDate = document.getElementById("toDate").value;
				var fromDate=document.getElementById("fromDate").value;
				if(fromDate==''){
					alert("Start date required");
					return false;

				}
				 if(toDate ==''){
					alert("End date required");
					return false;
				}
			}
		document.getElementById('A').action = "/SV_Working/searchStreetVedorAndDownload.html";

		document.deliveryForm.submit();
		/* 
		 var v=alertDate();
		 
		 if(v!=false)
			 {
			
		document.getElementById('A').action = "/SV_Working/searchStreetVedorAndDownload.html";

		document.deliveryForm.submit();}
		 else{
			 return false;
		 } */

	}
	
	
	function Back1() {

		document.getElementById('A').action = "/SV_Working/uploadedFileDataApplicationHistory.html?fileId="+'<%=file_id%>';

		document.getElementById('A').submit();

	}
	function excelDownload() {
		debugger;
		document.getElementById('B').action = "/SV_Working/streetVendorReportDownload.html";

		document.getElementById('B').submit();

	}
	
	
	function alertDate()
	{	
		debugger;
		var toDate = document.getElementById("toDate").value;
		var fromDate=document.getElementById("fromDate").value;
		/* if(document.getElementById("memberId").value==""){
			document.getElementById("memberIdError").innerHTML="Member Id is required";
			//alert("Member Id is required");
			return;
		}else */
		
	//alert(fromDate);
	//alert(toDate);
		if(fromDate==''){
			//document.getElementById("usr_password").innerHTML="Password is required";
			alert("Start date required");
			return false;

		}else if(toDate ==''){
			//document.getElementById("usr_id").innerHTML="UserId is required";
			alert("End date required");
			return false;
		}/* else if(document.getElementById("MLIID").value==''){
			alert("Mli ID required");
			return false;
		} */
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
