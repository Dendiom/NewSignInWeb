<%@ page import="com.lucky.NewSignInWeb.constant.Constants" %>
<%@ page import="com.lucky.NewSignInWeb.bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>签到页面</title>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
        }

        html, body {
            height: 100%;
            width: 100%;
        }
    </style>
</head>
<body>
<div class="banner" style="background-color: white;width:100%;height: 15%">
    <div class="info" style="text-align: right;margin-top: 5px;margin-right: 8px;font-size: 15px">
        欢迎你：<%= ((User)session.getAttribute(Constants.SessionAttrs.USER)).getNickname() %>
        <a href="<%=request.getContextPath()%>/logout.do">  退出登录</a>
    </div>
    <div class="title" style="text-align: center;font-size: 25px;color: #6b635e">
        <p>欢迎使用此签到系统</p>
    </div>
    <div class="button" style="width:100%;text-align: center;font-size: 18px;margin-top: 5px">
        <a style="text-align: left" target="main" href="<%=request.getContextPath()%>/main/count.do?method=get">查看统计</a>
        <a style="text-align: right" target="main" href="<%=request.getContextPath()%>/main/rank.do">查看排名</a>
    </div>

</div>
<div class="line" style="height: 2px;width: 100%;background-color: black"></div>
<iframe name="main" src= "<%=request.getContextPath()%>/main/count.do" style="background-color: white;width: 100%;height: 85%;"></iframe>
</body>

</html>
