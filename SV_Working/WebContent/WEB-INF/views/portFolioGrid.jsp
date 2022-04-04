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
<title>Portfolio Creation</title>
<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
<script type="text/javascript"
	src="jquery-ui-1.10.3/ui/jquery.ui.datepicker.js"></script>
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">

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
<!-- <div class="row"> -->
	<div>	
	
		<div class="tbl-details">
		<div class="col-md-12">
		   <form:form method="GET" id="A" class="form-horizontal">
			 <div class="col-md-2 col-sm-4 col-xs-12">
			<div class="form-group">
			<div class="col-md-12 prl-10">				
				<label>Portfolio Name</label>
				 <input type="text" class="form-control cus-control d-none" placeholder="Portfolio Name" > 
				<form:select path="nameSearch" id="nameSearch" class="form-control cus-control">
				   <form:option value="" label="--Select Column Name--" />
				   <form:option value="Long_name" label="Long Name" />
				   <form:option value="portfolioName" label="Portfolio Name" />
		       </form:select>
					<form:errors path="nameSearch" cssClass="error" />
			</div>
			</div>
			</div>
			
			 <div class="col-md-2 col-sm-4 col-xs-12">
			<div class="form-group">
			<div class="col-md-12 prl-10">
				<label>Value</label>
				<form:input type="text" path="searchValue" id="searchValue" class="form-control cus-control"/>
				<form:errors path="searchValue" cssClass="error" />
			</div>
			</div>
			</div>
			
			</form:form>   
			
			<div class="d-inlineblock mt-25">		
			<input value="Search"  type="button" onclick="searchRecord()" class="btn btn-reset" />		
					<a href="/SV/sanctionDetailsPageRM.html"><input type=button value="Add Portfolio" class="btn btn-reset " ></a>
					<input type="button" value="Reset"  class="btn btn-reset" />
					<input type="button" value="Download" onclick="resetForm()" class="btn btn-reset" />					
			<input type="submit" value="Print" onclick="resetForm()" class="btn btn-reset" />			
			</div>
			
			<div class="d-inlineblock float-right  mt-25">
		<!-- <label style="padding-top:8px; font-weight:100;">Search :</label> -->
		<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px;
  		  background-color: #ffffff;" placeholder="Search Data.." title="Type in a name">    		
    		<button style="border:none !important; cursor: not-allowed;">
    			<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
    		</button>			
		</div>
		
		<c:if test="${!empty portfolioList}">
		<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0">
		<thead>
			<tr>
					<th >PORTFOLIO NO</th>						
					<th >LONG NAME</th>					
					<th >MAX PORTFOLIO SIZE</th>
					<th >PORTFOLIO NAME</th>
					<th >PORTFOLIO STATUS</th>
					<th >PORTFOLIO START DATE</th>
					<th >ACTIONS ON ROW</th>
				</tr>
		</thead>
			<% int counter=0;%>
				<c:forEach items="${portfolioList}" var="portfolioList">
					<%-- <tr <% if(counter%2==0){%>bgcolor="silver" <%}%>> --%>
					<tr>
						<td><c:out value="${portfolioList.portfolioNo}" /></td>					
						<td><c:out value="${portfolioList.long_name}" /></td>
						<td><c:out value="${portfolioList.max_portfolio_size}" /></td>
						<td><c:out value="${portfolioList.portfolioName}" /></td>
						<td><c:out value="${portfolioList.portfolioStatus}" /></td>
						<td><c:out value="${portfolioList.portfolioStartDate}" /></td>						
						<td align="center" class="btn-edit"><!-- <a href=""> -->Edit <!-- </a> | Delete --> </td>
			</tr>
						<%-- <td align="center"><a
							href="getDetails.html?mliLongName=${mliList.mliLongName}">Edit</a>
							| <a href="deleteMLIDetails.html?id=${employee.mliLongName}">Delete</a></td> --%>
					
				</c:forEach>	
				<%  counter+=1;%>			
		</table>
		
		</c:if>
		
		</div>
		</div>	
	</div>
</div>
</div>	
	<!-- <div
		STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;"> -->
	<%-- <h1>Portfolio Registration Page</h1>
	<hr>
		<table  cellpadding=5 cellspacing=5 align=center width=90%  >			
				<tr>
					<td colspan=5><table border="0">							
								<tr>
								  <!-- <td></td>
								  <td>&nbsp;</td>			               
			             		  <td><a href=""><input type=button value="Download"></a></td>
			               		  <td>&nbsp;</td>
			               		  <td><a href=""><input type=button value="Print"></a></td>
			               		  <td>&nbsp;</td> -->
								
							  <td>
							  <form:form method="GET" id="A">
							  <td><form:select path="nameSearch" id="nameSearch">
							   <form:option value="" label="----Select Column Name----" />
							   <form:option value="Long_name" label="Long Name" />
							   <form:option value="portfolioName" label="Portfolio Name" />
						       </form:select></td>
						       <td><form:errors path="nameSearch" cssClass="error" /></td>
					          <td><form:input type="text" path="searchValue" id="searchValue"/>
					          <td></td>
					          </td><td></td> 
						       </form:form></td>
					       
					       </tr>
				</table>
				</td></tr>
		
				
				

				
			
		</table> --%>
	
	<!-- </div> -->

</body>

<script type="text/javascript">
function searchRecord(){
	var nameSearch = document.getElementById("nameSearch").value;
	var searchValue = document.getElementById("searchValue").value;
	//if(nameSearch!="" && searchValue!=""){
		document.getElementById('A').action = "/SV/seachDataByNames.html";
		document.getElementById('A').submit();
	//}else{
   //     alert("Please Select values for search data....")
	//}
	
}

</script>

</html>



