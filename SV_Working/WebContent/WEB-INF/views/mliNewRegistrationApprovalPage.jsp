<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>MLI Approval/Reject Screen</title>
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<%
	String s = (String) request.getAttribute("SName");
%>

</head>

<body>

<div class="main-section">
<div class="container-fluid">
<!-- <div class="row"> -->
	<div>	
<nav aria-label="breadcrumb">
  <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/approveRejectNewMLIDetails.html">MLI Registration Approval</a></li>  
    <li class="breadcrumb-item active" aria-current="page">MLI Registration Approval/Return</li>
  </ol> 
</nav> 
		<div class="frm-section">
		<div class="col-md-12">
		<h5 class="notification-message"><strong>${message}</strong></h5>
		<form:form method="POST" id="A"	class="form-horizontal">
			<h4 class="detail_sp1">MLI Basic Detail</h4>
		
	<div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		 <form:label path="longName">Long Name <span style="color: red;">*</span></form:label>
		  <form:input path="longName" id="longName" size="28" readonly="true" class="form-control cus-control" value="${mliNameList.longName}"/>
			<form:errors path="longName" cssClass="error" />
			<form:input path="" size="28" type="hidden" />
			
		  </div>
		  </div>
	 	 </div>
	 	 
	 	 <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="shortName">Short Name <span style="color: red;">*</span></form:label>
		<form:input path="shortName" id="shortName" size="28" readonly="true" class="form-control cus-control" value="${mliNameList.shortName}" />		    
		  <form:errors path="shortName" cssClass="error" />		   
		  </div>
		  </div>
	 	 </div>
	 	 
	 	 <div class="col-md-6 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="companyAddress">Registered Address of the Company<span style="color: red;">*</span> </form:label>
		<form:input path="companyAddress" id="companyAddress" size="28" class="form-control cus-control" readonly="true" value="${mliNameList.companyAddress}"/>		    
		  <form:errors path="companyAddress" cssClass="error" />   <form:input path="" size="28" type="hidden" />
		  </div>
		  </div>
	 	 </div>
	 	 
	 	 <div class="clearfix"></div>
	 	 <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="state">State <span style="color: red;">*</span></form:label>
	<form:input path="state" size="28" readonly="true" class="form-control cus-control" id="state" value="${mliNameList.state}"/>
		<form:errors path="state" cssClass="error" />	<form:input path="" size="28" type="hidden" />
		  </div>
		  </div>
	 	 </div>	
	 	 
	 	  <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="city">City <span style="color: red;">*</span> </form:label>
		<form:input path="city" id="city" size="28" readonly="true" value="${mliNameList.city}" class="form-control cus-control"/>
		<form:errors path="city" cssClass="error" /> <form:input path="" size="28" type="hidden" />
		  </div>
		  </div>
	 	 </div>	 
	 	 
	 	 <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="district">District <span style="color: red;">*</span> </form:label>
		<form:input path="district" size="28" readonly="true" id="district" class="form-control cus-control" value="${mliNameList.district}"/>
		<form:errors path="district" cssClass="error" />
		<form:input path="" size="28" type="hidden" />
		  </div>
		  </div>
	 	 </div>	
	 	 
	 	  <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
	<form:label path="pincode">Pincode <span style="color: red;">*</span> </form:label>
		<form:input path="pincode"  size="28" readonly="true" id="pincode" class="form-control cus-control" value="${mliNameList.pincode}"/>
			<form:errors path="pincode" cssClass="error" />
		<form:input path="" size="28" type="hidden" />
		  </div>
		  </div>
	 	 </div>	 
	 	 
	 	 <div class="clearfix"></div>
	 	  <h4 class="detail_sp1">MLI Contact Details</h4>
		
		 <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
	<form:label path="landlineNumber" class="d-block">Land Line Number <span style="color: red;">*</span> </form:label>		
		
		<form:input path="phoneCode"  readonly="true" class="form-control cus-control  w-28 d-inline"  id="phoneCode" size="3" value="${mliNameList.phone_code}" />
		<form:input path="landlineNumber"  readonly="true" class="form-control cus-control  w-69 d-inline" id="landlineNumber" value="${mliNameList.landlineNumber}"/>
		<form:errors path="landlineNumber" cssClass="error" />
		<form:input path="" size="28" type="hidden" />
		  </div>
		  </div>
	 	 </div>
	 	 
	 	 	 <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
	<form:label path="emailId">Email Id <span style="color: red;">*</span> </form:label>
		<form:input path="emailId" size="28" readonly="true" id="emailId" class="form-control cus-control" value="${mliNameList.emailId}"/>
		<form:errors path="emailId" cssClass="error" /> <form:input path="" size="28" type="hidden" />
		  </div>
		  </div>
	 	 </div>	
	 	 
	 	  <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
	<form:label path="contactPerson">Contact Person <span style="color: red;">*</span></form:label>
	<form:input path="contactPerson"  size="28" readonly="true" id="contactPerson" class="form-control cus-control" value="${mliNameList.contactPerson}"/>
	<form:errors path="contactPerson" cssClass="error" />
		  </div>
		  </div>
	 	 </div>	
	 	 
	 	 <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
				<form:label path="mobileNUmber">Mobile No.<span style="color: red;">*</span> </form:label>
				<form:input path="mobileNUmber" size="28" readonly="true" id="mobileNUmber" value="${mliNameList.mobileNUmber}" class="form-control cus-control"/> 
				<form:errors path="mobileNUmber" cssClass="error" />
		  </div>
		  </div>
	 	 </div>
	 	 
	 	 <div class="col-md-3 col-sm-4 col-xs-12 d-none">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="faxNumber" class="d-block">Fax Number </form:label>
		<form:input path="faxCode" readonly="true" id="faxCode" value="${mliNameList.faxCode}" size="3" class="form-control cus-control  w-28 d-inline"/>
		<form:input path="faxNumber" readonly="true" id="faxNumber" value="${mliNameList.faxNumber}" class="form-control cus-control w-69 d-inline" />		
		 <form:errors path="faxNumber" cssClass="error" />
			
		  </div>
		  </div>
	 	 </div>	
	 	 
	 	  <div class="clearfix"></div>
	 	  <h4 class="detail_sp2">MLI Registration Detail</h4>
	 	  
	 	   <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="rbiReggistrationNumber">RBI Registration No. <span style="color: red;">*</span> </form:label>
		<form:input path="rbiReggistrationNumber" size="28"	maxlength="15" class="form-control cus-control" readonly="true" id="rbiReggistrationNumber" value="${mliNameList.rbiReggistrationNumber}"/>
		<form:errors path="rbiReggistrationNumber" cssClass="error" />
		  </div>
		  </div>
	 	 </div>	
	 	 
	 	 <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="companyCIN">CIN of the Company <span style="color: red;">*</span> </form:label>
	<form:input path="companyCIN" id="companyCIN" size="28" maxlength="21" class="form-control cus-control" readonly="true" value="${mliNameList.companyCIN}"/>
				<form:errors path="companyCIN" cssClass="error" />
		  </div>
		  </div>
	 	 </div>	
	 	 
	 	  <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="companyPAN">PAN of the Company<span style="color: red;">*</span>	</form:label>
		<form:input path="companyPAN" size="28" readonly="true" id="companyPAN" value="${mliNameList.companyPAN}" class="form-control cus-control"/>
			<form:errors path="companyPAN" cssClass="error" />
		  </div>
		  </div>
	 	 </div>	
	 	 
	 	 <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
		<form:label path="gstinNumber">GSTIN  No.<span style="color: red;">*</span> </form:label>
		<form:input path="gstinNumber" size="28" readonly="true" class="form-control cus-control" id="gstinNumber" value="${mliNameList.gstinNumber}"/>
		<form:errors path="gstinNumber" cssClass="error" />
		  </div>
		  </div>
	 	 </div>	
	 	 
	 	 <div class="clearfix"></div>
	   <h4 class="detail_sp3">Rating Detail</h4>
	 	 
	 	 <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
	<form:label path="rating">Rating<span style="color: red;">*</span> </form:label>
		<form:input path="rating"  readonly="true" size="28" id="rating" class="form-control cus-control" value="${mliNameList.rating}" />
		<form:errors path="rating" cssClass="error" />
		  </div>
		  </div>
	 	 </div>	 
	 	 
	 	  <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
	<form:label path="ratingAgency">Rating Agency<span style="color: red;">*</span> </form:label>
		<form:input path="ratingAgency" size="28" readonly="true" class="form-control cus-control" id="ratingAgency" value="${mliNameList.ratingAgency}"/>
			<form:errors path="ratingAgency" cssClass="error" />
		  </div>
		  </div>
	 	 </div>	 
	 	 
	 	 <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
	<form:label path="ratingDate">Date of Rating<span style="color: red;">*</span> </form:label>
	<form:input path="ratingDate" class="form-control cus-control" size="28" readonly="true" id="ratingDate" value="${mliNameList.ratingDate}"/>
	<form:errors path="ratingDate" cssClass="error" />
		  </div>
		  </div>
	 	 </div>	
	 	 
	 	  <div class="col-md-3 col-sm-4 col-xs-12">
		<div class="form-group">
		<div class="col-md-12 prl-10">
	<label> Remark: </label>
	<form:textarea  path="remarks"  row="6" id="mliRemarks"  onKeyUp="enforceMaxLength(this);" class="form-control cus-control"/> 
			   	       <!--  <span id="count"></span> -->
		  </div>
		  </div>
	 	 </div>		 	 	 	 			            
	 	 
	 	 <div class="d-inlineblock">	 	 
			<input value="Approve" onclick="approveMLiDetails()" class="btn btn-reset" style="    width: 108px;" />
			<input value="Return"  onclick="return validateFormData()" class="btn btn-reset"  style="    width: 108px;" />
			<input value="Exit" onclick="exitMLIDetails()" class="btn btn-reset" style="    width: 108px;"  />		
		</div>
	 	 
		
		</form:form>
		</div>
		</div>
	
	</div>
</div>
</div>	

	<%-- <h2 align="center">MLI Registration</h2>
	<hr>
	<font color="green" size="3"><b>${message}</b></font>
	<div>
		<form:form method="POST" id="A"	>
			

				<table align="center">
					<tr>
						<td><form:label path="mliLongName">Select MLI Name :<span
									style="color: red;">*</span>
							</form:label></td>
						<td><form:select path="mliLongName" id="ViewFileList"
								name="mliLongName" onchange="onchangeApp()">
								<form:option value="" label="-------------Select Portfolio Name------------" />
								<c:forEach var="mliLongName" items="${mliNameList}">
									<form:option id="${mliLongName.longName}"
										value="${mliLongName.longName}">${mliLongName.longName}</form:option>
								</c:forEach>
							</form:select></td>
							
						<td><form:errors path="mliLongName" cssClass="error" /></td>
						<td><form:input path="" type="hidden"></form:input></td>
						
					</tr>
				</table>
				<div id="detailId">
							<table align="center" border=0 cellpadding=5 cellspacing=5 align=center>
						<tr>
							<td class="label"><form:label path="longName">Long Name <span
										style="color: red;">*</span>
								</form:label></td>
							<td class="DataElement" ><form:input path="longName" id="longName" size="28" readonly="true" value="${mliNameList.longName}"/></td>
							<td><form:errors path="longName" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>

							<td class="label" ><form:label path="shortName">Short Name <span
										style="color: red;">*</span>
								</form:label></td>
							<td class="DataElement"><form:input path="shortName" id="shortName" size="28" readonly="true" value="${mliNameList.shortName}" /></td>
							<td><form:errors path="shortName" cssClass="error" /></td>
						</tr>
						<tr>
							<td class="label" ><form:label path="companyAddress">Registered Address of the Company<span
										style="color: red;">*</span>
								</form:label></td>
							<td class="DataElement" ><form:input path="companyAddress" id="companyAddress" size="28" readonly="true" value="${mliNameList.companyAddress}"/></td>
							<td><form:errors path="companyAddress" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td class="label"><form:label path="rbiReggistrationNumber">RBI registration No <span style="color: red;">*</span>
								</form:label></td>
							<td class="DataElement"><form:input path="rbiReggistrationNumber" size="28"	maxlength="15" readonly="true" id="rbiReggistrationNumber" value="${mliNameList.rbiReggistrationNumber}"/></td>
							<td><form:errors path="rbiReggistrationNumber"
									cssClass="error" /></td>

						</tr>
						<tr>
							<td class="label"><form:label path="city">City <span style="color: red;">*</span>
								</form:label></td>
							<td class="DataElement" ><form:input path="city" id="city" size="28" readonly="true" value="${mliNameList.city}"/></td>
							<td><form:errors path="city" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td class="label" ><form:label path="companyCIN">CIN of the company <span style="color: red;">*</span>
								</form:label></td>
							<td class="DataElement"><form:input path="companyCIN" id="companyCIN" size="28" maxlength="21" readonly="true" value="${mliNameList.companyCIN}"/></td>
							<td><form:errors path="companyCIN" cssClass="error" /></td>

						</tr>
						<tr>
							<td class="label" ><form:label path="state">State <span
										style="color: red;">*</span>
								</form:label></td>
							<td class="DataElement"><form:input path="state" size="28" readonly="true" id="state" value="${mliNameList.state}"/></td>

							<td><form:errors path="state" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>

							<td class="label" ><form:label path="companyPAN">PAN of the company<span
										style="color: red;">*</span>
								</form:label></td>
							<td class="DataElement"><form:input path="companyPAN" size="28" readonly="true" id="companyPAN" value="${mliNameList.companyPAN}"/></td>
							<td><form:errors path="companyPAN" cssClass="error" /></td>
						</tr>
						<tr>
							<td class="label"><form:label path="district">District <span
										style="color: red;">*</span>
								</form:label></td>
							<td class="DataElement" ><form:input path="district" size="28" readonly="true" id="district" value="${mliNameList.district}"/></td>
							<td><form:errors path="district" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td class="label" ><form:label path="gstinNumber">GSTIN  No<span style="color: red;">*</span>
								</form:label></td>
							<td class="DataElement" ><form:input path="gstinNumber" size="28" readonly="true" id="gstinNumber" value="${mliNameList.gstinNumber}"/></td>
							<td><form:errors path="gstinNumber" cssClass="error" /></td>
						</tr>
						<tr>
							<td class="label" ><form:label path="pincode">Pincode <span style="color: red;">*</span>
								</form:label></td>
							<td class="DataElement" ><form:input path="pincode"  size="28" readonly="true" id="pincode" value="${mliNameList.pincode}"/></td>
							<td><form:errors path="pincode" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td class="label" ><form:label path="ratingAgency">Rating Agency<span style="color: red;">*</span>
								</form:label></td>
							<td class="DataElement" ><form:input path="ratingAgency" size="28" readonly="true" id="ratingAgency" value="${mliNameList.ratingAgency}"/></td>
							<td><form:errors path="ratingAgency" cssClass="error" /></td>

						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="label" ><form:label path="landlineNumber">Land line Number <span
										style="color: red;">*</span>
								</form:label></td>
							<td class="DataElement" ><form:input path="phoneCode"  readonly="true" id="phoneCode" size="3" value="${mliNameList.phone_code}" /><form:input path="landlineNumber"  readonly="true" id="landlineNumber" value="${mliNameList.landlineNumber}"/></td>
							<td><form:errors path="landlineNumber" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td class="label"><form:label path="rating">Rating <span style="color: red;">*</span></form:label></td>
							<td class="DataElement" ><form:input path="rating"  readonly="true" size="28" id="rating" value="${mliNameList.rating}" /></td>
							<td><form:errors path="rating" cssClass="error" /></td>

						</tr>
						<tr>
							<td class="label" ><form:label path="emailId">Email ID <span style="color: red;">*</span></form:label></td>
							<td class="DataElement"><form:input path="emailId" size="28" readonly="true" id="emailId" value="${mliNameList.emailId}"/></td>
							<td><form:errors path="emailId" cssClass="error" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td class="label"><form:label path="ratingDate">Date of Rating<span	style="color: red;">*</span></form:label></td>
							<td class="DataElement"><form:input path="ratingDate" size="28" readonly="true" id="ratingDate" value="${mliNameList.ratingDate}"/></td>
							<td><form:errors path="ratingDate" cssClass="error" /></td>
 						</tr>
 						
						<tr>
							<td class="label" ><form:label path="contactPerson">Contact Person <span	style="color: red;">*</span></form:label></td>
							<td class="DataElement"><form:input path="contactPerson"  size="28" readonly="true" id="contactPerson" value="${mliNameList.contactPerson}"/></td>
							<td><form:errors path="contactPerson" cssClass="error" /></td>

						</tr>
						<tr>
							<td class="label"><form:label path="mobileNUmber">Mobile No<span style="color: red;">*</span></form:label></td>
							<td class="DataElement"><form:input path="mobileNUmber" size="28" readonly="true" id="mobileNUmber" value="${mliNameList.mobileNUmber}"/></td>
							<td><form:errors path="mobileNUmber" cssClass="error" /></td>

						</tr>

						<tr>
							<td class="label"><form:label path="faxNumber">Fax Number</form:label></td>
							<td class="DataElement"><form:input path="faxCode" readonly="true" id="faxCode" value="${mliNameList.faxCode}" size="3"/><form:input path="faxNumber" readonly="true" id="faxNumber" value="${mliNameList.faxNumber}"/></td>
							<td><form:errors path="faxNumber" cssClass="error" /></td>

						</tr>
						 <tr>
			            <td  class="label">Remark:<span style="color:red">*</span></td>
			             <td class="DataElement" >&nbsp;&nbsp;&nbsp;&nbsp;<form:textarea  path="remarks"  row="6" id="mliRemarks"  onKeyUp="enforceMaxLength(this);"/> 
			   	        <span id="count"></span></td>
			       	   </tr>
						<tr id="remarks1" class="toshow" style="display: none">
					         <td class="label"><form:label path="remarks" id="REMARKS1">Remarks :</form:label></td>
					         <td class="DataElement"><form:input path="remarks" size="40"/></td>
					         <td><form:errors path="remarks" cssClass="error" /></td>
				        </tr>
				     	<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2"><input value="Approve"	class="button" onclick="approveMLiDetails()"/></td>
							<td colspan="2"><input value="Reject"  	class="button" onclick="return validateFormData()"/></td>
							<td></td>
							<td colspan="2"><input value="Exit" 	 class="button" onclick="exitMLIDetails()"/></td>
						</tr>
					</table>
        
				</div>
			
		</form:form>
	</div> --%>


</body>
<script type="text/javascript">

function approveMLiDetails()
{
	        document.getElementById('A').action = "/SV/approveMLINewRegistrationPage.html";
		    document.getElementById('A').submit();
	
	
	//alert(mliName);
   
}

/* function rejectMLIDetails()
{ var diveForReject = document.getElementById("REMARKS1");
	diveForReject.style.display = "block";
	var REMARKS = document.getElementById("REMARKS1").value;
 if(REMARKS!=''){
	document.getElementById('A').action = "";
    document.getElementById('A').submit();
} else{
	diveForReject.style.display = "block";
	alert('Please fill the Remarks');
} 
 }
 */
 
 document.getElementById("mliRemarks").disabled=true; 
 function validateFormData()
 {
     if(document.getElementById('mliRemarks').value==null || document.getElementById('mliRemarks').value=="")
     {
      alert("Please enter the Remarks!");
      document.getElementById("mliRemarks").focus();
      document.getElementById("mliRemarks").disabled=false;
      document.getElementById('approve1').style.visibility = 'hidden';
      return false;
     }
     else
    {
    	    document.getElementById('A').action = "/SV/rejectMLINewRegistrationPage.html";
    	    document.getElementById('A').submit();	 
    }
 }    
 
 var maxLength =500;
 function enforceMaxLength(obj)
 {
 	if (obj.value.length > maxLength)
 	{
 		obj.value = obj.value.substring(0, maxLength);
 	}
 }

 var text = document.getElementById('mliRemarks');
 var spanID= document.getElementById('count');
 spanID.innerHTML = maxLength;
 text.onkeyup = function ()
 {
   document.getElementById('count').innerHTML = (maxLength - this.value.length);
 };

 
function exitMLIDetails() {
	
    document.getElementById('A').action = "/SV/exitPage.html";
  //  document.getElementById('A').action = "/SV/approveRejectNewMLIDetails.html";
    document.getElementById('A').submit();
}


/* function disableUnableRemarks() {
	
	var diveForReject = document.getElementById("REMARKS1");
	diveForReject.style.display = "block";
	var REMARKS = document.getElementById("REMARKS").value;
	//alert(REMARKS);
if(REMARKS!=''){
	
	 document.getElementById("A").submit();
}else{
	diveForReject.style.display = "block";
	alert('Please fill the Remarks');
	}
} */
function myFunction(){
	alert('view');
	document.getElementById('detailId').style.display = '';
	}
function myFunction1(){
	document.getElementById('detailId').style.display = 'none';
	}
	function onchangeApp() {
		var portfolioNum = document.getElementById("mliLongName").value;
		alert(portfolioNum);
		try {
			$.ajax({
				type : "POST",
				url : "getmliDetails.html",
				data : "portfolioNum=" + portfolioNum,
				success : function(data) {

					var   longName	=	data.result.longName;
					var   shortName	=	data.result.shortName;
					var   companyAddress	=	data.result.companyAddress;
					var   city	=	data.result.city;
					var   state	=	data.result.state;
					var   district	=	data.result.district;
					var   pincode	=	data.result.pincode;
					var   landlineNumber	=	data.result.landlineNumber;
					var   emailId	=	data.result.emailId;
					var   contactPerson	=	data.result.contactPerson;
					var   mobileNUmber	=	data.result.mobileNUmber;
					var   faxNumber	=	data.result.faxNumber;
					var   rbiReggistrationNumber	=	data.result.rbiReggistrationNumber;
					var   companyCIN	=	data.result.companyCIN;
					var   companyPAN	=	data.result.companyPAN;
					var   gstinNumber	=	data.result.gstinNumber;
					var   ratingAgency	=	data.result.ratingAgency;
					var   rating	=	data.result.rating;
					var   ratingDate	=	data.result.ratingDate;
					var   phoneCode	=	data.result.phone_code;
					var   faxCode	=	data.result.faxCode;
										
					$('#phoneCode').val(phoneCode);
					$('#faxCode').val(faxCode);
					$('#longName').val(longName);
					$('#shortName').val(shortName);
					$('#companyAddress').val(companyAddress);
					$('#city').val(city);
					$('#state').val(state);
					$('#district').val(district);
					$('#pincode').val(pincode);
					$('#landlineNumber').val(landlineNumber);
					$('#emailId').val(emailId);
					$('#contactPerson').val(contactPerson);
					$('#mobileNUmber').val(mobileNUmber);
					$('#rbiReggistrationNumber').val(rbiReggistrationNumber);
					$('#companyCIN').val(companyCIN);
					$('#companyPAN').val(companyPAN);
					$('#gstinNumber').val(gstinNumber);
					$('#ratingAgency').val(ratingAgency);
					$('#rating').val(rating);
					$('#ratingDate').val(ratingDate);
					$('#faxNumber').val(faxNumber);
					
					//myFunction();			

				},
				error : function(e) {
					alert('Server Error : ' + e);
				}
			});

		} catch (err) {
			alert('Error is : ' + err);
		}

	}
</script>
</html>