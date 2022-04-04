package com.nbfc.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExcelDownloadController {
	@RequestMapping(value = "/downLoadExcelFile", method = RequestMethod.GET)
	public ModelAndView downloadExcelFile(HttpServletRequest request, HttpSession session, HttpServletResponse response)
			throws Exception {
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			return new ModelAndView("redirect:/SVLogin.html");
		} else {
			//File file = new File("C:\\NBFCDownload\\DownloadBatchExcelFormat.xls");
			//File file = new File(request.getContextPath()+"\\Download\\DownloadBatchExcelFormat.xls");
			@SuppressWarnings("deprecation")
			File file = new File(request.getRealPath("/")+"\\Download\\DownloadBatchExcelFormat.xls");
			String mimeType = URLConnection.guessContentTypeFromName(file.getName());
			if (mimeType == null) {
				System.out.println("mimetype is not detectable, will take default");
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
			response.setContentLength((int) file.length());
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, response.getOutputStream());
		}
		return null;
	}

}
