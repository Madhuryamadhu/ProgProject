<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
</head>
<body class="container" style="background-color:#E6EBE0;">
 <div class="col-lg-12 col-md-12 col-sm-12">
	 <h1>Types</h1>
			<div class="adminPageFilterDiv col-lg-12 col-md-12 col-sm-12">
				<div class="form-group col-lg-5 col-md-12 col-sm-12">
					<label for="email">Name:</label>
					 <input type="text" class="form-control">
				</div>
				<div class="form-group col-lg-5 col-md-12 col-sm-12">
					<label for="compl">Id:</label>
					<input type="text" class="form-control">
				</div>
				<div class="form-group col-lg-2 col-md-12 col-sm-12" style="vertical-align:bottom;">
					<button type="submit" class="btn btn-default filterSubmitBtn" id="submit">Search</button>
				</div>
			</div>
			<button type="submit" class="btn createBtnTable" onclick="loadCreateTypePage()" >Create</button>
			<table class="adminsTable" id="typesTable"></table>
			<div class="col-lg-12 col-md-12 col-sm-12" id="paginationDivAdmin"></div>
 </div>
</body>
 <script type="text/javascript">
    $(document).ready(function() {
    	$('#actionBtns').addClass('hide');
    	loadTypesForAdmin(false,'0','10');
 	});
    
    
    function loadTypesForAdmin(fromPagination,pageNum,perPageCount){
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
  			url : "getTypesForAdmin",
  			data : JSON.stringify(dataArray),
  			dataType : 'json',
  			timeout : 100000,
  			success : function(data) {
  				if (data.status == 0) {
  					if(data.totalCountofPages>0){
  						buildDataTable('typesTable',data.typeList,'XS,S,S,B,B,B');
  						
  						 if(!fromPagination){
  							buildPagination(data.totalCountofPages,'loadTypesForAdmin','paginationDivAdmin');
  						}
  						//Pagination Part-To handle Prev and Next-2
  					   if(pageNumTemp=='prev'){
  						   addActive('paginationDiv',activePagePrev);
  					   }else if(pageNumTemp=='next'){
  						   addActive('paginationDiv',activePageNext);
  					   }
  					}else{
  						$('#typesTable').empty();
  						$('#paginationDiv').empty();
  						$('#typesTable').append('<tbody><tr><th><p style="color: gray; text-align: center;">Sorry Currently there are no programs to show.Will update soon!!</p></th></tr></tbody>');
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
    
    function loadCreateTypePage(){
    	 var paremeters = {};
         loadNewPage('/adminPages/types/createType.jsp',paremeters,'content');
    }
    
    function loadModifyTypePage(typeId){
   	 var paremeters = {};
   	paremeters["typeId"]=typeId;
        loadNewPage('/adminPages/types/modifyType.jsp',paremeters,'content');
   }
    
    
    
    
    
    
    function deleteType(typeId){
   	 var dataArray = {}
 	   dataArray["type"]=typeId;
 		$.ajax({
 			type : "POST",
 			contentType : "application/json",
 			url : "deleteType",
 			data : JSON.stringify(dataArray),
 			timeout : 100000,
 			success : function(data) {
 				if(data.status==0){
 					loadTypesForAdmin(false,'0','10');
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
    
    
    function hideType(typeId,isHidden){
  	  var dataArray = {}
  	   dataArray["type"]=typeId;
  	  if(isHidden=='1'){
  		  dataArray["isHidden"]=0;
  	  }else{
  		  dataArray["isHidden"]=1;
  	  }
  	   
  		$.ajax({
  			type : "POST",
  			contentType : "application/json",
  			url : "hideShowType",
  			data : JSON.stringify(dataArray),
  			timeout : 100000,
  			success : function(data) {
  				if(data.status==0){
  					loadTypesForAdmin(false,'0','10');
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
