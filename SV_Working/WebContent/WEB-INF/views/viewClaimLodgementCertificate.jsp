<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
					<nav aria-label="breadcrumb">
	  					<ol class="breadcrumb cus-breadcrumb">
	   						<li class="breadcrumb-item"><a href="claimLodgementInputForm.html">Claim Loadgement</a></li>  
	    					<li class="breadcrumb-item active" aria-current="page">Declaration & Undertaking</li>
	  					</ol>
					</nav>
					<div class="frm-section">
						<div class="col-md-12 col-xs-12">
						<div class="Decl-msg">
							<h3 class="text-center "><a href="#">Declaration and Undertaking by MLI (NBFCs)</a></h3>
						<!-- 	<h3 class="text-center">(To be signed by the officer not below the rank of Assistant General Manager or of equivalent rank of NBFC ).</h3> 	 -->					
							<p class=" text-center pb-6">
								<strong> Declaration –</strong> We declare that the information given above is true and correct in every respect. 
								We further declare that there has been no fault or negligence on  the part of the MLI or any of its officers in conducting the account. 
								We also declare that the officer preferring the claim on behalf of MLI is having the authority to do so.
							</p>						
							<h2 class="  text-center pb-6">
								We hereby declare that no fault or negligence has been pointed out by internal / external auditors, inspectors of CGTMSE or its agency in respect of the account(s) for which claim is being preferred.
							</h2>
						</div> 
						<div class="under_msg">
							<h2 class="  text-center" >Undertaking – We hereby undertake: –</h2>
							<ol class="under_list">
								<li>To pursue all recovery steps including legal proceedings.</li>
								<li>	To report to CGTMSE the position of outstanding dues from the borrower on half-yearly basis (as on 31st March and 30th September of 
									each year till final settlement of guarantee claim by CGTMSE).</li>
								<li>To refund to CGTMSE the claim amount settled along with interest, if in the view of CGTMSE we fail or neglect to take any action for 
									recovery of the guaranteed debt from the borrower or any other person from whom the amount is to be recovered.</li>
								<li>On payment of claim by CGTMSE, to remit to CGTMSE all such recoveries, after adjusting towards the legal expenses incurred for recovery 
									of the amount, which we or our agents acting on our behalf, may make from the person or persons responsible for the administration of debt, or otherwise, in respect of the debt due from him / them to us.</li>
							 </ol>												
						</div>
						<div class="sign_details">									
							<ol class="under_list">
								 <li>	CGTMSE reserves the right to ask for any additional information, if required.</li>
								 <li>	GTMSE reserves the right to initiate any appropriate action / appoint any person / institution etc. 
									 	to verify the facts as mentioned above and if found contrary to the declaration, reserves the right to treat the claim under CGTMSE invalid.
								</li>
							</ol>
							</div>									
						</div>
					</div>
				</div>
			</div>
		 </div>
	</body>
</html>