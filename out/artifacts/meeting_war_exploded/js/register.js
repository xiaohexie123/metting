
function sum(name, id, pwd, tpwd, dept, tel, email) {
    // $("#addForm").submit();
    // 使用jquery的ajax函数提交数据
    if(!checkData()) return ;

            $.ajax({
                url: "user",//代表ajax提交数据到什么地址，就是form表单的提交地址get
                method: "post",//代表提交方式，post或者
                //data你需要提交的数据值为对象类型
                data: {
                    userId: id,
                    userName: name,
                    pwd: pwd,
                    dept: dept,
                    tel: tel,
                    email: email,
                    usertype: "0",
                    status: "0",
                    action: "reg",
                },
                //dataType代表服务器返回的字符串，前端jquery解析的格式JSON代表jquery把后端返回的字符串解析成JSON格式对象
                //常用的值有JSON,TEXT,HTML等
                dataType: "JSON",
                //success：回调函数，服务端返回数据到大浏览器是，自动执行，str参数为服务器返回给前端的字符串
                success: function (j) {
                    if (j.success) {
                        //关闭当前弹窗，刷新数据列表
                        window.location.href = "http://localhost:8080/meeting_war_exploded/login.jsp";
                    } else {
                        alert(j.msg)
                    }
                }
            });


    }



function reset() {
    $("#userName").val("");
    $("#userId").val("");
    $("#password").val("");
    $("#twoPass").val("");
    $("#select").selected();
    $("#tel").val("");
    $("#email").val("");

}

//返回true 代表检查通过，返回false代表检查未通过
function checkData(){
    let success = true;
    let userId = $("#userId").val();
    if(userId == ''){
        success = false;
        $("#userIdMsg").text("zhang hao bi tian");
    }else {
        $("#userIdMsg").text("");
    }
    let userName = $("#userName").val();
    if(userName == ''){
        success = false;
        $("#userNameMsg").text("xing ming  bi  tian!");
    }else {
        $("#userNameMsg").text("");
    }
    let password = $("#password").val();
    if(password == ''){
        success = false;
        $("#userpwdMsg").text("mi ma bi tian!");
    }else {
        $("#userpwdMsg").text("");
    }
    let twoPass = $("#twoPass").val();
    if(twoPass == ''){
        success = false;
        $("#usertwpMsg").text("que ren mi ma !");
    }else {
        $("#usertwpMsg").text("");
    }

    if(password != twoPass || twoPass != ""){
        success = false;
        $("#usertwpMsg").text("mi ma tian xie bu yi zhi !");
    }else {
        $("#usertwpMsg").text("");
    }
    return success;
}