<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<html>
<head>
	<title>班级信息管理</title>
</head>
<body class="childrenBody">
			<div class="ibox-title">
				<message:message content="${message}"></message:message>  
			</div>
			 <div class="ibox-title">
                  <a class="btn btn-outline btn-default" onclick="openDialog('插入班级信息','${ctx }/manage/baseinfo/classs/edit?id=${classs.id }','800px','200px','')" >添加</a>      
                  <a class="btn btn-outline btn-default" href="${ctx }/manage/baseinfo/classs/deleteAll" onclick="return confirmx(this.href);">删除</a>        
             	  <a class="btn btn-outline btn-default" href="${ctx }/manage/baseinfo/classs/list" >刷新</a>	
             </div>
			 <div class="ibox-content">
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
								<tr>
									<th>班级名称</th>
									<th>所属院系</th>		
									<th>操作</th>
								</tr> 
						    </thead>
						  
						    <c:forEach items="${classslist}" var="classs">
							<tr class="gradeX">
								
								<td class="center">
									${classs.c_name}
								</td>
								<td class="center">
									${classs.department.d_name}
								</td>
								
								
								<td class="center">										
										<a class="btn btn-outline btn-default" onclick="openDialog('编辑班级信息','${ctx }/manage/baseinfo/classs/edit?id=${classs.id }','800px','200px','')" ><i class="fa fa-paste"></i>编辑</a>
										<a class="btn btn-outline btn-default" href="${ctx }/manage/baseinfo/classs/delete?id=${classs.id}" onclick="return confirmx(this.href);" ><i class="fa fa-warning"></i>删除</a>
				
				    				
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