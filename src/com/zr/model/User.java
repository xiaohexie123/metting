package com.zr.model;

import javax.servlet.http.HttpServletRequest;

public class User {

    private String userId;

    private String userName;

    private String password;

    private Integer deptId;

    private String tel;

    private String email;

    private String userType;

    private String status;

    //无参构造方法必须写出来
    public User(){}

    public User(HttpServletRequest req){
        this.userId = req.getParameter("userId");
        this.userName = req.getParameter("userName");
        this.password = req.getParameter("password");
        if(req.getParameter("deptId") != null){
            this.deptId = Integer.parseInt(req.getParameter("deptId"));
        }
        this.tel = req.getParameter("tel");
        this.email = req.getParameter("email");
        this.userType = req.getParameter("userType");
        this.status = req.getParameter("status");

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
