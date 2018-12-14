<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑学生信息</title>
<script type="text/javascript">
function save() {
	var s_id = ${	sessionScope.Stu_user.id};
	var s_name = encodeURI($("#s_name").val());
	var s_pw = $("#s_pw").val();
	var s_phone = $("#s_phone").val();
	var s_sex = $("#s_sex").val();
	
	$.ajax({
		type : "get",
		contentType : "application/json;charset=utf-8",
		url : "${ctx }/student/infomation/save",
		data :

		{
			"s_id" : s_id,
			"s_name" : s_name,
			"s_pw" : s_pw,
			"s_phone" : s_phone,
			"t_sex" : s_sex
			
		},
		dataType : "text",
		success : function(result) {

		},
		error : function(result) {

		}
	});
}

	</script>
</head>
<body>

	 <div class="ibox-content">
	 <form:form id="inputForm" action="${ctx }/student/infomation/save" method="post" modelAttribute="studentInfo" class="form-horizontal">
	 <form:input path="id" type="hidden" />
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">学生学号：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="s_id"  readonly="true" onblur="save()" class="form-control required"/>
						
					</td>
					<td class="width-15 active"><label class="pull-right">学生姓名：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="s_name"  onblur="save()" class="form-control required"/>
						
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">学生密码：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="s_pw" type="text" onblur="save()" htmlEscape="密码" class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">性别：</label></td>
					<td class="width-35">
						<form:select path="s_sex" onblur="save()" class="form-control required">
							<form:options items="${fns:getDictList('s_sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					
					<td class="width-15 active"><label class="pull-right">所在班级：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="classs.c_name" readonly="true" htmlEscape="班级" class="form-control required"/>
					</td>
					
				
					<td class="width-15 active"><label class="pull-right">联系方式：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="s_phone" htmlEscape="false" onblur="save()" class="form-control required"/>
					</td>
					
				</tr>
				
				
		 	</tbody>
		</table>
	</form:form>
</div>
</body>
</html>