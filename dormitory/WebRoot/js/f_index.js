/**
 * 控制right页面的加载
 * @author log
 *
 */
$(function(){ 
	$(".stu li:eq(0)").click(function(){
		$("#base_right").empty().load("f_modify.html");
	});
	$(".stu li:eq(1)").click(function(){
		$("#base_right").empty().load("f_studentmessage.html");
	});
    $(".stuqin li:eq(0)").click(function(){
		$("#base_right").empty().load("f_stuchangehotel.html");
	});
   $(".stuqin li:eq(1)").click(function(){
		$("#base_right").empty().load("f_modifystuchangehotel.html");
	});
   $(".classes li:eq(0)").click(function(){
		$("#base_right").empty();
		$("#base_right").empty().load("f_viewclass.html");
	});
   $(".classes li:eq(1)").click(function(){
		$("#base_right").empty();
		$("#base_right").empty().load("f_addclassl.html");
	});	
	//树形控件点击添加事件，用于指示当前页面
	$(".stu li:eq(0),.stu li:eq(1),.stuqin li:eq(0),.stuqin li:eq(1),.classes li:eq(0),.classes li:eq(1)").hover(function(){
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
	});
});