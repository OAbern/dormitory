	/*luoqin*/
	/*新生分配步奏跳转*/
	$(".right_ul_newfenpei li:first-child").click(function(){
		$("#base_right").empty().load("super/s_newstu_fenpei.html");
	});
	$(".right_ul_newfenpei li:nth-child(2)").click(function(){
		if(sum_newfenpei>0){
		$(".right_contain").empty().load("super/s_qinshi_xuanze.html");
		$(this).find("a").addClass("editornav").parent().siblings().find("a").removeAttr("class");/*给下一步添加样式*/
		}
	});
	$(".right_ul_newfenpei li:nth-child(3)").click(function(){
		if(sum_newstu_kong>0){
		$(".right_contain").empty().load("super/s_fenpei_finish.html");
		$(this).find("a").addClass("editornav").parent().siblings().find("a").removeAttr("class");
		}
	});
	/*寝室调换跳转*/
$(".right_ul_diaohuan li:first-child").click(function(){
		$("#base_right").empty().load("super/s_qinshi_diaohuan.html");
	});
	$(".right_ul_diaohuan li:nth-child(2)").click(function(){
		if(sum_diaohuan>0){
		$(".right_contain").empty().load("super/s_atuo_qsxuanze.html");
		$(this).find("a").addClass("editornav").parent().siblings().find("a").removeAttr("class");
		}
	});
	$(".right_ul_diaohuan li:nth-child(3)").click(function(){
		if(sum_dhstu_kong>0){
		$(".right_contain").empty().load("super/s_fenpei_finish.html");
		$(this).find("a").addClass("editornav").parent().siblings().find("a").removeAttr("class");
		}
	});
	$(".right_ul_diaohuan li:nth-child(4)").click(function(){
		$(".right_contain").empty().load("super/s_diaohuan_yijian.html");
		$(this).find("a").addClass("editornav").parent().siblings().find("a").removeAttr("class");
	});
	/*超级管理员添加学生信息模块跳转*/
	$(".right_ul_tianjia li:first-child").click(function(){//添加学生
		$("#base_right").empty().load("super/s_tianjia_xuesheng_xinxi.html");
		$(this).find("a").addClass("editornav").parent().siblings().find("a").removeAttr("class");
	});
	$(".right_ul_tianjia li:nth-child(2)").click(function(){//批量添加学生
		$(".right_contain").empty().load("super/s_piliang_daoru.html");
		$(this).find("a").addClass("editornav").parent().siblings().find("a").removeAttr("class");
	});
	/*校外住宿管理模块跳转*/
	$(".right_ul_xiaowai li:first-child").click(function(){//校外住宿管理跳转
		$("#base_right").empty().load("super/s_xiaowai_zhusu_guanli.html");
		$(this).find("a").addClass("editornav").parent().siblings().find("a").removeAttr("class");
	});
	$(".right_ul_xiaowai li:nth-child(2)").click(function(){//添加校外住宿管理跳转
		$(".right_contain").empty().load("super/s_tianjia_xiaowai.html");
		$(this).find("a").addClass("editornav").parent().siblings().find("a").removeAttr("class");
	});