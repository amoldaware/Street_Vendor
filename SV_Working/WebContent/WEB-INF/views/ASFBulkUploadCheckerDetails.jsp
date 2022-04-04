<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String sN = (String) request.getAttribute("sName");
	String expLim = (String) request.getAttribute("eXposureLimit");
%>
<html>
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

<script>
	$(function() {

		$("#ratingDate").datepicker({
			dateFormat : 'dd-mm-yy'
		});

	});
</script>
</head>

<body>
<div class="main-section">
<div class="container-fluid">
<!-- <div class="row"> -->
	<div>	
		<div class="tbl-details">
			<div class="col-md-12">
			<div class="d-inlineblock float-right ">
		<!-- <label style="padding-top:8px; font-weight:100;">Search :</label> -->
		<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px;
  		  background-color: #ffffff;" placeholder="Search Data.." title="Type in a name">   
  		   		
    		<button style="border:none !important; cursor: not-allowed;">
    			<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
    		</button>			
		</div>
	

	<!-- <div
		STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;"> -->
	<h1 align="center">ASF APPROVAL</h1>
	<hr>
	<form:form method="GET" id="A">
		<table  cellpadding=5 cellspacing=5 class="table table-bordered table-hover cus-table mt-10 mb-0 danRpDataTable" align=center width=90%  >			
				<tr>
					<td colspan=5><table border="0">							
							
				</table>
				</td></tr>				
				<tr>
				    <td class="tableHeader1">S.NO.</td>
					<td class="tableHeader1">PortFolio Name</td>
					<td class="tableHeader1">File Id</td>
					<td class="tableHeader1">Upload Date</td>
					<td class="tableHeader1">Select For Payment</br><form:checkbox path="selectAll" id="select_all" onchange="selectAllCheckBox();" cssClass="select_all"/>
					</td>
					
				</tr>
				<c:if test="${!empty danDetailList}">
	  <% int counter=0;%>
				<c:forEach items="${danDetailList}" var="mliList" varStatus="loopStatus">
					<tr <% if(counter%2==0){%>bgcolor="silver" <%}%>>
					<td><%=counter+1%></td>
				        <td><c:out value="${mliList.portfolio_name}" /></td>
						<td><a href="/SV/downloadOutstandingUpdateExcel.html?fileid=${mliList.fileid}">${mliList.fileid}</a></td>
						<td><c:out value="${mliList.upload_Date}" /></td>
					   	<td align="center"><form:checkbox path="chcktbl" value="${mliList.fileid}" onchange="uncheckSelectAll();" cssClass="chcktbl"/></td>
  						
					   	<%  counter+=1;%></tr>
				</c:forEach>
				</c:if>
			
		</table>
	<div align="center"><input type="submit" value="Approve" id="onSubmit" onclick="clickToInitiate();" class="button" />
	<input type="submit" value="Return" onclick="buckToHome()" class="button" />
	</div>
	<!-- </div> -->
	</form:form>
	
</body>
<script type="text/javascript">
function clickToInitiate(){
alert("");
	$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
	//var value=document.getElementById('checkBox').value();
	
   if ($selectedCBs.length === 0) {
       alert("Please select the record.");
    return;
  }else{    //	var accept=document.getElementById('accept').value;
    	//var reject=document.getElementById('reject').value;
 	
    	 //alert("selected row size is "+$selectedCBs.length);
    	
    	document.getElementById('A').action = "/SV/submitForApprove.html";
		document.getElementById('A').submit();	
    	
	
    }
}
function buckToHome() {
	//alert('Back');
	
	document.getElementById('A').action = "/SV/nbfcMakerHome.html";
	document.getElementById('A').submit();
}

function submitForApproval() {
	//alert('Back');
	
	$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
	
    if ($selectedCBs.length === 0) {
        alert("Please select the record for approval.");
        //return;
    }else{    //	var accept=document.getElementById('accept').value;
    	//var reject=document.getElementById('reject').value;
 	
    	 //alert("selected row size is "+$selectedCBs.length);
    	
    	document.getElementById('A').action = "/SV/submitForConfirm.html";
		document.getElementById('A').submit();	
    	
	
    }
	var value=document.getElementById('checkBox').value();
	alert(value);
	if(value!=null){
		
		document.getElementById('A').action = "/SV/submitForConfirm.html";
		document.getElementById('A').submit();	
	}else{
		alert('Please select Portfolio Name');
	}
	
}
function searchRecord(){
	var nameSearch = document.getElementById("nameSearch").value;
	var searchValue = document.getElementById("searchValue").value;
	alert('search  :'+nameSearch+' searchValue  :'+searchValue);
	if(nameSearch!=null || searchValue!=null){
		document.getElementById('A').action = "/SV/mlidetailsByIndex.html";
		document.getElementById('A').submit();
	}
	
}
function selectAllCheckBox() {
    if (document.getElementById('checkbox').checked == true) {
        $('.chcktbl').each(function() {
            this.checked = true;
        });
        $selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
    	var count=$selectedCBs.length-1;
    	document.getElementById('count').innerHTML=count;
    } else {
        $('.chcktbl').each(function() {
            this.checked = false;
        });
        $selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
    	var count=$selectedCBs.length;
    	document.getElementById('count').innerHTML=count;
    }

}
function uncheckSelectAll()
{
	$('.chcktbl').click(function () {
        if (this.checked == false) {
            $('#select_all').attr('checked', false);
        }
        $selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
    	var count=$selectedCBs.length;
    	document.getElementById('count').innerHTML=count;
    });
	$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
	var count=$selectedCBs.length;
	document.getElementById('count').innerHTML=count;
	
}

</script>

</html>



