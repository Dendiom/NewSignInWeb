<%@ page import="com.lucky.NewSignInWeb.bean.Result" %>
<%@ page import="com.lucky.NewSignInWeb.constant.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="../../css/register.css">
    <script language="JavaScript" type="text/javascript" src="../../js/inputCheck.js"></script>
</head>
<body bgcolor="#ffffff">
<div class="wrapper">
    <form class="form-signin" action="register.do" method="post" style=""
          onsubmit="return registerCheck(document.getElementById('username').value,
          document.getElementById('password').value,
          document.getElementById('passwordAgain').value)">
        <h1 align="center">用户注册</h1>
        <div>
            <%
                Result result = (Result) request.getAttribute(Constants.ReqAttrs.ERROR);
                if (result != null) {
                    out.println(result.getObj());
                }
            %>
        </div>
        <input class="form-input" type="text" name="username" id="username" placeholder="请输入5~15位用户名" required="" autofocus="" /><br/>
        <input class="form-input" type="password" name="password" id="password" placeholder="请输入密码" required=""/><br/>
        <input class="form-input" type="password" name="password_again" id="passwordAgain" placeholder="请再次输入密码" required=""/><br/>
        <button class="form-button" type="submit">注册</button>
    </form>
</div>
</body>
</html>