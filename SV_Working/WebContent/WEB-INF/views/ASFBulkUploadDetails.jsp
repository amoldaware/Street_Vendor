<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Outstanding Amount Upload-MLI Maker</title>
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">

<%
	String s=(String)request.getAttribute("SName");
%>

<script type="text/javascript">
function validate_fileupload(ID){
	//"WebContent/WEB-INF/views/ASFBulkUploadDetails.jsp"
    var inp = document.getElementById('upload');
    //alert(inp);
    if(inp.files.length === 0){
        alert("Attachment Required");
        inp.focus();

      return false;
    }else{
    	document.getElementById('A').action = "/SV/ASFfileUploaded.html";
		document.getElementById('A').submit();
    }
}


</script>
</head>
<body onload="errorMessage()" bgcolor="#E0FFFF">
<div class="main-section">
<div class="container-fluid">
<!-- <div class="row"> -->
	<div>	
			
		<div class="frm-section">
		<div class="col-md-12">

		<form:form method="POST" enctype="multipart/form-data" id="A" class="form-horizontal">
		  <%-- <h5 class="notification-message"><strong>${message}</strong></h5> --%>
		
			<h5 style="font-size:60;color: red" align="left"><strong>${message}</strong></h5>
			<div class="clearfix"></div>
			<hr style=" margin: 5px 0;  border: 1px solid #d8d8d8;">
			
			<div class="col-md-12 prl-10">
			<div class="form-group">
			   <div>
			     <label>Download Excel To Update Outstanding Amount:</label>
			    		<!-- <button style="border: none !important;">
									<a href="exportModifyOutstandingAmountDetails.html">Download Excel File <img
										src="images/excel.png" alt="" data-toggle="tooltip"
										title="Export To Excel">
									</a>
								</button>  -->
								
				<table  cellpadding=5 cellspacing=5 class="table table-bordered table-hover cus-table mt-10 mb-0 danRpDataTable" align=center width=90%  >			
				<tr>
					<td colspan=5><table border="0">							
							
				</table>
				</td></tr>				
				<tr>
				    <th>SR.NO.</th>
					<th>PORTFOLIO NAME</th>
					<th>FILE ID</th>
					<th>DOWNLOAD</th>
					
					
				</tr>
				<c:if test="${!empty danDetailList}">
	  <% int counter=0;%>
				<c:forEach items="${danDetailList}" var="mliList" varStatus="loopStatus">
					<tr <% if(counter%2==0){%>bgcolor="silver" <%}%>>
					<td><%=counter+1%></td>
						<td><c:out value="${mliList.portfolioName}" /></td>
						<td><c:out value="${mliList.fileId}" /></td>
						<td><a href="exportModifyOutstandingAmountDetails.html?fileid=${mliList.fileId}">Download Excel File </a></td>
					   	<%  counter+=1;%></tr>
				</c:forEach>
				</c:if>
			
		</table>				
								
								
								 			   
			  </div>
			  </div>
			  </div>
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			  <label> File Upload:</label>
			<!--  <input type="file" name="file" size="28" id="upload" class="form-control cus-control" disabled />
			 -->	 <input type="file" name="file" size="28" id="upload" class="form-control cus-control" />
			
				<form:errors path="file" cssStyle="color: #ff0000;" />
				<%-- <form:errors path="file" cssClass="error" /> --%>
			    </div>	  
			 
			</div>
		<c:if test="${empty errormessage}">
				<div class="d-inlineblock mt-25">
					<input  type="button" value="Upload" class="btn btn-reset" id="uploadBt" onclick="$('#loading').show();validate_fileupload(this.value);"/> 
					<input type="submit" value=" Exit "	onclick="exitMLIDetails()" class="btn btn-reset" />
				</div>
			
		</c:if>
		
			
			</form:form>
		</div>
			<h5 style="font-size:60;color: red" align="left"><strong>${errormessage}</strong></h5>
		</div>
	</div>
	
</div>
</div>



	<div id="loading"></div>
	<div id="content"></div>

	<div id="loading"
		style="position: absolute; top: 100px; left: 100px; z-index: 10; display: none">
		<img src="C:/Users/koteswararao.CGTMSE/Desktop/image/Preloader_2.gif"
			alt="Uploading..." /> <br /> wait we are processing your request...
	</div>

	<script type="text/javascript">
	function exitMLIDetails() {
		//alert('Exit');
		document.getElementById('A').action = "/SV/nbfcMakerHomeBack.html";
		document.getElementById('A').submit();
	}
		function doAjaxPost4() {
			var portfoBaseYr = document.getElementById("PORTFOLIO_BASE_YER").value;

			try {
				$
						.ajax({
							type : "POST",
							url : "getPortfolioNumber.html",
							data : "portfoBaseYr=" + portfoBaseYr,
							success : function(response) {
								var select2 = document
										.getElementById('PORTFOLIO_NAME');
								if (response.status == "SUCCESS") {
									document.getElementById('PORTFOLIO_NAME').options.length = 0;
									var value = 'Please select Portfolio Number';
									for (var i = 0; i < response.result.length; i++) {
										option = document
												.createElement('option');

										option.text = response.result[i].portfolio_Number;
										option.value = response.result[i].portfolio_Number;
										select2.add(option);
									}
								}
							},
							error : function(e) {
								/* alert('Server Error : ' + e); */
							}
						});

			} catch (err) {
				alert('Error is : ' + err);
			}

		}
		function onchangeApp() {
			var portfolioNum = document.getElementById("PORTFOLIO_NAME").value;
			//alert(portfolioNum);
			try {
				$
						.ajax({
							type : "POST",
							url : "getPortfolioDetails.html",
							data : "portfolioNum=" + portfolioNum,
							success : function(data) {
								var select2 = document
										.getElementById('SENCTIONED_PORTFOLIO');

								var portfolio_max_size = data.result.senctioned_portfolio;
								var guarantee_commission = data.result.guarantee_commission;
								var payout_cap = data.result.payout_cap;
								var portfolio_start_date = data.result.portfolio_start_date;
								var portfolio_period = data.result.portfolio_period;
								$('#SENCTIONED_PORTFOLIO').val(
										portfolio_max_size);
								$('#GUARANTEE_COMMISSION').val(
										guarantee_commission);
								$('#PAYOUT_CAP').val(payout_cap);
								$('#PORTFOLIO_PERIOD').val(portfolio_period);
								$('#PORTFOLIO_START_DATE').val(
										portfolio_start_date);
								//alert(portfolioNum+' '+portfolio_base_yer+' '+senctioned_portfolio+''+guarantee_commission+''+payout_cap+''+portfolio_start_date+''+typeOfLone+''+portfolio_period);

							},
							error : function(e) {
								alert('Server Error : ' + e);
							}
						});

			} catch (err) {
				alert('Error is : ' + err);
			}

		}
		function onchange2() {
			var portfolioNumber = document.getElementById("PORTFOLIO_NAME").value;
            
			try {
				$
						.ajax({
							type : "POST",
							url : "getQuarterNumber.html",
							data : "portfolioNumber=" + portfolioNumber,
							success : function(response) {
								var select2 = document
										.getElementById('QUARTER_NO');
								if (response.status == "SUCCESS") {
									document.getElementById('QUARTER_NO').options.length = 0;
									var value = 'Please select Portfolio Number';
									for (var i = 0; i < response.result.length; i++) {
										option = document
												.createElement('option');

										option.text = response.result[i].portfolioQuarter;
										option.value = response.result[i].sub_portfolio_dt_no;
										select2.add(option);
									}
								}
							},
							error : function(e) {
								//alert('Server Error : ' + e);
							}
						});

			} catch (err) {
				//alert('Error is : ' + err);
			}

		}
		function unableDisable() {
			document.getElementById("upload").disabled = false;
			document.getElementById("uploadBt").disabled = false;
		}
		function preloader() {
			document.getElementById("loading").style.display = "none";
			document.getElementById("content").style.display = "block";
		}//preloader
		window.onload = preloader;
	</script>
	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
	<div class="loader"></div>
	<script type="text/javascript">
		$(window).load(function() {
			$(".loader").fadeOut("slow");
		});
	</script>
</body>
</html>