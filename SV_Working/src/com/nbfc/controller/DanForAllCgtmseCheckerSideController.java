package com.nbfc.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

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

import com.ibm.icu.math.BigDecimal;
import com.nbfc.bean.DanAllForCgtmseCheckerBean;
import com.nbfc.bean.DanCorrespondingDataListBeanCheckerBean;
import com.nbfc.bean.DanGenerateRpNumberForCheckerApprovalBean;

import com.nbfc.model.DanAllForCgtmseCheckerModel;
import com.nbfc.model.DanCorrespondigDataModel;
import com.nbfc.model.DanGenerateRpNumberForPaymentCheckerModel;
import com.nbfc.model.DisburseNonDisburseModel;
import com.nbfc.model.MLIDetails;
import com.nbfc.model.MLIName;

import com.nbfc.model.NBFCPrivilegeMaster;

import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.DanAllForCgtmseCheckerService;
import com.nbfc.service.DanGenerateRpNumberForPaymentCheckerService;
import com.nbfc.service.PortfolioInfoService;
import com.nbfc.service.UserService;

import com.nbfc.service.LoginService;

import com.nbfc.service.MLIRegService;

import com.nbfc.service.UserActivityService;
import com.nbfc.validation.EmployeeValidator;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class DanForAllCgtmseCheckerSideController {

	// ---------------------------------------------------------
	@Autowired
	DanAllForCgtmseCheckerService danAllForCgtmseCheckerService;
	@Autowired
	LoginService lofinService;

	@Autowired
	MLIRegService mliRegService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	EmployeeValidator validator;
	@Autowired
	UserService employeeService;
	UserPerivilegeDetails userPri;
	NBFCPrivilegeMaster userPrvMst;
	MLIDetails mliDetails;
	MLIName mliName;
	List<String> danList = new ArrayList<String>();
	@Autowired
	PortfolioInfoService portfolioInfoService;
	// List<DanGenerateRpNumberForPaymentCheckerModel>listPaymentInitiation=new
	// ArrayList<DanGenerateRpNumberForPaymentCheckerModel>();

	static Logger log = Logger
			.getLogger(DanForAllCgtmseCheckerSideController.class.getName());

	@RequestMapping(value = "/danForAllCgtmseChecker", method = RequestMethod.GET)
	public ModelAndView getDanGenerateData(
			@ModelAttribute("command") DanAllForCgtmseCheckerBean bean,
			BindingResult result, String message, Model modelMsg,
			HttpSession session) {
		log.info("Enter in getDanGenerateData() method in DanForAllCgtmseCheckerSideController class");
		Map<String, Object> model = new HashMap<String, Object>();

		/*
		 * List<String> portFiloNameList = new ArrayList<String>(); Map<String,
		 * String> borDetailsList; String userId = (String)
		 * session.getAttribute("userId"); mliDetails =
		 * employeeService.getBNKId(userId); mliName =
		 * employeeService.getMLIName(mliDetails.getMem_bnk_id());
		 * //portFoliNameList.clear(); //danIDList.clear();
		 * //fileIdList.clear(); borDetailsList =
		 * portfolioInfoService.getDetailsForMLI(mliName.getMliLName()); for
		 * (Map.Entry<String, String> entry : borDetailsList.entrySet()) {
		 * String value = entry.getValue();
		 * portFiloNameList.add(entry.getValue()); }
		 * List<DanGenerateRpNumberForCheckerApprovalBean>list; //String
		 * mliName="sdf"; if(!portFiloNameList.isEmpty()&&
		 * portFiloNameList.size()>0){ //list=prepareDanGenerateDataListofBean(
		 * danGenerateRpNumberForPaymentCheckerService
		 * .getDanId(portFiloNameList)
		 * ,danGenerateRpNumberForPaymentCheckerService);
		 * 
		 * }else{ throw new CustomExceptionHandler("MLI name is null"); }
		 */
		if (message != null) {
			model.put("message", message);
		} else {
			model.put("message", "");
		}
		System.out.println(danAllForCgtmseCheckerService.getDataForAllDan());
		List<DanAllForCgtmseCheckerBean> list = prepareForAllData(danAllForCgtmseCheckerService
				.getDataForAllDan());
		if (list.size() > 0 || !list.isEmpty()) {
			model.put("dataList", list);

		} else {
			log.info("Throwing exception due to data not found in DanGenerateRpNumberForPaymentCheckerController class");
			model.put("dataList", "");

			// throw new CustomExceptionHandler("No data found");
		}
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
				"danForAllCgtmseChecker"));// Added by Say 31 Jan19
		model.put("repList",
				userActivityService.getReport("CGTMSECHECKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSECHECKER", "Guarantee_Maintenance"));
		model.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
		model.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
//		model.put("actList", userActivityService.getActivity("CGTMSECHECKER",
//				"User_Activity"));
		model.put("homePage", "cgtmseCheckerHome");
		log.info("Exit in getDanGenerateData() method in DanForAllCgtmseCheckerSideController class");

		return new ModelAndView("danForAllCgtmseChecker", model);

	}

	@RequestMapping(value = "/danAllforCgtmseCheckerApproval", method = RequestMethod.POST)
	public ModelAndView approveDanAllForCGTMSEChecker(
			@ModelAttribute("command") DanAllForCgtmseCheckerBean bean,
			BindingResult result, Model modelMsg, HttpSession session) {
		log.info("Enter in approveDanAllForCGTMSEChecker() method in DanForAllCgtmseCheckerSideController class");
		Map<String, Object> model = new HashMap<String, Object>();
		int count = 0;

		List<String> list = bean.getChcktbl();
		if (!list.isEmpty()) {
			count = danAllForCgtmseCheckerService
					.approveDanAllForCGTMSEChecker(list);
		}
		String message = "";
		if (count > 0) {
			message = "Successfully Approved";

		}
		/*
		 * model.put("actList", userActivityService.getActivity("NBFCCHECKER",
		 * "User_Activity")); model.put("homePage","nbfcCheckerHome"); log.info(
		 * "Exit in approveDanAllForCGTMSEChecker() method in DanForAllCgtmseCheckerSideController class"
		 * );
		 */
		return new ModelAndView(
				"redirect:/danForAllCgtmseChecker.html?message=" + message);

	}

	private List<DanAllForCgtmseCheckerBean> prepareForAllData(
			List<DanAllForCgtmseCheckerModel> danGenetateDataForAppoval) {
		// TODO Auto-generated method stub
		// DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		List<DanAllForCgtmseCheckerBean> list = new ArrayList<DanAllForCgtmseCheckerBean>();
		if (danGenetateDataForAppoval.size() > 0) {
			DanAllForCgtmseCheckerBean dgbean = null;
			for (DanAllForCgtmseCheckerModel dm : danGenetateDataForAppoval) {
				dgbean = new DanAllForCgtmseCheckerBean();
				dgbean.setDanId(dm.getDan_Id());
				dgbean.setAmount(BigDecimal
						.valueOf(Double.valueOf(dm.getAmount())).toBigDecimal()
						.toPlainString());
				dgbean.setMliName(dm.getMli_name());
				dgbean.setPortfolioNo(dm.getPortfolio_name());
				dgbean.setMliName(dm.getMli_name());

				list.add(dgbean);
			}
		}

		return list;
	}

	@ExceptionHandler(CustomExceptionHandler.class)
	public ModelAndView handleCustomException(CustomExceptionHandler ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();

		//Added by say 6 FEb-------------------------------------------------------------
		model1.put("adminlist",
				userActivityService.getActivity("CGTMSECHECKER", "System_Admin"));
		model1.put("guaranteelist",
				userActivityService.getActivity("CGTMSECHECKER", "Registration"));
		model1.put("applicationList",
				userActivityService.getActivity("CGTMSECHECKER", "Application_Processing"));
		model1.put("RPaymentList",
				userActivityService.getActivity("CGTMSECHECKER", "Receipt_Payments"));
		model1.put("repList",
				userActivityService.getReport("CGTMSECHECKER", "User_Report"));
		model1.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSECHECKER", "Guarantee_Maintenance"));
		model1.put("CList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Lodgement"));
		model1.put("CBMFList", userActivityService.getActivity("CGTMSECHECKER", "Claim_Bank_Mandate"));
		model1.put("homePage", "cgtmseCheckerHome");
		ModelAndView model = new ModelAndView("customException", model1);
		model.addObject("customException", ex.getMessage());
		return model;

	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();

		// model1.put("actList", userActivityService.getActivity("NBFCCHECKER",
		// "User_Activity"));
		// model1.put("homePage","nbfcCheckerHome");
		ModelAndView model = new ModelAndView("exception", model1);
		model.addObject("exception", ex.getCause());
		return model;

	}

	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView handleArithException(ArithmeticException ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();

		// model1.put("actList", userActivityService.getActivity("NBFCCHECKER",
		// "User_Activity"));
		// model1.put("homePage","nbfcCheckerHome");
		ModelAndView model = new ModelAndView("exception", model1);
		model.addObject("exception", ex.getMessage());
		return model;

	}

	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullException(NullPointerException ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();

		// model1.put("actList", userActivityService.getActivity("NBFCCHECKER",
		// "User_Activity"));
		// model1.put("homePage","nbfcCheckerHome");
		ModelAndView model = new ModelAndView("exception", model1);
		model.addObject("exception", "Data is null");
		return model;

	}

}
