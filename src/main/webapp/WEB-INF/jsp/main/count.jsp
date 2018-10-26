<%@ page import="com.lucky.NewSignInWeb.bean.Result" %>
<%@ page import="com.lucky.NewSignInWeb.constant.Constants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        table {
            table-layout: fixed;
        }
        th {
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
        String[] week = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
        pageContext.setAttribute("week", week);
    %>
</div>
<div style="text-align: center">
    <table width="850px" border="1" cellspacing="2px" cellpadding="5px" style="margin: auto;margin-top: 40px">
        <tr>
            <th>时间</th>
            <th colspan="2">上午</th>
            <th colspan="2">下午</th>
            <th colspan="2">晚上</th>
        </tr>
        <c:forEach items="${sessionScope.record}" var="record" varStatus="status">
            <tr>
                <th>${week[status.index]}</th>
                <th>${fn:substring(record.in_time_mor, 10, 16)}</th>
                <th>${fn:substring(record.out_time_mor, 10, 16)}</th>
                <th>${fn:substring(record.in_time_noon, 10, 16)}</th>
                <th>${fn:substring(record.out_time_noon, 10, 16)}</th>
                <th>${fn:substring(record.in_time_eve, 10, 16)}</th>
                <th>${fn:substring(record.out_time_eve, 10, 16)}</th>
            </tr>
        </c:forEach>
        <tr>
            <th>总和</th>
            <th colspan="6"><%=session.getAttribute(Constants.SessionAttrs.TOTAL_TIME)%> 小时</th>
        </tr>

    </table>
</div>
<div class="button" style="width:100%;text-align: center;font-size: 18px;margin-top: 20px">
    <a style="text-align: left" target="main" href="<%=request.getContextPath()%>/main/count.do?method=sign_in">签到</a>
    <a style="text-align: right" target="main" href="<%=request.getContextPath()%>/main/count.do?method=sign_out">签出</a>
</div>
</body>
</html>
