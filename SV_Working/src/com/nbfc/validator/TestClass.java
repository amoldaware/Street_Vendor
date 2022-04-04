package com.nbfc.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Saurav Tyagi 2017
 * 
 */
public class TestClass {
	static String message = null;

	public String sanctionDateValidator(Date sanctionDate)
			throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date myDate = sdf.parse("01-01-2017");

		SimpleDateFormat currentDateFormate = new SimpleDateFormat("dd-MM-yyyy");
		String currentDate = currentDateFormate.format(new Date());
		Date sysDate = sdf.parse(currentDate);

		System.out.println("Sanction Date : " + sanctionDate);

		if (sanctionDate.compareTo(myDate) > 0
				&& sanctionDate.compareTo(sysDate) > 0) {
			message = "Sanction Date Should not be Gretar then Current Date !!";
		} else if (sanctionDate.compareTo(myDate) == 0) {
			message = "systion date is equal to mydate";
		} else if (sanctionDate.compareTo(myDate) < 0) {
			message = "Sanction Date Should not be Less then '01-01-2017' ";
		} else if (sanctionDate.compareTo(myDate) > 0
				&& sanctionDate.compareTo(sysDate) < 0) {
			message = "Sanction is grater than my date but less than sysdate";
		} else if (sanctionDate.compareTo(sysDate) == 0) {
			message = "Santion date nas current date is equal";
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
			message = "First Disburesement Date should not be Less Than Sanction Date !!";
		} else if (firstDisbursementDate.compareTo(sysDate) == 0) {
			System.out
					.println("firstDisbursementDate equal to current date !!");
			message = "success";
		}

		return message;
	}

	public static void main(String[] args) throws ParseException, IOException {/*
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		// String currentDate = sdf.format(new Date());
		Date sanctionDate = sdf.parse("01-04-2017");

		Date disbusmentDate = sdf.parse("01-04-2017");

		String message = new TestClass().firstDisbursementDateValidator(
				disbusmentDate, sanctionDate);
		System.out.println("Message : " + message);

	*/
		
		File myFile = new File("WebContent/jReport/abc.txt");
        System.out.println("Attempting to read from file in: "+myFile.getCanonicalPath());

        Scanner input = new Scanner(myFile);
        String in = "";
        in = input.nextLine();
		 System.out.println(in);
		//Read File Content
		
	}
}
