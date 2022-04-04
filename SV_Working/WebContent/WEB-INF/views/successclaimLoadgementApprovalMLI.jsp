<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>successclaimLoadgementApprovalMLI</title>
		<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/jquery-1.11.1.js"></script>
		<link href="<%=request.getContextPath()%>/css/stylesheet.css" rel="stylesheet" type="text/css">
		<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>
		<%
			String s = (String) request.getAttribute("SName");
		%>
	<Style>
	.Decl-msg{	width:100%; display:block;	}
	.Decl-msg h2{	font-size: 16px; margin: 10px 0;	color: #da294d;	}
	.Decl-msg h3:first-child {    font-size: 22px; margin: 5px 0; padding:0 0 15px;  position:relative;}
	.Decl-msg h3:first-child  a{ color:#da294d !important;}
	.Decl-msg h3:first-child:after{	content:'';	position:absolute;	width: 150px;
   	 bottom: 0px;   height: 2px;  left: 44%; background-color:gray; }	
	.Decl-msg h3:nth-child(2){	    font-size: 16px; margin: 6px 0; font-style:italic;	}
	.Decl-msg p{ font-size: 16px; margin: 6px 0;     }
	.under_msg{	width:100%; display:block;	border-bottom: 1px dotted gray;	}
	.under_msg  h2{	font-size: 20px; font-weight: 500; padding: 15px 0 15px; margin: 0 0; position:relative; 	}
	.under_msg  h2:after{	content:'';	position:absolute;	width: 150px;
    bottom: 0px;   height: 2px;  left: 44%; background-color:gray; }
 	 ol.under_list{	width:100%; display:block;	}
	 ol.under_list li{	font-size: 16px; padding:3px 0;	}
	.sign_details{width:100%; display:block;  padding-top: 15px;}
	.sign_details h4{	font-size:16px;font-style: italic;  font-weight: 700; margin:5px 0;	}
	.sign_details p{  	font-size:16px; }
	</Style>
	</head>
	<body bgcolor="#E0FFFF">
		<div class="main-section">
			<div class="container-fluid">
				<div>	
					
					<div class="frm-section">
						<div class="col-md-12 col-xs-12">
						<div class="Decl-msg">
							<h3 class="text-center "><font color="green">${message} </font></h3>
								<div class="clearfix"></div>
							<h3 class="text-center "><font color="Red">${messageR} </font></h3>
							
							
						</div> 
				
													
						</div>
					</div>
				</div>
			</div>
		 </div>
	</body>
</html>