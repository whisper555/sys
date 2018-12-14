<%@ tag language="java" pageEncoding="UTF-8"%>   

<%@ attribute name="url" type="java.lang.String" required="true"%> 
<%@ attribute name="id" type="java.lang.String" required="true"%>
<%@ attribute name="user" type="java.lang.String" required="true"%>  
<%@ attribute name="type" type="java.lang.String" required="false"%>  


<div class="form-group">
            <div class="file-loading" >
                <input id="fileupload${id}" name="file" class="file"  type="file" >
            </div> 
</div>
<script> 
$("#fileupload${id}").fileinput({  
    uploadUrl:'${url}/fileupload', 
    language: 'zh', //设置语言  
    allowedFileExtensions: ['jpg', 'gif', 'png','doc','docx','pdf','ppt','pptx','txt','xls','xlsx'],//接收的文件后缀 
    uploadExtraData:{ "user_id":'${user}','id':'${id}'},    
    uploadAsync: true, //默认异步上传  
    showUpload: true, //是否显示上传按钮  
    showRemove : true, //显示移除按钮  
    showPreview : true, //是否显示预览  
    showCaption: true,//是否显示标题  
    browseClass: "btn btn-primary", //按钮样式  
    dropZoneEnabled: false,//是否显示拖拽区域  
   /*  minImageWidth: 0, //图片的最小宽度  
    minImageHeight: 0,//图片的最小高度  
    maxImageWidth: 0,//图片的最大宽度  
    maxImageHeight: 0,//图片的最大高度   */
    maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小  
    minFileCount: 0,  
    maxFileCount:1, //表示允许同时上传的最大文件个数  
    enctype: 'multipart/form-data',  
    validateInitialCount:true,  
    previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",  
    msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",  

}).on('filepreupload', function(event, data, previewId, index) {     //上传中  
    var form = data.form, files = data.files, extra = data.extra,  
    response = data.response, reader = data.reader;  
    console.log('文件正在上传');  
}).on('fileerror', function(data) {  //一个文件上传失败  
	 var res = data.response;
     showMessage("文件上传失败");
})
.on("fileuploaded", function (data) {
	console.log(data);
		location.reload();
        showMessage("文件上传成功");       
    }); 
</script>  