$(function (){
    $("#menuMyOrder").css("color","red");
});



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
 * 取消会议
 * @param meetingId
 */
function cancel(meetingId){
    $("#cancelMeetingModal").modal("show")
    $.ajax({
        url: "meeting",//代表ajax提交数据到什么地址，就是form表单的提交地址get
        method: "post",//代表提交方式，post或者
        //data你需要提交的数据值为对象类型
        data: {
            mettingId: meetingId,
            action: "cancelMeeting"
        },
        //dataType代表服务器返回的字符串，前端jquery解析的格式JSON代表jquery把后端返回的字符串解析成JSON格式对象
        //常用的值有JSON,TEXT,HTML等
        dataType: "JSON",
        //success：回调函数，服务端返回数据到大浏览器是，自动执行，str参数为服务器返回给前端的字符串
        success: function (j) {
            if (j.obj != null) {
                //关闭当前弹窗，刷新数据列表
                $("#cancelMeetingName").val(j.obj.meetingName);
                $("#cancelStarTime").val(timeTrans(j.obj.startTime));
                $("#toolId").val(j.obj.meetingId);
            } else {
                alert();
            }
        }
    });
}


/**
 * 修改会议
 */
function confirCancel() {

    $.ajax({
        url: "meeting",//代表ajax提交数据到什么地址，就是form表单的提交地址get
        method: "post",//代表提交方式，post或者
        //data你需要提交的数据值为对象类型
        data: {
            mettingId: $("#toolId").val(),
            cancelMark :$("#cancelMark").val(),
            action: "updateMeeting"
        },
        //dataType代表服务器返回的字符串，前端jquery解析的格式JSON代表jquery把后端返回的字符串解析成JSON格式对象
        //常用的值有JSON,TEXT,HTML等
        dataType: "JSON",
        //success：回调函数，服务端返回数据到大浏览器是，自动执行，str参数为服务器返回给前端的字符串
        success: function (j) {
            if (j.success) {
                //关闭当前弹窗，刷新数据列表
                $("#cancelMeetingModal").modal("hide")
                window.location.href = "http://localhost:8080/meeting_war_exploded/meeting?action=myOrder&pageNum=1&pageSize=10";
            } else {
                alert();
            }
        }
    });
}

// 翻页
function queryList(pageNum) {
    window.location.href="http://localhost:8080/meeting_war_exploded/meeting?action=myOrder&pageNum="+pageNum+"&pageSize=10";
}