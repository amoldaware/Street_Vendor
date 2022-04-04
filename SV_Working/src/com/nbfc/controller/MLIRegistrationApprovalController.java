package com.nbfc.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.nbfc.bean.MLICheckerApproveRejectDetailsBean;
import com.nbfc.bean.MLIDEtailsBean;
import com.nbfc.bean.MLIRegistrationApprovalBean;
import com.nbfc.bean.MLIRegistrationBean;
import com.nbfc.model.MLIMainEditDetails;
import com.nbfc.model.MLIRegistration;
import com.nbfc.model.MLIRegistrationAppraval;
import com.nbfc.service.MLIDetailsService;
import com.nbfc.service.MLIRegService;
import com.nbfc.service.StateService;
import com.nbfc.service.UserActivityService;
import com.nbfc.validation.EmployeeValidator;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class MLIRegistrationApprovalController {
	
	
	@Autowired
	MLIRegService mliRegService;
	@Autowired
	EmployeeValidator validator;
	@Autowired
	MLIDetailsService mliDetailsService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	StateService stateService;
	@RequestMapping(value = "/mliNewRegistrationPage", method = RequestMethod.GET)
	public ModelAndView mliRegistrationApproveReg(
			@ModelAttribute("command") MLIRegistrationBean mliRegistrationBean,
			HttpSession session, Model modelReg,String mliLongName) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println("List................"+mliLongName);
		/*System.out.println(mliDetailsService.getMLIEditDetails(mliLongName, "CME"));*/
		//System.out.println(mliRegService.getMLIRegList("State Bank of India"));
		model.put("mliNameList",prepareMLIRegistrationModel(mliDetailsService.getMLIEditDetails(mliLongName, "CMR"),stateService));
		//Added by say 6 FEb-------------------------------------------------------------
		model.put("adminlist",
				userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
		model.put("guaranteelist",
				userActivityService.getActivity("CGTMSECHECKER", "Registration"));
		model.put("applicationList",
				userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
		model.put("RPaymentList",
				userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSECHECKER", "Guarantee_Maintenance"));
		model.put("actName", userActivityService.getActivityName("CGTMSECHECKER",
				"approveRejectNewMLIDetails"));// Added by Say 31 Jan19
		model.put("repList", userActivityService.getReport(
				"CGTMSECHECKER", "User_Report"));
		model.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
//		model.put("actList",
//				userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
//		model.put("actList",
//				userActivityService.getActivity("CGTMSECHECKER", "User_Activity"));
		model.put("homePage","cgtmseCheckerHome");
		return new ModelAndView("mliNewRegistrationSuccessPage", model);
	}
	
	/*@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView viewEditDetails(
			@ModelAttribute("command") MLICheckerApproveRejectDetailsBean mliRegistrationBean,
			HttpSession session, Model modelReg) {
		Map<String, Object> model = new HashMap<String, Object>();

		
		 * model.put("mlisList",
		 * prepareMLIListofBean(mliDetailsService.getMLIAllDetails()));
		 

		model.put("mlisList", prepareMLIListofBean(mliDetailsService
				.getMLIDetailsForApproval("CME", "CMR")));
		model.put("actList",
				userActivityService.getActivity("CGTMSECHECKER", "User_Activity"));
		model.put("homePage","cgtmseCheckerHome");
		return new ModelAndView("approveRejectNewMLIDetails", model);
	}
	*/
	@RequestMapping(value = "/approveRejectNewMLIDetailsBack", method = RequestMethod.POST)
	public ModelAndView mliRegCheckerApproveBack(
			@ModelAttribute("command") MLICheckerApproveRejectDetailsBean mliRegistrationBean,
			HttpSession session, Model modelReg) throws ParseException {
		Map<String, Object> model = new HashMap<String, Object>();

		/*
		 * model.put("mlisList",
		 * prepareMLIListofBean(mliDetailsService.getMLIAllDetails()));
		 */

		model.put("mlisList", prepareMLIListofBean(mliDetailsService
				.getMLIDetailsForApproval("CME", "CMR","CCA")));
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
		model.put("repList", userActivityService.getReport(
				"CGTMSECHECKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSECHECKER", "Guarantee_Maintenance"));
		model.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
//		model.put("actList",
//				userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
		model.put("homePage","cgtmseCheckerHome");
		return new ModelAndView("approveRejectNewMLIDetails", model);
	}
	
	@RequestMapping(value = "/approveMLINewRegistrationPage", method = RequestMethod.POST)
	public ModelAndView mliRegCheckerApprove(
			@ModelAttribute("command") MLIRegistrationBean mliRegistrationBean,
			HttpSession session, Model modelReg) throws ParseException {
		Map<String, Object> model = new HashMap<String, Object>();
		String userID = (String) session.getAttribute("userId");
		String mliName=mliRegistrationBean.getLongName();
		MLIRegistrationAppraval dfgsdf=mliRegService.getMLIDetailsForApproveReject(mliName);
		System.out.println("1"+dfgsdf);
		MLIRegistrationApprovalBean sdfasd=prepareBeanForMLI(dfgsdf);
		System.out.println("2");
		MLIRegistrationAppraval sdfasdf=prepareEntityForApproval(sdfasd,userID);
		System.out.println("3");
		mliRegService.updateMLIDetails(sdfasdf);
		System.out.println("4");
		/*mliRegService.updateMLIDetails(prepareEntityForApproval(
				prepareBeanForMLI(mliRegService
						.getMLIDetails(mliRegistrationBean.getLongName())),
				userID));*/
		
		
		
		return new ModelAndView("redirect:/successFullyApproved.html");
	}
	
	@RequestMapping(value = "/rejectMLINewRegistrationPage", method = RequestMethod.POST)
	public ModelAndView mliRegCheckerReject(
			@ModelAttribute("command") MLIRegistrationBean mliRegistrationBean,BindingResult result,
			HttpSession session, Model modelReg) throws ParseException {
		Map<String, Object> model = new HashMap<String, Object>();
		
		/*validator.validateUserRejection(mliRegistrationBean, result);
		if (result.hasErrors()) {
			model.put("mliNameList",mliRegService.getMLIRegList("State Bank of India","CMR"));
			return new ModelAndView("mliRegApprovalRegection", model);
		}*/
		String remarks=mliRegistrationBean.getRemarks();
		String userID = (String) session.getAttribute("userId");
		
			
		mliRegService.updateMLIDetails(prepareEntityForRejection(
				prepareBeanForMLI(mliRegService
						.getMLIDetailsForApproveReject(mliRegistrationBean.getLongName())),
				userID,remarks));
		
		return new ModelAndView("redirect:/successFullyReject.html");
	}
	
	@RequestMapping(value = "/successApproveNewRegistrationPage", method = RequestMethod.GET)
	public ModelAndView successMLIRegistrationApproveReg(
			@ModelAttribute("command") MLIRegistrationBean mliRegistrationBean,
			HttpSession session, Model modelReg,String mliLongName) {
		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println("List................"+mliLongName);
		/*System.out.println(mliDetailsService.getMLIEditDetails(mliLongName, "CME"));*/
		//System.out.println(mliRegService.getMLIRegList("State Bank of India"));
		modelReg.addAttribute("message", "MLI has been Successfully Approved.");
		//Added by say 6 FEb-------------------------------------------------------------
		model.put("adminlist",
				userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
		model.put("guaranteelist",
				userActivityService.getActivity("CGTMSECHECKER", "Registration"));
		model.put("applicationList",
				userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
		model.put("RPaymentList",
				userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
		model.put("repList", userActivityService.getReport(
				"CGTMSECHECKER", "User_Report"));
		model.put("actName", userActivityService.getActivityName("CGTMSECHECKER",
				"approveRejectNewMLIDetails"));// Added by Say 31 Jan19
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSECHECKER", "Guarantee_Maintenance"));
		model.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
//		model.put("actList",
//				userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
		model.put("homePage","cgtmseCheckerHome");
		return new ModelAndView("mliNewRegistrationSuccessPage", model);
	}
	
	@RequestMapping(value = "/successRejectNewRegistrationPage", method = RequestMethod.GET)
	public ModelAndView rejectMLIRegistrationApproveReg(
			@ModelAttribute("command") MLIRegistrationBean mliRegistrationBean,
			HttpSession session, Model modelReg,String mliLongName) {
		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println("List................"+mliLongName);
		/*System.out.println(mliDetailsService.getMLIEditDetails(mliLongName, "CME"));*/
		//System.out.println(mliRegService.getMLIRegList("State Bank of India"));
		modelReg.addAttribute("message", "MLI has been Rejected.");
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
		model.put("repList", userActivityService.getReport(
				"CGTMSECHECKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSECHECKER", "Guarantee_Maintenance"));
		model.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
//		model.put("actList",
//				userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
		model.put("homePage","cgtmseCheckerHome");
		return new ModelAndView("mliNewRegistrationSuccessPage", model);
	}
	private MLIRegistrationApprovalBean prepareBeanForMLI(MLIRegistrationAppraval mliRegNean) {
		MLIRegistrationApprovalBean mliReg = new MLIRegistrationApprovalBean();

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
		mliReg.setRatingDate(mliRegNean.getRatingDate());
		mliReg.setRbiReggistrationNumber(mliRegNean.getRbiReggistrationNumber());
		mliReg.setUserID(mliRegNean.getUserID());
		mliReg.setStatus(mliRegNean.getStatus());
		mliReg.setMem_bnk_name(mliRegNean.getMem_bnk_name());
		mliReg.setShortName(mliRegNean.getShortName());
		mliReg.setState(mliRegNean.getState());
		mliReg.setPhone_code(mliRegNean.getPhone_code());
		mliReg.setMem_status(mliRegNean.getMem_status());
		mliReg.setMem_mcgf(mliRegNean.getMem_mcgf());

		return mliReg;
	}
	
	private MLIRegistrationAppraval prepareEntityForApproval(
			MLIRegistrationApprovalBean mliRegNean, String cgtmseCheckerId) throws ParseException {
		MLIRegistrationAppraval mliReg = new MLIRegistrationAppraval();

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
		mliReg.setRatingDate(mliRegNean.getRatingDate());
		mliReg.setRbiReggistrationNumber(mliRegNean.getRbiReggistrationNumber());
		mliReg.setUserID(mliRegNean.getUserID());
		mliReg.setStatus("CCA");
		mliReg.setMem_bnk_name(mliRegNean.getMem_bnk_name());
		mliReg.setShortName(mliRegNean.getShortName());
		mliReg.setState(mliRegNean.getState());
		mliReg.setPhone_code(mliRegNean.getPhone_code());
		mliReg.setMem_status(mliRegNean.getMem_status());
		mliReg.setMem_mcgf(mliRegNean.getMem_mcgf());
		mliReg.setCgtmse_checker_id(cgtmseCheckerId);

		return mliReg;
	}
	
	private MLIRegistrationAppraval prepareEntityForRejection(
			MLIRegistrationApprovalBean mliRegNean,String cgtmseCheckerId,String checkerRemarks) throws ParseException {
		MLIRegistrationAppraval mliReg = new MLIRegistrationAppraval();

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
		//DateFormat df = new SimpleDateFormat("MM-dd-yyyy"); 
		mliReg.setRatingDate(mliRegNean.getRatingDate());
		mliReg.setRbiReggistrationNumber(mliRegNean.getRbiReggistrationNumber());
		mliReg.setUserID(mliRegNean.getUserID());
		mliReg.setStatus("CCR");
		mliReg.setMem_bnk_name(mliRegNean.getMem_bnk_name());
		mliReg.setShortName(mliRegNean.getShortName());
		mliReg.setState(mliRegNean.getState());
		mliReg.setPhone_code(mliRegNean.getPhone_code());
		mliReg.setMem_status(mliRegNean.getMem_status());
		mliReg.setMem_mcgf(mliRegNean.getMem_mcgf());
		mliReg.setCgtmse_checker_id(cgtmseCheckerId);
		mliReg.setRemarks(checkerRemarks);

		return mliReg;
	}
	private List<MLIDEtailsBean> prepareMLIListofBean(
			List<MLIRegistration> employees) {
		
		List<MLIDEtailsBean> beans = null;
		if (employees != null && !employees.isEmpty()) {
			beans = new ArrayList<MLIDEtailsBean>();
			MLIDEtailsBean bean = null;
			for (MLIRegistration employee : employees) {
				bean = new MLIDEtailsBean();
				bean.setMliLongName(employee.getLongName());
				bean.setShortName(employee.getShortName());
				bean.setCompanyAddress(employee.getCompanyAddress());
				bean.setCity(employee.getCity());
				bean.setCompanyPAN(employee.getCompanyPAN());
				if (employee.getStatus().equals("CCA")) {
					bean.setStatus("Approved");
				} else if (employee.getStatus().equals("CME")) {
					bean.setStatus("Pending for Approval(Edit)");
				} else if (employee.getStatus().equals("CCR")) {
					bean.setStatus("Rejected");
				}
				else if(employee.getStatus().equals("CEMMA")){
					bean.setStatus("CEMMA");
			    }
				else if(employee.getStatus().equals("CMR")){
					bean.setStatus("Pending For Approval(New)");
			    } 
				beans.add(bean);
			}
		}
		return beans;
	}
	
	private MLIDEtailsBean prepareMLIRegistrationModel(
			MLIMainEditDetails mliRegistration,StateService stateService) throws Exception {
		MLIDEtailsBean mliRegBean = new MLIDEtailsBean();

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

		/*System.out.println("employeeBean.getPortfolio_start_date()  :"
				+ mliRegistration.getRatingDate());
		String date = mliRegistration.getRatingDate().substring(0, 10);
		String dateSplit[] = date.split("-");
		String formatedSanctionDate = dateSplit[2] + "-" + dateSplit[1] + "-"
				+ dateSplit[0];*/
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
		if(mliRegistration.getRatingDate()!=null){
		String strDate = dateFormat.format(mliRegistration.getRatingDate());  
		
		mliRegBean.setRatingDate(strDate);
		
		}
		
		mliRegBean.setShortName(mliRegistration.getShortName());
		//mliRegBean.setState(mliRegistration.getState());
		mliRegBean.setState(stateService.stateName(mliRegistration.getState()).getSte_name());
		mliRegBean.setRbiReggistrationNumber(mliRegistration
				.getRbiReggistrationNumber());
		mliRegBean.setPhone_code(mliRegistration.getPhone_code());

		return mliRegBean;
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

		// added say 22 nov 2018------------------------------------------------------------
				@ExceptionHandler(NullPointerException.class)
				public ModelAndView handleNullException(NullPointerException ex) {
					Map<String, Object> model1 = new HashMap<String, Object>();		
					
//				   model1.put("exception", null);
					//Added by say 6 FEb-------------------------------------------------------------
					model1.put("adminlist",
							userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
					model1.put("guaranteelist",
							userActivityService.getActivity("CGTMSECHECKER", "Registration"));
					model1.put("applicationList",
							userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
					model1.put("RPaymentList",
							userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
					model1.put("GMaintainlist", userActivityService.getActivity(
							"CGTMSECHECKER", "Guarantee_Maintenance"));
					model1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
					model1.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
//					model1.put("actName", userActivityService.getActivityName("CGTMSECHECKER",
//							"approveRejectNewMLIDetails"));// Added by Say 31 Jan19
//					model.put("actList",
//							userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
					model1.put("homePage","cgtmseCheckerHome");
		           model1.put("exceptionMsg",null);
		            
//		            ModelAndView model = new ModelAndView("exception",model1);
//					model.addObject("exception", "Data is null");
//				
//					return model;
		      return new ModelAndView("exceptionPage", model1);

		}

}
