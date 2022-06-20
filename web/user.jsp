<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2022/4/7
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>员工搜索</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <%@ include file="bootstrap.jsp" %>
    <link rel="stylesheet" href="css/common.css">
    <script src="js/user.js"></script>
<body>
<div id="main">
    <%@ include file="head.jsp" %>
    <%@ include file="menu.jsp" %>
    <div class="right-content">
        <div style="margin:10px;">
            <ol class="breadcrumb">
                <li><a href="#">人员管理</a></li>
                <li class="active">员工搜索</li>
            </ol>
        </div>
        <h3 style="margin: 10px">搜索员工</h3>
        <div style="width: 80%;height: 20%;margin-left: 10%;border: 1px solid #F0F0F0; text-align: center">
            <span style="margin-right: 30px"> 姓名:<input type="text" id="userName"></span>
            <span style="margin-right: 30px">账号名:<input type="text" id="userId"></span>
            <select id="select">
                <option id="pinzhun">已批准</option>
                <option id="shenpin">待审批</option>
                <option id="close">已关闭</option>
            </select><br>
            <br>
            <br>
            <button onclick="queryUser($('#userName').val(),$('#userId').val(),$('#select').val())" style="margin-right: 60px">查询</button>
            <button onclick="cleary()">重置</button>

        </div>
        <div style="clear: left; position: relative;margin: 10px;">
            <div class="panel panel-default">
                <div class="panel-body table-responsive">
                    <table class="table table-hover table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>姓名</th>
                            <th>账号名</th>
                            <th>联系电话</th>
                            <th>电子邮件</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${userList}" var="u">
                            <tr>
                                <td>${u.userName}</td>
                                <td>${u.userId}</td>
                                <td>${u.tel}</td>
                                <td>${u.email}</td>
                                <td>
                                    <button type="button" class="btn btn-info" onclick="userClose('${u.userId}')">关闭账号</button> &nbsp;
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div style="margin-top: 15px; text-align: center;">
                        <button type="button" class="btn" onclick="queryList(1)">首页</button>
                        <button type="button" class="btn" onclick="queryList(${pageUtil.pageNum-1})">上一页</button>
                        <button type="button" class="btn" onclick="queryList(${pageUtil.pageNum+1})">下一页</button>
                        <button type="button" class="btn" onclick="queryList(${pageUtil.totalPage})">末页</button>
                        &nbsp;共 ${pageUtil.totalCount} 条 当前 ${pageUtil.pageNum}/${pageUtil.totalPage} 页
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
