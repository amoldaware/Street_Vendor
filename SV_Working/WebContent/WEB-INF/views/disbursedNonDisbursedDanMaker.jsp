<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<title>Portfolio Creation</title>
		<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<script type="text/javascript" src="jquery-ui-1.10.3/ui/jquery.ui.datepicker.js"></script>
		<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
		<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
	</head>
<body>
<div class="main-section">
	<div class="container-fluid">
		<div>	
			<div class="tbl-details">
				<div class="col-md-12">
					<div class="d-inlineblock ">
						<a href="/SV/disbursedborwerDetails.html?fileName=<%=session.getAttribute("fileName")%>&disbursedStatus=Y" class="btn btn-reset">Disbursed</a>
						<input type="submit" value="Back" onclick="buckToHome()" class="btn btn-reset" />
					</div>
					<div class="d-inlineblock float-right ">
						<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px; background-color: #ffffff;" placeholder="Search Data.." title="Type in a name">    		
	    				<button style="border:none !important; cursor: not-allowed;">
	    					<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
	    				</button>			
					</div>
	<c:if test="${!empty dataList1}">
		<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0">				
		<thead>
			<tr>
				<th >SR.NO</th>				
				<th >LOAN ACCOUNT NO.</th>
				<th>CGPAN</th>
				<th>DAN ID</th>
				<!--<th>Portfolio Name</th>						
				<th>Borrower Name</th>
			
				<th>Sanction Amount</th>					
				<th>Sanction Date</th>
			--><c:if test = "${disStatus == 'Y'}"><!--
				<th>Portfolio RATE</th>
				
				-->
				<th>OOUSTANDING AMOUNT</th>
				<th>DAN AMOUNT</th>
				<th>BASE AMOUNT</th>
				<th>IGST RATE</th>
				<th>IGST AMOUNT</th>
				<th>CGST RATE</th>						
				<th>CGST AMOUNT</th>
				<th >SGST RATE</th>										
				<th >SGST<br>AMOUNT</th>					
			</c:if>			
			</tr>
	 </thead>
			<% int counter=0;%>
	<c:forEach items="${dataList1}" var="dataList1">
		 	<tr>
				<td><c:out value="<%=counter+1%>" /></td>		
				<!--<td><c:out value="${dataList1.portfolioName}" /></td>
				--><td><c:out value="${dataList1.loanAccNo}" /></td>
				<td><c:out value="${dataList1.cgpan}" /></td>
				<td><c:out value="${dataList1.danId}" /></td>
				<td><c:out value="${dataList1.outstandingAmount}" /></td>
			
			<c:if test = "${disStatus == 'Y'}">
				
				<td><c:out value="${dataList1.danAmount}" /></td>
				<td><c:out value="${dataList1.baseAmount}" /></td>
				<td><c:out value="${dataList1.igstRate}" /></td>
				<td><c:out value="${dataList1.igstAmount}" /></td>
				<td><c:out value="${dataList1.cgstRate}" /></td>
				<td><c:out value="${dataList1.cgstAmount}" /></td>
				<td><c:out value="${dataList1.sgstRate}" /></td>
				<td><c:out value="${dataList1.sgstAmount}" /></td>						
			</c:if>			
			</tr>
			<%  counter+=1;	%>
	</c:forEach>
		</table>
</c:if>
				</div>
			</div>
		</div>
	</div>
</div>	
</body>
</html>

<script type="text/javascript">
	function getDisbursedFlag(){
		//gs=='disbused'){
		document.getElementById('A').action = "/SV/disburseNonDisburseData.html?rpNumber=";
		document.getElementById('A').submit();
	}

	function getNonDisbursedFlag(){
		alert("hii "+arg.value);
		//var disbursed = document.getElementById("disbused").value;
		//var nonDisbursed = document.getElementById("nonDisbused").value;
	if(args!=null && args=='nonDisbused'){
		document.getElementById('A').action = "/SV/disburseNonDisburseData.html?rpNumber=";
		document.getElementById('A').submit();
	}
	}
	function buckToHome() {
		//alert('Back');
		document.getElementById('A').action = "/SV/danAllocation.html";
		document.getElementById('A').submit();
	}
</script>

