<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<LINK href="<%=request.getContextPath()%>/css/stylesheet.css"
	rel="stylesheet" type="text/css">
<!-- ${recordNotExitsKey7}-->
${lists[loop.index].excelFileFullName}
<script type="text/javascript">
	$(window).load(function() {
		$(".loader").fadeOut("slow");
	})
</script>
</head>
<body>
<div class="main-section">
	  <nav aria-label="breadcrumb">
		<ol class="breadcrumb cus-breadcrumb">
			<li class="breadcrumb-item"><a href="/SV/cgtmseCheckerForBacthApprovalRM.html">Batch Approval By Checker</a></li>
			<li class="breadcrumb-item active" aria-current="page">Pending For Approval</li>
		</ol>
		</nav>
		<div class="container-fluid">
			<!-- <div class="row"> -->
			<div>
				<div class="tbl-details">
					<div class="col-md-12">
						<h2 align="center" class="heading">Pending For Approval</h2>
						<div id="disPlaySuccessMsg"></div>
						<div class="d-inlineblock float-right ">
							<!-- <label style="padding-top:8px; font-weight:100;">Search :</label> -->
							<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control" style="width: 200px; display: inline; height: 34px; border-radius: 4px; background-color: #ffffff;" placeholder="Search Data.." title="Type in a name">
							<button style="border: none !important; cursor: not-allowed;">
								<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
							</button>
						</div>

						<form:form method="POST" action="" id="A">
							<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0">
								<thead>
									<tr>
										<th align="left">SR.NO.</th>
										<th>DATE OF UPLOAD</th>
										<th>MLI NAME</th>
										<th>PORTFOLIO NAME</th>
					
										<th>FILE ID/FILE NAME</th> 
										<th>NO.OF RECORDS</th>
										<th>TOTAL GUARANTEE AMOUNT</th>
										<!--<th>Total Sanctioned Amount</th>
										--><th>DETAILS</th>
									</tr>
								</thead>
								<%
									int counter = 0;
										int srno = 1;
								%>
								<c:forEach items="${lists}" var="listValue" varStatus="loop">
									<%-- <tr <% if(counter%2==0){%>bgcolor="silver" <%}%>> --%>
									<tr>
										<td><%=srno++%></td>
										<td>${lists[loop.index].excelUploadedDate}</td>
										<td>${lists[loop.index].mliName}</td>
										<td>${lists[loop.index].portFolioName}</td><!--
										<td>${lists[loop.index].quaterName}</td>-->
									    <td>${lists[loop.index].uploadedExcelFileId}</td> 
										<td>${lists[loop.index].totalNoOfRecordsUploadedInExcelFile}</td>
										<td>${lists[loop.index].outstandingAmount}</td><!--
										<td>${lists[loop.index].totSanctionAmt}</td>
										--><td><a href="cgtmseCheckerForBatchApprovalAndRejectionRM.html?uploadedDate=${lists[loop.index].excelUploadedDate}&shortName=${lists[loop.index].mliName}&portfolioID=${lists[loop.index].portfolioNumber}&quaterName=${lists[loop.index].quaterName}&excelFileId=${lists[loop.index].uploadedExcelFileId}&totNoOfRecordsUploadedInExcelFile=${lists[loop.index].totalNoOfRecordsUploadedInExcelFile}&totalSanctionAmt=${lists[loop.index].totSanctionAmt}&outstandingAmount=${lists[loop.index].outstandingAmount}&excelFileFullName=${lists[loop.index].excelFileFullName}" class="btn-edit">Details</a></td>
										<%
											counter += 1;
										%>
									</tr>
								</c:forEach>
								<%
									if (counter == 0) {
								%>
								<tr>
									<td colspan=9 align="center">No Records</td>
								</tr>
								<%
									}
								%>

							</table>
						</form:form>

						<!--<div class="d-inlineblock mt-10">
							<input type="button" value="Exit" class="btn btn-reset" onclick="backBatch();">
						</div>
					--></div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	//added by say----------------------------------------------------------------
	function backBatch() {

		document.getElementById('A').action = "/SV/cgtmseCheckerHomeBack1.html";
		document.getElementById('A').submit();
	}
</script>
</html>


