// JavaScript Document
var GLOBAL={};
GLOBAL.grade=[];
//GLOBAL.academy={};
//生成年级数组
(function creatGrade(){
	var date=new Date();
	var year=date.getFullYear();
	GLOBAL.grade=[];
	for(var i=4;i>=0;i--){
		GLOBAL.grade.push(year-i);
	}	
})();
$.get('academyInfo/getAll.do',function(data){
	GLOBAL.academy=data;
});
//展示学院选项
GLOBAL.showAcademy=function(){
	if(!GLOBAL.academy){
		$.messager.alert("提示","加载学院数据失败,请刷新重试","error");
	}else{
		$.each(GLOBAL.academy,function(i,v){
			$('select[name="academy.id"]').append('<option value="'+v.id+'">'+v.name+'</option>');
		});
	}
}
//展示年级选项
GLOBAL.showGrade=function(){
	$.each(GLOBAL.grade,function(i,v){
		$('select[name="grade"]').append('<option value="'+v+'">'+v+'</option>');
	})		
}
//用于判断筛选条件是否选择
GLOBAL.notSelected=function(){
	if(GLOBAL.getAcademy()=='-1'||GLOBAL.getGrade()=='-1'||GLOBAL.getSex()=='-1'||GLOBAL.getBuilding()=='-1'||GLOBAL.getEducation()=='-1'||GLOBAL.getFloor()=='-1'||GLOBAL.getRoom()=='-1'){
		$.messager.alert("提示","请完善选择框！","warning");
		return false;
	}else{
		return true;
	}
};
//获取学院选择框的value
GLOBAL.getAcademy=function(){
	return $('select[name="academy.id"]').val();
};
//获取年级选择框的value
GLOBAL.getGrade=function(){
	return $('select[name="grade"]').val();
};
//获取性别选择框的value
GLOBAL.getSex=function(){
	return $('select[name="sex"]').val();
};
//获取学历选择框的value
GLOBAL.getEducation=function(){
	return $('select[name="education"]').val();
};
//获取楼栋选择框的value
GLOBAL.getBuilding=function(){
	return $('select[name="building"]').val();
};
//获取楼层选择框的value
GLOBAL.getFloor=function(){
	return $('select[name="floor"]').val();
};
//获取寝室号选择框的value
GLOBAL.getRoom=function(){
	return $('select[name="room"]').val();
};
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