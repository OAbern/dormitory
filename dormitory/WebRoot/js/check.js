// JavaScript Document
$(document).ready(function(){
	function checkForm() {
		if($("#userName").attr("value") == "") {
			alert("请输入用户名");
			return false;
		} else if($("#userPwd").attr("value") == "") {
			alert("请输入用户密码");
			return false;
		/*} else if($("#check").attr("value")=="" || $("#check").attr("value").length<4) {
			alert("请输入完整的验证码");
			return false;*/
		} else {
			return true;
		}
	}
	   
});
