package com.nbfc.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.text.SimpleDateFormat;
import com.nbfc.bean.MLIDEtailsBean;
import com.nbfc.bean.MLIDetailEditBean;
import com.nbfc.bean.MLIRegistrationApprovalBean;
import com.nbfc.bean.StateBean;
import com.nbfc.model.AudMLiDetails;
import com.nbfc.model.BankDetails;
import com.nbfc.model.District;
import com.nbfc.model.MLIEditDetails;
import com.nbfc.model.MLIRegistration;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.StateMaster;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.DistrictService;
import com.nbfc.service.LoginService;
import com.nbfc.service.MLIRegService;
import com.nbfc.service.StateService;
import com.nbfc.service.UserActivityService;
import com.nbfc.validation.EmployeeValidator;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class MLIRegistrationSaveUpdateController {

	@Autowired
	LoginService lofinService;
	@Autowired
	StateService stateService;
	@Autowired
	MLIRegService mliRegService;
	@Autowired
	DistrictService districtService;
	@Autowired
	EmployeeValidator validator;
	@Autowired
	UserActivityService userActivityService;
	
	UserPerivilegeDetails userPri;
	NBFCPrivilegeMaster userPrvMst;
	
	static Logger log = Logger.getLogger(NBFCController.class.getName());
	List<BankDetails> bankDetails = new ArrayList<BankDetails>();
	List<Integer> bankId = new ArrayList<Integer>();
	List<Integer> bankBranchId = new ArrayList<Integer>();
	List<Integer> bankZonId = new ArrayList<Integer>();
	private List<District> userList = new ArrayList<District>();
	String rating= null;
	String ratingAgency=null;
	String state=null;
	String district=null;
	String mliType=null;
	
	@RequestMapping(value = "/saveNewMLIDetails", method = RequestMethod.GET)
	public ModelAndView saveMLIDetails(
			@ModelAttribute("command") MLIDEtailsBean mliRegistrationBean,
			BindingResult result, Model modelMsg, HttpSession session) {
		
		Map<String, Object> model = new HashMap<String, Object>();
		bankDetails.clear();
		bankId.clear();
		bankBranchId.clear();
		bankZonId.clear();
		// System.out.println(mliRegistrationBean.getDistrict()+""+mliRegistrationBean.getMobileNUmber());
		String userId = (String) session.getAttribute("userId");
		if (userId.equals("CGTMSE ADMIN")) {

		} else {
			if (userId != null) {
				userPri = lofinService.getUserPrivlageDtl(userId, "A");
				userPrvMst = lofinService
						.getPrivlageMstDtl(userPri.getPrv_id());
				if (userPrvMst.getPrvCreatedModifiedBy().equals("CMAKER")) {

				} else {
					return new ModelAndView("redirect:/SVLogin.html");
				}
			}
		}
		validator.mliRegistrationValidator(mliRegistrationBean, result);
		if (result.hasErrors()) {
			log.info("Error in field*******************************************************************************************************************");
			model.put("ratingAgencyList", stateService.mliRatingList());
			model.put("stateList",
					prepareStateListofBean(stateService.listStateMaster()));
			model.put("actName",
					userActivityService.getActivityName("CGTMSEMAKER", "mliRegistrationPage"));// Added by Say 31 Jan19
			return new ModelAndView("mliRegistration", model);

		}

		bankDetails = mliRegService.getBankDetails();
		System.out.println("" + bankDetails);

		if (bankDetails.size() >= 0) {
			for (BankDetails bankList : bankDetails) {
				bankId.add(Integer.parseInt(bankList.getMEM_BNK_ID()));
				bankBranchId.add(Integer.parseInt(bankList.getMEM_BRN_ID()));
				bankZonId.add(Integer.parseInt(bankList.getMEM_ZNE_ID()));

			}
			for (BankDetails list : bankDetails) {
				System.out.println(list.getLongName());
				System.out.println(mliRegistrationBean.getLongName());
				if (list.getLongName().equalsIgnoreCase(mliRegistrationBean.getLongName())) {
                    modelMsg.addAttribute("error", ""+list.getLongName()+" MLI already Registred.");
					model.put("ratingAgencyList", stateService.mliRatingList());
					model.put("stateList", prepareStateListofBean(stateService.listStateMaster()));
					model.put("actName",
				   userActivityService.getActivityName("CGTMSEMAKER", "mliRegistrationPage"));// Added by Say 31 Jan19
					return new ModelAndView("mliRegistration", model);
                 }else if (list.getShortName().equalsIgnoreCase(mliRegistrationBean.getShortName())) {
                    modelMsg.addAttribute("error", "MLI Short name "+list.getShortName()+" already Registred.");
					model.put("ratingAgencyList", stateService.mliRatingList());
					model.put("stateList", prepareStateListofBean(stateService.listStateMaster()));
					
					model.put("actName",
							userActivityService.getActivityName("CGTMSEMAKER", "mliRegistrationPage"));// Added by Say 31 Jan19
					return new ModelAndView("mliRegistration", model);
                 } else if (list.getRBI_REGISTRATION_NO().equalsIgnoreCase(mliRegistrationBean.getRbiReggistrationNumber())) {
                    modelMsg.addAttribute("error", "RBI REGISTRATION NO "+list.getRBI_REGISTRATION_NO()+" already Registred.");
					model.put("ratingAgencyList", stateService.mliRatingList());
					model.put("stateList", prepareStateListofBean(stateService.listStateMaster()));
					model.put("actName",
							userActivityService.getActivityName("CGTMSEMAKER", "mliRegistrationPage"));// Added by Say 31 Jan19
					return new ModelAndView("mliRegistration", model);
                 }else if (list.getCOMPANY_CIN().equalsIgnoreCase(mliRegistrationBean.getCompanyCIN())) {
                    modelMsg.addAttribute("error", "Company CIN NO "+list.getCOMPANY_CIN()+" already Registred.");
					model.put("ratingAgencyList", stateService.mliRatingList());
					model.put("stateList", prepareStateListofBean(stateService.listStateMaster()));
					model.put("actName",
							userActivityService.getActivityName("CGTMSEMAKER", "mliRegistrationPage"));// Added by Say 31 Jan19
					return new ModelAndView("mliRegistration", model);
                 }else if (list.getCOMPANY_PAN().equalsIgnoreCase(mliRegistrationBean.getCompanyPAN())) {
                    modelMsg.addAttribute("error", "COMPANY PAN "+list.getCOMPANY_PAN()+" already Registred.");
					model.put("ratingAgencyList", stateService.mliRatingList());
					model.put("stateList", prepareStateListofBean(stateService.listStateMaster()));
					model.put("actName",
							userActivityService.getActivityName("CGTMSEMAKER", "mliRegistrationPage"));// Added by Say 31 Jan19
					return new ModelAndView("mliRegistration", model);
                 } else if (list.getGSTIN_NO().equalsIgnoreCase(mliRegistrationBean.getGstinNumber())) {
                    modelMsg.addAttribute("error", "GSTIN NO "+list.getGSTIN_NO()+" already Registred.");
					model.put("ratingAgencyList", stateService.mliRatingList());
					model.put("stateList", prepareStateListofBean(stateService.listStateMaster()));
					model.put("actName",
							userActivityService.getActivityName("CGTMSEMAKER", "mliRegistrationPage"));// Added by Say 31 Jan19
					return new ModelAndView("mliRegistration", model);
}
			}
			Integer i = Collections.max(bankId);
			Integer b = Collections.max(bankBranchId);
			Integer c = Collections.max(bankZonId);
			i = i + 1;
			b = b + 1;
			c = c + 1;
			System.out.println("Hello");
			System.out.println(i + " " + b + " " + c);
			MLIRegistration mliRegistration = prepareModelForMLIReg(
					mliRegistrationBean, Integer.toString(i),
					Integer.toString(b), Integer.toString(c), userId);
			mliRegService.addMLIDetails(mliRegistration);
			System.out.println("insert....");
		} else {

			MLIRegistration mliRegistration = prepareModelForMLIReg(
					mliRegistrationBean, "1000", "2000", "3000", userId);
			mliRegService.addMLIDetails(mliRegistration);

		}
		model.put("ratingAgencyList", stateService.mliRatingList());
		model.put("stateList",
				prepareStateListofBean(stateService.listStateMaster()));
		model.put("actName",
				userActivityService.getActivityName("CGTMSEMAKER", "mliRegistrationPage"));// Added by Say 31 Jan19
		return new ModelAndView("redirect:/addMLIRegistration.html");
	}

	@RequestMapping(value = "/updatetMLIDetails", method = RequestMethod.GET)
	public ModelAndView editSaveMLIDetailsGet()
	{
		return new ModelAndView("redirect:/SVLogin.html");
	}
	
	@RequestMapping(value = "/updatetMLIDetails", method = RequestMethod.POST)
	public ModelAndView editSaveMLIDetails(
			@ModelAttribute("command") MLIDEtailsBean mliRegistrationBean,
			BindingResult result,HttpSession session, Model modelReg) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println("MLI Long Name "+mliRegistrationBean.getMliLongName());
		System.out.println("Long Name "+mliRegistrationBean.getMliLongName());
		if(mliRegistrationBean.getLongName()==null)
		{	
			mliRegistrationBean.setLongName(mliRegistrationBean.getMliLongName());
		}
		if(mliRegistrationBean.getMliLongName()==null)
		{	
			mliRegistrationBean.setMliLongName(mliRegistrationBean.getLongName());
		}
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
		MLIRegistration mliRegistrationData=mliRegService.getMLIDetails(mliRegistrationBean.getLongName());
		//mliRegService.getMLIDtl(mliRegistrationBean.getMliLongName());
		//prepareModelForMLIAUD(mliRegService.getMLIDtl(mliRegistrationBean.getMliLongName()),userId)
		MLIEditDetails mliedit=prepareModelForMLIEdit(mliRegistrationBean,mliRegistrationData,userId);
		rating= mliRegistrationBean.getRating();
		ratingAgency=mliRegistrationBean.getRatingAgency();
		state=mliRegistrationBean.getState();
		district=mliRegistrationBean.getState();
		mliType=mliRegistrationBean.getMliType();
		System.out.println("updateMliDetails controller mliType "+mliType);
		Map<String, String> mapLongNameObj1 = districtService.listDistricts(mliRegistrationBean.getState());
		userList.clear();
		for (Map.Entry<String, String> entry : mapLongNameObj1.entrySet()) {
			District s1 = new District();
			String value = entry.getValue();
			s1.setDst_name(value);
			userList.add(s1);
		}
		mapLongNameObj1.clear();
		validator.mliRegistrationEditValidator(mliRegistrationBean, result);
		if (result.hasErrors()) {
			
			model.put("stateList", prepareStateListofBean(stateService.listStateMaster()));
			model.put("ratingAgencyList", stateService.mliRatingList());
			model.put("districtList",  userList);
			model.put("rating",  rating);
			model.put("ratingAgency",  ratingAgency);
			model.put("state",  state);
			model.put("district",  district);
			model.put("mliDetail", mliedit);
			model.put("mliType", mliType);
			model.put("adminlist",
					userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
			model.put("guaranteelist",
					userActivityService.getActivity("CGTMSEMAKER", "Registration"));
			model.put("applicationList",
					userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
			model.put("RPaymentList",
					userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
			model.put("GMaintainlist", userActivityService.getActivity(
					"CGTMSEMAKER", "Guarantee_Maintenance"));
		
//			model.put("actList",
//					userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
			
			model.put("homePage","cgtmseMakerHome");
			model.put("actName",
					userActivityService.getActivityName("CGTMSEMAKER", "mliRegistrationPage"));// Added by Say 31 Jan19
			return new ModelAndView("mliEditPage", model);

		}
		MLIRegistration mliRegistration=mliRegService.getMLIDetails(mliRegistrationBean.getLongName());
		//mliRegService.getMLIDtl(mliRegistrationBean.getMliLongName());
		//prepareModelForMLIAUD(mliRegService.getMLIDtl(mliRegistrationBean.getMliLongName()),userId);
		mliRegService.audAddMLIDetails(prepareModelForMLIAUD(mliRegService.getMLIDtl(mliRegistrationBean.getLongName()),userId));
		
		MLIEditDetails aaa=prepareModelForMLIEdit(mliRegistrationBean,mliRegistration,userId);
		mliRegService.editMLIDetails(aaa);
		System.out.println("Successfully update..........................................................");
		model.put("mliNameList",
				mliRegService.getMLIRegList("State Bank of India","CCA"));
		model.put("adminlist",
				userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		model.put("guaranteelist",
				userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model.put("applicationList",
				userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		model.put("RPaymentList",
				userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
//		model.put("actList",
//				userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
		model.put("actName",
				userActivityService.getActivityName("CGTMSEMAKER", "mliRegistrationPage"));// Added by Say 31 Jan19
		model.put("homePage","cgtmseMakerHome");
		return new ModelAndView("redirect:/successEditDetails.html");

	}
	
	@RequestMapping(value = "/successEditDetails", method = RequestMethod.GET)
	public ModelAndView successPage(
			@ModelAttribute("command") MLIRegistrationApprovalBean mliRegistrationBean,
			BindingResult result,HttpSession session, Model modelReg) throws ParseException {
			String userId = (String) session.getAttribute("userId");
		
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
		Map<String, Object> model = new HashMap<String, Object>();
		//modelReg.addAttribute("message", "MLI details successfully updated and pending for Checker Approval");
		modelReg.addAttribute("message", "MLI details successfully updated ");
		model.put("stateList", prepareStateListofBean(stateService.listStateMaster()));
		//Added by say 6 FEb
		model.put("adminlist",
				userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		model.put("guaranteelist",
				userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model.put("applicationList",
				userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		model.put("RPaymentList",
				userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
//		model.put("actList",
//				userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
		model.put("actName",
				userActivityService.getActivityName("CGTMSEMAKER", "mliRegistrationPage"));// Added by Say 31 Jan19
		model.put("homePage","cgtmseMakerHome");
		return new ModelAndView("mliEditPage",model);

	}
	private List<StateBean> prepareStateListofBean(List<StateMaster> employees) {
		List<StateBean> beans = null;
		if (employees != null && !employees.isEmpty()) {
			beans = new ArrayList<StateBean>();
			StateBean bean = null;
			for (StateMaster employee : employees) {
				bean = new StateBean();
				bean.setSte_code(employee.getSte_code());
				bean.setSte_name(employee.getSte_name());
				beans.add(bean);
			}
		}
		return beans;
	}
	
	private MLIRegistration prepareModelForMLIReg(MLIDEtailsBean mliRegNean,
			String mem_bnk_id, String mem_brn_id, String mem_zne_id,
			String userId) {
		MLIRegistration mliReg = new MLIRegistration();
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
		mliReg.setMem_bnk_id(mem_bnk_id);
		mliReg.setMem_brn_id(mem_brn_id);
		mliReg.setMem_zne_id(mem_zne_id);
		mliReg.setMobileNUmber(mliRegNean.getMobileNUmber());
		mliReg.setPincode(mliRegNean.getPincode());
		mliReg.setRating(mliRegNean.getRating());
		mliReg.setRatingAgency(mliRegNean.getRatingAgency());
		mliReg.setRatingDate(mliRegNean.getRatingDate());
		mliReg.setRbiReggistrationNumber(mliRegNean.getRbiReggistrationNumber());
		mliReg.setUserID(userId);
		mliReg.setStatus("CMR");
		mliReg.setMem_bnk_name(mliRegNean.getLongName());
		mliReg.setShortName(mliRegNean.getShortName());
		mliReg.setState(mliRegNean.getState());
		mliReg.setPhone_code(mliRegNean.getPhoneCode());
		mliReg.setFaxCode(mliRegNean.getFaxCode());
		mliReg.setMem_status("A");
		mliReg.setMem_mcgf("B");

		return mliReg;
	}
	
	private AudMLiDetails prepareModelForMLIAUD(MLIEditDetails mliRegNean,String userId) throws ParseException {
		AudMLiDetails mliReg = new AudMLiDetails();
		mliReg.setMliType(mliRegNean.getMliType());
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

	//	System.out.println("----------datesssssssssssss"  +date1);
		
		System.out.println("----------datesssssssssssss"  +mliReg.getRatingDate());
		//mliReg.setRatingDate(date1);
		mliReg.setRbiReggistrationNumber(mliRegNean.getRbiReggistrationNumber());
		mliReg.setUserID(mliRegNean.getUserID());
		//mliReg.setStatus("CME");
		mliReg.setStatus("CCA");
		mliReg.setMem_bnk_name(mliRegNean.getMem_bnk_name());
		mliReg.setShortName(mliRegNean.getShortName());
		mliReg.setState(mliRegNean.getState());
		mliReg.setPhone_code(mliRegNean.getPhone_code());
		mliReg.setFaxCodeE(mliRegNean.getFaxCode());
		mliReg.setMem_status(mliRegNean.getMem_status());
		mliReg.setMem_mcgf(mliRegNean.getMem_mcgf());
		mliReg.setCgtmse_maker_id(userId);
		mliReg.setAud_by(userId);
		
		return mliReg;
	}
	
	private MLIEditDetails prepareModelForMLIEdit(MLIDEtailsBean mliRegNean, MLIRegistration mliRegistration,String userId) throws ParseException {
		MLIEditDetails mliReg = new MLIEditDetails();
		mliReg.setMliType(mliRegNean.getMliType());
		mliReg.setCity(mliRegNean.getCity());
		mliReg.setCompanyAddress(mliRegNean.getCompanyAddress());
		mliReg.setCompanyCIN(mliRegistration.getCompanyCIN());
		mliReg.setCompanyPAN(mliRegistration.getCompanyPAN());
		mliReg.setContactPerson(mliRegNean.getContactPerson());
		mliReg.setDistrict(mliRegNean.getDistrict());
		mliReg.setEmailId(mliRegNean.getEmailId());
		mliReg.setFaxNumber(mliRegNean.getFaxNumber());
		mliReg.setGstinNumber(mliRegistration.getGstinNumber());
		mliReg.setLandlineNumber(mliRegNean.getLandlineNumber());
		mliReg.setLongName(mliRegistration.getLongName());
		mliReg.setMem_bnk_id(mliRegistration.getMem_bnk_id());
		mliReg.setMem_brn_id(mliRegistration.getMem_brn_id());
		mliReg.setMem_zne_id(mliRegistration.getMem_zne_id());
		mliReg.setMobileNUmber(mliRegNean.getMobileNUmber());
		mliReg.setPincode(mliRegNean.getPincode());
		mliReg.setRating(mliRegNean.getRating());
		mliReg.setRatingAgency(mliRegNean.getRatingAgency());
		mliReg.setPhone_code(mliRegNean.getPhoneCode());
		mliReg.setState(mliRegNean.getState());
		if(mliRegNean.getRatingDate()!=null && !"".equals(mliRegNean.getRatingDate()))
		{
		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(mliRegNean.getRatingDate());  
		
		mliReg.setRatingDate(date1);
		}
		mliReg.setRbiReggistrationNumber(mliRegistration.getRbiReggistrationNumber());
		mliReg.setUserID(mliRegistration.getUserID());
		//mliReg.setStatus("CME");
		mliReg.setStatus("CCA");
		mliReg.setMem_bnk_name(mliRegistration.getMem_bnk_name());
		mliReg.setShortName(mliRegistration.getShortName());
		mliReg.setPhone_code(mliRegNean.getPhoneCode());
		mliReg.setMem_status(mliRegistration.getMem_status());
		mliReg.setMem_mcgf(mliRegistration.getMem_mcgf());
		mliReg.setCgtmse_maker_id(userId);
		
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
