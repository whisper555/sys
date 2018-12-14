<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑字典</title>
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
	 <form:form id="inputForm" action="${ctx }/manage/controlstation/dictionary/save" method="post" modelAttribute="dictionary" class="form-horizontal">
	 <form:input path="id" type="hidden" />
	 <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">类型：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="type"  class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">描述：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="description"  class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">标签：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="label"  class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">值：<font color="red">*</font></label></td>
					<td class="width-35">
						<form:input path="value"  class="form-control required"/>
					</td>
				</tr>
				
				
		 	</tbody>
		</table>
	</form:form>

</body>
</html></html>