<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
   <head></head>
   <body>
         <div class="container adminActionsDiv col-lg-12 col-md-12 col-sm-12">
           <div class="col-lg-12 col-md-12 col-sm-12">
	           <div class="col-lg-6 col-md-12 col-sm-12">
	              <h4 id="adminActions">Admin Actions</h4>
	           </div>
	           <div class="col-lg-6 col-md-12 col-sm-12">
	          	  <button type="submit" onclick="hideShowAction()" class="btn-default tableButton_btn pull-right" style="margin-top: 12px; margin-right: -31px;"><i class="fa fa-angle-double-down" style="color: #A85BA6; font-size: 33px !important;"></i></button>
	           </div>
           </div>
           <div id="actionBtns" class="col-lg-12 col-md-12 col-sm-12 hide">
	            <button type="button" class="btn adminActionsBtn">Programs</button>
	            <button type="button" class="btn adminActionsBtn">Issues</button>
	            <button type="button" class="btn adminActionsBtn" onclick="loadAdminSectionPages('types')">Types</button>
	            <button type="button" class="btn adminActionsBtn" onclick="loadAdminSectionPages('admins')">Admins</button>
	            <button type="button" class="btn adminActionsBtn" onclick="goToHomePage()">HomePage</button>
	            <button type="button" class="btn adminActionsBtn" onclick="logout()">Logout</button>
            </div>
         </div>
         <div class="col-lg-12 col-md-12 col-sm-12" id="content" style="background-color:#FFFFFF; box-shadow: 10px 10px 5px grey;">
            <h1>Program Details</h1>
            <div class="adminPageFilterDiv  col-lg-12 col-md-12 col-sm-12">
               <div class="form-group col-lg-5 col-md-12 col-sm-12">
                  <label for="email">Date:</label>
                  <input type="text" class="form-control">
               </div>
               <div class="form-group col-lg-5 col-md-12 col-sm-12">
                  <label for="compl">Type:</label>
                  <input type="text" class="form-control">
               </div>
               <div class="form-group col-lg-2 col-md-12 col-sm-12" style="vertical-align:bottom;">
                  <button type="submit" class="btn btn-default filterSubmitBtn" id="submit">Search</button>
               </div>
            </div>
            <button type="submit" class="btn createBtnTable" onclick="loadCreateProgramPage()">Create</button>
            <table class="adminsTable" id="adminProgTable"></table>
            <div class="col-lg-12 col-md-12 col-sm-12" id="paginationDivAdmin"></div>
         </div>
   </body>
    <script type="text/javascript">
    $(document).ready(function() {
 	   loadProgramsForAdmin(false,'0','10');
 	});
    
    
    function loadProgramsForAdmin(fromPagination,pageNum,perPageCount){
 	   //Pagination Part-To handle Prev and Next-1
 	   var pageNumTemp=pageNum;
 	   if(pageNumTemp=='prev'){
 		   pageNum=activePagePrev*10;
 	   }else if(pageNumTemp=='next'){
 		   pageNum=activePageNext*10;
 	   }
 	   
 	   var dataArray = {}
 	   dataArray["type"]=$('#type').val();
 	   dataArray["complexity"]=$('#complexity').val();
 	   dataArray["pageNumber"]=pageNum;
 	   dataArray["perPageCount"]=perPageCount;
 		$.ajax({
 			type : "POST",
 			contentType : "application/json",
 			url : "getProgramsForAdmin",
 			data : JSON.stringify(dataArray),
 			dataType : 'json',
 			timeout : 100000,
 			success : function(data) {
 				if (data.status == 0) {
 					if(data.totalCountofPages>0){
 						buildDataTable('adminProgTable',data.programList,'XS,L,S,S,S,S,B,B,B');
 						
 						 if(!fromPagination){
 							buildPagination(data.totalCountofPages,'loadProgramsForAdmin','paginationDivAdmin');
 						}
 						//Pagination Part-To handle Prev and Next-2
 					   if(pageNumTemp=='prev'){
 						   addActive('paginationDiv',activePagePrev);
 					   }else if(pageNumTemp=='next'){
 						   addActive('paginationDiv',activePageNext);
 					   }
 					}else{
 						$('#adminProgTable').empty();
 						$('#paginationDiv').empty();
 						$('#adminProgTable').append('<tbody><tr><th><p style="color: gray; text-align: center;">Sorry Currently there are no programs to show.Will update soon!!</p></th></tr></tbody>');
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
    
    
    function loadCreateProgramPage(){
       var paremeters = {};
       paremeters["test"]='TESTVALUE';
       paremeters["test2"]='TESTVALUE';
       loadNewPage('/adminPages/createProgram.jsp',paremeters,'content');
    }
    
    
    function loadModifyProgramPage(progId){
        var paremeters = {};
        paremeters["progId"]=progId;
        loadNewPage('/adminPages/modifyProgram.jsp',paremeters,'content');
     }
    
    function deleteProgram(progId){
    	 var dataArray = {}
  	   dataArray["progId"]=progId;
  		$.ajax({
  			type : "POST",
  			contentType : "application/json",
  			url : "deleteProgram",
  			data : JSON.stringify(dataArray),
  			timeout : 100000,
  			success : function(data) {
  				if(data.status==0){
  					showAlert('Program deleted successfully','success');
  					loadProgramsForAdmin(false,'0','10');
  				}else{
  					showAlert('Program deletion failed','error');
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
    
  function hideProgram(progId,isHidden){
	  var dataArray = {}
	   dataArray["progId"]=progId;
	  if(isHidden=='1'){
		  dataArray["isHidden"]=0;
	  }else{
		  dataArray["isHidden"]=1;
	  }
	   
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "hideShowProgram",
			data : JSON.stringify(dataArray),
			timeout : 100000,
			success : function(data) {
				if(data.status==0){
					var paremeters = {};
					loadNewPage('/adminPages/adminMainPage.jsp',paremeters,'fullcontent');
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
    
  
  function loadAdminSectionPages(page){
	  var paremeters = {};
	  if(page=='types'){
		  loadNewPage('/adminPages/viewTypes.jsp',paremeters,'content');
	  }else if(page=='admins'){
		  loadNewPage('/adminPages/admin/viewAdmins.jsp',paremeters,'content');
	  }
		  
		
  }
  
  
  function logout(){
	   var dataArray = {};
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "logout",
			data : JSON.stringify(dataArray),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				if(data.status==0){
					showAlert('Logout Successful','success');
					loadPage('0');
				}else{
					showAlert('Logout Failed','error');
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
  
  
  function hideShowAction(){
	  if($('#actionBtns').hasClass('hide')){
		  $('#actionBtns').removeClass('hide');
	  }else{
		  $('#actionBtns').addClass('hide');
	  }
  }
    </script>
    
</html>