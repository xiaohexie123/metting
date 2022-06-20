<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2022/4/7
  Time: 14:52
 我的会议
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>会议管理</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <%@ include file="bootstrap.jsp" %>
    <link rel="stylesheet" href="css/common.css">
    <script src="js/myMeeting.js"></script>
    <script src="My97DatePicker/WdatePicker.js"></script>
<body>
<div id="main">
    <%@ include file="head.jsp" %>
    <%@ include file="menu.jsp" %>

    <div class="right-content">
        <div style="margin:10px;">
            <ol class="breadcrumb">
                <li><a href="#">会议预定</a></li>
                <li class="active">我的会议</li>
            </ol>
        </div>



        <h3>我将参加的会议:</h3>
        <div style="clear: left; position: relative;margin: 10px; margin-top: 50px">
            <div class="panel panel-default">
                <div class="panel-body table-responsive">
                    <table class="table table-hover table-bordered table-striped">
                        <thead>
                        <tr>
                            <th>会议名称</th>
                            <th>会议室名称</th>
                            <th>会议开始时间</th>
                            <th>会议结束时间</th>
                            <th>会议预定时间</th>
                            <th>预订者</th>
                            <th>操作</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${meetingList}" var="m">
                            <tr>
                                <td>${m.meetingName}</td>
                                <td>${m.room.roomName}</td>
                                <td>${m.startTime}</td>
                                <td>${m.endTime}</td>
                                <td>${m.orderTime}</td>
                                <td>${m.orderUserId}</td>
                                <td>
                                    <button type="button" class="btn btn-info" onclick="seeMetting('${m.meetingId}')">
                                        查看详情
                                    </button> &nbsp;
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
        <input type="hidden" id="LoginUser" value="${loginUser.userName}">
    </div>
</div>

<%--查看详情模态框--%>
<div class="modal fade" id="seeMettingModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">会议预定</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">会议名称</label>
                        <div class="col-sm-9">
                            <input type="text" id="mettingName" class="form-control" placeholder="会议名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">预计参加人数</label>
                        <div class="col-sm-9">
                            <input type="text" id="peopleNums" class="form-control" placeholder="参加人数">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">预计开始时间</label>
                        <div class="col-sm-9">
                            <input type="text" id="starTime" class="form-control" placeholder="预计开始时间">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">预计结束时间</label>
                        <div class="col-sm-9">
                            <input type="text" id="endTime" class="form-control" placeholder="预计结束时间">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">会议说明</label>
                        <div class="col-sm-9">
                            <textarea id="meetingMarks" class="form-control" placeholder="会议说明"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">参会人员</label>
                        <div class="col-sm-9">
                            <div style="clear: left; position: relative;margin: 10px; margin-top: 50px">
                                <div class="panel panel-default">
                                    <div class="panel-body table-responsive">
                                        <table class="table table-hover table-bordered table-striped">
                                            <thead>
                                            <tr>
                                                <th>会议名称</th>
                                                <th>会议室名称</th>
                                                <th>会议开始时间</th>

                                            </tr>
                                            </thead>
                                            <tbody id="tbody">

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
</body>
</html>
