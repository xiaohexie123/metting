package com.zr.dao.impl;

import com.zr.dao.UserMeetingDao;
import com.zr.util.MySQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserMeetingDaoImpl implements UserMeetingDao {
    /**
     * 添加员工
     * @param userid
     * @param meetingId
     * @return
     */
    @Override
    public int add(String userid, int meetingId) {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement(" insert into t_usermeeting values (?,?)");
            stm.setString(1,userid);
            stm.setInt(2,meetingId);
            count = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return count;
    }
}
