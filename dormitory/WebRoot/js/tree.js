// JavaScript Document

$.getJSON("../areaInfo/findAreaAndBuilding.do",function(data){
	var html="<li class='folder'><a href='#' haschild='true'>基本信息</a></li><ul ><li><a href='#' name='base_info'>宿舍基本信息</a><li><a href='#' name='add'>添加楼栋</a></ul>";
		$.each(data.rows,function(index,item){
			html+="<li class='folder'><a href='#' haschild='true'>"+item.area+" 区</a></li><ul>";
			$.each(item.buildingid,function(index,i){
				html+="<li><a href='#'>"+i+"栋</a></li>";
			});
			html+="</ul>";
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
					$("#base_right").empty().load("s_add_loudong.html");
					}else if($(this).find("a").attr("name")=="base_info"){
					$("#base_right").empty().load("s_sushe_xinxi_chaxun.html");
					}else{
						var tem=$(this).find("a").text();	
						buildingid=tem.substring(0,tem.length-1);
						$("#base_right").empty().load("s_sushe_xinxi.html");
					}
			}
				
			
		});
	});