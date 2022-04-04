package com.nbfc.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
//import //org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nbfc.bean.MLIMakerFileUploadBean;
import com.nbfc.bean.MLIMakerInterfaceUploadedBean;
import com.nbfc.bean.PortfolioDetailsBean;
import com.nbfc.exception.NBFCException;
import com.nbfc.helper.BulkUpload0;
import com.nbfc.helper.BulkUploadCleanup;
import com.nbfc.helper.ExcelHelperFile;
import com.nbfc.helper.ExcelValidator;
import com.nbfc.helper.FileExportHelper;
import com.nbfc.helper.NBFCHelper;
import com.nbfc.helper.TableDetailBean;
import com.nbfc.helper.UploadFileHelper;
import com.nbfc.model.DistrictName;
import com.nbfc.model.ExcelFile;
import com.nbfc.model.MliMakerEntity;
import com.nbfc.model.NBFCPrivilegeMaster;
import com.nbfc.model.PortfolioNumInfo;
import com.nbfc.model.PortfolioNumberInfo;
import com.nbfc.model.PortfolioValidate;
import com.nbfc.model.StateName;
import com.nbfc.model.UserInfo;
import com.nbfc.model.UserPerivilegeDetails;
import com.nbfc.service.DistrictService;
import com.nbfc.service.LoginService;
import com.nbfc.service.MLIValidatorService;
import com.nbfc.service.NPAService;
import com.nbfc.service.PortfolioApprovalService;
import com.nbfc.service.PortfolioBatchService;
import com.nbfc.service.PortfolioInfoService;
import com.nbfc.service.StateService;
import com.nbfc.service.UserActivityService;
import com.nbfc.service.UserService;
import com.nbfc.validation.EmployeeValidator;
import com.nbfc.validator.DataValidation;
import com.raistudies.domain.CustomExceptionHandler;

import net.sf.jmimemagic.Magic;

/**
 * @author Saurav Tyagi 2017
 * 
 */
@Controller
public class NBFCInterfaceUploadController {

	
	@Autowired
	PortfolioInfoService portfolioInfoService;
	@Autowired
	StateService stateService;
	@Autowired
	PortfolioBatchService portfolioBatchService;
	@Autowired
	DistrictService districtService;
	@Autowired
	EmployeeValidator validator;
	@Autowired
	PortfolioApprovalService portfolioApprovalService;
	@Autowired
	MLIValidatorService mliValidatorService;
	@Autowired
	LoginService lofinService;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	NPAService npaService;
	@Autowired
	SessionFactory sessionFactory;
	UserPerivilegeDetails userPri;
	NBFCPrivilegeMaster userPrvMst;
	ExcelValidator excelValidator = new ExcelValidator();
	DataValidation dataValidation = new DataValidation();

	List<Integer> blankErrorKey = new ArrayList<Integer>();
	List<String> blankErrorValue = new ArrayList<String>();

	Map<List<String>, List<String>> errorList = new LinkedHashMap<List<String>, List<String>>();

	Map<List<String>, List<Integer>> errorBlankRowList = new LinkedHashMap<List<String>, List<Integer>>();

	Map<String, String> erroList = new HashMap<String, String>();
	List<String> key = new ArrayList<String>();
	List<String> values = new ArrayList<String>();
	List<MLIMakerInterfaceUploadedBean> mliMakerfileIploadBeanList = new ArrayList<MLIMakerInterfaceUploadedBean>();

	NBFCHelper nbfcCHelper = new NBFCHelper();
	static int count;
	static double sanctiondAmount;

	// NBFCHelper nbfcHelper;
	@RequestMapping(value = "/mliMaker", method = RequestMethod.GET)
	public ModelAndView mliChecker(@ModelAttribute("command") MLIMakerFileUploadBean employeeBean, BindingResult result,
			Model modFr, HttpServletRequest request, HttpSession session) throws Exception {
		NBFCHelper nbfcHelper = new NBFCHelper();
		Map<String, Object> model = new HashMap<String, Object>();
		BulkUpload0 bulkObj = new BulkUpload0();
		String userId = (String) session.getAttribute("userId");
		if (userId != null) {
			userPri = lofinService.getUserPrivlageDtl(userId, "A");
			userPrvMst = lofinService.getPrivlageMstDtl(userPri.getPrv_id());

			if (userPrvMst.getPrvCreatedModifiedBy().equals("NMAKER")) {
				int buploadAccesCount = 0;
				// userActivityService.getUploadUserCount(userId);
				model.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
				// added by say 6 feb-----------------------
				model.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
				model.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
				model.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
				model.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
				model.put("actName", userActivityService.getActivityName("NBFCMAKER", "mliMaker"));// Added by say
																									// 25june19
				model.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
				model.put("GMaintainlist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
				model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
				request.setAttribute("buploadAccesCount", buploadAccesCount);
				// end---------------------------------------------------------------------------------------
				// modelAct.put("actList",
				// userActivityService.getActivity("NBFCMAKER",
				// "User_Activity"));

				model.put("homePage", "nbfcMakerHome");
				// modFr.addAttribute("fYrs", nbfcHelper.getCurrentYear());
				/*
				 * PortfolioDetailsBean bean = new PortfolioDetailsBean();
				 * 
				 * bean = npaService.getExposureDetails(userId); if(bean.getExpLmt()==null){
				 * 
				 * employeeBean.setPendingExp1(null); employeeBean.setUtilExp1(null);
				 * employeeBean.setTotExpSize1(null); employeeBean.setEXP_NO(null);
				 * employeeBean.setGURANTEE_COVERAGE(null); employeeBean.setGURANTEE_FEE(null);
				 * model.put("errormessage",
				 * "Notes:To upload file at first create exposure limit."); }else{
				 * employeeBean.setPAYOUT_CAP(bean.getPayCap());
				 * 
				 * // BigDecimal a=new BigDecimal(bean.getUTIL_EXP()); Long l = (new
				 * Double(bean.getUTIL_EXP())).longValue(); Long m = (new
				 * Double(bean.getPENDING_EXP())).longValue(); Long n = (new
				 * Double(bean.getExpLmt())).longValue();
				 * 
				 * employeeBean.setPendingExp1(m); employeeBean.setUtilExp1(l);
				 * employeeBean.setTotExpSize1(n); employeeBean.setEXP_NO(bean.getEXP_NO());
				 * employeeBean.setGURANTEE_COVERAGE(bean.getGURANTEE_COVERAGE());
				 * employeeBean.setGURANTEE_FEE(bean.getGURANTEE_FEE());
				 * modFr.addAttribute("fYrs", bean.getPortfolioName()); }
				 */
				return new ModelAndView("mliMakerScreen", model);
			} else {
				return new ModelAndView("redirect:/SVLogin.html");
			}
		}
		return new ModelAndView("redirect:/SVLogin.html");

	}

	@RequestMapping(value = "/uploadCleanup", method = RequestMethod.GET)
	public ModelAndView uploadCleanup(
			@ModelAttribute("command") MLIMakerFileUploadBean employeeBean,
			BindingResult result, Model modFr, HttpServletRequest request,
			HttpSession session) throws Exception {
		
		Map<String, Object> model = new HashMap<String, Object>();
		String userId = (String) session.getAttribute("userId");
		if (userId != null) {
			userPri = lofinService.getUserPrivlageDtl(userId, "A");
			userPrvMst = lofinService.getPrivlageMstDtl(userPri.getPrv_id());
			
			if (userPrvMst.getPrvCreatedModifiedBy().equals("NMAKER")) {
				int buploadAccesCount=0;
				//userActivityService.getUploadUserCount(userId);
				model.put("repList", userActivityService.getReport("NBFCMAKER",	"User_Report"));
				// added by say 6 feb-----------------------
				model.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
				model.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
				model.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
				model.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
				model.put("actName", userActivityService.getActivityName("NBFCMAKER", "mliMaker"));// Added by say 25june19
				model.put("repList",userActivityService.getReport("NBFCMAKER", "User_Report"));
				model.put("GMaintainlist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
				model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
				request.setAttribute("buploadAccesCount", buploadAccesCount);
				// end---------------------------------------------------------------------------------------
				model.put("homePage", "nbfcMakerHome");
				return new ModelAndView("uploadCleanupScreen", model);
			} else {
				return new ModelAndView("redirect:/SVLogin.html");
			}
		}
		return new ModelAndView("redirect:/SVLogin.html");
	}
	@RequestMapping(value = "/mliMakers", method = RequestMethod.GET)
	public ModelAndView mliMakers(@ModelAttribute("command") MLIMakerFileUploadBean employeeBean, BindingResult result,
			Model modFr, HttpServletRequest request, HttpSession session) throws Exception {
		NBFCHelper nbfcHelper = new NBFCHelper();
		Map<String, Object> model = new HashMap<String, Object>();

		String userId = (String) session.getAttribute("userId");
		if (userId != null) {
			userPri = lofinService.getUserPrivlageDtl(userId, "A");
			userPrvMst = lofinService.getPrivlageMstDtl(userPri.getPrv_id());
			if (userPrvMst.getPrvCreatedModifiedBy().equals("NMAKER")) {

				model.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
				// added by say 6 feb-----------------------
				model.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
				model.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
				model.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
				model.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
				model.put("actName", userActivityService.getActivityName("NBFCMAKER", "mliMaker"));// Added by say
																									// 25june19
				model.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
				model.put("GMaintainlist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
				model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
				model.put("message", "Batch File Should be .xls");
				// end---------------------------------------------------------------------------------------
				// modelAct.put("actList",
				// userActivityService.getActivity("NBFCMAKER",
				// "User_Activity"));

				model.put("homePage", "nbfcMakerHome");
				// modFr.addAttribute("fYrs", nbfcHelper.getCurrentYear());
				PortfolioDetailsBean bean = new PortfolioDetailsBean();

				bean = npaService.getExposureDetails(userId);

				employeeBean.setPAYOUT_CAP(bean.getPayCap());

				// BigDecimal a=new BigDecimal(bean.getUTIL_EXP());
				Long l = (new Double(bean.getUTIL_EXP())).longValue();
				Long m = (new Double(bean.getPENDING_EXP())).longValue();
				Long n = (new Double(bean.getExpLmt())).longValue();

				employeeBean.setPendingExp1(m);
				employeeBean.setUtilExp1(l);
				employeeBean.setTotExpSize1(n);
//		       	employeeBean.setUTIL_EXP(bean.getUTIL_EXP());
//				employeeBean.setPENDING_EXP(bean.getPENDING_EXP());
//				employeeBean.setTOT_EXP_SIZE(bean.getExpLmt());
				employeeBean.setEXP_NO(bean.getEXP_NO());

				employeeBean.setGURANTEE_COVERAGE(bean.getGURANTEE_COVERAGE());
				employeeBean.setGURANTEE_FEE(bean.getGURANTEE_FEE());
				// employeeBean.setSENCTIONED_PORTFOLIO(bean.getExpLmt());
				modFr.addAttribute("fYrs", bean.getPortfolioName());

				// return new ModelAndView("mliMakerScreen", model);
				return new ModelAndView("mliMakerScreen", model);
			} else {
				return new ModelAndView("redirect:/SVLogin.html");
			}
		}
		return new ModelAndView("redirect:/SVLogin.html");

	}

	@RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
	public ModelAndView fileUploding(@ModelAttribute("command") ExcelFile employeeBean, BindingResult result,
			HttpServletRequest request, HttpSession session) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();

		String userId = (String) session.getAttribute("userId");
		if (userId != null) {
			userPri = lofinService.getUserPrivlageDtl(userId, "A");
			userPrvMst = lofinService.getPrivlageMstDtl(userPri.getPrv_id());
			if (userPrvMst.getPrvCreatedModifiedBy().equals("NMAKER")) {
				model.put("actList", userActivityService.getActivity("NBFCMAKER", "User_Activity"));
				model.put("homePage", "nbfcMakerHome");
				return new ModelAndView("fileUploading", model);
			} else {
				return new ModelAndView("redirect:/SVLogin.html");
			}
		}
		// added by say 6 feb-----------------------
		model.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
		model.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
		model.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
		model.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
		model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
		model.put("actName", userActivityService.getActivityName("NBFCMAKER", "mliMaker"));// Added
																							// by
																							// say
																							// 25june19
		model.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
		model.put("GMaintainlist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
		// end---------------------------------------------------------------------------------------
		// modelAct.put("actList",
		// userActivityService.getActivity("NBFCMAKER", "User_Activity"));
		model.put("homePage", "nbfcMakerHome");
		return new ModelAndView("fileUploading", model);
	}

	@RequestMapping(value = "/fileUploaded", method = RequestMethod.POST)
	public ModelAndView importexcel(@ModelAttribute("command") MLIMakerInterfaceUploadedBean excelFile,
			BindingResult result, ModelMap modelMap, Model modFr, HttpServletRequest request, Model modelMsg,
			HttpSession session) {

		Map<String, Object> model = new HashMap<String, Object>();
		try {
			//String fileName = excelFile.getFile().getOriginalFilename();
			String mimeType = Magic.getMagicMatch(excelFile.getFile().getBytes()).getMimeType();
			System.out.println("original mime type " + mimeType);
			if (!"application/msword".equalsIgnoreCase(mimeType)) {
				String errmessage = "Please upload file xls format only.";
				String userId = (String) session.getAttribute("userId");
				if (userId != null) {
					userPri = lofinService.getUserPrivlageDtl(userId, "A");
					userPrvMst = lofinService.getPrivlageMstDtl(userPri.getPrv_id());
					if (userPrvMst.getPrvCreatedModifiedBy().equals("NMAKER")) {

						model.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
						// added by say 6 feb-----------------------
						model.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
						model.put("guaranteelist",
								userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
						model.put("applicationList",
								userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
						model.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
						model.put("actName", userActivityService.getActivityName("NBFCMAKER", "mliMaker"));
						model.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
						model.put("GMaintainlist",
								userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
						model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
						// end---------------------------------------------------------------------------------------
						model.put("homePage", "nbfcMakerHome");
						model.put("errmessage", errmessage);
						return new ModelAndView("mliMakerScreen", model);
					} else {
						return new ModelAndView("redirect:/SVLogin.html");
					}
				}
				return new ModelAndView("redirect:/SVLogin.html");
			}
			/*Added By Manoj*/
			InputStream is = null;
			try {
				is = excelFile.getFile().getInputStream();
				HSSFWorkbook book = new HSSFWorkbook(is);
			}catch(Exception e) {
				if(e.getMessage().contains("Is it really an excel file?")) {
					String errmessage = "Please upload file xls format only.";
					String userId = (String) session.getAttribute("userId");
					if (userId != null) {
						userPri = lofinService.getUserPrivlageDtl(userId, "A");
						userPrvMst = lofinService.getPrivlageMstDtl(userPri.getPrv_id());
						if (userPrvMst.getPrvCreatedModifiedBy().equals("NMAKER")) {

							model.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
							// added by say 6 feb-----------------------
							model.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
							model.put("guaranteelist",
									userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
							model.put("applicationList",
									userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
							model.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
							model.put("actName", userActivityService.getActivityName("NBFCMAKER", "mliMaker"));
							model.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
							model.put("GMaintainlist",
									userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
							model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement"));
							// end---------------------------------------------------------------------------------------
							model.put("homePage", "nbfcMakerHome");
							model.put("errmessage", errmessage);
							return new ModelAndView("mliMakerScreen", model);
						} else {
							return new ModelAndView("redirect:/SVLogin.html");
						}
					}
					return new ModelAndView("redirect:/SVLogin.html");
				}
			}finally {
				try {
					is.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
			UserInfo user = (UserInfo) session.getAttribute("userInfo");
			Session connSess = sessionFactory.openSession();
			Transaction tn = connSess.beginTransaction();
			Connection conn = connSess.connection();
			try {
				Map<String, ArrayList<String>> UploadedStatus = new HashMap<String, ArrayList<String>>();
				System.out.println("conn :" + conn);
				// ######################
				try {
					BulkUpload0 BlUpObj = new BulkUpload0();
					// String TableName="NBFC_INTERFACE_UPLOAD_STAG";
					String TableName = "sv_upload_stg";
					String BulkName = "APPS_STVN";
					LinkedHashMap<String, TableDetailBean> headerMap;
					headerMap = BlUpObj.getTableHeaderData(conn, TableName, BulkName);
					UploadedStatus = BlUpObj.CheckExcelData(excelFile.getFile(), headerMap, TableName, conn, user,
							BulkName);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						conn.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				// if(UploadedStatus.get("error").size()>0){
				if (UploadedStatus != null) {
					// ArrayList SuccessDataList=(ArrayList)UploadedStatus.get("successRecord");
					// ArrayList UnSuccessDataList=(ArrayList)UploadedStatus.get("unsuccessRecord");
					ArrayList allerrors = (ArrayList) UploadedStatus.get("allerror");
					ArrayList error = (ArrayList) UploadedStatus.get("error");
					ArrayList uploadArray = (ArrayList)UploadedStatus.get("uploadId");
					
					String upldId = "";
					if (error.size()==0 && uploadArray!=null)
						upldId = UploadedStatus.get("uploadId").get(0);
					HttpSession sess = request.getSession(false);
					request.setAttribute("UploadedStatus", UploadedStatus);
					request.setAttribute("upldId", upldId);
					// sess.setAttribute("SuccessDataList", SuccessDataList);
					// sess.setAttribute("UnSuccessDataList", UnSuccessDataList);
					sess.setAttribute("Allerrors", allerrors);
				} /*
					 * else { return new ModelAndView("redirect:/getBulkUploadTrack.html"); }
					 */

				model.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
				model.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
				model.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
				model.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
				model.put("actName", userActivityService.getActivityName("NBFCMAKER", "mliMaker"));// Added
				model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement")); // by
				model.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
				model.put("GMaintainlist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));// 25june19
				model.put("homePage", "nbfcMakerHome");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("batchUploadScreen", model);
	}

	@RequestMapping(value = "/fileCleanup", method = RequestMethod.POST)
	public ModelAndView fileCleanup(@ModelAttribute("command") MLIMakerInterfaceUploadedBean excelFile,
			BindingResult result, ModelMap modelMap, Model modFr, HttpServletRequest request, Model modelMsg,
			HttpSession session) {

		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String fileName = excelFile.getFile1().getOriginalFilename();
			if (!fileName.endsWith(".xls")) {
				return new ModelAndView("redirect:/mliMakers.html");
			}

			UserInfo user = (UserInfo) session.getAttribute("userInfo");
			Session connSess = sessionFactory.openSession();
			Transaction tn = connSess.beginTransaction();
			Connection conn = connSess.connection();
			System.out.println("conn :" + conn);
			BulkUploadCleanup BlUpObj = new BulkUploadCleanup();
			// String TableName="NBFC_INTERFACE_UPLOAD_CLEANUP";
			String TableName = "sv_upload_stg";
			String BulkName = "APPS_STVN";
			LinkedHashMap<String, TableDetailBean> headerMap;
			headerMap = BlUpObj.getTableHeaderDataCleanUp(conn, TableName, BulkName);
			Map<String, ArrayList<String>> UploadedStatus = new HashMap<String, ArrayList<String>>();
			UploadedStatus = BlUpObj.CheckExcelDataCleanUp(excelFile.getFile1(), headerMap, TableName, conn, user,
					BulkName);

			if (UploadedStatus != null) {

				ArrayList SuccessDataList = (ArrayList) UploadedStatus.get("successRecord");
				ArrayList UnSuccessDataList = (ArrayList) UploadedStatus.get("unsuccessRecord");
				ArrayList allerrors = (ArrayList) UploadedStatus.get("allerror");/**/
				System.out.println("allerrors:" + allerrors);
				System.out.println("UnSuccessDataList:" + UnSuccessDataList);
				HttpSession sess = request.getSession(false);
				request.setAttribute("UploadedStatus", UploadedStatus);
				sess.setAttribute("SuccessDataList", SuccessDataList);
				sess.setAttribute("UnSuccessDataList", UnSuccessDataList);
				sess.setAttribute("Allerrors", allerrors);
			}
			model.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
			model.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
			model.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
			model.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
			model.put("actName", userActivityService.getActivityName("NBFCMAKER", "mliMaker"));// Added
			model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement")); // by
			model.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
			model.put("GMaintainlist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));// 25june19
			model.put("homePage", "nbfcMakerHome");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("uploadCleanupSuccessScreen", model);

	}

	@RequestMapping(value = "/fileUploadedcd", method = RequestMethod.POST)
	public ModelAndView fileUploadedcd(HttpSession session) {
		System.out.println("Hello................................................");
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			UserInfo user = (UserInfo) session.getAttribute("userInfo");
			Session connSess = sessionFactory.openSession();
			Transaction tn = connSess.beginTransaction();
			Connection conn = connSess.connection();
			System.out.println("conn :" + conn);
			// ######################

			BulkUpload0 BlUpObj = new BulkUpload0();
			String TableName = "NBFC_INTERFACE_UPLOAD_STAG";
			String BulkName = "Apps";
			LinkedHashMap<String, TableDetailBean> headerMap;
			headerMap = BlUpObj.getTableHeaderData(conn, TableName, BulkName);
			Map<String, ArrayList<String>> UploadedStatus = new HashMap<String, ArrayList<String>>();
			model.put("adminlist", userActivityService.getActivity("NBFCMAKER", "System_Admin"));
			model.put("guaranteelist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
			model.put("applicationList", userActivityService.getActivity("NBFCMAKER", "Application_Processing"));
			model.put("RPaymentList", userActivityService.getActivity("NBFCMAKER", "Receipt_Payments"));
			model.put("actName", userActivityService.getActivityName("NBFCMAKER", "mliMaker"));
			model.put("CList", userActivityService.getActivity("NBFCMAKER", "Claim_Lodgement")); // by
			model.put("repList", userActivityService.getReport("NBFCMAKER", "User_Report"));
			model.put("GMaintainlist", userActivityService.getActivity("NBFCMAKER", "Guarantee_Maintenance"));
			model.put("homePage", "nbfcMakerHome");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("batchUploadScreen", model);

	}

	@RequestMapping(value = "exportToFile", method = RequestMethod.GET)
	public ModelAndView exportToFile(@ModelAttribute("command") MLIMakerInterfaceUploadedBean excelFile,
			BindingResult result, ModelMap modelMap, Model modFr, HttpServletRequest request,
			HttpServletResponse response, Model modelMsg, HttpSession session) throws IOException {
		// System.out.println("exportToFile.............START");

		// OutputStream os = response.getOutputStream();
		Session connSess = sessionFactory.openSession();
		Transaction tn = connSess.beginTransaction();
		Connection conn = connSess.connection();

		Map<String, Object> model = new HashMap<String, Object>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		String strDate = sdf.format(cal.getTime());
		// System.out.println("ExportToFile Calling..");

		// String contextPath1 =
		// request.getSession(false).getServletContext().getRealPath("");
		// String contextPath1 =
		// request.getSession(false).getServletContext().getRealPath("");
		//String contextPath1 = "C://NBFCDownload";
		String contextPath1 = request.getRealPath("/");
		try {
			ArrayList ClmDataList = new ArrayList<Object>();
			System.out.println("exportToFile@contextPath1 :" + contextPath1);
			//String contextPath = contextPath1.replace('\\', '/');
			String contextPath = contextPath1;
			System.out.println("exportToFile@contextPath1 :" + contextPath1);
			// System.out.println("contextPath1 :"+contextPath1);
			// System.out.println("contextPath :"+contextPath);
			HttpSession sess = request.getSession(false);
			String fileType = request.getParameter("fileType");
			String FlowLevel = request.getParameter("FlowLevel");
			String uploadid = request.getParameter("uploadid");
			String uploadflag = request.getParameter("uploadflag");

			// System.out.println("@@@@@@@@@@@@FlowLevel :"+FlowLevel);
			// ArrayList ClmDataList = (ArrayList)sess.getAttribute("ClaimSettledDatalist");
			/*
			 * LinkedHashMap<String, TableDetailBean> headerMap; headerMap =
			 * BlUpObj.getTableHeaderDataCleanUp(conn,TableName,BulkName);
			 */
			if (uploadid == null || uploadid == "")
				ClmDataList = (ArrayList) sess.getAttribute(FlowLevel);
			else
				ClmDataList = BulkUpload0.getDownloadList(uploadid, uploadflag, conn);
			// System.out.println("@@@@@@@@@@@@ClmDataList:"+ClmDataList);
			ArrayList HeaderArrLst = (ArrayList) ClmDataList.get(0);
			// System.out.println("@@@@@@@@@@@@HeaderArrLst:"+HeaderArrLst);
			int NoColumn = HeaderArrLst.size();
			// System.out.println("fileType:"+fileType);

			if (fileType.equals("XLSType")) {
				System.out.println("####################inside if#####################");
				FileExportHelper fileExportHelper = new FileExportHelper();
				byte[] b = fileExportHelper.generateEXL(ClmDataList, NoColumn, contextPath);
				if (response != null)
					response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition", "attachment; filename=ExcelData" + strDate + ".xls");
				OutputStream os = response.getOutputStream();
				os.write(b);
				// os.flush();
				os.close();
			} /**/
			// System.out.println("exportToFile.............END");
		} catch (Exception e) {
			try {
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
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
		return model;

	}

}
