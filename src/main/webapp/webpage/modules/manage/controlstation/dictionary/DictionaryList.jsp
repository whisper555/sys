<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<html>
<head>
	<title>字典管理</title>
</head>
<body class="childrenBody">
			 <div class="ibox-title">
			  <a class="btn btn-outline btn-default" onclick="openDialog('插入字典信息','${ctx }/manage/controlstation/dictionary/edit?id=${classs.id }','800px','400px','')" >添加</a>   
             	  <a class="btn btn-outline btn-default" href="${ctx }/manage/controlstation/dictionary/list" >刷新</a>	
             </div>
			 <div class="ibox-content">
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
								<tr>
									<th>类型</th>
									<th>标签</th>		
									<th>值</th>
									<th>描述</th>
									<th>操作</th>
								</tr> 
						    </thead>
						  
						    <c:forEach items="${dictionarylist}" var="dictionary">
							<tr class="gradeX">
								
								<td class="center">
									${dictionary.type}
								</td>
								<td class="center">
									${dictionary.label}
								</td>
								<td class="center">
									${dictionary.value}
								</td>
								<td class="center">
									${dictionary.description}
								</td>
								
								
								<td class="center">										
										<a class="btn btn-outline btn-default" onclick="openDialog('编辑班级信息','${ctx }/manage/controlstation/dictionary/edit?id=${dictionary.id }','800px','400px','')" ><i class="fa fa-paste"></i>编辑</a>
										<a class="btn btn-outline btn-default" href="${ctx }/manage/manage/controlstation/dictionary/delete?id=${dictionary.id}" onclick="return confirmx(this.href);" ><i class="fa fa-warning"></i>删除</a>
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