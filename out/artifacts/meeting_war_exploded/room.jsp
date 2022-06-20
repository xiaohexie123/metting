<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2022/4/7
  Time: 14:54
  会议室管理
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>会议预定</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <%@ include file="bootstrap.jsp" %>
    <link rel="stylesheet" href="css/common.css">
    <script src="js/room.js"></script>
<body>
<div id="main">
    <%@ include file="head.jsp"%>
    <%@ include file="menu.jsp"%>

    <div class="right-content">
        <div style="margin:10px;">
            <ol class="breadcrumb">
                <li><a href="#">会议预定</a></li>
                <li class="active">会议室管理</li>
            </ol>
        </div>
        <h3 style="margin: 10px">所有会议室</h3>
        <div>
            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#addRoomModal" style="margin: 10px">新增</button>
        </div>
        <div style="clear: left; position: relative;margin: 10px;">
            <div class="panel panel-default">
                <div class="panel-body table-responsive">
                    <table class="table table-hover table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>门牌编号</th>
                            <th>部门名称</th>
                            <th>容纳人数</th>
                            <th>当前状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${roomList}" var="r">
                            <tr>
                                <td>${r.roomId}</td>
                                <td>${r.roomName}</td>
                                <td>${r.nums}</td>
                                <c:if test="${r.status==1}">
                                <td>启用</td>
                                </c:if>
                                <c:if test="${r.status==0}">
                                    <td>停用</td>
                                </c:if>
                                <c:if test="${r.status==2}">
                                    <td>删除</td>
                                </c:if>
                                <td>
                                    <button type="button" class="btn btn-info" onclick="seeRoom('${r.roomId}')">查看</button>
                                    <c:if test="${r.status==0}">
                                        <button type="button" class="btn btn-info" onclick="editRoom('${r.roomId}')">修改</button>
                                        <button type="button" class="btn btn-info" onclick="starRoom('${r.roomId}')">启用</button>
                                        <button type="button" class="btn btn-info" onclick="removeRoom('${r.roomId}')">删除</button>
                                    </c:if>
                                    <c:if test="${r.status==1}">
                                        <button type="button" class="btn btn-info" onclick="editRoom('${r.roomId}')">修改</button>
                                        <button type="button" class="btn btn-info" onclick="closeRoom('${r.roomId}')">停用</button>
                                        <button type="button" class="btn btn-info" onclick="removeRoom('${r.roomId}')">删除</button>
                                    </c:if>
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
<%--添加会议室--%>
<div class="modal fade" id="addRoomModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">添加会议室</h4>
            </div>
            <div class="modal-body">
                <form  class="form-horizontal" role="form" >
                    <div class="form-group">
                        <label class="col-sm-3 control-label">门牌号</label>
                        <div class="col-sm-9">
                            <input type="text" id="roomId" class="form-control" placeholder="例如:201">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">会议室名称</label>
                        <div class="col-sm-9">
                            <input type="text" id="roomName" class="form-control" placeholder="例如:第一会议室">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">最多容纳人数</label>
                        <div class="col-sm-9">
                            <input type="text" id="nums" class="form-control" placeholder="填写一个正整数">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">会议室名称</label>
                        <div class="col-sm-9">
                            <select id="select">
                                <option>启用</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">备注</label>
                        <div class="col-sm-9">
                            <textarea type="" id="marks" class="form-control" placeholder="200字以内文字描述"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary"
                        onclick="saveRoom($('#roomId').val(),$('#roomName').val(),$('#nums').val(),$('#marks').val())">保存</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<%--查看会议室--%>
<div class="modal fade" id="seeRoomModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">添加会议室</h4>
            </div>
            <div class="modal-body">
                <form  class="form-horizontal" role="form" >
                    <div class="form-group">
                        <label class="col-sm-3 control-label">门牌号</label>
                        <div class="col-sm-9">
                            <input type="text" id="seeroomId" class="form-control" placeholder="例如:201">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">会议室名称</label>
                        <div class="col-sm-9">
                            <input type="text" id="seeroomName" class="form-control" placeholder="例如:第一会议室">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">最多容纳人数</label>
                        <div class="col-sm-9">
                            <input type="text" id="seenums" class="form-control" placeholder="填写一个正整数">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">会议室状态</label>
                        <div class="col-sm-9">
                            <input type="text" id="seestatus" class="form-control" placeholder="状态">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">备注</label>
                        <div class="col-sm-9">
                            <textarea type="" id="seemarks" class="form-control" placeholder="200字以内文字描述"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-toggle="modal" data-target="#seeRoomModal">关闭</button>

        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
</div>

<%--修改会议室--%>
<div class="modal fade" id="editRoomModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">添加会议室</h4>
            </div>
            <div class="modal-body">
                <form  class="form-horizontal" role="form" >
                    <div class="form-group">
                        <label class="col-sm-3 control-label">门牌号</label>
                        <div class="col-sm-9">
                            <input type="text" id="editroomId" class="form-control" placeholder="例如:201" readonly="readonly">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">会议室名称</label>
                        <div class="col-sm-9">
                            <input type="text" id="editroomName" class="form-control" placeholder="例如:第一会议室">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">最多容纳人数</label>
                        <div class="col-sm-9">
                            <input type="text" id="editnums" class="form-control" placeholder="填写一个正整数">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">备注</label>
                        <div class="col-sm-9">
                            <textarea type="" id="editmarks" class="form-control" placeholder="200字以内文字描述"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-toggle="modal" data-target="#editRoomModal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="saveEditRoom()">保存</button>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
</div>

</body>
</html>
