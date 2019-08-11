<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>

<html>
   <head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
     <script src="https://cdn.jsdelivr.net/gh/google/code-prettify@master/loader/run_prettify.js"></script>
   </head>
   <body>
      <div class="breadcrumbdiv col-lg-12 col-md-12 col-sm-12">
         <ul class="breadcrumb">
            <li><a onclick="goBack()">Home</a></li>
            <li>View Program</li>
         </ul>
      </div>
      <div class="col-lg-12 col-md-12 col-sm-12" id="content" style="background-color:#FFFFFF; border-radius: 5px; box-shadow: 10px 10px 5px grey; margin-top: 10px;">
         <h2 class="programName" ><u id="programName" style="font-weight: bold;"></u></h2>
         <button type="button" class="likeDislikeBtn" onclick="likeDislikeUpdate('dislike')"><i class="fa fa-thumbs-o-down"></i><span id="dislikeclicks"></span></button>
	     <button type="button" class="likeDislikeBtn" onclick="likeDislikeUpdate('like')"><i class="fa fa-thumbs-o-up"></i><span id="likeclicks"></span></button>
	     <button type="button" class="copy" onclick="copyToClipboard('programShowDiv')">Copy</button>
		
         
        <!--  <div class="programShowDiv col-lg-12 col-md-12 col-sm-12"> --><pre class="prettyprint programShowDiv col-lg-12 col-md-12 col-sm-12" id="programShowDiv" style="overflow-y: scroll; height:300px;"></pre><!-- </div> -->
         
         <div class="div3 col-lg-12 col-md-12 col-sm-12"><b><u>Output:</u></b></div>
         <div class="programOpDiv col-lg-12 col-md-12 col-sm-12">
            <p class="programOpParagraph" id="outputDiv"></p>
         </div>
         <!-- <div class="solution col-lg-12 col-md-12 col-sm-12">
            <button type="button" class="btn col-lg-4 col-md-4 col-sm-4 solnButtons">Solution1</button>
            <button type="button" class="btn col-lg-4 col-md-4 col-sm-4 solnButtons">Solution2</button>
            <button type="button" class="btn col-lg-4 col-md-4 col-sm-4 solnButtons">Solution3</button>
         </div> -->
         <!-- <div class="div3 col-lg-12 col-md-12 col-sm-12">
            <b>Article Tags:</b> <span class="label label-default">Java</span> <span class="label label-default">Tachnical Scripter</span> <span class="label label-default">Java-Puzzle</span>
         </div> -->
      </div>
   </body>
   <script type="text/javascript">
   var progId="";
   $(document).ready(function() {
	   progId='<c:out value="${userForm.progId}"></c:out>';
		getProgramForView();
	});
   
   function getProgramForView(){
		var dataArray = {};
		dataArray["progId"]=progId;
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "loadPagramForView",
			data : JSON.stringify(dataArray),
			timeout : 100000,
			success : function(data) {
				if(data.status==0){
					$('#programShowDiv').append(data.program1);
					$('#programName').append(data.progName);
					$('#outputDiv').append(data.output1);
					$('#dislikeclicks').append(data.dislikeCount);
					$('#likeclicks').append(data.likeCount);
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
   
   function likeDislikeUpdate(value){
	   var dataArray = {};
	   dataArray["progId"]=progId;
	   
	   console.log(Session.dump());
	   if(value=='like'){
		   if(Session.get('likeList').split(',').includes(progId)){
			   showAlert('You have aready liked this program','error');
			   return false;
		   }else{
			   dataArray["likeCount"]=Number($('#likeclicks').html())+1;
		   }
		  
	   }else if(value=='dislike'){
		   if(Session.get('dislikeList').split(',').includes(progId)){
			   showAlert('You have aready disliked this program','error');
			   return false;
		   }else{
			   dataArray["dislikeCount"]=Number($('#dislikeclicks').html())+1;
		   }
		  
	   }
	   $.ajax({
			type : "POST",
			contentType : "application/json",
			url : "updateLikeDislike",
			data : JSON.stringify(dataArray),
			timeout : 100000,
			success : function(data) {
				if(data.status==0){
					if(value=='like'){
						Session.set("likeList", Session.get('likeList')+','+progId);
					}else{
						Session.set("dislikeList", Session.get('dislikeList')+','+progId);
					}
					reloadPage(progId);
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
   function goBack(){
	   loadPage('0');
   }
   
   function reloadPage(progId){
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
   
   </script>
</html>