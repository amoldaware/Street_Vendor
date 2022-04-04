<h2 align="center" style="color: #5c4324;">Credit Guarantee Scheme for NBFCs</h2>
<div align="right"><a href="logout.html">Log Out</a></div>
<%
	String uesrName = (String) session.getAttribute("uName");
%>

<div align="left"><b><%=uesrName%></b></div>
 <hr/><br/>
<div align="center">
<button class="button" onclick="location.href='/nbfc/adminHome.html'">HOME</button><button class="button" onclick="location.href='/nbfc/add.html'">User Registration</button><button class="button" disabled="true" >MLI MAKER</button><button class="button" disabled="true">MLI Checker</button><button class="button" disabled="true">CGTMSE MAKER</button><button class="button" disabled="true">CGTMSE CHECKER</button>
</div>