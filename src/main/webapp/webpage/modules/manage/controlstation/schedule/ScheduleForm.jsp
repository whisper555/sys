<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑时间控制</title>

</head>
<body>

	 <div class="ibox-content">
	 <form:form id="inputForm" action="${ctx }/manage/controlstation/schedule/save" method="post" modelAttribute="schedule" class="form-horizontal">
	 <form:input path="id" type="hidden" />
	 <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">开始时间：<font color="red">*</font></label></td>
					<td class="width-35">
					<input id="starttime"
						name="starttimestr" htmlEscape="false"
						value="<fmt:formatDate value="${schedule.starttime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						class="form-control "  onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/>
<%-- 						<form:input class="form-control layer-date required" path="starttime" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/>
 --%>					</td>
					<td class="width-15 active"><label class="pull-right">结束时间：<font color="red">*</font></label></td>
					<td class="width-35">
					<input id="endtime"
						name="endtimestr" htmlEscape="false"
						value="<fmt:formatDate value="${schedule.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						class="form-control "  onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/>
						<%-- <form:input class="form-control layer-date required" path="endtime" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/> --%>
					</td> 
				</tr>
				
				
				
		 	</tbody>
		</table>
	</form:form>
	</div>
</body>
</html>