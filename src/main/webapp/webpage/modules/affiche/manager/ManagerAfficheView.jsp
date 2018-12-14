<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/affiche.jsp"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>查看公告</title>
    <meta name="keywords" content="查看公告">
    <meta name="description" content="公告的查看以及邮件的查看页面">


</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
     
            <div class="col-sm-12 animated fadeInRight">
                <div class="mail-box-header">
                    
                    <h2> 查看邮件</h2>
                    <div class="mail-tools tooltip-demo m-t-md">
                        <h3><span class="font-noraml">主题： ${affiche.a_title }</span></h3>
                    </div>
                </div>
                <div class="mail-box">


                    <div class="mail-body">
                        ${affiche.a_text }
                    </div>
                    <div class="mail-attachment">
                      

                        <%-- <div class="attachment">
                         <c:forEach items="${files}" var="file">
                            <div class="file-box">
                                <div class="file">
                                    <a href="mail_detail.html#">
                                        <span class="corner"></span>

                                        <div class="icon">
                                            <i class="fa fa-file"></i>
                                        </div>
                                        <div class="file-name">
                                            ${file }
                                        </div>
                                    </a>
                                </div>
						</c:forEach>
                            </div> --%>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                   <!--  <div class="mail-body text-right tooltip-demo">
                        <a class="btn btn-sm btn-white" href="#" onclick="callback()"><i class="fa fa-print"></i> 返回</a>      
                    </div> -->
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
  
    <script>
       /* $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});*/
        function callback(){
        
        	window.location.href="${ctx}/affiche/manager/list";
        }
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>
</html>
