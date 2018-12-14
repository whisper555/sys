<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<html>
<head>
	<title>学生信息管理</title>
</head>
<body class="childrenBody">
			  
		    <div class="ibox-title">
			<message:message content="${message}"></message:message>  
			</div>
 			<div class="ibox-title">
                  <a class="btn btn-outline btn-default" onclick="openDialog('插入学生信息','${ctx }/manage/baseinfo/student/insert','800px','400px','')" >添加</a>  
                  <excel:importExcel url="${ctx}/manage/baseinfo/student/import"></excel:importExcel><!-- 导入按钮 -->
                  <a class="btn btn-outline btn-default" href="${ctx }/manage/baseinfo/student/export" ><i class="fa fa-folder-open-o"></i>导出</a>     
                  <a class="btn btn-outline btn-default" href="${ctx }/manage/baseinfo/student/list" >刷新</a>                
             </div>
			 <div class="ibox-content">
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
								<tr>
									<th>学生学号</th>
									<%--<th>学生密码</th>--%>
									<th>学生姓名</th>
									<th>性别</th>	
									<th>所在班级</th>
									<th>联系方式</th>
									<th>论文标题</th>
									<th>指导教师</th>
									<th>毕设分数</th>
									<th>操作</th>
								</tr> 
						    </thead>
						  
						    <c:forEach items="${studentlist}" var="stu">
							<tr class="gradeX">
								
								<td class="center">
									${stu.s_id}
								</td>
								<%--<td class="center">--%>
									<%--${stu.s_pw}--%>
								<%--</td>--%>
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
									${stu.pager.p_name}
								</td>
								<td class="center">
									${stu.teacher.t_name}
								</td>
								<td class="center">
									${stu.s_score}
								</td>
								
								<td class="center">										
										<a class="btn btn-outline btn-default" onclick="openDialog('编辑学生信息','${ctx }/manage/baseinfo/student/edit?id=${stu.id }','800px','400px','')"><i class="fa fa-paste"></i>编辑</a>
										<a class="btn btn-outline btn-default" href="${ctx }/manage/baseinfo/student/delete?id=${stu.id}" onclick="return confirmx(this.href);" ><i class="fa fa-warning"></i>删除</a>
				
				    				
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