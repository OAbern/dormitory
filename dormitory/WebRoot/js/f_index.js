// JavaScript Document
$(function(){ 
    var status=0;
	$(".stu li:eq(0)").click(function(){
		$("#base_right").empty().load("fu/f_modify.html");
	});
	$(".stu li:eq(1)").click(function(){
		$("#base_right").empty().load("fu/f_studentmessage.html");
	});
    $(".stuqin li:eq(0)").click(function(){
		$("#base_right").empty().load("fu/f_stuchangehotel.html");
	});
   $(".stuqin li:eq(1)").click(function(){
		$("#base_right").empty().load("fu/f_modifystuchangehotel.html");
	});
	$(".wait li:first-child").click(function(){
		$("#base_right").empty();
		$.messager.alert("提示","此部分功能还在开发","info");
	});	
	$(".stu li:eq(0),.stu li:eq(1),.stuqin li:eq(0),.stuqin li:eq(1)").hover(function(){
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