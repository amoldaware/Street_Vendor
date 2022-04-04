<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script  src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
	</head>
	
	<body bgcolor="#E0FFFF">
	   <h2 style="width:100%; color:#5c4324; font-size:20px" align="center" >Batch files rejected by CGTMSE</h2>
		<form:form  method="POST" action="test2.html" id="A">
	   		<table border="0" align="center"  >
	   			<tr></tr>
	   			<tr></tr>
	   			<tr>
	   				<td style="color: #5c4324;"  align="left"><b>DATE OF UPLOAD</b></td>
	   				<td></td>
	   				<td style="color: #5c4324;"  align="left"><b>MLI NAME</b></td>
	   				<td></td>
	   				<td style="color: #5c4324;" align="left"><b>PORTFOLIO NO</b></td>
	   				<td></td>
	   				<td style="color: #5c4324;" align="left"><b>QUARTER FILE DETAILS</b></td>
	   				<td></td>
	   				<td style="color: #5c4324;"  align="left"><b>FILE ID/FILE NAME</b></td>
	   				<td></td>
	   				<td style="color: #5c4324;" align="left"><b>NO.OF RECORDS</b></td>
	   				<td></td>
	   				<td style="color: #5c4324;" align="left"><b>TOTAL SANCTIONED AMOUNT</b></td>
	   				<td></td>
	   				<td style="color: #5c4324;" align="right"><b>DETAILS</b></td>
	   				<td></td>
	   				<td style="color: #5c4324;"></td>
	   			</tr>
			 	<tr></tr>
	   			<tr></tr>
			  	
			   <c:forEach items="${lists}" var="listValue" varStatus="loop" > 
			    <tr>
			  	 	<td  align="left">${lists[loop.index].nbfcUploadedDate}</td>
			  	 	<td></td>
			   		<td  align="left">${lists[loop.index].shortName}</td>
			   		<td></td>
			   		<td  align="right">${lists[loop.index].portfolioNo}</td>
			        <td></td>
			        <td  align="right">${lists[loop.index].portfolioQuarter}</td>
			        <td></td>
			        <td  align="right">${lists[loop.index].filePath}</td>
			        <td></td>
			        <td  align="right">${lists[loop.index].noOfRecords}</td>
			        <td></td>
			        <td  align="right">${lists[loop.index].sanctionAmount}</td>
			        <td></td>
			    </tr> 
			    <tr></tr>
			    </c:forEach>
			    <tr></tr>
			    <tr></tr>
			    <tr></tr>
			    <tr></tr>
			    <tr></tr>
			    <tr><td colspan="2" align="center"><input type="submit" value="Back"	onclick="exitMLIDetails()" class="button" /></td></tr>	
		</table> 
		</form:form>
</body>
	<script type="text/javascript">
	function exitMLIDetails() {
	
		document.getElementById('A').action = "/SV/cgtmseMakerForBacthApprovalRMBack.html";
		document.getElementById('A').submit();
	}
	</script>
</html>
<style type="text/css">

.loader {
	position: fixed;
	left: 0px;
	top: 0px;
	width: 100%;
	height: 100%;
	z-index: 9999;
	}

.error {
	color: red;
	font-weight: bold;
}

.h3 {
	color: red;
	font-weight: bold;
}

.button {
	background-color: #6495ED;
	border: none;
	color: white;
	padding: 12px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: -02px 0px;
	cursor: pointer;
}
.form-group.required .control-label:before{
   color: red;
   content: "*";
   position: absolute;
   margin-left: -15px;
}


</style>
	
