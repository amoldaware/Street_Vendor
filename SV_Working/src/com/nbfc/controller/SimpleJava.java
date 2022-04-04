package com.nbfc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SimpleJava {

	public static void main(String[] args) {
		try {
		    String startDateString = "2020-12-20";
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
		    System.out.println(sdf2.format(sdf.parse(startDateString)));
		} catch (ParseException e) {
		    e.printStackTrace();
		}
	}

}
