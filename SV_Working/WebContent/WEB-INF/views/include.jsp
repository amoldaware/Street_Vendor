
<%@ page import="java.util.*"%>
<%@ page import="com.nbfc.helper.UrlRedirect" %>
<%@ page import="com.nbfc.helper.SessionManager" %>
<%

    String strCurrentUrl = request.getRequestURI();
	strCurrentUrl = strCurrentUrl.substring(strCurrentUrl.lastIndexOf("/")+1);
	System.out.println("strCurrentUrl="+strCurrentUrl);
    String user1 = (String) session.getAttribute("userId");
	System.out.println("User::"+user1);
    if(user1 == null)
     {
		//System.out.println("User is null");
	    UrlRedirect.redirectTo(response.getWriter(),null);
		return; 
	 }
    else
    {
		//Check for duplicate login
		if(SessionManager.isDuplicateLogin(session,user1))
		{
			String errorCode = "error.duplicatelogin";
			UrlRedirect.redirectTo(response.getWriter(),"Duplicate Login",errorCode);
			session.invalidate();
			return;
		}else if(SessionManager.isAlreadyLogin(session, user1)){
			return;
		}
   }
%>