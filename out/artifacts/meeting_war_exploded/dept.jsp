<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2022/4/7
  Time: 14:53
 部门管理页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>我的预订</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <%@ include file="bootstrap.jsp" %>
    <link rel="stylesheet" href="css/common.css">
    <script src="js/dept.js"></script>
<body>
<div id="main">
    <%@ include file="head.jsp"%>
    <%@ include file="menu.jsp"%>

    <div class="right-content">
        <div style="margin:10px;">
            <ol class="breadcrumb">
                <li><a href="#">人员管理</a></li>
                <li class="active">部门管理</li>
            </ol>
        </div>
        <div>
           <button type="button" class="btn btn-info" data-toggle="modal" data-target="#addModal" style="margin: 10px">新增</button>
        </div>
        <div style="clear: left; position: relative;margin: 10px;">
            <div class="panel panel-default">
                <div class="panel-body table-responsive">
                    <table class="table table-hover table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>部门编号</th>
                            <th>部门名称</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${deptList}" var="d">
                            <tr>
                                <td>${d.deptId}</td>
                                <td>${d.deptName}</td>
                                <td>
                                    <button type="button" class="btn btn-info" onclick="edit('${d.deptId}')">编辑</button> &nbsp;
                                    <button type="button" class="btn btn-warning" onclick="remove('${d.deptId}')">删除</button>
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
<!-- 编辑员工弹窗 -->
<div class="modal fade" id="editModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">部门编辑</h4>
            </div>
            <div class="modal-body">
                <form  class="form-horizontal" role="form" >
                    <div class="form-group">
                        <label class="col-sm-3 control-label">部门编号</label>
                        <div class="col-sm-9">
                            <input type="text" id="deptid" class="form-control" placeholder="部门编号" readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">部门名称</label>
                        <div class="col-sm-9">
                            <input type="text" id="deptname" class="form-control" placeholder="部门名称">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="saveEdit()">保存</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- 新增员工弹窗 -->
<div class="modal fade" id="addModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">部门编辑</h4>
            </div>
            <div class="modal-body">
                <form  class="form-horizontal" role="form" >
                    <div class="form-group">
                        <label class="col-sm-3 control-label">部门名称</label>
                        <div class="col-sm-9">
                            <input type="text" id="addName" class="form-control" placeholder="部门名称">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="insert()">保存</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
</body>
</html>
