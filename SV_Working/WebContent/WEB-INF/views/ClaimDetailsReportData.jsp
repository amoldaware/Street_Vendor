<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NPA Detail</title>

<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<LINK href="<%=request.getContextPath()%>/css/stylesheet.css"
	rel="stylesheet" type="text/css">

<link href="css/jquery-ui-css.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<%
	String s = (String) request.getAttribute("SName");

     
%>

</head>
<body>
	<div class="main-section">
			 <nav aria-label="breadcrumb">
   <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/ClaimStatusWiseDetailsReport.html">Claim Status Wise Report</a></li>  
    <li class="breadcrumb-item active" aria-current="page">Claim Status Wise Report Data</li>
  </ol> 
</nav>
		<div class="container-fluid">
			<div>
				<div class="frm-section">
					<div class="col-md-12"><h2 align="center" class="heading">Claim Status Wise Report</h2>
						<h5 class="notification-message">${message}</h5>
						<h5 class="error1">${Errormessage}</h5>
						<div class="loader"></div>
						<div class="d-inlineblock float-right  mt-25">
				<!-- <label style="padding-top:8px; font-weight:100;">Search :</label> -->
				<input type="text" id="myInput"  class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px;
		  		  background-color: #ffffff;" placeholder="Search Data111.." title="Type in a name">    		
		    		<button style="border:none !important;">
		    		<a href="ClaimDetailsReportDownload.html">
		    			<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
		    		</a>
		    		</button>			
				</div>
						<form:form method="POST" action="" id="A" class="form-horizontal">
		 <%
        String b=request.getParameter("claimStatus");
		
        %>
        
     	 <input type="hidden"   value="${formData.claimStatus}"/>
     	<input type="hidden"  value="${formData.fromDate}"/>
     	 <input type="hidden"  value="${formData.toDate}"/>
     	 <input type="hidden"  value="${formData.claimStatus}"/>
     	 <input type="hidden" value="${formData.memberId}">
        
        <table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0">
				<thead>
					<tr>
						<c:forEach items="${ClaimDetailsDataReport[0]}" var="column">							
							<th class="tableHeader1"><c:out value="${column.key}" /></th>
						</c:forEach>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ClaimDetailsDataReport}" var="columns">
						<tr>
							<c:forEach items="${columns}" var="column">
								<td>${column.value}</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</tbody>
		</table>
					
		</form:form>			
							
						
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="js/jquery-3.5.1.min.js" type="text/javascript">
	
	function backToHomePage() {
		document.getElementById('A').action = "/SV/nbfcMakerHome.html";
		document.getElementById('A').submit();

	}
	</script>


	
	<div class="loader"></div>
	<script type="text/javascript">
		$(window).load(function() {
			$(".loader").fadeOut("slow");
		});
	</script>
	
</body>
</html>