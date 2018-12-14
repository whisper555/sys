<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/affiche.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>发布公告</title> 
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">     
            <div class="col-sm-12 animated fadeInRight">
                <div class="mail-box-header">            
                    <h2>
                   		编辑公告
                </h2>
                </div>
                <div class="mail-box">
                    <div class="mail-body">
                        <form:form class="form-horizontal" action="${ctx }/affiche/manager/save" modelAttribute="affiche" method="get">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">标题：</label>
                                <div class="col-sm-9">
                                    <form:input path="a_title" type="text" class="form-control" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">日期：</label>
                                <div class="col-sm-10">         
                                     <form:input path="a_date" class="form-control layer-date" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"/>
                                     <label class="laydate-icon"></label> 
                                </div>
                            </div>
                             <form:textarea path="a_text" rows="20" cols="180"/>
                        
                    </div>

                   <!--  <div class="mail-text h-200">
						
                        <div class="summernote">
                       		
                        </div>
                       
                        <div class="clearfix"></div>
                    </div> -->
                    <div class="mail-body text-right tooltip-demo">
                        <button type="submit" class="btn btn-sm btn-primary" data-toggle="tooltip" data-placement="top" title="Send"><i class="fa fa-reply"></i> 发布</button>
                        <a href="${ctx }/affiche/manager/list" class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="top" title="Discard email"><i class="fa fa-times"></i> 放弃</a>
                    </div>
                    <div class="clearfix"></div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
   
    <script>
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",});$(".summernote").summernote({lang:"zh-CN"})});var edit=function(){$(".click2edit").summernote({focus:true})};var save=function(){var aHTML=$(".click2edit").code();$(".click2edit").destroy()};
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/mail_compose.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:15 GMT -->
</html>
