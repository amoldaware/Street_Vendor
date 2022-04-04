<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
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

</head>

<body bgcolor="#E0FFFF">
	<!-- <div
		STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;"> -->
	<h3>Borrower/Unit details</h3>
	<hr><form:form method="GET" id="A">
	<div "STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;">
		<table  cellpadding=5 cellspacing=5 align=center width=90%  >	
				
				<tr >
					<td class="tableHeader1"><a href="/SV/userReportdisburseNonDisburseData.html?file_Id=<%=session.getAttribute("file_Id")%>&danId=<%=session.getAttribute("danId")%>&disbursedStatus=Y"><div>Disbursed</div></a></td>				
					<%-- <td class="tableHeader1"><a href="/SV/disburseNonDisburseData.html?file_Id=<%=session.getAttribute("file_Id")%>&danId=<%=session.getAttribute("danId")%>&disbursedStatus=N"><div>Non Disbursed</div></a></td> --%>					
					<td ></td>
					<td ></td>
					<td ></td>
					<td ></td>	
					</tr>
				<c:if test="${!empty dataList1}">
				<tr>  
					<td class="tableHeader1">SR.NO</td>				
					<td class="tableHeader1">PORTFOLIO NAME</td>					
					<td class="tableHeader1">BORROWER NAME</td>
					<td class="tableHeader1">OUTSTANDING AMOUNT</td>
					<td class="tableHeader1">SANCTION AMOUNT</td>					
					<td class="tableHeader1">SANCTION DATE</td>
					<c:if test = "${disStatus == 'Y'}">
					<td class="tableHeader1">PORTFOLIO RATE</td>
					<td class="tableHeader1">BASE AMOUNT</td>
					<td class="tableHeader1">DAN AMOUNT</td>
					<td class="tableHeader1">IGST RATE</td>
					<td class="tableHeader1">IGST AMOUNT</td>
					<td class="tableHeader1">CGST RATE</td>						
					<td class="tableHeader1">CGST AMOUNT</td>
					<td class="tableHeader1">SGST RATE</td>										
					<td class="tableHeader1">SGST<br>AMOUNT</td>
					
					</c:if>			
				</tr>
				
				<% int counter=0;%>
				<c:forEach items="${dataList1}" var="dataList1">
					<tr <% if(counter%2==0){%>bgcolor="silver" <%}%>>
						<td><c:out value="<%=counter+1%>" /></td>		
						<td><c:out value="${dataList1.portfolioName}" /></td>
						<td><c:out value="${dataList1.borrowerName}" /></td>
						<td><c:out value="${dataList1.outstandingAmount}" /></td>
						<td><c:out value="${dataList1.sanctionAmount}" /></td>												
						<td><c:out value="${dataList1.sanctionDate}" /></td>
						<c:if test = "${disStatus == 'Y'}">
												<td><c:out value="${dataList1.portfolioRate}" /></td>
												<td><c:out value="${dataList1.baseAmount}" /></td>
												<td><c:out value="${dataList1.danAmount}" /></td>
												<td><c:out value="${dataList1.igstRate}" /></td>
												<td><c:out value="${dataList1.igstAmount}" /></td>
												<td><c:out value="${dataList1.cgstRate}" /></td>
												<td><c:out value="${dataList1.cgstAmount}" /></td>
												<td><c:out value="${dataList1.sgstRate}" /></td>
												<td><c:out value="${dataList1.sgstAmount}" /></td>						
						</c:if>			
					</tr>
					<%  counter+=1;%>
				</c:forEach>
				<tr>
					<td class="tableHeader1"></td>				
					<td class="tableHeader1"></td>					
					<td class="tableHeader1"></td>
					<td class="tableHeader1"></td>					
					<td class="tableHeader1"></td>
					<td class="tableHeader1"></td>
					<c:if test = "${disStatus == 'Y'}">
					<td class="tableHeader1"></td>
					<td class="tableHeader1"></td>	
					<td class="tableHeader1"></td>			
					<td class="tableHeader1"></td>					
					<td class="tableHeader1"></td>
					<td class="tableHeader1"></td>					
					<td class="tableHeader1"></td>
					<td class="tableHeader1"></td>	
					<td class="tableHeader1"></td>					
					</c:if>
				</tr>
				<tr >
					
					<c:if test = "${disStatus == 'Y'}">	
					<td ></td>				
					<td ></td>					
					<td ></td>
					<td ></td>
					<td ></td>
					<td ></td>
					<td ></td>			
					<td ></td>					
					<td ></td>
					<td ></td>
					<td ></td>
					<td ></td>
					<td ></td>
					<td ></td>
					<td class="tableHeader1"><a href="/SV/rpNumberCorrespondingData.html?rpNumber=<%=session.getAttribute("rpNumber")%>&danId=<%=session.getAttribute("danId")%>"><div>Back</div></a></td>	</tr>
					</c:if>
					<c:if test = "${disStatus == 'N'}">
					<td ></td>				
					<td ></td>					
					<td ></td>
					<td ></td>
					<td class="tableHeader1"><a href="/SV/rpNumberCorrespondingData.html?rpNumber=<%=session.getAttribute("rpNumber")%>&danId=<%=session.getAttribute("danId")%>"><div>Back</div></a></td>	</tr>
					</c:if>
				</c:if>
								
					<c:if test="${empty dataList1}">
				<tr>
					<td class="tableHeader1">SR.NO.</td>				
					<td class="tableHeader1">PORTFOLIO NAME</td>					
					<td class="tableHeader1">BORROWER NAME</td>
					<td class="tableHeader1">SANCTION AMOUNT</td>					
					<td class="tableHeader1">SANCTION ADTE</td>	
				</tr>
				<tr >
					<tr >
					<td></td>				
					<td></td>				
					<td align="center">Data not available</td>					
					<td ></td>
					<td ></td>					
					</tr>
					<tr>
					<td ></td>				
					<td ></td>					
					<td ></td>
					<td ></td>
					<td ></td>
					<td class="tableHeader1"><a href="/SV/rpNumberCorrespondingData.html?rpNumber=<%=session.getAttribute("rpNumber")%>&danId=<%=session.getAttribute("danId")%>"><div>Back</div></a></td>	</tr>
		</c:if>
	</table>
	</div>
	</form:form>
	<!-- </div> -->

</body>

</html>

<script type="text/javascript">
	function getDisbursedFlag(){
	//	alert("hii "+arg.value);
		//var disbursed = document.getElementById("disbused").value;
		//var nonDisbursed = document.getElementById("nonDisbused").value;
	 if(args!=null && args=='disbused'){
		document.getElementById('A').action = "/SV/userReportdisburseNonDisburseData.html?rpNumber=";
		document.getElementById('A').submit();
	}
	}
	function getNonDisbursedFlag(){
		//alert("hii "+arg.value);
		//var disbursed = document.getElementById("disbused").value;
		//var nonDisbursed = document.getElementById("nonDisbused").value;
	if(args!=null && args=='nonDisbused'){
		document.getElementById('A').action = "/SV/rpNumberCorrespondingData.html?rpNumber=";
		document.getElementById('A').submit();
	}
	}
	function back(){
		//var disbursed = document.getElementById("disbused").value;
		//var nonDisbursed = document.getElementById("nonDisbused").value;
		document.getElementById('A').action = "/SV/userReportdisburseNonDisburseData.html";
		document.getElementById('A').submit();
	
	}
</script>

