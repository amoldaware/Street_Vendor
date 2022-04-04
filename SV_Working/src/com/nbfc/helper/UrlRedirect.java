package com.nbfc.helper;


import java.io.PrintWriter;

import org.apache.log4j.Logger;

// Referenced classes of package com.csnet.util:
//            PropertiesFileReader

public class UrlRedirect
{

    public UrlRedirect()
    {
    }
    
    static Logger logger = Logger.getLogger(UrlRedirect.class);

    public static void redirectTo(PrintWriter out, String url)
    {
        String redirectUrl = "Login.html";
        String propFileName = "datagrid";
        String tagName = "error.sessioninvalid";
        String message = "";
        try
        {
        //    redirectUrl = PropertiesFileReader.readProperty("redirectPath", propFileName);
         //   message = PropertiesFileReader.readProperty(tagName, propFileName);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        logger.info("Here Message :".concat(String.valueOf(String.valueOf(message))));
        logger.info("Here URl  :".concat(String.valueOf(String.valueOf(redirectUrl))));
        String strOut = "<head><META HTTP-EQUIV=\"pragma\" CONTENT=\"no-cache\"><title>Redirecting...</title> </head> " +
        			"<script language=\"JavaScript\"> alert('" + message + "'); window.top.document.location.href='" + redirectUrl + "'; </script>";
        
        out.println(strOut);
    }

    public static void redirectTo(PrintWriter out, String url, String msgCode)
    {
        String redirectUrl = "";
        String propFileName = "datagrid";
        String message = "";
        try
        {
          //  redirectUrl = PropertiesFileReader.readProperty("redirectPath", propFileName);
           // message = PropertiesFileReader.readProperty(msgCode, propFileName);
        }
        catch(Exception e)
        {
        	//com.csnet.util.PathcsUtility.printStackTrace(e, logger);
        }
        logger.info("Here Message :".concat(String.valueOf(String.valueOf(message))));
        logger.info("Here URl  :".concat(String.valueOf(String.valueOf(redirectUrl))));
        String strOut = "<head><META HTTP-EQUIV=\"pragma\" CONTENT=\"no-cache\"><title>Redirecting...</title> </head> " +
		"<script language=\"JavaScript\"> alert('" + message + "'); window.top.document.location.href='" + redirectUrl + "'; </script>";
        
        out.println(strOut);
    }
}
