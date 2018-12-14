<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<html>
<head>
  <link rel="stylesheet" href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
	<title>我的学生</title>
	<style>
		.ui-autocomplete {
			max-height: 120px;
			overflow-y: auto;
			/* 防止水平滚动条 */
			overflow-x: hidden;
		}
		
	
			input::-webkit-input-placeholder { /* WebKit browsers */ 
			color: red !important; 
			} 
			input:-moz-placeholder { /* Mozilla Firefox 4 to 18 */ 
			color: red !important; 
			} 
			input::-moz-placeholder { /* Mozilla Firefox 19+ */ 
			color: red !important; 
			} 
			input:-ms-input-placeholder { /* Internet Explorer 10+ */ 
			color: red !important; 
			} 
	</style>

	<script>
	function getPagerList(dom,tid){
			var idobj = $("#"+dom+"pagerval");
			var nameobj = $("#"+dom+"pagerlab");
			var pageravailableTags;
			
			$.ajax({
				type:"get",
				async:false,
				contentType:"application/json;charset=utf-8",
				url:"${ctx }/teacher/mystudent/getTeacherPagerList",
				data: {"tid":tid.toString()},  
				dataType:"json",
				success:function(result){
					pageravailableTags=result;
					console.log(pageravailableTags);
				},
				 error:function(result){
					 showMessage("ajax error！   获取论文列表失败！");
				}	 
			});
			nameobj.autocomplete({
			      source: pageravailableTags,
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
	function setPager(dom,tid){
		
		var idobj = $("#"+dom+"pagerval");
		var id = idobj.val();
		if($("#"+dom+"pagerlab").val()==""||$("#"+dom+"pagerlab").val()==null){
			id=null;
			idobj.val(null);
		}else if(($("#"+dom+"pagerlab").val()!=""||$("#"+dom+"pagerlab").val()!=null)&&(id==""||id==null)){
			showMessage("没有匹配的论文！");
		}
		$.ajax({
			type:"get",
			async:false,
			contentType:"application/json;charset=utf-8",
			url:"${ctx }/teacher/mystudent/setPager",
			data: {"pager.id":id,"id":dom},  
			dataType:"text",
			success:function(result){
				console.log(result);
			},
			 error:function(result){
				showMessage("设置论文时出错  ajax error!");
			}	 
		});
	
	
}
	</script>

</head>
<body class="childrenBody">
 			<div class="ibox-title">   
                  <a class="btn btn-outline btn-default" href="${ctx }/teacher/mystudent/list?tid=${sessionScope.Tea_user.id }" >刷新</a>                
             </div>
			 <div class="ibox-content">
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
								<tr>
									<th>学生学号</th>
									<th>学生姓名</th>	
									<th>性别</th>	
									<th>所在班级</th>
									<th>联系方式</th>
									<th>导师</th>
									<th>论文题目</th>
									<th>操作</th>
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
									${fns:getDictLabel(stu.s_sex, 's_sex', '')}
								</td>
								<td class="center">
									${stu.classs.c_name}
								</td>
								<td class="center">
									${stu.s_phone}
								</td>
								<td class="center">
									${stu.teacher.t_name}
								</td>
								<td class="center">
									<input  id="${stu.id}pagerval" value="${stu.pager.id}" type="hidden">
									<input type="text" id="${stu.id}pagerlab"  value="${stu.pager.p_name}" onblur="setPager('${stu.id}','${sessionScope.Tea_user.id }')" onkeydown="getPagerList('${stu.id}','${sessionScope.Tea_user.id }')" class="form-control" />
								</td>
								<td class="center">			
										<a class="btn btn-outline btn-default" href="${ctx }/teacher/mystudent/delete?id=${stu.id}&tid=${sessionScope.Tea_user.id }" onclick="return confirmx(this.href);" >拒绝</a>
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