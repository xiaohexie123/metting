$(function (){
    $("#menuUserPass").css("color","red");
});
function pass(id) {
    $.ajax({
        url: "user",//代表ajax提交数据到什么地址，就是form表单的提交地址get
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
            if (j.success) {
                //关闭当前弹窗，刷新数据列表
                window.location.href="http://localhost:8080/meeting_war_exploded/user?action=userPass&pageNum=1&pageSize=10";
            } else {
                alert(j.msg);
            }
        }
    });
}
function remove(id) {
    console.info(id);
    $.ajax({
        url: "user",//代表ajax提交数据到什么地址，就是form表单的提交地址get
        method: "post",//代表提交方式，post或者
        //data你需要提交的数据值为对象类型
        data: {
            userId: id,
            action:"remove",
        },
        //dataType代表服务器返回的字符串，前端jquery解析的格式JSON代表jquery把后端返回的字符串解析成JSON格式对象
        //常用的值有JSON,TEXT,HTML等
        dataType: "JSON",
        //success：回调函数，服务端返回数据到大浏览器是，自动执行，str参数为服务器返回给前端的字符串
        success: function (j) {
            if (j.success) {
                //关闭当前弹窗，刷新数据列表
                window.location.href="http://localhost:8080/meeting_war_exploded/user?action=userPass&pageNum=1&pageSize=10";
            } else {
                alert(j.msg);
            }
        }
    });
}
function queryList(pageNum) {
    window.location.href="http://localhost:8080/meeting_war_exploded/user?action=userPass&pageNum="+pageNum+"&pageSize=10";
}