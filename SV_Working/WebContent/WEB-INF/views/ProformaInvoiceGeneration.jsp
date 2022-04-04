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
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<title>Proforma Invoice</title>
		<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
		<script type="text/javascript" src="jquery-ui-1.10.3/ui/jquery.ui.datepicker.js"></script>
		<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
  </head>

<body>
	<div class="main-section">
		<div class="container-fluid">
			<div>	
				<div class="tbl-details">
					<div class="col-md-12">
						<h2 align="center" class="heading">Generate Proforma-Invoice</h2>
						<div class="d-inlineblock float-right ">
							<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px; background-color: #ffffff;" placeholder="Search Data.." title="Type in a name">   
    						<button style="border:none !important; cursor: not-allowed;">
    							<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
    						</button>			
						</div>
<form:form method="POST" id="A" class="form-horizontal">
		
	<c:if test="${!empty dataList}">
		<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-10 danRpDataTable">
			<thead>
			    <tr>
					<th>SR.NO.</th>				
					<th>MLI NAME</th>					
					<th>PORTFOLIO NAME</th>
				<!--<th>DAN ID</th>  -->	
					<th>GENERATE</th>									  					
				</tr>
			</thead>
			<% int counter=0;%>
				<c:forEach items="${dataList}" var="dataList">
				<tr>
					<td><c:out value="<%=counter+1%>" /></td>
					<td align="center"><c:out value="${dataList.bankname}" /></td>
					<td align="center"><c:out value="${dataList.portfolioName}" /></td>
			    	<input type="hidden" value="${dataList.userId}" />
				    <td align="center"><a href="/SV/callJasperReportOfProforma.html?portfolioName=${dataList.portfolioName}&userId=${dataList.userId}">Generate ProformaInvoice</a></td>											
				</tr>
				<%  counter+=1;%>
			</c:forEach>
		</table>	
 </c:if>		
		
<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0">
	<c:if test="${empty dataList}">
		<thead>
			<tr>
			       <th>SR.NO.</th>				
					<th>MLI NAME</th>					
					<th>PORTFOLIO NAME</th>
				
					<th>GENERATE</th>			
			</tr>
		</thead>
			<tr>							
				<td colspan="5" align="center">Data not available</td>									
			</tr>
	</c:if>	
</table>
</form:form>
                 </div>
		 	</div>
		</div>
	</div>
</div>		
</body>

</html>



