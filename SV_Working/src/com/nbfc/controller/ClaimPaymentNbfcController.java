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

import com.nbfc.bean.ClaimErrorFileExcelDataBO;
import com.nbfc.bean.ClaimUploadCgtmseBO;
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
public class ClaimPaymentNbfcController 
{
	final static Logger logger = Logger.getLogger(ClaimPaymentNbfcController.class.getName());
	
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
	@RequestMapping(value = "/ClaimPaymentReport", method = RequestMethod.GET)
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
					model.put("homePage", "nbfcMakerHome");
					return new ModelAndView("claimPaymentNbfc", model);
				} else {
					return new ModelAndView("redirect:/SVLogin.html");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView("redirect:/SVLogin.html");
	}
	
	@RequestMapping(value = "/getAllNbfcData", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<ClaimUploadCgtmseBO>> getAllNBFCData(Model modeluserRole, HttpSession session) throws BusinessException {
		List<ClaimUploadCgtmseBO> allNBFC = null;
		try {
			String userId = (String) session.getAttribute("userId");
			String Role = (String) session.getAttribute("uRole");
			System.out.println("UserId :: ["+userId+"] \t Role :: ["+Role+"]");
			//allNBFC = cgtmseService.getAllNBFC(userId,Role);
			System.out.println("Final List :::" + allNBFC);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<ClaimUploadCgtmseBO>(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(allNBFC, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/download-nbfc-data", method = RequestMethod.GET)
	public  @ResponseBody String downloadExcelFileCGTMSE(@RequestParam String uploadId, HttpServletResponse response)
			throws BusinessException 
	{
		try {
			List<ClaimErrorFileExcelDataBO> excelList = cgtmseService.downloadUploadedFileList(uploadId, "S");
			String optionalColumns[] = {};
			Utils.writeToExcel("Claim_Uploaded_NBFC", "Claim_Uploaded_NBFC", optionalColumns, excelList, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;	
	}
	
	@RequestMapping(value = "/downloadFile-mgmt-certificate-nbfc", method = RequestMethod.GET)
	public @ResponseBody String downloadFileMgmtCertificate(@RequestParam String claimNumber, HttpServletResponse response)
			throws BusinessException {
		try {
			ClaimUploadHeader mgmt = cgtmseService.downloadFileMgmtCertificate(claimNumber, ApprovalStatus.SUBMITTED_TO_CGTMSE.getValue());
			if (mgmt != null) {
				byte[] fileData = mgmt.getMgmtFileDate();
				Path source = Paths.get(mgmt.getMgmtCertificateName());
				String contentType = Files.probeContentType(source);

				response.setContentType(contentType);
				response.setHeader("Content-Disposition","attachment; filename=\"" + mgmt.getMgmtCertificateName() + "\"");
				OutputStream os = response.getOutputStream();
				os.write(fileData);
				os.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
