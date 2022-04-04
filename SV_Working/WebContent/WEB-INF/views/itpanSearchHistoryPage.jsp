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
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<title>Portfolio Creation</title>
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
<script type="text/javascript">
	/* function noBack() {
		window.location.hash = "no-back-button";
		window.location.hash = "Again-No-back-button";//again because google chrome don't insert first hash into history
		window.onhashchange = function() {
			window.location.hash = "no-back-button";
		};
	} */
	/* window.onload=function(){                                                                      
	 var i=0; var previous_hash = window.location.hash;                                           
	 var x = setInterval(function(){                                                              
	 i++; window.location.hash = "/noop/" + i;                                                  
	 if (i==10){clearInterval(x);                                                               
	 window.location.hash = previous_hash;}                                                
	 },10);}; */
</script>

</head>
<%
	String uesrName=(String)session.getAttribute("userId");
   /*  if(uesrName==null){	
		response.sendRedirect("login");		
	} */
    System.out.println("uesrName....."+uesrName);
%>
<body onload="noBack();">

	<div class="main-section">
		<div class="container-fluid">
			<!-- <div class="row"> -->
			<div>
				<form:form method="POST" id="A"
					action="/SV/itPanSearchHistory.html" class="form-horizontal">
					<div class="frm-section">
						<div class="col-md-12">

							<!-- 	<h5 style=" margin: 5px 0; color: #4ba4bf;"></h5> -->
							<h5 class="error1 mtb-0">
								<strong>${message}</strong>
							</h5>
							<h5 class="sub-head">
								<strong>ITPAN Search History</strong>
							</h5>

							<div class="col-md-2 col-sm-4 col-xs-12">
								<div class="form-group">
									<div class="col-md-12 prl-10">
										<form:label path="itPan">Enter ITPAN :<span
												style="color: red;">*</span>
										</form:label>
										<form:input path="itPan" id="itPan"
											class="form-control cus-control" style="text-align: left"
											size="12" />
										<form:errors path="itPan" cssClass="error" />
									</div>
									
								</div>
								
							</div>

							
	
						</div>
<div>
                                    <form:checkbox path="confidentialFlagStatus" value="Y" />
									<span style="color: red;">* The information accessed is strictly for internal use and shall
									be kept confidential. </span><br>
									<form:errors path="confidentialFlagStatus" cssClass="error" />
									<form:checkbox path="creditDecisionFlagStatus" value="Y" />
									<span style="color: red;">*CGTMSE shall not be held accountable for credit decision taken
									based on the information shared.</span> <br>
									<form:errors path="creditDecisionFlagStatus" cssClass="error" />
									<form:checkbox path="changeSubsequentlyFlagStatus" value="Y" />
									<span style="color: red;">*The information accessed is at a given point of time and may
									change subsequently due to subsequent modifications/changes.</span> <br>
									<form:errors path="changeSubsequentlyFlagStatus" cssClass="error" />
									<form:checkbox path="noCostFlagStatus" value="Y" />
									<span style="color: red;">*Presently the information would be made available at no cost.</span> <br>
									<form:errors path="noCostFlagStatus" cssClass="error" />
									
									<form:checkbox path="cibileCheckFlagStatus" value="Y" />
									<span style="color: red;">*The information is not a substitution to CIBIL check and any
									other due deligence which should be carried out as per the
									banks policy. </span><br>
									<form:errors path="cibileCheckFlagStatus" />
									</div>
                             <div class="d-inlineblock mt-25">
								<input type="submit" value="Search" class="btn btn-reset" /> <input
									type="submit" value="Back" class="btn btn-reset" />
							</div>

						<c:if test="${!empty dataListReturn}">

							<table align="left" border="1" id="myTable"
								class="table table-bordered table-hover cus-table mt-10 mb-0">
								
								<tr>
									<th>BANK NAME</th>
									<th>UNIT NAME</th>
									<th>CONSTITUTION</th>
									<th>MLI ID</th>
									<th>CGPAN</th>
									<th>GUARANTEE AMOUNT</th>
									<th>APP STATUS</th>
									<th>NPA STATUS</th>
									<!-- <th>Scheam Name</th> -->
								</tr>

								<c:forEach items="${dataListReturn}" var="dataList">
									<tr>
										<td><c:out value="${dataList.memBankName}" /></td>
										<td><c:out value="${dataList.ssiUnitName}" /></td>
										<td><c:out value="${dataList.ssiCinstititupan}" /></td>
										<td><c:out value="${dataList.mliId}" /></td>
										<td><c:out value="${dataList.cgpanNumber}" /></td>
										<td><c:out value="${dataList.guaranteeAmount}" /></td>
										<td><c:out value="${dataList.appStatus}" /></td>
										<td><c:out value="${dataList.npaDate}" /></td>
										<%-- <td><c:out value="${dataList.scheamName}" /></td> --%>
										
									</tr>
								</c:forEach>
							</table>
						</c:if>
						<c:if test="${empty dataListReturn}">

							<table align="left" border="1" id="myTable"
								class="table table-bordered table-hover cus-table mt-10 mb-0">
								
								<tr>
									<th>BANK NAME</th>
									<th>UNIT NAME</th>
									<th>CONSTITUTION</th>
									<th>MLI ID</th>
									<th>CGPAN</th>
									<th>GUARANTEE AMOUNT</th>
									<th>APP STATUS</th>
									<th>NPA STATUS</th>
									<!-- <th>Scheam Name</th> -->
								</tr>

								<c:forEach items="${dataListReturn}" var="dataList">
									<tr>
										<td></td>
										<td></td>
										<td>SSSSS</td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<%-- <td><c:out value="${dataList.scheamName}" /></td> --%>
										
									</tr>
								</c:forEach>
							</table>
						</c:if>
					</div>
				</form:form>

			</div>
		</div>
	</div>
</body>



</html>



