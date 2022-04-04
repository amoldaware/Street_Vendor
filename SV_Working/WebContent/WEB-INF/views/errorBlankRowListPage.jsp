<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>MLI User Registration Form</title>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
		<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/css/customstyle.css" rel="stylesheet" type="text/css">
		<%
			String s = (String) request.getAttribute("SName");
		%>
	</head>
<body bgcolor="#E0FFFF">
	<div class="main-section">
		<div class="container-fluid">
	<div>	
		<div class="tbl-details">		
			<div class="col-md-12">
				<h3 align="center" class="error1">
				    "File not succesfull due to following error in data in excel file uploaded" 
					    Kindly correct the following data in excel file and upload again.
				</h3>
	<input type="submit" value="Back" class="button" />
	<form:form method="GET" action="/SV/mliMaker.html">
	 <% int srno=1;%>
    <c:forEach items="${errorBlankRowList}" var="list">	
		<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0">
			<tr class="tableHeader1">
				<td>Sr.No</td>
				<td>Row Number</td>
				<td>Error Detail</td>
			</tr>
			<tr>
				 <td align=center><c:forEach items="${list.value}" var="listItem" varStatus="loop" > ${loop.index+1} <br/></c:forEach></td>
				 <td align=center><c:forEach items="${list.value}" var="listItem"> ${listItem} <br/></c:forEach></td>
				 <td align=center><c:forEach items="${list.key}" var="listItem">  ${listItem} <br/></c:forEach></td>
			</tr>
			<tr class="tableHeader1"><td colspan=3></td></tr>
	  </table>
   </c:forEach>
 </form:form>
		</div>
	</div>	
</div>
</div>
</div>	

<%-- 
<div style="width: 100%; height: 100%">



	<h3 align="center">
	    <font color=red>!! File Upload not succesfull due to following error in data in excel file uploaded !! 
	                       <br>Kindly correct the following data in excel file and upload again.</font>
	</h3>
	<form:form method="GET" action="/SV/mliMaker.html">
                    <% int srno=1;%>
		<c:forEach items="${errorBlankRowList}" var="list">
		
	<table cellpadding=5 cellspacing=5 align=center   border=1 >
	
				<tr class="tableHeader1">
				    <td>Sr.No</td>
					<td>Row Number</td>
					<td>Error Detail</td>
				</tr>
				<tr>
				    <td align=center><c:forEach items="${list.value}" var="listItem" varStatus="loop" > ${loop.index+1} <br/></c:forEach></td>
					<td align=center><c:forEach items="${list.value}" var="listItem"> ${listItem} <br/></c:forEach></td>
					<td align=center><c:forEach items="${list.key}" var="listItem">  ${listItem} <br/></c:forEach></td>
				</tr>
				<tr class="tableHeader1"><td colspan=3></td></tr>
		
	</table>

		</c:forEach>
		 <br><br>
		<div align="center"><input type="submit" value="Back" class="button" />
	</div>
		</form:form>
		</div> --%>
</body>
</html>