package com.nbfc.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.text.SimpleDateFormat;
import com.nbfc.bean.CGTMSEExposureMasterBean;
import com.nbfc.bean.JsonResponse3;
import com.nbfc.common.utility.method.NBFCCommonUtility;
import com.nbfc.helper.NBFCHelper;
import com.nbfc.helper.NBFCValidator;
import com.nbfc.model.CGTMSEExposureMasterCheckerGETExposureDetails;
import com.nbfc.model.CGTMSEExposureMasterDetails;
import com.nbfc.model.CGTMSEExposureMasterGetDetailsOfMemberInfo;
import com.nbfc.model.CGTMSEExposureMasterMLIName;
import com.nbfc.model.CGTMSECreateExposureLimitByMaker;
import com.nbfc.model.MLIRegistration;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.PortFolioDetailsInChildTBL;
import com.nbfc.model.PortFolioDetailsInParentTBL;
import com.nbfc.model.User;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.CGTMSECreateExposureLimitMakerService;
import com.nbfc.service.LoginService;
import com.nbfc.service.SanctionDetailService;
import com.nbfc.service.UserActivityService;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class CGTMSECreateExposureLimitMakerController {
	@Autowired
	private SanctionDetailService sanctionDetailService;
	@Autowired
	LoginService lofinService;
	@Autowired
	UserActivityService userActivityService;
	UserPerivilegeDetails userPri;
	NBFCPrivilegeMaster userPrvMst;
	ModelAndView modelView;
	String userId=null;
	public static final int BUFFER_SIZE = 4096;
	@Autowired
	private CGTMSECreateExposureLimitMakerService cgtmseExposureMasterMakerService;
	@Autowired
	CGTMSECreateExposureLimitMakerService cgtmscCreateExposureLimitMakerService;
	ModelAndView modelAndViewObj;
	private List<CGTMSEExposureMasterBean> userList = new ArrayList<CGTMSEExposureMasterBean>();
	String portFiloName=null;
	NBFCValidator nbfcValidator = new NBFCValidator();
	@Value("${downloadFileDirPath}")
	private String downloadFileDirPath;
	@Value("${ExposureDetailsdownloadFileName}")
	private String downloadFileName;
	//Get the InputForm when you will hit URL showCreateExposureLimitMakerInputForm
	@RequestMapping(value = "showCreateExposureLimitMakerInputForm", method = RequestMethod.GET)
	public ModelAndView showCreateExposureLimitMakerInputForm(@ModelAttribute("command") CGTMSEExposureMasterBean exposureMasterBean,BindingResult result,HttpSession session,Model modelObj) {
		try{
			userId=(String)session.getAttribute("userId");
			 if(userId!=null){
				userPri=lofinService.getUserPrivlageDtl(userId, "A");
				userPrvMst=lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				if(userPrvMst.getPrvCreatedModifiedBy().equals("CMAKER")){
					//No of Pending records display in page onload
					List<Object[]> listObj=cgtmseExposureMasterMakerService.getExposureLimitDetailsPendingForApproval();
					int countNoOfExposureMasterPendingForApproval=listObj.size();
					
					//No of approved records display in page onload
					List<Object[]> listObj3=cgtmseExposureMasterMakerService.getExposureLimitApprovedDetails();
					int countNoOfExposureMasterApproval =listObj3.size();
					
					//No of Rejected Records display in page onload
					List<Object[]> listObj4=cgtmseExposureMasterMakerService.getExposureLimitRejectedDetails();
					int countNoOfExposureMasterRejected =listObj4.size();
					modelObj.addAttribute("countNoOfExposureMasterPendingForApprovalKey", countNoOfExposureMasterPendingForApproval);
					modelObj.addAttribute("countNoOfExposureMasterApprovalKey", countNoOfExposureMasterApproval);
					modelObj.addAttribute("countNoOfExposureMasterRejectedKey", countNoOfExposureMasterRejected);
					
					Map<String, Object> modelAct = new HashMap<String, Object>();
					modelAct.put("adminlist",userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
					modelAct.put("guaranteelist",userActivityService.getActivity("CGTMSEMAKER", "Registration"));
					modelAct.put("applicationList",userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
					modelAct.put("RPaymentList",userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
					modelAct.put("repList",
							userActivityService.getReport("CGTMSEMAKER", "User_Report"));
					modelAct.put("actName", userActivityService.getActivityName("CGTMSEMAKER","ExposureListingMaker"));
					modelAct.put("homePage","cgtmseMakerHome");
					modelAct.put("GMaintainlist", userActivityService.getActivity(
								"CGTMSEMAKER", "Guarantee_Maintenance"));
					modelView = new ModelAndView("cgtmseCreateExposureLimitByMaker",modelAct); 
				}else{
					modelView = new ModelAndView("redirect:/SVLogin.html");
				}
			 }else{
				 modelView = new ModelAndView("redirect:/SVLogin.html");
			 }
			return modelView;
		}catch(Exception e){
			System.out.println("Exception =="+e);
		}
			return modelView;
	}
	
	//Get the MLI Long Name in Drop Down on Page on Load
	@ModelAttribute("mliLongName")
	public Map<String, String> getMlilongName() {
		Map<String, String> mapMliLongNameObj = new HashMap<String, String>();
		mapMliLongNameObj = cgtmseExposureMasterMakerService.getMliLongNameInDropDown();
		return   mapMliLongNameObj;
	}

	//Get MLI Short Name onchange of dropdown
	@RequestMapping(value = "/fetchMliShortName", method = RequestMethod.POST)
	public @ResponseBody JsonResponse3 getMliShortName(@ModelAttribute(value = "mliLName") CGTMSEExposureMasterBean cgtmseExposureMasterBean,BindingResult result,String mliLName, HttpSession session) {
			ArrayList<String> arrayListObj1 = new ArrayList<String>();
			cgtmseExposureMasterBean.setMliLongName(mliLName);
			arrayListObj1 = cgtmseExposureMasterMakerService.getMliShortNameOnChangeOfMliLongName(cgtmseExposureMasterBean);
			Iterator<String> itr = arrayListObj1.iterator();
			JsonResponse3 res = new JsonResponse3();
			CGTMSEExposureMasterBean s1 = new CGTMSEExposureMasterBean();
			String shortName = (String) itr.next();
			s1.setMliShortName(shortName);
			userList.clear();
			userList.add(s1);
			res.setStatus("SUCCESS");
			res.setResult(userList);
			return res;
	}
	
	//save or Create Exposure  Details of InputForm  
	@RequestMapping(value = "createExposureLimitByMakerAndSendForApprovalToChecker", method = RequestMethod.POST)
	public ModelAndView createExposureLimit(@ModelAttribute("command") CGTMSEExposureMasterBean exposureMasterBean,BindingResult result,HttpSession session,Model modelObj) throws ParseException {
		// Added By Parmanand 12-Feb-2019 Start  to generate finalcial year and Quater
		//Convert String into Date and Store into DB as Date data type
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String startDate = exposureMasterBean.getMliValidityOfExposureLimitStartDate();
        String endDate=exposureMasterBean.getMliValidityOfExposureLimitEndDate();
        String dateOfSanctionOfExposure=exposureMasterBean.getMliDateOfSanctionOfExposure();
        Date sDate = null;
        Date eDate=null;
		//Commented By Parmanand
        //String fyBasedOnStartAndEndDate=cgtmseExposureMasterMakerService.getFyBasedOnStartAndEndDate(startDate,endDate);
        NBFCHelper nbfcHelper = new NBFCHelper();
        String fyBasedOnStartAndEndDate=nbfcHelper.getCurrentYear();
		
        Date date = new Date();
		Integer NoOfCountOfLongName = sanctionDetailService.getGeneratedPortfolioCount();
		Integer generatePortfolioNo = 10000000;
		if (NoOfCountOfLongName == 0) {
			NoOfCountOfLongName = 1;
			generatePortfolioNo = 0 + generatePortfolioNo;
		} else {
			NoOfCountOfLongName = NoOfCountOfLongName + 1;
			generatePortfolioNo = NoOfCountOfLongName + generatePortfolioNo;
		}
		portFiloName=exposureMasterBean.getMliShortName()+"/"+fyBasedOnStartAndEndDate+"/"+nbfcValidator.getQuarterNUmber(date)+"/"+nbfcValidator.lastDigit(generatePortfolioNo);
		// Added By Parmanand 12-Feb-2019 End
		Map<String, Object> model = new HashMap<String, Object>();
		String memBnkId=null;
		String memBrnId=null;
		String memZneId=null;
		Long generatedExposureId=(long) 10000;
		String status="CEMMA";
		String  exposureStatus="";
		String FYYear="";
		//Get System date exposureMasterBean.getMliDateOfSanctionOfExposure()
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
	    String sysDate = sdf.format(cal.getTime());
	    //Convert String into Date and Store into DB as Date data type
  		SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
        String sysCurrentDate = sysDate;
        Date makerDate = null;
  		try {
  			makerDate = formatter1.parse(sysCurrentDate);
  		} catch (ParseException e) {
  			e.printStackTrace();
  		}
		CGTMSEExposureMasterGetDetailsOfMemberInfo cgtmseExposureMasterGetDetailsOfMemberInfoObj2=new CGTMSEExposureMasterGetDetailsOfMemberInfo();
		cgtmseExposureMasterGetDetailsOfMemberInfoObj2.setMliLongName(exposureMasterBean.getMliLongName());
		List<CGTMSEExposureMasterGetDetailsOfMemberInfo> obj3=cgtmseExposureMasterMakerService.getDetailsOfMemberInfo(cgtmseExposureMasterGetDetailsOfMemberInfoObj2);
			if(obj3.size()>0){
			Iterator<CGTMSEExposureMasterGetDetailsOfMemberInfo> itr=obj3.iterator();
				while(itr.hasNext()){
					CGTMSEExposureMasterGetDetailsOfMemberInfo obj4=(CGTMSEExposureMasterGetDetailsOfMemberInfo)itr.next();
					if(obj4!=null){
						memBnkId=obj4.getMemBnkId();
						memBrnId=obj4.getMemBrnId();
						memZneId=obj4.getMemZneId();
					}
				}
		}
        Date sanctionExposureDate=null;
		try {
			sDate = formatter.parse(startDate);
			eDate=formatter.parse(endDate);
			sanctionExposureDate=formatter.parse(dateOfSanctionOfExposure);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Integer maxExposureIdCount = cgtmseExposureMasterMakerService.getMaxExposureIdCount();
		long increasedMaxExposureIdCount=maxExposureIdCount;
		if(maxExposureIdCount==0){
			generatedExposureId=(long) 10000;
		}else{			
			generatedExposureId=increasedMaxExposureIdCount;
		}
		CGTMSECreateExposureLimitByMaker cgtmseExposureMasterGetDetails=new CGTMSECreateExposureLimitByMaker();
		cgtmseExposureMasterGetDetails.setMemBnkId(memBnkId);
		cgtmseExposureMasterGetDetails.setMemBrnId(memBrnId);
		cgtmseExposureMasterGetDetails.setMemZneId(memZneId);
	
		CGTMSECreateExposureLimitByMaker obj4=cgtmseExposureMasterMakerService.getDetailsOfExposer(cgtmseExposureMasterGetDetails,fyBasedOnStartAndEndDate);
	    if(obj4!=null){
	    	exposureStatus=obj4.getStatus();
	    	FYYear=obj4.getFinancial_year();
	    }
	    if(FYYear.equals(fyBasedOnStartAndEndDate)) {
	    	
		    	modelObj.addAttribute("error","Exposure has been alrady created For Current finanacial Year!");
				model.put("actList", userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
				model.put("homePage", "cgtmseMakerHome");
				model.put("GMaintainlist", userActivityService.getActivity(
						"CGTMSEMAKER", "Guarantee_Maintenance"));
				modelAndViewObj = new ModelAndView("cgtmseCreateExposureLimitByMaker",model);
		    	
		    
	    	
	    }
	    
	    else {
	    CGTMSECreateExposureLimitByMaker cgtmseCreateExposureLimitByMakerObj=new CGTMSECreateExposureLimitByMaker();
	  //Midified Existing  Code By Parmanand 11/Feb/2019 Regarding validating to create Exposure for one year  Start
		List<CGTMSEExposureMasterGetDetailsOfMemberInfo> obj=cgtmseExposureMasterMakerService.getDetailsOfMemberInfo(cgtmseExposureMasterGetDetailsOfMemberInfoObj2);
		/*ArrayList<CGTMSECreateExposureLimitByMaker> arrayListObj10 = new ArrayList<CGTMSECreateExposureLimitByMaker>();
		arrayListObj10 = cgtmseExposureMasterMakerService.validateStartEndDateForOneYear(exposureMasterBean);
		if(arrayListObj10.size()>0){
			modelObj.addAttribute("error","Exposure has already created between given Start and End date!");
			model.put("actList", userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
			model.put("homePage", "cgtmseMakerHome");
			modelAndViewObj = new ModelAndView("cgtmseCreateExposureLimitByMaker",model);
		}else{*/
			if(obj.size()>0){
				Iterator<CGTMSEExposureMasterGetDetailsOfMemberInfo> itr_1=obj.iterator();
				while(itr_1.hasNext()){
					CGTMSEExposureMasterGetDetailsOfMemberInfo ob = (CGTMSEExposureMasterGetDetailsOfMemberInfo)itr_1.next();
					if((exposureMasterBean.getMliLongName().equals(ob.getMliLongName()) && ob.getStatus().equals("CCA"))){	
						String statusDescription="Pending for approval";
						cgtmseCreateExposureLimitByMakerObj.setExposureId(generatedExposureId);
						cgtmseCreateExposureLimitByMakerObj.setMemBnkId(memBnkId);
						cgtmseCreateExposureLimitByMakerObj.setMemBrnId(memBrnId); 
						cgtmseCreateExposureLimitByMakerObj.setMemZneId(memZneId);
						cgtmseCreateExposureLimitByMakerObj.setExposureLimit(exposureMasterBean.getMliExposureLimit());
						//Added By Parmanand  start 04/02/2019
						cgtmseCreateExposureLimitByMakerObj.setGurantee_fee(exposureMasterBean.getGurantee_fee());
						cgtmseCreateExposureLimitByMakerObj.setPay_out_cap(exposureMasterBean.getPay_out_cap());
						cgtmseCreateExposureLimitByMakerObj.setFinancial_year(fyBasedOnStartAndEndDate);
						cgtmseCreateExposureLimitByMakerObj.setGuranteeCoverage(exposureMasterBean.getGuranteeCoverage());
					
						//Added By Parmanand  End 04/02/2019
						cgtmseCreateExposureLimitByMakerObj.setMakerId(userId);
						cgtmseCreateExposureLimitByMakerObj.setMakerDate(makerDate);
						cgtmseCreateExposureLimitByMakerObj.setFromDate(sDate);
						cgtmseCreateExposureLimitByMakerObj.setToDate(eDate);
						cgtmseCreateExposureLimitByMakerObj.setStatus(status);
						cgtmseCreateExposureLimitByMakerObj.setExposureSanctionDate(sanctionExposureDate);
						cgtmseCreateExposureLimitByMakerObj.setStatusDescription(statusDescription);
						cgtmseExposureMasterMakerService.createNewExposureLimit(cgtmseCreateExposureLimitByMakerObj);
						//Added By Parmanand start 05/02/2019
						PortFolioDetailsInParentTBL objPortFolioDetailsInParentTBL=new PortFolioDetailsInParentTBL();
						objPortFolioDetailsInParentTBL.setPortfolio_no(generatePortfolioNo);
						Long generatedExpId = generatedExposureId;
						objPortFolioDetailsInParentTBL.setExposure_id(generatedExposureId);
						objPortFolioDetailsInParentTBL.setPortFolioName(portFiloName);
						objPortFolioDetailsInParentTBL.setGurantee_fee(exposureMasterBean.getGurantee_fee());
						objPortFolioDetailsInParentTBL.setPortFolio_baseYrs(fyBasedOnStartAndEndDate);
						objPortFolioDetailsInParentTBL.setPay_out_cap(exposureMasterBean.getPay_out_cap());
						
						//Commented By Parmanand  ExposureMasterMakerService.savePortFolioDetailsInParentTBL(objPortFolioDetailsInParentTBL);
						NBFCCommonUtility  nbfcCommUtiltyObj=new NBFCCommonUtility();
						long diffMonths=nbfcCommUtiltyObj.findMonthBetweenTwoDate(startDate, endDate);
						long noOfQuaterCreated=diffMonths/3;
						PortFolioDetailsInChildTBL objPortFolioDetailsInChildTBL=new PortFolioDetailsInChildTBL();
						for (int i = 1; i <=noOfQuaterCreated; i++) {
							String quater1 = fyBasedOnStartAndEndDate+ "Q" + i;
							objPortFolioDetailsInChildTBL.setPortfolio_No(generatePortfolioNo);
							String quater2 = exposureMasterBean.getMliShortName() + quater1;
							objPortFolioDetailsInChildTBL.setPortfolioQuarter(quater2);
							// Generated subportfoliioSerialNumber
							objPortFolioDetailsInChildTBL.setSubPortfolioSerialNo(i);
							//Commented By Parmanand ExposureMasterMakerService.savePortFolioDetailsInChildTBL(objPortFolioDetailsInChildTBL);
						}
						modelAndViewObj = new ModelAndView("redirect:/ExposureListingMakerWithSuccess.html");
					}else if((exposureMasterBean.getMliLongName().equals(ob.getMliLongName()) && ob.getStatus().equals("CCA") && (exposureStatus.equals("CEMMA") || exposureStatus.equals("CEMCCA") || exposureStatus.equals("CEMCR") ))){
						//modelObj.addAttribute("error","Exposure has been alrady created!");
						model.put("actList", userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
						model.put("homePage", "cgtmseMakerHome");
						modelAndViewObj = new ModelAndView("cgtmseCreateExposureLimitByMaker",model);
					}else{
						modelObj.addAttribute("error","Something Went worng!");
						model.put("actList", userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
						model.put("homePage", "cgtmseMakerHome");
						model.put("GMaintainlist", userActivityService.getActivity(
								"CGTMSEMAKER", "Guarantee_Maintenance"));
						modelAndViewObj = new ModelAndView("cgtmseCreateExposureLimitByMaker",model);
					}
				}
			}
	    }
	    
		/*}*/
		return modelAndViewObj;
	}

	//Get Exposure Limit Details PendingForApproval whose status is CEMMA
	@RequestMapping(value = "createdExposureLimitDetailsPendingForApprovalByChecker1", method = RequestMethod.GET)
	public ModelAndView getCreatedExposureLimitDetailsPendingForApproval(@ModelAttribute("command") CGTMSEExposureMasterBean exposureMasterBean,BindingResult result,HttpSession session,Model modelObj) {
		List<Object[]> listObj=cgtmseExposureMasterMakerService.getExposureLimitDetailsPendingForApproval();
		List<Object> list1 = new ArrayList<Object>();
		Iterator<Object[]> itr1= listObj.iterator();
		while(itr1.hasNext()){
			Object[] obj1 = (Object[]) itr1.next();
			CGTMSEExposureMasterBean cgtmseExposureMasterBeanObj1=new CGTMSEExposureMasterBean();
			cgtmseExposureMasterBeanObj1.setMliLongName((String) obj1[0]);
			cgtmseExposureMasterBeanObj1.setMliShortName((String) obj1[1]);
			cgtmseExposureMasterBeanObj1.setMliExposureLimit((Long) obj1[2]);
			
			// Convert Date to String, use format method of SimpleDateFormat class.
			java.util.Date dateOfSanctionOfExposure= (Date) obj1[3];
			java.util.Date mliValidityOfExposureLimitStartDate=(Date) obj1[4];
			java.util.Date mliValidityOfExposureLimitEndDate=(Date) obj1[5];
			String statusDescription=(String) obj1[6];
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String dateOfSanctionOfExposure1 = dateFormat.format(dateOfSanctionOfExposure);
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
            String mliValidityOfExposureLimitStartDate1 = dateFormat2.format(mliValidityOfExposureLimitStartDate);
            String mliValidityOfExposureLimitEndDate1 = dateFormat2.format(mliValidityOfExposureLimitEndDate);
            cgtmseExposureMasterBeanObj1.setMliDateOfSanctionOfExposure(dateOfSanctionOfExposure1);
            cgtmseExposureMasterBeanObj1.setMliValidityOfExposureLimitStartDate(mliValidityOfExposureLimitStartDate1);
            cgtmseExposureMasterBeanObj1.setMliValidityOfExposureLimitEndDate(mliValidityOfExposureLimitEndDate1);
            cgtmseExposureMasterBeanObj1.setStatusDescription(statusDescription);
			list1.add(cgtmseExposureMasterBeanObj1);
		}
		ModelAndView modelView=new ModelAndView("cgtmseCreatedExposureLimitDetailsPendingForApproval");
		modelView.addObject("lists", list1);
		
		return modelView;
	}
	
	//Get Exposure Limit Approve Details  whose status is CEMCA
	@RequestMapping(value = "createdExposureLimitDetailsApprovedByChecker1", method = RequestMethod.GET)
	public ModelAndView getcreatedExposureLimitApprovedDetails(@ModelAttribute("command") CGTMSEExposureMasterBean exposureMasterBean,BindingResult result,HttpSession session,Model modelObj) {
		List<Object[]> listObj=cgtmseExposureMasterMakerService.getExposureLimitApprovedDetails();
		List<Object> list2 = new ArrayList<Object>();
		Iterator<Object[]> itr1= listObj.iterator();
		while(itr1.hasNext()){
			Object[] obj1 = (Object[]) itr1.next();
			CGTMSEExposureMasterBean cgtmseExposureMasterBeanObj1=new CGTMSEExposureMasterBean();
			cgtmseExposureMasterBeanObj1.setMliLongName((String) obj1[0]);
			cgtmseExposureMasterBeanObj1.setMliShortName((String) obj1[1]);
			cgtmseExposureMasterBeanObj1.setMliExposureLimit((Long) obj1[2]);
			
			 // Convert Date to String, use format method of SimpleDateFormat class.
			java.util.Date dateOfSanctionOfExposure= (Date) obj1[3];
			java.util.Date mliValidityOfExposureLimitStartDate=(Date) obj1[4];
			java.util.Date mliValidityOfExposureLimitEndDate=(Date) obj1[5];
			
			String statusDescription=(String) obj1[6];
			String checkerId=(String) obj1[7];
			java.util.Date  checkerApproveDate= (Date) obj1[8];
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String dateOfSanctionOfExposure1 = dateFormat.format(dateOfSanctionOfExposure);
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
            String mliValidityOfExposureLimitStartDate1 = dateFormat2.format(mliValidityOfExposureLimitStartDate);
            String mliValidityOfExposureLimitEndDate1 = dateFormat2.format(mliValidityOfExposureLimitEndDate);
            
            cgtmseExposureMasterBeanObj1.setMliDateOfSanctionOfExposure(dateOfSanctionOfExposure1);
            cgtmseExposureMasterBeanObj1.setMliValidityOfExposureLimitStartDate(mliValidityOfExposureLimitStartDate1);
            cgtmseExposureMasterBeanObj1.setMliValidityOfExposureLimitEndDate(mliValidityOfExposureLimitEndDate1);
            cgtmseExposureMasterBeanObj1.setStatusDescription(statusDescription);
            cgtmseExposureMasterBeanObj1.setCheckerId(checkerId);  
            SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd/MM/yyyy");
            String checkerAppDate = dateFormat3.format(checkerApproveDate );
            cgtmseExposureMasterBeanObj1.setCheckerDate(checkerAppDate);
            list2.add(cgtmseExposureMasterBeanObj1);
		}
		ModelAndView modelView=new ModelAndView("cgtmseCreatedExposureLimitApprovedDetails");
		modelView.addObject("list2key", list2);
		return modelView;
	}
	
		
	//Get Exposure Limit Rejected Details   whose status is CEMCR
	@RequestMapping(value = "createdExposureLimitDetailsRejectedByChecker1", method = RequestMethod.GET)
	public ModelAndView getExposureLimitRejectedDetails(@ModelAttribute("command") CGTMSEExposureMasterBean exposureMasterBean,BindingResult result,HttpSession session,Model modelObj) {
		List<Object[]> listObj4=cgtmseExposureMasterMakerService.getExposureLimitRejectedDetails();
		List<Object> list4 = new ArrayList<Object>();
		Iterator<Object[]> itr1= listObj4.iterator();
		while(itr1.hasNext()){
			Object[] obj4 = (Object[]) itr1.next();
			CGTMSEExposureMasterBean cgtmseExposureMasterBeanObj1=new CGTMSEExposureMasterBean();
			
			cgtmseExposureMasterBeanObj1.setMliLongName((String) obj4[0]);
			cgtmseExposureMasterBeanObj1.setMliShortName((String) obj4[1]);
			cgtmseExposureMasterBeanObj1.setMliExposureLimit((Long) obj4[2]);
			
			 // Convert Date to String, use format method of SimpleDateFormat class.
			java.util.Date dateOfSanctionOfExposure= (Date) obj4[3];
			java.util.Date mliValidityOfExposureLimitStartDate=(Date) obj4[4];
			java.util.Date mliValidityOfExposureLimitEndDate=(Date) obj4[5];
			
			String statusDescription=(String) obj4[6];
			
			String checkerId=(String) obj4[7];
			java.util.Date  checkerRejectedDate= (Date) obj4[8];
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String dateOfSanctionOfExposure1 = dateFormat.format(dateOfSanctionOfExposure);
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
            String mliValidityOfExposureLimitStartDate1 = dateFormat2.format(mliValidityOfExposureLimitStartDate);
            String mliValidityOfExposureLimitEndDate1 = dateFormat2.format(mliValidityOfExposureLimitEndDate);
            
            cgtmseExposureMasterBeanObj1.setMliDateOfSanctionOfExposure(dateOfSanctionOfExposure1);
            cgtmseExposureMasterBeanObj1.setMliValidityOfExposureLimitStartDate(mliValidityOfExposureLimitStartDate1);
            cgtmseExposureMasterBeanObj1.setMliValidityOfExposureLimitEndDate(mliValidityOfExposureLimitEndDate1);
            cgtmseExposureMasterBeanObj1.setStatusDescription(statusDescription);
            cgtmseExposureMasterBeanObj1.setCheckerId(checkerId);  
            SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd/MM/yyyy");
            String checkerRejDate = dateFormat3.format(checkerRejectedDate );
            cgtmseExposureMasterBeanObj1.setCheckerDate(checkerRejDate);
            list4.add(cgtmseExposureMasterBeanObj1);
		}
		ModelAndView modelView=new ModelAndView("cgtmseCreatedExposureLimitRejectedDetails");
		modelView.addObject("list4key", list4);
		return modelView;
	}
		
	@RequestMapping(value = "/ExposureListingMaker", method = RequestMethod.GET)
	public ModelAndView getExpoLimitDetails(@ModelAttribute("command") CGTMSEExposureMasterBean cgtmseExposureMasterBeanObj1,BindingResult result, Model modelMsg, HttpSession session){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("expoList",prepareExpotureLimitListofBean(cgtmscCreateExposureLimitMakerService.getAllExposureLimitDetails()));
		model.put("adminlist",userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		model.put("guaranteelist",userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model.put("applicationList",userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		model.put("RPaymentList",userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		model.put("repList",
				userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		model.put("actName", userActivityService.getActivityName("CGTMSEMAKER","ExposureListingMaker"));// Added by Say 31 Jan19
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
		model.put("homePage","cgtmseMakerHome");
		return new ModelAndView("ExposureListingMaker",model);
		
	}
		
	@RequestMapping(value = "/ExposureListMaker", method = RequestMethod.POST)
	public ModelAndView getExpoLmitDetails(@ModelAttribute("command") CGTMSEExposureMasterBean cgtmseExposureMasterBeanObj1,BindingResult result, Model modelMsg, HttpSession session){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("expoList",prepareExpotureLimitListofBean(cgtmscCreateExposureLimitMakerService.getAllExposureLimitDetails()));
		model.put("adminlist",userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		model.put("guaranteelist",userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model.put("applicationList",userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		model.put("RPaymentList",userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		model.put("repList",
				userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		model.put("actName", userActivityService.getActivityName("CGTMSEMAKER","ExposureListingMaker"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
		model.put("homePage","cgtmseMakerHome");
		return new ModelAndView("ExposureListingMaker",model);
	}
		
	@RequestMapping(value = "/ExposureListingMakerWithSuccess", method = RequestMethod.GET)
	public ModelAndView getExpoLimitDetailsWithSuccess(@ModelAttribute("command") CGTMSEExposureMasterBean cgtmseExposureMasterBeanObj1,BindingResult result, Model modelMsg, HttpSession session){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("expoList",prepareExpotureLimitListofBean(cgtmscCreateExposureLimitMakerService.getAllExposureLimitDetails()));
		model.put("adminlist",userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		model.put("guaranteelist",userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model.put("applicationList",userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		model.put("RPaymentList",userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName("CGTMSEMAKER","ExposureListingMaker"));
		model.put("repList",
				userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
		model.put("homePage","cgtmseMakerHome");
		model.put("message","Exposure Created Sucessfully");
		return new ModelAndView("ExposureListingMaker",model);
	}
		
	//@RequestMapping(value = "/modifyExposureLimitByMakerAndSendForApproval", params = "action4", method = RequestMethod.POST)
	/*	public ModelAndView getExpoLimitDetailsForBackFromEditExposure(@ModelAttribute("command") CGTMSEExposureMasterBean cgtmseExposureMasterBeanObj1,BindingResult result, Model modelMsg, HttpSession session){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("expoList",prepareExpotureLimitListofBean(cgtmscCreateExposureLimitMakerService.getAllExposureLimitDetails()));
		model.put("adminlist",userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		model.put("guaranteelist",userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model.put("applicationList",userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		model.put("RPaymentList",userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName("CGTMSEMAKER","ExposureListingMaker"));// Added by Say 31 Jan19
		model.put("homePage","cgtmseMakerHome");
		return new ModelAndView("ExposureListingMaker",model);
	}*/
		
	@RequestMapping(value = "/createExposureLimitByMakerAndSendForApprovalToChecker", params = "action1", method = RequestMethod.POST)
	public ModelAndView getExpoLimitDetailsForBackFromAddExposure(@ModelAttribute("command") CGTMSEExposureMasterBean cgtmseExposureMasterBeanObj1,BindingResult result, Model modelMsg, HttpSession session){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("expoList",prepareExpotureLimitListofBean(cgtmscCreateExposureLimitMakerService.getAllExposureLimitDetails()));
		model.put("adminlist",userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		model.put("guaranteelist",userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model.put("applicationList",userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		model.put("RPaymentList",userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		model.put("actName", userActivityService.getActivityName("CGTMSEMAKER","ExposureListingMaker"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
		model.put("repList",
				userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		model.put("homePage","cgtmseMakerHome");
		return new ModelAndView("ExposureListingMaker",model);
	}
		
	private List<Object> prepareExpotureLimitListofBean(List<Object[]>list){
		List<Object> beans = null;
		beans = new ArrayList<Object>();
	    CGTMSEExposureMasterBean bean = null;
		String SanctionDate="";
		String StartDate="";
		String EndDate="";
		if (list != null && !list.isEmpty()) {					
			for (Object expoVar[] : list) {
				CGTMSEExposureMasterMLIName member1=(CGTMSEExposureMasterMLIName) expoVar[1];
				CGTMSEExposureMasterCheckerGETExposureDetails member2=(CGTMSEExposureMasterCheckerGETExposureDetails) expoVar[0];
				java.util.Date dateOfSanctionOfExposure= (Date) member2.getExposureSanctionDate();
				java.util.Date mliValidityOfExposureLimitStartDate=(Date) member2.getFromDate();
				java.util.Date mliValidityOfExposureLimitEndDate=(Date) member2.getToDate();
				
				SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
				SanctionDate=dateFormat2.format(dateOfSanctionOfExposure);
				StartDate=dateFormat2.format(mliValidityOfExposureLimitStartDate);
				EndDate=dateFormat2.format(mliValidityOfExposureLimitEndDate);
				
				bean = new CGTMSEExposureMasterBean();
				bean.setMliLongName(member1.getMliLongName());
				bean.setMliShortName(member1.getMliShortName());
				bean.setMliExposureLimit(member2.getExposureLimit());
				bean.setMliDateOfSanctionOfExposure(SanctionDate);	
				bean.setGurantee_fee(member2.getGurantee_fee());
				bean.setPay_out_cap(member2.getPay_out_cap());
				bean.setGuranteeCoverage(member2.getGuranteeCoverage());
				bean.setStatus(member2.getStatus());
				bean.setStatusDescription(member2.getStatusDescription());
				bean.setMliValidityOfExposureLimitStartDate(StartDate);
				bean.setMliValidityOfExposureLimitEndDate(EndDate);
				beans.add(bean);
			}
		}else{
				bean = new CGTMSEExposureMasterBean();
				bean.setMliLongName("");
				bean.setMliShortName("");
				bean.setMliExposureLimit(null);
				bean.setMliDateOfSanctionOfExposure(null);						
				bean.setStatus("");
				bean.setStatusDescription("");
				bean.setMliValidityOfExposureLimitStartDate(null);
				bean.setMliValidityOfExposureLimitEndDate(null);
				bean.setGurantee_fee(null);
				bean.setPay_out_cap(null);
				bean.setGuranteeCoverage(null);
				beans.add(bean);
		}
		return beans;
	}
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/ExposureDetailDownload", method = RequestMethod.GET)
	public ModelAndView userRoleCreationdownLoad(
			@ModelAttribute("command") User userDetails, BindingResult result,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException {
		try {
			downloadFileDirPath = request.getRealPath("/")+"/Download";
			String filePath = downloadFileDirPath + File.separator
					+ downloadFileName;
			System.out.println("Request Path 3:"+downloadFileDirPath);
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
			//model.put("expoList",prepareExpotureLimitListofBean(cgtmscCreateExposureLimitMakerService.getAllExposureLimitDetails()));
			List<Object[]> list = cgtmscCreateExposureLimitMakerService.getAllExposureLimitDetails();
			
//			CGTMSEExposureMasterMLIName	listobj1=(CGTMSEExposureMasterMLIName)list;
//			CGTMSEExposureMasterMLIName	listobj2=(CGTMSEExposureMasterMLIName)list;
			// Writing and Downlaoding Excel File

			XSSFWorkbook hwb = new XSSFWorkbook();
			XSSFSheet sheet = hwb.createSheet("ExposureDetailsdownloadFileName");

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
			cell0.setCellValue("MLI Long Name");// Done 1

			XSSFCell cell1 = rowhead.createCell(1);
			cell1.setCellStyle(style);
			cell1.setCellValue("MLI Short Name");// Done 3

			XSSFCell cell2 = rowhead.createCell(2);
			cell2.setCellStyle(style);
			cell2.setCellValue("Exposure Limit");// Done 4

			XSSFCell cell3 = rowhead.createCell(3);
			cell3.setCellStyle(style);
			cell3.setCellValue("Exposure Sanction Date");// Done 5

			XSSFCell cell4 = rowhead.createCell(4);
			cell4.setCellStyle(style);
			cell4.setCellValue("Start Date");// Done 6

			XSSFCell cell5 = rowhead.createCell(5);
			cell5.setCellStyle(style);
			cell5.setCellValue("End Date");// Done 7
			
			XSSFCell cell6 = rowhead.createCell(6);
			cell6.setCellStyle(style);
			cell6.setCellValue("Guarantee Fee(% p.a)");// Done 7
			
			XSSFCell cell7 = rowhead.createCell(7);
			cell7.setCellStyle(style);
			cell7.setCellValue("Pay-Out Cap");// Done 7
		
			int index = 1;
			int sno = 0;
			String SanctionDate="";
			String StartDate="";
			String EndDate="";
			if (list != null && !list.isEmpty()) {					
				for (Object expoVar[] : list) {
//			
				CGTMSEExposureMasterMLIName member11=(CGTMSEExposureMasterMLIName) expoVar[1];
				CGTMSEExposureMasterCheckerGETExposureDetails member22=(CGTMSEExposureMasterCheckerGETExposureDetails) expoVar[0];
				sno++;
				XSSFRow row = sheet.createRow((short) index);
				//
				java.util.Date dateOfSanctionOfExposure= (Date) member22.getExposureSanctionDate();
				java.util.Date mliValidityOfExposureLimitStartDate=(Date) member22.getFromDate();
				java.util.Date mliValidityOfExposureLimitEndDate=(Date) member22.getToDate();
				
				SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
				SanctionDate=dateFormat2.format(dateOfSanctionOfExposure);
				StartDate=dateFormat2.format(mliValidityOfExposureLimitStartDate);
				EndDate=dateFormat2.format(mliValidityOfExposureLimitEndDate);
				row.createCell((short) 0).setCellValue(member11.getMliLongName());// Done
																			// 1
				row.createCell((short) 1).setCellValue(member11.getMliShortName());// Done
																				// 2
				row.createCell((short) 2).setCellValue(member22.getExposureLimit());// Done
																				// 3
				row.createCell((short) 3).setCellValue(SanctionDate);// Done
																			// 4
				row.createCell((short) 4).setCellValue(StartDate);// Done
																				// 5
				row.createCell((short) 5).setCellValue(EndDate);// Done
				// 5
				row.createCell((short) 6).setCellValue(member22.getGurantee_fee());// Done
				// 5
				row.createCell((short) 7).setCellValue(member22.getPay_out_cap());// Done
				// 5

				index++;
			}
			}
			FileOutputStream fileOut = new FileOutputStream(filePath);
			hwb.write(fileOut);
			fileOut.close();

			ModelAndView model = new ModelAndView("newRolePage");

			model.addObject(
					"excelFileDownLoadSuccessfully",
					"File DownLoaded Successfully in this location F:/ExcelReports/nbfcExposuredetails.xls.");

			File downloadFile = new File(filePath);
		//	log.info("downloadFile =" + downloadFile);
			FileInputStream inputStream = new FileInputStream(downloadFile);

			response.setContentLength((int) downloadFile.length());

			// set headers for the response
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"",
					downloadFile.getName());
			response.setHeader(headerKey, headerValue);

			// get output stream of the response
			OutputStream outStream = response.getOutputStream();

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
		//	log.info("Exception == " + e);
			System.out.println("Exception == " + e);
		}
		return null;
	}
			
	 
	 
	@ExceptionHandler(CustomExceptionHandler.class)
	public ModelAndView handleCustomException(CustomExceptionHandler ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();		
		ModelAndView model = new ModelAndView("customException",model1);
		model.addObject("customException", ex.getMessage());
		return model;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();		
		ModelAndView model = new ModelAndView("exception",model1);
		model.addObject("exception", ex.getCause());
		return model;
	}

	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView handleArithException(ArithmeticException ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();		
		ModelAndView model = new ModelAndView("exception",model1);
		model.addObject("exception", ex.getMessage());
		return model;
	}

	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullException(NullPointerException ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();		
		ModelAndView model = new ModelAndView("exception",model1);
		model.addObject("exception", "Data is null");
		return model;
	}

}
