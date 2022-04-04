<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="fixednavbar">
<table border=0 cellpadding=0 cellspacing=0 align="center" width="100%" style="position: absolute; top: 0; bottom: 0; left: 0; right: 0;">
	<tr bgcolor="#008080">
		<td>&nbsp;&nbsp;&nbsp; 
			<font face="Arial" color="white"><b>Credit Guarantee Scheme - II for NBFCs</b></font>
		</td>
		<td align="right">
		    <div><img src="images/Logo.jpg" height="30%"	width="15%"></div>
		</td>
	</tr>
	
	<tr>
		
		<td colspan="2" bgcolor="orange"> <!-- Navigation Menu -->
		<nav id="primary_nav_wrap">
				<ul>
					<li>					
					<a href="/SV/${homePage}.html" target="#">Home</a></li>
					<li><a href="#">User Activity</a>
						<ul>
							<c:forEach var="actList" items="${actList}">
							<li><a href="/SV/${actList.act_name}.html">${actList.act_value}</a></li>
						    </c:forEach>
							</li>
				        </ul>

				
				<li><a href="/SV/getAppropriationData.html">Report</a>
					<ul>
						
					</ul></li>
					<li></li>
					<li></li>
					<li></li>
					<li><a href="/SV/getAppropriationDataBetweenDates.html">Password Change</a>
					<ul>
						
					</ul></li>
					<li><a href="logout.html">Log Out</a>
					<ul>
					</ul>
					</li>
				</li>
			</nav>
		</td>
	</tr>
	<%	String uesrName = (String) session.getAttribute("uName");%>
</table>
</div>

<table align=right>	<tr><td colpan=2><font face="arial" color="brown" size=1>Welcome</font></td>
	   	        <tr><td><font face="arial" color="brown" size=1>User Name : </font></td><td><font face="arial" color="brown" size=1><%=uesrName%></font></td></tr>
</table>