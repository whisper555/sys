<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<html>
<head>
	<title>第二志愿</title>
	<script>
		function countlimit(tid){
			console.log(tid);
			//console.log(${sessionScope.Tea_user.id});
			var flag;
			 $.ajax({
	        	   type:"get",
	        	   dataType:"text",
	        	   async:false,//这里使用同步方式发起请求
	        	   contentType:"application/json;charset:utf-8",
	        	   url:"${ctx }/teacher/choosestuactive/countlimit?tid="+tid,
	               success:function(result){
	            	   flag = result;
	            	   //console.log(flag);
	            	   
	               },
	        	   error:{	   
	        	   }
	           })
	           
	           if("true"==flag){
	        	  //showMessage("选择成功！");
	        	  console.log("ok,选择成功");
         		  return true;
         	   }else{
         		  showMessage("当前人数到达上限，不能再继续选择学生！");
         		  console.log("dislike,超出人数上限");
         		  return false;
         		 
         		  
         	   }
		}
	</script>
</head>
<body class="childrenBody">
 			<div class="ibox-title">   
                <a class="btn btn-outline btn-default" href="${ctx }/teacher/choosestu/list?tid=${sessionScope.Tea_user.id }&period=4" >刷新</a>                
             	<a class="btn btn-outline btn-default" href="${ctx }/teacher/mystudent/list?tid=${sessionScope.Tea_user.id }" >我的学生</a>                
             </div>
			 <div class="ibox-content">
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
								<tr>
									<th>学生学号</th>
									<th>学生姓名</th>	
									<th>性别</th>	
									<th>所在班级</th>
									<th>联系方式</th>
									<th>选择论文</th>
									<th>操作</th>
								</tr> 
						    </thead>
						  
						    <c:forEach items="${studentlist}" var="stu">
							<tr class="gradeX">
								
								<td class="center">
									${stu.s_id}
								</td>
								<td class="center">
									${stu.s_name}
								</td>
								<td class="center">
									${fns:getDictLabel(stu.s_sex, 's_sex', '')}
								</td>
								<td class="center">
									${stu.classs.c_name}
								</td>
								<td class="center">
									${stu.s_phone}
								</td>
								<td class="center">
									${stu.s_wish2.p_name}
								</td>
								<td class="center">			
										<a class="btn btn-outline btn-default" href="${ctx }/teacher/choosestu/choose?id=${stu.id}&period=4&tid=${sessionScope.Tea_user.id }&pid=${stu.s_wish2.id}"  onclick="return countlimit('${sessionScope.Tea_user.id }');">选择</a>
								</td>
							</tr>
						</c:forEach>
                        </table>
	</div>
    <script>
        $(document).ready(function(){$(".dataTables-example").dataTable();});
    </script>
</body>
</html>