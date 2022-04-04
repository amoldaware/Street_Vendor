package com.nbfc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.nbfc.model.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
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
import org.hibernate.validator.util.privilegedactions.GetConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*import org.json.JSONArray;
import org.json.JSONObject;*/
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jayway.jsonpath.JsonPath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nbfc.bean.*;
import com.nbfc.common.utility.method.Utils;
import com.nbfc.exception.BusinessException;
import com.nbfc.helper.PropertyLoader;
import com.nbfc.service.DisbursementDetailsService;
import com.nbfc.service.LoginService;
import com.nbfc.service.NBFCUserReportService;
import com.nbfc.service.RecoveryUploadService;
import com.nbfc.service.UserActivityService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;


@Controller
public class DisbursementDetailsController 
{
	final static Logger logger = Logger.getLogger(DisbursementDetailsController.class.getName());
	
	@Autowired
	DisbursementDetailsService disbursementDetailsService;
	@Autowired
	LoginService lofinService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	NBFCUserReportService nbfcUserReportService;
	@Autowired
	RecoveryUploadService recoveryUploadService;
	@Autowired
	MenuUtilsController menuUtilsController;
	@Value("${disbursementDetailFilePath}")
	private String downloadFileDirPath;
	@Autowired
	private SessionFactory sessionFactory;
	
	String userId = null;
	Login userDetails = null;
	HttpSession session1 = null;
	NBFCPrivilegeMaster userPrvMst=null;
	
	@RequestMapping(value = "/disbursement-Details", method = RequestMethod.GET)
	public ModelAndView disbursementDetails(Model modeluserRole, HttpSession session) throws BusinessException 
	{
		Map<String, Object> model = new HashMap<String, Object>();
		UserPerivilegeDetails userPri;
		try {
			userId = (String) session.getAttribute("userId");
			String Role = (String) session.getAttribute("uRole");
			if (userId != null) {
				userDetails = lofinService.userDetails(userId);
				userPri = lofinService.getUserPrivlageDtl(userId, "A");
				userPrvMst = lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				session.setAttribute("userRoleClaimPayment", userPrvMst.getPrvCreatedModifiedBy());
				session.setAttribute("userNameId",
						userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID());
				model = menuUtilsController.prepareMenu(session, Role, userId);
				if (model!=null) {
					if(Role.equals("CCHECKER")){
						model.put("homePage", "cgtmseCheckerHome");
					}
					if(Role.equals("CMAKER")){
						model.put("homePage", "cgtmseMakerHome");
					}
					
					if(Role.equals("NCHECKER")){
							model.put("homePage", "nbfcCheckerHome");
						}
						if(Role.equals("NMAKER")){
							model.put("homePage", "nbfcMakerHome");
						}
					return new ModelAndView("disbursementDetails", model);
				} else {
					return new ModelAndView("redirect:/SVLogin.html");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView("redirect:/SVLogin.html");
	}
	
	@RequestMapping(value = "/lender-details", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<LenderDetailsBO>> getLenderDetails(HttpSession session) throws BusinessException {
		List<LenderDetailsBO> lenderDetailsList = null;
		try {
			lenderDetailsList = disbursementDetailsService.getLenderDetails();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(lenderDetailsList, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(lenderDetailsList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/fetch-disbursed-details-data", method = RequestMethod.GET)
	//public @ResponseBody ResponseEntity<String> getDisbursedData(@RequestBody LenderDetailsBO detailsBO,HttpSession session) throws BusinessException
	public @ResponseBody ResponseEntity<String> getDisbursedData(HttpSession session) throws BusinessException
	{
		List<ApplicationDetailsBean> disbursementDtlList = null;
		Map<String,List> lenderDetails = new LinkedHashMap<String,List>();
		int lenderCode = 0;
		String apiDataString = "", retString = "" , msg = "";
		JSONObject apiDataStringObject = null , jsonArrayStatus = null;;
		try {
			System.out.println("Inside...");
			String userId = (String) session.getAttribute("userId");
			/*lenderCode = detailsBO.getLenderCode();
			String bankName = detailsBO.getBankName();
			String mliId = disbursementDetailsService.getLenderData(lenderCode);*/
			lenderCode = 1;
			String bankName = "ALL";
			String mliId = "xxxxxxxxxxxx";
			apiDataString = getDisbursementDetailsAPIData(lenderCode);
			System.out.println("111 apiDataString  \n"+ apiDataString);
			apiDataStringObject = new JSONObject(apiDataString);   
		    jsonArrayStatus = apiDataStringObject.getJSONObject("status");
		    
		    if(jsonArrayStatus.getBoolean("isSuccess"))
		    {
		    	System.out.println("111");
		    	lenderDetails = saveAPIDataToTBL(apiDataString, lenderCode,bankName,mliId);
				getDisbursedData(session);
		    	//getDisbursedData(lenderCode,bankName,session);
		    }
		    else
		    {
		    	System.out.println("222");
		    	int count = disbursementDetailsService.updateLenderDetails();
				if(count > 0){
					msg = "Data Saved Successfully";
				}
				msg = "Data Saved Successfully";
		    }
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new ResponseEntity<>("Error!!!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get-disbursed-detailsData", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<List<ApplicationDetailsBean>> getDisbursedDataOnSearch(@RequestBody LenderDetailsBO detailsBO,HttpSession session) throws BusinessException
	{
		List<ApplicationDetailsBean> list1 = null;
		int lenderCode = 0;
		String fromDate = "", toDate = "", applicationNo = "",guaranteeIssue= "", status="";
		String apiDataString = "",retString = "";
		try 
		{
			lenderCode = detailsBO.getLenderCode();
			fromDate = detailsBO.getFromDate();
			toDate = detailsBO.getToDate();
			applicationNo = detailsBO.getApplicationNo();
			status = detailsBO.getStatus();
			guaranteeIssue = detailsBO.getGuaranteeIssue();
			applicationNo = applicationNo!=null?applicationNo.trim():"";
			if((fromDate!=null && fromDate.trim().length()>0) && (toDate!=null && toDate.trim().length()>0)){
				java.util.Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromDate);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String fromDateNew = formatter.format(date1);
				
				java.util.Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(toDate);
				SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
				String toDateNew = formatter1.format(date2);
				fromDate = fromDateNew.concat("T00:00:00");toDate = toDateNew.concat("T00:00:00");
			}
			if(lenderCode != 0){
				list1 = disbursementDetailsService.getDisburmentDetailsData(lenderCode,fromDate,toDate,applicationNo,status,guaranteeIssue); 
			}
		}	   
		catch (Exception e) 
		{
			e.printStackTrace();
			logger.error(e.getMessage());
			return new ResponseEntity<>(list1, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(list1 , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/exportDisbursementDataToExcel", method = RequestMethod.GET)
	public ResponseEntity<String> exportDisbursementData(@RequestParam int lenderCode,String fromDate,String toDate,String applicationNo,String bankName,String status,String guaranteeIssue, HttpServletRequest request,HttpServletResponse response,HttpSession session)
			throws BusinessException {
		//String downloadFileName = "DisbursementDetails_"+bankName+".xlsx";
		String downloadFileName = "DisbursementDetails.xlsx";
		try {
			if((fromDate!=null && fromDate.trim().length()>0) && (toDate!=null && toDate.trim().length()>0)){
				java.util.Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromDate);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String fromDateNew = formatter.format(date1);
				
				java.util.Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(toDate);
				SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
				String toDateNew = formatter1.format(date2);
				fromDate = fromDateNew.concat("T00:00:00");toDate = toDateNew.concat("T00:00:00");
			}
			
			String contextPath1 = (request).getSession(false).getServletContext().getRealPath("");
			String contextPath = PropertyLoader.changeToOSpath(contextPath1);
			
			
			//contextPath = contextPath+ File.separator+"Download" + downloadFileName;
			//System.out.println("contextPath is ::::" + contextPath);
			//String filePath=downloadFileDirPath+File.separator+downloadFileName;
			//String filePath = contextPath+ File.separator+"Download"+ downloadFileName;
	         // File filePath = new File(contextPath + "\\Download\\DisbursementDetails" + ".csv");
			
			String filePath = downloadFileDirPath + File.separator
					+ downloadFileName;
			System.out.println("File Path==="+filePath);
			File file = new File(downloadFileDirPath);
			boolean isCreated = file.mkdir();
			
			/*if(isCreated){
				File file1 = new File(filePath);
				boolean isExists=file1.exists();
				if(isExists){
				}else{
					file1.createNewFile();
				}
			}*/

			List<ApplicationDetailsBean> list1 = disbursementDetailsService.getDisburmentDetailsData(lenderCode,fromDate,toDate,applicationNo,status,guaranteeIssue);  
			XSSFWorkbook hwb = new XSSFWorkbook();
			XSSFSheet sheet = hwb.createSheet("DownLoadedFileForDisbursementDetails");
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
			sheet.createFreezePane(0, 1); 
			XSSFRow rowhead = sheet.createRow((short)0);
			
			XSSFCell cell0 = rowhead.createCell(0);
			cell0.setCellStyle(style);
			cell0.setCellValue("SR.No.");//Done 1

			XSSFCell cell1 = rowhead.createCell(1);
			cell1.setCellStyle(style);
			cell1.setCellValue("APPLICATION NO");//Done 3
			    
			XSSFCell cell2 = rowhead.createCell(2);
			cell2.setCellStyle(style);
			cell2.setCellValue("APPLICATION DATE");//Done 4
			    
			XSSFCell cell3 = rowhead.createCell(3);
			cell3.setCellStyle(style);
			cell3.setCellValue("APPLICANT NAME");//Done 5
			    
			XSSFCell cell4 = rowhead.createCell(4);
			cell4.setCellStyle(style);
			cell4.setCellValue("APPLICANT GENDER");//Done 6
			    
			XSSFCell cell5 = rowhead.createCell(5);
			cell5.setCellStyle(style);
			cell5.setCellValue("APPLICANT DOB");//Done 7
			
			XSSFCell cell6 = rowhead.createCell(6);
			cell6.setCellStyle(style);
			cell6.setCellValue("MINORITY COMMUNITY");//Done 7
	        
		     XSSFCell cell7 = rowhead.createCell(7);
		     cell7.setCellStyle(style);
		     cell7.setCellValue("ACCOUNT NUMBER");//Done 9
		        
		     XSSFCell cell8 = rowhead.createCell(8);
		     cell8.setCellStyle(style);
		     cell8.setCellValue("BANK AND BRANCH NAME");//Done 10
		     
		     XSSFCell cell9 = rowhead.createCell(9);
		     cell9.setCellStyle(style);
		     cell9.setCellValue("CATEGORY");
		     
		     XSSFCell cell10 = rowhead.createCell(10);
		     cell10.setCellStyle(style);
		     cell10.setCellValue("CURRENT ADDRESS");
		     
		     XSSFCell cell11 = rowhead.createCell(11);
		     cell11.setCellStyle(style);
		     cell11.setCellValue("DISBURSED AMOUNT");
		     
		     XSSFCell cell12 = rowhead.createCell(12);
		     cell12.setCellStyle(style);
		     cell12.setCellValue("DISBURSED DATE");
		     
		     XSSFCell cell13 = rowhead.createCell(13);
		     cell13.setCellStyle(style);
		     cell13.setCellValue("DURATION OF VENDING");
		     
		     XSSFCell cell14 = rowhead.createCell(14);
		     cell14.setCellStyle(style);
		     cell14.setCellValue("IFSC CODE");//Done 11
		      
		     XSSFCell cell15 = rowhead.createCell(15);
		     cell15.setCellStyle(style);
		     cell15.setCellValue("IS DISBURSED");//Done 12
		        
		     XSSFCell cell16 = rowhead.createCell(16);
		     cell16.setCellStyle(style);
		     cell16.setCellValue("LENDER NAME AND BRANCH");//Done 13
		        
		     XSSFCell cell17 = rowhead.createCell(17);
		     cell17.setCellStyle(style);
		     cell17.setCellValue("LOAN ACCOUNT NUMBER");//Done 14
		        
		     XSSFCell cell18 = rowhead.createCell(18);
		     cell18.setCellStyle(style);
		     cell18.setCellValue("MARITAL STATUS NAME");//Done 15
		        
		     XSSFCell cell19 = rowhead.createCell(19);
		     cell19.setCellStyle(style);
		     cell19.setCellValue("MOBILE NUMBER");//Done 16
		        
		     XSSFCell cell20 = rowhead.createCell(20);
		     cell20.setCellStyle(style);
		     cell20.setCellValue("MOBILE VENDOR");//Done 17
		        
		     XSSFCell cell21 = rowhead.createCell(21);
		     cell21.setCellStyle(style);
		     cell21.setCellValue("NEAREST MOBILE LM");//Done 18
		        
		     XSSFCell cell22 = rowhead.createCell(22);
		     cell22.setCellStyle(style);
		     cell22.setCellValue("PERMANANT ADDRESS");//Done 19

		     XSSFCell cell23 = rowhead.createCell(23);
		     cell23.setCellStyle(style);
		     cell23.setCellValue("SVA VENDING ACTIVITY NAME");//Done 21
		     
		     XSSFCell cell24 = rowhead.createCell(24);
		     cell24.setCellStyle(style);
		     cell24.setCellValue("SANCTION DATE");//Done 21
		     
		     XSSFCell cell25 = rowhead.createCell(25);
		     cell25.setCellStyle(style);
		     cell25.setCellValue("SANCTION AMOUNT");//Done 21
		     
		     XSSFCell cell26 = rowhead.createCell(26);
		     cell26.setCellStyle(style);
		     cell26.setCellValue("LOAN TENURE");//Done 21
		     
		     XSSFCell cell27 = rowhead.createCell(27);
		     cell27.setCellStyle(style);
		     cell27.setCellValue("MORATORIUM MONTHS");//Done 21
		     
		     XSSFCell cell28 = rowhead.createCell(28);
		     cell28.setCellStyle(style);
		     cell28.setCellValue("RATE OF INTEREST");//Done 21
		     
		     XSSFCell cell29 = rowhead.createCell(29);
		     cell29.setCellStyle(style);
		     cell29.setCellValue("NAME OF CIGCLG");//Done 21
		     
		     XSSFCell cell30 = rowhead.createCell(30);
		     cell30.setCellStyle(style);
		     cell30.setCellValue("MEMBER OF CIGCLG");//Done 21
		     
		     XSSFCell cell31 = rowhead.createCell(31);
		     cell31.setCellStyle(style);
		     cell31.setCellValue("LOAN TERM");//Done 21
		     
		     XSSFCell cell32 = rowhead.createCell(32);
		     cell32.setCellStyle(style);
		     cell32.setCellValue("STATUS");//Done 21
	     
		     int index = 1;
				int sno = 1;
				String name = "";
				Iterator<ApplicationDetailsBean> itr2 = list1.iterator();
				while (itr2.hasNext()) {
					ApplicationDetailsBean obj1 = (ApplicationDetailsBean) itr2.next();
					XSSFRow row = sheet.createRow((short) index);
					row.createCell((short) 0).setCellValue(sno);//Done 0
					row.createCell((short) 1).setCellValue(obj1.getApplicationNo());//Done 1
				    row.createCell((short) 2).setCellValue(obj1.getApplicationDate());//Done 2
					row.createCell((short) 3).setCellValue(obj1.getPersonalDetails().getApplicant_Name());//Done 3
					row.createCell((short) 4).setCellValue(obj1.getPersonalDetails().getApplicant_Gender());//Done 4
					row.createCell((short) 5).setCellValue(obj1.getPersonalDetails().getApplicant_DOB().substring(0, obj1.getPersonalDetails().getApplicant_DOB().indexOf("T")));//Done 5
					row.createCell((short) 6).setCellValue(obj1.getPersonalDetails().getMinorityCommunity());//Done 5
					row.createCell((short) 7).setCellValue(obj1.getBankAccountDetails().getAccountNo());//Done 6
					row.createCell((short) 8).setCellValue(obj1.getBankAccountDetails().getBankName() +" "+ obj1.getBankAccountDetails().getBranchName());//Done 7
					row.createCell((short) 9).setCellValue(obj1.getCategory());//Done 8
					row.createCell((short) 10).setCellValue(obj1.getLenderCurrentAddress().getHouseNo()+" "+obj1.getLenderCurrentAddress().getTown_Dist()+" "+obj1.getLenderCurrentAddress().getVillage()+" "+obj1.getLenderCurrentAddress().getState()+" "+obj1.getLenderCurrentAddress().getPin());//Done 8
					row.createCell((short) 11).setCellValue(obj1.getDisbursedAmount());//Done 10
					row.createCell((short) 12).setCellValue(obj1.getDisbursedDt().substring(0, obj1.getDisbursedDt().indexOf("T")));//Done 11
					row.createCell((short) 13).setCellValue(obj1.getDurationOfVending());//Done 12
					row.createCell((short) 14).setCellValue(obj1.getBankAccountDetails().getIfsc());//Done 13
					row.createCell((short) 15).setCellValue(obj1.getIsDisbursed());//Done 14
					row.createCell((short) 16).setCellValue(obj1.getLenderName()+", "+obj1.getLenderBranch());//Done 15
					row.createCell((short) 17).setCellValue(obj1.getLoanAccountNumber());//Done 16
					row.createCell((short) 18).setCellValue(obj1.getPersonalDetails().getMaritalStatusName());//Done 17
					row.createCell((short) 19).setCellValue(obj1.getPersonalDetails().getMobileNo());//Done 18
					row.createCell((short) 20).setCellValue(obj1.getAreaOfVending().getMobileVendor());//Done 19
					row.createCell((short) 21).setCellValue(obj1.getAreaOfVending().getNearestMobileLM());//Done 20
					row.createCell((short) 22).setCellValue(obj1.getLenderPermanentAddress().getHouseNo()+" "+obj1.getLenderPermanentAddress().getTown_Dist()+" "+obj1.getLenderPermanentAddress().getVillage()+" "+obj1.getLenderPermanentAddress().getState()+" "+obj1.getLenderPermanentAddress().getPin());//Done 19
					row.createCell((short) 23).setCellValue(obj1.getSvaVendingActivityName());//Done 22
					row.createCell((short) 24).setCellValue(obj1.getSanctionedDt().substring(0, obj1.getSanctionedDt().indexOf("T")));//Done 22
					row.createCell((short) 25).setCellValue(obj1.getSanctionedAmount());//Done 22
					row.createCell((short) 26).setCellValue(obj1.getLoanTenure());//Done 22
					row.createCell((short) 27).setCellValue(obj1.getMoratoriumMonths());//Done 22
					row.createCell((short) 28).setCellValue(obj1.getRateOfInterest());//Done 22
					row.createCell((short) 29).setCellValue(obj1.getNameOfCIGGLG());//Done 22
					row.createCell((short) 30).setCellValue(obj1.getMemberOfCIGGLG());//Done 22
					row.createCell((short) 31).setCellValue(obj1.getLoanTerm());//Done 22
					row.createCell((short) 32).setCellValue(obj1.getStatus());//Done 22
					index++;
					sno++;
				}
				FileOutputStream fileOut = new FileOutputStream(filePath);
				hwb.write(fileOut);
				fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new ResponseEntity<>("Please Close the Open Excel File Before Export", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("File Downloaded on Path D://DisbursementDetails!!" , HttpStatus.OK);
	}
	
	public List<ApplicationDetailsBean> getDisburmentDetailsFetchData(Map<String,List> lenderDetails)
	{
		List<ApplicationDetailsBean> applicationDetailsBeanList1 = new ArrayList<>();
		List<ApplicationDetailsBean> ApplicationDetailsList = null;
		List<PersonalDetailsBean> PersonalDetailsList = null;
		List<BankAccountDetailsBean> BankAccountDetailsList = null;
		List<AreaOfVendingBean> BusinessDetailsList = null;
		List<PermanentAddressBean> PermanentAddressList = null;
		List<CurrentAddressBean> CurrentAddressList = null;
		List<LenderBean> LenderDetailsList = null;
		try {
			if (lenderDetails != null && !lenderDetails.isEmpty()) {
				for (String data : lenderDetails.keySet()) {
					ApplicationDetailsList = lenderDetails.get("ApplicationDetails");
					PersonalDetailsList = lenderDetails.get("PersonalDetails");
					BankAccountDetailsList = lenderDetails.get("BankAccountDetails");
					BusinessDetailsList = lenderDetails.get("BusinessDetails");
					PermanentAddressList = lenderDetails.get("PermanentAddress");
					CurrentAddressList = lenderDetails.get("CurrentAddress");
					LenderDetailsList = lenderDetails.get("LenderDetails");
				}
				int count = 0;
				for (ApplicationDetailsBean applicationDetailsBeanList : ApplicationDetailsList) {
					applicationDetailsBeanList.setPersonalDetails(PersonalDetailsList.get(count));
					applicationDetailsBeanList.setBankAccountDetails(BankAccountDetailsList.get(count));
					applicationDetailsBeanList.setAreaOfVending(BusinessDetailsList.get(count));
					applicationDetailsBeanList.setLenderPermanentAddress(PermanentAddressList.get(count));
					applicationDetailsBeanList.setLenderCurrentAddress(CurrentAddressList.get(count));
					applicationDetailsBeanList.setLenderBean(LenderDetailsList.get(count));
					applicationDetailsBeanList1.add(applicationDetailsBeanList);
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return applicationDetailsBeanList1;
	}
	public void sendApplicationNoToAPI(Map<String,String> applicationNoList,int lenderCode)
	{
		JSONObject lenderJson = new JSONObject();
		JSONArray array1 = new JSONArray();
		String url = "";
		try
		{
			if(applicationNoList != null && applicationNoList.size() > 0){
				for(String applicationNo : applicationNoList.keySet())
				{
					String[] loanTermStatusData = applicationNoList.get(applicationNo).split(":"); 
					JSONObject applicationNoObject = new JSONObject();
					applicationNoObject.put("ApplicationNo", applicationNo);
					applicationNoObject.put("loanTerm",loanTermStatusData[0]);
					applicationNoObject.put("status", loanTermStatusData[1]);
					array1.put(applicationNoObject);
				}
				//url = "https://uatapi.udyamimitra.in/API/API/CGTMSE/GetClaimDisbursedApplications";
				url = "https://api.udyamimitra.in/API/CGTMSE/GetClaimDisbursedApplications";
				lenderJson.put("applicationNumber", array1);
				lenderJson.put("LenderCode", lenderCode);
				
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost post = new HttpPost(url);
				StringEntity se = new StringEntity(lenderJson.toString());
				se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json; charset=utf-8"));
				post.setEntity(se);
				HttpResponse response = httpClient.execute(post);
				if(response.getStatusLine().getStatusCode() == 200)
				{
					System.out.println("Data Sent Successfully!!!!");
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
	public String getDisbursementDetailsAPIData(int lenderCode)
	{
		String url = "", jsonString = "";
		//ArrayList<String> applicationNumber = new ArrayList<>();
		JSONArray applicationNumber = new JSONArray();
		JSONObject lenderJson = new JSONObject();
		try
		{
			//url = "https://uatapi.udyamimitra.in/API/API/CGTMSE/GetClaimDisbursedApplications";
			url = "https://api.udyamimitra.in/API/CGTMSE/GetClaimDisbursedApplications";
			lenderJson.put("applicationNumber", applicationNumber);
			//lenderJson.put("LenderCode", lenderCode); Changes done because all data want on one click
			
			System.out.println("lenderJson is :::" + lenderJson.toString());
			
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			StringEntity se = new StringEntity(lenderJson.toString());
			se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json; charset=utf-8"));
			post.setEntity(se);
			HttpResponse response = httpClient.execute(post);
			System.out.println("response.getStatusLine() is :::" + response.getStatusLine().toString());
			if(response.getStatusLine().getStatusCode() == 200)
			{
				System.out.println("Inside If ....");
				jsonString  = EntityUtils.toString(response.getEntity());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return jsonString;
	}
	
	public Map<String,List> saveAPIDataToTBL(String apiDataString,int lenderCode,String bankName,String mli_id) throws BusinessException
	{
		ApplicationDetailsBean applicationDetails = null;
		Map<String,List> lenderDetails = new LinkedHashMap<String,List>();
		List<LenderBean> lenderBeanList = new ArrayList<>();
		List<ApplicationDetailsBean> applicationDetailsList = new ArrayList<>();
		List<PersonalDetailsBean> personalDetailsList = new ArrayList<>();
		List<BankAccountDetailsBean> bankAccountDetailsList = new ArrayList<>();
		List<AreaOfVendingBean> areaOfVendingList = new ArrayList<>();
		List<PermanentAddressBean> permanentAddressList = new ArrayList<>();
		List<CurrentAddressBean> currentAddressList = new ArrayList<>();
		String finalString = "" , applicationNo = "" , loanTermStatus = "";
		JSONObject apiDataStringObject = null,jsonArrayStatus = null,allAPIObjectData = null;
		JSONArray jsonArrayData = null, applicationsArray = null;
		JSONObject application_Details = null, personalDetails = null, communicationDetail = null,permanent_Address = null,current_Address=null,areaOfVending=null,business_Details=null,bankAccountDetails=null;
		List<String> applicationNoList = new ArrayList<>();
		List<String> longTermStatusList = new ArrayList<>();
		Map<String,String> sendingApplicationListMap = new HashMap<>();
		LenderBean lenderBean = new LenderBean();
		boolean isSuccess = true;
		try
		{
			apiDataStringObject = new JSONObject(apiDataString);   
		    jsonArrayStatus = apiDataStringObject.getJSONObject("status");
		    
		    if(jsonArrayStatus.getBoolean("isSuccess"))
		    {
		    	jsonArrayData = apiDataStringObject.getJSONArray("data");
		    	allAPIObjectData = jsonArrayData.getJSONObject(0);
		    	applicationsArray = allAPIObjectData.getJSONArray("applications");
		    	
 		    	for(int i=0;i<applicationsArray.length();i++)
		    	{
		    		application_Details = (JSONObject) allAPIObjectData.getJSONArray("applications").getJSONObject(i).get("application_Details");
					personalDetails = (JSONObject) allAPIObjectData.getJSONArray("applications").getJSONObject(i).get("personalDetails");
					communicationDetail = (JSONObject) allAPIObjectData.getJSONArray("applications").getJSONObject(i).get("communicationDetail");
					business_Details = (JSONObject) allAPIObjectData.getJSONArray("applications").getJSONObject(i).get("business_Details");
					bankAccountDetails = (JSONObject) allAPIObjectData.getJSONArray("applications").getJSONObject(i).get("bankAccountDetails");
					if(communicationDetail.has("permanent_Address"))
					{
						permanent_Address = communicationDetail.getJSONObject("permanent_Address");
					}
					if(communicationDetail.has("current_Address"))
					{
						current_Address = communicationDetail.getJSONObject("current_Address");
					}
					if(business_Details.has("areaOfVending"))
					{
						 areaOfVending = business_Details.getJSONObject("areaOfVending");
					}
					
					Gson gson = new Gson();
				    applicationDetails = gson.fromJson(application_Details.toString(), ApplicationDetailsBean.class);
					PersonalDetailsBean personal_Details = gson.fromJson(personalDetails.toString(), PersonalDetailsBean.class);
					BankAccountDetailsBean bankAccount_Details = gson.fromJson(bankAccountDetails.toString(), BankAccountDetailsBean.class);
					AreaOfVendingBean businessDetails = gson.fromJson(areaOfVending.toString(), AreaOfVendingBean.class);
					PermanentAddressBean permanentAddress = gson.fromJson(permanent_Address.toString(), PermanentAddressBean.class);
					CurrentAddressBean currentAddress = gson.fromJson(current_Address.toString(), CurrentAddressBean.class);
					//LenderBean lenderBean = disbursementDetailsService.getLenderData(lenderCode);
					//lenderBean.setBankName(bankName);
					lenderBean.setLender_bankName(bankName);
					lenderBean.setLenderCode(lenderCode);
					lenderBean.setMli_id(mli_id);
					lenderBean.setUpload_date(new Date());
					
					applicationDetailsList.add(applicationDetails);
					personalDetailsList.add(personal_Details);
					bankAccountDetailsList.add(bankAccount_Details);
					areaOfVendingList.add(businessDetails);
					permanentAddressList.add(permanentAddress);
					currentAddressList.add(currentAddress);
					lenderBeanList.add(lenderBean);
					
					lenderDetails.put("ApplicationDetails",applicationDetailsList);
					lenderDetails.put("PersonalDetails",personalDetailsList);
					lenderDetails.put("BankAccountDetails",bankAccountDetailsList);
					lenderDetails.put("BusinessDetails",areaOfVendingList);
					lenderDetails.put("PermanentAddress",permanentAddressList);
					lenderDetails.put("CurrentAddress",currentAddressList);
					lenderDetails.put("LenderDetails",lenderBeanList);
					
					applicationNo = applicationDetails.getApplicationNo();
					loanTermStatus = applicationDetails.getLoanTerm() +":" + applicationDetails.getStatus();
					sendingApplicationListMap.put(applicationNo,loanTermStatus);	
		    	}
 		    	try{
 		    		finalString = disbursementDetailsService.saveLenderDetails(lenderDetails);
 		    		System.out.println("Final String is :::" + finalString);
 		    	}catch(Exception e){
 		    		System.out.println("Inside Catch..1");
 		    		isSuccess = false;
 		    	}
		    }
		    else
		    {
		    	lenderDetails = null;
		    }
		}
		catch(Exception e)
		{
			System.out.println("Inside Catch..2");
			isSuccess = false;
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		System.out.println("All API Data Saved Successfully!!!" + isSuccess);
		if(isSuccess == true && finalString.contains("Success")){
		     //sendApplicationNoToAPI(sendingApplicationListMap,lenderCode); SARITA 20112021
		}
		return lenderDetails;
	}
}
