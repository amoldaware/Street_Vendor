package com.nbfc.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import com.ibm.icu.util.Calendar;
import com.nbfc.model.DistrictName;
import com.nbfc.model.PortfolioValidate;
import com.nbfc.model.StateName;
import com.nbfc.service.MLIValidatorService;

public class DataValidation {

	static String message = null;
	double sanctionValue;
	private Pattern pattern;
	private Matcher matcher;
	private static boolean flag;
	Map<String, String> distMap = new HashMap<String, String>();
	Map<String, String> stateMap = new HashMap<String, String>();
	Map<String, String> filteredStateCodeMap = new HashMap<String, String>();
	private String stateCode = null;

	public DataValidation() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public String loanTypeValidator(String loanType) {

		if (loanType.equalsIgnoreCase("WCTL")) {
			message = "success";
		} else if (loanType.equalsIgnoreCase("TL")) {
			message = "success";
		} else if (loanType.equalsIgnoreCase("FBL")) {
			message = "success";
		} else if (loanType.equalsIgnoreCase("NBFL")) {
			message = "success";
		} else {
			message = "Invalid Loan Type !!";
		}
		return message;
	}

	public double portfolioSizeValidator(XSSFRow row) {
		Cell cell = row.getCell(12);
		if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {

		} else if (cell == null || cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			sanctionValue = (double) row.getCell(12).getNumericCellValue();
		} else if (cell == null || cell.getCellType() == Cell.CELL_TYPE_STRING) {
		}
		return sanctionValue;
	}

	public boolean sanctionedPortfolioSizeValidator(double allSanctionAmount,
			String portfolioMaxSize) {
		try {
			long pMaxSize = Long.parseLong(portfolioMaxSize);
			if (allSanctionAmount <= pMaxSize) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public String mobileNumberValidator(long mobileNumber) {

		if ((long) (Math.log10(mobileNumber) + 1) == 10) {
			message = "success";
		} else if ((long) (Math.log10(mobileNumber) + 1) <= 10) {

			message = "Mobile Number Should not be less than 10 Digits";
		} else {
			message = "Mobile Number Should not be greater than 10 Digits";

		}
		return message;
	}

	public String emailIdValidator(String emailId) {

		matcher = pattern.matcher(emailId);
		matcher.matches();
		if (matcher.matches() == true) {
			message = "success";
		} else {
			message = "Invalid CHIEF PROMOTER'S MAIL ID !!";
		}
		return message;
	}

	public String productTypeValidator(String productType) {
		message = "success";
		/*
		 * if (productType.equalsIgnoreCase("Machine Loan")) { message =
		 * "success"; } else if (productType.equalsIgnoreCase("Business Loan"))
		 * { message = "success"; } else if
		 * (productType.equalsIgnoreCase("Industrial Property Loan")) { message
		 * = "success"; } else if
		 * (productType.equalsIgnoreCase("Working Capital Loan")) { message =
		 * "success"; } else if
		 * (productType.equalsIgnoreCase("Commercial Vihicle Loan")) { message =
		 * "success"; } else {
		 * 
		 * message = "Invalid Business Product !!"; }
		 */
		return message;
	}

	public String duplicateLoanAccChecker(String loanAccNo,
			List<PortfolioValidate> listLoneno) {

		for (PortfolioValidate listLon : listLoneno) {
			// System.out.println(listLon.getLoan_account_no());
			if (listLon.getLoan_account_no().equals(loanAccNo)) {
				message = "Duplicate Loan Account Number !!";
			} else {

				message = "success";
			}

		}

		return message;
	}

	public String duplicateLoanAccCheckerNum(long loanAccNo,
			List<PortfolioValidate> listLoneno) {

		ArrayList loanArrlist = new ArrayList();

		for (PortfolioValidate listLon : listLoneno) {
			// System.out.println(listLon.getLoan_account_no());

			loanArrlist.add(listLon.getLoan_account_no());

			if (listLon.getLoan_account_no().equals(Long.toString(loanAccNo))) {
				message = "Duplicate Loan Account Number !!";
			}

		}

		if (loanArrlist.contains(listLoneno)) {
			System.out.println("Duplicate");
			message = "Duplicate Loan Account Number !!";
		}
		;

		return message;
	}

	public String constitutionValidator(String constitution) {

		if (constitution.equalsIgnoreCase("PROPRIETORY")) {
			message = "success";
		} else if (constitution.equalsIgnoreCase("PARTNERSHIP")) {
			message = "success";
		} else if (constitution
				.equalsIgnoreCase("LIMITED LIABILITY PARTNERSHIP")) {
			message = "success";
		} else if (constitution.equalsIgnoreCase("PRIVATE LTD")) {
			message = "success";
		} else if (constitution.equalsIgnoreCase("PUBLIC")) {
			message = "success";
		} else if (constitution.equalsIgnoreCase("HUF")) {
			message = "success";
		} else if (constitution.equalsIgnoreCase("TRUST")) {
			message = "success";
		} else if (constitution.equalsIgnoreCase("SOCIETY/COOP SOCIETY")) {
			message = "success";
		} else if (constitution.equalsIgnoreCase("INDIVIDUAL")) {
			message = "success";
		} else {

			message = "Invalid Constitution detail !!";
		}
		return message;
	}

	public String sanctionDateValidator(Date sanctionDate)
			throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date myDate = sdf.parse("01-01-2017");

		String currentDate = sdf.format(new Date());
		Date sysDate = sdf.parse(currentDate);

		System.out.println("Sanction Date : " + sanctionDate);
		System.out.println("Current Date " + sysDate);

		if (sanctionDate.compareTo(myDate) > 0
				&& sanctionDate.compareTo(sysDate) > 0) {
			message = "Sanction Date cannot be Greater than Current Date !!";
		} else if (sanctionDate.compareTo(myDate) == 0) {
			message = "success";
		} else if (sanctionDate.compareTo(myDate) < 0) {
			message = "Sanction Date Should not be Less than '01-01-2017' ";
		} else if (sanctionDate.compareTo(myDate) > 0
				&& sanctionDate.compareTo(sysDate) < 0) {
			message = "success";
		} else if (sanctionDate.compareTo(sysDate) == 0) {
			message = "success";
		}
		return message;
	}

	public String firstDisbursementDateValidator(Date firstDisbursementDate,
			Date sanctionDate) throws ParseException {

		SimpleDateFormat currentDateFormate = new SimpleDateFormat("dd-MM-yyyy");
		String currentDate = currentDateFormate.format(new Date());
		Date sysDate = currentDateFormate.parse(currentDate);

		if (firstDisbursementDate.compareTo(sanctionDate) > 0
				&& firstDisbursementDate.compareTo(sysDate) < 0) {
			System.out
					.println("firstDisbursementDate greater than sanction date but less than sysDate ");
			message = "success";
		} else if (firstDisbursementDate.compareTo(sanctionDate) > 0
				&& firstDisbursementDate.compareTo(sysDate) > 0) {

			message = "First Disburesement Date should not be greater than Current Date !! ";
		} else if (firstDisbursementDate.compareTo(sanctionDate) == 0) {
			System.out.println("firstDisbursementDate equal Sanction date");
			message = "success";
		}

		else if (firstDisbursementDate.compareTo(sanctionDate) < 0) {
			System.out
					.println("firstDisbursementDate less than Senction date !!");
			message = "First Disbursement Date cannot not be prior to Sanction Date !!";
		} else if (firstDisbursementDate.compareTo(sysDate) == 0) {
			System.out
					.println("firstDisbursementDate equal to current date !!");
			message = "success";
		}

		return message;
	}

	public String sanctiondAmountValidator(int sanctiondAmount) {

		/*
		 * if (sanctiondAmount <= 20000000 && sanctiondAmount >= 1000000) {
		 * message = "success"; } else if (sanctiondAmount < 1000000) { message
		 * = "Sanctioned Amount should not be Less than '1000000' !! "; } else {
		 * 
		 * message =
		 * "Sanctioned Amount should not be Greater than '20000000' !! "; }
		 */

		if (sanctiondAmount <= 20000000 && sanctiondAmount > 0) {
			message = "success";
		} else if (sanctiondAmount == 0) {
			message = "Sanctioned Amount should  be Greater than '0' !! ";
		} else {

			message = "Sanctioned Amount should not be Greater than '20000000' !! ";
		}

		return message;
	}

	public String guarenteeAmountValidator(int guarenteeAmount) {

		/*
		 * if (sanctiondAmount <= 20000000 && sanctiondAmount >= 1000000) {
		 * message = "success"; } else if (sanctiondAmount < 1000000) { message
		 * = "Sanctioned Amount should not be Less than '1000000' !! "; } else {
		 * 
		 * message =
		 * "Sanctioned Amount should not be Greater than '20000000' !! "; }
		 */

		if (guarenteeAmount <= 10000000 && guarenteeAmount > 0) {
			message = "success";
		} else if (guarenteeAmount == 0) {
			message = "Guarantee Amount should  be Greater than '0' !! ";
		} else {

			message = "Guarantee Amount should not be Greater than '10000000' !! ";
		}

		return message;
	}

	public String guarenteeAmountValidatorOnY(int guarenteeAmount) {

		if (guarenteeAmount <= 20000000 && guarenteeAmount > 0) {
			message = "success";
		} else if (guarenteeAmount == 0) {
			message = "Guarantee Amount should  be Greater than '0' !! ";
		} else {

			message = "Guarantee Amount should not be Greater than '20000000' !!";
		}

		return message;
	}

	public String retailTradeAmountValidator(int sanctiondAmount) {

		/*
		 * if (sanctiondAmount <= 20000000 && sanctiondAmount >= 1000000) {
		 * message = "success"; } else if (sanctiondAmount < 1000000) { message
		 * = "Sanctioned Amount should not be Less than '1000000' !! "; } else {
		 * 
		 * message =
		 * "Sanctioned Amount should not be Greater than '20000000' !! "; }
		 */

		if (sanctiondAmount <= 20000000 && sanctiondAmount > 0) {
			message = "success";
		} else if (sanctiondAmount == 0) {
			message = "Sanctioned Amount should  be Greater than '0' !! ";
		} else {

			message = "Sanctioned Amount should not be Greater than '20000000' !! ";
		}

		return message;
	}

	public String sansanAmountAmountValidator(int sanctiondAmount) {

		/*
		 * if (sanctiondAmount <= 20000000 && sanctiondAmount >= 1000000) {
		 * message = "success"; } else if (sanctiondAmount < 1000000) { message
		 * = "Sanctioned Amount should not be Less than '1000000' !! "; } else {
		 * 
		 * message =
		 * "Sanctioned Amount should not be Greater than '20000000' !! "; }
		 */

		if (sanctiondAmount <= 500000 && sanctiondAmount > 0) {
			message = "success";
		} else {

			message = "If Outstanding Amount Greater than 500000 then 'CHIEF PROMOTER IT-PAN'or ADDHAR NUMBER is Mandatory !!";
		}

		return message;
	}

	public String aadharNumberValidation(long udyogAadharNumber) {

		/*
		 * if (sanctiondAmount <= 20000000 && sanctiondAmount >= 1000000) {
		 * message = "success"; } else if (sanctiondAmount < 1000000) { message
		 * = "Sanctioned Amount should not be Less than '1000000' !! "; } else {
		 * 
		 * message =
		 * "Sanctioned Amount should not be Greater than '20000000' !! "; }
		 */

		if (String.valueOf(udyogAadharNumber).length() >= 12) {
			message = "success";
		} else {

			message = "Invalid  'AADHAR NUMBER' !!";
		}

		return message;
	}

	public String pincodeValidator(int pincode) {

		if ((int) (Math.log10(pincode) + 1) == 6) {
			message = "success";
		} else {

			message = "Pincode Should Be 6 Digits !! ";
		}

		return message;
	}

	public String interestRateValidator(float interestRate) {

		if (interestRate <= 99.00 && interestRate != 00.00) {

			message = "success";

		} else if (interestRate == 00.00) {

			message = "Interest Rate Should not be '0' !!";

		} else {
			message = "Interest Rate Should be Less than OR equal to 99.00% !!";
		}
		return message;
	}

	public String tenorInMonthValidator(int tenorInMonth) {
		if (tenorInMonth == 0 && tenorInMonth < 12) {
			message = "'Tenure in Month' Should not be less than 12 months !!";
		} else {
			message = "success";
		}
		return message;
	}

	public String microSmallValidator(String microSmall) {

		if (microSmall.equalsIgnoreCase("Micro")) {
			message = "success";
		} else if (microSmall.equalsIgnoreCase("Small")) {
			message = "success";
		} else {
			message = "WHETHER THE ASSISTED UNIT IS MICRO/SMALL ? status Should Be 'MICRO' OR 'SMALL' !!";
		}
		return message;
	}

	public String industryNatureValidator(String industryNature) {

		if (industryNature.equalsIgnoreCase("MANUFACTURING")) {
			message = "success";
		} else if (industryNature.equalsIgnoreCase("SERVICES")) {
			message = "success";
		} else {
			message = "INDUSTRY NATURE Should Be 'Manufacturing' OR 'Services' !!";
		}
		return message;
	}

	public String industrySectorValidator(String industrySector) {/*
																 * 
																 * if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Mining and quarrying"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Manufacture of food products"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Manufacture of beverages"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Manufacture of tobacco products"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Manufacture of textiles"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Manufacture of wearing apparel"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Manufacture of leather and related products"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Manufacture of wood and products of wood and cork, except furniture; manufacture of articles of straw and plaiting materials"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Manufacture of paper and paper products"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Printing and reproduction of recorded media"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Manufacture of coke and refined petroleum products"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Manufacture of chemicals and chemical products"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Manufacture of pharmaceuticals, medicinal chemical and botanical products"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Manufacture of rubber and plastics products"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Manufacture of other non-metallic mineral products"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Manufacture of basic metals"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Manufacture of fabricated metal products, except machinery and equipment"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Manufacture of computer, electronic and optical products"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Manufacture of electrical equipment"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Manufacture of machinery and equipment n.e.c."
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Manufacture of motor vehicles, trailers and semi-trailers"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Manufacture of other transport equipment"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Manufacture of furniture"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Other manufacturing"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Repair and installation of machinery and equipment"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Electricity, gas, steam and air conditioning supply"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Water supply; sewerage, waste management and remediation activities"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Construction of buildings"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Civil engineering"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Specialized construction activities"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Land transport and transport via pipelines"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Water transport"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Air transport"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Warehousing and support activities for transportation"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Postal and courier activities"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Accommodation (Hotels, Resorts etc)"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Food and beverage service activities (Restaurants, Catering etc)"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Publishing activities"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Motion picture, video and television programme production, sound recording and music publishing activities"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Broadcasting and programming activities"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Telecommunications"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Computer programming, consultancy and related activities"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Information service activities"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Financial service activities, except insurance and pension funding"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Insurance, reinsurance and pension funding, except compulsory social security"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Other financial activities"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Real estate activities"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Legal and accounting activities"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Activities of head offices; management consultancy activities"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Architecture and engineering activities; technical testing and analysis"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Scientific research and development"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Advertising and market research"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Other professional, scientific and technical activities"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Veterinary activities"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Rental and leasing activities"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Employment activities"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Travel agency, tour operator and other reservation service activities"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Security and investigation activities"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Services to buildings and landscape activities"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Office administrative, office support and other business support activities"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Human health activities (Hospitals etc)"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Residential care activities (Nursing care facilities, Residential care activities etc)"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Social work activities without accommodation "
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Creative, arts and entertainment activities"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Libraries, archives, museums and other cultural activities"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Sports activities and amusement and recreation activities"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Repair of computers and personal and household goods"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * (
																 * "Other personal service activities (washing, dry cleaning, hairdressing & other beauty treatments etc)"
																 * )) { message
																 * = "success";
																 * } else if
																 * (industrySector
																 * .
																 * equalsIgnoreCase
																 * ("SRTO")) {
																 * message =
																 * "success"; }
																 * 
																 * else {
																 * message =
																 * "Invalid industry sector !!"
																 * ; }
																 */
		message = "success";
		return message;
	}

	public String numberOfEmployeesValidator(int numberOfEmployees) {
		if (numberOfEmployees >= 1) {
			message = "success";
		} else {
			message = "No. Of Employees Should Be Equal OR Greater than 1 !!";
		}
		return message;
	}

	public String newExistingValidator(String newExisting) {

		if (newExisting.equalsIgnoreCase("New")) {
			message = "success";
		} else if (newExisting.equalsIgnoreCase("Existing")) {
			message = "success";
		} else {
			message = "NEW/EXISTING Status Should Be New OR Existing !!";
		}
		return message;
	}

	public String unbankedCustomerValidator(String unbankedCustomer) {

		if (unbankedCustomer.equalsIgnoreCase("Y")) {
			message = "success";
		} else if (unbankedCustomer.equalsIgnoreCase("N")) {
			message = "success";
		} else {
			message = "CUSTOMER HAVING ANY PREVIOUS BANKING EXPERIENCE Status Should Be 'Y' OR 'N' !!";
		}
		return message;
	}

	public String newCustomerValidator(String newCustomer,
			String unbankedCustomer) {

		if (unbankedCustomer.equalsIgnoreCase("Y")) {
			if (newCustomer.equalsIgnoreCase("Y")) {
				message = "success";
			} else if (newCustomer.equalsIgnoreCase("N")) {
				message = "success";
			} else {
				message = "'NEW CUSTOMER' Status Should Be 'Y' OR 'N' !!";
			}
		} else if (unbankedCustomer.equalsIgnoreCase("N")) {

			if (newCustomer.equalsIgnoreCase("Y")) {
				message = "success";
			} else if (newCustomer.equalsIgnoreCase("N")) {
				message = "If CUSTOMER  HAVING ANY PREVIOUS BANKING EXPERIENCE Status is 'N' Then NEW Customer Status Should Be 'Y' !! ";
			} else {
				message = "'NEW CUSTOMER' status Should Be 'Y' OR 'N' !!";
			}

		}

		return message;
	}

	public String newCustomerDISBURSEMENTValidator(String DISBURSEMENT,
			Date disbursementDate) {

		if (DISBURSEMENT.equalsIgnoreCase("Y")) {
			if (disbursementDate != null) {
				message = "success";
			} else {
				message = "WHETHER DISBURSED OR NOT is 'Y' Then DISBURSMENT DATE Should Be Given !! ";
			}
		} else if (DISBURSEMENT.equalsIgnoreCase("N")) {
			message = "success";
		}
		return message;
	}

	public String minorityCommunityValidator(String minorityCommunity) {

		if (minorityCommunity.equalsIgnoreCase("Y")) {
			message = "success";
		} else if (minorityCommunity.equalsIgnoreCase("N")) {
			message = "success";
		} else {
			message = "Minority Community Status should Be 'Y' OR 'N' !!";
		}
		return message;
	}

	public String handicappedValidator(String handicapped) {

		if (handicapped.equalsIgnoreCase("Y")) {
			message = "success";
		} else if (handicapped.equalsIgnoreCase("N")) {
			message = "success";
		} else {
			message = "HANDICAPPED Status Should Be 'Y' OR 'N' !!";
		}
		return message;
	}

	public String partialSecurityFlag(String flag) {

		if (flag.equalsIgnoreCase("Y")) {
			message = "success";
		} else if (flag.equalsIgnoreCase("N")) {
			message = "success";
		} else {
			message = "Partial Security Status Should Be 'Y' OR 'N' !!";
		}
		return message;
	}

	public String womenValidator(String women) {

		if (women.equalsIgnoreCase("Y")) {
			message = "success";
		} else if (women.equalsIgnoreCase("N")) {
			message = "success";
		} else {
			message = "WOMEN Detail Should Be 'Y' OR 'N' !!";
		}
		return message;
	}

	public String CategoryValidator(String category) {

		if (category.equalsIgnoreCase("GENERAL")) {
			message = "success";
		} else if (category.equalsIgnoreCase("SC")) {
			message = "success";
		} else if (category.equalsIgnoreCase("ST")) {
			message = "success";
		} else if (category.equalsIgnoreCase("OBC")) {
			message = "success";
		} else {
			message = "Invalid Category of borrower !!";
		}
		return message;
	}

	public String aadharNumberValidator(String aadharNumber) {

		if (aadharNumber.length() == 12) {
			message = "success";
		} else {
			message = "Invalid AADHAR Number !!";
		}
		return message;
	}

	public String chiefPromoterITpanValidator(String panCard) {

		Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
		Matcher matcher = pattern.matcher(panCard.toUpperCase());
		if (matcher.matches()) {
			message = "success";
		} else {

			message = "Invalid 'CHIEF PROMOTER IT-PAN' !!";
		}
		return message;
	}

	public String mseITpanValidator(String panCard) {

		Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
		Matcher matcher = pattern.matcher(panCard.toUpperCase());
		if (matcher.matches()) {
			message = "success";
		} else {
			message = "Invalid 'MSE IT-PAN' !!";
		}
		return message;
	}

	public String portfolioQuarterValidator(String quarterName)
			throws ParseException {

		String quarter = quarterName.substring(quarterName.length() - 3);

		Calendar now = Calendar.getInstance();

		// ###
		Calendar QrtStartDt_Cal = Calendar.getInstance();
		QrtStartDt_Cal.setTime(now.getTime());
		QrtStartDt_Cal.set(Calendar.MONTH,
				QrtStartDt_Cal.get(Calendar.MONTH) / 3 * 3);
		QrtStartDt_Cal.set(Calendar.DAY_OF_MONTH, 1);

		Calendar QrtEndDt_Cal = Calendar.getInstance();
		QrtEndDt_Cal.setTime(now.getTime());
		QrtEndDt_Cal.set(Calendar.MONTH,
				QrtEndDt_Cal.get(Calendar.MONTH) / 3 * 3 + 2);
		QrtEndDt_Cal.set(Calendar.DAY_OF_MONTH,
				QrtEndDt_Cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		QrtEndDt_Cal.add(Calendar.DATE, 45); // number of days to add

		// ###

		System.out.println("Current Month is : "
				+ (now.get(Calendar.MONTH) + 1));
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		Date myDate = sdf.parse("01-01-2017");

		if (quarter.equalsIgnoreCase("Q_1")) {

			// String qaterStartDate = "01-03-2017";
			// String quarterEDate = "31-05-2017";

			// Calendar c = Calendar.getInstance();
			// c.setTime(sdf.parse(quarterEDate));
			// c.setTime(QrtEndDt_Cal.getTime());
			// c.add(Calendar.DATE, 45); // number of days to add

			/*
			 * String quarterEndDate = sdf.format(c.getTime());
			 * System.out.println(quarterEndDate);/*
			 */
			/*
			 * Date qStartdate = new SimpleDateFormat("yyyy-MM-dd")
			 * .parse(qaterStartDate); Date qEnddate = new
			 * SimpleDateFormat("yyyy-MM-dd") .parse(quarterEndDate);/*
			 */

			Calendar cal = Calendar.getInstance();
			cal.setTime(QrtStartDt_Cal.getTime());

			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(QrtEndDt_Cal.getTime());

			/*
			 * if (cal.get(Calendar.MONTH) <= cal1.get(Calendar.MONTH)) {
			 * 
			 * System.out.println("Months are equal");
			 * 
			 * 
			 * } else {
			 * 
			 * }
			 */

		} else if (quarter.equalsIgnoreCase("Q_2")) {

		} else if (quarter.equalsIgnoreCase("Q_3")) {

		} else if (quarter.equalsIgnoreCase("Q_4")) {

		}
		return message;
	}

	public String StateDistrictValidator(
			MLIValidatorService mliValidatorService, String districtName,
			String stateName, List<DistrictName> distObjList,
			List<StateName> stateObjList) {

		for (DistrictName districtName1 : distObjList) {
			distMap.put(districtName1.getDst_name(),
					districtName1.getSte_code());

		}
		for (StateName statName : stateObjList) {
			stateMap.put(statName.getSte_code(), statName.getSte_name());

		}
		for (Map.Entry<String, String> entry : distMap.entrySet()) {
			if (entry.getKey().contains(districtName)) {
				filteredStateCodeMap.put(entry.getKey(), entry.getValue());
				for (Map.Entry<String, String> FilterEntry : filteredStateCodeMap
						.entrySet()) {
					stateCode = FilterEntry.getValue();
				}
				for (Map.Entry<String, String> stateEntry : stateMap.entrySet()) {
					if (stateEntry.getKey().contains(stateCode)) {
						message = "success";
						break;
					} else {

						message = "District AND State detail is Mismatch !!";
					}
				}
				break;
			} else {
				message = "Invalid District And State";
			}
		}
		return message;
	}

	public List<String> getNPAResion() {
		

		List<String> npaResionList = new ArrayList<String>();
		npaResionList.add("Activity/ Unit closed");
		npaResionList.add("Depreciation");
		npaResionList.add("Wear & Tear");
		npaResionList.add("Assets disposed");
		npaResionList.add("Recession");
		npaResionList.add("Obsolete");
		npaResionList.add("High competition in market");
		npaResionList.add("Borrower not traceable");
	
		npaResionList.add("Business Failure");
		return npaResionList;
	}

	public List<String> getNPACreditRisk() {
		List<String> npaCreditRiskList = new ArrayList<String>();
		npaCreditRiskList.add("Legal Action");
		npaCreditRiskList.add("Notice Issue");
		npaCreditRiskList.add("Frequent Visits");
		npaCreditRiskList.add("Sale of assets");

		return npaCreditRiskList;
	}
	
	public String npaUpgradationDateValidator(String date1,
			String date2) throws ParseException {

		
		System.out.println("date1==="+date1);
		System.out.println("date2==="+date2);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String sysDate = sdf.format(new Date());
		//date2 = sdf.format(new Date());
		//String sysDate = sdf.parse(currentDate);
		Date npaDt = sdf.parse(date1);
		Date npaUpgradationDt = sdf.parse(date2);
		Date sysDate1=sdf.parse(sysDate);
		System.out.println("npaDt==="+npaDt);
		System.out.println("npaUpgradationDt==="+npaUpgradationDt);
		 if ( npaUpgradationDt.compareTo(sysDate1) > 0) {

			message = "NPA Upgradation Date should not be greater than Current Date !! ";
		} else if (npaDt.compareTo(npaUpgradationDt) == 0) {
			System.out.println("NPA Date equal to  npaUpgradation date");
			message = "NPA Upgradation Date should be greater than NPA Date !!";
		}

		else if (npaDt.compareTo(npaUpgradationDt) > 0) {
			System.out
					.println("NPA Date less than npaUpgradation date !!");
			message = "NPA Upgradation Date should be greater than NPA Date !!";
		} 

		return message;
	}
	
}
