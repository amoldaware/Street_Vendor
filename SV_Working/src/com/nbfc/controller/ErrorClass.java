package com.nbfc.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ErrorClass {
	Map<String, String> countryList = new HashMap<String, String>();
	public Map<String, String> errorFile(){
		countryList.put("1", "1");
		countryList.put("2", "1");
		countryList.put("3", "1");
		countryList.put("4", "1");
		countryList.put("5", "1");

		return countryList;
	}
	
	public String password(String fName,String lName){
		
		String l=lName.substring(0,1);
		System.out.println(l);
		String SALTCHARS = "0123456789";
	    StringBuilder salt = new StringBuilder();
	     Random rnd = new Random();
	     while (salt.length() < 4) {
	         int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	         salt.append(SALTCHARS.charAt(index));
	     }
	     String saltStr = fName+l+salt.toString();
	    System.out.println(saltStr);
	     return saltStr;
	}
	}


