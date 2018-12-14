
function confirmx(href){
		top.layer.confirm('确定要删除吗?', { title:'提示',skin: 'layui-layer-lan',anim: 4 },
			function(index){
			location = href;
			top.layer.close(index);
				return true;
			});
			return false;
		
	
	}
function confirmchoose(href){
	top.layer.confirm('确定要选择吗?', { title:'提示',skin: 'layui-layer-lan',anim: 4 },
		function(index){
		location = href;
		top.layer.close(index);
			return true;
		});
		return false;
	
}
function confirmMessage(href,message){
	top.layer.confirm(message, { title:'提示',skin: 'layui-layer-lan',anim: 4 },
		function(index){
		location = href;
		top.layer.close(index);
			return true;
		});
		return false;
	

}
function showMessage(title){
	
	  layer.alert(title, {
	    skin: 'layui-layer-lan'
	    ,closeBtn: 0
	    ,anim: 4 //动画类型
	  });

}
function openDialog(title,url,width,height,target){  	
	top.layer.open({
			  type: 2
			  ,title: title //不显示标题栏
			  ,closeBtn: false
			  ,area: [width,height]
			  ,shade: .5
			  ,id: 'view' //设定一个id，防止重复弹出
			  ,resize: false		//重新定义窗口大小
			  ,maxmin: false		//最大化 最小化
			  ,btn: ['确定', '关闭']
			  ,content: url 
			  ,moveType: 1 //拖拽模式，0或者1
			  ,yes: function(index,layero){
				     var body = top.layer.getChildFrame('body', index);
				 	 var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
				 	
				
			         var inputForm = body.find('#inputForm');
			         var top_iframe;
			         if(target){
			        	 top_iframe = target;//如果指定了iframe，则在改frame中跳转
			         }else{
			        	 //top_iframe = top.$(".J_iframe:visible").attr("name");//获取当前active的tab的iframe 
			        	//console.log(top_iframe);
			        	 top_iframe=  top.getActiveTab().attr("name");
			        	// console.log(top_iframe);
			         }
			         inputForm.attr("target",top_iframe);//表单提交成功后，从服务器返回的url在当前tab中展示
			         if(iframeWin.contentWindow.doSubmit() ){
			        	  //window.location.reload();
			        	  setTimeout(function(){top.layer.close(index)}, 1);//延时0.1秒，对应360 7.1版本bug			        	  
			         }    
	          }  
				,btn2:function () {
					setTimeout(function(index){top.layer.close(index)}, 10000);//延时0.1秒，对应360 7.1版本bug
			    }
			    
			  ,cancel:function(layero){	   
			    }	  
			});
    }
    