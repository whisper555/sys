<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<html>
<head>
	<title>分配教师</title>
	<link rel="stylesheet" href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
	<script>
	function getAllocationTeacherList(dom){
		var idobj = $("#"+dom+"teacherval");
		var nameobj = $("#"+dom+"teacherlab");
		var teacheravailableTags;
		
		$.ajax({
			type:"get",
			async:false,
			contentType:"application/json;charset=utf-8",
			url:"${ctx }/manage/allocation/allteacher/getAllocationTeacherList", 
			dataType:"json",
			success:function(result){
				teacheravailableTags=result;
				console.log(teacheravailableTags);
			},
			 error:function(result){
				 showMessage("ajax error！   获取教师列表失败！");
			}	 
		});
		nameobj.autocomplete({
		      source: teacheravailableTags,
		      focus: function( event, ui ) {
		          nameobj.val( ui.item.label );
		          return false;
		        },
		        select: function( event, ui ) {
		          nameobj.val( ui.item.label );
		          idobj.val( ui.item.value );
		   
		          return false;
		        }
		    });	 
	
	
}
function setTeacher(dom){
	
	var idobj = $("#"+dom+"teacherval");
	var t_id = idobj.val();
	if($("#"+dom+"teacherlab").val()==""||$("#"+dom+"teacherlab").val()==null){
		idobj.val("");
		t_id=null;
	}
	//console.log(t_id+"  "+dom);
	$.ajax({
		type:"get",
		async:false,
		contentType:"application/json;charset=utf-8",
		url:"${ctx }/manage/allocation/allteacher/saveallocationteacher",
		data: {"id":dom,"teacher.id":t_id},  
		dataType:"text",
		success:function(result){
			//console.log(result);
		},
		 error:function(result){
			showMessage("设置教师时出错  ajax error!");
		}	 
	});


}
	</script>
</head>
<body class="childrenBody">
 			<div class="ibox-title">
 				  <a class="btn btn-outline btn-default" href="${ctx }/manage/allocation/allteacher/nolist">只看未分配学生</a>  
                  <a class="btn btn-outline btn-default" href="${ctx }/manage/allocation/allteacher/list" >刷新</a>                
             </div>
			 <div class="ibox-content">
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
								<tr>
									<th>学生学号</th>
									<th>学生姓名</th>			
									<th>所在班级</th>
									<th>指导教师</th>
								</tr> 
						    </thead>
						  
						    <c:forEach items="${studentlist}" var="stu">
							<tr class="gradeX">
								
								<td class="center">
									${stu.s_id}
								</td>
								
								<td class="center">
									${stu.s_name}
								</td>
								
								<td class="center">
									${stu.classs.c_name}
								</td>

								<td class="center">
									<input  id="${stu.id}teacherval" value="${stu.teacher.id}" type="hidden">
									<input type="text" id="${stu.id}teacherlab"  value="${stu.teacher.t_name}" onblur="setTeacher('${stu.id}')" onkeydown="getAllocationTeacherList('${stu.id}')" class="form-control" />
								</td>
								
							</tr>
						</c:forEach>

                        </table>
		
	</div>
	
    <script>
        $(document).ready(function(){$(".dataTables-example").dataTable();});
    </script>
    
	
</body>
</html>