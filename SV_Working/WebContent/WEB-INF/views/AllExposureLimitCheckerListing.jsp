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
				
							<table id="myTable"  class="table table-bordered table-hover cus-table mt-10 mb-0">
								<c:if test="${!empty expoCheckerList}">
									<thead>
										<tr>
											<th>SR.No.</th>
											<th>MLI LONG NAME</th>
											<th>MLI SHORT NAME</th>
											<th>EXPOSURE LIMIT</th>
											<th>EXPOSURE SANCTION DATE</th>
											<th>START DATE</th>
											<th>END DATE</th>
											<th>GUARANTEE FEE(%P.A.)</th>
											<th>PAY-OUT CAP(TIMES)</th>
											<th>GUARANTEE COVERAGE</th>
											<th>STATUS </th>
											<th>ACTIONS ON ROW</th>
										</tr>
									</thead>
									<% int counter=0;%>
									<c:forEach items="${expoCheckerList}" var="expoCheckerList">
										<tr <% if(counter%2==0){%>bgcolor="silver" <%}%>>
											<td><%=counter+1%></td>
											<td><c:out value="${expoCheckerList.mliLongName}"/></td> 
											<td><c:out value="${expoCheckerList.mliShortName}"/></td> 
											<td><c:out value="${expoCheckerList.mliExposureLimit}" /></td>
											<td><c:out value="${expoCheckerList.mliDateOfSanctionOfExposure}" /></td>
											<td><c:out value="${expoCheckerList.mliValidityOfExposureLimitStartDate}" /></td>
											<td><c:out value="${expoCheckerList.mliValidityOfExposureLimitEndDate}" /></td>
											<td><c:out value="${expoCheckerList.gurantee_fee}" /></td>
											<td><c:out value="${expoCheckerList.pay_out_cap}" /></td>
											<td><c:out value="${expoCheckerList.guranteeCoverage}" /></td>
											<td><c:out value="${expoCheckerList.statusDescription}" /></td>
											<td align="center">
												<c:choose>
													<c:when test="${expoCheckerList.status=='CEMMA'}">
														<a href="getMliCheckerShortName1.html?mliLongName=${expoCheckerList.mliLongName}" class="btn-edit">Approve | Return</a>
													</c:when>
													<c:when test="${expoCheckerList.status=='CEMME'}">
														<a href="getMliCheckerShortName2.html?mliLongName=${expoCheckerList.mliLongName}" class="btn-edit">Approve | Return</a>
													</c:when>
											 	</c:choose>
											</td>
										
											  <%counter+=1;%>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty expoCheckerList}">
									<tr>Record Not Found</tr>
								</c:if>
							</table>
						</div>	
					</div>	
				</div>
			</div>
		</div>				
	</body>
</html>



