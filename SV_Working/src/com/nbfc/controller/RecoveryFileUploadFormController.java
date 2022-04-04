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

import org.apache.log4j.Logger;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nbfc.bean.FileExcelRecoveryDataBo;
import com.nbfc.bean.LodgeFreshClaimBO;
import com.nbfc.bean.LodgeFreshRecoveryBO;
import com.nbfc.bean.RecoveryDownLoadExcelData;
import com.nbfc.bean.RecoveryErrorFileExcelDataBO;
import com.nbfc.bean.RecoveryXmlData;
import com.nbfc.bean.UploadFileSuccessErrorCount;
import com.nbfc.common.utility.method.Utils;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.Login;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserActivity;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.LoginService;
import com.nbfc.service.NBFCUserReportService;
import com.nbfc.service.RecoveryUploadService;
import com.nbfc.service.UserActivityService;

@Controller
public class RecoveryFileUploadFormController {
	final static Logger logger = Logger.getLogger(RecoveryFileUploadFormController.class.getName());
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
	
	
	String userId = null;
	Login userDetails = null;

	/* vivek code */
	HttpSession session1 = null;

	NBFCPrivilegeMaster userPrvMst=null;
	
	@RequestMapping(value = "/RecoveryFileUpload", method = RequestMethod.GET)
	public ModelAndView urtFileUpload(Model modeluserRole, HttpSession session) throws BusinessException {
		Map<String, Object> model = new HashMap<String, Object>();
		UserPerivilegeDetails userPri;
		//LodgeFreshRecoveryBO bo=null;
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
					return new ModelAndView("RecoveryUpload", model);
				} else {
					return new ModelAndView("redirect:/SVLogin.html");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView("redirect:/SVLogin.html");
	}
	@RequestMapping(value = "/uploadRecoveryFile", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> uploadExcelFile(@RequestParam("uploadfile") MultipartFile uploadfile) throws BusinessException {

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

		Workbook wb = null;
		Sheet sheet = null;
		boolean isSuccess = false;
		String extensionType = null;
		Map<Integer, List<Object>> uploadDataMap = new HashMap<>();
		try {
			Type type = new TypeToken<LodgeFreshClaimBO>() {
			}.getType();
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

			
			for (int i = 0; i < physicalNumberOfRows; i++) {
				List<Object> genericUploadList = new ArrayList<>();
				Row row = sheet.getRow(i);

				if (i == 0) {
					int numberOfCells = row.getPhysicalNumberOfCells();
					if (numberOfCells != 3) {
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
			List<FileExcelRecoveryDataBo> uploadDataProcessList1 = new ArrayList<>();
			Set<Integer> keySet = uploadDataMap.keySet();

			if (keySet == null || keySet.isEmpty()) {
				return new ResponseEntity<>("Prescribe template is empty...!.", HttpStatus.BAD_REQUEST);
			}
			for (Integer key : keySet) {
				List<Object> list = uploadDataMap.get(key);
				FileExcelRecoveryDataBo bo = new FileExcelRecoveryDataBo();
				int index = -1;
				for (Object obj : list) {
					bo.setUserId(userDetails.getUsr_id());
					bo.setMemberBnkId(
							userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID());
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
							bo.setRecoveryAmt((String) obj);
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
							bo.setRecoveryAmt(Long.toString((long) valueDouble));
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
							bo.setRecoveryAmt(sdf1.format((Date) obj));
							continue;
						}
					

					}
				}
				uploadDataProcessList1.add(bo);
			}
           System.out.println("upload file Data"+uploadDataProcessList1);
			isSuccess = recoveryUploadService.saveRecoveryUploadExcelFileSTG(uploadDataProcessList1, session1);
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
	
	@RequestMapping(value = "/success-error-count-recoveryuploadfile", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<UploadFileSuccessErrorCount> getRecoveryProgressStatusFile() throws BusinessException {
		UploadFileSuccessErrorCount bo = null;
		try {
			bo = recoveryUploadService.getRecoveryProgressStatusFile(userId);
			if (bo == null) {
				bo = new UploadFileSuccessErrorCount();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new UploadFileSuccessErrorCount(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(bo, HttpStatus.OK);
	}

	@RequestMapping(value = "/getRecoveryUploadedRecordStatusBar", method = RequestMethod.GET)
	public synchronized @ResponseBody ResponseEntity<UploadFileSuccessErrorCount> getRecoveryUploadedRecordStatusBar()
			throws BusinessException {
		UploadFileSuccessErrorCount tracker = null;
		try {
			tracker = recoveryUploadService.findRecoveryUploadedRecordStatusBar(userId,
					userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID());
			if (tracker == null) {
				tracker = new UploadFileSuccessErrorCount();
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new UploadFileSuccessErrorCount(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(tracker, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/download-error-recoveryUploadedfile-count", method = RequestMethod.GET)
	public void downloadSuccessErrorRecoveryUploadedFileCount(@RequestParam String uploadId, @RequestParam String flag,
			HttpServletResponse response, HttpServletRequest request) throws BusinessException {
		List<RecoveryErrorFileExcelDataBO> errorExcelList = null;
		try {
			String fileName = "Recovery_Uploaded_Error";
			String miliId = userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID();

			if (flag.equalsIgnoreCase("S")) {
				fileName = "Recovery_Uploaded_Success";
			} else if (flag.equalsIgnoreCase("T")) {
				fileName = "Recovery_Uploaded_Total_Success";
			}
			errorExcelList = recoveryUploadService.downloadErrorRecoveryUploadedFileList(uploadId, userId, miliId, flag);
			String optionalColumns[] = {"PMS Number","Loan Account Number","Recovery Amt","Upload_Id","Processing Flag","Processing Desc"};
			Utils.writeToExcel(fileName, fileName, optionalColumns, errorExcelList, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/lodge-fresh-recovery", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<LodgeFreshRecoveryBO> getLodgeFreshRecoveryData(HttpSession session) throws BusinessException {
		List<LodgeFreshRecoveryBO> ListOfRecoveryData = null;
		LodgeFreshRecoveryBO bo=null;
		try {
			ListOfRecoveryData = recoveryUploadService.getLodgeFreshRecoveryByMliId(userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID());
			//bo.setUserType((String) session.getAttribute("uRole"));
			if(ListOfRecoveryData!=null && !ListOfRecoveryData.isEmpty()) {
				bo=ListOfRecoveryData.get(0);
				
			}else {
				bo=new LodgeFreshRecoveryBO();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new LodgeFreshRecoveryBO(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(bo, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveRecoveryUpload", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> saveLodgeFreshRecoveryData(@RequestParam("obj") String objString) throws BusinessException {
	boolean isSuccess = false;
	String msg="";
		try {
			Type type = new TypeToken<ArrayList<LodgeFreshRecoveryBO>>() {
			}.getType();
			ArrayList<LodgeFreshRecoveryBO> bo = new Gson().fromJson(objString, type);
			//listBo.get(0);
			//List<LodgeFreshUrtBO> listBo=new ArrayList<>();
		     String mliId=userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID();
			if (bo != null && !bo.isEmpty()) {
				if (bo.get(0).getApprovalStatus().equalsIgnoreCase("MM")) {
					
					msg="Your Data Successfully Saved.";
				} else if (bo.get(0).getApprovalStatus().equalsIgnoreCase("CG_R")) {
					
					msg="File returned";
				}else if (bo.get(0).getApprovalStatus().equalsIgnoreCase("CG_A")) {
					
					msg="Your Data Successfully Saved.";
					
				}
				
			isSuccess = recoveryUploadService.saveLodgeFreshRecoveryData(bo,userId,mliId,userPrvMst.getPrvCreatedModifiedBy());
			}
			
			if (isSuccess) {
				return new ResponseEntity<>(msg, HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<>("Something Went Wrong.", HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(value = "/download-recoveryUploadExcelData", method = RequestMethod.GET)
	public void downloadRecoveryExportApprovalData(HttpServletRequest request,HttpServletResponse response)
			throws BusinessException {
		List<RecoveryDownLoadExcelData> allRecoveryExelData = null;
		try {
			String mliId=userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID();
			String optionalColumns[] = {"RP Number","Virtual Account No.","Recovery Amount","IFSC Code"};

			
			allRecoveryExelData = recoveryUploadService.getExportToExcelRecoveryData(mliId);
			Utils.writeToExcel("Recovery Upload Report", "RecoveryUpload Detail", optionalColumns, allRecoveryExelData, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	@RequestMapping(value = "/get-Xml-recoveryData.html", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<RecoveryXmlData> getLodgeXmlRecoveryData(HttpSession session) throws BusinessException {
		//List<RecoveryXmlData> ListOfRecoveryXmlData = null;
		RecoveryXmlData bo=null;
		try {
			bo = recoveryUploadService.getXmlRecoveryData(userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID());
			
			/*
			 * if(bo!=null && !bo.isEmpty()) { bo=ListOfRecoveryXmlData.get(0);
			 * 
			 * 
			 * }else { bo=new RecoveryXmlData(); }
			 */
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new RecoveryXmlData(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(bo, HttpStatus.OK);
	}
	@RequestMapping(value = "/get-payment-recoveryData.html", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<RecoveryDownLoadExcelData> getLodgePaymentRecoveryData(HttpSession session) throws BusinessException {
		//List<RecoveryXmlData> ListOfRecoveryXmlData = null;
		RecoveryDownLoadExcelData bo=null;
		try {
			String mliId=userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID();
			List<RecoveryDownLoadExcelData> exportToExcelRecoveryData = recoveryUploadService.getExportToExcelRecoveryData(mliId);
			if(exportToExcelRecoveryData!=null && !exportToExcelRecoveryData.isEmpty()) {
				bo=exportToExcelRecoveryData.get(0);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new RecoveryDownLoadExcelData(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(bo==null?new RecoveryDownLoadExcelData():bo, HttpStatus.OK);
	}

}
