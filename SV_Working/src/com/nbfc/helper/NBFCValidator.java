package com.nbfc.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.ibm.icu.text.SimpleDateFormat;

/**
 * @author Saurav Tyagi 2017
 * 
 */
public class NBFCValidator {
	public static String quarterValue;
	List<Integer> qList = new ArrayList<Integer>();

	public String getQuarterNUmber(Date mmDate) throws ParseException {

		/*
		 * String q1StartDate = "01/04/2017"; String q1EndDate = "15/08/2017";
		 * Date q1sDate = new SimpleDateFormat("dd/MM/yyyy").parse(q1StartDate);
		 * Date q1eDate = new SimpleDateFormat("dd/MM/yyyy").parse(q1EndDate);
		 * String q2StartDate = "16/08/2017"; String q2EndDate = "15/11/2017";
		 * Date q2sDate = new SimpleDateFormat("dd/MM/yyyy").parse(q2StartDate);
		 * Date q2eDate = new SimpleDateFormat("dd/MM/yyyy").parse(q2EndDate);
		 * String q3StartDate = "16/11/2017"; String q3EndDate = "15/02/2018";
		 * Date q3sDate = new SimpleDateFormat("dd/MM/yyyy").parse(q3StartDate);
		 * Date q3eDate = new SimpleDateFormat("dd/MM/yyyy").parse(q3EndDate);
		 * String q4StartDate = "16/02/2018"; String q4EndDate = "15/05/2018";
		 * Date q4sDate = new SimpleDateFormat("dd/MM/yyyy").parse(q4StartDate);
		 * Date q4eDate = new SimpleDateFormat("dd/MM/yyyy").parse(q4EndDate);
		 * 
		 * if (q1sDate.compareTo(mmDate) * mmDate.compareTo(q1eDate) >= 0) {
		 * quarterValue = "Q1"; } else if (q2sDate.compareTo(mmDate) *
		 * mmDate.compareTo(q2eDate) >= 0) { quarterValue = "Q2"; } else if
		 * (q3sDate.compareTo(mmDate) * mmDate.compareTo(q3eDate) >= 0) {
		 * quarterValue = "Q3"; } else if (q4sDate.compareTo(mmDate) *
		 * mmDate.compareTo(q4eDate) >= 0) { quarterValue = "Q4"; } return
		 * quarterValue;
		 */

		// DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
		// Date pdate = (Date)formatter.parse(mmDate);

		String Quarter = "";
		Calendar c = Calendar.getInstance(Locale.US);
		c.setTime(mmDate);

		System.out.println("Current Date : " + c.getTime());

		Calendar QStDt = Calendar.getInstance(Locale.US);
		QStDt.set(Calendar.MONTH, 3);
		QStDt.set(Calendar.DAY_OF_MONTH, 1);
		QStDt.set(Calendar.HOUR, 11);
		QStDt.set(Calendar.MINUTE, 59);
		QStDt.set(Calendar.SECOND, 00);

		Date Q1StDt = QStDt.getTime();

		QStDt.add(QStDt.MONTH, 3);
		QStDt.add(QStDt.DAY_OF_MONTH, 45);
		Date Q1EnDt = QStDt.getTime();

		QStDt.add(Calendar.DAY_OF_MONTH, 1);
		Date Q2StDt = QStDt.getTime();

		QStDt.add(QStDt.MONTH, 3);
		QStDt.add(Calendar.DAY_OF_MONTH, 45);
		Date Q2EnDt = QStDt.getTime();

		QStDt.add(Calendar.DAY_OF_MONTH, 1);
		Date Q3StDt = QStDt.getTime();

		QStDt.add(QStDt.MONTH, 3);
		QStDt.add(Calendar.DAY_OF_MONTH, 45);
		Date Q3EnDt = QStDt.getTime();

		QStDt.add(Calendar.DAY_OF_MONTH, 1);
		Date Q4StDt = QStDt.getTime();

		QStDt.add(QStDt.MONTH, 3);
		QStDt.add(Calendar.DAY_OF_MONTH, 45);
		Date Q4EnDt = QStDt.getTime();

		System.out.println("equals check :"
				+ c.getTime().toString().contains(Q2StDt.toString()));

		System.out.println("====Q1====");

		System.out.println("Q2StDt: " + Q1StDt);
		System.out.println("Q2EnDt: " + Q1EnDt);

		System.out.println("====Q2====");
		System.out.println("Q2StDt: " + Q2StDt);
		System.out.println("Q2EnDt: " + Q2EnDt);

		System.out.println("====Q3====");
		System.out.println("Q2StDt: " + Q3StDt);
		System.out.println("Q2EnDt: " + Q3EnDt);

		System.out.println("====Q4====");
		System.out.println("Q2StDt: " + Q4StDt);
		System.out.println("Q2EnDt: " + Q4EnDt);

		// System.out.println("Current Date : "+c.getTime().equals(Q2StDt));
		String c_str = c.getTime().toString();

		if (c.getTime().compareTo(Q1StDt) >= 0
				&& c.getTime().compareTo(Q1EnDt) <= 0) {
			Quarter = "Q1";
			System.out.println("***Q1*****");
		}
		if (c.getTime().compareTo(Q2StDt) >= 0
				&& c.getTime().compareTo(Q2EnDt) <= 0) {
			Quarter = "Q2";
			System.out.println("****Q2*****");
		}
		if (c.getTime().compareTo(Q3StDt) >= 0
				&& c.getTime().compareTo(Q3EnDt) <= 0) {
			Quarter = "Q3";
			System.out.println("*****Q3*****");
		}
		if (c.getTime().compareTo(Q4StDt) >= 0
				&& c.getTime().compareTo(Q4EnDt) <= 0) {
			Quarter = "Q4";
			System.out.println("****Q4*****");
		}

		/*
		 * if((c.getTime().after(Q1StDt) ||
		 * c_str.contains(Q1StDt.toString())||c.getTime().equals(Q1StDt)) &&
		 * (c.getTime().before(Q1EnDt) || c_str.contains(Q1EnDt.toString())||
		 * c.getTime().equals(Q1EnDt)) ){ Quarter="Q1"; }
		 * 
		 * if( (c.getTime().after(Q2StDt) || c_str.contains(Q2StDt.toString())
		 * ||c.getTime().equals(Q2StDt) ) && (c.getTime().before(Q2EnDt) ||
		 * c_str.contains(Q2EnDt.toString() )|| c.getTime().equals(Q2EnDt) ) ){
		 * Quarter="Q2"; }
		 * 
		 * if((c.getTime().after(Q3StDt) ||
		 * c_str.contains(Q3StDt.toString())||c.getTime().equals(Q3StDt) ) &&
		 * (c.getTime().before(Q3EnDt) || c_str.contains(Q3EnDt.toString()) ||
		 * c.getTime().equals(Q3EnDt) ) ){ Quarter="Q3"; }
		 * 
		 * if((c.getTime().after(Q4StDt) || c_str.contains(Q4StDt.toString())
		 * ||c.getTime().equals(Q4StDt)) && (c.getTime().before(Q4EnDt) ||
		 * c_str.contains(Q4StDt.toString()) || c.getTime().equals(Q4EnDt)) ){
		 * Quarter="Q4"; }
		 */
		Quarter = "Q3";
		return Quarter;
	}

	public List<Integer> getQuarterIdNUmber(String quarterValue) {

		if (quarterValue.equalsIgnoreCase("Q1Q4")) {
			qList.add(1);
			qList.add(4);
		} else if (quarterValue.equalsIgnoreCase("Q1")) {
			qList.add(1);
		} else if (quarterValue.equalsIgnoreCase("Q1Q2")) {
			qList.add(1);
			qList.add(2);
		} else if (quarterValue.equalsIgnoreCase("Q2Q1")) {
			qList.add(2);
			qList.add(1);
		} else if (quarterValue.equalsIgnoreCase("Q2")) {
			qList.add(2);
		} else if (quarterValue.equalsIgnoreCase("Q2Q3")) {
			qList.add(2);
			qList.add(3);
		} else if (quarterValue.equalsIgnoreCase("Q3Q2")) {
			qList.add(3);
			qList.add(2);
		} else if (quarterValue.equalsIgnoreCase("Q3")) {
			qList.add(3);
		} else if (quarterValue.equalsIgnoreCase("Q3Q4")) {
			qList.add(3);
			qList.add(4);
		} else if (quarterValue.equalsIgnoreCase("Q4Q3")) {
			qList.add(4);
			qList.add(3);

		} else if (quarterValue.equalsIgnoreCase("Q4")) {
			qList.add(4);
		} else if (quarterValue.equalsIgnoreCase("Q4Q1")) {
			qList.add(4);
			qList.add(1);
		}

		return qList;
	}

	public int lastDigit(int number) {
		return number % 100;
	}
}
