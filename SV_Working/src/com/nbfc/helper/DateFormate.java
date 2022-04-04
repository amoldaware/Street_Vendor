package com.nbfc.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormate {
	public static String dateformate(String From$totate){
		 String date=From$totate.substring(0,2);
		 String month=From$totate.substring(3,5);
		 String year=From$totate.substring(6,10);
		 String todate="";
		 if(month.equals("01")){
			  todate=date+"-"+"JAN-"+year; 
		 }
		 if(month.equals("02")){
			  todate=date+"-"+"FEB-"+year; 
		 }
		 
		 if(month.equals("03")){
			  todate=date+"-"+"MAR-"+year; 
		 }
		 
		 if(month.equals("04")){
			  todate=date+"-"+"APR-"+year; 
		 }
		 
		 if(month.equals("05")){
			  todate=date+"-"+"MAY-"+year; 
		 }
		 
		 if(month.equals("06")){
			  todate=date+"-"+"JUN-"+year; 
		 }
		 
		 if(month.equals("07")){
			  todate=date+"-"+"JUL-"+year; 
		 }
		 
		 if(month.equals("08")){
			  todate=date+"-"+"AUG-"+year; 
		 }
		 
		 if(month.equals("09")){
			  todate=date+"-"+"SEP-"+year; 
		 }
		 
		 if(month.equals("10")){
			  todate=date+"-"+"OCT-"+year; 
		 }
		 
		 if(month.equals("11")){
			  todate=date+"-"+"NOV-"+year; 
		 }
		 if(month.equals("12")){
			  todate=date+"-"+"DEC-"+year; 
		 }

		return todate;	
	}
	public String datechangeformate(String date ) throws ParseException{
		DateFormat inputFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date date1 = inputFormat1.parse(date);
		DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
		String outputString = outputFormat.format(date1);
		return outputString;
		
	}
}
