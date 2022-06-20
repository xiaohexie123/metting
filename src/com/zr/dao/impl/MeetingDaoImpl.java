package com.zr.dao.impl;

import com.zr.dao.MeetingDao;
import com.zr.model.Meeting;
import com.zr.model.Room;
import com.zr.model.User;
import com.zr.util.MeetingUtil;
import com.zr.util.MySQLUtil;
import com.zr.util.PageUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MeetingDaoImpl implements MeetingDao {
    /**
     * 通过id查询我预定的会议
     * @param currentUserId
     * @param pageUtil
     * @return
     */
    @Override
    public List<Meeting> queryListByOrderUserId(String currentUserId, PageUtil pageUtil) {
        List<Meeting> meetingList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        try{
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("SELECT m.*, r.roomname FROM t_meeting m LEFT JOIN t_room r ON m.roomid = r.roomid WHERE orderuserid = ? ORDER BY starttime DESC LIMIT ?, ?");
            stm.setString(1, currentUserId);
            stm.setInt(2, (pageUtil.getPageNum()-1)*pageUtil.getPageSize());
            stm.setInt(3, pageUtil.getPageSize());
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Meeting meeting = new Meeting();
                meeting.setMeetingId(rs.getInt("meetingId"));
                meeting.setMeetingName(rs.getString("meetingName"));
                meeting.setNums(rs.getInt("nums"));
                meeting.setStartTime(rs.getTimestamp("startTime"));
                meeting.setEndTime(rs.getTimestamp("endTime"));
                meeting.setRoomId(rs.getString("roomId"));
                Room room = new Room();
                room.setRoomId(rs.getString("roomId"));
                room.setRoomName(rs.getString("roomName"));
                meeting.setRoom(room);
                meeting.setMark(rs.getString("mark"));
                meeting.setOrderTime(rs.getTimestamp("orderTime"));
                meeting.setOrderUserId(rs.getString("orderUserId"));
                meeting.setStatus(rs.getString("status"));
                meeting.setCancelTime(rs.getTime("cancelTime"));
                meeting.setCancelMark(rs.getString("cancelMark"));
                meetingList.add(meeting);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return meetingList;
    }

    /**
     * 我预定会议总共条数
     * @param currentUserId
     * @return
     */
    @Override
    public Integer count(String currentUserId) {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("select count(*) as totalCount from t_meeting where orderuserid = ?");
            stm.setString(1, currentUserId);
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                count = rs.getInt("totalCount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return count;
    }

    /**
     * 按照分页返回会议
     * @param pageMeeting
     * @return
     */
    @Override
    public List<Meeting> queryMeeting(PageUtil pageMeeting) {
        List<Meeting> meetingList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        try{
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("SELECT m.*, r.roomname FROM t_meeting m LEFT JOIN t_room r ON m.roomid = r.roomid LIMIT ?, ?");
            stm.setInt(1, (pageMeeting.getPageNum()-1)*pageMeeting.getPageSize());
            stm.setInt(2, pageMeeting.getPageSize());
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Meeting meeting = new Meeting();
                meeting.setMeetingId(rs.getInt("meetingId"));
                meeting.setMeetingName(rs.getString("meetingName"));
                meeting.setNums(rs.getInt("nums"));
                meeting.setStartTime(rs.getTimestamp("startTime"));
                meeting.setEndTime(rs.getTimestamp("endTime"));
                meeting.setRoomId(rs.getString("roomId"));
                Room room = new Room();
                room.setRoomId(rs.getString("roomId"));
                room.setRoomName(rs.getString("roomName"));
                meeting.setRoom(room);
                meeting.setMark(rs.getString("mark"));
                meeting.setOrderTime(rs.getTimestamp("orderTime"));
                meeting.setOrderUserId(rs.getString("orderUserId"));
                meeting.setStatus(rs.getString("status"));
                meeting.setCancelTime(rs.getTime("cancelTime"));
                meeting.setCancelMark(rs.getString("cancelMark"));
                meetingList.add(meeting);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return meetingList;
    }

    /**
     * 会议的总条数
     * @return
     */
    @Override
    public Integer countMeeting() {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("select count(*) as totalCount from t_meeting ");

            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                count = rs.getInt("totalCount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return count;
    }

    /**
     * 根据meetingid获取会议的全部信息
     * @param mettingId
     * @return
     */
    @Override
    public  Meeting getMetting(Integer mettingId) {
        Meeting meeting = new Meeting();
        Connection conn = null;
        PreparedStatement stm = null;
        try{
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("SELECT m.*, r.roomname FROM t_meeting m LEFT JOIN t_room r ON m.roomid = r.roomid where m.meetingid = ?");
            stm.setInt(1,mettingId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()){
                meeting.setMeetingId(rs.getInt("meetingId"));
                meeting.setMeetingName(rs.getString("meetingName"));
                meeting.setNums(rs.getInt("nums"));
                meeting.setStartTime(rs.getTimestamp("startTime"));
                meeting.setEndTime(rs.getTimestamp("endTime"));
                meeting.setRoomId(rs.getString("roomId"));
                Room room = new Room();
                room.setRoomId(rs.getString("roomId"));
                room.setRoomName(rs.getString("roomName"));
                meeting.setRoom(room);
                meeting.setMark(rs.getString("mark"));
                meeting.setOrderTime(rs.getTimestamp("orderTime"));
                meeting.setOrderUserId(rs.getString("orderUserId"));
                meeting.setStatus(rs.getString("status"));
                meeting.setCancelTime(rs.getTime("cancelTime"));
                meeting.setCancelMark(rs.getString("cancelMark"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return meeting;
    }

    /**
     * 查询参加该会议的员工信息
     * @param mettingId
     * @return
     */
    @Override
    public List<User> getUser(Integer mettingId) {
        List<User> userList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("SELECT username,tel,email from t_user where userid in (SELECT userid from t_usermeeting where meetingid = ?) ");
            stm.setInt(1,mettingId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserName(rs.getString("username"));
                user.setTel(rs.getString("tel"));
                user.setEmail(rs.getString("email"));
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return userList;
    }

    /**
     * 通过开始、预定时间来查询会议
     * @param meetingName
     * @param roomName
     * @param userId
     * @param startLine
     * @param endLine
     * @param startMeeting
     * @param endMeeting
     * @param pageUtil
     * @return
     */
    @Override
    public List<Meeting> queryTime(String meetingName, String roomName, String userId, String startLine, String endLine, String startMeeting, String endMeeting,PageUtil pageUtil) {
        List<Meeting> meetingList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        if (meetingName==null){
            meetingName = "";
        }
        if (roomName==null){
            roomName = "";
        }
        try{
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("SELECT m.*, r.roomname FROM t_meeting m LEFT JOIN t_room r ON m.roomid = r.roomid WHERE m.meetingname LIKE ? AND r.roomname LIKE ? AND m.orderuserid like ? AND (m.ordertime > ? AND m.ordertime  < ?)AND (m.starttime > ?)AND (m.endtime < ?) LIMIT ?, ? ");
            stm.setString(1,'%'+meetingName+'%');
            stm.setString(2,'%'+roomName+'%');
            stm.setString(3,'%'+userId+'%');
            stm.setString(4,startLine);
            stm.setString(5,endLine);
            stm.setString(6,startMeeting);
            stm.setString(7,endMeeting);
            stm.setInt(8, (pageUtil.getPageNum()-1)*pageUtil.getPageSize());
            stm.setInt(9, pageUtil.getPageSize());
            System.out.println(startLine);
            System.out.println(startLine);
            System.out.println(endLine);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Meeting meeting = new Meeting();
                meeting.setMeetingId(rs.getInt("meetingId"));
                meeting.setMeetingName(rs.getString("meetingName"));
                Room room = new Room();
                room.setRoomId(rs.getString("roomid"));
                room.setRoomName(rs.getString("roomname"));
                meeting.setRoom(room);
                meeting.setStartTime(rs.getTimestamp("starttime"));
                meeting.setEndTime(rs.getTimestamp("endtime"));
                meeting.setOrderTime(rs.getTimestamp("ordertime"));
                meeting.setOrderUserId(rs.getString("orderuserid"));
                meetingList.add(meeting);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return meetingList;
    }

    /**
     * 通过员工姓名来查找员工id
     * @param userName
     * @return
     */
    @Override
    public String queryId(String userName) {
        String id = "";
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("SELECT userid FROM t_user WHERE username LIKE ? ");
            stm.setString(1,userName);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                id = rs.getString("userid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return id;
    }

    @Override
    public Integer countTime() {
        return null;
    }

    /**
     * 获取当前天数，显示七天后的会议，并分页返回
     * @param afterTime
     * @param nowTime
     * @param pageUtils
     * @return
     */
    @Override
    public List<Meeting> queryAfterMeeting(String afterTime,String nowTime,PageUtil pageUtils) {
        List<Meeting> meetingList =  new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        try{
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("SELECT m.*, r.roomname FROM t_meeting m LEFT JOIN t_room r ON m.roomid = r.roomid where m.starttime > ? and m.starttime < ? or m.endtime < ? or m.starttime < ? and m.endtime > ? LIMIT ?, ?");

            stm.setString(1,nowTime);
            stm.setString(2,afterTime);
            stm.setString(3,afterTime);
            stm.setString(4,nowTime);
            stm.setString(5,afterTime);
            stm.setInt(6, (pageUtils.getPageNum()-1)*pageUtils.getPageSize());
            stm.setInt(7, pageUtils.getPageSize());
            System.out.println(nowTime);
            System.out.println(afterTime);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Meeting meeting = new Meeting();
                meeting.setMeetingId(rs.getInt("meetingId"));
                meeting.setMeetingName(rs.getString("meetingName"));
                meeting.setNums(rs.getInt("nums"));
                meeting.setStartTime(rs.getTimestamp("startTime"));
                meeting.setEndTime(rs.getTimestamp("endTime"));
                meeting.setRoomId(rs.getString("roomId"));
                Room room = new Room();
                room.setRoomId(rs.getString("roomId"));
                room.setRoomName(rs.getString("roomName"));
                meeting.setRoom(room);
                meeting.setMark(rs.getString("mark"));
                meeting.setOrderTime(rs.getTimestamp("orderTime"));
                meeting.setOrderUserId(rs.getString("orderUserId"));
                meeting.setStatus(rs.getString("status"));
                meeting.setCancelTime(rs.getTime("cancelTime"));
                meeting.setCancelMark(rs.getString("cancelMark"));
                meetingList.add(meeting);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return meetingList;
    }

    /**
     * 查询已取消的会议
     * @param pageUtil
     * @return
     */
    @Override
    public List<Meeting> queryCancelMeeting(PageUtil pageUtil) {
        List<Meeting> meetingList =  new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        try{
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("SELECT m.*, r.roomname FROM t_meeting m LEFT JOIN t_room r ON m.roomid = r.roomid where m.status = '0' LIMIT ?, ?" );
            stm.setInt(1, (pageUtil.getPageNum()-1)*pageUtil.getPageSize());
            stm.setInt(2, pageUtil.getPageSize());
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Meeting meeting = new Meeting();
                meeting.setMeetingId(rs.getInt("meetingId"));
                meeting.setMeetingName(rs.getString("meetingName"));
                meeting.setNums(rs.getInt("nums"));
                meeting.setStartTime(rs.getTimestamp("startTime"));
                meeting.setEndTime(rs.getTimestamp("endTime"));
                meeting.setRoomId(rs.getString("roomId"));
                Room room = new Room();
                room.setRoomId(rs.getString("roomId"));
                room.setRoomName(rs.getString("roomName"));
                meeting.setRoom(room);
                meeting.setMark(rs.getString("mark"));
                meeting.setOrderTime(rs.getTimestamp("orderTime"));
                meeting.setOrderUserId(rs.getString("orderUserId"));
                meeting.setStatus(rs.getString("status"));
                meeting.setCancelTime(rs.getTime("cancelTime"));
                meeting.setCancelMark(rs.getString("cancelMark"));
                meetingList.add(meeting);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return meetingList;
    }

    @Override
    public String queryUserId(String loginUser) {
        String id = "";
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("SELECT userid FROM t_user WHERE username = ? ");
            stm.setString(1,loginUser);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                id = rs.getString("userid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return id;
    }

    /**
     * 查询我的会议
     * @param userid
     * @param pageUtil
     * @return
     */
    @Override
    public List<Meeting> queryUserMeeting(String userid,PageUtil pageUtil) {
        List<Meeting> meetingList =  new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        try{
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("SELECT m.*, r.roomname FROM t_meeting m LEFT JOIN t_room r ON m.roomid = r.roomid where m.meetingid in(SELECT meetingid from t_usermeeting where userid = ?)LIMIT ?, ? ");
            stm.setString(1,userid);
            stm.setInt(2, (pageUtil.getPageNum()-1)*pageUtil.getPageSize());
            stm.setInt(3, pageUtil.getPageSize());
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Meeting meeting = new Meeting();
                meeting.setMeetingId(rs.getInt("meetingId"));
                meeting.setMeetingName(rs.getString("meetingName"));
                meeting.setNums(rs.getInt("nums"));
                meeting.setStartTime(rs.getTimestamp("startTime"));
                meeting.setEndTime(rs.getTimestamp("endTime"));
                meeting.setRoomId(rs.getString("roomId"));
                Room room = new Room();
                room.setRoomId(rs.getString("roomId"));
                room.setRoomName(rs.getString("roomName"));
                meeting.setRoom(room);
                meeting.setMark(rs.getString("mark"));
                meeting.setOrderTime(rs.getTimestamp("orderTime"));
                meeting.setOrderUserId(rs.getString("orderUserId"));
                meeting.setStatus(rs.getString("status"));
                meeting.setCancelTime(rs.getTime("cancelTime"));
                meeting.setCancelMark(rs.getString("cancelMark"));
                meetingList.add(meeting);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return meetingList;
    }

    /**
     * 我的会议总共条数
     * @param userid
     * @return
     */
    @Override
    public Integer countUserMeeting(String userid) {
        List<Meeting> meetingList =  new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        try{
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("SELECT m.*, r.roomname FROM t_meeting m LEFT JOIN t_room r ON m.roomid = r.roomid where m.meetingid in(SELECT meetingid from t_usermeeting where userid = ?) ");
            stm.setString(1,userid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Meeting meeting = new Meeting();
                meeting.setMeetingId(rs.getInt("meetingId"));
                meeting.setMeetingName(rs.getString("meetingName"));
                meeting.setNums(rs.getInt("nums"));
                meeting.setStartTime(rs.getTimestamp("startTime"));
                meeting.setEndTime(rs.getTimestamp("endTime"));
                meeting.setRoomId(rs.getString("roomId"));
                Room room = new Room();
                room.setRoomId(rs.getString("roomId"));
                room.setRoomName(rs.getString("roomName"));
                meeting.setRoom(room);
                meeting.setMark(rs.getString("mark"));
                meeting.setOrderTime(rs.getTimestamp("orderTime"));
                meeting.setOrderUserId(rs.getString("orderUserId"));
                meeting.setStatus(rs.getString("status"));
                meeting.setCancelTime(rs.getTime("cancelTime"));
                meeting.setCancelMark(rs.getString("cancelMark"));
                meetingList.add(meeting);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return meetingList.size();
    }

    /**
     * 通过开始结束时间查询会议室
     * @param orderStarTime
     * @param orderEndTime
     * @return
     */
    @Override
    public List<Room> showMeetingid(String orderStarTime, String orderEndTime) {
        List<Room> roomList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        try{
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("SELECT DISTINCT r.* FROM t_room r  LEFT JOIN t_meeting m ON r.roomid = m.roomid WHERE r.status = '1' and endtime < ? or starttime > ? OR starttime is NULL and endtime is NULL");
            stm.setString(1, orderStarTime);
            stm.setString(2, orderEndTime);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Room room = new Room();
                room.setRoomId(rs.getString("roomId"));
                room.setRoomName(rs.getString("roomName"));
                room.setNums(rs.getInt("nums"));
                roomList.add(room);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return roomList;
    }

    /**
     * 添加预定的会议
     * @param meeting
     * @return
     */
    @Override
    public int addOrderMeeting(Meeting meeting) {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("dao层"+meeting.getMark());
        Date date = new Date();
        String nowDate = sdf.format(date);
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("insert into t_meeting(meetingname,nums,starttime,endtime,roomid,mark,ordertime,orderuserid,status) values (?,?,?,?,?,?,?,'admin',1)");
            stm.setString(1, meeting.getMeetingName());
            stm.setInt(2,meeting.getNums());
            stm.setString(3,sdf.format(meeting.getStartTime()));
            stm.setString(4,sdf.format(meeting.getEndTime()));
            stm.setString(5,meeting.getRoomId());
            stm.setString(6,meeting.getMark());
            stm.setString(7,nowDate);
            count = stm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return count;
    }

    /**
     * 通过会议室的name来查找id
     * @param orderMettingName
     * @return
     */
    @Override
    public int queryByName(String orderMettingName) {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("select * from t_meeting where meetingname=?");
            stm.setString(1,orderMettingName);
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                Meeting meeting=new Meeting();
                meeting.setMeetingId(rs.getInt("meetingid"));
                return meeting.getMeetingId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return 0;
    }

    /**\
     *取消会议
     * @param meetingId
     * @param cancelMark
     * @return
     */
    @Override
    public int updateMeeting(int meetingId, String cancelMark) {
        int count = 0 ;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("update t_meeting set cancelmark = ?, status = '0' where meetingid = ?");
            stm.setString(1,cancelMark);
            stm.setInt(2,meetingId);
            count = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return count;
    }

    /**
     * 查找改时间端空闲会议室
     * @param afterTime
     * @param nowTime
     * @return
     */
    @Override
    public int queryAfterMeetingCounts(String afterTime, String nowTime) {
        List<Meeting> meetingList =  new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        try{
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("SELECT m.*, r.roomname FROM t_meeting m LEFT JOIN t_room r ON m.roomid = r.roomid where m.starttime > ? and m.starttime < ? or m.endtime < ? or m.starttime < ? and m.endtime > ?");

            stm.setString(1,nowTime);
            stm.setString(2,afterTime);
            stm.setString(3,afterTime);
            stm.setString(4,nowTime);
            stm.setString(5,afterTime);
            System.out.println(nowTime);
            System.out.println(afterTime);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Meeting meeting = new Meeting();
                meeting.setMeetingId(rs.getInt("meetingId"));
                meeting.setMeetingName(rs.getString("meetingName"));
                meeting.setNums(rs.getInt("nums"));
                meeting.setStartTime(rs.getTimestamp("startTime"));
                meeting.setEndTime(rs.getTimestamp("endTime"));
                meeting.setRoomId(rs.getString("roomId"));
                Room room = new Room();
                room.setRoomId(rs.getString("roomId"));
                room.setRoomName(rs.getString("roomName"));
                meeting.setRoom(room);
                meeting.setMark(rs.getString("mark"));
                meeting.setOrderTime(rs.getTimestamp("orderTime"));
                meeting.setOrderUserId(rs.getString("orderUserId"));
                meeting.setStatus(rs.getString("status"));
                meeting.setCancelTime(rs.getTime("cancelTime"));
                meeting.setCancelMark(rs.getString("cancelMark"));
                meetingList.add(meeting);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return meetingList.size();
    }

    /**
     * 查询取消会议的条数
     * @return
     */
    @Override
    public int queryCancelMeetingCount() {
        List<Meeting> meetingList =  new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        try{
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("SELECT m.*, r.roomname FROM t_meeting m LEFT JOIN t_room r ON m.roomid = r.roomid where m.status = '0'" );
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                Meeting meeting = new Meeting();
                meeting.setMeetingId(rs.getInt("meetingId"));
                meeting.setMeetingName(rs.getString("meetingName"));
                meeting.setNums(rs.getInt("nums"));
                meeting.setStartTime(rs.getTimestamp("startTime"));
                meeting.setEndTime(rs.getTimestamp("endTime"));
                meeting.setRoomId(rs.getString("roomId"));
                Room room = new Room();
                room.setRoomId(rs.getString("roomId"));
                room.setRoomName(rs.getString("roomName"));
                meeting.setRoom(room);
                meeting.setMark(rs.getString("mark"));
                meeting.setOrderTime(rs.getTimestamp("orderTime"));
                meeting.setOrderUserId(rs.getString("orderUserId"));
                meeting.setStatus(rs.getString("status"));
                meeting.setCancelTime(rs.getTime("cancelTime"));
                meeting.setCancelMark(rs.getString("cancelMark"));
                meetingList.add(meeting);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return meetingList.size();
    }

}



