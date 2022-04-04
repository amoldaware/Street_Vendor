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
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<title>Mli Detail Page M</title>
<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
  
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
	  function preventBack() { window.history.forward(); }  
	    setTimeout("preventBack()", 0);  
	    window.onunload = function () { null };
	    
</script>

</head>

<body oncontextmenu="return false;">
<div class="main-section">
<div class="container-fluid">
<!-- <div class="row"> -->
	<div>	
		<div class="tbl-details">
		<div class="col-md-12">
			<form:form method="GET" id="A" class="form-horizontal">
			<input type="hidden" name="csrfPreventionSalt" value="${csrfPreventionSalt}"/>
			  <span Class="error">${message}</span>
				<div class="col-md-2 col-sm-4 col-xs-12">
				<div class="form-group">
				<div class="col-md-12 prl-10">				
					<label>SEARCH MLI</label>
					 <input type="text" class="form-control cus-control d-none" placeholder="By Name" > 
					<form:select path="nameSearch" id="nameSearch" class="form-control cus-control">
						<form:option value="" label="--Select Column Name--" />
						<form:option value="longName" label="Name of the MLI" />
						<form:option value="emailId" label="Email ID" />
						<form:option value="companyPAN" label="PAN of Institution" />
						<%-- <form:option value="status" label="Status" /> --%>
					</form:select>
						<form:errors path="rating" cssClass="error" />
						<form:errors path="nameSearch" cssClass="error" />
				</div>
				</div>
				</div>
				
				 <div class="col-md-2 col-sm-4 col-xs-12">
					<div class="form-group">
						<div class="col-md-12 prl-10">
							<label style="visibility: hidden;"> value</label>
							<form:input type="text" path="searchValue" placeholder="Search Here.." class="form-control cus-control"	id="searchValue" style="top-margine:50"/>
							<form:errors path="searchValue" cssClass="error" />
						</div>
					</div>
				</div>
			
							
								
			</form:form>
			<div class="d-inlineblock mt-25">		
				<input value="Search" type="button" onclick="searchRecord()" class="btn btn-reset" /> 	
					<a href="/SV/newMLIRegistration.html"><input type=button value="New MLI Registration" class="btn btn-reset " ></a>
			</div>
			
			<button style="border:none !important;">
		    		<a href="/SV/mliRegistrationPage.html">
		    			<img src="images/refresh.png" alt="" data-toggle="tooltip" title="Refresh">
		    		</a>
		    </button>
			<div class="d-inlineblock float-right  mt-25">
				<!-- <label style="padding-top:8px; font-weight:100;">Search :</label> -->
				<input type="text" id="myInput"  class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px;
		  		  background-color: #ffffff;" placeholder="Search Data111.." title="Type in a name">    		
		    		<button style="border:none !important;">
		    		<a href="MliRegistrationDetailDownload.html">
		    			<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
		    		</a>
		    		</button>			
				</div>
	<form:form method="GET" id="B" class="form-horizontal">
	<input type="hidden" name="csrfPreventionSalt" value="${csrfPreventionSalt}"/>
	<input type="hidden" name="mliLongName" id="mliLongName" value=""/>			
	<c:if test="${!empty mlisList}">			
		<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0">
		<thead>
			<tr>
				<th>S.No.</th>
				<th>NAME OF THE MLI</th>
				<th>CONTACT PERSON</th>
				<th>CONTACT NUMBER</th>
				<th>EMAIL ID</th>
				<th>GSTIN</th>
				<th>MLI ID</th>
				<th>DOWNLOAD UNDERTAKING</th>
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
					<td><c:out value="${mliList.gstinNumber}" /></td>
					<td><c:out value="${mliList.mem_bnk_id}00000000" /></td>
					<td align="center" class="btn-edit"><c:choose>
					<c:when test="${mliList.fileName ne 'null'}">
								<a href="downloadMli.html?fileName=${mliList.fileName}" class="btn-edit ">Download</a> <!-- | Delete -->
					</c:when>
					<c:otherwise>
					<span> ---- --- </span>
					</c:otherwise>
				</c:choose>
					</td>
					<td align="center" class="btn-edit"><c:choose>
							
							<c:when test="${mliList.status=='Approved'}">
								<a href="#" class="btn-edit" onclick="javascript:editMLI('${mliList.mliLongName}');">Edit</a> <!-- | Delete -->
								</c:when>
							<c:when test="${mliList.status=='Panding for Approval(Edit)'}">
								<span class="btn-edit not-allow"> Edit</span> 
								</c:when>
							<c:when test="${mliList.status=='Rejected'}">
								<a href="#" class="btn-edit" onclick="javascript:editMLI('${mliList.mliLongName}');">Edit</a> <!-- | Delete -->
								</c:when>
							<c:when test="${mliList.status=='CEMMA'}">
							
								<!--<span class="not-allow" >Edit</span>	--><!-- | Delete -->
								<a href="#" class="btn-edit" onclick="javascript:editMLI('${mliList.mliLongName}');">Edit</a>
								</c:when>
							<c:when test="${mliList.status=='Panding For Approval(New)'}">
							<span class="btn-edit not-allow"> Edit</span>  

								</c:when>
							<c:otherwise>
								<!--<span class="not-allow">Edit</span> --><!-- | Delete -->
								<span class="btn-edit not-allow"> Edit</a> 
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
</form:form>
</body>
<script type="text/javascript">
	function editMLI(mliLongName){
		//alert('MLI Name:'+mliLongName);
		if (mliLongName != null || mliLongName != "") {
			document.getElementById('mliLongName').value=mliLongName;
			document.getElementById('B').action = "/SV/getDetails.html";
			document.getElementById('B').submit();
		}
	}
	function searchRecord() {
		var nameSearch = document.getElementById("nameSearch").value;
		var searchValue = document.getElementById("searchValue").value;
		//alert('search  :' + nameSearch + ' searchValue  :' + searchValue);
		if (nameSearch != null || searchValue != null) {
			document.getElementById('A').action = "/SV/mlidetailsByIndex.html";
			document.getElementById('A').submit();
		}

	}
	document.onkeydown = function(e) {
		  if(event.keyCode == 123) {
		     return false;
		  }
		  if(e.ctrlKey && e.shiftKey && e.keyCode == 'I'.charCodeAt(0)) {
		     return false;
		  }
		  if(e.ctrlKey && e.shiftKey && e.keyCode == 'C'.charCodeAt(0)) {
		     return false;
		  }
		  if(e.ctrlKey && e.shiftKey && e.keyCode == 'J'.charCodeAt(0)) {
		     return false;
		  }
		  if(e.ctrlKey && e.keyCode == 'U'.charCodeAt(0)) {
		     return false;
		  }
		}
	
</script>

</html>



