
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<title>Portfolio Creation</title>
<link href="<%=request.getContextPath()%>/js/jquery-ui.css"
	rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>

<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">


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
					         url: "./searchSanctionMakerData.html",
					         data: "portfolioNo=" + portfolioNo + "&loanAccountNumber=" + loanAccountNumber,
					         success: function(response){
						        	
							          $.each(response, function (key,value) {
									          
											for (var i in value) {	
												 if (key=='result') {												          						        
												tableCre='<tr><td>'+value[i].loanAccountNumber+			            	 
												'</td><td align="right">'+value[i].amountOfDisbursement+			            	 
												'</td><td>'+value[i].dateOfDisbursement+
												'</td><td align="right">'+'<input type="text" id="modifySanction'+i+'" class="modifySanction"  />'+
												'</td><td align="center">'+'<input type="checkbox" id="chcktbl'+i+'" class="chcktbl"  />'+
												'</td></tr>';	

												$("#sanctionDataTable").append(tableCre);
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
					    		var loanAccountNumber=$(this).closest('tr').children(':first').text();					    	        	
			        			var sanctionAmount=$(this).closest("tr").find("td:eq(1)").text(); 
			        			var numbers = /^[0-9]+$/;
				    	        var modifySanctionAmount=$(this).closest('tr').find('.modifySanction').val();
			    	        	if(modifySanctionAmount==''){
			    	            	alert('ModifySanctionAmount is empty for loan Account No '+loanAccountNumber);
			    	        		dataList.length=0;
			    	        		return false;
			    	        	}else if(modifySanctionAmount.match(numbers)){
				    	        	if(modifySanctionAmount<500000){
			    	        		alert('You cannot enter modifySanctionAmount less than 5 Lacs !');
			    	        		dataList.length=0;
			    	        		return false;
			    	            } else if(Number(modifySanctionAmount)>Number(sanctionAmount)){  
			    	            	alert('ModifySanctionAmount cannot be greater than SanctionAmount !');
			    	        		dataList.length=0;
			    	        		return false;
			    	            }else{
			    	            	dataList.push(loanAccountNumber);
			    	            	dataList.push(modifySanctionAmount); 
			    	            }
			    	        	}else{
			    	        		alert('Please input numeric characters only');
			    	        		return false;
			    	            	           	
			    	             }        
			
					    	}      
			        	                  	   
			   		 });
		        	}else{
		        		$selectedCBs.each(function(key) {


		        			//var currentRow=$(this).closest("tr"); 
				            
				    		// var col1=currentRow.find("td:eq(0)").text(); // get current row 1st table cell TD value
				            // var col2=currentRow.find("td:eq(1)").text(); // get current row 2nd table cell TD value
				             //var col3=currentRow.find("td:eq(2)").text(); 
	    	        		//alert('sanctionAmount '+col1+' '+col2+' '+col3);

			        	      
		        			var loanAccountNumber=$(this).closest('tr').children(':first').text();					    	        	
		        			var sanctionAmount=$(this).closest("tr").find("td:eq(1)").text(); 
		        			//alert('sanctionAmount '+sanctionAmount);
		        			var numbers = /^[0-9]+$/;
			    	        var modifySanctionAmount=$(this).closest('tr').find('.modifySanction').val();
		    	        	
		    	        	if(modifySanctionAmount==''){
		    	            	alert('ModifySanctionAmount is empty for loan Account No '+loanAccountNumber);
		    	        		dataList.length=0;
		    	        		return false;
		    	        	}else if(modifySanctionAmount.match(numbers)){
			    	        	if(modifySanctionAmount<500000){
		    	        		alert('You cannot enter modifySanctionAmount less than 5 Lacs !');
		    	        		dataList.length=0;
		    	        		return false;
		    	            } else if(Number(modifySanctionAmount)>Number(sanctionAmount)){  
		    	            	alert('ModifySanctionAmount cannot be greater than SanctionAmount !');
		    	        		dataList.length=0;
		    	        		return false;
		    	            }else{
		    	            	dataList.push(loanAccountNumber);
		    	            	dataList.push(modifySanctionAmount); 
		    	            }
		    	        	}else{
		    	        		alert('Please input numeric characters only');
			    	            
		    	            	           	
		    	             }          	
		        	    });

		       }
		    
		   
		        if(dataList.length>0){
			         $.ajax({			 
				         type: "POST",		 
				         url: "./sanctionMakerApproval.html",
				         data: "sanctionData=" + dataList,
				         success: function(response){
			        	 if(response.result===1){
					        	refreshTable();
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
			
			
			
		</script>

</head>

<body bgcolor="#E0FFFF">
<form:form method="POST" action="/SV/disburstmentMakerApproval.html"
	id="A">
	<!-- <div
		STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;"> -->
	
	<table cellpadding=5 cellspacing=5 align=center width=90%>
		<tr>
			<td align="center"><b>Sanction Modification</b></td>
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
					<th>Sanctioned Amount</th>
					<th>Amount of Disbursement till Date</th>
					<th>Modify Sanction Amount</th>
					<th>Whether disbursed<br>
					<div align="center"><input type="checkbox" id="selectAll" /></div>
					</th>
				</tr>

			</table>
			</div>
			</td>
		</tr>

		
		<tr>
			<td align="center"><input class="button" id="onSubmit" value="Forward for Approval"
				align="left">&nbsp;&nbsp;<input type="submit" value=" Exit "
				onclick="exitMLIDetails()" class="button" /></td>
		</tr>

	</table>

</form:form>
</body>


<script type="text/javascript">
function exitMLIDetails() {
	alert('Exit');
	document.getElementById('A').action = "/SV/nbfcMakerHomeBack.html";
	document.getElementById('A').submit();
}
</script>
</html>




