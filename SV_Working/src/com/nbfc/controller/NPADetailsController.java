package com.nbfc.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nbfc.bean.MLIMakerInterfaceUploadedBean;
import com.nbfc.bean.NPADetailsBean;
import com.nbfc.bean.NPAMarkBean;
import com.nbfc.bean.NPATCDetailsBean;
import com.nbfc.bean.NPAWCDetailsBean;
import com.nbfc.helper.FileExportHelper;
import com.nbfc.helper.NPABulkUpload;
import com.nbfc.helper.TableDetailBean;
import com.nbfc.model.UserInfo;
import com.nbfc.service.NPAService;
import com.nbfc.service.UserActivityService;
import com.nbfc.validation.EmployeeValidator;
import com.nbfc.validator.DataValidation;

@Controller
public class NPADetailsController {

	@Autowired
	UserActivityService userActivityService;
	@Autowired
	NPAService npaService;
	@Autowired
	EmployeeValidator validator;
	@Autowired
	SessionFactory sessionFactory;
	String memberId=null;
	@RequestMapping(value = "/NPADetails", method = RequestMethod.GET)
	public ModelAndView npaDetails(
			@ModelAttribute("command") NPADetailsBean Bean,
			BindingResult result, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		List<NPADetailsBean> npaList = null;
		if(userId==null){
			return new ModelAndView("redirect:/SVLogin.html");
		}
		memberId = npaService.getMemberId(userId);
       	System.out.println(memberId);
		npaList = npaService.getNPADetailsForApproval(userId,"NCR");
		 if (npaList != null) {
				model.put("dataListReturn", npaList);
			} else {
				//log.info("Throwing exception due to data not found in NPACheckerController class");
				model.put("dataListReturn", "");
			}
		//Added by say 6 FEb-------------------------------------------------------------
		 model.put("adminlist",
					userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		 model.put("guaranteelist",
					userActivityService.getActivity("NBFCMAKER", "Registration"));
		 model.put("applicationList",
					userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
		 model.put("RPaymentList",
					userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
		 model.put("actName", userActivityService.getActivityName("NBFCMAKER",
					"NPADetails"));// Added by Say 31 Jan19
		 model.put("repList",
					userActivityService.getReport("NBFCMAKER", "User_Report"));
		 model.put("GMaintainlist", userActivityService.getActivity(
					"NBFCMAKER", "Guarantee_Maintenance"));
		 model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
		 model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
//			modelAct.put("actList",
//					userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		 model.put("memberId", memberId);
		 model.put("homePage", "nbfcMakerHome");
		return new ModelAndView("npaDetail", model);

	}

	@RequestMapping(value = "/npaDetailsData", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView npaDetailsData(
			@ModelAttribute("command") @Valid NPADetailsBean Bean,
			BindingResult result, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		validator.npaValidation(Bean, result);
		String userId = (String) session.getAttribute("userId");
		if(userId==null){
			return new ModelAndView("redirect:/SVLogin.html");
		}
		NPADetailsBean npaDetailsBean;
		List<NPADetailsBean> npaList = null;
		List<NPADetailsBean> list= new ArrayList<NPADetailsBean>();
		DataValidation dataValidation = new DataValidation();
		
		String cgpan=Bean.getCGPAN();
		String bankId=Bean.getMLIID();
		String memberId = npaService.getMemberId(userId);
		System.out.println("bankId "+bankId+" ,cgpan  "+cgpan);
		//validator.npaValidation(Bean, result);
		if (result.hasErrors()) {
			//Added by say 6 FEb-------------------------------------------------------------
			 model.put("adminlist",
						userActivityService.getActivity("NBFCMAKER", "System_Admin"));
			 model.put("guaranteelist",
						userActivityService.getActivity("NBFCMAKER", "Registration"));
			 model.put("applicationList",
						userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
			 model.put("RPaymentList",
						userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
			 model.put("actName", userActivityService.getActivityName("NBFCMAKER",
						"NPADetails"));// Added by Say 31 Jan19
			 model.put("repList",
						userActivityService.getReport("NBFCMAKER", "User_Report"));
			 model.put("GMaintainlist", userActivityService.getActivity(
						"NBFCMAKER", "Guarantee_Maintenance"));
			 model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
			 model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
//				modelAct.put("actList",
//						userActivityService.getActivity("NBFCMAKER", "User_Activity"));
			 model.put("homePage", "nbfcMakerHome");
			model.put("memberId", memberId);
			
			return new ModelAndView("npaDetail", model);
		}
		//npaDetailsBean=npaService.getCGPANDetails(cgpan,userId);
		npaDetailsBean=npaService.getCGPANDetails(cgpan,userId);
		if(npaDetailsBean.getDayCount() ==0){
			model.put("message", " NPA date should be post completion of 90 days from Guarantee Start Date.");
			//Added by say 6 FEb-------------------------------------------------------------
			 model.put("adminlist",
						userActivityService.getActivity("NBFCMAKER", "System_Admin"));
			 model.put("guaranteelist",
						userActivityService.getActivity("NBFCMAKER", "Registration"));
			 model.put("applicationList",
						userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
			 model.put("RPaymentList",
						userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
			 model.put("actName", userActivityService.getActivityName("NBFCMAKER",
						"NPADetails"));// Added by Say 31 Jan19
			 model.put("repList",
						userActivityService.getReport("NBFCMAKER", "User_Report"));
			 model.put("GMaintainlist", userActivityService.getActivity(
						"NBFCMAKER", "Guarantee_Maintenance"));
			 model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
			 model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
//				modelAct.put("actList",
//						userActivityService.getActivity("NBFCMAKER", "User_Activity"));
			 model.put("homePage", "nbfcMakerHome");
//			model.put("actList", userActivityService.getActivity("NBFCMAKER",
//					"User_Activity"));
			model.put("memberId", memberId);
		
			return new ModelAndView("npaDetail", model);
		}else if(npaDetailsBean.getDayCount()==2){
			
			model.put("message", "CGPAN Does Not Exit !!");
			//Added by say 6 FEb-------------------------------------------------------------
			 model.put("adminlist",
						userActivityService.getActivity("NBFCMAKER", "System_Admin"));
			 model.put("guaranteelist",
						userActivityService.getActivity("NBFCMAKER", "Registration"));
			 model.put("applicationList",
						userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
			 model.put("RPaymentList",
						userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
			 model.put("actName", userActivityService.getActivityName("NBFCMAKER",
						"NPADetails"));// Added by Say 31 Jan19
			 model.put("repList",
						userActivityService.getReport("NBFCMAKER", "User_Report"));
			 model.put("GMaintainlist", userActivityService.getActivity(
						"NBFCMAKER", "Guarantee_Maintenance"));
			 model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
			 model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
			 //				modelAct.put("actList",
//						userActivityService.getActivity("NBFCMAKER", "User_Activity"));
			 model.put("homePage", "nbfcMakerHome");
			model.put("memberId", memberId);
		
			return new ModelAndView("npaDetail", model);
		}
		/*if(npaDetailsBean.getGuarStartDt1()==null && npaDetailsBean.getSanctionDt1()==null){
			model.put("message", "CGPAN Does Not Exit !!");
			model.put("actList", userActivityService.getActivity("NBFCMAKER",
					"User_Activity"));
			model.put("memberId", memberId);
			return new ModelAndView("npaDetail", model);
		}*/
		 list=npaService.getNPADetails(cgpan);
		 if(list.size() > 0 || !list.isEmpty()){
			
		//	model.put("dataList", list);
			model.put("message", "CGPAN Already NPA Mark !!");
			//Added by say 6 FEb-------------------------------------------------------------
			 model.put("adminlist",
						userActivityService.getActivity("NBFCMAKER", "System_Admin"));
			 model.put("guaranteelist",
						userActivityService.getActivity("NBFCMAKER", "Registration"));
			 model.put("applicationList",
						userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
			 model.put("RPaymentList",
						userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
			 model.put("actName", userActivityService.getActivityName("NBFCMAKER",
						"NPADetails"));// Added by Say 31 Jan19
			 model.put("repList",
						userActivityService.getReport("NBFCMAKER", "User_Report"));
			 model.put("GMaintainlist", userActivityService.getActivity(
						"NBFCMAKER", "Guarantee_Maintenance"));
			 model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
			 model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
			 model.put("homePage", "nbfcMakerHome");
			model.put("memberId", memberId);
		
			return new ModelAndView("npaDetail", model);
		}
		
		
		
		 /*npaList = npaService.getNPADetailsForApproval(userId,"NCR");
		 if (npaList != null) {
				model.put("dataListReturn", npaList);
			} else {
				//log.info("Throwing exception due to data not found in NPACheckerController class");
				model.put("dataListReturn", "");
			}*/
		Bean.setSanctionDt1(npaDetailsBean.getSanctionDt1());
		Bean.setGuarStartDt1(npaDetailsBean.getGuarStartDt1());
		Bean.setFirstDisbDt1(npaDetailsBean.getFirstDisbDt1());
		Bean.setIntrestRate(npaDetailsBean.getIntrestRate());
		Bean.setTotalDisbAmt1(npaDetailsBean.getTotalDisbAmt1());
		Bean.setTotalDisbAmt1(npaDetailsBean.getTotalDisbAmt1());
		///Bean.setTotalGuaranteeAmt(npaDetailsBean.getTotalGuaranteeAmt());
		///Bean.setLatestOsAmt(npaDetailsBean.getLatestOsAmt());
		
		Bean.setTolGuarAmtBD(npaDetailsBean.getTolGuarAmtBD());
		Bean.setLatestOSGuarAmtBD(npaDetailsBean.getLatestOSGuarAmtBD());
		//model.put("npaDetailsBean",	npaDetailsBean);	
		
		model.put("npaCreditRisk",dataValidation.getNPACreditRisk());
		model.put("npaResion",
				dataValidation.getNPAResion());
//		model.put("actList",
//				userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		//Added by say 6 FEb-------------------------------------------------------------
		 model.put("adminlist",
					userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		 model.put("guaranteelist",
					userActivityService.getActivity("NBFCMAKER", "Registration"));
		 model.put("applicationList",
					userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
		 model.put("RPaymentList",
					userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
		 model.put("actName", userActivityService.getActivityName("NBFCMAKER",
					"NPADetails"));// Added by Say 31 Jan19
		 model.put("repList",
					userActivityService.getReport("NBFCMAKER", "User_Report"));
		 model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
		 model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
		 model.put("GMaintainlist", userActivityService.getActivity(
					"NBFCMAKER", "Guarantee_Maintenance"));
//			modelAct.put("actList",
//					userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		 model.put("memberId", memberId);
		 model.put("homePage", "nbfcMakerHome");
		 model.put("formData", npaDetailsBean);
		return new ModelAndView("npaDetailsData", model);

	}

	@RequestMapping(value = "/npaUpdate", method = RequestMethod.GET)
	public ModelAndView updateNPAMarking(
			@ModelAttribute("command")NPAMarkBean Bean,
			BindingResult result, HttpSession session,String Cgpan) {
		Map<String, Object> model = new HashMap<String, Object>();
		//validator.npaValidation(Bean, result);
		String userId = (String) session.getAttribute("userId");
		if(userId==null){
			return new ModelAndView("redirect:/SVLogin.html");
		}
		List<NPADetailsBean> list= new ArrayList<NPADetailsBean>();
		NPADetailsBean npaDetailsBean;
		NPADetailsBean npaDetailBean;
		DataValidation dataValidation = new DataValidation();
		System.out.println("Bean"+Bean);
		String bankId=Bean.getMLIID();
		npaDetailsBean=npaService.getCGPANDetails(Cgpan,userId);
         Double IntrestRate=npaDetailsBean.getIntrestRate();
		npaDetailBean=npaService.getModifyNPADetails(Cgpan,userId);		
		
		npaDetailBean.setGuarStartDt1(npaDetailsBean.getGuarStartDt1());
		npaDetailBean.setSanctionDt1(npaDetailsBean.getSanctionDt1());
	
		npaDetailBean.setIntrestRate(IntrestRate);
		
		model.put("npaDetailsBean",	npaDetailBean);
		//Added by say 6 FEb-------------------------------------------------------------
		model.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		//added by say 6 feb-----------------------
		model.put("adminlist",
				userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
		model.put("guaranteelist",
				userActivityService.getActivity("NBFCCHECKER", "Registration"));
		model.put("applicationList",
				userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
		model.put("RPaymentList",
				userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName("NBFCCHECKER",
				"danGenerateRpNumberForPaymentChecker"));// Added by Say 31 Jan19
		model.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"NBFCCHECKER", "Guarantee_Maintenance"));
		 model.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
		 model.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
		 
		 model.put("homePage", "nbfcCheckerHome");
	
		 model.put("memberId", memberId);
		return new ModelAndView("npaEditDetails", model);
	}
	
	@RequestMapping(value = "/npaEditUpdate", method = RequestMethod.GET)
	public ModelAndView npaEditUpdate(
			@ModelAttribute("command")NPAMarkBean Bean,
			BindingResult result, HttpSession session,String Cgpan) {
		Map<String, Object> model = new HashMap<String, Object>();
		//validator.npaValidation(Bean, result);
		String userId = (String) session.getAttribute("userId");
		if(userId==null){
			return new ModelAndView("redirect:/SVLogin.html");
		}
		List<NPADetailsBean> list= new ArrayList<NPADetailsBean>();
		NPADetailsBean npaDetailsBean;
		NPADetailsBean npaDetailBean;
		DataValidation dataValidation = new DataValidation();
		System.out.println("Bean"+Bean);
		//String cgpan=Bean.getCGPAN();
		String bankId=Bean.getMLIID();
		//String memberId = npaService.getMemberId(userId);
		System.out.println("bankId "+bankId+" ,cgpan  "+Cgpan);

		npaDetailsBean=npaService.getCGPANDetails(Cgpan,userId);
         Double IntrestRate=npaDetailsBean.getIntrestRate();
		npaDetailBean=npaService.getModifyNPADetails(Cgpan,userId);		
		
		npaDetailBean.setGuarStartDt1(npaDetailsBean.getGuarStartDt1());
		npaDetailBean.setSanctionDt1(npaDetailsBean.getSanctionDt1());
	
		npaDetailBean.setIntrestRate(IntrestRate);
		model.put("npaResion",
				dataValidation.getNPAResion());
		model.put("npaDetailsBean",	npaDetailBean);
		//Added by say 6 FEb-------------------------------------------------------------
		model.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		//added by say 6 feb-----------------------
		model.put("adminlist",
				userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
		model.put("guaranteelist",
				userActivityService.getActivity("NBFCCHECKER", "Registration"));
		model.put("applicationList",
				userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
		model.put("RPaymentList",
				userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName("NBFCCHECKER",
				"danGenerateRpNumberForPaymentChecker"));// Added by Say 31 Jan19
		model.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"NBFCCHECKER", "Guarantee_Maintenance"));
		 model.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
		 model.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
		 model.put("homePage", "nbfcCheckerHome");
	
		 model.put("memberId", memberId);
		return new ModelAndView("npaEditModify", model);
	}
	
	
	@RequestMapping(value = "/npaDataModify", method = RequestMethod.POST)
	public ModelAndView npaDataModify(
			@ModelAttribute("command")NPADetailsBean Bean,
			BindingResult result, HttpSession session,String flag) {
		Map<String, Object> model = new HashMap<String, Object>();
	
		String userId = (String) session.getAttribute("userId");
		if(userId==null){
			return new ModelAndView("redirect:/SVLogin.html");
		}
		

		NPADetailsBean npaDetailsBean;
		DataValidation dataValidation = new DataValidation();
        Bean.setIsAsPerRBI(flag);
		String cgpan=Bean.getCGPAN();
		npaDetailsBean=npaService.getCGPANDetails(cgpan,userId);
        Double IntrestRate=npaDetailsBean.getIntrestRate();
		String bankId=Bean.getMLIID();
		String memberId = npaService.getMemberId(userId);
		System.out.println("bankId "+bankId+" ,cgpan  "+cgpan);
		validator.npaEditSaveValidation(Bean, result);
		if (result.hasErrors()) {
			//Added by say 6 FEb-------------------------------------------------------------
			model.put("npaResion",
					dataValidation.getNPAResion());
			 model.put("adminlist",
						userActivityService.getActivity("NBFCMAKER", "System_Admin"));
			 model.put("guaranteelist",
						userActivityService.getActivity("NBFCMAKER", "Registration"));
			 model.put("applicationList",
						userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
			 model.put("RPaymentList",
						userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
			 model.put("actName", userActivityService.getActivityName("NBFCMAKER",
						"NPADetails"));// Added by Say 31 Jan19
			 model.put("repList",
						userActivityService.getReport("NBFCMAKER", "User_Report"));
			 model.put("GMaintainlist", userActivityService.getActivity(
						"NBFCMAKER", "Guarantee_Maintenance"));
			 model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
			 model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
//				modelAct.put("actList",
//						userActivityService.getActivity("NBFCMAKER", "User_Activity"));
			 model.put("homePage", "nbfcMakerHome");
			model.put("memberId", memberId);
			
			return new ModelAndView("npaEditModify", model);
		}
		int num1=npaService.NPASaveInAuditTable(Bean,cgpan,userId);
		int val=npaService.updateNPADetails(Bean,cgpan,userId);
		if(val==0){
//			model.put("actList", userActivityService.getActivity("NBFCMAKER",
//					"User_Activity"));
			model.put("adminlist",
					userActivityService.getActivity("NBFCMAKER", "System_Admin"));
			model.put("guaranteelist",
					userActivityService.getActivity("NBFCMAKER", "Registration"));
			model.put("applicationList",
					userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
			model.put("RPaymentList",
					userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
			model.put("actName", userActivityService.getActivityName("NBFCMAKER",
					"NPADetails"));// Added by Say 31 Jan19
			model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
			model.put("GMaintainlist", userActivityService.getActivity(
					"NBFCMAKER", "Guarantee_Maintenance"));
			model.put("memberId", memberId);
			model.put("message", "NPA Successfully Modified : "+cgpan);
			return new ModelAndView("npaDetail", model);
			
		}
		System.out.println(val);
		
		model.put("npaCreditRisk",dataValidation.getNPACreditRisk());
		model.put("npaResion",
				dataValidation.getNPAResion());
		model.put("adminlist",
				userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		model.put("guaranteelist",
				userActivityService.getActivity("NBFCMAKER", "Registration"));
		model.put("applicationList",
				userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
		model.put("RPaymentList",
				userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName("NBFCMAKER",
				"NPADetails"));// Added by Say 31 Jan19
		 model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
		 model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"NBFCMAKER", "Guarantee_Maintenance"));
//		model.put("actList",
//				userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		model.put("repList", userActivityService.getReport(
				"NBFCMAKER", "User_Report"));
		model.put("memberId", memberId);
		model.put("homePage", "nbfcMakerHome");
		return new ModelAndView("npaDetailsData", model);

	}
	
	@RequestMapping(value = "/npaMark", method = RequestMethod.POST)
	public ModelAndView saveNPAMarking(
			@ModelAttribute("command")NPAMarkBean Bean,
			BindingResult result, HttpSession session) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		validator.npaValidation(Bean, result);
		String userId = (String) session.getAttribute("userId");
		if(userId==null){
			return new ModelAndView("redirect:/SVLogin.html");
		}
		NPADetailsBean npaDetailsBean;
		DataValidation dataValidation = new DataValidation();
		System.out.println("Bean"+Bean);
		String cgpan=Bean.getCGPAN();
		String bankId=Bean.getMLIID();
		String memberId = npaService.getMemberId(userId);
		System.out.println("bankId "+bankId+" ,cgpan  "+cgpan);
		validator.npaSaveValidation(Bean, result);
		/*try {
			validator.npaSaveForDate(Bean, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		if (result.hasErrors()) {
			//Added by say 6 FEb-------------------------------------------------------------
			 model.put("adminlist",
						userActivityService.getActivity("NBFCMAKER", "System_Admin"));
			 model.put("guaranteelist",
						userActivityService.getActivity("NBFCMAKER", "Registration"));
			 model.put("applicationList",
						userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
			 model.put("RPaymentList",
						userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
			 model.put("actName", userActivityService.getActivityName("NBFCMAKER",
						"NPADetails"));// Added by Say 31 Jan19
			 model.put("repList",
						userActivityService.getReport("NBFCMAKER", "User_Report"));
			 model.put("GMaintainlist", userActivityService.getActivity(
						"NBFCMAKER", "Guarantee_Maintenance"));
			 model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
			 model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
//				modelAct.put("actList",
//						userActivityService.getActivity("NBFCMAKER", "User_Activity"));
			 model.put("homePage", "nbfcMakerHome");
			model.put("memberId", memberId);
			model.put("npaCreditRisk",dataValidation.getNPACreditRisk());
			model.put("npaResion",
					dataValidation.getNPAResion());
			return new ModelAndView("npaDetailsData", model);
		}
		 
		npaDetailsBean=npaService.getCGPANDetails(cgpan,userId);
				
		Bean.setSanctionDt1(npaDetailsBean.getSanctionDt1());
		Bean.setGuarStartDt1(npaDetailsBean.getGuarStartDt1());

		//model.put("npaDetailsBean",	npaDetailsBean);
		
		NPATCDetailsBean npaTCDetailsBean= new NPATCDetailsBean();
		NPAWCDetailsBean npaWCDetailsBean= new NPAWCDetailsBean();
		
		String loanType=cgpan.substring(cgpan.length() - 2);
		Bean.setLoanType(loanType);
		
	
		int val=npaService.saveNPADetails(Bean,npaTCDetailsBean,npaWCDetailsBean,userId);
		if(val==0){
			//Added by say 6 FEb-------------------------------------------------------------
			 model.put("adminlist",
						userActivityService.getActivity("NBFCMAKER", "System_Admin"));
			 model.put("guaranteelist",
						userActivityService.getActivity("NBFCMAKER", "Registration"));
			 model.put("applicationList",
						userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
			 model.put("RPaymentList",
						userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
			 model.put("actName", userActivityService.getActivityName("NBFCMAKER",
						"NPADetails"));// Added by Say 31 Jan19
			 model.put("repList",
						userActivityService.getReport("NBFCMAKER", "User_Report"));
			 model.put("GMaintainlist", userActivityService.getActivity(
						"NBFCMAKER", "Guarantee_Maintenance"));
			 model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
			 model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
//				modelAct.put("actList",
//						userActivityService.getActivity("NBFCMAKER", "User_Activity"));
			 model.put("homePage", "nbfcMakerHome");
			model.put("memberId", memberId);
			model.put("message", "NPA Successfully Marked : "+cgpan);
			return new ModelAndView("npaDetail", model);
			
		}
		System.out.println(val);
		
		model.put("npaCreditRisk",dataValidation.getNPACreditRisk());
		model.put("npaResion",
				dataValidation.getNPAResion());
		//Added by say 6 FEb-------------------------------------------------------------
		 model.put("adminlist",
					userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		 model.put("guaranteelist",
					userActivityService.getActivity("NBFCMAKER", "Registration"));
		 model.put("applicationList",
					userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
		 model.put("RPaymentList",
					userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
		 model.put("actName", userActivityService.getActivityName("NBFCMAKER",
					"NPADetails"));// Added by Say 31 Jan19
		 model.put("repList",
					userActivityService.getReport("NBFCMAKER", "User_Report"));
		 model.put("GMaintainlist", userActivityService.getActivity(
					"NBFCMAKER", "Guarantee_Maintenance"));
		 model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
		 model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
//			modelAct.put("actList",
//					userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		 model.put("memberId", memberId);
		 model.put("homePage", "nbfcMakerHome");
		return new ModelAndView("npaDetailsData", model);

	}
//	@RequestMapping(value = "/npaDelete", method = RequestMethod.GET)
//	public ModelAndView npaDelete(
//			@ModelAttribute("command") NPADetailsBean Bean,
//			BindingResult result, HttpSession session) {
//		Map<String, Object> model = new HashMap<String, Object>();
//		String userId = (String) session.getAttribute("userId");
//		List<NPADetailsBean> npaList =  new ArrayList<NPADetailsBean>();
//		int flag;
//		if (userId == null) {
//			return new ModelAndView("redirect:/SVLogin.html");
//		}
//		String cgpan = Bean.getCGPAN();
//		flag = npaService.updateNPAApproveReject(cgpan, userId, "NMR","NMD");
//		if (flag == 0) {
//		memberId = npaService.getMemberId(userId);
//		System.out.println(memberId);
//		npaList = npaService.getNPADetailsForApproval(userId, "NCR");
//		if (npaList != null) {
//			model.put("dataListReturn", npaList);
//		} else {
//			model.put("dataListReturn", "");
//		}
//		model.put("message", "CGPAM Succefully Delete");
//		}
//	
//				model.put("adminlist",
//						userActivityService.getActivity("NBFCMAKER", "System_Admin"));
//				model.put("guaranteelist",
//						userActivityService.getActivity("NBFCMAKER", "Registration"));
//				model.put("applicationList",
//						userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
//				model.put("RPaymentList",
//						userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
//				model.put("actName", userActivityService.getActivityName("NBFCMAKER",
//						"NPADetails"));// Added by Say 31 Jan19
//				model.put("GMaintainlist", userActivityService.getActivity(
//						"NBFCMAKER", "Guarantee_Maintenance"));
////				model.put("actList",
////						userActivityService.getActivity("NBFCMAKER", "User_Activity"));
//				model.put("repList", userActivityService.getReport(
//						"NBFCMAKER", "User_Report"));
//				model.put("homePage", "nbfcMakerHome");
//		model.put("memberId", memberId);
//		return new ModelAndView("npaDetail", model);
//
//	}
	
	
	
	//Diksha NPA Bulk Upload 
	
	@RequestMapping(value = "/NpaBulkUpload", method = RequestMethod.GET)
	public ModelAndView NpaBulkUpload(
			@ModelAttribute("command") NPADetailsBean Bean,
			BindingResult result, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		List<NPADetailsBean> npaList = null;
		if(userId==null){
			return new ModelAndView("redirect:/SVLogin.html");
		}
		memberId = npaService.getMemberId(userId);
       	System.out.println(memberId);
		/*npaList = npaService.getNPADetailsForApproval(userId,"NCR");
		 if (npaList != null) {
				model.put("dataListReturn", npaList);
			} else {
				//log.info("Throwing exception due to data not found in NPACheckerController class");
				model.put("dataListReturn", "");
			}*/
		//Added by say 6 FEb-------------------------------------------------------------
		 model.put("adminlist",
					userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		 model.put("guaranteelist",
					userActivityService.getActivity("NBFCMAKER", "Registration"));
		 model.put("applicationList",
					userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
		 model.put("RPaymentList",
					userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
		 model.put("actName", userActivityService.getActivityName("NBFCMAKER",
					"NPADetails"));// Added by Say 31 Jan19
		 model.put("repList",
					userActivityService.getReport("NBFCMAKER", "User_Report"));
		 model.put("GMaintainlist", userActivityService.getActivity(
					"NBFCMAKER", "Guarantee_Maintenance"));
		 model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
		 model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
//			modelAct.put("actList",
//					userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		 model.put("memberId", memberId);
		 model.put("homePage", "nbfcMakerHome");
		return new ModelAndView("NpaBulkUploadInput", model);

	}
	
	@RequestMapping(value = "/NpaBulkUploadProcess", method = RequestMethod.POST)
	public ModelAndView NpaBulkUploadProcess(
			@ModelAttribute("command") MLIMakerInterfaceUploadedBean excelFile,
			BindingResult result, ModelMap modelMap, Model modFr,
			HttpServletRequest request, Model modelMsg, HttpSession session) {
		
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String fileName = excelFile.getFile().getOriginalFilename();
			if (!fileName.endsWith(".xls")) {
				
				 model.put("adminlist",
							userActivityService.getActivity("NBFCMAKER", "System_Admin"));
				 model.put("guaranteelist",
							userActivityService.getActivity("NBFCMAKER", "Registration"));
				 model.put("applicationList",
							userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
				 model.put("RPaymentList",
							userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
				 model.put("actName", userActivityService.getActivityName("NBFCMAKER",
							"NPADetails"));// Added by Say 31 Jan19
				 model.put("repList",
							userActivityService.getReport("NBFCMAKER", "User_Report"));
				 model.put("GMaintainlist", userActivityService.getActivity(
							"NBFCMAKER", "Guarantee_Maintenance"));
				 model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
				 model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
//					modelAct.put("actList",
//							userActivityService.getActivity("NBFCMAKER", "User_Activity"));
				 model.put("memberId", memberId);
				 model.put("homePage", "nbfcMakerHome");
				 model.put("message", "Batch File Should be .xls");
				return new ModelAndView("NpaBulkUploadInput", model);
			}
				
				
			
			
		UserInfo user=(UserInfo) session.getAttribute("userInfo");
		Session connSess = sessionFactory.openSession();
		Transaction tn = connSess.beginTransaction();
		Connection conn = connSess.connection();
		System.out.println("conn :"+conn);
		//######################
		
		NPABulkUpload NpaBlUpObj = new NPABulkUpload();
		String TableName="NBFC_NPA_UPLOAD_DETAIL";		
		String BulkName="Apps";
		LinkedHashMap<String, TableDetailBean> headerMap;
		headerMap = NpaBlUpObj.getTableHeaderData(conn,TableName,BulkName);
		HashMap<String, ArrayList<String>> UploadedStatus = new HashMap<String, ArrayList<String>>();
		UploadedStatus=NpaBlUpObj.CheckExcelData(excelFile.getFile(),headerMap,TableName,conn,user,BulkName);
		
		if(UploadedStatus!=null){	
			
			ArrayList SuccessDataList=(ArrayList)UploadedStatus.get("successRecord");
			ArrayList UnSuccessDataList=(ArrayList)UploadedStatus.get("unsuccessRecord");
			ArrayList allerrors=(ArrayList)UploadedStatus.get("allerror");/**/			
			System.out.println("allerrors:"+allerrors);
			System.out.println("UnSuccessDataList:"+UnSuccessDataList);	
			System.out.println("SuccessDataList:"+SuccessDataList);	
			HttpSession sess = request.getSession(false);
			request.setAttribute("UploadedStatus",UploadedStatus);
			sess.setAttribute("SuccessDataList", SuccessDataList);
			sess.setAttribute("UnSuccessDataList", UnSuccessDataList); 
			sess.setAttribute("Allerrors", allerrors);
		}
	//	request.setAttribute("UploadedStatus", UploadedStatus);
		//System.out.println("Conn:"+BlUpObj.getConnection());/**/
		//#################
		
		
		model.put("adminlist",
				userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		model.put("guaranteelist", userActivityService.getActivity("NBFCMAKER",
				"Guarantee_Maintenance"));
		model.put("applicationList", userActivityService.getActivity(
				"NBFCMAKER", "Application_Processing"));
		model.put("RPaymentList", userActivityService.getActivity("NBFCMAKER",
				"Receipt_Payments"));
		model.put("actName",
				userActivityService.getActivityName("NBFCMAKER", "mliMaker"));// Added
		model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
		model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));// by
																				// say
		model.put("repList",
				userActivityService.getReport("NBFCMAKER", "User_Report"));			
		model.put("GMaintainlist", userActivityService.getActivity(
				"NBFCMAKER", "Guarantee_Maintenance"));// 25june19
		model.put("homePage", "nbfcMakerHome");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ModelAndView("NpaBulkUploadProcess", model);

	}
	
	//DIKSHA
	@RequestMapping(value = "exportToNpaFile", method = RequestMethod.GET)
	public ModelAndView exportToNpaFile(
			@ModelAttribute("command") MLIMakerInterfaceUploadedBean excelFile,
			BindingResult result, ModelMap modelMap, Model modFr,
			HttpServletRequest request,HttpServletResponse response, Model modelMsg, HttpSession session) throws IOException {
		//System.out.println("exportToFile.............START");
		
		//OutputStream os = response.getOutputStream();
		Map<String, Object> model = new HashMap<String, Object>();
    	Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		String strDate = sdf.format(cal.getTime());		
    	System.out.println("ExportToFile Calling..");
		
    	//String contextPath1 = request.getSession(false).getServletContext().getRealPath("");
    	//String contextPath1 = request.getSession(false).getServletContext().getRealPath("");
    	String contextPath1 = "D://NBFCDownload";
    	
    	
    	
    	System.out.println("exportToFile@contextPath1 :"+contextPath1);
        String contextPath = contextPath1.replace('\\','/');
        System.out.println("exportToFile@contextPath1 :"+contextPath1);
        //System.out.println("contextPath1 :"+contextPath1);
        //System.out.println("contextPath :"+contextPath);
    	HttpSession sess = request.getSession(false);		
    	String fileType = request.getParameter("fileType");
    	String FlowLevel = request.getParameter("FlowLevel");    	
    	System.out.println("@@@@@@@@@@@@FlowLevel :"+FlowLevel);		
		//ArrayList ClmDataList = (ArrayList)sess.getAttribute("ClaimSettledDatalist");
		
		ArrayList ClmDataList = (ArrayList)sess.getAttribute(FlowLevel);
		
		//System.out.println("@@@@@@@@@@@@ClmDataList:"+ClmDataList);
		ArrayList HeaderArrLst = (ArrayList)ClmDataList.get(0);
		//System.out.println("@@@@@@@@@@@@HeaderArrLst:"+HeaderArrLst);
		int NoColumn = HeaderArrLst.size();		
		System.out.println("fileType:"+fileType);
		
		
		
		if(fileType.equals("XLSType")){
			System.out.println("####################inside if#####################");
			FileExportHelper fileExportHelper = new FileExportHelper();
			byte[] b = fileExportHelper.generateEXL(ClmDataList,NoColumn,contextPath);			
			if (response != null)
			    response.setContentType("APPLICATION/OCTET-STREAM");
			    response.setHeader("Content-Disposition","attachment; filename=ExcelData"+strDate+".xls");
			    OutputStream os = response.getOutputStream();
	            os.write(b);
	            //os.flush();			
	            os.close();
		}		 /**/
		//System.out.println("exportToFile.............END");
		return null;
    }
	
	//End 
}
