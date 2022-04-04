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
		<style>
		.clmref{	cursor:pointer; border-right:none; border-left:none; border-top:none; border-bottom:1px solid rgba(51, 122, 182, 1);	}
		.clmref:hover{color:rgba(146, 59, 149, 1);}
		.modal-dialog{width:80%;}
		.modal-header,.modal-footer{    padding: 10px 15px;}
		.modal-body{    padding: 5px 15px;}
		.declarebtn:hover{cursor:pointer;}
		.btn-popup:hover{	cursor:pointer;	}
		</style>
		<script>
function myFunction() {
	window.open(this.href, 'mywin',
	'left=20,top=20,width=500,height=500,toolbar=1,resizable=0'); return false;
}

</script>
		<script>
		
		
		/* var Pass='PASS';
		 var Fail='FAILED'; */
		 
			$(function() {
				$("#ratingDate").datepicker({
					dateFormat : 'dd-mm-yy'
				});
			});
			
			function validatedata(){
				  var flag="";
				  var submit="";
				  var str = '';
			      var elem = document.getElementById('mliApproveOrRejectClaimId').elements;
			      for(var i = 0; i < elem.length; i++){
			    	   if(elem[i].name=="AcceptReturn"){
			    		
			    		   if(elem[i].value=="Accept" || elem[i].value=="Return"){
			    			  
			    			 flag=true;
			    		   }else{
			    			  
			    			 flag=false;  
			    		   }
			    	   }
			    	   if(flag==true){
			    		   if(elem[i].name=="remarks"){
				            	if(elem[i].value=="" || elem[i].value==null ){
				                  alert("Remarks is mandetory");
				            	  //document.getElementById("requiredAcceptReturn").value="Enter Remarks";
				            	}else{
				            	   submit="1";
				            	   //document.getElementById("requiredAcceptReturn").value="";
				            	}
				            } 
			    	   }else{
			    		   if(elem[i].name=="remarks"){
				            	if(elem[i].value=="" || elem[i].value==null ){
				            		elem[i].value="Enter Remarks";
				            	}else{
				            		elem[i].value;
				            		submit="1";
				            	}
				            }  
			    	   }
			        } 
			  
			      if(submit=1){
			    	//	alert(document.getElementById("mli").value);
			      	document.getElementById('mliApproveOrRejectClaimId').action = "/SV/mliApproveOrRejectClaim.html?mli="+document.getElementById("mli").value;
			   		document.getElementById('mliApproveOrRejectClaimId').submit();
			      }else{
			    	flag=false;
			      }
			}
		
		</script>
		
	</head>

	<body bgcolor="#E0FFFF">
	<form:form  action="" method="POST" id="mliApproveOrRejectClaimId" class="form-horizontal">
	
	<!-- mliApproveOrRejectClaim.html -->
		<div class="main-section">
	
			<div class="container-fluid">
				<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
  <div class="panel panel-default cus-pnl cus-pnl">
    <div class="panel-heading cus-pheading cus-pheading" role="tab" id="headingOne">
      <h4 class="panel-title cus-ptitle cus-ptitle">
        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
        	1. Submission of First Installment Claim
        	<i class="fa fa-chevron-circle-down" aria-hidden="true"></i>
        	<!-- <span><img src="images/plus.png"  style="width:18px;" class="more-less plus" alt=""></span>
        <span><img src="images/negative.png"  style="width:18px;" class="more-less minus" alt=""></span> -->
        </a>
      </h4>
    </div><div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body">
				<div>	
					<div class="tbl-details">
						<div class="col-md-12">
							<div class="d-inlineblock float-right ">
								    		
		    					<!-- <button style="border:none !important; cursor: not-allowed;">
		    						<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
		    					</button> -->			
							</div>
						
				         <h5 class="notification-message">${msg}</h5>
							<table id="myTable"  class="table table-bordered table-hover cus-table mt-10 mb-0">
							<input type="hidden" path="mliName" id ="mli" value="${MLIID}"/> 
								<c:if test="${!empty danDetailList}">
									<thead>
										<tr>
											<th>Sr.No.</th>
											<th>Date Of Claim Lodgement</th>
											<th>Claim Reference No.</th>
											<th>Unit Name</th>
											<th>Guarantee Approved Amount</th>
											<th>First Installment Claim Amount</th>
											<th>Status</th>
											<th>View Checklist</th>
											<th>I Certify the Content of Claimform & D/U</th>
											<th>Recommendation</th>
											<th>Checker Remarks</th>
										
										</tr>
									</thead>
									<% int counter=0;%>
								
									<c:forEach items="${danDetailList}" var="danDetailList">
										<tr <% if(counter%2==0){%>bgcolor="silver" <%}%>>
											<td><%=counter+1%></td>
											<td><c:out value="${danDetailList.dateOfClaimLoadge}"/></td> 
											<!--<td><a href="getclaimLoadgeForm.html?claimRefNo=${danDetailList.claimRefNo}"><c:out value="${danDetailList.claimRefNo}"/></a></td>
											-->
											<td>
											<a href="getclaimLoadgeForm.html?claimRefNo=${danDetailList.claimRefNo}" data-toggle="tooltip" title="Click Here To Open Claim form!">
												<form:input path="claimRefNo" value="${danDetailList.claimRefNo}" class="clmref" id="claimRefNo"  readonly="true"/>
											</a>
											
											</td>
											<td><c:out value="${danDetailList.unitName}"/></td>  
												<td><c:out value="${danDetailList.latestOsAmt}"/></td>
												<td><c:out value="${danDetailList.firstInstallClaim}"/></td>
												<td><c:out value="${danDetailList.claimStatus}"/></td>
											<td>
											<%-- href="getChecklistDeclaration.html?claimRefNo=${danDetailList.claimRefNo}" value="View" --%>
											<a href="getChecklistDeclaration.html?claimRefNo=${danDetailList.claimRefNo}" value="View" class="form-control cus-control">View</a>
										</td>
										
									
								 	<%--  <td><c:out value="${danDetailList.chkListStatus}"/></td>   --%>
											<td>
								     
								             
												<c:if test="${danDetailList.chkListStatus eq 'FAILED'}">
											         <form:select path="AcceptReturn" class="form-control cus-control" id="AcceptReturn" readonly="true">
												<form:option value="Select" />
												<%-- <form:option value="Accept" /> --%>
												<form:option value="Return" />
										       
											       </form:select> 
											 <font color="red" size="2">   Ineligible As per CGS-II </font>
												</c:if>
										<c:if test="${danDetailList.chkListStatus eq 'PASS'}">
											<form:select path="AcceptReturn" class="form-control cus-control" id="AcceptReturn" readonly="true">
												<form:option value="Select" />
												<form:option value="Accept" />
												<form:option value="Return" />
										
											</form:select> 
											<font color="green" size="2">   Eligible </font>
									     </c:if>
											
									  
											</td>
												<td>
												 
												<a href="getChecklistDeclRecommand.html?claimRefNo=${danDetailList.claimRefNo}" value="View" class="form-control cus-control">View</a>
											<!-- <a type="button" data-toggle="modal" data-target="#myModal" class="form-control cus-control">View</a> -->
											 
											<%-- <a onclick="window.open(this.href, 'mywin',
											'left=20,top=20,width=1280,height=800,toolbar=1,resizable=true');" class="form-control cus-control" href="getChecklistDeclRecommand.html?claimRefNo=${danDetailList.claimRefNo}" value="View">View</a> --%>
									<!-- 		<a type="button" data-toggle="modal" data-target="#myModal" class="form-control cus-control btn-popup">View</a> -->
										</td>
											<%-- <form:radiobutton path="AcceptReturn"<%=counter%> value="Accept"/>Accept
											<form:radiobutton path="AcceptReturn"<%=counter%> value="return"/>return --%>
											
								   
											<td><form:input path="remarks" value="${danDetailList.remarks}" id="remarks" class="form-control cus-control"/></td>
										<%-- 	<td><input type="text" path="ClaimStatusRemarkHidden" id="finalout${danDetailList.claimRefNo}"/><td> --%>
											  <%counter+=1;%>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty danDetailList}">
								<thead>
										<tr>
											<th>Sr.No.</th>
											<th>Date Of Claim Lodgement</th>
											<th>Claim Reference No.</th>
											<th>Unit Name</th>
											<th>Guarantee Approved Amount</th>
											<th>First Installment Claim Amount</th>
											<th>Status</th>
											<th>View Checklist</th>
											<th>I Certify the Content of Claimform & D/U</th>
											<th>Recommendation</th>
											<th>Checker Remarks</th>
											
										</tr>
									</thead>
									<tr>Record Not Found</tr>
								</c:if>
							</table>
						   <div class="modal-footer">
						        <button type="Submit" class="btn btn-default" data-dismiss="modal" onclick="validatedata();">Submit</button>
						      </div>
						   <!--    <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						      </div> -->
						    </div>
						
						  </div>
						</div> 
						</div>
						</div>
						</div>	
						<div class="panel panel-default cus-pnl">
    <div class="panel-heading cus-pheading" role="tab" id="headingOne">
      <h4 class="panel-title cus-ptitle">
        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="true" aria-controls="collapseOne">
        		2. Submission of Relodged First Installment Claim. (Claim Returned by CGTMSE)
        	<i class="fa fa-chevron-circle-down" aria-hidden="true"></i>
        	<!-- <span><img src="images/plus.png"  style="width:18px;" class="more-less plus" alt=""></span>
        <span><img src="images/negative.png"  style="width:18px;" class="more-less minus" alt=""></span> -->
        </a>
      </h4>
    </div>	<!-- panel-head -->
      <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body">
      <div>	
					<div class="tbl-details">
						<div class="col-md-12">
							<div class="d-inlineblock float-right ">
								    		
		    					<!-- <button style="border:none !important; cursor: not-allowed;">
		    						<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
		    					</button> -->			
							</div>
						
				         <h5 class="notification-message">${msg}</h5>
							<table id="myTable2"  class="table table-bordered table-hover cus-table mt-10 mb-0">
									<input type="hidden" path="mliName" id ="mli" value="${MLIID}"/> 
								<c:if test="${!empty danDetailList1}">
									<thead>
										<tr>
											<th>Sr.No.</th>
											<th>Date Of Claim Lodgement</th>
											<th>Claim Reference No.</th>
											<th>Unit Name</th>
											<th>Guarantee Approved Amount</th>
											<th>First Installment Claim Amount</th>
											<th>Status</th>
											<th>View Checklist</th>
											<th>I Certify the Content of Claimform & D/U</th>
											<th>Recommendation</th>
											<th>Checker Remarks</th>
										
										</tr>
									</thead>
									<% int counter=0;%>
								
									<c:forEach items="${danDetailList1}" var="danDetailList">
										<tr <% if(counter%2==0){%>bgcolor="silver" <%}%>>
											<td><%=counter+1%></td>
											<td><c:out value="${danDetailList.dateOfClaimLoadge}"/></td> 
											<!--<td><a href="getclaimLoadgeForm.html?claimRefNo=${danDetailList.claimRefNo}"><c:out value="${danDetailList.claimRefNo}"/></a></td>
											-->
											<td>
											<a href="getclaimLoadgeForm.html?claimRefNo=${danDetailList.claimRefNo}" data-toggle="tooltip" title="Click Here To Open Claim form!">
												<form:input path="claimRefNo" value="${danDetailList.claimRefNo}" class="clmref" id="claimRefNo"  readonly="true"/>
											</a>
											
											</td>
											<td><c:out value="${danDetailList.unitName}"/></td>  
												<td><c:out value="${danDetailList.latestOsAmt}"/></td>
												<td><c:out value="${danDetailList.firstInstallClaim}"/></td>
												<td><c:out value="${danDetailList.claimStatus}"/></td>
											<td>
											<%-- href="getChecklistDeclaration.html?claimRefNo=${danDetailList.claimRefNo}" value="View" --%>
											<a href="getChecklistDeclaration.html?claimRefNo=${danDetailList.claimRefNo}" value="View" class="form-control cus-control">View</a>
										</td>
										
									
								 	<%--  <td><c:out value="${danDetailList.chkListStatus}"/></td>   --%>
											<td>
								     
								             
												<c:if test="${danDetailList.chkListStatus eq 'FAILED'}">
											         <form:select path="AcceptReturn" class="form-control cus-control" id="AcceptReturn" readonly="true">
												<form:option value="Select" />
												<%-- <form:option value="Accept" /> --%>
												<form:option value="Return" />
										       
											       </form:select> 
											 <font color="red" size="2">   Ineligible As per CGS-II </font>
												</c:if>
										<c:if test="${danDetailList.chkListStatus eq 'PASS'}">
											<form:select path="AcceptReturn" class="form-control cus-control" id="AcceptReturn" readonly="true">
												<form:option value="Select" />
												<form:option value="Accept" />
												<form:option value="Return" />
										
											</form:select> 
											<font color="green" size="2">   Eligible </font>
									     </c:if>
											
									  
											</td>
												<td>
												 
												<a href="getChecklistDeclRecommand.html?claimRefNo=${danDetailList.claimRefNo}" value="View" class="form-control cus-control">View</a>
											<!-- <a type="button" data-toggle="modal" data-target="#myModal" class="form-control cus-control">View</a> -->
											 
											<%-- <a onclick="window.open(this.href, 'mywin',
											'left=20,top=20,width=1280,height=800,toolbar=1,resizable=true');" class="form-control cus-control" href="getChecklistDeclRecommand.html?claimRefNo=${danDetailList.claimRefNo}" value="View">View</a> --%>
									<!-- 		<a type="button" data-toggle="modal" data-target="#myModal" class="form-control cus-control btn-popup">View</a> -->
										</td>
											<%-- <form:radiobutton path="AcceptReturn"<%=counter%> value="Accept"/>Accept
											<form:radiobutton path="AcceptReturn"<%=counter%> value="return"/>return --%>
											
								   
											<td><form:input path="remarks" value="${danDetailList.remarks}" id="remarks" class="form-control cus-control"/></td>
										<%-- 	<td><input type="text" path="ClaimStatusRemarkHidden" id="finalout${danDetailList.claimRefNo}"/><td> --%>
											  <%counter+=1;%>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty danDetailList1}">
								<thead>
										<tr>
										<tr>
											<th>Sr.No.</th>
											<th>Date Of Claim Lodgement</th>
											<th>Claim Reference No.</th>
											<th>Unit Name</th>
											<th>Guarantee Approved Amount</th>
											<th>First Installment Claim Amount</th>
											<th>Status</th>
											<th>View Checklist</th>
											<th>I Certify the Content of Claimform & D/U</th>
											<th>Recommendation</th>
											<th>Checker Remarks</th>
											
										</tr>
											
										</tr>
									</thead>
									<tr>Record Not Found</tr>
								</c:if>
							</table>
						
						      	   <div class="modal-footer">
						        <button type="Submit" class="btn btn-default" data-dismiss="modal" onclick="validatedata();">Submit</button>
						      </div>
						    </div>
						
						  </div>
						</div> 
      </div>
    </div>
  </div> <!-- panel -->
						
						<div id="" class="modal fade" role="dialog">
						  <div class="modal-dialog">
						   
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal">&times;</button>
						       <!--  <h4 class="modal-title">Modal Header</h4> -->
						      </div>
						      <div class="modal-body">
						 						
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						      </div>
						    </div>
						
						  </div>
						</div> 
						
					</div>	
				</div>
				</div>
				</div>
			</div>
		</div>
		</form:form>				
	</body>
</html>




