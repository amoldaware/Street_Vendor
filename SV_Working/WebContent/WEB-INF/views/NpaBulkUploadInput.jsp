<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>NPA Bulk Upload</title>
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
</head>

<body onload="errorMessage()" bgcolor="#E0FFFF">
<div class="main-section">
<div class="container-fluid">
<!-- <div class="row"> -->
	<div>	
			
		<div class="frm-section">
		<div class="col-md-12">

		<form:form method="POST" enctype="multipart/form-data" id="A" class="form-horizontal">
		  <h5 class="notification-message"><strong>${message}</strong></h5>
			  
				<div class="clearfix"></div>
			<hr style=" margin: 5px 0;  border: 1px solid #d8d8d8;">
			<div class="col-md-2 col-sm-4 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			  <a href="Download/NPA_EXCEL.xls" >Click here to download Excel Template File</a>
           
			    </div>	    			   
			  </div>
			</div>
		  
	<div class="col-md-3 col-sm-5 col-xs-12">
			  <div class="form-group">
			  <div class="col-md-12 prl-10">
			  <label>Upload File:</label>
			<!--  <input type="file" name="file" size="28" id="upload" class="form-control cus-control" disabled />
			 -->	 <input type="file" name="file" size="35" id="upload" class="form-control cus-control" />
			
				<form:errors path="file" cssStyle="color: #ff0000;" />
				<%-- <form:errors path="file" cssClass="error" /> --%>
			    </div>	    			   
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


<script type="text/javascript">
function validate_fileupload(ID){
	//
    var inp = document.getElementById('upload');
    //alert(inp);
    if(inp.files.length === 0){
        alert("Attachment Required");
        inp.focus();

      return false;
    }else{
    	document.getElementById('A').action = "/SV/NpaBulkUploadProcess.html";
		document.getElementById('A').submit();
    }
}
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