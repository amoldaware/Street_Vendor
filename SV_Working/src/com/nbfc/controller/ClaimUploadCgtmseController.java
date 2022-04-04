package com.nbfc.controller;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.nbfc.bean.BankMandateFormBo;
import com.nbfc.bean.ClaimErrorFileExcelDataBO;
import com.nbfc.bean.ClaimUploadCgtmseBO;
import com.nbfc.bean.ClaimUploadDetailsBO;
import com.nbfc.common.utility.method.Utils;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.ClaimUploadHeader;
import com.nbfc.model.Login;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.BankMandateFormService;
import com.nbfc.service.ClaimUploadCgtmseService;
import com.nbfc.service.ClaimUploadService;
import com.nbfc.service.LoginService;
import com.nbfc.service.NBFCUserReportService;
import com.nbfc.service.UserActivityService;
import com.nbfc.utility.ApprovalStatus;

@Controller
public class ClaimUploadCgtmseController {
	final static Logger logger = Logger.getLogger(ClaimUploadCgtmseController.class.getName());

	@Autowired
	ClaimUploadService claimUploadService;
	@Autowired
	LoginService lofinService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	NBFCUserReportService nbfcUserReportService;
	@Autowired
	BankMandateFormService bankMandateFormService;

	@Autowired
	ClaimUploadCgtmseService cgtmseService;
	@Autowired
	MenuUtilsController  menuUtilsController;

	String userId = null;
	Login userDetails = null;

	/* umesh code */
	HttpSession session1 = null;
	String msg=null;
	@RequestMapping(value = "/claim-upload-cgtmse", method = RequestMethod.GET)
	public ModelAndView claimUploadCgtmse(Model modeluserRole, HttpSession session) throws BusinessException {
		Map<String, Object> model = new HashMap<String, Object>();
		UserPerivilegeDetails userPri;
		NBFCPrivilegeMaster userPrvMst;
		try {
			session1 = session;
			userId = (String) session.getAttribute("userId");
			String Role = (String) session.getAttribute("uRole");
			if (userId != null) {
				
				userDetails = lofinService.userDetails(userId);
				userPri = lofinService.getUserPrivlageDtl(userId, "A");
				userPrvMst = lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				model = menuUtilsController.prepareMenu(session, Role, userId);
				if (model!=null) {
					if(Role.equals("CCHECKER")){
						model.put("homePage", "cgtmseCheckerHome");
					}
					if(Role.equals("CMAKER")){
						model.put("homePage", "cgtmseMakerHome");
					}
					return new ModelAndView("cliamUploadCgtmse", model);
				} else {
					return new ModelAndView("redirect:/SVLogin.html");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView("redirect:/SVLogin.html");
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	//public @ResponseBody ResponseEntity<List<ClaimUploadCgtmseBO>> getAllCGTMSE() throws BusinessException {
	public @ResponseBody ResponseEntity<List<ClaimUploadCgtmseBO>> getAllCGTMSE(@RequestParam String apprvStatus) throws BusinessException {
		List<ClaimUploadCgtmseBO> allCGTMSE = null;
		try {
			//allCGTMSE = cgtmseService.getAllCGTMSE();
			allCGTMSE = cgtmseService.getAllCGTMSE(apprvStatus);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<ClaimUploadCgtmseBO>(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(allCGTMSE, HttpStatus.OK);
	}

	@RequestMapping(value = "/getfinancial-data", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ClaimUploadDetailsBO> getFinancialData(@RequestParam String mliId)
			throws BusinessException {
		ClaimUploadDetailsBO bo = null;
		try {
			bo = claimUploadService.getClaimUploadByMemberBankId(mliId);
			if (bo == null) {
				bo = new ClaimUploadDetailsBO();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(bo, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(bo, HttpStatus.OK);
	}

	@RequestMapping(value = "/update-cgtmse-mli", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> updateCgtmseMli(@RequestParam("objField") String objString)
			throws BusinessException {
		boolean isSuccess = false;
		try {
			Gson gson = new Gson();
			ClaimUploadCgtmseBO bo = gson.fromJson(objString, ClaimUploadCgtmseBO.class);
			if (bo != null) {
				if(bo.getApprovalStatus().equalsIgnoreCase("Approve")) {
					bo.setApprovalStatus(ApprovalStatus.APPROVED.getValue());
					msg="Record updated successfully.";
				}else if(bo.getApprovalStatus().equalsIgnoreCase("Return")) {
					bo.setApprovalStatus(ApprovalStatus.REJECTED.getValue());
					msg="File returned";
				}
				bo.setUserId(userId);
				
			}
			isSuccess = cgtmseService.updateMliCgtmse(bo);
			if (isSuccess) {
				return new ResponseEntity<>(msg, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<>("Something Went Wrong...!", HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/download-cgtmse-data", method = RequestMethod.GET)
	public  @ResponseBody String downloadExcelFileCGTMSE(@RequestParam String uploadId, HttpServletResponse response)
			throws BusinessException {
		try {
			List<ClaimErrorFileExcelDataBO> excelList = cgtmseService.downloadUploadedFileList(uploadId, "S");
			String optionalColumns[] = {};
			Utils.writeToExcel("Claim_Uploaded_CGTMSE", "Claim_Uploaded_CGTMSE", optionalColumns, excelList, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;	
		}

	@RequestMapping(value = "/downloadFile-mgmt-certificate", method = RequestMethod.GET)
	
	public @ResponseBody String downloadFileMgmtCertificate(@RequestParam String claimNumber, HttpServletResponse response)
			throws BusinessException {
		try {
			ClaimUploadHeader mgmt = cgtmseService.downloadFileMgmtCertificate(claimNumber, ApprovalStatus.SUBMITTED_TO_CGTMSE.getValue());
			if (mgmt != null) {
				byte[] fileData = mgmt.getMgmtFileDate();
				Path source = Paths.get(mgmt.getMgmtCertificateName());
				String contentType = Files.probeContentType(source);

				response.setContentType(contentType);
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + mgmt.getMgmtCertificateName() + "\"");
				OutputStream os = response.getOutputStream();
				os.write(fileData);
				os.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value = "/download-mandate", method = RequestMethod.GET)
	@ResponseBody
	public void downloadMandateFileBankApprovalData(@RequestParam String mliId, HttpServletResponse response,HttpSession session)
			throws BusinessException {
		try {
			String usrMandate=(String)session.getAttribute("bankMandateUser");
			BankMandateFormBo bankMadateData = bankMandateFormService.findDataByUsrId(mliId, usrMandate);
			if (bankMadateData != null) {
				byte[] fileData = bankMadateData.getFileData();
				Path source = Paths.get(bankMadateData.getUploadDoc());
				String contentType = Files.probeContentType(source);

				response.setContentType(contentType);
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" +bankMadateData.getUploadDoc() + "\"");
				OutputStream os = response.getOutputStream();
				os.write(fileData);
				os.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
