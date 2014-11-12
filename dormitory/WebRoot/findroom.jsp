<%@ page language="java" import="java.util.*,com.cqupt.dormitory.model.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>"></base>
    
    <title>宿舍管理系统</title>

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h1>Spring-Mybatis框架整合测试</h1>
    <form action="test.do" method="post">
   		请输入要查找的房间号：<input type="text" name="roomId"/><br />
   		<button type="submit">查询</button>
    </form>
    <br />
    <%
    	String info = (String) request.getAttribute("notFound");
    	if(info == null) {
    		Room room = (Room) request.getAttribute("room");
        	if(room != null) {
        		out.println("房间自增id："+room.getId());
        		out.println("房间id："+room.getRoomId());
        		out.println("房间费用："+room.getCost());
        		out.println("房间总床数："+room.getTotalBed());	
        	}
    	}else {
    		out.println(info);
    	}
    	
    %>
    
  </body>
</html>
