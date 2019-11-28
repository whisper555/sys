<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/table.jsp"%>
<html>
<head>
    <title>没提交论文的教师</title>
</head>
<body class="childrenBody">
<div class="ibox-title">
    <a class="btn btn-outline btn-default" href="${ctx }/manage/baseinfo/teacher/nopagerlist" >刷新</a>
</div>
<div class="ibox-content">
    <table class="table table-striped table-bordered table-hover dataTables-example" >
        <thead>
        <tr>
            <th width="10%">教师工号</th>
            <th>教师姓名</th>
            <th>联系电话</th>
            <th>联系邮箱</th>
            <th>最大带人数量</th>
            <th>职称</th>
        </tr>
        </thead>

        <c:forEach items="${teacherlist}" var="teacher">
            <tr class="gradeX">
                <td class="center">
                        ${teacher.t_id}
                </td>
                <td class="center">
                        ${teacher.t_name}
                </td>
                <td class="center">
                        ${teacher.t_phone}
                </td>
                <td class="center">
                        ${teacher.t_mail}
                </td>
                <td class="center">
                        ${teacher.t_max}
                </td>
                <td class="center">
                        ${fns:getDictLabel(teacher.t_pro, 't_pro', '')}
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