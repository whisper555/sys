<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="文件字符串"%>
<%@ attribute name="url" type="java.lang.String" required="true" description="controller路径"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="id  要删除的角色"%>
<%@ attribute name="hidden" type="java.lang.String" required="false" description="是否隐藏删除"%>
<script type="text/javascript">
	function delfile(dom, file,id) {

		top.layer.confirm('确定要删除文件吗?', {
			title : '提示',
			skin : 'layui-layer-lan',
			anim : 4
		}, function(index) {
			$.ajax({

				url : "${url}/delfile",
				type : 'POST',
				data : {"file" : encodeURI(encodeURI(file)),"id":id},
				success : function(data) {
					var table = dom.parentNode.parentNode.parentNode;
					//console.log(table);
					table.removeChild(dom.parentNode.parentNode);
					
				},
				error : function(responseStr) {
					console.log(responseStr);
				}
			})
			top.layer.close(index);

		});

	}
</script>
<c:if test="${value != ''}">
	<table id="files">
		<c:forEach items="${fn:split(value, '|')}" var="file">
			<c:if test="${file!=null }">
				<tr id="${file }">
					<td><a href="${url}/filedownload?file=${file}">${file}</a></td>
					<td><a href="${url}/filedownload?file=${file}" class="btn btn-success btn-xs">下载</a></td>
					<c:if test="${hidden!=true }">
						<td><a href="javascript:void(0)" onclick="delfile(this,'${file}','${id }')" class="btn btn-success btn-xs" >删除</a></td>
					</c:if>
				</tr>
			</c:if>
		</c:forEach>
	</table>
</c:if>