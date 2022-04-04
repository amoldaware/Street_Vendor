<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Management Certificate</title>
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css"
	rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<%
	String s = (String) request.getAttribute("SName");
%>
<script type="text/javascript">
	$(document).ready(function() {
		$(".form-control").removeClass("cus-control");
		$(".form-control").addClass("txtarea");
		// $("p:first").addClass("intro");
	});
</script>
<style>
.form-control.txtarea {
	min-height: 31px !important;
	height: 31px;
	width: 100%;
	resize: vertical;
	transition: all 0.5s;
	border-top: 0px;
	border-right: 0px;
	border-left: 0px;
	border-radius: 5px;
	/*     border: 1px solid #92c7e5; box-shadow: 1px 1px 2px #47a0d4d9; 	  border-color: #66afe970;}		blue shades*/
	border: 1px solid #d4d4d4 !important;
	box-shadow: 1px 2px 3px #be54c180; /* purple shade        
  /*  box-shadow: 1px 2px 3px #47a0d4;	box-shadow: 1px 1px 2px #47a0d4ba;*/
}

.form-control.txtarea:hover,.form-control.txtarea:focus {
	/*  box-shadow: 1px 3px 3px #66afe970; */
	/* box-shadow: 1px 3px 3px #47d4c0;  blue shades*/
	/*   background-color: #a641a936;  pruple shade1*/
	background-color: #fa61ff42;
	box-shadow: 1px 3px 3px #be54c1; /* purple shade */
}

input[type=radio] {
	margin: 4px 2px 0;
}
</style>
</head>
<body bgcolor="#E0FFFF">

	<div class="main-section">
	<%String userRole = (String) session.getAttribute("uRole"); 
	
		if (userRole.equals("CCHECKER")) {
						%>

		<nav aria-label="breadcrumb">
		<ol class="breadcrumb cus-breadcrumb">
			<li class="breadcrumb-item">
			<a href="/SV/claimLoadgementApprovalCGTMSE.html?MLIName=${MLIID}&CNT=${CNT}">claimLoadgement
					Approval</a></li>
			<li class="breadcrumb-item active" aria-current="page">Claim Recommendation Details</li>
		</ol>
		</nav>
		<%}else if(userRole.equals("NCHECKER")) {%>
		<nav aria-label="breadcrumb">
		<ol class="breadcrumb cus-breadcrumb">
			<li class="breadcrumb-item"><a
				href="/SV/claimLoadgementApprovalMLI.html">Claim Lodgement
					Approval</a></li>
			<li class="breadcrumb-item active" aria-current="page">Claim CheckList Details</li>
		</ol>
		</nav>
		<%}%>
	
	
	

		<div class="container-fluid">
			<div class="frm-section">
				<div class="col-md-12">
					<form:form action="saveClaimLodgementCheckListData.html"
						method="POST" class="table-responsive">
							<input type="hidden" path="mliName" id ="mli" value="${MLIID}"/> 
							<input type="hidden" path="claimCnt" id ="claimCnt" value="${CNT}"/>
					
					<!-- <input type ="hidden" path="mliName" value="${MLIID}"/> -->	
						<table
							class="table table-bordered table-hover cus-table mt-10 mb-0">
							<tr>
								<th style="min-width: 70px;">Sr. No.</th>
								<th style="min-width: 100px;" >RECOMMENDATION</th>
							    <th style="min-width: 100px;" >DETAILS</th>
								<th style="min-width: 90px;">CONDITION</th>
							</tr>
							<tr>
								<td>1</td>
									<td> <c:out value="${danDetailList.intrestRateStatus}"/></td>
								<td>INTEREST RATE</td>
							
								<td><c:out value="${danDetailList.intrestRate}%"/></td>
							</tr>
							<tr>
								<td>2</td>
								<td><c:out value="${danDetailList.activitytypeStatus}"/></td>
								<td>TYPE OF ACTIVITY</td>
								
								<td><c:out value="${danDetailList.activityType}"/></td>

							</tr>
							<tr>
								<td>3</td>
								<td><c:out value="${danDetailList.wilfuldefaulterStatus}"/></td>
								<td>ACCOUNT DECLARED AS WILFUL DEFAULTER</td>
								
								<td><c:out value="${danDetailList.wilfulDefaulter}"/></td>
							</tr>
							<tr>
							<td>4</td>
								<td><c:out value="${danDetailList.accFraudStatus}"/></td>
							<td>ACCOUNT CLASSIFIED AS FRAUD</td>
						
							<td><c:out value="${danDetailList.fraudAcc}"/></td>
							</tr>
							<tr>
							<td>5</td>
							<td><c:out value="${danDetailList.forumStatus}"/></td>
							<td>NAME OF LEGAL FORUM</td>
							
							<td><c:out value="${danDetailList.forum}"/></td>
							</tr>
							<tr>
								<tr>
							<td>6</td>
								<td><c:out value="${danDetailList.legalFileDateStatus}"/></td>
						    <td>LEGAL FILING DATE</td>
						
							<td><c:out value="${danDetailList.legalFileDate}"/></td>
							</tr>
								<tr>
							<td>7</td>
							<td><c:out value="${danDetailList.suitNoStatus}"/></td>
							 <td>SUIT/REGISTRATION NO</td>
							
							<td><c:out value="${danDetailList.suitNo}"/></td>
							</tr>
								<tr>
							<td>8</td>
								<td><c:out value="${danDetailList.internalRatingStatus}"/></td>
								 <td>INTERNAL RATING</td>
						
							<td><c:out value="${danDetailList.internalRating}"/></td>
							</tr>
								<tr>
							<td>9</td>
								<td><c:out value="${danDetailList.withinNPA}"/></td>
								 <td>NPA DATE IS WITHIN 90 DAYS OF GUARANTEE START DATE</td>
						
							<td><c:out value="${danDetailList.withinNPA}"/></td>
							</tr>
						</table>
					</form:form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>