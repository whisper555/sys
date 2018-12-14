<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>换届管理</title>
</head>
<body class="childrenBody">
			 
	 <table class="table table-striped table-bordered table-hover dataTables-example">
		<tr class="gradeX center">
		<td >
				<a class="btn btn-outline btn-default" href="${ctx }/manage/controlstation/changeterm/delstu" onclick="return confirmx(this.href);" ><i class="fa fa-warning"></i>删除学生</a>
		</td>
		<td>
				<a class="btn btn-outline btn-default" href="${ctx }/manage/controlstation/changeterm/delpager" onclick="return confirmx(this.href);" ><i class="fa fa-warning"></i>删除论文</a>

		</td>
		<td>
				<a class="btn btn-outline btn-default" href="${ctx }/manage/controlstation/changeterm/delteacher" onclick="return confirmx(this.href);" ><i class="fa fa-warning"></i>删除教师</a>

		</td>
		
	</tr>
	</table>
    <script>
        $(document).ready(function(){$(".dataTables-example").dataTable();});
    </script>
   
	
</body>
</html>