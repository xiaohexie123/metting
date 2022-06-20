<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2022/4/7
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="js/head.js"></script>
<div class="top-head">
    <div id="logo"></div>
    <div id="title"></div>
    <div id="per-mes">
        您好，${loginUser.userName}
        <div class="btn-group">
        <button type="button" class="btn btn-default dropdown-toggle btn-sm" data-toggle="dropdown">
            设置<span class="caret"></span>
        </button>
        <ul class="dropdown-menu" role="menu">
            <li><a href="#" data-toggle="modal" data-target="#pwdModal">修改密码</a></li>
            <li class="divider"></li>
            <li><a href="#" onclick="logout()">退出系统</a></li>
        </ul>
    </div>
    </div>
</div>
<!-- 修改密码弹窗 -->
<div class="modal fade" id="pwdModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">修改密码</h4>
            </div>
            <div class="modal-body">
                <form  class="form-horizontal" role="form" >
                    <div class="form-group">
                        <label class="col-sm-3 control-label">输入原密码</label>
                        <div class="col-sm-9">
                            <input type="password" id="oldPassword" class="form-control" placeholder="输入密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">输入新密码</label>
                        <div class="col-sm-9">
                            <input type="password" id="password" class="form-control" placeholder="输入密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">确认新密码</label>
                        <div class="col-sm-9">
                            <input type="password" id="rePassword" class="form-control" placeholder="输入密码">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="pwd()">保存</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>