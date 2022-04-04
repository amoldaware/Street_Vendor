<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	//String sN = (String) request.getAttribute("sName");
	//String expLim = (String) request.getAttribute("eXposureLimit");
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
<LINK href="<%=request.getContextPath()%>/css/stylesheet.css"
	rel="stylesheet" type="text/css">


</head>

<body bgcolor="#E0FFFF">

<div class="main-section">
<div class="container-fluid">
<!-- <div class="row"> -->
	<div>
		
	<div class="tbl-details">
		<div class="col-md-12">
		
		<form:form method="POST" id="A">
		<c:if test="${!empty dataList}">
		
		<table id="myTable" class="table table-bordered table-hover cus-table mb-0 danRpDataTable">
			
			<thead>
					<tr>
						<th>SR.NO.</th>
						<th>CGPAN</th>
						<th>NPA DATE</th>
						<th>NPA REASON</th>
					<!-- 	<th>LOAN TYPE</th> -->
					
						<th>SELECT ALL</br>
						<form:checkbox path="selectAll" id="select_all"
								onchange="selectAllCheckBox();" cssClass="select_all" /></th>
					</tr>
			</thead>
					<%
						int counter = 0;
					%>
					<c:forEach items="${dataList}" var="dataList">
						<%-- <tr <%if (counter % 2 == 0) {%> bgcolor="silver" <%}%>> --%>
						<tr>
							<td><c:out value="<%=counter + 1%>" /></td>
							<td><c:out value="${dataList.CGPAN}" /></td>
							<td><c:out value="${dataList.npaDt}" /></td>
							<td width="30%"><c:out value="${dataList.npaReason}" /></td>
							<%-- <td><c:out value="${dataList.loanType}" /></td> --%>
						
							<td align="center"><form:checkbox path="chcktbl"
									value="${dataList.CGPAN}" onchange="uncheckSelectAll();"
									cssClass="chcktbl" /><input type="hidden" id="approveCount"
								name="approveCount" value="hiddenValue"></td>
							<%
								counter += 1;
							%>
						
					</c:forEach>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
			 							
						<td><div id="count"></div></td>
					</tr>				
			</table>
			
			<div class="d-block">
				<input class="btn btn-reset" readonly="true" id="onSubmit"
							onclick="clickToInitiate();" value="Return" align="" ></td>
					<!-- 	<td><input class="btn btn-reset" readonly="true" id="onReject"
							onclick="rejectFunction();" value="Back" align=""> -->
			</div>	
			
		</c:if>
		
		<c:if test="${empty dataList}">
		<table id="myTable" class="table table-bordered table-hover cus-table mb-0 danRpDataTable">			
			<thead>
					<tr>
					<th>SR.NO.</th>
						<th>CGPAN</th>
						<th>NPA DATE</th>
						<th>NPA REASON</th>
						<!-- <th>LOAN TYPE</th> -->
						<th>SELECT ALL</br>
						<form:checkbox path="selectAll" id="select_all"
							onchange="selectAllCheckBox();" cssClass="select_all" /></th>
					</tr>
			</thead>							
				<tr>
					<td></td>
					<td></td>						
					<td align="center">Data not available</td>
					<td></td>
					
					<td></td>
					<td></td>
				</tr>
		</table>
	</c:if>
				
		
		</form:form>
		
		</div>
	</div>					
	
	</div>
</div>
</div>	
<script type="text/javascript">
	function clickToInitiate() {

		$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');

		if ($selectedCBs.length === 0) {
			alert("Please select the record for Return.");
			//return;
		} else { //	var accept=document.getElementById('accept').value;
			//var reject=document.getElementById('reject').value;

			// alert("selected row size is "+$selectedCBs.length);
			var approveCount;
			if (document.getElementById('select_all').checked == true) {
				//alert("hellow ",$selectedCBs.length-1); 	       
				approveCount = $selectedCBs.length - 1;
			} else {
				approveCount = $selectedCBs.length;
			}

			var hiddenValue = document.getElementById('approveCount').value = approveCount;
			//alert("hellow "+hiddenValue); 
			document.getElementById('A').action = "/SV/NPAReturn.html";
			document.getElementById('A').submit();

		}
	}
	function rejectFunction() {

		
			document.getElementById('A').action = "/SV/cgtmseGetCheckerHome.html";
			document.getElementById('A').submit();

	
	}
	function selectAllCheckBox() {
		if (document.getElementById('select_all').checked == true) {
			$('.chcktbl').each(function() {
				this.checked = true;
			});
			$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
			var count = $selectedCBs.length - 1;
			document.getElementById('count').innerHTML = count;
		} else {
			$('.chcktbl').each(function() {
				this.checked = false;
			});
			$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
			var count = $selectedCBs.length;
			document.getElementById('count').innerHTML = count;
		}

	}
	function uncheckSelectAll() {
		$('.chcktbl').click(function() {
			if (this.checked == false) {
				$('#select_all').attr('checked', false);
			}
			$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
			var count = $selectedCBs.length;
			document.getElementById('count').innerHTML = count;
		});
		$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
		var count = $selectedCBs.length;
		document.getElementById('count').innerHTML = count;

	}
</script>

</html>



