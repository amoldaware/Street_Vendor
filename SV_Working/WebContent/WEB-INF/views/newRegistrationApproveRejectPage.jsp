<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

<script  type="text/javascript">
$(document).ready(function()
{
	 checkFilled();
});


function checkFilled() 
{
	    var longName1 = document.getElementById("editLongName").value;
	    var longName2 = document.getElementById("longName").value;
	    
	    if (longName1!=longName2)
   	    { 
	    	longName.style.backgroundColor = "#FFDAB9";
    	}
	    
	    var shortName1 = document.getElementById("editShortName").value;
	    var shortName2 = document.getElementById("shortName").value;
	    
	    if (shortName1!=shortName2)
   	    { 
	    	shortName.style.backgroundColor = "#FFDAB9";
    	}
	    
	    var companyAddress1 = document.getElementById("editCompanyAddress").value;
	    var companyAddress2 = document.getElementById("companyAddress").value;
	    
	    if (companyAddress1!=companyAddress2)
   	    { 
	    	companyAddress.style.backgroundColor = "#FFDAB9";
    	}
	    
	    var rbiReggistrationNumber1 = document.getElementById("editRBIReggistrationNumber").value;
	    var rbiReggistrationNumber2 = document.getElementById("rbiReggistrationNumber").value;
	    
	    if (rbiReggistrationNumber1!=rbiReggistrationNumber2)
   	    { 
	    	rbiReggistrationNumber.style.backgroundColor = "#FFDAB9";
    	}
	    
	    var city1 = document.getElementById("editCity").value;
	    var city2 = document.getElementById("city").value;
	    
	    if (city1!=city2)
   	    { 
	    	city.style.backgroundColor = "#FFDAB9";
    	}
	    
	    var companyCIN1 = document.getElementById("editCompanyCIN").value;
	    var companyCIN2 = document.getElementById("companyCIN").value;
	    
	    if (companyCIN1!=companyCIN2)
   	    { 
	    	companyCIN.style.backgroundColor = "#FFDAB9";
    	}
	    
	    var state1 = document.getElementById("editState").value;
	    var state2 = document.getElementById("state").value;

	    if (state1!=state2)
   	    { 
	    	state.style.backgroundColor = "#FFDAB9";
    	}
	    
	    var companyPAN1 = document.getElementById("editCompanyPAN").value;
	    var companyPAN2 = document.getElementById("companyPAN").value;

	    if (companyPAN1!=companyPAN2)
   	    { 
	    	companyPAN.style.backgroundColor = "#FFDAB9";
    	}
	    
	    var district1 = document.getElementById("editDistrict").value;
	    var district2 = document.getElementById("district").value;

	    if (district1!=district2)
   	    { 
	    	district.style.backgroundColor = "#FFDAB9";
    	}
	    
	    var pincode1 = document.getElementById("editPincode").value;
	    var pincode2 = document.getElementById("pincode").value;

	    if (pincode1!=pincode2)
   	    { 
	    	pincode.style.backgroundColor = "#FFDAB9";
    	}
	    
	    var gstinNumber1 = document.getElementById("editGstinNumber").value;
	    var gstinNumber2 = document.getElementById("gstinNumber").value;

	    if (gstinNumber1!=gstinNumber2)
   	    { 
	    	gstinNumber.style.backgroundColor = "#FFDAB9";
    	}
	    
	    var landlineNumber1 = document.getElementById("editLandlineNumber").value;
	    var landlineNumber2 = document.getElementById("landlineNumber").value;

	    if (landlineNumber1!=landlineNumber2)
   	    { 
	    	landlineNumber.style.backgroundColor = "#FFDAB9";
    	}
	    ratingAgency
	    var ratingAgency1 = document.getElementById("editRatingAgency").value;
	    var ratingAgency2 = document.getElementById("ratingAgency").value;

	    if (ratingAgency1!=ratingAgency2)
   	    { 
	    	ratingAgency.style.backgroundColor = "#FFDAB9";
    	}
	    var  emailId1= document.getElementById("editEmailId").value;
	    var  emailId2= document.getElementById("emailId").value;

	    if (emailId1!=emailId2)
   	    { 
	    	emailId2.style.backgroundColor = "#FFDAB9";
    	}
	    
	    var rating1 = document.getElementById("editRating").value;
	    var rating2= document.getElementById("rating").value;
	    
	    if (rating1!=rating2)
   	    { 
	    	rating.style.backgroundColor = "#FFDAB9";
    	}
	    
	    var contactPerson1 = document.getElementById("editContactPerson").value;
	    var contactPerson2= document.getElementById("contactPerson").value;

	    if (contactPerson1!=contactPerson2)
   	    { 
	    	contactPerson.style.backgroundColor = "#FFDAB9";
    	}
	    
	    var ratingDate1 = document.getElementById("ratingDateTwo").value;
	    var ratingDate2= document.getElementById("ratingDateOne").value;

	    if (ratingDate1!=ratingDate2)
   	    { 
	    	ratingDateOne.style.backgroundColor = "#FFDAB9";
    	}
	    
	    var mobileNUmber1 = document.getElementById("editMobileNUmber").value;
	    var mobileNUmber2= document.getElementById("mobileNUmber").value;

	    if (mobileNUmber1!=mobileNUmber2)
   	    { 
	    	mobileNUmber.style.backgroundColor = "#FFDAB9";
    	}
	    
	    var editFaxNumber1 = document.getElementById("editFaxNumber").value;
	    var editFaxNumber2= document.getElementById("faxNumber").value;

	    if (editFaxNumber1!=editFaxNumber2)
   	    { 
	    	faxNumber.style.backgroundColor = "#FFDAB9";
    	}
	    
	    
}    

   
</script>

<style type="text/css">
      .form-group{margin-bottom:10px;}
      .form-control{	height:30px !important;	}
      
      .line	{	position:relative; width:100%;	}
      .line:after{	content:"";	position:absolute;	width:2px; height:400px; backgound-color:gray;		}
      </style>
</head>
<!-- <body onload="myFunction1()" bgcolor="#E0FFFF"> -->
<!-- <body onload="myFunction()" bgcolor="#E0FFFF"> -->
<body bgcolor="#E0FFFF">
<div class="main-section">
<div class="container-fluid">
<!-- <div class="row"> -->
	<div>	
		<nav aria-label="breadcrumb">
  <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/approveRejectNewMLIDetails.html">MLI Registration Approval</a></li>  
    <li class="breadcrumb-item active" aria-current="page">New MLI Registration</li>
  </ol>
</nav>
	<div class="frm-section">
		<div class="col-md-12" >
		<h2 align="center" class="heading">New MLI Registration</h2>
	<h5 class="notification-message"><strong>${message}</strong></h5>
		<form:form method="GET" id="A" class="form-horizontal line">
		<div class="col-md-6"> 
		   
			<div class="col-md-12 prl-5">
			 <div class="form-group mb-0">
			    <label class="control-label col-sm-4 prl-10"></label>
			    <div class="col-sm-4 prl-10">
					<h5 style="    color: #ffffff;    background-color: #a641a9 !important;    text-align: center;    margin: 0 0 10px 0;
    					padding: 10px 0;"><strong>Old Data</strong></h5>			    				    	    
			    </div>			    			   
			    <div class="col-sm-4 prl-10">
			     <h5 style="    color: #ffffff;    background-color: #a641a9 !important;    text-align: center;    margin: 0 0 10px 0;
    					padding: 10px 0;"><strong>Updated Data</strong></h5>	
			    </div>
			  </div>
			  
			  <div class="form-group">
			  <form:label path="longName" class="control-label col-sm-4 prl-10">Long Name <span style="color: red;">*</span> </form:label>			    
			    <div class="col-sm-4  prl-10">
			    <form:input path="editLongName" id="editLongName"
					size="28"  readonly="true" value="${mliDetails.editLongName}" class="form-control" />	
					<form:input path="" size="28" type="hidden" />
					<form:input path="" size="28" type="hidden" />		    	 			    	   
			    </div>			    			   
			    <div class="col-sm-4  prl-10">
			    <form:input path="longName" id="longName" size="28"	class="form-control"
									readonly="true" value="${mliDetails.longName}" />			     
			    </div>
			  </div>
			  
			   <div class="form-group">
			   <form:label path="shortName" class="control-label col-sm-4 prl-10">Short Name <span style="color: red;">*</span>
								</form:label>			  			   
			    <div class="col-sm-4  prl-10">
			    <form:input path="editShortName" id="editShortName"
									size="28" disabled="true" value="${mliDetails.editShortName}" class="form-control" />
				<form:input path="" size="28" type="hidden" />
				<form:input path="" size="28" type="hidden" />			     	 			    	  
			    </div>			    			   
			    <div class="col-sm-4  prl-10">
			    <form:input path="shortName" id="shortName" size="28" class="form-control" 
									disabled="true" value="${mliDetails.editShortName}" />		     
			    </div>
			  </div>
			  
			   <div class="form-group">
			   <form:label path="companyAddress"  class="control-label col-sm-4 prl-10">Registered Address of the Company<span
										style="color: red;">*</span>
								</form:label>			   			  			  
			    <div class="col-sm-4  prl-10">
			    <form:input path="editCompanyAddress"	 class="form-control"
									id="editCompanyAddress" size="28" disabled="true"
									value="${mliDetails.editCompanyAddress}" />
						<form:input path="" size="28" type="hidden" />
							<form:input path="" size="28" type="hidden" />						  		     	 			    	 
			    </div>			    			   
			    <div class="col-sm-4  prl-10">
			    <form:input path="companyAddress" id="companyAddress" class="form-control" 
									size="28" disabled="true" value="${mliDetails.companyAddress}" />												  		     
			    </div>
			  </div>
			  
			   <div class="form-group">
			   <form:label path="rbiReggistrationNumber" class="control-label col-sm-4 prl-10">RBI registration No <span
										style="color: red;">*</span>
								</form:label>		
											   			  			 
			    <div class="col-sm-4 prl-10">
			    <form:input path="editRBIReggistrationNumber" size="28"	class="form-control"
									maxlength="15" disabled="true" id="editRBIReggistrationNumber"
									value="${mliDetails.editRBIReggistrationNumber}" />
						<form:input path="" size="28" type="hidden" />
						<form:input path="" size="28" type="hidden" />
								  		     	 			    	 
			    </div>			    			   
			    <div class="col-sm-4  prl-10">
			    <form:input path="rbiReggistrationNumber" size="28"	class="form-control"
									maxlength="15" disabled="true" id="rbiReggistrationNumber"
									value="${mliDetails.rbiReggistrationNumber}" />			  											  		     
			    </div>
			  </div>
			  
			   <div class="form-group">
			   
			  <form:label path="state" class="control-label col-sm-4 prl-10">State <span
										style="color: red;">*</span>
								</form:label>
									   			  			 
			    <div class="col-sm-4 prl-10">			    
					<form:input path="editState" disabled="true"	class="form-control"
							id="editState" value="${mliDetails.editState}" size="28" />
				<form:input path="" size="28" type="hidden" />
				<form:input path="" size="28" type="hidden" />
																
			    </div>			    			   
			    <div class="col-sm-4  prl-10">
			    <form:input path="state" disabled="true" id="state"	class="form-control"
						value="${mliDetails.state}" size="28"/>						 			  											  		   
			    </div>
			  </div>
			  
			     <div class="form-group">
			   <form:label path="district"	class="control-label col-sm-4 prl-10">District <span
						style="color: red;">*</span>
				</form:label>
	   			  			 
			    <div class="col-sm-4 prl-10">	
			    <form:input path="editDistrict" disabled="true"	class="form-control"
						id="editDistrict" value="${mliDetails.editDistrict}" size="28"/>
					<form:input path="" size="28" type="hidden" />
					<form:input path="" size="28" type="hidden" />
																
			    </div>			    			   
			    <div class="col-sm-4  prl-10">
			    <form:input path="district" disabled="true" class="form-control"
							id="district" value="${mliDetails.district}" size="28" />						 			  											  		   
			    </div>
			  </div>
			  
			   <div class="form-group">
			   <form:label path="city" class="control-label col-sm-4 prl-10">City <span
										style="color: red;">*</span>
								</form:label>				
											   			  			 
			    <div class="col-sm-4 prl-10">
			   <form:input path="editCity" id="editCity" size="28"	class="form-control"
									disabled="true" value="${mliDetails.editCity}" />
				<form:input path="" size="28" type="hidden" />
					<form:input path="" size="28" type="hidden" />													
			    </div>			    			   
			    <div class="col-sm-4  prl-10">			    						
				<form:input path="city" id="city" size="28"	class="form-control"
									disabled="true" value="${mliDetails.city}" />			  											  		    
			    </div>
			  </div>
			  
			   <div class="form-group">
			   <form:label path="companyCIN" class="control-label col-sm-4 prl-10">CIN of the company <span
										style="color: red;">*</span>
								</form:label>			  
											   			  			 
			    <div class="col-sm-4 prl-10">			    
				<form:input path="editCompanyCIN" id="editCompanyCIN"	class="form-control"
									size="28" maxlength="21" disabled="true"
									value="${mliDetails.editCompanyCIN}" />
					<form:input path="" size="28" type="hidden" />
					<form:input path="" size="28" type="hidden" />
																
			    </div>			    			   
			    <div class="col-sm-4  prl-10">
			    <form:input path="companyCIN" id="companyCIN" size="28"	class="form-control"
									maxlength="21" disabled="true" value="${mliDetails.companyCIN}" />			 			  											  		    
			    </div>
			  </div>
			  
			   <div class="form-group">			   
			 <form:label path="companyPAN" class="control-label col-sm-4 prl-10">PAN of the company<span
										style="color: red;">*</span>
								</form:label>
 
			    <div class="col-sm-4 prl-10">	
			   <form:input path="editCompanyPAN" disabled="true" class="form-control"
									id="editCompanyPAN" value="${mliDetails.editCompanyPAN}" size="28"/>
							<form:input path="" size="28" type="hidden" />
							<form:input path="" size="28" type="hidden" />
																
			    </div>			    			   
			    <div class="col-sm-4  prl-10">
			    	<form:input path="companyPAN" disabled="true" class="form-control"
									id="companyPAN" value="${mliDetails.companyPAN}"  size="28"/>					 			  											  		   
			    </div>
			  </div>
			  
			    <div class="form-group">				    
			    <form:label path="pincode"  class="control-label col-sm-4 prl-10">Pincode <span
										style="color: red;">*</span>
								</form:label>								
			
			    <div class="col-sm-4 prl-10">	
			  <form:input path="editPincode" disabled="true"	 class="form-control"
						id="editPincode" value="${mliDetails.editPincode}" size="28"/>
				<form:input path="" size="28" type="hidden" />
				<form:input path="" size="28" type="hidden" />
																
			    </div>			    			   
			    <div class="col-sm-4  prl-10">
			    <form:input path="pincode" disabled="true" id="pincode"  class="form-control"
						value="${mliDetails.pincode}" size="28"/>
										 			  											  		  
			    </div>
			  </div>
			  
			  <div class="form-group">			  
			  <form:label path="gstinNumber" class="control-label col-sm-4 prl-10">GSTIN  No<span
										style="color: red;">*</span>
								</form:label>																	
			    <div class="col-sm-4 prl-10">	
			 <form:input path="editGstinNumber" disabled="true" class="form-control"
						id="editGstinNumber" value="${mliDetails.editGstinNumber}" size="28"/>
				<form:input path="" size="28" type="hidden" />
				<form:input path="" size="28" type="hidden" />
																
			    </div>			    			   
			    <div class="col-sm-4  prl-10">			 
				<form:input path="gstinNumber" disabled="true" class="form-control"
						id="gstinNumber" value="${mliDetails.gstinNumber}" size="28"/>	
										 			  											  		  
			    </div>
			  </div>
			
		 	 </div>	
		 	 </div>	<!--  col-6 -->
		 <div class="col-md-6"> 
		   
		<div class="col-md-12 prl-5">
			 <div class="form-group mb-0">
			    <label class="control-label col-sm-4 prl-10"></label>
			    <div class="col-sm-4 prl-10">
					<h5 style="    color: #ffffff;    background-color: #a641a9 !important;    text-align: center;    margin: 0 0 10px 0;
	   					padding: 10px 0;"><strong>Old Data</strong></h5>			    				    	    
			    </div>			    			   
			    <div class="col-sm-4 prl-10">
			     <h5 style="    color: #ffffff;    background-color: #a641a9 !important;    text-align: center;    margin: 0 0 10px 0;
	   					padding: 10px 0;"><strong>Updated Data</strong></h5>	
			    </div>
			  </div>
				  
	  <div class="form-group">			 				  
		  <form:label path="landlineNumber"	 class="control-label col-sm-4 prl-10">Land line Number <span
								style="color: red;">*</span>
						</form:label>								
									 																				
			    <div class="col-sm-4 prl-10">	
			<form:input path="editLandlineNumber" disabled="true"	class="form-control"
						id="editLandlineNumber"
						value="${mliDetails.editLandlineNumber}" size="28" />
				<form:input path="" size="28" type="hidden" />
				<form:input path="" size="28" type="hidden" />
																
			    </div>			    			   
			    <div class="col-sm-4  prl-10">			 
				<form:input path="landlineNumber" disabled="true"	class="form-control"
						id="landlineNumber" value="${mliDetails.landlineNumber}" size="28"/>	
										 			  											  		  
			    </div>
			  </div>
			  
			  <div class="form-group">						  
			  <form:label path="ratingAgency" class="control-label col-sm-4 prl-10">Rating Agency<span
										style="color: red;">*</span>
					</form:label>			
									 																				
			    <div class="col-sm-4 prl-10">	
					    <form:input path="editRatingAgency" disabled="true"	class="form-control"
							id="editRatingAgency" value="${mliDetails.editRatingAgency}" size="28"/>
					<form:input path="" size="28" type="hidden" />
					<form:input path="" size="28" type="hidden" />															
			    </div>			    			   
			    <div class="col-sm-4  prl-10">			 			
			<form:input path="ratingAgency" disabled="true"	class="form-control"
					id="ratingAgency" value="${mliDetails.ratingAgency}" size="28"/>
										 			  											  		  
			    </div>
			  </div>
			  
			 <div class="form-group">					  
			  <form:label path="emailId"  class="control-label col-sm-4 prl-10">Email ID <span
					style="color: red;">*</span>
				</form:label>			
			  					 																				
			    <div class="col-sm-4 prl-10">	
				 	<form:input path="editEmailId" size="28" disabled="true" class="form-control"
							id="editEmailId" value="${mliDetails.editEmailId}" />
					<form:input path="" size="28" type="hidden" />
					<form:input path="" size="28" type="hidden" />													
			    </div>			    			   
			    <div class="col-sm-4  prl-10">			 					
				<form:input path="emailId" size="28" disabled="true" class="form-control"
						id="emailId" value="${mliDetails.emailId}" />
										 			  											  		  
			    </div>
			  </div>
			  
			  <div class="form-group">				  
			  <form:label path="rating"  class="control-label col-sm-4 prl-10">Rating <span
										style="color: red;">*</span>
								</form:label>																
			  					 																				
			    <div class="col-sm-4 prl-10">	
				 	<form:input path="editRating" disabled="true" class="form-control"
						id="editRating" value="${mliDetails.editRating}" size="28"/>						
					<form:input path="" size="28" type="hidden" />
					<form:input path="" size="28" type="hidden" />												
			    </div>			    			   
			    <div class="col-sm-4  prl-10">			 					
				<form:input path="rating" disabled="true" id="rating" class="form-control"
						value="${mliDetails.rating}" size="28"/>		
										 			  											  		  
			    </div>
			  </div>
			  
			   <div class="form-group">					   
			   <form:label path="contactPerson"  class="control-label col-sm-4 prl-10">Contact Person <span
										style="color: red;">*</span>
								</form:label>													
			  					 																				
			    <div class="col-sm-4 prl-10">	
				 	<form:input path="editContactPerson" disabled="true" class="form-control"
									id="editContactPerson" value="${mliDetails.editContactPerson}" size="28"/>
						<form:input path="" size="28" type="hidden" />
						<form:input path="" size="28" type="hidden" />										
			    </div>			    			   
			    <div class="col-sm-4  prl-10">			 					
				<form:input path="contactPerson" disabled="true" class="form-control"
					id="contactPerson" value="${mliDetails.contactPerson}" size="28"/>	
										 			  											  		  
			    </div>
			  </div>
			  
			   <div class="form-group">					   
			   <form:label path="ratingDate"  class="control-label col-sm-4 prl-10">Date of Rating<span
										style="color: red;">*</span>
								</form:label>																			 								
			  					 																				
			    <div class="col-sm-4 prl-10">	
				 	<form:input path="ratingDateTwo" disabled="true" class="form-control"
						id="ratingDateTwo" value="${mliDetails.ratingDateTwo}" size="28" />
					<form:input path="" size="28" type="hidden" />
					<form:input path="" size="28" type="hidden" />										
			    </div>			    			   
			    <div class="col-sm-4  prl-10">			 					
				<form:input path="ratingDateOne" disabled="true" class="form-control"
						id="ratingDateOne" value="${mliDetails.ratingDateOne}" size="28"/>	
										 			  											  		  
			    </div>
			  </div>
			  
			   <div class="form-group">				   
			   <form:label path="mobileNUmber" class="control-label col-sm-4 prl-10">Mobile No<span
										style="color: red;">*</span>
								</form:label>
					 																				
			    <div class="col-sm-4 prl-10">	
				 	<form:input path="editMobileNUmber" disabled="true" class="form-control"
						id="editMobileNUmber" value="${mliDetails.editMobileNUmber}" size="28" />
						<form:input path="" size="28" type="hidden" />
						<form:input path="" size="28" type="hidden" />
									
			    </div>			    			   
			    <div class="col-sm-4  prl-10">			 					
				<form:input path="mobileNUmber" disabled="true" class="form-control"
									id="mobileNUmber" value="${mliDetails.mobileNUmber}" size="28"/>										 			  											  		 
			    </div>
			  </div>
			  
		<%-- 	   <div class="form-group">					   
			   <form:label path="faxNumber"  class="control-label col-sm-4 prl-10">Fax Number </form:label>																						
			    <div class="col-sm-4 prl-10">	
				 	<form:input path="editFaxNumber" disabled="true"
					id="editFaxNumber" value="${mliDetails.editFaxNumber}" size="28" class="form-control" />
				<form:input path="" size="28" type="hidden" />
				<form:input path="" size="28" type="hidden" />
									
			    </div>			    			   
			    <div class="col-sm-4  prl-10">			 					
				<form:input path="faxNumber" disabled="true" class="form-control"
							id="faxNumber" value="${mliDetails.faxNumber}" size="28"/>									 			  											  		 
			    </div>
			  </div>
			   --%>
			   <div class="form-group" id="remarks1" class="toshow" style="display: none">			   			   
				<form:label path="remarks" id="remarksLable"  class="control-label col-sm-4 prl-10">Remarks :</form:label>																																
				    <div class="col-sm-4 prl-10">	
					 	<form:input path="remarks" size="40" class="form-control"/>
				<form:errors path="remarks" cssClass="error" />	
										
				    </div>			    			   
				    <div class="col-md-4  prl-10">			 																		 			  											  		
				    </div>
				  </div>
			  
			  <div class="col-sm-8 col-sm-offset-4">
				  <input type="submit" value="Approve"
					class="btn btn-reset" name="approve" onclick="approveMLIDetails()"/>
					<input value="Return" name="reject" style="width:100px;"
					class="btn btn-reset" onclick="rejectMLIDetails()" />				
					<input type="submit" value="Close"
						name="close" class="btn btn-reset" onclick="exitMLIDetails()"/>
			  </div>
			  
				  
				  </div>
		  </div>	<!--  col-6 -->
		
		
		
		</form:form>
		
		
		</div>
	</div>
		
	
	</div>
</div>
</div>	


	<%-- <h2 align="center">Approve MLI Details</h2>
	<font color="green" size="3"><b>${message}</b></font>
	<div>
		<form:form method="GET" id="A">
			<div
				STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;">

				<table align="center">

				</table>
				<div id="detailId">
					<table align="center" style="color: #5c4324;">
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>

						<tr>
							<th></th>
							<th>Old Data</th>
							<th></th>
							<th></th>
							<th>Updated Data</th>
						</tr>

						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><form:label path="longName">Long Name <span style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editLongName" id="editLongName"
									size="28"  readonly="true" value="${mliDetails.editLongName}" /></td>

							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>

							<td><form:input path="longName" id="longName" size="28"
									readonly="true" value="${mliDetails.longName}" /></td>
						</tr>
						<tr>
							<td><form:label path="shortName">Short Name <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editShortName" id="editShortName"
									size="28" disabled="true" value="${mliDetails.editShortName}" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>

							<td><form:input path="shortName" id="shortName" size="28"
									disabled="true" value="${mliDetails.editShortName}" /></td>
						</tr>
						<tr>
							<td><form:label path="companyAddress">Registered Address of the Company<span
										style="color: red;">*</span>
								</form:label></td>

							<td><form:input path="editCompanyAddress"
									id="editCompanyAddress" size="28" disabled="true"
									value="${mliDetails.editCompanyAddress}" /></td>

							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="companyAddress" id="companyAddress"
									size="28" disabled="true" value="${mliDetails.companyAddress}" /></td>

						</tr>
						<tr>
							<td><form:label path="rbiReggistrationNumber">RBI registration No <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editRBIReggistrationNumber" size="28"
									maxlength="15" disabled="true" id="editRBIReggistrationNumber"
									value="${mliDetails.editRBIReggistrationNumber}" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>

							<td><form:input path="rbiReggistrationNumber" size="28"
									maxlength="15" disabled="true" id="rbiReggistrationNumber"
									value="${mliDetails.rbiReggistrationNumber}" /></td>
						</tr>
						<tr>
							<td><form:label path="city">City <span
										style="color: red;">*</span>
								</form:label></td>

							<td><form:input path="editCity" id="editCity" size="28"
									disabled="true" value="${mliDetails.editCity}" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="city" id="city" size="28"
									disabled="true" value="${mliDetails.city}" /></td>

						</tr>
						<tr>
							<td><form:label path="companyCIN">CIN of the company <span
										style="color: red;">*</span>
								</form:label></td>

							<td><form:input path="editCompanyCIN" id="editCompanyCIN"
									size="28" maxlength="21" disabled="true"
									value="${mliDetails.editCompanyCIN}" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="companyCIN" id="companyCIN" size="28"
									maxlength="21" disabled="true" value="${mliDetails.companyCIN}" /></td>

						</tr>
						<tr>
							<td><form:label path="state">State <span
										style="color: red;">*</span>
								</form:label></td>

							<td><form:input path="editState" disabled="true"
									id="editState" value="${mliDetails.editState}" size="28" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="state" disabled="true" id="state"
									value="${mliDetails.state}" size="28"/></td>

						</tr>
						<tr>
							<td><form:label path="companyPAN">PAN of the company<span
										style="color: red;">*</span>
								</form:label></td>

							<td><form:input path="editCompanyPAN" disabled="true"
									id="editCompanyPAN" value="${mliDetails.editCompanyPAN}" size="28"/></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="companyPAN" disabled="true"
									id="companyPAN" value="${mliDetails.companyPAN}"  size="28"/></td>
						</tr>
						<tr>
							<td><form:label path="district">District <span
										style="color: red;">*</span>
								</form:label></td>

							<td><form:input path="editDistrict" disabled="true"
									id="editDistrict" value="${mliDetails.editDistrict}" size="28"/></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="district" disabled="true"
									id="district" value="${mliDetails.district}" size="28" /></td>
						</tr>
						<tr></tr>
						<tr>
							<td><form:label path="pincode">Pincode <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editPincode" disabled="true"
									id="editPincode" value="${mliDetails.editPincode}" size="28"/></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="pincode" disabled="true" id="pincode"
									value="${mliDetails.pincode}" size="28"/></td>

						</tr>
						<tr>
							<td><form:label path="gstinNumber">GSTIN  No<span
										style="color: red;">*</span>
								</form:label></td>

							<td><form:input path="editGstinNumber" disabled="true"
									id="editGstinNumber" value="${mliDetails.editGstinNumber}" size="28"/></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="gstinNumber" disabled="true"
									id="gstinNumber" value="${mliDetails.gstinNumber}" size="28"/></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><form:label path="landlineNumber">Land line Number <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editLandlineNumber" disabled="true"
									id="editLandlineNumber"
									value="${mliDetails.editLandlineNumber}" size="28" /></td>

							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="landlineNumber" disabled="true"
									id="landlineNumber" value="${mliDetails.landlineNumber}" size="28"/></td>
						</tr>
						<tr>
							<td><form:label path="ratingAgency">Rating Agency<span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editRatingAgency" disabled="true"
									id="editRatingAgency" value="${mliDetails.editRatingAgency}" size="28"/></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>

							<td><form:input path="ratingAgency" disabled="true"
									id="ratingAgency" value="${mliDetails.ratingAgency}" size="28"/></td>

						</tr>
						<tr>
							<td><form:label path="emailId">Email ID <span
										style="color: red;">*</span>
								</form:label></td>

							<td><form:input path="editEmailId" size="28" disabled="true"
									id="editEmailId" value="${mliDetails.editEmailId}" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="emailId" size="28" disabled="true"
									id="emailId" value="${mliDetails.emailId}" /></td>
						</tr>
						<tr>
							<td><form:label path="rating">Rating <span
										style="color: red;">*</span>
								</form:label></td>

							<td><form:input path="editRating" disabled="true"
									id="editRating" value="${mliDetails.editRating}" size="28"/></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="rating" disabled="true" id="rating"
									value="${mliDetails.rating}" size="28"/></td>

						</tr>
						<tr>
							<td><form:label path="contactPerson">Contact Person <span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editContactPerson" disabled="true"
									id="editContactPerson" value="${mliDetails.editContactPerson}" size="28"/></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="contactPerson" disabled="true"
									id="contactPerson" value="${mliDetails.contactPerson}" size="28"/></td>
						</tr>
						<tr>
							<td><form:label path="ratingDate">Date of Rating<span
										style="color: red;">*</span>
								</form:label></td>

							<td><form:input path="editRatingDate" disabled="true"
									id="editRatingDate" value="${mliDetails.editRatingDate}" size="28" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="ratingDate" disabled="true"
									id="ratingDate" value="${mliDetails.ratingDate}" size="28"/></td>
						</tr>
						<tr>
							<td><form:label path="mobileNUmber">Mobile No<span
										style="color: red;">*</span>
								</form:label></td>
							<td><form:input path="editMobileNUmber" disabled="true"
									id="editMobileNUmber" value="${mliDetails.editMobileNUmber}" size="28" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>

							<td><form:input path="mobileNUmber" disabled="true"
									id="mobileNUmber" value="${mliDetails.mobileNUmber}" size="28"/></td>

						</tr>
						<tr>
							<td><form:label path="faxNumber">Fax Number
					</form:label></td>
							<td><form:input path="editFaxNumber" disabled="true"
									id="editFaxNumber" value="${mliDetails.editFaxNumber}" size="28" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="" size="28" type="hidden" /></td>
							<td><form:input path="faxNumber" disabled="true"
									id="faxNumber" value="${mliDetails.faxNumber}" size="28"/></td>

						</tr>
						<tr></tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr id="remarks1" class="toshow" style="display: none">
							<td><form:label path="remarks" id="remarksLable">Remarks :</form:label></td>
							<td><form:input path="remarks" size="40" /></td>
							<td><form:errors path="remarks" cssClass="error" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" value="Approve"
								class="button" name="approve" onclick="approveMLIDetails()"/></td>
							<td colspan="2"><input value="Reject" name="reject"
								class="button" onclick="rejectMLIDetails()" /></td>
							<td></td>
							<td colspan="2"><input type="submit" value="Close"
								name="close" class="button" onclick="exitMLIDetails()"/></td>
						</tr>



					</table>

				</div>


			</div>
		</form:form>
	</div> --%>



</body>
<script type="text/javascript">
	function approveMLIDetails() {
		
		var longName="${mliList.mliLongName}";
		document.getElementById('A').action = "/SV/approveMLIDetailsByChecker.html";
		document.getElementById('A').submit();
	}
	function rejectMLIDetails() {
		
		var REMARKS = document.getElementById("remarks").value;
		
		if (REMARKS != '') {
			
			document.getElementById('A').action = "/SV/rejectMLIDetailsByChecker.html";
			document.getElementById('A').submit();
		} else {
			document.getElementById('remarks1').style.display = '';
			alert('Please fill the Remarks');
			document.getElementById("remarks").focus();
		} 
		
	}
	function exitMLIDetails() {
		
		document.getElementById('A').action = "/SV/approveRejectNewMLIDetails.html";
		document.getElementById('A').submit();
	}
	function disableUnableRemarks() {
		
		var diveForReject = document.getElementById("REMARKS1");
		diveForReject.style.display = "block";
		var REMARKS = document.getElementById("REMARKS").value;
		alert(REMARKS);
		if (REMARKS != '') {

			document.getElementById("A").submit();
		} else {
			diveForReject.style.display = "block";
			alert('Please fill the Remarks');
		}
	}
	function viewFunction() {

		//document.getElementById("editDetails").disabled = true;
		document.getElementById('editableDetailId').style.display = 'none';
		document.getElementById('detailId').style.display = '';
	}
	function editFunction() {
		
		onchangeApp();
		document.getElementById("viewDetails").disabled = true;
		document.getElementById('detailId').style.display = 'none';
		document.getElementById('editableDetailId').style.display = '';
	}
	function myFunction() {
		document.getElementById('detailId').style.display = 'none';
		document.getElementById('editableDetailId').style.display = 'none';
	}
	function onchangeApp() {
		var mliLongName = document.getElementById("mliLongName").value;
		//alert(portfolioNum);
		try {
			$
					.ajax({
						type : "POST",
						url : "getmliDetailsForApprovalRejection.html",
						data : "mliLongName=" + mliLongName,
						success : function(data) {

							var longName = data.result.longName;
							var shortName = data.result.shortName;
							var companyAddress = data.result.companyAddress;
							var city = data.result.city;
							var state = data.result.state;
							var district = data.result.district;
							var pincode = data.result.pincode;
							var landlineNumber = data.result.landlineNumber;
							var emailId = data.result.emailId;
							var contactPerson = data.result.contactPerson;
							var mobileNUmber = data.result.mobileNUmber;
							var faxNumber = data.result.faxNumber;
							var rbiReggistrationNumber = data.result.rbiReggistrationNumber;
							var companyCIN = data.result.companyCIN;
							var companyPAN = data.result.companyPAN;
							var gstinNumber = data.result.gstinNumber;
							var ratingAgency = data.result.ratingAgency;
							var rating = data.result.rating;
							var ratingDate = data.result.ratingDate;

							var editLongName = data.result.editLongName;
							var editShortName = data.result.editShortName;
							var editCompanyAddress = data.result.editCompanyAddress;
							var editCity = data.result.editCity;
							var editState = data.result.editState;
							var editDistrict = data.result.editDistrict;
							var editPincode = data.result.editPincode;
							var editLandlineNumber = data.result.editLandlineNumber;
							var editEmailId = data.result.editEmailId;
							var editContactPerson = data.result.editContactPerson;
							var editMobileNUmber = data.result.editMobileNUmber;
							var editRBIReggistrationNumber = data.result.editRBIReggistrationNumber;
							var editCompanyCIN = data.result.editCompanyCIN;
							var editCompanyPAN = data.result.editCompanyPAN;
							var editGstinNumber = data.result.editGstinNumber;
							var editRatingAgency = data.result.editRatingAgency;
							var editRating = data.result.editRating;
							var editRatingDate = data.result.editRatingDate;
							var editFaxNumber = data.result.editFaxNumber;

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
							$('#rbiReggistrationNumber').val(
									rbiReggistrationNumber);
							$('#companyCIN').val(companyCIN);
							$('#companyPAN').val(companyPAN);
							$('#gstinNumber').val(gstinNumber);
							$('#ratingAgency').val(ratingAgency);
							$('#rating').val(rating);
							$('#ratingDate').val(ratingDate);
							$('#faxNumber').val(faxNumber);

							$('#editLongName').val(editLongName);
							$('#editShortName').val(editShortName);
							$('#editCompanyAddress').val(editCompanyAddress);
							$('#editCity').val(editCity);
							$('#editState').val(editState);
							$('#editDistrict').val(editDistrict);
							$('#editPincode').val(editPincode);
							$('#editLandlineNumber').val(editLandlineNumber);
							$('#editEmailId').val(editEmailId);
							$('#editContactPerson').val(editContactPerson);
							$('#editMobileNUmber').val(editMobileNUmber);
							$('#editRBIReggistrationNumber').val(
									editRBIReggistrationNumber);
							$('#editCompanyCIN').val(editCompanyCIN);
							$('#editCompanyPAN').val(editCompanyPAN);
							$('#editGstinNumber').val(editGstinNumber);
							$('#editRatingAgency').val(editRatingAgency);
							$('#editRating').val(editRating);
							$('#editRatingDate').val(editRatingDate);
							$('#editFaxNumber').val(editFaxNumber);

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
	function disableUnable() {
		//document.getElementById("editDetails").disabled = false;
		document.getElementById("viewDetails").disabled = false;
	}
</script>
</html>