package com.zr.servlet;

import com.alibaba.fastjson.JSON;
import com.zr.model.Meeting;
import com.zr.model.Room;
import com.zr.model.User;
import com.zr.model.UserMeeting;
import com.zr.service.MeetingService;
import com.zr.service.UserMeetingService;
import com.zr.service.UserService;
import com.zr.service.impl.MeetingServiceImpl;
import com.zr.service.impl.UserMeetingServiceImpl;
import com.zr.service.impl.UserServiceImpl;
import com.zr.util.MeetingUtil;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet("/meeting")
public class MeetingServlet extends HttpServlet {

    private MeetingService meetingService = new MeetingServiceImpl();
    private UserMeetingService userMeetingService = new UserMeetingServiceImpl();

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
        if("myOrder".equals(action)){
            PageUtil pageUtil = new PageUtil();
            pageUtil.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
            pageUtil.setPageSize(Integer.parseInt(req.getParameter("pageSize")));
            List<Meeting> meetingList = meetingService.queryListByOrderUserId(UserUtil.currentUserId(req.getSession()),pageUtil);
            pageUtil.setTotalCount(meetingService.count(UserUtil.currentUserId(req.getSession())));
            req.setAttribute("meetingList", meetingList);
            req.setAttribute("pageUtil", pageUtil);
            req.getRequestDispatcher("myOrder.jsp").forward(req, resp);
        }else if ("queryMeeting".equals(action)){
            PageUtil pageMeeting = new PageUtil();
            pageMeeting.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
            pageMeeting.setPageSize(Integer.parseInt(req.getParameter("pageSize")));
            List<Meeting> meetingList = meetingService.queryMeeting(pageMeeting);
            System.out.println(meetingList.size());
            pageMeeting.setTotalCount(meetingService.countMeeting());
            req.setAttribute("meetingList", meetingList);
            req.setAttribute("pageUtil", pageMeeting);
            req.getRequestDispatcher("meeting.jsp").forward(req, resp);
        }else if ("seeMetting".equals(action)){
            Integer mettingId = null;
            try {
                mettingId = Integer.parseInt( req.getParameter("mettingId"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            System.out.println("传到后台的："+mettingId);
            Meeting meeting = meetingService.getMetting(mettingId);
            List<User> userList = meetingService.getUser(mettingId);
            result.setObj(meeting);
            result.setUserList(userList);
            if (meeting!=null){
                PrintWriter out = resp.getWriter();
                out.write(JSON.toJSONString(result));
                out.flush();
                out.close();
            }
        }else if ("queryTime".equals(action)){
            String meetingName = req.getParameter("meetingName");
            String roomName = req.getParameter("roomName");
            String userName = req.getParameter("userName");
            String userid = "";
            if (userName != ""){
               userid = meetingService.queryId(userName);
            }else {
                userid = "";
            }
            PageUtil pageUtil = new PageUtil();
            pageUtil.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
            pageUtil.setPageSize(Integer.parseInt(req.getParameter("pageSize")));
            String  startLine = req.getParameter("startLine");
            String  endLine = req.getParameter("endLine");
            String startMeeting = req.getParameter("startMeeting");
            String endMeeting =req.getParameter("endMeeting");
            List<Meeting> meetingList = meetingService.queryTime(meetingName,roomName,userid,startLine,endLine,startMeeting,endMeeting,pageUtil);
            System.out.println("后台查询的集合大小"+meetingList.size());
            pageUtil.setTotalCount(meetingList.size());
            req.setAttribute("meetingList",meetingList);
            req.setAttribute("pageUtil  ", pageUtil);
            req.getRequestDispatcher("meeting.jsp").forward(req,resp);
        }else if ("noticeMyMeeting".equals(action)){
            PageUtil pageUtils = new PageUtil();
            try {
                pageUtils.setPageNum(Integer.parseInt(req.getParameter("pageNums")));
                pageUtils.setPageSize(Integer.parseInt(req.getParameter("pageSizes")));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            PageUtil pageUtil = new PageUtil();
            try {
                pageUtil.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
                pageUtil.setPageSize(Integer.parseInt(req.getParameter("pageSize")));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.add(calendar.DATE, +7) ;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date d = calendar.getTime();
            Date date = new Date();
            System.out.println(sdf.format(d));
            String afterTime = sdf.format(d);
            String nowTime = sdf.format(date);
            List<Meeting> meetingList = meetingService.queryAfterMeeting(afterTime,nowTime,pageUtils);
            int counts =  meetingService.queryAfterMeetingCounts(afterTime,nowTime);
            System.out.println(counts);
            int count = meetingService.queryCancelMeetingCount();
            System.out.println(count);
            System.out.println("七天会议集合长度"+meetingList.size());
            List<Meeting> cancelMeeting = meetingService.queryCancelMeeting(pageUtil);
            System.out.println("取消会议集合长度"+cancelMeeting.size());
            pageUtils.setTotalCount(counts);
            pageUtil.setTotalCount(count);
            req.setAttribute("meetingList",meetingList);
            req.setAttribute("cancelMeeting", cancelMeeting);
            req.setAttribute("PageUtils",pageUtils);
            req.setAttribute("PageUtil",pageUtil);
            req.getRequestDispatcher("notice.jsp").forward(req,resp);
        }else if ("menuMyMeeting".equals(action)){
            String loginUser = req.getParameter("loginUser");
            System.out.println(loginUser);
            PageUtil pageMeeting = new PageUtil();
            pageMeeting.setPageNum(Integer.parseInt(req.getParameter("pageNum")));
            pageMeeting.setPageSize(Integer.parseInt(req.getParameter("pageSize")));
            String userid = meetingService.queryUserId(loginUser);
            List<Meeting> meetingList = meetingService.queryUserMeeting(userid,pageMeeting);
            pageMeeting.setTotalCount(meetingService.countUserMeeting(userid));
            req.setAttribute("meetingList", meetingList);
            req.setAttribute("pageUtil", pageMeeting);
            req.getRequestDispatcher("myMeeting.jsp").forward(req,resp);
        } else if ("showMeetingid".equals(action)) {
            String orderStarTime = req.getParameter("orderStarTime");
            String orderEndTime = req.getParameter("orderEndTime");
            List<Room> roomList = meetingService.showMeetingid(orderStarTime,orderEndTime);
            System.out.println("该时间空闲会议室的集合长度"+roomList.size());
            if (roomList!=null){
                result.setRoomList(roomList);
                result.setSuccess(true);
            }else {
                result.setSuccess(false);
                result.setMsg("改时间段没有空闲的会议室");
            }
            PrintWriter out = resp.getWriter();
            out.write(JSON.toJSONString(result));
            out.flush();
            out.close();
        }else if ("saveOrderMeeting".equals(action)){
            String orderMeetingId = req.getParameter("orderMeetingId");
           String  orderMettingName = req.getParameter("orderMettingName");
           String orderMeetingNums = req.getParameter("orderMeetingNums");
            String  orderStarTime = req.getParameter("orderStarTime");
            String  orderEndTime = req.getParameter("orderEndTime");
            String  meetingMark = req.getParameter("meetingMark");
            String  selDepartments = req.getParameter("selDepartments");
            String[] arr = req.getParameter("selSelectedEmployees").split(",");
            String  orderMeetingRoom = req.getParameter("orderMeetingRoom");
            System.out.println("预定会议的name"+orderMettingName);
            System.out.println("预定会议的name"+orderMeetingNums);
            System.out.println("预定会议的name"+orderStarTime);
            System.out.println("预定会议的name"+selDepartments);
            System.out.println("预定会议的name"+arr);
            System.out.println( "看一下"+meetingMark);
            Meeting meeting=new Meeting();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                meeting.setMeetingName(orderMettingName);
                meeting.setNums(Integer.parseInt(orderMeetingNums));
                meeting.setRoomId(orderMeetingRoom);
                meeting.setMark(meetingMark);
                meeting.setStartTime(sdf.parse(orderStarTime));
                System.out.println(sdf.parse(orderStarTime));
                System.out.println(sdf.parse(orderStarTime));
                meeting.setEndTime(sdf.parse(orderEndTime));
//                meeting.setOrderUserId(orderMeetingId);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int count= 0;
            if(meetingService.addOrderMeeting(meeting)==1){
                count++;
            }
            int meetingId=meetingService.queryByName(orderMettingName);
            for(String userid:arr){
                if(userMeetingService.add(userid,meetingId)==1){
                    count++;
                }
            }
            System.out.println("长度："+count);
            int com=arr.length+1;
            if(count==com){
                result.setSuccess(true);
                result.setMsg("新增成功");
            }else {
                result.setSuccess(false);
                result.setMsg("新增失败");
            }
            PrintWriter out=resp.getWriter();
            out.write(JSON.toJSONString(result));
            out.flush();
            out.close();
        }else if ("cancelMeeting".equals(action)){
            Integer mettingId = null;
            try {
                mettingId = Integer.parseInt( req.getParameter("mettingId"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            System.out.println("传到后台的："+mettingId);
            Meeting meeting = meetingService.getMetting(mettingId);
            result.setObj(meeting);
            if (meeting!=null){
                PrintWriter out = resp.getWriter();
                out.write(JSON.toJSONString(result));
                out.flush();
                out.close();
            }
        }else if ("updateMeeting".equals(action)){
           int meetingId = Integer.parseInt(req.getParameter("mettingId"));
           String cancelMark = req.getParameter("cancelMark");
            int count = meetingService.updateMeeting(meetingId,cancelMark);
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
        }

    }
}
