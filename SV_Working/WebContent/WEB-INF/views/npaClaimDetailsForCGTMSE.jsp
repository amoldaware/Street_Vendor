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


</head>

<body bgcolor="#E0FFFF">

<div class="main-section">
<div class="container-fluid">
<!-- <div class="row"> -->
	<div>
		
	<div class="tbl-details">
		<div class="col-md-12">
		
		<form:form method="POST" id="A">
		<c:if test="${!empty dataList}">
		<div class="d-inlineblock float-right">
		<!-- <label style="padding-top:8px; font-weight:100;">Search :</label> -->
		<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px;
  		  background-color: #ffffff;" placeholder="Search Data.." title="Type in a name">
    		
    		<button style="border:none !important; cursor: not-allowed;">
    			<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
    		</button>			
		</div>
		<table id="myTable" class="table table-bordered table-hover cus-table mb-0 danRpDataTable">
			
			<thead>
					<tr>
						<th>SR.NO.</th>
						<th>CGPAN</th>
						<th>NPA DATE</th>
						<th>NPA REASON</th>
					<!-- 	<th>LOAN TYPE</th> -->
	
					<%-- 	<th>Select All</br>
						<form:checkbox path="selectAll" id="select_all"
								onchange="selectAllCheckBox();" cssClass="select_all" /></th> --%>
					</tr>
			</thead>
					<%
						int counter = 0;
					%>
					<c:forEach items="${dataList}" var="dataList">
						<%-- <tr <%if (counter % 2 == 0) {%> bgcolor="silver" <%}%>> --%>
						<tr>
							<td><c:out value="<%=counter + 1%>" /></td>
							<td><c:out value="${dataList.CGPAN}" /></td>
							<td><c:out value="${dataList.npaDt}" /></td>
							<td width="30%"><c:out value="${dataList.npaReason}" /></td>
						<%-- 	<td><c:out value="${dataList.loanType}" /></td> --%>
							<%
								counter += 1;
							%>
						
					</c:forEach>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<!-- <td></td>	 -->		
						<td><div id="count"></div></td>
					</tr>				
			</table>
			
			<div class="d-block">
	     <input class="btn btn-reset" readonly="true" id="onReject"
							onclick="();" value="Back" align="">
			</div>	
			
		</c:if>
		
		<c:if test="${empty dataList}">
		<table id="myTable" class="table table-bordered table-hover cus-table mb-0 danRpDataTable">			
			<thead>
					<tr>
						<th>SR.NO.</th>
						<th>CGPAN</th>
						<th>NPA DTAE</th>
						<th>NPA REASON</th>
						<th>LOAN TYPE</th>
					</tr>
			</thead>							
				<tr>
					<td></td>
					<td></td>						
					<td align="center">Data not available</td>
					<td></td>
					<td></td>
				</tr>
		</table>
	</c:if>
				
		
		</form:form>
		
		</div>
	</div>					
	
	</div>
</div>
</div>	

	<!-- <div
		STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;"> -->
	<%-- <h3>NBFC NPA</h3>
	<hr>
	<form:form method="POST" id="A">
		<div
			"STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;">
			<table cellpadding=5 cellspacing=5 class="danRpDataTable"
				align=center width=90%>

                 ${message}
				<c:if test="${!empty dataList}">
					<tr>
						<td class="tableHeader1">Sr.No</td>
						<td class="t width="30%">CGPAN</td>
						<td class="tableHeader1" width="30%">NPA Date</td>
						<td class="tableHeader1" width="30%">NPA Resion</td>
						<td class="tableHeader1">Loan Type</td>
						<td class="tableHeader1">Efforts Taken</td>
						<td class="tableHeader1">Select All</br>
						<form:checkbox path="selectAll" id="select_all"
								onchange="selectAllCheckBox();" cssClass="select_all" /></td>
					</tr>

					<%
						int counter = 0;
					%>
					<c:forEach items="${dataList}" var="dataList">
						<tr <%if (counter % 2 == 0) {%> bgcolor="silver" <%}%>>
							<td><c:out value="<%=counter + 1%>" /></td>
							<td width="30%"><c:out value="${dataList.CGPAN}" /></td>
							<td width="30%"><c:out value="${dataList.npaDt}" /></td>
							<td width="30%"><c:out value="${dataList.npaReason}" /></td>
							<td><c:out value="${dataList.loanType}" /></td>
							<td><c:out value="${dataList.effortsTaken}" /></td>
							<td align="center"><form:checkbox path="chcktbl"
									value="${dataList.CGPAN}" onchange="uncheckSelectAll();"
									cssClass="chcktbl" /><input type="hidden" id="approveCount"
								name="approveCount" value="hiddenValue"></td>
							<%
								counter += 1;
							%>
						
					</c:forEach>
					<tr>
						<td class="tableHeader1"></td>
						<td class="tableHeader1"></td>
						<td class="tableHeader1"></td>
						<td class="tableHeader1"></td>
						<td class="tableHeader1"></td>
						<td class="tableHeader1"></td>
						
						
						<td class="tableHeader1"><div id="count"></div></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						
						
						<td><input class="button" readonly="true" id="onSubmit"
							onclick="clickToInitiate();" value="Approve" align=""></td>
						<td><input class="button" readonly="true" id="onReject"
							onclick="reject();" value="Reject" align=""></td>

					</tr>
				</c:if>
				
				<c:if test="${empty dataList}">
					<tr>

						<td class="tableHeader1">Sr.No</td>
						<td class="tableHeader1" width="30%">CGPAN</td>
						<td class="tableHeader1" width="30%">NPA Date</td>
						<td class="tableHeader1" width="30%">NPA Resion</td>
						<td class="tableHeader1">Loan Type</td>
						<td class="tableHeader1">Efforts Taken</td>
						<td class="tableHeader1">Select All</br>
						<form:checkbox path="selectAll" id="select_all"
								onchange="selectAllCheckBox();" cssClass="select_all" /></td>
					</tr>
					<tr>
					<tr>
						<td></td>
						<td></td>
						
						<td align="center">Data not available</td>

						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</c:if>

			</table>
		</div>
	</form:form> --%>
	<!-- </div> -->

</body>
<script type="text/javascript">
	function rejectFunction() {
		document.getElementById('A').action = "/SV/cgtmseGetMakerHome.html";
		document.getElementById('A').submit();
	}
</script>


</html>



