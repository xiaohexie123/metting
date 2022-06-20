<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2022/4/7
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="left-menu">
    <!-- 侧边栏标签页 -->
    <ul class="nav nav-stacked">
        <!-- 侧边栏选项 -->
        <li role="presentation" class="active">
            <!-- 选项控制data-target对应的显隐 -->
            <a href="#" data-toggle="collapse" data-target="#list1">个人中心</a>
            <!-- 隐藏的详细菜单 -->
            <ul id="list1" class="collapse in">
                <li><a id="menuNotice" href="meeting?action=noticeMyMeeting&pageNums=1&pageSizes=2&pageNum=1&pageSize=2" >最新通知</a></li>
                <li><a id="menuMyOrder" href="meeting?action=myOrder&pageNum=1&pageSize=10">我的预订</a></li>
                <li><a id="menuMyMeeting" href="meeting?action=menuMyMeeting&loginUser=${loginUser.userName}&pageNum=1&pageSize=10">我的会议</a></li>
            </ul>
        </li>
        <c:if test="${loginUser.userType==1}">
        <li role="presentation"><a href="#" data-toggle="collapse" data-target="#list2">人员管理</a>
            <ul id="list2" class="collapse in">
                <li><a id="menuDept" href="dept?action=dept&pageNum=1&pageSize=10">部门管理</a></li>
                <li><a id="menuUserPass" href="user?action=userPass&pageNum=1&pageSize=10">注册审批</a></li>
                <li><a id="menuUser" href="user?action=search&pageNum=1&pageSize=10">搜索员工</a></li>
            </ul>
        </li>
        </c:if>
        <li role="presentation"><a href="#" data-toggle="collapse" data-target="#list3">会议预订</a>
            <ul id="list3" class="collapse in">
                <c:if test="${loginUser.userType==1}">
                <li><a id="menuRoom" href="room?action=room&pageNum=1&pageSize=10">管会议室</a></li>
                </c:if>
                <li><a id="menuMeeting" href="meeting?action=queryMeeting&pageNum=1&pageSize=10">会议管理</a></li>
            </ul>
        </li>
    </ul>
</div>