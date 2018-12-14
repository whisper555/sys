<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<html>
<head>
	<title>时间管理</title>
</head>
<body class="childrenBody">
			 <div class="ibox-title">
             	  <a class="btn btn-outline btn-default" href="${ctx }/manage/controlstation/schedule/list" >刷新</a>	
             </div>
			 <div class="ibox-content">
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
								<tr>
									<th>阶段</th>
									<th>开始时间</th>		
									<th>结束时间</th>
									<th>操作</th>
								</tr> 
						    </thead>
						  
						    <c:forEach items="${schedulelist}" var="schedule">
							<tr class="gradeX">
								<td class="center">
									${fns:getDictLabel(schedule.period,"period","")}
								</td>
								<td class="center">
									<fmt:formatDate value="${schedule.starttime}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td class="center">
									<fmt:formatDate value="${schedule.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td class="center">										
									<a class="btn btn-outline btn-default" onclick="openDialog('更改时间','${ctx }/manage/controlstation/schedule/edit?id=${schedule.id }','900px','400px','')" ><i class="fa fa-paste"></i>修改时间段</a>
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