<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String sN = (String) request.getAttribute("sName");
	String expLim = (String) request.getAttribute("eXposureLimit");
%>
<html>
<head>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<title>Portfolio Creation</title>
<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
<script type="text/javascript"
	src="jquery-ui-1.10.3/ui/jquery.ui.datepicker.js"></script>
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">

<script>
	$(function() {

		$("#ratingDate").datepicker({
			dateFormat : 'dd-mm-yy'
		});

	});
</script>

</head>

<body bgcolor="#E0FFFF">
<div class="main-section">
<div class="container-fluid">
<!-- <div class="row"> -->
<nav aria-label="breadcrumb">
  <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/danAllocation.html">DAN Allocation</a></li>  
    <li class="breadcrumb-item"><a href="/SV/danAllocation.html">Confirm Guarantee Fee Allocation</a></li>  
    <li class="breadcrumb-item active" aria-current="page">Generate RP Number</li>
  </ol>
</nav>
	<div>	

		<div class="frm-section">
			<form:form method="GET" id="A" class="form-horizontal">
			<div class="col-md-12">		
				<h2 align="center" class="heading pb-15">Generate RP Number</h2>
				<div class="col-md-3 col-sm-4 col-xs-12">
			<div class="form-group">
				<div class="col-md-12 prl-10">
				<label class="d-block">RP Number :	</label>
					<label class="d-block" style="padding: 5px; background-color: #e0e0e0; height:30px">	
					${rpNumberBean.rpNumber}</label>
					  </div>
			 </div>
	 		 </div>
	 		 <div class="col-md-3 col-sm-4 col-xs-12">
			<div class="form-group">
				<div class="col-md-12 prl-10">
				<label class="d-block">Amount :	</label>
					<label class="d-block" style="padding: 5px; background-color: #e0e0e0; height:30px">	
					${rpNumberBean.totalAmount}</label>
					  </div>
			 </div>
	 		 </div>
	 		 <div class="col-md-3 col-sm-4 col-xs-12">
			<div class="form-group">
				<div class="col-md-12 prl-10">
				<label class="d-block">Generated Date :	</label>
					<label class="d-block" style="padding: 5px; background-color: #e0e0e0; height:30px">
						${rpNumberBean.currentDate}</label>
					  </div>
			 </div>
	 		 </div>
			
			<div class="d-inlineblock mt-25">
				<input type="submit"
				value="Close" onclick="buckToHome()" class="btn btn-reset" />
			</div>	
			
			</div>
			</form:form>
		</div>
			
	
	</div>
</div>
</div>		

	<%-- <form:form method="GET" id="A">
		<table cellpadding=5 cellspacing=5 align=center width=90%>
			<tr>
				<td class="tableHeader1">RP Number :</td>
				<td><b>${rpNumberBean.rpNumber}</b></td>

			</tr>
			<tr>
				<td class="tableHeader1">Amount :</td>
				<td><b>${rpNumberBean.totalAmount}</b></td>

			</tr>
			<tr>
				<td class="tableHeader1">Generated Date :</td>
				<td><b>${rpNumberBean.currentDate}</b></td>
			</tr>
			<tr>
				<td align="center" style="visibility: hidden;"><form:input
						path="danNumber" value="${rpNumberBean.danNumber}" /></td>
			</tr>
			<tr>
				<td align="center" style="visibility: hidden;"><form:input
						path="rpNumber" value="${rpNumberBean.rpNumber}" /></td>
			</tr>
			<tr>
				<td align="center" style="visibility: hidden;"><form:input
						path="totalFee" value="${rpNumberBean.totalFee}" /></td>
			</tr>
			<tr>
				<td align="center" style="visibility: hidden;"><form:input
						path="dateOfRPGenration" value="${rpNumberBean.dateOfRPGenration}" /></td>
			</tr>

			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>


		<div align="center">
			<input type="submit"
				value="Close" onclick="buckToHome()" class="button" />
		</div>
		<!-- </div> -->
	</form:form> --%>
</body>
<script type="text/javascript">
	function buckToHome() {
		//alert('Back');
		document.getElementById('A').action = "/SV/nbfcMakerHome.html";
		document.getElementById('A').submit();
	}

	function submitToProcess() {
		//alert('Back');
		document.getElementById('A').action = "/SV/rpNumberConfirmation.html";
		document.getElementById('A').submit();
	}
	function searchRecord() {
		var nameSearch = document.getElementById("nameSearch").value;
		var searchValue = document.getElementById("searchValue").value;
		alert('search  :' + nameSearch + ' searchValue  :' + searchValue);
		if (nameSearch != null || searchValue != null) {
			document.getElementById('A').action = "/SV/mlidetailsByIndex.html";
			document.getElementById('A').submit();
		}

	}
</script>

</html>



