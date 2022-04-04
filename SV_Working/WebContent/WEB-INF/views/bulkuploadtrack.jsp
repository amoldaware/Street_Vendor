<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MLI User Registration Form</title>

<%-- <script src="<%=request.getContextPath()%>/js/jquery.js"></script> --%>
<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script>
<%-- <script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script> --%>
<%-- <LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css"> --%>





<%
	String s = (String) request.getAttribute("SName");
%>

<script type="text/javascript">

$(document).ready(function(){
  $("#refreshid").click(function(){
	  location.reload(true); 
     // alert('Reloading Page'); 
  });
});

</script>

<script type="text/javascript">
	$(window).load(function() {
		$(".loader").fadeOut("slow");
	})
</script>

</head>
<body>
	<div class="main-section">

		<div class="container-fluid">
			<TABLE>
				<tr>
					<!-- 	<TD class="ColumnBackground"><font style="color: red; font-size: 11px;">Notes : </font></TD> -->
					<!-- <td class="ColumnBackground" > -->
					<div>
						<br>
						<br> <br> <b><font
							style="color: blue; font-size: 11px;">P : Pending</font></b> <br>
						<b><font style="color: red; font-size: 11px;">I : In
								Progress</font></b> <br> <b><font
							style="color: green; font-size: 11px;">C : Completed</font></b> <br>
						<!-- <font style="color: red; font-size: 11px;">Error - </font> 
									<font style="color: black; font-size: 11px;">Data not as per Excel Template.</font><br> 
								 -->
					</div>
					<!-- </TD> -->
				</TR>
			</TABLE>
			

					

			
			<div>
			<div style="text-align-last: right;">
						<input type="Button" value="Refresh" id="refreshid" style="color: white;background-color: orangered;">
					</div>
				<div class="frm-section">
					<div class="col-md-12">
						

													<c:if test="${!empty bulklist}">

								<!-- <div class="col-md-3 mb-10 float-right">
		<label style="width:30%; float:left; text-align:center;">Search :</label>
		<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control"  style="width:70%; float:left;" placeholder="Search for names.." title="Type in a name">
		</div> -->

								<table id="myTable"
									class="table table-bordered table-hover cus-table mb-0">
									<thead>
										<tr>
											<th>SR.NO.</th>
											<th>MEMBER ID</th>
											<th>UPLOAD ID</th>
											<!-- <th>PROCESS NAME</th> -->
											<th>UPLOADED BY</th>
											<th>UPLOADED DATE</th>
											<th>STATUS</th>
											<th>SUCCESS COUNT</th>
											<th>UNSUCCESS COUNT</th>
											<!-- <th>MODIFY DATE</th> -->
											<th>IN EXCEL COUNT</th> 
											
										</tr>
									</thead>
									<%
										int counter = 0;
									%>
									<c:forEach items="${bulklist}" var="employee">
										<tr>
											<td><%=counter + 1%></td>

											<td><c:out value="${employee.member_id}" /></td>
											<td><c:out value="${employee.upload_id}" /></td>
											<%-- <td><c:out value="${employee.process_name}" /></td> --%>
											<td><c:out value="${employee.upload_by}" /></td>
											<%-- <td><c:out value="${employee.upload_date}" /></td> --%>
											<td><fmt:formatDate pattern="dd/MM/yyyy hh:mm" value="${employee.upload_date}" /></td>
											<td><c:out value="${employee.status}" /></td>
											<td><a href="exportToFile.html?fileType=XLSType&FlowLevel=SuccessDataList&uploadid=${employee.upload_id}&uploadflag=S" ><c:out value="${employee.status_cnt}" /></a></td>
											<td><a href="exportToFile.html?fileType=XLSType&FlowLevel=SuccessDataList&uploadid=${employee.upload_id}&uploadflag=E" ><c:out value="${employee.unsuccess_cnt}" /></a></td>
											<%-- <td><c:out value="${employee.modified_date}" /></td> --%>
											 <td><c:out value="${employee.excel_cnt}" /></td> 
											
											<%
												counter += 1;
											%>
										</tr>

									</c:forEach>
								</table>
							

							</c:if>



						</div>
					</div>
				</div>

			</div>
		</div>

	
	<script src="js/jquery-3.5.1.min.js" type="text/javascript"></script>

	<div class="loader"></div>
	<script type="text/javascript">
		$(window).load(function() {
			$(".loader").fadeOut("slow");
		});
		
	</script>
</body>
</html>