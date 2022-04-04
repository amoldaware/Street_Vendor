

package com.nbfc.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
import com.nbfc.bean.SanctionCheckerBean;
import com.nbfc.model.MLIDetails;
import com.nbfc.model.MLIName;
import com.nbfc.model.PortfolioNumberMaster;
import com.nbfc.model.SanctionCheckerModel;
import com.nbfc.service.DisbursementCheckerService;
import com.nbfc.service.SanctionCheckerService;
import com.nbfc.service.UserActivityService;
import com.nbfc.service.UserService;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class SanctionCheckerController {
	@Autowired
    UserService employeeService;
	@Autowired
	SanctionCheckerService sanctionCheckerService;
	@Autowired
	UserActivityService userActivityService;
	MLIDetails mliDetails;
	MLIName mliName;
	
		static Logger log = Logger.getLogger(SanctionCheckerController.class.getName());
		
		
	/*Method for sanction for maker */
	@RequestMapping(value="sanctionChecker", method=RequestMethod.GET)
	public ModelAndView getDisburseDataForMaker(@ModelAttribute("command")SanctionCheckerBean disbursementForApprovalBean,BindingResult result,HttpServletRequest request,HttpSession session) throws CustomExceptionHandler{
		String userId = (String) session.getAttribute("userId");
		Map<String, Object> map = new HashMap<String, Object>();
		//List<SanctionCheckerModel> portfolioNoList = new ArrayList<SanctionCheckerModel>();
		List<PortfolioNumberMaster> portfolioNoList = new ArrayList<PortfolioNumberMaster>();
		mliDetails = employeeService.getBNKId(userId);
		mliName = employeeService.getMLIName(mliDetails.getMem_bnk_id());
		portfolioNoList = employeeService.getPortfolioNUmberForChecker(mliName.getMliLName());
		
		
		//portfolioNoList = sanctionCheckerService.getPortfolioNoForCheckerApproval();
		System.out.println(portfolioNoList);
		List<SanctionCheckerBean> unique=new ArrayList<SanctionCheckerBean>();
		Set<String> uniquePortfolio=null;

		if(portfolioNoList.size()>0){

		for(PortfolioNumberMaster dm:portfolioNoList){
			 uniquePortfolio=new HashSet<String>();			
			uniquePortfolio.add(dm.getPortfolioNUmber().toString());
		}
		}
		List<String> list=new ArrayList<String>(uniquePortfolio);		
		
		if (list.size() > 0) {
			for(int i=0;i<list.size();i++){
				SanctionCheckerBean db = new SanctionCheckerBean();
				db.setPortfolioNo(list.get(i));
				unique.add(db);
			}
						
			map.put("list", unique);
		} else {
			throw new CustomExceptionHandler("Portfolio no is not found");
		}
		//added by say 6 feb-----------------------
		map.put("adminlist",
				userActivityService.getActivity("NBFCCHECKER", "System_Admin"));
		map.put("guaranteelist",
				userActivityService.getActivity("NBFCCHECKER", "Registration"));
		map.put("applicationList",
				userActivityService.getActivity("NBFCCHECKER", "Application_Processing"));
		map.put("RPaymentList",
				userActivityService.getActivity("NBFCCHECKER", "Receipt_Payments"));
		map.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
//end---------------------------------------------------------------------------------------	
		
//
//		map.put("actList",
//				userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
		map.put("homePage","nbfcCheckerHome");
		return new ModelAndView("sanctionChecker", map);
	}
	
	
	@RequestMapping(value="/searchCheckerDataForSanction",method=RequestMethod.POST)
    public @ResponseBody JsonResponse2 getData(@RequestParam("portfolioNo") 
    		String portfolioNo,@RequestParam("loanAccountNumber") String loanAccountNumber) {
		
		JsonResponse2 res = new JsonResponse2();
		
		//List<SanctionCheckerBean> disburstDataList =new ArrayList<SanctionCheckerBean>();
		
		List<SanctionCheckerBean> responseList =new ArrayList<SanctionCheckerBean>();
		if((!portfolioNo.equals("NONE") || !loanAccountNumber.equals(""))||(portfolioNo.equals("NONE") || !loanAccountNumber.equals(""))){
			try {
				responseList  = sanctionCheckerService.getSanctionCheckerDataForApproval(portfolioNo,loanAccountNumber);
				TreeSet<SanctionCheckerBean> hs = new TreeSet<SanctionCheckerBean>();
				hs.addAll(responseList);
				responseList.clear();
				responseList.addAll(hs);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		res.setStatus("SUCCESS");
		res.setResult(responseList);
        return res;
    }

	@RequestMapping(value="/sanctionCheckerApproval",method=RequestMethod.POST)
    public @ResponseBody JsonResponse2 approveDisbursedData(@RequestParam("disbursedData") 
    		List disbursedData,HttpSession session) {
		String userId = (String) session.getAttribute("userId");

		JsonResponse2 res = new JsonResponse2();				
		//List<DisbursementCheckerApprovalBean> responseList =new ArrayList<DisbursementCheckerApprovalBean>();
		int count=0;
		if(disbursedData.size()>0 && userId!=null){
			try {
				 count=sanctionCheckerService.approveDisbursedData(disbursedData,userId);
			} catch (CustomExceptionHandler e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		res.setStatus("SUCCESS");
		res.setResult(count);
		return res;
    }
	@RequestMapping(value="/sanctionCheckerRejection",method=RequestMethod.POST)
    public @ResponseBody JsonResponse2 rejectDisbursedData(@RequestParam("rejectDataList") 
    		List rejectDataList,@RequestParam("remark") 
    		String remark,HttpSession session) {
		String userId = (String) session.getAttribute("userId");

		JsonResponse2 res = new JsonResponse2();				
		List<SanctionCheckerBean> responseList =new ArrayList<SanctionCheckerBean>();
		int count=0;
		if(rejectDataList.size()>0 && userId!=null){
			 count=sanctionCheckerService.rejectDisbursedData(rejectDataList,remark,userId);
		}		
		res.setStatus("SUCCESS");
		res.setResult(count);
        return res;
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
