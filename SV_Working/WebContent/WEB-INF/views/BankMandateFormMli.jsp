<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <script src="<%=request.getContextPath()%>/js/jquery-3.5.1.min.js"></script> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/datatables.min.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datatables.min.js"></script>
<style type="text/css">
#body-section {
	background-color: white !important;
	padding-bottom: 0px !important;
	height: auto !important;
}

.footer1 {
	margin: 0 0 0 0 !important;
}
.btn.btn-reset{padding: 2px 9px;  border-radius: 4px;}
.btn.btn-reset:hover,.btn.btn-reset:focus{padding: 2px 9px;  border-radius: 4px;}
.btn.btn-approve{    transition: all 0.2s;   background-color: #47b900;  color: white; padding: 2px 9px;  border-radius: 4px;}
.btn.btn-approve:hover{color: white;
    box-shadow: 1px 3px 3px #4a4a4a9e;}
.claim h1,.claim h2{color:#4b8abf; text-align:center;  margin: 10px 0;  font-size: 20px;}
.claim h4{color:#4b8abf; text-align:center; font-size: 20px;}
.claim h4 a{display:block;}
.claim h4 a:hover{text-decoration:none}
table.dataTable.no-footer{border-bottom:0px;}
.tbl-checker tr td{ text-align:center; }
.tbl-checker tbody tr td textarea{	width:100%; border:1px solid #cacaca}
.tbl-checker thead tr th:nth-child(1){	width:20%	}
.tbl-checker thead tr th:nth-child(2){	width:25%	}
.tbl-checker thead tr th:nth-child(3){	width:40%	}
.tbl-checker thead tr th:nth-child(4){	width:15%	}
.custom-file, .custom-file1 {
    position: relative;
    display: inline-block;
    width: auto;
    height: calc(1.5em + .75rem + 2px);
}
.custom-file-input, .custom-file-input1 {
    position: relative;
    z-index: 2;
    width: 100%;
    cursor: pointer;
    height: calc(1.5em + .75rem + 2px);
    margin: 0;
    opacity: 0;
}
.frm-box >.form-group input {
    background-color: rgb(245,245,245);
    font-family: 'Open Sans', sans-serif;
    font-size: 14px;
    border: none;
    box-shadow: 1px 3px 3px rgba(8, 198, 255, 0.3);
}
.custom-file-label.custom-file-label1 {
    position: absolute;
    top: 0;
    right: 0;
    left: 0;
    z-index: 1;
    text-align: left;
    height: calc(1.5em + .75rem + 2px);
    padding: .375rem .55rem .375rem 3rem;
    font-weight: 400;
    line-height: 1.5;
    color: rgb(176,176,176);
    background-color: #fff;
    border: 1px solid #ced4da;
    border-radius: 0.2rem !important;
    font-size: 14px;
}
.custom-file-label::after,.custom-file-label1::after {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    z-index: 3;
    display: block;
    height: calc(1.5em + .75rem);
    padding: .375rem 1rem;
    text-align:center;
    line-height: 1.5;
    color: #fff;
    content: "Upload File";
    background-color: rgb(193, 193, 193);
    border-left: inherit;
    border-radius: 0.2rem;
    width: 114px;
}
.dataTables_length label select,.dataTables_wrapper .dataTables_filter input,
.dataTables_wrapper .dataTables_filter input:focus {    border: 1px solid #b3b3b3;}
.form-group input { 
    font-family: 'Open Sans', sans-serif;
    font-size: 14px;
         height: 32px; padding: 3px 12px; border: 1px solid #ccc;
}
</style>
<script>

</script>
</head>
<body>
<form  id="myform" action="">
<div class="main-section">
	<div class="container-fluid">
	<div class="frm-section">
			<div class="col-md-12">
				<div class="claim">
					<h1>BANK MANDATE FORM</h1>
				</div>
			</div>
			<div class="col-md-12">
			<h5 style="color: #4b8abf; font-weight: 600;">MLI Details</h5>
				<!-- <p>Particulars of Bank Account for payment purpose</p> -->
			</div> 
			
			<div class="form-horizontal">
			  <div class="col-lg-12 col-md-6 col-sm-12">
               <div class="form-group">
                    <div class="col-lg-4 col-md-6 col-sm-12">
                   <input type="hidden" class="form-control" value="" id="bankId" > 
                 	</div>
                 </div>
              </div>
            
			<div class="col-lg-12 col-md-6 col-sm-12">
               <div class="form-group">
                 <label for="inputEmail3" class="col-form-label col-lg-2 col-md-6 col-sm-12"><span style="color:red;">*</span>MLI Name:</label> 
                    <div class="col-lg-4 col-md-6 col-sm-12">
                   <input type="text" class="form-control" id="mliNameBankMandate" value="${MliName}" placeholder="Enter MLI Name"  readonly="readonly"> 
                 	</div>
                 </div>
              </div> 
              <div class="col-lg-12 col-md-6 col-sm-12">
               <div class="form-group">
                 <label for="inputEmail3" class="col-form-label col-lg-2 col-md-6 col-sm-12"><span style="color:red;">*</span>Member ID:</label> 
                    <div class="col-lg-4 col-md-6 col-sm-12">
                   <input type="text" class="form-control" id="mliIdBankMandate" value="${user_id}" placeholder="Enter Member ID"   readonly="readonly">
                   <input type="hidden" class="form-control" id="mliLoginId" value="${loginId}" >
                 	</div>
                 </div>
              </div>
               <div class="col-lg-12 col-md-6 col-sm-12">
               <div class="form-group">
                 <label for="inputEmail3" class="col-form-label col-lg-2 col-md-6 col-sm-12"><span style="color:red;">*</span>Contact No:</label> 
                    <div class="col-lg-4 col-md-6 col-sm-12">
                   <input type="text" class="form-control" id="contactNoBankMandate" maxlength="13" placeholder="Enter Contact No" onkeypress="return isNumberKey(event)"> 
                 	</div>
                 </div>
              </div> 
              <div class="col-lg-12 col-md-6 col-sm-12">
               <div class="form-group">
                 <label for="inputEmail3" class="col-form-label col-lg-2 col-md-6 col-sm-12"><span style="color:red;">*</span>Mobile No:</label> 
                    <div class="col-lg-4 col-md-6 col-sm-12">
                   <input type="text" class="form-control" id="mobliNoBankMandate" maxlength="10" placeholder="Enter Mobile No"> 
                 	</div>
                 </div>
              </div>
              <div class="col-lg-12 col-md-6 col-sm-12">
               <div class="form-group">
                 <label for="inputEmail3" class="col-form-label col-lg-2 col-md-6 col-sm-12"><span style="color:red;">*</span>Email Id:</label> 
                    <div class="col-lg-4 col-md-6 col-sm-12">
                   <input type="text" class="form-control" id="emailIdBankMandate" placeholder="Enter Email Id"> 
                 	</div>
                 </div>
              </div> 	 
              </div>
              
			<div class="col-md-12">
			<h5 style="color: #4b8abf; font-weight: 600;">Account Details</h5>
			<!-- <h5 style="color: #4b8abf; font-weight: 600;">MLI HO will fill this</h5> -->
				<!-- <p>Particulars of Bank Account for payment purpose</p> -->
			</div> 
			<div class="form-horizontal">
             <div class="col-lg-12 col-md-6 col-sm-12">
               <div class="form-group">
                 <label for="inputEmail3" class="col-form-label col-lg-2 col-md-6 col-sm-12"><span style="color:red;">*</span>Bank Name</label> 
                    <div class="col-lg-4 col-md-6 col-sm-12">
                   <input type="text" class="form-control" id="bankNameMandateForm" placeholder="Enter Bank Name"> 
                 	</div>
                 </div>
              </div> 
             <div class="col-lg-12 col-md-6 col-sm-12">
               <div class="form-group">
                 <label for="inputEmail3" class="col-form-label col-lg-2 col-md-6 col-sm-12"><span style="color:red;">*</span>Branch Name</label> 
                    <div class="col-lg-4 col-md-6 col-sm-12">
                   <input type="text" class="form-control" id="branchNameMandateForm" placeholder="Enter Branch Name"> 
                 	</div>
                 </div>
              </div>           
              <div class="col-lg-12 col-md-6 col-sm-12">
               <div class="form-group">
                 <label for="inputEmail3" class="col-form-label col-lg-2 col-md-6 col-sm-12"><span style="color:red;">*</span>Account Name</label> 
                   <div class="col-lg-4 col-md-6 col-sm-12">
                   <input type="text" class="form-control" id="accountNameMandateForm" placeholder="Enter Account Name"> 
                 	</div>
                 </div>
              </div>           
              <div class="col-lg-12 col-md-6 col-sm-12">
               <div class="form-group">
                 <label for="inputEmail3" class="col-form-label col-lg-2 col-md-6 col-sm-12"><span style="color:red;">*</span>Account Number</label> 
                   <div class="col-lg-4 col-md-6 col-sm-12">
                   <input type="text" class="form-control" id="accountNoMandateForm" placeholder="Enter Account Number" maxlength="50"/>
                   </div> 
                 </div>
              </div>    
               <div class="col-lg-12 col-md-6 col-sm-12">
               <div class="form-group">
                 <label for="inputEmail3" class="col-form-label col-lg-2 col-md-6 col-sm-12"><span style="color:red;">*</span>Type of Account: </label> 
                   <div class="col-lg-4 col-md-6 col-sm-12">
                     <div class="form-check" style="display: inline-block;">
					  <input class="form-check-input" style="vertical-align: middle;" type="radio" name="exampleRadios" id="account1" value="saving" checked>
					  <label class="form-check-label" for="account1">
					    Saving
					  </label>
					</div>
					<div class="form-check" style="display: inline-block;">
					  <input class="form-check-input" style="vertical-align: middle;" type="radio" name="exampleRadios" id="account2" value="current">
					  <label class="form-check-label" for="account2">
					    Current
					  </label>
					</div>
					<div class="form-check" style="display: inline-block;">
					  <input class="form-check-input" style="vertical-align: middle;" type="radio" name="exampleRadios" id="account3" value="cash credit">
					  <label class="form-check-label" for="account3">
					    Cash Credit
					  </label>
					</div>
					<input type="hidden" id="radioVal" value="">
                   </div> 
                 </div>
              </div>
               <div class="col-lg-12 col-md-6 col-sm-12">
               <div class="form-group">
                 <label for="inputEmail3" class="col-form-label col-lg-2 col-md-6 col-sm-12"><span style="color:red;">*</span>IFSC Code</label> 
                   <div class="col-lg-4 col-md-6 col-sm-12">
                   <input type="text" class="form-control" id="ifscCodeMandateForm"  maxlength="11" placeholder="Enter IFSC Code">
                   </div> 
                 </div>
              </div>
              
              <div class="col-lg-12 col-md-6 col-sm-12">
               <div class="form-group">
                 <label for="inputEmail3" class="col-form-label col-lg-2 col-md-6 col-sm-12"><span style="color:red;">*</span>Download Bank Mandate Form:</label> 
                   <div class="col-lg-4 col-md-6 col-sm-12">
                    <a href="${pageContext.request.contextPath}/Download/Bankmandate through RTGS.doc" target="_blank" download><i class="fa fa-file-word-o" aria-hidden="true" style="color:red; font-size: 20px;"></i> Form</a>
                   </div> 
                 </div>
              </div>
              
              <div class="col-lg-12 col-md-6 col-sm-12">
               <div class="form-group">
               
                 <label for="inputEmail3" class="col-form-label col-lg-2 col-md-6 col-sm-12""><span style="color:red;">*</span>Upload Document</label> 
                   <div class="col-lg-4 col-md-6 col-sm-12">
                    <div class="custom-file">
                    <input  type="file" id="customFile" class="form-control-file">
                          <!--  <input type="file" class="custom-file-input custom-file-input1" id="customFile">
                           <label class="custom-file-label custom-file-label1" for="customFile">No file choosen</label> -->
                     </div>
                     <!-- <div class="form-control" id="displayfileName">
                    
                    </div> -->
                    
                     </div>
                 </div>
                  </div> 
                  <div class="col-lg-12 col-md-6 col-sm-12">
                   <div class="form-group">
                 <label for="inputEmail3" class="col-form-label col-lg-2 col-md-6 col-sm-12">Uploaded Document</label> 
                  <div class="col-lg-4 col-md-6 col-sm-12">
                    <a href="#"  id="downLoadBankMandateAgainInfo"><i  id="downLoadBankMandateDoc" aria-hidden="true" style="color:red; font-size: 20px;"></i></a>
                  </div>
                  </div>
                  </div>
              <div class="col-lg-12 col-md-6 col-sm-12">
               <div class="form-group">
                 <label for="inputEmail3" class="col-form-label col-lg-2 col-md-6 col-sm-12">Status</label> 
                   <div class="col-lg-4 col-md-6 col-sm-12">
                   <input type="text" class="form-control" id="approvalStatus"  readonly="readonly">
                   </div> 
                 </div>
              </div>              
              <div class="col-lg-12 col-md-6 col-sm-12">
               <div class="form-group">
                 <label for="inputEmail3" class="col-form-label col-lg-2 col-md-6 col-sm-12"><span style="color:red;">*</span>Maker Remark:</label> 
                   <div class="col-lg-4 col-md-6 col-sm-12"> 
                     <textarea class="form-control" id="makerRemarkMandateForm" placeholder="Enter maker Remark"></textarea>
                   </div> 
                 </div>
              </div>
              <div class="col-lg-12 col-md-6 col-sm-12">
               <div class="form-group">
                 <label for="inputEmail3" class="col-form-label col-lg-2 col-md-6 col-sm-12">Checker Remark:</label> 
                   <div class="col-lg-4 col-md-6 col-sm-12"> 
                      <textarea class="form-control" id="checkerRemarkMandateForm" placeholder="Enter Checker Remark"   readonly="readonly"></textarea>
                   </div> 
                 </div>
              </div>
              
              </div>
              
             <div class="col-lg-12 col-md-6 col-sm-12">
             	<div class="form-check">
				 <div style="width:2%;float:left;">
				  <input class="form-check-input" type="checkbox" name="declartionMandate" value="true" id="defaultCheck1">
				  </div>
				  <div style="width:94%;float:left;">
				  <label class="form-check-label" for="defaultCheck1">
				    I hereby declare that the particulars given above are correct and complete. If any transaction is delayed or not effected for reasons of incomplete or incorrect information, I shall not hold CGTMSE / CGTMSE's operating Bank responsible. I also undertake to advise any change in the particulars of my account to facilitate up-dation of records for purpose of credit of amount through RBI RTGS/NEFT. I authorize CGTMSE to credit the proceeds of Claims to the above mentioned account.
				  </label>
				  </div>
				</div>
             </div>   
             <div class="col-lg-12 col-md-6 col-sm-12" style="margin-top:10px;  ">
             	<button type="button" class="btn btn-approve" id="saveMandateForm">Save</button>
             	<button type="button" class="btn btn-reset" id="clearMandateForm">Clear</button>
             	<button type="button" class="btn btn-reset" id="exitMandateForm"><a href="claim-upload.html">Exit</a></button>
             </div>       
	</div>				
	
	</div>
</div>
</form>
</body>

<script type="text/javascript">

getBankMandateFormMliDetails();
function getBankMandateFormMliDetails() {
		
			$.ajax({
				url : '${pageContext.request.contextPath}/bank_MandateFormData_MliStatus.html',
				cache : false,
				crossDomain : true,
				type : 'GET',
				dataType : 'json',
				contentType : 'application/json; charset=utf-8',
				success : function(result) {
					console.log("kkkkkkk",result);
					//debugger;
					/* $('#displayfileName').val(result.uploadDoc); */
					var AccountType = result.typeOfAcc;
					if(AccountType == "" || AccountType == "null" || AccountType == "saving"){
						$("#account1").prop('checked', true);
					}
					if(AccountType != "" && AccountType == "current"){
						$("#account2").prop('checked', true);
					}
					if(AccountType != "" && AccountType == "cash credit"){
						$("#account3").prop('checked', true);
					}
					if(result.approveStatus=='MC_R' || result.approveStatus=='CG_R')
					{	
					$('#bankId').val(result.id);
					$('#contactNoBankMandate').val(result.mliContactNo);
					$('#mobliNoBankMandate').val(result.mliMobileNo);
					$('#emailIdBankMandate').val(result.mliEmailId);
					$('#bankNameMandateForm').val(result.bankName);
					$('#branchNameMandateForm').val(result.branch);
					$('#accountNameMandateForm').val(result.accName);
					$('#accountNoMandateForm').val(result.accNum);
	                //$("input[name=exampleRadios][value="+a+"]").prop('checked', true);
	                $('#ifscCodeMandateForm').val(result.ifscCode);
	                $('#downLoadBankMandateDoc').text(result.uploadDoc);
	                $('#bankMandatecheckerStatus').val(result.approveStatus);
	                $('#makerRemarkMandateForm').val(result.mRemarks);
	                $('#checkerRemarkMandateForm').val(result.cRemarks);
	                $('#defaultCheck1').prop("checked", result.declaration);
				    $('#approvalStatus').val((result.approveStatus=='MC_R')? 'REJECTED BY CHECKER':'REJECTED');
				    $('#saveMandateForm').text('Update');
				   
					}else if(result.approveStatus=='MC_A' || result.approveStatus=='CG_A'|| result.approveStatus=='MM'){
						$('#bankId').val(result.id);
						$('#contactNoBankMandate').val(result.mliContactNo);
						$('#mobliNoBankMandate').val(result.mliMobileNo);
						$('#emailIdBankMandate').val(result.mliEmailId);
						$('#bankNameMandateForm').val(result.bankName);
						$('#branchNameMandateForm').val(result.branch);
						$('#accountNameMandateForm').val(result.accName);
						$('#accountNoMandateForm').val(result.accNum);
						$('#radioVal').val(result.typeOfAcc);
						var b = document.getElementById("radioVal").value;
		                //$("input[name=exampleRadios][value=" + result.typeOfAcc + "]").prop('checked', true);
		                $('#ifscCodeMandateForm').val(result.ifscCode);
		                $('#downLoadBankMandateDoc').text(result.uploadDoc);
		                $('#bankMandatecheckerStatus').val(result.approveStatus);
		                $('#makerRemarkMandateForm').val(result.mRemarks);
		                $('#checkerRemarkMandateForm').val(result.cRemarks);
		                
	                    $('#defaultCheck1').prop("checked", result.declaration);
	                    $('#approvalStatus').val((result.approveStatus=='MC_A')?'SUBMITTED TO CGTMSE':(result.approveStatus=='CG_A')? 'APPROVED':'SUBMITTED TO MLI CHECKER');
				
							$('#contactNoBankMandate').prop('disabled', true);
						    $('#mobliNoBankMandate').prop('disabled', true);
						    $('#emailIdBankMandate').prop('disabled', true);
						    $('#bankNameMandateForm').prop('disabled', true);
						    $('#branchNameMandateForm').prop('disabled', true);
						    $('#accountNameMandateForm').prop('disabled', true);
						    $('#accountNoMandateForm').prop('disabled', true);
						    $('#ifscCodeMandateForm').prop('disabled', true);
						    $('#bankMandatecheckerStatus').prop('disabled', true);
						    $('#makerRemarkMandateForm').prop('disabled', true);
						    $('#customFile').prop('disabled', true); 
						    $('#saveMandateForm').prop('disabled', true);
						    $('#clearMandateForm').prop('disabled', true);
						   
								
						}
		},
				error : function(xhr, httpStatusMessage,
						customErrorMessage) {
					alert(xhr.responseText);
				}
	});
}  

$(document).on('click','#saveMandateForm',function() {
	
	if (confirm("Do you want to Save?")) {

		var filenameLen = $('#customFile')[0].files.length;
		if (filenameLen == 0) {
			alert("No file selected");
			return false
		}
		var filename = $('#customFile')[0].files[0].name;
		var fileExtType = $('#customFile')[0].files[0].type;
		if (fileExtType != '') {
			switch (fileExtType) {
			/* case 'application/msword':
				break;
			case 'application/vnd.openxmlformats-officedocument.wordprocessingml.document':
				break; */
			case 'application/pdf':
			   break;
			default:
				//alert(fileExtType + " : Unsupported file type.");
				alert("Upload mandate form in pdf format");
				return false
			}
		}
	

		var mliName = $('#mliNameBankMandate').val();
		var mliId = $('#mliIdBankMandate').val();
		var mliContactNo = $('#contactNoBankMandate').val();
		var mliMobileNo = $('#mobliNoBankMandate').val();
		var mliEmailId = $('#emailIdBankMandate').val();
		var bankName = $('#bankNameMandateForm').val();
		var branchName = $('#branchNameMandateForm').val();
		var accountName = $('#accountNameMandateForm').val();
		var accountNo = $('#accountNoMandateForm').val();
		var typeOfAcc = $("input[name='exampleRadios']:checked").val();
		var ifscCode = $('#ifscCodeMandateForm').val();
		var uploadDoc = $('#customFile').val();
		var makerRemark = $('#makerRemarkMandateForm').val();
		var checkerRemark=$('#checkerRemarkMandateForm').val();
		var mandateFormDeclartion=$("input[name='declartionMandate']:checked").val();
		var loginId=$('#mliLoginId').val();
		var ifscCodeLen=ifscCode.length;
		var mobilelen=mliMobileNo.length;
		var bankId = $('#bankId').val();
		//alert(uploadDoc);
		//alert(mliName);
		//alert("typeOfAccont:::"+typeOfAcc);
		//alert(ifscCodeLen);
		//alert("declartion::"+mandateFormDeclartion);
		if (mliContactNo == '') {
			alert('Please fill the Contact No');
			return false;
		}
		/* if (isNumberKey(mliContactNo)==false) {
			alert('Please enter number Only');
			return false;
		} */
		
		if (mliMobileNo == '') {
			alert('Please fill the Mobile No');
			return false;
		}
		if(mob_validate(mliMobileNo)==false){
			alert('Please enter mobile Number');
	          return false;
	     }
		if (mobilelen < 10) {
			alert('Please enter 10 digit Mobile No');
			return false;
		}
	
		if (mliEmailId == '') {
			alert('Please fill the Email Id');
			return false;
		}
		
	             if (IsEmail(mliEmailId) == false) {
						alert('Email Id is not Correct');
						return false;
					}
					if (bankName == '') {
						alert('Please fill the Bank Name');
						return false;
					}
					if (branchName == '') {
						alert('Please fill the Branch Name');
						return false;
					}
					if (accountName == '') {
						alert('Please fill the Account Name');
						return false;
					}
					if (accountNo == '') {
						alert('Please fill the Account Number');
						return false;
					}
					if (ifscCode == '') {
						alert('Please enter IFSC Code');
						return false;
					}
					if (ifscCodeLen <11) {
						alert('Please enter 11 digit IFSC Code');
						return false;
					}
					if (uploadDoc == '') {
						alert('Please select File ');
						return false;
					}
					if (makerRemark == '') {
						alert('Please enter maker remark');
						return false;
					}
					if (mandateFormDeclartion != 'true') {
						alert('Please select declaration before proceeding');
						return false;
					}
					var jsonData = {
							
						"bankId":bankId,
						"mlIName" : mliName,
						"milId" : mliId,
						"mliContactNo" : mliContactNo,
						"mliMobileNo" : mliMobileNo,
						"mliEmailId" : mliEmailId,
						"bankName" : bankName,
						"branch" : branchName,
						"accName" : accountName,
						"accNum" : accountNo,
						"typeOfAcc" : typeOfAcc,
						"ifscCode" : ifscCode,
						"uploadDoc" : filename,
						"mRemarks" : makerRemark,
						"cRemarks" : checkerRemark,
						"declaration" : mandateFormDeclartion,
						"loginId" : loginId
					};

					var mandateFData = new FormData();
					mandateFData.append('objField', JSON.stringify(jsonData));
					mandateFData.append('uploadfile', $('#customFile')[0].files[0]);
					$.ajax({
						type : 'POST',
						url : 'bankMandateFormMakerSave.html',
						processData : false, // tell jQuery not to process the data
						contentType : false, // tell jQuery not to set contentType
						cache : false,
						crossDomain : true,
						data : mandateFData,
						success : function(data) {
							//alert('show data');
							alert(data);
							getBankMandateFormMliDetails();
						},
						error : function(xhr, httpStatusMessage,
								customErrorMessage) {
							alert(xhr.responseText);
							
						}
					});

				} else {
					return false;
				}
			});

	$(document).on('click', '#clearMandateForm', function() {

		$('#myform').reset();
	});


	$(document).on('click','#downLoadBankMandateAgainInfo',function() {
		 var mliId = $('#mliIdBankMandate').val();
		 var bankName=$('#bankNameMandateForm').val();
			//alert("mliId"+mliId);
			if (bankName != null) {
				window.location.href = '${pageContext.request.contextPath}/download-mandate.html?mliId='+ mliId;
			}else{
	               alert("Data not found");
	               return false;
				}
		});
		

	function IsEmail(email) {
		var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if (!regex.test(email)) {
			return false;
		} else {
			return true;
		}
	}

	function mob_validate(phno) {
		var regexPattern = new RegExp(/^[0-9-+]+$/); // regular expression pattern
		return regexPattern.test(phno);
	}

	function isNumberKey(evt){
	    var charCode = (evt.which) ? evt.which : evt.keyCode
	    if (charCode > 31 && (charCode < 48 || charCode > 57))
	        return false;
	    return true;
	}


 </script>
</html>