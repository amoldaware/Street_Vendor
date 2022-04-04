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
<title>Exposure Wise Data</title>
<link href="css/jquery-ui-css.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<LINK href="<%=request.getContextPath()%>/css/stylesheet.css"
	rel="stylesheet" type="text/css">
<title>Exposure Wise Data</title>
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
//String f_date=(String)session.getAttribute("FDate");
//String s_date=(String)session.getAttribute("TDate");
//String status=(String)session.getAttribute("AppStatus");
%>
			<div>
				<div class="frm-section">
					<div class="col-md-12">
					<h2 align="center" class="heading">NBFC Exposure Wise Guarantee Data</h2>
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
									<div class="d-inlineblock mt-35">
										<input type="submit" value="Search" class="btn btn-reset"
											class="btn btn-reset" onclick="return searchFun()" />
										<!-- mt-25 -->
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
	
	<div class="container-fluid">
			<div class="frm-section">
				<div class="col-md-12">
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
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${!empty rowsChild}">
	
		<div class="container-fluid">
			<div class="frm-section">
				<div class="col-md-12">
					<table id="myTable" cellpadding=5
						class="table table-bordered table-hover cus-table mt-10 mb-0"
						cellspacing=5 align=center width=90%>
						<thead>
							<tr>
								<c:forEach items="${rowsChild[0]}" var="column">
									<b> </b>
									<th><c:out value="${column.key}" /></th>
								</c:forEach>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${rowsChild}" var="columns">
								<tr>
									<c:forEach items="${columns}" var="column">
										<td align="center">${column.value}</td>
									</c:forEach>
								</tr>
							</c:forEach>
						</tbody>
					</table>
						<div class="d-inlineblock mt-35">
										
										<form:form action="/SV/getMliExposureDataById.html">
										<input type="submit" value="Back" class="btn btn-reset"
											class="btn btn-reset" />
											</form:form>
										</div>
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${!empty rowsChild1}">
	
		<div class="container-fluid">
			<div class="frm-section">
				<div class="col-md-12">
				<div class="table-responsive">
					<table id="myTable" cellpadding=5
						class="table table-bordered table-hover cus-table mt-10 mb-0"
						align=center width=100%>
						<thead>
							<tr>
								<c:forEach items="${rowsChild1[0]}" var="column">
									<b> </b>
									<th><c:out value="${column.key}" /></th>
								</c:forEach>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${rowsChild1}" var="columns">
								<tr>
									<c:forEach items="${columns}" var="column">
										<td align="center">${column.value}</td>
									</c:forEach>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
						<div class="d-inlineblock mt-35">
										
										<form:form action="/SV/backfromfolionamedata.html">
										<input type="submit" value="Back" class="btn btn-reset"
											class="btn btn-reset" />
											</form:form>
										</div>
				</div>
			</div>
		</div>
	</c:if>
		<c:if test="${!empty rowsChild4}">
		<div class="container-fluid">
			<div class="frm-section">
				<div class="col-md-12">
				<div class="table-responsive">
					<table id="myTable" cellpadding=5
						class="table table-bordered table-hover cus-table mt-10 mb-0"
						align=center width=100%>
						<thead>
							<tr>
								<c:forEach items="${rowsChild4[0]}" var="column">
									<b> </b>
									
									<th><c:out value="${column.key}" /></th>
								</c:forEach>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${rowsChild4}" var="columns">
								<tr>
									<c:forEach items="${columns}" var="column">
										<td align="center">${column.value}</td>
									</c:forEach>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
						<div class="d-inlineblock mt-35">
										
										<form:form action="/SV/getDataByPortfolioName.html">
										<input type="submit" value="Back" class="btn btn-reset"
											class="btn btn-reset" />
											</form:form>
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
</body>
</html>
<script language="javascript">
	function searchFun() {

		document.getElementById('A').action = "/SV/searchExposureData.html";

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