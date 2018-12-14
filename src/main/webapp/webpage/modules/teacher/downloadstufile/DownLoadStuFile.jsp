<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<html>
<head>
  <link rel="stylesheet" href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
	<title>毕设下载</title>
</head>
<body class="childrenBody">
 			<div class="ibox-title">   
                  <a class="btn btn-outline btn-default" href="${ctx }/teacher/mystudent/list?tid=${sessionScope.Tea_user.id }" >刷新</a>                
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
									<th>毕设</th>
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
								<td>
									<file:showfile value="${stu.s_file}" url="${ctx }/student/mysubject" id="${student.id}" hidden="true"></file:showfile>
								</td>
								<td class="center">			
										<a class="btn btn-outline btn-default" href="${ctx }/teacher/mystudent/delete?id=${stu.id}&tid=${sessionScope.Tea_user.id }" onclick="return confirmx(this.href);" >拒绝</a>
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