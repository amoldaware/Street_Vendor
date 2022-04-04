<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	//String sN = (String) request.getAttribute("sName");
	//String expLim = (String) request.getAttribute("eXposureLimit");
%>
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
		<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
	</head>
<body>
	<div class="main-section">
		<div class="container-fluid">
			<div>	
				<div class="tbl-details">
					<div class="col-md-12">
						<form:form method="GET" id="A">
							<div class="d-inlineblock">
								<input type="submit" value="Back" class="btn btn-reset" onclick="backToHomePage()" />
							</div>
	       <div class="d-inlineblock float-right">
				<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px; background-color: #ffffff;" placeholder="Search Data.." title="Type in a name">    		
	    		<button style="border:none !important; cursor: not-allowed;">
	    			<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
	    		</button>			
			</div>
		<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0">
				<thead>
					<tr>
						<c:forEach items="${rows[0]}" var="column">							
							<th class="tableHeader1"><c:out value="${column.key}" /></th>
						</c:forEach>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${rows}" var="columns">
						<tr>
							<c:forEach items="${columns}" var="column">
								<td>${column.value}</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
		</table>
             </form:form>
		</div>
	</div>
	</div>
</div>
</div>	
</body>
	<script type="text/javascript">
		function backToHomePage() {
		document.getElementById('A').action = "/SV/nbfcCheckerHome.html";
		document.getElementById('A').submit();
	}
	</script>
</html>



