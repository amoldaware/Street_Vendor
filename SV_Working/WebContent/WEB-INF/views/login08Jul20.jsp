<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
 		<title>Street Vendor ( CGTMSE)</title>
    <!-- Bootstrap CSS -->
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
   </style>
  </head> 
<body>

<div id="login">
      <div class="container-fluid">
        <div id="login-row" class="row justify-content-end">
          
          <div id="login-column" class="col-md-5 col-xs-12">
            <div id="login-box" class="">
              <div id="login-form" class="form">
                <div class="logo">
                  <img src="images/udaanlogo.svg" alt="cgtmse street Vendor">
                </div>
                <!--  <h3 class="text-center text-info">Login</h3> -->
                <div class="form-group">
                  <label for="username" class="text-info"> Member Id</label><br>

                  <input type="text" class="form-control" name="memberId" id="memberId"
                    size="14" value="" autocomplete="off" placeholder="Enter Member Id" /> 
                  
                </div>
                
                <div class="form-group">
                  <label for="userid" class="text-info">User Id</label>
                  <input type="text" class="form-control" name="userId" autocomplete="off" placeholder="Enter User Id"/>
                </div>
              
                <div class="form-group">
                  <label for="password" class="text-info">Password</label><br>
                    <input type="password" class="form-control" name="" placeholder="Enter Password" />
            
                </div> 
                 <div class="form-group">
                  <label for="password" class="text-info">Enter Captcha</label><br> 
                      <div style="width: 100%">
                      <input type="text" class="form-control"  placeholder="Captcha Code" autocomplete="off">
                    </div>
                  
                      <div style="width:100%">
                       <!--  <img src="<%=request.getContextPath()%>/CaptchaImg.jpg" alt="Captcha" id="imgCaptcha"> -->
                        <input type="button" value="Refresh" id="btnCaptcha" class="btn btn-md btn-save">
                      </div>
                </div>                   
                
                <div class="form-group mb-10">
                   
                  <input type="checkbox" name="checkbox" id="checkBoxId"
                    value="Y" onclick="assignPasswordInHiddenField();return validate();">
                  
                  <label class="text-info d-inline">I agree to terms and conditions </label>
                 
                </div>
                

                <div class="form-group mb-10" style="text-align: center;">
                 <a href="main.html" type="button" class="btn btn-md btn-save">Submit</a>
                   
                </div>

                 <div class="form-group line pt-10" style="text-align:center;">                                                          
            <label class="info-btn">  
                <a href=""><i class="fa fa-lock" aria-hidden="true"></i> Forgot Password ? </a>
               
             </label>
               
                </div>

              </div>
            </div>
          </div>
        </div>
      </div>
</div>
</body>
</html>