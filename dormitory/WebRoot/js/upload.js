// JavaScript Document
$( "#fileImg").uploadify({ 
              'uploader' : '${ctx}/handlePicture.action?JSESSIONID=${pageContext.session.id}' , 
              'swf' : '${ctx}/js/uploadify/uploadify.swf' , 
              'cancelImg' :'${ctx}/js/uploadify/cancel.png' , 
              'fileObjName' :'fileImg' , 
              'queueID' : 'fileQueue' ,
			  'folder': 'upload', 
              'auto' : false , 
              'buttonText' :'选择' , 
              'fileSizeLimit' : '50MB' , 
              'multi' :false , 
              'height' :20, 
              'wmode' :'transparent' , 
              //上传完成时事件  
              'onUploadSuccess' : function (file,data,response) {                   
                  data=eval( "(" +data+")" ); 
                   if (data.status=='1' ) 
                  { 
                        $.messager.alert( '提示:' ,'上传成功' ,'info' ); 
                        $( "#imgView" ).attr("src" ,ctx+ "/"+data.object); 
                        $( "#picturew" ).val(data.object.substring(data.object.lastIndexOf( "/")+1)); 
                  } 
                   else 
                  { 
                        $.messager.alert( '提示:' ,'上传失败' ,'info' ); 
                  } 
              } 
          }); 

