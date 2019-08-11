<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="col-lg-12 col-md-12 col-sm-12">
<h1>Create Types</h1>
			
			<div class="col-lg-12 col-md-12 col-sm-12 first">
				
				<div class="form-group col-lg-6 col-md-12 col-sm-12">
					<label for="name">Name:</label>
					 <input type="text" class="form-control" id="name">
				</div>
				
				<!-- <div class="form-group col-lg-5 col-md-12 col-sm-12">
					<label for="complexityType">Level:</label>
					<select class="form-control" id="complexityType"></select>
				</div> -->
				
				
				<div class="form-group col-lg-1 col-md-12 col-sm-12">
				<label for="isHidden" id="hideLabel">Hide:</label></br>
					<input type="checkbox" id="isHidden" checked>
				</div>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12" style="vertical-align:bottom;">
				<div class="form-group col-lg-12 col-md-12 col-sm-12 col-lg-offset-5">
					<button type="submit" class="btn" id="submitCreate" onclick="createType()">Create</button>
				</div>
			</div>
			</div>
</body>

<script type="text/javascript">

$(document).ready(function() {
	
 });
 
function createType(){
	   var dataArray = {}
	   dataArray["typeName"]=$('#name').val();
	   if($('#isHidden').is(':checked')){
		   dataArray["isHidden"]=1;
	   }else{
		   dataArray["isHidden"]=0;
	   }
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "createType",
			data : JSON.stringify(dataArray),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				 var paremeters = {};
				loadNewPage('/adminPages/adminMainPage.jsp',paremeters,'fullcontent');
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("getTypeDetails DONE");
			}
		});
}

</script>
</html>