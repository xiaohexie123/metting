$(function (){
    $("#menuMeeting").css("color","red");
});
// 重置方法
function relocate() {
    $("#meetingName").val("");
    $("#roomName").val("");
    $("#userName").val("");
    $("#startLine").val("");
    $("#endLine").val("");
    $("#startMeeting").val("");
    $("#endMeeting").val("");
}


// 添加

function addMeeting() {
    $("#addMettingModal").modal("show")
}


// 搜索方法
function queryTime() {
    console.info($("#startMeeting").val())
    console.info(typeof $("#startMeeting").val())

           let meetingName = $("#meetingName").val();
           let roomName = $("#roomName").val();
           let userName = $("#userName").val();
           let startLine = $("#startLine").val();
             let endLine = $("#endLine").val();
           let startMeeting =$("#startMeeting").val();
            let endMeeting = $("#endMeeting").val();
           let action = "queryTime";
    window.location.href ="http://localhost:8080/meeting_war_exploded/meeting?meetingName="+meetingName+"&roomName="+roomName+"&userName="+userName+"meetingName="+userName+"&startLine="+startLine+"&endLine="+endLine+"&startMeeting="+startMeeting+"&endMeeting="+endMeeting+"&action="+action+"&pageNum=1&pageSize=10";
}

// 查看详情
function seeMetting(id) {
    console.info(id);
    $("#seeMettingModal").modal("show")
    $.ajax({
        url: "meeting",//代表ajax提交数据到什么地址，就是form表单的提交地址get
        method: "post",//代表提交方式，post或者
        //data你需要提交的数据值为对象类型
        data: {
            mettingId: id,
            action: "seeMetting"
        },
        //dataType代表服务器返回的字符串，前端jquery解析的格式JSON代表jquery把后端返回的字符串解析成JSON格式对象
        //常用的值有JSON,TEXT,HTML等
        dataType: "JSON",
        //success：回调函数，服务端返回数据到大浏览器是，自动执行，str参数为服务器返回给前端的字符串
        success: function (j) {
            if (j.obj != null) {
                //关闭当前弹窗，刷新数据列表
                $("#mettingName").val(j.obj.meetingName);
                $("#peopleNums").val(j.obj.nums);
                $("#starTime").val(timeTrans(j.obj.startTime));
                $("#endTime").val(timeTrans(j.obj.endTime));
                $("#meetingMarks").val(j.obj.mark);
                $("#tbody").empty();
                    var thisListValueStr = "";
                    console.log(j.userList.length + "条数据");
                    for (var i = 0; i < j.userList.length; i++) {
                        var caseList = j.userList[i];
                        thisListValueStr = "<tr><td>" + caseList.userName + "</td><td>" + caseList.tel + "</td><" + "<td>" + caseList.email + "</td></tr>";
                        $("#tbody").append(thisListValueStr);
                        thisListValueStr = "";
                    }

            } else {
                alert();
            }
        }
    });
}

// 转换日期
function timeTrans(number) {

    var date = new Date(number)
    var Y = date.getFullYear() + '-'
    var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
    var D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + ' '
    var h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':'
    var m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':'
    var s = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds())
    return Y + M + D + h + m + s

}

/**
 * 保存预定会议
 */
function saveOrderMeeting() {
    console.info($("#orderMettingName").val());
    console.info($("#orderMeetingNums").val());
    console.info($("#orderStarTime").val());
    console.info($("#orderEndTime").val());
    console.info($("#meetingMark").val());
    console.info($("#selDepartments").val());
    console.info($("#selEmployees").val());
    console.info($("#orderMeetingRoom").val());
    let select_com = [];
    $("#selSelectedEmployees option").each(function () {
        let option_con = $(this).val();
        select_com.push(option_con)
    });
    console.info(select_com);
    // $("#addForm").submit();
    // 使用jquery的ajax函数提交数据
    $.ajax({
        url: "meeting",//代表ajax提交数据到什么地址，就是form表单的提交地址get
        method: "post",//代表提交方式，post或者
        //data你需要提交的数据值为对象类型
        data: {
            orderMettingName: $("#orderMettingName").val(),
            orderMeetingNums: $("#orderMeetingNums").val(),
            orderStarTime:$("#orderStarTime").val(),
            orderEndTime:$("#orderEndTime").val(),
            meetingMark:$("#meetingMark").val(),
            selSelectedEmployees:select_com.toString(),
            orderMeetingRoom :$("#orderMeetingRoom").val(),
            // orderMeetingId : id,
            action:"saveOrderMeeting",
        },
        //dataType代表服务器返回的字符串，前端jquery解析的格式JSON代表jquery把后端返回的字符串解析成JSON格式对象
        //常用的值有JSON,TEXT,HTML等
        dataType: "JSON",
        //success：回调函数，服务端返回数据到大浏览器是，自动执行，str参数为服务器返回给前端的字符串
        success: function (j) {
            if (j.success) {
                //关闭当前弹窗，刷新数据列表
                window.location.href = "http://localhost:8080/meeting_war_exploded/meeting?action=queryMeeting&pageNum=1&pageSize=10";
            } else {
                alert("失败");
            }
        }
    });
}

/**
 * 通过时间选取空闲的会议室
 */
function showMeetingid() {
    let orderStarTime = $("#orderStarTime").val();
    let orderEndTime = $("#orderEndTime").val();
    if (orderEndTime != ""&&orderStarTime!=""){
        $.ajax({
            url: "meeting",//代表ajax提交数据到什么地址，就是form表单的提交地址get
            method: "post",//代表提交方式，post或者
            //data你需要提交的数据值为对象类型
            data: {
                orderStarTime: orderStarTime,
                orderEndTime :orderEndTime,
                action:"showMeetingid",
            },
            //dataType代表服务器返回的字符串，前端jquery解析的格式JSON代表jquery把后端返回的字符串解析成JSON格式对象
            //常用的值有JSON,TEXT,HTML等
            dataType: "JSON",
            //success：回调函数，服务端返回数据到大浏览器是，自动执行，str参数为服务器返回给前端的字符串
            success: function (j) {
                if (j.success) {
                    //关闭当前弹窗，刷新数据列表
                    $("#orderMeetingRoom").empty();
                    let thisListValueStr = "";
                    console.log(j.roomList.length + "条数据");
                    for (let i = 0; i < j.roomList.length; i++) {
                        let caseList = j.roomList[i];
                        thisListValueStr = "<option value="+caseList.roomId+">" + caseList.roomName + "</option>";
                        $("#orderMeetingRoom").append(thisListValueStr);
                        thisListValueStr = "";
                    }
                } else {
                    alert(j.msg);
                }
            }
        });
    }
}

/**
 * 翻页
 * @param pageNum
 */
function queryList(pageNum) {
    window.location.href="http://localhost:8080/meeting_war_exploded/meeting?action=queryMeeting&pageNum="+pageNum+"&pageSize=10";
}
