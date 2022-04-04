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
<title>Portfolio Creation</title>
<link href="<%=request.getContextPath()%>/js/jquery-ui.css"
	rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css"
	rel="stylesheet" type="text/css">

<style type="text/css">
.form-group{margin-bottom:5px;}
.form-control{height: 30px;}
/* div.ui-datepicker{    top: 278px !important;} */
</style>
</head>

<body bgcolor="#E0FFFF">
<div class="main-section">
<div class="container-fluid">
<!-- <div class="row"> -->
	<div>	
	<nav aria-label="breadcrumb">
  <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/betweenTwoDateAppropriationASF.html">Appropriation 2</a></li>  
    <li class="breadcrumb-item active" aria-current="page">Appropriate Payments</li>
  </ol>
</nav>
		<div class="frm-section">
		<div class="col-md-12">
		<form:form method="POST" id="A" class="form-horizontal">
			<div class="payment-sec">
				<h5><strong>Appropriate Payments</strong></h5>
				<!-- <hr style="border-bottom: 1px solid #dedede; margin:0;"> -->
				
				
				<!-- <div class="col-md-8 mt-10" style="border: 1px solid #dedede;"> -->
				<div class="col-md-8 mt-10 col-md-offset-2">    
				<h6 style="    margin: 0; padding: 15px 0;	/* border: 1px solid #dedede;  background-color: #dedede; */
   /*  background-color: #4ba4bf; blue shade*/ background-color:#a641a9; color:white	"><strong>Payment Details of &nbsp; ${paymentId}</strong></h6>
				
				<div class="col-md-6 prl-10 payment-col">
			  <div class="form-group">
			    <label class="control-label col-sm-5 prl-10">Instrument No :</label>
			    <div class="col-sm-7">
			    <div class="pay-amount">	
			   		<p> ${paymentDetails.instrumentNo}</p>		      
			    </div>			      
			    </div>			    			   
			  </div>
			  
			   <div class="form-group">
			    <label class="control-label col-sm-5 prl-10">Instrument Amount :</label>
			    <div class="col-sm-7">
			    <div class="pay-amount">	
			   		<p> ${paymentDetails.instrumentAmount }</p>		 
			   		<form:hidden path="instrumentAmount"/>     
			    </div>			      
			    </div>			    			   
			  </div>
			  
			    <div class="form-group">
			    <label class="control-label col-sm-5 prl-10">Collecting Bank Name :</label>
			    <div class="col-sm-7">
			    <div class="pay-amount">	
			   		<p>${paymentDetails.collectingBank}</p>		      
			    </div>			      
			    </div>			    			   
			  </div>
			  
			   <div class="form-group">
			    <label class="control-label col-sm-5 prl-10">Drawn at (Bank) :</label>
			    <div class="col-sm-7">
			    <div class="pay-amount">	
			   		<p>${paymentDetails.drawnAtBank}</p>		      
			    </div>			      
			    </div>			    			   
			  </div>
			  
			   <div class="form-group">
			    <label class="control-label col-sm-5 prl-10">Payble At :</label>
			    <div class="col-sm-7">
			    <div class="pay-amount">	
			   		<p>${paymentDetails.payableAt}</p>		      
			    </div>			      
			    </div>			    			   
			  </div>
			  
			  <div class="form-group">
			    <label class="control-label col-sm-5 prl-10">Appropriated Amount</label>
			    <div class="col-sm-7">
			    <div class="pay-amount">	
			   		<p></p>		      
			    </div>			      
			    </div>			    			   
			  </div>
			  
			  <div class="form-group">
			    <label class="control-label col-sm-5 prl-10">Date of Realisation<span
												style="color: red;">*</span></label>
			    <div class="col-sm-7">
			    <div class="pay-amount">	
		   		<form:input path="realisationDate" type="text" id="dateOfRealisation" class="form-control" />		
		   			
				<div id="dateOfRealisationError1" Class="displayErrorMessageInRedColor"></div>
			    </div>			      
			    </div>			    			   
			  </div>
			  
			  
			  </div>
			  
			  <div class="col-md-6 prl-10 payment-col">
				  <div class="form-group">
				    <label class="control-label col-sm-5 prl-10">Instrument Date :</label>
				    <div class="col-sm-7">
				    <div class="pay-amount">	
				   		<p> ${paymentDetails.instrumentDate}</p>		      
				    </div>			      
				    </div>			    			   
				  </div>
				  <div class="form-group">
				    <label class="control-label col-sm-5 prl-10">Drawn at (Bank) :</label>
				    <div class="col-sm-7">
				    <div class="pay-amount">	
				   		<p> ${paymentDetails.modeOfPayment }</p>		      
				    </div>			      
				    </div>			    			   
				  </div>
				  
				   <div class="form-group">
				    <label class="control-label col-sm-5 prl-10">Collecting Bank Branch :</label>
				    <div class="col-sm-7">
				    <div class="pay-amount">	
				   		<p>${paymentDetails.collectingBankBranch}</p>		      
				    </div>			      
				    </div>			    			   
				  </div>
				  
				   <div class="form-group">
				    <label class="control-label col-sm-5 prl-10">Drawn at (Branch) :</label>
				    <div class="col-sm-7">
				    <div class="pay-amount">	
				   		<p>${paymentDetails.drawnAtBranch}</p>		      
				    </div>			      
				    </div>			    			   
				  </div>
				  
				   <div class="form-group">
				    <label class="control-label col-sm-5 prl-10">Allocated Amount :</label>
				    <div class="col-sm-7">
				    <div class="pay-amount">	
				   		<p>${paymentDetails.drawnAtBranch}</p>		      
				    </div>			      
				    </div>			    			   
				  </div>
				  
				  <div class="form-group">
				    <label class="control-label col-sm-5 prl-10">Short/Excess :</label>
				    <div class="col-sm-7">
				    <div class="pay-amount">	
				   		<p></p>		      
				    </div>			      
				    </div>			    			   
				  </div>
				  
				   <div class="form-group">
				    <label class="control-label col-sm-5 prl-10">Received Amount :<span
												style="color: red;">*</span></label>
				    <div class="col-sm-7">
				    <div class="pay-amount">	
				   		<form:input path="receivedAmount" size="28" value="" id="receivedAmount" class="form-control"  />  
				   		
					<div id="receivedAmountError1" Class="displayErrorMessageInRedColor"></div>
				    </div>			      
				    </div>			    			   
				  </div>
			  
			  </div>
			  
			  
			 
  				</div>		<!--  col-md-8 -->  				  			
  				
			</div>
			
			<div class="clearfix"></div>
			<div class="payment-sec">
			<h5><strong>Dan Details</strong></h5>
			</div>					
			
			<table  class="table table-bordered table-hover cus-table mt-10 mb-5 danRpDataTable">
				<thead>
				<!-- <tr >
				<th	colspan="12">Dan Details</th>
				</tr> -->
				<tr>
					<th rowspan="2" >SR.NO.</th>
					<th rowspan="2" >DAN ID</th>
					<th rowspan="2" >CGPAN</th>
					<th rowspan="2" >STATUS</th>
					<th colspan="3" >AMOUNT</th>
					<th rowspan="2" >TOTAL
					AMOUNT(DAN WISE)</th>
					<th rowspan="2" >REMARK/COMMENTS</th>
					<th rowspan="2" >APPROPRIATE</th>
					<th colspan="2" ><div align="center">REMARK</div>
					</th>
				</tr>
				<tr>
					<th >DAN AMOUNT</th>
					<th >PENALTY</th>
					<th >TOTAL</th>
					<th>COPY ALL <input type="checkbox" id="copyAll" onchange="copyText()"/></th>
				</tr>
				</thead>
					<c:if test="${!empty danDetails}">

				<% int counter=0;%>
				<c:forEach items="${danDetails}" var="danDetails">
					<%-- <tr <% if(counter%2==0){%>bgcolor="silver" <%}%>> --%>
					<tr <% if(counter%2==0){%>bgcolor="silver" <%}%>>
					<td><c:out value="<%=counter+1%>" /></td>
					<td><c:out value="${danDetails.danNo}" /></td>
					<td><c:out value="${danDetails.cgpan}" /></td>
					<td><c:out value="${danDetails.status}" /></td>
					<td><c:out value="${danDetails.amountRaised}" /></td>
					<td><c:out value="${danDetails.penalty}" /></td>
					<td><c:out value="${danDetails.allocated}" /></td>
					<td><c:out value="${danDetails.claimRevoverAmount}" /></td>					
					<td><c:out value="${danDetails.reason}" /></td>

					<td align="center">
						<form:checkbox path="chcktbl"
						value="${danDetails.danNo}" onchange="uncheckSelectAll();"
						cssClass="chcktbl" checked="true" />
					 </td>
					<td><textarea rows="2" id="textArea<%=counter+1%>" onkeyup="copyText()"> </textarea></td></tr>					
					<%  counter+=1;%>
				</c:forEach>
				</c:if>
				</table>
				
				<div class="d-block" style="margin:0 auto; text-align:center;">
				<div class="d-inlineblock" >
					<input class="btn btn-reset" readonly="true" id="onSubmit"
						onclick="clickToInitiate();" value="Save" >
					<input class="btn btn-reset" readonly="true" id="onReject"
						onclick="reject();" value="Return" >
					<button style="border:none !important; cursor: not-allowed;">
		    			<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
		    		</button>
				</div>
				
				</div>
				
			</form:form>
			
			
				
			
		</div>
		</div>
	</div>
</div>
</div>



<!-- <div
		STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;"> -->
<%-- <h3>Payment Appropriate</h3>
<hr>
<form:form method="POST" id="A">
	<div
		"STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;">
	<table cellpadding=5 cellspacing=5 class="danRpDataTable" align=center
		width=90%>



		<tr align="left" >
			<td class="tableHeader1" colspan="11">Appropriate Payments</td>
		</tr>
		<tr align="left" >
			<td class="tableHeader1" align="left" colspan="11">Payment Details of &nbsp; ${paymentId}</td>
		</tr>
		
				<tr bgcolor="silver">
					<td colspan="2">Instrument No</td>
					<td colspan="3">${paymentDetails.instrumentNo}</td>
					<td colspan="4">Instrument Date</td>
					<td colspan="2">${paymentDetails.instrumentDate}</td>
				</tr>
				<tr bgcolor="silver">
					<td colspan="2">Instrument Amount</td>
					<td colspan="3">${paymentDetails.instrumentAmount }</td>
					<td colspan="4">Mode Of Payment</td>
					<td colspan="2">${paymentDetails.modeOfPayment }</td>
				</tr>
				<tr bgcolor="silver">
					<td colspan="2">Collecting Bank Name</td>
					<td colspan="3">${paymentDetails.collectingBank}</td>
					<td colspan="4">Collecting Bank Branch</td>
					<td colspan="2">${paymentDetails.collectingBankBranch}</td>
				</tr>
				<tr bgcolor="silver">
					<td colspan="2">Drawn at (Bank)</td>
					<td colspan="3">${paymentDetails.drawnAtBank}</td>
					<td colspan="4">Drawn at (Branch)</td>
					<td colspan="2">${paymentDetails.drawnAtBranch}</td>
				</tr>
				<tr bgcolor="silver">
					<td colspan="2">Payble At</td>
					<td colspan="3">${paymentDetails.payableAt}</td>
					<td colspan="4">Allocated Amount</td>
					<td colspan="2">${paymentDetails.allocatedAmount}<form:input path="allocatedAmount" value="${paymentDetails.allocatedAmount}" size="28" type="hidden" /></td>
					
				</tr>
				<tr bgcolor="silver">
					<td colspan="2">Appropriated Amount</td>
					<td colspan="3"></td>
					<td colspan="4">Short/Excess</td>
					<td colspan="2"></td>
				</tr>
				<tr bgcolor="silver">
					<td colspan="2">Date of Realisation</td>
					<td colspan="3"><form:input path="realisationDate" type="text" id="dateOfRealisation"/></td>
					<td  colspan="4">Received Amount</td>
					<td colspan="2"><form:input path="receivedAmount" size="28"
						value=""  /></td>
				</tr>
			
		<tr>
			<td class="tableHeader1" colspan="11">Dan Details</td>
		</tr>

		
				<tr>
					<td rowspan="2" class="tableHeader1">Sr.No</td>
					<td rowspan="2" class="tableHeader1" width="30%">Dan Id</td>
					<td rowspan="2" class="tableHeader1" width="30%">CGPAN</td>
					<td rowspan="2" class="tableHeader1" width="30%">Status</td>

					<td colspan="3" class="tableHeader1">Amount</td>
					<td rowspan="2" class="tableHeader1" width="30%">Total
					Amount(Dan Wise)</td>
					<td rowspan="2" class="tableHeader1" width="30%">Remark/Comments</td>
					<td rowspan="2" class="tableHeader1" width="30%">Appropriate</td>
					<td rowspan="2" class="tableHeader1" width="30%"><div align="center">Remark</div><hr>
														<div align="right">Copy All
														<input type="checkbox" id="copyAll" onchange="copyText()"/></div></td>
				</tr>
				<tr>
					<td class="tableHeader1">Dan Amt</td>
					<td class="tableHeader1">Penalty</td>
					<td class="tableHeader1">Total</td>
				</tr>
				<c:if test="${!empty danDetails}">

				<% int counter=0;%>
				<c:forEach items="${danDetails}" var="danDetails">
					<tr <% if(counter%2==0){%>bgcolor="silver" <%}%>>
					<td><c:out value="<%=counter+1%>" /></td>
					<td width="30%"><c:out value="${danDetails.danNo}" /></td>
					<td width="30%"><c:out value="${danDetails.cgpan}" /></td>
					<td width="30%"><c:out value="${danDetails.status}" /></td>
					<td><c:out value="${danDetails.amountRaised}" /></td>
					<td><c:out value="${danDetails.penalty}" /></td>
					<td><c:out value="${danDetails.allocated}" /></td>
					<td><c:out value="${danDetails.claimRevoverAmount}" /></td>					
					<td><c:out value="${danDetails.reason}" /></td>

					<td align="center">
						<form:checkbox path="chcktbl"
						value="${danDetails.danNo}" onchange="uncheckSelectAll();"
						cssClass="chcktbl" checked="true" />
					 </td>
					<td><textarea rows="4" cols="30" id="textArea<%=counter+1%>" onkeyup="copyText()" class="text_Area"> </textarea></td></tr>					
					<%  counter+=1;%>
				</c:forEach>
				</c:if>

		




		<tr>
			<td class="tableHeader1"></td>
			<td class="tableHeader1"></td>
			<td class="tableHeader1"></td>
			<td class="tableHeader1"></td>
			<td class="tableHeader1"></td>
			<td class="tableHeader1"></td>
			<td class="tableHeader1"></td>
			<td class="tableHeader1"></td>
			<td class="tableHeader1"></td>
			<td class="tableHeader1"></td>
			<td class="tableHeader1"></td>
			</td>
		</tr>
		<tr align="center">
			
			<td colspan="11" align="center"><input class="button" readonly="true" id="onSubmit"
				onclick="clickToInitiate();" value="Save" align="">
			<input class="button" readonly="true" id="onReject"
				onclick="reject();" value="Reject" align=""></td>

		</tr>



	</table>
	</div>
</form:form> --%>


</body>
<script type="text/javascript">
function copyText(){
	
	var txt=$('.text_Area').val();
	$('.text_Area').val(txt);
}

$(function() {
  	$("#dateOfRealisation").datepicker({ dateFormat: 'dd/mm/yy' });
	
	
});


function clickToInitiate(){
//	var jsonBom = $('#paymentId').val();
	
     var chkflag=5;
    
	//alert(paramOne);
	$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
	
    if ($selectedCBs.length === 0) {
        alert("Please select the record for approval.");
        //return;
    }else{    //	var accept=document.getElementById('accept').value;
    	//var reject=document.getElementById('reject').value;
 	
    	// alert("selected row size is "+$selectedCBs.length);
    	// var approveCount;
    	// 	if (document.getElementById('select_all').checked == true) {   
        	 	//alert("hellow ",$selectedCBs.length-1); 	       
    	//	 approveCount=$selectedCBs.length-1;
    	 //   } else {    	       
    	 //   	approveCount=$selectedCBs.length;    	    	
    	 //   }
    	 	var amt=document.getElementById('receivedAmount').value;
    	    var date=document.getElementById('dateOfRealisation').value;
    	    if(date==null || date==""){
    	    	document.getElementById("dateOfRealisationError1").innerHTML = "Select Date of Relisation.";	
    	    	chkflag=chkflag-1;
    	    }else{
    	    	document.getElementById("dateOfRealisationError1").innerHTML = "";		
    	    }
             if(amt==null || amt=="" || amt ==0.0){
            	 document.getElementById("receivedAmountError1").innerHTML = "Enter the Receive Amount";
            	 chkflag=chkflag-1;
    	    }else{
    	    	 document.getElementById("receivedAmountError1").innerHTML = "";
    	    }
    	    if(chkflag==5){
    		document.getElementById('A').action = "/SV/updateAppropriateDataASF.html?rpno=" +"<c:out value="${paymentId}"/>";
    		document.getElementById('A').submit();
    	    }
	
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



