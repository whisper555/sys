<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<html>
<head>
	<title>院系信息管理</title>
</head>
<body class="childrenBody">
			 <div class="ibox-title">
				<message:message content="${message}"></message:message>  
			</div>
			 <div class="ibox-title">
                  <a class="btn btn-outline btn-default" onclick="openDialog('插入院系信息','${ctx }/manage/baseinfo/department/edit?id=${department.id }','800px','400px','')" >添加</a>       
                  <a class="btn btn-outline btn-default" href="${ctx }/manage/baseinfo/department/deleteAll" onclick="return confirmx(this.href);">删除</a>        
            	  <a class="btn btn-outline btn-default" href="${ctx }/manage/baseinfo/department/list" >刷新</a>	
             </div>
			 <div class="ibox-content">
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
								<tr>
									<th>院系名称</th>
									<th>所在院</th>
									<th>操作</th>
								</tr> 
						    </thead>
						  
						    <c:forEach items="${departmentlist}" var="department">
							<tr class="gradeX">

								<td class="center">
									${department.d_name}
								</td>
								<td class="center">
									${department.f_name}
								</td>
								
								<td class="center">										
										<a class="btn btn-outline btn-default" onclick="openDialog('编辑院系信息','${ctx }/manage/baseinfo/department/edit?id=${department.id }','800px','400px','')" ><i class="fa fa-paste"></i>编辑</a>
										<a class="btn btn-outline btn-default" href="${ctx }/manage/baseinfo/department/delete?id=${department.id}" onclick="return confirmx(this.href);" ><i class="fa fa-warning"></i>删除</a>
				
				    				
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