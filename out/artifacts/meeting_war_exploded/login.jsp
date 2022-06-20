<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2022/4/7
  Time: 9:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title></title>
    <%@ include file="bootstrap.jsp"%>
    <link rel="stylesheet" href="css/login.css">
    <script src="js/login.js"></script>
</head>
<body>
<div id="login">
    <form>
        <div class="input-group">
            <span class="input-group-addon">账号</span> <input type="text" id="userId" class="form-control" placeholder="输入账号">
        </div>
        <div class="input-group" style="margin-top: 20px;">
            <span class="input-group-addon">密码</span> <input type="password" id="password" class="form-control" placeholder="输入密码">
        </div>
        <div class="input-group" style="margin-top: 20px;">
            <span class="input-group-addon">验证</span>
            <input type="text" id="code"  class="form-control" placeholder="输入验证码" style="width: 40%;">
            <img id="img" src="img" style="width: 40%;height: 34px;">
            <input type="button" class="btn btn-info" value="刷新" onclick="refresh()"/>
        </div>
        <div class="btn-group" style="margin-top: 20px;">
            <input type="button" class="btn btn-success" value="登录" style="width: 360px;" onclick="login()">
        </div>
        <div class="btn-group" style="margin-top: 20px;">
            <input type="button" class="btn btn-info" value="注册" style="width: 360px;" onclick="reg()">
        </div>
    </form>
</div>
<%@ include file="copyright.jsp"%>
</body>
</html>
