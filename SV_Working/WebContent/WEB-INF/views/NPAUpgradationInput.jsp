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
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
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
<LINK href="<%=request.getContextPath()%>/css/stylesheet.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript">
	/* function noBack() {
		window.location.hash = "no-back-button";
		window.location.hash = "Again-No-back-button";//again because google chrome don't insert first hash into history
		window.onhashchange = function() {
			window.location.hash = "no-back-button";
		};
	} */
	/* window.onload=function(){                                                                      
	 var i=0; var previous_hash = window.location.hash;                                           
	 var x = setInterval(function(){                                                              
	 i++; window.location.hash = "/noop/" + i;                                                  
	 if (i==10){clearInterval(x);                                                               
	 window.location.hash = previous_hash;}                                                
	 },10);}; */
</script>

</head>
<%
	String uesrName=(String)session.getAttribute("userId");
   /*  if(uesrName==null){	
		response.sendRedirect("login");		
	} */
    System.out.println("uesrName....."+uesrName);
%>
<body onload="noBack();">

	<div class="main-section">
		<div class="container-fluid">
			<!-- <div class="row"> -->
			<div>
				<form:form method="POST" id="A" action="/SV/npaUpgradationDetails.html"
					class="form-horizontal">
					<div class="frm-section">
						<div class="col-md-12">

							<!-- 	<h5 style=" margin: 5px 0; color: #4ba4bf;"></h5> -->
							<h5 class="error1 mtb-0">
								<strong>${message}</strong>
							</h5>
						
							<h5 class="sub-head">
								<strong>NPA Upgradation Detail</strong>
							</h5>
							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="MLIID">Member ID<span
												style="color: red;">*</span>
										</form:label>
										<form:input path="MLIID" id="mliId" value="${memberId}"
											style="text-align: left" size="12"
											class="form-control cus-control" readonly="true" />
										<form:errors path="MLIID" cssClass="error" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="CGPAN">CGPAN<span
												style="color: red;">*</span>
										</form:label>
										<form:input path="CGPAN" id="cgPan"
											class="form-control cus-control" style="text-align: left"
											size="12" />
										<form:errors path="CGPAN" cssClass="error" />
									</div>
								</div>
							</div>

							<div class="d-inlineblock mt-25">
								<input class="btn btn-reset" type="button" value="Submit" onclick="npaUpgradationDetails()"/> 
								<input
									type="button" value="Back" onclick="exitMLIDetails()"
									class="btn btn-reset" />
							</div>
							<!-- <div class="col-md-12 prl-10">			
								<h5 class="error1 mtb-0" style="text-align: left;"><b>Notes:</b>NPA Marking will be available only after 90 days guarantee start.
							</div>	 -->
				<%-- 			<c:if test="${!empty dataList}">

								<h2 class="heading"
									style="text-align: center; margin: 15px 0 !important;">NPA Upgradation Detail</h2>

								<table id="myTable"
									class="table table-bordered table-hover cus-table mt-10 mb-0">
									<thead>
										<tr>
											<th>CGPAN</th>
											<th>NPA Upgradation Date</th>
											<th>Upgradation Remarks</th>
										</tr>
									</thead>
									<c:forEach items="${dataList}" var="dataList">
										<tr>
												<td><c:out value="${dataList.CGPAN}" /></td>
										<td><c:out value="${dataList.npaDt}" /></td>
									 	<td><c:out value="${dataList.upgradationRemarks}" /></td>
											<td align="center"><a
												href="npaUpdate.html?CGPAN=${dataList.CGPAN}"
												class="btn-edit">Edit</a></td>
										</tr>
									</c:forEach>
								</table>
								<!-- <div class="d-block text-center">
		<input type="submit" value="Search" class="btn btn-reset" />
			</div> -->
							</c:if> --%>

						</div>

						<c:if test="${!empty dataListReturn}">

									<table id="myTable"
								class="table table-bordered table-hover cus-table mt-10 mb-0">
									<tr>
											<th>SR.NO.</th>
											<th>CGPAN</th>
											<th>NPA Upgradation Date</th>
											<th>Upgradation Return Remarks</th>
											<th>ACTIONS ON ROW</th>
										</tr>
								
	<%
										int counter = 0;
									%>
								<c:forEach items="${dataListReturn}" var="dataList">
									<tr>
											<td><%=counter + 1%></td>
										<td><c:out value="${dataList.CGPAN}" /></td>
										<td><c:out value="${dataList.npaDt}" /></td>
									 	<td><c:out value="${dataList.upgradationReturnRemarks}" /></td>
										<%-- <td><c:out value="${dataList.loanType}" /></td> --%>
										<td align="center"><a
											href="SaveNpaUpgradationEditDetails.html?Cgpan=${dataList.CGPAN}"
											class="btn-edit">Edit</a> </td>
									</tr>
								</c:forEach>
							</table>
						</c:if>
					</div>
				</form:form>

			</div>
		</div>
	</div>



	<!-- <div
		STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;"> -->
	<%--  <h1>NPA Details</h1>
	<hr>
	<form:form method="POST" id="A" action="/SV/npaDetailsData.html">
		<div
			STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;">
			<table cellpadding=5 cellspacing=5 align=center width=90%>

				<tr>
					<h3>Enter Details:</h3>
				</tr>
				<tr>
					<td class="tableHeader1"><form:label path="MLIID">Member ID<span
								style="color: red;">*</span>
						</form:label></td>

					<td class="DataElement"><form:input path="MLIID" id="mliId"
							value="${memberId}" style="text-align: left" size="12"
							readonly="true" /></td>

				</tr>

				<tr>
					<td class="tableHeader1"><form:label path="CGPAN">CGPAN<span
								style="color: red;">*</span>
						</form:label></td>
					<td class="DataElement"><form:input path="CGPAN" id="cgPan"
							style="text-align: left" size="12" /></td>

				</tr>
				<tr>
					<td></td>
					<td><form:errors path="CGPAN" cssClass="error" /><b
						style="color: red;">${message}</b></td>
				</tr>
				<tr>
					<c:if test="${!empty dataList}">
						<h2>NPA Detail</h2>
						<table align="left" border="1">
							<tr>
								<th>NPA ID</th>
								<th>CGPAN</th>
								<th>NPA Reason</th>
								<th>NPA Date</th>
								<th>Employee Address</th>
								<th>Loan Type</th>
								<th>Actions on Row</th>
							</tr>

							<c:forEach items="${dataList}" var="dataList">
								<tr>
									<td><c:out value="${dataList.npaId}" /></td>
									<td><c:out value="${dataList.CGPAN}" /></td>
									<td><c:out value="${dataList.npaReason}" /></td>
									<td><c:out value="${dataList.npaDt}" /></td>
									<td><c:out value="${dataList.npaCreatedDate}" /></td>
									<td><c:out value="${dataList.loanType}" /></td>
									<td align="center"><a
										href="npaUpdate.html?CGPAN=${dataList.CGPAN}">Edit</a></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>
				</tr>

				<tr>
					<td><input type="submit" value="Search" class="button" /></td>
				</tr>

			</table>

		</div>
	</form:form> --%>
	<!-- </div> -->

</body>
<script type="text/javascript">
	$('a.Delete').on('click', function() {
		var choice = confirm('Do you really want to delete this record?');
		if (choice === true) {
			return true;
		}
		return false;
	});

	function exitMLIDetails() {
		//alert('Exit');
		document.getElementById('A').action = "/SV/nbfcMakerHomeBack.html";
		document.getElementById('A').submit();
	}
	function npaUpgradationDetails() {
		document.getElementById('A').action = "/SV/npaUpgradationDetails.html";
		document.getElementById('A').submit();
	}
</script>

</html>



