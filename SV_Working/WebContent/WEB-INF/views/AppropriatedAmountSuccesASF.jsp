<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Appropriation</title>
<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">


</head>
<body bgcolor="#E0FFFF">

<div class="main-section">
<div class="container-fluid">
<!-- <div class="row"> -->
	<div>	
		<div class="frm-section">
			<div class="col-md-12">
			<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0 danRpDataTable">
<tr>
<td class="notification-message" id="successMsg"><strong>${message}</strong></td>

</tr>
<tr>
	<th>TOTAL RECEIVED AMOUNT:</th>
	<td>	${receivedAmount}</td>
</tr>
<tr>
	<th><font face=arial>TOTAL APPROPRIATED AMOUNT:	</th>
	<td><font face=arial>	${appropriatedAmount}</td>
</tr>
<tr>
	<th><font face=arial>SHORT / EXCESS :	</th>
	<td><font face=arial>	${shortOrExcess}</td>
</tr>
</table>
			
		</div>
		</div>
		
	</div>
	</div>
</div>	





	
</body>
</html>