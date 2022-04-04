package com.nbfc.controller;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.nbfc.helper.SessionManager;

public class ApplicationSessionListner implements HttpSessionListener{

	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		event.getSession().setMaxInactiveInterval(900);
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("Session Destroyed Method Called for User:"+event.getSession().getAttribute("userId"));
		try {
			SessionManager.removeLogin(event.getSession(), (String)event.getSession().getAttribute("userId"));
			event.getSession().removeAttribute("uName");
			event.getSession().removeAttribute("userId");
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Session Destroyed Method Completed");
	}

}
