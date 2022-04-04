package com.nbfc.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nbfc.bean.ApplicationStatusDetailsBean;
import com.nbfc.bean.ClaimLodgementBean;
import com.nbfc.bean.GetMLINameBean;
import com.nbfc.bean.LenderDetailsBO;
import com.nbfc.helper.PropertyLoader;
import com.nbfc.model.MLIIdDetails;
import com.nbfc.service.ApplicationStatusService;
import com.nbfc.service.NBFCUserReportService;

import net.sf.jasperreports.engine.JRException;

@Controller
public class ApplicationStateActivityReportController 
{
	GetMLINameBean getMLINameBean;
	@Autowired
	NBFCUserReportService nbfcUserReportService;
	@Autowired
	ApplicationStatusService applicationStatusService;
	@Autowired
	MenuUtilsController menuUtilsController;
	
	final static Logger logger = Logger.getLogger(ApplicationStateActivityReportController.class.getName());
	
	@RequestMapping(value = "/summaryReportStateActivityWise", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView streetVendorStatusActivityReport(@ModelAttribute("command") ApplicationStatusDetailsBean bean,
			BindingResult result, HttpSession session) throws JRException {
		String userId = (String) session.getAttribute("userId");
		Map<String, Object> model = new HashMap<String, Object>();
		try
		{
			session.setAttribute("summaryReportPage", "summaryReportStateActivityWise");
			userId = (String) session.getAttribute("userId");
			String Role = (String) session.getAttribute("uRole");
			model = menuUtilsController.prepareMenu(session, Role, userId);
			System.out.println("User Role is ::::" + Role);
			if (userId != null) 
			{
				if (model!=null) 
				{
					if("CMAKER".equalsIgnoreCase(Role)){
						model.put("homePage", "cgtmseMakerHome");
						}
					return new ModelAndView("summaryReportStateActivity", model);	
				}
			}
		}
		catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/SVLogin.html");
	}
	
	@RequestMapping(value = "/district-details", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody ResponseEntity<Map<Integer, String>> getDistrictDetails(@RequestParam String state,@ModelAttribute("command") ApplicationStatusDetailsBean bean,
			BindingResult result, HttpSession session) throws JRException 
	{
		Map<Integer, String> rows = new HashMap<Integer, String>();
		Map<String, Object> model = new HashMap<String, Object>();
		try
		{
			session.setAttribute("summaryReportPage", "summaryReportStateActivityWise");
			rows = applicationStatusService.getDistrictDetails(state);
			System.out.println("Rows ::::" + rows.toString());
		}
		catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(rows, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(rows, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getStateActivityWiseInfo", method = {RequestMethod.POST})
	public ModelAndView getStateActivityWiseInfo(@ModelAttribute("command") ApplicationStatusDetailsBean bean,
			BindingResult result, HttpSession session, Model model, HttpServletRequest request,
			HttpServletResponse response) throws JRException, ParseException 
	{
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		Map<String, Object> model1 = new HashMap<String, Object>();
		Date toDate = null , fromDate = null;
		try
		{
			String userId = (String) session.getAttribute("userId");
			
			if (userId == null) {
				return new ModelAndView("redirect:/SVLogin.html");
			}
			getMLINameBean = nbfcUserReportService.getMliDetails(userId);
			session.setAttribute("MliName", getMLINameBean.getMliName());

			String Role = (String) session.getAttribute("uRole");
			
			if (result.hasErrors()) {
				model1 = menuUtilsController.prepareMenu(session, Role, userId);
				return new ModelAndView("summaryReportStateActivity", model1);
			}
			model1 = menuUtilsController.prepareMenu(session, Role, userId);
		    String summaryReportPage = (String) session.getAttribute("summaryReportPage");
		    
		    
		    if ("summaryReportStateActivityWise".equalsIgnoreCase(summaryReportPage)) 
		    {
		    	String toDateF = bean.getToDate();
				String fromDateF = bean.getFromDate();
				String state = bean.getState();
				String district = bean.getDistrict();
				String activity = bean.getActivity();
				
				session.setAttribute("toDateF", toDateF);
				session.setAttribute("fromDateF", fromDateF);
				session.setAttribute("state", state);
				session.setAttribute("district", district);
				session.setAttribute("activity", activity);
				
				if(toDateF != null && toDateF.trim().length()>0){
					toDate = new SimpleDateFormat("dd/MM/yyyy").parse(toDateF);
				}
				if(fromDateF != null && fromDateF.trim().length()>0){
					fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(fromDateF);
				}
				if(state == null || "0".equals(state))
				{
					state = "";
				}
				if(district == null || "0".equals(district)){
					district = "";
				}
				if(activity == null || "0".equals(activity)){
					activity = "";
				}
				System.out.println("toDateF is ::::" + toDate + "\t fromDateF ::::" +fromDate + "\t state ::::"+state + 
				           "\t district ::::"+district + "\t activity ::::"+activity);
				if(fromDate != null && toDate != null){
					rows = applicationStatusService.getStateActivityDetails(fromDate,toDate,state,district,activity);
					session.setAttribute("stateActivityReport", rows);
					if (!rows.isEmpty()) {
						model1.put("rows", rows);
					} else {
						model1.put("noDataFound", "NO Data Found !!");
					}
				}
		    }
		    else
		    {
		    	String toDateF = bean.getToDate();
				String fromDateF = bean.getFromDate();
				String state = bean.getState();
				String district = bean.getDistrict();
				String activity = bean.getActivity();
				
				session.setAttribute("toDateF", toDateF);
				session.setAttribute("fromDateF", fromDateF);
				session.setAttribute("state", state);
				session.setAttribute("district", district);
				session.setAttribute("activity", activity);
				
				if(toDateF != null && toDateF.trim().length()>0){
					toDate = new SimpleDateFormat("dd/MM/yyyy").parse(toDateF);
				}
				if(fromDateF != null && fromDateF.trim().length()>0){
					fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(fromDateF);
				}
				
				if(state == null || "0".equals(state))
				{
					state = "";
				}
				if(district == null || "0".equals(district)){
					district = "";
				}
				if(activity == null || "0".equals(activity)){
					activity = "";
				}
				System.out.println("toDateF is ::::" + toDate + "\t fromDateF ::::" +fromDate + "\t state ::::"+state + 
				           "\t district ::::"+district + "\t activity ::::"+activity);
				if(fromDate != null && toDate != null){
					rows = applicationStatusService.getStateActivityDetails(fromDate,toDate,state,district,activity);
					session.setAttribute("stateActivityReport", rows);
					if (!rows.isEmpty()) {
						model1.put("rows", rows);
					} else {
						model1.put("noDataFound", "NO Data Found !!");
					}
				}
		    }
		}
		catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return new ModelAndView("summaryReportStateActivity", model1);
	}
	
	@RequestMapping(value = "/stateActivityWiseReportDownload", method = RequestMethod.GET)
	public ModelAndView stateActivityWiseReportDownload(@ModelAttribute("command") ClaimLodgementBean bean,
			BindingResult result, HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception 
	{
		try
		{
			System.out.println("Inside Method..stateActivityWiseReportDownload.. ");
			String userId = (String) session.getAttribute("userId");
			if (userId == null) {
				return new ModelAndView("redirect:/SVLogin.html");
			}
			
			OutputStream os = response.getOutputStream();
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
			String strDate = sdf.format(cal.getTime());
			
			String contextPath1 = request.getSession(false).getServletContext().getRealPath("");
			String contextPath = PropertyLoader.changeToOSpath(contextPath1);

			System.out.println("contextPath1 :" + contextPath1);
			System.out.println("contextPath :" + contextPath);
			
			List<Map<String, Object>> list = (List<Map<String, Object>>) session.getAttribute("stateActivityReport");
			System.out.println("LIST ::::" + list);
			
			ArrayList<Object> HeaderArrLst = new ArrayList<Object>();
			
			String key = null;
			for (Map<String, Object> ClmDataList : list) {
				for (Map.Entry<String, Object> entry : ClmDataList.entrySet()) {
					key = entry.getKey();
					System.out.println("ClmDataList key==" + key);
					HeaderArrLst.add(key);
				}
				break;
			}
			int NoColumn = HeaderArrLst.size();
			System.out.println("NoColumn:" + NoColumn);
			
			byte[] b = StreetVendorGenerateCSV(list, NoColumn, contextPath);

			if (response != null)
				response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment; filename=ExcelData" + strDate + ".csv");
			os.write(b);
			os.flush();
		}
		catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public byte[] StreetVendorGenerateCSV(List<Map<String, Object>> list, int No_Column, String contextPath)
			throws IOException {

		System.out.println("---generateCSV()---");
		StringBuffer strbuff = new StringBuffer();
		ArrayList<Object> rowDataLst = new ArrayList<Object>();
		String key = null;
		for (Map<String, Object> HeaderLst : list) {
			for (Map.Entry<String, Object> entry : HeaderLst.entrySet()) {
				key = entry.getKey();
				System.out.println("ClmDataList key==" + key);
				rowDataLst.add(key);
			}
			System.out.println("HeaderLst" + HeaderLst);
			break;
		}
		System.out.println("rowDataLst" + rowDataLst);


		for (Map<String, Object> RecordWiseLst : list) {
			for (Map.Entry<String, Object> entry : RecordWiseLst.entrySet()) {
				Object value = entry.getValue();
				System.out.println("StreetVendor key==" + value);
				rowDataLst.add(value);

			}
			System.out.println("RecordWiseLst" + RecordWiseLst);
		}
		System.out.println("rowDataLst" + rowDataLst);
		ArrayList<Object> FinalrowDatalist = new ArrayList<Object>();
		int y = 0;
		System.out.println("2==" + No_Column);
		for (int n = 0; n < rowDataLst.size(); n++) {

			if (n % No_Column == 0 && n != 0) {
				FinalrowDatalist.add(rowDataLst.get(n));
				FinalrowDatalist.add(n + y, "\n");
				System.out.println("2n value inside if:" + n);
				System.out.println("n:" + n);
				y++;
			} else {
				System.out.println("2n inside else:" + n);
				if (null != rowDataLst.get(n)) {
					if (((Object) rowDataLst.get(n)).equals(",")) {
						rowDataLst.set(n, ((String) rowDataLst.get(n)).replace(",", ";"));
					}
				}
				FinalrowDatalist.add(rowDataLst.get(n));
			}
			System.out.println("rowDataLst.get " + rowDataLst.get(n) + "    " + n % 3);
		}

		String tempStr = FinalrowDatalist.toString().replace("\n,", "\n").replace(" ,", ",").replace(", ", ",");
		strbuff.append(tempStr.substring(1, tempStr.length() - 1));
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		String strDate = sdf.format(cal.getTime());
		BufferedWriter output = null;
		OutputStream outStrm;
		File genfile = new File(contextPath + "\\Download\\DataCSVFile" + strDate + ".csv");

		output = new BufferedWriter(new FileWriter(genfile));
		output.write(strbuff.toString());
		output.flush();
		output.close();
		// System.out.println("8");

		// ##
		// FileInputStream fis = new
		// FileInputStream("D:\\GenerateFiles\\SampleFile" + strDate+ ".csv");
		FileInputStream fis = new FileInputStream(contextPath + "\\Download\\DataCSVFile" + strDate + ".csv");

		// System.out.println("9");
		byte b[];
		int x = fis.available();
		b = new byte[x];
		// System.out.println(" b size"+b.length);

		fis.read(b);
		// ##
		return b;
		// genfile.setReadOnly();
	}
	/*public ModelAndView getStateActivityWiseInfo(@RequestParam String fromDate,String toDate,String state,String district,String activity,@ModelAttribute("command") ApplicationStatusDetailsBean bean,BindingResult result, HttpSession session) throws JRException
	{
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		Map<String, Object> model1 = new HashMap<String, Object>();
		try
		{
			System.out.println("Inside getStateActivityWiseInfo Method!!!!");
			System.out.println("fromDate ::["+fromDate+"]\t toDate ::["+toDate+"]\t state ::["+state+"]\t district ::["+district+"]\t activity ::["+activity+"]");
			if("0".equals(state))
			{
				state = "";
			}
			if("0".equals(district)){
				district = "";
			}
			if("0".equals(activity)){
				activity = "";
			}
			
			Date fromDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(fromDate);
			Date toDate1 = new SimpleDateFormat("dd/MM/yyyy").parse(toDate);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			rows = applicationStatusService.getStateActivityDetails(fromDate1,toDate1,state,district,activity);
			
			System.out.println("ROWS ::::11" + rows);
			if (!rows.isEmpty()) {
				model1.put("rows", rows);
			} else {
				model1.put("noDataFound", "NO Data Found !!");
			}
		}
		catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return new ModelAndView("summaryReportStateActivity", model1);
	}*/
	
	
}
