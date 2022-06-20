<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2022/4/7
  Time: 14:53
  注册审批
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>注册审批</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <%@ include file="bootstrap.jsp" %>
    <link rel="stylesheet" href="css/common.css">
    <script src="js/userPass.js"></script>
<body>
<div id="main">
    <%@ include file="head.jsp"%>
    <%@ include file="menu.jsp"%>

    <div class="right-content">
        <div style="margin:10px;">
            <ol class="breadcrumb">
                <li><a href="#">人员管理</a></li>
                <li class="active">注册审批</li>
            </ol>
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
                                    <button type="button" class="btn btn-info" onclick="pass('${u.userId}')">通过</button> &nbsp;
                                    <button type="button" class="btn btn-warning" onclick="remove('${u.userId}')">删除</button>
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
