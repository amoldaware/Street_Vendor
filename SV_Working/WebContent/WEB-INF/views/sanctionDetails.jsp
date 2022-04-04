<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String sN=(String)request.getAttribute("sName");
    String expLim=(String)request.getAttribute("eXposureLimit");
%>
<html >
	<head>
	<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script  src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<title>Portfolio Creation</title>
		<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
		<script type="text/javascript" src="jquery-ui-1.10.3/ui/jquery.ui.datepicker.js"></script>
		<link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
         rel = "stylesheet">
      		<script src = "https://code.jquery.com/jquery-3.5.1.min.js"></script>
    		<script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
      <link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
      
      <script>
     	 $(function() {
          	$("#portfolio_start_date").datepicker({ dateFormat: 'dd/mm/yy' });
          	
       	});
      </script>
	</head>
	
	<body  onload="clearField()">
<div class="main-section">
<div class="container-fluid">
<!-- <div class="row"> -->
	<div>	
		<div class="frm-section">
		<div class="col-md-12">
		<div id="notification-message" class=""><strong>${SuccessMsg} ${showPortfolioNoKey}</strong></div>
		
		<form:form  method="POST" action="sanctionDetailsRM.html" id="submitFormId" class="form-horizontal">
		<h5 class="sub-head"><strong>	NBFC MLI</strong></h5>
		
		<div class="col-md-2 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			   <label >Long Name:<span style="color:red">*</span></label>
			    	<form:select path="long_name" id="long_name" onchange="getShortName()" class="form-control cus-control">
							<form:option value="NONE" label="----------Select---------"/>	
							<form:options items="${lstLongName}" />
						</form:select>	
							<div Class="error" id="requiredLongName"></div>				     
			    </div>			    			   
			  </div>
			</div>
			
			<div class="col-md-2 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			   <label >Short Name: <span style="color:red">*</span></label>
			    	<form:select path="short_name" id="short_name" class="form-control cus-control">
							<form:option value="NONE" label="-------Select --------" /> 
							<form:option value="<%=sN%>" label="-------Select --------"/> 
						</form:select>				     
			    </div>			    			   
			  </div>
			</div>
			
			<div class="col-md-2 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			   <label >Exposure Limit( &#8377; )	 <span style="color:red">*</span></label>
			    	<form:input path="exposure_limit" value="<%=expLim%>" size="20" disabled="true" class="form-control cus-control" id="exposure_limit"/>		     
			    </div>			    			   
			  </div>
			</div>
			
			<div class="col-md-2 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			   <label >	Utilised Exposure Limit( &#8377; ):	 <span style="color:red">*</span></label>
			    	<input type="text" name="uExplimit" value="" id="uExplimit" disabled="true" size="18" class="form-control cus-control">
			        <span id="requiredExposureLimit" Class="displayErrorMessageInRedColor"></span>		     
			    </div>			    			   
			  </div>
			</div>
			
			<div class="col-md-2 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			   <label >	Exposure Validity Date:	 <span style="color:red">*</span></label>
			    	<form:input path="dateOfSanction" value=""   size="20"   disabled="true" id="dateOfSanction" class="form-control cus-control"/> 
			       <span id="requiredDateOfSanction" Class="displayErrorMessageInRedColor"></span> 	     
			    </div>			    			   
			  </div>
			</div>
			<div class="clearfix"></div>
			<hr style=" margin: 0;  border: 1px solid #d8d8d8">
			<h5 class="sub-head"><strong>	Portfolio Details :	</strong></h5>
		
			
			<div class="col-md-2 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			   <label >Max Portfolio Size( &#8377; ): <span style="color:red">*</span></label>
			    	<form:input path="max_portfolio_size" value="" size="15"  class="form-control cus-control" id="max_portfolio_size" placeholder="eg.2500000"/>
                    <div Class="error" id="requiredExposureLimitMaxPortfolioSize"></div>
                    <div Class="error" id="p_error"></div>
                    <div Class="displayErrorMessageInRedColor" id="p_error1"></div>
                    <div Class="error" id="mpsSouldNotBZero"></div>
                    <div Class="error" id="maxPortfolioLimitTenLacks"></div>
			    </div>			    			   
			  </div>
			</div>
				<div class="col-md-2 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			   <label >Guarantee Fee(% p.a): <span style="color:red">*</span></label>
			    	<form:input path="gurantee_fee" value="" size="15" class="form-control cus-control" placeholder="eg:3"/>
                    <div Class="error" id="requiredGuranteeFee"></div>
			    </div>			    			   
			  </div>
			</div>
				<div class="col-md-2 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			   <label >Guarantee Fee(% p.a): <span style="color:red">*</span></label>
			    	<form:input path="gurantee_fee" value="" size="15" class="form-control cus-control" placeholder="eg:3"/>
                    <div Class="error" id="requiredGuranteeFee"></div>
			    </div>			    			   
			  </div>
			</div>
				<div class="col-md-2 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			   <label >Financial Year/Base Year: <span style="color:red">*</span></label>
			    	<form:input path="gurantee_fee" value="" size="15" class="form-control cus-control" placeholder="eg:3"/>
                    <div Class="error" id="requiredGuranteeFee"></div>
			    </div>			    			   
			  </div>
			</div>
			<div class="col-md-2 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			   <label >Financial Year/Base Year: <span style="color:red">*</span></label>
			    	<form:select path="financial_year" id="financial_year" class="form-control cus-control" >
							<form:option value="" label="-------Select-----"/>	
							<form:options items="${finalcial_year}"/>
						</form:select>
						<div Class="error" id="requiredFinancialYear"></div>
			    </div>			    			   
			  </div>
			</div>
			
			<div class="col-md-2 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			   <label >Portfolio life(Months): <span style="color:red">*</span></label>
			    	<form:input path="portfolio_life" value="" size="15" class="form-control cus-control" placeholder="eg:2" />
                    <div Class="error" id="requiredPortfolioLife"></div>
                    <div Class="error" id="portfolifeShouldNotBeZeroId"></div>
			    </div>			    			   
			  </div>
			</div>
			<div class="col-md-2 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			   <label >Portfolio Start Date(dd/mm/yyyy): <span style="color:red">*</span></label>
			    	<form:input path="portfolio_start_date"  value="" class="form-control cus-control" size="15" id="portfolio_start_date" onchange="validatePortfolioStartDate()" placeholder="format:dd/mm/yyyy"/>
                    <div Class="error" id="requiredPortfolioStartDate"></div>
                    <div Class="error" id="requiredPortfolioStartDate1"></div>
                    <div Class="error" id="datepicker_14_Error"></div>
			    </div>			    			   
			  </div>
			</div>
			
			<div class="col-md-2 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			   <label >Pay-out Cap(% of the Cystalized Portfolio ): <span style="color:red">*</span></label>
			    	<form:input path="pay_out_cap" value="" size="15" id="pay_out_cap" class="form-control cus-control" />
                    <div Class="error" id="requiredPayOutCap"></div>
			    </div>			    			   
			  </div>
			</div>
			
			<div class="d-inlineblock mt-35">
			<input class="btn btn-reset" type="button" value="Submit" onclick="return validateFormData()"/>
			     <input class="btn btn-reset" type="button" value="Exit" onclick="return exitMLIDetails()"/>
			      <span id="datepicker_14_Error" Class="error"></span>
			<span Class="displayErrorMessageInRedColor" ></span>
			</div>
		
		</form:form>
		
		</div>
		</div>
		
	</div>
</div>
</div>
			
		<%-- <div id="successMsgId"><font color="green" size="4"><b>${SuccessMsg} ${showPortfolioNoKey}</b></font></div>
		<h2 style="width:100%; height:1px;color:#5c4324;" align="center">Portfolio Creation</h2>
		<form:form  method="POST" action="sanctionDetailsRM.html" id="submitFormId" >
		<table align="center"  >
			<tr>
				<td>
					<table border="0" align="left"  cellpadding=5 cellspacing=5 >
			   		<tr>
			        	<td><b>NBFC MLI</b></td>
			   		</tr>			   		
			    	<tr></tr>
			    <tr>
			        <td class="label">Long Name:<span style="color:red">*</span></td>
			        
			         <td class="DataElement" >
						<form:select path="long_name" id="long_name" onchange="getShortName()">
							<form:option value="NONE" label="----------Select---------"/>	
							<form:options items="${lstLongName}" />
						</form:select>
						
					</td>
					<td class="label">Short Name:<span style="color:red">*</td>
			        <td class="DataElement">
						<form:select path="short_name" id="short_name" >
							<form:option value="NONE" label="-------Select --------" /> 
							<form:option value="<%=sN%>" label="-------Select --------"/> 
						</form:select>
						
					</td>
			    </tr>
			    
			    <tr>
			    	<td class="label">Exposure Limit( &#8377; ):<span style="color:red">*</td>
			        <td class="DataElement"><form:input path="exposure_limit" value="<%=expLim%>" size="20" disabled="true" style="text-align: right" id="exposure_limit"/></td>
			        <td class="label">Utilised Exposure Limit( &#8377; ):</td>
			        <td class="DataElement"><input type="text" name="uExplimit" value="" id="uExplimit" disabled="true" size="18" style="text-align: right"></td>
			        <td id="requiredExposureLimit" Class="displayErrorMessageInRedColor"></td>
			    </tr>
			       <tr>
			        <td class="label">Exposure Validity Date(dd/mm/yyyy):<span style="color:red">*</td>
			       <td class="DataElement"><form:input path="dateOfSanction" value=""   size="20"   disabled="true" id="dateOfSanction"/></td> 
			       <td id="requiredDateOfSanction" Class="displayErrorMessageInRedColor"></td> 
			    </tr>
			    <tr></tr>
			    <tr></tr>
			    <tr>
			        <td><b>Portfolio Details</b></td>
			        <td></td>
			        <td><div Class="error" id="requiredLongName"></div></td>
			    </tr>
			    
			    <tr>
			        <td></td>
			        <td class="label">Max Portfolio Size( &#8377; ):<span style="color:red">*</td>
                    <td class="DataElement" ><form:input path="max_portfolio_size" value="" size="15" style="text-align: right" id="max_portfolio_size" placeholder="eg.2500000"/>
                    <div Class="error" id="requiredExposureLimitMaxPortfolioSize"></div>
                    <div Class="error" id="p_error"></div>
                    <div Class="displayErrorMessageInRedColor" id="p_error1"></div>
                    <div Class="error" id="mpsSouldNotBZero"></div>
                    <div Class="error" id="maxPortfolioLimitTenLacks"></div>
                    </td>
			    </tr>
			    <tr>
			        <td></td>
			        <td class="label">Guarantee Fee(% p.a):<span style="color:red">*</td>
                    <td class="DataElement"><form:input path="gurantee_fee" value="" size="15" style="text-align: right" placeholder="eg:3"/>
                    <div Class="error" id="requiredGuranteeFee"></div>
                    </td>
			    </tr>
			    <tr>
			    	 <td></td>
                   	 <td class="label">Financial Year/Base Year:<span style="color:red">*</td>
			         <td class="DataElement">
						<form:select path="financial_year" id="financial_year" >
							<form:option value="" label="-------Select-----"/>	
							<form:options items="${finalcial_year}"/>
						</form:select>
						<div Class="error" id="requiredFinancialYear"></div>
					</td>
					
			    </tr>
			     <tr>
			        <td></td>
			        <td class="label">Portfolio life(Months):<span style="color:red">*</td>
                    <td class="DataElement"><form:input path="portfolio_life" value="" size="15" style="text-align: right" placeholder="eg:2" />
                    <div Class="error" id="requiredPortfolioLife"></div>
                    <div Class="error" id="portfolifeShouldNotBeZeroId"></div>
                    </td>
			    </tr>
			    <tr>
			        <td ></td>
			        <td class="label">Portfolio Start Date(dd/mm/yyyy):<span style="color:red">*</td>
                    <td class="DataElement"><form:input path="portfolio_start_date"  value=""    size="15" id="portfolio_start_date" onchange="validatePortfolioStartDate()" placeholder="format:dd/mm/yyyy"/>
                    <div Class="error" id="requiredPortfolioStartDate"></div>
                    <div Class="error" id="requiredPortfolioStartDate1"></div>
                    <div Class="error" id="datepicker_14_Error"></div>
                    </td>                    
			    </tr>
			    <tr>
			        <td></td>
			        <td class="label">Pay-out Cap(% of the Cystalized Portfolio ):<span style="color:red">*</td>
                    <td class="DataElement"><form:input path="pay_out_cap" value="" size="15" id="pay_out_cap" />
                    <div Class="error" id="requiredPayOutCap"></div>
                    </td>
                    
			    </tr>
			    <tr>	
			      <td></td>	      
			      <td align="center" colspan=2><input class="button" type="button" value="Submit" onclick="return validateFormData()"/></td>
			      <td align="center" colspan=2><input class="button" type="button" value="Exit" onclick="return exitMLIDetails()"/></td>
			      <td id="datepicker_14_Error" Class="error"> </td>
		      </tr>
		      
		     
			</table> 
					<h2 style="width:100%; height:1px;"></h2>
			</tr>
			<tr>
				<td Class="displayErrorMessageInRedColor" ></td>				
			</tr>
		</table>
	</form:form> --%>
	
</body>

</html>


<script language="javascript">

function exitMLIDetails() {
	//alert('Exit');
	document.getElementById('submitFormId').action = "/SV/portfolioGridPage.html";
	document.getElementById('submitFormId').submit();
}
//Ajax call to pulate MLi Long Name in Drop Down box and Short Name,Exposure Limit(Rs.),Utilised Exposure Limit,Exposure Validity Date(dd/mm/yyyy) in respective control
function getShortName() {  
	//alert("hello");
	var long_name=document.getElementById("long_name").value;
	//alert("hello1");
	var long_name2 = document.getElementById('long_name');
	//alert("hello2");
    var long_name3 = long_name2.options[long_name2.selectedIndex].value;
    //alert("hello3");
	//If MLI Drop Down value is not empty or null then empty the validation msg and success message on click of submit button
    if(long_name3!=null || long_name3!="" || long_name3!='NONE'){
    	 //alert("If");
    	document.getElementById("successMsgId").innerHTML="";
    	//alert("If1");
    	document.getElementById("requiredLongName").innerHTML="";
    	//alert("If2");
    	document.getElementById("requiredExposureLimitMaxPortfolioSize").innerHTML="";
    	//alert("If3");
    	document.getElementById("p_error").innerHTML="";
    	//alert("If4");
    	document.getElementById("mpsSouldNotBZero").innerHTML="";
    	//alert("If5");
    	document.getElementById("requiredGuranteeFee").innerHTML="";
    	//alert("If6");
    	document.getElementById("requiredFinancialYear").innerHTML="";
    	//alert("If7");
    	document.getElementById("requiredPortfolioLife").innerHTML="";
    	//alert("If8");
    	document.getElementById("portfolifeShouldNotBeZeroId").innerHTML="";
    	//alert("If9");
    	document.getElementById("requiredPortfolioStartDate").innerHTML="";
    	document.getElementById("requiredPortfolioStartDate1").innerHTML="";
    	//alert("If00");
    	
    	document.getElementById("requiredPayOutCap").innerHTML="";
    	//alert("If11");
    	document.getElementById("p_error1").innerHTML="";
    	//alert("If22");
    }
    
	//On Change of MLI LongName DropDown value will empty and none then  short_name,exposure_limit,uExplimit,dateOfSanction,max_portfolio_size field value will be blank or empty
	
	if(long_name3==null || long_name3=="" || long_name3=='NONE'){
		 //alert("1");
		 document.getElementById("short_name").value='';
		 //alert("else2");
		 document.getElementById("exposure_limit").value='';
		// alert("else3");
		 document.getElementById("uExplimit").value='';
		// alert("else4");
		 document.getElementById("dateOfSanction").value='';
		// alert("else5");
		 document.getElementById("max_portfolio_size").value='';
		// alert("else6");
		 //Display validatino msg when you do have selected MLI LongName and click on Submit button
		 document.getElementById("requiredLongName").innerHTML="Please select long name";
		 //alert("else7");
		 document.getElementById("requiredExposureLimitMaxPortfolioSize").innerHTML="Please enter max portfolio size";
		 //alert("else8");
		 document.getElementById("requiredGuranteeFee").innerHTML="Please enter gurantee fee";
		// alert("else9");
		 document.getElementById("requiredFinancialYear").innerHTML="Please select financial year";
		// alert("else00");
		 document.getElementById("requiredPortfolioLife").innerHTML="Please enter portfolio life";
		// alert("else11");
		 document.getElementById("requiredPortfolioStartDate").innerHTML="Please enter portfolio start date";
		 document.getElementById("requiredPortfolioStartDate1").innerHTML="Please enter portfolio start date";
		// alert("else111");
		 document.getElementById("requiredPayOutCap").innerHTML="Please enter pay out cap";  
		 
	}else{
		// alert("ajax");
		try { 
			  $.ajax({  
			    type: "POST",  
			    url:  "AddUser3.html",  
			    data: "long_name=" + long_name ,  
			    success: function(response){
			      							var select2 = document.getElementById('short_name');
			      							if(response.status == "SUCCESS"){
			    								document.getElementById('short_name').options.length=0;
			    								//alert("Object=="+response.result[0]);
			    								//alert("doAjaxPost9 length="+response.result.length);
			    								//alert("doAjaxPost10="+response.result[0].short_name);
			    								//alert("doAjaxPost11="+response.result[0].exposure_limit);
			    								//alert("===max_portfolio_size======="+response.result[0].max_portfolio_size);
			    								//alert("===toDate======="+response.result[0].dateOfSanction);
			    								
			    								document.getElementById("short_name").value=response.result[0].short_name;
			    								document.getElementById("exposure_limit").value=response.result[0].exposure_limit;
			    								document.getElementById("uExplimit").value=response.result[0].max_portfolio_size;
			    								document.getElementById("dateOfSanction").value=response.result[0].dateOfSanction;
			    								
			    								/*for(var i = 0 ; i < response.result.length ; i++){
			    		    						option = document.createElement('option');
			    		    						option.text =response.result[i].short_name;
			    		    						select2.add(option);
			    		    						
			    	  							}*/
			    								option = document.createElement('option');
		    		    						option.text =response.result[0].short_name;
		    		    						select2.add(option);
			      							}     
			      							var exposureLimitValue=document.getElementById("exposure_limit").value;
			      							var uExplimitValue=document.getElementById("uExplimit").value;
			      							var maxPortfolioSizeBalance=exposureLimitValue-uExplimitValue;

			      							document.getElementById("max_portfolio_size").value=maxPortfolioSizeBalance;
			    },  
			      error: function(e){  
			      alert('Response Not Came Properly====: ' + e);  
			    }  
			  });  
			  
			  }catch(err) {
			    	alert('Unable to send request properly: ' + err);  
			  }
			}  
	}

//Validate Form data on click on submit button
function validateFormData(){
//alert("1");
var chkflag=false;
	 var today = new Date();
	 var currentDate=today.getDate()+'/'+today.getMonth()+1  +'/'+today.getFullYear();
	//alert('velidation');
	  	var long_name1 = document.getElementById('long_name');
	    var long_name2 = long_name1.options[long_name1.selectedIndex].value;
	    
	    var short_name1 = document.getElementById('short_name');
	    var short_name2 = short_name1.options[short_name1.selectedIndex].value;

	    var financial_year1 = document.getElementById('financial_year');
	    var financial_year2 = financial_year1.options[financial_year1.selectedIndex].value;

	    var max_portfolio_size_Value=parseInt(document.getElementById("max_portfolio_size").value);
		var exposure_limit_value=parseInt(document.getElementById("exposure_limit").value);
		
	 // alert("long_name2"+long_name2);
	    if(long_name2==null || long_name2=="" || long_name2=='NONE'){
		 	document.getElementById("requiredLongName").innerHTML="Please select long name";
		 	chkflag=true;
		 }else{
	    	document.getElementById("requiredLongName").innerHTML="";
	    	chkflag=false;	    	
		 }
		// alert("1chkflag :"+chkflag);
		if(document.getElementById('max_portfolio_size').value==null || document.getElementById('max_portfolio_size').value==""){
	    	document.getElementById("requiredExposureLimitMaxPortfolioSize").innerHTML="Please enter max portfolio size";
	    	chkflag=true;
		}else{
			document.getElementById("requiredExposureLimitMaxPortfolioSize").innerHTML="";
			chkflag=false;
		}
		//alert("2chkflag :"+chkflag);
		//alert("2chkflag"+document.getElementById('max_portfolio_size').value);
		//alert("2chkflag"+document.getElementById('max_portfolio_size').value.length);

		
		if(document.getElementById('max_portfolio_size').value.length==0||document.getElementById('max_portfolio_size').value==0){
			//alert('velidation1');	
			document.getElementById("requiredExposureLimitMaxPortfolioSize").style.display="none";
				document.getElementById("p_error").style.display="none";
	     		var errorMsg1="Max portfolio size should not be zero.";
	     		document.getElementById("mpsSouldNotBZero").innerHTML = errorMsg1;
	     		chkflag=true;
		}else{
					document.getElementById("mpsSouldNotBZero").innerHTML="";
					chkflag=false;
		}	
		//alert("3chkflag :"+chkflag);	
		if(document.getElementById('gurantee_fee').value==null || document.getElementById('gurantee_fee').value==""){
	    	document.getElementById("requiredGuranteeFee").innerHTML="Please enter gurantee fee";
	    	chkflag=true;
		}else{
			document.getElementById("requiredGuranteeFee").innerHTML="";
			chkflag=false;
		}
		//alert("4chkflag :"+chkflag);
		if(financial_year2==null || financial_year2==""){
		    
			 document.getElementById("requiredFinancialYear").innerHTML="Please select financial year";
			 chkflag=true;
		   
		}else{
			document.getElementById("requiredFinancialYear").innerHTML="";
			chkflag=false; 
		}
		//alert("5chkflag :"+chkflag);

		if(document.getElementById('portfolio_life').value.length==0){
	    	document.getElementById("requiredPortfolioLife").innerHTML="Please enter portfolio life";
	    	chkflag=true;
		}else{
			document.getElementById("requiredPortfolioLife").innerHTML="";
			chkflag=false;
		}
		//alert("6chkflag :"+chkflag);
		if(document.getElementById('portfolio_life').value==0 || document.getElementById('portfolio_life').value.length==0){
			document.getElementById("requiredPortfolioLife").style.display="none";
	    	document.getElementById("portfolifeShouldNotBeZeroId").innerHTML="Portfolio life should not be zero";
	    	chkflag=true;
		}else{ 
			document.getElementById("portfolifeShouldNotBeZeroId").innerHTML="";
			chkflag=false;
		}
		//alert("7 chkflag :"+chkflag);
		//alert("7 chkflag :"+document.getElementById('portfolio_start_date').value.length);
		//alert("div:"+document.getElementById("requiredPortfolioStartDate").style.display);
		
		if(document.getElementById('portfolio_start_date').value.length==0){
	    	document.getElementById("requiredPortfolioStartDate1").innerHTML="Please enter portfolio start date";
	    	chkflag=true;
		}else{
			document.getElementById("requiredPortfolioStartDate1").innerHTML="";
			chkflag=false;
		}

		//alert("pfStartDate"+pfStartDate.length);
		var pfStartDate = document.getElementById("portfolio_start_date").value;		
		if(pfStartDate.length!=0){
		    if(pfStartDate>currentDate){
		    	document.getElementById("requiredPortfolioStartDate1").innerHTML="Portfolio start date should not be future date";		    	
		    	return false;
			 }else{
				 document.getElementById("requiredPortfolioStartDate1").innerHTML="";				
			 }
		}
		
		//alert("8 chkflag :"+chkflag);
		if(document.getElementById('pay_out_cap').value.length==0){
	    	document.getElementById("requiredPayOutCap").innerHTML="Please enter pay out cap";
	    	chkflag=true;
		}else{
			document.getElementById("requiredPayOutCap").innerHTML="";
			chkflag=false;
			
		}
		//alert("9 chkflag :"+chkflag);
	
		
	
		
		//alert("10 chkflag :"+chkflag);  
		   		
		//Portfolio start date should not be less than the exposure limit		
		if((document.getElementById("portfolio_start_date").value!=null || document.getElementById("portfolio_start_date").value!='')||(document.getElementById("dateOfSanction").value!=null || document.getElementById("dateOfSanction").value!='')){
			document.getElementById("requiredPortfolioStartDate").style.display="none";
			var psdate=document.getElementById("portfolio_start_date").value;
			var expDate=document.getElementById("dateOfSanction").value;
			var psdate1 = psdate.split('/');
			var expDate1 = expDate.split('/');
			var psdateValue = new Date(psdate1[2], psdate1[1], psdate1[0]); //Year, Month, Date
			var expDateValue = new Date(expDate1[2], expDate1[1], expDate1[0]);
			if(psdateValue > expDateValue){
				var errorMsg1="Portfolio Start Date should be less than Exposure Validity Date";
				document.getElementById("requiredPortfolioStartDate").style.display="none";
				document.getElementById("datepicker_14_Error").innerHTML = errorMsg1;
			}else{
				document.getElementById("datepicker_14_Error").innerHTML = "";
			}
		}

		//alert("max_portfolio_size_Value"+max_portfolio_size_Value);
		//alert("exposure_limit_value"+exposure_limit_value);
		// Validation of Max portfolio size should be less than exposure limit.
		if(max_portfolio_size_Value!=null  && exposure_limit_value!=null){
			if(max_portfolio_size_Value >= exposure_limit_value){
			//	alert('velidation2');
				document.getElementById("requiredExposureLimitMaxPortfolioSize").style.display="none";
				document.getElementById("mpsSouldNotBZero").style.display="none";
				 
		    	 var errorMsg="Max portfolio size should be less than exposure limit.";
		    	document.getElementById("p_error").innerHTML = errorMsg;
		         return false;
		        }else{
		      //  	alert('velidation3');
		        	document.getElementById("p_error").innerHTML ="";
			}
		}
		//Max Portfolio size should not be more then sum of exposure limit
		var utilizedExposureLimit=document.getElementById('uExplimit').value;
		var exposure_limit_value1=parseInt(document.getElementById("exposure_limit").value);
		var utilizedExposureLimitValue1=parseInt(document.getElementById("uExplimit").value);
		var max_portfolio_size_Value1=parseInt(document.getElementById("max_portfolio_size").value);
		var sumOfCreatedPortfolio=utilizedExposureLimitValue1+max_portfolio_size_Value1;
		//alert(sumOfCreatedPortfolio+' '+exposure_limit_value1);
		if(exposure_limit_value1!=null && utilizedExposureLimitValue1!=null  && max_portfolio_size_Value1!=null){
			if(sumOfCreatedPortfolio > exposure_limit_value1){
				//alert('velidation4');
				document.getElementById("requiredExposureLimitMaxPortfolioSize").style.display="none";
				document.getElementById("p_error").style.display="none";
				document.getElementById("mpsSouldNotBZero").style.display="none";
		    	 var errorMsg="Max portfolio size should be less than sum of Exposure limit.";
		    	 document.getElementById("p_error1").innerHTML = errorMsg;
		         return false;
		        }else{
		        	//alert('velidation5');
		        	//document.getElementById("p_error1").innerHTML ="";
			}
		}
		//max_portfolio_size
		// Validation of Max portfolio size should be less than exposure limit.
		if(max_portfolio_size_Value!=null){
			var maxPortfolioLimit=1000000;
			if(max_portfolio_size_Value < maxPortfolioLimit){
				document.getElementById("requiredExposureLimitMaxPortfolioSize").style.display="none";
				document.getElementById("mpsSouldNotBZero").style.display="none";
				document.getElementById("p_error1").style.display="none";
				document.getElementById("p_error").style.display="none";
		    	 var errorMsg="Max portfolio size should not be less than 1000000.";
		    	 document.getElementById("maxPortfolioLimitTenLacks").innerHTML = errorMsg;
		         return false;
		        }else{
		        	document.getElementById("maxPortfolioLimitTenLacks").innerHTML ="";
			}
		}
		//alert("5"+chkflag);				
		if(chkflag==false){
			//alert("SUBMIT");
		document.getElementById("submitFormId").submit();
		}
	}
	
//validate portfolio_start_date on change of portfolio_start_date field 

function validatePortfolioStartDate(){
	if((document.getElementById("portfolio_start_date").value!=null && document.getElementById("portfolio_start_date").value!='')&&(document.getElementById("dateOfSanction").value!=null && document.getElementById("dateOfSanction").value!='')){
		document.getElementById("requiredPortfolioStartDate").style.display="none";
		var psdate=document.getElementById("portfolio_start_date").value;
		var expDate=document.getElementById("dateOfSanction").value;
		var psdate1 = psdate.split('/');
		var expDate1 = expDate.split('/');
		var psdateValue = new Date(psdate1[2], psdate1[1], psdate1[0]); //Year, Month, Date
		var expDateValue = new Date(expDate1[2], expDate1[1], expDate1[0]);
		if(psdateValue > expDateValue){
			var errorMsg1="Portfolio Start Date should be less than Exposure Validity Date";
			document.getElementById("requiredPortfolioStartDate").style.display="none";
			document.getElementById("datepicker_14_Error").innerHTML = errorMsg1;
		}else{
			document.getElementById("datepicker_14_Error").innerHTML = "";
		}
	}
}
  //Clear form data after submit form
function clearField(){
			var clearLongName = document.getElementById('long_name');
			clearLongName.selectedIndex = 0;
			var clearShortName = document.getElementById('short_name');
			clearShortName.selectedIndex = 0;
			document.getElementById('exposure_limit').value="";
			document.getElementById('uExplimit').value="";
			document.getElementById('dateOfSanction').value="";
			document.getElementById('max_portfolio_size').value="";
			document.getElementById('gurantee_fee').value="";
			var clearFinancial_year = document.getElementById('financial_year');
	   		clearFinancial_year.selectedIndex = 0;
			document.getElementById('portfolio_life').value="";
			document.getElementById('portfolio_start_date').value="";
			document.getElementById('pay_out_cap').value="";
	 }

	
</script>