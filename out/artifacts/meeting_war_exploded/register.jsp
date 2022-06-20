<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: 14450
  Date: 2022/4/8
  Time: 17:49
 注册页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>员工注册</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <%@ include file="bootstrap.jsp" %>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/reg.css">
    <script src="js/register.js"></script>
<body>
<div style="margin: auto;text-align: center;">

    <span class="text">姓名：</span><input type="text" id="userName"/>
    <span style=color:red>*</span> <br><div id="userNameMsg" style=color:red></div>

    <span class="text">账户名：</span><input type="text" id="userId"/>
    <span style=color:red>*</span><br><div id="userIdMsg" style=color:red></div>

    <span class="text">密码：</span><input type="password" id="password"/>
    <span style=color:red>*</span><div id="userpwdMsg" style=color:red></div>

    <span class="text">确认密码：</span><input type="password" id="twoPass"/>
    <span style=color:red>*</span><br><div id="usertwpMsg" style=color:red></div>

    <span class="text" >所属部门：</span>
<%--    连接数据库动态查询,赋值--%>
<%--    这个是最开始用的,后面通过ajax动态的赋值--%>
    <select id="select">
        <%
                String username="root";
                String password="root";
                String url="jdbc:mysql://127.0.0.1:3306/meeting?useSSL=false";
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn= DriverManager.getConnection(url,username,password);

                String strSql="select * from t_dept";
                Statement stmt=conn.createStatement();
                ResultSet rs=stmt.executeQuery(strSql);
                while(rs.next()){ //循环遍历输出

                    out.print("<option value="+rs.getString("deptid")+">"+rs.getString("deptname")+"</option>");
                }
            %>
    </select><br>
    <span class="text">联系电话：</span><input type="text" id="tel"/><br>

    <span class="text">电子邮件：</span><input type="text" id="email"/><br>
    <button type="button" onclick="sum($('#userName').val(),$('#userId').val(),$('#password').val(),
    $('#twoPass').val(),$('#select').val(),$('#tel').val(),$('#email').val())">提交</button>
    <button type="button" onclick="reset()">重置</button>
</div>
</body>
</html>
