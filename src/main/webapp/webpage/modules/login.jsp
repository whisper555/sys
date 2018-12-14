<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<html>
<head>
	<meta charset="utf-8">
	<title>毕业设计管理系统登录</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link href="${ctxStatic }/hplus/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctxStatic }/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${ctxStatic }/hplus/css/animate.min.css" rel="stylesheet">
    <link href="${ctxStatic }/hplus/css/style.min.css" rel="stylesheet">
    <link href="${ctxStatic }/hplus/css/login.min.css" rel="stylesheet">
	<link rel="stylesheet" href="${ctxStatic }/login/login.css" media="all" />
	<script>
		function login(){
			var flag= false;
			var id = $("#id").val();
			var pw = $("#pw").val();
			
			var user =$("input[name='user']:checked").val();
			console.log(id+"         " + pw+"         " +user);
			$.ajax({
					async:false,
					type : "get",
					contentType : "application/json;charset=utf-8",
					url : "${ctx }/login",
					data :{"id":id,"pw":pw,"user":user},
					dataType : "json",
					success : function(result) {
						console.log(result);
						if(result==true){
							flag = true;
						}else{
							$("#pw").val("");	
							$("#pw"). attr('placeholder','帐号或者密码错误');	
						}
						
					
					},
					error : function(result) {

					}
				
			});
			return flag;
		}
	</script>
</head>
<body>
	<video class="video-player" preload="auto" autoplay="autoplay" loop="loop" data-height="1080" data-width="1920" height="1080" width="1920">
	    <source src="${ctxStatic }/login/login.mp4" type="video/mp4">
	    <!-- 此视频文件为支付宝所有，在此仅供样式参考，如用到商业用途，请自行更换为其他视频或图片，否则造成的任何问题使用者本人承担，谢谢 -->
	</video>
	<div class="video_mask"></div>
	<div class="login-title">
	 <h2 style="display:block;width:960px;height:60px;font-size:120px;color:#fff;margin:50px auto 10px;">毕业设计管理系统</h2>
	 </div>
	
	
	<div class="login">
		 <div class="col-sm-60">
                
                    <h3 class="no-margins">登录：</h3>
					 <select class="form-control m-b" name="account">
                                        <option>管理科学与工程系</option>
                                        
                    </select>
				<form id="inputForm" action="${ctx }/login/index" method="post">
                    <input type="text" class="form-control" placeholder="用户名" value="" style="margin:15px auto;" name="id" id="id"/>
                    <input type="password" class="form-control" placeholder="密码" value="" style="margin:15px auto;" name="pw" id="pw"/>
                    <button type="submit" class="btn btn-success btn-block" onclick="return login();" style="margin:15px auto;">登录</button>
                    <div style="width:180px;height:20px;margin:auto;">
                    <input type="radio" checked="checked" value="1" name="user"><label>学生</label>
                     <input type="radio"  value="2" name="user"><label>教师</label>
					<input type="radio"  value="3" name="user"><label>管理员</label>
                    </div>   
     			</form>
                
            </div>
	</div>

	
</body>
</html>