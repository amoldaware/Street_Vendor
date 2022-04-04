package com.nbfc.helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nbfc.bean.MLIMakerInterfaceUploadedBean;
import com.nbfc.model.MliMakerEntity;
import com.nbfc.service.NPAService;

/**
 * @author Saurav Tyagi 2017
 * 
 */
public class NBFCHelper {

	
	List<String> yrsList= new ArrayList<String>();
	
	
	public MliMakerEntity prepareModelForFileUpload(
			MLIMakerInterfaceUploadedBean employeeBean) {
		MliMakerEntity employee = new MliMakerEntity();

		//employee.setBUSINESS_PRODUCT(employeeBean.getBUSINESS_PRODUCT());
		employee.setCATEGORY(employeeBean.getCATEGORY());
		employee.setCHIEF_PROMOTER_CONTACT_NUMBER(employeeBean
				.getCHIEF_PROMOTER_CONTACT_NUMBER());
		employee.setCHIEF_PROMOTER_IT_PAN(employeeBean
				.getCHIEF_PROMOTER_IT_PAN());
		employee.setCHIEF_PROMOTER_MAIL_ID(employeeBean
				.getCHIEF_PROMOTER_MAIL_ID());
		employee.setCHIEF_PROMOTER_FIRST_NAME(employeeBean
				.getCHIEF_PROMOTER_FIRST_NAME());
		employee.setCHIEF_PROMOTER_MIDDLE_NAME(employeeBean
				.getCHIEF_PROMOTER_MIDDLE_NAME());
		employee.setCHIEF_PROMOTER_LAST_NAME(employeeBean
				.getCHIEF_PROMOTER_LAST_NAME());
		employee.setCITY(employeeBean.getCITY());
		employee.setCONSTITUTION(employeeBean.getCONSTITUTION());
		employee.setCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE(employeeBean
				.getCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE());
		System.out.println("employeeBean.getINSERT_DATE_TIME()"
				+ employeeBean.getINSERT_DATE_TIME());
		employee.setDISTRICT(employeeBean.getDISTRICT());
		employee.setFILE_PATH(employeeBean.getFILE_PATH());
		employee.setFIRST_DISBURSEMENT_DATE(employeeBean
				.getFIRST_DISBURSEMENT_DATE());
		//employee.setFIRST_TIME_CUSTOMER(employeeBean.getFIRST_TIME_CUSTOMER());
		employee.setFLAG(employeeBean.getFLAG());
		employee.setHANDICAPPED(employeeBean.getHANDICAPPED());
		employee.setINDUSTRY_NATURE(employeeBean.getINDUSTRY_NATURE());
		employee.setINDUSTRY_SECTOR(employeeBean.getINDUSTRY_SECTOR());
		// employee.setINSERT_DATE_TIME(employeeBean.getINSERT_DATE_TIME());
		employee.setINTEREST_RATE(employeeBean.getINSERT_RATE());
		employee.setLOAN_ACCOUNT_NO(employeeBean.getLOAN_ACCOUNT_NO());
		employee.setLONE_TYPE(employeeBean.getLONE_TYPE());
		employee.setMICRO_SMALL(employeeBean.getMICRO_SMALL());
		employee.setMINORITY_COMMUNITY(employeeBean.getMINORITY_COMMUNITY());
		employee.setMSE_ADDRESS(employeeBean.getMSE_ADDRESS());
		employee.setMSE_ITPAN(employeeBean.getMSE_ITPAN());
		employee.setMSE_NAME(employeeBean.getMSE_NAME());
		//employee.setMSME_REGISTRATION_NO(employeeBean.getMSME_REGISTRATION_NO());
		employee.setNEW_EXISTING_UNIT(employeeBean.getNEW_EXISTING_UNIT());
		employee.setNO_OF_EMPLOYEES(employeeBean.getNO_OF_EMPLOYEES());
		employee.setPINCODE(employeeBean.getPINCODE());
		employee.setPORTFOLIO_BASE_YER(employeeBean.getPORTFOLIO_BASE_YER());
		System.out.println("Sanction Date " + employeeBean.getSNCTION_DATE());
		employee.setPORTFOLIO_NO(employeeBean.getPORTFOLIO_NO());
		System.out.println("employee" + employee.getSNCTION_DATE());
		employee.setPROJECTED_EXPORTS(employeeBean.getPROJECTED_EXPORTS());
		employee.setSTATUS("NMA");
		employee.setPROJECTED_SALES(employeeBean.getPROJECTED_SALES());
		employee.setQUARTER_NO(employeeBean.getQUARTER_NO());
		employee.setUSR_ID("AKZPT0125");
		employee.setREMARKS(employeeBean.getREMARKS());
		employee.setSANCTIONED_AMOUNT(employeeBean.getSANCTIONED_AMOUNT());
		employee.setSNCTION_DATE(employeeBean.getSNCTION_DATE());
		employee.setSTATE(employeeBean.getSTATE());
		employee.setTENOR_IN_MONTHS(employeeBean.getTENOR_IN_MONTHS());
		employee.setUDYOG_AADHAR_NO(employeeBean.getUDYOG_AADHAR_NO());
		employee.setWOMEN(employeeBean.getWOMEN());
		//employee.setDISBURSEMENT_STATUS(employeeBean.getDisbursement_status());

		return employee;
	}

	public List<MliMakerEntity> prepareListofEntity(List<MLIMakerInterfaceUploadedBean> employees,String userId,NPAService npaService) {
		List<MliMakerEntity> beans = null;
		if (employees != null && !employees.isEmpty()) {
			beans = new ArrayList<MliMakerEntity>();
			MliMakerEntity bean = null;
			for (MLIMakerInterfaceUploadedBean employee : employees) {
				bean = new MliMakerEntity();
				
				//bean.setBUSINESS_PRODUCT(employee.getBUSINESS_PRODUCT());
				bean.setCATEGORY(employee.getCATEGORY());
				bean.setCHIEF_PROMOTER_CONTACT_NUMBER(employee
						.getCHIEF_PROMOTER_CONTACT_NUMBER());
				bean.setCHIEF_PROMOTER_IT_PAN(employee
						.getCHIEF_PROMOTER_IT_PAN());
				bean.setCHIEF_PROMOTER_MAIL_ID(employee
						.getCHIEF_PROMOTER_MAIL_ID());
				bean.setCHIEF_PROMOTER_FIRST_NAME(employee
						.getCHIEF_PROMOTER_FIRST_NAME());
				bean.setCHIEF_PROMOTER_MIDDLE_NAME(employee
						.getCHIEF_PROMOTER_MIDDLE_NAME());
				bean.setCHIEF_PROMOTER_LAST_NAME(employee
						.getCHIEF_PROMOTER_LAST_NAME());
				bean.setCITY(employee.getCITY());
				bean.setCONSTITUTION(employee.getCONSTITUTION());
				bean.setCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE(employee
						.getCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE());
				System.out.println("employee.getINSERT_DATE_TIME()"
						+ employee.getINSERT_DATE_TIME());
				bean.setDISTRICT(employee.getDISTRICT());
				bean.setFILE_PATH(employee.getFILE_PATH());
				bean.setFIRST_DISBURSEMENT_DATE(employee
						.getFIRST_DISBURSEMENT_DATE());
				//bean.setFIRST_TIME_CUSTOMER(employee.getFIRST_TIME_CUSTOMER());
				bean.setFLAG(employee.getFLAG());
				bean.setHANDICAPPED(employee.getHANDICAPPED());
				bean.setINDUSTRY_NATURE(employee.getINDUSTRY_NATURE());
				bean.setINDUSTRY_SECTOR(employee.getINDUSTRY_SECTOR());
				// bean.setINSERT_DATE_TIME(employee.getINSERT_DATE_TIME());
				bean.setINTEREST_RATE(employee.getINSERT_RATE());
				bean.setLOAN_ACCOUNT_NO(employee.getLOAN_ACCOUNT_NO());
				bean.setLONE_TYPE(employee.getLONE_TYPE());
				bean.setMICRO_SMALL(employee.getMICRO_SMALL());
				bean.setMINORITY_COMMUNITY(employee.getMINORITY_COMMUNITY());
				bean.setMSE_ADDRESS(employee.getMSE_ADDRESS());
				bean.setMSE_ITPAN(employee.getMSE_ITPAN());
				bean.setMSE_NAME(employee.getMSE_NAME());
				//bean.setMSME_REGISTRATION_NO(employee.getMSME_REGISTRATION_NO());
				bean.setNEW_EXISTING_UNIT(employee.getNEW_EXISTING_UNIT());
				bean.setNO_OF_EMPLOYEES(employee.getNO_OF_EMPLOYEES());
				bean.setPINCODE(employee.getPINCODE());
				bean.setPORTFOLIO_BASE_YER(employee.getPORTFOLIO_BASE_YER());
				System.out.println("Sanction Date "
						+ employee.getSNCTION_DATE());
				bean.setPORTFOLIO_NAME(employee.getPORTFOLIO_NAME());
				bean.setPORTFOLIO_NO(employee.getPORTFOLIO_NO());
				System.out.println("employee" + bean.getSNCTION_DATE());
				bean.setPROJECTED_EXPORTS(employee.getPROJECTED_EXPORTS());
				bean.setSTATUS("NMA");
				bean.setPROJECTED_SALES(employee.getPROJECTED_SALES());
				bean.setQUARTER_NO(employee.getQUARTER_NO());
				bean.setUSR_ID(userId);
				bean.setREMARKS(employee.getREMARKS());
				bean.setSANCTIONED_AMOUNT(employee.getSANCTIONED_AMOUNT());
				bean.setSNCTION_DATE(employee.getSNCTION_DATE());
				bean.setSTATE(employee.getSTATE());
				bean.setTENOR_IN_MONTHS(employee.getTENOR_IN_MONTHS());
				bean.setUDYOG_AADHAR_NO(employee.getUDYOG_AADHAR_NO());
				bean.setRETAIL_TRADE(employee.getRETAIL_TRADE());
				
				if(employee.getWOMEN()==null){
					bean.setWOMEN("");
				}else{
					bean.setWOMEN(employee.getWOMEN());
				}
				
				//bean.setDISBURSEMENT_STATUS(employee.getDisbursement_status());
				bean.setFILE_ID(employee.getFILE_ID());
				bean.setPARTIAL_SECURITY_FLAG(employee.getPartialSecurityFlag());
				bean.setGUARANTEE_AMOUNT(employee.getGuaranteeAmount());

				bean.setCOLLETRAL_SECURITY_AMOUNT(employee.getColletralSecurityAmount());

				bean.setOUTSTANDING_AMOUNT(employee.getOutstandingAmount());
				bean.setAADHAR_NUMBER(employee.getAADHAR_NUMBER());//new add by Saurav Tyagi 28/08/2018
				bean.setCGPAN(npaService.getCGPAN());
				beans.add(bean);
				
			}
		}
		return beans;
	}
	
	public String getCurrentYear(){
		 //int year = getYearFromDate(new Date());
		 String fYrs;
		 int year = Calendar.getInstance().get(Calendar.YEAR);

		    int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		    System.out.println("Financial month : " + month);
		    if (month <= 3) {
		    	fYrs= (year - 1) + "-" + year;
		    } else {
		    	fYrs=year + "-" + (year + 1);
		    }
		 //=year + "-" + (year+1);
	      /*  System.out.println("Financial Year : " + year + "-" + (year+1));
	        System.out.println("Financial month : " + getMonthFromDate(new Date()));*/
		 //System.out.println("fYrs"+fYrs);
		return fYrs;
	}
	private static int getMonthFromDate(Date date) {
        int result = -1;
           if (date != null) {
               Calendar cal = Calendar.getInstance();
               cal.setTime(date);
               result = cal.get(Calendar.MONTH)+1;
           }
           return result;
   }

   public static int getYearFromDate(Date date) {
       int result = -1;
       if (date != null) {
           Calendar cal = Calendar.getInstance();
           cal.setTime(date);
           result = cal.get(Calendar.YEAR);
       }
       return result;
   }
}