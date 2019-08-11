<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<div class="col-lg-12 col-md-12 col-sm-12">
<h1>Modify Types</h1>
			
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
					<input type="checkbox" id="isHidden">
				</div>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12" style="vertical-align:bottom;">
				<div class="form-group col-lg-12 col-md-12 col-sm-12 col-lg-offset-5">
					<button type="submit" class="btn" id="submitCreate" onclick="modifyType()">Modify</button>
				</div>
			</div>
			</div>
</body>

<script type="text/javascript">
var typeId="";
$(document).ready(function() {
	typeId='<c:out value="${parameters.typeId}"></c:out>';
	
	loadTypeData();
 });
 
function loadTypeData(){
	var dataArray = {}
	   dataArray["type"]=typeId;
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "loadTypeForModify",
			data : JSON.stringify(dataArray),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				$('#name').val(data.typeName);
				if(data.isHidden==0){
					$('#isHidden').prop('checked', false);
				}else{
					$('#isHidden').prop('checked', true);
				}
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("getTypeDetails DONE");
			}
		});
	
}



function modifyType(){
	   var dataArray = {};
	   
	   dataArray["type"]=typeId;
	   dataArray["typeName"]=$('#name').val();
	   if($('#isHidden').is(':checked')){
		   dataArray["isHidden"]=1;
	   }else{
		   dataArray["isHidden"]=0;
	   }
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "modifyType",
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