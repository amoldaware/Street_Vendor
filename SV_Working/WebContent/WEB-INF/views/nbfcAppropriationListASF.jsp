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
<LINK href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">


</head>

<body>

 <div class="main-section">

<div class="container-fluid">
<!-- <div class="row"> -->
	<div>
<nav aria-label="breadcrumb">
  <ol class="breadcrumb cus-breadcrumb">
    <li class="breadcrumb-item"><a href="/SV/getAppropriationDataASF.html">Appropriation1</a></li> 
    <li class="breadcrumb-item active" aria-current="page">NBFC Appropriation</li>
  </ol>
</nav>
	
	<div class="tbl-details">
		<div class="col-md-12">
			
				<h3 class="heading text-center">NBFC Appropriation</h3>
		
		<div class="d-inlineblock float-right">
		<!-- <label style="padding-top:8px; font-weight:100;">Search :</label> -->
		<input type="text" id="myInput" onkeyup="myFunction()" class="form-control cus-control" style="width:200px; display: inline; height: 34px; border-radius: 4px;
  		  background-color: #ffffff;" placeholder="Search Data.." title="Type in a name">
    		
    		<button style="border:none !important; cursor: not-allowed;">
    			<img src="images/excel.png" alt="" data-toggle="tooltip" title="Export To Excel">
    		</button>			
		</div>
				
		<form:form method="POST" id="A" >	
		<c:if test="${!empty dataList}">
				<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-10 danRpDataTable">		
				
				
				
				<thead>
				<tr>
							<th >SR.NO.</th>				
					<th >MEMBER ID</th>					
					<th >NAME OF NBFC</th>
					<th >PAYMENT ID</th>
					<th >INWARD AMOUNT IN RS.</th>
					<th >ALOCATED AMOUNT IN RS.</th>
					<th >INSTRUMENT NO</th>
					<th >INSTRUMENT AMOUNT IN RS. </th>
					<th >INSTRUMENT DATE </th>
					<th >APPROPRIATION ALL </br><form:checkbox path="selectAll" id="select_all" onchange="selectAllCheckBox();" cssClass="select_all"/></th>
				</tr>
				</thead>
				
				<% int counter=0;%>
				<c:forEach items="${dataList}" var="dataList">
					<%-- <tr <% if(counter%2==0){%>bgcolor="silver" <%}%>> --%>
					<tr>
					<td><c:out value="<%=counter+1%>" /></td>
					<td ><c:out value="${dataList.memberId}" /></td>
					<td ><c:out value="${dataList.memberName}" /></td>
					<td ><c:out value="${dataList.rpNumber}" /></td>
					<td><c:out value="${dataList.inwardAmount}" /></td>
					<td><c:out value="${dataList.allocatedAmount}" /></td>
					<td><c:out value="${dataList.virtualAccountNumber}" /></td>
					<td><c:out value="${dataList.instrumentAmount}" /></td>
					<td><c:out value="${dataList.instrumentDate}" /></td>

					<td align="center"><form:checkbox path="chcktbl"
						value="${dataList.rpNumber}" onchange="uncheckSelectAll();"
						cssClass="chcktbl" /><input type="hidden" id="approveCount" name="approveCount" value="hiddenValue"> </td>					
					<%  counter+=1;%>
				</c:forEach>
				
			
					
	
				</table>
				
				<div class="d-block text-center">
				<input class="btn btn-reset" style="width:100px"	readonly="true"	id="onSubmit" onclick="clickToInitiate();"  value="Approve" >
			<!-- 	<input class="btn btn-reset" style="width:100px"	readonly="true"	id="onReject" onclick="reject();" value="Return" > -->
				</div>
				</c:if>	
				
				
				
	<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0 danRpDataTable">	
		<c:if test="${empty dataList}">
		
		<thead>
		<tr>				
								<th >SR.NO.</th>				
					<th >MEMBER ID</th>					
					<th >NAME OF NBFC</th>
					<th >PAYMENT ID</th>
					<th >INWARD AMOUNT IN RS.</th>
					<th >ALOCATED AMOUNT IN RS.</th>
					<th >INSTRUMENT NO</th>
					<th >INSTRUMENT AMOUNT IN RS. </th>
					<th >INSTRUMENT DATE </th>
					<th>APPROPRIATION ALL</br><form:checkbox path="selectAll" id="select_all" onchange="selectAllCheckBox();" cssClass="select_all"/></th>
					</tr>
		</thead>					
				<tr >
					<tr>
					<td></td>				
					<td></td>
					<td ></td>
					<td ></td>
							<td ></td>		
					<td align="center">Data not available</td>					
					
					<td ></td>
					<td ></td>
					<td ></td>					
					<td ></td>	
					</tr>
		</c:if>
	
	</table>
	
				
		
			</form:form>
		</div>
	</div>
	
	</div>	
</div>
</div> 
	
	<!-- <div
		STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;"> -->
	<%-- 	<form:form method="POST" action="" id="A">
	<h3>NBFC Appropriation</h3>
	<hr>
	<div	STYLE="height: 450px; width: 1030px; font-size: 12px; overflow: auto;">
		<table  cellpadding=5 cellspacing=5 class="danRpDataTable" align=center width=90%  >			
				
				
				<c:if test="${!empty dataList}">
				<tr>
					<td class="tableHeader1">Sr.No</td>				
					<td class="tableHeader1" width="30%">Member Id</td>					
					<td class="tableHeader1" width="30%">Member Name</td>
					<td class="tableHeader1" width="30%">Payment Id</td>
					<td class="tableHeader1">Inward Amount In Rs</td>
					<td class="tableHeader1">Allocated Amount In Rs.</td>
					<td class="tableHeader1">Instrument Number</td>
					<td class="tableHeader1">Instrument Amount In Rs. </td>
					<td class="tableHeader1">Instrument Date </td>
					<td class="tableHeader1">Appropriation All</br><form:checkbox path="selectAll" id="select_all" onchange="selectAllCheckBox();" cssClass="select_all"/></td>
				</tr>
				
				<% int counter=0;%>
				<c:forEach items="${dataList}" var="dataList">
					<tr <% if(counter%2==0){%>bgcolor="silver" <%}%>>
					<td><c:out value="<%=counter+1%>" /></td>
					<td width="30%"><c:out value="${dataList.memberId}" /></td>
					<td width="30%"><c:out value="${dataList.memberName}" /></td>
					<td width="30%"><c:out value="${dataList.rpNumber}" /></td>
					<td><c:out value="${dataList.inwardAmount}" /></td>
					<td><c:out value="${dataList.allocatedAmount}" /></td>
					<td><c:out value="${dataList.virtualAccountNumber}" /></td>
					<td><c:out value="${dataList.instrumentAmount}" /></td>
					<td><c:out value="${dataList.instrumentDate}" /></td>

					<td align="center"><form:checkbox path="chcktbl"
						value="${dataList.rpNumber}" onchange="uncheckSelectAll();"
						cssClass="chcktbl" /><input type="hidden" id="approveCount" name="approveCount" value="hiddenValue"> </td>					
					<%  counter+=1;%>
				</c:forEach>
				<tr><td class="tableHeader1"></td>				
					<td class="tableHeader1"></td>
					<td class="tableHeader1"></td>					
					<td class="tableHeader1"></td>
					<td class="tableHeader1"></td>				
					<td class="tableHeader1"></td>
					<td class="tableHeader1"></td>					
					<td class="tableHeader1"></td>
					<td class="tableHeader1"></td>					
					<td class="tableHeader1"><div id="count"></div></td>
				</tr>
				<tr >
					<td></td>				
					<td></td>				
					<td></td>					
					<td ></td>
					<td></td>				
					<td></td>				
					<td></td>					
					<td ></td>
					<td ><input class="button"	readonly="true"	id="onSubmit" onclick="clickToInitiate();"  value="Save" align=""></td>
					<td ><input class="button"	readonly="true"	id="onReject" onclick="reject();" value="Reject" align=""></td>	
					
					</tr>
				</c:if>
		<c:if test="${empty dataList}">
		<tr>
					
				<td class="tableHeader1">Sr.No</td>				
					<td class="tableHeader1" width="30%">Member Id</td>					
					<td class="tableHeader1" width="30%">Member Name</td>
					<td class="tableHeader1" width="30%">Payment Id</td>
					<td class="tableHeader1">Inward Amount In Rs</td>
					<td class="tableHeader1">Allocated Amount In Rs.</td>
					<td class="tableHeader1">Instrument Number</td>
					<td class="tableHeader1">Instrument Amount In Rs. </td>
					<td class="tableHeader1">Instrument Date </td>
					<td class="tableHeader1">Appropriation All</br><form:checkbox path="selectAll" id="select_all" onchange="selectAllCheckBox();" cssClass="select_all"/></td>
					</tr>
				<tr >
					<tr>
					<td></td>				
					<td></td>
					<td ></td>
					<td ></td>
							<td ></td>		
					<td align="center">Data not available</td>					
					
					<td ></td>
					<td ></td>
					<td ></td>					
					<td ></td>	
					</tr>
		</c:if>
	
	</table>
	</div> 
	</form:form>--%>
	<!-- </div> -->

</body>
<script type="text/javascript">



function clickToInitiate(){

	$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
	
    if ($selectedCBs.length === 0) {
        alert("Please select the record for approval.");
        //return;
    }else{    //	var accept=document.getElementById('accept').value;
    	//var reject=document.getElementById('reject').value;
 	
    	// alert("selected row size is "+$selectedCBs.length);
    	 var approveCount;
    	 	if (document.getElementById('select_all').checked == true) {   
        	 	//alert("hellow ",$selectedCBs.length-1); 	       
    		 approveCount=$selectedCBs.length-1;
    	    } else {    	       
    	    	approveCount=$selectedCBs.length;    	    	
    	    }
    	 	
    	 	var hiddenValue=document.getElementById('approveCount').value=approveCount;
    	 	//alert("hellow "+hiddenValue); 
    		document.getElementById('A').action = "/SV/saveAppropriationData.html";
    		document.getElementById('A').submit();
    	
	
    }
}

function selectAllCheckBox() {
    if (document.getElementById('select_all').checked == true) {
        $('.chcktbl').each(function() {
            this.checked = true;
        });
        $selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
    	var count=$selectedCBs.length-1;
    	document.getElementById('count').innerHTML=count;
    } else {
        $('.chcktbl').each(function() {
            this.checked = false;
        });
        $selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
    	var count=$selectedCBs.length;
    	document.getElementById('count').innerHTML=count;
    }

}
function uncheckSelectAll()
{
	$('.chcktbl').click(function () {
        if (this.checked == false) {
            $('#select_all').attr('checked', false);
        }
        $selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
    	var count=$selectedCBs.length;
    	document.getElementById('count').innerHTML=count;
    });
	$selectedCBs = $('.danRpDataTable input[type="checkbox"]:checked');
	var count=$selectedCBs.length;
	document.getElementById('count').innerHTML=count;
	
}




</script>

</html>



