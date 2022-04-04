package com.nbfc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nbfc.bean.ClaimLodgementBean;
import com.nbfc.bean.MLIMakerInterfaceUploadedBean;
import com.nbfc.bean.NPADetailsBean;
import com.nbfc.bean.NPAMarkBean;
import com.nbfc.bean.NPATCDetailsBean;
import com.nbfc.bean.NPAWCDetailsBean;
import com.nbfc.bean.NpaUpgradationBean;
import com.nbfc.helper.FileExportHelper;
import com.nbfc.helper.NPABulkUpload;
import com.nbfc.helper.TableDetailBean;
import com.nbfc.model.MLIName;
import com.nbfc.model.UserInfo;
import com.nbfc.service.CGTMSECreateExposureLimitMakerService;
import com.nbfc.service.NPAService;
import com.nbfc.service.NPAUpgradationService;
import com.nbfc.service.UserActivityService;
import com.nbfc.validation.EmployeeValidator;
import com.nbfc.validator.DataValidation;

import net.sf.jasperreports.engine.JRException;

@Controller
public class NPAUpgradationController {

	@Autowired
	UserActivityService userActivityService;
	@Autowired
	NPAService npaService;
	@Autowired
	NPAUpgradationService npaUpgradationService;
	@Autowired
	EmployeeValidator validator;
	@Value("${downloadFileDirPath}")
	private String downloadFileDirPath;
	@Value("${NPAUpgrdationReportFile}")
	private String downloadFileName;
	@Autowired
	private CGTMSECreateExposureLimitMakerService cgtmseExposureMasterMakerService;
	@Autowired
	static Logger log = Logger.getLogger(NBFCController.class.getName());
	public static final int BUFFER_SIZE = 4096;
	String memberId=null;
	String userId = null;
	MLIName mem_id=null;
	@RequestMapping(value = "/NPAUpgradation", method = RequestMethod.GET)
	public ModelAndView NPAUpgradation(
			@ModelAttribute("command") NpaUpgradationBean Bean,
			BindingResult result, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		List<NpaUpgradationBean> npaList = null;
		if(userId==null){
			return new ModelAndView("redirect:/SVLogin.html");
		}
		memberId = npaService.getMemberId(userId);
       	System.out.println(memberId);
		npaList = npaUpgradationService.getNPADetailsForUpgradationEdit(userId,"NCR");
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
//			modelAct.put("actList",
//					userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		 model.put("memberId", memberId);
		 model.put("homePage", "nbfcMakerHome");
		return new ModelAndView("NPAUpgradationInput", model);
	}
	
	@RequestMapping(value = "/npaUpgradationDetails", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView npaUpgradationDetails(
			@ModelAttribute("command") @Valid NpaUpgradationBean Bean,
			BindingResult result, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println("NpaUpgradationDetails==");
		//validator.npaValidation(Bean, result);
		String userId = (String) session.getAttribute("userId");
		if(userId==null){
			return new ModelAndView("redirect:/SVLogin.html");
		}
		NpaUpgradationBean npaDetailsBean;
		List<NpaUpgradationBean> npaList = null;
		List<NpaUpgradationBean> list= new ArrayList<NpaUpgradationBean>();
		//DataValidation dataValidation = new DataValidation();
		
		String cgpan=Bean.getCGPAN();
		String bankId=Bean.getMLIID();
		String memberId = npaService.getMemberId(userId);
		System.out.println("bankId "+bankId+" ,cgpan  "+cgpan);
		//validator.npaValidation(Bean, result);
		
		list=npaUpgradationService.getCgpanNpaDetails(cgpan);
		 if(list.size() < 0 ){
			
		//	model.put("dataList", list);
			model.put("message", "NPA has not marked !!");
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
			 model.put("homePage", "nbfcMakerHome");
			model.put("memberId", memberId);
			return new ModelAndView("NPAUpgradationInput", model);
		 }
		 list=npaUpgradationService.getCgpanNpaUpgradationDetails(cgpan);
		 if(list.size()<0 ){
			
		//	model.put("dataList", list);
			model.put("message", "Npa Upgradation has already lodged !!");
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
			 model.put("homePage", "nbfcMakerHome");
			model.put("memberId", memberId);
			return new ModelAndView("NPAUpgradationInput", model);
		 }
		 list=npaUpgradationService.getCgpanStatus(cgpan,userId);//getNPADetailsForUpgradation
		 if(list.size() <0 || list.isEmpty()){
				
				//	model.put("dataList", list);
					model.put("message", "CGPAN has not live !!");
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
					 model.put("homePage", "nbfcMakerHome");
					model.put("memberId", memberId);
					return new ModelAndView("NPAUpgradationInput", model);
				 }
		 npaDetailsBean=npaUpgradationService.getNPADetailsForUpgradation(cgpan,userId);//getNPADetailsForUpgradation
		 if(npaDetailsBean.getDayCount()==2){
				
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
//					modelAct.put("actList",
//							userActivityService.getActivity("NBFCMAKER", "User_Activity"));
				 model.put("homePage", "nbfcMakerHome");
				model.put("memberId", memberId);
			
				return new ModelAndView("NPAUpgradationInput", model);
			}
		 
		 Bean.setBankName(npaDetailsBean.getBankName());
		 Bean.setMLIID(npaDetailsBean.getMLIID());
		 Bean.setBorrowerName(npaDetailsBean.getBorrowerName());
		 Bean.setCGPAN(npaDetailsBean.getCGPAN());
		 Bean.setStatus(npaDetailsBean.getStatus());
		 Bean.setNpaDt(npaDetailsBean.getNpaDt());
		 Bean.setNpaReason(npaDetailsBean.getNpaReason());
		 	
		/*
		 * model.put("npaCreditRisk",dataValidation.getNPACreditRisk());
		 * model.put("npaResion", dataValidation.getNPAResion());
		 */
//			model.put("actList",
//					userActivityService.getActivity("NBFCMAKER", "User_Activity"));
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
			 model.put("GMaintainlist", userActivityService.getActivity(
						"NBFCMAKER", "Guarantee_Maintenance"));
//				modelAct.put("actList",
//						userActivityService.getActivity("NBFCMAKER", "User_Activity"));
			 model.put("memberId", memberId);
			 model.put("homePage", "nbfcMakerHome");
			return new ModelAndView("NpaUpgradationDetails", model);

	}
	
	@RequestMapping(value = "/SaveNpaUpgradation", method = RequestMethod.POST)
	public ModelAndView saveNpaUpgradation(
			@ModelAttribute("command")NpaUpgradationBean Bean,
			BindingResult result, HttpSession session) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		validator.npaUpgradationValidation(Bean, result);
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
		validator.npaUpgradationValidation(Bean, result);
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
//				modelAct.put("actList",
//						userActivityService.getActivity("NBFCMAKER", "User_Activity"));
			 model.put("homePage", "nbfcMakerHome");
			model.put("memberId", memberId);
		return new ModelAndView("NPAUpgradationInput", model);
		}
		String npaDt=Bean.getNpaDt();
		System.out.println("NPA Date=="+npaDt);
		String npaUpgradationDt=Bean.getNpaUpgradationDt();
		System.out.println("NPA Upgradation Date=="+npaUpgradationDt);
	System.out.println("NPA Upgradation Date=="+npaUpgradationDt);
	
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date npaDt1=sdf.parse(npaDt);
		System.out.println("NPA Date==npaDt1==="+npaDt1);
		String msg=dataValidation.npaUpgradationDateValidator(npaDt, npaUpgradationDt);
		System.out.println("Msg==="+msg);
		if (msg!=null) {
			System.out.println("Msg######==="+msg);
			model.put("message", msg);
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
//				modelAct.put("actList",
//						userActivityService.getActivity("NBFCMAKER", "User_Activity"));
			 model.put("homePage", "nbfcMakerHome");
			model.put("memberId", memberId);
			return new ModelAndView("NpaUpgradationDetails", model);
		}
		
		int val=npaUpgradationService.saveNPAUpgradationDetails(Bean,userId);
		
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
//				modelAct.put("actList",
//						userActivityService.getActivity("NBFCMAKER", "User_Activity"));
			 model.put("homePage", "nbfcMakerHome");
			model.put("memberId", memberId);
			model.put("message", "Account upgraded successfully : "+cgpan);
			return new ModelAndView("NPAUpgradationInput", model);
			
		}
		System.out.println(val);
		
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
//			modelAct.put("actList",
//					userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		 model.put("memberId", memberId);
		 model.put("homePage", "nbfcMakerHome");
		return new ModelAndView("NpaUpgradationDetails", model);
	}
	
	
	@RequestMapping(value = "/NPAUpgradationApproval", method = RequestMethod.GET)
	public ModelAndView npaApproval(
			@ModelAttribute("command") NpaUpgradationBean Bean,
			BindingResult result, HttpSession session) {
		Map<String, Object> model = new HashMap<String, Object>();
		List<NpaUpgradationBean> npaList = null;
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		}
		memberId = npaService.getMemberId(userId);
		npaList = npaUpgradationService.getNPAUpgradationDetailsForApproval(userId,"NMA");
		if (npaList != null) {
			model.put("dataList", npaList);
		}
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
		 model.put("homePage", "nbfcCheckerHome");
		 model.put("memberId", memberId);
	
		return new ModelAndView("NpaUpgradationApproval", model);

	}
	
	@RequestMapping(value = "/NpaUpgradationUpdate", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView NpaUpgradationUpdate(
			@ModelAttribute("command")NpaUpgradationBean Bean,
			BindingResult result, HttpSession session,String Cgpan) {
		Map<String, Object> model = new HashMap<String, Object>();
		//validator.npaValidation(Bean, result);
		String userId = (String) session.getAttribute("userId");
		if(userId==null){
			return new ModelAndView("redirect:/SVLogin.html");
		}
		List<NpaUpgradationBean> list= new ArrayList<NpaUpgradationBean>();
		NpaUpgradationBean npaDetailsBean;
		NpaUpgradationBean npaDetailBean;
		DataValidation dataValidation = new DataValidation();
		System.out.println("Bean"+Bean);
		String bankId=Bean.getMLIID();
		npaDetailsBean=npaUpgradationService.getNPAUpgradationDetails(Cgpan,userId); //getNPAUpgradationDetails
		//npaDetailBean=npaService.getModifyNPADetails(Cgpan,userId);		
		
		//model.put("npaDetailsBean",	npaDetailsBean);
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
		 model.put("homePage", "nbfcCheckerHome");
	
		 model.put("memberId", memberId);
		 
		 Bean.setBankName(npaDetailsBean.getBankName());
		 Bean.setMLIID(npaDetailsBean.getMLIID());
		 Bean.setBorrowerName(npaDetailsBean.getBorrowerName());
		 Bean.setCGPAN(npaDetailsBean.getCGPAN());
		 Bean.setStatus(npaDetailsBean.getStatus());
		 Bean.setNpaDt(npaDetailsBean.getNpaDt());
		 Bean.setNpaReason(npaDetailsBean.getNpaReason());
		 Bean.setNpaUpgradationDt(npaDetailsBean.getNpaUpgradationDt());
		 Bean.setUpgradationRemarks(npaDetailsBean.getUpgradationRemarks());
		 
		return new ModelAndView("NpaUpgradationEditUpdate", model);
	}
	
	@RequestMapping(value = "/SaveNPAUpgradationApprove", method = RequestMethod.POST)
	public ModelAndView SaveNPAUpgradationApprove(
			@ModelAttribute("command")NpaUpgradationBean Bean,
			BindingResult result, HttpSession session) throws Exception {
		
		
		Map<String, Object> model = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		String memberId=(String) session.getAttribute("memberId");
		System.out.println("memberId=="+memberId);
		
		int flag;
		
		List<NpaUpgradationBean> npaList;
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		}
		memberId = npaService.getMemberId(userId);
		//flag = npaService.updateNPAApproveReject(checCgpan, userId, "NCR","NM",ReturnRemark);
		flag=npaUpgradationService.SaveNPAUpgradationApprove(Bean,userId);//SaveNPAUpgradationReject

		if (flag == 0) {
			npaList = npaUpgradationService.getNPAUpgradationDetailsForApproval(userId,"NMA");
			//if (npaList.size() > 0 || !npaList.isEmpty()) {
				if (npaList!=null) {
					
				model.put("dataList", npaList);
				
			} else {
				//log.info("Throwing exception due to data not found in NPACheckerController class");
				model.put("dataList", "");
			}
				model.put("message", "Record Succefully Approved");
		}

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
		 model.put("homePage", "nbfcCheckerHome");
		model.put("memberId", memberId);
		//return new ModelAndView("npaApprovalRejection", model);
		return new ModelAndView("NpaUpgradationApproval", model);
		
	
	}
	
	@RequestMapping(value = "/SaveNPAUpgradationReject", method = RequestMethod.POST)
	public ModelAndView SaveNPAUpgradationReject(
			@ModelAttribute("command")NpaUpgradationBean Bean,
			BindingResult result, HttpSession session) throws Exception {
		
		Map<String, Object> model = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		String memberId=(String) session.getAttribute("memberId");
		System.out.println("memberId=="+memberId);
		
		int flag;
			
	
		
		List<NpaUpgradationBean> npaList;
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		}
		memberId = npaService.getMemberId(userId);
		//flag = npaService.updateNPAApproveReject(checCgpan, userId, "NCR","NM",ReturnRemark);
		flag=npaUpgradationService.SaveNPAUpgradationReject(Bean,userId);//SaveNPAUpgradationReject

		if (flag == 0) {
			npaList = npaUpgradationService.getNPAUpgradationDetailsForApproval(userId,"NMA");
			//if (npaList.size() > 0 || !npaList.isEmpty()) {
				if (npaList!=null) {
					
				model.put("dataList", npaList);
				
			} else {
				//log.info("Throwing exception due to data not found in NPACheckerController class");
				model.put("dataList", "");
			}
				model.put("message", "Record Succefully Returned");
		}

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
		 model.put("homePage", "nbfcCheckerHome");
		model.put("memberId", memberId);
		//return new ModelAndView("npaApprovalRejection", model);
		return new ModelAndView("NpaUpgradationApproval", model);
	}
	
	@RequestMapping(value = "/SaveNpaUpgradationEditDetails", method = { RequestMethod.GET,RequestMethod.POST})
	public ModelAndView SaveNpaUpgradationEditDetails(
			@ModelAttribute("command")NpaUpgradationBean Bean,
			BindingResult result, HttpSession session,String Cgpan) throws ParseException {
		Map<String, Object> model = new HashMap<String, Object>();
		//validator.npaValidation(Bean, result);
		String userId = (String) session.getAttribute("userId");
		if(userId==null){
			return new ModelAndView("redirect:/SVLogin.html");
		}
		List<NpaUpgradationBean> list= new ArrayList<NpaUpgradationBean>();
		NpaUpgradationBean npaDetailsBean;
		//NpaUpgradationBean npaDetailBean;
		DataValidation dataValidation = new DataValidation();
		System.out.println("Bean"+Bean);
		System.out.println("CGPAN=="+Cgpan);
		String bankId=Bean.getMLIID();
		
		npaDetailsBean=npaUpgradationService.getNPAUpgradationDetails(Cgpan,userId); //getNPAUpgradationDetails
		
		//npaDetailBean=npaService.getModifyNPADetails(Cgpan,userId);		
		
		//model.put("npaDetailsBean",	npaDetailsBean);
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
//			modelAct.put("actList",
//					userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		 model.put("homePage", "nbfcMakerHome");
		model.put("memberId", memberId);
		 
		 Bean.setBankName(npaDetailsBean.getBankName());
		 Bean.setMLIID(npaDetailsBean.getMLIID());
		 Bean.setBorrowerName(npaDetailsBean.getBorrowerName());
		 Bean.setCGPAN(npaDetailsBean.getCGPAN());
		 Bean.setStatus(npaDetailsBean.getStatus());
		 Bean.setNpaDt(npaDetailsBean.getNpaDt());
		 Bean.setNpaReason(npaDetailsBean.getNpaReason());
		 Bean.setNpaUpgradationDt(npaDetailsBean.getNpaUpgradationDt());
		 Bean.setUpgradationRemarks(npaDetailsBean.getUpgradationRemarks());
		 
		
		 
		return new ModelAndView("SaveNpaUpgradationEditDetails", model);
	}
	
	@RequestMapping(value = "/SaveNpaUpgradationEdit", method = RequestMethod.POST)
	public ModelAndView SaveNpaUpgradationEdit(
			@ModelAttribute("command")NpaUpgradationBean Bean,
			BindingResult result, HttpSession session,String Cgpan) throws ParseException {
		Map<String, Object> model = new HashMap<String, Object>();
		//validator.npaValidation(Bean, result);
		String userId = (String) session.getAttribute("userId");
		if(userId==null){
			return new ModelAndView("redirect:/SVLogin.html");
		}
		List<NpaUpgradationBean> list= new ArrayList<NpaUpgradationBean>();
		NpaUpgradationBean npaDetailsBean;
		//NpaUpgradationBean npaDetailBean;
		DataValidation dataValidation = new DataValidation();
		System.out.println("Bean"+Bean);
		String bankId=Bean.getMLIID();
		String npaDt=Bean.getNpaDt();
		System.out.println("NPA Date=="+npaDt);
		String npaUpgradationDt=Bean.getNpaUpgradationDt();
		System.out.println("NPA Upgradation Date=="+npaUpgradationDt);
	System.out.println("NPA Upgradation Date=="+npaUpgradationDt);
	
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date npaDt1=sdf.parse(npaDt);
		System.out.println("NPA Date==npaDt1==="+npaDt1);
	
		String msg=dataValidation.npaUpgradationDateValidator(npaDt, npaUpgradationDt);
		System.out.println("Msg==="+msg);
		if (msg!=null) {
			System.out.println("Msg######==="+msg);
			model.put("message", msg);
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
//				modelAct.put("actList",
//						userActivityService.getActivity("NBFCMAKER", "User_Activity"));
			 model.put("homePage", "nbfcMakerHome");
			model.put("memberId", memberId);
			return new ModelAndView("SaveNpaUpgradationEditDetails", model);
		}
		 int val=npaUpgradationService.saveNPAUpgradationEditDetails(Bean,userId);
			
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
//					modelAct.put("actList",
//							userActivityService.getActivity("NBFCMAKER", "User_Activity"));
				 model.put("homePage", "nbfcMakerHome");
				model.put("memberId", memberId);
				model.put("message", "Account upgraded successfully : ");
				return new ModelAndView("NPAUpgradationInput", model);
				
			}
		 
		return new ModelAndView("SaveNpaUpgradationEditDetails", model);
	}
	
	
	//NPA Upgrdation Report 
	
	@RequestMapping(value = "/NPAUpgradationReport", method = RequestMethod.GET)
	public ModelAndView NPAUpgradationReport(
			@ModelAttribute("command") NpaUpgradationBean bean,
			BindingResult result, HttpSession session, Model model)
			throws JRException {
		Map<String, Object> model1 = new HashMap<String, Object>();
		String Role = (String) session.getAttribute("uRole");
		 userId = (String) session.getAttribute("userId");
		String loginUserMemId = npaService.getMemberId(userId);
		// nbfcInvoiceService.callTaxInvoiceReport(dan_id, response,request);
		if (Role.equals("CMAKER")) {
			model1.put("guaranteelist", userActivityService.getActivity(
					"CGTMSEMAKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"CGTMSEMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"CGTMSEMAKER", "Receipt_Payments"));
			model1.put("CList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement"));
			model1.put("repList",
					userActivityService.getReport("CGTMSEMAKER", "User_Report"));
			
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSEMAKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("MLIID", loginUserMemId);
			model1.put("homePage", "cgtmseMakerHome");

		} else if (Role.equals("CCHECKER")) {
			model1.put("adminlist", userActivityService.getActivity(
					"CGTMSECHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity(
					"CGTMSECHECKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"CGTMSECHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"CGTMSECHECKER", "Receipt_Payments"));
			model1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
			model1.put("repList", userActivityService.getReport(
					"CGTMSECHECKER", "User_Report"));
			// model1.put("actNameHome",
			// userActivityService.getActivityName("CGTMSECHECKER",
			// "cgpanDetailReport"));// Added by Say 31 Jan19
			model1.put("MLIID", loginUserMemId);
			model1.put("homePage", "cgtmseCheckerHome");
			// return null;
		} else if (Role.equals("NMAKER")) {
			// added by say 6 feb-----------------------
			model1.put("adminlist", userActivityService.getActivity(
					"NBFCMAKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity(
					"NBFCMAKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"NBFCMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"NBFCMAKER", "Receipt_Payments"));
			model1.put("repList",
					userActivityService.getReport("NBFCMAKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
			model1.put("MLIID", loginUserMemId);
			model1.put("homePage", "nbfcMakerHome");

		} else if (Role.equals("NCHECKER")) {
			model1.put("adminlist", userActivityService.getActivity(
					"NBFCCHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity(
					"NBFCCHECKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"NBFCCHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"NBFCCHECKER", "Receipt_Payments"));
			model1.put("repList",
					userActivityService.getReport("NBFCCHECKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
			model1.put("MLIID", loginUserMemId);
			model1.put("homePage", "nbfcCheckerHome");

		}
		return new ModelAndView("NPAUpgradationReportInput", model1);
		// return null;
	}
	
	@RequestMapping(value = "/NPAUpgradationReportData", method = RequestMethod.POST)
	public ModelAndView NPAUpgradationReportData(
			@ModelAttribute("command") NpaUpgradationBean bean,
			BindingResult result, HttpSession session, Model model)
			throws JRException, ParseException {
		Map<String, Object> model1 = new HashMap<String, Object>();
		List<NpaUpgradationBean> NpaUpgradationReport=null;
		
		/*
		 * session.setAttribute("ClaimSettledDataReport",null);
		 * session.removeAttribute("ClaimSettledDataReport");
		 */
		
		//String mliLongName=(String) session.getAttribute("mliLongName");
		
		String Role = (String) session.getAttribute("uRole");
		
		String userId = (String) session.getAttribute("userId");
		memberId = npaService.getMemberId(userId);
		
		String mliLongName=bean.getBankName();
		
		System.out.println("mliLongName=="+mliLongName);
		
		validator.NPAUpgradationReportValidate(bean, result);
		if (result.hasErrors()) {
			if (Role.equals("CMAKER")) {
				// userId = "ADMIN";
				model1.put("guaranteelist", userActivityService.getActivity(
						"CGTMSEMAKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity(
						"CGTMSEMAKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity(
						"CGTMSEMAKER", "Receipt_Payments"));
				model1.put("CList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement"));
				model1.put("repList", userActivityService.getReport(
						"CGTMSEMAKER", "User_Report"));
				// model1.put("actNameHome",
				// userActivityService.getActivityName("CGTMSEMAKER",
				// "cgpanDetailReport"));// Added by Say 31 Jan19
				model1.put("homePage", "cgtmseMakerHome");

			} else if (Role.equals("CCHECKER")) {
				// userId = "ADMIN";
				model1.put("adminlist", userActivityService.getActivity(
						"CGTMSECHECKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity(
						"CGTMSECHECKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity(
						"CGTMSECHECKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity(
						"CGTMSECHECKER", "Receipt_Payments"));
				model1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
				model1.put("repList", userActivityService.getReport(
						"CGTMSECHECKER", "User_Report"));
				// model1.put("actNameHome",
				// userActivityService.getActivityName("CGTMSECHECKER",
				// "cgpanDetailReport"));// Added by Say 31 Jan19
				model1.put("homePage", "cgtmseCheckerHome");
				// return null;
			} else if (Role.equals("NMAKER")) {
				// added by say 6 feb-----------------------
				// userId = (String) session.getAttribute("userId");
				model1.put("adminlist", userActivityService.getActivity(
						"NBFCMAKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity(
						"NBFCMAKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity(
						"NBFCMAKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity(
						"NBFCMAKER", "Receipt_Payments"));
				model1.put("repList", userActivityService.getReport(
						"NBFCMAKER", "User_Report"));
				model1.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
				model1.put("homePage", "nbfcMakerHome");

			} else if (Role.equals("NCHECKER")) {
				// userId = (String) session.getAttribute("userId");
				model1.put("adminlist", userActivityService.getActivity(
						"NBFCCHECKER", "System_Admin"));
				model1.put("guaranteelist", userActivityService.getActivity(
						"NBFCCHECKER", "Registration"));
				model1.put("applicationList", userActivityService.getActivity(
						"NBFCCHECKER", "Application_Processing"));
				model1.put("RPaymentList", userActivityService.getActivity(
						"NBFCCHECKER", "Receipt_Payments"));
				model1.put("repList", userActivityService.getReport(
						"NBFCCHECKER", "User_Report"));
				// model1.put("actNameHome",
				// userActivityService.getActivityName("NBFCCHECKER",
				// "cgpanDetailReport"));// Added by Say 31 Jan19
				model1.put("homePage", "nbfcCheckerHome");

			}
			return new ModelAndView("NPAUpgradationReportInput", model1);
		}

		if (Role.equals("CMAKER")) {
			userId = "ADMIN";
			model1.put("guaranteelist", userActivityService.getActivity(
					"CGTMSEMAKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"CGTMSEMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"CGTMSEMAKER", "Receipt_Payments"));

			model1.put("repList",
					userActivityService.getReport("CGTMSEMAKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("CGTMSEMAKER", "Claim_Lodgement"));
			model1.put("homePage", "cgtmseMakerHome");

		} else if (Role.equals("CCHECKER")) {
			userId = "ADMIN";
			model1.put("adminlist", userActivityService.getActivity(
					"CGTMSECHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity(
					"CGTMSECHECKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"CGTMSECHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"CGTMSECHECKER", "Receipt_Payments"));

			model1.put("repList", userActivityService.getReport(
					"CGTMSECHECKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
			model1.put("homePage", "cgtmseCheckerHome");
			// return null;
		} else if (Role.equals("NMAKER")) {
			// added by say 6 feb-----------------------
			userId = (String) session.getAttribute("userId");
			model1.put("adminlist", userActivityService.getActivity(
					"NBFCMAKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity(
					"NBFCMAKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"NBFCMAKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"NBFCMAKER", "Receipt_Payments"));
			model1.put("repList",
					userActivityService.getReport("NBFCMAKER", "User_Report"));
			model1.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
			model1.put("homePage", "nbfcMakerHome");

		} else if (Role.equals("NCHECKER")) {
			userId = (String) session.getAttribute("userId");
			model1.put("adminlist", userActivityService.getActivity(
					"NBFCCHECKER", "System_Admin"));
			model1.put("guaranteelist", userActivityService.getActivity(
					"NBFCCHECKER", "Registration"));
			model1.put("applicationList", userActivityService.getActivity(
					"NBFCCHECKER", "Application_Processing"));
			model1.put("RPaymentList", userActivityService.getActivity(
					"NBFCCHECKER", "Receipt_Payments"));
			model1.put("repList",
					userActivityService.getReport("NBFCCHECKER", "User_Report"));
			// model1.put("actNameHome",
			model1.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
			model1.put("homePage", "nbfcCheckerHome");

		}
		
		/*String toDateF = bean.getToDate();
		String fromDateF = bean.getFromDate();
		session.setAttribute("FDate", fromDateF);
		session.setAttribute("TDate", toDateF);*/
		

		Date toDate = new SimpleDateFormat("dd/MM/yyyy").parse(bean.getToDate());
		Date fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(bean.getFromDate());
		if(Role.equals("CCHECKER") || Role.equals("CMAKER")){
			//String mliname=bean.getMliName();
			//System.out.println("mliname=="+mliname);
			mem_id = userActivityService.getBankID(mliLongName);
			String Mem_Id= mem_id.getBnkId() + mem_id.getZneID()
					+ mem_id.getBrnName();
			NpaUpgradationReport=npaUpgradationService.getNpaUpgradationReport(userId, toDate, fromDate, Mem_Id,mliLongName,Role);
		}else{
			
			NpaUpgradationReport=npaUpgradationService.getNpaUpgradationReport(userId, toDate, fromDate, memberId,mliLongName,Role);
		}
	
		// nbfcInvoiceService.callTaxInvoiceReport(dan_id, response,request);
		if (!NpaUpgradationReport.isEmpty()) {
			model1.put("NpaUpgradationReport", NpaUpgradationReport);
			session.setAttribute("NpaUpgradationReport", NpaUpgradationReport);
		} else {
			model1.put("message", "NO Data Found !!");
		}
		return new ModelAndView("NPAUpgradationReportData", model1);
	}

	//Get the MLI Long Name in Drop Down on Page on Load
		@ModelAttribute("bankName")
		public Map<String, String> getMlilongName() {
			Map<String, String> mapMliLongNameObj = new HashMap<String, String>();
			mapMliLongNameObj = cgtmseExposureMasterMakerService.getMliLongNameInDropDown();
			return   mapMliLongNameObj;
		}
		
	@RequestMapping(value = "/NPAUpgradationReportDownload", method = RequestMethod.GET)
	public ModelAndView NPAUpgradationReportDownload(
			@ModelAttribute("command") NpaUpgradationBean bean, BindingResult result,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException {
		try {

			String filePath = downloadFileDirPath + File.separator
					+ downloadFileName;
			System.out.println("File Path==="+filePath);
			File file = new File(downloadFileDirPath);
			boolean isCreated = file.mkdir();

			if (isCreated) {
				File file1 = new File(filePath);
				boolean isExists = file1.exists();
				if (isExists) {

				} else {
					file1.createNewFile();
				}

			}
			List<NpaUpgradationBean> list = (List<NpaUpgradationBean>) session.getAttribute("NpaUpgradationReport");
			//List<MLIRegistration> list = mliDetailsService.getMLIAllDetails();

			// List<UserPerivilegeDetails>
			// list=employeeService.getUserPrivlageDetails();
			log.info("list size==" + list.size());
			log.info("list Data==" + list);

			// Writing and Downlaoding Excel File
			System.out.println("Create Sheet=");
			XSSFWorkbook hwb = new XSSFWorkbook();
			XSSFSheet sheet = hwb.createSheet("NpaUpgradationReportExcelFile");

			// Making bold and color to excel column heading
			CellStyle style = hwb.createCellStyle();
			Font font = hwb.createFont();
			font.setFontHeightInPoints((short) 11);
			font.setFontName(HSSFFont.FONT_ARIAL);
			font.setBoldweight(HSSFFont.COLOR_NORMAL);
			font.setBold(true);
			font.setColor(HSSFColor.DARK_BLUE.index);
			style.setFont(font);
			style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			sheet.createFreezePane(0, 1); // Freeze 1st Row
											// sheet.createFreezePane(int
											// colSplit, int rowSplit, int
											// leftmostColumn, int topRow)

			// Creating First rows for excel heading
			XSSFRow rowhead = sheet.createRow((short) 0);

			XSSFCell cell0 = rowhead.createCell(0);
			cell0.setCellStyle(style);
			cell0.setCellValue("Bank Name");// Done 1

			XSSFCell cell1 = rowhead.createCell(1);
			cell1.setCellStyle(style);
			cell1.setCellValue("Member ID");// Done 3

			XSSFCell cell2 = rowhead.createCell(2);
			cell2.setCellStyle(style);
			cell2.setCellValue("Unit Name");// Done 4

			XSSFCell cell3 = rowhead.createCell(3);
			cell3.setCellStyle(style);
			cell3.setCellValue("CGPAN Number");// Done 5
			
			XSSFCell cell4 = rowhead.createCell(4);
			cell4.setCellStyle(style);
			cell4.setCellValue("CGPAN Number");// Done 5

			XSSFCell cell5 = rowhead.createCell(5);
			cell5.setCellStyle(style);
			cell5.setCellValue("Application Status");// Done 5
			
			XSSFCell cell6 = rowhead.createCell(6);
			cell6.setCellStyle(style);
			cell6.setCellValue("NPA date");// Done 5
			
			XSSFCell cell7 = rowhead.createCell(7);
			cell7.setCellStyle(style);
			cell7.setCellValue("Date of NPA Upgradation");// Done 5
			
			XSSFCell cell8 = rowhead.createCell(8);
			cell8.setCellStyle(style);
			cell8.setCellValue("Reason for Upgradation");// Done 5
		
			int index = 1;
			int sno = 0;
			Iterator<NpaUpgradationBean> itr2 = list.iterator();
			while (itr2.hasNext()) {
				NpaUpgradationBean obj1 = (NpaUpgradationBean) itr2.next();
				sno++;
				XSSFRow row = sheet.createRow((short) index);
				//

				row.createCell((short) 0).setCellValue(obj1.getBankName());// Done
																			// 1
				row.createCell((short) 1).setCellValue(obj1.getMLIID());// Done
																				// 2
				row.createCell((short) 2).setCellValue(obj1.getBorrowerName());// Done
				
				row.createCell((short) 3).setCellValue(obj1.getCGPAN());// Done
				
				row.createCell((short) 4).setCellValue(obj1.getStatus());// Done
				
				row.createCell((short) 5).setCellValue(obj1.getNpaDt());// Done
				
				row.createCell((short) 4).setCellValue(obj1.getNpaUpgradationDt());// Done
				
				row.createCell((short) 4).setCellValue(obj1.getUpgradationRemarks());// Done
				
				
				index++;
			}
			FileOutputStream fileOut = new FileOutputStream(filePath);
			hwb.write(fileOut);
			fileOut.close();

			ModelAndView model = new ModelAndView("newRolePage");

			model.addObject(
					"excelFileDownLoadSuccessfully",
					"File DownLoaded Successfully in this location F:/ExcelReports/nbfcNPAUpgradationExcel.xls.");

			File downloadFile = new File(filePath);
			log.info("downloadFile =" + downloadFile);
			FileInputStream inputStream = new FileInputStream(downloadFile);

			response.setContentLength((int) downloadFile.length());

			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					downloadFile.getName());
			response.setHeader(headerKey, headerValue);

			// get output stream of the response
			OutputStream outStream = response.getOutputStream();
			System.out.println("DDDDD==");
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

			// write bytes read from the input stream into the output stream
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			inputStream.close();
			outStream.close();
			return model;
		} catch (Exception e) {
			log.info("Exception == " + e);
			System.out.println("Exception == " + e);
		}
		return null;
	}
}
