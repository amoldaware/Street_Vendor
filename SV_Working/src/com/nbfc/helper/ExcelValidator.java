package com.nbfc.helper;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ibm.icu.text.SimpleDateFormat;
import com.nbfc.bean.MLIMakerFileUploadBean;
import com.nbfc.bean.MLIMakerInterfaceUploadedBean;
import com.nbfc.bean.StateBean;
import com.nbfc.model.DistrictName;
import com.nbfc.model.MliMakerEntity;
import com.nbfc.model.PortfolioValidate;
import com.nbfc.model.PromoterDetails;
import com.nbfc.model.SSIDetail;
import com.nbfc.model.State;
import com.nbfc.model.StateName;
import com.nbfc.service.MLIValidatorService;
import com.nbfc.validator.DataValidation;

public class ExcelValidator {

	private static final Object Integer = null;
	private static final Object String = null;
	static boolean flag = false;
	public static int rowCount;
	static String msgFlage = null;
	String message = null;
	static Map<String, String> errorList = new HashMap<String, String>();
	DataValidation dataValidation = new DataValidation();
	private Cell c08;
	private Cell c008;
	static int count;

	// ##
	Cell c02 = null;
	Cell c021 = null;
	Cell c = null;
	Cell c3 = null;
	Cell c2 = null;
	Cell c022 = null;
	Cell c5 = null;
	Cell c6 = null;
	Cell c8 = null;
	Cell c9 = null;
	Cell c06 = null;
	Cell c38 = null;
	Cell c39 = null;
	Cell c40 = null;
	Cell c140 = null;
	Cell c41 = null;
	Cell c10 = null;
	Cell c11 = null;
	Cell c12 = null;
	Cell c13 = null;
	Cell c14 = null;
	Cell c15 = null;
	Cell c16 = null;
	Cell c17 = null;
	Cell c18 = null;
	Cell c20 = null;
	Cell c21 = null;
	Cell c22 = null;
	Cell c23 = null;
	Cell c24 = null;
	Cell c25 = null;
	Cell c26 = null;
	Cell c28 = null;
	Cell c29 = null;
	Cell c30 = null;
	Cell c31 = null;
	Cell c32 = null;
	Cell c33 = null;
	Cell c34 = null;
	Cell c35 = null;
	Cell c36 = null;
	Cell c37 = null;
	Cell c001 = null;

	// ##

	public String passwordGenrator() {

		String SALTCHARS = "ACD@EFa#bcdef$GHIJKghijkLMNO&PQklmnopqRSTUVW@rstu#vwXY$Zxyz$12@34#56$78@90#12$34@56#78$90&";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) {
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();

		return saltStr;

	}

	public String userIDGenrator(String fName, String lName) {

		String l = lName.substring(0, 1);
		System.out.println(l);
		String SALTCHARS = "0123456789";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 4) {
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = fName + l + salt.toString();
		System.out.println(saltStr);
		return saltStr;

	}

	public String headerValidator(XSSFRow row) {

		System.out.println(row.getCell(0).getStringCellValue());
		if (!row.getCell(0).getStringCellValue().equalsIgnoreCase("Loan Type")) {
			msgFlage = "Loan Type Field is Missing !!";
		} else if (!row.getCell(1).getStringCellValue()
				.equalsIgnoreCase("Business Product")) {
			msgFlage = "Business Product Field is Missing !!";
		} else if (!row.getCell(2).getStringCellValue()
				.equalsIgnoreCase("Appl Ref No")) {
			msgFlage = "Appl Ref No Field is Missing !!";
		} else if (!row.getCell(3).getStringCellValue()
				.equalsIgnoreCase("Constitution")) {
			msgFlage = "Constitution Field is Missing !!";
		} else if (!row.getCell(4).getStringCellValue()
				.equalsIgnoreCase("MSE Name")) {
			msgFlage = "MSE Name Field is Missing !!";
		} else if (!row.getCell(5).getStringCellValue()
				.equalsIgnoreCase("Sanction Date")) {
			msgFlage = "Sanction Date Field is Missing !!";
		} else if (!row.getCell(6).getStringCellValue()
				.equalsIgnoreCase("Sanctioned Amount")) {
			msgFlage = "Sanctioned Amount Field is Missing !!";
		} else if (!row.getCell(7).getStringCellValue()
				.equalsIgnoreCase("First Disbursement Date")) {
			msgFlage = "First Disbursement Date Field is Missing !!";
		} else if (!row.getCell(8).getStringCellValue()
				.equalsIgnoreCase("Interest Rate")) {
			msgFlage = "Interest Rate Field is Missing !!";
		} else if (!row.getCell(9).getStringCellValue()
				.equalsIgnoreCase("Micro/Small")) {
			msgFlage = "Micro/Small Field is Missing !!";
		} else if (!row.getCell(10).getStringCellValue()
				.equalsIgnoreCase("MSE Address")) {
			msgFlage = "MSE Address Field is Missing !!";
		} else if (!row.getCell(11).getStringCellValue()
				.equalsIgnoreCase("City")) {
			msgFlage = "City Field is Missing !!";
		} else if (!row.getCell(12).getStringCellValue()
				.equalsIgnoreCase("District")) {
			msgFlage = "District Field is Missing !!";
		} else if (!row.getCell(13).getStringCellValue()
				.equalsIgnoreCase("Pincode")) {
			msgFlage = "Pincode Field is Missing !!";
		} else if (!row.getCell(14).getStringCellValue()
				.equalsIgnoreCase("State")) {
			msgFlage = "State Field is Missing !!";
		} else if (!row.getCell(15).getStringCellValue()
				.equalsIgnoreCase("ITPan")) {
			msgFlage = "ITPan Field is Missing !!";
		} else if (!row.getCell(16).getStringCellValue()
				.equalsIgnoreCase("Udyog Aadhar No")) {
			msgFlage = "Udyog Aadhar No Field is Missing !!";
		} else if (!row.getCell(17).getStringCellValue()
				.equalsIgnoreCase("Industry Nature")) {
			msgFlage = "Industry Nature Field is Missing !!";
		} else if (!row.getCell(18).getStringCellValue()
				.equalsIgnoreCase("Industry Sector")) {
			msgFlage = "Industry Sector Field is Missing !!";
		} else if (!row.getCell(19).getStringCellValue()
				.equalsIgnoreCase("No Of Employees")) {
			msgFlage = "No Of Employees Field is Missing !!";
		} else if (!row.getCell(20).getStringCellValue()
				.equalsIgnoreCase("Projected Sales")) {
			msgFlage = "Projected Sales Field is Missing !!";
		} else if (!row.getCell(21).getStringCellValue()
				.equalsIgnoreCase("Projected Exports")) {
			msgFlage = "Projected Exports Field is Missing !!";
		} else if (!row.getCell(22).getStringCellValue()
				.equalsIgnoreCase("New/Existing Unit")) {
			msgFlage = "New/Existing Unit Field is Missing !!";
		} else if (!row.getCell(23).getStringCellValue()
				.equalsIgnoreCase("Unbanked Customer")) {
			msgFlage = "Unbanked Customer Field is Missing !!";
		} else if (!row.getCell(24).getStringCellValue()
				.equalsIgnoreCase("New Customer")) {
			msgFlage = "New Customer Field is Missing !!";
		} else if (!row.getCell(25).getStringCellValue()
				.equalsIgnoreCase("Chief Promater Name")) {
			msgFlage = "Chief Promater Name Field is Missing !!";
		} else if (!row.getCell(26).getStringCellValue()
				.equalsIgnoreCase("Minority Community")) {
			msgFlage = "Minority Community Field is Missing !!";
		} else if (!row.getCell(27).getStringCellValue()
				.equalsIgnoreCase("Handicapped")) {
			msgFlage = "Handicapped Field is Missing !!";
		} else if (!row.getCell(28).getStringCellValue()
				.equalsIgnoreCase("Women")) {
			msgFlage = "Women Field is Missing !!";
		} else if (!row.getCell(29).getStringCellValue()
				.equalsIgnoreCase("Category")) {
			msgFlage = "Category Field is Missing !!";
		} else if (!row.getCell(30).getStringCellValue()
				.equalsIgnoreCase("SSI Registration No")) {
			msgFlage = "SSI Registration No Field is Missing !!";
		} else if (!row.getCell(31).getStringCellValue()
				.equalsIgnoreCase("Promoter IT-PAN")) {
			msgFlage = "Promoter IT-PAN Field is Missing !!";
		} else if (!row.getCell(32).getStringCellValue()
				.equalsIgnoreCase("TENURE")) {
			msgFlage = "TENURE Field is Missing !!";
		}

		else {

			msgFlage = "success";

		}

		return msgFlage;
	}

	public String portfolioHeaderValidator(
			MLIValidatorService mliValidatorService,
			HashMap<Integer, String> headerMap) {

		HashMap<Integer, String> headerMapDB = mliValidatorService
				.getHeaderDetails();

		if (headerMap.entrySet().containsAll(headerMapDB.entrySet())) {
			msgFlage = "success";
			System.out.println("Header Is successfuly match....");

		}

		/*
		 * System.out.println(row.getCell(0).getStringCellValue()); if
		 * (!row.getCell(0).getStringCellValue().equalsIgnoreCase("LOAN TYPE"))
		 * { msgFlage = "'LOAN TYPE' Field is Missing !!"; } else if
		 * (!row.getCell(1).getStringCellValue()
		 * .equalsIgnoreCase("BUSINESS PRODUCT")) { msgFlage =
		 * "'BUSINESS PRODUCT' Field is Missing !!"; } else if
		 * (!row.getCell(1).getStringCellValue()
		 * .equalsIgnoreCase("LOAN ACCOUNT NO.")) { msgFlage =
		 * "'LOAN ACCOUNT NO.' Field is Missing !!"; } else if
		 * (!row.getCell(2).getStringCellValue()
		 * .equalsIgnoreCase("CONSTITUTION")) { msgFlage =
		 * "'CONSTITUTION' Field is Missing !!"; } else if
		 * (!row.getCell(4).getStringCellValue() .equalsIgnoreCase("MSE NAME"))
		 * { //Edit by Saurav Tyagi 10/07/2018 else if
		 * (!row.getCell(3).getStringCellValue() .equalsIgnoreCase("UNIT NAME"))
		 * { msgFlage = "'UNIT NAME' Field is Missing !!"; } else if
		 * (!row.getCell(4).getStringCellValue()
		 * .equalsIgnoreCase("SANCTION DATE")) { msgFlage =
		 * "'SANCTION DATE' Field is Missing !!"; } //Changed BY Saurav Tyagi
		 * 10/07/2018 else if (!row.getCell(5).getStringCellValue()
		 * .equalsIgnoreCase("SANCTIONED AMOUNT")) {
		 * 
		 * msgFlage = "'SANCTIONED AMOUNT' Field is Missing !!"; } else if
		 * (!row.getCell(7).getStringCellValue()
		 * .equalsIgnoreCase("WHETHER DISBURSED/NOT")) { msgFlage =
		 * "'WHETHER DISBURSED/NOT' Field is Missing !!"; } else if
		 * (!row.getCell(6).getStringCellValue()
		 * .equalsIgnoreCase("FIRST DISBURSEMENT DATE")) { msgFlage =
		 * "'FIRST DISBURSEMENT DATE' Field is Missing !!"; } else if
		 * (!row.getCell(7).getStringCellValue()
		 * .equalsIgnoreCase("INTEREST RATE (% p.a)")) { msgFlage =
		 * "'INTEREST RATE (% p.a)' Field is Missing !!"; } else if
		 * (!row.getCell(8).getStringCellValue()
		 * .equalsIgnoreCase("TENURE IN MONTHS")) { msgFlage =
		 * "'TENURE IN MONTHS' Field is Missing !!"; } else if
		 * (!row.getCell(9).getStringCellValue()
		 * .equalsIgnoreCase("WHETHER THE ASSISTED UNIT IS MICRO/SMALL ?")) {
		 * msgFlage =
		 * "'WHETHER THE ASSISTED UNIT IS MICRO/SMALL ?' Field is Missing !!"; }
		 * else if (!row.getCell(10).getStringCellValue()
		 * .equalsIgnoreCase("MSE ADDRESS")) { msgFlage =
		 * "'MSE ADDRESS' Field is Missing !!"; } else if
		 * (!row.getCell(11).getStringCellValue() .equalsIgnoreCase("CITY")) {
		 * msgFlage = "'CITY' Field is Missing !!"; } else if
		 * (!row.getCell(12).getStringCellValue() .equalsIgnoreCase("DISTRICT"))
		 * { msgFlage = "'DISTRICT' Field is Missing !!"; } else if
		 * (!row.getCell(13).getStringCellValue() .equalsIgnoreCase("PINCODE"))
		 * { msgFlage = "'PINCODE' Field is Missing !!"; } else if
		 * (!row.getCell(14).getStringCellValue() .equalsIgnoreCase("STATE")) {
		 * msgFlage = "'STATE' Field is Missing !!"; } else if
		 * (!row.getCell(15).getStringCellValue()
		 * .equalsIgnoreCase("MSE ITPAN")) { msgFlage =
		 * "'MSE ITPAN' Field is Missing !!"; } else if
		 * (!row.getCell(16).getStringCellValue()
		 * .equalsIgnoreCase("UDYOG AADHAR NO.")) { msgFlage =
		 * "'UDYOG AADHAR NO.' Field is Missing !!"; } else if
		 * (!row.getCell(19).getStringCellValue()
		 * .equalsIgnoreCase("MSME REGISTRATION NO.")) { msgFlage =
		 * "'MSME REGISTRATION NO.' Field is Missing !!"; } else if
		 * (!row.getCell(17).getStringCellValue()
		 * .equalsIgnoreCase("INDUSTRY NATURE")) { msgFlage =
		 * "'INDUSTRY NATURE' Field is Missing !!"; } else if
		 * (!row.getCell(18).getStringCellValue()
		 * .equalsIgnoreCase("INDUSTRY SECTOR")) { msgFlage =
		 * "'INDUSTRY SECTOR' Field is Missing !!"; } else if
		 * (!row.getCell(19).getStringCellValue()
		 * .equalsIgnoreCase("NO. OF EMPLOYEES")) { msgFlage =
		 * "'NO. OF EMPLOYEES' Field is Missing !!"; } else if
		 * (!row.getCell(20).getStringCellValue()
		 * .equalsIgnoreCase("PROJECTED SALES")) { msgFlage =
		 * "'PROJECTED SALES' Field is Missing !!"; } else if
		 * (!row.getCell(21).getStringCellValue()
		 * .equalsIgnoreCase("PROJECTED EXPORTS")) { msgFlage =
		 * "'PROJECTED EXPORTS' Field is Missing !!"; } else if
		 * (!row.getCell(22).getStringCellValue()
		 * .equalsIgnoreCase("NEW/ EXISTING UNIT")) { msgFlage =
		 * "'NEW/ EXISTING UNIT' Field is Missing !!"; } else if (!row
		 * .getCell(23) .getStringCellValue() .equalsIgnoreCase(
		 * "CUSTOMER  HAVING ANY PREVIOUS BANKING EXPERIENCE?")) { msgFlage =
		 * "'CUSTOMER  HAVING ANY PREVIOUS BANKING EXPERIENCE?' Field is Missing !!"
		 * ; } else if (!row.getCell(27).getStringCellValue()
		 * .equalsIgnoreCase("FIRST TIME CUSTOMER ?")) { msgFlage =
		 * "'FIRST TIME CUSTOMER ?' Field is Missing !!"; } else if
		 * (!row.getCell(24).getStringCellValue()
		 * .equalsIgnoreCase("CHIEF PROMOTER FIRST NAME")) { msgFlage =
		 * "'CHIEF PROMOTER FIRST NAME' Field is Missing !!"; } else if
		 * (!row.getCell(25).getStringCellValue()
		 * .equalsIgnoreCase("CHIEF PROMOTER MIDDLE NAME")) { msgFlage =
		 * "'CHIEF PROMOTER MIDDLE NAME' Field is Missing !!"; } else if
		 * (!row.getCell(26).getStringCellValue()
		 * .equalsIgnoreCase("CHIEF PROMOTER LAST NAME")) { msgFlage =
		 * "'CHIEF PROMOTER LAST NAME' Field is Missing !!"; } else if
		 * (!row.getCell(27).getStringCellValue()
		 * .equalsIgnoreCase("CHIEF PROMOTER IT-PAN")) { msgFlage =
		 * "'CHIEF PROMOTER IT-PAN' Field is Missing !!"; } else if
		 * (!row.getCell(28).getStringCellValue()
		 * .equalsIgnoreCase("CHIEF PROMOTER'S MAIL ID")) { msgFlage =
		 * "'CHIEF PROMOTER'S MAIL ID' Field is Missing !!"; } else if
		 * (!row.getCell(29).getStringCellValue()
		 * .equalsIgnoreCase("CHIEF PROMOTER'S CONTACT NUMBER")) { msgFlage =
		 * "'CHIEF PROMOTER'S CONTACT NUMBER' Field is Missing !!"; } else if
		 * (!row.getCell(30).getStringCellValue()
		 * .equalsIgnoreCase("MINORITY COMMUNITY")) { msgFlage =
		 * "'MINORITY COMMUNITY' Field is Missing !!"; } else if
		 * (!row.getCell(31).getStringCellValue()
		 * .equalsIgnoreCase("HANDICAPPED")) { msgFlage =
		 * "'SSI Registration No' Field is Missing !!"; } else if
		 * (!row.getCell(32).getStringCellValue() .equalsIgnoreCase("WOMEN")) {
		 * msgFlage = "'WOMEN' Field is Missing !!"; } else if
		 * (!row.getCell(33).getStringCellValue() .equalsIgnoreCase("CATEGORY"))
		 * { msgFlage = "'CATEGORY' Field is Missing !!"; } // Changed By Saurav
		 * Tyagi on 10/07/2018 else if (!row.getCell(38).getStringCellValue()
		 * .equalsIgnoreCase("PARTIAL SECURITY")) { else if
		 * (!row.getCell(34).getStringCellValue()
		 * .equalsIgnoreCase("PARTIALLY SECURED BY COLLATRAL")) { msgFlage =
		 * "'PARTIALLY SECURED BY COLLATRAL' Field is Missing !!"; }else if
		 * (!row.getCell(35).getStringCellValue()
		 * .equalsIgnoreCase("GUARANTEE AMOUNT")) { msgFlage =
		 * "'GUARANTEE AMOUNT' Field is Missing !!"; }else if
		 * (!row.getCell(36).getStringCellValue()
		 * .equalsIgnoreCase("COLLETRAL SECURITY AMOUNT")) { msgFlage =
		 * "'COLLETRAL SECURITY AMOUNT' Field is Missing !!"; }else if
		 * (!row.getCell(37).getStringCellValue()
		 * .equalsIgnoreCase("OUTSTANDING AMOUNT")) { msgFlage =
		 * "'OUTSTANDING AMOUNT' Field is Missing !!"; }
		 * 
		 * else {
		 */
		// msgFlage = "success";

		/* } */

		return msgFlage;
	}

	public MLIMakerInterfaceUploadedBean dataReader(XSSFRow row,
			MLIMakerInterfaceUploadedBean UploadedexcelFile) {
		MLIMakerInterfaceUploadedBean excelFile = new MLIMakerInterfaceUploadedBean();
		count = 0;
		excelFile.setPORTFOLIO_BASE_YER(UploadedexcelFile
				.getPORTFOLIO_BASE_YER());
		excelFile.setPORTFOLIO_NAME(UploadedexcelFile.getPORTFOLIO_NAME());
		excelFile.setPORTFOLIO_NO(UploadedexcelFile.getPORTFOLIO_NO());

		excelFile.setQUARTER_NO(UploadedexcelFile.getQUARTER_NO());
		excelFile.setFILE_PATH(UploadedexcelFile.getFILE_PATH());
		excelFile.setFILE_ID(UploadedexcelFile.getFILE_ID());

		// System.out.println("Lone Type " +
		// row.getCell(0).getStringCellValue());
		excelFile.setLONE_TYPE(row.getCell(2).getStringCellValue()
				.toUpperCase());

		// System.out.println("Lone Type " +
		// row.getCell(1).getStringCellValue());

		/*
		 * excelFile.setBUSINESS_PRODUCT(row.getCell(1).getStringCellValue()
		 * .toUpperCase());
		 */

		// System.out.println("Lone Type " +
		// row.getCell(2).getStringCellValue());
		if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {
			Long intInstance = (long) row.getCell(3).getNumericCellValue();
			System.out.println("loain AC number:" + intInstance);
			String numberAsString = intInstance.toString();
			System.out.println();
			excelFile.setLOAN_ACCOUNT_NO(numberAsString.toUpperCase());

		} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
			excelFile.setLOAN_ACCOUNT_NO(row.getCell(3).getStringCellValue()
					.toUpperCase());

		}
		/*
		 * excelFile.setLOAN_ACCOUNT_NO(row.getCell(2).getStringCellValue()
		 * .toUpperCase());
		 */

		// System.out.println("Lone Type " +
		// row.getCell(3).getStringCellValue());
		excelFile.setCONSTITUTION(row.getCell(1).getStringCellValue()
				.toUpperCase());

		// System.out.println("Lone Type " +
		// row.getCell(4).getStringCellValue());
		excelFile
				.setMSE_NAME(row.getCell(0).getStringCellValue().toUpperCase());

		// System.out.println("Lone Type " +
		// row.getCell(5).getStringCellValue());

		// System.out.println("Lone Type " + row.getCell(6).getDateCellValue());
		excelFile.setSNCTION_DATE(row.getCell(4).getDateCellValue());

		// System.out.println("Lone Type " +
		// row.getCell(7).getNumericCellValue());
		excelFile.setSANCTIONED_AMOUNT((int) row.getCell(5)
				.getNumericCellValue());
		/*
		 * excelFile.setDisbursement_status(row.getCell(7).getStringCellValue()
		 * .toUpperCase());
		 */
		// System.out.println("Lone Type " + row.getCell(8).getDateCellValue());
		excelFile.setFIRST_DISBURSEMENT_DATE(row.getCell(6).getDateCellValue());

		// System.out.println("Lone Type " +
		// row.getCell(9).getNumericCellValue());
		excelFile.setINSERT_RATE((float) row.getCell(7).getNumericCellValue());

		// System.out.println("Lone Type " +
		// row.getCell(10).getNumericCellValue());
		excelFile.setTENOR_IN_MONTHS((int) row.getCell(13)
				.getNumericCellValue());

		// System.out.println("Lone Type " +
		// row.getCell(11).getStringCellValue());

		excelFile.setRETAIL_TRADE(row.getCell(8).getStringCellValue());

		excelFile.setPartialSecurityFlag(row.getCell(9).getStringCellValue()
				.toUpperCase());

		excelFile.setGuaranteeAmount((long) row.getCell(10)
				.getNumericCellValue());

		excelFile.setColletralSecurityAmount((long) row.getCell(11)
				.getNumericCellValue());

		excelFile.setOutstandingAmount((double) row.getCell(12)
				.getNumericCellValue());

		excelFile.setMICRO_SMALL(row.getCell(14).getStringCellValue()
				.toUpperCase());

		// System.out.println("Lone Type " +
		// row.getCell(12).getStringCellValue());
		excelFile.setMSE_ADDRESS(row.getCell(15).getStringCellValue()
				.toUpperCase());

		// System.out.println("Lone Type " +
		// row.getCell(13).getStringCellValue());
		excelFile.setCITY(row.getCell(16).getStringCellValue().toUpperCase());

		// System.out
		// .println("Lone Type " + row.getCell(14).getStringCellValue());
		Cell c17 = row.getCell(17);
		if (c17 == null || c17.getCellType() == Cell.CELL_TYPE_BLANK) {
		} else {
			excelFile.setDISTRICT(row.getCell(17).getStringCellValue()
					.toUpperCase());
		}
		// System.out.println("Lone Type " +
		// row.getCell(15).getNumericCellValue());
		excelFile.setPINCODE((int) row.getCell(18).getNumericCellValue());

		// System.out.println("Lone Type " +
		// row.getCell(16).getStringCellValue());
		excelFile.setSTATE(row.getCell(19).getStringCellValue().toUpperCase());

		// System.out.println("Lone Type " +
		// row.getCell(17).getStringCellValue());
		Cell c20 = row.getCell(20);
		if (c20 == null || c20.getCellType() == Cell.CELL_TYPE_BLANK) {

		} else {
			excelFile.setMSE_ITPAN(row.getCell(20).getStringCellValue()
					.toUpperCase());

		}

		// System.out.println("Lone Type " +
		// row.getCell(18).getStringCellValue());

		Cell c18 = row.getCell(21);
		if (c18 == null || c18.getCellType() == Cell.CELL_TYPE_BLANK) {

		} else {
			if (row.getCell(21).getCellType() == Cell.CELL_TYPE_STRING) {
				excelFile.setUDYOG_AADHAR_NO(row.getCell(21)
						.getStringCellValue().toUpperCase());

			} else if (row.getCell(21).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				Integer intInstance = new Integer((int) row.getCell(21)
						.getNumericCellValue());
				excelFile.setUDYOG_AADHAR_NO(intInstance.toString());
				// errorList.put("UDYOG AADHAR NO. is Required !!",
				// row.getCell(2)
				// .getStringCellValue());

			}

		}

		// System.out.println("Lone Type " +
		// row.getCell(19).getNumericCellValue());

		/*
		 * Cell c19 = row.getCell(17); if(c19 == null || c19.getCellType() ==
		 * Cell.CELL_TYPE_BLANK){
		 * 
		 * }else{ if (row.getCell(17).getCellType() == Cell.CELL_TYPE_STRING) {
		 * int result = ((java.lang.Integer)
		 * Integer).parseInt(row.getCell(19).getStringCellValue());
		 * excelFile.setMSME_REGISTRATION_NO(result); } else if
		 * (row.getCell(19).getCellType() == Cell.CELL_TYPE_NUMERIC) {
		 * excelFile.setMSME_REGISTRATION_NO((int) row.getCell(19)
		 * .getNumericCellValue()); //
		 * errorList.put("MSME REGISTRATION NO. is Required !!", row //
		 * .getCell(2).getStringCellValue());
		 * 
		 * }
		 * 
		 * }
		 */

		// System.out
		// .println("Lone Type " + row.getCell(20).getStringCellValue());
		excelFile.setINDUSTRY_NATURE(row.getCell(22).getStringCellValue()
				.toUpperCase());

		// System.out
		// .println("Lone Type " + row.getCell(21).getStringCellValue());
		excelFile.setINDUSTRY_SECTOR(row.getCell(23).getStringCellValue()
				.toUpperCase());

		// System.out
		// .println("Lone Type " + row.getCell(22).getNumericCellValue());
		excelFile.setNO_OF_EMPLOYEES((int) row.getCell(24)
				.getNumericCellValue());

		// System.out.println("Lone Type " +
		// row.getCell(23).getNumericCellValue());

		Cell c25 = row.getCell(25);
		if (c25 == null || c25.getCellType() == Cell.CELL_TYPE_BLANK) {
		} else {
			excelFile.setPROJECTED_SALES((int) row.getCell(25)
					.getNumericCellValue());
		}

		// System.out.println("Lone Type " +
		// row.getCell(24).getNumericCellValue());
		Cell c26 = row.getCell(26);
		if (c26 == null || c26.getCellType() == Cell.CELL_TYPE_BLANK) {
		} else {
			excelFile.setPROJECTED_EXPORTS((int) row.getCell(26)
					.getNumericCellValue());
		}

		// System.out.println("Lone Type " +
		// row.getCell(25).getStringCellValue());
		excelFile.setNEW_EXISTING_UNIT(row.getCell(27).getStringCellValue()
				.toUpperCase());

		// System.out.println("Lone Type " +
		// row.getCell(26).getStringCellValue());
		Cell c28 = row.getCell(28);
		if (c28 == null || c28.getCellType() == Cell.CELL_TYPE_BLANK) {
		} else {
			excelFile.setCUSTOMER_HAVING_ANY_PREVIOUS_BANKING_EXPERIENCE(row
					.getCell(28).getStringCellValue().toUpperCase());
		}

		// System.out.println("Lone Type " +
		// row.getCell(27).getStringCellValue());
		/*
		 * excelFile.setFIRST_TIME_CUSTOMER(row.getCell(27).getStringCellValue()
		 * .toUpperCase());
		 */
		// System.out.println("Lone Type " +
		// row.getCell(28).getStringCellValue());
		excelFile.setCHIEF_PROMOTER_FIRST_NAME(row.getCell(29)
				.getStringCellValue().toUpperCase());

		// System.out.println("Lone Type " +
		// row.getCell(29).getStringCellValue());

		Cell c29 = row.getCell(30);
		if (c29 == null || c29.getCellType() == Cell.CELL_TYPE_BLANK) {

		} else {
			if (row.getCell(30).getCellType() == Cell.CELL_TYPE_STRING) {
				excelFile.setCHIEF_PROMOTER_MIDDLE_NAME(row.getCell(30)
						.getStringCellValue().toUpperCase());

			} else if (row.getCell(30).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				Integer intInstance = new Integer((int) row.getCell(30)
						.getNumericCellValue());
				excelFile.setCHIEF_PROMOTER_MIDDLE_NAME(intInstance.toString());
				// errorList.put("UDYOG AADHAR NO. is Required !!",
				// row.getCell(2)
				// .getStringCellValue());

			}

		}
		// System.out.println("Lone Type " +
		// row.getCell(30).getStringCellValue());
		excelFile.setCHIEF_PROMOTER_LAST_NAME(row.getCell(31)
				.getStringCellValue().toUpperCase());

		// System.out.println("itpan " + row.getCell(31).getStringCellValue());
		c32 = row.getCell(32);
		if (c32 == null || c32.getCellType() == Cell.CELL_TYPE_BLANK) {
		} else {
			excelFile.setCHIEF_PROMOTER_IT_PAN(row.getCell(32)
					.getStringCellValue().toUpperCase());
		}

		// System.out.println("Lone Type " +
		// row.getCell(32).getStringCellValue());
		Cell c33 = row.getCell(33);
		if (c33 == null || c33.getCellType() == Cell.CELL_TYPE_BLANK) {
		} else {
			excelFile.setCHIEF_PROMOTER_MAIL_ID(row.getCell(33)
					.getStringCellValue().toUpperCase());
		}

		// System.out.println("Lone Type " +
		// row.getCell(33).getNumericCellValue());
		excelFile.setCHIEF_PROMOTER_CONTACT_NUMBER((int) row.getCell(34)
				.getNumericCellValue());

		// System.out.println("Lone Type " +
		// row.getCell(34).getStringCellValue());

		c35 = row.getCell(35);
		if (c35 == null || c35.getCellType() == Cell.CELL_TYPE_BLANK) {
		} else {
			excelFile.setMINORITY_COMMUNITY(row.getCell(35)
					.getStringCellValue().toUpperCase());
		}

		// System.out.println("Lone Type " +
		// row.getCell(35).getStringCellValue());
		c36 = row.getCell(36);
		if (c36 == null || c36.getCellType() == Cell.CELL_TYPE_BLANK) {
		} else {
			excelFile.setHANDICAPPED(row.getCell(36).getStringCellValue()
					.toUpperCase());
		}

		// System.out.println("Lone Type " +
		// row.getCell(36).getStringCellValue());
		c37 = row.getCell(37);
		if (c37 == null || c37.getCellType() == Cell.CELL_TYPE_BLANK) {
		} else {
			excelFile.setWOMEN(row.getCell(37).getStringCellValue()
					.toUpperCase());
		}

		// System.out.println("Lone Type " +
		// row.getCell(37).getStringCellValue());
		System.out.println(count);
		c38 = row.getCell(38);
		if (c38 == null || c38.getCellType() == Cell.CELL_TYPE_BLANK) {
		} else {
			excelFile.setCATEGORY(row.getCell(38).getStringCellValue()
					.toUpperCase());
		}

		c39 = row.getCell(39);
		if (c39 == null || c39.getCellType() == Cell.CELL_TYPE_BLANK) {
		} else {
			excelFile.setAADHAR_NUMBER((long) row.getCell(39)
					.getNumericCellValue());
		}

		// Added four new fields
		count++;
		return excelFile;

	}

	public boolean duplicateDataChecker(String firstName, String middleName,
			String lastName, String address, int ssiRegNo,
			String promoterITPAN, MLIValidatorService mliValidatorService) {

		List<PromoterDetails> mliDetails = mliValidatorService.getDetails();
		List<SSIDetail> ssiDetail = mliValidatorService.getSSIDetails();
		if (mliDetails != null) {

			if (ssiDetail != null) {

				for (PromoterDetails employee : mliDetails) {

					System.out.println("PMR_CHIEF_FIRST_NAME()"
							+ employee.getPMR_CHIEF_FIRST_NAME());
					System.out.println("PMR_CHIEF_FIRST_NAME()"
							+ employee.getPMR_CHIEF_IT_PAN());
					System.out.println("PMR_CHIEF_FIRST_NAME()"
							+ employee.getPMR_CHIEF_LAST_NAME());
					System.out.println("PMR_CHIEF_FIRST_NAME()"
							+ employee.getPMR_CHIEF_MIDDLE_NAME());

				}

			}

		} else if (ssiDetail != null) {

			if (mliDetails != null) {

			}
		}

		return true;
	}

	public Map<String, String> portfolioDataValidate(XSSFRow row,
			MLIMakerInterfaceUploadedBean excelFile,
			MLIValidatorService mliValidatorService,
			List<PortfolioValidate> listLoneno, List<DistrictName> distObjList,
			List<StateName> stateObjList) throws Exception {

		Map<String, String> errorList = new HashMap<String, String>();
		rowCount = 0;

		errorList.clear();
		c08 = row.getCell(8);
		c02 = row.getCell(3);

		c = row.getCell(0);

		if (c == null || c.getCellType() == Cell.CELL_TYPE_BLANK) {
			if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
				errorList.put("UNIT NAME is Required !!", "");
			} else {
				if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {
					errorList.put("UNIT NAME is Required !!", Long
							.toString((long) row.getCell(3)
									.getNumericCellValue()));
				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					errorList.put("UNIT NAME is Required !!", row.getCell(3)
							.getStringCellValue());
				}
			}

		} else {

			if (row.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid UNIT NAME !!", "");
				} else {
					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid UNIT NAME !!", Long
								.toString((long) row.getCell(3)
										.getNumericCellValue()));

					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid UNIT NAME !!", row.getCell(3)
								.getStringCellValue());
					}
				}

			} else if (row.getCell(0).getCellType() == Cell.CELL_TYPE_STRING) {/*
																				 * 
																				 * if
																				 * (
																				 * dataValidation
																				 * .
																				 * loanTypeValidator
																				 * (
																				 * row
																				 * .
																				 * getCell
																				 * (
																				 * 0
																				 * )
																				 * .
																				 * getStringCellValue
																				 * (
																				 * )
																				 * )
																				 * .
																				 * equals
																				 * (
																				 * "success"
																				 * )
																				 * )
																				 * {
																				 * 
																				 * }
																				 * else
																				 * {
																				 * if
																				 * (
																				 * c02
																				 * ==
																				 * null
																				 * ||
																				 * c02
																				 * .
																				 * getCellType
																				 * (
																				 * )
																				 * ==
																				 * Cell
																				 * .
																				 * CELL_TYPE_BLANK
																				 * )
																				 * {
																				 * errorList
																				 * .
																				 * put
																				 * (
																				 * dataValidation
																				 * .
																				 * loanTypeValidator
																				 * (
																				 * row
																				 * .
																				 * getCell
																				 * (
																				 * 0
																				 * )
																				 * .
																				 * getStringCellValue
																				 * (
																				 * )
																				 * )
																				 * ,
																				 * ""
																				 * )
																				 * ;
																				 * }
																				 * else
																				 * {
																				 * if
																				 * (
																				 * row
																				 * .
																				 * getCell
																				 * (
																				 * 2
																				 * )
																				 * .
																				 * getCellType
																				 * (
																				 * )
																				 * ==
																				 * Cell
																				 * .
																				 * CELL_TYPE_NUMERIC
																				 * )
																				 * {
																				 * 
																				 * errorList
																				 * .
																				 * put
																				 * (
																				 * dataValidation
																				 * .
																				 * loanTypeValidator
																				 * (
																				 * row
																				 * .
																				 * getCell
																				 * (
																				 * 0
																				 * )
																				 * .
																				 * getStringCellValue
																				 * (
																				 * )
																				 * )
																				 * ,
																				 * Long
																				 * .
																				 * toString
																				 * (
																				 * (
																				 * long
																				 * )
																				 * row
																				 * .
																				 * getCell
																				 * (
																				 * 2
																				 * )
																				 * .
																				 * getNumericCellValue
																				 * (
																				 * )
																				 * )
																				 * )
																				 * ;
																				 * 
																				 * }
																				 * else
																				 * if
																				 * (
																				 * row
																				 * .
																				 * getCell
																				 * (
																				 * 2
																				 * )
																				 * .
																				 * getCellType
																				 * (
																				 * )
																				 * ==
																				 * Cell
																				 * .
																				 * CELL_TYPE_STRING
																				 * )
																				 * {
																				 * errorList
																				 * .
																				 * put
																				 * (
																				 * dataValidation
																				 * .
																				 * loanTypeValidator
																				 * (
																				 * row
																				 * .
																				 * getCell
																				 * (
																				 * 0
																				 * )
																				 * .
																				 * getStringCellValue
																				 * (
																				 * )
																				 * )
																				 * ,
																				 * row
																				 * .
																				 * getCell
																				 * (
																				 * 2
																				 * )
																				 * .
																				 * getStringCellValue
																				 * (
																				 * )
																				 * )
																				 * ;
																				 * }
																				 * 
																				 * }
																				 * }
																				 */

			}

		}

		c3 = row.getCell(1);
		if (c3 == null || c3.getCellType() == Cell.CELL_TYPE_BLANK) {

			if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
				errorList.put("CONSTITUTION detail is Required !!", "");
			} else {
				if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

					errorList.put("CONSTITUTION detail is Required !!", Long
							.toString((long) row.getCell(3)
									.getNumericCellValue()));
				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					errorList.put("CONSTITUTION detail is Required !!", row
							.getCell(3).getStringCellValue());
				}

			}

		} else {

			if (row.getCell(1).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid CONSTITUTION detail!!", "");
				} else {
					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid CONSTITUTION detail !!", Long
								.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid CONSTITUTION detail !!", row
								.getCell(3).getStringCellValue());
					}

				}

			} else if (row.getCell(1).getCellType() == Cell.CELL_TYPE_STRING) {

				if (dataValidation.constitutionValidator(
						row.getCell(1).getStringCellValue()).equals("success")) {

					System.out.println("CONSTITUTION Is valid..");
				} else {
					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {

						errorList.put(dataValidation.constitutionValidator(row
								.getCell(1).getStringCellValue()), "");
					} else {

						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put(dataValidation
									.constitutionValidator(row.getCell(1)
											.getStringCellValue()), Long
									.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put(dataValidation
									.constitutionValidator(row.getCell(1)
											.getStringCellValue()), row
									.getCell(3).getStringCellValue());
						}
					}

				}
			}
		}

		c2 = row.getCell(2);

		if (c2 == null || c2.getCellType() == Cell.CELL_TYPE_BLANK) {

			if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
				errorList.put("Loan Type is Required !!", "");
			} else {

				if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

					errorList.put("Loan Type is Required !!", Long
							.toString((long) row.getCell(3)
									.getNumericCellValue()));
				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					errorList.put("Loan Type is Required !!", row.getCell(3)
							.getStringCellValue());
				}
			}

		} else {

			if (row.getCell(2).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid Loan Type !!", "");
				} else {
					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid Loan Type !!", Long
								.toString((long) row.getCell(3)
										.getNumericCellValue()));

					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid Loan Type !!", row.getCell(3)
								.getStringCellValue());
					}
				}

			} else if (row.getCell(2).getCellType() == Cell.CELL_TYPE_STRING) {

				if (dataValidation.loanTypeValidator(
						row.getCell(2).getStringCellValue()).equals("success")) {

				} else {
					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put(dataValidation.loanTypeValidator(row
								.getCell(2).getStringCellValue()), "");
					} else {
						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put(dataValidation.loanTypeValidator(row
									.getCell(2).getStringCellValue()), Long
									.toString((long) row.getCell(3)
											.getNumericCellValue()));

						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put(dataValidation.loanTypeValidator(row
									.getCell(2).getStringCellValue()), row
									.getCell(3).getStringCellValue());
						}

					}
				}
			}

		}

		c022 = row.getCell(3);
		if (c022 == null || c022.getCellType() == Cell.CELL_TYPE_BLANK) {
			errorList.put("LOAN ACCOUNT NO. is Required !!", "");

		} else {
			if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (dataValidation
						.duplicateLoanAccCheckerNum(
								(long) row.getCell(3).getNumericCellValue(),
								listLoneno).equals("success")) {

				} else {

					errorList.put(dataValidation.duplicateLoanAccCheckerNum(
							(long) row.getCell(3).getNumericCellValue(),
							listLoneno), Long.toString((long) row.getCell(3)
							.getNumericCellValue()));

				}

			} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {

				if (dataValidation.duplicateLoanAccChecker(
						row.getCell(3).getStringCellValue(), listLoneno)
						.equals("success")) {

				} else {

					errorList.put(dataValidation.duplicateLoanAccChecker(row
							.getCell(3).getStringCellValue(), listLoneno), row
							.getCell(3).getStringCellValue());

				}
			}

		}

		/*
		 * else if (row.getCell(2).getCellType() == Cell.CELL_TYPE_BLANK) {
		 * 
		 * errorList.put("LOAN ACCOUNT NO. is Required !!", row.getCell(2)
		 * .getStringCellValue());
		 * 
		 * }
		 */

		// else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_BLANK) {}
		c5 = row.getCell(4);
		if (c5 == null || c5.getCellType() == Cell.CELL_TYPE_BLANK) {
			if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
				errorList.put("SANCTION DATE is Required !!", "");
			} else {

				if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

					errorList.put("SANCTION DATE is Required !!", Long
							.toString((long) row.getCell(3)
									.getNumericCellValue()));
				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					errorList.put("SANCTION DATE is Required !!", row
							.getCell(3).getStringCellValue());
				}
			}

		} else {

			if (row.getCell(4).getCellType() == Cell.CELL_TYPE_NUMERIC) {

				if (dataValidation.sanctionDateValidator(
						row.getCell(4).getDateCellValue()).equals("success")) {

				} else {

					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put(dataValidation.sanctionDateValidator(row
								.getCell(4).getDateCellValue()), "");
					} else {
						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put(dataValidation
									.sanctionDateValidator(row.getCell(4)
											.getDateCellValue()), Long
									.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put(dataValidation
									.sanctionDateValidator(row.getCell(4)
											.getDateCellValue()), row
									.getCell(3).getStringCellValue());
						}

					}

				}

			} else if (row.getCell(4).getCellType() == Cell.CELL_TYPE_STRING) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid SANCTION DATE !!", "");
				} else {
					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid SANCTION DATE !!", Long
								.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid SANCTION DATE !!", row
								.getCell(3).getStringCellValue());
					}

				}

			}
		}
		c6 = row.getCell(5);
		if (c6 == null || c6.getCellType() == Cell.CELL_TYPE_BLANK) {
			if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
				errorList.put("SANCTION AMOUNT is Required !!", "");
			} else {

				if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

					errorList.put("SANCTION AMOUNT is Required !!", Long
							.toString((long) row.getCell(3)
									.getNumericCellValue()));
				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					errorList.put("SANCTION AMOUNT is Required !!", row
							.getCell(3).getStringCellValue());
				}
			}

		} else {

			if (row.getCell(5).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (dataValidation.retailTradeAmountValidator(
						(int) row.getCell(5).getNumericCellValue()).equals(
						"success")) {
				} else {
					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put(dataValidation
								.retailTradeAmountValidator((int) row
										.getCell(5).getNumericCellValue()), "");
					} else {
						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put(dataValidation
									.retailTradeAmountValidator((int) row
											.getCell(5).getNumericCellValue()),
									Long.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put(dataValidation
									.retailTradeAmountValidator((int) row
											.getCell(5).getNumericCellValue()),
									row.getCell(3).getStringCellValue());
						}

					}

				}

			} else if (row.getCell(5).getCellType() == Cell.CELL_TYPE_STRING) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid SANCTION AMOUNT !!", "");
				} else {

					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid SANCTION AMOUNT !!", Long
								.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid SANCTION AMOUNT !!", row
								.getCell(3).getStringCellValue());
					}
				}

			}
		}

		c8 = row.getCell(6);
		if (c8 == null || c8.getCellType() == Cell.CELL_TYPE_BLANK) {

		} else {

			if (row.getCell(6).getCellType() == Cell.CELL_TYPE_NUMERIC) {

				if (row.getCell(4).getCellType() == Cell.CELL_TYPE_NUMERIC) {

					if (dataValidation.sanctionDateValidator(
							row.getCell(4).getDateCellValue())
							.equals("success")) {

						if (dataValidation.firstDisbursementDateValidator(
								row.getCell(6).getDateCellValue(),
								row.getCell(4).getDateCellValue()).equals(
								"success")) {
						} else {
							if (c02 == null
									|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
								errorList.put(dataValidation
										.firstDisbursementDateValidator(row
												.getCell(6).getDateCellValue(),
												row.getCell(4)
														.getDateCellValue()),
										"");
							} else {
								if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

									errorList.put(dataValidation
											.firstDisbursementDateValidator(row
													.getCell(6)
													.getDateCellValue(), row
													.getCell(4)
													.getDateCellValue()), Long
											.toString((long) row.getCell(3)
													.getNumericCellValue()));
								} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
									errorList.put(dataValidation
											.firstDisbursementDateValidator(row
													.getCell(6)
													.getDateCellValue(), row
													.getCell(4)
													.getDateCellValue()), row
											.getCell(3).getStringCellValue());
								}

							}

						}

					} else {
						if (c02 == null
								|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
							errorList.put(dataValidation
									.sanctionDateValidator(row.getCell(4)
											.getDateCellValue()), "");
						} else {
							if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

								errorList.put(dataValidation
										.sanctionDateValidator(row.getCell(4)
												.getDateCellValue()), Long
										.toString((long) row.getCell(3)
												.getNumericCellValue()));
							} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
								errorList.put(dataValidation
										.sanctionDateValidator(row.getCell(4)
												.getDateCellValue()), row
										.getCell(3).getStringCellValue());
							}

						}

					}

				} else if (row.getCell(4).getCellType() == Cell.CELL_TYPE_STRING) {
					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put("Ivalid SANCTION DATE !!", "");
					} else {

						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put("Ivalid SANCTION DATE !!", Long
									.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put("Ivalid SANCTION DATE !!", row
									.getCell(3).getStringCellValue());
						}
					}

				} else if (row.getCell(4).getCellType() == Cell.CELL_TYPE_BLANK) {
					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put("SANCTION DATE is Required !!", "");
					} else {

						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put("SANCTION DATE is Required !!", Long
									.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put("SANCTION DATE is Required !!", row
									.getCell(3).getStringCellValue());
						}
					}

				}

			} else if (row.getCell(6).getCellType() == Cell.CELL_TYPE_STRING) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid FIRST DISBURSEMENT DATE !!", "");
				} else {
					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid FIRST DISBURSEMENT DATE !!",
								Long.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid FIRST DISBURSEMENT DATE !!", row
								.getCell(3).getStringCellValue());
					}

				}

			}

		}

		/*
		 * else if (row.getCell(8).getCellType() == Cell.CELL_TYPE_BLANK) {
		 * 
		 * 
		 * errorList.put("FIRST DISBURSEMENT DATE is Required !!", row
		 * .getCell(2).getStringCellValue());
		 * 
		 * 
		 * } else if (row.getCell(8).getCellType() == Cell.CELL_TYPE_ERROR) {
		 * 
		 * System.out.println("error..........."); }
		 */
		c9 = row.getCell(7);
		if (c9 == null || c9.getCellType() == Cell.CELL_TYPE_BLANK) {
			if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
				errorList.put("INTEREST RATE (% p.a) is Required !!", "");
			} else {
				if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

					errorList.put("INTEREST RATE (% p.a) is Required !!", Long
							.toString((long) row.getCell(3)
									.getNumericCellValue()));
				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					errorList.put("INTEREST RATE (% p.a) is Required !!", row
							.getCell(3).getStringCellValue());
				}

			}

		} else {

			if (row.getCell(7).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				System.out.println((float) row.getCell(7).getNumericCellValue()
						+ "*************************************");
				if (dataValidation.interestRateValidator(
						(float) row.getCell(7).getNumericCellValue()).equals(
						"success")) {

				} else {
					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put(dataValidation
								.interestRateValidator((int) row.getCell(7)
										.getNumericCellValue()), "");
					} else {
						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put(dataValidation
									.interestRateValidator((int) row.getCell(7)
											.getNumericCellValue()), Long
									.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put(dataValidation
									.interestRateValidator((int) row.getCell(7)
											.getNumericCellValue()), row
									.getCell(3).getStringCellValue());
						}

					}

				}
			} else if (row.getCell(7).getCellType() == Cell.CELL_TYPE_STRING) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid INTEREST RATE (% p.a) !!", "");
				} else {

					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid INTEREST RATE (% p.a) !!", Long
								.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid INTEREST RATE (% p.a) !!", row
								.getCell(3).getStringCellValue());
					}
				}

			}
		}
		// else if (row.getCell(9).getCellType() == Cell.CELL_TYPE_BLANK) {}

		c06 = row.getCell(8);
		if (c06 == null || c06.getCellType() == Cell.CELL_TYPE_BLANK) {
			if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
				errorList.put("RETAIL TRADE is Required !!", "");
			} else {

				if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

					errorList.put("RETAIL TRADE is Required !!", Long
							.toString((long) row.getCell(3)
									.getNumericCellValue()));
				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					errorList.put("RETAIL TRADE is Required !!", row.getCell(3)
							.getStringCellValue());
				}
			}

		} else {

			if (row.getCell(8).getCellType() == Cell.CELL_TYPE_NUMERIC) {

				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid RETAIL TRADE !!", "");
				} else {

					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid RETAIL TRADE !!", Long
								.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid RETAIL TRADE !!", row.getCell(3)
								.getStringCellValue());
					}
				}

			} else if (row.getCell(8).getCellType() == Cell.CELL_TYPE_STRING) {

				if (!row.getCell(8).getStringCellValue().equalsIgnoreCase("Y")) {

				} else if (!row.getCell(8).getStringCellValue()
						.equalsIgnoreCase("N")) {

				} else {
					errorList.put("Invalid RETAIL TRADE !!", row.getCell(3)
							.getStringCellValue());
				}
			}
		}

		c38 = row.getCell(9);
		if (c38 == null || c38.getCellType() == Cell.CELL_TYPE_BLANK) {

			if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
				errorList.put("Partial Security Flag is Mandatory!!", "");
			} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

				errorList.put("Partial Security Flag is Mandatory!!", Long
						.toString((long) row.getCell(3).getNumericCellValue()));
			} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
				errorList.put("Partial Security Flag is Mandatory!!", row
						.getCell(3).getStringCellValue());
			}

		} else {
			if (row.getCell(9).getCellType() == Cell.CELL_TYPE_NUMERIC) {

				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid Partial Security Flag !!", "");
				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

					errorList.put("Invalid Partial Security Flag !!", Long
							.toString((long) row.getCell(3)
									.getNumericCellValue()));
				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					errorList.put("Invalid Partial Security Flag !!", row
							.getCell(3).getStringCellValue());
				}

			} else if (row.getCell(9).getCellType() == Cell.CELL_TYPE_STRING) {

				if (dataValidation.partialSecurityFlag(
						row.getCell(9).getStringCellValue()).equals("success")) {
				} else {
					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put(
								dataValidation.partialSecurityFlag(row.getCell(
										9).getStringCellValue()), "");
					} else {

						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put(dataValidation
									.partialSecurityFlag(row.getCell(9)
											.getStringCellValue()), Long
									.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put(dataValidation
									.partialSecurityFlag(row.getCell(9)
											.getStringCellValue()), row
									.getCell(3).getStringCellValue());
						}
					}

				}

			}

		}
		/* For Guarantee Amount */
		c39 = row.getCell(10);

		if (c39 == null || c39.getCellType() == Cell.CELL_TYPE_BLANK) {

			if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
				errorList.put("Guarantee Amount is Mandatory !!", "");
			} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				errorList.put("Guarantee Amount is Mandatory !!", Long
						.toString((long) row.getCell(3).getNumericCellValue()));
			} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
				errorList.put("Guarantee Amount is Mandatory !!", row
						.getCell(3).getStringCellValue());
			}

		} else // if (row.getCell(38).getStringCellValue().equals("Y")) {
		if (row.getCell(10).getCellType() == Cell.CELL_TYPE_NUMERIC) {

			if (row.getCell(8).getCellType() == Cell.CELL_TYPE_NUMERIC) {

			} else if (row.getCell(8).getCellType() == Cell.CELL_TYPE_STRING) {
				if (row.getCell(8).getStringCellValue().equalsIgnoreCase("Y")) {
					if (dataValidation.guarenteeAmountValidator(
							(int) row.getCell(10).getNumericCellValue())
							.equals("success")) {
					} else {
						if (c02 == null
								|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
							errorList
									.put(dataValidation
											.guarenteeAmountValidator((int) row
													.getCell(10)
													.getNumericCellValue()), "");
						} else {
							if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

								errorList.put(dataValidation
										.guarenteeAmountValidator((int) row
												.getCell(10)
												.getNumericCellValue()), Long
										.toString((long) row.getCell(3)
												.getNumericCellValue()));
							} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
								errorList.put(dataValidation
										.guarenteeAmountValidator((int) row
												.getCell(10)
												.getNumericCellValue()), row
										.getCell(3).getStringCellValue());
							}

						}

					}

				} else if (row.getCell(8).getStringCellValue()
						.equalsIgnoreCase("N")) {

					if (dataValidation.guarenteeAmountValidatorOnY(
							(int) row.getCell(10).getNumericCellValue())
							.equals("success")) {
					} else {
						if (c02 == null
								|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
							errorList
									.put(dataValidation
											.guarenteeAmountValidatorOnY((int) row
													.getCell(10)
													.getNumericCellValue()), "");
						} else {
							if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

								errorList.put(dataValidation
										.guarenteeAmountValidatorOnY((int) row
												.getCell(10)
												.getNumericCellValue()), Long
										.toString((long) row.getCell(3)
												.getNumericCellValue()));
							} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
								errorList.put(dataValidation
										.guarenteeAmountValidatorOnY((int) row
												.getCell(10)
												.getNumericCellValue()), row
										.getCell(3).getStringCellValue());
							}

						}

					}

					if (row.getCell(10).getCellType() != Cell.CELL_TYPE_BLANK) {/*
																				 * if
																				 * (
																				 * c02
																				 * ==
																				 * null
																				 * ||
																				 * c02
																				 * .
																				 * getCellType
																				 * (
																				 * )
																				 * ==
																				 * Cell
																				 * .
																				 * CELL_TYPE_BLANK
																				 * )
																				 * {
																				 * errorList
																				 * .
																				 * put
																				 * (
																				 * "Guarantee Amount Should be blank !!"
																				 * ,
																				 * ""
																				 * )
																				 * ;
																				 * }
																				 * else
																				 * {
																				 * if
																				 * (
																				 * row
																				 * .
																				 * getCell
																				 * (
																				 * 3
																				 * )
																				 * .
																				 * getCellType
																				 * (
																				 * )
																				 * ==
																				 * Cell
																				 * .
																				 * CELL_TYPE_NUMERIC
																				 * )
																				 * {
																				 * 
																				 * errorList
																				 * .
																				 * put
																				 * (
																				 * "Guarantee Amount Should be blank !!!!"
																				 * ,
																				 * Long
																				 * .
																				 * toString
																				 * (
																				 * (
																				 * long
																				 * )
																				 * row
																				 * .
																				 * getCell
																				 * (
																				 * 3
																				 * )
																				 * .
																				 * getNumericCellValue
																				 * (
																				 * )
																				 * )
																				 * )
																				 * ;
																				 * }
																				 * else
																				 * if
																				 * (
																				 * row
																				 * .
																				 * getCell
																				 * (
																				 * 3
																				 * )
																				 * .
																				 * getCellType
																				 * (
																				 * )
																				 * ==
																				 * Cell
																				 * .
																				 * CELL_TYPE_STRING
																				 * )
																				 * {
																				 * errorList
																				 * .
																				 * put
																				 * (
																				 * "Guarantee Amount Should be blank !!"
																				 * ,
																				 * row
																				 * .
																				 * getCell
																				 * (
																				 * 3
																				 * )
																				 * .
																				 * getStringCellValue
																				 * (
																				 * )
																				 * )
																				 * ;
																				 * }
																				 * 
																				 * }
																				 */
					}
				}
			}

		} else if (row.getCell(10).getCellType() == Cell.CELL_TYPE_STRING) {

			if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
				errorList.put("Guarantee Amount is Mandatory !!", "");
			} else {

				if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

					errorList.put("Guarantee Amount is Mandatory !!", Long
							.toString((long) row.getCell(3)
									.getNumericCellValue()));
				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					errorList.put("Guarantee Amount is Mandatory !!", row
							.getCell(3).getStringCellValue());
				}
			}

		}

		/*
		 * }else{ if(row.getCell(38).getStringCellValue().equals("N")){
		 * if(row.getCell(39).getCellType() == Cell.CELL_TYPE_NUMERIC){
		 * if(row.getCell(39).getCellType()!=Cell.CELL_TYPE_BLANK){
		 * errorList.put
		 * ("Guarantee Amount is not valid for Partial Security Flag 'N' !!",
		 * row.getCell(2) .getStringCellValue()); } }
		 * 
		 * } }
		 */

		/* For Colletral Security Amount */
		c40 = row.getCell(11);
		System.out.println(c40);
		if (c40 == null || c40.getCellType() == Cell.CELL_TYPE_BLANK) {

			c140 = row.getCell(9);
			if (c140 == null || c140.getCellType() == Cell.CELL_TYPE_BLANK) {

			} else if (row.getCell(9).getCellType() == Cell.CELL_TYPE_NUMERIC) {

			} else if (row.getCell(9).getCellType() == Cell.CELL_TYPE_STRING) {
				if (row.getCell(9).getStringCellValue().equals("Y")) {

					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_BLANK) {

						errorList
								.put("Colletral Security Amount is Mandatory for Partial Security Flag 'Y'!!",
										"");
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {
						errorList
								.put("Colletral Security Amount is Mandatory for Partial Security Flag 'Y'!!",
										Long.toString((long) row.getCell(3)
												.getNumericCellValue()));

					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid Colletral Security Amount !!",
								row.getCell(3).getStringCellValue());

					}

				} else if (row.getCell(9).getStringCellValue().equals("N")) {
					if (row.getCell(11).getCellType() == Cell.CELL_TYPE_BLANK) {
						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_BLANK) {

							errorList
									.put("Colletral Security Amount should be '0' or above '0' if Partial Security Flag is 'N' !!",
											"");
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {
							errorList
									.put("Colletral Security Amount should be '0' or above '0' if Partial Security Flag is 'N' !!",
											Long.toString((long) row.getCell(3)
													.getNumericCellValue()));

						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList
									.put("Colletral Security Amount should be '0' or above '0' if Partial Security Flag is 'N' !!",
											row.getCell(3).getStringCellValue());

						}

					} else if (row.getCell(11).getCellType() == Cell.CELL_TYPE_STRING) {
						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_BLANK) {

							errorList
									.put("Colletral Security Amount should be '0' or above '0' if Partial Security Flag is 'N' !!",
											"");
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {
							errorList
									.put("Colletral Security Amount should be '0' or above '0' if Partial Security Flag is 'N' !!",
											Long.toString((long) row.getCell(3)
													.getNumericCellValue()));

						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList
									.put("Colletral Security Amount should be '0' or above '0' if Partial Security Flag is 'N' !!",
											row.getCell(3).getStringCellValue());

						}

					}

				}
			}

		} else if (row.getCell(11).getCellType() == Cell.CELL_TYPE_NUMERIC) {

			if (row.getCell(9).getCellType() == Cell.CELL_TYPE_BLANK) {

			} else if (row.getCell(9).getCellType() == Cell.CELL_TYPE_NUMERIC) {

			} else if (row.getCell(9).getCellType() == Cell.CELL_TYPE_STRING) {
				if (row.getCell(9).getStringCellValue().equals("Y")) {

					if (row.getCell(11).getNumericCellValue() == 0) {

						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_BLANK) {

							errorList
									.put("Colletral Security Amount is Mandatory for Partial Security Flag 'Y'!!",
											"");
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {
							errorList
									.put("Colletral Security Amount is Mandatory for Partial Security Flag 'Y'!!",
											Long.toString((long) row.getCell(3)
													.getNumericCellValue()));

						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList
									.put("Colletral Security Amount is Mandatory for Partial Security Flag 'Y'!!",
											row.getCell(3).getStringCellValue());

						}

					} else if (row.getCell(11).getNumericCellValue() != 0) {

						// valid

					}

				} else {
					if (row.getCell(9).getStringCellValue().equals("N")) {/*
																		 * if(row
																		 * .
																		 * getCell
																		 * (11).
																		 * getCellType
																		 * (
																		 * )!=Cell
																		 * .
																		 * CELL_TYPE_BLANK
																		 * ){
																		 * 
																		 * 
																		 * if(row
																		 * .
																		 * getCell
																		 * (3).
																		 * getCellType
																		 * () ==
																		 * Cell.
																		 * CELL_TYPE_BLANK
																		 * ){
																		 * 
																		 * errorList
																		 * .put(
																		 * "Colletral Security Amount should be '0' or above '0' if Partial Security Flag is 'N' !!"
																		 * ,
																		 * "");
																		 * }else
																		 * if
																		 * (row
																		 * .
																		 * getCell
																		 * (3).
																		 * getCellType
																		 * () ==
																		 * Cell.
																		 * CELL_TYPE_NUMERIC
																		 * ) {
																		 * errorList
																		 * .put(
																		 * "Colletral Security Amount should be '0' or above '0' if Partial Security Flag is 'N' !!"
																		 * ,
																		 * Long
																		 * .
																		 * toString
																		 * (
																		 * (long
																		 * )row.
																		 * getCell
																		 * (3).
																		 * getNumericCellValue
																		 * ()));
																		 * 
																		 * 
																		 * 
																		 * }
																		 * else
																		 * if
																		 * (row
																		 * .
																		 * getCell
																		 * (3).
																		 * getCellType
																		 * () ==
																		 * Cell.
																		 * CELL_TYPE_STRING
																		 * ) {
																		 * errorList
																		 * .put(
																		 * "Colletral Security Amount should be '0' or above '0' if Partial Security Flag is 'N' !!"
																		 * , row
																		 * .
																		 * getCell
																		 * (3).
																		 * getStringCellValue
																		 * ());
																		 * 
																		 * }
																		 * 
																		 * 
																		 * 
																		 * 
																		 * 
																		 * }else
																		 * if
																		 * (row
																		 * .
																		 * getCell
																		 * (11).
																		 * getCellType
																		 * () ==
																		 * Cell.
																		 * CELL_TYPE_STRING
																		 * ) {
																		 * errorList
																		 * .put(
																		 * "Invalid Colletral Security Amount, it should be '0' or above '0' for Partial Security Flag 'N'!!"
																		 * , row
																		 * .
																		 * getCell
																		 * (3).
																		 * getStringCellValue
																		 * ());
																		 * 
																		 * }
																		 */
					}
				}

			}

		} else if (row.getCell(11).getCellType() == Cell.CELL_TYPE_STRING) {

			if (row.getCell(3).getCellType() == Cell.CELL_TYPE_BLANK) {

				errorList
						.put("Invalid Colletral Security Amount, it should be '0' or above '0' ",
								"");
			} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				errorList
						.put("Invalid Colletral Security Amount, it should be '0' or above '0' ",
								Long.toString((long) row.getCell(3)
										.getNumericCellValue()));

			} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
				errorList
						.put("Invalid Colletral Security Amount, it should be '0' or above '0' ",
								row.getCell(3).getStringCellValue());

			}

		}

		/* For Colletral Security Amount */
		c41 = row.getCell(12);

		if (c41 == null || c41.getCellType() == Cell.CELL_TYPE_BLANK) {
			if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
				errorList.put("Outstanding Amount is mandatory !!", "");
			} else {
				if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

					errorList.put("Outstanding Amount is mandatory !!", Long
							.toString((long) row.getCell(3)
									.getNumericCellValue()));
				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					errorList.put("Outstanding Amount is mandatory !!", row
							.getCell(3).getStringCellValue());
				}

			}

			/*
			 * errorList.put("Outstanding Amount is mandatory !!", row
			 * .getCell(3).getStringCellValue());
			 */
		} else {
			// if (row.getCell(38).getStringCellValue().equals("Y")) {
			if (row.getCell(12).getCellType() == Cell.CELL_TYPE_NUMERIC) {

				/*
				 * if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK)
				 * { errorList.put("Invalid Outstanding Amount!!", ""); } else {
				 * 
				 * if (row.getCell(2).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				 * 
				 * errorList.put("Invalid Outstanding Amount!!", Long
				 * .toString((long) row.getCell(2) .getNumericCellValue())); }
				 * else if (row.getCell(2).getCellType() ==
				 * Cell.CELL_TYPE_STRING) {
				 * errorList.put("Invalid Outstanding Amount!!", row
				 * .getCell(2).getStringCellValue()); } }
				 */
			} else if (row.getCell(12).getCellType() == Cell.CELL_TYPE_STRING) {
				errorList.put("Invalid Outstanding Amount !!", row.getCell(3)
						.getStringCellValue());

			}

			/*
			 * } else { if (row.getCell(38).getStringCellValue().equals("N")) {
			 * if (row.getCell(41).getCellType() == Cell.CELL_TYPE_NUMERIC) { if
			 * (row.getCell(40).getCellType()!=Cell.CELL_TYPE_BLANK) { errorList
			 * .put(
			 * "Outstanding Amount is not valid for Partial Security Flag 'N' !!"
			 * , row.getCell(2).getStringCellValue()); }
			 * 
			 * }
			 * 
			 * } }
			 */

		}

		c10 = row.getCell(13);
		if (c10 == null || c10.getCellType() == Cell.CELL_TYPE_BLANK) {
			if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
				errorList.put("TENURE IN MONTHS is Required !!", "");
			} else {
				if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

					errorList.put("TENURE IN MONTHS is Required !!", Long
							.toString((long) row.getCell(3)
									.getNumericCellValue()));
				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					errorList.put("TENURE IN MONTHS is Required !!", row
							.getCell(3).getStringCellValue());
				}

			}

		} else {
			if (row.getCell(13).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (dataValidation.tenorInMonthValidator(
						(int) row.getCell(13).getNumericCellValue()).equals(
						"success")) {
				} else {
					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put(dataValidation
								.tenorInMonthValidator((int) row.getCell(13)
										.getNumericCellValue()), "");
					} else {
						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put(
									dataValidation
											.tenorInMonthValidator((int) row
													.getCell(13)
													.getNumericCellValue()),
									Long.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put(
									dataValidation
											.tenorInMonthValidator((int) row
													.getCell(13)
													.getNumericCellValue()),
									row.getCell(3).getStringCellValue());
						}

					}

				}
				// System.out.println("valid TENOR IN MONTHS..");
			} else if (row.getCell(13).getCellType() == Cell.CELL_TYPE_STRING) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid TENURE IN MONTHS !!", "");
				} else {

					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid TENURE IN MONTHS !!", Long
								.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid TENURE IN MONTHS !!", row
								.getCell(3).getStringCellValue());
					}
				}

			}

		}

		// else if (row.getCell(10).getCellType() == Cell.CELL_TYPE_BLANK) {}

		c11 = row.getCell(14);
		if (c11 == null || c11.getCellType() == Cell.CELL_TYPE_BLANK) {
			if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
				errorList
						.put("WHETHER THE ASSISTED UNIT IS MICRO/SMALL ? is Required !! ",
								"");
			} else {
				if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

					errorList
							.put("WHETHER THE ASSISTED UNIT IS MICRO/SMALL ? is Required !! ",
									Long.toString((long) row.getCell(3)
											.getNumericCellValue()));
				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					errorList
							.put("WHETHER THE ASSISTED UNIT IS MICRO/SMALL ? is Required !! ",
									row.getCell(3).getStringCellValue());
				}

			}

		} else {

			if (row.getCell(14).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList
							.put("Invalid WHETHER THE ASSISTED UNIT IS MICRO/SMALL ? !!",
									"");
				} else {
					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList
								.put("Invalid WHETHER THE ASSISTED UNIT IS MICRO/SMALL ? !!",
										Long.toString((long) row.getCell(3)
												.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList
								.put("Invalid WHETHER THE ASSISTED UNIT IS MICRO/SMALL ? !!",
										row.getCell(3).getStringCellValue());
					}

				}

			} else if (row.getCell(14).getCellType() == Cell.CELL_TYPE_STRING) {

				if (dataValidation.microSmallValidator(
						row.getCell(14).getStringCellValue()).equals("success")) {
				} else {
					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put(
								dataValidation.microSmallValidator(row.getCell(
										14).getStringCellValue()), "");
					} else {
						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put(dataValidation
									.microSmallValidator(row.getCell(14)
											.getStringCellValue()), Long
									.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put(dataValidation
									.microSmallValidator(row.getCell(14)
											.getStringCellValue()), row
									.getCell(3).getStringCellValue());
						}

					}

				}

			}
		}

		// else if (row.getCell(11).getCellType() == Cell.CELL_TYPE_BLANK) {}
		c12 = row.getCell(15);
		if (c12 == null || c12.getCellType() == Cell.CELL_TYPE_BLANK) {
			if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
				errorList.put("UNIT ADDRESS is Required !!", "");
			} else {
				if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

					errorList.put("MSE ADDRESS is Required !!", Long
							.toString((long) row.getCell(3)
									.getNumericCellValue()));
				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					errorList.put("MSE ADDRESS is Required !!", row.getCell(3)
							.getStringCellValue());
				}

			}

		} else {
			if (row.getCell(15).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid MSE ADDRESS !!", "");
				} else {
					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid MSE ADDRESS !!", Long
								.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid MSE ADDRESS !!", row.getCell(3)
								.getStringCellValue());
					}

				}

			} else if (row.getCell(15).getCellType() == Cell.CELL_TYPE_STRING) {

				System.out.println("Valid Data..");
			}

		}
		// else if (row.getCell(12).getCellType() == Cell.CELL_TYPE_BLANK) {}

		c13 = row.getCell(16);
		if (c13 == null || c13.getCellType() == Cell.CELL_TYPE_BLANK) {
			if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
				errorList.put("CITY name is Required !!", "");
			} else {

				if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

					errorList.put("CITY name is Required !!", Long
							.toString((long) row.getCell(2)
									.getNumericCellValue()));
				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					errorList.put("CITY name is Required !!", row.getCell(3)
							.getStringCellValue());
				}
			}

		} else {
			if (row.getCell(16).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid CITY name !!", "");
				} else {

					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid CITY name !!", Long
								.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid CITY name !!", row.getCell(3)
								.getStringCellValue());
					}
				}

			} else if (row.getCell(16).getCellType() == Cell.CELL_TYPE_STRING) {

				System.out.println("Valid Data..");
			}

		}
		// else if (row.getCell(13).getCellType() == Cell.CELL_TYPE_BLANK) {}

		c14 = row.getCell(17);
		if (c14 == null || c14.getCellType() == Cell.CELL_TYPE_BLANK) {/*
																		 * 
																		 * 
																		 * if(c02
																		 * ==
																		 * null
																		 * ||
																		 * c02.
																		 * getCellType
																		 * () ==
																		 * Cell.
																		 * CELL_TYPE_BLANK
																		 * ){
																		 * errorList
																		 * .put(
																		 * "DISTRICT name is Required !!"
																		 * ,
																		 * "");
																		 * }
																		 * else{
																		 * 
																		 * if
																		 * (row
																		 * .
																		 * getCell
																		 * (3).
																		 * getCellType
																		 * () ==
																		 * Cell.
																		 * CELL_TYPE_NUMERIC
																		 * ){
																		 * 
																		 * errorList
																		 * .put(
																		 * "DISTRICT name is Required !!"
																		 * ,
																		 * Long.
																		 * toString
																		 * (
																		 * (long
																		 * )row.
																		 * getCell
																		 * (3).
																		 * getNumericCellValue
																		 * ()));
																		 * }else
																		 * if
																		 * (row
																		 * .
																		 * getCell
																		 * (3).
																		 * getCellType
																		 * () ==
																		 * Cell.
																		 * CELL_TYPE_STRING
																		 * ) {
																		 * errorList
																		 * .put(
																		 * "DISTRICT name is Required !!"
																		 * ,
																		 * row.
																		 * getCell
																		 * (3) .
																		 * getStringCellValue
																		 * ());
																		 * } }
																		 */
		} else {

			if (row.getCell(17).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid DISTRICT name !!", "");
				} else {

					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid DISTRICT name !!", Long
								.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid DISTRICT name !!", row
								.getCell(3).getStringCellValue());
					}
				}

			} else if (row.getCell(17).getCellType() == Cell.CELL_TYPE_STRING) {

				System.out.println("Valid Data..");
				if (dataValidation.StateDistrictValidator(mliValidatorService,
						row.getCell(17).getStringCellValue(),
						row.getCell(19).getStringCellValue(), distObjList,
						stateObjList).equalsIgnoreCase("success")) {

				} else {
					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put(dataValidation.StateDistrictValidator(
								mliValidatorService, row.getCell(17)
										.getStringCellValue(), row.getCell(19)
										.getStringCellValue(), distObjList,
								stateObjList), "");
					} else {

						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put(dataValidation
									.StateDistrictValidator(
											mliValidatorService, row
													.getCell(17)
													.getStringCellValue(), row
													.getCell(19)
													.getStringCellValue(),
											distObjList, stateObjList), Long
									.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put(dataValidation
									.StateDistrictValidator(
											mliValidatorService, row
													.getCell(17)
													.getStringCellValue(), row
													.getCell(19)
													.getStringCellValue(),
											distObjList, stateObjList), row
									.getCell(3).getStringCellValue());
						}
					}

				}

			}
		}

		// else if (row.getCell(14).getCellType() == Cell.CELL_TYPE_BLANK) {}

		c15 = row.getCell(18);
		if (c15 == null || c15.getCellType() == Cell.CELL_TYPE_BLANK) {
			if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
				errorList.put("PINCODE is Required !!", "");
			} else {
				if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

					errorList.put("PINCODE is Required !!", Long
							.toString((long) row.getCell(3)
									.getNumericCellValue()));

				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					errorList.put("PINCODE is Required !!", row.getCell(3)
							.getStringCellValue());

				}

			}

		} else {

			if (row.getCell(18).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (dataValidation.pincodeValidator(
						(int) row.getCell(18).getNumericCellValue()).equals(
						"success")) {
				} else {
					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put(dataValidation.pincodeValidator((int) row
								.getCell(18).getNumericCellValue()), "");
					} else {
						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put(dataValidation
									.pincodeValidator((int) row.getCell(18)
											.getNumericCellValue()), Long
									.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put(dataValidation
									.pincodeValidator((int) row.getCell(18)
											.getNumericCellValue()), row
									.getCell(3).getStringCellValue());
						}

					}

				}

			} else if (row.getCell(18).getCellType() == Cell.CELL_TYPE_STRING) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid PINCODE !!", "");
				} else {
					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid PINCODE !!", Long
								.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid PINCODE !!", row.getCell(3)
								.getStringCellValue());
					}

				}

			}
		}
		// else if (row.getCell(15).getCellType() == Cell.CELL_TYPE_BLANK) {}
		c16 = row.getCell(19);
		if (c16 == null || c16.getCellType() == Cell.CELL_TYPE_BLANK) {
			if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
				errorList.put("STATE name is Required !!", "");
			} else {
				if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

					errorList.put("STATE name is Required !!", Long
							.toString((long) row.getCell(3)
									.getNumericCellValue()));
				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					errorList.put("STATE name is Required !!", row.getCell(3)
							.getStringCellValue());
				}

			}

		} else {
			if (row.getCell(19).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid STATE name !!", "");
				} else

				if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

					errorList.put("Invalid STATE name !!", Long
							.toString((long) row.getCell(3)
									.getNumericCellValue()));
				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					errorList.put("Invalid STATE name !!", row.getCell(3)
							.getStringCellValue());
				}

			} else if (row.getCell(19).getCellType() == Cell.CELL_TYPE_STRING) {
				System.out.println("Valid Data..");

			}

		}
		// else if (row.getCell(16).getCellType() == Cell.CELL_TYPE_BLANK) {}

		//
		c17 = row.getCell(20);
		c001=row.getCell(12);
		
		if (c17 == null || c17.getCellType() == Cell.CELL_TYPE_BLANK) {
			if (c001!=null && row.getCell(12).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (dataValidation.sansanAmountAmountValidator(
						(int) row.getCell(12).getNumericCellValue()).equals(
						"success")) {
				} else {
					c021 = row.getCell(39);
					if (c021 == null
							|| c021.getCellType() == Cell.CELL_TYPE_NUMERIC) {
						if (dataValidation.aadharNumberValidation(
								(long) row.getCell(39).getNumericCellValue())
								.equals("success")) {

						} else {
							if (c02 == null
									|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
								errorList.put(dataValidation
										.aadharNumberValidation((long) row
												.getCell(39)
												.getNumericCellValue()), "");
							} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

								errorList.put(dataValidation
										.aadharNumberValidation((long) row
												.getCell(39)
												.getNumericCellValue()), Long
										.toString((long) row.getCell(3)
												.getNumericCellValue()));
							} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
								errorList.put(dataValidation
										.aadharNumberValidation((long) row
												.getCell(39)
												.getNumericCellValue()), row
										.getCell(3).getStringCellValue());
							}

						}

					} else if (c021 == null
							|| c021.getCellType() == Cell.CELL_TYPE_STRING) {
						if (c02 == null
								|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
							errorList.put("Invalid AADHAR Number !!", "");
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {
							errorList.put("Invalid AADHAR Number !!", Long
									.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put("Invalid AADHAR Number !!", row
									.getCell(3).getStringCellValue());
						}

					} else if (c021 == null
							|| c021.getCellType() == Cell.CELL_TYPE_BLANK) {

						if (c02 == null
								|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
							errorList
									.put("If Sanction amount above '500000' than IT-PAN or AADHAR NUMBER is Mandotary !!",
											"");
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {
							errorList
									.put("If Sanction amount above '500000' than IT-PAN or AADHAR NUMBER is Mandotary !!",
											Long.toString((long) row.getCell(3)
													.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList
									.put("If Sanction amount above '500000' than IT-PAN or AADHAR NUMBER is Mandotary !!",
											row.getCell(3).getStringCellValue());

						}
					}

				}

			}

		} else {
			if (row.getCell(20).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {

				} else {

					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid UNIT ITPAN !!", Long
								.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid UNIT ITPAN !!", row.getCell(3)
								.getStringCellValue());
					}

				}

			} else if (row.getCell(20).getCellType() == Cell.CELL_TYPE_STRING) {
				if (dataValidation.mseITpanValidator(
						row.getCell(20).getStringCellValue()).equals("success")) {
				} else {

					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put(dataValidation.mseITpanValidator(row
								.getCell(20).getStringCellValue()), "");
					} else {

						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put(dataValidation.mseITpanValidator(row
									.getCell(20).getStringCellValue()), Long
									.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put(dataValidation.mseITpanValidator(row
									.getCell(20).getStringCellValue()), row
									.getCell(3).getStringCellValue());
						}
					}

				}

			}

		}
		/*
		 * else if (row.getCell(17).getCellType() == Cell.CELL_TYPE_BLANK) {
		 * System.out.println(
		 * "***********************************************************************Null"
		 * );
		 * 
		 * if (row.getCell(2).getCellType() == Cell.CELL_TYPE_NUMERIC){
		 * 
		 * errorList.put("MSE ITPAN is Required !!",
		 * Long.toString((long)row.getCell(2).getNumericCellValue()));
		 * 
		 * }else if (row.getCell(2).getCellType() == Cell.CELL_TYPE_STRING) {
		 * errorList.put("MSE ITPAN is Required !!", row.getCell(2)
		 * .getStringCellValue());
		 * 
		 * }else if (row.getCell(2).getCellType() == Cell.CELL_TYPE_BLANK) {
		 * errorList.put("MSE ITPAN is Required !!", row.getCell(2)
		 * .getStringCellValue()); }
		 * 
		 * }
		 */

		c18 = row.getCell(21);
		if (c18 == null || c18.getCellType() == Cell.CELL_TYPE_BLANK) {

		} else {
			if (row.getCell(21).getCellType() == Cell.CELL_TYPE_STRING) {
				System.out.println("Valid Data..");

			} else if (row.getCell(16).getCellType() == Cell.CELL_TYPE_NUMERIC) {

				// errorList.put("UDYOG AADHAR NO. is Required !!",
				// row.getCell(2)
				// .getStringCellValue());

			}

		}
		/*
		 * if (row.getCell(18).getCellType() == Cell.CELL_TYPE_BLANK) { //
		 * errorList.put("Invalid UDYOG AADHAR NO. !!", row.getCell(2) //
		 * .getStringCellValue()); System.out.println("Blank"); } else
		 */
		// Edit by Saurav Tyagi on 19/07/2018 for client requirements
		/*
		 * Cell c19 = row.getCell(19); if(c19 == null || c19.getCellType() ==
		 * Cell.CELL_TYPE_BLANK){
		 * 
		 * }else{ if (row.getCell(19).getCellType() == Cell.CELL_TYPE_STRING) {
		 * 
		 * // errorList.put("Invalid MSME REGISTRATION NO. !!", row.getCell(2)
		 * // .getStringCellValue()); System.out.println("CELL_TYPE_NUMERIC"); }
		 * else if (row.getCell(19).getCellType() == Cell.CELL_TYPE_NUMERIC) {
		 * 
		 * // errorList.put("MSME REGISTRATION NO. is Required !!", row //
		 * .getCell(2).getStringCellValue());
		 * 
		 * }
		 * 
		 * }
		 *//*
			 * if (row.getCell(19).getCellType() == Cell.CELL_TYPE_BLANK) {
			 * 
			 * } else
			 */
		c20 = row.getCell(22);
		if (c20 == null || c20.getCellType() == Cell.CELL_TYPE_BLANK) {
			if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
				errorList.put("INDUSTRY NATURE is Required !!", "");
			} else {
				if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

					errorList.put("INDUSTRY NATURE is Required !!", Long
							.toString((long) row.getCell(3)
									.getNumericCellValue()));
				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					errorList.put("INDUSTRY NATURE is Required !!", row
							.getCell(3).getStringCellValue());
				}

			}

		} else {
			if (row.getCell(22).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {

				} else {
					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid INDUSTRY NATURE !!", Long
								.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid INDUSTRY NATURE !!", row
								.getCell(3).getStringCellValue());
					}

				}

			} else if (row.getCell(22).getCellType() == Cell.CELL_TYPE_STRING) {

				if (dataValidation.industryNatureValidator(
						row.getCell(22).getStringCellValue()).equals("success")) {

				} else {
					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put(dataValidation
								.industryNatureValidator(row.getCell(22)
										.getStringCellValue()), "");
					} else {
						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put(dataValidation
									.industryNatureValidator(row.getCell(22)
											.getStringCellValue()), Long
									.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put(dataValidation
									.industryNatureValidator(row.getCell(22)
											.getStringCellValue()), row
									.getCell(3).getStringCellValue());
						}

					}

				}
			}

		}
		// else if (row.getCell(20).getCellType() == Cell.CELL_TYPE_BLANK) {}

		c21 = row.getCell(23);
		if (c21 == null || c21.getCellType() == Cell.CELL_TYPE_BLANK) {

			if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
				errorList.put("INDUSTRY SECTOR is Required !!", "");
			} else {

				if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

					errorList.put("INDUSTRY SECTOR is Required !!", Long
							.toString((long) row.getCell(3)
									.getNumericCellValue()));
				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					errorList.put("INDUSTRY SECTOR is Required !!", row
							.getCell(2).getStringCellValue());
				}
			}

		} else {

			if (row.getCell(23).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid INDUSTRY SECTOR !!", "");
				} else {
					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid INDUSTRY SECTOR !!", Long
								.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid INDUSTRY SECTOR !!", row
								.getCell(3).getStringCellValue());
					}

				}

			} else if (row.getCell(23).getCellType() == Cell.CELL_TYPE_STRING) {

				if (dataValidation.industrySectorValidator(
						row.getCell(23).getStringCellValue()).equals("success")) {

				} else {
					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put(dataValidation
								.industrySectorValidator(row.getCell(23)
										.getStringCellValue()), "");
					} else {
						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put(dataValidation
									.industrySectorValidator(row.getCell(23)
											.getStringCellValue()), Long
									.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put(dataValidation
									.industrySectorValidator(row.getCell(23)
											.getStringCellValue()), row
									.getCell(3).getStringCellValue());
						}

					}

				}

			}
		}
		// else if (row.getCell(21).getCellType() == Cell.CELL_TYPE_BLANK) {}
		c22 = row.getCell(24);
		if (c22 == null || c22.getCellType() == Cell.CELL_TYPE_BLANK) {

			/*
			 * if(c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK){
			 * errorList.put("NO. OF EMPLOYEES is Required !!", ""); }else{
			 * 
			 * if (row.getCell(2).getCellType() == Cell.CELL_TYPE_NUMERIC){
			 * 
			 * errorList.put("NO. OF EMPLOYEES is Required !!",
			 * Long.toString((long)row.getCell(2).getNumericCellValue())); }else
			 * if (row.getCell(2).getCellType() == Cell.CELL_TYPE_STRING) {
			 * errorList.put("NO. OF EMPLOYEES is Required !!", row.getCell(2)
			 * .getStringCellValue()); } }
			 */

		} else {
			if (row.getCell(24).getCellType() == Cell.CELL_TYPE_NUMERIC) {

				if (dataValidation.numberOfEmployeesValidator(
						(int) row.getCell(24).getNumericCellValue()).equals(
						"success")) {
				} else {
					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put(dataValidation
								.numberOfEmployeesValidator((int) row.getCell(
										24).getNumericCellValue()), "");
					} else {
						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList
									.put(dataValidation
											.numberOfEmployeesValidator((int) row
													.getCell(24)
													.getNumericCellValue()),
											Long.toString((long) row.getCell(3)
													.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList
									.put(dataValidation
											.numberOfEmployeesValidator((int) row
													.getCell(24)
													.getNumericCellValue()),
											row.getCell(3).getStringCellValue());
						}

					}

				}

			} else if (row.getCell(24).getCellType() == Cell.CELL_TYPE_STRING) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid NO. OF EMPLOYEES !!", "");
				} else {
					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid NO. OF EMPLOYEES !!", Long
								.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid NO. OF EMPLOYEES!!", row
								.getCell(3).getStringCellValue());
					}

				}

			}

		}
		// else if (row.getCell(22).getCellType() == Cell.CELL_TYPE_BLANK) {

		// }

		c23 = row.getCell(25);
		if (c23 == null || c23.getCellType() == Cell.CELL_TYPE_BLANK) {/*
																		 * if(c02
																		 * ==
																		 * null
																		 * ||
																		 * c02.
																		 * getCellType
																		 * () ==
																		 * Cell.
																		 * CELL_TYPE_BLANK
																		 * ){
																		 * errorList
																		 * .put(
																		 * "PROJECTED SALES detail is Required !!"
																		 * ,
																		 * "");
																		 * }
																		 * else{
																		 * if
																		 * (row
																		 * .
																		 * getCell
																		 * (3).
																		 * getCellType
																		 * () ==
																		 * Cell.
																		 * CELL_TYPE_NUMERIC
																		 * ){
																		 * 
																		 * errorList
																		 * .put(
																		 * "PROJECTED SALES detail is Required !!"
																		 * ,
																		 * Long
																		 * .
																		 * toString
																		 * (
																		 * (long
																		 * )row.
																		 * getCell
																		 * (3).
																		 * getNumericCellValue
																		 * ()));
																		 * }else
																		 * if
																		 * (row
																		 * .
																		 * getCell
																		 * (3).
																		 * getCellType
																		 * () ==
																		 * Cell.
																		 * CELL_TYPE_STRING
																		 * ) {
																		 * errorList
																		 * .put(
																		 * "PROJECTED SALES detail is Required !!"
																		 * ,
																		 * row.
																		 * getCell
																		 * (3) .
																		 * getStringCellValue
																		 * ());
																		 * }
																		 * 
																		 * }
																		 */
		} else {
			if (row.getCell(25).getCellType() == Cell.CELL_TYPE_NUMERIC) {

				System.out.println("valid Data");

			} else if (row.getCell(25).getCellType() == Cell.CELL_TYPE_STRING) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid PROJECTED SALES detail !!", "");
				} else {
					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid PROJECTED SALES detail !!", Long
								.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid PROJECTED SALES detail !!", row
								.getCell(3).getStringCellValue());
					}

				}

			}

		}
		// else if (row.getCell(23).getCellType() == Cell.CELL_TYPE_BLANK) {}
		c24 = row.getCell(26);
		if (c24 == null || c24.getCellType() == Cell.CELL_TYPE_BLANK) {/*
																		 * if(c02
																		 * ==
																		 * null
																		 * ||
																		 * c02.
																		 * getCellType
																		 * () ==
																		 * Cell.
																		 * CELL_TYPE_BLANK
																		 * ){
																		 * errorList
																		 * .put(
																		 * "PROJECTED EXPORTS detail is Required !!"
																		 * ,
																		 * "");
																		 * }
																		 * else{
																		 * 
																		 * if
																		 * (row
																		 * .
																		 * getCell
																		 * (3).
																		 * getCellType
																		 * () ==
																		 * Cell.
																		 * CELL_TYPE_NUMERIC
																		 * ){
																		 * 
																		 * errorList
																		 * .put(
																		 * "PROJECTED EXPORTS detail is Required !!"
																		 * ,
																		 * Long
																		 * .
																		 * toString
																		 * (
																		 * (long
																		 * )row.
																		 * getCell
																		 * (3).
																		 * getNumericCellValue
																		 * ()));
																		 * }else
																		 * if
																		 * (row
																		 * .
																		 * getCell
																		 * (3).
																		 * getCellType
																		 * () ==
																		 * Cell.
																		 * CELL_TYPE_STRING
																		 * ) {
																		 * errorList
																		 * .put(
																		 * "PROJECTED EXPORTS detail is Required !!"
																		 * ,
																		 * row.
																		 * getCell
																		 * (3) .
																		 * getStringCellValue
																		 * ());
																		 * } }
																		 */
		} else {

			if (row.getCell(26).getCellType() == Cell.CELL_TYPE_NUMERIC) {

				System.out.println("valid Data");

			} else if (row.getCell(26).getCellType() == Cell.CELL_TYPE_STRING) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid PROJECTED EXPORTS detail !!", "");
				} else {

					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid PROJECTED EXPORTS detail !!",
								Long.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid PROJECTED EXPORTS detail !!",
								row.getCell(3).getStringCellValue());
					}
				}

			}
		}
		// else if (row.getCell(24).getCellType() == Cell.CELL_TYPE_BLANK) {}
		c25 = row.getCell(27);
		if (c25 == null || c25.getCellType() == Cell.CELL_TYPE_BLANK) {
			if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
				errorList.put("NEW/ EXISTING Unit Status is Required", "");
			} else {

				if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

					errorList.put("NEW/EXISTING Unit Status is Required", Long
							.toString((long) row.getCell(3)
									.getNumericCellValue()));
				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					errorList.put("NEW/EXISTING Unit Status is Required !!",
							row.getCell(3).getStringCellValue());
				}
			}

		} else {
			if (row.getCell(27).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid NEW/ EXISTING UNIT Status !!", "");
				} else {

					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid NEW/ EXISTING UNIT Status!!",
								Long.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid NEW/EXISTING UNIT Status !!",
								row.getCell(3).getStringCellValue());
					}
				}

			} else if (row.getCell(27).getCellType() == Cell.CELL_TYPE_STRING) {

				if (dataValidation.newExistingValidator(
						row.getCell(27).getStringCellValue()).equals("success")) {

				} else {
					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put(dataValidation.newExistingValidator(row
								.getCell(27).getStringCellValue()), "");
					} else {

						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put(dataValidation
									.newExistingValidator(row.getCell(27)
											.getStringCellValue()), Long
									.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put(dataValidation
									.newExistingValidator(row.getCell(27)
											.getStringCellValue()), row
									.getCell(3).getStringCellValue());
						}
					}

				}
			}

		}
		// else if (row.getCell(25).getCellType() == Cell.CELL_TYPE_BLANK) {}

		c26 = row.getCell(28);
		if (c26 == null || c26.getCellType() == Cell.CELL_TYPE_BLANK) {
			/*
			 * if(c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK){
			 * errorList .put(
			 * "CUSTOMER  HAVING ANY PREVIOUS BANKING EXPERIENCE? status is Required !!"
			 * , ""); }else{ if (row.getCell(2).getCellType() ==
			 * Cell.CELL_TYPE_NUMERIC){
			 * 
			 * errorList .put(
			 * "CUSTOMER  HAVING ANY PREVIOUS BANKING EXPERIENCE? status is Required !!"
			 * , Long.toString((long)row.getCell(2).getNumericCellValue()));
			 * }else if (row.getCell(2).getCellType() == Cell.CELL_TYPE_STRING)
			 * { errorList .put(
			 * "CUSTOMER  HAVING ANY PREVIOUS BANKING EXPERIENCE? status is Required !!"
			 * , row.getCell(2).getStringCellValue()); }
			 * 
			 * }
			 */

		} else {

			if (row.getCell(28).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList
							.put("Invalid data for CUSTOMER HAVING ANY PREVIOUS BANKING EXPERIENCE? !!",
									"");
				} else {
					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList
								.put("Invalid data for CUSTOMER HAVING ANY PREVIOUS BANKING EXPERIENCE? !!",
										Long.toString((long) row.getCell(3)
												.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList
								.put("Invalid data for CUSTOMER HAVING ANY PREVIOUS BANKING EXPERIENCE? !!",
										row.getCell(3).getStringCellValue());
					}

				}

			} else if (row.getCell(28).getCellType() == Cell.CELL_TYPE_STRING) {

				if (dataValidation.unbankedCustomerValidator(
						row.getCell(28).getStringCellValue()).equals("success")) {

				} else {
					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put(dataValidation
								.unbankedCustomerValidator(row.getCell(28)
										.getStringCellValue()), "");
					} else {

						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put(dataValidation
									.unbankedCustomerValidator(row.getCell(28)
											.getStringCellValue()), Long
									.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put(dataValidation
									.unbankedCustomerValidator(row.getCell(28)
											.getStringCellValue()), row
									.getCell(3).getStringCellValue());
						}
					}

				}
			}
		}
		// else if (row.getCell(26).getCellType() == Cell.CELL_TYPE_BLANK) {}
		// Edit by Saurav Tyagi on 19/07/2018 for
		/*
		 * Cell c27 = row.getCell(27); if(c27 == null || c27.getCellType() ==
		 * Cell.CELL_TYPE_BLANK){ if(c02 == null || c02.getCellType() ==
		 * Cell.CELL_TYPE_BLANK){
		 * errorList.put("FIRST TIME CUSTOMER ? Status is Required !!","");
		 * }else{ if (row.getCell(2).getCellType() == Cell.CELL_TYPE_NUMERIC){
		 * 
		 * errorList.put("FIRST TIME CUSTOMER ? Status is Required !!",
		 * Long.toString((long)row.getCell(2).getNumericCellValue())); }else if
		 * (row.getCell(2).getCellType() == Cell.CELL_TYPE_STRING) {
		 * errorList.put("FIRST TIME CUSTOMER ? Status is Required !!", row
		 * .getCell(2).getStringCellValue()); }
		 * 
		 * }
		 * 
		 * 
		 * }else{
		 * 
		 * if (row.getCell(27).getCellType() == Cell.CELL_TYPE_NUMERIC) { if(c02
		 * == null || c02.getCellType() == Cell.CELL_TYPE_BLANK){
		 * errorList.put("Invalid data for FIRST TIME CUSTOMER ? !!",""); }else{
		 * 
		 * if (row.getCell(2).getCellType() == Cell.CELL_TYPE_NUMERIC){
		 * 
		 * errorList.put("Invalid data for  FIRST TIME CUSTOMER ? !!",
		 * Long.toString((long)row.getCell(2).getNumericCellValue())); }else if
		 * (row.getCell(2).getCellType() == Cell.CELL_TYPE_STRING) {
		 * errorList.put("Invalid FIRST TIME CUSTOMER ? Status !!",
		 * row.getCell(2) .getStringCellValue()); } }
		 * 
		 * 
		 * 
		 * } else if (row.getCell(27).getCellType() == Cell.CELL_TYPE_STRING) {
		 * 
		 * if (dataValidation.newCustomerValidator(
		 * row.getCell(27).getStringCellValue(),
		 * row.getCell(26).getStringCellValue()).equals("success")) { } else {
		 * if(c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK){
		 * errorList.put(dataValidation.newCustomerValidator(
		 * row.getCell(27).getStringCellValue(), row.getCell(26)
		 * .getStringCellValue()), ""); }else{
		 * 
		 * if (row.getCell(2).getCellType() == Cell.CELL_TYPE_NUMERIC){
		 * 
		 * errorList.put(dataValidation.newCustomerValidator(
		 * row.getCell(27).getStringCellValue(), row.getCell(26)
		 * .getStringCellValue()),
		 * Long.toString((long)row.getCell(2).getNumericCellValue())); }else if
		 * (row.getCell(2).getCellType() == Cell.CELL_TYPE_STRING) {
		 * errorList.put(dataValidation.newCustomerValidator(
		 * row.getCell(27).getStringCellValue(), row.getCell(26)
		 * .getStringCellValue()), row.getCell(2) .getStringCellValue()); } }
		 * 
		 * 
		 * 
		 * } } } // else if (row.getCell(27).getCellType() ==
		 * Cell.CELL_TYPE_BLANK) {}
		 */
		c28 = row.getCell(29);
		if (c28 == null || c28.getCellType() == Cell.CELL_TYPE_BLANK) {
			if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
				errorList.put("CHIEF PROMOTER FIRST NAME is Required !!", "");
			} else {
				if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

					errorList.put("CHIEF PROMOTER FIRST NAME is Required !!",
							Long.toString((long) row.getCell(3)
									.getNumericCellValue()));
				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					errorList.put("CHIEF PROMOTER FIRST NAME is Required !!",
							row.getCell(3).getStringCellValue());
				}

			}

		} else {
			if (row.getCell(29).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid CHIEF PROMOTER FIRST NAME !!", "");
				} else {
					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid CHIEF PROMOTER FIRST NAME !!",
								Long.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid CHIEF PROMOTER FIRST NAME !!",
								row.getCell(3).getStringCellValue());
					}

				}

			} else if (row.getCell(29).getCellType() == Cell.CELL_TYPE_STRING) {

				System.out.println("valid data..");
			}

		}
		// else if (row.getCell(28).getCellType() == Cell.CELL_TYPE_BLANK) {}

		c29 = row.getCell(30);
		if (c29 == null || c29.getCellType() == Cell.CELL_TYPE_BLANK) {

		} else {
			if (row.getCell(30).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid CHIEF PROMOTER MIDDLE NAME !!", "");
				} else {

					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid CHIEF PROMOTER MIDDLE NAME !!",
								Long.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid CHIEF PROMOTER MIDDLE NAME !!",
								row.getCell(3).getStringCellValue());
					}
				}

			} else if (row.getCell(30).getCellType() == Cell.CELL_TYPE_STRING) {

				System.out.println("valid data..");
			}

		}
		// else if (row.getCell(29).getCellType() == Cell.CELL_TYPE_BLANK) {

		// errorList.put("CHIEF PROMOTER MIDDLE NAME is Required !!", row
		// .getCell(2).getStringCellValue());

		// }
		c30 = row.getCell(31);
		if (c30 == null || c30.getCellType() == Cell.CELL_TYPE_BLANK) {
			if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
				errorList.put("CHIEF PROMOTER LAST NAME is Required !!", "");
			} else {
				if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

					errorList.put("CHIEF PROMOTER LAST NAME is Required !!",
							Long.toString((long) row.getCell(3)
									.getNumericCellValue()));
				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					errorList.put("CHIEF PROMOTER LAST NAME is Required !!",
							row.getCell(3).getStringCellValue());
				}

			}

		} else {

			if (row.getCell(31).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid CHIEF PROMOTER LAST NAME !!", "");
				} else {

					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid CHIEF PROMOTER LAST NAME !!",
								Long.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid CHIEF PROMOTER LAST NAME !!",
								row.getCell(3).getStringCellValue());
					}
				}

			} else if (row.getCell(31).getCellType() == Cell.CELL_TYPE_STRING) {

				System.out.println("valid data..");
			}
		}
		// else if (row.getCell(30).getCellType() == Cell.CELL_TYPE_BLANK) {}
		c31 = row.getCell(32);
		if (c31 == null || c31.getCellType() == Cell.CELL_TYPE_BLANK) {

		} else {

			if (row.getCell(32).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid CHIEF PROMOTER IT-PAN !!", "");
				} else {
					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid CHIEF PROMOTER IT-PAN !!", Long
								.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid CHIEF PROMOTER IT-PAN !!", row
								.getCell(3).getStringCellValue());
					}

				}

			} else if (row.getCell(32).getCellType() == Cell.CELL_TYPE_STRING) {
				if (dataValidation.chiefPromoterITpanValidator(
						row.getCell(32).getStringCellValue()).equals("success")) {
				} else {
					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put(dataValidation
								.chiefPromoterITpanValidator(row.getCell(32)
										.getStringCellValue()), "");
					} else {
						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put(dataValidation
									.chiefPromoterITpanValidator(row
											.getCell(32).getStringCellValue()),
									Long.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put(dataValidation
									.chiefPromoterITpanValidator(row
											.getCell(32).getStringCellValue()),
									row.getCell(3).getStringCellValue());
						}

					}

				}

			}
		}
		/*
		 * else if (row.getCell(31).getCellType() == Cell.CELL_TYPE_BLANK) { if
		 * (row.getCell(2).getCellType() == Cell.CELL_TYPE_NUMERIC){
		 * 
		 * errorList.put("CHIEF PROMOTER IT-PAN is Required !!",
		 * Long.toString((long)row.getCell(2).getNumericCellValue())); }else if
		 * (row.getCell(2).getCellType() == Cell.CELL_TYPE_STRING) {
		 * errorList.put("CHIEF PROMOTER IT-PAN is Required !!", row
		 * .getCell(2).getStringCellValue()); }else if
		 * (row.getCell(2).getCellType() == Cell.CELL_TYPE_BLANK) {
		 * errorList.put("CHIEF PROMOTER IT-PAN is Required !!", row
		 * .getCell(2).getStringCellValue()); }
		 * 
		 * 
		 * }
		 */

		c32 = row.getCell(33);
		if (c32 == null || c32.getCellType() == Cell.CELL_TYPE_BLANK) {/*
																		 * if(c02
																		 * ==
																		 * null
																		 * ||
																		 * c02.
																		 * getCellType
																		 * () ==
																		 * Cell.
																		 * CELL_TYPE_BLANK
																		 * ){
																		 * errorList
																		 * .put(
																		 * "CHIEF PROMOTER'S MAIL ID is Required !!"
																		 * ,
																		 * "");
																		 * }
																		 * else{
																		 * if
																		 * (row
																		 * .
																		 * getCell
																		 * (3).
																		 * getCellType
																		 * () ==
																		 * Cell.
																		 * CELL_TYPE_NUMERIC
																		 * ){
																		 * 
																		 * errorList
																		 * .put(
																		 * "CHIEF PROMOTER'S MAIL ID is Required !!"
																		 * ,
																		 * Long
																		 * .
																		 * toString
																		 * (
																		 * (long
																		 * )row.
																		 * getCell
																		 * (3).
																		 * getNumericCellValue
																		 * ()));
																		 * }else
																		 * if
																		 * (row
																		 * .
																		 * getCell
																		 * (3).
																		 * getCellType
																		 * () ==
																		 * Cell.
																		 * CELL_TYPE_STRING
																		 * ) {
																		 * errorList
																		 * .put(
																		 * "CHIEF PROMOTER'S MAIL ID is Required !!"
																		 * , row
																		 * .
																		 * getCell
																		 * (3).
																		 * getStringCellValue
																		 * ());
																		 * }
																		 * 
																		 * }
																		 */
		} else {

			if (row.getCell(33).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid CHIEF PROMOTER'S MAIL ID !!", "");
				} else {

					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid CHIEF PROMOTER'S MAIL ID !!",
								Long.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid CHIEF PROMOTER'S MAIL ID !!",
								row.getCell(3).getStringCellValue());
					}
				}

			} else if (row.getCell(33).getCellType() == Cell.CELL_TYPE_STRING) {

				if (dataValidation.emailIdValidator(
						row.getCell(33).getStringCellValue()).equals("success")) {
				} else {
					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put(dataValidation.emailIdValidator(row
								.getCell(33).getStringCellValue()), "");
					} else {
						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put(dataValidation.emailIdValidator(row
									.getCell(33).getStringCellValue()), Long
									.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put(dataValidation.emailIdValidator(row
									.getCell(28).getStringCellValue()), row
									.getCell(3).getStringCellValue());
						}

					}

				}

			}
		}
		// else if (row.getCell(32).getCellType() == Cell.CELL_TYPE_BLANK) {}

		c33 = row.getCell(34);
		if (c33 == null || c33.getCellType() == Cell.CELL_TYPE_BLANK) {
			if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
				errorList.put("CHIEF PROMOTER'S CONTACT NUMBER is Required !!",
						"");
			} else {
				if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

					errorList.put(
							"CHIEF PROMOTER'S CONTACT NUMBER is Required !!",
							Long.toString((long) row.getCell(3)
									.getNumericCellValue()));
				} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
					errorList.put(
							"CHIEF PROMOTER'S CONTACT NUMBER is Required !!",
							row.getCell(3).getStringCellValue());
				}

			}

		} else {
			if (row.getCell(34).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				System.out.println("Mobile Number "
						+ (long) row.getCell(34).getNumericCellValue());
				if (dataValidation.mobileNumberValidator(
						(long) row.getCell(34).getNumericCellValue()).equals(
						"success")) {
				} else {
					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put(dataValidation
								.mobileNumberValidator((long) row.getCell(34)
										.getNumericCellValue()), "");
					} else {
						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put(dataValidation
									.mobileNumberValidator((long) row.getCell(
											34).getNumericCellValue()), Long
									.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put(dataValidation
									.mobileNumberValidator((long) row.getCell(
											34).getNumericCellValue()), row
									.getCell(3).getStringCellValue());
						}

					}

				}

			} else if (row.getCell(34).getCellType() == Cell.CELL_TYPE_STRING) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid CHIEF PROMOTER'S CONTACT NUMBER !!",
							"");
				} else {
					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put(
								"Invalid CHIEF PROMOTER'S CONTACT NUMBER !!",
								Long.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put(
								"Invalid CHIEF PROMOTER'S CONTACT NUMBER !!",
								row.getCell(3).getStringCellValue());
					}

				}

			}

		}
		// else if (row.getCell(33).getCellType() == Cell.CELL_TYPE_BLANK) {}

		c34 = row.getCell(35);
		if (c34 == null || c34.getCellType() == Cell.CELL_TYPE_BLANK) {
			/*
			 * if(c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK){
			 * errorList.put("MINORITY COMMUNITY Status is Required !!", "");
			 * }else{ if (row.getCell(2).getCellType() ==
			 * Cell.CELL_TYPE_NUMERIC){
			 * 
			 * errorList.put("MINORITY COMMUNITY Status is Required !!",
			 * Long.toString((long)row.getCell(2).getNumericCellValue())); }else
			 * if (row.getCell(2).getCellType() == Cell.CELL_TYPE_STRING) {
			 * errorList.put("MINORITY COMMUNITY Status is Required !!",
			 * row.getCell(2) .getStringCellValue()); }
			 * 
			 * }
			 */

		} else {
			if (row.getCell(35).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid MINORITY COMMUNITY Status !!", "");
				} else {

					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid MINORITY COMMUNITY Status !!",
								Long.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid MINORITY COMMUNITY Status !!",
								row.getCell(3).getStringCellValue());
					}
				}

			} else if (row.getCell(35).getCellType() == Cell.CELL_TYPE_STRING) {

				if (dataValidation.minorityCommunityValidator(
						row.getCell(35).getStringCellValue()).equals("success")) {
				} else {
					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put(dataValidation
								.minorityCommunityValidator(row.getCell(35)
										.getStringCellValue()), "");
					} else {
						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put(dataValidation
									.minorityCommunityValidator(row.getCell(35)
											.getStringCellValue()), Long
									.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put(dataValidation
									.minorityCommunityValidator(row.getCell(35)
											.getStringCellValue()), row
									.getCell(3).getStringCellValue());
						}

					}

				}

			}

		}
		// else if (row.getCell(34).getCellType() == Cell.CELL_TYPE_BLANK) {}

		c35 = row.getCell(36);
		if (c35 == null || c35.getCellType() == Cell.CELL_TYPE_BLANK) {
			/*
			 * if(c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK){
			 * errorList.put("HANDICAPPED Status is Required !!", ""); }else{
			 * 
			 * if (row.getCell(2).getCellType() == Cell.CELL_TYPE_NUMERIC){
			 * 
			 * errorList.put("HANDICAPPED Status is Required !!",
			 * Long.toString((long)row.getCell(2).getNumericCellValue())); }else
			 * if (row.getCell(2).getCellType() == Cell.CELL_TYPE_STRING) {
			 * errorList.put("HANDICAPPED Status is Required !!", row.getCell(2)
			 * .getStringCellValue()); } }
			 */

		} else {
			if (row.getCell(36).getCellType() == Cell.CELL_TYPE_NUMERIC) {

				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid HANDICAPPED Status !!", "");
				} else {

					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid HANDICAPPED Status !!", Long
								.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid HANDICAPPED Detail !!", row
								.getCell(3).getStringCellValue());
					}
				}

			} else if (row.getCell(36).getCellType() == Cell.CELL_TYPE_STRING) {

				if (dataValidation.handicappedValidator(
						row.getCell(36).getStringCellValue()).equals("success")) {
				} else {
					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put(dataValidation.handicappedValidator(row
								.getCell(36).getStringCellValue()), "");
					} else {

						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put(dataValidation
									.handicappedValidator(row.getCell(36)
											.getStringCellValue()), Long
									.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put(dataValidation
									.handicappedValidator(row.getCell(36)
											.getStringCellValue()), row
									.getCell(3).getStringCellValue());
						}
					}

				}

			}

		}
		// else if (row.getCell(35).getCellType() == Cell.CELL_TYPE_BLANK) {}
		c36 = row.getCell(37);
		if (c36 == null || c36.getCellType() == Cell.CELL_TYPE_BLANK) {
			/*
			 * if(c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK){
			 * errorList.put("WOMEN Detail is Required !!", ""); }else{
			 * 
			 * if (row.getCell(2).getCellType() == Cell.CELL_TYPE_NUMERIC){
			 * 
			 * errorList.put("WOMEN Detail is Required !!",
			 * Long.toString((long)row.getCell(2).getNumericCellValue())); }else
			 * if (row.getCell(2).getCellType() == Cell.CELL_TYPE_STRING) {
			 * errorList.put("WOMEN Detail is Required !!", row.getCell(2)
			 * .getStringCellValue()); } }
			 */

		} else {
			if (row.getCell(37).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid WOMEN Details !!", "");
				} else {

					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid WOMEN Details !!", Long
								.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid WOMEN Details !!", row
								.getCell(3).getStringCellValue());
					}
				}

			} else if (row.getCell(37).getCellType() == Cell.CELL_TYPE_STRING) {

				if (dataValidation.womenValidator(
						row.getCell(37).getStringCellValue()).equals("success")) {
				} else {
					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put(dataValidation.womenValidator(row
								.getCell(37).getStringCellValue()), "");
					} else {
						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put(dataValidation.womenValidator(row
									.getCell(37).getStringCellValue()), Long
									.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put(dataValidation.womenValidator(row
									.getCell(37).getStringCellValue()), row
									.getCell(3).getStringCellValue());
						}

					}

				}

			}

		}
		// else if (row.getCell(36).getCellType() == Cell.CELL_TYPE_BLANK) {}

		c37 = row.getCell(38);
		if (c37 == null || c37.getCellType() == Cell.CELL_TYPE_BLANK) {
			/*
			 * if(c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK){
			 * errorList.put( "CATEGORY of borrower is mandatory !!", "");
			 * }else{ if (row.getCell(2).getCellType() ==
			 * Cell.CELL_TYPE_NUMERIC){
			 * 
			 * errorList.put( "CATEGORY of borrower is mandatory !!",
			 * Long.toString((long)row.getCell(2).getNumericCellValue())); }else
			 * if (row.getCell(2).getCellType() == Cell.CELL_TYPE_STRING) {
			 * errorList.put( "CATEGORY of borrower is mandatory !!", row
			 * .getCell(2).getStringCellValue()); }
			 * 
			 * }
			 */

		} else {

			if (row.getCell(38).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (c02 == null || c02.getCellType() == Cell.CELL_TYPE_BLANK) {
					errorList.put("Invalid CATEGORY !!", row.getCell(3)
							.getStringCellValue());
				} else {
					if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

						errorList.put("Invalid CATEGORY of borrower!!", Long
								.toString((long) row.getCell(3)
										.getNumericCellValue()));
					} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
						errorList.put("Invalid CATEGORY of borrower!!", row
								.getCell(3).getStringCellValue());
					}

				}

			} else if (row.getCell(38).getCellType() == Cell.CELL_TYPE_STRING) {

				if (dataValidation.CategoryValidator(
						row.getCell(38).getStringCellValue()).equals("success")) {
				} else {
					if (c02 == null
							|| c02.getCellType() == Cell.CELL_TYPE_BLANK) {
						errorList.put(dataValidation.CategoryValidator(row
								.getCell(38).getStringCellValue()), "");
					} else {

						if (row.getCell(3).getCellType() == Cell.CELL_TYPE_NUMERIC) {

							errorList.put(dataValidation.CategoryValidator(row
									.getCell(38).getStringCellValue()), Long
									.toString((long) row.getCell(3)
											.getNumericCellValue()));
						} else if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
							errorList.put(dataValidation.CategoryValidator(row
									.getCell(38).getStringCellValue()), row
									.getCell(3).getStringCellValue());
						}
					}

				}

			}
		}
		// else if (row.getCell(37).getCellType() == Cell.CELL_TYPE_BLANK) {}
		/* Added four fields */
		/* For Partial Security */
		return errorList;

	}

	public String dataValidate(XSSFRow row, MLIMakerFileUploadBean excelFile)
			throws Exception {
		if (row.getCell(0).getStringCellValue().isEmpty()) {

			msgFlage = "Loan Type is Required !!";

		} else if (!row.getCell(0).getStringCellValue().isEmpty()) {

			if (dataValidation.loanTypeValidator(
					row.getCell(0).getStringCellValue()).equals("success")) {

				if (row.getCell(1).getStringCellValue().isEmpty()) {

					msgFlage = "Business Product is Required !!";

				} else if (!row.getCell(1).getStringCellValue().isEmpty()) {

					if (dataValidation.productTypeValidator(
							row.getCell(1).getStringCellValue()).equals(
							"success")) {

						if (row.getCell(2).getStringCellValue().isEmpty()) {
							msgFlage = "Appl Ref No is Required !!";

						} else if (row.getCell(3).getStringCellValue()
								.isEmpty()) {

							msgFlage = "Constitution is Required !!";
						}

						else if (!row.getCell(3).getStringCellValue().isEmpty()) {

							if (dataValidation.constitutionValidator(
									row.getCell(3).getStringCellValue())
									.equals("success")) {

								if (row.getCell(4).getStringCellValue()
										.isEmpty()) {
									msgFlage = "MSE Name is Required !!";
								} else if (row.getCell(5).getDateCellValue() == null) {
									msgFlage = "Sanction Date is Required !!";
								} else if (row.getCell(5).getDateCellValue() != null) {
									msgFlage = dataValidation
											.sanctionDateValidator(row.getCell(
													5).getDateCellValue());

									if (dataValidation.sanctionDateValidator(
											row.getCell(5).getDateCellValue())
											.equals("success")) {
										if (row.getCell(6)
												.getNumericCellValue() == 0) {
											msgFlage = "Sanctioned Amount is Required !!";
										} else if (row.getCell(6)
												.getNumericCellValue() != 0) {

											if (dataValidation
													.sanctiondAmountValidator(
															(int) row
																	.getCell(6)
																	.getNumericCellValue())
													.equals("success")) {

												if (row.getCell(7)
														.getDateCellValue() == null) {
													msgFlage = "First Disbursement Date is Required !!";
												} else if (row.getCell(7)
														.getDateCellValue() != null) {
													if (dataValidation
															.firstDisbursementDateValidator(
																	row.getCell(
																			7)
																			.getDateCellValue(),
																	row.getCell(
																			5)
																			.getDateCellValue())
															.equals("success")) {

														if (row.getCell(8)
																.getNumericCellValue() == 0) {
															msgFlage = "Interest Rate is Required !!";
														} else if (row
																.getCell(8)
																.getNumericCellValue() != 0) {

															if (dataValidation
																	.interestRateValidator(
																			(int) row
																					.getCell(
																							8)
																					.getNumericCellValue())
																	.equals("success")) {

																if (row.getCell(
																		9)
																		.getStringCellValue()
																		.isEmpty()) {
																	msgFlage = "Micro/Small is Required !!";
																} else if (!row
																		.getCell(
																				9)
																		.getStringCellValue()
																		.isEmpty()) {

																	if (dataValidation
																			.microSmallValidator(
																					row.getCell(
																							9)
																							.getStringCellValue())
																			.equals("success")) {

																		if (row.getCell(
																				10)
																				.getStringCellValue()
																				.isEmpty()) {
																			msgFlage = "MSE Address is Required !!";
																		} else if (row
																				.getCell(
																						11)
																				.getStringCellValue()
																				.isEmpty()) {
																			msgFlage = "City is Required !!";
																		} else if (row
																				.getCell(
																						12)
																				.getStringCellValue()
																				.isEmpty()) {
																			msgFlage = "District is Required !!";
																		} else if (row
																				.getCell(
																						13)
																				.getNumericCellValue() == 0) {
																			msgFlage = "Pincode is Required !!";
																		} else if (row
																				.getCell(
																						14)
																				.getStringCellValue()
																				.isEmpty()) {
																			msgFlage = "State is Required !!";
																		} else if (row
																				.getCell(
																						15)
																				.getStringCellValue()
																				.isEmpty()) {
																			msgFlage = "ITPan is Required !!";
																		} else if (row
																				.getCell(
																						16)
																				.getStringCellValue()
																				.isEmpty()) {
																			msgFlage = "Udyog Aadhar No is Required !!";
																		} else if (row
																				.getCell(
																						17)
																				.getStringCellValue()
																				.isEmpty()) {
																			msgFlage = "Industry Nature is Required !!";
																		} else if (!row
																				.getCell(
																						17)
																				.getStringCellValue()
																				.isEmpty()) {

																			if (dataValidation
																					.industryNatureValidator(
																							row.getCell(
																									17)
																									.getStringCellValue())
																					.equals("success")) {
																				if (row.getCell(
																						18)
																						.getStringCellValue()
																						.isEmpty()) {
																					msgFlage = "Industry Sector is Required !!";
																				} else if (row
																						.getCell(
																								19)
																						.getNumericCellValue() == 0) {
																					msgFlage = "No Of Employees is Required !!";
																				} else if (row
																						.getCell(
																								19)
																						.getNumericCellValue() != 0) {

																					if (dataValidation
																							.numberOfEmployeesValidator(
																									(int) row
																											.getCell(
																													19)
																											.getNumericCellValue())
																							.equals("success")) {
																						if (row.getCell(
																								20)
																								.getNumericCellValue() == 0) {
																							msgFlage = "Projected Sales is Required !!";
																						} else if (row
																								.getCell(
																										21)
																								.getNumericCellValue() == 0) {
																							msgFlage = "Projected Exports is Required !!";
																						} else if (row
																								.getCell(
																										22)
																								.getStringCellValue()
																								.isEmpty()) {
																							msgFlage = "New/Existing Unit is Required !!";
																						} else if (!row
																								.getCell(
																										22)
																								.getStringCellValue()
																								.isEmpty()) {

																							if (dataValidation
																									.newExistingValidator(
																											row.getCell(
																													22)
																													.getStringCellValue())
																									.equals("success")) {

																								if (row.getCell(
																										23)
																										.getStringCellValue()
																										.isEmpty()) {
																									msgFlage = "Unbanked Customer is Required !!";
																								} else if (!row
																										.getCell(
																												23)
																										.getStringCellValue()
																										.isEmpty()) {

																									if (dataValidation
																											.unbankedCustomerValidator(
																													row.getCell(
																															23)
																															.getStringCellValue())
																											.equals("success")) {
																										if (row.getCell(
																												24)
																												.getStringCellValue()
																												.isEmpty()) {
																											msgFlage = "New Customer is Required !!";
																										} else if (!row
																												.getCell(
																														24)
																												.getStringCellValue()
																												.isEmpty()) {

																											if (dataValidation
																													.newCustomerValidator(
																															row.getCell(
																																	24)
																																	.getStringCellValue(),
																															row.getCell(
																																	23)
																																	.getStringCellValue())
																													.equals("success")) {
																												if (row.getCell(
																														25)
																														.getStringCellValue()
																														.isEmpty()) {
																													msgFlage = "Chief Promater Name is Required !!";
																												} else if (row
																														.getCell(
																																26)
																														.getStringCellValue()
																														.isEmpty()) {
																													msgFlage = "Minority Community is Required !!";
																												} else if (!row
																														.getCell(
																																26)
																														.getStringCellValue()
																														.isEmpty()) {

																													if (dataValidation
																															.minorityCommunityValidator(
																																	row.getCell(
																																			26)
																																			.getStringCellValue())
																															.equals("success")) {

																														if (row.getCell(
																																27)
																																.getStringCellValue()
																																.isEmpty()) {
																															msgFlage = "Handicapped is Required !!";
																														} else if (!row
																																.getCell(
																																		27)
																																.getStringCellValue()
																																.isEmpty()) {

																															if (dataValidation
																																	.handicappedValidator(
																																			row.getCell(
																																					27)
																																					.getStringCellValue())
																																	.equals("success")) {

																																if (row.getCell(
																																		28)
																																		.getStringCellValue()
																																		.isEmpty()) {
																																	msgFlage = "Women field is Required !!";
																																} else if (!row
																																		.getCell(
																																				28)
																																		.getStringCellValue()
																																		.isEmpty()) {
																																	if (dataValidation
																																			.womenValidator(
																																					row.getCell(
																																							28)
																																							.getStringCellValue())
																																			.equals("success")) {

																																		if (row.getCell(
																																				29)
																																				.getStringCellValue()
																																				.isEmpty()) {
																																			msgFlage = "Category is Required !!";
																																		} else if (!row
																																				.getCell(
																																						29)
																																				.getStringCellValue()
																																				.isEmpty()) {

																																			if (dataValidation
																																					.CategoryValidator(
																																							row.getCell(
																																									29)
																																									.getStringCellValue())
																																					.equals("success")) {
																																				if (row.getCell(
																																						30)
																																						.getStringCellValue()
																																						.isEmpty()) {
																																					msgFlage = "SSI Registration No. is Required !!";
																																				} else if (row
																																						.getCell(
																																								31)
																																						.getStringCellValue()
																																						.isEmpty()) {
																																					msgFlage = "Promoter IT-PAN is Required !!";
																																				} else if (row
																																						.getCell(
																																								32)
																																						.getNumericCellValue() == 0) {
																																					msgFlage = "TENURE is Required !!";
																																				}

																																			} else {
																																				msgFlage = dataValidation
																																						.CategoryValidator(row
																																								.getCell(
																																										29)
																																								.getStringCellValue());
																																			}
																																		}
																																	} else {
																																		msgFlage = dataValidation
																																				.womenValidator(row
																																						.getCell(
																																								28)
																																						.getStringCellValue());

																																	}
																																}
																															} else {
																																msgFlage = dataValidation
																																		.handicappedValidator(row
																																				.getCell(
																																						27)
																																				.getStringCellValue());

																															}
																														}

																													} else {
																														msgFlage = dataValidation
																																.minorityCommunityValidator(row
																																		.getCell(
																																				26)
																																		.getStringCellValue());
																													}
																												}

																											} else {
																												msgFlage = dataValidation
																														.newCustomerValidator(
																																row.getCell(
																																		24)
																																		.getStringCellValue(),
																																row.getCell(
																																		23)
																																		.getStringCellValue());
																											}
																										}

																									} else {
																										msgFlage = dataValidation
																												.unbankedCustomerValidator(row
																														.getCell(
																																23)
																														.getStringCellValue());
																									}
																								}
																							} else {
																								msgFlage = dataValidation
																										.newExistingValidator(row
																												.getCell(
																														22)
																												.getStringCellValue());
																							}
																						}

																					} else {
																						msgFlage = dataValidation
																								.numberOfEmployeesValidator((int) row
																										.getCell(
																												19)
																										.getNumericCellValue());
																					}
																				}

																			} else {
																				msgFlage = dataValidation
																						.industryNatureValidator(row
																								.getCell(
																										17)
																								.getStringCellValue());
																			}
																		}
																	} else {
																		msgFlage = dataValidation
																				.microSmallValidator(row
																						.getCell(
																								9)
																						.getStringCellValue());
																	}
																}
															} else {

																msgFlage = dataValidation
																		.interestRateValidator((int) row
																				.getCell(
																						8)
																				.getNumericCellValue());
															}
														}

													} else {
														msgFlage = dataValidation
																.firstDisbursementDateValidator(
																		row.getCell(
																				7)
																				.getDateCellValue(),
																		row.getCell(
																				5)
																				.getDateCellValue());

													}
												}
											} else {
												msgFlage = dataValidation
														.sanctiondAmountValidator((int) row
																.getCell(6)
																.getNumericCellValue());
											}
										}

									} else {

									}
								}

							} else {
								msgFlage = dataValidation
										.constitutionValidator(row.getCell(3)
												.getStringCellValue());
							}
						}
					} else {
						msgFlage = dataValidation.productTypeValidator(row
								.getCell(1).getStringCellValue());
					}

				}

			} else {
				msgFlage = dataValidation.loanTypeValidator(row.getCell(0)
						.getStringCellValue());

			}

		}

		else {
			msgFlage = "success";
		}
		return msgFlage;

	}

	public String dataValidated(XSSFRow row, MLIMakerFileUploadBean excelFile)
			throws Exception {

		if (!row.getCell(0).getStringCellValue().isEmpty()) {

			msgFlage = "Loan Type is Required !!";

		} else if (row.getCell(0).getStringCellValue().isEmpty()) {

			msgFlage = "Loan Type is Required !!";

		} else if (row.getCell(1).getStringCellValue().isEmpty()) {

			msgFlage = "Business Product is Required !!";

		} else if (row.getCell(2).getStringCellValue().isEmpty()) {
			msgFlage = "Appl Ref No is Required !!";

		} else if (row.getCell(3).getStringCellValue().isEmpty()) {

			msgFlage = "Constitution is Required !!";
		} else if (row.getCell(4).getStringCellValue().isEmpty()) {
			msgFlage = "MSE Name is Required !!";
		} else if (row.getCell(5).getDateCellValue() == null) {
			msgFlage = "Sanction Date is Required !!";
		} else if (row.getCell(6).getNumericCellValue() == 0) {
			msgFlage = "Sanctioned Amount is Required !!";
		} else if (row.getCell(7).getDateCellValue() == null) {
			msgFlage = "First Disbursement Date is Required !!";
		} else if (row.getCell(8).getNumericCellValue() == 0) {
			msgFlage = "Interest Rate is Required !!";
		} else if (row.getCell(9).getStringCellValue().isEmpty()) {
			msgFlage = "Micro/Small is Required !!";
		} else if (row.getCell(10).getStringCellValue().isEmpty()) {
			msgFlage = "MSE Address is Required !!";
		} else if (row.getCell(11).getStringCellValue().isEmpty()) {
			msgFlage = "City is Required !!";
		} else if (row.getCell(12).getStringCellValue().isEmpty()) {
			msgFlage = "District is Required !!";
		} else if (row.getCell(13).getNumericCellValue() == 0) {
			msgFlage = "Pincode is Required !!";
		} else if (row.getCell(14).getStringCellValue().isEmpty()) {
			msgFlage = "State is Required !!";
		} else if (row.getCell(15).getStringCellValue().isEmpty()) {
			msgFlage = "ITPan is Required !!";
		} else if (row.getCell(16).getStringCellValue().isEmpty()) {
			msgFlage = "Udyog Aadhar No is Required !!";
		} else if (row.getCell(17).getStringCellValue().isEmpty()) {
			msgFlage = "Industry Nature is Required !!";
		} else if (row.getCell(18).getStringCellValue().isEmpty()) {
			msgFlage = "Industry Sector is Required !!";
		} else if (row.getCell(19).getNumericCellValue() == 0) {
			msgFlage = "No Of Employees is Required !!";
		} else if (row.getCell(20).getNumericCellValue() == 0) {
			msgFlage = "Projected Sales is Required !!";
		} else if (row.getCell(21).getNumericCellValue() == 0) {
			msgFlage = "Projected Exports is Required !!";
		} else if (row.getCell(22).getStringCellValue().isEmpty()) {
			msgFlage = "New/Existing Unit is Required !!";
		} else if (row.getCell(23).getStringCellValue().isEmpty()) {
			msgFlage = "Unbanked Customer is Required !!";
		} else if (row.getCell(24).getStringCellValue().isEmpty()) {
			msgFlage = "New Customer is Required !!";
		} else if (row.getCell(25).getStringCellValue().isEmpty()) {
			msgFlage = "Chief Promater Name is Required !!";
		} else if (row.getCell(26).getStringCellValue().isEmpty()) {
			msgFlage = "Minority Community is Required !!";
		} else if (row.getCell(27).getStringCellValue().isEmpty()) {
			msgFlage = "Handicapped is Required !!";
		} else if (row.getCell(28).getStringCellValue().isEmpty()) {
			msgFlage = "Women field is Required !!";
		} else if (row.getCell(29).getStringCellValue().isEmpty()) {
			msgFlage = "Category is Required !!";
		} else if (row.getCell(30).getStringCellValue().isEmpty()) {
			msgFlage = "SSI Registration No. is Required !!";
		} else if (row.getCell(31).getStringCellValue().isEmpty()) {
			msgFlage = "Promoter IT-PAN is Required !!";
		} else if (row.getCell(32).getNumericCellValue() == 0) {
			msgFlage = "TENURE is Required !!";
		}
		msgFlage = "success";
		return msgFlage;

	}

	public void procesStatus(String departEmailId)

	{

		String qrykey = null;
		String qryValue = null;
		try {

			String subject = "Respose to your Query from CGTMSE";
			String mailBody = (new StringBuilder("Your Ticket/Query  Number '"))
					.append(qrykey)
					.append("' Raised to CGTMSE has been Resolved.\n")
					.append(qryValue).append("\n Please check in the System. ")
					.toString();
			String filePath = "";
			String host = "192.168.10.118";
			boolean sessionDebug = false;
			Properties props = System.getProperties();
			props.put("mail.host", host);
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", host);
			props.put("mail.from", "administrator@cgtmse.in");
			Session session1 = null;
			session1 = Session.getDefaultInstance(props, null);
			session1.setDebug(sessionDebug);
			MimeMessage msg = new MimeMessage(session1);
			msg.setFrom(new InternetAddress("administrator@cgtmse.in"));
			InternetAddress Toaddress[] = { new InternetAddress(departEmailId) };
			msg.setRecipients(javax.mail.Message.RecipientType.TO, Toaddress);
			msg.setRecipients(javax.mail.Message.RecipientType.TO, Toaddress);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			if (filePath.equals(""))
				msg.setText(mailBody);
			Transport.send(msg);

		} catch (Exception e) {
			e.printStackTrace();
		}
		Exception exception;
		return;
	}

	public Date dateFormateChange(Date date) throws Exception {

		SimpleDateFormat f2 = new SimpleDateFormat("dd MMMM, yyyy"); // MMMM for
																		// full
																		// month
																		// //
																		// name
		String d1 = f2.format(date);
		Date date1 = new SimpleDateFormat("dd MMMM, yyyy").parse(d1);
		return date1;
	}

	public boolean districtMatching() {
		boolean fleg = true;

		return fleg;
	}
}
