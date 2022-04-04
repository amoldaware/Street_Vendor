<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="en-US">
<head>
<meta charset="utf-8">
<title>Processing</title>
</head>
<body>

    <%
        //Following is a manual sleep simulating a process 
        try {
            //Sleep for 10 seconds
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            out.write("Error occurred while processing");
        }
        out.write("Done!");
    %>
</body>
</html>