<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%> 
<html>
<head>
	<title>我的题目</title>
</head>
<body class="childrenBody">
			 <div class="ibox-title">
                  <a class="btn btn-outline btn-default" onclick="openDialog('插入论文信息','${ctx }/teacher/subject/insert?period=1','800px','400px','')" >添加</a>    
            	  <a class="btn btn-outline btn-default" href="${ctx }/teacher/subject/list?tid=${sessionScope.Tea_user.id }&period=1" >刷新</a>	
             </div>
			 <div class="ibox-content">
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
								<tr>
									<th>毕设标题</th>
									<th>题目描述</th>
									<th>毕设类型</th>
									<th>毕设导师</th>		
									<th>所属院系</th>
									<th>允许最大人数</th>
									<th>操作</th>
								</tr>  
						    </thead>
						  
						    <c:forEach items="${pagerlist}" var="pager">
							<tr class="gradeX">

								<td class="center">
									${pager.p_name}
								</td>
								<td class="center">	
									<%--<file:fileupload id="${pager.id}" url="${ctx }/teacher/subject" user="${sessionScope.Tea_user.id }"  ></file:fileupload>
									<file:showfile value="${pager.p_descripe}" url="${ctx }/teacher/subject" id="${pager.id}"></file:showfile>--%>
									${pager.p_descripe}
								</td> 
								<td class="center">
									${fns:getDictLabel(pager.p_type,'p_type','')}
								</td>
								<td class="center">
									${sessionScope.Tea_user.name }
								</td>
								<td class="center">
									${pager.department.d_name}
								</td>
								<td class="center">
									${pager.p_max}
								</td>							
								<td class="center">										
										<a class="btn btn-outline btn-default" onclick="openDialog('插入论文信息','${ctx }/teacher/subject/edit?id=${pager.id }','800px','400px','')" ><i class="fa fa-paste"></i>编辑</a>
										<a class="btn btn-outline btn-default" href="${ctx }/teacher/subject/delete?id=${pager.id}&tid=${sessionScope.Tea_user.id }&period=1" onclick="return confirmx(this.href);" ><i class="fa fa-warning"></i>删除</a>
				
				    				
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