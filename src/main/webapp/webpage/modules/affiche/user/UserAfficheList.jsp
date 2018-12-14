<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>查看公告</title>
    
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
            
            <div class="col-sm-12 animated fadeInRight">
               
                <div class="ibox-title">   
             	  <a class="btn btn-outline btn-default" href="${ctx }/affiche/manager/list" >刷新</a>	
             </div>
			 <div class="ibox-content">
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
								<tr>
									<th class="col-sm-8">标题</th>
									<th class="col-sm-2">时间</th>		
								</tr> 
						    </thead>
						  
						    <c:forEach items="${affichelist}" var="affiche">
							<tr class="gradeX">
								
								<td class="center">
									<a href="${ctx }/affiche/user/view?id=${affiche.id}">${affiche.a_title}</a>
								</td>
								<td class="center">
									${affiche.a_date}
								</td>
							</tr>
						</c:forEach>

                        </table>
		
	</div>
	
    <script>
        $(document).ready(function(){$(".dataTables-example").dataTable();});
    </script>
   
            </div>
        </div>
    </div>
    
    <script>
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/mailbox.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:18:22 GMT -->
</html>
