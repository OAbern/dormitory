// JavaScript Document

$.getJSON("json/get/s_loudong_xinxi.json",function(data){
		var html="";
		$.each(data.rows,function(index,item){
			html+="<li class='folder'><a href='#' haschild='true'>"+item.area+" 区</a></li><ul>";
			$.each(item.buildingid,function(index,i){
				html+="<li><a href='#'>"+i+"栋</a></li>";
			});
			html+="<li><a href='#' name='add'>添加楼栋</a></li></ul>";
		});
		html+="</ul>";
		$("#sushe").append(html);
		$("#sushe").find("li").click(function(){
			
			if($(this).find("a").attr("haschild")=="true"){
				if($(this).next("ul").attr("show")=="true"){
				$(this).next("ul").attr("show","false");
				$(this).next("ul").hide();	/* 显示 show 属性为 true 的子级菜单 */
				$(this).removeClass("open");	/* 添加 show 属性为 true 的子级菜单父节点的 open 样式 */
										
				}else{
					$(this).next("ul").attr("show","true");
					$(this).next("ul").show();	/* 显示 show 属性为 true 的子级菜单 */
				    $(this).addClass("open");	/* 添加 show 属性为 true 的子级菜单父节点的 open 样式 */
				}
			
			}else{
				if($(this).find("a").attr("name")=="add"){
					area=$(this).parent().prev().text()[0];
					$("#base_right").empty().load("super/s_add_loudong.html");
					}else{
						buildingid=$(this).find("a").text()[0];	
						$("#base_right").empty().load("super/s_sushe_xinxi.html");
					}
				}
				
			
		});
	});