$(function (){
    $("#menuUser").css("color","red");
});
function queryUser(name,id,status) {
console.info(name);
console.info(id);
console.info(status);

window.location.href="http://localhost:8080/meeting_war_exploded/user?id="+id+"&name="+name+"&status="+status+"&action=search&pageNum=1&pageSize=10";
}
function cleary() {
window.location.href="http://localhost:8080/meeting_war_exploded/user?id="+id+"&name="+name+"&status="+status+"&action=search&pageNum=1&pageSize=10";
}
function userClose(id) {
    console.info("关闭账户传过来的"+id)
    $.ajax({
        url: "user",//代表ajax提交数据到什么地址，就是form表单的提交地址get
        method: "post",//代表提交方式，post或者
        //data你需要提交的数据值为对象类型
        data: {
            userId: id,
            action:"userClose",
        },
        //dataType代表服务器返回的字符串，前端jquery解析的格式JSON代表jquery把后端返回的字符串解析成JSON格式对象
        //常用的值有JSON,TEXT,HTML等
        dataType: "JSON",
        //success：回调函数，服务端返回数据到大浏览器是，自动执行，str参数为服务器返回给前端的字符串
        success: function (j) {
            if (j.success) {
                //关闭当前弹窗，刷新数据列表
               console.info("成功")
            } else {
                alert("失败");
            }
        }
    });
}
function queryList(pageNum) {
    window.location.href="http://localhost:8080/meeting_war_exploded/user?action=search&pageNum="+pageNum+"&pageSize=10";
}