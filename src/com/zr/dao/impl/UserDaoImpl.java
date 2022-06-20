package com.zr.dao.impl;

import com.zr.dao.UserDao;
import com.zr.model.User;
import com.zr.util.MySQLUtil;
import com.zr.util.PageUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    /**
     * 登录逻辑，判断账号密码是否正确
     * @param userId
     * @param password
     * @return
     */
    @Override
    public User login(String userId, String password) {
        User user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("select * from t_user where userid = ? and password = ? and status = 1");
            stm.setString(1, userId);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getString("userId"));
                user.setUserName(rs.getString("userName"));
                user.setPassword(rs.getString("password"));
                user.setDeptId(rs.getInt("deptId"));
                user.setTel(rs.getString("tel"));
                user.setEmail(rs.getString("email"));
                user.setUserType(rs.getString("userType"));
                user.setStatus(rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return user;
    }

    /**
     *添加员工
     * @param id
     * @param name
     * @param password
     * @param dept
     * @param tel
     * @param email
     * @param usertype
     * @param status
     * @return
     */
    @Override
    public int add(String id, String name, String password, int dept, String tel, String email,String usertype,String status) {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("insert into t_user values(?,?,?,?,?,?,?,?)");
            stm.setString(1,id);
            stm.setString(2,name);
            stm.setString(3,password);
            stm.setInt(4,dept);
           stm.setString(5,tel);
           stm.setString(6,email);
           stm.setString(7,usertype);
           stm.setString(8,status);
            count = stm.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return count;
    }

    /**
     * 翻页逻辑，
     * @param pageUtil
     * @return
     */
    @Override
    public  List<User> queryList(PageUtil pageUtil) {
        List<User> userList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        try {

            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("select * from t_user where status= '0' limit ?,?");
            stm.setInt(1, (pageUtil.getPageNum()-1)*pageUtil.getPageSize());
            stm.setInt(2, pageUtil.getPageSize());
            ResultSet rs =stm.executeQuery();
            while (rs.next()){
                User user=new User();
                user.setUserId(rs.getString("userid"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setDeptId(rs.getInt("deptid"));
                user.setTel(rs.getString("tel"));
                user.setEmail(rs.getString("email"));
                user.setUserType(rs.getString("usertype"));
                user.setStatus(rs.getString("status"));
                userList.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return userList ;
    }

    /**
     * 查询总共条数
     * @return
     */
    @Override
    public Integer count() {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("select count(userid) as totalCount from t_user where status= '0'");
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
     * 注册审批
     * @param userid
     * @return
     */
    @Override
    public int edit(String userid) {
        int count = 0 ;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("update t_user set status = '1' where userid = ?");
            stm.setString(1,userid);
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
     * 删除员工
     * @param userId
     * @return
     */
    @Override
    public int remove(String userId) {
        int count = 0 ;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            System.out.println(userId);
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("delete from t_user where userid = ?");
            stm.setString(1,userId);
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
     * 把该状态员工按照分页输出
     * @param pageUtils
     * @param status
     * @param userId
     * @param userName
     * @return
     */
    @Override
    public List<User> queryUser(PageUtil pageUtils, String status, String userId, String userName) {
        System.out.println("我是dao层");
        System.out.println("dao层状态"+status);
        System.out.println("dao层状态"+userId);
        if (userId==null&&userName==null){
            userId="";
            userName="";
        }
        List<User> userList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("select * from t_user where usertype = '0' and userid like ?  and username like ? and status= ?  limit ?,?");
            stm.setString(1,"%"+ userId+"%");
            stm.setString(2,"%"+userName+"%");
            stm.setString(3,status);
            stm.setInt(4, (pageUtils.getPageNum()-1)*pageUtils.getPageSize());
            stm.setInt(5, pageUtils.getPageSize());
            ResultSet rs =stm.executeQuery();
            while (rs.next()){
                User user=new User();
                user.setUserId(rs.getString("userid"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setDeptId(rs.getInt("deptid"));
                user.setTel(rs.getString("tel"));
                user.setEmail(rs.getString("email"));
                user.setUserType(rs.getString("usertype"));
                user.setStatus(rs.getString("status"));
                userList.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return userList ;
    }

    /**
     * 该状态所有员工
     * @param status
     * @param userId
     * @param userName
     * @return
     */
    @Override
    public Integer countUser(String status, String userId, String userName) {
        if (userId==null&&userName==null){
            userId="";
            userName="";
        }
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("select count(userid) as totalCount from t_user where usertype = '0' and userid like ?  and username like ? and status= ?");
            stm.setString(1,"%"+ userId+"%");
            stm.setString(2,"%"+userName+"%");
            stm.setString(3,status);
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
     * 关闭用户账号
     * @param userId
     * @return
     */
    @Override
    public int userClose(String userId) {
        int count = 0 ;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("update t_user set status = '2' where userid = ?");
            stm.setString(1,userId);
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
     * 查询部门id
     * @param deptId
     * @return
     */
    @Override
    public List<User> selectListByDeptId(Integer deptId) {
        List<User> userList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("select * from t_user where deptid = ? ");
            stm.setInt(1, deptId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("userId"));
                user.setUserName(rs.getString("userName"));
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


}

