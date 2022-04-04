<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
		<link href="css/customstyle.css" type="text/css" rel="stylesheet">
		<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
		<script type="text/javascript" src="jquery-ui-1.10.3/ui/jquery.ui.datepicker.js"></script>
		<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
		<script>
			$(function() {
				$("#ratingDate").datepicker({
					dateFormat : 'dd-MM-yyyy'
				});
			});
		</script>
	</head>

	<body>
		<div class="main-section">
			<div class="container-fluid">
				<div>	
					<div class="tbl-details">
						<div class="col-md-12">
							<h5 class="notification-message"><strong>${message}</strong></h5>
							<div class="d-inlineblock">
								<a href="/SV/showCreateExposureLimitMakerInputForm.html"><input type=button value=" Create Exposure Limit " class="btn btn-reset " ></a>
								<!-- 	<input type="button" value="Download" onclick="resetForm()" class="btn btn-reset" />
									<input type="submit" value="Print" onclick="resetForm()" class="btn btn-reset" /> -->
							</div>
							<div class="d-inlineblock float-right">
								<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px; background-color: #ffffff;" placeholder="Search Data.." title="Type in a name">
								 <button style="border:none !important; cursor: not-allowed;">
								 		<a href="ExposureDetailDownload.html">
									<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
									</a>
								 </button>			
							</div>

							<c:if test="${!empty expoList}">
								<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0">
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
											<th>ACTIONS</th>
										</tr>
									</thead>	
									<% int counter=0;%>
									<c:forEach items="${expoList}" var="expoList">
											<tr>
											    <td><%=counter+1%></td>
												<td><c:out value="${expoList.mliLongName}"/></td> 
												<td><c:out value="${expoList.mliShortName}"/></td> 
												<td><c:out value="${expoList.mliExposureLimit}" /></td>
												<td><c:out value="${expoList.mliDateOfSanctionOfExposure}" /></td>
												<td><c:out value="${expoList.mliValidityOfExposureLimitStartDate}" /></td>
												<td><c:out value="${expoList.mliValidityOfExposureLimitEndDate}" /></td>
												<td><c:out value="${expoList.gurantee_fee}" /></td>
												<td><c:out value="${expoList.pay_out_cap}" /></td>
												<td><c:out value="${expoList.guranteeCoverage}" /></td>
												<td><c:out value="${expoList.statusDescription}" /></td>
												<td align="center" class="btn-edit">
													<c:choose>
															<c:when test="${expoList.status=='CEMCR'}">
																<a href="/SV/getMliExposureMasterMakerShortName.html?mliLongName=${expoList.mliLongName}" class="btn-edit">Edit</a>
															</c:when>
															<c:when test="${expoList.status=='CEMCA'}">
																<a href="/SV/getMliExposureMasterMakerShortName.html?mliLongName=${expoList.mliLongName}" class="btn-edit">Edit</a> 
															</c:when>
															<c:otherwise>
															<span class="btn-edit not-allow">	Edit </span>
															</c:otherwise>
													</c:choose>
												</td>
												<%  counter+=1;%>
											</tr>
									</c:forEach>
								</table>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>



