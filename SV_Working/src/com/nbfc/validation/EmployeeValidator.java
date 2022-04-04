package com.nbfc.validation;
import com.nbfc.helper.DateHelper;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nbfc.bean.ApplicationStatusDetailsBean;
import com.nbfc.bean.CGPANDetailsReportBean;
import com.nbfc.bean.ClaimLodgementBean;
import com.nbfc.bean.GeneralReport;
import com.nbfc.bean.ITPANSearchHistoryBean;
import com.nbfc.bean.MLIDEtailsBean;
import com.nbfc.bean.MLIDetailEditBean;
import com.nbfc.bean.MLIMakerInterfaceUploadedBean;
import com.nbfc.bean.MLIRegBean;
import com.nbfc.bean.NPADetailsBean;
import com.nbfc.bean.NPAMarkBean;
import com.nbfc.bean.NbfcAppropriationBean;
import com.nbfc.bean.NpaUpgradationBean;
import com.nbfc.bean.PortfolioGridPanBean;
import com.nbfc.bean.UserBean;
import com.nbfc.bean.UserRoleBean;
import com.nbfc.helper.NBFCHelper;

import net.sf.jmimemagic.Magic;

/**
 * @author Saurav Tyagi 2017
 * 
 */

@Component
public class EmployeeValidator implements Validator {
	 DateHelper DateHelper = new DateHelper();
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	// String ID_PATTERN = "[0-9]+";
	String STRING_PATTERN = "^([a-zA-z0-9()\\s&-,.]{3,100})$";
	String MLI_PATTERN = "[a-zA-Z0-9()\\s&,-//]*$";
//	String MLI_PATTERN = ".*[a-zA-Z\\s]+.*";
	// String STRING_PATTERN1 = ".*^([a-zA-z\\s]+\\..*{3,30})$";
	//add azda
		String Dateformate="^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
		//end
	String TEXT_PATTERN = "[a-zA-Z0-9()\\s,&-]*$";
	String TEXT_ADDRESS = "[a-zA-Z0-9()\\s&,-]*$";
	String ADDRESS_PATTERN="[a-zA-Z0-9()&\\s,.'-//]{3,}$";
	String SHORTNAME_PATTERN = "^([a-zA-z()\\s&-]{2,50})$";
	String PHONE_CODE = "([0-9]{3}|[0-9]{4}|[0-9]{5})";
	String PIN_CODE = "([0-9]{6})";
	String RBI_NO = "(^$|(?i)[A-Z]{1}(?-i)-\\d{2}+\\.\\d{5})";
	String CIN_NO = "^$|(?i)[A-Z]{1}\\d{5}(?i)[A-Z]{2}\\d{4}(?i)[A-Z]{3}\\d{6}";
	String PAN_NO = "^$|(?i)[A-Z]{5}\\d{4}(?i)[A-Z]{1}";
	//String GSTIN_NO = "^$|[0-9]{2}(?i)[A-Z]{5}\\d{4}(?i)[A-Z]{1}\\d{1}(?i)[A-Z]{2}";
	String GSTIN_NO = "^$|[0-9]{2}(?i)[A-Z]{5}\\d{4}(?i)[A-Z]{1}\\d{1}(?i)[A-Z]{1}(?i)[A-Z0-9]{1}";
	String MOBILE_PATTERN = "[0-9]{10}";
	String PHONE_PATTERN = "[0-9]{8}|[0-9]{7}|[0-9]{6}";
	String Digits = "[0-9]{10}";
	private Pattern pattern;
	private Matcher matcher;
	@Value("${emailIdValidation}")
	private String emailIdValidation;
    
	public boolean supports(Class clazz) {
		return UserBean.class.isAssignableFrom(clazz);
	}

	public void npaValidation(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "CGPAN",
				"error.CGPAN", "CGPAN Field is mandatory.");

	}
	public void npaEditSaveValidation(NPADetailsBean bean, Errors errors) {
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		// "firstDisbDt1","error.firstDisbDt1", "firstDisbDt1 is Required !!.");
		// errors.rejectValue("firstDisbDt1", "firstDisbDt1.incorrect",
		// "Date is Required");
		

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "CGPAN",
				",error.CGPAN", " CGPAN Field is mandatory.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "npaDt",
				",error.npaDt", " NPA Date Field is mandatory.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isAsPerRBI",
				",error.isAsPerRBI", " isAsPerRBI Field is mandatory.");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "npaConfirm",
		// ",error.npaConfirm", " npaConfirm is Required !!.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "npaReason",
				",error.npaReason", " npaReason Field is mandatory.");
	

		if (bean.getOutstandingPrincipal1() == 0.0) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"outstandingPrincipal1Error",
					",error.outstandingPrincipal1Error",
					" outstandingPrincipal Field is mandatory.");
		}
		if (bean.getOutstandingInterest1() == 0.0) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"outstandingInterest1Error",
					",error.outstandingInterest1Error",
					" outstandingInterest Field is mandatory.");
		}
		if (bean.getClaimEligibityAmt() == 0.0) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"claimEligibityAmtError",
					",error.claimEligibityAmtError",
					" Claim Eligibility Amount Field is mandatory.");
		}
	
	
	/*	if (bean.getTotalSecuritydetails1() == 0.0) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"totalSecuritydetails1Error", ",error.totalSecuritydetails1Error",
					"Totalsecurity  is Required(As on date of NPA), MLI must be fill atleast one Security Nature Valuein !!.");
		}
		if (bean.getTotalSecuritydetails() == 0.0) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"totalSecuritydetailsError", ",error.totalSecuritydetailsError",
					" Totalsecurity  is Required(As on date of sanctioncredit), MLI must be fill atleast one Security Nature Valuein !!.");

		}*/
	}
	
public void exposureDetailsValidate(GeneralReport bean, BindingResult errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDate",
				"error.toDate", "Todate is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDate",
				"error.fromDate", "From Date is required.");

	}

	public void npaSaveValidation(Object target, Errors errors) {
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		// "firstDisbDt1","error.firstDisbDt1", "firstDisbDt1 is Required !!.");
		// errors.rejectValue("firstDisbDt1", "firstDisbDt1.incorrect",
		// "Date is Required");
		NPAMarkBean bean = (NPAMarkBean) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "CGPAN",
				",error.CGPAN", " CGPAN Field is mandatory.");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "npaDt",
				",error.npaDt", "NPA Date Field is mandatory.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isAsPerRBI",
				",error.isAsPerRBI", " isAsPerRBI Field is mandatory.");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "npaConfirm",
		// ",error.npaConfirm", " npaConfirm is Required !!.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "npaReason",
				",error.npaReason", " npaReason Field is mandatory.");
	

		if (bean.getOutstandingPrincipal1() == 0.0) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"outstandingPrincipal1Error",
					",error.outstandingPrincipal1Error",
					" outstandingPrincipal Field is mandatory.");
		}
		if (bean.getOutstandingInterest1() == 0.0) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"outstandingInterest1Error",
					",error.outstandingInterest1Error",
					" outstandingInterest Field is mandatory.");
		}
		if (bean.getClaimEligibityAmt() == 0.0) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"claimEligibityAmtError",
					",error.claimEligibityAmtError",
					" Claim Eligibility Amount Field is mandatory.");
		}
	
	
	/*	if (bean.getTotalSecuritydetails1() == 0.0) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"totalSecuritydetails1Error", ",error.totalSecuritydetails1Error",
					"Totalsecurity  is Required(As on date of NPA), MLI must be fill atleast one Security Nature Valuein !!.");
		}
		if (bean.getTotalSecuritydetails() == 0.0) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors,
					"totalSecuritydetailsError", ",error.totalSecuritydetailsError",
					" Totalsecurity  is Required(As on date of sanctioncredit), MLI must be fill atleast one Security Nature Valuein !!.");

		}*/
	}

	public void portfolioApprovalValidate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "PORTFOLIO_NO",
				"error.PORTFOLIO_NO", "Please Select Portfolio Name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "acceptStatus",
				"error.acceptStatus",
				"Please Check the Management Certificate !! ");
	}

	public void portfolioDetailsValidate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "PORTFOLIO_NO",
				"error.PORTFOLIO_NO", "Please Select Portfolio Name");
	}

	public void portfolioRejectionValidate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "REMARKS",
				"error.REMARKS", "Please fill the remarks.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "PORTFOLIO_NO",
				"error.PORTFOLIO_NO", "Please Select Portfolio Name");

	}

	public void validate(Object target, Errors errors) {
		UserBean userBean = (UserBean) target;
        
//		if (userBean.getState().equals("") == true) {
//
//			errors.rejectValue("state", "error.state",
//					"Select a MLI Name");
//		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNumber",
				"error.mobileNumber", "Mobile Number is Required.");
		if (!(userBean.getMobileNumber() != null && userBean.getMobileNumber()
				.isEmpty())) {
			pattern = Pattern.compile(MOBILE_PATTERN);
			matcher = pattern.matcher(userBean.getMobileNumber());
			if (!matcher.matches()) {
				errors.rejectValue("mobileNumber", "mobileNumber.incorrect",
						"Mobile Number Should be 10 Digits");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneCode",
				"error.phoneCode", "Phone Code is Required.");
		if (!(userBean.getPhoneCode() != null && userBean.getPhoneCode()
				.isEmpty())) {
			pattern = Pattern.compile(PHONE_CODE);
			matcher = pattern.matcher(userBean.getPhoneCode());
			if (!matcher.matches()) {
				errors.rejectValue("phoneCode", "phoneCode.incorrect",
						"Phone Code Should be between 3 to 5 Digits");
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber",
				"error.phoneNumber", "Phone Number is Required.");
		if (!(userBean.getPhoneNumber() != null && userBean.getPhoneNumber()
				.isEmpty())) {
			pattern = Pattern.compile(PHONE_PATTERN);
			matcher = pattern.matcher(userBean.getPhoneNumber());
			if (!matcher.matches()) {
				errors.rejectValue("phoneNumber", "phoneNumber.incorrect",
						"Phone Number Should be 6-8 Digits");
			}
		}
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userType",
		// "error.userType", "Please Select User Type.");
		/*
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state",
		 * "error.state", "Please Select MLI Name.");
		 */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fName",
				"error.fName", "First Name is Required.");
		if (!(userBean.getfName() != null && userBean.getfName().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher(userBean.getfName());
			///Commented By Parmannd
			/*if (!matcher.matches()) {
				errors.rejectValue("fName", "fName.containNonChar",
						"Numeric Vaule not allowed for First Name.");
			}*/
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lName",
				"error.lName", "Last Name is Required");

		if (!(userBean.getlName() != null && userBean.getlName().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher(userBean.getlName());
			///Commented By Parmannd
			/*if (!matcher.matches()) {
				errors.rejectValue("lName", "lName.containNonChar",
						"Numeric Vaule not allowed for Last Name.");
			}*/
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
				"error.email", "Email is Required.");
		if (!(userBean.getEmail() != null && userBean.getEmail().isEmpty())) {
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(userBean.getEmail());
			if (!matcher.matches()) {
				errors.rejectValue("email", "email.incorrect",
						"Enter a Correct Email");
			}
		}
		/*
		String[] eValidation = emailIdValidation.split("\\@");
		for (String w : eValidation) {

			if (userBean.getEmail().toUpperCase().endsWith(w)) {
				errors.rejectValue("email", "email.incorrect",
						"Enter Offical EmailId.");
			}
		}
		*/
		if (!(userBean.getMiddalName() != null && userBean.getMiddalName()
				.isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher(userBean.getMiddalName());
			if (!matcher.matches()) {
				errors.rejectValue("middalName", "middalName.containNonChar",
						"Enter a valid Middle Name");
			}
		}
		if (userBean.getUserType().equals("") == true) {

			errors.rejectValue("userType", "error.userType",
					"Select a User Role Name");
		}

	}

	public void validateUser(Object target, Errors errors) {
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userType",
		// "error.userType", "Please Select User Type.");
		UserBean userBean = (UserBean) target;
		/*
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
		 * "error.email", "Please Fill the Email ID.");
		 */

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
				"error.email", "Email is required.");
		if (!(userBean.getEmail() != null && userBean.getEmail().isEmpty())) {
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(userBean.getEmail());
			if (!matcher.matches()) {
				errors.rejectValue("email", "email.incorrect",
						"Enter a correct Email");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fName",
				"error.fName", "First name is required.");
		if (!(userBean.getfName() != null && userBean.getfName().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher(userBean.getfName());
			if (!matcher.matches()) {
				errors.rejectValue("fName", "fName.containNonChar",
						"Enter a valid First name");
			}
		}

		/*
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lName",
		 * "error.lName", "Please Fill the Last Name");
		 */

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lName",
				"error.lName", "Last Name is required");

		if (!(userBean.getlName() != null && userBean.getlName().isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher(userBean.getlName());
			if (!matcher.matches()) {
				errors.rejectValue("lName", "lName.containNonChar",
						"Enter a valid Last name");
			}
		}

		/*
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state",
		 * "error.state", "Please Select MLI Name.");
		 */
	}

	public void makerValidate(MLIMakerInterfaceUploadedBean bean, Errors errors) {

		MLIMakerInterfaceUploadedBean aaa = (MLIMakerInterfaceUploadedBean) bean;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "PORTFOLIO_BASE_YER",
				"error.PORTFOLIO_BASE_YER",
				"Please Select Portfolio Base Year.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "PORTFOLIO_NAME",
				"error.PORTFOLIO_NO", "Please Select Portfolio Name.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "QUARTER_NO",
				"error.QUARTER_NO", "Please Select Quarter Number.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "file", "error.file",
				"Please Select the File.");

	}
	public void stateInputDetailsValidate(GeneralReport bean, BindingResult errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "guaranteeStatus",
				"error.requiredGuranteeOption", "Please select Required Status.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDate",
				"error.toDate", "Todate is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDate",
				"error.fromDate", "From Date is required.");

	}
	public void validateUserRejection(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "remarks",
				"error.remarks", "Please Fill the remarks.");
	}

	// Add by say 4 feb 19------
	public void mliRegistrationValidator(Object target, Errors errors) {

		MLIRegBean bean = (MLIRegBean) target;
		MultipartFile file=bean.getMlifile();

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "longName",
				"error.longName", "MLI Long Name is Required.");
		if (!(bean.getLongName() != null && bean.getLongName().isEmpty())) {
			pattern = Pattern.compile(MLI_PATTERN);

			matcher = pattern.matcher(bean.getLongName());
			if (!matcher.matches()) {
				errors.rejectValue("longName", "longName.containNonChar",
						"Should be between 3 - 100 characters.");
			}
		
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mliType",
				"error.mliType", "Please Select MLI Type.");
		//
		if (!(bean.getMliType()!= null && bean.getMliType().isEmpty())) {
			pattern = Pattern.compile(TEXT_PATTERN);
			matcher = pattern.matcher(bean.getMliType());
			if (!matcher.matches()) {
				errors.rejectValue("mliType", "mliType.containNonChar",
						"Please Select Valid MLI Type.");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shortName",
				"error.shortName", "MLI Short Name is Required.");
		if (!(bean.getShortName() != null && bean.getShortName().isEmpty())) {
			pattern = Pattern.compile(SHORTNAME_PATTERN);
			matcher = pattern.matcher(bean.getShortName());
			if (!matcher.matches()) {
				errors.rejectValue("shortName", "shortName.containNonChar",
						"Should be between 2 - 50 characters.");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyAddress",
				"error.companyAddress",
				"MLI Registered Addr.of the Company is Required.");
		
		if (!(bean.getCompanyAddress() != null && bean.getCompanyAddress().isEmpty())) {
			pattern = Pattern.compile(ADDRESS_PATTERN);
			matcher = pattern.matcher(bean.getCompanyAddress());
			if (!matcher.matches()) {
				errors.rejectValue("companyAddress", "companyAddress.incorrect",
						"Invalid Address Only , . - & Are Allowed.");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "error.city",
				"City is Required.");
		if (!(bean.getCity() != null && bean.getCity().isEmpty())) {
			pattern = Pattern.compile(TEXT_ADDRESS);
			matcher = pattern.matcher(bean.getCity());
			if (!matcher.matches()) {
				errors.rejectValue("city", "city.incorrect",
						"Please Enter Valid City Name.");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state",
				"error.state", "Please Select State.");
		if (!(bean.getState() != null && bean.getState().isEmpty())) {
			pattern = Pattern.compile(TEXT_ADDRESS);
			matcher = pattern.matcher(bean.getState());
			if (!matcher.matches()) {
				errors.rejectValue("state", "state.incorrect",
						"Please Select Valid State.");
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "district",
				"error.district", "Please Select District.");
		if (!(bean.getDistrict() != null && bean.getDistrict().isEmpty())) {
			pattern = Pattern.compile(TEXT_ADDRESS);
			matcher = pattern.matcher(bean.getDistrict());
			if (!matcher.matches()) {
				errors.rejectValue("district", "district.incorrect",
						"Please Select Valid District.");
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pincode",
				"error.pincode", "Pin code is Required.");
		if (!(bean.getPincode() != null && bean.getPincode().isEmpty())) {
			pattern = Pattern.compile(PIN_CODE);
			matcher = pattern.matcher(bean.getPincode());
			if (!matcher.matches()) {
				errors.rejectValue("pincode", "pincode.containNonChar",
						"Should be 6 digits.");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "landlineNumber",
				"error.landlineNumber", "Landline No is Required.");
		if (!(bean.getLandlineNumber() != null && bean.getLandlineNumber()
				.isEmpty())) {
			pattern = Pattern.compile(PHONE_PATTERN);
			matcher = pattern.matcher(bean.getLandlineNumber());
			if (!matcher.matches()) {
				errors.rejectValue("landlineNumber",
						"landlineNumber.incorrect", "Should be 6-8 digits.");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId",
				"error.emailId", "Email id is Required.");
		if (!(bean.getEmailId() != null && bean.getEmailId().isEmpty())) {
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(bean.getEmailId());
			if (!matcher.matches()) {
				errors.rejectValue("emailId", "emailId.incorrect",
						"Wrong email.");
			}
		}
		/*
		String[] eValidation = emailIdValidation.split("\\@");
		for (String w : eValidation) {
			if (bean.getEmailId().toUpperCase().endsWith(w)) {
				errors.rejectValue("emailId", "emailId.incorrect",
						"Enter Offical EmailId.");
			}
		}
		*/
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactPerson",
				"error.contactPerson", "Contact Person is Required .");
		if (!(bean.getContactPerson() != null && bean.getContactPerson()
				.isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher(bean.getContactPerson());
			if (!matcher.matches()) {
				errors.rejectValue("contactPerson",
						"contactPerson.containNonChar",
						"Should be between 3 - 100 characters.");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNUmber",
				"error.mobileNUmber", "Mobile Number is Required .");
		if (!(bean.getMobileNUmber() != null && bean.getMobileNUmber()
				.isEmpty())) {
			pattern = Pattern.compile(MOBILE_PATTERN);
			matcher = pattern.matcher(bean.getMobileNUmber());
			if (!matcher.matches()) {
				errors.rejectValue("mobileNUmber", "mobileNUmber.incorrect",
						"Should be 10 digits.");
			}
		}

		/*ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"rbiReggistrationNumber", "error.rbiReggistrationNumber",
				"RBI Registration No. is Required.");
				*/
		if (!(bean.getRbiReggistrationNumber() != null && bean
				.getRbiReggistrationNumber().isEmpty())) {
			pattern = Pattern.compile(RBI_NO);
			matcher = pattern.matcher(bean.getRbiReggistrationNumber());
			if (!matcher.matches()) {
				errors.rejectValue("rbiReggistrationNumber",
						"rbiReggistrationNumber.incorrect",
						"Invalid RBI Registration Number.");
			}
		}

		/*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyCIN",
				"error.companyCIN", "Company CIN is Required.");
				*/
		if (!(bean.getCompanyCIN() != null && bean.getCompanyCIN().isEmpty())) {
			pattern = Pattern.compile(CIN_NO);
			matcher = pattern.matcher(bean.getCompanyCIN());
			if (!matcher.matches()) {
				errors.rejectValue("companyCIN", "companyCIN.incorrect",
						"Invalid Company CIN No.");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyPAN",
				"error.companyPAN", "Institution PAN is Required.");
		if (!(bean.getCompanyPAN() != null && bean.getCompanyPAN().isEmpty())) {
			pattern = Pattern.compile(PAN_NO);
			matcher = pattern.matcher(bean.getCompanyPAN());
			if (!matcher.matches()) {
				errors.rejectValue("companyPAN", "companyPAN.incorrect",
						"Invalid Institution Pan No.");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gstinNumber",
				"error.gstinNumber", "GSTIN is Required.");
		
		
		//*Commented By Parmanand on the request of Kirity 28 Nov 2019
		String regex = "^[0-9]{2}[A-Z]{5}[0-9]{4}"
                + "[A-Z]{1}[1-9A-Z]{1}"
                + "Z[0-9A-Z]{1}$";
		 if (!(bean.getGstinNumber() != null && bean.getGstinNumber().isEmpty())) {
			 pattern = Pattern.compile(regex);
			 matcher = pattern.matcher(bean.getGstinNumber());
			 //pattern = Pattern.compile(GSTIN_NO);
			//matcher = pattern.matcher(bean.getGstinNumber());
			if(!bean.getGstinNumber().contains(bean.getCompanyPAN()))
			{
				errors.rejectValue("gstinNumber", "gstinNumber.incorrect",
						"Invalid Company GSTIN Number.");
			}
			if (!matcher.matches()) {
				errors.rejectValue("gstinNumber", "gstinNumber.incorrect",
						"Invalid Company GSTIN Number..");
			}
		}

		/*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ratingAgency",
				"error.ratingAgency", "Rating Agency is Required. ");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rating",
				"error.rating", "Rating is Required.");
*/
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		// "ratingDate","error.ratingDate", "Date of Rating is Required !!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneCode",
				"error.phoneCode", "Code &");
		if (!(bean.getPhoneCode() != null && bean.getPhoneCode().isEmpty())) {
			pattern = Pattern.compile(PHONE_CODE);
			matcher = pattern.matcher(bean.getPhoneCode());
			if (!matcher.matches()) {
				errors.rejectValue("phoneCode", "phoneCode.incorrect",
						"Should be between 3 to 5 digits.");
			}
		}

	/*	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ratingDate",
				"error.ratingDate", "Rating Date is Required.");*/
		
		
		if (!file.isEmpty()) {
			try {
				
				
				double bytes = file.getSize();
				double kilobytes = (bytes / 1024);
				double filesize = (kilobytes / 1024); 
				System.out.println("file ka content type "+file.getContentType());
				if (filesize>10)
				{
					errors.rejectValue("mlifile", "mlifile.incorrect",
							"File size is too large");		
				}
				else
				{
					
				if(file.getName().contains(".exe") || file.getName().contains(".msi") || file.getName().contains(".bat"))
				{
					errors.rejectValue("mlifile", "mlifile.incorrect",
							"Please Upload Only Pdf Files");
				}
				try{
					String mimeType=Magic.getMagicMatch(file.getBytes()).getMimeType();
					if(!"application/pdf".equalsIgnoreCase(mimeType))
					{
						errors.rejectValue("mlifile", "mlifile.incorrect",
								"Please Upload Only Pdf Files");
				
					}
						}catch(Exception e){
							e.printStackTrace();
							errors.rejectValue("mlifile", "mlifile.incorrect",
									"Please Upload Only Pdf Files");
						}
				
				}

			}catch(Exception e)
			{
				
				e.printStackTrace();
				errors.rejectValue("mlifile", "mlifile.incorrect",
						"Please Upload Only Pdf Files");
			}
		}
		else
		{
			errors.rejectValue("mlifile", "mlifile.incorrect",
					"Please Upload Pdf Files");
			
		}

	}

	public void mliRegistrationEditValidator(MLIDEtailsBean bean, Errors errors) {

		// MLIRegBean bean = (MLIRegBean) target;

		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "longName",
		// "error.longName", "MLI Long Name is Required.");
		// if (!(bean.getLongName() != null && bean.getLongName().isEmpty())) {
		// pattern = Pattern.compile(MLI_PATTERN);
		//
		// matcher = pattern.matcher(bean.getLongName());
		// if (!matcher.matches()) {
		// errors.rejectValue("longName", "longName.containNonChar",
		// "Should be between 3 - 30 characters.");
		// }
		// }
		// //
		//
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shortName",
		// "error.shortName", "MLI Short Name is Required.");
		// if (!(bean.getShortName() != null && bean.getShortName().isEmpty()))
		// {
		// pattern = Pattern.compile(SHORTNAME_PATTERN);
		// matcher = pattern.matcher(bean.getShortName());
		// if (!matcher.matches()) {
		// errors.rejectValue("shortName", "shortName.containNonChar",
		// "Should be between 2 - 10 characters.");
		// }
		// }
		//
		//
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyAddress",
				"error.companyAddress",
				"MLI Registered Addr.of the Company is Required.");
		
		if (!(bean.getCompanyAddress() != null && bean.getCompanyAddress().isEmpty())) {
			pattern = Pattern.compile(ADDRESS_PATTERN);
			matcher = pattern.matcher(bean.getCompanyAddress());
			if (!matcher.matches()) {
				errors.rejectValue("companyAddress", "companyAddress.incorrect",
						"Invalid Address Only , . - & Are Allowed.");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "error.city",
				"City is Required.");
		if (!(bean.getCity() != null && bean.getCity().isEmpty())) {
			pattern = Pattern.compile(TEXT_ADDRESS);
			matcher = pattern.matcher(bean.getCity());
			if (!matcher.matches()) {
				errors.rejectValue("city", "city.incorrect",
						"Please Enter Valid City Name.");
			}
		}


		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state",
				"error.state", "Please Select State.");
		if (!(bean.getState() != null && bean.getState().isEmpty())) {
			pattern = Pattern.compile(TEXT_ADDRESS);
			matcher = pattern.matcher(bean.getState());
			if (!matcher.matches()) {
				errors.rejectValue("state", "state.incorrect",
						"Please Select Valid State.");
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "district",
				"error.district", "Please Select District.");
		if (!(bean.getDistrict() != null && bean.getDistrict().isEmpty())) {
			pattern = Pattern.compile(TEXT_ADDRESS);
			matcher = pattern.matcher(bean.getDistrict());
			if (!matcher.matches()) {
				errors.rejectValue("district", "district.incorrect",
						"Please Select Valid District.");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pincode",
				"error.pincode", "Pinecode is Required.");
		if (!(bean.getPincode() != null && bean.getPincode().isEmpty())) {
			pattern = Pattern.compile(PIN_CODE);
			matcher = pattern.matcher(bean.getPincode());
			if (!matcher.matches()) {
				errors.rejectValue("pincode", "pincode.containNonChar",
						"Should be 6 digits.");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "landlineNumber",
				"error.landlineNumber", "Landline No is Required.");
		if (!(bean.getLandlineNumber() != null && bean.getLandlineNumber()
				.isEmpty())) {
			pattern = Pattern.compile(PHONE_PATTERN);
			matcher = pattern.matcher(bean.getLandlineNumber());
			if (!matcher.matches()) {
				errors.rejectValue("landlineNumber",
						"landlineNumber.incorrect", "Should be 8 digits.");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId",
				"error.emailId", "Email id is Required.");

		if (!(bean.getEmailId() != null && bean.getEmailId().isEmpty())) {
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(bean.getEmailId());
			if (!matcher.matches()) {
				errors.rejectValue("emailId", "emailId.incorrect",
						"Wrong email.");
			}
		}
		/*
		String[] eValidation = emailIdValidation.split("\\@");
		for (String w : eValidation) {
			if (bean.getEmailId().endsWith(w)) {
				errors.rejectValue("emailId", "emailId.incorrect",
						"Enter Offical EmailId.");
			}
		}
		*/
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactPerson",
				"error.contactPerson", "Contact Person is Required .");
		if (!(bean.getContactPerson() != null && bean.getContactPerson()
				.isEmpty())) {
			pattern = Pattern.compile(STRING_PATTERN);
			matcher = pattern.matcher(bean.getContactPerson());
			if (!matcher.matches()) {
				errors.rejectValue("contactPerson",
						"contactPerson.containNonChar",
						"Should be between 3 - 100 characters.");
			}
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNUmber",
				"error.mobileNUmber", "Mobile Number is Required .");
		if (!(bean.getMobileNUmber() != null && bean.getMobileNUmber()
				.isEmpty())) {
			pattern = Pattern.compile(MOBILE_PATTERN);
			matcher = pattern.matcher(bean.getMobileNUmber());
			if (!matcher.matches()) {
				errors.rejectValue("mobileNUmber", "mobileNUmber.incorrect",
						"Should be 10 digits.");
			}
		}

		
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		// "rbiReggistrationNumber", "error.rbiReggistrationNumber",
		// "RBI Reggistration No. is Required.");
		
		if (!(bean.getRbiReggistrationNumber() != null && bean
				.getRbiReggistrationNumber().isEmpty())) {
			pattern = Pattern.compile(RBI_NO);
			matcher = pattern.matcher(bean.getRbiReggistrationNumber());
			if (!matcher.matches()) {
				errors.rejectValue("rbiReggistrationNumber",
						"rbiReggistrationNumber.incorrect",
						"Invalid RBI Registration Number.");
			}
		}

		/*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyCIN",
				"error.companyCIN", "Company CIN is Required.");
				*/
		
		if (!(bean.getCompanyCIN() != null && bean.getCompanyCIN().isEmpty())) {
			pattern = Pattern.compile(CIN_NO);
			matcher = pattern.matcher(bean.getCompanyCIN());
			if (!matcher.matches()) {
				errors.rejectValue("companyCIN", "companyCIN.incorrect",
						"Invalid Company CIN No.");
			}
		}
		
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyPAN",
		 "error.companyPAN", "Institution PAN is Required.");
		 if (!(bean.getCompanyPAN()!= null && bean.getCompanyPAN().isEmpty()))
		 {
		 pattern = Pattern.compile(PAN_NO);
		 matcher = pattern.matcher(bean.getCompanyPAN());
		 if (!matcher.matches()) {
		 errors.rejectValue("companyPAN", "companyPAN.incorrect",
		 "Invalid Institution Pan No.");
		 }
		 }

		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gstinNumber",
		 "error.gstinNumber", "GSTIN is Required.");
		 if (!(bean.getGstinNumber()!= null &&
		 bean.getGstinNumber().isEmpty())) {
		 pattern = Pattern.compile(GSTIN_NO);
		 matcher = pattern.matcher(bean.getGstinNumber());
		 if (!matcher.matches()) {
		 errors.rejectValue("gstinNumber", "gstinNumber.incorrect",
		 "Invalid Company GSTIN Number..");
		 }
		 }

		/*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ratingAgency",
				"error.ratingAgency", "Rating Agency is Required. ");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rating",
				"error.rating", "Rating is Required.");*/

		// ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		// "ratingDate","error.ratingDate", "Date of Rating is Required !!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneCode",
				"error.phoneCode", "Code &");
		if (!(bean.getPhoneCode() != null && bean.getPhoneCode().isEmpty())) {
			pattern = Pattern.compile(PHONE_CODE);
			matcher = pattern.matcher(bean.getPhoneCode());
			if (!matcher.matches()) {
				errors.rejectValue("phoneCode", "phoneCode.incorrect",
						"Should be between 3 to 5 digits.");
			}
		}

		/*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ratingDate",
				"error.ratingDate", "Rating Date is Required.");*/
	}
	public void checkDate(NbfcAppropriationBean bean, BindingResult errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDate",
				"error.toDate", "Realisation date is Mandatory.");

	}

	public void validateTwoDate(NbfcAppropriationBean bean, BindingResult errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDate",
				"error.toDate", "To date is Mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDate",
				"error.fromDate", "From date is Mandatory.");

	}
	
	public void npaReportDetailsValidate(NPADetailsBean bean,
			BindingResult errors) {
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "MLIID",
//				"error.MLIID", "MLIID Required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDate",
				"error.toDate", "Todate is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDate",
				"error.fromDate", "From Date is required.");
	
	}

	/*
	 * public void validateDatesForAppropriation(Object target, Errors errors) {
	 * NbfcAppropriationBean nab=(NbfcAppropriationBean)target;
	 * if(nab.getDate()!=null){
	 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fName", "error.fName",
	 * "First name is required."); }else if(date1!=null && date2!=null){
	 * 
	 * } // TODO Auto-generated method stub
	 * 
	 * }
	 */
	// added by say 22 nov
	// 2018-------------------------------------------------------------
	public void mliRoleValidator(UserBean mliRegistrationBean,
			BindingResult errors) {
		// TODO Auto-generated method stub
		UserBean userBean = (UserBean) mliRegistrationBean;

		if (userBean.getUserType().equals("Select") == true) {

			errors.rejectValue("userType", "error.userType",
					" Select a User type");
		}
		System.out.println("status......." + userBean.getStatus());
		if (userBean.getStatus().equals("Select") == true) {

			errors.rejectValue("status", "error.status", "Select a User status");
		}
	}

	public void mliCreateRoleValidator(UserRoleBean mliRegistrationBean,
			BindingResult errors) {
		// TODO Auto-generated method stub
		UserRoleBean userBean = (UserRoleBean) mliRegistrationBean;
		System.out.println("mliname.............." + userBean.getMliName());
		if (userBean.getMliName().equals("") == true) {

			errors.rejectValue("mliName", "error.mliName", "Select a mliName");
		}
		if (userBean.getUserName().equals("") == true) {

			errors.rejectValue("userName", "error.userName",
					"Select a userName");
		}
		if (userBean.getRoleName().equals("") == true) {

			errors.rejectValue("roleName", "error.roleName",
					"Select a User roleName");
		}

		// end-------------------------------------------------------------------------------------
	}

	public void searchValidator(MLIDEtailsBean mliDetails, BindingResult errors) {
		// TODO Auto-generated method stub
		MLIDEtailsBean bean = (MLIDEtailsBean) mliDetails;

		System.out.println(bean.getNameSearch());
		if (bean.getNameSearch().equals("") == true) {

			errors.rejectValue("nameSearch", "error.nameSearch",
					"Select Dropdown Value");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "searchValue",
				"error.searchValue", "Search Value is Required.");

	}

	public void searchValidatorForPortfolio(
			PortfolioGridPanBean portfolioGridPanBean, BindingResult errors) {
		// TODO Auto-generated method stub
		PortfolioGridPanBean bean = (PortfolioGridPanBean) portfolioGridPanBean;

		if (bean.getNameSearch().equals("") == true) {

			errors.rejectValue("nameSearch", "error.nameSearch",
					"Select Dropdown value.");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "searchValue",
				"error.searchValue", "Search Value is Required.");

	}

	public void Cgpanvalidate(CGPANDetailsReportBean bean, BindingResult errors) {
		// TODO Auto-generated method stub
		if (bean.getCgpan().equals("") == true) {

			errors.rejectValue("Cgpan", "error.Cgpan", "Enter the CGPAN");
		}

	}

	public void itPanSearchValidation(Object target, Errors errors) {

		ITPANSearchHistoryBean bean = (ITPANSearchHistoryBean) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itPan",
				"error.itPan", "ITPan is Required !!.");
		if (!(bean.getItPan() != null && bean.getItPan().isEmpty())) {
			pattern = Pattern.compile(PAN_NO);
			matcher = pattern.matcher(bean.getItPan());
			if (!matcher.matches()) {
				errors.rejectValue("itPan", "itPan.incorrect",
						"Invalid ITPan No.");
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"confidentialFlagStatus", "error.confidentialFlagStatus",
				"Check is Required !!.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"creditDecisionFlagStatus", "error.creditDecisionFlagStatus",
				"Check is Required !!.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"changeSubsequentlyFlagStatus",
				"error.changeSubsequentlyFlagStatus", "Check is Required !!.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "noCostFlagStatus",
				"error.noCostFlagStatus", "Check is Required !!.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,
				"cibileCheckFlagStatus", "error.cibileCheckFlagStatus",
				"Ckeck is Required !!.");

	}
	
	public void appDetailsValidate(ApplicationStatusDetailsBean bean, BindingResult errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "appStatus",
				"error.appStatus", "Please select Required Status.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDate",
				"error.toDate", "Todate is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDate",
				"error.fromDate", "From Date is required.");

	}



	public void npaSaveForDate(NPAMarkBean bean, BindingResult result) throws Exception {
		// TODO Auto-generated method stub
		String npaDate = bean.getNpaDt();

		// System.out.println("NPA Date:"+npaDate);
		java.util.Date currentDate = new java.util.Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		String toValue = null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);
		int temp = 0;
		if (month >= 0 && month <= 2) {
			year = year - 1;
			calendar.set(Calendar.MONTH, 9);
			calendar.set(Calendar.DATE, 1);
			calendar.set(Calendar.YEAR, year);
			temp = 0;
		} else if (month >= 3 && month <= 5) {
			calendar.set(Calendar.MONTH, 0);
			calendar.set(Calendar.DATE, 1);
			calendar.set(Calendar.YEAR, year);
			temp = 1;
		} else if (month >= 6 && month <= 8) {
			calendar.set(Calendar.MONTH, 3);
			calendar.set(Calendar.DATE, 1);
			calendar.set(Calendar.YEAR, year);
			temp = 2;
		} else if (month >= 9 && month <= 11) {
			calendar.set(Calendar.MONTH, 6);
			calendar.set(Calendar.DATE, 1);
			calendar.set(Calendar.YEAR, year);
			temp = 3;
		}

		java.util.Date toDate = calendar.getTime();
		toValue = dateFormat.format(toDate);

		
		// System.out.println("To Date:"+npaDate.compareTo(tenureAppprvedDate));
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			

			String stringDate = dateFormat.format(currentDate);
		//	String npaDate1 = dateFormat.format(npaDate);
			
			
		

				if (DateHelper.compareDates(npaDate, stringDate) != 0 && DateHelper.compareDates(npaDate, stringDate) != 1) {
					result.rejectValue("npaDt", "error.npaDt", "Date On Which Account Turned NPA should be earlier than or equal to current Date");
				}
				
				
				if ( DateHelper.compareDates(toValue, npaDate) != 0 && DateHelper.compareDates(toValue, npaDate) != 1) {
					result.rejectValue("npaDt", "error.npaDt", "Date On Which Account Turned NPA should be entered by the end of subsequent quarter.");
				}

			
			
			
		} catch (NumberFormatException numberFormatException) {

		}

		
	}

	//Diksha
		public void ClaimSettledReportValidate(ClaimLodgementBean bean,
				BindingResult errors) {
//			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "MLIID",
//					"error.MLIID", "MLIID Required.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDate",
					"error.toDate", "Todate is required.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDate",
					"error.fromDate", "From Date is required.");
		
		}
		
		public void ClaimDetailsReportValidate(ClaimLodgementBean bean,
				BindingResult errors) {
//			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "MLIID",
//					"error.MLIID", "MLIID Required.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDate",
					"error.toDate", "Todate is required.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDate",
					"error.fromDate", "From Date is required.");
		
		}
		
		public void NPAUpgradationReportValidate(NpaUpgradationBean bean,
				BindingResult errors) {
//			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "MLIID",
//					"error.MLIID", "MLIID Required.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDate",
					"error.toDate", "Todate is required.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDate",
					"error.fromDate", "From Date is required.");
		
		}
		public void npaUpgradationValidation(Object obj, Errors errors) {
			// ValidationUtils.rejectIfEmptyOrWhitespace(errors,
			// "firstDisbDt1","error.firstDisbDt1", "firstDisbDt1 is Required !!.");
			// errors.rejectValue("firstDisbDt1", "firstDisbDt1.incorrect",
			// "Date is Required");
			NpaUpgradationBean bean = (NpaUpgradationBean) obj;

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "CGPAN",
					",error.CGPAN", " CGPAN Field is mandatory.");

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "npaUpgradationDt",
					",error.npaDt", "NPA Upgradation Date is mandatory.");
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "upgradationRemarks",
					",error.npaReason", " Npa upgradation remark is mandatory.");
		
		
	}
		public void appClosureRequestValidation(Object obj, Errors errors) {
			// ValidationUtils.rejectIfEmptyOrWhitespace(errors,
			// "firstDisbDt1","error.firstDisbDt1", "firstDisbDt1 is Required !!.");
			// errors.rejectValue("firstDisbDt1", "firstDisbDt1.incorrect",
			// "Date is Required");
			ApplicationStatusDetailsBean bean = (ApplicationStatusDetailsBean) obj;

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "CGPAN",
					",error.CGPAN", " CGPAN Field is mandatory.");

			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "AppClosureDate",
					",error.AppClosureDate", "App Closure Request Date is mandatory.");
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "AppClosureRemarks",
					",error.AppClosureRemarks", " App Closure Request remark is mandatory.");
		
		
	}
		//End
		public void StatutsWiseValidate(GeneralReport bean, BindingResult errors) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "guaranteeStatus",
					"error.requiredGuranteeOption", "Please select Required Status.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDate",
					"error.toDate", "Todate is required.");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDate",
					"error.fromDate", "From Date is required.");
			 
			 if (!(bean.getToDate() != null && bean.getToDate()
					.isEmpty())) {
				pattern = Pattern.compile(Dateformate);
				matcher = pattern.matcher(bean.getToDate());
				if (!matcher.matches()) {
					errors.rejectValue("toDate", "error.toDate", 
							"Date formate should be dd/mm/yyyy");
				}
			}
			
			if (!(bean.getFromDate() != null && bean.getFromDate()
					.isEmpty())) {
				pattern = Pattern.compile(Dateformate);
				matcher = pattern.matcher(bean.getFromDate());
				if (!matcher.matches()) {
					errors.rejectValue("fromDate", "error.fromDate",
							"Date formate should be dd/mm/yyyy");
				}
			} 

		}


	public void MliReportDetailsValidate(NPADetailsBean bean, BindingResult errors) throws ParseException {
			   ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toDate",
							"error.toDate", "From Date is required.");
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromDate",
							"error.fromDate", "To Date is required");
					
					 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mliLongName",
					 		  "error.mliLongName", "mliLong Name Required.");
					
							if (!(bean.getToDate() != null && bean.getToDate()
									.isEmpty())) {
								pattern = Pattern.compile(Dateformate);
								matcher = pattern.matcher(bean.getToDate());
								if (!matcher.matches()) {
									errors.rejectValue("toDate", "error.toDate", 
											"Date formate should be dd/mm/yyyy");
								}
							}
							
							if (!(bean.getFromDate() != null && bean.getFromDate()
									.isEmpty())) {
								pattern = Pattern.compile(Dateformate);
								matcher = pattern.matcher(bean.getFromDate());
								if (!matcher.matches()) {
									errors.rejectValue("fromDate", "error.fromDate",
											"Date formate should be dd/mm/yyyy");
								}
							} 
							/*if((!bean.getFromDate().equals("null") && !bean.getToDate().equals(""))&& (!bean.getFromDate().equals("") && !bean.getToDate().equals(""))){
							SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
							Date date5 = sdf.parse(bean.getFromDate());
							Date date6 = sdf.parse(bean.getToDate());
							if(date6.after(date5)){
							 errors.rejectValue("toDate", "error.toDate", "Form Date can not greater than To Date");
							}
						}*/
							 
		                 /*if((!bean.getFromDate().equals("null") && !bean.getToDate().equals(""))&& (!bean.getFromDate().equals("") && !bean.getToDate().equals(""))){
				            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
				            Date date5 = sdf.parse(bean.getFromDate());
				            Date date6 = sdf.parse(bean.getToDate());
				            if(date6.after(date5)){
				            errors.rejectValue("toDate", "error.toDate", "FormDate can not greater than toDate");
				           }
				        }*/
			 
			
		
	}

}
