<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.util.Random"%>
 
 <%
    String path = request.getContextPath();

%>
 
 
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
 		<title>Street Vendor ( CGTMSE)</title>
    <!-- Bootstrap CSS -->
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/css/custom.css" rel="stylesheet" type="text/css">
    <link href="css/custom1.css" rel="stylesheet" type="text/css">
   <link href="<%=request.getContextPath()%>/css/responsive.css" rel="stylesheet">    
     <link href="<%=request.getContextPath()%>/css/font-awesome.css" rel="stylesheet">        
    <link href="<%=request.getContextPath()%>/css/reset.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/login.css" rel="stylesheet">
   <link href="css/responsive.css" rel="stylesheet">    
     <link href="css/font-awesome.css" rel="stylesheet">        
    <link href="css/reset.css" rel="stylesheet">
	 <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,400i,600,600i|Raleway:400,400i,500,500i,600|Roboto:400,400i,500,500i&display=swap" rel="stylesheet"> <link href="https://fonts.googleapis.com/css2?family=Arvo:wght@400;700&display=swap" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
   <style type="text/css">
   	/*.header-login p a:nth-child(1){border-right: none;}*/
  /* 	#dashboardModal >.modal-dialog{    width: 100% !important;   margin: 0 auto;  max-width: 100% !important;} 
   	marquee{
         font-size: 17px;
	    font-weight: 600;
	    color: #ff0000;
	    padding: 5px;
	    background-color: #eac29a;
	    margin: 0;
      }*/
      #footer{  background: #535353 !important;}
   </style>
  </head> 
<body oncontextmenu="return false;">

<div class="header-contact">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<ul>
					<li><a href="mailto:querysvs@cgtmse.in"><i class="fa fa-envelope" aria-hidden="true"></i> querysvs@cgtmse.in</a></li>
					<li><a href=""><i class="fa fa-phone-square" aria-hidden="true"></i> Cgtmse Support : 022 67531293/ 67221438 / 67531189   Mobile No. 9321702101</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>
<header id="header-info">
	<div class="container">
		<div class="row justify-content-md-end">
			<div class="col-sm-12">

				<div class="header-info col-50">
				<!-- <p><a href="mailto:eclgs@ncgtc.in"><i class="fa fa-envelope" aria-hidden="true"></i> eclgs@ncgtc.in</a> 
					<i class="fa fa-phone-square" aria-hidden="true"></i> 9987257699 / 9664509037 / 8655180208 </p> -->
					<a href="#" target="_blank"><img src="images/udaanlogo.svg" alt="cgtmse street Vendor"></a>
				</div>
				<div class="header-login col-50">
					<p> 
					<!-- 	<a type="button" data-toggle="modal" data-target="#dashboardModal"><i class="fa fa-tachometer" aria-hidden="true"></i> Dashboard	</a> -->
						 <a href="${pageContext.request.contextPath}/faq.html" target="_blank"  style="margin-right: 5px"> FAQ's </a>
						<a href="Login.html"> Login	</a>
						<!-- <a href="contact.html">  Grievances – ECLGS	</a> -->
					</p>
				</div>

			</div>
		</div>
	</div>
</header>
<section id="header-detail">
	   <div class="banner">
		 	<img src="images/StreetVendorBanner.jpg" alt="credit Guarantee scheme pm svnidhi">
		</div>
</section>
 <!-- <marquee>The ECLGS application shall be under maintenance from 9 pm of May 30, 2020 upto 10am of May 31, 2020</marquee> -->
<section id="sec-intro">
	<div class="container">
		<div class="row">

			<div class="col-lg-6 col-md-6 col-sm-12">
				<div class="container-box">
					<div class="intro-details">
					 	<img src="images/streetVendorObject.png" alt="street vendors objects">
					</div> 
				 </div>
			</div>

			<div class="col-lg-6 col-md-6 col-sm-12">
				<div class="container-box">
					<div class="intro-details">
					<h2>Objective of the Scheme</h2>
					<p> Launched by Government of India to support urban street vendors</p>
					<!-- <p>Launched by Government of India as a special scheme to strengthen the crisis ridden credit delivery system.</p> -->
					<p>To provide Graded Guarantee coverage to Member Lending Institution to enable them to extend credit facilities to Street Vendors to meet their working capital requirements, as per details:</p> 
					<ol>
						<li>First Loss Default (Up to 5%): 100%  </li>
						<li>Second Loss (beyond 5% up to 15%): 75% of default portfolio </li>
					</ol>
					</div>

				 </div>
			</div>
		</div>	
	</div>
</section>

<section id="sec-Features">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="container-box">
					<div class="objective-details">
						 <h2>Salient Features of the Scheme</h2>
					</div>
				</div>
			</div>
			<div class="col-lg-4 col-md-6 col-sm-12">
				<div class="container-box"> 
					<div class="feature-detail">
						 <img src="images/creditFacility.png" alt=""> 
						<h3> Credit Facility </h3>
						<p> The amount of Working Capital(WC) loan extended to urban street vendors would be upto Rs.10,000/-</p>
						<div>
						 
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-4 col-md-6 col-sm-12">
				<div class="container-box"> 
					<div class="feature-detail">
						 <img src="images/eligibleMli.png" alt=""> 
						<h3> Eligible MLI </h3>
						<p> All Scheduled Commercial Banks, Regional Rural Banks (RRBs), Small Finance Banks (SFBs), Cooperative Banks, Non-Banking Finance Companies (NBFCs), Micro Finance Institutions (MFIs) & SHG Banks established in some States/UTs e.g. Stree Nidhi etc.</p>
						<div>
						 
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-4 col-md-6 col-sm-12">
				<div class="container-box">
					<div class="feature-detail">
					 	<img src="images/eligible.png" alt="">  
						<h3>Eligible Borrower </h3>
						<p> All street vendors engaged in vending in urban areas as on or before March 24, 2020. </p>
						<div>
						 
						</div>	
					</div>
				</div>
			</div>	
 
			 <div class="col-lg-4 col-md-6 col-sm-12">
				<div class="container-box">
					<div class="feature-detail">
					  <img src="images/tenure.png" alt="">  
						<h3>Tenure</h3>
						<p> The tenure of working capital facility would be 12 months</p>
						<div>
						 
						</div>
					</div>
				</div>
			</div>
			<div class="col-lg-4 col-md-6 col-sm-12">
				<div class="container-box">
					<div class="feature-detail">
						<img src="images/fee.png" alt="">
						<h3>Guarantee Fee / charge</h3>
						<p> No Guarantee Fee / Charge to be charged by MLIs / CGTMSE</p>
						<div>
							
						</div>
					</div>
				</div>
			</div>	 
			 <div class="col-lg-4 col-md-6 col-sm-12">
				<div class="container-box">
					<div class="feature-detail">
					 <img src="images/gradedGuarantee.png" alt="">  
						<h3>Graded Guarantee Coverage</h3>
						<p> First Loss Default (Up to 5%): 100%  and Second Loss (beyond 5% up to 15%): 75% of default portfolio </p>
						<div>
							 
						</div>
					</div>
				</div>
			</div>

		</div>	
	</div>
</section>


 <section id="section-doc">
	<div class="container-fluid">
		<div class="row"> 
			<div class="col-md-6 col-sm-12 col-xs-12 document-bg">
			<div class="document-info">	
				<h5>Documents</h5>
			<ul>
				<li><img src="images/pdf.png" alt="pdf">
					<a href="${pageContext.request.contextPath}/Download/CGS-PMS_Operational_Guidelines.pdf" target="_blank">
							CGS-PMS - Operational Guidelines </a>	</li>
				<!-- <li><img src="images/pdf.png" alt="pdf"> <a href="Download/FAQ.pdf" target="_blank">CGS-PMS - FAQs</a> </li> -->				
				<li><img src="images/pdf.png" alt="pdf">
					<a href="${pageContext.request.contextPath}/Download/Undertaking_Format.pdf" target="_blank">
						CGS-PMS - Undertaking MLIs</a>	</li>
				<!-- <li><img src="images/zip-format.png" alt="word">
					<a href="documents/ECLGS_UNDERTAKING_MLIs.zip" download> 
				 		UNDERTAKING-MLIs	</a></li> -->
				 
			</ul>			
			</div>	
			</div>
			<div class="col-md-6 col-sm-12 col-xs-12">	
			<div class="contact-no">
				<h6>  Business Support 	 </h6>
				<p><a href="mailto:querysvs@cgtmse.in"><img src="images/mail.png" alt="">  querysvs@cgtmse.in</a></p>
			<ul> 
				<li> <img src="images/phone.png" alt=""> Anburaj C -  022 6753 1189 </li>
				<li> <img src="images/phone.png" alt=""> Sangeetha P - 022 6722 1438 </li>
				<!-- <li> <img src="images/phone.png" alt=""> X Y Tripathi - +91 86555520534 </li>  -->
			</ul>

		   </div> 
		   <div class="it-support">
		   	<h6>IT Support</h6> 		   	
			<p><a href="mailto:itsupportsvs@cgtmse.in"><img src="images/mail.png" alt=""> itsupportsvs@cgtmse.in</a></p>
			<ul> 
				<li> <img src="images/phone.png" alt=""> Cgtmse Support - 022 67531293/ 67221438 / 67531189 </li>
				<!-- <li> <img src="images/phone.png" alt=""> X Y Tripathi - +91 86555520534 </li>
				<li> <img src="images/phone.png" alt=""> X Y Tripathi - +91 86555520534 </li>  -->
			</ul>

			</div>
		</div>


		</div>
	</div>
</section>  

 
<footer id="footer">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<p><a href="${pageContext.request.contextPath}/disclaimer.html" target="_blank">Privacy Policy    |    Terms And Conditions    |    Disclaimer</a></p>
				<p>Copyright © 2019-2020 Credit Guarantee Fund Trust for Micro and Small Enterprises (CGTMSE). All Rights Reserved</p>
			</div>
		</div>
	</div>
</footer>

<!-- Modal -->
<div id="dashboardModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button> 
      </div>
      <div class="modal-body">
        <iframe src="dashboard_login.html" height="600" width="100%"></iframe>
      </div> 
    </div>

  </div>
</div>



  <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
   <!--  <script src="js/wow.min.js"></script>
              <script>
              new WOW().init();
              </script> -->
      <script src="<%=request.getContextPath()%>/js/jquery-3.3.1.slim.min.js" ></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
  <!--   <script src="js/custom.js"></script> -->
  
</body>

<script>
/* document.onkeydown = function(e) {
	  if(event.keyCode == 123) {
	     return false;
	  }
	  if(e.ctrlKey && e.shiftKey && e.keyCode == 'I'.charCodeAt(0)) {
	     return false;
	  }
	  if(e.ctrlKey && e.shiftKey && e.keyCode == 'C'.charCodeAt(0)) {
	     return false;
	  }
	  if(e.ctrlKey && e.shiftKey && e.keyCode == 'J'.charCodeAt(0)) {
	     return false;
	  }
	  if(e.ctrlKey && e.keyCode == 'U'.charCodeAt(0)) {
	     return false;
	  }
	} */
</script>
</html>