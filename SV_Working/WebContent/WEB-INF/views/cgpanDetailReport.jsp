<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MLI User Registration Form</title>
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>

</head>

<body>

	<div class="main-section">

		<div class="container-fluid">
			<div>
<!-- 			 <nav aria-label="breadcrumb">
  <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/getAppropriationDataBetweenDates.html">CGPAN History Report</a></li>  
    <li class="breadcrumb-item active" aria-current="page">CGPAN Details</li>
  </ol> 
</nav> --> 
				<div class="frm-section">
					<div class="col-md-12">
						<form:form method="POST" action="cgpanDetailsData.html">
							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="Cgpan">Enter CGPAN<span
												style="color: red;">*</span>
										</form:label>
										<form:input path="Cgpan" class="form-control cus-control" id="CGPAN" />
									<form:errors path="Cgpan" cssClass="error" />
									</div>
								</div>
							</div>

							<div class="d-inlineblock mt-25">
								<input type="submit" value="Search" name="submit" class="btn btn-reset " name="save" />
							</div>
						</form:form>

					</div>
				</div>
			</div>

		</div>


	</div>
	<c:if test="${!empty cgpenList}">
		<div id="split-sec">

			<div class="container-fluid">
		<!-- 		<h2 align="center" class="sub-head">
					<span style="background-color: white;">Application History</span>
				</h2>
 -->
				<div>
					<div class="tbl-details">
           <table class="table table-bordered table-hover cus-table mt-10 mb-0">
           <thead>
										<tr>
										<h2 align="center" class="heading">CGPAN HISTORY </h2>
											
											</tr>
												</thead>
								<c:if test="${!empty cgpenList}">
										<thead>
										<tr>
											
											<th>Member ID</th>
											<th>CGPAN</th>
											<th>Unit NAme</th>
											<th>DAN ID</th>
											<th>Approved Amount</th>
										    <th>Outstanding Amount</th>
										     <th>Crystalization Date</th>
											<th>Dan Amount</th>
											<th>Payment ID</th>
											<th>Realised date</th>
											<th>Expiry date</th>
											<th>Appropriation date</th>
											<th>Appropriation By</th>
											<th>Status</th>
											<th>UTR No</th>
											
										</tr>
									</thead>
									<% int counter=0;%>
									<c:forEach items="${cgpenList}" var="cgpenList">
										<tr <% if(counter%2==0){%>bgcolor="silver" <%}%>>
											
											<td><c:out value="${cgpenList.mliId}"/></td>
											<td><c:out value="${cgpenList.displayCgpen}"/></td>
											<td><c:out value="${cgpenList.ssiName}"/></td>
											<td><c:out value="${cgpenList.danId}"/></td>
											<td><c:out value="${cgpenList.guaranteeAmt}"/></td>
										  <td><c:out value="${cgpenList.outstandingAmt}"/></td>
										  	 <td><c:out value="${cgpenList.crystalizationDate}"/></td>  
										  	<td><c:out value="${cgpenList.dan_Amt}"/></td>  
											<td><c:out value="${cgpenList.payId}"/></td>
											<td><c:out value="${cgpenList.dciAppropriationDate}"/></td>
											<td><c:out value="${cgpenList.expiryDate}"/></td>
											<td><c:out value="${cgpenList.dciAppropriationDate}"/></td>
											<td><c:out value="${cgpenList.appropriation_by}"/></td>
											<td><c:out value="${cgpenList.status}"/></td>
										<td></td>
										<td></td>
											<%-- <td><a href="claimLoadgementApprovalCGTMSE.html?MLIName=${danDetailList.mliName}"><form:input path="claimCnt" value="${danDetailList.claimCnt}" class="form-control cus-control" readonly="true"/></td> --%>
																						  <%counter+=1;%>
										</tr>
									</c:forEach>
								</c:if>
						
							</table>

					

	<div align="center">
										
									
									</div>
						</div>
					</div>
				</div>
				
			</div>


		</div>
		
	</c:if>
</body>