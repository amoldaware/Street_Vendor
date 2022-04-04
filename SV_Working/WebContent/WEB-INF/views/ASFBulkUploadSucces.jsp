<%@ page trimDirectiveWhitespaces="true" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.FileOutputStream"%>
<%@ page import="java.io.IOException"%>
<%@ page import="org.apache.poi.hpsf.HPSFException"%>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFCell"%>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFCellStyle"%>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFRow"%>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFSheet"%>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFWorkbook"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map.Entry"%>

<%
String value="";
String strException="Something went wrong , kindly contact to CGTMSE Support team.";
String strSuccessful="Record successfully uploaded.";
String strError="Please verify record and upload again.";

HashMap<String,ArrayList> map =null;
ArrayList successRecords = null;
ArrayList unsuccessRecords = null;
ArrayList errorRecords = null;
ArrayList error = null;
int success_cnt = 0;
int unsuccess_cnt = 0;
int all_eror_cnt = 0;
int eror_cnt = 0;

if(request.getAttribute("UploadedStatus")!=null)
{
	
	map = (HashMap)request.getAttribute("UploadedStatus");
	
	if(null!=map.get("successRecord")){		
		successRecords = (ArrayList)map.get("successRecord");
		out.println("DAN generated for successRecords.size() :"+successRecords.size());	
		if(successRecords.size()==2){
	  		ArrayList SuccessDataList=(ArrayList)successRecords.get(1);
	  		success_cnt = SuccessDataList.size();
		}
	}
	
	if(null!=map.get("unsuccessRecord")){
			unsuccessRecords = (ArrayList)map.get("unsuccessRecord");
			//out.println("unsuccessRecords.size() :"+unsuccessRecords.size());
			if(unsuccessRecords.size()==2){				
			  ArrayList UnSuccessDataList=(ArrayList)unsuccessRecords.get(1);
		  	  unsuccess_cnt = UnSuccessDataList.size();		
			}
	}
	
	if(null!=map.get("allerror")){
		errorRecords = (ArrayList)map.get("allerror");
		//out.println("errorRecords.size() :"+errorRecords.size());
		if(errorRecords.size()==2){				
		  ArrayList errorDataList=(ArrayList)errorRecords.get(1);
		  all_eror_cnt = errorDataList.size();		
		}
		
	if(null!=map.get("error")){
		   error = (ArrayList)map.get("error");
		   eror_cnt=error.size();
			//out.println("errorRecords.size() :"+error.size());
	}	
		
}
	

}  	

 

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<title>Portfolio Creation</title>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
<script type="text/javascript"
	src="jquery-ui-1.10.3/ui/jquery.ui.datepicker.js"></script>
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">


</head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<BODY>
<div class="main-section">
<div class="container-fluid">
<div>	
		<div class="tbl-details">
			<div class="col-md-12">

<!-- <TABLE width="100%" border="0" cellpadding="0" cellspacing="0"> -->
	 	<form:form id="A" action="/SV/asfDetails.html" class="form-horizontal">


			<TABLE width="100%" border="1" align="left" cellpadding="0"
				cellspacing="0">
				<TR>
				
					<% if(eror_cnt==0){ %>
					<h5 class="notification-message"><b>${msg}</b></h5>
					
					<table class="table table-bordered table-hover cus-table mt-10 mb-0 danRpDataTable">
					<thead>
					<tr>	
						<th>SR.NO.</th>
						<th>RESPONCE TYPE</th>
						<th>COUNT</th>
					</tr>
					</thead>
					<tr>
						<td>1</td>
						<td>Successful Records</td>
						<%if(success_cnt>0){%>
						<%-- <td><a href="ApplicationLogdementInBulkProcess.do?method=ExportToFile&&fileType=XLSType&FlowLevel=SuccessDataList"><%=success_cnt%></a></td>
					 --%>	
					 <td><a href="exportToFileASF.html?fileType=XLSType&FlowLevel=SuccessDataList"><%=success_cnt%></a></td>
						
						<%}else{ %>
						<td>0</td>
						<%}%>
						
					</tr>
					<tr>	
						<td>2</td>
						<td>UnSuccessful Records</td>
						<%if(unsuccess_cnt>0){%>
						<td><a href="exportToFileASF.html?fileType=XLSType&FlowLevel=UnSuccessDataList"><%=unsuccess_cnt%></a></td>
						<%}else{ %>
						<td>0</td>
						<%}%>
					</tr>					 
					<tr>	
						<td>3</td>
						<td>Error</td>
						<%if(all_eror_cnt>0){%>
						<td><a href="exportToFileASF.html?fileType=XLSType&FlowLevel=Allerrors"><%=all_eror_cnt%></a></td>
						<%}else{ %>
						<td>0</td>
						<%}%>
					</tr>
					
					</TABLE>
					<%}else{ %>
			            <TABLE width="100%" border="0" cellspacing="5" cellpadding="5" class="TableData" style="text-align: center;" > 
					<tr>	
						<td><font color=red><h2><%=error%></h2></font></td>
					</tr>
					</TABLE>
			            <%} %>
					 	 		 
				     </TD>
				</TR> 
				    
				<%--s --%>
				<TR align="center" valign="baseline" >
					<TD colspan="2" width="700">
						<DIV align="center">
						<!-- <input type="submit" value="Back"	onclick="backMLIDetails()" class="btn btn-reset" /> -->
						 	<input type="submit" value="exit"	onclick="exitMLIDetails()" class="btn btn-reset" />
						
						 <!-- 	<A href="javascript:submitForm('ApplicationLogdementInBulkInput.do?method=ApplicationLogdementInBulkInput')">
								<IMG src="images/Back.gif" alt="Back" width="49" height="37" border="0"> -->
							</A>								
						</DIV>
					</TD>
				</TR>
				<%--e --%>
			</TABLE>
			            
			<!-- </TD> -->
			<TD width="20" background="images/TableVerticalRightBG.gif">
			&nbsp;</TD>
		<!-- </TR> -->
		<TR>
			<TD width="20" align="right" valign="top"><IMG
				src="images/TableLeftBottom1.gif" width="20" height="15"></TD>
			<TD background="images/TableBackground2.gif">&nbsp;</TD>
			<TD width="20" align="left" valign="top"><IMG
				src="images/TableRightBottom1.gif" width="23" height="15"></TD>
		</TR>
	</form:form>
<!-- </TABLE> --> 
<TABLE>
<tr>
						<!-- 	<TD class="ColumnBackground"><font style="color: red; font-size: 11px;">Notes : </font></TD> -->							
							<!-- <td class="ColumnBackground" > -->
								<div>
								<font style="color: red; font-size: 11px;">Notes : </font>
									<font style="color: red; font-size: 11px;">
									Please upload guarantee application records only once. Only Unsuccessful / Error records may be re-uploaded again</font>
									<br><br>									
									<font style="color: green; font-size: 11px;">Successful Records - </font>
									<font style="color: black; font-size: 11px;"> Records uploaded successfully. </font> <br>
									<font style="color: red; font-size: 11px;">Unsuccessful Records - </font> 
									<font style="color: black; font-size: 11px;">Business validations failed.</font> <br>
									<font style="color: red; font-size: 11px;">Error - </font> 
									<font style="color: black; font-size: 11px;">Data not as per Excel Template.</font><br> 
								</div>
							<!-- </TD> -->
						</TR>
</TABLE>
<script type="text/javascript">
function backMLIDetails() {
	alert('Back');
	document.getElementById('A').action = "/SV/asfDetails.html";
	document.getElementById('A').submit();
}
function exitMLIDetails() {
	//alert('Exit');
	document.getElementById('A').action = "/SV/nbfcMakerHomeBack.html";
	document.getElementById('A').submit();
}
</script>
</div>
</div>
</div>
</div>
</div>
</BODY>
</HTML>