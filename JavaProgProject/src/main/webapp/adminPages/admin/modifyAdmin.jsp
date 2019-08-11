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
<div>
			<h1>Modify Admin</h1>
			<div class="col-lg-12 col-md-12 col-sm-12 first">
				<div class="form-group col-lg-4 col-md-4 col-sm-12">
					<label for="userName">UserName:</label>
					 <input type="text" class="form-control" id="userName">
				</div>
				<div class="form-group col-lg-4 col-md-4 col-sm-12 pwd">
					<label for="password">Password:</label>
					<input type="password" class="form-control" id="password">
				</div>
				 <div class="form-group col-lg-3 col-md-3 col-sm-12">
					<label for="level">Level:</label>
					<select class="form-control" id="level">
						<option value>Select</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
					</select>
				</div>
				<div class="form-group col-lg-1 col-md-1 col-sm-12">
					<label for="isActive" id="hideLabel">Active:</label></br>
					<input type="checkbox" class="checkBox" id="isActive" checked>
				</div>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12" style="vertical-align:bottom;">
				<div class="form-group col-lg-12 col-md-12 col-sm-12 col-lg-offset-5">
					<button type="submit" class="btn" id="submitCreate" onclick="modifyAdmin()">Modify</button>
				</div>
			</div>
		</div>
		<input type="text" class="form-control hide" id="adminId" value="<%= session.getAttribute("admin_Id") %>" />
</body>
<script type="text/javascript">
var adminId="";
$(document).ready(function() {
	adminId='<c:out value="${parameters.adminId}"></c:out>';
	loadModifyAdmin();
 });



function loadModifyAdmin(){
	var dataArray = {}
	dataArray["adminId"]=adminId;
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "loadAdminForModify",
			data : JSON.stringify(dataArray),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				$('#userName').val(data.username);
				$('#password').val(data.password);
				$('#level').val(data.adminLevel);
				if(data.adminIsActive==0){
					$('#isActive').prop('checked', false);
				}else{
					$('#isActive').prop('checked', true);
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

function modifyAdmin(){
	var dataArray = {};
	dataArray["adminId"]=adminId;
	dataArray["username"]=$('#userName').val();
	dataArray["password"]=$('#password').val();
	dataArray["adminLevel"]=$('#level').val();
	if($('#isActive').is(':checked')){
	   dataArray["adminIsActive"]=1;
    }else{
	   dataArray["adminIsActive"]=0;
    }
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "modifyAdmin",
			data : JSON.stringify(dataArray),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				if(data.status==0){
					showAlert('Admin Modified Successfully','success');
					var paremeters = {};
					loadNewPage('/adminPages/admin/viewAdmins.jsp',paremeters,'content');
				}else{
					showAlert('Admin Modification failed','error');
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
</script>
</html>