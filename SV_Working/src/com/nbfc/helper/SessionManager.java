package com.nbfc.helper;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Hashtable;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class SessionManager extends HttpServlet
{
	public static final Logger logger = Logger.getLogger(SessionManager.class);
    private static ServletContext ctx = null;

    public SessionManager()
    {
    }

    public void init(ServletConfig config)
        throws ServletException
    {
        super.init(config);
        ctx = config.getServletContext();
        HashMap<String, String> currentLogins = new HashMap<String, String>();
        ctx.setAttribute("currentLogins", currentLogins);
      //  System.out.println("APPLICATION SCOPE.(INIT).....=".concat(String.valueOf(String.valueOf(ctx.getAttribute("currentLogins")))));
    }

    public static void putLoginInfo(HttpSession session, String userId)
    {
    	HashMap<String, String> currentLogins = (HashMap<String, String>)ctx.getAttribute("currentLogins");
        currentLogins.put(userId, session.getId());
        ctx.setAttribute("currentLogins", currentLogins);
        System.out.println("put Login hash "+currentLogins);
    }
    public static void removeLoginInfo(HttpSession session, String userId)
    {
    	HashMap<String, String> currentLogins = (HashMap<String, String>)ctx.getAttribute("currentLogins");
        try {
        	currentLogins.remove(userId);
        	ctx.setAttribute("currentLogins", currentLogins);
        	session.invalidate();
        }catch(Exception e) {
        	logger.info(e);
        	e.printStackTrace();
        }
    }
    public static boolean isDuplicateLogin(HttpSession session, String userIdValue)
    {
    	HashMap<String, String> currentLogins = (HashMap<String, String>)ctx.getAttribute("currentLogins");
        System.out.println("isDuplicateLogin Session Id::"+session.getId());
        if(currentLogins.containsKey(userIdValue) && !currentLogins.get(userIdValue).equals(session.getId()))
        {
        	System.out.println("isDuplicateLogin Duplicate Login on different browser");
            return true;
        } else
        {
        	System.out.println("isDuplicateLogin Already Login on same browser");
            return false;
        }
    }
    public static boolean isAlreadyLogin(HttpSession session, String userIdValue)
    {
    	HashMap<String, String> currentLogins = (HashMap<String, String>)ctx.getAttribute("currentLogins");
        System.out.println("isAlreadyLogin Session Id::"+session.getId());
        if(currentLogins.containsKey(userIdValue) && currentLogins.get(userIdValue).equals(session.getId())){
        	System.out.println(" isAlreadyLogin Already Login on same browser");
            return true;
        }else{
        	System.out.println("isAlreadyLogin No Already Login");
            return false;
        }
    }
    public static void removeLogin(HttpSession session, String userId)
    {
    	HashMap<String, String> currentLogins = (HashMap<String, String>)ctx.getAttribute("currentLogins");
        try {
        	currentLogins.remove(userId);
        	ctx.setAttribute("currentLogins", currentLogins);
        }catch(Exception e) {
        	logger.info(e);
        	e.printStackTrace();
        }
    }
}
