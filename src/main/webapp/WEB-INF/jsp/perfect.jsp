<%@ page import="com.lucky.NewSignInWeb.bean.Result" %>
<%@ page import="com.lucky.NewSignInWeb.constant.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>完善用户信息</title>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
        }

        html, body {
            height: 100%;
            width: 100%;
        }

        .main-form {
            position: absolute;
            width: 600px;
            height: 450px;
            text-align: center;
            top: 8%;
            left: 50%;
            margin-left: -300px;
            border: 2px solid gray;
        }

        .form-button {
            width: 240px;
            height: 50px;
            border: 1px solid black;
            background-color: white;
            margin-top: 20px;
            font-size: 20px;
            color: black;
        }

        .div-style {
            margin-top: 25px;
            font-size: 19px;
            text-align: left;
        }

        .span-left {
            margin-left: 180px;
            vertical-align: top;
        }

        .input-style {
            font-size: 17px;
        }
    </style>
</head>
<body>
<div class="main-form">
    <div>
        <%
            Result result = (Result) request.getAttribute(Constants.ReqAttrs.ERROR);
            if (result != null) {
                out.println(result.getObj());
            }
        %>
    </div>
    <form action="perfect.do" method="post">
        <div class="div-style" id="nickname">
            <label>
                <span class="span-left">姓名：</span>
                <input class="input-style" type="text" name="nickname" placeholder="必填" required="required"/>
            </label>
        </div>
        <div class="div-style" id="grade">
            <label>
                <span class="span-left">年级：</span>
                <select class="input-style" name="grade">
                    <option value="大四" selected>大四</option>
                    <option value="研一" >研一</option>
                    <option value="研二" >研二</option>
                    <option value="研三" >研三</option>
                    <option value="博士" >博士</option>
                </select>
            </label>
        </div>
        <div class="div-style" id="sex">
            <label>
                <span class="span-left">性别：</span>
                <input type="radio" name="sex" checked="checked" value="1">男
                <input type="radio" name="sex" value="0">女
            </label>
        </div>
        <div class="div-style" id="phone">
            <label>
                <span class="span-left">电话：</span>
                <input class="input-style" type="text" placeholder="非必填" name="phone"/>
            </label>
        </div>
        <div class="div-style" id="mail">
            <label>
                <span class="span-left">邮箱：</span>
                <input class="input-style" type="text" placeholder="非必填" name="mail"/>
            </label>
        </div>
        <div class="div-style" id="description">
            <label>
                <span class="span-left">描述：</span>
                <textarea class="input-style" rows="3" placeholder="非必填" name="description"></textarea>
            </label>
        </div>
        <input class="form-button" type="submit"/>
    </form>
</div>
</body>
</html>
