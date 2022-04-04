<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
		<title>Portfolio Creation</title>
		<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<script type="text/javascript" src="jquery-ui-1.10.3/ui/jquery.ui.datepicker.js"></script>
		<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
		<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
	</head>
<body>
<div class="main-section">
	<div class="container-fluid">
		<div>	
			<div class="tbl-details">
				<div class="col-md-12">
					<div class="d-inlineblock float-right ">
				<!-- <label style="padding-top:8px; font-weight:100;">Search :</label> -->
						<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px; background-color: #ffffff;" placeholder="Search Data.." title="Type in a name">   
		    		    <button style="border:none !important; cursor: not-allowed;">
		    				<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
		    			</button>			
					</div>
<form:form method="POST" id="B">
	<c:if test="${!empty dataList}">
		<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0 danRpDataTable">
			<thead>
				<tr>			
					<th>SR.NO</th>				
					<th>RP NUMBER</th>					
					<th>AMOUNT</th>
					<th>GUARANTEE FEE GENERATION DATE</th>
					<th>Status</th>
					<th>SELECT FOR PAYMENT</br><form:checkbox path="select_all2" id="select_all2" onchange="selectAllCheckBox1();" cssClass="select_all1"/></th>					
				</tr>
			</thead>
				
	<% int counter=0;%>
	<c:forEach items="${dataList}" var="dataList">
					<tr>
						<td><c:out value="<%=counter+1%>" /></td>
						<td><c:out value="${dataList.rpNumber}" /></td>					
						<td><c:out value="${dataList.amount}" /></td>
						<td><c:out value="${dataList.date}" /></td>
						<td><c:out value="${dataList.status}" /></td>						
						<td align="center"><form:checkbox path="chcktbl2" value="${dataList.rpNumber}/${dataList.amount}/${dataList.virtualAccountNumber}" onchange="uncheckSelectAll1();" cssClass="chcktbl2" /></td>
		    			<%  counter+=1;%>
	</c:forEach>																				
	   </table>										
		<div class="col-md-5 col-sm-4 col-xs-12">
			<div class="form-group">
				 <div class="col-md-12 prl-10">
				 	 <label class="control-label col-sm-2 prl-10">Remark :</label>		     
				    	 <div class="col-md-10">	
				     		<form:input path="remark" id="remark"  class="form-control cus-control" value=""/>		      	    			  
				   		</div>	      
				  </div>			    			   
			 </div>
		</div>
    	<div class="d-inlineblock">     			    								
			<input class="btn btn-reset" readonly="true" id="onSubmit" onclick="clickToInitiate();"  value="Approve" align="">
			<!-- <input class="btn btn-reset" readonly="true" id="onReject" onclick="reject();" value="Return"  style="width:100px;">				   			      	   			    -->
		</div>  						
</c:if>
<c:if test="${empty dataList}">
   <table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0 danRpDataTable">
		<thead>
				<tr>			
					<th>SR.NO</th>				
					<th>RP NUMBER</th>					
					<th>AMOUNT</th>
					<th>GUARANTEE FEE GENERATION DATE</th>
					<th>Status</th>
					<th>SELECT FOR PAYMENT</br><form:checkbox path="select_all2" id="select_all2" onchange="selectAllCheckBox1();" cssClass="select_all2"/></th>					
				</tr>
		</thead>				
				<tr>
					<td colspan="6"  align="center">Data not available</td>	
				</tr>
	</table>
</c:if>
</form:form>			
			</div>
		</div>
	</div>	
</div>
</div>
</body>
<script type="text/javascript">
function selectAllCheckBox1() {
	 if (document.getElementById('select_all2').checked == true) {
	        $('.chcktbl2').each(function() {
	            this.checked = true;
	        });
	        $selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
	       // alert($selectedCBs.length);
	    	var count=$selectedCBs.length-1;
	    	document.getElementById('count').innerHTML=count;
	    } else {
	        $('.chcktbl2').each(function() {
	            this.checked = false;
	        });
	        $selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
	    	var count=$selectedCBs.length;
	    	document.getElementById('count').innerHTML=count;
	    }
	
   

}
function uncheckSelectAll1()
{		

	$('.chcktbl2').click(function () {
        if (this.checked == false) {
            $('#select_all2').attr('checked', false);
        }
        $selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
    	var count=$selectedCBs.length;
    	document.getElementById('count').innerHTML=count;
    });
		$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
		var count=$selectedCBs.length;
		document.getElementById('count').innerHTML=count;

	
}


function clickToInitiate(){
	$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
	
    if ($selectedCBs.length === 0) {
        alert("Please select the record");
        //return;
    }else{
		document.getElementById('B').action = "/SV/danPaymentInitiationSuccessPageASF.html";
		document.getElementById('B').submit();
    }
}



function reject() {
	$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
	
    if ($selectedCBs.length === 0) {
        alert("Please select the record for Rejection.");
        //return;
    }else{
		var remark=document.getElementById("remark").value;
		if(remark==''){
			alert("Remark is mandatory for rejection");
		}else{
	   document.getElementById('B').action = "/SV/danPaymentInitiationCheckerRejection.html";
	   document.getElementById('B').submit();
		}
    }
}


</script>

</html>



