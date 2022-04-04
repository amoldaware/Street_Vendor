<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MLI User Registration Form</title>
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<%
	String s=(String)request.getAttribute("SName");
%>
</style>
<script type="text/javascript">



</script>

<script type="text/javascript">
$(window).load(function() {
	$(".loader").fadeOut("slow");
})
</script>

</head>
<body>

<!-- <div class="main-section">

<div class="container-fluid">
<div class="row">
<div>
	<div class="frm-section">
	<div class="col-md-12">
		
	</div>	
	</div>
</div>
</div>
</div> -->

	<h2 class="pageHeader">MLI Tax Invoice Report</h2>
	<font color="green" size="3"><b>${message}</b></font>
	<div class="loader"></div>
			
	<form:form method="POST" action="/SV/save.html">
	  	<table align="center" border="0" cellspacing=0 cellpadding=10 >
	
		</form:form>
	<c:if test="${!empty employees}">

		<table align="center" border="1">
			<tr>
			    <td class="tableHeader1">SR.NO.</td>
			 
				<td class="tableHeader1">MEMBER ID</td>
				<td class="tableHeader1">NBFC NAME</td>
				<td class="tableHeader1">PORTFOLIO NO</td>
				<td class="tableHeader1">SANCTION AMOUNT</td>
				<td class="tableHeader1">APPROVED AMOUNT</td>
				<th>Action </th>
			</tr>
<% int counter=0;%>
			<c:forEach items="${employees}" var="employee">
				<tr <% if(counter%2==0){%>class="bgcolor" <%}%>  >
				    <td><%=counter+1%></td>
				  
					<td><c:out value="${employee.mem_id}" /></td>
					<td><c:out value="${employee.nbfc_name}" /></td>
					<td><c:out value="${employee.portfoliono}" /></td>
					<td><c:out value="${employee.sanction_amt}" /></td>
					<td><c:out value="${employee.approve_amt}" /></td>
				  <td> <c:out value="" /><a href="callJasperReportOfTax.html?memID=${employee.mem_id}">Download</a>
		    
<%  counter+=1;%>
				</tr>
				
			</c:forEach>
		</table>
	</c:if>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<div class="loader"></div>
	<script type="text/javascript">

		$(window).load(function() {
			$(".loader").fadeOut("slow");
		});
		
	</script>
</body>
</html>