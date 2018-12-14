<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑个人信息</title>
<script type="text/javascript">
	function save(id) {
		
		var t_id = $("#t_id").val();
		var t_name = encodeURI($("#t_name").val());
		var t_pw = $("#t_pw").val();
		var t_phone = $("#t_phone").val();
		var t_mail = $("#t_mail").val();
		var t_max = $("#t_max").val();
		var t_pro = $("#t_pro").val();
		$.ajax({
			type : "get",
			contentType : "application/json;charset=utf-8",
			url : "${ctx }/teacher/infomation/save",
			data :

			{
				"t_id" : t_id,
				"t_name" : t_name,
				"t_pw" : t_pw,
				"t_phone" : t_phone,
				"t_mail" : t_mail,
				"t_max" : t_max,
				"t_pro" : t_pro
			},
			dataType : "json",
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
		<form:form id="inputForm" action="${ctx }/teacher/information/save"
			method="post" modelAttribute="teacherInfo" class="form-horizontal">
			<form:input path="id" type="hidden" />

			<table
				class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
				<tbody>
					<tr>
						<td class="width-15 active"><label class="pull-right">教师工号：<font	color="red">*</font></label></td>
						<td class="width-35">
							<form:input path="t_id" readonly="true"	class="form-control required" />
						</td>
						<td class="width-15 active"><label class="pull-right">教师姓名：<font		color="red">*</font></label></td>
						<td class="width-35"><form:input path="t_name" onblur="save()" class="form-control required" />
						</td>
					</tr>
					<tr>
						<td class="width-15 active"><label class="pull-right">密码：<font color="red">*</font></label></td>
						<td class="width-35">
							<form:input path="t_pw" type="text"	onblur="save()" htmlEscape="密码" class="form-control required" />
						</td>
						<td class="width-15 active"><label class="pull-right">所在院系：<font  color="red">*</font></label></td>
						<td class="width-35">
							<form:input path="department.id"	type="hidden" htmlEscape="院系" class="form-control" /> <form:input	path="department.d_name" htmlEscape="院系"	class="form-control required" /></td>
					</tr>
					<tr>
						<td class="width-15 active"><label class="pull-right">联系电话：<font color="red">*</font></label></td>
						<td class="width-35">
							<form:input path="t_phone"	htmlEscape="密码" onblur="save()"	class="form-control required" /></td>
						<td class="width-15 active"><label class="pull-right">联系邮箱：<font		color="red">*</font></label></td>
						<td class="width-35">
							<form:input path="t_mail"	htmlEscape="邮箱" onblur="save()"	class="form-control required" /></td>

					</tr>
					<tr>
						<td class="width-15 active"><label class="pull-right">带人数量：<font color="red">*</font></label></td>
						<td class="width-35">
							<form:input path="t_max" htmlEscape="人数"	readonly="true"  class="form-control required" /></td>
						<td class="width-15 active"><label class="pull-right">职称：</label></td>
						<td class="width-35">
							<form:select path="t_pro"	onblur="save()" class="form-control required">
								<form:options items="${fns:getDictList('t_pro')}" itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select></td>
					</tr>

				</tbody>
			</table>
		</form:form>
	</div>
</body>
</html>