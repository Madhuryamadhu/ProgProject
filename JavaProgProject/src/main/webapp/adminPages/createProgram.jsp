<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head></head>
<body>
<div class="col-lg-12 col-md-12 col-sm-12">
			<h1>Create Programs</h1>
			
			<div class="col-lg-12 col-md-12 col-sm-12 first">
				<div class="form-group col-lg-6 col-md-12 col-sm-12">
					<label for="email">Type:</label>
					<select class="form-control" id="typeCreate"></select>
				</div>
				<div class="form-group col-lg-5 col-md-12 col-sm-12">
					<label for="email">Name:</label>
					 <input id="progName" type="text" class="form-control">
				</div>
				
				<div class="form-group col-lg-1 col-md-12 col-sm-12">
					<label for="hide" id="hideLabel">Hide:</label></br>
					<input type="checkbox" id="hide" checked>
				</div>
				<div class="form-group col-lg-6 col-md-12 col-sm-12">
					<label for="complexityCreate">Complexity:</label>
					<select class="form-control" id="complexityCreate"></select>
				</div>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12">
				<div class="form-group col-lg-6 col-md-12 col-sm-12">
					<label for="email">Programs:</label>
					<textarea id="prog1" rows="4" cols="50" class="form-control"></textarea>
				</div>
				<div class="form-group col-lg-6 col-md-12 col-sm-12">
					<label for="email">Output:</label>
					<textarea id="output1" rows="4" cols="50" class="form-control"></textarea>
				</div>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12" style="vertical-align:bottom;">
				<div class="form-group col-lg-12 col-md-12 col-sm-12 col-lg-offset-5">
					<button type="submit" class="btn" id="submitCreate" onclick="createProgram()">Create</button>
				</div>
			</div>
		</div>
</body>
<script type="text/javascript">
   $(document).ready(function() {
	   loadDropDown('typeCreate',typeMap);
	   getComplexityDetails('complexityCreate');
    });
   
   function getComplexityDetails(id) {
		var dataArray = {}
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "getComplexity",
			data : JSON.stringify(dataArray),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				if (data.status == 0) {
					loadDropDown(id,data.complexityMap);
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
   
   function createProgram(){
	   var dataArray = {}
	   dataArray["type"]=$('#typeCreate').val();
	   dataArray["progName"]=$('#progName').val();
	   dataArray["isHidden"]=1;
	   dataArray["complexity"]=$('#complexityCreate').val();
	   dataArray["prog1"]=$('#prog1').val();
	   dataArray["output1"]=$('#output1').val();
	   
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "createProgram",
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