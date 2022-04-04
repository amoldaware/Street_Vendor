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
    <div id="loading" style="display: none">
		<img src="images/loader.gif" height="1200px" width="1550px"  style="margin:auto;"/>
	</div>
	<div class="main-section">
		<div class="container-fluid">
	 <nav aria-label="breadcrumb">
 <!--  <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/appstatus.html">Application Status</a></li>  
    <li class="breadcrumb-item"><a href="/SV/searchappstatus.html">Application Status1</a></li>
    <li class="breadcrumb-item active" aria-current="page">Application Status2</li>
  </ol> -->
</nav>
<%! String activity = ""; %>
<%
String userRole = (String) session.getAttribute("uRole");
String isSummaryReportPage = (String) session.getAttribute("summaryReportPage");
String toDateF = (String) session.getAttribute("toDateF");
String fromDateF = (String) session.getAttribute("fromDateF");
String state = (String) session.getAttribute("state");
String district = (String) session.getAttribute("district");
activity = (String) session.getAttribute("activity");
System.out.println("Activity ["+activity+"]" + "\t toDateF ["+toDateF+"]\t fromDateF ["+fromDateF+"]\t state ["+state+"]\t district ["+district+"]");
%> 
			<div>
				<div class="frm-section">
				    <!-- <div><h3 align="center" style="color: red">State Activity Report</h3></div>  -->   
					<div class="col-md-12" >
						<c:if test="${!empty noDataFound}">
							<span id="tblDiv" style="color: red"><b>${noDataFound}</b></span>
						</c:if>
						<form:form method="POST" action="" class="form-horizontal" id="A" autocomplete="off">
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-6 prl-10">
										<label>Start Date : <span style="color: red">*</span></label>
										<form:input path="fromDate" value="" size="20" id="fromDate"
											class="form-control cus-control" style="text-align: left" onclick="setDateValue(id)"
											placeholder="eg. dd/mm/yyyy" autocomplete="off"/>
									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-6 prl-10">
										<label>End Date : <span style="color: red">*</span></label>
										<form:input path="toDate" type="" size="28" id="toDate" onclick="setDateValue(id)"
											class="form-control cus-control" placeholder="eg. dd/mm/yyyy" autocomplete="off"/>
									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-6 prl-10">
										<label>State : <span style="color: red">*</span></label>
										<form:select path="state"  name="state" id="state" class="form-control cus-control" onchange="getDistinctDistrictDetails();">
										      <option value='0'>SELECT</option>
										      <option value='ANDAMAN AND NICOBAR ISLANDS'>ANDAMAN AND NICOBAR ISLANDS</option>
										      <option value='ANDHRA PRADESH'>ANDHRA PRADESH</option>
										      <option value='ARUNACHAL PRADESH'>ARUNACHAL PRADESH</option>
										      <option value='ASSAM'>ASSAM</option>
										      <option value='BIHAR'>BIHAR</option>
										      <option value='CHANDIGARH'>CHANDIGARH</option>
										      <option value='CHHATTISGARH'>CHHATTISGARH</option>
										      <option value='DADRA & NAGAR HAVELI'>DADRA & NAGAR HAVELI</option>
										      <option value='DAMAN AND DIU AND DADRA AND NAGAR HAVELI'>DAMAN AND DIU AND DADRA AND NAGAR HAVELI</option>
										      <option value='DELHI'>DELHI</option>
										      <option value='GOA'>GOA</option>
										      <option value='GUJARAT'>GUJARAT</option>
										      <option value='HARYANA'>HARYANA</option>
										      <option value='HIMACHAL PRADESH'>HIMACHAL PRADESH</option>
										      <option value='JAMMU & KASHMIR'>JAMMU & KASHMIR</option>
										      <option value='JHARKHAND'>JHARKHAND</option>
										      <option value='KARNATAKA'>KARNATAKA</option>
										      <option value='KERALA'>KERALA</option>
										      <option value='LADAKH'>LADAKH</option>
										      <option value='MADHYA PRADESH'>MADHYA PRADESH</option>
										      <option value='MAHARASHTRA'>MAHARASHTRA</option>
										      <option value='MANIPUR'>MANIPUR</option>
										      <option value='MEGHALAYA'>MEGHALAYA</option>
										      <option value='MIZORAM'>MIZORAM</option>
										      <option value='NAGALAND'>NAGALAND</option>
										      <option value='ODISHA'>ODISHA</option>
										      <option value='ORISSA'>ORISSA</option>
										      <option value='PONDICHERRY'>PONDICHERRY</option>
										      <option value='PUDUCHERRY'>PUDUCHERRY</option>
										      <option value='PUNJAB'>PUNJAB</option>
										      <option value='RAJASTHAN'>RAJASTHAN</option>
										      <option value='SIKKIM'>SIKKIM</option>
										      <option value='TAMILNADU'>TAMILNADU</option>
										      <option value='TELANGANA'>TELANGANA</option>
										      <option value='TRIPURA'>TRIPURA</option>
										      <option value='UTTAR PRADESH'>UTTAR PRADESH</option>
										      <option value='UTTARAKHAND'>UTTARAKHAND</option>
										      <option value='WEST BENGAL'>WEST BENGAL</option>
										</form:select>
									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-6 prl-10">
										<label>District : <span style="color: red">*</span></label>
										<form:select path="district"  name="district" id="district" class="form-control cus-control">
											<option value='0'>SELECT</option>
										</form:select>	
									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-6 prl-10">
										<label>Activity :</label>
									    <form:select path="activity"  name="activity" id="activity" class="form-control cus-control">
									    <option value='0'>SELECT</option>
									    <option value='BEAUTY AND FASHION ACCESSORIES'>BEAUTY AND FASHION ACCESSORIES</option>
									    <option value='CLOTH AND HANDLOOM ITEMS'>CLOTH AND HANDLOOM ITEMS</option>
									    <option value='ELECTRICAL AND ELECTRONIC GOODS'>ELECTRICAL AND ELECTRONIC GOODS</option>
									    <option value='FAST FOOD AND FOOD ITEMS'>FAST FOOD AND FOOD ITEMS</option>
									    <option value='FLOWER AND POOJA ITEMS'>FLOWER AND POOJA ITEMS</option>
									    <option value='FOOTWEAR AND LEATHER PRODUCTS'>FOOTWEAR AND LEATHER PRODUCTS</option>
									    <option value='FOOTWEAR AND LEATHERS PRODUCTS'>FOOTWEAR AND LEATHERS PRODUCTS</option>
									    <option value='FRUITS AND VEGETABLES'>FRUITS AND VEGETABLES</option>
									    <option value='HOME DECOR& HANDICRAFTS'>HOME DECOR& HANDICRAFTS</option>
									    <option value='KITCHEN ITEMS'>KITCHEN ITEMS</option>
									    <option value='OTHERS'>OTHERS</option>
									    <option value='OTHERS (PLS SPECIFY)'>OTHERS (PLS SPECIFY)</option>
									    <option value='PLASTIC ITEMS'>PLASTIC ITEMS</option>
									    <option value='SERVICES'>SERVICES</option>
										</form:select>		
									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
							    <div class="d-inlineblock mt-20" style="margin-left:-176px;">
										<input type="submit" value="Search" id="Search" class="btn btn-reset"
											class="btn btn-reset" onclick="return searchFun();"/>
									</div>
									<div class="d-inlineblock mt-20">
								<input type="button" value="Clear" id="Clear" class="btn btn-reset"
								class="btn btn-reset" onclick="return ClearFun();" />
									</div>
							</div>
						</form:form>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<c:if test="${!empty rows}">
		<div class="container-fluid">
			<div class="frm-section" id="tblDiv">
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
						<div class="d-inlineblock mt-35"></div>
						<div class="d-inlineblock mt-35">
							<form:form method="GET" action="/SV/stateActivityWiseReportDownload.html" class="form-horizontal" id="B">
								<button style="border:none !important; cursor: allowed;">
		    						<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel" onclick="return excelDownload()">
		    					</button>	
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
<script type="text/javascript">

$(function() {
	$("#toDate").datepicker({
		dateFormat : 'dd/mm/yy'
	});
	$("#fromDate").datepicker({
		dateFormat : 'dd/mm/yy'
	});

});

function getDistinctDistrictDetails()
{
	$('#loading').show();
	var state = $("#state").val();
	var selHTML = "<option value='0'>SELECT</option>";
	$.ajax({
		url : '${pageContext.request.contextPath}/district-details.html?state='+state,
		cache : false,
		crossDomain: true,
		type : 'GET',
		dataType : 'json',
		contentType : 'application/json; charset=utf-8',
		 success : function(response) {
			 $('#loading').hide();
	   			     $.each(response, function(key, value) {
	   					selHTML+= "<option value='"+value+"'>"+value+"</option>";
	   			  }); 
	   			  $('#district').html(selHTML);  
			},
			error : function(xhr, httpStatusMessage, customErrorMessage) {
				alert(xhr.responseText);
		    } 
	});
}

function searchFun()
{
	var fromDate = $("#fromDate").val(); var toDate = $("#toDate").val();	
	var state = $("#state").val();var district = $("#district").val();
	if(fromDate==''){
		alert("From Date can't be Blank!!!");
	    return false;
    }
	if(toDate ==''){
		alert("To Date can't be Blank!!!");
		return false;
	}
	if((state == "0") && (district != "0")){
		alert("Please Select State!!!");
		return false;
	}
			
	document.getElementById('A').action = "/SV_Working/getStateActivityWiseInfo.html";
	document.deliveryForm.submit();
}
function excelDownload() {
	document.getElementById('B').action = "/SV_Working/stateActivityWiseReportDownload.html";
	document.getElementById('B').submit();
}
function ClearFun()
{
	$("#fromDate").val('');
	$("#toDate").val('');
	$("#tblDiv").empty();
}
</script>
</html>
