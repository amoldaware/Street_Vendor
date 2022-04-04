package com.nbfc.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nbfc.bean.DisbursementForApprovalBean;
import com.nbfc.bean.JsonResponse2;
import com.nbfc.model.DisbursementForApprovalModel;
import com.nbfc.model.MLIDetails;
import com.nbfc.model.MLIName;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.PortfolioNumberMaster;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.DisbursementForApprovalService;
import com.nbfc.service.LoginService;
import com.nbfc.service.UserActivityService;
import com.nbfc.service.UserService;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class DisbursementMakerForApprovalController {

	@Autowired
	UserService employeeService;
	@Autowired
	DisbursementForApprovalService disbursementForApprovalService;
	@Autowired
	LoginService lofinService;
	NBFCPrivilegeMaster userPrvMst;
	UserPerivilegeDetails userPri;
	@Autowired
	UserActivityService userActivityService;
	static Logger log = Logger
			.getLogger(DisbursementMakerForApprovalController.class.getName());
	MLIDetails mliDetails;
	MLIName mliName;
	List<PortfolioNumberMaster> portfolioNoList = new ArrayList<PortfolioNumberMaster>();
	List<DisbursementForApprovalBean> masterPortfolioList = new ArrayList<DisbursementForApprovalBean>();

	/* Method for Dispburstment for maker */
	@RequestMapping(value = "disburstmentMakerApproval", method = RequestMethod.GET)
	public ModelAndView getDisburseDataForMaker(
			@ModelAttribute("command") DisbursementForApprovalBean disbursementForApprovalBean,
			BindingResult result, HttpServletRequest request,
			HttpSession session) throws CustomExceptionHandler {
		masterPortfolioList = new ArrayList<DisbursementForApprovalBean>();
		
		Map<String, Object> map = new HashMap<String, Object>();

		String userId = (String) session.getAttribute("userId");
		
		mliDetails = employeeService.getBNKId(userId);
		mliName = employeeService.getMLIName(mliDetails.getMem_bnk_id());
		portfolioNoList = employeeService.getPortfolioNUmberForChecker(mliName
				.getMliLName());

		if(portfolioNoList.size()>0){
		for (PortfolioNumberMaster masterList : portfolioNoList) {
			DisbursementForApprovalBean db = new DisbursementForApprovalBean();
			db.setPortfolioNo(Integer.toString(masterList.getPortfolioNUmber()));
			masterPortfolioList.add(db);

		}
		}else{
			throw new CustomExceptionHandler("Portfolio data is not available");
		}

		if (masterPortfolioList.size() != 0) {
			map.put("list", masterPortfolioList);
		} else {
			throw new CustomExceptionHandler("Portfolio no is not found");
		}
		map.put("actList",
				userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		map.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
		map.put("homePage","nbfcMakerHome");
		return new ModelAndView("disbursementMakerForApproval", map);
	}

	/*
	 * Get Portfolio no for Drop Down list for Disbursement maker private
	 * List<DisbursementForApprovalBean> getPortfolioNoList(
	 * List<DisbursementForApprovalModel> portfolioNumber) {
	 * 
	 * List<DisbursementForApprovalBean> list = null; if (portfolioNumber !=
	 * null && !portfolioNumber.isEmpty()) { DisbursementForApprovalBean disbean
	 * = null; list = new ArrayList<DisbursementForApprovalBean>();
	 * 
	 * for (DisbursementForApprovalModel disb : portfolioNumber) { disbean = new
	 * DisbursementForApprovalBean();
	 * disbean.setPortfolioNo(disb.getPortfolioNo().toString());
	 * System.out.println("Portfolio No. " + disb.getPortfolioNo());
	 * list.add(disbean); } } return list; }
	 */

	@RequestMapping(value = "/searchData", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse2 getData(@RequestParam("portfolioNo") String portfolioNo,
			@RequestParam("loanAccountNumber") String loanAccountNumber) {

		JsonResponse2 res = new JsonResponse2();
		DisbursementForApprovalModel disbursementForApprovalModel = new DisbursementForApprovalModel();

		List<DisbursementForApprovalModel> disburstDataList = new ArrayList<DisbursementForApprovalModel>();

		List<DisbursementForApprovalBean> responseList = new ArrayList<DisbursementForApprovalBean>();
		if ((!portfolioNo.equals("NONE") || !loanAccountNumber.equals(""))
				|| (portfolioNo.equals("NONE") || !loanAccountNumber.equals(""))) {
			disburstDataList = disbursementForApprovalService
					.getDisburseDataForApproval(portfolioNo, loanAccountNumber);
		}
		if (responseList.size() == 0) {
			if (disburstDataList.size() != 0) {
				for (DisbursementForApprovalModel disburseList : disburstDataList) {
					DisbursementForApprovalBean disbursementForApprovalBean = new DisbursementForApprovalBean();

					disbursementForApprovalBean.setUnit(disburseList.getUnit());
					disbursementForApprovalBean
							.setLoanAccountNumber(disburseList
									.getLoanAccountNumber());
					disbursementForApprovalBean
							.setAmountOfDisbursement(disburseList
									.getAmountOfDisbursement());
					disbursementForApprovalBean
							.setDateOfDisbursement(disburseList
									.getDateOfDisbursement());
					disbursementForApprovalBean
							.setWhetherDisbursed(disburseList
									.getWhetherDisbursed());
					System.out.println(disburseList.getUnit() + " "
							+ disburseList.getLoanAccountNumber() + " "
							+ disburseList.getDateOfDisbursement());
					responseList.add(disbursementForApprovalBean);

				}
			}
		} else {
			responseList = new ArrayList<DisbursementForApprovalBean>();

		}
		res.setStatus("SUCCESS");
		res.setResult(responseList);
		return res;
	}

	@RequestMapping(value = "/disbursementMaker", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse2 updateDisbursedData(
			@RequestParam("disbursedData") List disbursedData1,
			HttpSession session) {
		List<DisbursementForApprovalModel> disbursedData = new ArrayList<DisbursementForApprovalModel>();
		String userId = (String) session.getAttribute("userId");
		JsonResponse2 res = new JsonResponse2();

		if (disbursedData1.size() > 0) {
			for (int i = 0; i < disbursedData1.size(); ++i) {
				DisbursementForApprovalModel disbursementForApprovalModel = new DisbursementForApprovalModel();
				disbursementForApprovalModel
						.setLoanAccountNumber(disbursedData1.get(i).toString());
				i++;
				for (int j = i + 1; j <= i + 1; j++) {
					DateFormat formatter = null;
					Date convertedDate = null;

					// Creating SimpleDateFormat with yyyyMMdd format
					// e.g."20110914"

					// convert string to date with ddMMyyyy format example
					// "14092011"
					String ddMMyyyy = disbursedData1.get(i).toString();
					formatter = new SimpleDateFormat("dd/MM/yyyy");
					try {
						convertedDate = (Date) formatter.parse(ddMMyyyy);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					disbursementForApprovalModel
							.setDateOfDisbursement(convertedDate);
				}
				disbursedData.add(disbursementForApprovalModel);
			}

		}
		// List<DisbursementForApprovalBean> responseList = new
		// ArrayList<DisbursementForApprovalBean>();
		int count = 0;
		if (disbursedData.size() > 0) {
			count = disbursementForApprovalService.updateDisbursedData(
					disbursedData, userId);

			// count =
			// disbursementForApprovalService.updateDisbursedData(setDisbursedMakerData(disbursedData1),userId);
		}
		res.setStatus("SUCCESS");
		res.setResult(count);
		// return new ModelAndView("redirect:/disburstmentMakerApproval.html");
		return res;

	}

	/*
	 * private List<DisbursementForApprovalBean> setDisbursedMakerData(List
	 * disbursedData) { // TODO Auto-generated method stub List
	 * <DisbursementForApprovalBean> list=new
	 * ArrayList<DisbursementForApprovalBean>(); if(disbursedData.size()>0){
	 * for(int i=0;i<disbursedData.size();i++){ DisbursementForApprovalBean
	 * db=new DisbursementForApprovalBean();
	 * db.setLoanAccountNumber(disbursedData.get(i).toString());
	 * db.setDateOfDisbursement((Date) disbursedData.get(i)); }} return list; }
	 */

	@ExceptionHandler(CustomExceptionHandler.class)
	public ModelAndView handleCustomException(CustomExceptionHandler ex) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("actList",
				userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		map.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
		map.put("homePage","nbfcMakerHome");
		ModelAndView model = new ModelAndView("customException",map);
		model.addObject("customException", ex);
		return model;

	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("actList",
				userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		map.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
		map.put("homePage","nbfcMakerHome");
		ModelAndView model = new ModelAndView("exception",map);
		model.addObject("exception", ex);
		return model;

	}

	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView handleArithException(ArithmeticException ex) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("actList",
				userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		map.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
		map.put("homePage","nbfcMakerHome");
		ModelAndView model = new ModelAndView("exception",map);
		model.addObject("exception", ex);
		return model;

	}

	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullException(NullPointerException ex) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("actList",
				userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		map.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
		map.put("homePage","nbfcMakerHome");
		ModelAndView model = new ModelAndView("exception",map);
		model.addObject("exception", ex);
		return model;

	}

}
