<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	//String sN = (String) request.getAttribute("sName");
	//String expLim = (String) request.getAttribute("eXposureLimit");
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<title>Portfolio Creation</title>
<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
<script type="text/javascript"
	src="jquery-ui-1.10.3/ui/jquery.ui.datepicker.js"></script>
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">

<style type="text/css">
.cus-table tr th{ text-align:center !important;}
</style>

</head>

<body >
<div class="main-section">
<div class="container-fluid">
<!-- <div class="row"> -->
	<div>	
	<div class="frm-section">
		<div class="col-md-12">
		<div class="d-inlineblock"><h5 class="notification-message"><strong>${message}</strong></h5>
		</div>
		<div class="d-inlineblock float-right ">
		<!-- <label style="padding-top:8px; font-weight:100;">Search :</label> -->
		<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px;
  		  background-color: #ffffff;" placeholder="Search Data.." title="Type in a name">    		
    		<button style="border:none !important; cursor: not-allowed;">
    			<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
    		</button>			
		</div>
		
			<form:form method="POST" id="A" class="form-horizontal">
			<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0 danRpDataTable">
				<c:if test="${!empty dataList}">
				<thead>
				<tr>
					<th>SR.NO.</th>				
					<th>DAN NO.</th>					
					<th>AMOUNT</th>
					<th>MLI LONG NAME</th>
					<th>PORTFOLIO NAME</th>
					<th>SELECT FOR APPROVAL<form:checkbox path="selectAll" id="select_all" onchange="selectAllCheckBox();" cssClass="select_all ml-10"/></th>
				</tr>
				</thead>								
				
				<% int counter=0;%>
				<c:forEach items="${dataList}" var="dataList">
					<%-- <tr <% if(counter%2==0){%>bgcolor="silver" <%}%>> --%>
					<tr>
						<td><c:out value="<%=counter+1%>" /></td>
						<td><c:out value="${dataList.danId}" /></a></td>					
						<td><c:out value="${dataList.amount}" /></td>
						<td><c:out value="${dataList.mliName}" /></td>
						<td><c:out value="${dataList.portfolioNo}" /></td>						
						<td align="center"><form:checkbox path="chcktbl" value="${dataList.danId}" onchange="uncheckSelectAll();" cssClass="chcktbl"/></td>
  						<!--<input type="checkbox" id="chcktbl" class="chcktbl"  />
						-->


						<%-- <td align="center"><a
							href="getDetails.html?mliLongName=${mliList.mliLongName}">Edit</a>
							| <a href="deleteMLIDetails.html?id=${employee.mliLongName}">Delete</a></td> <%  counter+=1;%>--%>
					</tr>
					<%  counter+=1;%>
				</c:forEach>
				<tr>
					<td></td>				
					<td></td>
					<td></td>					
					<td></td>
					<td></td>					
					<td><div id="count"></div></td>
				</tr>
				
				</c:if>
				
			</table>
			<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0 danRpDataTable">
			<c:if test="${empty dataList}">
				<thead>
				<tr>
					<th>SR.NO.</th>				
					<th>DAN NO.</th>					
					<th>AMOUNT</th>
					<th>MLI LONG NAME</th>
					<th>PORTFOLIO NAME</th>
					<th>SELECT FOR APPROVAL<form:checkbox path="selectAll" id="select_all" onchange="selectAllCheckBox();" cssClass="select_all ml-10"/></th>
				</tr>
				</thead>					
				<tr >
					<td></td>				
					<td></td>				
					<td align="center">Data not available</td>					
					<td ></td>
					<td ></td>
					<td ></td>	
				</tr>
		</c:if>
		
			</table>
			<div class="d-block" style="margin:0 auto; text-align:center;">
				<input class="btn btn-reset" style="width:100px"	readonly="true"	id="onSubmit" onclick="clickToInitiate();"  value="Approve">
				<!-- <input class="btn btn-reset" style="width:100px" readonly="true"	id="onReject" onclick="reject();" value="Reject"> -->
			</div>
			</form:form>
		
		</div>
	</div>	
	</div>
</div>
</div>	

	<!-- <div
		STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;"> -->
	<%-- 	<font color="green" size="3"><b>${message}</b></font>
	<center><h3>Generation of fee</h3></center>
		
		
	
	<hr><form:form method="POST" id="A">
	<div	"STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;">
		<table  cellpadding=5 cellspacing=5 class="danRpDataTable" align=center width=90%  >			
				
				
				<c:if test="${!empty dataList}">
				<tr>
					<td class="tableHeader1">Sr.No</td>				
					<td class="tableHeader1">Dan No</td>					
					<td class="tableHeader1">Amount</td>
					<td class="tableHeader1">MLI Name</td>
					<td class="tableHeader1">Portfolio Name</td>
					<td class="tableHeader1">Select for Approval</br><form:checkbox path="selectAll" id="select_all" onchange="selectAllCheckBox();" cssClass="select_all"/></td>
				</tr>
				
				<% int counter=0;%>
				<c:forEach items="${dataList}" var="dataList">
					<tr <% if(counter%2==0){%>bgcolor="silver" <%}%>>
						<td><c:out value="<%=counter+1%>" /></td>
						<td><c:out value="${dataList.danId}" /></a></td>					
						<td><c:out value="${dataList.amount}" /></td>
						<td><c:out value="${dataList.mliName}" /></td>
						<td><c:out value="${dataList.portfolioNo}" /></td>						
						<td align="center"><form:checkbox path="chcktbl" value="${dataList.danId}" onchange="uncheckSelectAll();" cssClass="chcktbl"/></td>
  						<!--<input type="checkbox" id="chcktbl" class="chcktbl"  />
						-->


						<td align="center"><a
							href="getDetails.html?mliLongName=${mliList.mliLongName}">Edit</a>
							| <a href="deleteMLIDetails.html?id=${employee.mliLongName}">Delete</a></td> <%  counter+=1;%>
					</tr>
					<%  counter+=1;%>
				</c:forEach>
				<tr>
					<td class="tableHeader1"></td>				
					<td class="tableHeader1"></td>
					<td class="tableHeader1"></td>					
					<td class="tableHeader1"></td>
					<td class="tableHeader1"></td>					
					<td class="tableHeader1"><div id="count"></div></td>
				</tr>
				<tr >
					<td></td>				
					<td></td>				
					<td></td>					
					<td ></td>
					<td ><input class="button"	readonly="true"	id="onSubmit" onclick="clickToInitiate();"  value="Approve" align=""></td>
					<td ><input class="button"	readonly="true"	id="onReject" onclick="reject();" value="Exit" align=""></td>	
					</tr>
				</c:if>
		<c:if test="${empty dataList}">
		<tr>
					<td class="tableHeader1">Sr.No</td>				
					<td class="tableHeader1">Dan No</td>					
					<td class="tableHeader1">Amount</td>
					<td class="tableHeader1">MLI Name</td>
					<td class="tableHeader1">Portfolio Name</td>
					<td class="tableHeader1">Select for Payment</br><form:checkbox path="selectAll" id="select_all" onchange="selectAllCheckBox();" cssClass="select_all"/></td>
				</tr>
				<tr >
					<tr >
					<td></td>				
					<td></td>				
					<td align="center">Data not available</td>					
					<td ></td>
					<td ></td>
					<td ></td>	
					</tr>
		</c:if>
					
	</table>
	</div>
	</form:form> --%>
	<!-- </div> -->

</body>
<script type="text/javascript">

function reject() {
	//$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
	
   // if ($selectedCBs.length === 0) {
    //    alert("Please select the record for Rejection.");
        //return;
  //  }else{
	//	var remark=document.getElementById("remark").value;
	//	if(remark==''){
	//		alert("Remark is mandatory for rejection");
	//	}else{
	   //document.getElementById('A').action = "/nbfc/danRPNumberCheckerRejection.html";
	   //document.getElementById('A').submit();
		//}
  //  }
}

function clickToInitiate(){

	$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
	
    if ($selectedCBs.length === 0) {
        alert("Please select the record for approval.");
        //return;
    }else{    //	var accept=document.getElementById('accept').value;
    	//var reject=document.getElementById('reject').value;
 	
    	 //alert("selected row size is "+$selectedCBs.length);
    	
    		document.getElementById('A').action = "/SV/danAllforCgtmseCheckerApproval.html";
    		document.getElementById('A').submit();
    	
	
    }
}

function selectAllCheckBox() {
    if (document.getElementById('select_all').checked == true) {
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



