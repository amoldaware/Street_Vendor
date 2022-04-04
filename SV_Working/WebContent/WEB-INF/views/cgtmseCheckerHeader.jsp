<h2 align="center" style="color: #5c4324;">Credit Guarantee Scheme for NBFCs</h2>
<div align="right"><a href="logout.html">Log Out</a></div>
<%
	String uesrName = (String) session.getAttribute("uName");
%>
<div align="left"><%=uesrName%></div>
 <hr/><br/>
<div align="center">
     <button class="button" onclick="location.href='/SV/cgtmseCheckerHome.html'">HOME</button><button class="button" disabled="true">User Registration</button><button class="button" disabled="true">MLI MAKER</button><button class="button" disabled="true">MLI Checker</button><button class="button" disabled="true">CGTMSE MAKER</button><button class="button" onclick="location.href='/nbfc/cgtmseCheckerForBacthApprovalRM.html'">CGTMSE CHECKER</button>
</div>