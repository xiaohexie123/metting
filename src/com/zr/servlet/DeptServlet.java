package com.zr.servlet;

import com.alibaba.fastjson.JSON;
import com.zr.model.Dept;
import com.zr.model.Meeting;
import com.zr.model.User;
import com.zr.service.DeptService;
import com.zr.service.UserService;
import com.zr.service.impl.DeptServiceImpl;
import com.zr.service.impl.UserServiceImpl;
import com.zr.util.PageUtil;
import com.zr.util.ResultUtil;
import com.zr.util.UserUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/dept")
public class DeptServlet extends HttpServlet {
    private DeptService deptService = new DeptServiceImpl();

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultUtil result = new ResultUtil();
        //获取本此请求表单中action的值，判断是什么操作
        String action = req.getParameter("action");
        System.out.println("UserServlet=>action:" + action);
        if("dept".equals(action)){
            PageUtil pageUtil = new PageUtil();
            pageUtil.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
            pageUtil.setPageSize(Integer.parseInt(req.getParameter("pageSize")));
            List<Dept> deptList = deptService.queryList(pageUtil);
            pageUtil.setTotalCount(deptService.count());
            req.setAttribute("deptList", deptList);
            req.setAttribute("pageUtil", pageUtil);
            req.getRequestDispatcher("dept.jsp").forward(req, resp);
        }else if("edit".equals(action)){
            String deptid = req.getParameter("userId");
            Dept dept = deptService.queryDept(Integer.parseInt(deptid));
            if (dept != null){
                result.setObj(dept);

            }
        }else if ("saveEdit".equals(action)){
            String deptid = req.getParameter("deptId");
            String deptName = req.getParameter("deptName");
            try {
                int count = deptService.saveEdit(Integer.parseInt(deptid),deptName);
                if (count == 1){
                    result.setSuccess(true);
                    result.setMsg("新增成功");
                }else {
                    result.setSuccess(false);
                    result.setMsg("新增失败");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            PrintWriter out = resp.getWriter();
            out.write(JSON.toJSONString(result));
            out.flush();
            out.close();

        }else if ("add".equals(action)){
            String addName = req.getParameter("addName");
            int count = deptService.add(addName);
            if (count == 1){
                result.setSuccess(true);
                result.setMsg("新增成功");
            }else {
                result.setSuccess(false);
                result.setMsg("新增失败");
            }
            PrintWriter out = resp.getWriter();
            out.write(JSON.toJSONString(result));
            out.flush();
            out.close();
        }else if ("remove".equals(action)){
            String removeId = req.getParameter("deptId");
            try {
               int count =  deptService.remove(Integer.parseInt(removeId));
                if (count == 1){
                    result.setSuccess(true);
                    result.setMsg("新增成功");
                }else {
                    result.setSuccess(false);
                    result.setMsg("新增失败");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            PrintWriter out = resp.getWriter();
            out.write(JSON.toJSONString(result));
            out.flush();
            out.close();
        }else if("queryAll".equals(action)){
            List<Dept> deptList = deptService.query();
            System.out.println("查找部门的集合长度"+deptList.size());
            for(Dept dept : deptList){
                List<User> userList = userService.selectListByDeptId(dept.getDeptId());
                System.out.println("每次通过id查询集合的长度"+userList.size());
                dept.setUserList(userList);
            }
            result.setSuccess(true);
            result.setObj(deptList);
            PrintWriter out = resp.getWriter();
            out.write(JSON.toJSONString(result));
            out.flush();
            out.close();
        }

    }
}
