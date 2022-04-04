package com.nbfc.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nbfc.bean.JsonResponse2;
import com.nbfc.bean.MLICheckerApproveRejectDetailsBean;
import com.nbfc.model.AudMLiDetails;
import com.nbfc.model.MLIEditApproveRejectUpdate;
import com.nbfc.model.MLIEditDetails;
import com.nbfc.service.MLIRegService;
import com.nbfc.service.UserActivityService;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class MLIMasterApprovalRejectionController {

	@Autowired
	MLIRegService mliRegService;
	@Autowired
	UserActivityService userActivityService;
	@RequestMapping(value = "/approveRejectMLIDetails", method = RequestMethod.GET)
	public ModelAndView viewEditDetails(
			@ModelAttribute("command") MLICheckerApproveRejectDetailsBean mliRegistrationBean,
			HttpSession session, Model modelReg) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("mliNameList",
				mliRegService.getMLIRegList("State Bank of India","CME"));
		//Added by say 6 FEb-------------------------------------------------------------
				model.put("adminlist",
						userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
				model.put("guaranteelist",
						userActivityService.getActivity("CGTMSECHECKER", "Registration"));
				model.put("applicationList",
						userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
				model.put("RPaymentList",
						userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
				model.put("actName", userActivityService.getActivityName("CGTMSECHECKER",
						"approveRejectNewMLIDetails"));// Added by Say 31 Jan19
				model.put("repList",
						userActivityService.getReport("CGTMSECHECKER", "User_Report"));
				model.put("GMaintainlist", userActivityService.getActivity(
						"CGTMSECHECKER", "Guarantee_Maintenance"));
				model.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
				model.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
		model.put("homePage","cgtmseCheckerHome");
		return new ModelAndView("approveRejectMLIDetails", model);
	}
	
	@RequestMapping(value = "/approveRejectMLIDetails",params="view", method = RequestMethod.POST)
	public ModelAndView viewAudAndUpdatedDetails(
			@ModelAttribute("command") MLICheckerApproveRejectDetailsBean mliRegistrationBean,
			HttpSession session, Model modelReg) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("updatedMLIDetails", mliRegService.getMLIDtl(mliRegistrationBean.getMliLongName()));
		model.put("audMLIDetails", mliRegService.getMLIAudDetails(mliRegistrationBean.getMliLongName()));
		model.put("mliNameList",
				mliRegService.getMLIRegList("State Bank of India","CME"));
		//Added by say 6 FEb-------------------------------------------------------------
				model.put("adminlist",
						userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
				model.put("guaranteelist",
						userActivityService.getActivity("CGTMSECHECKER", "Registration"));
				model.put("applicationList",
						userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
				model.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
				model.put("RPaymentList",
						userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
				model.put("actName", userActivityService.getActivityName("CGTMSECHECKER",
						"approveRejectNewMLIDetails"));// Added by Say 31 Jan19
				model.put("GMaintainlist", userActivityService.getActivity(
						"CGTMSECHECKER", "Guarantee_Maintenance"));
				model.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
		model.put("homePage","cgtmseCheckerHome");
		model.put("repList",
				userActivityService.getReport("CGTMSECHECKER", "User_Report"));
		return new ModelAndView("approveRejectMLIDetails", model);
	}
	
	@RequestMapping(value = "/approveRejectMLIDetails",params="approve", method = RequestMethod.POST)
	public ModelAndView approveMLIDetails(
			@ModelAttribute("command") MLICheckerApproveRejectDetailsBean mliRegistrationBean,
			HttpSession session, Model modelReg) throws ParseException {
		Map<String, Object> model = new HashMap<String, Object>();
		String userID = (String) session.getAttribute("userId");
		// mliRegService.getMLIDtl(mliRegistrationBean.getMliLongName());
		 //prepareModelForMLIDerailsApprove(mliRegService.getMLIDtl(mliRegistrationBean.getMliLongName()),userID);
		 mliRegService.updateMLIApproveRejectStatus(prepareModelForMLIDerailsApprove(mliRegService.getMLIDtl(mliRegistrationBean.getMliLongName()),userID));
		 model.put("mliNameList",
				 mliRegService.getMLIRegList("State Bank of India","CME"));
			modelReg.addAttribute("message", "MLI has been Successfully Approved.");
			//Added by say 6 FEb-------------------------------------------------------------
			model.put("adminlist",
					userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
			model.put("guaranteelist",
					userActivityService.getActivity("CGTMSECHECKER", "Registration"));
			model.put("applicationList",
					userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
			model.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
			model.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
			model.put("RPaymentList",
					userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
			model.put("actName", userActivityService.getActivityName("CGTMSECHECKER",
					"approveRejectNewMLIDetails"));// Added by Say 31 Jan19
			model.put("GMaintainlist", userActivityService.getActivity(
					"CGTMSECHECKER", "Guarantee_Maintenance"));
			model.put("homePage","cgtmseCheckerHome");
			model.put("repList",
					userActivityService.getReport("CGTMSECHECKER", "User_Report"));
		 return new ModelAndView("approveRejectMLIDetails", model);
	}
	
	@RequestMapping(value = "/approveRejectMLIDetails",params="reject", method = RequestMethod.POST)
	public ModelAndView rejectMLIDetails(
			@ModelAttribute("command")MLICheckerApproveRejectDetailsBean mliRegistrationBean,
			HttpSession session, Model modelReg) throws ParseException {
		Map<String, Object> model = new HashMap<String, Object>();
		String userID = (String) session.getAttribute("userId");
		//mliRegService.getMLIDtl(mliRegistrationBean.getMliLongName());
		//prepareModelForMLIDerailsReject(mliRegService.getMLIDtl(mliRegistrationBean.getMliLongName()),userID);
		mliRegService.updateMLIApproveRejectStatus(prepareModelForMLIDerailsReject(mliRegService.getMLIDtl(mliRegistrationBean.getMliLongName()),userID,mliRegistrationBean.getRemarks()));
		model.put("mliNameList",
				mliRegService.getMLIRegList("State Bank of India","CME"));
		modelReg.addAttribute("message", "MLI has been Successfully Rejected.");
		//Added by say 6 FEb-------------------------------------------------------------
				model.put("adminlist",
						userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
				model.put("guaranteelist",
						userActivityService.getActivity("CGTMSECHECKER", "Registration"));
				model.put("applicationList",
						userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
				model.put("RPaymentList",
						userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
				model.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
				model.put("actName", userActivityService.getActivityName("CGTMSECHECKER",
						"approveRejectNewMLIDetails"));// Added by Say 31 Jan19
				model.put("GMaintainlist", userActivityService.getActivity(
						"CGTMSECHECKER", "Guarantee_Maintenance"));
				model.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
		model.put("homePage","cgtmseCheckerHome");
		model.put("repList",
				userActivityService.getReport("CGTMSECHECKER", "User_Report"));
		return new ModelAndView("approveRejectMLIDetails", model);
	}
	
	@RequestMapping(value = "/getmliDetailsForApprovalRejection", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse2 getPortfolioDetails(
			@ModelAttribute(value = "mliLongName") MLICheckerApproveRejectDetailsBean mliRegistrationBean,
			BindingResult result, String mliLongName)
			throws NullPointerException, Exception {
		System.out.println("portfolioNum " + mliLongName);
		JsonResponse2 res = new JsonResponse2();
		System.out.println(mliRegService.getMLIDtl(mliLongName));
		mliRegService.getMLIAudDetails(mliLongName);
		MLICheckerApproveRejectDetailsBean beanlist = prepareMLIRegistrationModel(mliRegService.getMLIDtl(mliLongName),mliRegService.getMLIAudDetails(mliLongName));

		res.setStatus("SUCCESS");
		res.setResult(beanlist);
		return res;
	}
	
	private MLICheckerApproveRejectDetailsBean prepareMLIRegistrationModel(
			MLIEditDetails mliRegistration,AudMLiDetails audMLiDetails) throws Exception {
		MLICheckerApproveRejectDetailsBean mliRegBean = new MLICheckerApproveRejectDetailsBean();

		mliRegBean.setCity(mliRegistration.getCity());
		mliRegBean.setCompanyAddress(mliRegistration.getCompanyAddress());
		mliRegBean.setCompanyCIN(mliRegistration.getCompanyCIN());
		mliRegBean.setCompanyPAN(mliRegistration.getCompanyPAN());
		mliRegBean.setContactPerson(mliRegistration.getContactPerson());
		mliRegBean.setDistrict(mliRegistration.getDistrict());
		mliRegBean.setEmailId(mliRegistration.getEmailId());
		mliRegBean.setFaxNumber(mliRegistration.getFaxNumber());
		mliRegBean.setGstinNumber(mliRegistration.getGstinNumber());
		mliRegBean.setLandlineNumber(mliRegistration.getLandlineNumber());
		mliRegBean.setLongName(mliRegistration.getLongName());
		mliRegBean.setMobileNUmber(mliRegistration.getMobileNUmber());
		mliRegBean.setPincode(mliRegistration.getPincode());
		mliRegBean.setRating(mliRegistration.getRating());
		mliRegBean.setRatingAgency(mliRegistration.getRatingAgency());
		mliRegBean.setRbiReggistrationNumber(mliRegistration.getRbiReggistrationNumber());
		System.out.println(mliRegistration.getRbiReggistrationNumber());
		
		/*System.out.println("employeeBean.getPortfolio_start_date()  :"
				+ mliRegistration.getRatingDate());
		String date = mliRegistration.getRatingDate().substring(0, 10);
		String dateSplit[] = date.split("-");
		String formatedSanctionDate = dateSplit[2] + "-" + dateSplit[1] + "-"
				+ dateSplit[0];*/
		
		mliRegBean.setRatingDate(mliRegistration.getRatingDate());
		mliRegBean.setShortName(mliRegistration.getShortName());
		mliRegBean.setState(mliRegistration.getState());

		mliRegBean.setEditCity(audMLiDetails.getCity());
		mliRegBean.setEditCompanyAddress(audMLiDetails.getCompanyAddress());
		mliRegBean.setEditCompanyCIN(audMLiDetails.getCompanyCIN());
		mliRegBean.setEditCompanyPAN(audMLiDetails.getCompanyPAN());
		mliRegBean.setEditContactPerson(audMLiDetails.getContactPerson());
		mliRegBean.setEditDistrict(audMLiDetails.getDistrict());
		mliRegBean.setEditEmailId(audMLiDetails.getEmailId());
		mliRegBean.setEditFaxNumber(audMLiDetails.getFaxNumber());
		mliRegBean.setEditGstinNumber(audMLiDetails.getGstinNumber());
		mliRegBean.setEditLandlineNumber(audMLiDetails.getLandlineNumber());
		mliRegBean.setEditLongName(audMLiDetails.getLongName());
		mliRegBean.setEditMobileNUmber(audMLiDetails.getMobileNUmber());
		mliRegBean.setEditPincode(audMLiDetails.getPincode());
		mliRegBean.setEditRating(audMLiDetails.getRating());
		mliRegBean.setEditRatingAgency(audMLiDetails.getRatingAgency());	
		mliRegBean.setEditRatingDate(audMLiDetails.getRatingDate());
		mliRegBean.setEditShortName(audMLiDetails.getShortName());
		mliRegBean.setEditState(audMLiDetails.getState());
		mliRegBean.setEditRBIReggistrationNumber(audMLiDetails.getRbiReggistrationNumber());
		System.out.println(audMLiDetails.getRbiReggistrationNumber());
		return mliRegBean;
	}
	
	private MLIEditApproveRejectUpdate prepareModelForMLIDerailsApprove(MLIEditDetails mliRegNean,String userId) throws ParseException {
		MLIEditApproveRejectUpdate mliReg = new MLIEditApproveRejectUpdate();

		mliReg.setCity(mliRegNean.getCity());
		mliReg.setCompanyAddress(mliRegNean.getCompanyAddress());
		mliReg.setCompanyCIN(mliRegNean.getCompanyCIN());
		mliReg.setCompanyPAN(mliRegNean.getCompanyPAN());
		mliReg.setContactPerson(mliRegNean.getContactPerson());
		mliReg.setDistrict(mliRegNean.getDistrict());
		mliReg.setEmailId(mliRegNean.getEmailId());
		mliReg.setFaxNumber(mliRegNean.getFaxNumber());
		mliReg.setGstinNumber(mliRegNean.getGstinNumber());
		mliReg.setLandlineNumber(mliRegNean.getLandlineNumber());
		mliReg.setLongName(mliRegNean.getLongName());
		mliReg.setMem_bnk_id(mliRegNean.getMem_bnk_id());
		mliReg.setMem_brn_id(mliRegNean.getMem_brn_id());
		mliReg.setMem_zne_id(mliRegNean.getMem_zne_id());
		mliReg.setMobileNUmber(mliRegNean.getMobileNUmber());
		mliReg.setPincode(mliRegNean.getPincode());
		mliReg.setRating(mliRegNean.getRating());
		mliReg.setRatingAgency(mliRegNean.getRatingAgency());
		
		//Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(mliRegNean.getEditRatingDate());  
		
		mliReg.setRatingDate(mliRegNean.getRatingDate());
		mliReg.setRbiReggistrationNumber(mliRegNean.getRbiReggistrationNumber());
		mliReg.setUserID(mliRegNean.getUserID());
		mliReg.setStatus("CCA");
		mliReg.setMem_bnk_name(mliRegNean.getMem_bnk_name());
		mliReg.setShortName(mliRegNean.getShortName());
		mliReg.setState(mliRegNean.getState());
		mliReg.setFaxCode(mliRegNean.getFaxCode());
		mliReg.setPhone_code(mliRegNean.getPhone_code());
		mliReg.setMem_status(mliRegNean.getMem_status());
		mliReg.setMem_mcgf(mliRegNean.getMem_mcgf());
		mliReg.setCgtmse_maker_id(mliRegNean.getCgtmse_maker_id());
		mliReg.setCgtmse_maker_date(mliRegNean.getCgtmse_maker_date());
		System.out.println(mliRegNean.getCgtmse_maker_date());
		mliReg.setCgtmse_checker_id(userId);
		
		return mliReg;
	}
	
	private MLIEditApproveRejectUpdate prepareModelForMLIDerailsReject(MLIEditDetails mliRegNean,String userId,String remarks) throws ParseException {
		MLIEditApproveRejectUpdate mliReg = new MLIEditApproveRejectUpdate();

		mliReg.setCity(mliRegNean.getCity());
		mliReg.setCompanyAddress(mliRegNean.getCompanyAddress());
		mliReg.setCompanyCIN(mliRegNean.getCompanyCIN());
		mliReg.setCompanyPAN(mliRegNean.getCompanyPAN());
		mliReg.setContactPerson(mliRegNean.getContactPerson());
		mliReg.setDistrict(mliRegNean.getDistrict());
		mliReg.setEmailId(mliRegNean.getEmailId());
		mliReg.setFaxNumber(mliRegNean.getFaxNumber());
		mliReg.setGstinNumber(mliRegNean.getGstinNumber());
		mliReg.setLandlineNumber(mliRegNean.getLandlineNumber());
		mliReg.setLongName(mliRegNean.getLongName());
		mliReg.setMem_bnk_id(mliRegNean.getMem_bnk_id());
		mliReg.setMem_brn_id(mliRegNean.getMem_brn_id());
		mliReg.setMem_zne_id(mliRegNean.getMem_zne_id());
		mliReg.setMobileNUmber(mliRegNean.getMobileNUmber());
		mliReg.setPincode(mliRegNean.getPincode());
		mliReg.setRating(mliRegNean.getRating());
		mliReg.setRatingAgency(mliRegNean.getRatingAgency());
		
		//Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(mliRegNean.getEditRatingDate());  
		
		mliReg.setRatingDate(mliRegNean.getRatingDate());
		mliReg.setRbiReggistrationNumber(mliRegNean.getRbiReggistrationNumber());
		mliReg.setUserID(mliRegNean.getUserID());
		mliReg.setStatus("CER");
		mliReg.setMem_bnk_name(mliRegNean.getMem_bnk_name());
		mliReg.setShortName(mliRegNean.getShortName());
		mliReg.setState(mliRegNean.getState());
		mliReg.setPhone_code(mliRegNean.getPhone_code());
		mliReg.setMem_status(mliRegNean.getMem_status());
		mliReg.setMem_mcgf(mliRegNean.getMem_mcgf());
		mliReg.setCgtmse_maker_id(mliRegNean.getCgtmse_maker_id());
		mliReg.setCgtmse_checker_id(userId);
		System.out.println(mliRegNean.getCgtmse_maker_date());
		mliReg.setCgtmse_maker_date(mliRegNean.getCgtmse_maker_date());
		mliReg.setRemarks(remarks);
		
		return mliReg;
	}
	 @ExceptionHandler(CustomExceptionHandler.class)
		public ModelAndView handleCustomException(CustomExceptionHandler ex) {
			Map<String, Object> model1 = new HashMap<String, Object>();		

			 //model1.put("actList",						userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
			// model1.put("homePage","nbfcCheckerHome");
			ModelAndView model = new ModelAndView("customException",model1);
			model.addObject("customException", ex.getMessage());
			return model;

		}

		@ExceptionHandler(Exception.class)
		public ModelAndView handleAllException(Exception ex) {
			Map<String, Object> model1 = new HashMap<String, Object>();		

			// model1.put("actList",						userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
			// model1.put("homePage","nbfcCheckerHome");
			ModelAndView model = new ModelAndView("exception",model1);
			model.addObject("exception", ex.getCause());
			return model;

		}

		@ExceptionHandler(ArithmeticException.class)
		public ModelAndView handleArithException(ArithmeticException ex) {
			Map<String, Object> model1 = new HashMap<String, Object>();		

			 //model1.put("actList",						userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
			// model1.put("homePage","nbfcCheckerHome");
			ModelAndView model = new ModelAndView("exception",model1);
			model.addObject("exception", ex.getMessage());
			return model;

		}

		@ExceptionHandler(NullPointerException.class)
		public ModelAndView handleNullException(NullPointerException ex) {
			Map<String, Object> model1 = new HashMap<String, Object>();		

			// model1.put("actList",						userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
			// model1.put("homePage","nbfcCheckerHome");
			ModelAndView model = new ModelAndView("exception",model1);
			model.addObject("exception", "Data is null");
			return model;

		}

}
