<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%
    String path = request.getContextPath();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/FaviIcon.ico" />
<title><tiles:insertAttribute name="title" ignore="true"></tiles:insertAttribute>
</title>

<meta charset="utf-8">  
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans|Roboto" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
  <script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
  <link href="<%=request.getContextPath()%>/css/customstyle.css" type="text/css" rel="stylesheet">
   <link href="<%=request.getContextPath()%>/css/font-awesome.min.css" type="text/css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<link href="https://cdn.datatables.net/fixedcolumns/3.2.4/css/fixedColumns.dataTables.css" type="text/css" rel="stylesheet">
<script>
function sessionTimeOut(){
	
	$(function () {
		var sessionValue="<%=session.getAttribute("uName")%>";
	   
	    var _redirectUrl = '/SV/Login.html';
	    var _redirectHandle = null;

	    function resetRedirect() {
	        if (_redirectHandle) clearTimeout(_redirectHandle);
	        if(sessionValue=='null'){
	        _redirectHandle = setTimeout(function () {
		        //alert("! Session timeout !");
	            window.location.href = _redirectUrl;
	        },0);
	        }
	    }

	    $.ajaxSetup({ complete: function () { resetRedirect(); } }); // reset idle redirect when an AJAX request completes
			resetRedirect();
			
	});
}
</script>
<style type="text/css">
body{
/*background-color:#ececec";*/
width:100%;
display:block;
background-color:#ececec !important;
/*  background-image:url(images/bg4.jpg); */
/*  background-image:url(images/bg-logo.png); */
background-size:35%;
height:100vh !important;
}
</style>
</head>
<body onload="sessionTimeOut();">
<div id="header-section">
<tiles:insertAttribute name="header"></tiles:insertAttribute>
</div>
<div  id="body-section">
<tiles:insertAttribute name="body"></tiles:insertAttribute>
</div>
<div  id="footer-section">
<tiles:insertAttribute
					name="footer"></tiles:insertAttribute>
</div>




<!--  <script src="https://code.jquery.com/jquery-3.5.1.min.js" type="text/javascript"></script> -->
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js" type="text/javascript"></script>
 <!--  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->
<script src="js/jquery-ui.js" type="text/javascript"></script>
	<script>
	    $(document).ready(function(){
		    
		  	        $("#myModal").modal('show');
	    });
	</script>

<script>
$( function() {
    $( "#ratingDate" ).datepicker();
  } );
</script>

<!-- <script src="css/bower_components/jquery/jquery.min.js"></script> -->
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/fixedcolumns/3.2.4/js/dataTables.fixedColumns.min.js" ></script> 
<script>
$('#myTable,myTable2').DataTable( {
    "pagingType": "full_numbers",
    responsive: true

});
$('#myTable1').DataTable( {
      scrollX:        true,
      scrollCollapse: true,
      paging:         true,
      fixedColumns:   {
          leftColumns: 5,
          
      }		
 
	});
</script>
</body>
</html>