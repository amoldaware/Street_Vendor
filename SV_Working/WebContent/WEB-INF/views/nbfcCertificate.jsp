<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Management Certificate</title>
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<%
	String s = (String) request.getAttribute("SName");
%>


</head>
<body bgcolor="#E0FFFF">

<div class="main-section">
<div class="container-fluid">
<!-- <div class="row"> -->
	<div>	
	<nav aria-label="breadcrumb">
  <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/mliChecker.html">Application Lodgement Approval</a></li>  
    <li class="breadcrumb-item active" aria-current="page">Management Certificate</li>
  </ol>
</nav>
		<div class="frm-section">
			<div class="col-md-12">
			<h3 class="heading text-center pb-15">Management Certificate</h3>
				<ol class="certificate">
					<li>	The information provided in the uploaded file (called as batch file) are in line with Credit Guarantee Scheme for NBFCs as notified in CGTMSE Circular 
                        No. 123/2016-17 dated January 25, 2017.</li>
					<li>All accounts covered in the portfolio are of MSE borrowers engaged in manufacturing or service & Retail Trade activities excluding Educational Institutions,
                        Agriculture, Self Help Groups (SHGs) and Training Institutions.</li>
					<li>All accounts covered in the portfolio are standard and regular accounts and there are no overdues as on the date of upload.</li>
					<li>All loans accounts covered in portfolio are with/without collateral security and / or third-party guarantee.
					</li>
					<li>All the accounts qualify the criteria of minimum acceptable investment grade prescribed by the MLI.	</li>
					<li>The loans are not additionally covered by Govt. / General Insurer/ Any person or association of persons carrying on the business of insurance, guarantee or indemnity 
to the extent they are covered under the Scheme.</li>
					<li> The Loans are not inconsistent with Law/ any directives or instructions issued by the Central Government or the RBI.</li>
				</ol>
		
			</div>
		</div>
	
	</div>
</div>
</div>	

	<!-- <h3 align="center"><b><u>Management Certificate</u></b></h3>
	<table>
		<tr>

			<td><p>1. The information provided in the uploaded file
					(called as batch file) are in line with Credit Guarantee Scheme for
					NBFCs as notified  in CGTMSE Circular <br>No. 123/2016-17 dated January
					25, 2017.</p></td>
		</tr>
		<tr>
			<td><p>2. All accounts covered in the portfolio are of MSE
					borrowers engaged in manufacturing or service activities excluding
					Pure Retail Trade, Educational Institutions,<br> Agriculture, Self Help
					Groups (SHGs) and Training Institutions.</p></td>
		</tr>
		<tr>
			<td><p>3. All accounts covered in the portfolio are standard
					and regular accounts and there are no overdues as on the date of
					upload.</p></td>
		</tr>
		<tr>
			<td><p>4. All loans accounts covered in portfolio are
					without collateral security and / or third party guarantee.</p></td>
		</tr>
		<tr>
			<td><p>5. All the accounts qualify the criteria of minimum
					acceptable investment grade prescribed by the MLI.</p></td>
		</tr>
		<tr>
			<td><p>6. The loans are not additionally covered by Govt. /
					General Insurer/ Any person or association of persons carrying on
					the business of insurance, guarantee or indemnity <br>to the extent
					they are covered under the Scheme.</p></td>
		</tr>
		<tr>
			<td><p>7. The Loans are not inconsistent with Law/ any
					directives or instructions issued by the Central Government or the
					RBI.</p></td>
		</tr>
		<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
		<tr><td align="center"><input onclick="window.open('', '_self', ''); window.close();" class="button1" value="Close"/>
	</td>
	
		</tr>
	</table> -->
	
</body>
</html>