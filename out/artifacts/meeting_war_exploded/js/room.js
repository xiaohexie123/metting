$(function (){
    $("#menuRoom").css("color","red");
});
function saveRoom(id,name,nums,marks) {
console.info(id);
console.info(name);
console.info(nums);
console.info(marks);
    $.ajax({
        url: "room",//代表ajax提交数据到什么地址，就是form表单的提交地址get
        method: "post",//代表提交方式，post或者
        //data你需要提交的数据值为对象类型
        data: {
            roomId: id,
            roomName: name,
            nums:nums,
            marks:marks,
            action:"saveRoom"
        },
        //dataType代表服务器返回的字符串，前端jquery解析的格式JSON代表jquery把后端返回的字符串解析成JSON格式对象
        //常用的值有JSON,TEXT,HTML等
        dataType: "JSON",
        //success：回调函数，服务端返回数据到大浏览器是，自动执行，str参数为服务器返回给前端的字符串
        success: function (j) {
            if (j.success) {
                //关闭当前弹窗，刷新数据列表
                $("#addRoomModal").modal('hide');
                window.location.href="http://localhost:8080/meeting_war_exploded/room?action=room&pageNum=1&pageSize=10";
            } else {
                alert(j.msg);
            }
        }
    });
}


function seeRoom(id) {
    $("#seeRoomModal").modal('show');
    $.ajax({
        url: "room",//代表ajax提交数据到什么地址，就是form表单的提交地址get
        method: "post",//代表提交方式，post或者
        //data你需要提交的数据值为对象类型
        data: {
            roomId: id,
            action:"seeRoom"
        },
        //dataType代表服务器返回的字符串，前端jquery解析的格式JSON代表jquery把后端返回的字符串解析成JSON格式对象
        //常用的值有JSON,TEXT,HTML等
        dataType: "JSON",
        //success：回调函数，服务端返回数据到大浏览器是，自动执行，str参数为服务器返回给前端的字符串
        success: function (j) {
            if (j.success) {
                //关闭当前弹窗，刷新数据列表
               $("#seeroomId").val(j.obj.roomId);
                $("#seeroomName").val(j.obj.roomName);
                $("#seenums").val(j.obj.nums)
                $("#seestatus").val(j.obj.status)
                $("#seemarks").val(j.obj.mark)
            } else {
                alert(j.msg);
            }
        }
    });
}

function editRoom(id) {
    $("#editRoomModal").modal('show');
    $.ajax({
        url: "room",//代表ajax提交数据到什么地址，就是form表单的提交地址get
        method: "post",//代表提交方式，post或者
        //data你需要提交的数据值为对象类型
        data: {
            roomId: id,
            action:"seeRoom"
        },
        //dataType代表服务器返回的字符串，前端jquery解析的格式JSON代表jquery把后端返回的字符串解析成JSON格式对象
        //常用的值有JSON,TEXT,HTML等
        dataType: "JSON",
        //success：回调函数，服务端返回数据到大浏览器是，自动执行，str参数为服务器返回给前端的字符串
        success: function (j) {
            if (j.success) {
                //关闭当前弹窗，刷新数据列表
                $("#editroomId").val(j.obj.roomId);
                $("#editroomName").val(j.obj.roomName);
                $("#editnums").val(j.obj.nums)
                $("#editmarks").val(j.obj.mark)
            } else {
                alert(j.msg);
            }
        }
    });
}

function saveEditRoom() {
    $.ajax({
        url: "room",//代表ajax提交数据到什么地址，就是form表单的提交地址get
        method: "post",//代表提交方式，post或者
        //data你需要提交的数据值为对象类型
        data: {
            roomId: $("#editroomId").val(),
            roomName:  $("#editroomName").val(),
            nums:$("#editnums").val(),
            marks:$("#editmarks").val(),
            action:"saveEditRoom"
        },
        //dataType代表服务器返回的字符串，前端jquery解析的格式JSON代表jquery把后端返回的字符串解析成JSON格式对象
        //常用的值有JSON,TEXT,HTML等
        dataType: "JSON",
        //success：回调函数，服务端返回数据到大浏览器是，自动执行，str参数为服务器返回给前端的字符串
        success: function (j) {
            if (j.success) {
                //关闭当前弹窗，刷新数据列表
                $("#editRoomModal").modal('hide');
                window.location.href="http://localhost:8080/meeting_war_exploded/room?action=room&pageNum=1&pageSize=10";
            } else {
                alert(j.msg);
            }
        }
    });
}

function starRoom(id) {
    swal({
        title: "确定启用?",
        text: "启用数据不可恢复!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
        buttons: ["取消", "确定"],
    })
        .then((willDelete) => {
            if (willDelete) {
                $.ajax({
                    url : "room",//代表ajax提交数据到什么地址，就是没有用ajax时，form表单的action的值
                    method : "post",//代表提交的方式，post或者get，就是没有用ajax时，form表单的method的值
                    //data：你需要提交的数据，值为对象类型
                    data : {
                        roomId : id,
                        action:"starRoom"
                    },
                    //dataType 代表服务器返回的字符串，前端jquery解析的格式 JSON代表jquery把后端返回的字符串解析成JSON格式对象
                    //常用的值有 JSON、TEXT、HTML等
                    dataType : "JSON",
                    //sucess 回调函数！执行时机：服务端返回数据到达浏览器时，自动执行，str参数为服务器返回给前端的字符串
                    success : function(j) {
                        if (j.success) {
                            //刷新数据列表
                            window.location.href="http://localhost:8080/meeting_war_exploded/room?action=room&pageNum=1&pageSize=10";
                        } else {
                            swal("消息", j.msg, "error")
                        }
                    }
                });
            }
        });
}


function closeRoom(id) {
    swal({
        title: "确定停用?",
        text: "停用数据可以恢复!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
        buttons: ["取消", "确定"],
    })
        .then((willDelete) => {
            if (willDelete) {
                $.ajax({
                    url : "room",//代表ajax提交数据到什么地址，就是没有用ajax时，form表单的action的值
                    method : "post",//代表提交的方式，post或者get，就是没有用ajax时，form表单的method的值
                    //data：你需要提交的数据，值为对象类型
                    data : {
                        roomId : id,
                        action:"closeRoom"
                    },
                    //dataType 代表服务器返回的字符串，前端jquery解析的格式 JSON代表jquery把后端返回的字符串解析成JSON格式对象
                    //常用的值有 JSON、TEXT、HTML等
                    dataType : "JSON",
                    //sucess 回调函数！执行时机：服务端返回数据到达浏览器时，自动执行，str参数为服务器返回给前端的字符串
                    success : function(j) {
                        if (j.success) {
                            //刷新数据列表
                            window.location.href="http://localhost:8080/meeting_war_exploded/room?action=room&pageNum=1&pageSize=10";
                        } else {
                            swal("消息", j.msg, "error")
                        }
                    }
                });
            }
        });
}


function removeRoom(id) {
    swal({
        title: "确定删除?",
        text: "删除数据不可恢复!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
        buttons: ["取消", "确定"],
    })
        .then((willDelete) => {
            if (willDelete) {
                $.ajax({
                    url : "room",//代表ajax提交数据到什么地址，就是没有用ajax时，form表单的action的值
                    method : "post",//代表提交的方式，post或者get，就是没有用ajax时，form表单的method的值
                    //data：你需要提交的数据，值为对象类型
                    data : {
                        roomId : id,
                        action:"removeRoom"
                    },
                    //dataType 代表服务器返回的字符串，前端jquery解析的格式 JSON代表jquery把后端返回的字符串解析成JSON格式对象
                    //常用的值有 JSON、TEXT、HTML等
                    dataType : "JSON",
                    //sucess 回调函数！执行时机：服务端返回数据到达浏览器时，自动执行，str参数为服务器返回给前端的字符串
                    success : function(j) {
                        if (j.success) {
                            //刷新数据列表
                            window.location.href="http://localhost:8080/meeting_war_exploded/room?action=room&pageNum=1&pageSize=10";
                        } else {
                            swal("消息", j.msg, "error")
                        }
                    }
                });
            }
        });
}

function queryList(pageNum) {
    window.location.href="http://localhost:8080/meeting_war_exploded/room?action=room&pageNum="+pageNum+"&pageSize=10";
}