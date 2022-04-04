package com.nbfc.helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ibm.icu.text.SimpleDateFormat;

public class Test {

	
	public static void main(String[] args) {
		 int year = getYearFromDate(new Date());
	        System.out.println("Financial Year : " + year + "-" + ((year%100)+1));
	        System.out.println("Financial month : " + getMonthFromDate(new Date()));
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
