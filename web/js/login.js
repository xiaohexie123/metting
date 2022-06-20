function refresh(){
    $("#img").attr("src","img?r=" + Math.random());
}
function login(){
    $.ajax({
        url:"code",
        method:"post",
        data:{
            code: $("#code").val()
        },
        dataType:"JSON",
        success: function(j){
            if(j.success){
                //使用ajax提交登录信息
                $.ajax({
                    url : "user",
                    method: "post",
                    data: {
                        userId: $("#userId").val(),
                        password: $("#password").val(),
                        action: "login"
                    },
                    dataType:"JSON",
                    success: function (json){
                        if(json.success){
                            //跳转到主页
                            window.location.href = "notice.jsp";
                        }else{
                            swal(json.msg,"", "info");
                        }
                    }
                });
            }else{
                swal(j.msg,"", "info");
            }
        }
    });
}
function reg() {
    window.location.href ="register.jsp"
}