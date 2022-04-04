
	var EMAIL_PATTERN= /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var STRING_PATTERN = /^([a-zA-z ]{3,30})$/;
	var MLI_PATTERN = /^([a-zA-Z0-9 ]{3,30})$/;
	var TEXT_PATTERN = /^[a-zA-Z& ]*$/;
	var TEXT_ADDRESS = /^[a-zA-Z& ]*$/;
	var ADDRESS_PATTERN=/^[a-zA-Z0-9&\s,.'-]{3,}$/;
	//var NAME_PATTERN = /^[a-zA-Z0-9 ]*$/;
	
	var SHORTNAME_PATTERN = /^([a-zA-z ]{2,10})$/;
	var PHONE_CODE = /^\d{3,5}$/;
	var PIN_CODE=/^[0-9]{6}$/;
	var PAN_NO = /^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}$/;
	var GSTIN_NO = /^[0-9]{2}[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}[a-zA-Z1-9]{1}[a-zA-Z]{1}[a-zA-Z0-9]{1}$/;
	var MOBILE_PATTERN = /^[0-9]{10}$/;
	var PHONE_PATTERN = /^[0-9]{8}$/;
	var allowedExtensions = /(\.pdf)$/i;
	
function validateMLIRegistration()
{
	var errorFlag=true;
	
	var mliTypeSelect=document.getElementById("mliType");
    var mliTypeOption = mliTypeSelect.options[mliTypeSelect.selectedIndex].value.trim();
    
    var longName=document.getElementById("longName").value.trim();
    var shortName=document.getElementById("shortName").value.trim();
    var companyAddress=document.getElementById("companyAddress").value.trim();
    
    var stateSelect=document.getElementById("state");
    var stateOption = stateSelect.options[stateSelect.selectedIndex].value.trim()
    
    var districtSelect=document.getElementById("district");
    var districtOption = districtSelect.options[districtSelect.selectedIndex].value.trim();
    
    var city=document.getElementById("city").value.trim();
    var pincode=document.getElementById("pincode").value.trim();
    var companyPAN=document.getElementById("companyPAN").value.trim();
    var gstinNumber=document.getElementById("gstinNumber").value.trim();
    var phoneCode=document.getElementById("phoneCode").value.trim();
    var landlineNumber=document.getElementById("landlineNumber").value.trim();
    var emailId=document.getElementById("emailId").value.trim();
    var contactPerson=document.getElementById("contactPerson").value.trim();
    var mobileNo=document.getElementById("mobileNUmber").value.trim();
    var mlifile=document.getElementById("mlifile").value.trim();
    
    
    clearErrorText();


    if (mliTypeOption == "" || mliTypeOption == null) {
		document.getElementById("mliTypeerror").innerHTML = "Please Select MLI Type.";
		errorFlag = false;
	} else if (!TEXT_PATTERN.test(mliTypeOption)) {
		document.getElementById("mliTypeerror").innerHTML = "Please Select Valid MLI Type.";
		errorFlag = false;
	}

	if (shortName == "" || shortName == null) {
		document.getElementById("shortNameerror").innerHTML = "MLI Short Name is Required.";
		errorFlag = false;
	} else if (!SHORTNAME_PATTERN.test(shortName)) {
		document.getElementById("shortNameerror").innerHTML = "Should be between 2 - 10 characters.";
		errorFlag = false;
	}
	
	if (longName == "" || longName == null) {
		document.getElementById("longNameerror").innerHTML = "MLI Long Name is Required.";
		errorFlag = false;
	} else if (!MLI_PATTERN.test(longName)) {
		document.getElementById("longNameerror").innerHTML = "Should be between 3 - 30 characters.";
		errorFlag = false;
	}

	if (stateOption == "" || stateOption == null) {
		document.getElementById("stateerror").innerHTML = "Please Select State.";
		errorFlag = false;
	} else if (!TEXT_ADDRESS.test(stateOption)) {
		document.getElementById("stateerror").innerHTML = "Please Select Valid State.";
		errorFlag = false;
	}

	if (districtOption == "" || districtOption == null) {
		document.getElementById("districterror").innerHTML = "Please Select District.";
		errorFlag = false;
	} else if (!TEXT_ADDRESS.test(districtOption)) {
		document.getElementById("districterror").innerHTML = "Please Select Valid District.";
		errorFlag = false;
	}

	if (companyAddress == "" || companyAddress == null) {
		document.getElementById("companyAddresserror").innerHTML = "MLI Registered Addr.of the Company is Required.";
		errorFlag = false;
	} else if (!ADDRESS_PATTERN.test(companyAddress)) {
		if (companyAddress.length < 4) {
			document.getElementById("companyAddresserror").innerHTML = "Invalid Address Lenght Must be Greater Than 3";
		} else {
			document.getElementById("companyAddresserror").innerHTML = "Invalid Address Only , . - & Are Allowed.";
		}
		errorFlag = false;
	}
	    
	if (city == "" || city == null) {
		document.getElementById("cityerror").innerHTML = "City is Required.";
		errorFlag = false;
	} else if (!TEXT_ADDRESS.test(city)) {
		document.getElementById("cityerror").innerHTML = "Please Enter Valid City Name.";
		errorFlag = false;
	}
	
	if (pincode == "" || pincode == null) {
		document.getElementById("pincodeerror").innerHTML = "Pin code is Required.";
		errorFlag = false;
	} else if (!PIN_CODE.test(pincode)) {
		document.getElementById("pincodeerror").innerHTML = "Should be 6 digits.";
		errorFlag = false;
	}
	if (companyPAN == "" || companyPAN == null) {
		document.getElementById("panOFCompanyerror").innerHTML = "Institution PAN is Required.";
		errorFlag = false;
	} else if (!PAN_NO.test(companyPAN)) {
		document.getElementById("panOFCompanyerror").innerHTML = "Invalid Institution Pan No.";
		errorFlag = false;
	}
	if (gstinNumber == "" || gstinNumber == null) {
		document.getElementById("gstinNOerror").innerHTML = "GSTIN is Required.";
		errorFlag = false;
	} else if (!GSTIN_NO.test(gstinNumber)) {
		document.getElementById("gstinNOerror").innerHTML = "Invalid Company GSTIN Number.";
		errorFlag = false;
	}
	
	if (phoneCode == "" || phoneCode == null) {
		document.getElementById("phoneCodeerror").innerHTML = "Phone Code is Required.";
		errorFlag = false;
	} else if (!PHONE_CODE.test(phoneCode)) {
		document.getElementById("phoneCodeerror").innerHTML = "Should be between 3 to 5 digits.";
		errorFlag = false;
	}
	
	if (landlineNumber == "" || landlineNumber == null) {
		document.getElementById("phoneCodeerror").innerHTML = "Phone Number is Required.";
		errorFlag = false;
	} else if (!PHONE_PATTERN.test(landlineNumber)) {
		document.getElementById("phoneCodeerror").innerHTML = "Phone Number Should be 8 Digits";
		errorFlag = false;
	}
	
	
	if (emailId == "" || emailId == null) {
		document.getElementById("emailIderror").innerHTML = "Email is Required.";
		errorFlag = false;
	} else if (!EMAIL_PATTERN.test(emailId)) {
		document.getElementById("emailIderror").innerHTML = "Enter a Correct Email.";
		errorFlag = false;
	}
	
	
	if (contactPerson == "" || contactPerson == null) {
		document.getElementById("contactPersonerror").innerHTML = "Contact Person is Required .";
		errorFlag = false;
	} else if (!STRING_PATTERN.test(contactPerson)) {
		document.getElementById("contactPersonerror").innerHTML = "Should be between 3 - 30 characters.";
		errorFlag = false;
	}
	
	if (mobileNo == "" || mobileNo == null) {
		document.getElementById("mobileNUmbererror").innerHTML = "Mobile Number is Required .";
		errorFlag = false;
	} else if (!MOBILE_PATTERN.test(mobileNo)) {
		document.getElementById("mobileNUmbererror").innerHTML = "Mobile Number Should be 10 Digits.";
		errorFlag = false;
	}
	
	if (mlifile == "" || mlifile == null) {
		document.getElementById("mlifileerror").innerHTML = "Please Select File to Upload";
		errorFlag = false;
	}
	else if(!allowedExtensions.exec(mlifile) && mlifile.length>0){

        document.getElementById("mlifileerror").innerHTML="Please upload only pdf File";
		errorFlag=false;
		}
	return errorFlag;
	
}
function clearErrorText()
{
	document.getElementById("mliTypeerror").innerHTML = "";
	document.getElementById("shortNameerror").innerHTML = "";
	document.getElementById("longNameerror").innerHTML = "";
	document.getElementById("stateerror").innerHTML = "";
	document.getElementById("districterror").innerHTML = "";
	document.getElementById("companyAddresserror").innerHTML = "";
	document.getElementById("cityerror").innerHTML = "";
	document.getElementById("pincodeerror").innerHTML = "";
	document.getElementById("panOFCompanyerror").innerHTML = "";
	document.getElementById("gstinNOerror").innerHTML = "";
	document.getElementById("phoneCodeerror").innerHTML = "";
	document.getElementById("phoneCodeerror").innerHTML = "";
	document.getElementById("emailIderror").innerHTML = "";
	document.getElementById("contactPersonerror").innerHTML = "";
	document.getElementById("mobileNUmbererror").innerHTML = "";
	 document.getElementById("mlifileerror").innerHTML="";
	
}