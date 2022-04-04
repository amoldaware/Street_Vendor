<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<title>Portfolio Creation</title>
<link href="<%=request.getContextPath()%>/js/jquery-ui.css"
	rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<link	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"	rel="stylesheet">
<!--<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>-->
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring MVC Form Handling</title>


<script type="text/javascript">
function refreshTable(){
		//$("#searchDisburseData").click(function(){
	 $("#sanctionDataTable").find("tr:not(:first)").remove();
		var portfolioNo = $('#portfolioNo').val();
		var loanAccountNumber = $('#loanAccountNumber').val();
     var tableCre ;
			
			         $.ajax({			 
			         type: "POST",		 
			         url: "./searchData.html",
			         data: "portfolioNo=" + portfolioNo + "&loanAccountNumber=" + loanAccountNumber,
			         success: function(response){
				         
			        	 
					          $.each(response, function (key,value) {
									for (var i in value) {
									      if (key=='result') {					          
								          
								         tableCre='<tr><td>'+value[i].loanAccountNumber+			            	 
											'</td><td>'+value[i].unit+			            	 
											'</td><td>'+'<input type="text" id="datePicker'+i+'" name="datePicker'+i+'" class="datePicker"  />'+
											'</td><td align="right">'+value[i].amountOfDisbursement+
											'</td><td align="center">'+'<input type="checkbox" id="chcktbl'+i+'"class="chcktbl"  />'+
											'</td></tr>';	

											$("#sanctionDataTable").append(tableCre);
											$("#datePicker"+i).datepicker({ dateFormat: 'dd/mm/yy' });		
											$('.chcktbl').click(function () {
										        if (this.checked == false) {
										            $('#selectAll:checked').attr('checked', false);
										        }
										       
										    });
										}
									}
									});
					       
								
			         },
			 
			         error: function(e){
			         //alert('Error: ' + e.responseText);
			         alert('Error: ' + e);
					 
			         }
			 
			         });
			         
			            $('#selectAll:checked').attr('checked', false);
				        
		
	}
	//var dd=document.getElementById("name").value;
	$(document).ready(function(){
	//$("#searchDisburseData").click(function(){
		
	$('#selectAll').click(function () {
        if (this.checked == false) {
            $('.chcktbl:checked').attr('checked', false);
        }
        else {
            $('.chcktbl:not(:checked)').attr('checked', true);
        }
    });
    
	$('#onSubmit').click(function () {
       // var object1=new Object();
		var dataList1 = [];
		var dataList = [];
        $selectedCBs = $('.sanctionDataTable input[type="checkbox"]:checked');
		
    if ($selectedCBs.length === 0) {
        alert("No rows selected to initiate.");
        return;
    }else{ 
    	if($('#selectAll:checked').is(':checked') ){		    	
	    	$selectedCBs.each(function(key) {
		    	if(key===0){

			    	}else{
			    		//$selectedCBs.each(function(key) {
			    	    	//var dataList1 = [];      
			    	        	var loanAccountNumber=$(this).closest('tr').children(':first').text();
			    	        	var today = new Date();
			    	        	var today = new Date();
			    	        	var dd = today.getDate();
			    	        	var mm = today.getMonth()+1; //January is 0!

			    	        	var yyyy = today.getFullYear();
			    	        	if(dd<10){
			    	        	    dd='0'+dd;
			    	        	} 
			    	        	if(mm<10){
			    	        	    mm='0'+mm;
			    	        	} 
			    	        	var today = dd+'/'+mm+'/'+yyyy;
			    	        	var date=$(this).closest('tr').find('.datePicker').datepicker().val();
			    	        	if(date==''){
			    	            	alert('Date is empty of loan Account No '+loanAccountNumber);
			    	        		dataList.length=0;
			    	        		return false;
			    	        	}else if(date>today){
			    	        		alert('You cannot enter a date in the future!');
			    	        		dataList.length=0;
			    	        		return false;
			    	            }else{                
			    	                //alert(loanAccountNumber+date);
			    	               // var object=new Object();
			    	               // object.loanAccountNumber=loanAccountNumber;
			    	               // object.date=date;                
			    	            	//dataList.push(object); 
			    	            	dataList.push(loanAccountNumber);
			    	            	dataList.push(date);            	
			    	                //dataList.push(object);               
			    	                }         	
			    	  // });
	
			    	}      
	        	                  	   
	   		 });
        	}else{
        		$selectedCBs.each(function(key) {
        	    	//var dataList1 = [];      
        	        	var loanAccountNumber=$(this).closest('tr').children(':first').text();
        	        	var today = new Date();
        	        	var today = new Date();
        	        	var dd = today.getDate();
        	        	var mm = today.getMonth()+1; //January is 0!

        	        	var yyyy = today.getFullYear();
        	        	if(dd<10){
        	        	    dd='0'+dd;
        	        	} 
        	        	if(mm<10){
        	        	    mm='0'+mm;
        	        	} 
        	        	var today = dd+'/'+mm+'/'+yyyy;
        	        	var date=$(this).closest('tr').find('.datePicker').datepicker().val();
        	        	
        	        	if(date==''){
        	            	alert('Date is empty of loan Account No '+loanAccountNumber);
        	            	dataList.length=0;
	    	        		return false;
        	        	}else if(date>today){
        	        		alert('You cannot enter a date in the future!');        	                   
	    	        		dataList.length=0;
	    	        		return false;
        	            }else{                
        	               // var object=new Object();
        	               // object.loanAccountNumber=loanAccountNumber;
        	               // object.date=date;                
        	            	//dataList.push(object); 
        	            	dataList.push(loanAccountNumber);
        	            	dataList.push(date);            	
        	                //dataList.push(object);               
        	                }         	
        	    });

       }
    
   
        if(dataList.length>0){
	         $.ajax({			 
		         type: "POST",		 
		         url: "./disbursementMaker.html",
		         data: "disbursedData=" + dataList,
		         success: function(response){
	        	 if(response.result===1){
			        	refreshTable();
			        	//clearField();
			        	alert("Data Update Successfull!!!");
			        	
		        	}	
		         },
		 
		         error: function(e){
		         alert('Error: 2' + e);
				 
		         }
		 
		         });
        }
    		}
    	    
    	});
	
	
	});
	
	
	function exitDisbursementDetails() {
		//alert('Exit');
		document.getElementById('A').action = "/SV/nbfcMakerHome.html";
		document.getElementById('A').submit();
	}
</script>

</head>


<body bgcolor="#E0FFFF">
<form:form method="GET" action="/SV/disburstmentMakerApproval.html"
	id="A">
	<!-- <div
		STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;"> -->
	
	<table cellpadding=5 cellspacing=5 align=center width=90%>
		<tr>
			<td align="center"><b>Disbursement
	Maker For Approval</b></td>
		</tr>
		<tr>
			<td colspan=5 align="center">
			<table border="0">
				<tr>
					<td>Portfolio No :</td>
					<td><form:select path="portfolioNo" id="portfolioNo">
						<form:option value="NONE" label="--------Select-------" />
						<form:options items="${list}" itemValue="portfolioNo" />
					</form:select></td>
					<td>&nbsp;</td>
					<td>Loan Account No:</td>
					<td>&nbsp;<form:input path="loanAccountNumber"
						id="loanAccountNumber" value="" /></td>
					<td>&nbsp;</td>
					<td><input type="button" value="Search"
						onclick="refreshTable()" id="searchDisburseData"></td>
					<td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<div align="center" id="scrollDiv"
				 font-size: 15px; overflow: auto;">
			<table border="1" id="sanctionDataTable" class="sanctionDataTable"
				height="5px" width="65%">
				<tr>
					<th>Loan Account No</th>
					<th>Unit</th>
					<th>Sanction Date</th>
					<th>Sanction Amount</th>
					<th>Whether disbursed<br align="center"><div align="center">
					<input type="checkbox" id="selectAll" /></div></th>
				</tr>

			</table>
			</div>
			</td>
		</tr>

		
		<tr>
			<td align="center"><input class="button" id="onSubmit" value="Update"
				align="left">&nbsp;&nbsp;<input type="submit" value=" Exit "
				onclick="exitDisbursementDetails()" class="button" /></td>
		</tr>

	</table>

</form:form>
</body>
</html>

