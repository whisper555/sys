<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<html>
<head>
	<title>毕设信息管理</title>
</head>
<body class="childrenBody">
			<div class="ibox-title">
				<message:message content="${message}"></message:message>  
			</div>
			 <div class="ibox-title">
                  <a class="btn btn-outline btn-default" onclick="openDialog('插入论文信息','${ctx }/manage/baseinfo/pager/insert','800px','400px','')" >添加</a>  
                  <%-- <excel:importExcel url="${ctx}/manage/baseinfo/pager/import"></excel:importExcel><!-- 导入按钮 -->
                  <a class="btn btn-outline btn-default" href="${ctx }/manage/baseinfo/pager/export" >导出</a>    --%>  
                  <a class="btn btn-outline btn-default" href="${ctx }/manage/baseinfo/pager/deleteAll" onclick="return confirmx(this.href);">删除</a>        
            	  <a class="btn btn-outline btn-default" href="${ctx }/manage/baseinfo/pager/list" >刷新</a>	
             </div>
			 <div class="ibox-content">
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
								<tr>
									<th>毕设标题</th>
									<th width="30%">题目描述</th>
									<th>毕设类型</th>
									<th>毕设导师</th>		
									<th>所属院系</th>
									<th>最大人数</th>
									<th>操作</th>
								</tr> 
						    </thead>
						  
						    <c:forEach items="${pagerlist}" var="pager">
							<tr class="gradeX">

								<td class="center">
									${pager.p_name}
								</td>
								<td width="30%">
									<file:showfile value="${pager.p_descripe}" url="${ctx }/manage/subject" id="${pager.id}" hidden="true"></file:showfile>
									<%-- ${pager.p_descripe} --%>
								</td>
								<td class="center">
									${fns:getDictLabel(pager.p_type,'p_type','')}
								</td>
								<td class="center">
									${pager.teacher.t_name}
								</td>
								<td class="center">
									${pager.department.d_name}
								</td>
								<td class="center">
									${pager.p_max}
								</td>							
								<td class="center">										
										<a class="btn btn-outline btn-default" onclick="openDialog('编辑论文信息','${ctx }/manage/baseinfo/pager/edit?id=${pager.id }','800px','400px','')" ><i class="fa fa-paste"></i>编辑</a>
										<a class="btn btn-outline btn-default" href="${ctx }/manage/baseinfo/pager/delete?id=${pager.id}" onclick="return confirmx(this.href);" ><i class="fa fa-warning"></i>删除</a>
				
				    				
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