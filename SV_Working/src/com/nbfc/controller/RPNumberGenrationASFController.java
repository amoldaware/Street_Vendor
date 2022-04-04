package com.nbfc.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.math.BigDecimal;
import com.nbfc.bean.BorrowerDetailsBean;
import com.nbfc.bean.DANAllocationBean;
import com.nbfc.bean.PortfolioBatchBean;
import com.nbfc.bean.RPNumberGenrationBean;
import com.nbfc.bean.UserBean;
import com.nbfc.model.DANAllocation;
import com.nbfc.model.DANAllocationFee;
import com.nbfc.model.DanAllocationForASFNbfcMakerUsingVWModel;
import com.nbfc.model.DanAllocationForNbfcMakerUsingVWModel;
import com.nbfc.model.MLICGFeeDetails;
import com.nbfc.model.MLIDetails;
import com.nbfc.model.MLIName;
import com.nbfc.model.PortfolioBatchApp;
import com.nbfc.model.User;
import com.nbfc.service.DANAllocationService;
import com.nbfc.service.PortfolioInfoService;
import com.nbfc.service.UserActivityService;
import com.nbfc.service.UserService;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class RPNumberGenrationASFController {

	@Autowired
	UserActivityService userActivityService;
	@Autowired
	DANAllocationService danAllocationService;
	@Autowired
	UserService employeeService;
	@Autowired
	PortfolioInfoService portfolioInfoService;
	static Logger log = Logger.getLogger(NBFCController.class.getName());
	List<DANAllocationBean> danDetailList = new ArrayList<DANAllocationBean>();
	List<BorrowerDetailsBean> borrowerDetailList = new ArrayList<BorrowerDetailsBean>();
	List<RPNumberGenrationBean> rpNumberDetailList = new ArrayList<RPNumberGenrationBean>();
	List<DANAllocationFee> danlist = new ArrayList<DANAllocationFee>();
	double totalOfAmount;
	String rpNumber = null;
	String virtualACNo = null;
	String rpNumber1 = null;
	static float tatalFee = 0f;

	MLIDetails mliDetails;
	MLIName mliName;
	String value;
	List<String> portFiloNameList = new ArrayList<String>();
	List<Float> portFiloRateList;
	List<String> fileNameList;
	List<String> portFoliNameList = new ArrayList<String>();
	List<String> danIDList = new ArrayList<String>();
	List<String> fileIdList = new ArrayList<String>();
	List<String> rpNUmberList = new ArrayList<String>();
	List<String> amountList = new ArrayList<String>();
	List<String> danList = new ArrayList<String>();
	List<String> rpNrList = new ArrayList<String>();
	List<DanAllocationForASFNbfcMakerUsingVWModel> allocationList = new ArrayList<DanAllocationForASFNbfcMakerUsingVWModel>();

	Map<String, String> borDetailsList;
	List<String> danLIst = new ArrayList<String>();
	private static final DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	@RequestMapping(value = "rpNumberGenrationASF", method = RequestMethod.GET)
	public ModelAndView submitForConfirmASF(
			@ModelAttribute(value = "command") DANAllocationBean loginBean,
			BindingResult result, String totalOfAmo, Model model,
			HttpSession session) {
		log.info("adminHome***************************************************************************************************************"
				+ session.getAttribute("userId"));
		String userId=session.getAttribute("userId").toString();
		DANAllocationBean danDean = new DANAllocationBean();
		borrowerDetailList.clear();
		List<String> danList = loginBean.getCheckbtn();
		danDean.setDanNumber(danList);
		// double dadsa=(Double) session.getAttribute("totalOfAmount");
		allocationList = new ArrayList<DanAllocationForASFNbfcMakerUsingVWModel>();
		for (String dt : danList) {
			String[] arr = dt.split("@");
			setDataToBean(arr);

		}

		DANAllocationBean danAllocation = null;
		if (!allocationList.isEmpty()) {
			if(userId!=null){
				danAllocation = danAllocationService.getRpNumberDataASF(allocationList,userId);
			}
			
		}
		Map<String, Object> modelAct = new HashMap<String, Object>();

		// danAllocationService.addDANAllocatioDetails(prepareDanAllocationEntity(danAllocationService.getCGAmoutDetailsList(rpNrList,
		// "NCDI"),danAllocationBean,userID));

		Date date = new Date();
		String currentDate = sdf.format(date);
		if (danAllocation != null) {
			danAllocation.setCurrentDate(currentDate);
			modelAct.put("rpNumberBean", danAllocation);
			modelAct.put("successMessage", "Successfully sent for Approval");
		} else {

			modelAct.put("failedMessage",
					"RP Number not Generated,Please contact to CGTMST support Team.");

		}
		//added by say 6 feb-----------------------
		modelAct.put("adminlist",
				userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		modelAct.put("guaranteelist",
				userActivityService.getActivity("NBFCMAKER", "Registration"));
		modelAct.put("applicationList",
				userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
		modelAct.put("RPaymentList",
				userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
		modelAct.put("actName", userActivityService.getActivityName(
				"NBFCMAKER", "danAllocation"));// Added by say 25june19
		modelAct.put("repList",
				userActivityService.getReport("NBFCMAKER", "User_Report"));	
		modelAct.put("GMaintainlist", userActivityService.getActivity(
				"NBFCMAKER", "Guarantee_Maintenance"));
		modelAct.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
		modelAct.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
//end---------------------------------------------------------------------------------------	
//		modelAct.put("actList",
//				userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		modelAct.put("successMessage", "Successfully sent for Approval");

		modelAct.put("homePage", "nbfcMakerHome");
		return new ModelAndView("rpNumberGenrationPageASF", modelAct);
	}

	private void setDataToBean(String[] arr) {
		// TODO Auto-generated method stub

		if (arr.length > 0) {
			DanAllocationForASFNbfcMakerUsingVWModel db = new DanAllocationForASFNbfcMakerUsingVWModel();
			db.setDanId(arr[0]);
			db.setPortfolioRate(arr[1]);
			db.setAmount(arr[2]);
			db.setPortfolioName(arr[3]);
			db.setFileId(arr[4]);
			db.setMliId(arr[5]);
			allocationList.add(db);
		}
	}

	@RequestMapping(value = "genrateRPNumberVirtualACASF", method = RequestMethod.GET)
	public ModelAndView submitRPForConfirmASF(
			@ModelAttribute("command") DANAllocationBean loginBean,
			BindingResult result, Model model, HttpSession session) {
		log.info("adminHome***************************************************************************************************************"
				+ session.getAttribute("userId"));

		String userId = (String) session.getAttribute("userId");
		mliDetails = employeeService.getBNKId(userId);
		mliName = employeeService.getMLIName(mliDetails.getMem_bnk_id());
		portFoliNameList.clear();
		danIDList.clear();
		fileIdList.clear();
		borDetailsList = portfolioInfoService.getDetailsForMLI(mliName
				.getMliLName());
		for (Map.Entry<String, String> entry : borDetailsList.entrySet()) {
			value = entry.getValue();
			portFiloNameList.add(entry.getValue());
			System.out.println(value);
		}
		List<MLICGFeeDetails> borworDetailsList = danAllocationService
				.getPortFiliDetails(portFiloNameList);

		for (MLICGFeeDetails fasd : borworDetailsList) {

			System.out.println(fasd.getPortfolio_Name() + "  "
					+ fasd.getFILE_ID() + " " + fasd.getDAN_ID());
			portFoliNameList.add(fasd.getPortfolio_Name());
			danIDList.add(fasd.getDAN_ID());
			fileIdList.add(fasd.getFILE_ID());

		}
		Set pSet = new HashSet(portFoliNameList);
		ArrayList uniquePList = new ArrayList(pSet);
		Set dSet = new HashSet(danIDList);
		ArrayList uniqueDList = new ArrayList(dSet);
		Set fSet = new HashSet(fileIdList);
		ArrayList uniqueFList = new ArrayList(fSet);

		System.out.println(uniquePList + " " + uniqueDList + " " + uniqueFList);

		danlist = danAllocationService.getApyIDAmoutDetailsList(uniqueDList,
				"NCDA");
		preparePaymentListBean(danlist);
		rpNumberDetailList.clear();
		Map<String, Object> modelAct = new HashMap<String, Object>();
		Date date = new Date();
		String currentDate = sdf.format(date);
		System.out.println(currentDate);
		totalOfAmount = 26500;
		rpNumber1 = "RP-" + "0001" + currentDate;
		RPNumberGenrationBean loginBean0 = new RPNumberGenrationBean(rpNumber1,
				"856987458965896", 56568, currentDate, "Approve");
		RPNumberGenrationBean loginBean1 = new RPNumberGenrationBean(rpNumber1,
				"856987458965896", 56569, currentDate, "Pending for approval");
		RPNumberGenrationBean loginBean2 = new RPNumberGenrationBean(rpNumber1,
				"856987458965896", 85698, currentDate, "Approve");

		totalOfAmount = loginBean0.getAmount() + loginBean2.getAmount()
				+ loginBean1.getAmount();
		System.out.println(totalOfAmount);
		rpNumberDetailList.add(loginBean0);
		rpNumberDetailList.add(loginBean1);
		rpNumberDetailList.add(loginBean2);

		modelAct.put("totalOfAmount", totalOfAmount);
		modelAct.put("danDetailList", preparePaymentListBean(danlist));
		//added by say 6 feb-----------------------
		modelAct.put("adminlist",
				userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		modelAct.put("guaranteelist",
				userActivityService.getActivity("NBFCMAKER", "Registration"));
		modelAct.put("applicationList",
				userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
		modelAct.put("RPaymentList",
				userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
		modelAct.put("actName", userActivityService.getActivityName(
				"NBFCMAKER", "danAllocation"));// Added by say 25june19
		modelAct.put("repList",
				userActivityService.getReport("NBFCMAKER", "User_Report"));	
		modelAct.put("GMaintainlist", userActivityService.getActivity(
				"NBFCMAKER", "Guarantee_Maintenance"));
		modelAct.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
		modelAct.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
//end---------------------------------------------------------------------------------------	
//		modelAct.put("actList",
//				userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		modelAct.put("homePage", "nbfcMakerHome");
		return new ModelAndView("genrateRPNumberVirtualACPage", modelAct);
	}

/*	@RequestMapping(value = "editRPNUmberASF", method = RequestMethod.GET)
	public ModelAndView editRPNumberASF(
			@ModelAttribute("command") BorrowerDetailsBean loginBean,
			BindingResult result, Model model, HttpSession session) {
		log.info("adminHome***************************************************************************************************************"
				+ session.getAttribute("userId"));
		borrowerDetailList.clear();
		Map<String, Object> modelAct = new HashMap<String, Object>();
		BorrowerDetailsBean loginBean0 = new BorrowerDetailsBean(
				"REL/2017-2018/Q2/12", "Ashok Int. Pvt.", 400000, "ASD12456",
				"12-Dec-2017", "10-Dec-2018", 5420);
		BorrowerDetailsBean loginBean1 = new BorrowerDetailsBean(
				"REL/2017-2018/Q2/13", "INDIA Int. Pvt.", 400000, "ASD12445",
				"11-Dec-2017", "12-Dec-2018", 5000);
		BorrowerDetailsBean loginBean2 = new BorrowerDetailsBean(
				"REL/2017-2018/Q2/14", "Satish Int. Pvt.", 400000, "ASD12786",
				"02-Dec-2017", "01-Dec-2018", 5400);
		BorrowerDetailsBean loginBean3 = new BorrowerDetailsBean(
				"REL/2017-2018/Q2/15", "Mum Int. Pvt.", 400000, "ASD12496",
				"12-Sep-2017", "15-Dec-2018", 3333);
		BorrowerDetailsBean loginBean4 = new BorrowerDetailsBean(
				"REL/2017-2018/Q2/16", "Jindal Int. Pvt.", 400000, "ASD00456",
				"12-Oct-2017", "22-Dec-2018", 5580);
		BorrowerDetailsBean loginBean5 = new BorrowerDetailsBean(
				"REL/2017-2018/Q2/17", "Del Int. Pvt.", 400000, "ASD12400",
				"12-Jan-2017", "13-Dec-2018", 7890);
		totalOfAmount = loginBean0.getCalculatedAmount()
				+ loginBean2.getCalculatedAmount()
				+ loginBean3.getCalculatedAmount()
				+ loginBean4.getCalculatedAmount()
				+ loginBean5.getCalculatedAmount();
		System.out.println(totalOfAmount);
		borrowerDetailList.add(loginBean0);
		borrowerDetailList.add(loginBean1);
		borrowerDetailList.add(loginBean2);
		borrowerDetailList.add(loginBean3);
		borrowerDetailList.add(loginBean4);
		borrowerDetailList.add(loginBean5);
		modelAct.put("totalOfAmount", totalOfAmount);
		modelAct.put("danDetailList", borrowerDetailList);
		//added by say 6 feb-----------------------
		modelAct.put("adminlist",
				userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		modelAct.put("guaranteelist",
				userActivityService.getActivity("NBFCMAKER", "Registration"));
		modelAct.put("applicationList",
				userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
		modelAct.put("RPaymentList",
				userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
		modelAct.put("actName", userActivityService.getActivityName(
				"NBFCMAKER", "danAllocation"));// Added by say 25june19
		modelAct.put("repList",
				userActivityService.getReport("NBFCMAKER", "User_Report"));	
		modelAct.put("GMaintainlist", userActivityService.getActivity(
				"NBFCMAKER", "Guarantee_Maintenance"));
//end---------------------------------------------------------------------------------------	
//		modelAct.put("actList",
//				userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		modelAct.put("homePage", "nbfcMakerHome");
		return new ModelAndView("editRPNUmberPage", modelAct);
	}
*/
	@RequestMapping(value = "paymentInscateASF", method = RequestMethod.GET)
	public ModelAndView paymentInscateASF(
			@ModelAttribute("command") DANAllocationBean loginBean,
			BindingResult result, Model model, HttpSession session) {
		log.info("adminHome***************************************************************************************************************"
				+ session.getAttribute("userId"));
		borrowerDetailList.clear();
		tatalFee = 0;
		Map<String, Object> modelAct = new HashMap<String, Object>();

		List<String> Fileid = loginBean.getChcktbl();
		for (String value : Fileid) {

			String[] arrOfStr = value.split("/");

			for (String a : arrOfStr) {
				rpNUmberList.add(arrOfStr[0]);
				amountList.add(arrOfStr[1]);
				danList.add(arrOfStr[2]);
			}

		}

		System.out.println(rpNUmberList + " " + amountList);
		Set pSet = new HashSet(rpNUmberList);
		ArrayList uniqRPNumberList = new ArrayList(pSet);
		Set dSet = new HashSet(amountList);
		ArrayList<String> AmountUniqList = new ArrayList<String>(dSet);
		Set qSet = new HashSet(danList);
		ArrayList uniqDanList = new ArrayList(qSet);

		for (String value : AmountUniqList) {

			tatalFee = tatalFee + Float.parseFloat(value);
		}

		Date date = new Date();
		String currentDate = sdf.format(date);
		System.out.println(currentDate);
		totalOfAmount = 26500;
		rpNumber = "RP-" + "0001" + currentDate;
		String virtACNumber = "18888"
				+ rpNumber.replaceAll("[^a-zA-Z0-9 ]", "").substring(2);
		// preparePayListBean(uniqRPNumberList,uniqDanList,String.valueOf(tatalFee),virtACNumber,currentDate);
		virtualACNo = "856987458965896";
		modelAct.put("totalOfAmount", totalOfAmount);
		modelAct.put("rpNumber", rpNumber);
		modelAct.put("currentDate", currentDate);
		modelAct.put("virtualACNo", virtualACNo);
		modelAct.put(
				"rpNumberList",
				preparePayListBean(uniqRPNumberList, uniqDanList,
						String.valueOf(tatalFee), virtACNumber, currentDate));
		//added by say 6 feb-----------------------
		modelAct.put("adminlist",
				userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		modelAct.put("guaranteelist",
				userActivityService.getActivity("NBFCMAKER", "Registration"));
		modelAct.put("applicationList",
				userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
		modelAct.put("RPaymentList",
				userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
		modelAct.put("actName", userActivityService.getActivityName(
				"NBFCMAKER", "danAllocation"));// Added by say 25june19
		modelAct.put("repList",
				userActivityService.getReport("NBFCMAKER", "User_Report"));	
		modelAct.put("GMaintainlist", userActivityService.getActivity(
				"NBFCMAKER", "Guarantee_Maintenance"));
		modelAct.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
		modelAct.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
//end---------------------------------------------------------------------------------------	
//		modelAct.put("actList",
//				userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		modelAct.put("homePage", "nbfcMakerHome");
		return new ModelAndView("paymentInscatePage", modelAct);
	}

	@RequestMapping(value = "rpNumberConfirmationASF", method = RequestMethod.GET)
	public ModelAndView rpNumberConfirmationASF(
			@ModelAttribute("command") DANAllocationBean danAllocationBean,
			BindingResult result, Model model, HttpSession session)
			throws ParseException {
		log.info("adminHome***************************************************************************************************************"
				+ session.getAttribute("userId"));
		String userID = (String) session.getAttribute("userId");
		borrowerDetailList.clear();

		rpNrList.clear();

		List<String> danLIst = danAllocationBean.getDanNumber();

		for (String value : danLIst) {
			String val0 = value.replace("[", "");
			String val1 = val0.replace("]", "");

			rpNrList.add(val1);
		}

		/* ArrayList uniqueDList = new ArrayList(danLIst); */
		// List<DANAllocationFee>
		// listDan=danAllocationService.getCGAmoutDetailsList(rpNrList, "NCDI");

		// List<DANAllocationFee> list
		// =prepareDanAllocationEntity(danAllocationService.getCGAmoutDetailsList(rpNrList,
		// "CMDI"),danAllocationBean,userID);

		danAllocationService.addDANAllocatioDetails(prepareDanAllocationEntity(
				danAllocationService.getCGAmoutDetailsList(rpNrList, "NCDI"),
				danAllocationBean, userID));
		System.out.println(rpNrList);
		Map<String, Object> modelAct = new HashMap<String, Object>();

		modelAct.put("message", "Successfully Sent for Approval");

		//added by say 6 feb-----------------------
		modelAct.put("adminlist",
				userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		modelAct.put("guaranteelist",
				userActivityService.getActivity("NBFCMAKER", "Registration"));
		modelAct.put("applicationList",
				userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
		modelAct.put("RPaymentList",
				userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
		modelAct.put("actName", userActivityService.getActivityName(
				"NBFCMAKER", "danAllocation"));// Added by say 25june19
		modelAct.put("repList",
				userActivityService.getReport("NBFCMAKER", "User_Report"));	
		modelAct.put("GMaintainlist", userActivityService.getActivity(
				"NBFCMAKER", "Guarantee_Maintenance"));
		modelAct.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
		modelAct.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
//end---------------------------------------------------------------------------------------	
//		modelAct.put("actList",
//				userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		modelAct.put("homePage", "nbfcMakerHome");
		return new ModelAndView("rpNumberConfirmationPageASF", modelAct);
	}

	@RequestMapping(value = "rpNumberConfirmationScreenASF", method = RequestMethod.GET)
	public ModelAndView rpNumberConfirmationScreenASF(
			@ModelAttribute("command") DANAllocationBean danAllocationBean,
			BindingResult result, Model model, HttpSession session)
			throws ParseException {
		log.info("adminHome***************************************************************************************************************"
				+ session.getAttribute("userId"));
		String userID = (String) session.getAttribute("userId");
		borrowerDetailList.clear();

		rpNrList.clear();

		List<String> danLIst = danAllocationBean.getDanIDList();

		for (String value : danLIst) {
			String val0 = value.replace("[", "");
			String val1 = val0.replace("]", "");

			rpNrList.add(val1);
		}

		/* ArrayList uniqueDList = new ArrayList(danLIst); */
		// List<DANAllocationFee>
		// listDan=danAllocationService.getCGAmoutDetailsList(rpNrList, "NCDI");

		// List<DANAllocationFee> list
		// =prepareDanAllocationEntity(danAllocationService.getCGAmoutDetailsList(rpNrList,
		// "CMDI"),danAllocationBean,userID);

		danAllocationService
				.addDANAllocatioDetails(prepareDanForRPNOAllocationEntity(
						danAllocationService.getCGAmoutDetailsList(rpNrList,
								"NCDA"), danAllocationBean, userID));

		System.out.println(rpNrList);
		Map<String, Object> modelAct = new HashMap<String, Object>();

		modelAct.put("message", "Successfully Sent for Approval");

		//added by say 6 feb-----------------------
		modelAct.put("adminlist",
				userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		modelAct.put("guaranteelist",
				userActivityService.getActivity("NBFCMAKER", "Registration"));
		modelAct.put("applicationList",
				userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
		modelAct.put("RPaymentList",
				userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
		modelAct.put("actName", userActivityService.getActivityName(
				"NBFCMAKER", "danAllocation"));// Added by say 25june19
		modelAct.put("repList",
				userActivityService.getReport("NBFCMAKER", "User_Report"));	
		modelAct.put("GMaintainlist", userActivityService.getActivity(
				"NBFCMAKER", "Guarantee_Maintenance"));
		modelAct.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
		modelAct.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
//end---------------------------------------------------------------------------------------	
//		modelAct.put("actList",
//				userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		modelAct.put("homePage", "nbfcMakerHome");
		return new ModelAndView("rpNumberConfirmationPageASF", modelAct);
	}

	private List<DANAllocationFee> prepareDanAllocationEntity(
			List<DANAllocationFee> danAllocationEntityHo,
			DANAllocationBean danAllocationBean, String userID)
			throws ParseException {
		List<DANAllocationFee> danAllocationEntitySo = null;
		if (danAllocationEntityHo != null && !danAllocationEntityHo.isEmpty()) {
			danAllocationEntitySo = new ArrayList<DANAllocationFee>();
			DANAllocationFee danAllocationEntity = null;
			for (DANAllocationFee danEntity : danAllocationEntityHo) {
				danAllocationEntity = new DANAllocationFee();

				danAllocationEntity.setAPPROVAL_STATUS("NMDA");

				danAllocationEntity.setCGPAN(danEntity.getCGPAN());
				danAllocationEntity.setCGST_AMT(danEntity.getCGST_AMT());
				danAllocationEntity.setCGST_RATE(danEntity.getCGST_RATE());
				System.out.println();
				danAllocationEntity.setCLAIM_RISK_RATE(danEntity
						.getCLAIM_RISK_RATE());

				danAllocationEntity.setDAN_ID(danEntity.getDAN_ID());
				danAllocationEntity.setDCI_ALLOCATION_BY(danEntity
						.getDCI_ALLOCATION_BY());
				danAllocationEntity.setDCI_ALLOCATION_DT(danEntity
						.getDCI_ALLOCATION_DT());
				danAllocationEntity.setDCI_ALLOCATION_FLAG(danEntity
						.getDCI_ALLOCATION_FLAG());
				danAllocationEntity.setDCI_ALLOCATION_VALID_FLAG(danEntity
						.getDCI_ALLOCATION_VALID_FLAG());
				danAllocationEntity.setDCI_AMOUNT_CANCELLED(danEntity
						.getDCI_AMOUNT_CANCELLED());
				danAllocationEntity.setDCI_AMOUNT_RAISED(danEntity
						.getDCI_AMOUNT_RAISED());
				danAllocationEntity.setDCI_APPROPRIATION_BY(userID);
				Date date1 = new SimpleDateFormat("dd-MM-yyyy")
						.parse(danAllocationBean.getDateOfRPGenration());

				if (date1 != null) {
					danAllocationEntity.setDCI_APPROPRIATION_DT(date1);

				}
				// danAllocationEntity.setDCI_APPROPRIATION_DT(date1);

				danAllocationEntity.setDCI_APPROPRIATION_FLAG(danEntity
						.getDCI_APPROPRIATION_FLAG());
				danAllocationEntity
						.setDCI_BASE_AMT(danEntity.getDCI_BASE_AMT());
				danAllocationEntity.setDCI_CLAIM_RECOVERY_AMT(danEntity
						.getDCI_CLAIM_RECOVERY_AMT());
				danAllocationEntity
						.setDCI_DUE_DATE(danEntity.getDCI_DUE_DATE());
				danAllocationEntity.setDCI_ECESS_AMT(danEntity
						.getDCI_ECESS_AMT());
				danAllocationEntity.setDCI_GUARANTEE_START_DT(danEntity
						.getDCI_GUARANTEE_START_DT());
				danAllocationEntity.setDCI_HECESS_AMT(danEntity
						.getDCI_HECESS_AMT());
				danAllocationEntity.setDCI_KKALYANCESS_AMT(danEntity
						.getDCI_KKALYANCESS_AMT());
				danAllocationEntity.setDCI_NEW_DAN_ID(danEntity
						.getDCI_NEW_DAN_ID());
				danAllocationEntity.setDCI_PENALTY(danEntity.getDCI_PENALTY());

				danAllocationEntity.setDCI_REMARKS(danEntity.getDCI_REMARKS());
				danAllocationEntity.setDCI_STANDARD_RATE(danEntity
						.getDCI_STANDARD_RATE());
				danAllocationEntity
						.setDCI_STAX_AMT(danEntity.getDCI_STAX_AMT());
				danAllocationEntity.setDCI_SWBHCESS_AMT(danEntity
						.getDCI_SWBHCESS_AMT());
				danAllocationEntity.setEST_FEE_AMT(danEntity.getEST_FEE_AMT());
				danAllocationEntity.setFINAL_RATE(danEntity.getFINAL_RATE());
				danAllocationEntity.setIGST_AMT(danEntity.getIGST_AMT());
				danAllocationEntity.setIGST_RATE(danEntity.getIGST_RATE());
				danAllocationEntity.setNPA_RISK_RATE(danEntity
						.getNPA_RISK_RATE());
				danAllocationEntity.setPAY_ID(danAllocationBean.getRpNumber());
				danAllocationEntity.setREMARK(danEntity.getREMARK());

				danAllocationEntity.setREMARK(danEntity.getREMARK());
				danAllocationEntity.setSGST_AMT(danEntity.getSGST_AMT());
				danAllocationEntity.setSGST_RATE(danEntity.getSGST_RATE());

				danAllocationEntitySo.add(danAllocationEntity);
			}
		}
		return danAllocationEntitySo;
	}

	private List<DANAllocationFee> prepareDanForRPNOAllocationEntity(
			List<DANAllocationFee> danAllocationEntityHo,
			DANAllocationBean danAllocationBean, String userID)
			throws ParseException {
		List<DANAllocationFee> danAllocationEntitySo = null;
		if (danAllocationEntityHo != null && !danAllocationEntityHo.isEmpty()) {
			danAllocationEntitySo = new ArrayList<DANAllocationFee>();
			DANAllocationFee danAllocationEntity = null;
			for (DANAllocationFee danEntity : danAllocationEntityHo) {
				danAllocationEntity = new DANAllocationFee();

				danAllocationEntity.setAPPROVAL_STATUS("NMPI");

				danAllocationEntity.setCGPAN(danEntity.getCGPAN());
				danAllocationEntity.setCGST_AMT(danEntity.getCGST_AMT());
				danAllocationEntity.setCGST_RATE(danEntity.getCGST_RATE());
				System.out.println();
				danAllocationEntity.setCLAIM_RISK_RATE(danEntity
						.getCLAIM_RISK_RATE());

				danAllocationEntity.setDAN_ID(danEntity.getDAN_ID());
				danAllocationEntity.setDCI_ALLOCATION_BY(danEntity
						.getDCI_ALLOCATION_BY());
				danAllocationEntity.setDCI_ALLOCATION_DT(danEntity
						.getDCI_ALLOCATION_DT());
				danAllocationEntity.setDCI_ALLOCATION_FLAG(danEntity
						.getDCI_ALLOCATION_FLAG());
				danAllocationEntity.setDCI_ALLOCATION_VALID_FLAG(danEntity
						.getDCI_ALLOCATION_VALID_FLAG());
				danAllocationEntity.setDCI_AMOUNT_CANCELLED(danEntity
						.getDCI_AMOUNT_CANCELLED());
				danAllocationEntity.setDCI_AMOUNT_RAISED(danEntity
						.getDCI_AMOUNT_RAISED());
				danAllocationEntity.setDCI_APPROPRIATION_BY(userID);
				Date date1 = new SimpleDateFormat("dd-MM-yyyy")
						.parse(danAllocationBean.getCurrentDate());

				if (date1 != null) {
					danAllocationEntity.setDCI_APPROPRIATION_DT(date1);

				}
				// danAllocationEntity.setDCI_APPROPRIATION_DT(date1);

				danAllocationEntity.setDCI_APPROPRIATION_FLAG(danEntity
						.getDCI_APPROPRIATION_FLAG());
				danAllocationEntity
						.setDCI_BASE_AMT(danEntity.getDCI_BASE_AMT());
				danAllocationEntity.setDCI_CLAIM_RECOVERY_AMT(danEntity
						.getDCI_CLAIM_RECOVERY_AMT());
				danAllocationEntity
						.setDCI_DUE_DATE(danEntity.getDCI_DUE_DATE());
				danAllocationEntity.setDCI_ECESS_AMT(danEntity
						.getDCI_ECESS_AMT());
				danAllocationEntity.setDCI_GUARANTEE_START_DT(danEntity
						.getDCI_GUARANTEE_START_DT());
				danAllocationEntity.setDCI_HECESS_AMT(danEntity
						.getDCI_HECESS_AMT());
				danAllocationEntity.setDCI_KKALYANCESS_AMT(danEntity
						.getDCI_KKALYANCESS_AMT());
				danAllocationEntity.setDCI_NEW_DAN_ID(danEntity
						.getDCI_NEW_DAN_ID());
				danAllocationEntity.setDCI_PENALTY(danEntity.getDCI_PENALTY());

				danAllocationEntity.setDCI_REMARKS(danEntity.getDCI_REMARKS());
				danAllocationEntity.setDCI_STANDARD_RATE(danEntity
						.getDCI_STANDARD_RATE());
				danAllocationEntity
						.setDCI_STAX_AMT(danEntity.getDCI_STAX_AMT());
				danAllocationEntity.setDCI_SWBHCESS_AMT(danEntity
						.getDCI_SWBHCESS_AMT());
				danAllocationEntity.setEST_FEE_AMT(danEntity.getEST_FEE_AMT());
				danAllocationEntity.setFINAL_RATE(danEntity.getFINAL_RATE());
				danAllocationEntity.setIGST_AMT(danEntity.getIGST_AMT());
				danAllocationEntity.setIGST_RATE(danEntity.getIGST_RATE());
				danAllocationEntity.setNPA_RISK_RATE(danEntity
						.getNPA_RISK_RATE());
				danAllocationEntity.setPAY_ID(danAllocationBean.getRpNumber());
				danAllocationEntity.setREMARK(danEntity.getREMARK());

				danAllocationEntity.setREMARK(danEntity.getREMARK());
				danAllocationEntity.setSGST_AMT(danEntity.getSGST_AMT());
				danAllocationEntity.setSGST_RATE(danEntity.getSGST_RATE());

				danAllocationEntitySo.add(danAllocationEntity);
			}
		}
		return danAllocationEntitySo;
	}

	private DANAllocationBean preparePayListBean(List<String> payIdList,
			List<String> danList, String amount, String vrNo, String date) {
		DANAllocationBean danBean = new DANAllocationBean();
		if (payIdList != null && !payIdList.isEmpty() && danList != null
				&& !danList.isEmpty()) {

			danBean.setDanIDList(danList);
			danBean.setCgPanList(payIdList);
			danBean.setVirtualAccountNumber(vrNo);
			danBean.setTotalAmount(amount);
			danBean.setCurrentDate(date);

		}
		return danBean;
	}

	private List<DANAllocationBean> preparePaymentListBean(
			List<DANAllocationFee> danAllocation) {
		List<DANAllocationBean> danBeans = null;
		if (danAllocation != null && !danAllocation.isEmpty()) {
			danBeans = new ArrayList<DANAllocationBean>();
			DANAllocationBean danBean = null;
			for (DANAllocationFee danEntity : danAllocation) {
				danBean = new DANAllocationBean();
				try {
					danBean.setRpNumber(danEntity.getPAY_ID());
					danBean.setTotalFee(BigDecimal.valueOf(danEntity.getDCI_AMOUNT_RAISED()).toBigDecimal().toPlainString());
					DateFormat df3 = new SimpleDateFormat("dd-MMM-yyyy");

					danBean.setDateOfRPGenration(df3.format(danEntity
							.getDCI_APPROPRIATION_DT()));
					danBean.setStatus(danEntity.getAPPROVAL_STATUS());
					danBean.setDAN_ID(danEntity.getDAN_ID());
					danBeans.add(danBean);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return danBeans;
	}

	/*
	 * private List<DANAllocationBean> prepareDANListOfBean(
	 * DANAllocationService danAllocationService, List<String> danIDList,
	 * List<String> portfolioNameList, List<String> fileIDList) {
	 * List<DANAllocationBean> beans = null;
	 * 
	 * beans = new ArrayList<DANAllocationBean>(); DANAllocationBean bean =
	 * null;
	 * 
	 * for (int i = 0, j = 0, k = 0; i < danIDList.size() && j <
	 * portfolioNameList.size() && k < fileIDList.size(); ++i, ++j, ++k) { try {
	 * bean = new DANAllocationBean(); System.out.println(danIDList.get(i));
	 * System.out.println(portfolioNameList.get(j));
	 * System.out.println(fileIDList.get(k)); // do something here
	 * 
	 * bean.setPortfoliName(portfolioNameList.get(j));
	 * bean.setFileName(fileIDList.get(k)); bean.setDAN_ID(danIDList.get(i)); //
	 * bean.setPortfolioRate(dAllocation.getINTEREST_RATE()); DANAllocationFee
	 * danAllocationfo = danAllocationService
	 * .getCGAmoutDetails(danIDList.get(j), "CMDI");
	 * if(danAllocationfo.getDCI_AMOUNT_RAISED()!=0){
	 * bean.setTotalFee(danAllocationfo.getDCI_AMOUNT_RAISED());
	 * 
	 * }else{ bean.setTotalFee(0);
	 * 
	 * } if(danAllocationfo.getDCI_STANDARD_RATE()!=0){
	 * bean.setPortfolioRate(Double
	 * .toString(danAllocationfo.getDCI_STANDARD_RATE()));
	 * 
	 * }else{
	 * 
	 * bean.setPortfolioRate("0");
	 * 
	 * }
	 * 
	 * beans.add(bean); } catch (NullPointerException e) { e.printStackTrace();
	 * }
	 * 
	 * 
	 * 
	 * }
	 * 
	 * return beans; }
	 */

	@ExceptionHandler(CustomExceptionHandler.class)
	public ModelAndView handleCustomException(CustomExceptionHandler ex) {

		Map<String, Object> modelAct = new HashMap<String, Object>();
		modelAct.put("actList",
				userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		modelAct.put("homePage", "nbfcMakerHome");
		ModelAndView model = new ModelAndView("customException",modelAct);
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
