
package com.nbfc.controller;

import java.text.DateFormat;
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

import com.nbfc.bean.JsonResponse2;
import com.nbfc.bean.SanctionMakerBean;
import com.nbfc.model.DisbursementForApprovalModel;
import com.nbfc.model.MLIDetails;
import com.nbfc.model.MLIName;
import com.nbfc.model.PortfolioNumberMaster;
import com.nbfc.model.SanctionMakerModel;
import com.nbfc.service.SanctionMakerService;
import com.nbfc.service.UserActivityService;
import com.nbfc.service.UserService;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class SanctionMakerController {
	
	@Autowired
    UserService employeeService;
	@Autowired
	SanctionMakerService sanctionMakerService;
	@Autowired
	UserActivityService userActivityService;
	static Logger log = Logger.getLogger(SanctionMakerController.class
			.getName());
	MLIDetails mliDetails;
	MLIName mliName;
	List<PortfolioNumberMaster> portfolioNoList = new ArrayList<PortfolioNumberMaster>();
	List<SanctionMakerBean> masterPortfolioList = new ArrayList<SanctionMakerBean>();
	
	/* Method for sanction for maker */
	@RequestMapping(value = "sanctionMaker", method = RequestMethod.GET)
	public ModelAndView getSanctionDataForMaker(
			@ModelAttribute("command") SanctionMakerBean SanctionMakerBean,
			BindingResult result, HttpServletRequest request,
			HttpSession session) throws CustomExceptionHandler {
		masterPortfolioList = new ArrayList<SanctionMakerBean>();
		String userId = (String) session.getAttribute("userId");
		Map<String, Object> map = new HashMap<String, Object>();	

		mliDetails = employeeService.getBNKId(userId);
		mliName = employeeService.getMLIName(mliDetails.getMem_bnk_id());
		portfolioNoList = employeeService.getPortfolioNUmberForChecker(mliName.getMliLName());

		if(portfolioNoList.size()>0){
		for (PortfolioNumberMaster masterList : portfolioNoList) {
			SanctionMakerBean db=new SanctionMakerBean();
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
		//added by say 6 feb-----------------------
		map.put("adminlist",
				userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		map.put("guaranteelist",
				userActivityService.getActivity("NBFCMAKER", "Registration"));
		map.put("applicationList",
				userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
		map.put("RPaymentList",
				userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
		map.put("CBMFList", userActivityService.getActivity("NBFCMAKER", "Claim_Bank_Mandate"));
//end---------------------------------------------------------------------------------------	

//		modelAct.put("actList", userActivityService.getActivity(
//				"NBFCMAKER", "User_Activity"));//commented by say Feb 6
		map.put("homePage","nbfcMakerHome");
		return new ModelAndView("sanctionMaker", map);
	}

	

	@RequestMapping(value = "/searchSanctionMakerData", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse2 getData(@RequestParam("portfolioNo") String portfolioNo,
			@RequestParam("loanAccountNumber") String loanAccountNumber) {

		JsonResponse2 res = new JsonResponse2();
		//SanctionMakerModel sanctionMakerModel = new SanctionMakerModel();

		List<SanctionMakerModel> sanctionDataList = new ArrayList<SanctionMakerModel>();

		List<SanctionMakerBean> responseList = new ArrayList<SanctionMakerBean>();
		if ((!portfolioNo.equals("NONE") || !loanAccountNumber.equals(""))
				|| (portfolioNo.equals("NONE") || !loanAccountNumber.equals(""))) {
			sanctionDataList = sanctionMakerService.getDisburseDataForApproval(portfolioNo, loanAccountNumber);
		}
		if (responseList.size() == 0) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

			if (sanctionDataList.size() != 0) {
				for (SanctionMakerModel sanctionList : sanctionDataList) {
					SanctionMakerBean sanctionForApprovalBean = new SanctionMakerBean();

				//	sanctionForApprovalBean.setUnit(sanctionList.getUnit());
					sanctionForApprovalBean.setLoanAccountNumber(sanctionList
									.getLoanAccountNo());
					sanctionForApprovalBean.setAmountOfDisbursement(sanctionList
									.getSanctionedAmount());
					sanctionForApprovalBean.setDateOfDisbursement(df.format(sanctionList.getFirstDisbursementDate()));
					//sanctionForApprovalBean.setWhetherDisbursed(sanctionList.getWhetherDisbursed());
					
					responseList.add(sanctionForApprovalBean);

				}
			}
		} else {
			responseList = new ArrayList<SanctionMakerBean>();

		}
		res.setStatus("SUCCESS");
		res.setResult(responseList);
		return res;
	}

	@RequestMapping(value = "/sanctionMakerApproval", method = RequestMethod.POST)
	public @ResponseBody JsonResponse2 updateDisbursedData(
			@RequestParam("sanctionData") List sanctionData1,HttpSession session) {
		

		List<SanctionMakerModel> sanctionedData = new ArrayList<SanctionMakerModel>();
		String userId = (String) session.getAttribute("userId");
		JsonResponse2 res = new JsonResponse2();
		
		if(sanctionData1.size()>0){
		for(int i=0;i<sanctionData1.size();++i){
			SanctionMakerModel sanctionMakerModel=new SanctionMakerModel();
			sanctionMakerModel.setLoanAccountNo(sanctionData1.get(i).toString());
			i++;
			for(int j=i+1;j<=i+1;j++){ 
				sanctionMakerModel.setSanctionedAmount(sanctionData1.get(i).toString());
			}		
			sanctionedData.add(sanctionMakerModel);
		}
		
		}
		//List<DisbursementForApprovalBean> responseList = new ArrayList<DisbursementForApprovalBean>();
		int count=0;
		if (sanctionedData.size() > 0) {
			 count = sanctionMakerService.updateDisbursedData(sanctionedData,userId);

			 //count = disbursementForApprovalService.updateDisbursedData(setDisbursedMakerData(disbursedData1),userId);
		}
		res.setStatus("SUCCESS");
		res.setResult(count);
		//return new ModelAndView("redirect:/disburstmentMakerApproval.html");
		return res;

	
	}

	/*private List<SanctionMakerBean> setDisbursedMakerData(List disbursedData) {
		// TODO Auto-generated method stub
		List <SanctionMakerBean> list=new ArrayList<SanctionMakerBean>();
		if(disbursedData.size()>0){
		for(int i=0;i<disbursedData.size();i++){
			SanctionMakerBean db=new SanctionMakerBean();
			db.setLoanAccountNumber(disbursedData.get(i).toString());
			db.setDateOfDisbursement((Date) disbursedData.get(i));
		}}
		return list;
	}*/

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
