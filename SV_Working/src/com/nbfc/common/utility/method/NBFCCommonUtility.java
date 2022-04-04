package com.nbfc.common.utility.method;

import java.text.ParseException;
import java.util.Date;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

public class NBFCCommonUtility {
	// find Month between given two date
	long monthsDiff;

	public long findMonthBetweenTwoDate(String startDate, String endDate) {

		SimpleDateFormat sdFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date startDateObj = sdFormat.parse(startDate);
			Date endDateObj = sdFormat.parse(endDate);
			// startDateObj.getTime() method gives date in milli seconds format
			// find time difference in milli seconds
			long timeDiff = endDateObj.getTime() - startDateObj.getTime();
			// time difference in seconds
			long secondsDiff = (timeDiff / 1000);
			// time difference in minutes
			long minDiff = timeDiff / (1000 * 60);
			// time difference in minutes
			long hoursDiff = timeDiff / (1000 * 60 * 60);
			// time difference in minutes
			long daysDiff = timeDiff / (1000 * 60 * 60 * 24);
			monthsDiff = daysDiff / 30;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return monthsDiff;

	}

	// Convert String Date into Date in dd/MM/yyyy Format
	public Date convertStringDateInToDateFormatddMMyyyy(String date) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date convertedStringDateInToDateFormatddMMyyyy = null;
		try {
			convertedStringDateInToDateFormatddMMyyyy = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return convertedStringDateInToDateFormatddMMyyyy;
	}

	// Convert String Date into Date in dd/MM/yyyy Format
	public Date convertStringDateInToDateFormatMMddyyyy(String date) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date convertStringDateInToDateFormatMMddyyyy = null;
		try {
			convertStringDateInToDateFormatMMddyyyy = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return convertStringDateInToDateFormatMMddyyyy;
	}
	//Convert date in to date along with dd/MM/yyyy format
	public Date getDateStringFormat(Date fDate) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Call");
		String cDate = dateFormat.format(fDate);
		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(cDate);  
		System.out.println("Date Formate"+date1);
		return date1;
	}
}
