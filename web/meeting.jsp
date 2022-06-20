<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2022/4/7
  Time: 14:55
 会议管理页面
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
    <script src="js/meeting.js"></script>
    <script src="My97DatePicker/WdatePicker.js"></script>
    <link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">


    <script>
        $(function () {
            //文档就是，执行的函数
            body_load();
        });

        function employee(employeeid, employeename){
            this.employeeid = employeeid;
            this.employeename = employeename;
        }
        function department(departmentid, departmentname, employees){
            this.departmentid = departmentid;
            this.departmentname = departmentname;
            this.employees = employees;
        }
        let data = new Array();

        $.ajax({
            url:"dept",
            method:"post",
            data:{
                action:"queryAll"
            },
            dataType:"JSON",
            //设置ajax为同步
            async: false,
            success: function (j){
                let deptList = j.obj;
                for(let i = 0 ;i < deptList.length; i++){
                    let dept = deptList[i];
                    let userArray = new Array();
                    for(let j = 0 ; j < dept.userList.length; j++){
                        let user = dept.userList[j];
                        let userData = new employee(user.userId, user.userName);
                        userArray.push(userData);
                    }
                    let deptData = new department(dept.deptId,dept.deptName,userArray);
                    data.push(deptData);
                }
            }
        });
        console.info(data);
        let selDepartments;
        let selEmployees;
        let selSelectedEmployees;

        function body_load(){
            selDepartments = document.getElementById("selDepartments");
            selEmployees = document.getElementById("selEmployees");
            selSelectedEmployees = document.getElementById("selSelectedEmployees");

            for(let i=0;i<data.length;i++){
                let dep = document.createElement("option");
                dep.value = data[i].departmentid;
                dep.text = data[i].departmentname;
                selDepartments.appendChild(dep);
            }

            fillEmployees();
        }

        function fillEmployees(){
            clearList(selEmployees);
            let departmentid = selDepartments.options[selDepartments.selectedIndex].value;
            let employees;
            for(let i=0;i<data.length;i++){
                if (departmentid == data[i].departmentid){
                    employees = data[i].employees;
                    break;
                }
            }
            for(i=0;i<employees.length;i++){
                let emp = document.createElement("option");
                emp.value = employees[i].employeeid;
                emp.text = employees[i].employeename;
                selEmployees.appendChild(emp);
            }
        }

        function clearList(list){
            while(list.childElementCount > 0){
                list.removeChild(list.lastChild);
            }
        }

        function selectEmployees(){
            for(let i=0;i<selEmployees.options.length;i++){
                if (selEmployees.options[i].selected){
                    addEmployee(selEmployees.options[i]);
                    selEmployees.options[i].selected = false;
                }
            }
        }

        function deSelectEmployees(){
            let elementsToRemoved = new Array();
            let options = selSelectedEmployees.options;
            for(let i=0;i<options.length;i++){
                if (options[i].selected){
                    elementsToRemoved.push(options[i]);
                }
            }
            for(i=0;i<elementsToRemoved.length;i++){
                selSelectedEmployees.removeChild(elementsToRemoved[i]);
            }
        }

        function addEmployee(optEmployee){
            let options = selSelectedEmployees.options;
            let i = 0;
            let insertIndex = -1;
            while(i < options.length){
                if (optEmployee.value == options[i].value){
                    return;
                } else if (optEmployee.value < options[i].value) {
                    insertIndex = i;
                    break;
                }
                i++;
            }
            let opt = document.createElement("option");
            opt.value = optEmployee.value;
            opt.text = optEmployee.text;

            if (insertIndex == -1){
                selSelectedEmployees.appendChild(opt);
            } else {
                selSelectedEmployees.insertBefore(opt, options[insertIndex]);
            }
        }
    </script>


    <style type="text/css">
        #divfrom {
            float: left;
            width: 150px;
        }

        #divto {
            float: left;
            width: 150px;
        }

        #divoperator {
            float: left;
            width: 50px;
            padding: 60px 5px;
        }

        #divoperator input[type="button"] {
            margin: 10px 0;
        }

        #selDepartments {
            display: block;
            width: 100%;
        }

        #selEmployees {
            display: block;
            width: 100%;
            height: 200px;
        }

        #selSelectedEmployees {
            display: block;
            width: 100%;
            height: 225px;
        }

    </style>



<body>
<div id="main">
    <%@ include file="head.jsp" %>
    <%@ include file="menu.jsp" %>

    <div class="right-content">
        <div style="margin:10px;">
            <ol class="breadcrumb">
                <li><a href="#">会议预定</a></li>
                <li class="active">会议管理</li>
            </ol>
        </div>
        <h3 style="margin: 10px">搜索会议</h3>
        <div style="width: 80%;height: 10%;margin-left: 10%;border: 1px solid #F0F0F0; text-align: center">
            <span style="margin-right: 60px;margin-left: 60px"> 会议名称:<input type="text" id="meetingName"></span>
            <span style="margin-right: 60px">会议室名称:<input type="text" id="roomName"></span>
            <span style="margin-right: 60px">预定者姓名:<input type="text" id="userName"></span><br>
        </div>
        <div style="width: 80%;height: 10%;margin-left: 10%;border: 1px solid #f0f0f0;">
            <span style="margin-right: 60px;margin-left: 60px">预定日期:
                从:<input type="text" id="startLine" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                到:<input type="text" id="endLine" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
            </span><br>
            <span style="margin-right: 60px;margin-left: 60px">会议日期:
                从:<input type="text" id="startMeeting" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                到:<input type="text" id="endMeeting" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
            </span><br>
            <br>
            <div style="width: 80%;height: 10%;text-align: center;margin-left: 10%;">
                <button type="button" id="querySeeting()" onclick="queryTime()">查询</button>&nbsp;&nbsp;
                <button type="button" id="relocateSeeting()" onclick="relocate()">重置</button>
                <button type="button" class="btn btn-warning" id="addMeeting()" style="float: right"
                        onclick="addMeeting()">添加
                </button>
            </div>
        </div>
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
        <%----%>
    </div>
</div>

<%--查看会议弹窗--%>
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


<%--添加模态框--%>
<div class="modal fade" id="addMettingModal">
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
                            <input type="text" id="orderMettingName" class="form-control" placeholder="会议名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">预计参加人数</label>
                        <div class="col-sm-9">
                            <input type="text" id="orderMeetingNums" class="form-control" placeholder="参加人数">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" >预计开始时间</label>
                        <div class="col-sm-9">
                            <input type="text" id="orderStarTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:ss:mm'})" onblur="showMeetingid()">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">预计结束时间</label>
                        <div class="col-sm-9">
                            <input type="text" id="orderEndTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:ss:mm'})"onblur="showMeetingid()">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">会议室</label>
                        <div class="col-sm-9">
                            <select id="orderMeetingRoom">

                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">会议说明</label>
                        <div class="col-sm-9">
                            <textarea id="meetingMark" class="form-control" placeholder="会议说明"></textarea>
                        </div>
                    </div>
                    <div class="form-group" style="margin-left: 20%">
                        <div id="divfrom">
                            <select id="selDepartments" onchange="fillEmployees()">
                            </select>
                            <select id="selEmployees" multiple="true">
                            </select>
                        </div>
                        <div id="divoperator">
                            <input type="button" class="clickbutton" value="&gt;" onclick="selectEmployees()"/>
                            <input type="button" class="clickbutton" value="&lt;" onclick="deSelectEmployees()"/>
                        </div>
                        <div id="divto">
                            <select id="selSelectedEmployees" multiple="true">
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-success" data-dismiss="modal" onclick="saveOrderMeeting()">保存</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>


</body>




</html>

