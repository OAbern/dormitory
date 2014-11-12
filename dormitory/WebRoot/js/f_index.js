// JavaScript Document
$(function(){ 
    var status=0;
	$(".stu li:first-child").click(function(){
		$("#base_right").empty().load("fu/f_studentmessage.html");
	});
   $(".stuqin li:first-child").click(function(){
		$("#base_right").empty().load("fu/f_stuchangehotel.html");
	});
	$(".wait li:first-child").click(function(){
		$("#base_right").empty();
		$.messager.alert("提示","此部分功能还在开发","info");
	});	
	$(".stu li:first-child,.stuqin li:first-child").hover(function(){
		var status=0;
		$(this).click(function(){
			if(status==0){
				$("li").removeClass("hover");
			    $(this).addClass("hover");
			    status=1;
				}
	    	else if(status==1){
			  $(this).removeClass("hover");
			  status=0;
			}
		});
	})
})