<%@ page import="com.lucky.NewSignInWeb.bean.Result" %>
<%@ page import="com.lucky.NewSignInWeb.constant.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="../../css/login.css">
    <script language="JavaScript" type="text/javascript" src="../../js/inputCheck.js"></script>
</head>
<body bgcolor="#ffffff">

<div class="wrapper">
    <form class="form-signin" action="login.do" method="post"
          onsubmit="return usernameCheck(document.getElementById('username').value)">
        <h1 align="center">用户登录</h1>
        <div>
            <%
                Result result = (Result) request.getAttribute(Constants.ReqAttrs.ERROR);
                if (result != null) {
                    out.println(result.getObj());
                }
            %>
        </div>
        <input class="form-input" type="text" name="username" id="username" placeholder="用户名" required="" autofocus=""/><br/>
        <input class="form-input" type="password" name="password" placeholder="密码" required=""/><br/>
        <label class="form-label">
            <input type="checkbox" value="1" id="rememberMe" name="rememberMe"> 记住登录状态
        </label><br/>
        <button class="form-button" type="submit">登录</button>
        <a class="form-label" href="register">还没有用户，点击此处注册</a>
    </form>
</div>

</body>
</html>
