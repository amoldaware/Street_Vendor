<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<title>Portfolio Creation</title>
		<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
		<script type="text/javascript" src="jquery-ui-1.10.3/ui/jquery.ui.datepicker.js"></script>
		<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
		<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
		<script>
			$(function() {
				$("#ratingDate").datepicker({
					dateFormat : 'dd-mm-yy'
				});
			});
		</script>
	</head>

	<body bgcolor="#E0FFFF">
	<form:form  action="mliApproveOrRejectClaim.html" method="POST" class="form-horizontal">
		<div class="main-section">
			<div class="container-fluid">
			
				<div>	
					<div class="tbl-details">
						<div class="col-md-12">
							<div class="d-inlineblock float-right ">
								<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px;background-color: #ffffff;" placeholder="Search Data.." title="Type in a name">    		
		    					<button style="border:none !important; cursor: not-allowed;">
		    						<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
		    					</button>			
							</div>
				         <h5 class="notification-message">${msg}</h5>
				         <h5><b>NBFC MLI wise claim Application	</b>		
				         </h5>
							<table id="myTable"  class="table table-bordered table-hover cus-table mt-10 mb-0">
								<c:if test="${!empty danDetailList}">
									<thead>
										<tr>
											<th>SR.No.</th>
											<th>MLI Name</th>
											<th>No. Of Claims Upto 10 Lakhs</th>
											<th>No. Of Claims Above 10 Lakhs to 25 Lakhs</th>
											<th>No. Of Claims Above 25 Lakhs</th>
											<th>Total Count Of Claims</th>
											<th>Total Amount Of Claims</th>
											
										</tr>
									</thead>
									<% int counter=0;%>
									<c:forEach items="${danDetailList}" var="danDetailList">
										<tr <% if(counter%2==0){%>bgcolor="silver" <%}%>>
											<td><%=counter+1%></td>
											<td><c:out value="${danDetailList.mliName}"/></td>
											<td><a href="claimLoadgementApprovalCGTMSE.html?MLIName=${danDetailList.mliName}&CNT=upto10L"><c:out value="${danDetailList.UPTO_10L_CNT}"/></a></td>
											<td><a href="claimLoadgementApprovalCGTMSE.html?MLIName=${danDetailList.mliName}&CNT=10L_25L"><c:out value="${danDetailList.BETWEEN_10L_25L_CNT}"/></a></td>
											<td><a href="claimLoadgementApprovalCGTMSE.html?MLIName=${danDetailList.mliName}&CNT=UPTO25L"><c:out value="${danDetailList.UPTO_25L_CNT}"/></a></td>
											<td><c:out value="${danDetailList.claimCnt}"/></td>
											<td><c:out value="${danDetailList.total_AmountStr}"/></td> 
											<%-- <td><a href="claimLoadgementApprovalCGTMSE.html?MLIName=${danDetailList.mliName}"><form:input path="claimCnt" value="${danDetailList.claimCnt}" class="form-control cus-control" readonly="true"/></td> --%>
																						  <%counter+=1;%>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty danDetailList}">
								<thead>
										<tr>
											<th>SR.No.</th>
											<th>MLI Name</th>
											<th>No. Of Claims Upto 10 Lakhs</th>
											<th>No. Of Claims Above 10 Lakhs to 25 Lakhs</th>
											<th>No. Of Claims Above 25 Lakhs</th>
											<th>Total Count Of Claims</th>
											<th>Total Amount Of Claims</th>
										</tr>
									</thead>
									<tr>
										
								<td></td>
									<td></td>
											<td></td>
										<td>Record Not Found</td>
										<td></td>	
										<td></td>	
										<td></td>	
											<%-- <td><a href="claimLoadgementApprovalCGTMSE.html?MLIName=${danDetailList.mliName}"><form:input path="claimCnt" value="${danDetailList.claimCnt}" class="form-control cus-control" readonly="true"/></td> --%>
																						
										</tr>
								
								</c:if>
							</table>
					
 		 
						</div>	
					</div>	
				</div>
			</div>
		</div>
		</form:form>				
	</body>
</html>



