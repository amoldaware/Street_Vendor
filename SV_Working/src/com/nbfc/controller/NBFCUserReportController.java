package com.nbfc.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.math.BigDecimal;
import com.nbfc.bean.DanCorrespondingDataListBeanCheckerBean;
import com.nbfc.bean.DanGenerateRpNumberForCheckerApprovalBean;
import com.nbfc.bean.NBFCUserReportBean;
import com.nbfc.model.DanCorrespondigDataModel;
import com.nbfc.model.DanCorressDataToRPNumberCheckerModel;
import com.nbfc.model.MLIDetails;
import com.nbfc.model.NBFCUserPortfolioDetails;
import com.nbfc.service.DanGenerateRpNumberForPaymentCheckerService;
import com.nbfc.service.NBFCUserReportService;
import com.nbfc.service.UserActivityService;
import com.nbfc.service.UserService;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class NBFCUserReportController {

	@Autowired
	NBFCUserReportService nbfcUserReportService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	DanGenerateRpNumberForPaymentCheckerService danGenerateRpNumberForPaymentCheckerService;
	@Autowired
	UserService employeeService;
	MLIDetails mliDetails;

	List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();

	static Logger log = Logger.getLogger(NBFCUserReportController.class
			.getName());
	ArrayList<List<String>> headerList = new ArrayList<List<String>>();
	ArrayList<List<String>> valueList = new ArrayList<List<String>>();

	/*
	 * @RequestMapping(value = "/userReport", method = RequestMethod.GET) public
	 * ModelAndView getUserReport(
	 * 
	 * @ModelAttribute("command") DanGenerateRpNumberForCheckerApprovalBean
	 * reportBean, BindingResult result, Model modelMsg, HttpSession session) {
	 * log.info(
	 * "Enter in getDanGenerateData() method in DanGenerateRpNumberForPaymentCheckerController class"
	 * ); Map<String, Object> model = new HashMap<String, Object>();
	 * 
	 * List<String> portFiloNameList = new ArrayList<String>(); Map<String,
	 * String> borDetailsList; String userId = (String)
	 * session.getAttribute("userId"); mliDetails =
	 * employeeService.getBNKId(userId); String
	 * mliId=mliDetails.getMem_bnk_id()+
	 * mliDetails.getMem_zne_id()+mliDetails.getMem_brn_id();
	 * 
	 * List<DanGenerateRpNumberForCheckerApprovalBean>list; if(userId!=null){
	 * //list=preparePortfolioDetailForUserReportBean(nbfcUserReportService.
	 * getPortfolioDetailForUserReport(userId));
	 * list=prepareDanGenerateDataListofBean
	 * (danGenerateRpNumberForPaymentCheckerService.getUserReportData(mliId));
	 * 
	 * }else{ throw new CustomExceptionHandler("User name is null"); }
	 * 
	 * if(list.size()>0||!list.isEmpty()){ model.put("dataList", list);
	 * 
	 * }else{ log.info(
	 * "Throwing exception due to data not found in DanGenerateRpNumberForPaymentCheckerController class"
	 * ); model.put("dataList", "");
	 * 
	 * //throw new CustomExceptionHandler("No data found"); }
	 * 
	 * model.put("actList", userActivityService.getActivity("NBFCCHECKER",
	 * "User_Activity")); System.out.println(
	 * "*********************************************************Reoport");
	 * model.put("repList", userActivityService.getReport("NBFCCHECKER",
	 * "User_Report")); model.put("homePage","nbfcCheckerHome"); log.info(
	 * "Exit in getDanGenerateData() method in DanGenerateRpNumberForPaymentCheckerController class"
	 * );
	 * 
	 * //return new ModelAndView("DanGeneratedRpNumberMLIChecker", model);
	 * return new ModelAndView("PortfolioDetailForUserReport", model);
	 * 
	 * }
	 */

	@RequestMapping(value = "/nbfcUploadedFileReport", method = RequestMethod.GET)
	public ModelAndView getUserReport(
			@ModelAttribute("command") DanGenerateRpNumberForCheckerApprovalBean reportBean,
			BindingResult result, Model modelMsg, HttpSession session) {
		log.info("Enter in getDanGenerateData() method in DanGenerateRpNumberForPaymentCheckerController class");
		Map<String, Object> model = new HashMap<String, Object>();
		headerList.clear();
		valueList.clear();
		String userId = (String) session.getAttribute("userId");

		rows = danGenerateRpNumberForPaymentCheckerService.createReport(userId);

		model.put("rows", rows);
		//added by say 6 feb-----------------------
		model.put("adminlist",
						userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
		model.put("guaranteelist",
						userActivityService.getActivity("NBFCCHECKER", "Registration"));
		model.put("applicationList",
						userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
		model.put("RPaymentList",
						userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName(
						"NBFCCHECKER", "nbfcUploadedFileReport"));// Added by say 25june19
		model.put("GMaintainlist", userActivityService.getActivity(
				"NBFCCHECKER", "Guarantee_Maintenance"));
		model.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
		model.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
		//end---------------------------------------------------------------------------------------	

		
//		model.put("actList",
//				userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
		System.out
				.println("*********************************************************Reoport");
		model.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		model.put("homePage", "nbfcCheckerHome");
		log.info("Exit in getDanGenerateData() method in DanGenerateRpNumberForPaymentCheckerController class");

		// return new ModelAndView("DanGeneratedRpNumberMLIChecker", model);
		return new ModelAndView("dynamicUserReport", model);

	}
	@RequestMapping(value = "/nbfcUploadedFileReportMK", method = RequestMethod.GET)
	public ModelAndView getUserMKReport(
			@ModelAttribute("command") DanGenerateRpNumberForCheckerApprovalBean reportBean,
			BindingResult result, Model modelMsg, HttpSession session) {
		log.info("Enter in getDanGenerateData() method in DanGenerateRpNumberForPaymentCheckerController class");
		Map<String, Object> model = new HashMap<String, Object>();
		headerList.clear();
		valueList.clear();
		String userId = (String) session.getAttribute("userId");

		rows = danGenerateRpNumberForPaymentCheckerService.createReportMK(userId);

		model.put("rows", rows);
		//added by say 6 feb-----------------------
		model.put("adminlist",
						userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		model.put("guaranteelist",
						userActivityService.getActivity("NBFCMAKER", "Registration"));
		model.put("applicationList",
						userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
		model.put("RPaymentList",
						userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName(
						"NBFCMAKER", "nbfcUploadedFileReport"));// Added by say 25june19
		model.put("GMaintainlist", userActivityService.getActivity(
				"NBFCMAKER", "Guarantee_Maintenance"));
		model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
		model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
		

		//end---------------------------------------------------------------------------------------	
		System.out
				.println("*********************************************************Reoport");
		model.put("repList",
				userActivityService.getReport("NBFCMAKER", "User_Report"));
		model.put("homePage", "nbfcMakerHome");
		log.info("Exit in getDanGenerateData() method in DanGenerateRpNumberForPaymentCheckerController class");

		// return new ModelAndView("DanGeneratedRpNumberMLIChecker", model);
		return new ModelAndView("dynamicUserReportMK", model);

	}
	
	@RequestMapping(value = "/uploadedFileDataMK", method = RequestMethod.GET)
	public ModelAndView getDanCorrespondingDataMK(
			@ModelAttribute(value = "command") DanCorrespondingDataListBeanCheckerBean bean,
			BindingResult result, String fileId, String danId,
			HttpSession session) {
		log.info("Enter from getDanCorrespondingData() method in DanGenerateRpNumberForPaymentCheckerController class");

		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println("fileId .................: " + fileId);
		rows = danGenerateRpNumberForPaymentCheckerService.getFileData(fileId);

		model.put("rows", rows);
		model.put("repList",
				userActivityService.getReport("NBFCMAKER", "User_Report"));
		//added by say 6 feb-----------------------
		model.put("adminlist",
						userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		model.put("guaranteelist",
						userActivityService.getActivity("NBFCMAKER", "Registration"));
		model.put("applicationList",
						userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
		model.put("RPaymentList",
						userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName(
						"NBFCMAKER", "nbfcUploadedFileReport"));// Added by say 25june19
		model.put("GMaintainlist", userActivityService.getActivity(
				"NBFCMAKER", "Guarantee_Maintenance"));
		model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
		model.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
		//end---------------------------------------------------------------------------------------	
//		model.put("actList",
//				userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		model.put("homePage", "nbfcMakerHome");
		log.info("Exit from getDanCorrespondingData() method in DanGenerateRpNumberForPaymentCheckerController class");

		return new ModelAndView("rpNumberCorrespondingDataMK", model);
	}
	@RequestMapping(value = "/uploadedFileData", method = RequestMethod.GET)
	public ModelAndView getDanCorrespondingData(
			@ModelAttribute(value = "command") DanCorrespondingDataListBeanCheckerBean bean,
			BindingResult result, String fileId, String danId,
			HttpSession session) {
		log.info("Enter from getDanCorrespondingData() method in DanGenerateRpNumberForPaymentCheckerController class");

		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println("fileId .................: " + fileId);
		rows = danGenerateRpNumberForPaymentCheckerService.getFileData(fileId);

		model.put("rows", rows);
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
		model.put("actName", userActivityService.getActivityName(
						"NBFCCHECKER", "nbfcUploadedFileReport"));// Added by say 25june19
		model.put("GMaintainlist", userActivityService.getActivity(
				"NBFCCHECKER", "Guarantee_Maintenance"));
		model.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
		model.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
		//end---------------------------------------------------------------------------------------	
//		model.put("actList",
//				userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
		model.put("homePage", "nbfcCheckerHome");
		log.info("Exit from getDanCorrespondingData() method in DanGenerateRpNumberForPaymentCheckerController class");

		return new ModelAndView("rpNumberCorrespondingData", model);
	}

	@RequestMapping(value = "/userReportdisburseNonDisburseData", method = RequestMethod.GET)
	public ModelAndView getDisburseNonDisburseData(
			@ModelAttribute(value = "command") DanCorrespondingDataListBeanCheckerBean bean,
			BindingResult result, String file_Id, String danId,
			String disbursedStatus, HttpSession session) {
		log.info("Enter from getDisburseNonDisburseData() method in DanGenerateRpNumberForPaymentCheckerController class");

		Map<String, Object> model = new HashMap<String, Object>();
		if (!file_Id.isEmpty() && !disbursedStatus.isEmpty()) {
			session.setAttribute("file_Id", file_Id);
			session.setAttribute("danId", danId);

			model.put("dataList1",
					danGenerateRpNumberForPaymentCheckerService
							.getDisburseNonDisburseData(file_Id, danId,
									disbursedStatus));

			// prepareDisburseNonDisburseList(rpNumber));
		}
		model.put("disStatus", disbursedStatus);
		//added by say 6 feb-----------------------
		model.put("adminlist",
						userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
		model.put("guaranteelist",
						userActivityService.getActivity("NBFCCHECKER", "Registration"));
		model.put("applicationList",
						userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
		model.put("RPaymentList",
						userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName(
						"NBFCCHECKER", "nbfcUploadedFileReport"));// Added by say 25june19
		model.put("GMaintainlist", userActivityService.getActivity(
				"NBFCCHECKER", "Guarantee_Maintenance"));
		model.put("CList", userActivityService.getActivity("NBFCCHECKER", "Claim_Lodgement"));
		model.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
		//end---------------------------------------------------------------------------------------	
//		model.put("actList",
//				userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
		model.put("repList",
				userActivityService.getReport("NBFCCHECKER", "User_Report"));
		model.put("homePage", "nbfcCheckerHome");
		log.info("Exit from getDisburseNonDisburseData() method in DanGenerateRpNumberForPaymentCheckerController class");

		return new ModelAndView("userReportdisburseNonDisburseData", model);

	}

	private List<DanGenerateRpNumberForCheckerApprovalBean> prepareDanGenerateDataListofBean(
			List<DanCorrespondigDataModel> modelData) {
		// TODO Auto-generated method stub
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		// List<DanGenerateRpNumberForPaymentCheckerModel> modelData=new
		// ArrayList<DanGenerateRpNumberForPaymentCheckerModel>();
		/*
		 * List<String>danId1=new ArrayList<String>();
		 * 
		 * for(DanCorrespondigDataModel dg:danId){
		 * 
		 * danList.add(dg.getDanId());
		 * 
		 * } Set pSet = new HashSet(danList); ArrayList uniquePList = new
		 * ArrayList(pSet);
		 */

		// if(!uniquePList.isEmpty()){
		// modelData=danGenerateRpNumberForPaymentCheckerService.getApprovedDanData(uniquePList);

		// }

		List<DanGenerateRpNumberForCheckerApprovalBean> list = new ArrayList<DanGenerateRpNumberForCheckerApprovalBean>();
		if (modelData.size() > 0) {
			DanGenerateRpNumberForCheckerApprovalBean dgbean = null;
			for (DanCorrespondigDataModel dm : modelData) {
				dgbean = new DanGenerateRpNumberForCheckerApprovalBean();
				dgbean.setRpNumber(dm.getRpNumber());
				// dgbean.setAmount(Long.parseLong(dm.getTotalFee()));
				dgbean.setAmount(BigDecimal
						.valueOf(Double.parseDouble(dm.getTotalFee()))
						.toBigDecimal().toPlainString());
				// dgbean.setDanId(dm.getDanId());
				if (dm.getDate() != null) {
					dgbean.setDate(df.format(dm.getDate()));
				} else {
					dgbean.setDate("");
					// throw new
					// CustomExceptionHandler("Generate Date is null");
				}
				String status = "";
				if (dm.getStatus().equals("DNMA")) {
					status = "Pending for Approval";
				}
				dgbean.setStatus(status);
				list.add(dgbean);
			}
		}

		return list;
	}

	private List<DanCorrespondingDataListBeanCheckerBean> prepareDanGenerateDataListCorresponingData(
			List<DanCorressDataToRPNumberCheckerModel> modelList) {
		// TODO Auto-generated method stub
		List<DanCorrespondingDataListBeanCheckerBean> list = new ArrayList<DanCorrespondingDataListBeanCheckerBean>();
		if (modelList.size() > 0) {
			DanCorrespondingDataListBeanCheckerBean dgbean = null;
			for (DanCorressDataToRPNumberCheckerModel dm : modelList) {
				dgbean = new DanCorrespondingDataListBeanCheckerBean();
				dgbean.setPortfolioName(dm.getPortfolioName());
				dgbean.setFileName(dm.getFili_Id());
				dgbean.setTotalFee(dm.getTotalFee());
				// dgbean.setDanId(dm.getDanId());
				// dgbean.setDanId(dm.getDanId());

				list.add(dgbean);
			}
		}

		return list;
	}
}
