<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<html>
<head>
	<title>我的导师</title>
</head>
 <form:form id="inputForm" action="${ctx }/manage/baseinfo/teacher/save" method="post" modelAttribute="teacher" class="form-horizontal">
	 <form:input path="id" type="hidden" />
	 <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<%-- <td class="width-15 active"><label class="pull-right">教师工号：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="t_id" readonly="true" class="form-control required" />
						
					</td> --%>
					<td class="width-15 active"><label class="pull-right">职称：</label></td>
					<td class="width-35">
						<form:select path="t_pro" readonly="true" class="form-control required">
							<form:options readonly="true" items="${fns:getDictList('t_pro')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right">教师姓名：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="t_name" readonly="true" class="form-control required"/>
						
					</td>
					<td class="width-15 active"><label class="pull-right">所在院系：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="department.id"   type="hidden" htmlEscape="院系" class="form-control"/>
						<form:input path="department.d_name" readonly="true"  htmlEscape="院系" class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">联系电话：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="t_phone" readonly="true" class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">联系邮箱：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="t_mail" readonly="true" class="form-control required"/>
					</td>
					
				
					<td class="width-15 active"><label class="pull-right">带人数量：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="t_max" readonly="true" class="form-control required"/>
					</td>
					
				</tr>
		 	</tbody>
	</table>
	</form:form>
<body class="childrenBody">
			 <div class="ibox-title">
            	  <a class="btn btn-outline btn-default" href="${ctx }/student/myteacher/list?id=${sessionScope.Stu_user.id }" >刷新</a>	
             </div>
			 <div class="ibox-content">
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
								<tr>
									<th>毕设标题</th>
									<th>题目描述</th>
									<th>毕设类型</th>	
									<th>允许最大人数</th>
									<!-- <th>操作</th> -->
								</tr> 
						    </thead>
						  
						    <c:forEach items="${pagerList}" var="pager">
							<tr class="gradeX">

								<td class="center">
									${pager.p_name}
								</td>
								<td class="center">
<%--
									<file:showfile value="${pager.p_descripe}" url="${ctx }/student/myteacher" id="${pager.id}" hidden="true"></file:showfile>
--%>
								${pager.p_descripe}
								</td>
								<td class="center">
									${fns:getDictLabel(pager.p_type,'p_type','')}
								</td>
								<td class="center">
									${pager.p_max}
								</td>							
								<%-- <td class="center">										
									<a class="btn btn-outline btn-default" href="${ctx }/teacher/choosestu/choose?id=${stu.id}&period=4&tid=${sessionScope.Tea_user.id }&pid=${stu.s_wish2.id}"  onclick="return confirmchoose(this.href);">更换</a>	
								</td> --%>
							</tr>
						</c:forEach>

                        </table>
		
	</div>
	
    <script>
        $(document).ready(function(){$(".dataTables-example").dataTable();});
    </script>
    
	
</body>
</html>