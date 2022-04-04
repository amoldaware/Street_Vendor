<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://fonts.googleapis.com/css?family=Open+Sans|Roboto"
	rel="stylesheet">


<link href="css/customstyle.css" type="text/css" rel="stylesheet">
<link href="css/font-awesome.min.css" type="text/css" rel="stylesheet">
<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet">


</head>
<body>
	<%
		String uesrName = (String) session.getAttribute("uName");
		String mliName = (String) session.getAttribute("mliName");
		String userRole = (String) session.getAttribute("uRole");
	%>

	<div
		style="position: fixed; top: 0; display: block; width: 100%; margin-bottom: 100px; z-index: 9 !important;">
		<div class="header">
		
		
		<c:forEach var="actName" items="${actName}">
				<h5 style="display: inline;">
					<strong>${actName.act_value}</strong>
					
				</h5>
				
			</c:forEach>
			</ul>
			<p></p> 

		</div>
		<!-- Static navbar -->
		<div class="navi">
			<nav class="navbar navbar-default custom-navbar">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target="#navbar"
							aria-expanded="false" aria-controls="navbar">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand logo" href="#"> <img
							src="images/udaan.png" style="display: inline;"
							class="img-responsive" alt=""> <!-- <h4 style="display:inline;">User Role Creation Page</h4> -->
						</a>
					</div>
					<div id="navbar" class="navbar-collapse collapse custom-navi shift">
						<ul class="nav navbar-nav navbar-right custom-nav">
							<li><a href="${homePage}.html"  class="">Home</a></li>

		
						<!--For NBFCMaker & NBFCCHECKER  Menu  -->

						<%
							if (userRole.equals("NMAKER") || (userRole.equals("NCHECKER"))) {
						%>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">Application Processing<span
								class="caret"></span>
						</a>
							<ul id="menu-list" class="dropdown-menu">
								<c:forEach var="applicationList" items="${applicationList}">
									<li><a href="/SV/${applicationList.act_name}.html">${applicationList.act_value}</a></li>
								</c:forEach>
							</ul>
							</li>


						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">Receipt & Payments <span class="caret"></span>
						</a>
							<ul id="menu-list" class="dropdown-menu">
								<c:forEach var="RPaymentList" items="${RPaymentList}">
									<li><a href="/SV/${RPaymentList.act_name}.html">${RPaymentList.act_value}</a></li>
								</c:forEach>
							</ul>
							</li>
                      <li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">Guarantee Maintainence<span class="caret"></span></a>
							<ul id="menu-list" class="dropdown-menu">
								<c:forEach var="GMaintainlist" items="${GMaintainlist}">
									<li><a href="/SV/${GMaintainlist.act_name}.html">${GMaintainlist.act_value}</a></li>
								</c:forEach>
							</ul>
						</li>
						 <li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">Claim Processing<span class="caret"></span></a>
							<ul id="menu-list" class="dropdown-menu">
								<c:forEach var="CList" items="${CList}">
									<li><a href="/SV/${CList.act_name}.html">${CList.act_value}</a></li>
								</c:forEach>
							</ul>
						</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">Report & MIS <span class="caret"></span></a>
							<ul id="menu-list" class="dropdown-menu">
								<c:forEach var="repList" items="${repList}">
									<li><a href="/SV/${repList.act_name}.html">${repList.act_value}</a></li>
								</c:forEach>
							</ul>
						</li>					

						<%
							}
						%>
						<!--For CMaker & CCHECKER  Menu  -->
				 	<%
							if (userRole.equals("CMAKER") || userRole.equals("CCHECKER")) {
						%>
 
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">Registration<span
								class="caret"></span>
						</a>
							<ul id="menu-list" class="dropdown-menu">
								<c:forEach var="guaranteelist" items="${guaranteelist}">
									<li><a href="/SV/${guaranteelist.act_name}.html">${guaranteelist.act_value}</a></li>
								</c:forEach>
							</ul></li>

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">Application Processing<span
								class="caret"></span>
						</a>
							<ul id="menu-list" class="dropdown-menu">
								<c:forEach var="applicationList" items="${applicationList}">
									<li><a href="/SV/${applicationList.act_name}.html">${applicationList.act_value}</a></li>
								</c:forEach>
							</ul>
							</li>

					<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">Receipt & Payments <span class="caret"></span>
						</a>
							<ul id="menu-list" class="dropdown-menu">
								<c:forEach var="RPaymentList" items="${RPaymentList}">
									<li><a href="/SV/${RPaymentList.act_name}.html">${RPaymentList.act_value}</a></li>
								</c:forEach>
							</ul>
							</li>
                 
                     <li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">Claim Processing <span class="caret"></span></a>
							<ul id="menu-list" class="dropdown-menu">
								<c:forEach var="CList" items="${CList}">
									<li><a href="/SV/${CList.act_name}.html">${CList.act_value}</a></li>
								</c:forEach>
							</ul>
						</li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">Report & MIS <span class="caret"></span></a>
							<ul id="menu-list" class="dropdown-menu">
								<c:forEach var="repList" items="${repList}">
									<li><a href="/SV/${repList.act_name}.html">${repList.act_value}</a></li>
								</c:forEach>
							</ul>
						</li>

						
					 	<%
							}
						%>
						
						<!-- 	<li><a href="#">Password Change</a></li> -->
						<li class="logout"><a href="logout.html">Log Out <i
								class="fa fa-sign-out" aria-hidden="true"></i></a></li>

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"> <img src="images/student.png" alt="">
						</a> <!--  <span class="caret"></span>-->
							<ul id="menu-list" class="dropdown-menu dropdown-user">
								
								
								<li><strong>User Name :</strong> <%=uesrName%></li>
								<hr class="line">
								<li><strong>User Role: </strong> <br><%=userRole%></li>
							</ul></li>
						</ul>


					</div>
					<!--/.nav-collapse -->
				</div>
				<!--/.container-fluid -->
			</nav>
		</div>
	</div>
	<script src="js/jquery-3.5.1.min.js" type="text/javascript"></script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>



	<script type="text/javascript">
		/* 
		 $(window).scroll(function() {
		 var sticky = $('.navi'),
		 scroll = $(window).scrollTop();
		
		 if (scroll >= 5) { 
		 sticky.addClass('fixed'); }
		 else { 
		 sticky.removeClass('fixed');

		 }
		 });
		 */
		$(function() {
									
			
			$("#navbar .dropdown").hover(
					function() {
						$('#menu-list.dropdown-menu', this).stop(true, true)
								.fadeIn("fast");
						$(this).toggleClass('open');
					},
					function() {
						$('#menu-list.dropdown-menu', this).stop(true, true)
								.fadeOut("fast");
						$(this).toggleClass('open');
					});
		});
	</script>
</body>
</html>
