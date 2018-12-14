<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑毕设信息</title>
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
	 <form:form id="inputForm" action="${ctx }/teacher/subject/save" method="post" modelAttribute="pagerInfo" class="form-horizontal">
	 <form:input path="id" type="hidden" />
	 <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">毕设标题：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="p_name"  class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">所属院系：<font color="red">*</font></label></td>
					<td class="width-35">
					<form:select path="department.id"  class="form-control required">
						<c:forEach items="${departList }" var ="depart" >
							<form:option value="${depart.id }">${depart.d_name }</form:option>
						</c:forEach>
					</form:select>
						<%-- <form:input path="department.d_name" htmlEscape="院系" class="form-control"/> --%>
					</td>

				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">毕设类型：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:select path="p_type" class="form-control required">
							<form:options items="${fns:getDictList('p_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="width-15 active"><label class="pull-right">毕设导师：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="teacher.id" value="${sessionScope.Tea_user.id }" type="hidden" htmlEscape="false" class="form-control"/>
						<input  value="${sessionScope.Tea_user.name }" class="form-control"/>
					</td>
				</tr>
				
				<tr>
					
					<td class="width-15 active"><label class="pull-right">允许最大人数：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="p_max" htmlEscape="人数" class="form-control number required" />
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>

</body>
</html>