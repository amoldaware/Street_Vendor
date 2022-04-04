<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String sN = (String) request.getAttribute("sName");
	String expLim = (String) request.getAttribute("eXposureLimit");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<title>Portfolio Creation</title>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
<script type="text/javascript"
	src="jquery-ui-1.10.3/ui/jquery.ui.datepicker.js"></script>
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<LINK href="<%=request.getContextPath()%>/css/stylesheet.css"
	rel="stylesheet" type="text/css">

<script>
	$(function() {

		$("#ratingDate").datepicker({
			dateFormat : 'dd-mm-yy'
		});

	});
</script>
</head>

<body>
	<div class="main-section">
		<div class="container-fluid">
			<!-- <div class="row"> -->
			<div>
				<div class="tbl-details">
					<div class="col-md-12">
						<nav aria-label="breadcrumb">
		<ol class="breadcrumb cus-breadcrumb">
		
			<li class="breadcrumb-item">
			<a href="/SV/claimLoadgementApprovalCGTMSE.html?MLIName=${MLIID}&CNT=${CNT}">claimLoadgement
					Approval</a></li>
			<li class="breadcrumb-item active" aria-current="page">Claim Return Details</li>
		</ol>
		</nav>
						
<%String MLIID=(String)session.getAttribute("MLINameID");
  String CNT=(String)session.getAttribute("CNT12");
%>
						<form:form method="GET" id="A">
							<table id="myTable"
								class="table table-bordered table-hover cus-table mt-10 mb-0 danRpDataTable">
								<h3 class="text-center "><font color="green">${msg}</font></h3>
								
									<input type="hidden" path="mliName" id ="mli" value="${MLIID}"/> 
									<input type="hidden" path="Cnt" id ="Cnt" value="${CNT}"/> 
										<input type="hidden" path="Cnt" id ="Cnt" value="${claimRefNo}"/> 
								<thead>
									<tr>
										<th class="tableHeader1">SR.NO.</th><!--
											<th class="tableHeader1">RemarkID</th>
										--><th class="tableHeader1">Return Remarks</th>
										<th class="tableHeader1">SELECT <form:checkbox path="selectAll" id="select_all"
												onchange="selectAllCheckBox();" cssClass="select_all" />
										</th>

									</tr>
								</thead>
								<c:if test="${!empty danDetailList}">
									<%
										int counter = 0;
									%>
									<c:forEach items="${danDetailList}" var="mliList"
										varStatus="loopStatus">
										<%-- <tr <% if(counter%2==0){%>bgcolor="silver" <%}%>> --%>
										<tr>
											<td><%=counter + 1%></td><!--
                                            <td><c:out value="${mliList.remarkID}" /></td>
											--><td><c:out value="${mliList.remarks}" /></td>
                                         
											<td align="center"><form:checkbox path="chcktbl"
													value="${claimRefNo}@${mliList.remarkID}"
													onchange="uncheckSelectAll();" cssClass="chcktbl" /></td>

											<%
												counter += 1;
											%>
										</tr>
									</c:forEach>
								</c:if>

							</table>
							<div class="d-block"
								style="margin: 10px auto 0; text-align: center;">
								<div class="d-inlineblock">
									<input type="submit" value="Save"
										id="onSubmit" onclick="clickToInitiate();"
										class="btn btn-reset" /> 
								</div>
							</div>
		<div>					
	<label>Checker Return Remark:
	</label> <label class="d-block"><form:textarea path="returnReasonsRemarks"  id="returnReasonsRemarks" size="20" class="form-control cus-control" /></label>
		<input type="submit" value="SubmitRemark" id="onSubmit" onclick="clickToupdateRemark();" class="btn btn-reset" /> 
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
	function clickToInitiate() {
	
		$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');

		/*  if ($selectedCBs.length === 0) {
			alert("Please select the record.");
			//return;
		} else { //	*/
			
			var mli=document.getElementById('mli').value;
			var cnt=document.getElementById('Cnt').value;

			document.getElementById('A').action = "/SV/submitForClaimReturnResons.html";
			document.getElementById('A').submit();

		//}
	}
	function buckToHome() {
		//alert('Back');

		document.getElementById('A').action = "/SV/nbfcMakerHome.html";
		document.getElementById('A').submit();
	}

	function submitForApproval() {
		//alert('Back');

		$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');

		if ($selectedCBs.length === 0) {
			alert("Please select the record for approval.");
			//return;
		} else { //	var accept=document.getElementById('accept').value;
			//var reject=document.getElementById('reject').value;

			//alert("selected row size is "+$selectedCBs.length);

			var mli=document.getElementById('mli').value;
			var cnt=document.getElementById('Cnt').value;

			document.getElementById('A').action = "/SV/submitForClaimReturnResons.html";
			document.getElementById('A').submit();

		}
		var value = document.getElementById('checkBox').value();
		alert(value);
		if (value != null) {

			var mli=document.getElementById('mli').value;
			var cnt=document.getElementById('Cnt').value;

			document.getElementById('A').action = "/SV/submitForClaimReturnResons.html";
			document.getElementById('A').submit();
		} else {
			alert('Please select Portfolio Name');
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
	
	function clickToupdateRemark(){
		document.getElementById('A').action = "/SV/submitReturnResonsRemark.html";
		document.getElementById('A').submit();
		
	}
</script>


</html>



