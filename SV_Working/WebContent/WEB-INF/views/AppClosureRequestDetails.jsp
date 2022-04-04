<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<link href="<%=request.getContextPath()%>/js/jquery-ui.css"
	rel="stylesheet">
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<!--<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>-->
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css"
	rel="stylesheet" type="text/css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<script type="text/javascript">

	$(function() {
		$("#AppClosureDate").datepicker({
			// showAnim : "fold"
			dateFormat : "dd/mm/yy"
		});
	});
</script>
</head>
<%
	String uesrName = (String) session.getAttribute("userId");
	if (uesrName == null) {
		response.sendRedirect("login");
	}
	System.out.println("uesrName....." + uesrName);
%>
<body onload="clearField()">

	<div class="main-section">
		<div class="container-fluid">
			<!-- <div class="row"> -->
			<div>
				<div class="frm-section">
					<div class="col-md-12">
						<h5 class="error1 mtb-0">
							<strong>${message}</strong>
						</h5>
					

						<div id="successMsgId">
							<h5 class="notification-message">
								<strong>${SuccessMsg} ${showPortfolioNoKey} </strong>
							</h5>
						</div>

							
								<form:form method="POST" id="A" action="/SV/SaveAppClosureDetails.html"
					class="form-horizontal">
					<div class="frm-section">
						<div class="col-md-12">
							
							<h5 class="sub-head">
								<strong> General Details</strong>
							</h5>
							
							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>NBFC MLI Name:</label>
										<form:input path="BankName"
											class="date-picker form-control cus-control" size="28" readonly="true" />
										<form:errors path="bankName" cssClass="error" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Member ID:</label>
										<form:input path="MEMBER_ID"
											class="date-picker form-control cus-control" size="28" readonly="true" />
										<form:errors path="MEMBER_ID" cssClass="error" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Unit Name:</label>
										<form:input path="MSE_NAME"
											class="date-picker form-control cus-control" size="28" readonly="true" />
										<form:errors path="MSE_NAME" cssClass="error" />
									</div>
								</div>
							</div>

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>CGPAN:</label>
										<form:input path="CGPAN"  size="20" readonly="true"
											class="form-control cus-control" id="CGPAN" />
									</div>
								</div>
							</div>



							<div class="clearfix"></div>
							<hr style="margin: 0; border: 1px solid #d8d8d8">
							<h5 class="sub-head">
								<strong> Closure Request Details: </strong>
							</h5>

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Closure Date:</label>
										<form:input path="AppClosureDate"
											class="date-picker form-control cus-control" size="28"/>
										<form:errors path="AppClosureDate" cssClass="error" />
									</div>
								</div>
							</div>
	
							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<label>Reason Of Closure::</label>
										<form:input path="AppClosureRemarks"
											class="date-picker form-control cus-control" size="28"
											placeholder="Closure Reason.." />
										<form:errors path="AppClosureRemarks" cssClass="error" />
									</div>
								</div>
							</div>


							<div class="d-inlineblock">
								<input class="btn btn-reset" type="button" value="Submit" onclick="SaveAppClosureDetails()" /> 
								<input class="btn btn-reset" type="button" value="Exit" onclick="" />
								 <span id="datepicker_14_Error" Class="error"></span>

								<div Class="displayErrorMessageInRedColor"></div>
							</div>

</div>
</div>

						</form:form>
						
					</div>
				</div>

			</div>
		</div>
	</div>




</body>
<script type="text/javascript">

	function SaveAppClosureDetails() {
		document.getElementById('A').action = "/SV/SaveAppClosureDetails.html";
		document.getElementById('A').submit();

	}

	
</script>
</html>


