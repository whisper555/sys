<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<html>
<head>
	<title>填报志愿</title>
</head>
<body class="childrenBody">

			 <div class="ibox-title">
                  <a class="btn btn-outline btn-default" href="${ctx }/student/choosepager/list?sid=${sessionScope.Stu_user.id }&period=2" >刷新</a>                
            <form:form modelAttribute="studentInfo" class="form-inline">
            <tr>
					<td class="width-15 active">第一志愿：</td>
					<td class="width-25">
						<form:input path="s_wish1.id" type="hidden" class="form-control"/>
						<form:input path="s_wish1.p_name" class="form-control"/>
					</td>
					<td class="width-15 active">第二志愿：</td>
					<td class="width-25">
						<form:input path="s_wish2.id" type="hidden" class="form-control"/>
						<form:input path="s_wish2.p_name" class="form-control"/>
					</td>
					<td class="width-15 active">第三志愿：</td>
					<td class="width-25">
						<form:input path="s_wish3.id" type="hidden" class="form-control"/>	
						<form:input path="s_wish3.p_name" class="form-control"/>
					</td>
			</tr>
            </form:form>
             </div>
              <div class="ibox-content">
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
								<tr>
									<th>毕设标题</th>
									<th>题目描述</th>
									<th>毕设类型</th>
									<th>毕设导师</th>		
									<th>允许最大人数</th>
									<th>当前人数</th>
									<th>操作</th>
								</tr> 
						    </thead>
						  
						    <c:forEach items="${pagerlist}" var="pager">
							<tr class="gradeX">

								<td class="center">
									${pager.p_name}
								</td>
								<td class="center">
<%-- 									<%--<file:fileupload id="${pager.id}" url="${ctx }/teacher/subject" user="${sessionScope.Tea_user.id }" ></file:fileupload>
 &ndash;%&gt;									<file:showfile value="${pager.p_descripe}" url="${ctx }/teacher/subject" id="${pager.id}" hidden="true"></file:showfile>--%>
									 ${pager.p_descripe}
								</td>
								<td class="center">
									${fns:getDictLabel(pager.p_type,'p_type','')}
								</td>
								<td class="center">
									${pager.teacher.t_name}
								</td>
								
								<td class="center">
									${pager.p_max}
								</td>	
								<td class="center">
									${pager.p_now}
								</td>							
								<td class="center">										
										
									<a class="btn btn-outline btn-default" href="${ctx }/student/choosepager/setorder?sid=${sessionScope.Stu_user.id }&id=${pager.id}&order=1"  >第一志愿</a>
									<a class="btn btn-outline btn-default" href="${ctx }/student/choosepager/setorder?sid=${sessionScope.Stu_user.id }&id=${pager.id}&order=2"  >第二志愿</a>
				    				<a class="btn btn-outline btn-default" href="${ctx }/student/choosepager/setorder?sid=${sessionScope.Stu_user.id }&id=${pager.id}&order=3"  >第三志愿</a>
							
				    				
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