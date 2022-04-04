<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
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
<title>Portfolio Creation</title>
<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<script type="text/javascript"
	src="jquery-ui-1.10.3/ui/jquery.ui.datepicker.js"></script>
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css"
	rel="stylesheet" type="text/css">


<script>
	$(function() {

		$("#ratingDate").datepicker({
			dateFormat : 'dd-mm-yy'
		});

	});
</script>

</head>

<body bgcolor="#E0FFFF">
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
					<label>SEARCH NBFC</label>
					 <input type="text" class="form-control cus-control d-none" placeholder="By Name" > 
					<form:select path="nameSearch" id="nameSearch" class="form-control cus-control">
						<form:option value="" label="--Select Column Name--" />
						<form:option value="longName" label="NBFC Name" />
						<form:option value="emailId" label="Email ID" />
						<form:option value="companyPAN" label="Company PAN" />
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
				
			</div>
			
			<button style="border:none !important;">
		    		<a href="/SV/approveRejectNewMLIDetails.html">
		    			<img src="images/refresh.png" alt="" data-toggle="tooltip" title="Refresh">
		    		</a>
		    </button>
						<div class="d-inlineblock float-right ">
							<!-- <label style="padding-top:8px; font-weight:100;">Search :</label> -->
							<input type="text" id="myInput" onkeyup="myFunction()"
								class="form-control cus-control"
								style="width: 200px; display: inline; height: 34px; border-radius: 4px; background-color: #ffffff;"
								placeholder="Search Data.." title="Type in a name">
							<button style="border: none !important; cursor: not-allowed;">
								<img src="images/excel.png" alt="" data-toggle="tooltip"
									title="Export To Excel">
							</button>
						</div>
						<font color="green" size="3"><b>${message}</b></font>
						<c:if test="${!empty mlisList}">

							<table id="myTable"
								class="table table-bordered table-hover cus-table mt-10 mb-0">
								<!-- <tr><td><table border="0"><tbody><tr><td><a href="/nbfc/newMLIRegistration.html">Add MLI</a></td><td><a href="">&nbsp;</a></td><td><a href="">&nbsp;</a></td><td><a href="">Download</a></td><td><a href="">&nbsp;</a></td><td><a href="">&nbsp;</a></td><td><a href="">Print</a></td><td><a href="">&nbsp;</a></td><td><a href="">&nbsp;</a></td></tr></tbody></table></td></tr> -->
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
								<%
									int counter = 0;
								%>
								<c:forEach items="${mlisList}" var="mliList">
									<%-- <tr <% if(counter%2==0){%>bgcolor="silver" <%}%>> --%>
									<tr>
										<td><%=counter + 1%></td>
										<td><c:out value="${mliList.mliLongName}" /></td>
										<td><c:out value="${mliList.contactPerson}" /></td>
										<td><c:out value="${mliList.mobileNUmber}" /></td>
										<td><c:out value="${mliList.companyPAN}" /></td>
										<td><c:out value="${mliList.emailId}" /></td>
									<%-- 	<td><c:out value="${mliList.status}" /></td> --%>
										<td align="center"><c:choose>
												<c:when test="${mliList.status=='Approval Done'}">
       NA
    </c:when>
												<c:when test="${mliList.status=='Approval Case'}">
													<a
														href="getmliDetailsForApprovalByChecker.html?mliLongName=${mliList.mliLongName}"
														class="btn-edit">Pending For Approval </a>
												</c:when>
												<c:when test="${mliList.status=='Approval Act'}">
													<a
														href="mliNewRegistrationPage.html?mliLongName=${mliList.mliLongName}"
														class="btn-edit">Pending For Approval</a>
												</c:when>
												<c:otherwise>
													<a
														href="getmliDetailsForApprovalByChecker.html?mliLongName=${mliList.mliLongName}"
														class="btn-edit">Approve New MLI</a>
												</c:otherwise>
											</c:choose></td>

										<%
											counter += 1;
										%>
									</tr>
								</c:forEach>

							</table>
						</c:if>


					</div>
				</div>

			</div>
		</div>
	</div>

	<!-- <div
		STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;"> -->
	<%-- <h1 align="center">MLI Registration Status Page</h1>
	<hr>
<font color="green" size="3"><b>${message}</b></font>
	<c:if test="${!empty mlisList}">

		<table  cellpadding=5 cellspacing=5 align=center width=90%  >
				<!-- <tr><td><table border="0"><tbody><tr><td><a href="/nbfc/newMLIRegistration.html">Add MLI</a></td><td><a href="">&nbsp;</a></td><td><a href="">&nbsp;</a></td><td><a href="">Download</a></td><td><a href="">&nbsp;</a></td><td><a href="">&nbsp;</a></td><td><a href="">Print</a></td><td><a href="">&nbsp;</a></td><td><a href="">&nbsp;</a></td></tr></tbody></table></td></tr> -->
				<tr>
				<td class="tableHeader1">S.NO.</td>
					<td class="tableHeader1">Long Name</td>
					<td class="tableHeader1">Contact Person</td>
					<td class="tableHeader1">Contact Number</td>
					<td class="tableHeader1">Company PAN</td>
					<td class="tableHeader1">Email ID</td>
					<td class="tableHeader1">Status</td>
					<td class="tableHeader1">Actions on Row</td>
				</tr>
<% int counter=0;%>
				<c:forEach items="${mlisList}" var="mliList">
					<tr <% if(counter%2==0){%>bgcolor="silver" <%}%>>
					<td><%=counter+1%></td>
						<td><c:out value="${mliList.mliLongName}" /></td>
						<td><c:out value="${mliList.contactPerson}" /></td>
						<td><c:out value="${mliList.mobileNUmber}" /></td>
						<td><c:out value="${mliList.companyPAN}" /></td>
						<td><c:out value="${mliList.emailId}" /></td>
						<td><c:out value="${mliList.status}" /></td>
						<td align="center"><c:choose>
								<c:when test="${mliList.status=='Approval Done'}">
       NA
    </c:when>
								<c:when test="${mliList.status=='Approval Case'}">
									<a
										href="getmliDetailsForApprovalByChecker.html?mliLongName=${mliList.mliLongName}">Approve Existing MLI
										</a>
								</c:when>
								<c:when test="${mliList.status=='Approval Act'}">
									 <a
										href="mliNewRegistrationPage.html?mliLongName=${mliList.mliLongName}">Approve New MLI</a>
								</c:when>
								<c:otherwise>
							 <a
							href="getmliDetailsForApprovalByChecker.html?mliLongName=${mliList.mliLongName}">Approve New MLI</a>
    </c:otherwise>
							</c:choose></td>
						
						<%  counter+=1;%></tr>
				</c:forEach>
			 
		</table>
	</c:if>
	
	<c:if test="${empty mlisList}">

		
	</c:if> --%>
	<!-- </div>
 -->
</body>

</html>
<script type="text/javascript">
	function searchRecord() {
		var nameSearch = document.getElementById("nameSearch").value;
		var searchValue = document.getElementById("searchValue").value;
		//alert('search  :' + nameSearch + ' searchValue  :' + searchValue);
		if (nameSearch != null || searchValue != null) {
			document.getElementById('A').action = "/SV/ApproverejectMliDetailsByIndex.html";
			document.getElementById('A').submit();
		}

	}
</script>


