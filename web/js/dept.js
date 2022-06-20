$(function (){
    $("#menuDept").css("color","red");
});

/**
 * 修改，回显
 * @param id
 */
function edit(id) {
    $("#editModal").modal('show');
    // $("#addForm").submit();
    // 使用jquery的ajax函数提交数据
    $.ajax({
        url: "dept",//代表ajax提交数据到什么地址，就是form表单的提交地址get
        method: "post",//代表提交方式，post或者
        //data你需要提交的数据值为对象类型
        data: {
            userId: id,
            action:"edit",
        },
        //dataType代表服务器返回的字符串，前端jquery解析的格式JSON代表jquery把后端返回的字符串解析成JSON格式对象
        //常用的值有JSON,TEXT,HTML等
        dataType: "JSON",
        //success：回调函数，服务端返回数据到大浏览器是，自动执行，str参数为服务器返回给前端的字符串
        success: function (j) {
            if (j.obj!=null) {
                //关闭当前弹窗，刷新数据列表
              $("#deptid").val(j.obj.deptId);
                $("#deptname").val(j.obj.deptName);
            } else {
                alert("失败");
            }
        }
    });
}

/**
 * 保存修改后的信息，保存到数据库
 */
function saveEdit() {
    $.ajax({
        url: "dept",//代表ajax提交数据到什么地址，就是form表单的提交地址get
        method: "post",//代表提交方式，post或者
        //data你需要提交的数据值为对象类型
        data: {
            deptId: $("#deptid").val(),
            deptName: $("#deptname").val(),
            action:"saveEdit"
        },
        //dataType代表服务器返回的字符串，前端jquery解析的格式JSON代表jquery把后端返回的字符串解析成JSON格式对象
        //常用的值有JSON,TEXT,HTML等
        dataType: "JSON",
        //success：回调函数，服务端返回数据到大浏览器是，自动执行，str参数为服务器返回给前端的字符串
        success: function (j) {
            if (j.success) {
                //关闭当前弹窗，刷新数据列表
                window.location.href="http://localhost:8080/meeting_war_exploded/dept?action=dept&pageNum=1&pageSize=10";
                $("#editModal").modal('hide');
            } else {
                alert(j.msg);
            }
        }
    });
}

/**
 * 翻页
 * @param pageNum
 */
function queryList(pageNum) {
    window.location.href="http://localhost:8080/meeting_war_exploded/dept?action=dept&pageNum="+pageNum+"&pageSize=10";
}

/**
 * 添加会议室
 */
function insert() {
    $.ajax({
        url: "dept",//代表ajax提交数据到什么地址，就是form表单的提交地址get
        method: "post",//代表提交方式，post或者
        //data你需要提交的数据值为对象类型
        data: {
           addName:$("#addName").val(),
            action:"add"
        },
        //dataType代表服务器返回的字符串，前端jquery解析的格式JSON代表jquery把后端返回的字符串解析成JSON格式对象
        //常用的值有JSON,TEXT,HTML等
        dataType: "JSON",
        //success：回调函数，服务端返回数据到大浏览器是，自动执行，str参数为服务器返回给前端的字符串
        success: function (j) {
            if (j.success) {
                //关闭当前弹窗，刷新数据列表
                window.location.href="http://localhost:8080/meeting_war_exploded/dept?action=dept&pageNum=1&pageSize=10";
                $("#addModal").modal('hide');
            } else {
                alert(j.msg);
            }
        }
    });
}

/**
 *
 * 删除
 * @param id
 */
function remove(id) {
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
                    url : "dept",//代表ajax提交数据到什么地址，就是没有用ajax时，form表单的action的值
                    method : "post",//代表提交的方式，post或者get，就是没有用ajax时，form表单的method的值
                    //data：你需要提交的数据，值为对象类型
                    data : {
                        deptId : id,
                        action : "remove"

                    },
                    //dataType 代表服务器返回的字符串，前端jquery解析的格式 JSON代表jquery把后端返回的字符串解析成JSON格式对象
                    //常用的值有 JSON、TEXT、HTML等
                    dataType : "JSON",
                    //sucess 回调函数！执行时机：服务端返回数据到达浏览器时，自动执行，str参数为服务器返回给前端的字符串
                    success : function(j) {
                        if (j.success) {
                            //刷新数据列表
                            window.location.href="http://localhost:8080/meeting_war_exploded/dept?action=dept&pageNum=1&pageSize=10";
                        } else {
                            swal("消息", j.msg, "error")
                        }
                    }
                });
            }
        });
}