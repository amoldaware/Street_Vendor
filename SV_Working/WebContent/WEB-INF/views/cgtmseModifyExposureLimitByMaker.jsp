<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<title>Modify Exposure Limit By Maker</title>
		<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<script type="text/javascript" src="js/jquery-ui-1.10.2/ui/jquery.ui.datepicker.js"></script>
		<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
	</head>
	
	<body>
		<div align="left" id="successMsg"><font color="green" size="5">${Modifymessage}</font></div>
		<div align="left" id="error"><font color="red" size="5">${modifyerror}</font></div>
		<form:form method="POST" action="modifyExposureLimitByMakerAndSendForApproval.html" onload="hideDiv()">
			<table align="center" border=0 cellpadding=5 cellspacing=5 align=center>
				<c:forEach items="${modifyList}" var="modifyList">
					<tr>
						<td class="label">MLI Long Name:<span style="color: red">*</span></td>
						<td class="DataElement"><form:input path="mliLongName" value="${modifyList.mliLongName}" size="20" style="text-align: right" id="mliLongName" readOnly="true" /><div id="requiredMliLongName" Class="displayErrorMessageInRedColor"></div></td>
						<td></td>
					</tr>
					<tr>
						<td class="label">MLI Short Name:<span style="color: red">*</td>
						<td class="DataElement"><form:input path="mliShortName" value="${modifyList.mliShortName}" size="20" style="text-align: right" id="mliShortName" readOnly="true" /><div id="requiredMliShortName" Class="displayErrorMessageInRedColor"></div></td>
					</tr>
					<tr>
						<td class="label">Exposure Limit (Rs):<span style="color: red">*</span></td>
						<td class="DataElement"><form:input path="mliExposureLimit" value="${modifyList.mliExposureLimit}" size="20" style="text-align: right" id="mliExposureLimit" onblur="myFunction()" /><div id="requiredExposureLimit" Class="displayErrorMessageInRedColor"></div></td>
					</tr>
					<tr>
						<td class="label">Date of Sanction of Exposure:<span style="color: red">*</span></td>
						<td class="DataElement"><form:input path="mliDateOfSanctionOfExposure" value="${modifyList.exposureSanctionDate}" size="20" id="mliDateOfSanctionOfExposure" style="text-align: left" /><div id="requiredExposureLimitMliDateOfSanctionOfExposure" Class="displayErrorMessageInRedColor"></div></td>
					</tr>
					<tr>
						<td class="label">Validity of Exposure Limit(dd/mm/yyyy):</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td class="label">Start Date:<span style="color: red">*</td>
						<td class="DataElement"><form:input path="mliValidityOfExposureLimitStartDate" value="${modifyList.fromDate}" size="20" id="mliValidityOfExposureLimitStartDate" style="text-align: left" /><div id="requiredMliValidityOfExposureLimitStartDate" Class="displayErrorMessageInRedColor"></div></td>
					</tr>
					<tr>
						<td class="label">End Date:<span style="color: red">*</td>
						<td class="DataElement"><form:input path="mliValidityOfExposureLimitEndDate" value="${modifyList.toDate}" size="20" id="mliValidityOfExposureLimitEndDate" style="text-align: left" /><div id="requiredMliValidityOfExposureLimitEndDate" Class="displayErrorMessageInRedColor"></div></td>
					</tr>
					<tr>
						<td class="label">Remarks:<span style="color: red">*</td>
						<td class="DataElement"><form:hidden path="remarks" value="${modifyList.remarks}" /> <textarea row="6" readOnly="true">${modifyList.remarks} </textarea><div id="remarks" Class="displayErrorMessageInRedColor"></div></td>
					</tr>
					<tr>
						<td align="right"><input type="submit" value="Back" name="action4" class="button" id="back" /></td>
						<td><input type="submit" value="Modify Exposure Limit And Send For Approval" name="action5" class="button" id="approveAndModify" onclick="return validateFormData()" /></td>
					</tr>
				</c:forEach>
			</table>
		</form:form>
	</body>
</html>



<script language="javascript">
	 //Ajax Call
	 function getMliShortName(){
		var mliLName=document.getElementById("mliLongName").value;
		try {
			$.ajax({
				type : "POST",
				url : "getMliExposureMasterMakerShortName.html",
				data : "mliLName=" + mliLName,
				success : function(response) {
					var select2 = document.getElementById('mliShortName');
					if (response.status == "SUCCESS") {
						document.getElementById('mliShortName').options.length=0;
						document.getElementById("mliShortName").value=response.result[0].mliShortName;
						document.getElementById("mliExposureLimit").value=response.result[0].mliExposureLimit;
						document.getElementById("mliDateOfSanctionOfExposure").value=response.result[0].exposureSanctionDate;
						document.getElementById("mliValidityOfExposureLimitStartDate").value=response.result[0].fromDate;
						document.getElementById("mliValidityOfExposureLimitEndDate").value=response.result[0].toDate;
						document.getElementById("mliRemarks").value=response.result[0].remarks;
						option = document.createElement('option');
						option.text =response.result[0].mliShortName;
						select2.add(option);
					}
				},
				error : function(e) {
					alert('Server Error : ' + e); 
			}
					});
	
		} catch (err) {
			alert('Error is : ' + err);
		}
	}  
	  
	//Date Picker UI
	$(function() {
	  	$("#mliDateOfSanctionOfExposure").datepicker({ dateFormat: 'dd/mm/yy' });
		$("#mliValidityOfExposureLimitStartDate").datepicker({ dateFormat: 'dd/mm/yy' });
		$("#mliValidityOfExposureLimitEndDate").datepicker({ dateFormat: 'dd/mm/yy' });
	});
	
	var x = document.getElementById("mliExposureLimit").value;
	function myFunction(){ 
		var y = document.getElementById("mliExposureLimit").value;
		var y1=parseInt(y);
	    if(x<y1){
	    	 document.getElementById("requiredExposureLimit").innerHTML="once exposure gets Approve or Reject,you can't increase the limit of exposure amount!";
	    }
	    else{
	    	 document.getElementById("requiredExposureLimit").innerHTML="";	
	    }
	}

	//Form Validation
	function validateFormData(){
		var longName1 = document.getElementById('mliLongName').value;
		/*  var long_name2 = long_name1.options[long_name1.selectedIndex].value; */
	    var shortName1 = document.getElementById('mliShortName').value;
	    /* var shortName2 = shortName1.options[shortName1.selectedIndex].value; */
	    var seldateDate = document.getElementById('mliValidityOfExposureLimitEndDate').value;
	    var SancDate  = document.getElementById('mliDateOfSanctionOfExposure').value;
	    var dateString = document.getElementById('mliValidityOfExposureLimitStartDate').value;
	    var today = new Date();
	    var currentDate=today.getDate()+1+'/'+today.getMonth()+1  +'/'+today.getFullYear();
	    selectedDate = new Date(Date.parse(seldateDate));
	    //alert("Akhil Selected Date after parsing::="+selectedDate);
		if(longName1==null || longName1=="" || longName1=='NONE'){
		 	document.getElementById("requiredMliLongName").innerHTML="Please enter Long Name";
		 }else{
	    	document.getElementById("requiredMliLongName").innerHTML="";
		 }
		if(shortName1==null || shortName1=="" || shortName1=='NONE'){
		 	document.getElementById("requiredMliShortName").innerHTML="Please enter Short Name";
		}else{
	    	document.getElementById("requiredMliShortName").innerHTML="";
		}
		if(document.getElementById('mliExposureLimit').value==null || document.getElementById('mliExposureLimit').value==""){
	    	document.getElementById("requiredExposureLimit").innerHTML="Please enter Exposure Limit";
		}else{
			document.getElementById("requiredExposureLimit").innerHTML="";
		}
		if(document.getElementById('mliDateOfSanctionOfExposure').value==null || document.getElementById('mliDateOfSanctionOfExposure').value==""){
	    	document.getElementById("requiredExposureLimitMliDateOfSanctionOfExposure").innerHTML="Please enter Date Of Sanction Of Exposure";
		}else{
			document.getElementById("requiredExposureLimitMliDateOfSanctionOfExposure").innerHTML="";
		}
		if(document.getElementById('mliValidityOfExposureLimitStartDate').value==null || document.getElementById('mliValidityOfExposureLimitStartDate').value==""){
	    	document.getElementById("requiredMliValidityOfExposureLimitStartDate").innerHTML="Please enter Validity Of Exposure Limit Start Date";
		}else{
			document.getElementById("requiredMliValidityOfExposureLimitStartDate").innerHTML="";
		}
		if(document.getElementById('mliValidityOfExposureLimitEndDate').value==null || document.getElementById('mliValidityOfExposureLimitEndDate').value==""){
	    	document.getElementById("requiredMliValidityOfExposureLimitEndDate").innerHTML="Please enter Validity Of Exposure Limit End Date";
		}else{
			document.getElementById("requiredMliValidityOfExposureLimitEndDate").innerHTML="";
		}
		if(SancDate>currentDate){
		    document.getElementById("requiredExposureLimitMliDateOfSanctionOfExposure").innerHTML="Expouser Sanction date should not be future date"; 
			return false;
		}
		if (dateString>currentDate){
			document.getElementById("requiredMliValidityOfExposureLimitStartDate").innerHTML="Expouser start date should not be future date"; 
			return false;
		}
		     
		var startdate = document.getElementById('mliValidityOfExposureLimitStartDate').value;
		var endDate = document.getElementById('mliValidityOfExposureLimitEndDate').value;
		/*if(startdate!="" && endDate!=""){
			if(startdate>endDate){
				document.getElementById("requiredMliValidityOfExposureLimitStartDate").innerHTML="Exposure start date cannot be greater then Exposure end date";
			 	return false;
			}
			if(document.getElementById('mliValidityOfExposureLimitEndDate').value!='' ){
				document.getElementById('exposureMasterMakerDetailsFormId').submit();
			}else{
				return false;
			}
		}*/
		if(SancDate!="" && startdate!=""){
			if(startdate<SancDate){
				document.getElementById("requiredMliValidityOfExposureLimitStartDate").innerHTML="Exposure start date must be greater then Date of Sanction of Exposure";
				return false;
			}
		}
	
		
	}
	function clearField(){
		//document.getElementById("id1").style.display="none";
		var clearLongName = document.getElementById('mliLongName');
		clearLongName.selectedIndex = 0;
		var clearShortName = document.getElementById('mliShortName');
		clearShortName.selectedIndex = 0;
		document.getElementById('mliExposureLimit').value="";
		document.getElementById('mliDateOfSanctionOfExposure').value="";
		document.getElementById('mliValidityOfExposureLimitStartDate').value="";
		document.getElementById('mliValidityOfExposureLimitEndDate').value="";
	}
</script>