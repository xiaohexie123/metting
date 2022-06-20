package com.zr.dao.impl;

import com.zr.dao.RoomDao;
import com.zr.model.Dept;
import com.zr.model.Room;
import com.zr.util.MySQLUtil;
import com.zr.util.PageUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl implements RoomDao {
    /**
     * 分页逻辑，输出会议室
     * @param pageUtil
     * @return
     */
    @Override
    public List<Room> queryList(PageUtil pageUtil) {
        List<Room> roomList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        try{
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("select * from t_room limit ?,?");

            stm.setInt(1, (pageUtil.getPageNum()-1)*pageUtil.getPageSize());
            stm.setInt(2, pageUtil.getPageSize());
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                 Room room =new Room();
                room.setRoomId(rs.getString("roomid"));
                room.setRoomName(rs.getString("roomname"));
                room.setNums(rs.getInt("nums"));
                room.setMark(rs.getString("mark"));
                room.setStatus(rs.getString("status"));
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
     * 会议室总共条数
     * @return
     */
    @Override
    public Integer roomcount() {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("select count(roomid) as totalCount from t_room");
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
     * 添加会议室
     * @param roomId
     * @param roomName
     * @param nums
     * @param marks
     * @return
     */
    @Override
    public int addRoom(String roomId, String roomName, Integer nums, String marks) {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("insert into t_room values(?,?,?,?,?)");
            stm.setString(1,roomId);
            stm.setString(2,roomName);
            stm.setInt(3,nums);
            stm.setString(4,marks);
            stm.setString(5,"1");
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
     * 查看会议室
     * @param roomId
     * @return
     */
    @Override
    public Room seeRoom(String roomId) {
        Room room = new Room();
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("select * from t_room where roomid = ?");
            stm.setString(1,roomId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                room.setRoomId(rs.getString("roomid"));
                room.setRoomName(rs.getString("roomname"));
                room.setNums(rs.getInt("nums"));
                room.setMark(rs.getString("mark"));
                room.setStatus(rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return room;
    }

    /**\
     * 保存修改之后的会议室信息
     * @param roomId
     * @param roomName
     * @param nums
     * @param mark
     * @return
     */
    @Override
    public int saveEditRoom(String roomId, String roomName, String nums, String mark) {
        int count = 0 ;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("update t_room set roomname = ?,nums = ?,mark = ? where roomid = ?");
            stm.setString(1,roomName);
            stm.setString(2,nums);
            stm.setString(3,mark);
            stm.setString(4,roomId);
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
     * 修改会议室的状态
     * @param roomId
     * @return
     */
    @Override
    public int starRoom(String roomId) {
        int count = 0 ;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("update t_room set status = '1' where roomid = ?");
            stm.setString(1,roomId);
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
     * 关闭会议室
     * @param roomId
     * @return
     */
    @Override
    public int closeRoom(String roomId) {
        int count = 0 ;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("update t_room set status = '0' where roomid = ?");
            stm.setString(1,roomId);
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
     * 停用会议室
     * @param roomId
     * @return
     */
    @Override
    public int removeRoom(String roomId) {
        int count = 0 ;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("update t_room set status = '2' where roomid = ?");
            stm.setString(1,roomId);
            count = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return count;
    }
}
