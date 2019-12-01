<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>我的论文</title>
</head>
<body>

	 <div class="ibox-content">
	 <form:form id="inputForm" action="" method="post" modelAttribute="pager" class="form-horizontal">
	 <form:input path="id" type="hidden" />
	 <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">毕设标题：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="p_name"  readonly="true" class="form-control required"/>
						
					</td>
					<td class="width-15 active"><label class="pull-right">所属院系：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="department.d_name" readonly="true" htmlEscape="院系" class="form-control"/>
					</td>

				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">毕设类型：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:select path="p_type" readonly="true" class="form-control required">
							<form:options items="${fns:getDictList('p_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right">毕设导师：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="teacher.t_name" readonly="true" htmlEscape="院系" class="form-control"/>
					</td>
				</tr>
				
				<tr>
					
					<td class="width-15 active"><label class="pull-right">允许最大人数：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="p_max" htmlEscape="人数" readonly="true" class="form-control required" />
					</td>
					<td class="width-15 active"><label class="pull-right">分数：<font color="red">*</font></label></td>
					<td class="width-35">
						<input value="${student.s_score}" readonly="true" class="form-control required" />
					</td>
					
				</tr>
				<%--<tr>
					
					<td class="width-15 active"><label class="pull-right">上传论文：<font color="red">*</font></label></td>
					<td class="center" colspan="3">	
						<file:fileupload id="${student.id}" url="${ctx }/student/mysubject" user="${sessionScope.Stu_user.id }"  ></file:fileupload>
						<file:showfile value="${student.s_file}" url="${ctx }/student/mysubject" id="${student.id}"></file:showfile>
					</td>
				</tr>--%>
		 	</tbody>
		</table>
	</form:form>

</div>
</body>
</html>