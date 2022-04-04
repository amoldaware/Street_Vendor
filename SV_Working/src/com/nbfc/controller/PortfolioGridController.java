
package com.nbfc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.nbfc.bean.PortfolioGridPanBean;
import com.nbfc.model.PortfolioGridPanModel;
import com.nbfc.service.PortfolioGridPaneService;
import com.nbfc.service.UserActivityService;
import com.nbfc.service.UserService;
import com.nbfc.validation.EmployeeValidator;
import com.raistudies.domain.CustomExceptionHandler;


@Controller
public class PortfolioGridController {
	
	@Autowired
	UserActivityService userActivityService;
	@Autowired
	PortfolioGridPaneService portfolioGridPaneService;
	@Autowired
	UserService userService;
	@Autowired
	EmployeeValidator validator;
	static Logger log = Logger.getLogger(PortfolioGridController.class.getName());

	@RequestMapping(value = "/portfolioGrid", method = RequestMethod.GET)
	public ModelAndView getPortfolioDetails(
			@ModelAttribute(value = "command") com.nbfc.bean.PortfolioGridPanBean portfolioGridPanBean,
			BindingResult result, String userId) throws CustomExceptionHandler {
		log.info("Enter from getPortfolioDetails() method in PortfolioGridController class ");

		Map<String, Object> model = null;
		model = new HashMap<String, Object>();
		List<PortfolioGridPanBean> list = preparePortfolioSearchListofBean(portfolioGridPaneService.getPortfolioData());
//		if (list.size() > 0) {
//			model.put("portfolioList", list);
//		} else {
//			throw new CustomExceptionHandler("No data in DB");
//		}
		model.put("portfolioList", list);
		model.put("actList",
				userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
		model.put("homePage","cgtmseMakerHome");
		return new ModelAndView("portFolioGrid", model);
	}
	
	@RequestMapping(value = "/portfolioGridPage", method = RequestMethod.POST)
	public ModelAndView getPortfoioDetails(
			@ModelAttribute(value = "command") com.nbfc.bean.PortfolioGridPanBean portfolioGridPanBean,
			BindingResult result, String userId) throws CustomExceptionHandler {
		log.info("Enter from getPortfolioDetails() method in PortfolioGridController class ");

		Map<String, Object> model = null;
		model = new HashMap<String, Object>();
		List<PortfolioGridPanBean> list = preparePortfolioSearchListofBean(portfolioGridPaneService.getPortfolioData());
//		if (list.size() > 0) {
//			model.put("portfolioList", list);
//		} else {
//			throw new CustomExceptionHandler("No data in DB");
//		}
		model.put("portfolioList", list);
		model.put("actList",
				userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
		model.put("homePage","cgtmseMakerHome");
		return new ModelAndView("portFolioGrid", model);
	}
	//Added by Say 22 nov 2018-------------------------------------------------------
		@RequestMapping(value = "/seachDataByNames", method = RequestMethod.GET)
		public ModelAndView getMLIDetailsByIndex(
				@ModelAttribute("command") PortfolioGridPanBean portfolioGridPanBean,
				BindingResult result, Model modelMsg, HttpSession session) {
			log.info("Enter from getMLIDetailsByIndex() method in PortfolioGridController class ");
			List<Object> listObj=new ArrayList<Object>();
			List<PortfolioGridPanBean> list1=new ArrayList<PortfolioGridPanBean>();
			Map<String, Object> model = new HashMap<String, Object>();
			try {
				validator.searchValidatorForPortfolio(portfolioGridPanBean, result);
				if (result.hasErrors()) {
					//log.info("Error in field*******************************************************************************************************************");
					List<PortfolioGridPanBean> list = preparePortfolioSearchListofBean(portfolioGridPaneService.getPortfolioData());
//					if (list.size() > 0) {
//						model.put("portfolioList", list);
//					} else {
//						throw new CustomExceptionHandler("No data in DB");
//					}
					model.put("portfolioList", list);
					model.put("actList",
							userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
					model.put("homePage","cgtmseMakerHome");
					return new ModelAndView("portFolioGrid", model);

				}
				
				if (portfolioGridPanBean.getNameSearch().equals("Long_name")&& !portfolioGridPanBean.getNameSearch().isEmpty()) {
				
				     listObj=portfolioGridPaneService.getPortfolioDetailsbymliLongName(portfolioGridPanBean.getNameSearch(),portfolioGridPanBean.getSearchValue());
					if(listObj.size()!=0){
						Iterator<Object> itr1= listObj.iterator();
					
						while(itr1.hasNext()){
						Object[] obj1 = (Object[]) itr1.next();
						    Integer portfolioNo =(Integer) obj1[0];
					        Long size=(Long) obj1[1];
						    String portfolioName=(String) obj1[2];
						    String portDate=   (String) obj1[3];
//							SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");	
//							String reportDate = df.format(portDate);
							String status=(String) obj1[4];
							String longname=(String) obj1[5];
							
							portfolioGridPanBean.setPortfolioNo(String.valueOf(portfolioNo));
							portfolioGridPanBean.setPortfolioName(portfolioName);
							portfolioGridPanBean.setPortfolioStartDate(portDate);
							portfolioGridPanBean.setLong_name(longname);
							portfolioGridPanBean.setPortfolioStatus(status);
							portfolioGridPanBean.setMax_portfolio_size(size);
							list1.add(portfolioGridPanBean);
							
						}
					}
					model.put("portfolioList", list1);
				}else if(portfolioGridPanBean.getNameSearch().equals("portfolioName")&& !portfolioGridPanBean.getNameSearch().isEmpty()){
					
					model.put("portfolioList",
							preparePortfolioSearchListofBean(portfolioGridPaneService.getPortfolioDetailsbyIndex(portfolioGridPanBean.getNameSearch(),portfolioGridPanBean.getSearchValue())));	
				}
			//	List<PortfolioGridPanBean> list =portfolioGridPaneService.getexpoid(portfolioGridPanBean.getSearchValue());
			
			//	pb.setLong_name(userService.getMLILongName(userService.getMemBankId(sd.getExposureID()).getMEM_BNK_ID()).getLongName());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.put("actList",
					userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
			model.put("homePage","cgtmseMakerHome");
			return new ModelAndView("portFolioGrid", model);
		}
		//ended-------------------------------------------------------------------------------
//	@RequestMapping(value = "/seachDataByNames", method = RequestMethod.GET)
//	public ModelAndView getMLIDetailsByIndex(
//			@ModelAttribute("command") PortfolioGridPanBean portfolioGridPanBean,
//			BindingResult result, Model modelMsg, HttpSession session) {
//		log.info("Enter from getMLIDetailsByIndex() method in PortfolioGridController class ");
//
//		Map<String, Object> model = new HashMap<String, Object>();
//		try {
//			model.put("portfolioList",
//					preparePortfolioSearchListofBean(portfolioGridPaneService
//							.getPortfolioDetailsbyIndex(
//									portfolioGridPanBean.getNameSearch(),
//									portfolioGridPanBean.getSearchValue())));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		model.put("actList",
//				userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
//		model.put("homePage","cgtmseMakerHome");
//		return new ModelAndView("portFolioGrid", model);
//	}
//	
//	
	/*@RequestMapping(value = "/EditPortfolioDetails", method = RequestMethod.GET)
	public ModelAndView editMLIDetails(
			@ModelAttribute("command") PortfolioGridPanBean portfolioGridPanBean,
			BindingResult result, Model modelMsg, HttpSession session) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put(
				"portfolioEdit",
				preparePortfolioEditListofBean(portfolioGridPaneService.getPortfolioDetails(portfolioGridPanBean.getPortfolioNo())));
		model.put("actList",
				userActivityService.getActivity("CGTMSEMAKER", "User_Activity"));
		model.put("homePage", "cgtmseCheckerHome");
		return new ModelAndView("sanctionDetails", model);
	}*/
	/*private PortfolioGridPanBean preparePortfolioEditListofBean(
			PortfolioGridPanModel sd) {
		log.info("Enter from preparePortfolioEditListofBean() method in PortfolioGridController class ");

		PortfolioGridPanBean pb=null;
		
		if(sd!=null){		
					pb=new PortfolioGridPanBean();
					pb.setLong_name(sd.getLong_name());
					pb.setMax_portfolio_size(sd.getMax_portfolio_size());
					pb.setPortfolioNo(String.valueOf(sd.getPortfolio_no()));
					pb.setPortfolioName(sd.getPortFolioName());
				}
		return pb;
			}*/
				
	private List<PortfolioGridPanBean> preparePortfolioSearchListofBean(List<PortfolioGridPanModel> portfolioData) {
		log.info("Enter from preparePortfolioSearchListofBean() method in PortfolioGridController class ");

		List<PortfolioGridPanBean> list=new ArrayList<PortfolioGridPanBean>();
		PortfolioGridPanBean pb=null;
		// TODO Auto-generated method stub
		if(portfolioData.size()>0){		
	
		for(PortfolioGridPanModel sd:portfolioData){
			//DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
			pb=new PortfolioGridPanBean();
			pb.setLong_name(userService.getMLILongName(userService.getMemBankId(sd.getExposureID()).getMEM_BNK_ID()).getLongName());
			pb.setMax_portfolio_size(sd.getMax_portfolio_size());
			pb.setPortfolioNo(String.valueOf(sd.getPortfolio_no()));
			pb.setPortfolioName(sd.getPortFolioName());
			//pb.setShort_name(sd.getShort_name());
			//pb.setExposure_limit(sd.getExposure_limit());
			//pb.setUtilized_exposure_limit("22222");
			//pb.setExposureValidityDate(sd.getEx());
			pb.setPortfolioStatus(sd.getPortfolioStatus());
			//System.out.println("Date "+sd.getPortfolioDate());
			pb.setPortfolioStartDate(sd.getPortfolioDate());
			
			list.add(pb);
		}
		}
		//Added by Say 17jAN2019--------------------------
		else{
			pb=new PortfolioGridPanBean();
			pb.setLong_name("");
		//	pb.setMax_portfolio_size(null);
			pb.setPortfolioNo("");
			pb.setPortfolioName("");
			//pb.setShort_name(sd.getShort_name());
			//pb.setExposure_limit(sd.getExposure_limit());
			//pb.setUtilized_exposure_limit("22222");
			//pb.setExposureValidityDate(sd.getEx());
			pb.setPortfolioStatus("");
			//System.out.println("Date "+sd.getPortfolioDate());
			pb.setPortfolioStartDate("");
			
			list.add(pb);
			
		}
		//END---------------------------------------------------
		return list;
	}
	
	@ExceptionHandler(CustomExceptionHandler.class)
	public ModelAndView handleCustomException(CustomExceptionHandler ex) {
		ModelAndView model = new ModelAndView("customException");
		model.addObject("customException", ex.getMessage());
		return model;

	}
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		ModelAndView model = new ModelAndView("exception");
		model.addObject("exception", ex.getCause());
		return model;

	}
	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView handleArithException(ArithmeticException ex) {
		ModelAndView model = new ModelAndView("exception");
		model.addObject("exception", ex.getCause());
		return model;

	}
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullException(NullPointerException ex) {
		ModelAndView model = new ModelAndView("exception");
		model.addObject("exception", ex.getCause());
		return model;

	}
}
