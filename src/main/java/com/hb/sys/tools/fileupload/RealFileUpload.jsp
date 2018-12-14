<%--
  Created by IntelliJ IDEA.
  User: wangf
  Date: 2017-10-26
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp" %>
<html lang="en">
<head>
    <meta name="description"
          content="File Upload widget with multiple file selection, drag&amp;drop support, progress bars, validation and preview images, audio and video for jQuery. Supports cross-domain, chunked and resumable file uploads and client-side image resizing. Works with any server-side platform (PHP, Python, Ruby on Rails, Java, Node.js, Go etc.) that supports standard HTML form file uploads.">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%--<!-- Bootstrap styles -->--%>
    <%--<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">--%>
    <!-- Bootstrap styles -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <!-- Generic page styles -->
    <link rel="stylesheet" href="${ctxStatic}/wf/jquery-fileupload/css/style.css">

    <!-- jQuery UI styles -->
    <link rel="stylesheet" href="http://cdn.bootcss.com/jqueryui/1.11.3/jquery-ui.min.css" id="theme">
    <!-- blueimp Gallery styles -->
    <link rel="stylesheet" href="http://blueimp.github.io/Gallery/css/blueimp-gallery.min.css">
    <!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
    <link rel="stylesheet" href="${ctxStatic}/jquery-fileupload/css/jquery.fileupload.css">
    <link rel="stylesheet" href="${ctxStatic}/jquery-fileupload/css/jquery.fileupload-ui.css">

    <script type="text/javascript">
        //全局变量，js获得el表达式值
        <%--var delete = "${delete}";--%>
        var parentId = "${parentInputId}";
        var Posturl = "${ctx}" + "/wf/fileupload/Realfileupload/save";

        //        var hosturl_s = window.document.location.host;
        //父页面input传值，关闭子页面并打开父页面
        function Closepage() {
            <%--alert(${pageContext.request.contextPath});--%>
            //打开父业面并关闭子页面
            if (window.opener && !window.opener.closed) {
                window.location.href = window.parent.opener.location.href;
//                window.opener.location.reload();
//                window.parent.dialogArguments.document.execCommand('Refresh');
            }
            window.close();
            return false;
        }

        //判断文件是否是照片
        function isPhoto(type) {
            var photo = /(\.|\/)(gif|jpe?g|png)$/i;
            return photo.test(type);
        }
    </script>
</head>
<body>


<!-- The file upload form used as target for the file upload widget -->
<div class="container">
    <form id="fileupload" action="" method="POST" enctype="multipart/form-data">
        <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
        <input id="id" name="id" type="hidden" value="${accidentHandleInfo.id}"/>
        <input id="${parentInputId}" name="${parentInputId}" type="hidden"/>
        <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
        <div class="row fileupload-buttonbar">
            <div class="col-lg-7">
                <!-- The fileinput-button span is used to style the file input field as button -->
                <span class="btn btn-success fileinput-button">
                    <i class="glyphicon glyphicon-plus"></i>
                    <span>Add files...</span>
                    <input type="file" name="files[]" multiple>
                </span>
                <button type="submit" class="btn btn-primary start">
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>Start upload</span>
                </button>
                <button type="reset" class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel upload</span>
                </button>
                <button type="button" class="btn btn-danger delete">
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>Delete</span>
                </button>
                <button type="button" class="btn btn-primary" onclick="Closepage()">
                    <i class="glyphicon glyphicon-ok"></i>
                    <span>Save</span>
                </button>
                <input type="checkbox" class="toggle">
                <!-- The global file processing state -->
                <span class="fileupload-process"></span>
            </div>
            <!-- The global progress state -->
            <div class="col-lg-5 fileupload-progress fade">
                <!-- The global progress bar -->
                <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
                    <div class="progress-bar progress-bar-success" style="width:0%;"></div>
                </div>
                <!-- The extended global progress state -->
                <div class="progress-extended">&nbsp;</div>
            </div>
        </div>
        <!-- The table listing the files available for upload/download -->
        <table role="presentation" class="table table-striped">
            <tbody class="files"></tbody>
        </table>

    </form>
</div>
<br>
<!-- The blueimp Gallery widget -->
<div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls" data-filter=":even">
    <div class="slides"></div>
    <h3 class="title"></h3>
    <a class="prev">‹</a>
    <a class="next">›</a>
    <a class="close">×</a>
    <a class="play-pause"></a>
    <ol class="indicator"></ol>
</div>
<!-- The template to display files available for upload -->
<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td>
            <span class="preview"></span>
        </td>
        <td>
            <p class="name">{%=file.name%}</p>

        </td>
        <td>
            <p class="size">Processing...</p>

        </td>
        <td>
            {% if (!i && !o.options.autoUpload) { %}
                <button class="start" disabled>START</button>
            {% } %}
            {% if (!i) { %}
                <button class="cancel">CANCEL</button>
            {% } %}
        </td>
    </tr>
{% } %}

</script>
<!-- The template to display files available for download -->
<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        <td>
            <span class="preview">
                {% if (isPhoto(file.type)) { %}
                    <a href="${realPath}${pageContext.request.contextPath}{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery>
                    <img src="${realPath}${pageContext.request.contextPath}{%=file.thumbnailUrl%}" style="width:130px;height:60px">
                    </a>
                {% } %}
            </span>
        </td>
        <td>
            <p class="name">
                {% if (file.url) { %}
                    <a href="${realPath}${pageContext.request.contextPath}{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" >{%=file.name%}</a>
                {% } else { %}
                    <span>{%=file.name%}</span>
                {% } %}
            </p>
            {% if (file.error) { %}
                <div><span class="label label-danger">Error</span> {%=file.error%}</div>
            {% } %}
        </td>
        <td>
            <span class="size">{%=o.formatFileSize(file.size)%}</span>
        </td>
        <td>
            {% if (file.deleteUrl) { %}
                <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="${ctx}/wf/fileupload/Realfileupload{%=file.deleteUrl%}?FileName={%=file.url%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>Delete</span>
                </button>
                <input type="checkbox" name="delete" value="1" class="toggle">
            {% } else { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}

</script>
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/jqueryui/1.11.3/jquery-ui.min.js"></script>
<!-- The jQuery UI widget factory, can be omitted if jQuery UI is already included -->
<script src="${ctxStatic}/wf/jquery-fileupload/js/vendor/jquery.ui.widget.js"></script>
<!-- The Templates plugin is included to render the upload/download listings -->
<script src="http:////blueimp.github.io/JavaScript-Templates/js/tmpl.min.js"></script>
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script src="http:////blueimp.github.io/JavaScript-Load-Image/js/load-image.all.min.js"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<script src="http:////blueimp.github.io/JavaScript-Canvas-to-Blob/js/canvas-to-blob.min.js"></script>
<!-- Bootstrap JS is not required, but included for the responsive demo navigation -->
<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<!-- blueimp Gallery script -->
<script src="http:////blueimp.github.io/Gallery/js/jquery.blueimp-gallery.min.js"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="${ctxStatic}/jquery-fileupload/js/jquery.iframe-transport.js"></script>
<!-- The basic File Upload plugin -->
<script src="${ctxStatic}/jquery-fileupload/js/jquery.fileupload.js"></script>
<!-- The File Upload processing plugin -->
<script src="${ctxStatic}/jquery-fileupload/js/jquery.fileupload-process.js"></script>
<!-- The File Upload image preview & resize plugin -->
<script src="${ctxStatic}/jquery-fileupload/js/jquery.fileupload-image.js"></script>
<!-- The File Upload audio preview plugin -->
<script src="${ctxStatic}/jquery-fileupload/js/jquery.fileupload-audio.js"></script>
<!-- The File Upload video preview plugin -->
<script src="${ctxStatic}/jquery-fileupload/js/jquery.fileupload-video.js"></script>
<!-- The File Upload validation plugin -->
<script src="${ctxStatic}/jquery-fileupload/js/jquery.fileupload-validate.js"></script>
<!-- The File Upload user interface plugin -->
<script src="${ctxStatic}/jquery-fileupload/js/jquery.fileupload-ui.js"></script>
<!-- The File Upload jQuery UI plugin -->
<%--<script src="${ctxStatic}/jquery-fileupload/js/jquery.fileupload-jquery-ui.js"></script>--%>
<!-- The main application script -->
<script src="${ctxStatic}/wf/jquery-fileupload/js/main.js"></script>
<%--<script src="${ctxStatic}/jquery-fileupload/js/jquery.ui.widget.js"></script>--%>
<!--[if (gte IE 8)&(lt IE 10)]>
<script src="${ctxStatic}/wf/jquery-fileupload/js/cors/jquery.xdr-transport.js"></script>
<![endif]-->
</body>
</html>
