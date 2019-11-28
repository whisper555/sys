<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<html>
<head>
	<title>随机分配</title>
	<link rel="stylesheet" href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
</head>
<body class="childrenBody">
 			<div class="ibox-title">
 				 <a class="btn btn-outline btn-default" href="${ctx }/manage/allocation/random/randomAllocationTeacher" onclick="return confirmMessage(this.href,'确定要随机分配教师吗，不可逆');">分配教师</a>  
 			  	  <a class="btn btn-outline btn-default" href="${ctx }/manage/allocation/random/randomAllocationPager" onclick="return confirmMessage(this.href,'确定要随机分配论文吗，不可逆');" >分配论文</a>  
                   <a class="btn btn-outline btn-default" href="${ctx }/manage/baseinfo/student/exportresult" ><i class="fa fa-folder-open-o"></i>导出毕业分配结果</a>
                  <a class="btn btn-outline btn-default" href="${ctx }/manage/allocation/random/list" >刷新</a>                
             </div>
			 <div class="ibox-content">
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
								<tr>
									<th>学生学号</th>
									<th>学生姓名</th>			
									<th>所在班级</th>
									<th>指导教师</th>
									<th>毕设题目</th>
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
									${stu.classs.c_name}
								</td>

								<td class="center">
									${stu.teacher.t_name}
								</td>
								<td class="center">
									${stu.pager.p_name}
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