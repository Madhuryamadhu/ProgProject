<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	 <title>JAVA PROGRAMS</title>
	 <meta charset="ISO-8859-1">
	 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/allStyle.css" />
	 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css" />
  	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
 	 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
 	 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
</head>
<body class="container">
<img class="spinner hide" style="position:fixed;height:40px;width: 40px;min-width: 20px;margin-left: -10px;min-height: 20px;margin-bottom: -10px;left: 50%;top: 50%;z-index: 99999;" src="${pageContext.request.contextPath}/resources/images/backgrounds/spinner.svg" />
<div id="indexContainer">
<!-- HEADER DIVISION -->
 <div class="col-lg-12 col-md-12 col-sm-12">
	 <div class="col-lg-12 col-md-12 col-sm-12 headerFooterDiv">
			<div class="col-lg-4 col-md-5 col-sm-5">
				<img src="${pageContext.request.contextPath}/resources/images/backgrounds/programslogo.jpg" class="logoImage" alt="Paris" width="70" height="70" />
			</div>
			<div class="col-lg-6 col-md-7 col-sm-7">
				<h1><a>all<strong>PROGRAMS</strong></a></h1>
				<h2>For all your program needs.</h2>
			</div>
			<div class="col-lg-2 col-md-7 col-sm-7">
				<button type="button" class="suggestProgram suggest" id="suggestProgram">Suggest Programs</button>
			</div>
	  </div>
 </div>
 
 <!--CONTENT DIVISION -->
 <div class="col-lg-12 col-md-12 col-sm-12"  style="display: flex; height: 100%;">
	
	<!-- USER PAGE CONTENT DIVISION -->
	<div id="userPageContent" class="col-lg-9 col-md-12 col-sm-12" style="padding-left: 0px !important;" ></div>
	<div class="messageDiv"><p id="alertText"></p></div>
	
	<!-- ADMIN PAGE CONTENT DIVISION -->
	<div id="adminPageContent" class="col-lg-12 col-md-12 col-sm-12" style="padding-left: 0px !important;padding-right: 0px !important;"></div>
	
	
	<!-- SIDE BAR DIVISION -->
	<div class="sidebarDiv col-lg-3 col-md-12 col-sm-12" id="sidebarDiv">
		<div  class="col-lg-12 col-md-12 col-sm-12" style="margin-bottom: 48px;">
			<center> <h1 id="headerSideBar" class="h1BlackBold">TYPES</h1></center>
			<table class="userTable" id="typesTable"></table>
		</div>
		<div class="col-lg-12 col-md-12 col-sm-12">
			<center> <h1 id="headerSideBar" >Joing us on whatsapp</h1></center>
			<div class="col-lg-12 col-md-12 col-sm-12">
				<img class="col-lg-12 col-md-12 col-sm-12" src="${pageContext.request.contextPath}/resources/images/backgrounds/WhatsappJoinImage.jpg" style="margin-bottom: 50px;" />
			</div>
		</div>
	</div>	
	<div style="flex-grow : 1;"></div>
	
  </div>
  
  <!-- FOOTER DIVISION -->
  <div class="col-lg-12 col-md-12">
     <div class="col-lg-12 col-md-12 col-sm-12 headerFooterDiv footerDiv">
				<div class="col-lg-6 col-md-6 col-sm-6">
                    <h3>About Us</h3>
                    <p>We are a young team of developers who are willing to develop innovative websites for fun. To get your website done please contact us :)</p>
               </div>

               <div class="col-lg-6 col-md-6 col-sm-6" style="text-align: right;">
                    <h3>Talk to us</h3>
                    <p><i class="fa fa-globe"></i> 512 Delicious Street, San Francisco, CA 10880</p>
                    <p><i class="fa fa-phone"></i> 010-020-0990     |     <i class="fa fa-save"></i> madhuryanmadhu@gmail.com</p>
                    <p></p>
               </div>
				<div class="col-lg-12 col-md-12 col-sm-12">
                    <ul class="social-icon">
                         <li><a href="#" class="fa fa-facebook"></a></li>
                         <li><a href="#" class="fa fa-twitter"></a></li>
                         <li><a href="#" class="fa fa-google-plus"></a></li>
                         <li><a href="#" class="fa fa-dribbble"></a></li>
                         <li><a href="#" class="fa fa-linkedin"></a></li>
                    </ul>
               </div>
			    <div class="footer-copyright">
                    <p style="text-align: center;">Copyright &copy; 2019 Madhurya Devs</p>
                </div>
	 </div>
  </div>
 </div>
   <div class="dialog" id="myform">
 
	
		<i class="fa fa-close" id="btnOK"></i>
	
	<div class="reportdiv">
		 <div class="form-group">
				 <label for="name">Name:</label><input type="text" class="form-control" placeholder="Please enter your Name" />
		</div>
		 <div class="form-group">
				 <label for="email">Email:</label><input type="email" class="form-control" placeholder="Please enter your Mail">
		</div>
		
		<div class="form-group">
				 <label for="suggest">Suggest Programs:</label>
				 <textarea rows="4" cols="50" placeholder="Please suggest the programs" class="form-control"></textarea>
		</div>

		<div align="center">
			<input type="submit" value="Submit" id="submit">
		</div>
	</div>
	
  </div>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/json-serialization.js" ></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/session.js" ></script>
</body>
<script type="text/javascript">

//GLOBAL VARIABLES
var typeMap;

$(document).ready(function() {
	if(!(isValid(Session.get('likeList'))&&isValid(Session.get('dislikeList')))){
		Session.set("likeList", "dummy");
		Session.set("dislikeList", "dummy");
	}
	var element = document.getElementById("indexContainer");
	$("#myform").hide();
    $(".suggestProgram").click(function() {
        $("#myform").show(1000);
		 
        element.classList.add("blur");
    });
    $("#btnOK").click(function() {
       
        $("#myform").hide(600);
	element.classList.remove("blur");
    });
	
	
	loadPage('0');
	getTypeDetails();
	setTimeout(function(){buildListTableContent('typesTable',typeMap,'loadBasedOnType');  }, 100);
});


$(document).ajaxStart(function () {
  $('.spinner').removeClass('hide');
  $('#indexContainer').addClass('noPointerEvent');
}).ajaxStop(function () {
	$('.spinner').addClass('hide');
	$('#indexContainer').removeClass('noPointerEvent');
});

var map = {18: false, 77: false, 80: false};
$(document).keydown(function(e) {
    if (e.keyCode in map) {
        map[e.keyCode] = true;
        if (map[18] && map[77]&& map[80]) {
        	loadPage('1');
        }
    }
}).keyup(function(e) {
    if (e.keyCode in map) {
        map[e.keyCode] = false;
    }
});

function loadPage(pageId) {
	
	
	var dataArray = {}
	dataArray["pageId"]=pageId;    //0- userPages   ||   1-adminPages
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "loadPages",
		data : JSON.stringify(dataArray),
		timeout : 100000,
		success : function(data) {
			if(pageId==0){
				 $('#userPageContent').show();
				 $('#sidebarDiv').show();
				 $('#userPageContent').html(data);
				 $('#adminPageContent').hide();
			}else if(pageId==1){
				 $('#userPageContent').hide();
				 $('#sidebarDiv').hide();
				 $('#adminPageContent').show();
				 $('#adminPageContent').html(data);
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

function getTypeDetails() {
		var dataArray = {}
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "getTypes",
			data : JSON.stringify(dataArray),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				if (data.status == 0) {
					typeMap=data.typeMap;
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



function loadBasedOnType(typeId){
	loadPrograms(false,'0','10',typeId);
}








//COMMON FUNCTIONS

function isValid(value){
	if(value==null||value==''||value=='undefined'||value=='NaN'||value=='null')
		return false;
	
	return true;
}
function buildListTableContent(id,map,onlickFunction){
	$('#'+id).empty();
	var listTableContent="";
	for (var key in map) {
	    if (map.hasOwnProperty(key)) {  
	    	listTableContent +='<tr><th id="th_'+id+'_'+key+'"> <a class="listTableContent" onclick="'+onlickFunction+'(\''+key+'\')">'+map[key]+'</a></th></tr>';
	    }
	}
	$('#'+id).append(listTableContent);
}


function loadDropDown(id,map){
	$('#'+id).empty();
    var dropdownContent="<option value>Select</option>";
	for (var key in map) {
	    if (map.hasOwnProperty(key)) {  
	    	dropdownContent +='<option value="'+key+'">'+map[key]+'</option>';
	    }
	}
	$('#'+id).append(dropdownContent);
}

function copyToClipboard(containerid){
	 var range = document.createRange();
     range.selectNode(document.getElementById(containerid));
     window.getSelection().removeAllRanges(); // clear current selection
     window.getSelection().addRange(range); // to select text
     document.execCommand("copy");
     window.getSelection().removeAllRanges();// to deselect
}

var activePageNext=1;
var activePagePrev=0;
var totalPagesGlobal=0;
function buildPagination(totalPages,onclickFunction,divId){
	$('#'+divId).empty(); 
	totalPagesGlobal=Number(totalPages);
	console.log(totalPages+" "+totalPagesGlobal);
 	var paginationContent='';
 	paginationContent+='<ul class="pagination  pull-right" style="margin-top: 0px !important;">';
	 paginationContent+='<li class="page-item disabled" id="'+divId+'_Prev"><a class="page-link" onclick="'+onclickFunction+'(true,\'prev\',\'10\');">Previous</a></li>';
	
	 for(var i=0;i<totalPages;i++){
		 if(i==0){
			 paginationContent+='<li class="page-item"><a class="page-link activePagination" id="'+divId+'_p'+i+'" onclick="'+onclickFunction+'(true,\''+(i*10)+'\',\'10\');addActive(\''+divId+'\',\''+i+'\');">'+(i+1)+'</a></li>';
		 }else{
			 paginationContent+='<li class="page-item"><a class="page-link" id="'+divId+'_p'+i+'" onclick="'+onclickFunction+'(true,\''+(i*10)+'\',\'10\');addActive(\''+divId+'\',\''+i+'\');">'+(i+1)+'</a></li>';
		 }
		 
	 }
	 paginationContent+='<li class="page-item" id="'+divId+'_Next"><a class="page-link" onclick="'+onclickFunction+'(true,\'next\',\'10\');">Next</a></li>';
	 $('#'+divId).html(paginationContent);
	 
	}
	
	function addActive(divid,i){
		$('.page-item').removeClass('disabled');
		
		var divTemp=divid+'_p'+i;
		activePageNext=Number(i)+1;
		activePagePrev=Number(i)-1;
		console.log(activePageNext+" "+activePagePrev+" "+totalPagesGlobal);
		if(activePageNext==1){
			$('#'+divid+'_Prev').addClass('disabled');
		}
		if(activePageNext==totalPagesGlobal){
			$('#'+divid+'_Next').addClass('disabled');
		}
		$('.page-link').removeClass('activePagination');
		$('#'+divTemp).addClass('activePagination');
	}
	
	
	
	//Table Build Section
	function buildDataTable(id,list,columnSizes){
		$('#'+id).empty();
    	var pageDataSize=(list.length)-1;
    	var dataContent='';
    	for(var i=0;i<list.length;i++){
    		var columnSizeArr=columnSizes.split(',');
    		var dataList=list[i];
    		dataContent +='<tr>';
			for(var j=0;j<dataList.length;j++){
				if(i==0){
					dataContent +='<th>'+dataList[j]+'</th>';
				}else{
					if(dataList[j].includes('BUTTON')){
						var tempArr=dataList[j].split('^');
						dataContent +='<td class="tableButton_td">'+getTableButton(tempArr[1],tempArr[2])+'</td>';
					}else{
						if(columnSizeArr[j]=='XS'){
							dataContent +='<td class="tableExtraSmall_td">'+dataList[j]+'</td>';
						}else if(columnSizeArr[j]=='S'){
							dataContent +='<td class="tableSmall_td">'+dataList[j]+'</td>';
						}else if(columnSizeArr[j]=='L'){
							dataContent +='<td title="'+dataList[j]+'">'+dataList[j]+'</td>';
						}else{
							dataContent +='<td>'+dataList[j]+'</td>';
						}
						
					}
				}
			}
			dataContent +='</tr>';
    	}
    	$('#'+id).append(dataContent);
    }
    
    function getTableButton(buttonType,onclickFunction){
    	//1-modify 2-delete 3-hide
    	var button="";
    	switch(buttonType) {
    	  case '1':
    	    button='<button type="submit" class="btn-default tableButton_btn" onclick="'+onclickFunction+';"><i class="fa fa-edit" style="color: black; font-size: 20px !important;"></i></button>';
    	    break;
    	  case '2':
    		  button='<button type="submit" class="btn-default tableButton_btn" onclick="'+onclickFunction+';" ><i class="fa fa-trash" style="color: black; font-size: 20px !important;"></i></button>';
    	    break;
    	  case '3':
    		  button='<button type="submit" class="btn-default tableButton_btn" onclick="'+onclickFunction+';" ><i class="fa fa-eye-slash" style="color: black; font-size: 20px !important;"></i></button>';
      	    break;
    	  case '4':
       		  button='<button type="submit" class="btn-default tableButton_btn" onclick="'+onclickFunction+';" ><i class="fa fa-ban" style="color: black; font-size: 20px !important;"></i></button>';
       	    break;
    	  default:
    		  button='<button type="submit" class="btn-default tableButton_btn" >NA</button>';
    	}
    	return button;
    	
    }
	
    function loadNewPage(pagePath,parameters,Id){
    	$.ajax({
   			type : "POST",
   			url : "goToPage",
   		    data : {
				"pagePath" : pagePath,
				"parameters" : parameters
			},
   			success : function(data) {
   				 $('#'+Id).empty();
   			     $('#'+Id).html(data);
   			},
   			error : function(e) {
   				console.log("ERROR: ", e);
   			},
   			done : function(e) {
   				console.log("getTypeDetails DONE");
   			}
   		});
    }
	
	function showAlert(text,result){
		$('#alertText').append(text);
		if(result=='error')
			$('#alertText').css('color','#fa4040');
		else
			$('#alertText').css('color','#42d542');
		$(".messageDiv").addClass("show");
		setTimeout(function(){  
			$(".messageDiv").removeClass("show"); 
			$('#alertText').empty();
		}, 3000);
		
	}
</script>
</html>
