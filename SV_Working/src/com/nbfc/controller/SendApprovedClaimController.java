package com.nbfc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.nbfc.bean.APIClaimDataBO;
import com.nbfc.bean.ClaimErrorFileExcelDataBO;
import com.nbfc.bean.ClaimUploadCgtmseBO;
import com.nbfc.common.utility.method.Utils;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.ApplicationDetailsBean;
import com.nbfc.model.Login;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.ClaimUploadCgtmseService;
import com.nbfc.service.LoginService;
import com.nbfc.service.NBFCUserReportService;
import com.nbfc.service.RecoveryUploadService;
import com.nbfc.service.UserActivityService;

import net.minidev.json.JSONArray;

@Controller
public class SendApprovedClaimController 
{
	final static Logger logger = Logger.getLogger(DisbursementDetailsController.class.getName());
	
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
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	ClaimUploadCgtmseService cgtmseService;
	
	
	String userId = null;
	Login userDetails = null;
	HttpSession session1 = null;
	NBFCPrivilegeMaster userPrvMst=null;
	List<ApplicationDetailsBean> appListData = null;
	
	@RequestMapping(value = "/claimApprovedData", method = RequestMethod.GET)
	public ModelAndView disbursementDetailsData(Model modeluserRole, HttpSession session) throws BusinessException 
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
					return new ModelAndView("claimApprovedData", model);
				} else {
					return new ModelAndView("redirect:/SVLogin.html");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView("redirect:/SVLogin.html");
	}
	
	
	@RequestMapping(value = "/getApprovedSubmittedData", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<ClaimUploadCgtmseBO>> getApprovedSubmittedData(@RequestParam String submitStatus) throws BusinessException 
	{
		List<ClaimUploadCgtmseBO> allCGTMSE = null;
		try
		{
			allCGTMSE = cgtmseService.getApprovedSubmittedData(submitStatus);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<>(allCGTMSE, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sendclaimdatathroughapi", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> sendClaimDataUsingAPI(@RequestParam String uploadId,HttpSession session) throws BusinessException
	{
		List<APIClaimDataBO> ClaimAPIData = null;
		JSONObject finalJSONData = new JSONObject();
		try
		{
			System.out.println("Inside Method...." + uploadId);
			
			ClaimAPIData = cgtmseService.getRequiredDataToSendThroughAPI(uploadId);
			finalJSONData = createJSONAPIData(ClaimAPIData);
			sendJSONAPIData(finalJSONData);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public JSONObject createJSONAPIData(List<APIClaimDataBO> ClaimAPIData)
	{
		List<JSONObject> apiclaim_DetailsList = new  ArrayList<JSONObject>();
		JSONObject finalAPIClmObject = new JSONObject();
		JSONObject apiclaim_Details = null;
		JSONArray jsonArray = new JSONArray();
		try
		{
			for(APIClaimDataBO clmData : ClaimAPIData)
			{
				apiclaim_Details = new JSONObject();
				apiclaim_Details.put("PMSNumber", checkNull(clmData.getpMSNumber()));
				apiclaim_Details.put("BorrowerName", checkNull(clmData.getBorrowerName()));
				apiclaim_Details.put("ClaimStatus", checkNull(clmData.getClaimStatus()));
				apiclaim_Details.put("LoanAccountNumber", checkNull(clmData.getLoanAccountNumber()));
				apiclaim_Details.put("DateOfNPA", checkNull(clmData.getDateOfNPA()));
				apiclaim_Details.put("OutstandingLoanAmount",checkNull(clmData.getOutstandingLoanAmount()));
				apiclaim_Details.put("DateOfClaimSubmission",checkNull(clmData.getDateOfClaimSubmission()));
				apiclaim_Details.put("DateOfClaimSettlement",checkNull(clmData.getDateOfClaimSettlement()));
				apiclaim_Details.put("ClaimSettledByCGTMSE", checkNull(clmData.getClaimSettledByCGTMSE()));
				apiclaim_Details.put("LoanTerm", checkNull(clmData.getLoanTerm()));
				
				System.out.println("Generated JSON Object is ::::" + apiclaim_Details.toString());
				apiclaim_DetailsList.add(apiclaim_Details);
			}
			finalAPIClmObject.put("ClaimSettlementsRequest",apiclaim_DetailsList);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		System.out.println("Final JSON Object is ::::" + finalAPIClmObject.toString());
		
		return finalAPIClmObject;
	}
	
	public String sendJSONAPIData(JSONObject finalJSONData) throws BusinessException
	{
		JSONObject finalAPIClmObject = new JSONObject();
		String url = "" , jsonString = "";
		JSONObject apiDataStringObject = null , jsonArrayStatus = null;
		try
		{
			url = "https://uatapi.udyamimitra.in/API/API/CGTMSE/CGTMSENPAClaimSettlementData";
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			StringEntity se = new StringEntity(finalJSONData.toString());
			se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json; charset=utf-8"));
			post.setEntity(se);
			
			HttpResponse response = httpClient.execute(post);
			System.out.println("response.getStatusLine() is :::" + response.getStatusLine().toString());
			System.out.println("12345::::" + response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode() == 200)
			{
				jsonString  = EntityUtils.toString(response.getEntity());
				
				apiDataStringObject = new JSONObject(jsonString);
				jsonArrayStatus = apiDataStringObject.getJSONObject("status");
				System.out.println("Status of API is ::::" + jsonArrayStatus.toString());
				if(jsonArrayStatus.getBoolean("isSuccess"))
				{
					updateAPIReturnData(apiDataStringObject);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return jsonString;
	}
	
	
	public String updateAPIReturnData(JSONObject apiDataStringObject) throws BusinessException
	{
		JSONObject jsonArrayStatus = null;
		JSONObject apiJSONData = null , allJSONData = null;
		org.json.JSONArray jsonArrayData = null;
		Map<String,String> apiRtnData = new HashMap<>();
		String updateAPIData = "";
		try
		{
			jsonArrayStatus = apiDataStringObject.getJSONObject("status");
			if(jsonArrayStatus.getBoolean("isSuccess"))
			{
				jsonArrayData = apiDataStringObject.getJSONArray("data");
				for(int i=0;i<jsonArrayData.length();i++)
				{
					allJSONData = (JSONObject)jsonArrayData.getJSONObject(i);
					
					apiRtnData.put(jsonArrayData.getJSONObject(i).getString("pmsNumber"), 
							       jsonArrayData.getJSONObject(i).getString("loanTerm"));
				}	
				
				if(apiRtnData != null && apiRtnData.size() > 0){
					updateAPIData = cgtmseService.updateClaimSentData(apiRtnData);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return "";
	}
	
	@RequestMapping(value = "/download-claimapi-data", method = RequestMethod.GET)
	public  @ResponseBody String downloadClaimAPIExcelFile(@RequestParam String uploadId, HttpServletResponse response)
			throws BusinessException {
		try {
			List<ClaimErrorFileExcelDataBO> excelList = cgtmseService.downloadUploadedAPIClaimFile(uploadId, "S");
			String optionalColumns[] = {};
			Utils.writeToExcel("Claim_Submitted_Data", "Claim_Submitted_Data", optionalColumns, excelList, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;	
		}
	
	public String checkNull(String str){
		if(str != null && str.trim().length()>0){
			return str.trim();
		}
		else {
			return "";
		}
	}
}
