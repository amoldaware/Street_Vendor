<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@	taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Management Certificate</title>
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css"
	rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<%
	String s = (String) request.getAttribute("SName");
%>
<script type="text/javascript">
	$(document).ready(function() {
		$(".form-control").removeClass("cus-control");
		$(".form-control").addClass("txtarea");
		// $("p:first").addClass("intro");
	});
</script>
<style>
.form-control.txtarea {
	min-height: 31px !important;
	height: 31px;
	width: 100%;
	resize: vertical;
	transition: all 0.5s;
	border-top: 0px;
	border-right: 0px;
	border-left: 0px;
	border-radius: 5px;
	/*     border: 1px solid #92c7e5; box-shadow: 1px 1px 2px #47a0d4d9; 	  border-color: #66afe970;}		blue shades*/
	border: 1px solid #d4d4d4 !important;
	box-shadow: 1px 2px 3px #be54c180; /* purple shade        
  /*  box-shadow: 1px 2px 3px #47a0d4;	box-shadow: 1px 1px 2px #47a0d4ba;*/
}

.form-control.txtarea:hover,.form-control.txtarea:focus {
	/*  box-shadow: 1px 3px 3px #66afe970; */
	/* box-shadow: 1px 3px 3px #47d4c0;  blue shades*/
	/*   background-color: #a641a936;  pruple shade1*/
	background-color: #fa61ff42;
	box-shadow: 1px 3px 3px #be54c1; /* purple shade */
}

input[type=radio] {
	margin: 4px 2px 0;
}
</style>
</head>
<body bgcolor="#E0FFFF">

	<div class="main-section">
		<div class="container-fluid">
			<div class="frm-section">
				<div class="col-md-12">
					
				</div>
			</div>
		</div>
	</div>

</body>
</html>