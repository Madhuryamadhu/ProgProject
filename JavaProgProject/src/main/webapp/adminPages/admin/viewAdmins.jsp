<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<div>
		<h1>Admin View</h1>
			<button type="submit" class="btn createBtnTable" onclick="loadCreateAdminPage()">Create</button>
			<table class="adminsTable" id="adminViewTable"></table>
		</div>
</body>
  <script type="text/javascript">
    $(document).ready(function() {
    	$('#actionBtns').addClass('hide');
 	   loadAdmins(false,'0','10');
 	});
    
    
    function loadAdmins(fromPagination,pageNum,perPageCount){
    	 //Pagination Part-To handle Prev and Next-1
   	   var pageNumTemp=pageNum;
   	   if(pageNumTemp=='prev'){
   		   pageNum=activePagePrev*10;
   	   }else if(pageNumTemp=='next'){
   		   pageNum=activePageNext*10;
   	   }
   	   
   	   var dataArray = {}
       dataArray["adminLevel"] = '${sessionScope.admin_level}';
       dataArray["adminId"] = '${sessionScope.admin_Id}';
       dataArray["pageNumber"]=pageNum;
  	   dataArray["perPageCount"]=perPageCount;
   		$.ajax({
   			type : "POST",
   			contentType : "application/json",
   			url : "getAdminList",
   			data : JSON.stringify(dataArray),
   			dataType : 'json',
   			timeout : 100000,
   			success : function(data) {
   				if (data.status == 0) {
   					if(data.totalCountofPages>0){
   						buildDataTable('adminViewTable',data.adminsList,'XS,S,S,S,S,XS,XS,B,B,B');
   						
   						 if(!fromPagination){
   							buildPagination(data.totalCountofPages,'loadAdmins','paginationDivAdmin');
   						}
   						//Pagination Part-To handle Prev and Next-2
   					   if(pageNumTemp=='prev'){
   						   addActive('paginationDiv',activePagePrev);
   					   }else if(pageNumTemp=='next'){
   						   addActive('paginationDiv',activePageNext);
   					   }
   					}else{
   						$('#adminViewTable').empty();
   						$('#paginationDiv').empty();
   						$('#adminViewTable').append('<tbody><tr><th><p style="color: gray; text-align: center;">Sorry Currently there are no programs to show.Will update soon!!</p></th></tr></tbody>');
   					}
   					
   				}
   			},
   			error : function(e) {
   				console.log("ERROR: ", e);
   			},
   			done : function(e) {
   				console.log("getTypeDetails DONE");
   			}
   		});
   		
   		//to move to top of the page on load of programs
   		$(this).scrollTop(0);
    }
    
    
    function loadCreateAdminPage(){
    	var paremeters = {};
        loadNewPage('/adminPages/admin/createAdmin.jsp',paremeters,'content');
    }
    
    function loadModifyAdmin(adminId){
    	var paremeters = {};
    	paremeters["adminId"]=adminId;
        loadNewPage('/adminPages/admin/modifyAdmin.jsp',paremeters,'content');
    }
    
    
    function deactivateAdmin(adminId,isActive){
    	var dataArray = {};
   	   dataArray["adminId"]=adminId;
   	  if(isActive=='1'){
   		  dataArray["adminIsActive"]=0;
   	  }else{
   		  dataArray["adminIsActive"]=1;
   	  }
   		$.ajax({
   			type : "POST",
   			contentType : "application/json",
   			url : "deactivateAdmin",
   			data : JSON.stringify(dataArray),
   			timeout : 100000,
   			success : function(data) {
   				if(data.status==0){
					showAlert('Admin Deactivated Successfully','success');
					refreshPage();
				}else{
					showAlert('Admin Deactivation failed','error');
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
    
    function deleteAdmin(adminId){
      	 var dataArray = {};
      	 dataArray["adminId"]=adminId;
    		$.ajax({
    			type : "POST",
    			contentType : "application/json",
    			url : "deleteAdmin",
    			data : JSON.stringify(dataArray),
    			timeout : 100000,
    			success : function(data) {
    				if(data.status==0){
    					showAlert('Admin Deleted Successfully','success');
    					refreshPage();
    				}else{
    					showAlert('Admin Deletion failed','error');
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
    
    function refreshPage(){
    	var paremeters = {};
		loadNewPage('/adminPages/admin/viewAdmins.jsp',paremeters,'content');
    }
   </script>
</html>