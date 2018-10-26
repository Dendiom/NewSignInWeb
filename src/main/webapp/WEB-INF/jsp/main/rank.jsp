<%@ page import="com.lucky.NewSignInWeb.bean.Result" %>
<%@ page import="com.lucky.NewSignInWeb.constant.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        th, td {
            text-align: center;
        }
    </style>
</head>
<body>

<div style="text-align: center">
    <%
        Result result = (Result) request.getAttribute(Constants.ReqAttrs.ERROR);
        if (result != null) {
            out.println(result.getObj());
        }
    %>
</div>
<div style="text-align: center">
    <table width="400px" border="1" cellspacing="2px" cellpadding="5px" style="margin: auto;margin-top: 20px">
        <tr>
            <th>姓名</th>
            <th>年级</th>
            <th>时间</th>
        </tr>

        <c:forEach items="${requestScope.rank}" var="rank">
            <tr>
                <th>${rank.nickname}</th>
                <th>${rank.grade}</th>
                <th><fmt:formatNumber type="number" value="${rank.count/(60 * 60)}" maxFractionDigits="1"/></th>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
