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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
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
<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">

<script>
	$(function() {

		$("#ratingDate").datepicker({
			dateFormat : 'dd-mm-yy'
		});

	});
</script>
</head>

<body>
<div class="main-section">
<div class="container-fluid">
<nav aria-label="breadcrumb">
  <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/danAllocationASF.html">DAN Allocation For ASF</a></li>  
    <li class="breadcrumb-item active" aria-current="page">Confirm Guarantee Fee Allocation</li>
  </ol>
</nav>
<!-- <div class="row"> -->
	<div>	

		<div class="tbl-details">
		<div class="col-md-12">
		<h2 align="center" class="heading">Confirm Guarantee Fee Allocation</h2>
		<!-- <h5 style=" display: inline-block; text-align: center;"> <strong>Confirm Guarantee Fee Allocation	</strong></h5> -->
		<div class="d-inlineblock float-right ">
		<!-- <label style="padding-top:8px; font-weight:100;">Search :</label> -->
		<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px;
  		  background-color: #ffffff;" placeholder="Search Data.." title="Type in a name">    		
    		<button style="border:none !important; cursor: not-allowed;">
    			<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
    		</button>			
		</div>			
			<form:form method="GET" id="A">	
				<c:if test="${!empty danDetailList}">			
			<table  class="table table-bordered table-hover cus-table mt-10 mb-10">
				<thead>		
				<tr>
				    <th>SR.NO.</th>
					<th>PORTFOLIO NAME</th>
					<th>FILE NAME</th>
					<th>DAN NUMBER</th>
					<th>TOTAL FEE</th>
					<th>SELECT FOR RP GENERATION</th>
					
				</tr>	
				</thead>			
	  		<% int counter=0;%>
				<c:forEach items="${danDetailList}" var="mliList" varStatus="loopStatus">
					<tr <% if(counter%2==0){%>bgcolor="silver" <%}%>>
					<td><%=counter+1%></td>
					
						<td><c:out value="${mliList.portfoliName}" /></td>
						<td><c:out value="${mliList.fileName}" /></td>
					    <td><c:out value="${mliList.DAN_ID}" /></td>
						<td><c:out value="${mliList.totalFee}" /></td>
						
						<%-- <td align="center"><c:out value="Y" /></td> --%>
						<td align="center"><form:checkbox path="checkbtn" value="${mliList.DAN_ID}@${mliList.portfolioRate}@${mliList.totalFee}@${mliList.portfoliName}@${mliList.fileName}@${mliList.mliId}"  cssClass="chcktbl" checked="checked"/></td>
					   		<%  counter+=1;%>
					   		</tr>
					  <tr>
					  <td></td>
					  <td></td>
					  <td></td>
					  <td ><strong>Total Amount</strong></td>
					  	<td><strong><c:out value="${totalOfAmount}" /></strong></td>
					  	<td></td>
					  </tr> 		
					   		
				</c:forEach>			
			</table>
				</c:if>
			</form:form>
			
			<div class="d-inlineblock">
				<input type="submit" value="Generate RP Number" onclick="submitForApproval()" class="btn btn-reset" />
	<input type="submit" value="Back" onclick="buckToHome()" class="btn btn-reset" />
			</div>
			
		</div>
		</div>
		
	</div>
</div>
</div>	
		
	
</body>
<script type="text/javascript">
function buckToHome() {
	//alert('Back');
	
	document.getElementById('A').action = "/nbfc/danAllocationASF.html";
	document.getElementById('A').submit();
}

function submitForApproval() {
//	alert('Approve');
	//var totalOfAmount=document.getElementById("totalOFAmount").value;
	
	//var totalOfAmo=${totalOfAmount};
	/* var totalOfAmo="Saurav";
	alert(totalOfAmo); */
	document.getElementById('A').action = "/SV/rpNumberGenrationASF.html";
	document.getElementById('A').submit();
}
function searchRecord(){
	var nameSearch = document.getElementById("nameSearch").value;
	var searchValue = document.getElementById("searchValue").value;
	alert('search  :'+nameSearch+' searchValue  :'+searchValue);
	if(nameSearch!=null || searchValue!=null){
		document.getElementById('A').action = "/SV/mlidetailsByIndex.html";
		document.getElementById('A').submit();
	}
	
}

</script>

</html>



