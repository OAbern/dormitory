// JavaScript Document
/*
Author：baozi
Date：2014.9.20
*/
$(function(){

	$('#search').click(function(){
		$('#form1').form({    
		url:'#',    
		onSubmit: function(){    
			// do some check    
			// return false to prevent submit;    
		},    
		success:function(data){    
			//alert(data)    
		}    
	});    
	  // submit the form    
	  $('#form1').submit(); 
	  });
 
});