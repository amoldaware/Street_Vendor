<%@ page language="java" contentType="text/html; charset=ISO-8859-1"pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Quarterly File Upload Approval-MLI Checker</title>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
		<%
			String s = (String) request.getAttribute("SName");
		%>
	</head>
<body>
	<div class="main-section">
		<div class="container-fluid">
			<div>
				<div class="frm-section">
					<div class="col-md-12">
						<form:form action="" id="A" method="POST">
							<h4 class="sub-head text-center">
								<strong> ${exception.exceptionMsg} </strong>
							</h4>
							<p class="text-center">
								<a href="approveRejectNewMLIDetails.html" class="btn btn-reset" align="right">Back</a>
							</p>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

	<script type="text/javascript">
			function back() {
			//alert('Exit');
			document.getElementById('A').action = "/SV/approveRejectNewMLIDetails.html";
			document.getElementById('A').submit();
		}
	</script>