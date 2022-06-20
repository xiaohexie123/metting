package com.zr.servlet;

import com.alibaba.fastjson.JSON;
import com.zr.model.Dept;
import com.zr.model.User;
import com.zr.service.UserService;
import com.zr.service.impl.UserServiceImpl;
import com.zr.util.PageUtil;
import com.zr.util.ResultUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取本此请求表单中action的值，判断是什么操作
        String action = req.getParameter("action");
        System.out.println("UserServlet=>action:" + action);
        ResultUtil result = new ResultUtil();
        //把请求表单封装成User对象
        User user = new User(req);
        if("login".equals(action)){
            User loginUser = userService.login(user.getUserId(), user.getPassword());
            if(loginUser != null){
                req.getSession().setAttribute("loginUser", loginUser);
                result.setSuccess(true);
                result.setMsg("登录成功!");
            }else{
                result.setSuccess(false);
                result.setMsg("账号或密码错误!");
            }

        }else if("logout".equals(action)){
            req.getSession().invalidate();//销毁session
            resp.sendRedirect("login.jsp");
        } else if("reg".equals(action)){
            String id = req.getParameter("userId");
            String name = req.getParameter("userName");
            String password = req.getParameter("pwd");
            String dept = req.getParameter("dept");
            String tel = req.getParameter("tel");
            String email = req.getParameter("email");
            String usertype = req.getParameter("usertype");
            String status = req.getParameter("status");
            int count = 0;
            try {
                count = userService.add(id,name,password,Integer.parseInt(dept),tel,email,usertype,status);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            System.out.println(count);
            if(count == 1){
                result.setSuccess(true);
                result.setMsg("成功!");
            }else{
                result.setSuccess(false);
                result.setMsg("错误!");
            }
        }else if("edit".equals(action)){
            String userid = req.getParameter("userId");
            int count = userService.edit(userid);
            System.out.println(count);
            if(count == 1){
                result.setSuccess(true);
                result.setMsg("成功!");
            }else{
                result.setSuccess(false);
                result.setMsg("错误!");
            }
        }else if ("userPass".equals(action)){
            PageUtil pageUtil = new PageUtil();
            pageUtil.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
            pageUtil.setPageSize(Integer.parseInt(req.getParameter("pageSize")));
            List<User> userList = userService.queryPage(pageUtil);
            pageUtil.setTotalCount(userService.count());
            req.setAttribute("pageUtil", pageUtil);
            req.setAttribute("userList",userList);
            req.getRequestDispatcher("userPass.jsp").forward(req,resp);
        }else if ("remove".equals(action)){
            String userId = req.getParameter("userId");
            int count = userService.remove(userId);
            if(count == 1){
                result.setSuccess(true);
                result.setMsg("成功!");
            }else{
                result.setSuccess(false);
                result.setMsg("错误!");
            }
        }else if ("search".equals(action)){
            PageUtil pageUtil = new PageUtil();
            pageUtil.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
            pageUtil.setPageSize(Integer.parseInt(req.getParameter("pageSize")));
            String userId = req.getParameter("id");
            String userName = req.getParameter("name");
            String status = req.getParameter("status");
            if (status == null){
                status = "1";
            }else if ("已批准".equals(status)){
                status = "1";
            }else if ("待审批".equals(status)){
                status = "0";
            }else if ("已关闭".equals(status)){
                status = "2";
            }
            List<User> userList = userService.queryUser(pageUtil,status,userId,userName);
            System.out.println("返回集合长度："+userList.size());
            pageUtil.setTotalCount(userService.usercount(status,userId,userName));
            req.setAttribute("pageUtil",pageUtil);
            req.setAttribute("userList",userList);
            req.getRequestDispatcher("user.jsp").forward(req,resp);
        }else if ("userClose".equals(action)){
            String userId = req.getParameter("userId");
            int count = userService.userClose(userId);
            if(count == 1){
                result.setSuccess(true);
                result.setMsg("成功!");
            }else{
                result.setSuccess(false);
                result.setMsg("错误!");
            }
        }

        PrintWriter out = resp.getWriter();
        out.write(JSON.toJSONString(result));
        out.flush();
        out.close();
    }
}
