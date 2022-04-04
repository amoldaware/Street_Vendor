package com.nbfc.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nbfc.bean.ClaimUploadCgtmseBO;
import com.nbfc.bean.FileExcelDataBO;
import com.nbfc.bean.LodgeFreshClaimBO;
import com.nbfc.bean.LodgeFreshUrtBO;
import com.nbfc.bean.UploadFileSuccessErrorCount;
import com.nbfc.bean.UtrErrorFileExcelDataBO;
import com.nbfc.common.utility.method.MenuUtils;
import com.nbfc.common.utility.method.Utils;
import com.nbfc.exception.BusinessException;
import com.nbfc.model.Login;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.UserActivity;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.LoginService;
import com.nbfc.service.NBFCUserReportService;
import com.nbfc.service.UrtUploadService;
import com.nbfc.service.UserActivityService;

@Controller
public class UrtFileUploadFormController {
	final static Logger logger = Logger.getLogger(UrtFileUploadFormController.class.getName());
	
	@Autowired
	LoginService lofinService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	NBFCUserReportService nbfcUserReportService;
	@Autowired
	UrtUploadService utrUploadService;
	@Autowired
	MenuUtilsController menuUtilsController;
	
	
	String userId = null;
	Login userDetails = null;

	/* vivek code */
	HttpSession session1 = null;
	NBFCPrivilegeMaster userPrvMst=null;
	String msg=null;
	@RequestMapping(value = "/UrtFileUpload", method = RequestMethod.GET)
	public ModelAndView urtFileUpload(Model modeluserRole, HttpSession session) throws BusinessException {
		Map<String, Object> model = new HashMap<String, Object>();
		UserPerivilegeDetails userPri;

		try {
			session1 = session;
			userId = (String) session.getAttribute("userId");
			String Role = (String) session.getAttribute("uRole");
			if (userId != null) {
				userDetails = lofinService.userDetails(userId);
				userPri = lofinService.getUserPrivlageDtl(userId, "A");
				userPrvMst = lofinService.getPrivlageMstDtl(userPri.getPrv_id());
				model = menuUtilsController.prepareMenu(session,Role, userId);
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
					return new ModelAndView("urtFileUploadCgtmseMaker", model);
				} else {
					return new ModelAndView("redirect:/SVLogin.html");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ModelAndView("redirect:/SVLogin.html");
	}
	@RequestMapping(value = "/uploadUrtFile", method = RequestMethod.POST)
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
			//LodgeFreshUrtBO urtBo = new Gson().fromJson(objString, type);
			/*
			 * if (urtBo != null) { if (urtBo.getElgibilityCheck() != null &&
			 * !urtBo.getElgibilityCheck().isEmpty()) { if
			 * (urtBo.getElgibilityCheck().equalsIgnoreCase("FAIL")) { boolean isSuccess1 =
			 * claimUploadService.deleteFailureDataByUserIdAndQuater(userId,
			 * claimBo.getCurrentQuater()); } } }
			 */

			//String fileuploadStatus = claimUploadService.getFileProcessStatusByUserId(userId);
			/*
			 * if (fileuploadStatus != null) { if (fileuploadStatus.equalsIgnoreCase("1")) {
			 * return new ResponseEntity<>("Uploaded File already is in Process...!",
			 * HttpStatus.BAD_REQUEST); } }
			 */

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
					if (numberOfCells != 4) {
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
							userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID());
					if (obj instanceof String) {
						index++;
						if (index == 0) {
							bo.setClaimPaymentVoucher((String) obj);
							continue;
						}
						if (index == 1) {
							bo.setClaimNumber((String) obj);
							continue;
						}
						if (index == 2) {
							bo.setClaimPaymentDate((String) obj);
							continue;
						}
						if (index == 3) {
							bo.setUtrNo((String) obj);
							continue;
						}

					}
					if (obj instanceof Double) {
						index++;
						double valueDouble = (Double) obj;
						if (index == 0) {
							bo.setClaimPaymentVoucher(Long.toString((long) valueDouble));
							continue;
						}
						if (index == 1) {
							bo.setClaimNumber(Long.toString((long) valueDouble));
							continue;
						}
						if (index == 2) {
							bo.setClaimPaymentDate(Long.toString((long) valueDouble));
							continue;
						}
						if (index == 3) {
							bo.setUtrNo(Long.toString((long) valueDouble));
							continue;
						}
					}
					if (obj instanceof Date) {
						index++;
						if (index == 0) {
							bo.setClaimPaymentVoucher(sdf1.format((Date) obj));
							continue;
						}
						if (index == 1) {
							bo.setClaimNumber(sdf1.format((Date) obj));
							continue;
						}
						if (index == 2) {
							bo.setClaimPaymentDate(sdf1.format((Date) obj));
							continue;
						}
						if (index == 3) {
							bo.setUtrNo(sdf1.format((Date) obj));
							continue;
						}

					}
				}
				uploadDataProcessList.add(bo);
			}
           System.out.println("upload file Data"+uploadDataProcessList);
			isSuccess = utrUploadService.saveUtrUploadExcelFileSTG(uploadDataProcessList, session1);
			if (isSuccess) {
				logger.info("File Uploaded Successfully.");
				return new ResponseEntity<>("File Uploaded Successfully.", HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
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
	
	@RequestMapping(value = "/success-error-count-utruploadfile", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<UploadFileSuccessErrorCount> getUtrProgressStatusFile() throws BusinessException {
		UploadFileSuccessErrorCount bo = null;
		try {
			bo = utrUploadService.getUtrProgressStatusFile(userId);
			if (bo == null) {
				bo = new UploadFileSuccessErrorCount();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new UploadFileSuccessErrorCount(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(bo, HttpStatus.OK);
	}
 
	@RequestMapping(value = "/getUtrUploadedRecordStatusBar", method = RequestMethod.GET)
	public synchronized @ResponseBody ResponseEntity<UploadFileSuccessErrorCount> getUploadedRecordStatusBar()
			throws BusinessException {
		UploadFileSuccessErrorCount tracker = null;
		try {
			tracker = utrUploadService.findUtrUploadedRecordStatusBar(userId,
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
	@RequestMapping(value = "/download-error-utrUploadedfile-count", method = RequestMethod.GET)
	public void downloadSuccessErrorUtrUploadedFileCount(@RequestParam String uploadId, @RequestParam String flag,
			HttpServletResponse response, HttpServletRequest request) throws BusinessException {
		List<UtrErrorFileExcelDataBO> errorExcelList = null;
		try {
			String fileName = "Utr_Uploaded_Error";
			String miliId = userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID();

			if (flag.equalsIgnoreCase("S")) {
				fileName = "Utr_Uploaded_Success";
			} else if (flag.equalsIgnoreCase("T")) {
				fileName = "Utr_Uploaded_Total_Success";
			}
			errorExcelList = utrUploadService.downloadErrorUtrUploadedFileList(uploadId, userId, miliId, flag);
			String optionalColumns[] = {"Claim Payment Voucher","Claim Number","UTR date","URT No","Upload Id","Processing Flag","Processing Desc"};
			Utils.writeToExcel(fileName, fileName, optionalColumns, errorExcelList, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/lodge-fresh-utr", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<LodgeFreshUrtBO>> getLodgeFreshUtrData() throws BusinessException {
		List<LodgeFreshUrtBO> ListOfUtrData = null;
		try {
			ListOfUtrData = utrUploadService.getLodgeFreshUtrByMliId(
					userDetails.getUsr_id());
			/*
			 * if (bo != null) { if (bo.getClaimNumber() != null) { boolean claimNumber =
			 * utrUploadService.findByClaimNumber(bo.getClaimNumber()); if (claimNumber) {
			 * bo.setDisableSaveBtn("yes"); } } } else { bo = new LodgeFreshUrtBO(); }
			 */
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new ArrayList<LodgeFreshUrtBO>(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(ListOfUtrData, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/saveUtrUpload", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> saveLodgeFreshUtrData(@RequestParam("obj") String objString) throws BusinessException {
	boolean isSuccess = false;
		try {
			Type type = new TypeToken<ArrayList<LodgeFreshUrtBO>>() {
			}.getType();
			ArrayList<LodgeFreshUrtBO> bo = new Gson().fromJson(objString, type);
			//listBo.get(0);
			//List<LodgeFreshUrtBO> listBo=new ArrayList<>();
		     String mliId=userDetails.getMEM_BNK_ID() + userDetails.getMEM_ZNE_ID() + userDetails.getMEM_ZNE_ID();
			if (bo != null && !bo.isEmpty()) {
				
			isSuccess = utrUploadService.saveLodgeFreshUtrData(bo,userId,mliId,userPrvMst.getPrvCreatedModifiedBy());
			}
			
			if (isSuccess) {
				return new ResponseEntity<>("Your Data Successfully Saved.", HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<>("Something Went Wrong.", HttpStatus.BAD_REQUEST);
	}

}
