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
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<title>Mli Detail Page M</title>
<script src="js/jquery-1.11.1.js" type="text/javascript"></script>

  
    <link href = "css/jquery-ui-css.css" rel="stylesheet" type="text/css">
 
 <style type="text/css">
  div.ui-datepicker{	top:210px !important;	}
  
  </style>
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
					<label>By Name111</label>
					 <input type="text" class="form-control cus-control d-none" placeholder="By Name" > 
					<form:select path="nameSearch" id="nameSearch" class="form-control cus-control">
						<form:option value="" label="--Select Column Name--" />
						<form:option value="longName" label="Long Name" />
						<form:option value="emailId" label="Email ID" />
						<form:option value="companyPAN" label="Company PAN" />
						<form:option value="status" label="Status" />
					</form:select>
						<form:errors path="rating" cssClass="error" />
						<form:errors path="nameSearch" cssClass="error" />
				</div>
				</div>
				</div>
				
			 <div class="col-md-2 col-sm-4 col-xs-12">
			<div class="form-group">
			<div class="col-md-12 prl-10">
				<label>Value</label>
				<form:input type="text" path="searchValue" class="form-control cus-control"	id="searchValue" />
				<form:errors path="searchValue" cssClass="error" />
			</div>
			</div>
			</div>					
								
			</form:form>
			<div class="d-inlineblock mt-25">		
			<input value="Search" type="button" onclick="searchRecord()" class="btn btn-reset" /> 	
					<a href="/SV/newMLIRegistration.html"><input type=button value="New MLI Registration" class="btn btn-reset " ></a>
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
				
	<c:if test="${!empty mlisList}">			
		<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0">
		<thead>
			<tr>
					<th>SR.No.</th>
									<th>NBFC NAME</th>
									<th>CONTACT PERSON</th>
									<th>CONTACT NUMBER</th>
									<th>COMPANY PAN</th>
									<th>EMAIL ID</th>
									<!-- <th>STATUS</th> -->
									<th>ACTION ON ROWS</th>
			</tr>
		</thead>
			<%
				int counter=0;
			%>
			<c:forEach items="${mlisList}" var="mliList">
				<%-- <tr <%if(counter%2==0){%> bgcolor="silver" <%}%>> --%>
				<tr>
					<td><%=counter+1%></td>
					<td><c:out value="${mliList.mliLongName}" /></td>
					<td><c:out value="${mliList.contactPerson}" /></td>
					<td><c:out value="${mliList.mobileNUmber}" /></td>
					<td><c:out value="${mliList.emailId}" /></td>
					<td><c:out value="${mliList.companyPAN}" /></td>
					<td><c:out value="${mliList.status}" /></td>
					<td align="center" class="btn-edit"><c:choose>
							<c:when test="${mliList.status=='Approved'}">
								<a href="getDetails.html?mliLongName=${mliList.mliLongName}" class="btn-edit">Edit</a> <!-- | Delete -->
								</c:when>
							<c:when test="${mliList.status=='Panding for Approval(Edit)'}">
								<span class="btn-edit not-allow">	Edit </span><!-- | Delete -->
								</c:when>
							<c:when test="${mliList.status=='Rejected'}">
								<a href="getDetails.html?mliLongName=${mliList.mliLongName}" class="btn-edit">Edit</a> <!-- | Delete -->
								</c:when>
							<c:when test="${mliList.status=='CEMMA'}">
								<span class="btn-edit not-allow">	Edit </span>	<!-- | Delete -->
								</c:when>
							<c:when test="${mliList.status=='Panding For Approval(New)'}">
								<span class="not-allow">	  </span> <!-- | Delete -->
								</c:when>
							<c:otherwise>
								<span class="btn-edit not-allow">	Edit  </span> <!-- | Delete -->
								</c:otherwise>
						</c:choose></td>


					<%-- <td align="center"><a
							href="getDetails.html?mliLongName=${mliList.mliLongName}">Edit</a>
							| <a href="deleteMLIDetails.html?id=${employee.mliLongName}">Delete</a></td> --%>
					<%
						counter+=1;
					%>
				</tr>
			</c:forEach>					
		</table>
		<!-- <ul class="pagination mtb-0"  >
			 <li><a href="#">Previous</a></li>
				  <li class="active"><a href="#">1</a></li>
				  <li ><a href="#">2</a></li>
				  <li><a href="#">3</a></li>
				  <li><a href="#">4</a></li>
				  <li><a href="#">5</a></li>
				  <li><a href="#">6</a></li>
				  <li><a href="#">7</a></li>
				  <li><a href="#">8</a></li>
				  <li><a href="#">9</a></li>
				  <li><a href="#">10</a></li>
				  <li><a href="#">Next</a></li>
				</ul>	 -->
	</c:if>	
		</div>
		</div>
	</div>
</div>
</div>	
	<!-- <div
		STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;"> -->
<!-- 	<h1>MLI Registration Page</h1>
	<hr> -->
	<table cellpadding=5 cellspacing=5 align=center width=90%>
		<tr>
			<!-- <td colspan=5><table border="0">
					<tr>
						<td><a href="/SV/newMLIRegistration.html"><input
								type=button value="Add MLI"></a></td>
						<td>&nbsp;</td>
						<td><a href=""><input type=button value="Download"></a></td>
						<td>&nbsp;</td>
						<td><a href=""><input type=button value="Print"></a></td>
						<td>&nbsp;</td>

						<td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
										<td></td>	
								<td></td>
							</td>

					</tr>
				</table></td> -->
		</tr>
		
			<tr>
				
			</tr>

		
		

	</table>

	<!-- </div> -->

</body>
<script type="text/javascript">
	function searchRecord() {
		var nameSearch = document.getElementById("nameSearch").value;
		var searchValue = document.getElementById("searchValue").value;
		//alert('search  :' + nameSearch + ' searchValue  :' + searchValue);
		if (nameSearch != null || searchValue != null) {
			document.getElementById('A').action = "/SV/mlidetailsByIndex.html";
			document.getElementById('A').submit();
		}

	}
</script>

</html>



