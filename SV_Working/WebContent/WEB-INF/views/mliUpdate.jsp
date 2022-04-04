<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<title>Application Status</title>
<link href="css/jquery-ui-css.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<LINK href="<%=request.getContextPath()%>/css/stylesheet.css"
	rel="stylesheet" type="text/css">
<title>Mli Update</title>
</head>
<body onload="clearField()">
	<div class="main-section">
		<div class="container-fluid">
	 <nav aria-label="breadcrumb">
 <!--  <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/appstatus.html">Application Status</a></li>  
    <li class="breadcrumb-item"><a href="/SV/searchappstatus.html">Application Status1</a></li>
    <li class="breadcrumb-item active" aria-current="page">Application Status2</li>
  </ol> -->
</nav> 
<%
 String file_id=(String)session.getAttribute("FILE_ID");
String f_date=(String)session.getAttribute("FDate");
String s_date=(String)session.getAttribute("TDate");
String status=(String)session.getAttribute("AppStatus");

String userRole = (String) session.getAttribute("uRole");
String isSummaryReportPage = (String) session.getAttribute("summaryReportPage");
session.setAttribute("summaryReportPage",isSummaryReportPage);
System.out.println("summaryReportPage"+isSummaryReportPage);

%>



			<div>
				<div class="frm-section">
					<div class="col-md-12">
						<form:form method="POST" action="" class="form-horizontal" id="A" autocomplete="off">
							<div class="col-md-3 col-sm-4 col-xs-12 mar-top21">
								<div class="form-group">

									<div class="d-inlineblock mt-35">
									<table>
									<tr>
									<td>
									<form:label path="LenderName">Lender Name</form:label>
											<form:select path="LenderName" onchange="getCount()">
											<form:option value="NONE" label="---Select---"/>
											<form:options items="${lenderNames}"/>
											</form:select>
											<form:hidden path="selected"/>
											</td>
											<td>
											<label>Count:</label>
											<form:input path="count"/>
											</td>
											
									</tr>
									<tr>
									<td>
											<form:label path="mliName">MLI Name:</form:label>
											<form:select path="mliName" onchange="getHoUser()">
											<form:option value="NONE" label="---Select---"/>
											<form:options items="${mliNames}"/>
											</form:select>
											</td>
											<td>
											<form:label path="hoUser">HO User Details:</form:label>
											<form:select path="hoUser" onchange="setUserId()">
											<form:option value="NONE" label="---Select---"/>
											<form:options items="${hoUser}"/>
											</form:select>
											</td>
											<td>
											<label>User Id:</label>
											<form:input path="userId"/>
											</td>
											</tr>
									</table>
									</div>
									
									<div class="d-inlineblock mt-35">
										<!-- <input type="button" value="Create Exposure Limit"
											class="btn btn-reset" class="btn btn-reset"
											onclick="return searchFun()" /> -->
										<input type="submit" value="Update" class="btn btn-reset"
											class="btn btn-reset" onclick="return searchFunAndDownload();" />
									</div>
								</div>
							</div>
							<div></div>

						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

	
		<!-- <div
			STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;"> -->
		<div class="container-fluid">
			<div class="frm-section">
				<div class="col-md-12">
					<!-- <div class="tbl-details"> -->
					<!-- 	<table cellpadding=5 class="danRpDataTable" cellspacing=5
				align=center width=90%> -->
				</div>
			</div>
		</div>
	
	
	<div align="center">
										
										<input type="button" value="Back" class="btn btn-reset"
											class="btn btn-reset" onclick="Back1()" />
										<!-- mt-25 -->
										<!-- <input type="button" value="Back" class="btn btn-reset" onclick="return exitMLIDetails()"/> -->
									</div>
				
		



	<div align="left" id="successMsg">
		<font color="green" size="5">${message}</font>
	</div>
	<div align="left" id="error">
		<font color="red" size="5">${error}</font>
	</div>
	
<div class="overlay" id="load">
    <div class="overlay__inner">
        <div class="overlay__content"><span class="spinner"></span></div>
    </div>
</div>
	<script >


document.onreadystatechange = function () {
	  var state = document.readyState;
	  if (state == 'interactive') {
	      // document.getElementById('contents').style.visibility="hidden";
	       document.getelementsbyclassname('main-section').style.visibility="hidden";
	       
	  } else if (state == 'complete') {
	      setTimeout(function(){
	         document.getElementById('interactive');
	         document.getElementById('load').style.visibility="hidden";
	         document.getElementById('main-section').style.visibility="visible";
	      },1000);
	  }
	}


	function searchFun() {
		//isSummaryReportPage
			var reportType = document.getElementById("reporttype").value;
			if(reportType=='isSummaryReportPage'){
				var fromDate=document.getElementById("fromDate").value;
				
				if(fromDate==''){
					//document.getElementById("usr_password").innerHTML="Password is required";
					alert("Start date required");
					return false;

				}
				
			}else{
					var toDate = document.getElementById("toDate").value;
					var fromDate=document.getElementById("fromDate").value;
					if(fromDate==''){
						alert("Start date required");
						return false;

					}
					 if(toDate ==''){
						alert("End date required");
						return false;
					}
				}
		document.getElementById('A').action = "/SV/searchStreetVedor.html";

		document.deliveryForm.submit();
		
		/*  var v=alertDate();
		 
		 if(v!=false)
			 {
			
		document.getElementById('A').action = "/SV/searchStreetVedor.html";

		document.deliveryForm.submit();}
		 else{
			 return false;
		 } */

	}
	
	function searchFunAndDownload() {
		document.getElementById('selected').value = 'download'
		document.getElementById('A').action = "/SV/mliUpdate.html";
		document.deliveryForm.submit();
		/* 
		 var v=alertDate();
		 
		 if(v!=false)
			 {
			
		document.getElementById('A').action = "/SV/searchStreetVedorAndDownload.html";

		document.deliveryForm.submit();}
		 else{
			 return false;
		 } */

	}
	
	
	function Back1() {
		document.getElementById('A').action = "/SV/mliUpdate.html";
		document.getElementById('A').submit();

	}
	
	function getCount(){
		document.getElementById('selected').value = 'selected' 
		document.getElementById('A').action = "/SV/mliUpdate.html";
		document.getElementById('A').submit();
		
	}
	
	function getHoUser(){
		document.getElementById('selected').value = 'selectedHoUser' 
			document.getElementById('A').action = "/SV/mliUpdate.html";
			document.getElementById('A').submit();
	}
	
	function setUserId(){
		var val = document.getElementById('hoUser').value;
		document.getElementById('userId').value = val;
	}
	
	
	function alertDate()
	{	
		debugger;
		var toDate = document.getElementById("toDate").value;
		var fromDate=document.getElementById("fromDate").value;
		/* if(document.getElementById("memberId").value==""){
			document.getElementById("memberIdError").innerHTML="Member Id is required";
			//alert("Member Id is required");
			return;
		}else */
		
	//alert(fromDate);
	//alert(toDate);
		if(fromDate==''){
			//document.getElementById("usr_password").innerHTML="Password is required";
			alert("Start date required");
			return false;

		}else if(toDate ==''){
			//document.getElementById("usr_id").innerHTML="UserId is required";
			alert("End date required");
			return false;
		}/* else if(document.getElementById("MLIID").value==''){
			alert("Mli ID required");
			return false;
		} */
		}


	/*  function Back2() {

		document.getElementById('A').action = "/SV/searchappstatus.html?fdate=" +f_date"&sdate=" +s_date "&status=" +status;

		document.getElementById('A').submit();

	}  */
	$(document).ready(function() {
		$('#id1').hide();
		$('#preview').on('click', function() {
			$('#div1').toggle(300);
		});
	});

	//Ajax Call

	//Date Picker UI
	$(function() {
		$("#mliDateOfSanctionOfExposure").datepicker({
			dateFormat : 'dd/mm/yy'
		});
		$("#toDate").datepicker({
			dateFormat : 'dd/mm/yy'
		});
		$("#fromDate").datepicker({
			dateFormat : 'dd/mm/yy'
		});

	});

	//Form Validation
	//Form Validation
	//Form Validation
</script>
</body>
</html>
