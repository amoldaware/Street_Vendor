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
<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">


</head>

<body>
<div class="main-section">

<div class="container-fluid">
<!-- <div class="row"> -->
	<div>
	 <nav aria-label="breadcrumb">
  <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/getAppropriationDataBetweenDates.html">Appropriation Payment for Guarantee</a></li>  
    <li class="breadcrumb-item active" aria-current="page">Appropriation Payment</li>
  </ol> 
</nav> 
	<div class="tbl-details">
		<div class="col-md-12">
		<div class="d-inlineblock float-right ">
		<!-- <label style="padding-top:8px; font-weight:100;">Search :</label> -->
		<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px;
  		  background-color: #ffffff;" placeholder="Search Data.." title="Type in a name">    		
    		<button style="border:none !important; cursor: not-allowed;">
    			<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
    		</button>			
		</div>
		
			<form:form method="POST" id="A" >	
				<c:if test="${!empty dataList}">
			<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0 danRpDataTable">
				<thead>
					<tr>
					<th >SR.NO.</th>				
					<th >MEMBER ID</th>					
					<th >NAME OF NBFC</th>
					<th >PAYMENT ID</th>
					<th >INWARD AMOUNT IN RS.</th>
					<th >ALOCATED AMOUNT IN RS.</th>
					<th >INSTRUMENT NO</th>
					<th >INSTRUMENT AMOUNT IN RS. </th>
					<th >INSTRUMENT DATE </th>
				</tr>
				</thead>
				<% int counter=0;%>
				<c:forEach items="${dataList}" var="dataList">
					<%-- <tr <% if(counter%2==0){%>bgcolor="silver" <%}%>> --%>
					<tr>
					<td><c:out value="<%=counter+1%>" /></td>
					<td ><c:out value="${dataList.memberId}" /></td>
					<td ><c:out value="${dataList.memberName}" /></td>
					<td ><a
						href="/SV/getAppropriatePayment.html?paymentId=${dataList.rpNumber}"><c:out
						value="${dataList.rpNumber}" /></a></td>
					<!--<td><c:out value="${dataList.rpNumber}" /></td>
					--><td><c:out value="${dataList.inwardAmount}" /></td>
					<td><c:out value="${dataList.allocatedAmount}" /></td>
					<td><c:out value="${dataList.virtualAccountNumber}" /></td>
					<td><c:out value="${dataList.instrumentAmount}" /></td>
					<td><c:out value="${dataList.instrumentDate}" /></td>

							<%  counter+=1;%>
				</c:forEach>								
			</table>
				
				</c:if>
		
		<c:if test="${empty dataList}">
		<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0 danRpDataTable">
				<thead>
					<tr>
								<th >SR.NO.</th>				
					<th >MEMBER ID</th>					
					<th >NAME OF NBFC</th>
					<th >PAYMENT ID</th>
					<th >INWARD AMOUNT IN RS.</th>
					<th >ALOCATED AMOUNT IN RS.</th>
					<th >INSTRUMENT NO</th>
					<th >INSTRUMENT AMOUNT IN RS. </th>
					<th >INSTRUMENT DATE </th>
				</tr>
				</thead>
				
					<tr>
					<td></td>				
					<td></td>
					<td ></td>
					<td ></td>
					<td align="center">Data not available</td>					
					
					<td ></td>
					<td ></td>
					<td ></td>					
					<td ></td>	
					</tr>
		</table>
		</c:if>
		
		
			</form:form>
		</div>
	</div>
	
	</div>	
</div>
</div>

	<!-- <div
		STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;"> -->
	<%-- <h3>NBFC Appropriation</h3>
	<hr><form:form method="POST" id="A" >
	<div	"STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;">
		<table  cellpadding=5 cellspacing=5 class="danRpDataTable" align=center width=90%  >										
				<c:if test="${!empty dataList}">										
				<tr><td class="tableHeader1"></td>				
					<td class="tableHeader1"></td>
					<td class="tableHeader1"></td>					
					<td class="tableHeader1"></td>
					<td class="tableHeader1"></td>				
					<td class="tableHeader1"></td>
					<td class="tableHeader1"></td>					
					<td class="tableHeader1"></td>
					<td class="tableHeader1"></td>					
				</tr>
				
				</c:if>
		
	
	</table>
	</div>
	</form:form> --%>
	<!-- </div> -->

</body>



</html>



