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
<!--<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
-->
<title>Portfolio Creation</title>

<LINK href="<%=request.getContextPath()%>/css/stylesheet.css"
	rel="stylesheet" type="text/css">

<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
<!--<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
-->
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<!--
<script type="text/javascript" src="jquery-ui-1.10.3/ui/jquery.ui.datepicker.js"></script>
<link href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>



-->
</head>

<body bgcolor="#E0FFFF">

	<div class="main-section">
		<div class="container-fluid">
			<!-- <div class="row"> -->
			<div>

				<div class="tbl-details">
					<div class="col-md-12">
						<h5 class="notification-message">
							<strong>${message}</strong>
						</h5>
						<form:form method="POST" id="A">
							<c:if test="${!empty dataList}">

								<table id="myTable"
									class="table table-bordered table-hover cus-table mt-10 mb-0 danRpDataTable">

									<thead>
										<tr>
											<th>SR.NO</th>
											<th>CGPAN</th>
											<th>NPA DATE</th>
											<th>NPA REASON</th>
											<th>Remarks</th>
											<th>SELECT ALL</br> <form:checkbox path="selectAll"
													id="select_all" onchange="selectAllCheckBox();"
													cssClass="select_all" /> <!--<div id="count"></div>	 --></th>
										</tr>
									</thead>
									<tbody>
										<%
											int counter = 0;
										%>
										<c:forEach items="${dataList}" var="dataList">

											<tr>
												<td><c:out value="<%=counter + 1%>" /></td>
												<td><a
													href="/SV/npaUpdate.html?Cgpan=${dataList.CGPAN}"><c:out
															value="${dataList.CGPAN}" /></a></td>
												<td><c:out value="${dataList.npaDt}" /></td>
												<td width="30%"><c:out value="${dataList.npaReason}" /></td>
												
													<td><form:textarea path="remarks" id="mliRemarks"
														class="form-control" /></td>
													        
												

												<td align="center"><form:checkbox path="chcktbl"
														value="${dataList.CGPAN}" onchange="uncheckSelectAll();"
														cssClass="chcktbl" /> <input type="hidden"
													id="approveCount" name="approveCount" value="hiddenValue"></td>
												<%
													counter += 1;
												%>
											
										</c:forEach>

									</tbody>
								</table>

								<div class="d-block">
									<input class="btn btn-reset" readonly="true" id="onSubmit"
										onclick="clickToInitiate();" value="Approve"> <input
										class="btn btn-reset" readonly="true" id="onReject"
										onclick="rejectFunction();" value="Return">

								</div>

							</c:if>

							<c:if test="${empty dataList}">
								<table
									class="table table-bordered table-hover cus-table mt-10 mb-0 danRpDataTable">
									<thead>
										<tr>
											<th>SR.NO.</th>
											<th>CGPAN</th>
											<th>NPA DATE</th>
											<th>NPA REASON</th>
											<!-- <th>LOAN TYPE</th> -->
											<!-- 	<th>EFFORTS TAKEN</th> -->
											<th>SELECT ALL</br> <form:checkbox path="selectAll"
													id="select_all" onchange="selectAllCheckBox();"
													cssClass="select_all" /></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td></td>
											<td></td>
											<td align="center">Data not available</td>
											<td></td>
											<!-- <td></td> -->

											<td></td>
										</tr>
									</tbody>
								</table>
							</c:if>


						</form:form>

					</div>
				</div>

			</div>
		</div>
	</div>


	<!-- </div> -->

</body>
<script type="text/javascript">
	var maxLength = 500;
	function enforceMaxLength(obj) {
		/* 	if (obj.value.length > maxLength) {
		 alert();
		 obj.value = obj.value.substring(0, maxLength);
		 } */
	}

	/* var text = document.getElementById('mliRemarks');
	 var maxLength =0;
	 var spanID= document.getElementById('count');
	 spanID.innerHTML = maxLength;
	 text.onkeyup = function ()
	 {
	 // document.getElementById('count').innerHTML = (maxLength - this.value.length);
	 };
	 */
	//document.getElementById("mliRemarks").disabled=true; 
	function clickToInitiate() {

		//alert(111);
		$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');

		if ($selectedCBs.length === 0) {
			alert("Please select the record for approval.");
			//return;
		} else { //	var accept=document.getElementById('accept').value;
			//var reject=document.getElementById('reject').value;

			// alert("selected row size is "+$selectedCBs.length);
			var approveCount;
			if (document.getElementById('select_all').checked == true) {
				//alert("hellow ",$selectedCBs.length-1); 	       
				approveCount = $selectedCBs.length - 1;
			} else {
				approveCount = $selectedCBs.length;
			}

			var hiddenValue = document.getElementById('approveCount').value = approveCount;
			//alert("hellow "+hiddenValue); 
			document.getElementById('A').action = "/SV/NPAApproved.html";
			document.getElementById('A').submit();

		}
	}

	function selectAllCheckBox() {
		if (document.getElementById('select_all').checked == true) {
			$('.chcktbl').each(function() {
				this.checked = true;
			});
			$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
			var count = $selectedCBs.length - 1;
			document.getElementById('count').innerHTML = count;
		} else {
			$('.chcktbl').each(function() {
				this.checked = false;
			});
			$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
			var count = $selectedCBs.length;
			document.getElementById('count').innerHTML = count;
		}

	}
	function rejectFunction() {

		$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');

		if ($selectedCBs.length === 0) {
			alert("Please select the record for Rejection.");
			//return;
		} else { //	var accept=document.getElementById('accept').value;

			var approveCount;
			if (document.getElementById('select_all').checked == true) {
				//alert("hellow ",$selectedCBs.length-1); 	       
				approveCount = $selectedCBs.length - 1;
			} else {
				approveCount = $selectedCBs.length;
			}
			var hiddenValue = document.getElementById('approveCount').value = approveCount;

			if (validateFormData()) {

				//alert("hellow "+hiddenValue); 
				document.getElementById('A').action = "/SV/NPAReject.html";
				document.getElementById('A').submit();
				document.getElementById("mliRemarks").value = "";
			} else {

				return false;
			}

		}
	}

	function validateFormData() {

		if (document.getElementById('mliRemarks').value == null
				|| document.getElementById('mliRemarks').value == "") {
			alert("Please enter the Remarks!");
			document.getElementById("mliRemarks").focus();
			document.getElementById("mliRemarks").disabled = false;
			return false;
		} else {
			return true;
		}
	}
	function uncheckSelectAll() {
		$('.chcktbl').click(function() {
			if (this.checked == false) {
				$('#select_all').attr('checked', false);
			}
			$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
			var count = $selectedCBs.length;
			document.getElementById('count').innerHTML = count;
		});
		$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
		var count = $selectedCBs.length;
		document.getElementById('count').innerHTML = count;

	}
</script>

</html>



