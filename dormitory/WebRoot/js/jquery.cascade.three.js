(function($){
$.fn.cascade=function(opt){//opt即option
	var opts=$.extend({//设置默认参数,参数可选择性覆盖
		url:"address.json",
		firstName:"学院",//一级框前面的文字
		secondName:"学历",
		thirdName:"专业",
		fourName:"班级",
		firstText:"请选择学院",//一级框里默认的选项
		secondText:"请选择学历",
		thirdText:"请选择专业",
		fourText:"请选择班级",
		attrName:"name",
		attrValue:"id"
	},opt||{});
	//定义一个变量指向this，方便在闭包里操作
	var target=this;
	//级联中的html中select的初始化,将对象缓存，便于后面遍历
	opts.one=$("<select id='first_select'><option>"+opts.firstText+"</option></select>");//一级框
	opts.two=$("<select id='second_select'><option>"+opts.secondText+"</option></select>");//二级框
	opts.three=$("<select id='third_select'><option>"+opts.thirdText+"</option></select>");//三级框
	opts.four=$("<select id='four_select'><option>"+opts.fourText+"</option></select>");//四级框
	
	//通过ajax加载文件
	$.getJSON(opts.url,function(data){
		$.each(data,function(index,value){//一级遍历添加
			opts.one.append(createNode(this));
		});
		
		opts.one.change(function(){
			//清除二三四级级联框以前的内容
			opts.two.find("option:gt(0)").remove();
			opts.three.find("option:gt(0)").remove();
			opts.four.find("option:gt(0)").remove();
			var str=$(this).val();//保存要选择的元素的值
			var child=findChildren(data,str);//获取二级元素
			$(child).each(function(index, element) {//二级遍历
				opts.two.append(createNode(this));
			});
		});
		opts.two.change(function(){
			//清除三四级级联框以前的内容
			opts.three.find("option:gt(0)").remove();
			opts.four.find("option:gt(0)").remove();
			var parentStr=$("#first_select").val();//一级框的当前值
			var str=$(this).val();//保存要选择的元素的值
			var child=findChildren(data,parentStr);//获取二级元素
			var grandson=findChildren(child,str);//获取三级元素
			$(grandson).each(function(index, element) {//遍历三级框内容
				opts.three.append(createNode(this));
			});
		});
		opts.three.change(function(){
			//清除四级级联框以前的内容
			opts.four.find("option:gt(0)").remove();
			var parentStr=$("#first_select").val();//一级框的当前值
			var str=$(this).val();//保存要选择的元素的值
			var child=findChildren(data,parentStr);//获取二级元素
			var secondStr=$("#second_select").val();//一级框的当前值
			var grandson=findChildren(child,secondStr);//获取三级元素
			var pregrandson=findChildren(grandson,str);//获取四级元素
			$(pregrandson).each(function(index, element) {//遍历四级框内容
				opts.four.append(createNode(this));
			});
		});
		target.append(opts.firstName+": ");//第一个select前的文本
		target.append(opts.one);
		target.append("  "+opts.secondName+":  ");//第二个
		target.append(opts.two);
		target.append("  "+opts.thirdName+":  ");//第三个
		target.append(opts.three);	
		target.append("  "+opts.fourName+":  ");//第四个
		target.append(opts.four);		
	});
	function findChildren(arr,str){	//寻找子元素
		var child;
		$.each(arr,function(index,value){
			if(arr[index].id==str){
				child=arr[index].children;		
			}
		})
		return child;
	}
	function createNode(obj){//创建节点<option>
		return "<option value='"+obj[opts.attrValue]+"'>"+obj[opts.attrName]+"</option>";
	}
}
})(jQuery)