// JavaScript Document
var GLOBAL={};
GLOBAL.grade=[];
GLOBAL.academy={};
$.post('academyInfo/getAll.do',function(data){
	GLOBAL.academy=data;
});
(function creatGrade(){
	var date=new Date();
	var year=date.getFullYear();
	GLOBAL.grade=[];
	for(var i=4;i>=0;i--){
		GLOBAL.grade.push(year-i);
	}	
})();
 //展示学院选项
function showAcademy(){
	$('select[name="academy.id"]').empty();
	$('select[name="academy.id"]').append("<option value='请选择'>请选择</option><option value=''>全部</option>");
    $.each(GLOBAL.academy,function(i,v){
    	$('select[name="academy.id"]').append('<option value="'+v.id+'">'+v.name+'</option>');
    });  
}
//展示年级选项
function showGrade(){
	$('select[name="grade"]').empty();
	$('select[name="grade"]').append("<option value='请选择'>请选择</option><option value=''>全部</option>");
	$.each(GLOBAL.grade,function(i,v){
	    $('select[name="grade"]').append('<option value="'+v+'">'+v+'</option>');
	});
}
$(function(){
	$("#studentInfo li:eq(0)").click(function(event) {
		$("#base_right").empty().load("super/s_tianjia_xuesheng_xinxi.html");
	});
	$("#studentInfo li:eq(1)").click(function(event) {
		$("#base_right").empty().load("super/s_chaxun_xuesheng_xinxi.html");
	});
	$("#studentInfo li:eq(2)").click(function(event) {
		$("#base_right").empty().load("super/s_xiugai_xuesheng_xinxi.html");
	});
	$("#studentInfo li:eq(3)").click(function(event) {
		$("#base_right").empty().load("super/s_xueyuan_xuesheng_fenbu.html");
	});
	$("#managerInfo li:eq(0)").click(function(event) {
		$("#base_right").empty().load("super/s_chaxun_xinxi.html");
	});
	$("#managerInfo li:eq(1)").click(function(event) {
		$("#base_right").empty().load("super/s_personInfo_modify.html");
	});
	//-----------------------------tdg
	//---------------------------luoqin
	//左边宿舍分配与调整管理模块跳转
	$(".fenpyutiaozh li:first-child").click(function(){
		$("#base_right").empty().load("super/s_newstu_fenpei.html");
	});
	$(".fenpyutiaozh li:nth-child(2)").click(function(){
		$("#base_right").empty().load("super/s_qinshi_diaohuan.html");
	});
	$(".fenpyutiaozh li:nth-child(3)").click(function(){
		$("#base_right").empty().load("super/s_xiaowai_zhusu_guanli.html");
	});
	$(".fenpyutiaozh li:nth-child(4)").click(function(){
		$("#base_right").empty().load("super/s_tuisu_guanli.html");
	});
	$(".fenpyutiaozh li:nth-child(5)").click(function(){
		$("#base_right").empty().load("super/s_sushe_xinxi_chaxun.html");
	});
	$("#studentInfo li:eq(0),#studentInfo li:eq(1),#studentInfo li:eq(2),#studentInfo li:eq(3),#managerInfo li:eq(0),.fenpyutiaozh li:first-child,.fenpyutiaozh li:nth-child(2),.fenpyutiaozh li:nth-child(3),.fenpyutiaozh li:nth-child(4),.fenpyutiaozh li:nth-child(5),.fenpyutiaozh li:nth-child(6)").hover(function(){
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
	$("#sushe").click(function(){
		$("li").removeClass("hover");	  
	})
});