package com.nbfc.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import com.nbfc.bean.MLIDetailEditBean;
import com.nbfc.bean.MLIRegistrationBean;
import com.nbfc.model.AudMLiDetails;
import com.nbfc.model.MLIEditDetails;
import com.nbfc.model.MLIRegistration;
import com.nbfc.service.MLIRegService;
import com.nbfc.validation.EmployeeValidator;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class MLIViewEditController {

	@Autowired
	MLIRegService mliRegService;
	@Autowired
	EmployeeValidator validator;
	
	@RequestMapping(value = "/viewEditMLIDetails", method = RequestMethod.GET)
	public ModelAndView viewEditMLIDetails(
			@ModelAttribute("command") MLIRegistrationBean mliRegistrationBean,
			HttpSession session, Model modelReg) {
		String userId = (String) session.getAttribute("userId");
		
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
		Map<String, Object> model = new HashMap<String, Object>();
		//System.out.println(mliRegistrationBean.getMliLongName());
		model.put("mliNameList",
				mliRegService.getMLIRegList("State Bank of India","CCA"));
		return new ModelAndView("mliViewEditDetails", model);

	}
	
	@RequestMapping(value = "/viewEditMLIDetails", params = "close", method = RequestMethod.POST)
	public ModelAndView closeMLIEditDetails(HttpSession session, Model modelReg) {
		Map<String, Object> model = new HashMap<String, Object>();
		//System.out.println("Close************************************************************");
		String userId = (String) session.getAttribute("userId");
		
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
		return new ModelAndView("successCGTMSEMAKERPage", model);
	}
	
	@RequestMapping(value = "/viewEditMLIDetails", params="editSave", method = RequestMethod.POST)
	public ModelAndView editSaveMLIDetails(
			@ModelAttribute("command") MLIDetailEditBean mliRegistrationBean,
			BindingResult result,HttpSession session, Model modelReg) throws ParseException {
		Map<String, Object> model = new HashMap<String, Object>();
		//System.out.println(mliRegistrationBean.getMliLongName());
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
//		validator.mliRegistrationEditValidator(mliRegistrationBean, result);
//		if (result.hasErrors()) {
//			
//			model.put("mliNameList",
//					mliRegService.getMLIRegList("State Bank of India","CCA"));
//			return new ModelAndView("mliViewEditDetails", model);
//
//		}
		MLIRegistration mliRegistration=mliRegService.getMLIDetails(mliRegistrationBean.getMliLongName());
		//mliRegService.getMLIDtl(mliRegistrationBean.getMliLongName());
		//prepareModelForMLIAUD(mliRegService.getMLIDtl(mliRegistrationBean.getMliLongName()),userId);
		mliRegService.audAddMLIDetails(prepareModelForMLIAUD(mliRegService.getMLIDtl(mliRegistrationBean.getMliLongName()),userId));
		
		MLIEditDetails aaa=prepareModelForMLIEdit(mliRegistrationBean,mliRegistration,userId);
		mliRegService.editMLIDetails(aaa);
		//System.out.println("Successfully update..........................................................");
		model.put("mliNameList",
				mliRegService.getMLIRegList("State Bank of India","CCA"));
		return new ModelAndView("redirect:/viewEditSuccessMLIDetails.html");

	}
	
	@RequestMapping(value = "/viewEditSuccessMLIDetails", method = RequestMethod.GET)
	public ModelAndView editSaveSuccessMLIDetails(
			@ModelAttribute("command") MLIRegistrationBean mliRegistrationBean,
			HttpSession session, Model modelReg) throws ParseException {
		String userId = (String) session.getAttribute("userId");
		
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
		Map<String, Object> model = new HashMap<String, Object>();
		//System.out.println(mliRegistrationBean.getMliLongName());
		modelReg.addAttribute("message", "MLI Successfully Edit");
		model.put("mliNameList",
				mliRegService.getMLIRegList("State Bank of India","CCA"));
		return new ModelAndView("mliViewEditDetails", model);

	}
	
	private MLIEditDetails prepareModelForMLIEdit(MLIDetailEditBean mliRegNean, MLIRegistration mliRegistration,String userId) throws ParseException {
		MLIEditDetails mliReg = new MLIEditDetails();

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
		
		Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(mliRegNean.getRatingDate());  
		
		mliReg.setRatingDate(date1);
		mliReg.setRbiReggistrationNumber(mliRegistration.getRbiReggistrationNumber());
		mliReg.setUserID(mliRegistration.getUserID());
		mliReg.setStatus("CME");
		mliReg.setMem_bnk_name(mliRegistration.getMem_bnk_name());
		mliReg.setShortName(mliRegistration.getShortName());
		mliReg.setState(mliRegNean.getState());
		mliReg.setPhone_code(mliRegistration.getPhone_code());
		mliReg.setMem_status(mliRegistration.getMem_status());
		mliReg.setMem_mcgf(mliRegistration.getMem_mcgf());
		mliReg.setCgtmse_maker_id(userId);
		
		return mliReg;
	}
	
	private AudMLiDetails prepareModelForMLIAUD(MLIEditDetails mliRegNean,String userId) throws ParseException {
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
		
		//Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(mliRegNean.getRatingDate());  
		
		mliReg.setRatingDate(mliRegNean.getRatingDate());
		mliReg.setRbiReggistrationNumber(mliRegNean.getRbiReggistrationNumber());
		mliReg.setUserID(mliRegNean.getUserID());
		mliReg.setStatus(mliRegNean.getStatus());
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


