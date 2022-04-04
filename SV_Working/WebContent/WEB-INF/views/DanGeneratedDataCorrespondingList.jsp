<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<title>Portfolio Creation</title>
		<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
		<script type="text/javascript" src="jquery-ui-1.10.3/ui/jquery.ui.datepicker.js"></script>
		<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
		<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
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
				<div>	
				 <nav aria-label="breadcrumb">
  <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/danGenerateRpNumberForPaymentChecker.html">DAN Allocation</a></li>  
    <li class="breadcrumb-item active" aria-current="page">RP Number Details</li>
  </ol> 
</nav> 
					<div class="tbl-details">
						<div class="col-md-12">
							<div class="d-inlineblock float-right ">
								<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px; background-color: #ffffff;" placeholder="Search Data.." title="Type in a name">   
    							<button style="border:none !important; cursor: not-allowed;">
    								<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
    							</button>			
							</div>
		
<form:form method="GET" id="A">	
	<c:if test="${!empty dataList1}">
		<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-10 danRpDataTable">
			<thead>
				<tr>
					<th>SR.NO.</th>				
					<th>PORTFOLIO NAME</th>					
					<th>FILE NAME</th>
					<th>TOTAL FEE</th>					
				</tr>
			</thead>
		<% int counter=0;
		double totalFee=0.0;
		%>
		<c:forEach items="${dataList1}" var="dataList1">
			<tr <% if(counter%2==0){%>bgcolor="silver" <%}%>>
				<td><c:out value="<%=counter+1%>" /></td>					
				<td><c:out value="${dataList1.portfolioName}" /></td>
				<td><a href="/SV/disburseNonDisburseData.html?file_Id=${dataList1.fileName}&disbursedStatus=Y"><c:out value="${dataList1.fileName}" /></a></td>
				<td><c:out value="${dataList1.totalFee}" /></td>													
			</tr>
				<%  counter+=1;%>
		</c:forEach>					
		</table>
		<div class="d-inlineblock">
			<a href="/SV/danGenerateRpNumberForPaymentChecker.html" class="btn btn-reset">Back</a>
		</div>
		</c:if>
		<c:if test="${empty dataList1}">	
			<table  class="table table-bordered table-hover cus-table mt-10 mb-10 danRpDataTable">
		<thead>
				<tr>
						<th>SR.NO.</th>				
					<th>PORTFOLIO NAME</th>					
					<th>FILE NAME</th>
					<th>TOTAL FEE</th>						
				</tr>
		</thead>
				<tr>
					<td></td>				
					<td></td>				
					<td align="center">Data not available</td>					
					<td ></td>
				</tr>													
			</table>
			<div class="d-inlineblock">
				<a href="/SV/danGenerateRpNumberForPaymentChecker.html" class="btn btn-reset">Back</a>
			</div>
		</c:if>
					</form:form>
				</div>
			</div>	
		</div>
	</div>
</div>	

</body>
<script type="text/javascript">
function selectAllCheckBox() {
    if (document.getElementById('select_all').checked == true) {
        $('.chcktbl').each(function() {
            this.checked = true;
        });
        $selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
    	var count=$selectedCBs.length-1;
    	document.getElementById('count').innerHTML=count;
    } else {
        $('.chcktbl').each(function() {
            this.checked = false;
        });
        $selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
    	var count=$selectedCBs.length;
    	document.getElementById('count').innerHTML=count;
    }

}

function uncheckSelectAll()
{
	$('.chcktbl').click(function () {
        if (this.checked == false) {
            $('#select_all').attr('checked', false);
        }
        $selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
    	var count=$selectedCBs.length;
    	document.getElementById('count').innerHTML=count;
    });
	$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
	var count=$selectedCBs.length;
	document.getElementById('count').innerHTML=count;
	
}

</script>

</html>



