<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑学生信息</title>
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
	 <form:form id="inputForm" action="${ctx }/manage/baseinfo/student/save" method="post" modelAttribute="studentInfo" class="form-horizontal">
	 <form:input path="id" type="hidden" />
	<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">学生学号：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="s_id"  class="form-control required"/>
						
					</td>
					<td class="width-15 active"><label class="pull-right">学生姓名：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="s_name"  class="form-control required"/>
						
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">学生密码：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="s_pw" type="password" htmlEscape="密码" class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">性别：</label></td>
					<td class="width-35">
						<form:select path="s_sex" class="form-control required">
							<form:options items="${fns:getDictList('s_sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					
					<td class="width-15 active"><label class="pull-right">所在班级：<font color="red">*</font></label></td>
					<td class="width-35">
						<%-- <form:input path="classs.id" htmlEscape="班级" class="form-control required"/> --%>
						<form:select path="classs.id"  class="form-control required">
							<c:forEach items="${classsList }" var ="classs" >
								<form:option value="${classs.id }">${classs.c_name }</form:option>
							</c:forEach>
						</form:select>
					</td>
					<%-- <td class="width-15 active"><label class="pull-right">论文标题：</label></td>
					<td class="width-35">
						<form:input path="pager.p_name" htmlEscape="未选择" class="form-control"/>
					</td> --%>
					<td class="width-15 active"><label class="pull-right">教师评分：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="s_score_t" htmlEscape="暂无" class="form-control required number"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">联系方式：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="s_phone" htmlEscape="false" class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">毕业分数：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="s_score" htmlEscape="暂无" class="form-control required number"/>
					</td>
					
				</tr>
				
				
		 	</tbody>
		</table>
	</form:form>
</div>
</body>
</html>