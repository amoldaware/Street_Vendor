
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

import com.nbfc.bean.DisbursementCheckerApprovalBean;
import com.nbfc.bean.DisbursementForApprovalBean;
import com.nbfc.bean.JsonResponse2;
import com.nbfc.model.DisbursementCheckerApprovalModel;
import com.nbfc.model.MLIDetails;
import com.nbfc.model.MLIName;
import com.nbfc.model.PortfolioNumberMaster;
import com.nbfc.service.DisbursementCheckerService;
import com.nbfc.service.UserActivityService;
import com.nbfc.service.UserService;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class DisbursementCheckerApprovalController {
	@Autowired
    UserService employeeService;
	@Autowired
	DisbursementCheckerService disbursementCheckerService;
	@Autowired
	UserActivityService userActivityService;
	
	MLIDetails mliDetails;
	MLIName mliName;
	
		static Logger log = Logger.getLogger(DisbursementCheckerApprovalController.class.getName());
		
		
	/*Method for Dispburstment for maker */
	@RequestMapping(value="disburstmentCheckerApproval", method=RequestMethod.GET)
	public ModelAndView getDisburseDataForMaker(@ModelAttribute("command")DisbursementCheckerApprovalBean disbursementForApprovalBean,BindingResult result,HttpServletRequest request,HttpSession session) throws CustomExceptionHandler{
		//String userId = (String) session.getAttribute("userId");
		Map<String, Object> map = new HashMap<String, Object>();
		List<DisbursementCheckerApprovalModel> portfolioNoList = new ArrayList<DisbursementCheckerApprovalModel>();
		List<PortfolioNumberMaster> portfolioNoList1 = new ArrayList<PortfolioNumberMaster>();
		//Map<String, Object> map = new HashMap<String, Object>();

		String userId = (String) session.getAttribute("userId");
		
		mliDetails = employeeService.getBNKId(userId);
		mliName = employeeService.getMLIName(mliDetails.getMem_bnk_id());
		portfolioNoList1 = employeeService.getPortfolioNUmberForChecker(mliName.getMliLName());
		
		//portfolioNoList = disbursementCheckerService.getPortfolioNoForCheckerApproval();
		
		List<DisbursementCheckerApprovalBean> unique=new ArrayList<DisbursementCheckerApprovalBean>();
		Set<String> uniquePortfolio=null;

		if(portfolioNoList1.size()>0){

		for(PortfolioNumberMaster dm:portfolioNoList1){
			 uniquePortfolio=new HashSet<String>();			
			uniquePortfolio.add(dm.getPortfolioNUmber().toString());
		}
		}else{
			throw new CustomExceptionHandler("Portfolio data is not available");

		}
		List<String> list=new ArrayList<String>(uniquePortfolio);		
		
		if (list.size() > 0) {
			for(int i=0;i<list.size();i++){
				DisbursementCheckerApprovalBean db = new DisbursementCheckerApprovalBean();
				db.setPortfolioNo(list.get(i));
				unique.add(db);
			}
						
			map.put("list", unique);
		} else {
			throw new CustomExceptionHandler("Portfolio no is not found");
		}
		map.put("actList",
				userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
		map.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
		map.put("homePage","nbfcCheckerHome");
		return new ModelAndView("disbursementCheckerApproval", map);
	}
	/*Get Portfolio no for Drop Down list for Disbursement maker */
	private List<DisbursementCheckerApprovalBean> getPortfolioNoList(List<DisbursementCheckerApprovalModel> portfolioNumber) {
		
		List<DisbursementCheckerApprovalBean> list=null;
		if(portfolioNumber!=null && !portfolioNumber.isEmpty()){
			DisbursementCheckerApprovalBean disbean=null;
			list=new ArrayList<DisbursementCheckerApprovalBean>();

		for(DisbursementCheckerApprovalModel disb:portfolioNumber){
			disbean=new DisbursementCheckerApprovalBean();
			disbean.setPortfolioNo(disb.getPortfolioNo().toString());
			System.out.println("Portfolio No. "+disb.getPortfolioNo());
			list.add(disbean);
		}
		}
		return list;
	}
	
	
	@RequestMapping(value="/searchCheckerData",method=RequestMethod.POST)
    public @ResponseBody JsonResponse2 getData(@RequestParam("portfolioNo") 
    		String portfolioNo,@RequestParam("loanAccountNumber") String loanAccountNumber) {
		
		JsonResponse2 res = new JsonResponse2();
		
		List<DisbursementCheckerApprovalModel> disburstDataList =new ArrayList<DisbursementCheckerApprovalModel>();
		
		List<DisbursementCheckerApprovalBean> responseList =new ArrayList<DisbursementCheckerApprovalBean>();
		if((!portfolioNo.equals("NONE") || !loanAccountNumber.equals(""))||(portfolioNo.equals("NONE") || !loanAccountNumber.equals(""))){
			disburstDataList  = disbursementCheckerService.getDisburseDataForApproval(portfolioNo,loanAccountNumber);
		}
		if(responseList.size()==0){
		if(disburstDataList.size()!=0){
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		for(DisbursementCheckerApprovalModel disburseList:disburstDataList){
			DisbursementCheckerApprovalBean disbursementForApprovalBean = new DisbursementCheckerApprovalBean();

			disbursementForApprovalBean.setUnit(disburseList.getUnit());
			disbursementForApprovalBean.setLoanAccountNumber(disburseList.getLoanAccountNumber());
			disbursementForApprovalBean.setAmountOfDisbursement(disburseList.getAmountOfDisbursement());
			disbursementForApprovalBean.setDateOfDisbursement(df.format(disburseList.getDateOfDisbursement()));
			disbursementForApprovalBean.setWhetherDisbursed(disburseList.getWhetherDisbursed());
			System.out.println(disburseList.getUnit()+" "+disburseList.getLoanAccountNumber()+" "+disburseList.getDateOfDisbursement());
			responseList.add(disbursementForApprovalBean);

		}		
		}
		}else{
			responseList=new ArrayList<DisbursementCheckerApprovalBean>();

		}
		res.setStatus("SUCCESS");
		res.setResult(responseList);
        return res;
    }

	@RequestMapping(value="/disbursementChecker",method=RequestMethod.POST)
    public @ResponseBody JsonResponse2 approveDisbursedData(@RequestParam("disbursedData") 
    		List disbursedData,HttpSession session) {
		String userId = (String) session.getAttribute("userId");

		//JsonResponse2 res = new JsonResponse2();				
		//List<DisbursementCheckerApprovalBean> responseList =new ArrayList<DisbursementCheckerApprovalBean>();
		int count=0;
		JsonResponse2 res = new JsonResponse2();				

		if(disbursedData.size()>0 && userId!=null){
			try {
				 count=disbursementCheckerService.approveDisbursedData(disbursedData,userId);
			} catch (CustomExceptionHandler e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
		
		res.setStatus("SUCCESS");
		res.setResult(count);
		return res;
    }
	@RequestMapping(value="/disbursementCheckerRejection",method=RequestMethod.POST)
    public @ResponseBody JsonResponse2 rejectDisbursedData(@RequestParam("rejectDataList") 
    		List rejectDataList,@RequestParam("remark") 
    		String remark,HttpSession session) {
		String userId = (String) session.getAttribute("userId");

		JsonResponse2 res = new JsonResponse2();				
		List<DisbursementCheckerApprovalBean> responseList =new ArrayList<DisbursementCheckerApprovalBean>();
		int count=0;
		if(rejectDataList.size()>0 && userId!=null){
			 count=disbursementCheckerService.rejectDisbursedData(rejectDataList,remark,userId);
			System.out.println("value of count "+count);
		}		
		res.setStatus("SUCCESS");
		res.setResult(count);
        return res;
    }
	
	
	@ExceptionHandler(CustomExceptionHandler.class)
	public ModelAndView handleCustomException(CustomExceptionHandler ex) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("actList",
				userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
		map.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
		map.put("homePage","nbfcCheckerHome");
		ModelAndView model = new ModelAndView("customException",map);
		model.addObject("customException", ex.getMessage());
		return model;

	}
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("actList",
				userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
		map.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
		map.put("homePage","nbfcCheckerHome");
		ModelAndView model = new ModelAndView("exception",map);
		model.addObject("exception", ex);
		return model;

	}
	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView handleArithException(ArithmeticException ex) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("actList",
				userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
		map.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
		map.put("homePage","nbfcCheckerHome");
		ModelAndView model = new ModelAndView("exception",map);
		model.addObject("exception", ex);
		return model;

	}
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullException(NullPointerException ex) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("actList",
				userActivityService.getActivity("NBFCCHECKER", "User_Activity"));
		map.put("CBMFList", userActivityService.getActivity("NBFCCHECKER", "Claim_Bank_Mandate"));
		map.put("homePage","nbfcCheckerHome");
		ModelAndView model = new ModelAndView("exception",map);
		model.addObject("exception", ex);
		return model;

	}
}
