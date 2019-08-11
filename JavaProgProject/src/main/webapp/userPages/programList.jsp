<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
   <body>
   		 <!-- FILTER SECTION -->
         <div class="progFilterDiv col-lg-12 col-md-12 col-sm-12">
            <div class="container col-lg-12 col-md-12 col-sm-12" style="margin-top: 15px;">
               <div class="form-group col-lg-4 col-md-12 col-sm-12">
                  <label for="type">Type:</label>
                  <select class="form-control" id="type"></select>
               </div>
               <div class="form-group col-lg-4 col-md-12 col-sm-12">
                  <label for="complexity">Complexity:</label>
                  <select class="form-control" id="complexity"></select>
               </div>
               <div class="form-group col-lg-4 col-md-12 col-sm-12">
                 <div class="col-lg-6 col-md-12 col-sm-12">
             	    <button type="submit" class="btn btn-default filterSubmitBtn col-lg-12 col-md-12 col-sm-12" onclick="loadPrograms(false,'0','10')">Submit</button>
                 </div>
                 <div class="col-lg-6 col-md-12 col-sm-12">
            	     <button class="btn btn-default filterSubmitBtn col-lg-12 col-md-12 col-sm-12" onclick="resetPrograms()">Reset</button>
                 </div>
               </div>
            </div>
         </div>
         
         <!-- PROGRAM LIST SECTION -->
         <div class="progContentDiv col-lg-12 col-md-12 col-sm-12">
            <h1 class="h1BlackBold">List Of Java Programs</h1>
            <p>Click on the program to view the solution.Use the filter to search quickly!!</p>
            <table class="userTable" id="programTable"></table>
            <div class="col-lg-12 col-md-12 col-sm-12" id="paginationDiv"></div>
         </div>
   </body>
   <script type="text/javascript">
   $(document).ready(function() {
	   //Loading dropdowns
	   loadDropDown('type',typeMap);
	   getComplexityDetails();
	   
	   loadPrograms(false,'0','10');
	   

		
	});
   
   
   function loadPrograms(fromPagination,pageNum,perPageCount,typeId){
	   //Pagination Part-To handle Prev and Next-1
	   var pageNumTemp=pageNum;
	   if(pageNumTemp=='prev'){
		   pageNum=activePagePrev*10;
	   }else if(pageNumTemp=='next'){
		   pageNum=activePageNext*10;
	   }
	   
	   
	   var dataArray = {}
	   if(typeId){
		   dataArray["type"]=typeId;
	   }else{
		   dataArray["type"]=$('#type').val();
	   }
	   dataArray["complexity"]=$('#complexity').val();
	   dataArray["pageNumber"]=pageNum;
	   dataArray["perPageCount"]=perPageCount;
	   
	   
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "getProgramList",
			data : JSON.stringify(dataArray),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				if (data.status == 0) {
					if(data.totalCountofPages>0){
						buildListTableContent('programTable',data.programMap,'loadProgramPage');
						addLikeDislikeCount('programTable',data.likeCountMap,data.dislikeCountMap);
						if(!fromPagination){
							buildPagination(data.totalCountofPages,'loadPrograms','paginationDiv');
						}
						
						//Pagination Part-To handle Prev and Next-2
					   if(pageNumTemp=='prev'){
						   addActive('paginationDiv',activePagePrev);
					   }else if(pageNumTemp=='next'){
						   addActive('paginationDiv',activePageNext);
					   }
					}else{
						$('#programTable').empty();
						$('#paginationDiv').empty();
						$('#programTable').append('<tbody><tr><th><p style="color: gray; text-align: center;">Sorry Currently there are no programs to show.Will update soon!!</p></th></tr></tbody>');
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
   
   
   function resetPrograms(){
	   $('#type').val('');
	   $('#complexity').val('');
	   loadPrograms(false,'0','10');
   }
   
   
   function getComplexityDetails() {
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
					loadDropDown('complexity',data.complexityMap);
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
   
   
   function loadProgramPage(progId){
	   var dataArray = {}
	   dataArray["progId"]=progId;
		$.ajax({
			type : "POST",
			url : "getProgramViewPage",
			data : dataArray,
			timeout : 100000,
			success : function(data) {
					 $('#userPageContent').show();
					 $('#sidebarDiv').show();
					 $('#userPageContent').html(data);
					 $('#adminPageContent').hide();
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("getTypeDetails DONE");
			}
		});
   }
   
   function addLikeDislikeCount(id,likeMap,dislikeMap){
	   for (var key in dislikeMap) {
		    if (dislikeMap.hasOwnProperty(key)) {  
		    	var dislikeCountDiv="";
		    	dislikeCountDiv ='<button type="button" class="likeDislikeBtn" disabled><i class="fa fa-thumbs-o-down"></i><span id="dislikeclicks">'+dislikeMap[key]+'</span></button>';
		    	$('#th_'+id+'_'+key).append(dislikeCountDiv);
		    }
		}
	   for (var key in likeMap) {
		    if (likeMap.hasOwnProperty(key)) {  
		    	var likeCountDiv="";
		    	likeCountDiv ='<button type="button" class="likeDislikeBtn" disabled><i class="fa fa-thumbs-o-up"></i><span id="dislikeclicks">'+likeMap[key]+'</span></button>';
		    	$('#th_'+id+'_'+key).append(likeCountDiv);
		    }
		}
   }
   </script>
</html>