package com.nbfc.controller;

import java.io.*;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nbfc.bean.MLIDEtailsBean;
import com.nbfc.bean.MliUploadDetails;
import com.nbfc.service.MLIDetailsService;
import com.nbfc.service.MLIRegService;
import com.nbfc.service.UserActivityService;

@Controller
public class FileDownloadController {
	@Value("${downloadFileDirPath}")
	private String downloadFileDirPath;
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	MLIRegService mliRegService;
	@Autowired
	MLIDetailsService mliDetailsService;
	@Autowired
	ServletContext context;

	@RequestMapping(value = "/downloadMli", method = RequestMethod.GET)
	public ModelAndView downloadExcelFile(@ModelAttribute("command") MLIDEtailsBean mliDetails,
			BindingResult result,  Model modelMsg,HttpServletRequest request, HttpSession session, HttpServletResponse response,@RequestParam("fileName") String fileName)
			throws Exception {
		
		//downloadFileDirPath=context.getRealPath("Download");
	
		String userId = (String) session.getAttribute("userId");
		
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} else {
			
			File file = new File(downloadFileDirPath+"\\"+fileName);
			if(!file.exists())
			{
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("mlisList", prepareUnderTakenListofBean(mliDetailsService.getUploadFile(
					mliDetails.getLongName(), "CCA")));
				
			model.put("actList",
						userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
				//Added by say 6 FEb-------------------------------------------------------------
//				model.put("adminlist",
//						userActivityService.getActivity("CGTMSEMAKER", "System_Admin"));
				model.put("guaranteelist",
						userActivityService.getActivity("CGTMSEMAKER", "Registration"));
				model.put("applicationList",
						userActivityService.getActivity("CGTMSEMAKER", "Application_Processing"));
				model.put("RPaymentList",
						userActivityService.getActivity("CGTMSEMAKER", "Receipt_Payments"));
				model.put("GMaintainlist", userActivityService.getActivity(
						"CGTMSEMAKER", "Guarantee_Maintenance"));
//				model.put("actList",
//						userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
				
				model.put("actName", userActivityService.getActivityName("CGTMSEMAKER",
						"mliRegistrationPage"));// Added by Say 31 Jan19

				model.put("homePage", "cgtmseMakerHome");
				model.put("message", "File Not Found");
				return new ModelAndView("mliRegistrationPage", model);

			}
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				System.out.println("mimetype is not detectable, will take default");
				mimeType = "application/pdf";
			}
			response.setContentType(mimeType);
			//response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
			response.setHeader("Content-Disposition", "attachment; filename=\\"+ file.getName());
			response.setContentLength((int) file.length());
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
			response.getOutputStream().flush();
		}
		return null;
	}
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
}
