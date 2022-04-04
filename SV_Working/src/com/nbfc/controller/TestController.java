package com.nbfc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TestController {

	@RequestMapping(value = "/testPage", method = RequestMethod.GET)
	public ModelAndView testMehod(){
		Map<String, Object> model = new HashMap<String, Object>();
		System.out.println("Action Testing..");
		return new ModelAndView("testPage", model);
	}
	@RequestMapping(value = "/test", params = "save", method = RequestMethod.POST)
public void testMehod1(){
		
		System.out.println("Save Action Testing..");
	}
	@RequestMapping(value = "/test", params = "denay", method = RequestMethod.POST)
public void testMehod2(){
	
	System.out.println("Denay Action Testing..");
}
}
