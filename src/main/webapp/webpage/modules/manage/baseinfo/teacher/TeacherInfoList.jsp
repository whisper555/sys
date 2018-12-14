<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<html>
<head>
	<title>教师信息管理</title>
</head>
<body class="childrenBody">
			<div class="ibox-title">
				<message:message content="${message}"></message:message>  
			</div>
			 <div class="ibox-title">
                  <a class="btn btn-outline btn-default" onclick="openDialog('插入教师信息','${ctx }/manage/baseinfo/teacher/insert','800px','500px')">添加</a>  
                  <excel:importExcel url="${ctx}/manage/baseinfo/teacher/import"></excel:importExcel><!-- 导入按钮 -->
                  <a class="btn btn-outline btn-default" href="${ctx }/manage/baseinfo/teacher/export" ><i class="fa fa-folder-open-o"></i> 导出</a> 
                  <a class="btn btn-outline btn-default" href="${ctx }/manage/baseinfo/teacher/list" >刷新</a> 
                 
                                 
             </div>
			 <div class="ibox-content">
                        <table class="table table-striped table-bordered table-hover dataTables-example" >
                            <thead>
								<tr>
									<th width="10%">教师工号</th>
									<%--<th>密码</th>--%>
									<th>教师姓名</th>		
									<th>所在部门</th>
									<th>联系电话</th>
									<th>联系邮箱</th>
									<th>是否管理</th>
									<th>最大带人数量</th>
									<th>职称</th>
									<th>操作</th>
								</tr> 
						    </thead>
						  
						    <c:forEach items="${teacherlist}" var="teacher">
							<tr class="gradeX">
								
								<td class="center">
									${teacher.t_id}
								</td>
								<%--<td class="center">
									${teacher.t_pw}
								</td>--%>
								<td class="center">
									${teacher.t_name}
								</td>
								<td class="center">
									${teacher.department.d_name}
								</td>
								<td class="center">
									${teacher.t_phone}
								</td>
								<td class="center">
									${teacher.t_mail}
								</td>
								<td class="center">
									${fns:getDictLabel(teacher.t_manager, 't_manager', '')}
								</td>
								<td class="center">
									${teacher.t_max}
								</td>
								<td class="center">
									${fns:getDictLabel(teacher.t_pro, 't_pro', '')}
								</td>
								
								<td class="center">										
										<a class="btn btn-outline btn-default"  onclick="openDialog('修改教师信息','${ctx }/manage/baseinfo/teacher/edit?id=${teacher.id}','800px','500px','')"><i class="fa fa-paste"></i>编辑</a>
										<a class="btn btn-outline btn-default" href="${ctx }/manage/baseinfo/teacher/delete?id=${teacher.id}" onclick="return confirmx(this.href);" ><i class="fa fa-warning"></i>删除</a>
				
				    				
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