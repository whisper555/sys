<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<html>
<head>
	<title>文件管理</title>
</head>
<body class="childrenBody">
			 <div class="ibox-title">
				<a class="btn btn-outline btn-default" href="${ctx }/manage/controlstation/file/deleteAll" onclick="return confirmx(this.href);" ><i class="fa fa-warning"></i>全部删除</a>
             	 <a class="btn btn-outline btn-default" href="${ctx }/manage/controlstation/fileInfo/list" >刷新</a>	
             </div>
			 <div class="ibox-content">
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
								<tr>
									<th>地址</th>
									<th>用户代码</th>		
									<th>操作</th>		
								</tr> 
						    </thead>
						  
						    <c:forEach items="${filelist}" var="file">
							<tr class="gradeX">
								
								
								<td class="center">
									${file.file_url}
								</td>
								<td class="center">
									${file.user_id}
								</td>
								
								<td class="center">										
									
										<a class="btn btn-outline btn-default" href="${ctx }/manage/controlstation/file/delete?id=${file.id}" onclick="return confirmx(this.href);" ><i class="fa fa-warning"></i>删除</a>
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