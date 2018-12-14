<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<html>
<head>
	<title>分数上传</title>
</head>
<body class="childrenBody">
			  
		     <div class="ibox-title">
			<message:message content="${message}"></message:message>  
			</div>
 			<div class="ibox-title">
                  <excel:importExcel url="${ctx}/manage/analysis/fractionalupload/import"></excel:importExcel><!-- 导入按钮 -->
                  <a class="btn btn-outline btn-default" href="${ctx }/manage/analysis/fractionalupload/export" ><i class="fa fa-folder-open-o"></i>导出</a>     
                  <a class="btn btn-outline btn-default" href="${ctx }/manage/analysis/fractionalupload/list" >刷新</a>                
             </div>
			 <div class="ibox-content">
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
								<tr>
									<th>学生学号</th>
									<th>学生姓名</th>
									
									<th>毕设分数</th>
									
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
									${stu.s_score}
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