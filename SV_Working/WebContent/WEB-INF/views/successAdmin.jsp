<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/css/stylesheet.css"
	rel="stylesheet" type="text/css">
	<script src="<%=request.getContextPath()%>/js/nbfcCommon.js"></script>

<title>Admin Login</title>

<style type="text/css">
#body-section{	background-color:white !important;	  padding-bottom: 0px !important;	height: auto !important;    }
.footer1{	margin: 0 0 0 0 !important; }
</style>

</head>
<body>
<div class="main-section" >

	<!-- <div> -->		
	<div class="main-section" >

	<!-- <div> -->		
	<div id="wel-sec">
		<div class="container">
 			<div class="row">
 
	<div class="col-md-8 col-md-offset-2 col-sm-12 col-xs-12"  >
	
		<div class="wel-sec">
			<h1>	Welcome To CGTMSE	</h1>
			<h2>Credit Guarantee Scheme for PM SVANidhi</h2>
			<p>Credit Guarantee Scheme for PM SVANidhi provides guarantee coverage to the Member Lending Institutions (MLIs) to facilitate sanction of working capital loan up to Rs.10,000 or any amount as per PM SVANidhi. The Scheme has a provision of Graded Guarantee Cover for the loans sanctioned, as indicated below, which is being administered by CGTMSE, to be operated on portfolio basis: 
			</p>
			 <ul>
				<li> First Loss Default (Up to 5%): 100%</li>

<li>Second Loss (beyond 5% up to 15%): 75%   of default portfolio</li>

<li>Maximum guarantee coverage will be 15% of the year portfolio</li>
			</ul>
		</div>	
	</div>
	
</div>	
</div>	
</div>	
</div>
	
	
		
		<%-- <div class="col-md-4 prl-5">	
		<!-- <div class="row">	 -->
		<table id="myTable" class="table table-bordered table-hover cus-table mt-10 mb-0">
			<thead>
				<tr><td colspan=5 align=center style="font-size:17px !important;"><strong>User Dashboard</strong></td>
					</tr>
					<tr>
						<c:forEach items="${rows[0]}" var="column">						
							<th class="tableHeader1"><c:out value="${column.key}" /></th>
						</c:forEach>						
					</tr>
			</thead>
			<tbody>
				<c:forEach items="${rows}" var="columns">
					<tr>
					<c:forEach items="${columns}" var="column">
						<td align="center"><c:out value="${column.value}" /></td>
					</c:forEach>											
					</tr>
				</c:forEach>
			</tbody>				
		</table>		
		<!-- </div>	 -->
		</div> --%>
	 
		
<div id="feature-sec">
		<div class="container">
		 <div class="row">		 
				<div class="col-md-12">
					
					<div class="feature-sec">
						<h3>Silent Features of Credit Guarantee Scheme For NBFCs (CGS-II)</h3>
					
			      </div>																		
			</div>		
						
		<div class="col-md-6">
		<div class="accordion mt-25" id="myAccordion">
			<div class="panel cus-panel mb-0">
			 <div class="faq-header" id="faqHeading-1">     
                         <h5 class="faq-title" style="text-align:center;     font-size: 20px;">Eligibility Criteria</h5>
                   </div>
               </div>
    <div class="panel cus-panel mb-0">
        <!-- <button type="button" class="btn btn-danger" data-toggle="collapse" data-target="#collapsible-1" data-parent="#myAccordion">Types of NBFCs</button> -->        
        <div class="faq-header" id="faqHeading-1">             
                 <h5 class="faq-title" data-toggle="collapse" data-target="#collapsible-1" data-parent="#myAccordion">
                     <span class="badge">1</span>Types of NBFCs	
                 </h5>             
         </div>        
        <div id="collapsible-1" class="collapse">
       	 <div class="cus-panelbody">
            <p>-	Systemically Important NBFCs. Profit making for last three years.
				Credit Rating: BBB& above 	</p>
			</div>		
        </div>
    </div>
     <div class="panel cus-panel mb-0">
        <!-- <button type="button" class="btn btn-danger" data-toggle="collapse" data-target="#collapsible-1" data-parent="#myAccordion">Types of NBFCs</button> -->        
        <div class="faq-header" id="faqHeading-1">             
                 <h5 class="faq-title" data-toggle="collapse" data-target="#collapsible-2" data-parent="#myAccordion">
                     <span class="badge">2</span>Credit Rating:
                 </h5>             
         </div>        
        <div id="collapsible-2" class="collapse">
       	 <div class="cus-panelbody">
            <p>- BBB & above 	</p>
			</div>		
        </div>
    </div>
    <div class="panel cus-panel mb-0">
        <!-- <button type="button" class="btn btn-danger" data-toggle="collapse" data-target="#collapsible-1" data-parent="#myAccordion">Types of NBFCs</button> -->        
        <div class="faq-header" id="faqHeading-1">             
                 <h5 class="faq-title" data-toggle="collapse" data-target="#collapsible-3" data-parent="#myAccordion">
                     <span class="badge">3</span>Lendding to MSEs :
                 </h5>             
         </div>        
        <div id="collapsible-3" class="collapse">
       	 <div class="cus-panelbody">
            <p>- Experence of minimum 5 years having MSE loan portfolio of at least 100 crore	</p>
			</div>		
        </div>
    </div>
    <div class="panel cus-panel mb-0">
        <!-- <button type="button" class="btn btn-danger" data-toggle="collapse" data-target="#collapsible-1" data-parent="#myAccordion">Types of NBFCs</button> -->        
        <div class="faq-header" id="faqHeading-1">             
                 <h5 class="faq-title" data-toggle="collapse" data-target="#collapsible-4" data-parent="#myAccordion">
                     <span class="badge">4</span>Net NPA Level :
                 </h5>             
         </div>        
        <div id="collapsible-4" class="collapse">
       	 <div class="cus-panelbody">
            <p>- less then 5%	</p>
			</div>		
        </div>
    </div>
    <div class="panel cus-panel mb-0">
        <!-- <button type="button" class="btn btn-danger" data-toggle="collapse" data-target="#collapsible-1" data-parent="#myAccordion">Types of NBFCs</button> -->        
        <div class="faq-header" id="faqHeading-1">             
                 <h5 class="faq-title" data-toggle="collapse" data-target="#collapsible-5" data-parent="#myAccordion">
                     <span class="badge">5</span>Average Recovery Ratio :
                 </h5>             
         </div>        
        <div id="collapsible-5" class="collapse">
       	 <div class="cus-panelbody">
            <p>- Over 90% for the MSE portfolio</p>
			</div>		
        </div>
    </div>
   
</div>
</div>

<div class="col-md-6">
		<div class="accordion mt-25" id="myAccordion2">
			<div class="panel cus-panel mb-0">
			 <div class="faq-header" id="faqHeading-2">     
                         <h5 class="faq-title" style="text-align:center;     font-size: 20px;">Feaatures of CGS-II</h5>
                   </div>
               </div>
    <div class="panel cus-panel mb-0">
        <!-- <button type="button" class="btn btn-danger" data-toggle="collapse" data-target="#collapsible-1" data-parent="#myAccordion">Types of NBFCs</button> -->        
        <div class="faq-header" id="faqHeading-2">             
                 <h5 class="faq-title" data-toggle="collapse" data-target="#collapsible-6" data-parent="#myAccordion2">
                     <span class="badge">1</span>Guarantee Type :
                 </h5>             
         </div>        
        <div id="collapsible-6" class="collapse">
       	 <div class="cus-panelbody">
            <p>- portFolio based Guarantee
				- Each portfolio build-up would be on quarterly basis	</p>
			</div>		
        </div>
    </div>
     <div class="panel cus-panel mb-0">
        <!-- <button type="button" class="btn btn-danger" data-toggle="collapse" data-target="#collapsible-1" data-parent="#myAccordion">Types of NBFCs</button> -->        
        <div class="faq-header" id="faqHeading-2">             
                 <h5 class="faq-title" data-toggle="collapse" data-target="#collapsible-7" data-parent="#myAccordion2">
                     <span class="badge">2</span>Eligible borrowers for coverage under CGS II :
                 </h5>             
         </div>        
        <div id="collapsible-7" class="collapse">
       	 <div class="cus-panelbody">
            <p>- New and existing Micro and Small Enterprises (MSE) in manufacturing, service and retail trade segment. 	</p>
			</div>		
        </div>
    </div>
    <div class="panel cus-panel mb-0">
        <!-- <button type="button" class="btn btn-danger" data-toggle="collapse" data-target="#collapsible-1" data-parent="#myAccordion">Types of NBFCs</button> -->        
        <div class="faq-header" id="faqHeading-2">             
                 <h5 class="faq-title" data-toggle="collapse" data-target="#collapsible-8" data-parent="#myAccordion2">
                     <span class="badge">3</span>Coverage :
                 </h5>             
         </div>        
        <div id="collapsible-8" class="collapse">
       	 <div class="cus-panelbody">
             <p>- collaterial free and third party guarantee free loans.
				- Partially collateralised loans
				- Unsecured bussiness loans	</p>
			</div>		
        </div>
    </div>
    <div class="panel cus-panel mb-0">
        <!-- <button type="button" class="btn btn-danger" data-toggle="collapse" data-target="#collapsible-1" data-parent="#myAccordion">Types of NBFCs</button> -->        
        <div class="faq-header" id="faqHeading-2">             
                 <h5 class="faq-title" data-toggle="collapse" data-target="#collapsible-9" data-parent="#myAccordion2">
                     <span class="badge">4</span>Loan amount :
                 </h5>             
         </div>        
        <div id="collapsible-9" class="collapse">
       	 <div class="cus-panelbody">
           <p>- Upto 200 lakh extended to the MSEs (For retails trade it is upto 100lakh)</p>
			</div>		
        </div>
    </div>
    <div class="panel cus-panel mb-0">
        <!-- <button type="button" class="btn btn-danger" data-toggle="collapse" data-target="#collapsible-1" data-parent="#myAccordion">Types of NBFCs</button> -->        
        <div class="faq-header" id="faqHeading-2">             
                 <h5 class="faq-title" data-toggle="collapse" data-target="#collapsible-10" data-parent="#myAccordion2">
                     <span class="badge">5</span>Extent of coverage :
                 </h5>             
         </div>        
        <div id="collapsible-10" class="collapse">
       	 <div class="cus-panelbody">
           <p>- Upto 75% (option of 50% (or) 60% (or) 75% available) of the amount in default.</p>
			</div>		
        </div>
    </div>
    <div class="panel cus-panel mb-0">
        <!-- <button type="button" class="btn btn-danger" data-toggle="collapse" data-target="#collapsible-1" data-parent="#myAccordion">Types of NBFCs</button> -->        
        <div class="faq-header" id="faqHeading-2">             
                 <h5 class="faq-title" data-toggle="collapse" data-target="#collapsible-11" data-parent="#myAccordion2">
                     <span class="badge">6</span>Interest Rate Cap on Loans :
                 </h5>             
         </div>        
        <div id="collapsible-11" class="collapse">
       	 <div class="cus-panelbody">
              <p>- Fullfilling RBI guidleines or any other rate as prescribed by the Trust frin time to time.</p>
			</div>		
        </div>
    </div>
   
</div>
</div>		<!-- col-6  -->
					
					
				</div>
				
		</div>	<!-- row  -->
	</div>		<!-- feature-section -->
	
</div>		<!-- main-section -->



<%-- 	<table style="width: 50%; float: left" border=1 bordercolor=black>
		<tr>
			<td> 
			<img src="images/HomeNote.jpg" style="margin: top" hight="100%"	width="100%">
			</td>
			
		</tr>
	</table>
	 


	<table style="width: 40%; float: right" border="1" bordercolor="black">

		<thead>
			<tr>
			<td colspan=5 align=center><font size=4><b><u>User Dashboard</u></b></font></td>
			</tr>
			<tr>
				<c:forEach items="${rows[0]}" var="column">
					<b> </b>
					<td class="tableHeader1"><c:out value="${column.key}" /></td>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${rows}" var="columns">
				<tr>
					<c:forEach items="${columns}" var="column">
						<td align="center"><c:out value="${column.value}" /></td>
					</c:forEach>
				</tr>
			</c:forEach>
		</tbody>

	</table> --%>

</body>
</html>