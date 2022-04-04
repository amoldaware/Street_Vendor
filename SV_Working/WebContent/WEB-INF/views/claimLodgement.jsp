<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<title>Claim Lodgment</title>
		<link href="css/jquery-ui-css.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
		<title>Claim Lodgment</title>
		<script type="text/javascript">
		/* function toggleIcon(e) {
			debugger;
		    $(e.target)
		        .prev('.panel-heading')
		        .find(".more-less")
		        .toggleClass('fa-plus fa-minus');
		}
		$('.panel-group').on('hidden.bs.collapse', toggleIcon);
		$('.panel-group').on('shown.bs.collapse', toggleIcon); */
		
			$(function() {
				$("#dateOfNotice").datepicker({
					// showAnim : "fold"
					dateFormat : "dd/mm/yy"
				});
			});
		
			$(function() {
				$("#dateOfSarfaesi").datepicker({
					// showAnim : "fold"
					dateFormat : "dd/mm/yy"
				});
			});
		
			$(function() {
				$("#dateOfSubsidy").datepicker({
					// showAnim : "fold"
					dateFormat : "dd/mm/yy"
				});
			});

		</script>
	</head>
	<body onload="clearField()">
		<div class="main-section">
			<div class="frm-section">
				<div class="col-md-12">
					<form:form method="POST" action="claimLodgementInputForm.html" class="form-horizontal" id="exposureMasterMakerDetailsFormId">
                 <h5 class="error1 mtb-0">
				 	<strong>${message}</strong>
			  </h5>
	
	<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
  <div class="panel panel-default cus-pnl cus-pnl">
    <div class="panel-heading cus-pheading cus-pheading" role="tab" id="headingOne">
      <h4 class="panel-title cus-ptitle cus-ptitle">
        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
        	1. Claim Lodgement For First Installment
        	<i class="fa fa-chevron-circle-down" aria-hidden="true"></i>
        	<!-- <span><img src="images/plus.png"  style="width:18px;" class="more-less plus" alt=""></span>
        <span><img src="images/negative.png"  style="width:18px;" class="more-less minus" alt=""></span> -->
        </a>
      </h4>
    </div>	<!-- panel-head -->
      <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body">
    <div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
			<div class="form-group">
				<div class="col-md-12 prl-10">
					<label>Member Id :</label>
					<form:input path="memberId" value="${memberId}" size="20" style="text-align: right" id="memberId" class="form-control cus-control" readonly="true"/>
				</div>
			</div>
		</div>
		
		<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
			<div class="form-group">
				<div class="col-md-12 prl-10">
					<label>Enter CGPAN :</label>
					<form:input path="cgpan" value="" size="20" id="cgpan" class="form-control cus-control" />
				</div>
			</div>
		</div>
		<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
			<div class="form-group">
				<div class="col-md-12 prl-10">
					<label style="display: none;"> CGPAN</label>
					<input type="submit" value="Submit"   class="btn btn-reset mt-15" />			
				</div>
			</div>
		</div>
      </div>
    </div>
  </div> <!-- panel -->
 <div class="panel panel-default cus-pnl">
    <div class="panel-heading cus-pheading" role="tab" id="headingOne">
      <h4 class="panel-title cus-ptitle">
        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="true" aria-controls="collapseOne">
        	2. Resubmission of First Installment Claim.(Claim Returned by NBFC Checker)
        	<i class="fa fa-chevron-circle-down" aria-hidden="true"></i>
        	<!-- <span><img src="images/plus.png"  style="width:18px;" class="more-less plus" alt=""></span>
        <span><img src="images/negative.png"  style="width:18px;" class="more-less minus" alt=""></span> -->
        </a>
      </h4>
    </div>	<!-- panel-head -->
      <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body">
      
     	<div class="tbl-details">
			<div class="col-md-12">
				<div class="d-inlineblock float-right "></div>
				<table id="myTable"  class="table table-bordered table-hover cus-table mt-10 mb-0">
					<c:if test="${!empty claimReturnedRecordsList}">
						<thead>
						<tr>
							<th>Sr.No.</th>
							<th>Date Of Claim Lodgement</th>
							<th>Claim Reference No.</th>
							<th>Unit Name</th>
							<th>Guarantee Approved Amount</th>
							<th>Status</th>
							<th>Checker Remarks</th>
							<th>Resubmission</th>
						</tr>
			
						</thead>
						<% int counter=0;%>
	
						<c:forEach items="${claimReturnedRecordsList}" var="claimReturnedRecordsList">
								<tr <% if(counter%2==0){%>bgcolor="silver" <%}%>>
									<td><%=counter+1%></td>
									<td>${claimReturnedRecordsList.dateOfClaimLoadge}</td> 
									<td>${claimReturnedRecordsList.claimRefNo}</td>
									<td>${claimReturnedRecordsList.unitName}</td>  
									<td>${claimReturnedRecordsList.latestOsAmt}</td>
									<td>${claimReturnedRecordsList.claimStatus} </td>
									<td>${claimReturnedRecordsList.remarks} </td>
									<%-- <td>${claimReturnedRecordsList.cgpan} </td> --%>
									<td align="center" class="btn-edit">
										<a href="/SV/claimLodgedDataForModification.html?cgpan=${claimReturnedRecordsList.cgpan}" class="btn-edit">Edit</a> 
										<%-- <c:choose>
												<c:when test="${claimReturnedRecordsList.claimStatus=='CR'}">
													<a href="claimLodgedDataForModification.html?cgpan=${claimReturnedRecordsList.cgpan}" class="btn-edit">Edit</a>
												</c:when>
												<c:when test="${claimReturnedRecordsList.claimStatus=='RT1'}">
													<a href="/SV/claimLodgedDataForModification.html?cgpan=${claimReturnedRecordsList.cgpan}" class="btn-edit">Edit</a> 
												</c:when>
												<c:when test="${claimReturnedRecordsList.claimStatus=='RC'}">
													<a href="/SV/claimLodgedDataForModification.html?cgpan=${claimReturnedRecordsList.cgpan}" class="btn-edit">Edit</a> 
												</c:when>
										</c:choose> --%>
									</td>																	
								</tr>
								 <%counter+=1;%>
						</c:forEach>
					</c:if>
					<c:if test="${empty claimReturnedRecordsList}">
						<thead>
							<tr>
								<th>Sr.No.</th>
									<th>Date Of Claim Lodgement</th>
								<th>Claim Reference No.</th>
								<th>Unit Name</th>
								<th>Guarantee Approved Amount</th>
								<th>Status</th>
								<th>Checker Remarks</th>
								<th>Resubmission</th>
							</tr>
						</thead>
						<tr>Record Not Found</tr>
					</c:if>
				</table>
			</div>	
		</div>	
      </div>
    </div>
  </div> <!-- panel -->
 <div class="panel panel-default cus-pnl">
    <div class="panel-heading cus-pheading" role="tab" id="headingOne">
      <h4 class="panel-title cus-ptitle">
        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="true" aria-controls="collapseOne" >
        	3. Relodgement of First Installment Claim.(Claim Returned by CGTMSE)
        	 <i class="fa fa-chevron-circle-down text-right" aria-hidden="true"></i>
        </a>
       
      </h4>
    </div>	<!-- panel-head -->
      <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body">
 			<div class="tbl-details">
				<div class="col-md-12">
					<div class="d-inlineblock float-right "></div>
					<table id="myTable2"  class="table table-bordered table-hover cus-table mt-10 mb-0">
						<c:if test="${!empty claimReturnedRecordsList1}">
							<thead>
									<tr>
										<th>Sr.No.</th>
										<th>Date Of Claim Lodgement</th>
										<th>Claim Reference No.</th>
										<th>Unit Name</th>
										<th>Guarantee Approved Amount</th>
										<th>Status</th>
										<th>Checker Remarks</th>
										<th>Resubmission</th>
									</tr>
				
							</thead>
							<% int counter=0;%>
		
							<c:forEach items="${claimReturnedRecordsList1}" var="claimReturnedRecordsList1">
									<tr <% if(counter%2==0){%>bgcolor="silver" <%}%>>
										<td><%=counter+1%></td>
										<td>${claimReturnedRecordsList1.dateOfClaimLoadge}</td> 
										<td>${claimReturnedRecordsList1.claimRefNo}</td>
										<td>${claimReturnedRecordsList1.unitName}</td>  
										<td>${claimReturnedRecordsList1.latestOsAmt}</td>
										<td>${claimReturnedRecordsList1.claimStatus} </td>
										<td>${claimReturnedRecordsList1.remarks} </td>
										<%-- <td>${claimReturnedRecordsList.cgpan} </td> --%>
										<td align="center" class="btn-edit">
											<a href="/SV/claimLodgedDataForModification.html?cgpan=${claimReturnedRecordsList1.cgpan}" class="btn-edit">Edit</a> 
											<%-- <c:choose>
													<c:when test="${claimReturnedRecordsList.claimStatus=='CR'}">
														<a href="claimLodgedDataForModification.html?cgpan=${claimReturnedRecordsList.cgpan}" class="btn-edit">Edit</a>
													</c:when>
													<c:when test="${claimReturnedRecordsList.claimStatus=='RT1'}">
														<a href="/SV/claimLodgedDataForModification.html?cgpan=${claimReturnedRecordsList.cgpan}" class="btn-edit">Edit</a> 
													</c:when>
													<c:when test="${claimReturnedRecordsList.claimStatus=='RC'}">
														<a href="/SV/claimLodgedDataForModification.html?cgpan=${claimReturnedRecordsList.cgpan}" class="btn-edit">Edit</a> 
													</c:when>
											</c:choose> --%>
										</td>
										
										
									</tr>
									 <%counter+=1;%>
							</c:forEach>
						</c:if>
						<c:if test="${empty claimReturnedRecordsList1}">
							<thead>
								<tr>
									<th>Sr.No.</th>
										<th>Date Of Claim Lodgement</th>
									<th>Claim Reference No.</th>
									<th>Unit Name</th>
									<th>Guarantee Approved Amount</th>
									<th>Status</th>
									<th>Checker Remarks</th>
									<th>Resubmission</th>
								</tr>
							</thead>
							<tr>Record Not Found</tr>
						</c:if>
					</table>
				</div>	
			</div>	
      </div>
    </div>
  </div> <!-- panel --> 
 
    
</div><!-- panel-group -->									
 	
	</form:form>
	</div>
	<div class="col-md-12">
 <marquee style="    color: green;
    font-weight: 600;
 ">MR-Maker Relodge,ML-Maker lodge</marquee>	 
</div>
</div>
</div>

	

	</body>
</html>
