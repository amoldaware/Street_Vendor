<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
 		<title>Credit Guarantee scheme PM SVANIDHI</title>
    <!-- Bootstrap CSS -->
      <link href="css/bootstrap.min.css" rel="stylesheet">
     <link href="css/custom.css" rel="stylesheet">
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
    <style>

    	p{line-height: 22px !important;}
    	[class*="col-"]{
	padding-left: 10px; padding-right: 10px;  	font-family: 'Open Sans', sans-serif; }
    	.row{margin-right: -10px; margin-left: -10px;}
		.container-fluid{padding-right: 10px; padding-left: 10px;} 
		.header-contact{display: block; width: 100%;  background-color: #f05926; padding: 10px 0;}
.header-contact ul{width: 100%; list-style-type: none; text-align: right;}
.header-contact ul li{ display: inline;  padding: 0 5px;}
.header-contact ul li a{color: white}
.header-contact ul li a i{font-size: 18px; padding-right: 5px;}
#header-info{display: block; width: 100%;  background-color: white; 
			}/*border-top: 5px solid rgb(255, 159, 140); background-color: rgb(230, 230, 230); lightwhite*/

		 .col-70{width:70%; }   
		 .col-30{width:30%; }   
		 .col-50{width:50%; }   
		 .header-info{float: left;} 
		.header-info{float: left; position: relative;}

 .header-info img{width: 200px;}
 .header-info.col-70 img,.header-info.col-50 img{ position: absolute; padding: 10px 10px; border-radius: 5px; background-color: white;}
.header-info p a,.header-info p, .header-login p {color:white; padding: 15px 10px; }
    .header-login p a {color:white; padding: 5px 20px; font-size: 16px; 
    border-radius: 15px; background-color:#f05926 ; box-shadow: 2px 2px 5px #f05926;}
/*.header-login p a:nth-child(1){border-right:2px solid #ff9f8c;}*/
.header-info p a:hover,.header-login p a:hover{border-radius: 15px; background-color: #f05926; color: white;  }
.header-login{float: right;}
.header-login p{text-align: right;}
.header-info p i,.header-login p i{padding-right: 3px} 

    	.feature-detail{  margin: 10px auto;  text-align: center; background-color: #ebebeb; border-radius: 10px; min-height: 250px;
				position: relative; transition: 0.5s all ease;}
		.feature-detail:hover{ transform: translateY(-10px); box-shadow: 0px 2px 8px #757575; }				
		.feature-detail h3{padding: 20px 15px; background: #df9537; border-radius: 10px 10px 0 0; font-size: 16px; 
						color: white; font-weight: 600;}
		.feature-detail p{padding: 15px 25px; font-family: 'Arvo', serif; text-align: center; font-size: 14px;}
		.feature-detail img {width:60px; position: absolute; bottom: 30px; left: 0; right: 0; margin: auto;}
		#footer{padding: 10px 0;  background: #535353;
    border-bottom: 15px solid #f05926;}
		#footer p{    text-align: center;  color: white;}
		#footer p a{ color:white; }
		#footer p a:hover{color:black;}

		table{font-size:14px;}
		table> tbody >tr >td{padding: 8px 10px !important;}
		table> thead >tr >th{background-color:#f05926; color: white; padding: 8px 10px !important;}
    	/*	privacy page	*/
#disclaimer-header{padding: 100px 0 90px; background-color:#f05926; color: white; box-shadow: 2px 3px 14px rgb(127, 127, 127)}
#disclaimer-header h1{font-size: 20px;  }
#disclaimer-info{display: block; width: 100%;}
.disclaimer-main-info{}
.disclaimer-info{width: 100%; display: block;   background: #ffffff; position: relative;  top: -40px;
    padding: 30px 30px 10px;     border-radius: 10px;}
.disclaimer-info h3{font-size: 16px; padding: 0 0 15px 0;     color: #f05926; border-bottom: 1px solid 1px solid rgb(255, 130, 130);
    font-weight: 600;}
.disclaimer-info p{font-size: 14px; text-align: justify;}
.disclaimer-info p:nth-of-type(1) { padding: 5px 0 0 0; }
.disclaimer-info ul{display: block; padding-left: 15px}
.disclaimer-info ul li{list-style-type: disc; font-size: 13px; line-height: 22px;}

.disclaimer-info h4{border-radius: 3px; background-color: #d76a4d; padding: 7px 20px;
    margin-bottom: 10px;  color: white;}
.disclaimer-info .form-group .control-label,.disclaimer-info .form-group label{padding:0 5px 5px 5px; font-weight: 600; font-size: 14px}    
.disclaimer-info .form-group .control-label span{color:red;}
.disclaimer-info .form-group input.form-control,select.form-control {font-size: 14px; }
.disclaimer-info .form-group input.form-control:focus,.disclaimer-info .form-group select.form-control:focus
,.disclaimer-info .form-group textarea.form-control:focus{
	border-color: rgb(243, 163, 142); box-shadow: 1px 1px 4px rgb(251, 131, 99);
}

#section-support { padding: 20px 0; background-color: #dedede; }
.document-bg{background-image: url(../images/documentbg.jpg); background-size: cover; background-repeat: no-repeat;
    background-position: center; width: 100%}
.document-info {width: 100%; display: block;   text-align: center; margin: 40px auto 0; }
.document-info h5{text-align: center; font-size: 18px; font-weight: 600; color:#dedede;font-family: 'Open Sans', sans-serif;
        position: relative;  padding-bottom: 15px; }
/*.document-info p a{padding:20px 20px 20px 0; background-color:white; color:black;  border-radius: 5px; font-size: 14px;}
.document-info p a img{width: 52px;}*/

.contact-support{display: block; width: 100%;  }
.contact-support h5{text-align: center; font-size: 18px; font-weight: 600; color:rgb(215, 106, 77);font-family: 'Open Sans', sans-serif; }
.contact-no{margin-top: 25px; float: left;
    padding: 15px 15px;
   }     /*border-radius: 5px;   background-color: #e8e8e8;  box-shadow: 1px 2px 5px #d4d4d4;  border: 1px solid #cacaca;*/
.it-support{padding: 15px 15px; float: left;}   
.contact-no h6{font-family: 'Open Sans', sans-serif;  font-size: 18px; text-align: center; position: relative; 
    padding-bottom: 15px;}
.contact-no p{ padding: 5px 5px;     margin: 10px 0 0 0;}
.contact-no p img{width:20px;}
.contact-no ul{width: 100%; text-align: left;  float: left; }
.contact-no ul li{list-style-type: none; display: inline-block; padding: 5px 5px; color: rgb(97, 97, 97)}
.contact-no ul li img{width:20px;}
.contact-no ul li i{font-size: 20px; margin-right: 15px;}
.contact-no ul li a,.document-info ul li a{color:rgb(97, 97, 97)}
.contact-no ul li a:hover,.contact-no ul li:hover,.it-support ul li:hover{color:rgb(215, 106, 77)}

.it-support h6{font-family: 'Open Sans', sans-serif;  font-size: 18px; text-align: center; position: relative;padding-bottom: 15px;}
.contact-no h6:after{content: ''; height:2px; width: 40%; background-color: #f05926; position: absolute; bottom: 0;
left: 0; right: 0; margin: 0 auto;}
.document-info h5:after{content: ''; height:2px; width: 40%; background-color: white; position: absolute; bottom: 0;
left: 0; right: 0; margin: 0 auto;}

.contact-no h6 a,.it-support h6 a{margin-left: 10px; color:rgb(215, 106, 77)}
.it-support p{ padding: 5px 5px;   margin: 10px 0 0 0;}
.it-support p img{width:20px;}
.contact-no p a,.it-support p a{color:rgb(97, 97, 97);}
.contact-no p a:hover,.it-support p a:hover{color:rgb(215, 106, 77)}
.it-support ul{width: 100%; text-align: left;  float: left;  }
.it-support ul li{list-style-type: none; display: inline-block; padding: 5px 5px; color: rgb(97, 97, 97); }
.it-support ul li img{width:20px;}
.it-support ul li span{color:rgb(215, 106, 77)}

.document-info ul{width: 100%; padding: 10px 0;   margin-top: 25px}
.document-info ul li{list-style-type: none; display: inline-block; width: 48%; box-shadow: 1px 2px 6px rgba(41, 41, 41, 0.31);
   background-color: white;min-height: 60px; color: rgb(97, 97, 97);   vertical-align: middle; border-radius: 5px;
 transition: all 0.5s ease; margin: 10px 5px;}
.document-info ul li:hover{transform: translateY(-7px);}
.document-info ul li a{ text-align: left; padding: 10px;    font-size: 14px;     width: 78%;  min-height: 60px;
    float: left; }   
.document-info ul li img{ float: left; width: 21%;     display: block; padding: 6px; 
 background-color: rgb(230, 230, 230);}

    </style>
   </head>
 <body>

<div class="header-contact">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<ul style="visibility: hidden;">
					<li><a href="mailto:querysvs@cgtmse.in"><i class="fa fa-envelope" aria-hidden="true"></i> querysvs@cgtmse.in</a></li>
					<li><a href=""><i class="fa fa-phone-square" aria-hidden="true"></i> Cgtmse Support : 022 67531293</a></li>
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
					<a href="#" target="_blank"><img src="images/udaanlogo.svg" alt="Credit Guarantee Scheme for Subordinate Debt (CGSSD)"></a>
				</div>
				<div class="header-login col-50">
					<p style="visibility: hidden;"> 
					<!-- 	<a type="button" data-toggle="modal" data-target="#dashboardModal"><i class="fa fa-tachometer" aria-hidden="true"></i> Dashboard	</a> -->
						<a href="Login.html"> Login	</a>
						<!-- <a href="contact.html">  Grievances – ECLGS	</a> -->
					</p>
				</div>

			</div>
		</div>
	</div>
</header>
 	
<section id="disclaimer-header">
 	<div class="container">
 		<div class="row">
 			<div class="col-md-12 col-xs-12"> 
 					<h1> Frequently Asked Questions (FAQs)</h1> 
 			</div>	 
 		</div>
 	</div>	
</section>
<section id="disclaimer-info">
	<div class="container">
			<div class="row">
				<div class="col-md-12">
	<article> 
			<div class="disclaimer-info  ">
				<h3>1. What is PM Street Vendor's AtmaNirbhar Nidhi (PM SVANidhi) ?</h3>
				<p>PM SVANidhi is a Scheme of Ministry of Housing & Urban Affairs (MoHUA) for sanction of working capital loan upto Rs. 10,000 to street vendors through the Lending Institutions.</p>
				<p>For details of the Scheme, please visit https://pmsvanidhi.mohua.gov.in</p>
				 
			</div>
	</article>

	<article> 
			<div class="disclaimer-info">
				<h3>2. What is Credit Guarantee Scheme for PM SVANIDHI (CGS-PMS)? </h3>
				<p>Credit Guarantee Scheme for PM SVANidhi is the graded guarantee scheme under which the credit product / loan would be guaranteed by Credit Guarantee Fund Trust for Micro and Small Enterprises (CGTMSE).

The CGS-PMS is a portfolio guarantee provided by CGTMSE to Member Lending Institutions (MLIs) for facilitating sanction of Working Capital (WC) loan of upto Rs.10,000/- to individual street vendors.</p>
				 
			</div> 
	</article>
	<article> 
			<div class="disclaimer-info">
				<h3>3. What is the objective of the Scheme? </h3>
				<p>To provide portfolio-based guarantee coverage to the Member Lending Institutions (MLIs) of CGTMSE to facilitate sanction of working capital loan up to Rs.10,000 as per PM SVANidhi.</p> 
			</div> 
	</article>
	<article> 
			<div class="disclaimer-info">
				<h3>4. What is the amount of Initial working capital loan?</h3>
				<p>The initial working capital loan is upto Rs.10,000/-.
				 </p> 
				 </div>
	</article>
	<article> 
			<div class="disclaimer-info">
				<h3>5.Whether subsequent loan under PM SVANidhi is eligible ?</h3>
				<p>Yes. On timely or early repayment, the vendors will be eligible for the next loan with an enhanced limit of a maximum of 200% of the earlier loan, subject to a ceiling of  Rs.20,000/-.
				 </p> 
				 </div>
	</article>
	<article> 
			<div class="disclaimer-info">
				<h3>6. What will be the Tenure of the loan?</h3>
				<p>Tenure of the loan will be maximum of 1 year.
				 </p> 
				 </div>
	</article>
	<article> 
			<div class="disclaimer-info">
				<h3>7. Who are the eligible Lenders / MLIs under the Scheme?</h3>
				<p>All Scheduled Commercial Banks, Regional Rural Banks (RRBs), Small Finance Banks (SFBs), Cooperative Banks, Non-Banking Finance Companies (NBFCs), Micro Finance Institutions (MFIs) & SHG Banks established in some States/UTs e.g. Stree Nidhi etc. who on-board CGTMSE by signing the given undertaking.
				 </p> 
				 </div>
	</article>
	<article> 
			<div class="disclaimer-info">
				<h3>8. Whether a new vendor can start a business under the Scheme?</h3>
				<p>No. The Scheme is available to all street vendors engaged in vending in urban areas as on or before March 24, 2020.
				 </p> 
				 </div>
	</article> 
	<article> 
			<div class="disclaimer-info">
				<h3>9. Who are the eligible vendors to avail loan under PM SVANidhi Scheme ?</h3>
				<p><strong>The eligible vendors will be identified as per following criteria:</strong> </p>
				 <ul>
				 	<li>Street vendors in possession of Certificate of Vending / Identity Card issued by Urban Local Bodies (ULBs);</li>
				 	 <li>The vendors, who have been identified in the survey but have not been issued Certificate of Vending / Identity Card;</li>
				 	 <li> Street Vendors, left out of the ULB led identification survey or who have started vending after completion of the survey and have been issued Letter of Recommendation (LoR) to that effect by the ULB / Town Vending Committee (TVC); and</li>
				 	 <li>The vendors of surrounding development/ peri-urban / rural areas vending in the geographical limits of the ULBs and have been issued Letter of Recommendation (LoR) to that effect by the ULB / TVC.</li>
				 	  
				 	  
				 </ul>
				</div> 
	</article>
		<article> 
			<div class="disclaimer-info">
				<h3>10. Whether all loans sanctioned to Street Vendors are eligible for guarantee coverage?</h3>
				<p>No. The loans sanctioned to eligible street vendors under PM SVANidhi Scheme are eligible for guarantee coverage.
				 </p> 
				 </div>
	</article>
	<article> 
			<div class="disclaimer-info">
				<h3>11. Will MLIs be required to furnish any Undertaking for registration with CGTMSE for the purpose of this Scheme?</h3>
				<p>Yes. As a part of registration, MLIs will be required to submit a one time Undertaking to CGTMSE for the purpose of this Scheme as per the prescribed format.
				 </p> 
				 </div>
	</article>
	<article> 
			<div class="disclaimer-info">
				<h3>12.	Whether the loans sanctioned under PM SVANidhi prior to date of execution of the Undertaking /submission to CGTMSE  / Trust is eligible?</h3>
				<p>Yes. The benefit of guarantee cover under the Scheme will be available to all the lending institutions since the launch of the scheme i.e., 2nd July 2020, irrespective of date of execution of the Undertaking to be furnished to the Trust.
				 </p> 
				 </div>
	</article>
	<article> 
			<div class="disclaimer-info">
				<h3>13.	Whether Undertaking to be submitted by Lending Institutions before lodging the portfolio for guarantee cover ?</h3>
				<p>Yes. Guarantees will be approved after furnishing of the undertaking by the MLIs..
				 </p> 
				 </div>
	</article>
		<article> 
			<div class="disclaimer-info">
				<h3>14.	Whether any cut-off date applicable for sanction of loan under the Guarantee Scheme ?</h3>
				<p>All loans sanctioned on and after July 02, 2020 under PM SVANidhi Scheme are eligible.
				 </p> 
				 </div>
	</article>
	<article> 
			<div class="disclaimer-info">
				<h3>15. What would be Interest Rate charged for the working capital loan under PM SVANidhi Scheme?</h3>
				<p>In case of Scheduled Commercial Banks, Regional Rural Banks (RRBs), Small Finance Banks (SFBs), Cooperative Banks & SHG Banks, the rates will be as per their prevailing rates of interest.
				 </p> 
				 <p>Banks using BCs for sourcing and monitoring function for the Scheme can have a differential interest rate structure for the Scheme, to provide for the cost towards BCs, subjects to any regulatory caps that may have been imposed by RBI. In case of NBFC, NBFC-MFIs etc., interest rates will be as per RBI guidelines for respective lender category.</p>
				 <p>In respect of MFIs (non NBFC) & other lender categories not covered under the RBI guidelines, interest rates under the scheme would be applicable as per the extant RBI guidelines for NBFC-MFIs.</p>
				 </div>
	</article>
	<article> 
			<div class="disclaimer-info">
				<h3>16. Whether any collateral security is required to avail the loan?</h3>
				<p>No collateral security is required to avail the loan from the Member Lending Institution.
				 </p> 
				 </div>
	</article>
	<article> 
			<div class="disclaimer-info">
				<h3>17. What would be the guarantee coverage available for MLIs under the Scheme?</h3>
				<p>The Scheme has a provision of Graded Guarantee Cover for the loans sanctioned, as indicated below, which will be operated on portfolio basis:
				 </p> 
				 <p> a) First Loss Default (Up to 5%): 100% </p>
				  <p> b) Second Loss (beyond 5% up to 15%): 75% of default portfolio </p>
				   <p> c) Maximum guarantee coverage will be 15% of the year portfolio</p>
				   <p><strong>Illustrations:</strong> </p>
				 <ul>
				 	<li> In an MLI covers a portfolio of Rs.100 crore and has a portfolio loss of Rs.5 crore, 100% of the loss (Rs.5 crore) shall be covered by CGTMSE.</li>
				 	 <li> In an MLI covers a portfolio of Rs.100 crore and has a portfolio loss of Rs.15 crore, then CGTMSE will cover Rs.12.50 crore loss (100% of Rs.5 crore + 75% of Rs.10 crore).</li>
				 	 <li> In an MLI covers a portfolio of Rs.100 crore and has a portfolio loss of more than Rs.15 crore, then CGTMSE coverage will be still Rs.12.50 crore.</li>
				 	 
				 </ul>
				 </div>
	</article>
	<article> 
			<div class="disclaimer-info">
				<h3>18. What is the period for calculation of the year portfolio, either Calendar year or Financial Year?</h3>
				<p>Portfolio creation would be recognised on annual basis ending with the Financial Year and eligibility of claim will be based on such annual portfolio.
				 </p> 
				 </div>
	</article>
	<article> 
			<div class="disclaimer-info">
				<h3>19.	Whether AADHAAR No. of borrowers is required to be shared with CGTMSE for Guarantee coverage?</h3>
				<p>Aadhaar Number of the borrower is not required for availing guarantee from CGTMSE and lending institution can provide PMS No. of the borrowers as the Unique Identification Number (PMS No. is generated at the successful submission of application by an eKYC verified street vendor).
				 </p> 
				 </div>
	</article>
	<article> 
			<div class="disclaimer-info">
				<h3>20. How to obtain the coverage, whether individual guarantee is obtained?</h3>
				<p>No. The Trust shall provide guarantee cover on portfolio basis for credit facilities extended by Member Lending Institution(s) to eligible borrower. All loans sanctioned in a month can be pooled together and lodged for guarantee as a portfolio.
				 </p> 
				 </div>
	</article>
	<article> 
			<div class="disclaimer-info">
				<h3>21.	Will any guarantee fee be charged under the Scheme by CGTMSE? </h3>
				<p>No, CGTMSE will not charge any guarantee fee under the Scheme.
				 </p> 
				 </div>
	</article>
	<article> 
			<div class="disclaimer-info">
				<h3>22.	Whether the unsecured loan provided under PM SVANidhi Scheme will be eligible for guarantee cover ?</h3>
				<p>Yes. Unsecured loan provided to the borrower under this Scheme will be eligible for the guarantee cover from CGTMSE
				 </p> 
				 </div>
	</article>
	<article> 
			<div class="disclaimer-info">
				<h3>23.	Whether any documentation between the lender and the borrower is required for availing guarantee cover?</h3>
				<p>No. The issue of documentation between the lender and the borrower will be sole prerogative of the Lending Institution and CGTMSE will not insist on any documentation for availing credit guarantee.
				 </p> 
				 </div>
	</article>
		<article> 
			<div class="disclaimer-info">
				<h3>24.	Whether any benefit on risk weight is available on the portfolio guaranteed with CGTMSE? </h3>
				<p>Risk weight for this portfolio will apply as per the extant RBI guidelines.
				 </p> 
				 </div>
	</article>
		<article> 
			<div class="disclaimer-info">
				<h3>25.	What is the process for invocation of guarantee?</h3>
				<p>MLIs are required to invoke the guarantee once the accounts turns into NPA. MLIs to pool all the accounts in a particular quarter and lodge for claim during the next quarter.
				 </p> 
				 </div>
	</article>
		<article> 
			<div class="disclaimer-info">
				<h3>26.	Whether any limitation period is there for invoking guarantee / lodgement of claim?</h3>
				<p>Yes. The lending institution may invoke the guarantee / lodge claim application in respect of credit facilities under a portfolio within a maximum period of 1 year from the NPA date.
				 </p> 
				 </div>
	</article>
		<article> 
			<div class="disclaimer-info">
				<h3>27.	Whether initiation of legal proceedings is necessary?</h3>
				<p>Initiation of legal proceedings is not necessary given the small loan size. 
				 </p> 
				 </div>
	</article>
		<article> 
			<div class="disclaimer-info">
				<h3>28.	If legal initiation is not necessary what is the process for submitting claims ?</h3>
				<p>Lending Institutions are expected to have their own prudent recovery measures and may submit management certificate confirming that the amount due and payable to the lending institution in respect of the loan has not been paid and the dues have been classified by the lending institution as Non Performing Asset.
				 </p> 
				 </div>
	</article>
		<article> 
			<div class="disclaimer-info">
				<h3>29.	On lodgement of claim application by the MLI, how the claims in a portfolio will be settled?</h3>
				<p>On lodgement of claim application by MLI on quarterly basis, CGTMSE would settle the claim. Trust shall pay in one instalment 100% of the portfolio guaranteed amount on preferring of eligible claim by the lending institution within 30 days subject to the extant guidelines.Claim settlements would be carried out quarterly subject to maximum guarantee coverage of 15% of the year portfolio.
				 </p> 
				 </div>
	</article>
	<!--	<article> 
			<div class="disclaimer-info">
				<h3>30.	Can you please give us an illustration of the portfolio claim settlements?</h3>
				<p>Yes. Unsecured loan provided to the borrower under this Scheme will be eligible for the guarantee cover from CGTMSE
				 </p> 
				 </div>
	</article>
 		<article> 
			<div class="disclaimer-info">
				<h3>22.	Whether the unsecured loan provided under PM SVANidhi Scheme will be eligible for guarantee cover ?</h3>
				<p>Yes. Unsecured loan provided to the borrower under this Scheme will be eligible for the guarantee cover from CGTMSE
				 </p> 
				 </div>
	</article>
	
	<article> 
			<div class="disclaimer-info">
				<h3> 23.	Whether any documentation between the lender and the borrower is required for availing guarantee cover?</h3>
				<p>On lodgement of claim application by MLI on quarterly basis, CGTMSE would settle the claim in two instalments.
				 </p> 
				 <p>First instalment of claim of 75% of the eligible portfolio loss amount (outstanding basis as on the date of NPA)</p>
				 <p>Second instalment of claim would be 25% of the eligible portfolio loss would be settled after conclusion of recovery process.</p>
				 <p>Claim settlements would be carried out quarterly subject to maximum guarantee coverage of 15% of the year portfolio.</p>
				 </div>
	</article> -->
	<article> 
			<div class="disclaimer-info">
				<h3>30. Can you please give us an illustration of the portfolio claim settlements?</h3>
				<p>The Scheme has a provision of Graded Guarantee Cover for the loans sanctioned which will be operated on portfolio basis: <br> 
				a) First Loss Default (Up to 5%): 100%	<br> 
				b) Second Loss (beyond 5% up to 15%): 75% of default portfolio</p> 
				<p><strong><b>Settlement of claims - Illustrations </b></strong></p> 

				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>NPA Scenarios on a portfolio Details</th>
							<th>NPA @ 15% <br>(Rs. Crore)</th>
							<th>NPA @ 8% <br>(Rs. Crore)</th>
							<th>NPA @ 25% <br>(Rs. Crore)</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Loan portfolio created</td>
							<td>100.00</td>
							<td>100.00</td>
							<td>100.00</td>
						</tr>
					<!-- 	<tr>
							<td>Outstanding of the portfolio at the time of NPA</td>
							<td>100.00</td>
							<td>100.00</td>
							<td>100.00</td>
						</tr> -->
						<tr>
							<td>Assumed NPA %</td>
							<td>15.00</td>
							<td>8.00</td>
							<td>25.00</td>
						</tr>
						<tr>
							<td>Maximum NPA Cap under the Scheme</td>
							<td>15%</td>
							<td>15%</td>
							<td>15%</td>
						</tr>
						<tr>
							<td>CGTMSE liability</td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>100% coverage upto first 5% loss of

portfolio</td>
							<td>5.00</td>
							<td>5.00</td>
							<td>5.00</td>
						</tr>
						<tr>
							<td>75% guarantee cover beyond 5% upto

15% loss of the portfolio (i.e. 75% on 5-

15% loss)</td>
							<td>7.50</td>
							<td>2.25</td>
							<td>7.50</td>
						</tr>
						<tr>
							<td>Total Claim Payable by CGTMSE</td>
							<td>12.50</td>
							<td>7.25</td>
							<td>12.50</td>
						</tr> 
				
<!-- 
						<tr>
							<td>Claim payments done as per below:</td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>Claim payment towards 1st

instalment

(75% of the eligible claim amount)</td>
							<td>9.375</td>
							<td>5.4375</td>
							<td>9.375</td>
						</tr>
						<tr>
							<td>Claim payment towards 2nd

instalment

(25% of the eligible claim amount)</td>
							<td>3.125</td>
							<td>1.8125</td>
							<td>3.125</td>
						</tr>-->
					</tbody>
				</table></div> 
				<article> 
			<div class="disclaimer-info">
				<p>
				Note : In an MLI covers a portfolio of Rs.100 crore and has a portfolio loss of more than Rs.15 crore, then the eligible claim amount  will be Rs.12.50 crore.</p>
				</div></article>
	</article>
	<article> 
			<div class="disclaimer-info">
				<h3>31. Whether MLIs are required to pass on the recoveries received after the settlement of claims?</h3>
				<!-- <p>Yes. All recoveries received by the Lender post claim settlement have to be refunded to CGTMSE
				 </p>  -->
				 <p>Any recovery made from the NPA portfolio against which claim has been settled by CGTMSE will be allowed to be adjusted against future claim, if any, else will be returned to CGTMSE by the concerned lending institutions. 
However, lending institutions will be required to file annual returns for 2 years after settlement of the last claim against a created portfolio in a FY about recovery made from the borrowers against which claim has been settled by CGTMSE. Amount payable to CGTME out of the recovery proceeds will be returned to CGTMSE after which accounts will be treated as finally settled and closed.
				 </p>
				 
				 </div>
	</article>
	<article> 
			<div class="disclaimer-info">
				<h3>32. Will there be inspection of cases covered under the Scheme?</h3>
				<p>Yes. CGTMSE reserves the right to inspect cases covered under the Scheme at any given time
				 </p> 
				 </div>
	</article>
	<article> 
			<div class="disclaimer-info">
				<h3>33. Who can provide answers to any further queries related to guarantee coverage?</h3>
				<p>Please address your queries/suggestions to <a href="mailto:querysvs@cgtmse.in">querysvs@cgtmse.in.</a>
				 </p> 
				 </div>
	</article>


		</div>
	</div>
</div>
</section>

<section id="section-doc" style="display:none;">
	<div class="container-fluid">
		<div class="row"> 
			<div class="col-md-6 col-sm-12 col-xs-12 document-bg">
			<div class="document-info">	
				<h5>Documents</h5>
			<ul>
				<li><img src="images/pdf.png" alt="pdf">
					<a href="documents/Subdebt_Scheme_Guidilines.pdf" target="_blank">
							CGSSD – Operational Guidelines </a>	</li>
				<li><img src="images/pdf.png" alt="pdf"> <a href="documents/FAQs_on_Sub-Debt_Scheme.pdf" target="_blank">CGSSD –PMS – FAQs</a> </li>				
				<li><img src="images/word.png" alt="pdf">
					<a href="documents/Undertaking_Sub-debt_Scheme.docx" target="_blank">
						CGSSD – Undertaking – MLIs</a>	</li>
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
			<p><a href="#"><img src="images/mail.png" alt=""> itsupportsvs@cgtmse.in</a></p>
			<ul> 
				<li> <img src="images/phone.png" alt=""> Cgtmse Support - 022 67531293 </li>
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
				<p><a href="/SV/disclaimer.html" target="_blank">Privacy Policy    |    Terms And Conditions    |    Disclaimer</a></p>
				<p>Copyright © 2020-2021 Credit Guarantee Fund Trust for Micro and Small Enterprises (CGTMSE). All Rights Reserved</p>
			</div>
		</div>
	</div>
</footer>

 </body>   
</html>
