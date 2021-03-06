<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ include file="/webpage/include/head.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>毕业设计管理系统-教师</title>

    <meta name="keywords" content="毕业设计管理系统">
    <meta name="description" content="毕业设计管理系统">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <span><img alt="image" class="img-circle" src="${ctxStatic }/images/userinfo.jpg" /></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold">${sessionScope.Tea_user.name }</strong></span>
                                <span class="text-muted text-xs block">${sessionScope.Tea_user.id }<b class="caret"></b></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                              
                                <li><a class="J_menuItem" href="${ctx }/teacher/infomation/list?t_id=${sessionScope.Tea_user.id }">个人资料</a>
                                <li class="divider"></li>
                                <li><a href="${ctx }/login/loginout">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element">${sessionScope.Stu_user.name }
                        </div>
                    </li>
                    
                   <%--  <li>
                        <a class="J_menuItem" href="${ctx }/teacher/infomation/list?t_id=${sessionScope.Tea_user.id }"><i class="fa fa-columns"></i> <span class="nav-label">个人信息</span></a>
                    </li> --%>
                   
                    <li>
                        <a href="#">
                            <i class="fa fa-edit"></i>
                            <span class="nav-label">我的毕设</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                       		 <li>
                              <a class="J_menuItem" href="${ctx }/teacher/infomation/list?t_id=${sessionScope.Tea_user.id }" data-index="0">个人信息</a>
                             </li>
                             <li>
                              <a class="J_menuItem" href="${ctx }/teacher/subject/list?tid=${sessionScope.Tea_user.id }"  data-index="0">我的题目</a>
                             </li>
                             <li>
                              <a class="J_menuItem" href="${ctx }/teacher/mystudent/list?tid=${sessionScope.Tea_user.id }"   data-index="0">我的学生</a>
                             </li>
                             <%-- <li>
                              <a class="J_menuItem" href="${ctx }/teacher/downloadstufile/list?tid=${sessionScope.Tea_user.id }"   data-index="0">学生论文下载</a>
                             </li>--%>
                             
                        </ul>

                    </li>
                    
                    <li>
                        <a href="#">
                            <i class="fa fa-cutlery"></i> 
                            <span class="nav-label">选择学生</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                           <li>
                              <a class="J_menuItem" href="${ctx }/teacher/choosestu/list?tid=${sessionScope.Tea_user.id }&period=3"   data-index="0">选择第一志愿学生</a>
                             </li>
                             <li>
                              <a class="J_menuItem" href="${ctx }/teacher/choosestu/list?tid=${sessionScope.Tea_user.id }&period=4"   data-index="0">选择第二志愿学生</a>
                             </li>
                             <li>
                              <a class="J_menuItem" href="${ctx }/teacher/choosestu/list?tid=${sessionScope.Tea_user.id }&period=5"   data-index="0">选择第三志愿学生</a>
                             </li>
                              
                             <li>
                              <a class="J_menuItem" href="${ctx }/teacher/choosestuactive/stulist?period=6"   data-index="0">选择学生</a>
                             </li>
                        </ul>

                    </li>
                     <li>
                        <a href="#">
                            <i class="fa fa-edit"></i>
                            <span class="nav-label">公告箱</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                               <li>
                                <a class="J_menuItem" href="${ctx }/affiche/user/list" data-index="0">公告查看</a>
                            </li>
                            
                        </ul>

                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa-edit"></i>
                            <span class="nav-label">统计分析</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            
                            <li>
                                <a class="J_menuItem" href="${ctx }/analysis/teacher" data-index="0">导师分析</a>
                            </li>
                             <li>
                                <a class="J_menuItem" href="${ctx }/analysis/classs" data-index="0">班级分析</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="${ctx }/analysis/student" data-index="0">学生分析</a>
                            </li>
                        </ul>

                    </li>
                  
                   

                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                        <form role="search" class="navbar-form-custom" method="post" action="http://www.baidu.com">
                            <div class="form-group">
                                <input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search" id="top-search">
                            </div>
                        </form>
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li class="dropdown hidden-xs">
                            <a class="right-sidebar-toggle" aria-expanded="false">
                                <i class="fa fa-tasks"></i> 主题
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="index_v1.html">首页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="${ctx }/login/loginout" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${ctx }/analysis/teacher" frameborder="0" data-id="https://www.baidu.com" seamless></iframe>
            </div>
            <div class="footer">
                <div class="pull-right">&copy; 2017-XXXX(HB) <a href="https://user.qzone.qq.com/546889070?ptlang=2052" target="_blank">毕业设计管理系统</a>
                </div>
            </div>
        </div>
        <!--右侧部分结束-->
        <!--右侧边栏开始-->
        <div id="right-sidebar">
            <div class="sidebar-container">

                <ul class="nav nav-tabs navs-3">

                    <li class="active">
                        <a data-toggle="tab" href="#tab-1">
                            <i class="fa fa-gear"></i> 主题
                        </a>
                    </li>
                   
                </ul>

                <div class="tab-content">
                    <div id="tab-1" class="tab-pane active">
                        <div class="sidebar-title">
                            <h3> <i class="fa fa-comments-o"></i> 主题设置</h3>
                            <small><i class="fa fa-tim"></i> 你可以从这里选择和预览主题的布局和样式，这些设置会被保存在本地，下次打开的时候会直接应用这些设置。</small>
                        </div>
                        <div class="skin-setttings">
                            <div class="title">主题设置</div>
                            <div class="setings-item">
                                <span>收起左侧菜单</span>
                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="collapsemenu">
                                        <label class="onoffswitch-label" for="collapsemenu">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="setings-item">
                                <span>
                        固定宽度
                    </span>

                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="boxedlayout" class="onoffswitch-checkbox" id="boxedlayout">
                                        <label class="onoffswitch-label" for="boxedlayout">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="title">皮肤选择</div>
                            <div class="setings-item default-skin nb">
                                <span class="skin-name ">
                         <a href="#" class="s-skin-0">
                             默认皮肤
                         </a>
                    </span>
                            </div>
                            <div class="setings-item blue-skin nb">
                                <span class="skin-name ">
                        <a href="#" class="s-skin-1">
                            蓝色主题
                        </a>
                    </span>
                            </div>
                            <div class="setings-item yellow-skin nb">
                                <span class="skin-name ">
                        <a href="#" class="s-skin-3">
                            黄色/紫色主题
                        </a>
                    </span>
                            </div>
                        </div>
                    </div>

            </div>
        </div>
        <!--右侧边栏结束-->

</body>
</html>
</html>