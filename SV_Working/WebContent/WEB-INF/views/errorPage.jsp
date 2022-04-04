<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>MLI User Registration Form</title>
		<%
			String s = (String) request.getAttribute("SName");
		%>
		<style type="text/css">
		</style>
	</head>
<body>
	<div class="main-section">
		<div class="container-fluid">
			<div>
				<nav aria-label="breadcrumb">
				<ol class="breadcrumb cus-breadcrumb">
					<li class="breadcrumb-item"><a href="/SV/mliMaker.html">Application
							Lodgement </a></li>
					<li class="breadcrumb-item active" aria-current="page">Application
						Lodgement Error Page</li>
				</ol>
				</nav>
				<div class="tbl-details">
					<div class="col-md-12">
						<h3 align="center" class="error1">File Upload Failed. Due to
							following errors, Kindly correct the following data in excel file
							and upload again.</h3>
						<form:form method="GET" action="/SV/mliMaker.html">
							<%
								int srno = 1;
							%>
							<c:forEach items="${errorList}" var="list">
								<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0 tbl-error">
									<thead>
										<tr>
											<th>Sr.No</th>
											<th>Loan Account No.</th>
											<th>Error Detail</th>
										</tr>
									</thead>
									<tr>
										<td><c:forEach items="${list.value}" var="listItem"
												varStatus="loop"> ${loop.index+1} <br />
											</c:forEach></td>
										<td><c:forEach items="${list.value}" var="listItem"> ${listItem} <br />
											</c:forEach></td>
										<td><c:forEach items="${list.key}" var="listItem">  ${listItem} <br />
											</c:forEach></td>
									</tr>
								</table>
							</c:forEach>
						</form:form>

					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>