package com.nbfc.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nbfc.bean.BankMandateFormBo;
import com.nbfc.bean.ClaimErrorFileExcelDataBO;
import com.nbfc.bean.ClaimPmsDetail;
import com.nbfc.bean.ClaimUploadDetailsBO;
import com.nbfc.bean.FileExcelDataBO;
import com.nbfc.bean.LodgeFreshClaimBO;
import com.nbfc.bean.UploadFileSuccessErrorCount;
import com.nbfc.common.utility.method.Utils;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.ClaimUploadHeader;
import com.nbfc.model.ClaimUploadTracker;
import com.nbfc.model.Login;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserActivity;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.BankMandateFormService;
import com.nbfc.service.ClaimUploadCgtmseService;
import com.nbfc.service.ClaimUploadService;
import com.nbfc.service.LoginService;
import com.nbfc.service.NBFCUserReportService;
import com.nbfc.service.UserActivityService;
import com.nbfc.utility.ApprovalStatus;

@Controller
public class ClaimUploadController {
	final static Logger logger = Logger.getLogger(ClaimUploadController.class.getName());

	@Autowired
	ClaimUploadService claimUploadService;
	@Autowired
	LoginService lofinService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	NBFCUserReportService nbfcUserReportService;
	@Autowired
	ClaimUploadCgtmseService cgtmseService;
	@Autowired
	BankMandateFormService bnkMandateService;
	@Autowired
	MenuUtilsController menuUtilsController;

	/*String userId = null;
	Login userDetails = null;*/
	UserPerivilegeDetails userPri;
	NBFCPrivilegeMaster userPrvMst;

	/* umesh code */
	/*HttpSession session1 = null;
    String msg=null;*/
	@RequestMapping(value = "/claim-upload", method = RequestMethod.GET)
	public ModelAndView claimUpload(Model modeluserRole, HttpSession session) throws BusinessException {
		Map<String, Object> model = new HashMap<String, Object>();
		//HttpSession session1 = null;
		Login userDetails = null;
		String userId = null;
	    String msg=null;
		try {
			//session1 = session;
			userId = (String) session.getAttribute("userId");
			String Role = (String) session.getAttribute("uRole");
			if (userId != null) {
				userDetails = lofinService.userDetails(userId);
				userPri = lofinService.getUserPrivlageDtl(userId, "A");
				userPrvMst = lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				model = menuUtilsController.prepareMenu(session, Role, userId);
				if (model!=null) {
					model.put("homePage", "nbfcMakerHome");
					return new ModelAndView("cliamUpload", model);
				} else {
					return new ModelAndView("redirect:/SVLogin.html");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView("redirect:/SVLogin.html");
	}

	@RequestMapping(value = "/financial-years", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<ClaimUploadDetailsBO>> getFinancialYears() throws BusinessException {
		List<ClaimUploadDetailsBO> financialYearsList = null;
		try {
			financialYearsList = claimUploadService.getFinancialYears();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(financialYearsList, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(financialYearsList, HttpStatus.OK);
	}

	@RequestMapping(value = "/upload-details", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ClaimUploadDetailsBO> uploadDetails(HttpSession session,Login userDetails)
			throws BusinessException {
		ClaimUploadDetailsBO bo = null;
		String userId = null;
		try {
			//Added by sarita 17/08/2021 [START]
			userId = (String) session.getAttribute("userId");
			if (userId != null) {
				userDetails = lofinService.userDetails(userId);
			}
			//Added by sarita 17/08/2021 [END]
			if (userDetails != null) {
				bo = claimUploadService.getClaimUploadByMemberBankId(
						//userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID());  changes by sarita 12/08/2021 for data conflict issue
						userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_BRN_ID());
				if (bo != null) {
					bo.setRoleType((String) session.getAttribute("uRole"));
				}

				if (bo == null) {
					bo = new ClaimUploadDetailsBO();
					bo.setRoleType((String) session.getAttribute("uRole"));
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(bo, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(bo, HttpStatus.OK);
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> uploadExcelFile(@RequestParam("uploadfile") MultipartFile uploadfile,
			@RequestParam("obj") String objString,HttpSession session,Login userDetails) throws BusinessException {

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

		Workbook wb = null;
		Sheet sheet = null;
		boolean isSuccess = false;
		String extensionType = null;
		String userId = null;
		HttpSession session1 = null;
		Map<Integer, List<Object>> uploadDataMap = new HashMap<>();
		try {
			//Added by sarita 17/08/2021 [START]
			session1 = session;
			userId = (String) session.getAttribute("userId");
			if (userId != null) {
				userDetails = lofinService.userDetails(userId);
			}
			//Added by sarita 17/08/2021 [END]
			
			Type type = new TypeToken<LodgeFreshClaimBO>() {
			}.getType();
			LodgeFreshClaimBO claimBo = new Gson().fromJson(objString, type);
			if (claimBo != null) {
				if (claimBo.getElgibilityCheck() != null && !claimBo.getElgibilityCheck().isEmpty()) {
					if (claimBo.getElgibilityCheck().equalsIgnoreCase("FAIL")) {
						boolean isSuccess1 = claimUploadService.deleteFailureDataByUserIdAndQuater(userId,
								claimBo.getCurrentQuater());
					}
				}
			}

			String fileuploadStatus = claimUploadService.getFileProcessStatusByUserId(userId);
			if (fileuploadStatus != null) {
				if (fileuploadStatus.equalsIgnoreCase("1")) {
					return new ResponseEntity<>("Uploaded File already is in Process...!", HttpStatus.BAD_REQUEST);
				}
			}

			if (uploadfile.isEmpty()) {
				return new ResponseEntity<>("Please select file.", HttpStatus.BAD_REQUEST);
			}
			if (uploadfile.getOriginalFilename().contains("..")) {
				return new ResponseEntity<>(
						"Sorry! Filename contains invalid path sequence " + uploadfile.getOriginalFilename(),
						HttpStatus.BAD_REQUEST);
			}
			if (uploadfile.getOriginalFilename().lastIndexOf(".") != -1
					&& uploadfile.getOriginalFilename().lastIndexOf(".") != 0) {
				extensionType = uploadfile.getOriginalFilename()
						.substring(uploadfile.getOriginalFilename().lastIndexOf(".") + 1);
			}

			String[] docType = { "xls", "xlsx" };
			List<String> validExtensionList = Arrays.asList(docType);
			if (!validExtensionList.contains(extensionType)) {
				return new ResponseEntity<>("Only xls or xlsx file type allowed...!", HttpStatus.BAD_REQUEST);
			}

			if (extensionType.equalsIgnoreCase("xlsx")) {
				wb = new XSSFWorkbook(uploadfile.getInputStream());

			} else if (extensionType.equalsIgnoreCase("xls")) {
				wb = new HSSFWorkbook(uploadfile.getInputStream());

			}

			sheet = wb.getSheetAt(0);

			int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
			if (physicalNumberOfRows == 0) {
				return new ResponseEntity<>("Prescribe template is empty...!.", HttpStatus.BAD_REQUEST);
			}

			String currentQuarter = claimUploadService.getCurrentQuarter();

			String quarter = claimUploadService.getQuarterFileUploadByUserId(userDetails.getUsr_id(), currentQuarter,
					claimBo.getFinancialYears());

			if (quarter != null && !quarter.isEmpty()) {
				if (currentQuarter.equalsIgnoreCase(quarter)) {
					return new ResponseEntity<>("Claim file can upload only once in a quarter : " + quarter,
							HttpStatus.BAD_REQUEST);
				}
			}
			for (int i = 0; i < physicalNumberOfRows; i++) {
				List<Object> genericUploadList = new ArrayList<>();
				Row row = sheet.getRow(i);

				if (i == 0) {
					int numberOfCells = row.getPhysicalNumberOfCells();
					//if (numberOfCells != 4) { Commented by Sarita on 21/10/2021 AS Field BorrowerName added
					//if (numberOfCells != 5) {
					if (numberOfCells != 6) {
						return new ResponseEntity<>("Prescribe template is not used while uploading these records.",
								HttpStatus.BAD_REQUEST);
					}
					continue;
				}

				Iterator<Cell> iterator = row.iterator();
				while (iterator.hasNext()) {

					Cell cell = iterator.next();

					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_STRING:
						genericUploadList.add(cell.getStringCellValue());
						break;

					case Cell.CELL_TYPE_NUMERIC:

						if (DateUtil.isCellDateFormatted(cell)) {
							genericUploadList.add(cell.getDateCellValue());
						} else {
							genericUploadList.add(cell.getNumericCellValue());
						}
						break;

					case Cell.CELL_TYPE_BLANK:
						genericUploadList.add("");
						break;
					}
				}
				uploadDataMap.put(Integer.valueOf(i), genericUploadList);
			}
			List<FileExcelDataBO> uploadDataProcessList = new ArrayList<>();
			Set<Integer> keySet = uploadDataMap.keySet();

			if (keySet == null || keySet.isEmpty()) {
				return new ResponseEntity<>("Prescribe template is empty...!.", HttpStatus.BAD_REQUEST);
			}
			for (Integer key : keySet) {
				List<Object> list = uploadDataMap.get(key);
				FileExcelDataBO bo = new FileExcelDataBO();
				int index = -1;
				for (Object obj : list) {
					bo.setUserId(userDetails.getUsr_id());
					bo.setMemberBnkId(
							userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_BRN_ID());
					bo.setQuater(currentQuarter);
					bo.setFinancialyear(claimBo.getFinancialYears());

					if (obj instanceof String) {
						index++;
						if (index == 0) {
							bo.setPmsNumber((String) obj);
							continue;
						}
						if (index == 1) {
							bo.setLoanAcountNo((String) obj);
							continue;
						}
						if (index == 2) {
							bo.setBorrowerName((String) obj);
							continue;
						}
						if(index == 3){
							bo.setLoanTerm((String) obj);
							continue;
						}
						if (index == 4) {
							bo.setDateOfNpa((String) obj);
							continue;
						}
						
						if (index == 5) {
							bo.setOutstandingAmountNpa((String) obj);
							continue;
						}

					}
					if (obj instanceof Double) {
						index++;
						double valueDouble = (Double) obj;
						if (index == 0) {
							bo.setPmsNumber(Long.toString((long) valueDouble));
							continue;
						}
						if (index == 1) {
							bo.setLoanAcountNo(Long.toString((long) valueDouble));
							continue;
						}
						if (index == 2) {
							bo.setBorrowerName(Long.toString((long) valueDouble));
							continue;
						}
						if(index == 3){
							bo.setLoanTerm(Long.toString((long)valueDouble));
						}
						if (index == 4) {
							bo.setDateOfNpa(Long.toString((long) valueDouble));
							continue;
						}
						if (index == 5) {
							bo.setOutstandingAmountNpa(Long.toString((long) valueDouble));
							continue;
						}
					}
					if (obj instanceof Date) {
						index++;
						if (index == 0) {
							bo.setPmsNumber(sdf1.format((Date) obj));
							continue;
						}
						if (index == 1) {
							bo.setLoanAcountNo(sdf1.format((Date) obj));
							continue;
						}
						if (index == 2) {
							bo.setBorrowerName(sdf1.format((Date) obj));
							continue;
						}
						if(index == 3){
							bo.setLoanTerm(sdf1.format((Date)obj));
							continue;
						}
						if (index == 4) {
							bo.setDateOfNpa(sdf1.format((Date) obj));
							continue;
						}
						if (index == 5) {
							bo.setOutstandingAmountNpa(sdf1.format((Date) obj));
							continue;
						}

					}
				}
				System.out.println("List is ::::" + uploadDataProcessList);
				uploadDataProcessList.add(bo);
			}
			isSuccess = claimUploadService.saveClaimUploadExcelFileSTG(uploadDataProcessList, session1);
			if (isSuccess) {
				logger.info("File Uploaded Successfully.");
				return new ResponseEntity<>("File Uploaded Successfully.", HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			try {
				if (wb != null) {
					wb.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return new ResponseEntity<>("Getting Error! File Not Successfully Uploaded.", HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/lodge-fresh-Claim", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<LodgeFreshClaimBO> getLodgeFreshClaimData(Login userDetails,HttpSession session) throws BusinessException {
		LodgeFreshClaimBO bo = null;
		String userId = null;
		try {
			//Added by sarita 17/08/2021 [START]
			userId = (String) session.getAttribute("userId");
			if (userId != null) {
				userDetails = lofinService.userDetails(userId);
			}
			//Added by sarita 17/08/2021 [END]
			bo = claimUploadService.getLodgeFreshClaimByMliId(
					userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_BRN_ID());
			if (bo != null) {
				if (bo.getClaimNumber() != null) {
					boolean claimNumber = claimUploadService.findByClaimNumber(bo.getClaimNumber());
					if (claimNumber) {
						bo.setDisableSaveBtn("yes");
					}
				}
			} else {
				bo = new LodgeFreshClaimBO();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new LodgeFreshClaimBO(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(bo, HttpStatus.OK);
	}

	@RequestMapping(value = "/save-lodge-fresh-Claim", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> saveLodgeFreshClaimMaker(@RequestParam("objField") String objString,Login userDetails,HttpSession session)
			throws BusinessException {
		boolean isSuccess = false;
		String userId=null;
		try {
			//Added by sarita 17/08/2021 [START]
			userId = (String) session.getAttribute("userId");
			if (userId != null) {
				userDetails = lofinService.userDetails(userId);
			}
			//Added by sarita 17/08/2021 [END]
			Gson gson = new Gson();
			LodgeFreshClaimBO bo = gson.fromJson(objString, LodgeFreshClaimBO.class);
			if (bo != null) {
				if (bo.getUserId() == null) {
					bo.setUserId(userId);
					bo.setMliId(
							userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_BRN_ID());

				}
			}
			boolean claimNumber = claimUploadService.findByClaimNumber(bo.getClaimNumber());
			if (claimNumber) {
				 return new ResponseEntity<>("You have been already saved this claim Number : " +bo.getClaimNumber(), HttpStatus.BAD_REQUEST);	 
			}
			isSuccess = claimUploadService.saveLodgeFreshClaimData(bo);
			if (isSuccess) {
				return new ResponseEntity<>("Your Data Successfully Saved.", HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<>("Something Went Wrong.", HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/save-lodge-fresh-Claim-checker", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> saveLodgeFreshClaimChecker(
			@RequestParam("uploadfile") MultipartFile uploadfile, @RequestParam("objField") String objString,
			Login userDetails,HttpSession session)
			throws BusinessException {
		boolean isSuccess = false;
		String msg="",userId=null;
		try {
			//Added by sarita 17/08/2021 [START]
			userId = (String) session.getAttribute("userId");
			if (userId != null) {
				userDetails = lofinService.userDetails(userId);
			}
			//Added by sarita 17/08/2021 [END]
			
			if (uploadfile.isEmpty()) {
				return new ResponseEntity<>("Please select file.", HttpStatus.BAD_REQUEST);
			}
			if (uploadfile.getOriginalFilename().contains("..")) {
				return new ResponseEntity<>(
						"Sorry! Filename contains invalid path sequence " + uploadfile.getOriginalFilename(),
						HttpStatus.BAD_REQUEST);
			}
			Type type = new TypeToken<LodgeFreshClaimBO>() {
			}.getType();
			LodgeFreshClaimBO bo = new Gson().fromJson(objString, type);
			if (bo != null) {
				if (bo.getUserId() == null) {
					bo.setUserId(userId);
					bo.setFileData(uploadfile.getBytes());
					if (bo.getApprovalStatus().equalsIgnoreCase("Approve")) {
						bo.setApprovalStatus(ApprovalStatus.SUBMITTED_TO_CGTMSE.getValue());
						msg="Your Data Successfully Saved.";
					} else if (bo.getApprovalStatus().equalsIgnoreCase("Return")) {
						bo.setApprovalStatus(ApprovalStatus.REJECTED_BY_CHECKER.getValue());
						msg="File returned";
					}
				}
			}
			String mliId = userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_BRN_ID();
			String quarter = claimUploadService.getCurrentQuarter();

			String approvalStatus = claimUploadService.getApprovalStatusCheckerByMliIdAndQuaterFy(mliId, quarter,
					bo.getFinancialYears());
			if (approvalStatus != null) {
				if (approvalStatus.equalsIgnoreCase("MC_A")) {
					return new ResponseEntity<>("File already approved by CGTMSE", HttpStatus.BAD_REQUEST);
				}
			}

			isSuccess = claimUploadService.updateLodgeFreshClaimChecker(bo);
			if (isSuccess) {
				return new ResponseEntity<>(msg, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<>("Something Went Wrong.", HttpStatus.BAD_REQUEST);
	}

	/* vivek code below */

	@RequestMapping(value = "/claimPmsDetail", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<ClaimPmsDetail>> claimPmsDetail(@RequestParam String claimNo,
			Login userDetails,HttpSession session)
			throws BusinessException {
		List<ClaimPmsDetail> claimpms = null;
		String userId = null;
		try {
			//Added by sarita 17/08/2021 [START]
			userId = (String) session.getAttribute("userId");
			if (userId != null) {
				userDetails = lofinService.userDetails(userId);
			}
			//Added by sarita 17/08/2021 [END]
			claimpms = claimUploadService.getClaimPmsDetail(
					userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_BRN_ID(), claimNo,
					null);
			if (claimpms == null || claimpms.isEmpty()) {
				claimpms = new ArrayList<ClaimPmsDetail>();
			}
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<ClaimPmsDetail>(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(claimpms, HttpStatus.OK);
	}

	@RequestMapping(value = "/excelPmsDetail", method = RequestMethod.GET)
	@ResponseBody
	public void excelDwclaimPmsDetail(@RequestParam String claimNo, HttpServletResponse response,
			                          Login userDetails,HttpSession session)
			throws BusinessException {
		List<ClaimPmsDetail> claimpms = null;
		String userId = null;
		try {
			//Added by sarita 17/08/2021 [START]
			userId = (String) session.getAttribute("userId");
			if (userId != null) {
				userDetails = lofinService.userDetails(userId);
			}
			//Added by sarita 17/08/2021 [END]
			claimpms = claimUploadService.getClaimPmsDetail(
					userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_BRN_ID(), claimNo,
					response);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@RequestMapping(value = "/downloadMangementCertifi", method = RequestMethod.GET)
	public void pdfMangCertificateDwclaimPmsDetail(@RequestParam String claimNo, HttpServletResponse response,
			HttpServletRequest request,Login userDetails,HttpSession session) throws BusinessException {
		String userId = null;
		try 
		{
			//Added by sarita 17/08/2021 [START]
			userId = (String) session.getAttribute("userId");
			if (userId != null) {
				userDetails = lofinService.userDetails(userId);
			}
			//Added by sarita 17/08/2021 [END]
			claimUploadService.getPdfManagementCertificateClaimPms(userId, claimNo, response, request);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@RequestMapping(value = "/success-error-count-uploadfile", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<UploadFileSuccessErrorCount> getProgressStatusFile(Login userDetails,HttpSession session) throws BusinessException {
		UploadFileSuccessErrorCount bo = null;
		try {
			//Added by sarita 17/08/2021 [START]
			String userId = (String) session.getAttribute("userId");
			if (userId != null) {
				userDetails = lofinService.userDetails(userId);
			}
			//Added by sarita 17/08/2021 [END]
			bo = claimUploadService.getProgressStatusFile(userId);
			if (bo == null) {
				bo = new UploadFileSuccessErrorCount();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new UploadFileSuccessErrorCount(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(bo, HttpStatus.OK);
	}
	/*
	 * @RequestMapping(value = "/getUploadedRecordStatusBar", method =
	 * RequestMethod.GET) public synchronized @ResponseBody
	 * ResponseEntity<UploadFileSuccessErrorCount> getUploadedRecordStatusBar()
	 * throws BusinessException { UploadFileSuccessErrorCount tracker = null; try {
	 * tracker = claimUploadService.findUploadedRecordStatusBar(userId); if (tracker
	 * == null) { tracker = new UploadFileSuccessErrorCount(); }
	 * 
	 * } catch (Exception e) { logger.error(e.getMessage()); return new
	 * ResponseEntity<>(new UploadFileSuccessErrorCount(), HttpStatus.BAD_REQUEST);
	 * } return new ResponseEntity<>(tracker, HttpStatus.OK); }
	 */

	@RequestMapping(value = "/getUploadedRecordStatusBar", method = RequestMethod.GET)
	public synchronized @ResponseBody ResponseEntity<UploadFileSuccessErrorCount> getUploadedRecordStatusBar(Login userDetails,HttpSession session)
			throws BusinessException {
		UploadFileSuccessErrorCount tracker = null;
		try {
			//Added by sarita 17/08/2021 [START]
			String userId = (String) session.getAttribute("userId");
			if (userId != null) {
				userDetails = lofinService.userDetails(userId);
			}
			//Added by sarita 17/08/2021 [END]
			tracker = claimUploadService.findUploadedRecordStatusBar(userId,
					userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_BRN_ID());
			if (tracker == null) {
				tracker = new UploadFileSuccessErrorCount();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new UploadFileSuccessErrorCount(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(tracker, HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value = "/download-error-uploadedfile-count", method =
	 * RequestMethod.GET) public void
	 * downloadSuccessErrorUploadedFileCount(@RequestParam String uploadId,
	 * HttpServletResponse response, HttpServletRequest request) throws
	 * BusinessException { try { List<ClaimErrorFileExcelDataBO> errorExcelList =
	 * claimUploadService.downloadErrorUploadedFileList(uploadId, userId); String
	 * optionalColumns[] = {}; Utils.writeToExcel("Claim_Uploaded_Error_Count",
	 * "Claim_Uploaded_Error", optionalColumns, errorExcelList, response); } catch
	 * (Exception e) { logger.error(e.getMessage()); } }
	 */

	/*
	 * @RequestMapping(value = "/download-error-uploadedfile-count", method =
	 * RequestMethod.GET) public void
	 * downloadSuccessErrorUploadedFileCount(@RequestParam String
	 * uploadId, @RequestParam String flag, HttpServletResponse response,
	 * HttpServletRequest request) throws BusinessException { try {
	 * List<ClaimErrorFileExcelDataBO> errorExcelList =
	 * claimUploadService.downloadErrorUploadedFileList(uploadId, userId, flag);
	 * String optionalColumns[] = {};
	 * Utils.writeToExcel("Claim_Uploaded_Error_Count", "Claim_Uploaded_Error",
	 * optionalColumns, errorExcelList, response); } catch (Exception e) {
	 * logger.error(e.getMessage()); } }
	 */

	@RequestMapping(value = "/check-bank-mandate-approve", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<BankMandateFormBo> checkBankMandateApprove(Login userDetails,HttpSession session) throws BusinessException {
		BankMandateFormBo bnkMandate = null;
		try {
			//Added by sarita 17/08/2021 [START]
			String userId = (String) session.getAttribute("userId");
			if (userId != null) {
				userDetails = lofinService.userDetails(userId);
			}
			//Added by sarita 17/08/2021 [END]
			bnkMandate = bnkMandateService.getBankMandateDataStatusWise(
					userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_BRN_ID(),
					ApprovalStatus.APPROVED.getValue());

			if (bnkMandate.getApproveStatus().equalsIgnoreCase("CG_A")) {
				bnkMandate.setApproveStatus("Approved");
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new BankMandateFormBo(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(bnkMandate, HttpStatus.OK);
	}

	@RequestMapping(value = "/file-upload-process-status", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> getFileUploadProcessStatus(Login userDetails,HttpSession session) throws BusinessException {
		String fileuploadedStatus = "";
		try {
			//Added by sarita 17/08/2021 [START]
			String userId = (String) session.getAttribute("userId");
			if (userId != null) {
				userDetails = lofinService.userDetails(userId);
			}
			//Added by sarita 17/08/2021 [END]
			fileuploadedStatus = claimUploadService.getFileProcessStatusByUserId(userId);

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(fileuploadedStatus, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(fileuploadedStatus, HttpStatus.OK);
	}
	/*
	 * @RequestMapping(value = "/download-error-uploadedfile-count", method =
	 * RequestMethod.GET) public void
	 * downloadSuccessErrorUploadedFileCount(@RequestParam String
	 * uploadId, @RequestParam String flag, HttpServletResponse response,
	 * HttpServletRequest request) throws BusinessException { try { String maker =
	 * null; List<ClaimErrorFileExcelDataBO> errorExcelList = null; String fileName
	 * = "Claim_Uploaded_Error"; if (flag != null && !flag.isEmpty()) { if
	 * (flag.equalsIgnoreCase("S")) { fileName = "Claim_Uploaded_Success"; } } if
	 * (userPrvMst.getPrvCreatedModifiedBy().equals("NCHECKER")) { maker =
	 * claimUploadService.getMakerByMliIdandStatus("C", userDetails.getMEM_BNK_ID()
	 * + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID()); errorExcelList
	 * = claimUploadService.downloadErrorUploadedFileList(uploadId, maker, flag); }
	 * else { errorExcelList =
	 * claimUploadService.downloadErrorUploadedFileList(uploadId, userId, flag); }
	 * String optionalColumns[] = {}; Utils.writeToExcel(fileName, fileName,
	 * optionalColumns, errorExcelList, response); } catch (Exception e) {
	 * logger.error(e.getMessage()); } }
	 */

	@RequestMapping(value = "/download-error-uploadedfile-count", method = RequestMethod.GET)
	public void downloadSuccessErrorUploadedFileCount(@RequestParam String uploadId, @RequestParam String flag,
			HttpServletResponse response, HttpServletRequest request,Login userDetails,HttpSession session) throws BusinessException {
		List<ClaimErrorFileExcelDataBO> errorExcelList = null;
		try {
			//Added by sarita 17/08/2021 [START]
			String userId = (String) session.getAttribute("userId");
			if (userId != null) {
				userDetails = lofinService.userDetails(userId);
			}
			//Added by sarita 17/08/2021 [END]
			String fileName = "Claim_Uploaded_Error";
			String miliId = userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_BRN_ID();

			if (flag.equalsIgnoreCase("S")) {
				fileName = "Claim_Uploaded_Success";
			} else if (flag.equalsIgnoreCase("T")) {
				fileName = "Claim_Uploaded_Total_Success";
			}
			errorExcelList = claimUploadService.downloadErrorUploadedFileList(uploadId, userId, miliId, flag);
			//String optionalColumns[] = {"PMS Number","Loan Acount No","Date of NPA","NPA Outstanding Amount","Upload Id","Processing Flag","Processing Desc"};//SARITA24102021
			//String optionalColumns[] = {"PMS Number","Loan Acount No","Date of NPA","NPA Outstanding Amount","Upload Id","Processing Flag","Processing Desc","Borrower Name"};//SARITA06122021
			//String optionalColumns[] = {"PMS Number","Loan Acount No","Date of NPA","Amount in Default","Upload Id","Processing Flag","Processing Desc","Borrower Name"};//SARITA27012022
			String optionalColumns[] = {"PMS Number","Loan Acount No","Borrower Name","Loan Term","Date of NPA","Amount in Default","Upload Id","Processing Flag","Processing Desc"};
			Utils.writeToExcel(fileName, fileName, optionalColumns, errorExcelList, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
