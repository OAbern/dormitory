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
$.get('../academyInfo/getAll.do',function(data){
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
	if(GLOBAL.getAcademy()=='-1'||GLOBAL.getGrade()=='-1'||GLOBAL.getSex()=='-1'||GLOBAL.getBuilding()=='-1'||GLOBAL.getEducation()=='-1'||GLOBAL.getFloor()=='-1'||GLOBAL.getRoom()=='-1'||GLOBAL.getArea()=='-1'){
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
//获取片区选择框的value
GLOBAL.getArea=function(){
	return $('select[name="area"]').val();
}
//获得请求的url和参数
function getPostParam(action){
	if(action=="添加新寝室"){
		url="../roomInfo/addRoom.do";//添加新寝室的地址
		param.buildingId=buildingid;
		param.floorNum=floorid;
		param.cata=$('.cata').val();
		param.fee=$('.fee').val();
		info="添加";
		}
	if(action=="修改楼层"){
		url="../roomInfo/updateFloor.do";//修改某层楼的宿舍信息
		param.buildingNum=buildingid;
		param.floorNum=floorid;
		param.cata=$('.cata').val();
		param.fee=$('.fee').val();
		info="修改楼层";
		}
	if(action=="修改楼栋"){
		url="../areaInfo/updateBuilding.do";//修改楼栋的宿舍信息
		param.buildingId=buildingid;
		param.sex=$('select').val();
		param.area=$('.area').val();
		param.cata=$('.building_cata').val();
		param.fee=$('.building_fee').val();
		info="修改楼栋";
		}
	if(action=="修改宿舍"){
		url="../roomInfo/updateRoom.do";//修改单个寝室的宿舍信息
		param.roomNum=susheid;
		param.cata=$('.sushe_cata').val();
		param.fee=$('.sushe_fee').val();
		info="修改宿舍";
		}
}
//清空表单和传递参数
function clear(){
	$('input[type="text"]').val('');
	$('.area').html('');
	$('.check').html('');
	url="";
	param={};
	info="";
	}
//展示dialog--zhangying
function showDailog(dgl,form){
	clear();
	$(form).show();
	$(dgl).dialog({   
		title: action,   
	    width: 400,   
	    height: 300,   
	    closed: false,
	   /*  onClose : function() {
            $(dgl).dialog('close');
        }, */
        cache: false,   
	    modal: true,
	    buttons:[{  
        text:'提交',   
        handler:function(){ 
        	if(ok_c && ok_f){
        		getPostParam(action);
        		$.post(url,param,function(data){
        			if(data.status==1){
        				$.messager.alert("提示",info+"成功","info",function(){
        					//$(form).hide();
        					$(dgl).dialog("close");	
        					if(action=="修改宿舍"){
        						$('#student').datagrid({ url:"../roomInfo/findAllPersonInRoom.do",queryParams:{roomNum:susheid},method:"post"});
        					}else if(action=="修改楼栋"){
        						addTree();
        						$('#dormitory').datagrid({ url:"../roomInfo/findAllRoom.do",queryParams:{buildingNum:buildingid},method:"post"});
        					}
        					else{
        						$('#dormitory').datagrid({ url:"../roomInfo/findAllRoom.do",queryParams:{buildingNum:buildingid},method:"post"});
        					}
        				/*	clear();*/
        				}); 
        			}else if(data.status==0){
        				if(action=="添加寝室"){
        					$.messager.alert("提示",info+"失败","info"); $("#dgl").dialog("close");	
        				}else{
        					$.messager.alert("提示",+"有人入住，"+info+"失败","info"); $("#dgl").dialog("close");	
        				}
        				/*clear();*/
        			}else if(data.status==2){
    					$.messager.alert("提示","由于改楼栋有学生入住，只修改了该收费的收费标准和所属片区。","info",function(){
    						$(dgl).dialog("close");	
    					}); 
        			}else{
        				$.messager.alert("提示","发生错误","info"); 
        				$(dgl).dialog("close");
        			}
        		});
        	}else{
        		$.messager.alert("提示","请完善表单","error"); 
        		
        	}
        }
	    }]
	
	}); 	
}

$(function(){
	$("#studentInfo li:eq(0)").click(function(event) {
		$("#base_right").empty().load("s_tianjia_xuesheng_xinxi.html");
	});
	$("#studentInfo li:eq(1)").click(function(event) {
		$("#base_right").empty().load("s_xiugai_xuesheng_xinxi.html");
	});
	$("#studentInfo li:eq(2)").click(function(event) {
		$("#base_right").empty().load("s_tianjia_guanliyuan.html");
	});
	$("#studentInfo li:eq(3)").click(function(event) {
		$("#base_right").empty().load("s_chaxun_xinxi.html");
	});
	$("#studentInfo li:eq(4)").click(function(event) {
		$("#base_right").empty().load("s_personInfo_modify.html");
	});
	//-----------------------------tdg
	//---------------------------luoqin
	//-------------------zhangying
	//左边统计跳转
	$("#tongji li:first-child").click(function(){
		$("#base_right").empty().load("s_xueyuan_xuesheng_fenbu.html");
	});
	$("#tongji li:nth-child(2)").click(function(){
		$("#base_right").empty().load("s_kongqinshi_tongji.html");
	});
	$("#tongji li:nth-child(3)").click(function(){
		$("#base_right").empty().load("s_chuangwei_tongji.html");
	});
	/*$("#tongji li:nth-child(4)").click(function(){
		$("#base_right").empty().load("s_qinshi_leixing_tongji.html");
	});*/
	//左边宿舍分配与调整管理模块跳转
	$(".fenpyutiaozh li:first-child").click(function(){
		$("#base_right").empty().load("s_newstu_fenpei.html");
	});
	$(".fenpyutiaozh li:nth-child(2)").click(function(){
		$("#base_right").empty().load("s_qinshi_diaohuan.html");
	});
	$(".fenpyutiaozh li:nth-child(3)").click(function(){
		$("#base_right").empty().load("s_xiaowai_zhusu_guanli.html");
	});
	$(".fenpyutiaozh li:nth-child(4)").click(function(){
		$("#base_right").empty().load("s_tuisu_guanli.html");
	});
	$(".fenpyutiaozh li:nth-child(5)").click(function(){
		$("#base_right").empty().load("s_sushe_xinxi_chaxun.html");
	});
	//当前点击li增加底纹样式
	$("#studentInfo li:eq(0),#studentInfo li:eq(1),#studentInfo li:eq(2),#studentInfo li:eq(3),#managerInfo li:eq(0),#managerInfo li:eq(1),#tongji li:first-child,#tongji li:nth-child(2),#tongji li:nth-child(3),#tongji li:nth-child(4),.fenpyutiaozh li:first-child,.fenpyutiaozh li:nth-child(2),.fenpyutiaozh li:nth-child(3),.fenpyutiaozh li:nth-child(4),.fenpyutiaozh li:nth-child(5),.fenpyutiaozh li:nth-child(6)").hover(function(){
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