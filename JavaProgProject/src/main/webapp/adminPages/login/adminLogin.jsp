<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
</head>
<body>
 <div id="fullcontent">	
		<div class="col-lg-12 col-md-12 col-sm-12" id="content" style="background-color:#FFFFFF; box-shadow: 10px 10px 5px grey;">
		
		    <div class="col-lg-12 col-md-12 col-sm-12" style="text-align: center;"><h1>Login</h1></div>
			<div class="col-lg-12 col-md-12 col-sm-12">
			    <div class="form-group col-lg-3 col-md-12 col-sm-12"></div>
				<div class="form-group col-lg-6 col-md-12 col-sm-12">
					<label for="email">UserName:</label>
					 <input type="text" class="form-control" id="username" />
				</div>
				<div class="form-group col-lg-3 col-md-12 col-sm-12"></div>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12">
			    <div class="form-group col-lg-3 col-md-12 col-sm-12"></div>
				<div class="form-group col-lg-6 col-md-12 col-sm-12 loginDiv">
					<label for="compl">Password:</label>
					<input type="text" class="form-control" id="password" />
				</div>
				<div class="form-group col-lg-3 col-md-12 col-sm-12"></div>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12" style="vertical-align:bottom;">
			    <div class="form-group col-lg-3 col-md-12 col-sm-12"></div>
				<div class="form-group col-lg-3 col-md-12 col-sm-12">
					<button type="submit" class="btn col-lg-12 col-md-12 col-sm-12" id="login" onclick="adminLogin()">Login</button>
				</div>
				<div class="form-group col-lg-3 col-md-12 col-sm-12">
					<button type="submit" class="btn col-lg-12 col-md-12 col-sm-12" id="login" onclick="goToHomePage()">Back</button>
				</div>
				<div class="form-group col-lg-3 col-md-12 col-sm-12"></div>
			</div>
			</div>
            <input type="text" class="form-control hide" id="adminId" value="<%= session.getAttribute("admin_Id") %>" />
		
	</div>
</body>
<script type="text/javascript">

//GLOBAL VARIABLES
var typeMap;

$(document).ready(function() {
	if(isValid($('#adminId').val())){
		var paremeters = {};
		loadNewPage('/adminPages/adminMainPage.jsp',paremeters,'fullcontent');
	}
});


function adminLogin(){
	   var dataArray = {};
	   dataArray["username"]=$('#username').val();
	   dataArray["password"]=$('#password').val();
	   
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "adminLogin",
			data : JSON.stringify(dataArray),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				
				if(data.status==0){
					var paremeters = {};
					loadNewPage('/adminPages/adminMainPage.jsp',paremeters,'fullcontent');
					showAlert('Login Successful','success');
				}else if(data.status==2){
					showAlert('Login Failed(Admin is Inactive)','error');
				}else if(data.status==1){
					showAlert('Login Failed(wrong username and password)','error');
				}
				 
			},
			error : function(e) {
				console.log("getTypeDetails DONE");
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("getTypeDetails DONE");
			}
		});
}

function goToHomePage(){
	   loadPage('0');
}
</script>

</html>
