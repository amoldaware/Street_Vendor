<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<link href="css/jquery-ui-css.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<LINK href="<%=request.getContextPath()%>/css/stylesheet.css"
	rel="stylesheet" type="text/css">
	


<style>
.Decl-msg{ width:100%; display:block; }
.Decl-msg h2{ font-size: 16px; margin: 10px 0; color: #da294d; }
.Decl-msg h3:first-child {    font-size: 22px; margin: 5px 0; padding:0 0 15px;  position:relative;}
.Decl-msg h3:first-child  a{ color:#da294d !important;}
.Decl-msg h3:first-child:after{ content:''; position:absolute; width: 150px;
    bottom: 0px;   height: 2px;  left: 44%; background-color:gray; }
.Decl-msg h3:nth-child(2){    font-size: 16px; margin: 6px 0; font-style:italic; }
.Decl-msg p{ font-size: 16px; margin: 6px 0;     }
.under_msg{ width:100%; display:block; border-bottom: 1px dotted gray; }
.under_msg  h2{ font-size: 20px; font-weight: 500; padding: 15px 0 15px; margin: 0 0; position:relative; }
.under_msg  h2:after{ content:''; position:absolute; width: 150px;
    bottom: 0px;   height: 2px;  left: 44%; background-color:gray; }
  ol.under_list{ width:100%; display:block; }
ol.under_list li{ font-size: 16px; padding:3px 0; }
.sign_details{width:100%; display:block;  padding-top: 15px;}
.sign_details h4{ font-size:16px;font-style: italic;  font-weight: 700; margin:5px 0; }
.sign_details p{   font-size:16px; }
.modal-dialog{width:80%;}
.modal-header,.modal-footer{    padding: 10px 15px;}
.modal-body{    padding: 5px 15px;}
.declarebtn:hover{cursor:pointer;}
.securityValue {
	display: inline;
	float: left;
	padding: 0 1px;
}

.securityValue:nth-child(1),.securityValue:nth-child(2),.securityValue:nth-child(5),.securityValue:nth-child(6)
	{
	width: 130px;
}

.securityValue:nth-child(3) {
	width: 220px;
}

.securityValue:nth-child(4) {
	width: 200px;
}
</style>
<script type="text/javascript">

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

	/* $(function() {
		handleInternalRatting();
	}); */
	
</script>

<script type="text/javascript">
var currentDate1;
/*
function handleInternalRatting(){
	
	var sanctionAmt=document.getElementById("sanctionAmt1").value;
	var limit=5000000;
	if(parseInt(sanctionAmt) < parseInt(limit)){
		 document.getElementById("internalNA").checked = true;
		 document.getElementById("internalRating1").disabled=true;
		 
	}else{
		 document.getElementById("internalRating1").disabled=false;	
		 document.getElementById("internalNA").disabled=true;
		
	}
}*/

function checkextension() {
	  var file = document.querySelector("#file");
	  if ( /\.(pdf)$/i.test(file.files[0].name) === false ){ 
		 return false;
	  }else{
		  return true;  
	  }
}

function disableField(ID){
	
	/*
	var sanctionAmt=document.getElementById("sanctionAmt1").value;
	//alert("sanctionAmt=="+sanctionAmt);
	var limit=5000000;
	if(parseInt(sanctionAmt) < parseInt(limit)){
		 document.getElementById("internalNA").checked = true;
		 document.getElementById("internalRating1").disabled=true;
	}else{
		 document.getElementById("internalRating1").disabled=false;	
		 document.getElementById("internalNA").disabled=true;
	}
	
	var internalRatingChecked=document.getElementsByName('internalRating');
	for(var i = 0; i < internalRatingChecked.length; i++){
		if (internalRatingChecked[i].checked) {
	       if(internalRatingChecked[i].value=="N" || internalRatingChecked[i].value=="Y"){
	    	   document.getElementById("internalRatingRemarks").disabled=false;
		    }else{
		    	document.getElementById("internalRatingRemarks").disabled=true;
		    }
	    	
	    }
	}
	
	
	
	*/

	var dateOfSarfaesi=document.getElementById("dateOfSarfaesi").value;
	var dateOfNPA=document.getElementById("dateOfNPA").value;
	
	var array1 = dateOfSarfaesi.split("/");
	var Date=array1[2]+array1[1]+array1[0];

	var array2 = dateOfNPA.split("/");
	var Date1=array2[2]+array2[1]+array2[0];
	if(Date<Date1){
    	document.getElementById("resonFillingSuit").disabled=false;
	}else{
		document.getElementById("resonFillingSuit").disabled=true;
	}
	
	var subsidy=document.getElementById("anySubsidyInvolved").value;
	if (ID=="Y") {
		document.getElementById("dateOfSubsidy").readOnly=false;
		document.getElementById("subsidyAmt").readOnly=false;
	}else{
		document.getElementById("subsidyAmt").readOnly=true;
		document.getElementById("dateOfSubsidy").readOnly=true;
	} 
	
}



function validateKeypress(validChars) {
    var keyChar = String.fromCharCode(event.which || event.keyCode);
    return validChars.test(keyChar) ? keyChar : false;
}

function isNumberKey(evt, element) {
	  var charCode = (evt.which) ? evt.which : event.keyCode
	  if (charCode > 31 && (charCode < 48 || charCode > 57) && !(charCode == 46 || charCode == 8))
	    return false;
	  else {
	  var len = $(element).val().length;
	  var index = $(element).val().indexOf('.');
	  if (index > 0 && charCode == 46) {
	      return false;
	   }
	    if (index > 0) {
	      var CharAfterdot = (len + 1) - index;
	      if (CharAfterdot > 3) {
	        return false;
	      }
	    }
	
	  }
	  return true;
}

function myFunction(id) {
	var x = document.getElementById(id);
	if (x.className.indexOf("w3-show") == -1) {
		x.className += " w3-show";
	} else {
		x.className = x.className.replace(" w3-show", "");
	}
}

function calTotOutstandingAsOnDateOfLodgementOfClaim(id) {
	var subsidyAmount=0;
	var anySubsidyInvolvedVal1="";
	var anySubsidyInvolvedVal = document.getElementsByName('anySubsidyInvolved');
	for(var i = 0; i < anySubsidyInvolvedVal.length; i++){
		if (anySubsidyInvolvedVal[i].checked) {
			
	       if(anySubsidyInvolvedVal[i].value=="Y"){
	  
	    	   anySubsidyInvolvedVal1 = anySubsidyInvolvedVal[i].value;
	    }
		}
	}
	
	recoveryMadeAmt = document.getElementById('recovery').value;
	var subsidyAdjust1=document.getElementsByName('subsidyAdjust');
	for(var i = 0; i < subsidyAdjust1.length; i++){
		if (subsidyAdjust1[i].checked) {
	       if(subsidyAdjust1[i].value=="N" && anySubsidyInvolvedVal1=='Y'){
	    		subsidyAmount = document.getElementById('subsidyAmt').value;
		    }else{
		    	subsidyAmount = 0;	
		    }
		}
	}
	
	document.getElementById('subsidyAmt1').value =subsidyAmount;
	var totalOsAmount = document.getElementById('totalOsAmt').value;
	var latestOsAmount = document.getElementById('latestOsAmt').value;
	if (parseInt(totalOsAmount) > parseInt(latestOsAmount)) {
		document.getElementById('osAmtClaim').value = parseInt(latestOsAmount)- parseInt(recoveryMadeAmt)-parseInt(subsidyAmount);
		var AMount=parseInt(latestOsAmount)- parseInt(recoveryMadeAmt)-parseInt(subsidyAmount);
		if(parseInt(AMount)>0){
			document.getElementById('osAmtClaim').value=AMount;
		}else{
			document.getElementById('osAmtClaim').value='0.0';
		}
		
	}else if (parseInt(latestOsAmount) > parseInt(totalOsAmount)) {
		var AMount=parseInt(totalOsAmount)- parseInt(recoveryMadeAmt)-parseInt(subsidyAmount);
		if(parseInt(AMount)>0){
			document.getElementById('osAmtClaim').value=AMount;
		}else{
			document.getElementById('osAmtClaim').value='0.0';
		}
	} 
		
	document.getElementById('osAmtClaimGCP').value = document.getElementById('osAmtClaim').value;
	var calTotElaimEligibilityAmount = (parseInt(document.getElementById('osAmtClaimGCP').value) * parseInt(document.getElementById('guaranteeCov').value)) / 100;
	document.getElementById('eligableAmtClaim').value = calTotElaimEligibilityAmount;
	document.getElementById('firstInstallClaim').value = ((calTotElaimEligibilityAmount) * 75) / 100;
	var final_amt= Math.round(((calTotElaimEligibilityAmount) * 75) / 100);
	document.getElementById('firstInstallClaim').value=final_amt;
}

function validateDateOfRecall(){
	var today = new Date();
	var yyyy = today.getFullYear().toString();
	var mm = (today.getMonth()+1).toString(); //January is 0!
	var dd = today.getDate().toString();
	(dd.length == 1) && (dd = '0' + dd);
	(mm.length == 1) && (mm = '0' + mm);
	var yyyymmdd = yyyy + mm + dd;
	currentDate1=yyyymmdd;
}

	function claimLodgementFormValidatiOnSubmit(ID){
	
		validateDateOfRecall();
		var chkflag=5; 

		/*if(document.getElementById('dateOfNotice').value==null || document.getElementById('dateOfNotice').value==""){
		    document.getElementById("requiredDateOfNotice").innerHTML=" Field is mandatory";
		    chkflag=chkflag-1; 
		}else{
			document.getElementById("requiredDateOfNotice").innerHTML="";
		}*/
		
		/*if(document.getElementById('dateOfNotice').value!=null && document.getElementById('dateOfNotice').value!="") {
			var dateOfNoticeV = document.getElementById('dateOfNotice').value;
			var array1 = dateOfNoticeV.split("/");
			var dateOfNoticeVYYMMDD=array1[2]+array1[1]+array1[0];   
			
			var dateOfNPAV = document.getElementById('dateOfNPA').value;			
			var array2 = dateOfNPAV.split("/");			
			var dateOfNPAVYYMMDD=array2[2]+array2[1]+array2[0];
			
			if (dateOfNoticeVYYMMDD <= dateOfNPAVYYMMDD) {
				document.getElementById("requiredDateOfNotice").innerHTML="Date of Recall/issue date should be after NPA Date.";
		     	chkflag=chkflag-1;
			}else{
				document.getElementById("requiredDateOfNotice").innerHTML="";
			}
			
		}*/
		
		if(document.getElementById('dateOfNotice').value!=null && document.getElementById('dateOfNotice').value!="") {
			var dateOfNoticeV1 = document.getElementById('dateOfNotice').value;
			var array2 = dateOfNoticeV1.split("/");
			var dateOfNoticeVYYMMDD1=array2[2]+array2[1]+array2[0];   
			if (dateOfNoticeVYYMMDD1 <= currentDate1) {
				document.getElementById("requiredDateOfNotice1").innerHTML="";
			}else{
				document.getElementById("requiredDateOfNotice1").innerHTML="Date of Recall/issue date should not be greater than Current Date.";
				chkflag=chkflag-1;
			}
			
		}
		
		var anySubsidyInvolved11=document.getElementsByName('anySubsidyInvolved');
		for(var i = 0; i < anySubsidyInvolved11.length; i++){
			if (anySubsidyInvolved11[i].checked) {
			//	alert(option18[i].value);
		       if(anySubsidyInvolved11[i].value=="Y"){
		    	
		    	   if(document.getElementById('subsidyAmt').value=="0.0" || document.getElementById('subsidyAmt').value=="" ){
						document.getElementById("requiredSubsidyAmt").innerHTML="Field is mandatory .";
					//	document.getElementById("requireddateOfSubsidy").innerHTML="Field is mandatory.";
						 chkflag=chkflag-1; 
				  	}else{
						document.getElementById("requiredSubsidyAmt").innerHTML="";
					//	document.getElementById("requireddateOfSubsidy").innerHTML="";
					}

					if(document.getElementById('dateOfSubsidy').value==null || document.getElementById('dateOfSubsidy').value==""){
						//document.getElementById("requiredSubsidyAmt").innerHTML="Field is mandatory .";
						document.getElementById("requireddateOfSubsidy").innerHTML="Field is mandatory.";
						 chkflag=chkflag-1; 
				  	}else{
					//	document.getElementById("requiredSubsidyAmt").innerHTML="";
						document.getElementById("requireddateOfSubsidy").innerHTML="";
					}
		       }else{
		    		document.getElementById("requiredSubsidyAmt").innerHTML="";
					document.getElementById("requireddateOfSubsidy").innerHTML="";
		       }
		        break;
		    }
	    }



		
	/*	var subsidy=document.getElementById("anySubsidyInvolved").value;
		if(ID=='Y'){

			alert(document.getElementById('dateOfSubsidy').value);
			if(document.getElementById('subsidyAmt').value=="0.0" || document.getElementById('subsidyAmt').value=="" ){
				document.getElementById("requiredSubsidyAmt").innerHTML="Field is mandatory .";
			//	document.getElementById("requireddateOfSubsidy").innerHTML="Field is mandatory.";
				 chkflag=chkflag-1; 
		  	}else{
				document.getElementById("requiredSubsidyAmt").innerHTML="";
			//	document.getElementById("requireddateOfSubsidy").innerHTML="";
			}

			if(document.getElementById('dateOfSubsidy').value==null || document.getElementById('dateOfSubsidy').value==""){
				//document.getElementById("requiredSubsidyAmt").innerHTML="Field is mandatory .";
				document.getElementById("requireddateOfSubsidy").innerHTML="Field is mandatory.";
				 chkflag=chkflag-1; 
		  	}else{
			//	document.getElementById("requiredSubsidyAmt").innerHTML="";
				document.getElementById("requireddateOfSubsidy").innerHTML="";
			}
		}else{
			document.getElementById("requiredSubsidyAmt").innerHTML="";
			document.getElementById("requireddateOfSubsidy").innerHTML="";
		}*/
		 var inp = document.getElementById('file');
		    //alert(inp);
		    if(inp.files.length === 0){
		    	document.getElementById("requiredfile").innerHTML="Attachment of PDF is mandatory";
		       
		        inp.focus();
		        chkflag=chkflag-1; 
		        
		    }else{
		    	if(checkextension()==true){
					
					document.getElementById("requiredfile1").innerHTML="";
				}else{
					 document.getElementById("requiredfile1").innerHTML="Only .PDF format";
					 chkflag=chkflag-1; 
				} 
		    	
		    	document.getElementById("requiredfile").innerHTML="";
		    }
		    
		    
		    
	if(document.getElementById('dealingOfficerName').value==""||document.getElementById('dealingOfficerName').value==null){
			document.getElementById("requiredDealingOfficerName").innerHTML="Field is mandatory.";
			chkflag=chkflag-1; 
	}else{
		document.getElementById("requiredDealingOfficerName").innerHTML="";
	}
	
	var dealingOfficerNameV = document.getElementById("dealingOfficerName");
	var alpha = /^[a-zA-Z\s-, ]+$/;  
	    if (dealingOfficerNameV.value !="" && dealingOfficerNameV.value!=null) {
	    	if (!dealingOfficerNameV.value.match(alpha)) {
		        document.getElementById("requiredDealingOfficerName").innerHTML="Digits are not allowed";    
		        chkflag=chkflag-1;
		   }else {
			   document.getElementById("dealingOfficerName").innerHTML="";
		   }
	}

	    
		
	var option=document.getElementsByName('wilfulDefaulter');
	if (!(option[0].checked || option[1].checked)) {
	    document.getElementById("requiredWilfulDefaulter").innerHTML="Field is mandatory.";
	    chkflag=chkflag-1; 
	}else{
		document.getElementById("requiredWilfulDefaulter").innerHTML="";
	}
	
	
	var option1=document.getElementsByName('fraudAcc');
	if (!(option1[0].checked || option1[1].checked)) {
	    //alert("Select Classified fraud Radio Button ");
	     document.getElementById("requiredFraudAcc").innerHTML="Field is mandatory";
	     chkflag=chkflag-1; 
	}else{
		document.getElementById("requiredFraudAcc").innerHTML="";
	}	
	

	var option2=document.getElementsByName('enquiryConcluded');
	if (!(option2[0].checked || option2[1].checked)) {
	    //alert("Select Internal and/or external enquiry  Radio Button ");
	    document.getElementById("requiredEnquiryConcluded").innerHTML="Field  is mandatory.";
	    chkflag=chkflag-1; 
	}else{
		document.getElementById("requiredEnquiryConcluded").innerHTML="";
	}	
	
	var option7=document.getElementsByName('mliReported');
	if (!(option7[0].checked || option7[1].checked)) {
	   // alert("Select Involvement of staff  Radio Button ");
	    document.getElementById("requiredLliReported").innerHTML="Field is mandatory.";
	    chkflag=chkflag-1; 
	}else{
		document.getElementById("requiredLliReported").innerHTML="";
	}	
   
	if(document.getElementById('forum').value=='NONE' ||document.getElementById('forum').value==null || document.getElementById('forum').value==""){
		document.getElementById("requiredForum").innerHTML="Field is mandatory.";
		chkflag=chkflag-1; 
	}else{
		document.getElementById("requiredForum").innerHTML="";
	}

	

	
	if(document.getElementById('suitNo').value==""||document.getElementById('suitNo').value==null){
		document.getElementById("requiredSuitNo").innerHTML="Field is mandatory.";
		chkflag=chkflag-1; 
  	}else{
		document.getElementById("requiredSuitNo").innerHTML="";
	}
	//var forum=document.getElementById("forum").value;
	//if(forum == "SARFAESI"){
	if(document.getElementById('dateOfSarfaesi').value==null || document.getElementById('dateOfSarfaesi').value==""){
	    document.getElementById("requiredsDateOfSarfaesi").innerHTML="Field is mandatory.";
	    chkflag=chkflag-1; 
	}else{
		document.getElementById("requiredsDateOfSarfaesi").innerHTML="";
	}
	/* }else{
		document.getElementById("requiredsDateOfSarfaesi").innerHTML="";
	} */
	if(document.getElementById('locationOfForum').value==""||document.getElementById('locationOfForum').value==null){
		document.getElementById("requiredLocationOfForum").innerHTML="Field is mandatory.";
		chkflag=chkflag-1; 
  	}else{
		document.getElementById("requiredLocationOfForum").innerHTML="";
	}
	
	if(document.getElementById('claimAmt').value=="0.0" || document.getElementById('claimAmt').value=="" ||document.getElementById('claimAmt').value==null){
		document.getElementById("requiredClaimAmt").innerHTML="Field is mandatory.";
		chkflag=chkflag-1; 
  	}else{
		document.getElementById("requiredClaimAmt").innerHTML="";
	}
  	
	/* if(document.getElementById('subsidyAmt').value==""||document.getElementById('subsidyAmt').value==null){
		document.getElementById("requiredSubsidyAmt").innerHTML="Enter Subsidy Amount.";
		chkflag=chkflag-1; 
  	}else{
		document.getElementById("requiredSubsidyAmt").innerHTML="";
	} */

	var optionAnySubsidyInvolved=document.getElementsByName('anySubsidyInvolved');
	if (!(optionAnySubsidyInvolved[0].checked || optionAnySubsidyInvolved[1].checked)) {
	    document.getElementById("requiredAnySubsidyInvolved").innerHTML="Field is mandatory";
	    chkflag=chkflag-1; 
	}else{
		document.getElementById("requiredAnySubsidyInvolved").innerHTML="";
	}

	var option31=document.getElementsByName('subsidyReceived');
	if (!(option31[0].checked || option31[1].checked)) {
	    document.getElementById("requiredSubsidyReceived").innerHTML="Field is mandatory";
	    chkflag=chkflag-1; 
	}else{
		document.getElementById("requiredSubsidyReceived").innerHTML="";
	}

	var option32=document.getElementsByName('subsidyAdjust');
	if (!(option32[0].checked || option32[1].checked)) {
	    document.getElementById("requiredSubsidyAdjust").innerHTML="Field is mandatory";
	    chkflag=chkflag-1; 
	}else{
		document.getElementById("requiredSubsidyAdjust").innerHTML="";
	}

	/* var vModeRecovery = document.getElementById('modeRecovery');
	var sModeRecovery = vModeRecovery.options[vModeRecovery.selectedIndex].value;*/
	
	var recovery= document.getElementById('recovery').value;
	if(recovery != "0.0"){
	if(document.getElementById('modeRecovery').value=="Select"){
		document.getElementById("requiredmodeRecovery").innerHTML="Select Mode Of Recovery.";
		chkflag=chkflag-1; 
	}else{
		document.getElementById("requiredmodeRecovery").innerHTML="";
	} 
	}else{
		document.getElementById("requiredmodeRecovery").innerHTML="";
	} 
	
/* 	alert(document.getElementById('recovery').value);
	 if(document.getElementById('recovery').value==0.0){
		document.getElementById("requiredrecovery").innerHTML="Recovery Amount is mandatory";
		chkflag=chkflag-1; 
	}else{
		document.getElementById("requiredrecovery").innerHTML="";
	} 
	 */

	var option4=document.getElementsByName('npaRecoveryFlag');
	if (!(option4[0].checked || option4[1].checked || option4[2].checked)) {
	   document.getElementById("requiredNpaRecoveryFlag").innerHTML="Field is mandatory.";
	   chkflag=chkflag-1; 
	}else{
		document.getElementById("requiredNpaRecoveryFlag").innerHTML="";
	}

	var option5=document.getElementsByName('confirmRecoveryFlag');
	if (!(option5[0].checked || option5[1].checked)) {
	   document.getElementById("requiredConfirmRecoveryFlag").innerHTML="Field is mandatory.";
	   chkflag=chkflag-1; 
	}else{
		document.getElementById("requiredConfirmRecoveryFlag").innerHTML="";
	}
//-------------------------------------------------------------------------------------------------------------------------------	
	
	var option18=document.getElementsByName('activityEligible');
	if (!(option18[0].checked || option18[1].checked)) {
		//	alert(option18[0].checked);
	   document.getElementById("requiredActivityEligible").innerHTML="Field is mandatory.";
	   chkflag=chkflag-1; 
	}else{
		  document.getElementById("requiredActivityEligible").innerHTML="";	
	}


	var option19=document.getElementsByName('cibil');
	if (!(option19[0].checked || option19[1].checked)) {
			//alert(option19[0].checked);
			   document.getElementById("requiredcibil").innerHTML="Field is mandatory.";
			   chkflag=chkflag-1; 
			}else{
				  document.getElementById("requiredcibil").innerHTML="";	
			}
	
	var option20=document.getElementsByName('rateCharge');
	if (!(option20[0].checked || option20[1].checked)) {
		//	alert(option18[0].checked);
			   document.getElementById("requiredrateCharge").innerHTML="Field is mandatory.";
			   chkflag=chkflag-1; 
			}else{
				  document.getElementById("requiredrateCharge").innerHTML="";	
			}
	
	var option21=document.getElementsByName('dateOfNPAAsRBI');
	if (!(option21[0].checked || option21[1].checked)) {
		//	alert(option18[0].checked);
			   document.getElementById("requireddateOfNPAAsRBI").innerHTML="Field is mandatory.";
			   chkflag=chkflag-1; 
			}else{
				  document.getElementById("requireddateOfNPAAsRBI").innerHTML="";	
			}
/* 			
	var option22=document.getElementsByName('whetherOutstanding');
	if (!(option22[0].checked || option22[1].checked)) {
		document.getElementById("requiredwhetherOutstanding").innerHTML="Field is mandatory.";
		chkflag=chkflag-1; 
		}else{
		document.getElementById("requiredwhetherOutstanding").innerHTML="";	
	} */
	var option23=document.getElementsByName('apprisalDisbursement');
	if (!(option23[0].checked || option23[1].checked)) {
		document.getElementById("requiredapprisalDisbursement").innerHTML="Field is mandatory.";
		chkflag=chkflag-1; 
		}else{
		document.getElementById("requiredapprisalDisbursement").innerHTML="";	
	}
	var option24=document.getElementsByName('postDisbursement');
	if (!(option24[0].checked || option24[1].checked)) {
		document.getElementById("requiredpostDisbursement").innerHTML="Field is mandatory.";
		chkflag=chkflag-1; 
		}else{
		document.getElementById("requiredpostDisbursement").innerHTML="";	
	}
	var option25=document.getElementsByName('exerciseCarried');
	if (!(option25[0].checked || option25[1].checked)) {
		document.getElementById("requiredexerciseCarried").innerHTML="Field is mandatory.";
		chkflag=chkflag-1; 
		}else{
		document.getElementById("requiredexerciseCarried").innerHTML="";	
	}
	var option26=document.getElementsByName('internalRating');
	if (!(option26[0].checked || option26[1].checked || option26[2].checked)) {
		document.getElementById("requiredinternalRating").innerHTML="Field is mandatory.";
		chkflag=chkflag-1; 
		}else{
		document.getElementById("requiredinternalRating").innerHTML="";	
	}
	var option27=document.getElementsByName('recoverPertaining');
	if (!(option27[0].checked || option27[1].checked)) {
		document.getElementById("requiredrecoverPertaining").innerHTML="Field is mandatory.";
		chkflag=chkflag-1; 
		}else{
		document.getElementById("requiredrecoverPertaining").innerHTML="";	
	}
	//PANDIT--------------------------------------------------------------------------------------------------------------------
	var option18=document.getElementsByName('activityEligible');

		
	for(var i = 0; i < option18.length; i++){
		if (option18[i].checked) {
		//	alert(option18[i].value);
	       if(option18[i].value=="N"){
	    	
	    	   if(document.getElementById('activityEligibleRemarks').value=="")
			   {

	   		document.getElementById("requiredActivityEligibleRemarks").innerHTML=" Remarks is mandatory";
	   		chkflag=chkflag-1;    
	       }else{
	    		document.getElementById("requiredActivityEligibleRemarks").innerHTML="";
	       }
	       }else{
	    		document.getElementById("requiredActivityEligibleRemarks").innerHTML="";
	       }
	        break;
	    }
    }
	
			
		
	
	var option19=document.getElementsByName('cibil');
	for(var i = 0; i < option19.length; i++){
		if (option19[i].checked) {
			
	       if(option19[i].value=="N"){
	    	   if(document.getElementById('cibilRemarks').value=="")
			   {

	   		document.getElementById("requiredCibilRemarks").innerHTML="Remarks  is mandatory";
	   		chkflag=chkflag-1;    
	       }else{
	    		document.getElementById("requiredCibilRemarks").innerHTML="";
	       }
	       }else{
	    		document.getElementById("requiredCibilRemarks").innerHTML="";
	       }
	        break;
	    }
    }
	

	var option20=document.getElementsByName('rateCharge');
	for(var i = 0; i < option20.length; i++){
		if (option20[i].checked) {
			
	       if(option20[i].value=="N"){
	    	   if(document.getElementById('rateChargeRemarks').value=="")
			   {
	   		document.getElementById("requiredRateChargeRemarks").innerHTML=" Remarks  is mandatory";
	   		chkflag=chkflag-1;    
	       }else{
	    		document.getElementById("requiredRateChargeRemarks").innerHTML="";
	       }
	       }else{
	    		document.getElementById("requiredRateChargeRemarks").innerHTML="";
	       }
	        break;
	    }
    }
	
	var option21=document.getElementsByName('dateOfNPAAsRBI');
	for(var i = 0; i < option21.length; i++){
		if (option21[i].checked) {
			
	       if(option21[i].value=="N"){
	    	   if(document.getElementById('dateOfNPAAsRBIRemarks').value=="")
			   {
	   		document.getElementById("requiredDateOfNPAAsRBIRemarks").innerHTML="Remarks  is mandatory";
	   		chkflag=chkflag-1;    
	       }else{
	    		document.getElementById("requiredDateOfNPAAsRBIRemarks").innerHTML="";
	       }
	       }else{
	    		document.getElementById("requiredDateOfNPAAsRBIRemarks").innerHTML="";
	       }
	        break;
	    }
    }


	var option22=document.getElementsByName('whetherOutstanding');
	for(var i = 0; i < option22.length; i++){
		if (option22[i].checked) {
			
	       if(option22[i].value=="N"){
	    	   if(document.getElementById('whetherOutstandingRemarks').value=="")
			   {	
	   		document.getElementById("requiredWhetherOutstandingRemarks").innerHTML="Remarks is mandatory";
	   		chkflag=chkflag-1;    
	       }else{
	    		document.getElementById("requiredWhetherOutstandingRemarks").innerHTML="";
	       }
	       }else{
	    		document.getElementById("requiredWhetherOutstandingRemarks").innerHTML="";
	       }
	        break;
	    }
    }
	

	var option23=document.getElementsByName('apprisalDisbursement');
	for(var i = 0; i < option23.length; i++){
		if (option23[i].checked) {
			
			   if(option23[i].value=="Y"){
				   if(document.getElementById('apprisalDisbursementRemarks').value=="")
				   {	   
	   		document.getElementById("requiredApprisalDisbursementRemarks").innerHTML="Remarks is mandatory";
	   		chkflag=chkflag-1;    
	       }else{
	    		document.getElementById("requiredApprisalDisbursementRemarks").innerHTML="";
	       }
			   }else{
		    		document.getElementById("requiredApprisalDisbursementRemarks").innerHTML="";
		       }
	        break;
	    }
    }
	

	var option24=document.getElementsByName('postDisbursement');
	for(var i = 0; i < option24.length; i++){
		if (option24[i].checked) {
			
	       if(option24[i].value=="Y"){
	   if(document.getElementById('postDisbursementRemarks').value=="")
		   {
	   		document.getElementById("requiredpostDisbursementremark").innerHTML="Remarks is mandatory";
	   		chkflag=chkflag-1;    
	       }else{
	    		document.getElementById("requiredpostDisbursementremark").innerHTML="";
	       }
	       }else{
	    		document.getElementById("requiredpostDisbursementremark").innerHTML="";
	       }
	        break;
	    }
    }
	
	
	var option25=document.getElementsByName('exerciseCarried');
	for(var i = 0; i < option25.length; i++){
		if (option25[i].checked) {
			
	       if(option25[i].value=="Y"){
	    	   if(document.getElementById('exerciseCarriedRemarks').value=="")
	    			   {
	   		document.getElementById("requiredExerciseCarriedRemarks").innerHTML="Remarks is mandatory";
	   		chkflag=chkflag-1;    
	       }else{
	    		document.getElementById("requiredExerciseCarriedRemarks").innerHTML="";
	       }
	      
	    }else{
    		document.getElementById("requiredExerciseCarriedRemarks").innerHTML="";
	       }
	       
	       break;
    }
	}

	var option26=document.getElementsByName('internalRating');
	for(var i = 0; i < option26.length; i++){
		if (option26[i].checked) {
			 
	       if(option26[i].value=="Y"){
	    	
	   if(document.getElementById('internalRatingRemarks').value=="")
	       {
	   		document.getElementById("requiredInternalRatingRemarks").innerHTML="Remarks is mandatory";
	   		chkflag=chkflag-1;    
	       }else{
	    		document.getElementById("requiredInternalRatingRemarks").innerHTML="";
	    		
	       }
	      
	    }else{
	    	
    		document.getElementById("requiredInternalRatingRemarks").innerHTML="";
	       }
	       break;
    } 
	}

	var option27=document.getElementsByName('recoverPertaining');
	for(var i = 0; i < option27.length; i++){
		if (option27[i].checked) {
		
	       if(option27[i].value=="N"){
	    	//   alert(document.getElementById('recoverPertainingRemarks').value);
	    if(document.getElementById('recoverPertainingRemarks').value=="")
	        {
	   		   document.getElementById("requiredRecoverPertainingRemarks").innerHTML="Remarks is mandatory";
	   		   chkflag=chkflag-1; 
	    	    }else{
		    		document.getElementById("requiredRecoverPertainingRemarks").innerHTML="";
	 	       }
	       }else{
	    		document.getElementById("requiredRecoverPertainingRemarks").innerHTML="";
 	       }
	        break;
	    }
    }
	

	if(document.getElementById('financialPositionComments').value==""||document.getElementById('financialPositionComments').value==null){
		document.getElementById("requiredFinancialPositionComments").innerHTML="Field is mandatory";
		chkflag=chkflag-1; 
  	}else{
		document.getElementById("requiredFinancialPositionComments").innerHTML="";
	}
	if(document.getElementById('financialAssistanceDtls').value==""||document.getElementById('financialAssistanceDtls').value==null){
		document.getElementById("requiredFinancialAssistanceDtls").innerHTML="Field is mandatory";
		chkflag=chkflag-1; 
  	}else{
		document.getElementById("requiredFinancialAssistanceDtls").innerHTML="";
	}
	
	var option6=document.getElementsByName('creditSupport');
	if (!(option6[0].checked || option6[1].checked)) {
	   document.getElementById("requiredCreditSupport").innerHTML="Field is mandatory";
	   chkflag=chkflag-1; 
	}else{
		document.getElementById("requiredCreditSupport").innerHTML="";
	}
	
	if(document.getElementById('bankFacilityDtls').value==""||document.getElementById('bankFacilityDtls').value==null){
		document.getElementById("requiredBankFacilityDtls").innerHTML="Field is mandatory";
		chkflag=chkflag-1; 
  	}else{
		document.getElementById("requiredBankFacilityDtls").innerHTML="";
	}

	if(document.getElementById('claimLodgementCertificate').checked == true){
		 document.getElementById("requiredClaimLodgementCertificate").innerHTML="";
	}else{
		
		 document.getElementById("requiredClaimLodgementCertificate").innerHTML="Select Declaration & Undertaking";
		 chkflag=chkflag-1; 
	}
	if(document.getElementById('dealingOfficerNO').value==""||document.getElementById('dealingOfficerNO').value==null){
		document.getElementById("requireddealingOfficerNO").innerHTML="Field is mandatory.";
		chkflag=chkflag-1; 
	}else{
		document.getElementById("requireddealingOfficerNO").innerHTML="";
	}
	if(document.getElementById('dealingOfficerNO').value!=""||document.getElementById('dealingOfficerNO').value!=null){
		var phoneno = /^\d{10}$/;
		if(!(document.getElementById('dealingOfficerNO').value.match(phoneno))){
			document.getElementById("requireddealingOfficerNO").innerHTML="";
			document.getElementById("requireddealingOfficerNO").innerHTML="Dealing Officer Number Must be 10 Digit.";
		    chkflag=chkflag-1;  
		}else{
			document.getElementById("requireddealingOfficerNO").innerHTML=""; 
		}
	}

	
	  
	
	var total_NPA=document.getElementById("totalSecuritydetails1").value;

	
	
	 var total=document.getElementById("totalSecuritydetails2").value;
	
	 if(total_NPA!="0.00"){
		 
	if((total) == "0.0" || (total) == "0" || (total) == "0.00"){
	
		document.getElementById("requiredtotalSecuritydetails2").innerHTML="Total Security For Claim Must be greater than 0.";
		chkflag=chkflag-1; 
	}else{
		document.getElementById("requiredtotalSecuritydetails2").innerHTML="";
		
	} 
	 }else{
		 document.getElementById("requiredtotalSecuritydetails2").innerHTML="";
	 }
	 
	 if(total!="0.0"){
		 
		if(document.getElementById("reductionReason2").value == "Select") {
			document.getElementById("requiredreductionReason2").innerHTML="Field is mandatory";
			chkflag=chkflag-1; 	
		}else{
			document.getElementById("requiredreductionReason2").innerHTML="";
		}
		 
	 }else{
		 document.getElementById("requiredreductionReason2").innerHTML="";
	 }
		
		var dateOfSarfaesi=document.getElementById("dateOfSarfaesi").value;
		var dateOfNPA=document.getElementById("dateOfNPA").value;
		var array1 = dateOfSarfaesi.split("/");
		var Date=array1[2]+array1[1]+array1[0];
		var array2 = dateOfNPA.split("/");
		var Date1=array2[2]+array2[1]+array2[0];
		if(Date<Date1){
	          document.getElementById("resonFillingSuit").disabled=false;
	      	if(document.getElementById('resonFillingSuit').value==""||document.getElementById('resonFillingSuit').value==null){
				document.getElementById("requiredResonFillingSuit").innerHTML="Field is mandatory.";
				chkflag=chkflag-1; 
		  	}else{
				document.getElementById("requiredResonFillingSuit").innerHTML="";
			}     
		}else{
				document.getElementById("resonFillingSuit").disabled=true;
				document.getElementById("requiredResonFillingSuit").innerHTML="";
		}
		
	
	
	if(chkflag==5){
		document.getElementById('saveClaimLodgmentDtlsFormId').action = "/SV/saveClaimLodgmentDtls.html";
		document.getElementById('saveClaimLodgmentDtlsFormId').submit();
	}	

}

	
	 function claimLodgementCheckListValidation(){
		var option8=document.getElementsByName('activityEligible');
		if (!(option8[0].checked)) {
			document.getElementById("activityEligibleRemarks").disabled=false;//Enable
		}else{
			document.getElementById("activityEligibleRemarks").value="";
			document.getElementById("activityEligibleRemarks").disabled=true;//Disable
			
		}
		
		var option9=document.getElementsByName('cibil');
		if (!option9[0].checked) {
			document.getElementById("cibilRemarks").disabled=false;
		}else{
			document.getElementById("cibilRemarks").value="";
			document.getElementById("cibilRemarks").disabled=true;
		}


		var option10=document.getElementsByName('rateCharge');
		if (!option10[0].checked) {
			document.getElementById("rateChargeRemarks").disabled=false;
		}else{
			document.getElementById("rateChargeRemarks").value="";
			document.getElementById("rateChargeRemarks").disabled=true;
		}

		var option11=document.getElementsByName('dateOfNPAAsRBI');
		if (!option11[0].checked) {
			document.getElementById("dateOfNPAAsRBIRemarks").disabled=false;
		}else{
			document.getElementById("dateOfNPAAsRBIRemarks").value="";
			document.getElementById("dateOfNPAAsRBIRemarks").disabled=true;
		}

		var option12=document.getElementsByName('whetherOutstanding');
		if (!option12[0].checked) {
			document.getElementById("whetherOutstandingRemarks").disabled=false;
		}else{
			document.getElementById("whetherOutstandingRemarks").value="";
			document.getElementById("whetherOutstandingRemarks").disabled=true;
		}

		var option13=document.getElementsByName('apprisalDisbursement');
		if (!option13[0].checked) {
			document.getElementById("apprisalDisbursementRemarks").disabled=false;
		}else{
			document.getElementById("apprisalDisbursementRemarks").value="";
			document.getElementById("apprisalDisbursementRemarks").disabled=true;
		}

		var option14=document.getElementsByName('postDisbursement');
		if (!option14[0].checked) {
			document.getElementById("postDisbursementRemarks").disabled=false;
		}else{
			document.getElementById("postDisbursementRemarks").value="";
			document.getElementById("postDisbursementRemarks").disabled=true;
		}
		
		
		var option15=document.getElementsByName('exerciseCarried');
		if (!option15[0].checked) {
			document.getElementById("exerciseCarriedRemarks").disabled=false;
		}else{
			document.getElementById("exerciseCarriedRemarks").value="";
			document.getElementById("exerciseCarriedRemarks").disabled=true;
		}

		var option16=document.getElementsByName('internalRating');
		if (!option16[0].checked) {
			document.getElementById("internalRatingRemarks").disabled=false;
		}else{
			document.getElementById("internalRatingRemarks").value="";
			document.getElementById("internalRatingRemarks").disabled=true;
		}

		var option17=document.getElementsByName('recoverPertaining');
		if (!option17[0].checked) {
			document.getElementById("recoverPertainingRemarks").disabled=false;
		}else{
			document.getElementById("recoverPertainingRemarks").value="";
			document.getElementById("recoverPertainingRemarks").disabled=true;
		}
	
	
		
	} 
		

	function caltotalPrefermentOfClaim(field) {
		var landValue2 = document.getElementById("landValue2").value;
		var buildingValue2 = document.getElementById("buildingValue2").value;
		var planetValue2 = document.getElementById("planetValue2").value;
		var otherAssetValue2 = document.getElementById("otherAssetValue2").value;
		var currentAssetValue2 = document.getElementById("currentAssetValue2").value;
		var othersValue2 = document.getElementById("othersValue2").value;
		var networthOfPromotor2 = document.getElementById("networthOfPromotor2").value;
		var total = 0;
		if ((isNaN(landValue2)) || landValue2 == "0.0" || landValue2 == "") {
			landValue2 = 0;
	       document.getElementById("landValue2").value =landValue2;
		
		}

		if ((isNaN(buildingValue2)) || buildingValue2 == "0.0" || buildingValue2 == "" ) {
			buildingValue2 = 0;
		    document.getElementById("buildingValue2").value =buildingValue2 ;
		}
		
		if ((isNaN(planetValue2)) || planetValue2 == "0.0" || planetValue2 == "") {
			planetValue2 = 0;
		    document.getElementById("planetValue2").value =planetValue2 ;
		}

		if ((isNaN(otherAssetValue2)) || otherAssetValue2 == "0.0" || otherAssetValue2 == "") {
			otherAssetValue2 = 0;
			document.getElementById("otherAssetValue2").value =otherAssetValue2 ;
		}
		if ((isNaN(currentAssetValue2)) || currentAssetValue2 == "0.0" || currentAssetValue2 == "") {
			currentAssetValue2 = 0;
			document.getElementById("currentAssetValue2").value =currentAssetValue2 ;
		}
		if ((isNaN(othersValue2)) || othersValue2 == "0.0"|| othersValue2 == "") {
			othersValue2 = 0;
			document.getElementById("othersValue2").value =othersValue2 ;
		}

		if ((isNaN(networthOfPromotor2)) || networthOfPromotor2 == "0.0"|| networthOfPromotor2 == "") {
			networthOfPromotor2 = 0;
			document.getElementById("networthOfPromotor2").value =networthOfPromotor2 ;
		}
		total = Number(landValue2) + Number(buildingValue2) + Number(planetValue2)
				+ Number(otherAssetValue2) + Number(currentAssetValue2) + Number(othersValue2);

		document.getElementById("totalSecuritydetails2").value = total;

	}
	
	
</script>
</head>
<body>

	<div class="main-section">
		<div class="container-fluid">
			<nav aria-label="breadcrumb">
				  <ol class="breadcrumb cus-breadcrumb">
				    <li class="breadcrumb-item"><a href="/SV/displayClaimLodgementForm.html">Claim Lodgement For First Installment</a></li>  
				    <li class="breadcrumb-item active" aria-current="page">Claim Lodgement</li>
				  </ol>
			</nav>
			<div>
		
			
				<div class="frm-section">
					<div class="col-md-12">
						<form:form action="" method="POST" class="form-horizontal"
							enctype="multipart/form-data" id="saveClaimLodgmentDtlsFormId">
							<div class="clearfix"></div>
							<h5 class="sub-head">
								<strong> Details of Operating Office and Lending
									Branch: </strong>
							</h5>
							<hr style="margin: 5px 0; border: 1px solid #d8d8d8">
							<div class="clearfix"></div>
						<%-- <form:input path="sanctionAmt" id="sanctionAmt1" value="${formData.sanctionAmt}"/> --%>
                 	 <input id="sanctionAmt1" name="sanctionAmt" type="hidden" value="${formData.sanctionAmt}"> 
                   
							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<!-- <div id="sucessMsg" style="color: green;"></div> -->

										<label>Member Id :</label>
										<form:input path="memberId" id="memberId" value="${memberId}"
											size="20" readonly="true" style="text-align: right"
											class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>CGPAN :</label>
										<form:input path="cgpan" id="cgpan" value="" size="20"
											readonly="true" class="form-control cus-control" />
									</div>
								</div>
							</div>


							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Lending NBFC Name :</label>
										<form:input path="lendingNbfcName" id="lendingNbfcName"
											value="${formData.lendingNbfcName}" size="20" readonly="true"
											class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>City :</label>
										<form:input path="city" id="city" value="${formData.city}"
											size="20" readonly="true" class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Complete Address : </label>
										<form:input path="address" id="address"
											value="${formData.address}" size="20" readonly="true"
											class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>District :</label>
										<form:input path="district" id="district"
											value="${formData.district}" size="20" readonly="true"
											class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">

									<div class="col-md-12 prl-10">
										<label>State :</label>
										<form:input path="state" id="state" value="${formData.state}"
											size="20" readonly="true" class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">

									<div class="col-md-12 prl-10">
										<label>Email :</label>
										<form:input path="email" id="email" value="${formData.email}"
											size="20" readonly="true" class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">

									<div class="col-md-12 prl-10">
										<label>Telephone No :</label>
										<form:input path="telephoneNo" id="telephoneNo"
											value="${formData.telephoneNo}" size="20" readonly="true"
											class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>GSTIN No :</label>
										<form:input path="gstinNo" id="gstinNo"
											value="${formData.gstinNo}" size="20" readonly="true"
											class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Dealing Officer Name<span style="color: red">*</span>
											:
										</label>
										<form:input path="dealingOfficerName" id="dealingOfficerName"
											value="${formData1.dealingOfficerName}"
											size="20" class="form-control cus-control" onchange="disableField(this.value);" />
										<div id="requiredDealingOfficerName"
											Class="displayErrorMessageInRedColor"></div>
									</div>
								</div>
							</div>
							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Dealing Officer Number<span style="color: red">*</span>
											:
										</label>
										<form:input path="dealingOfficerNO" id="dealingOfficerNO" maxlength="10"
											value="${formData1.dealingOfficerNO}"
											onkeypress="return isNumberKey(event,this)" size="11"
											class="form-control cus-control"
											onchange="claimLodgementFormValidation();" />
										<div id="requireddealingOfficerNO"
											Class="displayErrorMessageInRedColor"></div>
									</div>
								</div>
							</div>
							<div class="clearfix"></div>

							<h5 class="sub-head">
								<strong> Borrower's /Unit's Details: </strong>
							</h5>
							<hr style="margin: 5px 0; border: 1px solid #d8d8d8">

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Name of the Borrower/Unit :</label>
										<form:input path="nameOfBorrower" id="nameOfBorrower"
											value="${formData.nameOfBorrower}" size="20" readonly="true"
											class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Complete Address :</label>
										<form:input path="adressOfBorrower" id="adressOfBorrower"
											value="${formData.adressOfBorrower}" size="20"
											readonly="true" class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>City :</label>
										<form:input path="cityOfBorrower" id="cityOfBorrower"
											value="${formData.cityOfBorrower}" size="20" readonly="true"
											class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>District :</label>
										<form:input path="districtOfBorrower" id="districtOfBorrower"
											value="${formData.districtOfBorrower}" size="20"
											readonly="true" class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>State :</label>
										<form:input path="stateOfBorrower" id="stateOfBorrower"
											value="${formData.stateOfBorrower}" size="20" readonly="true"
											class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-1 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>PinCode :</label>
										<form:input path="pincodeOfBorrower" id="pincodeOfBorrower"
											value="${formData.pincodeOfBorrower}" size="20"
											readonly="true" class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="clearfix"></div>
							<h5 class="sub-head">
								<strong> Status of Account(s): </strong>
							</h5>
							<hr style="margin: 5px 0; border: 1px solid #d8d8d8">

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Latest Outstanding Guaranteed Amount :</label>
										<form:input path="latestOsGuranteeAmt"
											id="latestOsGuranteeAmt"
											value="${formData.latestOsGuranteeAmtVStr}" size="20"
											readonly="true" class="form-control cus-control" />
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Date on which Account was Classified as NPA :</label>
										<form:input path="dateOfNPA" id="dateOfNPA"
											value="${formData.dateOfNPA}" size="20" readonly="true"
											class="form-control cus-control" />
									</div>
								</div>
							</div>
							
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Reasons for Account turning NPA: </label> <label
											class="d-block"> <form:input path="reasonOfNPA"
												value="${formData.reasonOfNPA}" size="20" id="reasonOfNPA"
												class="form-control cus-control" placeholder=""
												readonly="true" /></label>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<!-- <div id="requiredDateOfNotice"  Class="displayErrorMessageInRedColor"></div> -->
										<div id="requiredDateOfNotice1"  Class="displayErrorMessageInRedColor"></div>
										<label>Date of issue of Recall Notice:
										</label> <label class="d-block"> <form:input
												style="font-size: 12px;" path="dateOfNotice"
												id="dateOfNotice" value="${formData1.dateOfNotice}"
												size="20" class="date-picker form-control cus-control"
												placeholder="e.g:dd/mm/yyyy" /></label>
									</div>
								</div>
							</div>
							
							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div id="requiredWilfulDefaulter"
											Class="displayErrorMessageInRedColor"></div>
										<label>Wilful defaulter<span style="color: red">*</span>:
										</label> <label class="d-block"> <c:choose>
												<c:when test="${formData1.wilfulDefaulter=='Y'}">
													<form:radiobutton path="wilfulDefaulter"
														id="wilfulDefaulter1" value="Y" checked="checked" />Yes
												 		<form:radiobutton path="wilfulDefaulter"
														id="wilfulDefaulter2" value="N" />No
												 	</c:when>
												<c:when test="${formData1.wilfulDefaulter=='N'}">
													<form:radiobutton path="wilfulDefaulter"
														id="wilfulDefaulter1" value="Y" />Yes 
												 		<form:radiobutton path="wilfulDefaulter"
														id="wilfulDefaulter2" value="N" checked="checked" />No
												 	</c:when>
												<c:otherwise>
													<form:radiobutton path="wilfulDefaulter"
														id="wilfulDefaulter1" value="Y" />Yes 
												 		<form:radiobutton path="wilfulDefaulter"
														id="wilfulDefaulter2" value="N" />No
												</c:otherwise>
											</c:choose>
										</label>
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div id="requiredFraudAcc"
											Class="displayErrorMessageInRedColor"></div>
										<label>Has the account been classified as fraud<span
											style="color: red">*</span>:
										</label> <label class="d-block"> <c:choose>
												<c:when test="${formData1.fraudAcc=='Y'}">
													<form:radiobutton path="fraudAcc" value="Y"
														checked="checked" />Yes
												 		<form:radiobutton path="fraudAcc" value="N" />No
												 		</c:when>
												<c:when test="${formData1.fraudAcc=='N'}">
													<form:radiobutton path="fraudAcc" value="Y" />Yes 
												 		<form:radiobutton path="fraudAcc" value="N"
														checked="checked" />No
												 	</c:when>
												<c:otherwise>
													<form:radiobutton path="fraudAcc" value="Y" />Yes 
												 		<form:radiobutton path="fraudAcc" value="N" />No
												 	</c:otherwise>
											</c:choose>
										</label>
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div id="requiredEnquiryConcluded"
											Class="displayErrorMessageInRedColor"></div>
										<label>Internal and/or external enquiry has been
											concluded<span style="color: red">*</span>:
										</label> <label class="d-block"> <c:choose>
												<c:when test="${formData1.enquiryConcluded=='Y'}">
													<form:radiobutton path="enquiryConcluded" value="Y"
														checked="checked" />Yes 
												 		<form:radiobutton path="enquiryConcluded" value="N" />No
												 	</c:when>
												<c:when test="${formData1.enquiryConcluded=='N'}">
													<form:radiobutton path="enquiryConcluded" value="Y" />Yes 
												 		<form:radiobutton path="enquiryConcluded" value="N"
														checked="checked" />No
												 	</c:when>
												<c:otherwise>
													<form:radiobutton path="enquiryConcluded" value="Y" />Yes 
												 		<form:radiobutton path="enquiryConcluded" value="N" />No
												 	</c:otherwise>
											</c:choose>
										</label>
									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div id="requiredLliReported"
											Class="displayErrorMessageInRedColor"></div>
										<label>Involvement of staff of MLI has been reported<span
											style="color: red">*</span>:
										</label> <label class="d-block"> <c:choose>
												<c:when test="${formData1.mliReported=='Y'}">
													<form:radiobutton path="mliReported" value="Y"
														checked="checked" />Yes 
											 			<form:radiobutton path="mliReported" value="N" />No
											 		</c:when>
												<c:when test="${formData1.mliReported=='N'}">
													<form:radiobutton path="mliReported" value="Y" />Yes 
											 			<form:radiobutton path="mliReported" value="N"
														checked="checked" />No
											 		</c:when>
												<c:otherwise>
													<form:radiobutton path="mliReported" value="Y" />Yes 
											 		<form:radiobutton path="mliReported" value="N" />No
											 	</c:otherwise>
											</c:choose>
										</label>
									</div>
								</div>
							</div>
                            <div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div id="RequiredinternalRating11" Class="displayErrorMessageInRedColor"></div>
										<label class="d-block">Internal Rating :</label>
										<form:input path="internalRating1" id="internalRating1"
											value="${formData1.internalRating1}"  
											 class="form-control cus-control" />
									</div>
								</div>
							</div>
							<div class="clearfix"></div>
							<h5 class="sub-head">
								<strong> Details of Legal Proceedings: </strong>
							</h5>
							<hr style="margin: 5px 0; border: 1px solid #d8d8d8">



							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div id="requiredForum" Class="displayErrorMessageInRedColor"></div>
										<label class="d-block">Forum through which legal
											proceedings were initiated against borrower :<span
											style="color: red">*</span>
										</label>
										<form:select path="forum" id="forum" disabled="false"
											class="form-control cus-control">
											<form:option value="NONE" label="---Select Forum---" />
											<form:option value="SARFAESI ACT 13(4)" label="SARFAESI ACT 13(4)" />
											<form:option value="CIVIL COURT" label="CIVIL COURT" />
											<form:option value="DRT" label="DRT" />
											<form:option value="LOK ADALAT" label="LOK ADALAT" />
											<form:option value="ARBITRATION PROCEEDING" label="ARBITRATION PROCEEDING" />
											<form:option value="REVENUE RECOVERY AUTHORITY"
												label="REVENUE RECOVERY AUTHORITY" />
										<%-- 	<form:option value="SECURATIATION ACT 2002"
												label="SECURITIZATION ACT 2002" /> --%>
												<form:option value="IRREVOCABLE DEMAND NOTICE "
												label=" IRREVOCABLE DEMAND NOTICE " />


										</form:select>
									</div>
								</div>
							</div>
							
					


							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div id="requiredSuitNo" Class="displayErrorMessageInRedColor"></div>
										<label>Suit / Case Registration No.<span
											style="color: red">*</span>:
										</label> <label class="d-block"> <form:input path="suitNo"
												id="suitNo" value="${formData1.suitNo}" size="20"
												class="form-control cus-control" onkeypress="return validateKeypress(alphanumeric);"/></label>
									</div>
								</div>

							</div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div id="requiredResonFillingSuit"
											Class="displayErrorMessageInRedColor"></div>
										<label>Provide satisfactory reason for Initiation of legal action before NPA date(if applicable)<span style="color: red">*</span>:</label> 
										<label class="d-block"> <form:input
												path="resonFillingSuit" id="resonFillingSuit"
												value="${formData1.resonFillingSuit}"
												size="20" class="form-control cus-control" /></label>
									</div>
								</div>
							</div>
					
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div id="requiredsDateOfSarfaesi"
											Class="displayErrorMessageInRedColor"></div>
										<label>Date of legal file/ Date of possession of assets under SARFAESI act/Demand Notice<span
											style="color: red">*</span>:
										</label> <label class="d-block"> <form:input
												path="dateOfSarfaesi" id="dateOfSarfaesi" size="20"
												value="${formData1.dateOfSarfaesi}"
												class="date-picker form-control cus-control"
												placeholder="e.g:dd/mm/yyyy" onchange="disableField(this.value);"/></label>
									</div>
								</div>
							</div>
							<div class="clearfix"></div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div id="requiredLocationOfForum"
											Class="displayErrorMessageInRedColor"></div>
										<label>Location of Legal forum<span style="color: red">*</span>:
										</label> <label class="d-block"> <form:input
												path="locationOfForum" id="locationOfForum"
												value="${formData1.locationOfForum}"
												size="20" class="form-control cus-control" /></label>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div id="requiredClaimAmt"
											Class="displayErrorMessageInRedColor"></div>
										<label>Amount Claimed in the Demand
											Notice/Suit(in&#8377;): <span style="color: red">*</span>
										</label> <label class="d-block"> <form:input type="number"
												path="claimAmt" id="claimAmt" value="${formData1.claimAmt}"
												size="20" class="form-control cus-control" min="0"
												onkeypress="return isNumberKey(event,this)" />

										</label>
									</div>
								</div>
							</div>
							
						
							

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Legal Attachments in PDF Format(Demand Notice/legal documents:<span
											style="color: red">*</span>
										</label> <label class="d-block">
											<div id="requiredfile" Class="displayErrorMessageInRedColor"></div>
												<div id="requiredfile1" Class="displayErrorMessageInRedColor"></div>
											<td><input type="file" name="file" id="file" onchange="checkextension()"
												class="form-control cus-control"></input></td>
										</label>
									</div>
								</div>
							</div>
							
							<div class="clearfix"></div>
							<h5 class="sub-head">
								<strong> Subsidy Details : </strong>
							</h5>
							<hr style="margin: 5px 0; border: 1px solid #d8d8d8">

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div id="requiredAnySubsidyInvolved"
											Class="displayErrorMessageInRedColor"></div>
										<label>Does the project covered under CGTMSE
											guarantee, involve any subsidy?<span style="color: red">*</span>
										</label> <label class="d-block"> <form:radiobutton
												path="anySubsidyInvolved" id="anySubsidyInvolved" value="Y"
												onclick="claimLodgementFormValidatiOnSubmit(this.value);disableField(this.value)" />Yes
											<form:radiobutton path="anySubsidyInvolved"
												id="anySubsidyInvolved" value="N"
												onclick="claimLodgementFormValidatiOnSubmit(this.value);disableField(this.value)" />No
											
										</label>
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div id="requiredSubsidyReceived"
											Class="displayErrorMessageInRedColor"></div>
										<label>Has the subsidy been received after NPA?<span
											style="color: red">*</span></label> <label class="d-block"> <c:choose>
												<c:when test="${formData1.subsidyReceived=='Y'}">
													<form:radiobutton path="subsidyReceived" value="Y"
														checked="checked" />Yes 
											 		<form:radiobutton path="subsidyReceived" value="N" />No
											 	</c:when>
												<c:when test="${formData1.subsidyReceived=='N'}">
													<form:radiobutton path="subsidyReceived" value="Y" />Yes 
											 		<form:radiobutton path="subsidyReceived" value="N"
														checked="checked" />No
											 	</c:when>
												<c:otherwise>
													<form:radiobutton path="subsidyReceived" value="Y" />Yes 
											 		<form:radiobutton path="subsidyReceived" value="N" />No
											 	</c:otherwise>
											</c:choose>
										</label>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div id="requiredSubsidyAdjust"
											Class="displayErrorMessageInRedColor"></div>
										<label>Has the subsidy been adjusted against
											principal/interest over dues?<span style="color: red">*</span>
										</label> <label class="d-block"> <c:choose>
												<c:when test="${formData1.subsidyAdjust=='Y'}">
													<form:radiobutton path="subsidyAdjust" value="Y" id="subsidyAdjust"
														checked="checked" onclick="calTotOutstandingAsOnDateOfLodgementOfClaim(this)"/>Yes 
												 			<form:radiobutton path="subsidyAdjust" value="N" id="subsidyAdjust" onclick="calTotOutstandingAsOnDateOfLodgementOfClaim(this)"/>No
												 		</c:when>
												<c:when test="${formData1.subsidyAdjust=='N'}">
													<form:radiobutton path="subsidyAdjust" value="Y" id="subsidyAdjust" onclick="calTotOutstandingAsOnDateOfLodgementOfClaim(this)"/>Yes 
												 		<form:radiobutton path="subsidyAdjust" value="N" id="subsidyAdjust"
														checked="checked" onclick="calTotOutstandingAsOnDateOfLodgementOfClaim(this)"/>No
												 	</c:when>
												<c:otherwise>
													<form:radiobutton path="subsidyAdjust" value="Y" id="subsidyAdjust" onclick="calTotOutstandingAsOnDateOfLodgementOfClaim(this)"/>Yes 
												 		<form:radiobutton path="subsidyAdjust" value="N" id="subsidyAdjust" onclick="calTotOutstandingAsOnDateOfLodgementOfClaim(this)"/>No
												 	</c:otherwise>
											</c:choose>
										</label>
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div id="requireddateOfSubsidy"
											Class="displayErrorMessageInRedColor"></div>
										<label>Subsidy Credit Date:</label> <label class="d-block">
											<form:input path="dateOfSubsidy" id="dateOfSubsidy"
												value="${formData1.dateOfSubsidy}" size="20"
												class="date-picker form-control cus-control"
												placeholder="e.g:dd/mm/yyyy" />
										</label>
									</div>
								</div>

							</div>

							<div class="col-md-2 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div id="requiredSubsidyAmt"
											Class="displayErrorMessageInRedColor"></div>
										<label>Subsidy Amount(in&#8377;): <span
											style="color: red">*</span>:
										</label> <label class="d-block"> <form:input path="subsidyAmt"
												id="subsidyAmt" value="${formData1.subsidyAmt}" size="20"
												class="form-control cus-control" type="number" min="0"
												onchange="calTotOutstandingAsOnDateOfLodgementOfClaim(this.value);"
												onkeypress="return isNumberKey(event,this)" /></label>
									</div>
								</div>
							</div>

							<div class="clearfix"></div>
							<h5 class="sub-head">
								<strong> Term Loan (TL) : </strong>
							</h5>
							<hr style="margin: 5px 0; border: 1px solid #d8d8d8">
							<table class="table table-bordered table-hover cus-table mb-0">
								<thead>
									<tr>
										<th style="width: 150px;">CGPAN</th>
										<th>Latest Outstanding Guaranteed Amount (in &#8377;)<br>
											A
										</th>
										<th>Total outstanding as on date of NPA(in &#8377;)<br>
											B
										</th>
										<th>Repayment/Recovery made from Borrower / Unit after account
											classified as NPA(in &#8377;)<span style="color: red">*</span>
											<br>C
										</th>
										<th>Subsidy
											Amount(in &#8377;)<span style="color: red">*</span>
											<br>E
										</th>
										<th>Mode of recovery<span style="color: red">*</span></th>
										<th>Outstanding As On Date of Lodgement of Claim (in
											&#8377;)<br> D=(Least of A/B)-C-E
										</th>
									</tr>
								</thead>
								<tr>
									<td><form:input path="cgPanTL" id="cgPanTL" style="padding:2px;"
											value="${formData.cgpan}" size="20" readonly="true"
											class="form-control cus-control" /></td>
									<td><form:input path="latestOsAmt" id="latestOsAmt"
											value="${formData.latestOsGuranteeAmtVStr}" readonly="true"
											class="form-control cus-control" /></td>
									<td><form:input path="totalOsAmt" id="totalOsAmt"
											value="${formData.totalOsAmtStr}" size="20" readonly="true"
											class="form-control cus-control" /></td>
									<td>
										<div id="" Class="displayErrorMessageInRedColor"></div>
											 <form:input
											path="recovery" id="recovery" value="${formData1.recovery}"
											size="20" class="form-control cus-control" type="number"
											min="0"
											onchange="calTotOutstandingAsOnDateOfLodgementOfClaim(this.value);"
											onkeypress="return isNumberKey(event,this)" />
									</td>
												
									<td><form:input path="subsidyAmt"
												id="subsidyAmt1" value="${formData1.subsidyAmt}" size="20"
												class="form-control cus-control" type="number" min="0"
												onkeypress="return isNumberKey(event,this)" readonly="true"/></td>
									<td>
										<div id="requiredmodeRecovery"
											Class="displayErrorMessageInRedColor"></div> 
												<form:select path="modeRecovery" id="modeRecovery" 
											class="form-control cus-control">
											<form:option value="Select" label="---Select---" />
											<form:option value="SARFAESI Act u/s 13(4)" label="SARFAESI Act u/s 13(4)" />
											<form:option value="Arbitration Proceedings" label="Arbitration Proceedings" />
											<form:option value="Irrevocable Demand notice" label="Irrevocable Demand notice" />
											<form:option value="Sale of Asset" label="Sale of Asset" />
											
										
										</form:select>

									</td>
									<td><form:input path="osAmtClaim" id="osAmtClaim"
											value="${formData1.osAmtClaim}" size="20" readonly="true"
											class="form-control cus-control" /></td>
								</tr>
							</table>
							<div class="clearfix"></div>
							<h5 class="sub-head">
								<strong> </strong>
							</h5>
							<hr style="margin: 5px 0; border: 1px solid #d8d8d8">
							<div class="col-md-6 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div id="requiredNpaRecoveryFlag"
											Class="displayErrorMessageInRedColor"></div>
										<label class="d-block"> Have you ensured inclusion of
											unappropriated receipts also in the amount of recovery after
											NPA indicated above?:<span style="color: red">*</span>
										</label>
										<c:choose>
											<c:when test="${formData1.npaRecoveryFlag=='Y'}">
												<form:radiobutton path="npaRecoveryFlag" value="Y"
													checked="checked" />Yes 
											 	<form:radiobutton path="npaRecoveryFlag" value="N" />No
											 	<form:radiobutton path="npaRecoveryFlag" value="NA"
													checked="checked" />NA
											</c:when>
											<c:when test="${formData1.npaRecoveryFlag=='N'}">
												<form:radiobutton path="npaRecoveryFlag" value="Y" />Yes
												<form:radiobutton path="npaRecoveryFlag" value="N"
													checked="checked" />No
												<form:radiobutton path="npaRecoveryFlag" value="NA" />NA
											</c:when>
											<c:when test="${formData1.npaRecoveryFlag=='NA'}">
												<form:radiobutton path="npaRecoveryFlag" value="Y" />Yes
												<form:radiobutton path="npaRecoveryFlag" value="N" />No
												<form:radiobutton path="npaRecoveryFlag" value="NA"
													checked="checked" />NA
											</c:when>
											<c:otherwise>
												<form:radiobutton path="npaRecoveryFlag" value="Y" />Yes
												<form:radiobutton path="npaRecoveryFlag" value="N" />No
												<form:radiobutton path="npaRecoveryFlag" value="NA" />NA
											 </c:otherwise>
										</c:choose>

									</div>
								</div>
							</div>

							<div class="col-md-6 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div id="requiredConfirmRecoveryFlag"
											Class="displayErrorMessageInRedColor"></div>
										<label class="d-block"> Do you confirm feeding of
											correct value as recoveries after NPA?:<span
											style="color: red">*</span>
										</label>
										<c:choose>
											<c:when test="${formData1.confirmRecoveryFlag=='Y'}">
												<form:radiobutton path="confirmRecoveryFlag" value="Y"
													checked="checked" />Yes
												<form:radiobutton path="confirmRecoveryFlag" value="N" />No
											</c:when>
											<c:when test="${formData1.confirmRecoveryFlag=='N'}">
												<form:radiobutton path="confirmRecoveryFlag" value="Y" />Yes
												<form:radiobutton path="confirmRecoveryFlag" value="N"
													checked="checked" />No
											 </c:when>
											<c:otherwise>
												<form:radiobutton path="confirmRecoveryFlag" value="Y" />Yes
												<form:radiobutton path="confirmRecoveryFlag" value="N" />No
											</c:otherwise>
										</c:choose>
									</div>
								</div>
							</div>

							<div class="clearfix"></div>
							<hr style="margin: 5px 0; border: 1px solid #d8d8d8">
							<h5 class="sub-head">
								<strong>Security and Personal Guarantee Details:</strong>
							</h5>
						<div class="table-responsive">
							<table class="table table-bordered table-hover cus-table mb-0">
								<thead>
									<tr>
										<th style="max-width: 100px">Particulars</th>
										<th style="max-width: 970px;">Security Nature Value (in
											&#8377;)</th>
										<th style="max-width: 160px;">Networth of
											Guarantor/Promoter(in &#8377;)</span>
										</th>
										<th style="max-width: 200px;">Reasons for Reduction in
											the value of Security, if any<span style="color: red">*</span>
										</th>
									</tr>
									<tr>

									</tr>
								</thead>
								<tr>
									<td><label class="d-block">As on Date of Sanction
											of Credit:</label></td>
									<td style="padding: 0 !important">
										<table class="table table-bordered table-hover cus-table mb-0">
											<tr>
												<th>Land:</th>
												<th>Building:</th>
												<th>Plant and Machinery/Equipments:</th>
												<th>Other Fixed/Movable Assets:</th>
												<th>Current Assets:</th>
												<th>Others:</th>
											</tr>
											<tr>
												<td><form:input path="landValue" id="landValue"
														size="10" value="${formData.landValueStr}" readonly="true"
														style="text-align: right" class="form-control cus-control" />
													<p id="landValueerror"
														Class="displayErrorMessageInRedColor"></p></td>
												<td><form:input path="buildingValue" id="buildingValue"
														value="${formData.buildingValueStr}" size="10"
														readonly="true" style="text-align: right"
														class="form-control cus-control"
														onchange="caltotalSecurity(this);"
														oninput="validity.valid||(value='');" /></td>
												<td><form:input path="planetValue" id="planetValue"
														value="${formData.planetValueStr}" size="10"
														readonly="true" style="text-align: right"
														class="form-control cus-control"
														onchange="caltotalSecurity(this);"
														oninput="validity.valid||(value='');" /></td>
												<td><form:input path="otherAssetValue"
														id="otherAssetValue"
														value="${formData.otherAssetValueStr}" size="10"
														readonly="true" style="text-align: right"
														class="form-control cus-control"
														onchange="caltotalSecurity(this);"
														oninput="validity.valid||(value='');" /></td>
												<td><form:input path="currentAssetValue"
														id="currentAssetValue"
														value="${formData.currentAssetValueStr}" size="10"
														readonly="true" style="text-align: right"
														class="form-control cus-control"
														onchange="caltotalSecurity(this);"
														oninput="validity.valid||(value='');" /></td>
												<td><form:input path="othersValue" size="10"
														id="othersValue" value="${formData.othersValueStr}"
														readonly="true" class="form-control cus-control"
														style="text-align: right"
														onchange="caltotalSecurity(this);"
														oninput="validity.valid||(value='');" /></td>
											</tr>
										</table>
									</td>
									<td><form:input path="networthOfPromotor"
											id="networthOfPromotor"
											value="${formData.networthOfPromotorStr}" size="10"
											readonly="true" style="text-align: right"
											class="form-control cus-control" /></td>
									<td>
											<form:select  path="reductionReason" id="reductionReason"  class="form-control cus-control" readonly="true">
												${formData.reductionReason}
												<form:option value="--Select--"  />
											</form:select>
									</td>
								</tr>

								<tr>
									<td></td>
									<td><label> Total: </label> <form:input
											path="totalSecuritydetails" size="10"
											style="margin-left:5px; text-align: right; display:inline; width:50%;"
											class="form-control cus-control"
											value="${formData.totalSecuritydetailsStr}"
											id="totalSecuritydetails"
											oninput="validity.valid||(value='');" readonly="true" /></td>
								</tr>

								<tr>
									<td><label class="d-block">As on Date of NPA:</label></td>

									<td style="padding: 0 !important">
										<table class="table table-bordered table-hover cus-table mb-0">
											<tr style="">
												<th>Land:</th>
												<th>Building:</th>
												<th>Plant and Machinery/Equipments:</th>
												<th>Other Fixed/Movable Assets:</th>
												<th>Current Assets:</th>
												<th>Others:</th>
											</tr>
											<tr>
												<td><form:input path="landValue1" id="landValue1"
														value="${formData.landValue1Str}" size="10"
														readonly="true" style="text-align: right"
														class="form-control cus-control"
														oninput="validity.valid||(value='');" />
													<p id="landValueerror"
														Class="displayErrorMessageInRedColor"></p></td>
												<td><form:input path="buildingValue1"
														id="buildingValue1" value="${formData.buildingValue1Str}"
														size="10" readonly="true" style="text-align: right"
														class="form-control cus-control"
														oninput="validity.valid||(value='');" /></td>
												<td><form:input path="planetValue1" id="planetValue1"
														value="${formData.planetValue1Str}" size="10"
														readonly="true" style="text-align: right"
														class="form-control cus-control"
														oninput="validity.valid||(value='');" /></td>
												<td><form:input path="otherAssetValue1"
														id="otherAssetValue1"
														value="${formData.otherAssetValue1Str}" size="10"
														readonly="true" style="text-align: right"
														class="form-control cus-control"
														oninput="validity.valid||(value='');" /></td>
												<td><form:input path="currentAssetValue1"
														id="currentAssetValue1"
														value="${formData.currentAssetValue1Str}" size="10"
														readonly="true" style="text-align: right"
														class="form-control cus-control"
														oninput="validity.valid||(value='');" /></td>
												<td><form:input path="othersValue1" id="othersValue1"
														value="${formData.othersValue1Str}" size="20"
														style="text-align: right" readonly="true"
														class="form-control cus-control"
														oninput="validity.valid||(value='');" /></td>
											</tr>
										</table>
									</td>

									<td><form:input path="networthOfPromotor1"
											id="networthOfPromotor1"
											value="${formData.networthOfPromotor1Str}" size="20"
											readonly="true" style="text-align: right"
											class="form-control cus-control"
											oninput="validity.valid||(value='');" /></td>

									<td><form:select path="reductionReason1"
											id="reductionReason1" class="form-control cus-control"
											readonly="true">
											<form:option value="${formData.reductionReason1}" />
										</form:select></td>
								</tr>
								<tr>
									<td></td>
									<td><label>Total:</label> <form:input
											path="totalSecuritydetails1" id="totalSecuritydetails1"
											value="${formData.totalSecuritydetails1Str}" size="20"
											style="display:inline; margin-left:5px; text-align: right; width:50%;"
											class="form-control cus-control"
											oninput="validity.valid||(value='');" readonly="true" /></td>
								</tr>
								<tr>
									<td><label class="d-block">As on Date of
											Preferment of Claim:</label></td>

									<td style="padding: 0 !important">
										<table class="table table-bordered table-hover cus-table mb-0">
											<tr style="">
												<th>Land:</th>
												<th>Building:</th>
												<th>Plant and Machinery/Equipments:</th>
												<th>Other Fixed/Movable Assets:</th>
												<th>Current Assets:</th>
												<th>Others:</th>
											</tr>
											<tr>
												<td><form:input path="landValue2" id="landValue2"
														value="${formData1.landValue2Str}" size="10"
														readonly="false" style="text-align: right"
														class="form-control cus-control"
														type="number" min="0" oninput="validity.valid||(value='');" 
														onchange="caltotalPrefermentOfClaim(this);"/></td>
												<td><form:input path="buildingValue2"
														id="buildingValue2" value="${formData1.buildingValue2Str}"
														size="10" readonly="false" style="text-align: right"
														class="form-control cus-control" type="number" min="0"
														oninput="validity.valid||(value='');"
														onchange="caltotalPrefermentOfClaim(this);" /></td>
												<td><form:input path="planetValue2" id="planetValue2"
														value="${formData1.planetValue2Str}" size="10"
														readonly="false" style="text-align: right"
														class="form-control cus-control" type="number" min="0"
														oninput="validity.valid||(value='');"
														onchange="caltotalPrefermentOfClaim(this);" /></td>
												<td><form:input path="otherAssetValue2"
														id="otherAssetValue2"
														value="${formData1.otherAssetValue2Str}" size="10"
														readonly="false" style="text-align: right"
														class="form-control cus-control" type="number" min="0"
														oninput="validity.valid||(value='');"
														onchange="caltotalPrefermentOfClaim(this);" /></td>
												<td><form:input path="currentAssetValue2"
														id="currentAssetValue2"
														value="${formData1.currentAssetValue2Str}" size="10"
														readonly="false" style="text-align: right"
														class="form-control cus-control" type="number" min="0"
														oninput="validity.valid||(value='');"
														onchange="caltotalPrefermentOfClaim(this);" /></td>
												<td><form:input path="othersValue2" id="othersValue2"
														value="${formData1.othersValue2Str}" size="20"
														style="text-align: right" readonly="false"
														class="form-control cus-control" type="number" min="0"
														oninput="validity.valid||(value='');"
														onchange="caltotalPrefermentOfClaim(this);" /></td>
											</tr>
										</table>
									</td>

									<td><form:input path="networthOfPromotor2"
											id="networthOfPromotor2"
											value="${formData1.networthOfPromotor2Str}" size="20"
											readonly="false" style="text-align: right"
											class="form-control cus-control" type="number" min="0"
											oninput="validity.valid||(value='');" /></td>

									<td>
									<div id="requiredreductionReason2"
												Class="displayErrorMessageInRedColor"></div>
									<form:select path="reductionReason2" id="reductionReason2" disabled="false"
											class="form-control cus-control">	
											<form:option value="Select" label="Select" />
											<form:option value="Activity/ Unit closed" label="Activity/ Unit closed" />
											<form:option value="Business Failure" label="Business Failure" />
											<form:option value="Assets disposed" label="Assets disposed" />
											<form:option value="Wear & Tear" label="Wear & Tear" />
											<form:option value="Recession" label="Recession" />
											<form:option value="Obsolete" label="Obsolete" />
											<form:option value="High competition in market" label="High competition in market" />
											<form:option value="Borrower not traceable" label="Borrower not traceable" />
											<form:option value="Depreciation" label="Depreciation" />
											
											<%-- <c:forEach var="value" items="${reductionReason2}">
												<form:option id="${value}" value="${value}">${value}</form:option>
											</c:forEach>
											 --%>
											
										</form:select></td>
								</tr>
								<tr>
									<td></td>
									<td>
									
										<div id="requiredtotalSecuritydetails2"
												Class="displayErrorMessageInRedColor"></div>
									<label>Total:</label> <form:input
											path="totalSecuritydetails2" id="totalSecuritydetails2"
											value="${formData1.totalSecuritydetails2Str}" size="20"
											style="display:inline; margin-left:5px; width:50%; text-align: right"
											class="form-control cus-control"
											oninput="validity.valid||(value='');" readonly="true"
											onchange="caltotalPrefermentOfClaim(this);" /></td>
								</tr>

							</table>
							</div>
							<div class="clearfix"></div>
							<h5 class="sub-head">
								<strong> Total amount for which guarantee claim is
									being preferred: </strong>
							</h5>
							<hr style="margin: 5px 0; border: 1px solid #d8d8d8">

							<table class="table table-bordered table-hover cus-table mb-0">
								<thead>
									<tr>
										<th>CGPAN</th>
										<th>Loan / Limit Covered under CGTMSE (in &#8377;):</th>
										<th>Outstanding As On Date of Lodgement of Claim (in
											&#8377;)<br> A
										</th>
										<th>Guarantee Coverage<br> B
										</th>
										<th>Claim eligibility amount(in &#8377;)<br> A * B =
											C
										</th>
										<th>First installment of claim (in &#8377;)<br> 75%
											of C (Rs.)
										</th>
									</tr>
								</thead>
								<tr>
									<td><form:input path="cgPanGCP" id="cgPanGCP"
											value="${formData.cgpan}" size="20" readonly="true"
											class="form-control cus-control" /></td>
									<td><form:input path="loanLimitCovered"
											id="loanLimitCovered"
											value="${formData.latestOsGuranteeAmtVStr}" size="20"
											readonly="true" class="form-control cus-control" /></td>
									<td><form:input path="osAmtClaimGCP" id="osAmtClaimGCP"
											value="${formData1.osAmtClaimGCPStr}" size="20"
											readonly="true" class="form-control cus-control" /></td>
									<td><form:input path="guaranteeCov" id="guaranteeCov"
											value="${formData.guaranteeCovStr}" size="20" readonly="true"
											class="form-control cus-control" /></td>
									<!--<td><form:input path="osAmtClaimGCP" id="osAmtClaimGCP" value="" size="20"  readonly="true"  class="form-control cus-control" /></td>
										<td><form:input path="eligableAmtClaim" id="eligableAmtClaim" value="" size="20"  readonly="true" class="form-control cus-control" /></td>
										-->

									<td><form:input path="eligableAmtClaim"
											id="eligableAmtClaim"
											value="${formData1.eligableAmtClaimStr}" size="20"
											readonly="true" class="form-control cus-control" /></td>
									<td><form:input path="firstInstallClaim"
											id="firstInstallClaim" value="${formData1.firstInstallClaim}"
											size="20" readonly="true" class="form-control cus-control" /></td>

								</tr>
							</table>
							<div class="clearfix"></div>
							<h5 class="sub-head">
								<strong> Claim Lodgement Check List: </strong>
							</h5>
							<div class="clearfix"></div>
							<div class="table-responsive">
								<table
									class="table table-bordered table-hover cus-table mt-10 mb-0">
									<tr>
										<th style="min-width: 70px;">Sr. No.</th>
										<th style="min-width: 500px;">Description</th>
										<th style="min-width: 90px;">Yes / No</th>
										<th style="min-width: 650px;">Comments/observations</th>
									</tr>
									<tr>
										<td>1</td>
										<td>Activity is eligible as per CGS-II.</td>
									
										<td>
										
											<div id="requiredActivityEligible"
												Class="displayErrorMessageInRedColor"></div>
											<form:radiobutton path="activityEligible" value="Y" onclick="claimLodgementCheckListValidation()"/>Yes
											<form:radiobutton path="activityEligible" value="N" onclick="claimLodgementCheckListValidation()"/>No <%-- 	<c:choose>
												<c:when test="${formData2.activityEligible=='Y'}">
													<form:radiobutton path="activityEligible" value="Y" checked="checked"  />Yes
													<form:radiobutton path="activityEligible" value="N" />No
											 	</c:when>
												<c:when test="${formData2.activityEligible=='N'}">
													<form:radiobutton path="activityEligible" value="Y" />Yes
													<form:radiobutton path="activityEligible" value="N" checked="checked" />No
											 	</c:when>
												<c:otherwise>
													<form:radiobutton path="activityEligible" value="Y" checked="checked" />Yes
													<form:radiobutton path="activityEligible" value="N" />No
											 	</c:otherwise>
											</c:choose> --%></td>
										<!--  --><td>
											<div id="requiredActivityEligibleRemarks" Class="displayErrorMessageInRedColor"></div> 
												<form:input
												path="activityEligibleRemarks" id="activityEligibleRemarks"
												value="${formData2.activityEligibleRemarks}"
												class="form-control cus-control" />
										</td>
									</tr>
									<tr>
										<td>2</td>
										<td>Whether Credit bureau check done/CIR/KYC obtained and findings are
											satisfactory.</td>
										<td>
										<div id="requiredcibil"
												Class="displayErrorMessageInRedColor"></div>
										
										<form:radiobutton path="cibil" value="Y" onclick="claimLodgementCheckListValidation()"/>Yes
										 <form:radiobutton
												path="cibil" value="N" onclick="claimLodgementCheckListValidation()"/>No </td>
										<td>
											<div id="requiredCibilRemarks"
												Class="displayErrorMessageInRedColor"></div> <form:input
												path="cibilRemarks" id="cibilRemarks"
												value="${formData2.cibilRemarks}"
												class="form-control cus-control" />
										</td>
									</tr>
									<tr>
										<td>3</td>
										<td>Rate charged on loan is as per RBI  guidelines.</td>
										<td>
										<div id="requiredrateCharge"
												Class="displayErrorMessageInRedColor"></div>
										<form:radiobutton path="rateCharge" value="Y" onclick="claimLodgementCheckListValidation()"/>Yes
											<form:radiobutton path="rateCharge" value="N" onclick="claimLodgementCheckListValidation()"/>No </td>
										<td>
											<div id="requiredRateChargeRemarks"
												Class="displayErrorMessageInRedColor"></div> <form:input
												path="rateChargeRemarks" id="rateChargeRemarks"
												value="${formData2.rateChargeRemarks}"
												class="form-control cus-control" />
										</td>
									</tr>
									<tr>
										<td>4</td>
										<td>Date of NPA as fed in the system is as per RBI
											guidelines.</td>
										<td>
										<div id="requireddateOfNPAAsRBI"
												Class="displayErrorMessageInRedColor"></div>
										<form:radiobutton path="dateOfNPAAsRBI" value="Y" onclick="claimLodgementCheckListValidation()"/>Yes
											<form:radiobutton path="dateOfNPAAsRBI" value="N" onclick="claimLodgementCheckListValidation()"/>No
										<td>
											<div id="requiredDateOfNPAAsRBIRemarks"
												Class="displayErrorMessageInRedColor"></div> <form:input
												path="dateOfNPAAsRBIRemarks" id="dateOfNPAAsRBIRemarks"
												value="${formData2.dateOfNPAAsRBIRemarks}"
												class="form-control cus-control" />
										</td>
									</tr>
									<%-- <tr>
										<td>5</td>
										<td>Whether outstanding amount mentioned in the claim
											application form is with respect to the NPA date as reported
											in the claim form.</td>
										<td>
										<div id="requiredwhetherOutstanding" Class="displayErrorMessageInRedColor"></div>
										<form:radiobutton path="whetherOutstanding" value="Y" onclick="claimLodgementCheckListValidation()"/>Yes
											<form:radiobutton path="whetherOutstanding" value="N" onclick="claimLodgementCheckListValidation()"/>No </td>
										<td>
											<div id="requiredWhetherOutstandingRemarks"
												Class="displayErrorMessageInRedColor"></div> <form:input
												path="whetherOutstandingRemarks"
												id="whetherOutstandingRemarks"
												value="${formData2.whetherOutstandingRemarks}"
												class="form-control cus-control" />
										</td>
									</tr> --%>
									<tr>
										<td>5</td>
										<td>Whether serious deficiencies /irregularities observed
											in the matter of appraisal/renewal/disbursement/follow
											up/conduct of the account.</td>
										<td>
										
										<div id="requiredapprisalDisbursement" Class="displayErrorMessageInRedColor"></div>
										<form:radiobutton path="apprisalDisbursement"
												value="Y" onclick="claimLodgementCheckListValidation()"/>Yes <form:radiobutton
												path="apprisalDisbursement" value="N" onclick="claimLodgementCheckListValidation()"/>No </td>

										<td>
											<div id="requiredApprisalDisbursementRemarks"
												Class="displayErrorMessageInRedColor"></div> <form:input
												path="apprisalDisbursementRemarks"
												id="apprisalDisbursementRemarks"
												value="${formData2.apprisalDisbursementRemarks}"
												class="form-control cus-control" />
										</td>
									</tr>
									<tr>
										<td>6</td>
										<td>Major deficiencies observed in Pre-sanction/Post
											disbursement inspections.</td>
										<td>
										<div id="requiredpostDisbursement" Class="displayErrorMessageInRedColor"></div>
										<form:radiobutton path="postDisbursement" value="Y" onclick="claimLodgementCheckListValidation()"/>Yes
											<form:radiobutton path="postDisbursement" value="N" onclick="claimLodgementCheckListValidation()"/>No</td>
										<td>
											<div id="requiredpostDisbursementremark"
												Class="displayErrorMessageInRedColor"></div> <form:input
												path="postDisbursementRemarks" id="postDisbursementRemarks"
												value="${formData2.postDisbursementRemarks}"
												class="form-control cus-control" />
										</td>
									</tr>
									<tr>
										<td>7</td>
										<td>Whether deficiencies observed on part of internal
											staff as per Staff accountability exercise carried out.</td>
										<td>
										<div id="requiredexerciseCarried" Class="displayErrorMessageInRedColor"></div>
										<form:radiobutton path="exerciseCarried" value="Y" onclick="claimLodgementCheckListValidation()"/>Yes
											<form:radiobutton path="exerciseCarried" value="N" onclick="claimLodgementCheckListValidation()"/>No </td>
										<td>
											<div id="requiredExerciseCarriedRemarks"
												Class="displayErrorMessageInRedColor"></div> <form:input
												path="exerciseCarriedRemarks" id="exerciseCarriedRemarks"
												value="${formData2.exerciseCarriedRemarks}"
												class="form-control cus-control" />
										</td>
									</tr>
									<tr>
										<td>8</td>
										<td>Internal rating was carried out and proposal was found of Investment Grade. (applicable for Loans sanctioned above 50 Lakh) (Mentioned rating in comments column.)</td>
										<td>
										<div id="requiredinternalRating" Class="displayErrorMessageInRedColor"></div>
									
										    <form:radiobutton path="internalRating" value="Y" onclick="disableField(this.value);claimLodgementCheckListValidation();"/>Yes
											<form:radiobutton path="internalRating" value="N" onclick="disableField(this.value);claimLodgementCheckListValidation();"/>No 
											<form:radiobutton path="internalRating" id ="internalNA" value="NA" onclick="disableField(this.value);claimLodgementCheckListValidation();"/>NA </td>
									
										<td>
											<div id="requiredInternalRatingRemarks"
												Class="displayErrorMessageInRedColor"></div> <form:input
												path="internalRatingRemarks" id="internalRatingRemarks"
												value="${formData2.internalRatingRemarks}"
												class="form-control cus-control" />
										</td>
									</tr>
									<tr>
										<td>9</td>
										<td>Whether all the recoveries pertaining to the account
											after the date of NPA and before the claim lodgement have
											been duly incorporated in the claim form.</td>
										<td>
										<div id="requiredrecoverPertaining"
												Class="displayErrorMessageInRedColor"></div>
										<form:radiobutton path="recoverPertaining" value="Y" onclick="claimLodgementCheckListValidation()"/>Yes
											<form:radiobutton path="recoverPertaining" value="N" onclick="claimLodgementCheckListValidation()"/>No </td>
										<td>
											<div id="requiredRecoverPertainingRemarks"
												Class="displayErrorMessageInRedColor"></div> <form:input
												path="recoverPertainingRemarks"
												id="recoverPertainingRemarks"
												value="${formData2.recoverPertainingRemarks}"
												style="width:500px; height: 50px;"
												class="form-control cus-control" />
										</td>

									</tr>

								</table>
							</div>

							<div class="clearfix"></div>
							<h5 class="sub-head">
								<strong> General Information: </strong>
							</h5>
							<hr style="margin: 5px 0; border: 1px solid #d8d8d8">

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div id="requiredFinancialPositionComments"
											Class="displayErrorMessageInRedColor"></div>
										<label>MLI's Comment on financial position of
											Borrower/Unit : <span style="color: red">*</span>
										</label> <label class="d-block"> <form:input
												path="financialPositionComments"
												id="financialPositionComments"
												value="${formData1.financialPositionComments}" size="20"
												class="form-control cus-control" />

										</label>

									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div id="requiredFinancialAssistanceDtls"
											Class="displayErrorMessageInRedColor"></div>
										<label>Details of Financial Assistance provided/being
											considered by MLI to minimize default : <span
											style="color: red">*</span>
										</label> <label class="d-block"> <form:input
												path="financialAssistanceDtls" id="financialAssistanceDtls"
												value="${formData1.financialAssistanceDtls}" size="20"
												class="form-control cus-control" /></label>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div id="requiredCreditSupport"
											Class="displayErrorMessageInRedColor"></div>
										<label>Does the MLI propose to provide credit support
											to Chief Promoter/Borrower for any other project :<span
											style="color: red">*</span>
										</label> <label class="d-block"> <c:choose>
												<c:when test="${formData1.creditSupport=='Y'}">
													<form:radiobutton path="creditSupport" value="Y"
														checked="checked" />Yes
													<form:radiobutton path="creditSupport" value="N" />No
											 	</c:when>
												<c:when test="${formData1.creditSupport=='N'}">
													<form:radiobutton path="creditSupport" value="Y" />Yes
													<form:radiobutton path="creditSupport" value="N"
														checked="checked" />No
											 		</c:when>
												<c:otherwise>
													<form:radiobutton path="creditSupport" value="Y" />Yes
													<form:radiobutton path="creditSupport" value="N" />No
											 	</c:otherwise>
											</c:choose>
										</label>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<div id="requiredBankFacilityDtls"
											Class="displayErrorMessageInRedColor"></div>
										<label>Details Of Bank Facility already provided to
											Borrower : <span style="color: red">*</span>:
										</label> <label class="d-block"> <form:input
												path="bankFacilityDtls" id="bankFacilityDtls"
												value="${formData1.bankFacilityDtls}" size="20"
												class="form-control cus-control" /></label>
									</div>
								</div>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
									<div class="form-group">
										<div class="col-md-12 prl-10">
											<label>Remarks :</label> <label class="d-block"> 
											<form:input
													path="nbfcMakerClaimLodgRemarks" id="nbfcMakerClaimLodgRemarks" value="${formData1.nbfcMakerClaimLodgRemarks}"
													size="20" class="form-control cus-control" />
											</label>
										</div>
									</div>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Return Remarks :</label> <label class="d-block"> <form:input
												path="remarks" id="remarks" value="${formData1.remarks}"
												size="20" class="form-control cus-control" readonly="true"/>
										</label>
									</div>
								</div>
							</div>
							<div align="left" id="successMsg">
								<font color="green" size="5">${message}</font>
							</div>
							<div align="left" id="error">
								<font color="red" size="5">${error}</font>
							</div>

							<div class="col-md-4 col-sm-4 col-xs-12 pt-20">
								<div id="requiredClaimLodgementCertificate"
									Class="displayErrorMessageInRedColor"></div>
									<form:checkbox path="claimLodgementCertificate"
											id="claimLodgementCertificate" value="Y" />
										<a style="padding: 5px;" class="declarebtn" type="button" data-toggle="modal" data-target="#myModal">Declaration
											& Undertaking </a>
							</div>


							<div>
								<input type="button" value="Claim Lodgement"
									class="btn btn-reset"
									onclick="claimLodgementFormValidatiOnSubmit(this.value);" />
							</div>
							<div>
							<div class="form-group">
								<h><br/><b>Amount Eligible for claim is 50%/60%/75% of :</b> <br/>A.Lower of<br/>1.Latest Outstanding Guaranteed Amount<br/>2.Total Outstanding as on date of NPA <br/>
								after netting of any repayment /recovery /subsidy made under the account after the date of NPA.<br/>3.Latest Outstanding as on paymnet of ASF  
								</h>
								</div>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10"></div>
								</div>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10"></div>
								</div>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">
									<div class="col-md-12 prl-10"></div>
								</div>
							</div>
							<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">
   
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
       <!--  <h4 class="modal-title">Modal Header</h4> -->
      </div>
      <div class="modal-body">
       
<div class="Decl-msg">
<h3 class="text-center "><a href="#">Declaration and Undertaking by MLI (NBFCs)</a></h3>
<!-- <h3 class="text-center">(To be signed by the officer not below the rank of Assistant General Manager or of equivalent rank of NBFC ).</h3> -->
<p class=" text-center pb-6">
<strong> Declaration-</strong> We declare that the information given above is true and correct in every respect.
We further declare that there has been no fault or negligence on  the part of the MLI or any of its officers in conducting the account.
We also declare that the officer preferring the claim on behalf of MLI is having the authority to do so.
</p>
<h2 class="  text-center pb-6">
We hereby declare that no fault or negligence has been pointed out by internal / external auditors, inspectors of CGTMSE or its agency in respect of the account(s) for which claim is being preferred.
</h2>
</div>
<div class="under_msg">
<h2 class="  text-center" >Undertaking- We hereby undertake:</h2>
<ol class="under_list">
<li>To pursue all recovery steps including legal proceedings.</li>
<li>On payment of claim by CGTMSE, to remit to CGTMSE all such recoveries, after adjusting towards the legal expenses incurred for recovery
of the amount, which we or our agents acting on our behalf, may make from the person or persons responsible for the administration of debt, or otherwise, in respect of the debt due from him / them to us.</li>
</ol>
</div>
<div class="sign_details">
<ol class="under_list">
<li> CGTMSE reserves the right to ask for any additional information, if required.</li>
<li> CGTMSE reserves the right to initiate any appropriate action / appoint any person / institution etc.
to verify the facts as mentioned above and if found contrary to the declaration, reserves the right to treat the claim under CGTMSE invalid.
</li>
</ol>
</div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>

						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>
