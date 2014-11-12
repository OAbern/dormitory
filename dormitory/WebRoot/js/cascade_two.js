(function($){
$.fn.cascade_two=function(opt){//opt即option
	var opts=$.extend({//设置默认参数,参数可选择性覆盖
		url:"address.json",
		firstName:"所属片区",//一级框前面的文字
		secondName:"所属楼栋",
		firstText:"请选择片区",//一级框里默认的选项
		secondText:"请选择楼栋",
		attrName:"name",
		attrValue:"id"
	},opt||{});
	//定义一个变量指向this，方便在闭包里操作
	var target=this;
	//级联中的html中select的初始化,将对象缓存，便于后面遍历
	opts.one=$("<select name='area' id='first_select'><option>"+opts.firstText+"</option></select>");//一级框
	opts.two=$("<select name='building' id='second_select'><option>"+opts.secondText+"</option></select>");//二级框
	
	//通过ajax加载文件
	$.getJSON(opts.url,function(data){
		$.each(data,function(index,value){//一级遍历添加
			opts.one.append(createNode(this));
		});
		
		opts.one.change(function(){
			//清除二级级联框以前的内容
			opts.two.find("option:gt(0)").remove();
			var str=$(this).val();//保存要选择的元素的值
			var child=findChildren(data,str);//获取二级元素
			$(child).each(function(index, element) {//二级遍历
				opts.two.append(createNode(this));
			});
		});
		target.append(opts.firstName+": ");//第一个select前的文本
		target.append(opts.one);
		target.append("  "+opts.secondName+":  ");//第二个
		target.append(opts.two);	
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