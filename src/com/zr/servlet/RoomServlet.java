package com.zr.servlet;

import com.alibaba.fastjson.JSON;
import com.zr.model.Dept;
import com.zr.model.Room;
import com.zr.service.RoomService;
import com.zr.service.impl.RoomServiceImpl;
import com.zr.util.PageUtil;
import com.zr.util.ResultUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/room")
public class RoomServlet extends HttpServlet {
    private RoomService roomService = new RoomServiceImpl();
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
        if("room".equals(action)){
            PageUtil pageUtil = new PageUtil();
            pageUtil.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
            pageUtil.setPageSize(Integer.parseInt(req.getParameter("pageSize")));
            List<Room> roomList = roomService.queryList(pageUtil);
            pageUtil.setTotalCount(roomService.roomcount());
            req.setAttribute("roomList", roomList);
            req.setAttribute("pageUtil", pageUtil);
            req.getRequestDispatcher("room.jsp").forward(req, resp);
        }else if ("saveRoom".equals(action)){
            String roomId = req.getParameter("roomId");
            String roomName =req.getParameter("roomName");
            Integer nums = null;
            try {
                nums = Integer.parseInt( req.getParameter("nums"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            String marks = req.getParameter("marks");
            int count = roomService.addRoom(roomId,roomName,nums,marks);
            System.out.println("新增会议室的count:"+count);
            if (count == 1){
                result.setSuccess(true);
                result.setMsg("新增成功");
            }else {
                result.setSuccess(false);
                result.setMsg("新增失败");
            }

        }else if ("seeRoom".equals(action)){
            String roomId = req.getParameter("roomId");
            Room room = roomService.seeRoom(roomId);
            if (room != null){
                if (room.getStatus().equals("0")){
                    room.setStatus("停用");
                }else if (room.getStatus().equals("1")){
                    room.setStatus("启用");
                }else if (room.getStatus().equals("2")){
                    room.setStatus("删除");
                }
                result.setSuccess(true);
                result.setMsg("新增成功");
                result.setObj(room);

            }else {
                result.setSuccess(false);
                result.setMsg("新增失败");
            }
        }else if ("saveEditRoom".equals(action)){
            String roomId = req.getParameter("roomId");
            String roomName = req.getParameter("roomName");
            String nums = req.getParameter("nums");
            String mark = req.getParameter("marks");
            int count = roomService.saveEditRoom(roomId,roomName,nums,mark);
            if (count == 1){
                result.setSuccess(true);
                result.setMsg("新增成功");
            }else {
                result.setSuccess(false);
                result.setMsg("新增失败");
            }
        }else if ("starRoom".equals(action)){
            String roomId = req.getParameter("roomId");
            int count = roomService.starRoom(roomId);
            if (count == 1){
                result.setSuccess(true);
                result.setMsg("新增成功");
            }else {
                result.setSuccess(false);
                result.setMsg("新增失败");
            }
        }else if ("closeRoom".equals(action)){
            String roomId = req.getParameter("roomId");
            int count = roomService.closeRoom(roomId);
            if (count == 1){
                result.setSuccess(true);
                result.setMsg("新增成功");
            }else {
                result.setSuccess(false);
                result.setMsg("新增失败");
            }
        }else if ("removeRoom".equals(action)){
            String roomId = req.getParameter("roomId");
            int count = roomService.removeRoom(roomId);
            if (count == 1){
                result.setSuccess(true);
                result.setMsg("新增成功");
            }else {
                result.setSuccess(false);
                result.setMsg("新增失败");
            }
        }
        PrintWriter out = resp.getWriter();
        out.write(JSON.toJSONString(result));
        out.flush();
        out.close();
    }
}
