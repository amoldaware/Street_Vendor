package com.nbfc.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.nbfc.bean.JsonResponse2;
import com.nbfc.bean.MLICheckerApproveRejectDetailsBean;
import com.nbfc.bean.MLIDEtailsBean;
import com.nbfc.common.utility.method.NBFCCommonUtility;
import com.nbfc.exception.NBFCException;
import com.nbfc.model.AudMLiDetails;
import com.nbfc.model.MLIEditApproveRejectUpdate;
import com.nbfc.model.MLIEditDetails;
import com.nbfc.model.MLIMainEditDetails;
import com.nbfc.model.MLIRegistration;
import com.nbfc.service.MLIDetailsService;
import com.nbfc.service.MLIRegService;
import com.nbfc.service.StateService;
import com.nbfc.service.UserActivityService;
import com.nbfc.validation.EmployeeValidator;
import com.raistudies.domain.CustomExceptionHandler;


@Controller
public class MLIEditAppvalController {

	@Autowired
	MLIRegService mliRegService;
	@Autowired
	MLIDetailsService mliDetailsService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	EmployeeValidator validator;
	@Autowired
	StateService stateService;
	@Autowired
	MenuUtilsController menuUtilsController;
	
	@RequestMapping(value = "/approveRejectNewMLIDetails", method = RequestMethod.GET)
	public ModelAndView viewEditDetails(
			@ModelAttribute("command") MLICheckerApproveRejectDetailsBean mliRegistrationBean,
			HttpSession session, Model modelReg) {
		String userId = (String) session.getAttribute("userId");
		String Role = (String) session.getAttribute("uRole");
		Map<String, Object> model = new HashMap<String, Object>();

		/*
		 * model.put("mlisList",
		 * prepareMLIListofBean(mliDetailsService.getMLIAllDetails()));
		 */
		model = menuUtilsController.prepareMenu(session, Role, userId);
		model.put("mlisList", prepareMLIListofBean(mliDetailsService
				.getMLIDetailsForApproval("CME", "CMR","CCA")));
		//Added by say 6 FEb-------------------------------------------------------------
		/*model.put("guaranteelist",
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
		*/
		model.put("homePage","cgtmseCheckerHome");
		return new ModelAndView("approveRejectNewMLIDetails", model);
	}
	// added by say 24 jan 2019------------------------------------------------
	@RequestMapping(value = "/ApproverejectMliDetailsByIndex", method = RequestMethod.GET)
	public ModelAndView getMLIDetailsByIndex(
			@ModelAttribute("command") MLIDEtailsBean mliDetails,
			BindingResult result, Model modelMsg, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		String Role = (String) session.getAttribute("uRole");
		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println("Search Columan Name " + mliDetails.getNameSearch()
				+ " value : " + mliDetails.getSearchValue());
		System.out.println("OK");
	
		validator.searchValidator(mliDetails, result);
		if (result.hasErrors()) {
			// log.info("Error in field*******************************************************************************************************************");
			model = menuUtilsController.prepareMenu(session, Role, userId);
			model.put("mlisList", prepareMLIListofBean(mliDetailsService
					.getMLIDetailsForApproval("CME", "CMR","CCA")));
			/*//Added by say 6 FEb-------------------------------------------------------------
			model.put("adminlist",
					userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
			model.put("guaranteelist",
					userActivityService.getActivity("CGTMSECHECKER", "Registration"));
			model.put("applicationList",
					userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
			model.put("RPaymentList",
					userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
			 model.put("repList",
						userActivityService.getReport("CGTMSECHECKER", "User_Report"));
			model.put("actName", userActivityService.getActivityName("CGTMSECHECKER",
					"approveRejectNewMLIDetails"));// Added by Say 31 Jan19
			model.put("GMaintainlist", userActivityService.getActivity(
					"CGTMSECHECKER", "Guarantee_Maintenance"));
			model.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
			model.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
			model.put("homePage","cgtmseCheckerHome");*/
			return new ModelAndView("approveRejectNewMLIDetails", model);

		}
		model.put("mlisList", prepareMLIListofBean(mliDetailsService
				.ApproverejectMliDetailsByIndex(mliDetails.getNameSearch(),
						mliDetails.getSearchValue())));
		/*
		 * model.put( "mlisList",
		 * prepareMLIListofBean(mliDetailsService.getMLIAllDetails()));
		 */
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
//		model.put("actList",
//				userActivityService.getActivity("CGTMSECHECKER", "User_Activity"));
		model.put("homePage","cgtmseCheckerHome");
		return new ModelAndView("approveRejectNewMLIDetails", model);
	}

	// ended---------------------------------------------------------------------

	@RequestMapping(value = "/successFullyApproved", method = RequestMethod.GET)
	public ModelAndView viewSuccessApproveDetails(
			@ModelAttribute("command") MLICheckerApproveRejectDetailsBean mliRegistrationBean,
			HttpSession session, Model modelReg) {
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
				 model.put("repList",
							userActivityService.getReport("CGTMSECHECKER", "User_Report"));
		model.put("homePage","cgtmseCheckerHome");
		model.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
		model.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSECHECKER", "Guarantee_Maintenance"));
		modelReg.addAttribute("message", "MLI Registration Successfully  Approved .");
		return new ModelAndView("mliRegApprovalRegectionSuccess", model);
	}
	
	@RequestMapping(value = "/successFullyApprovedExit", method = RequestMethod.GET)
	public ModelAndView viewSuccessApproveExitDetails(
			@ModelAttribute("command") MLICheckerApproveRejectDetailsBean mliRegistrationBean,
			HttpSession session, Model modelReg) {
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
				 model.put("repList",
							userActivityService.getReport("CGTMSECHECKER", "User_Report"));
					model.put("GMaintainlist", userActivityService.getActivity(
							"CGTMSECHECKER", "Guarantee_Maintenance"));
					model.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
					model.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
		model.put("homePage","cgtmseCheckerHome");
		//modelReg.addAttribute("message", "MLI has been Successfully Approved.");
		return new ModelAndView("approveRejectNewMLIDetails", model);
	}
	
	@RequestMapping(value = "/successFullyReject", method = RequestMethod.GET)
	public ModelAndView viewSuccessRejectDetails(
			@ModelAttribute("command") MLICheckerApproveRejectDetailsBean mliRegistrationBean,
			HttpSession session, Model modelReg) {
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
				 model.put("repList",
							userActivityService.getReport("CGTMSEMAKER", "User_Report"));
					model.put("GMaintainlist", userActivityService.getActivity(
							"CGTMSECHECKER", "Guarantee_Maintenance"));
					model.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
					model.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
		model.put("homePage","cgtmseCheckerHome");
		modelReg.addAttribute("message", "MLI Registration Returned.");
		return new ModelAndView("mliRegApprovalRegectionSuccess", model);
	}
	
	@RequestMapping(value = "/approveRejectNewMLIDetailsByChecker", method = RequestMethod.GET)
	public ModelAndView viewAudAndUpdatedDetails(
			@ModelAttribute("command") MLICheckerApproveRejectDetailsBean mliRegistrationBean,
			HttpSession session, Model modelReg) {
		Map<String, Object> model = new HashMap<String, Object>();
		if(mliRegistrationBean.getMliLongName()==null){
			
			throw new NBFCException("Exception : MLI Long name not found");
		}
		model.put("updatedMLIDetails",
				mliRegService.getMLIDtl(mliRegistrationBean.getMliLongName()));
		model.put("audMLIDetails", mliRegService
				.getMLIAudDetails(mliRegistrationBean.getMliLongName()));
		model.put("mliNameList",
				mliRegService.getMLIRegList("State Bank of India", "CME"));
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
		model.put("homePage","cgtmseCheckerHome");
		model.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
		model.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSECHECKER", "Guarantee_Maintenance"));
		return new ModelAndView("approveRejectNewMLIDetailsByChecker", model);
	}

	@RequestMapping(value = "/getmliDetailsForApprovalByChecker", method = RequestMethod.GET)
	public ModelAndView getPortfolioDetails(
			@ModelAttribute(value = "command") MLICheckerApproveRejectDetailsBean mliRegistrationBean,
			BindingResult result, String mliLongName)
			throws NullPointerException, Exception {
		System.out.println("portfolioNum " + mliLongName);
		Map<String, Object> model = new HashMap<String, Object>();
		JsonResponse2 res = new JsonResponse2();
		if(mliLongName==null){
			
			throw new NBFCException("Exception : MLI Long name not found");
		}
		model.put(
				"mliDetails",
				prepareMLIRegistrationModel(mliDetailsService.getMLIEditDetails(mliLongName, "CME"),mliDetailsService.getAUDMLIEditDetails(mliLongName,	"CME"),stateService));
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
		model.put("homePage","cgtmseCheckerHome");
		model.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
		model.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSECHECKER", "Guarantee_Maintenance"));
		return new ModelAndView("getmliDetailsForApprovalRejectionByChecker",
				model);
	}
	//changes 22 nov 2018----------------------------------------------
		@ExceptionHandler(NBFCException.class)
		public ModelAndView handleStudentNotFoundException(
				NBFCException ex) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("exception", ex);
			//Added by say 6 FEb-------------------------------------------------------------
			model.put("adminlist",
					userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
			model.put("guaranteelist",
					userActivityService.getActivity("CGTMSECHECKER", "Registration"));
			model.put("applicationList",
					userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
			model.put("RPaymentList",
					userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
			 model.put("repList",
						userActivityService.getReport("CGTMSECHECKER", "User_Report"));
				model.put("GMaintainlist", userActivityService.getActivity(
						"CGTMSECHECKER", "Guarantee_Maintenance"));
			model.put("homePage","cgtmseCheckerHome");
			model.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
			model.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
			return new ModelAndView("exceptionPage", model);
		}
	//end---------------------------------------------------------------

	@RequestMapping(value = "/rejectMLIDetailsByChecker", method = RequestMethod.GET)
	public ModelAndView rejectMLIDetails(
			@ModelAttribute("command") MLICheckerApproveRejectDetailsBean mliRegistrationBean,
			HttpSession session, Model modelReg) throws ParseException {
		Map<String, Object> model = new HashMap<String, Object>();
		String userID = (String) session.getAttribute("userId");
		// mliRegService.getMLIDtl(mliRegistrationBean.getMliLongName());
		// prepareModelForMLIDerailsReject(mliRegService.getMLIDtl(mliRegistrationBean.getMliLongName()),userID);
		mliRegService
				.updateMLIApproveRejectStatus(prepareModelForMLIDerailsReject(
						mliDetailsService.getMLIEditDetails(
								mliRegistrationBean.getLongName(), "CME"),
						userID, mliRegistrationBean.getRemarks()));
		model.put("mliNameList",
				mliRegService.getMLIRegList("State Bank of India", "CME"));
		modelReg.addAttribute("message", "MLI has been Successfully Rejected.");
		return new ModelAndView(
				"redirect:/successFullyReject.html");
	}

	@RequestMapping(value = "/approveMLIDetailsByChecker", method = RequestMethod.GET)
	public ModelAndView approveMLIDetails(
			@ModelAttribute("command") MLICheckerApproveRejectDetailsBean mliRegistrationBean,
			HttpSession session, Model modelReg) throws ParseException {
		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println("MLi Long Name" + mliRegistrationBean.getLongName());
		String userID = (String) session.getAttribute("userId");
		// mliRegService.getMLIDtl(mliRegistrationBean.getMliLongName());
		// prepareModelForMLIDerailsApprove(mliRegService.getMLIDtl(mliRegistrationBean.getMliLongName()),userID);
		mliRegService
				.updateMLIApproveRejectStatus(prepareModelForMLIDerailsApprove(
						mliDetailsService.getMLIEditDetails(
								mliRegistrationBean.getLongName(), "CME"),
						userID));
		 mliDetailsService.updateAUDMLIApproveRejectStatus(prepareModelForAUDMLIDerailsApprove(mliDetailsService.getAUDMLIEditDetails(mliRegistrationBean.getLongName(),"CME"),userID));

		// model.put("mliNameList",
		// mliRegService.getMLIRegList("State Bank of India","CME"));
		modelReg.addAttribute("message", "MLI has been Successfully Approved.");
		return new ModelAndView(
				"redirect:/successFullyApproved.html");
	}

	@RequestMapping(value = "/successfullyRejectMLIDetailsByChecker", method = RequestMethod.GET)
	public ModelAndView successfullyRejected(
			@ModelAttribute("command") MLICheckerApproveRejectDetailsBean mliRegistrationBean,
			HttpSession session, Model modelReg) throws ParseException {
		Map<String, Object> model = new HashMap<String, Object>();

		modelReg.addAttribute("message", "MLI has been Succcessfully Rejected.");
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
		model.put("homePage","cgtmseCheckerHome");
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSECHECKER", "Guarantee_Maintenance"));
		model.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
		model.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
		 model.put("repList",
					userActivityService.getReport("CGTMSECHECKER", "User_Report"));
		return new ModelAndView("getmliDetailsForApprovalRejectionByChecker",
				model);
	}

	@RequestMapping(value = "/successfullyApproveMLIDetailsByChecker", method = RequestMethod.GET)
	public ModelAndView successfullyApproved(
			@ModelAttribute("command") MLICheckerApproveRejectDetailsBean mliRegistrationBean,
			HttpSession session, Model modelReg) throws ParseException {
		Map<String, Object> model = new HashMap<String, Object>();
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
		model.put("actName", userActivityService.getActivityName("CGTMSECHECKER",
				"approveRejectNewMLIDetails"));// Added by Say 31 Jan19
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSECHECKER", "Guarantee_Maintenance"));
		model.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
		model.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
		model.put("homePage","cgtmseCheckerHome");
		 model.put("repList",
					userActivityService.getReport("CGTMSECHECKER", "User_Report"));
		return new ModelAndView("getmliDetailsForApprovalRejectionByChecker",
				model);
	}

	private MLIEditApproveRejectUpdate prepareModelForMLIDerailsApprove(
			MLIMainEditDetails mliRegNean, String userId) throws ParseException {
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

		// Date date1=new
		// SimpleDateFormat("dd-MM-yyyy").parse(mliRegNean.getEditRatingDate());

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

	private AudMLiDetails prepareModelForAUDMLIDerailsApprove(
			AudMLiDetails mliRegNean, String userId) throws ParseException {
		AudMLiDetails mliReg = new AudMLiDetails();

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

		// Date date1=new
		// SimpleDateFormat("dd-MM-yyyy").parse(mliRegNean.getEditRatingDate());

		mliReg.setRatingDate(mliRegNean.getRatingDate());
		mliReg.setRbiReggistrationNumber(mliRegNean.getRbiReggistrationNumber());
		mliReg.setUserID(mliRegNean.getUserID());
		mliReg.setStatus("CCA");
		mliReg.setMem_bnk_name(mliRegNean.getMem_bnk_name());
		mliReg.setShortName(mliRegNean.getShortName());
		mliReg.setState(mliRegNean.getState());
		mliReg.setFaxCodeE(mliRegNean.getFaxCodeE());
		mliReg.setPhone_code(mliRegNean.getPhone_code());
		mliReg.setMem_status(mliRegNean.getMem_status());
		mliReg.setMem_mcgf(mliRegNean.getMem_mcgf());
		mliReg.setCgtmse_maker_id(mliRegNean.getCgtmse_maker_id());
		mliReg.setCgtmse_maker_date(mliRegNean.getCgtmse_maker_date());
		System.out.println(mliRegNean.getCgtmse_maker_date());
		mliReg.setCgtmse_checker_id(userId);

		return mliReg;
	}

	private MLIEditApproveRejectUpdate prepareModelForMLIDerailsReject(
			MLIMainEditDetails mliRegNean, String userId, String remarks)
			throws ParseException {
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

		// Date date1=new
		// SimpleDateFormat("dd-MM-yyyy").parse(mliRegNean.getEditRatingDate());

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

	private MLICheckerApproveRejectDetailsBean prepareMLIRegistrationModel(
			MLIMainEditDetails mliRegistration, AudMLiDetails audMLiDetails,StateService stateService)
			throws Exception {
		MLICheckerApproveRejectDetailsBean mliRegBean = new MLICheckerApproveRejectDetailsBean();
		if (audMLiDetails == null) {
			
			throw new NBFCException("Record Not Found in AUD Database");
			
		}else if(mliRegistration==null){
			
			throw new NBFCException("Record Not Found in main Database");
		}
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
		mliRegBean.setRbiReggistrationNumber(mliRegistration
				.getRbiReggistrationNumber());
		System.out.println(mliRegistration.getRbiReggistrationNumber());

		/*
		 * System.out.println("employeeBean.getPortfolio_start_date()  :" +
		 * mliRegistration.getRatingDate()); String date =
		 * mliRegistration.getRatingDate().substring(0, 10); String dateSplit[]
		 * = date.split("-"); String formatedSanctionDate = dateSplit[2] + "-" +
		 * dateSplit[1] + "-" + dateSplit[0];
		 */

/*		SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy");
		NBFCCommonUtility tility =new NBFCCommonUtility();*//*
		tility.getDateStringFormat(mliRegistration.getRatingDate());*/
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("=====Call");
		String cDate = dateFormat.format(mliRegistration.getRatingDate());
		/*System.out.println("cDate"+cDate);
		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(cDate);  
		System.out.println("Date Formate"+date1);*/
		
		mliRegBean.setRatingDateOne(cDate);
		mliRegBean.setShortName(mliRegistration.getShortName());
		//mliRegBean.setState((mliRegistration.getState()));
		mliRegBean.setState(stateService.stateName(mliRegistration.getState()).getSte_name());
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
		DateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("=====Call");
		String cDate1 = dateFormat1.format(audMLiDetails.getRatingDate());
		
		mliRegBean.setRatingDateTwo(cDate1);
		mliRegBean.setEditShortName(audMLiDetails.getShortName());
	

		mliRegBean.setEditState(stateService.stateName(audMLiDetails.getState()).getSte_name());
		//mliRegBean.setEditState(audMLiDetails.getState());
		
		mliRegBean.setEditRBIReggistrationNumber(audMLiDetails.getRbiReggistrationNumber());
		System.out.println(audMLiDetails.getRbiReggistrationNumber());
		return mliRegBean;
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
				
				bean.setContactPerson(employee.getContactPerson());
				bean.setMobileNUmber(employee.getMobileNUmber());
				bean.setEmailId(employee.getEmailId());
				if (employee.getStatus().equals("CCA")) {
					bean.setStatus("Approval Done");
				} else if (employee.getStatus().equals("CME")) {
					bean.setStatus("Approval Case");
				} else if (employee.getStatus().equals("CMR")) {
					bean.setStatus("Approval Act");
				} else if (employee.getStatus().equals("CEMMA")) {
					bean.setStatus("Approval Act");
				}
				// bean.setStatus(employee.getStatus());
				beans.add(bean);
			}
			Collections.sort(beans,MLIDEtailsBean.StatusComparator);
		}
		return beans;
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
