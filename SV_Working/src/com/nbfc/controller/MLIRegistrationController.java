package com.nbfc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import com.nbfc.bean.JsonResponse2;
import com.nbfc.bean.MLIDEtailsBean;
import com.nbfc.bean.MliUploadDetails;
import com.nbfc.bean.StateBean;
import com.nbfc.model.BankDetails;
import com.nbfc.model.District;
import com.nbfc.model.MLIName;
import com.nbfc.model.MLIRegistration;
import com.nbfc.model.MLI_UNDERTAKEN_UPLOAD;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.State;
import com.nbfc.model.StateMaster;
import com.nbfc.model.User;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.DistrictService;
import com.nbfc.service.LoginService;
import com.nbfc.service.MLIDetailsService;
import com.nbfc.service.MLIRegService;
import com.nbfc.service.StateService;
import com.nbfc.service.UserActivityService;
import com.nbfc.validation.EmployeeValidator;
import com.raistudies.domain.CustomExceptionHandler;

@Controller
public class MLIRegistrationController {

	@Autowired
	MLIDetailsService mliDetailsService;
	@Autowired
	StateService stateService;
	@Autowired
	MenuUtilsController menuUtilsController;
	@Autowired
	DistrictService districtService;
	private List<District> userList = new ArrayList<District>();
	@Value("${downloadFileDirPath}")
	private String downloadFileDirPath;
	@Value("${MLIDetailsdownloadFileName}")
	private String downloadFileName;
	@Value("${MLIDetailCheckersdownloadFileName}")
	private String downloadFileName1;
	@Autowired
	ServletContext context;
	public static final int BUFFER_SIZE = 4096;
	@Autowired
	LoginService lofinService;

	@Autowired
	MLIRegService mliRegService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	EmployeeValidator validator;
	UserPerivilegeDetails userPri;
	NBFCPrivilegeMaster userPrvMst;

	static Logger log = Logger.getLogger(NBFCController.class.getName());
	List<BankDetails> bankDetails = new ArrayList<BankDetails>();
	List<Integer> bankId = new ArrayList<Integer>();
	List<Integer> bankBranchId = new ArrayList<Integer>();
	List<Integer> bankZonId = new ArrayList<Integer>();

	@RequestMapping(value = "/mliRegistrationPage", method = RequestMethod.GET)
	public ModelAndView getMLIDetails(
			@ModelAttribute("command") MLIDEtailsBean mliDetails,
			BindingResult result, Model modelMsg, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		String Role = (String) session.getAttribute("uRole");
		
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
		Map<String, Object> model = new HashMap<String, Object>();
		model = menuUtilsController.prepareMenu(session, Role, userId);
		model.put("mlisList", prepareUnderTakenListofBean(mliDetailsService.getUploadFile(
				mliDetails.getLongName(), "CCA")));
		if(Role.equals("CMAKER")){
			model.put("homePage", "cgtmseMakerHome");
		}
		if(Role.equals("CCHECKER")){
			model.put("homePage", "cgtmseCheckerHome");
		}
		return new ModelAndView("mliRegistrationPage", model);
	}

	@RequestMapping(value = "/EditMLIDetails", method = RequestMethod.GET)
	public ModelAndView editMLIDetails(
			@ModelAttribute("command") MLIDEtailsBean mliDetails,
			BindingResult result, Model modelMsg, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		String Role = (String) session.getAttribute("uRole");
		
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
		Map<String, Object> model = new HashMap<String, Object>();
		model = menuUtilsController.prepareMenu(session, Role, userId);
		model.put(
				"mlisList",
				prepareMLIListofBean(mliDetailsService.getMLIDetails(
						mliDetails.getLongName(), "CME")));
		if(Role.equals("CMAKER")){
			model.put("homePage", "cgtmseMakerHome");
		}
		if(Role.equals("CCHECKER")){
			model.put("homePage", "cgtmseCheckerHome");
		}
		return new ModelAndView("mliRegistrationPage", model);
	}

	@RequestMapping(value = "/getDetails", method = RequestMethod.GET)
	public ModelAndView getPortfolioDetails(
			@ModelAttribute(value = "command") MLIDEtailsBean mliRegistrationBean,
			BindingResult result, String portfolioNum,HttpSession session)
			throws NullPointerException, Exception {
			String userId = (String) session.getAttribute("userId");
			String Role = (String) session.getAttribute("uRole");
			if(mliRegistrationBean.getLongName()==null)
			{	
				mliRegistrationBean.setLongName(mliRegistrationBean.getMliLongName());
			}
			if(mliRegistrationBean.getMliLongName()==null)
			{	
				mliRegistrationBean.setMliLongName(mliRegistrationBean.getLongName());
			}
			
			
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
		Map<String, Object> model = new HashMap<String, Object>();
		MLIDEtailsBean mliDEtailsBean = prepareMLIRegistrationModel(mliDetailsService
				.getMLIDetails(mliRegistrationBean.getMliLongName()));
		Map<String, String> mapLongNameObj1 = districtService
				.listDistricts("RAJ");
		userList.clear();
		for (Map.Entry<String, String> entry : mapLongNameObj1.entrySet()) {
			District s1 = new District();
			String value = entry.getValue();
			s1.setDst_name(value);
			userList.add(s1);
		}
		mapLongNameObj1.clear();
		// model.put("stateList",prepareStateListofBean(stateService.listStateMaster()));
		model = menuUtilsController.prepareMenu(session, Role, userId);
		model.put("stateList",
				prepareStateListofBean(stateService.listStateMaster()));
		model.put("ratingAgencyList", stateService.mliRatingList());
		model.put("mliDetail", mliDEtailsBean);
		model.put("stateNameObj", prepareStateListofBean(mliDetailsService
				.getStatename(mliDEtailsBean.getState())));

		model.put("districtList", userList);
		if(Role.equals("CMAKER")){
			model.put("homePage", "cgtmseMakerHome");
		}
		if(Role.equals("CCHECKER")){
			model.put("homePage", "cgtmseCheckerHome");
		}
		return new ModelAndView("mliEditPage", model);
	}

	@RequestMapping(value = "/getDistrictList", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse2 addUser(@ModelAttribute(value = "state") District district,
			BindingResult result, String state) {
		JsonResponse2 res = new JsonResponse2();
		Map<String, String> mapLongNameObj1 = districtService
				.listDistricts(state);
		userList.clear();
		for (Map.Entry<String, String> entry : mapLongNameObj1.entrySet()) {
			District s1 = new District();
			String value = entry.getValue();
			s1.setDst_name(value);
			userList.add(s1);
		}
		mapLongNameObj1.clear();
		res.setStatus("SUCCESS");
		res.setResult(userList);

		return res;
	}

	@RequestMapping(value = "/mlidetailsByIndex", method = RequestMethod.GET)
	public ModelAndView getMLIDetailsByIndex(
			@ModelAttribute("command") MLIDEtailsBean mliDetails,
			BindingResult result, Model modelMsg, HttpSession session) {
			String userId = (String) session.getAttribute("userId");
			String Role = (String) session.getAttribute("uRole");
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
		Map<String, Object> model = new HashMap<String, Object>();
		
		// added by say 22 nov
		// 2018-------------------------------------------------
		validator.searchValidator(mliDetails, result);
		if (result.hasErrors()) {
			// log.info("Error in field*******************************************************************************************************************");
			model = menuUtilsController.prepareMenu(session, Role, userId);
			model.put("mlisList", prepareMLIListofBean(mliDetailsService
					.getMLIDetails(mliDetails.getLongName(), "CCA")));
			/*
			 * model.put( "mlisList",
			 * prepareMLIListofBean(mliDetailsService.getMLIAllDetails()));
			 */
			//Added by say 6 FEb-------------------------------------------------------------
//			model.put("adminlist",
//					userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
			/*model.put("repList",
					userActivityService.getReport("CGTMSEMAKER", "User_Report"));
			model.put("guaranteelist",
					userActivityService.getActivity("CGTMSEMAKER", "Registration"));
			model.put("applicationList",
					userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
			model.put("RPaymentList",
					userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
			model.put("GMaintainlist", userActivityService.getActivity(
					"CGTMSEMAKER", "Guarantee_Maintenance"));
//			model.put("actList",
//					userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
			model.put("actName", userActivityService.getActivityName(
					"CGTMSEMAKER", "mliRegistrationPage"));// Added by Say 31
															// Jan19
			model.put("homePage", "cgtmseCheckerHome");*/
			if(Role.equals("CCHECKER")){
				model.put("homePage", "cgtmseCheckerHome");
			}
			if(Role.equals("CMAKER")){
				model.put("homePage", "cgtmseMakerHome");
			}
			return new ModelAndView("mliRegistrationPage", model);

		}
		// ended---------------------------------------------------------------------
		model = menuUtilsController.prepareMenu(session, Role, userId);
		model.put("mlisList", prepareUnderTakenListofBean(mliDetailsService
				.getMLIDetailsbyFileIndex(mliDetails.getNameSearch(),
						mliDetails.getSearchValue())));
		/*
		 * model.put( "mlisList",
		 * prepareMLIListofBean(mliDetailsService.getMLIAllDetails()));
		 */
		//Added by say 6 FEb-------------------------------------------------------------
		/*model.put("repList",
				userActivityService.getReport("CGTMSEMAKER", "User_Report"));
		model.put("adminlist",
				userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
		model.put("guaranteelist",
				userActivityService.getActivity("CGTMSEMAKER", "Registration"));
		model.put("applicationList",
				userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
		model.put("RPaymentList",
				userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
		model.put("GMaintainlist", userActivityService.getActivity(
				"CGTMSEMAKER", "Guarantee_Maintenance"));
//		model.put("actList",
//				userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
		model.put("actName", userActivityService.getActivityName("CGTMSEMAKER",
				"mliRegistrationPage"));// Added by Say 31 Jan19
		model.put("homePage", "cgtmseCheckerHome");*/
		if(Role.equals("CCHECKER")){
			model.put("homePage", "cgtmseCheckerHome");
		}
		if(Role.equals("CMAKER")){
			model.put("homePage", "cgtmseMakerHome");
		}
		return new ModelAndView("mliRegistrationPage", model);
	}
	
	private List<MLIDEtailsBean> prepareMLIListofBean(
			List<MLIRegistration> employees) {
		List<MLIDEtailsBean> beans = null;
		if (employees != null && !employees.isEmpty()) {
			beans = new ArrayList<MLIDEtailsBean>();
			MLIDEtailsBean bean = null;
			for (MLIRegistration employee : employees) {
				bean = new MLIDEtailsBean();
				bean.setGstinNumber(employee.getGstinNumber());
				bean.setMem_bnk_id(employee.getMem_bnk_id());
				bean.setMliLongName(employee.getLongName());
				bean.setShortName(employee.getShortName());
				bean.setCompanyAddress(employee.getCompanyAddress());
				bean.setCity(employee.getCity());
				bean.setCompanyPAN(employee.getCompanyPAN());
				bean.setContactPerson(employee.getContactPerson());
				bean.setMobileNUmber(employee.getMobileNUmber());
				bean.setEmailId(employee.getEmailId());
				if (employee.getStatus().equals("CCA")) {
					bean.setStatus("Approved");
				} else if (employee.getStatus().equals("CME")) {
					bean.setStatus("Pending for Approval");
				} else if (employee.getStatus().equals("CCR")) {
					bean.setStatus("Rejected");
				} else if (employee.getStatus().equals("CEMMA")) {
					bean.setStatus("CEMMA");
				} else if (employee.getStatus().equals("CMR")) {
					bean.setStatus("Pending For Approval(New)");
				}else if (employee.getStatus().equals("CER")) {
					bean.setStatus("Rejected");
				}
				beans.add(bean);
			}
//			Collections.sort(beans, MLIDEtailsBean.NameComparator);
		}
		return beans;
	}

	private MLIDEtailsBean prepareMLIRegistrationModel(
			MLIRegistration mliRegistration) throws Exception {
		MLIDEtailsBean mliRegBean = new MLIDEtailsBean();
		mliRegBean.setMliType(mliRegistration.getMliType());
		mliRegBean.setCity(mliRegistration.getCity());
		mliRegBean.setCompanyAddress(mliRegistration.getCompanyAddress());
		mliRegBean.setCompanyCIN(mliRegistration.getCompanyCIN());
		mliRegBean.setCompanyPAN(mliRegistration.getCompanyPAN());
		mliRegBean.setContactPerson(mliRegistration.getContactPerson());
		mliRegBean.setDistrict(mliRegistration.getDistrict());
		mliRegBean.setEmailId(mliRegistration.getEmailId());
		mliRegBean.setFaxNumber(mliRegistration.getFaxNumber());
		mliRegBean.setGstinNumber(mliRegistration.getGstinNumber());
		mliRegBean.setLandlineNumber(mliRegistration.getLandlineNumber());
		mliRegBean.setLongName(mliRegistration.getLongName());
		mliRegBean.setMobileNUmber(mliRegistration.getMobileNUmber());
		mliRegBean.setPincode(mliRegistration.getPincode());
		mliRegBean.setRating(mliRegistration.getRating());
		mliRegBean.setRatingAgency(mliRegistration.getRatingAgency());

		/*System.out.println("employeeBean.getPortfolio_start_date()  :"
				+ mliRegistration.getRatingDate());*/
		if(mliRegistration.getRatingDate()!=null && !"".equals(mliRegistration.getRatingDate()))
		{
		String date = mliRegistration.getRatingDate().substring(0, 10);
		String dateSplit[] = date.split("-");
		String formatedSanctionDate = dateSplit[2] + "/" + dateSplit[1] + "/"
				+ dateSplit[0];
		mliRegBean.setRatingDate(formatedSanctionDate);
		}
		mliRegBean.setShortName(mliRegistration.getShortName());

		mliRegBean.setState(mliRegistration.getState());

		// System.out.println("StateName------"+StateName);
		mliRegBean.setRbiReggistrationNumber(mliRegistration
				.getRbiReggistrationNumber());
		mliRegBean.setPhone_code(mliRegistration.getPhone_code());

		return mliRegBean;
	}

	private List<StateBean> prepareStateListofBean(List<StateMaster> employees) {
		List<StateBean> beans = null;
		if (employees != null && !employees.isEmpty()) {
			beans = new ArrayList<StateBean>();
			StateBean bean = null;
			for (StateMaster employee : employees) {
				bean = new StateBean();
				bean.setSte_code(employee.getSte_code());
				bean.setSte_name(employee.getSte_name());
				beans.add(bean);
			}
		}
		return beans;
	}

	// For DownLoadExcel File Added by Say 5 Feb 19

	private List<MliUploadDetails> prepareUnderTakenListofBean(
			List<MliUploadDetails> upload) {
		List<MliUploadDetails> beans = null;
		if (upload != null && !upload.isEmpty()) {
			beans = new ArrayList<MliUploadDetails>();
			MliUploadDetails bean = null;
			for (MliUploadDetails data : upload) {
				bean = new MliUploadDetails();
				bean.setGstinNumber(data.getGstinNumber());
				bean.setMem_bnk_id(data.getMem_bnk_id());
				bean.setMliLongName(data.getMliLongName());
				bean.setShortName(data.getShortName());
				bean.setCompanyAddress(data.getCompanyAddress());
				bean.setCity(data.getCity());
				bean.setCompanyPAN(data.getCompanyPAN());
				bean.setContactPerson(data.getContactPerson());
				bean.setMobileNUmber(data.getMobileNUmber());
				bean.setEmailId(data.getEmailId());
				if(data.getFileName()!=null && !"".equals(data.getFileName()))
				{
					bean.setFileName(data.getFileName());
					bean.setFileExtension(data.getFileExtension());
					bean.setFilePath(data.getFilePath());
				}
				
				
				if (data.getStatus().equals("CCA")) {
					bean.setStatus("Approved");
				} else if (data.getStatus().equals("CME")) {
					bean.setStatus("Pending for Approval");
				} else if (data.getStatus().equals("CCR")) {
					bean.setStatus("Rejected");
				} else if (data.getStatus().equals("CEMMA")) {
					bean.setStatus("CEMMA");
				} else if (data.getStatus().equals("CMR")) {
					bean.setStatus("Pending For Approval(New)");
				}else if (data.getStatus().equals("CER")) {
					bean.setStatus("Rejected");
				}
				beans.add(bean);
			}
		}
		return beans;
	}
	@RequestMapping(value = "/MliRegistrationDetailDownload", method = RequestMethod.GET)
	public ModelAndView userRoleCreationdownLoad(
			@ModelAttribute("command") User userDetails, BindingResult result,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws IOException {
			String userId = (String) session.getAttribute("userId");
		
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} 
		try {
			
			//downloadFileDirPath=context.getRealPath("Download");
			
			String filePath = downloadFileDirPath + File.separator
					+ downloadFileName;

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

			List<MLIRegistration> list = mliDetailsService.getMLIAllDetails();

			// List<UserPerivilegeDetails>
			// list=employeeService.getUserPrivlageDetails();
			log.info("list size==" + list.size());
			log.info("list Data==" + list);

			// Writing and Downlaoding Excel File

			XSSFWorkbook hwb = new XSSFWorkbook();
			XSSFSheet sheet = hwb.createSheet("UserRoleDownLoadedFile");

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
			cell1.setCellValue("Contact Person");// Done 3

			XSSFCell cell2 = rowhead.createCell(2);
			cell2.setCellStyle(style);
			cell2.setCellValue("Mobile No.");// Done 4

			XSSFCell cell3 = rowhead.createCell(3);
			cell3.setCellStyle(style);
			cell3.setCellValue("Email ID");// Done 5

			XSSFCell cell4 = rowhead.createCell(4);
			cell4.setCellStyle(style);
			cell4.setCellValue("Company PAN");// Done 6

			XSSFCell cell5 = rowhead.createCell(5);
			cell5.setCellStyle(style);
			cell5.setCellValue("Status");// Done 7

			int index = 1;
			int sno = 0;
			Iterator<MLIRegistration> itr2 = list.iterator();
			while (itr2.hasNext()) {
				MLIRegistration obj1 = (MLIRegistration) itr2.next();
				sno++;
				XSSFRow row = sheet.createRow((short) index);
				//

				row.createCell((short) 0).setCellValue(obj1.getLongName());// Done
																			// 1
				row.createCell((short) 1).setCellValue(obj1.getContactPerson());// Done
																				// 2
				row.createCell((short) 2).setCellValue(obj1.getMobileNUmber());// Done
																				// 3
				row.createCell((short) 3).setCellValue(obj1.getEmailId());// Done
																			// 4
				row.createCell((short) 4).setCellValue(obj1.getCompanyPAN());// Done
																				// 5

				if (obj1.getStatus().equals("CCA")) {
				row.createCell((short) 5).setCellValue("Approved");// Done 5
				} else if (obj1.getStatus().equals("CME")) {
					row.createCell((short) 5).setCellValue(
							"Pending for Approval(Edit)");
				} else if (obj1.getStatus().equals("CCR")) {
					row.createCell((short) 5).setCellValue("Rejected");
				} else if (obj1.getStatus().equals("CEMMA")) {
					row.createCell((short) 5).setCellValue("CEMMA");

				} else if (obj1.getStatus().equals("CMR")) {
					row.createCell((short) 5).setCellValue(
							"Pending For Approval(New)");

				}

				index++;
			}
			FileOutputStream fileOut = new FileOutputStream(filePath);
			hwb.write(fileOut);
			fileOut.close();

			ModelAndView model = new ModelAndView("newRolePage");

			model.addObject(
					"excelFileDownLoadSuccessfully",
					"File DownLoaded Successfully in this location F:/ExcelReports/nbfcUserRoleExcelFile.xls.");

			File downloadFile = new File(filePath);
			log.info("downloadFile =" + downloadFile);
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
			log.info("Exception == " + e);
			System.out.println("Exception == " + e);
		}
		return null;
	}

	// For DownLoadExcel File Added by Say 22May19

		@RequestMapping(value = "/MliRegistrationDetailCheckerDownload", method = RequestMethod.GET)
		public ModelAndView MliRegistrationDetailCheckerDownload(
				@ModelAttribute("command") User userDetails, BindingResult result,
				HttpServletRequest request, HttpServletResponse response,
				HttpSession session) throws IOException {
			String userId = (String) session.getAttribute("userId");
			
			if (userId == null) {
				return new ModelAndView("redirect:/SVLogin.html");
			} 
			try {
				//downloadFileDirPath=context.getRealPath("Download");
				
				String filePath = downloadFileDirPath + File.separator
						+ downloadFileName1;

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

				List<MLIRegistration> list = mliDetailsService.getMLIDetailsForApproval("CME", "CMR","CCA");

				// List<UserPerivilegeDetails>
				// list=employeeService.getUserPrivlageDetails();
				log.info("list size==" + list.size());
				log.info("list Data==" + list);

				// Writing and Downlaoding Excel File

				XSSFWorkbook hwb = new XSSFWorkbook();
				XSSFSheet sheet = hwb.createSheet("UserRoleDownLoadedFile");

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
				cell1.setCellValue("Contact Person");// Done 3

				XSSFCell cell2 = rowhead.createCell(2);
				cell2.setCellStyle(style);
				cell2.setCellValue("Mobile No.");// Done 4

				XSSFCell cell3 = rowhead.createCell(3);
				cell3.setCellStyle(style);
				cell3.setCellValue("Email ID");// Done 5

				XSSFCell cell4 = rowhead.createCell(4);
				cell4.setCellStyle(style);
				cell4.setCellValue("Company PAN");// Done 6

				XSSFCell cell5 = rowhead.createCell(5);
				cell5.setCellStyle(style);
				cell5.setCellValue("Status");// Done 7

				int index = 1;
				int sno = 0;
				Iterator<MLIRegistration> itr2 = list.iterator();
				while (itr2.hasNext()) {
					MLIRegistration obj1 = (MLIRegistration) itr2.next();
					sno++;
					XSSFRow row = sheet.createRow((short) index);
					//

					row.createCell((short) 0).setCellValue(obj1.getLongName());// Done
																				// 1
					row.createCell((short) 1).setCellValue(obj1.getContactPerson());// Done
																					// 2
					row.createCell((short) 2).setCellValue(obj1.getMobileNUmber());// Done
																					// 3
					row.createCell((short) 3).setCellValue(obj1.getEmailId());// Done
																				// 4
					row.createCell((short) 4).setCellValue(obj1.getCompanyPAN());// Done
																					// 5

					if (obj1.getStatus().equals("CCA")) {
					row.createCell((short) 5).setCellValue("Approved");// Done 5
					} else if (obj1.getStatus().equals("CME")) {
						row.createCell((short) 5).setCellValue(
								"Pending for Approval(Edit)");
					} else if (obj1.getStatus().equals("CCR")) {
						row.createCell((short) 5).setCellValue("Rejected");
					} else if (obj1.getStatus().equals("CEMMA")) {
						row.createCell((short) 5).setCellValue("CEMMA");

					} else if (obj1.getStatus().equals("CMR")) {
						row.createCell((short) 5).setCellValue(
								"Pending For Approval(New)");

					}

					index++;
				}
				FileOutputStream fileOut = new FileOutputStream(filePath);
				hwb.write(fileOut);
				fileOut.close();

				ModelAndView model = new ModelAndView("newRolePage");

				model.addObject(
						"excelFileDownLoadSuccessfully",
						"File DownLoaded Successfully in this location F:/ExcelReports/nbfcUserRoleExcelFile.xls.");

				File downloadFile = new File(filePath);
				log.info("downloadFile =" + downloadFile);
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
				log.info("Exception == " + e);
				System.out.println("Exception == " + e);
			}
			return null;
		}
	@ExceptionHandler(CustomExceptionHandler.class)
	public ModelAndView handleCustomException(CustomExceptionHandler ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();

		// model1.put("actList", userActivityService.getActivity("NBFCCHECKER",
		// "User_Activity"));
		// model1.put("homePage","nbfcCheckerHome");
		ModelAndView model = new ModelAndView("customException", model1);
		model.addObject("customException", ex.getMessage());
		ex.printStackTrace();
		return model;

	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();

		// model1.put("actList", userActivityService.getActivity("NBFCCHECKER",
		// "User_Activity"));
		// model1.put("homePage","nbfcCheckerHome");
		ModelAndView model = new ModelAndView("exception", model1);
		model.addObject("exception", ex.getCause());
		ex.printStackTrace();
		return model;

	}

	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView handleArithException(ArithmeticException ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();

		// model1.put("actList", userActivityService.getActivity("NBFCCHECKER",
		// "User_Activity"));
		// model1.put("homePage","nbfcCheckerHome");
		ModelAndView model = new ModelAndView("exception", model1);
		model.addObject("exception", ex.getMessage());
		ex.printStackTrace();
		return model;

	}

	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullException(NullPointerException ex) {
		Map<String, Object> model1 = new HashMap<String, Object>();

		// model1.put("actList", userActivityService.getActivity("NBFCCHECKER",
		// "User_Activity"));
		// model1.put("homePage","nbfcCheckerHome");
		ModelAndView model = new ModelAndView("exception", model1);
		model.addObject("exception", "Data is null");
		ex.printStackTrace();
		return model;

	}

}
