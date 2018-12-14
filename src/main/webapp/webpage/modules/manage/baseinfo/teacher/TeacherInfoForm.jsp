<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑教师</title>
<script type="text/javascript">
		var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $("#inputForm").submit();
			  return true;
		  }
	
		  return false;
		}
		$(document).ready(function() {
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					form.submit();
				},
			});
			
		});

	</script>
</head>
<body>

	 <div class="ibox-content">
	 <form:form id="inputForm" action="${ctx }/manage/baseinfo/teacher/save" method="post" modelAttribute="teacherInfo" class="form-horizontal">
	 <form:input path="id" type="hidden" />

	 <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">教师工号：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="t_id"  class="form-control required" />
						
					</td>
					<td class="width-15 active"><label class="pull-right">教师姓名：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="t_name"  class="form-control required"/>
						
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">密码：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="t_pw" type="password" htmlEscape="密码" class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">所在院系：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:select path="department.id" onchange="refresh()" class="form-control required">
						<c:forEach items="${departList }" var ="department" >
							<form:option value="${department.id }">${department.d_name }</form:option>
						</c:forEach>
					</form:select>
					</td>
					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">联系电话：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="t_phone" htmlEscape="密码" class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">联系邮箱：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="t_mail" htmlEscape="密码" class="form-control required"/>
					</td>
					
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">带人数量：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="t_max" htmlEscape="人数" class="form-control required number"/>
					</td>
					<td class="width-15 active"><label class="pull-right">职称：</label></td>
					<td class="width-35">
						<form:select path="t_pro" class="form-control required">
							<form:options items="${fns:getDictList('t_pro')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">是否管理：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:select path="t_manager" class="form-control required">
							<form:options items="${fns:getDictList('t_manager')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
		 	</tbody>
	</table>
	</form:form>
</div>
</body>
</html>