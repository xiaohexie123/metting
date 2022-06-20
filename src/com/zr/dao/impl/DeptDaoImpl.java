package com.zr.dao.impl;

import com.zr.dao.DeptDao;
import com.zr.model.Dept;
import com.zr.model.Meeting;
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

public class DeptDaoImpl implements DeptDao {
    /**
     * 查询部门表
     * @param pageUtil
     * @return
     */
    @Override
    public List<Dept> queryList(PageUtil pageUtil) {
        List<Dept> deptList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        try{
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("select * from t_dept limit ?,?");

            stm.setInt(1, (pageUtil.getPageNum()-1)*pageUtil.getPageSize());
            stm.setInt(2, pageUtil.getPageSize());
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Dept dept =new Dept();
                dept.setDeptId(rs.getInt("deptid"));
                dept.setDeptName(rs.getString("deptname"));
                deptList.add(dept);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return deptList;
    }

    /**
     * 部门的总条数
     * @return
     */
    @Override
    public Integer count() {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("select count(deptid) as totalCount from t_dept");
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
     * 通过id查找部门信息
     * @param deptid
     * @return
     */
    @Override
    public Dept querydept(int deptid) {
        Connection conn = null;
        PreparedStatement stm = null;
        Dept dept =new Dept();
        try{
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("select * from t_dept where deptid = ?");
            stm.setInt(1,deptid);
            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                dept.setDeptId(rs.getInt("deptid"));
                dept.setDeptName(rs.getString("deptname"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return dept;
    }

    /**
     * 保存修改后的部门信息
     * @param deptId
     * @param deptName
     * @return
     */
    @Override
    public int saveEdit(int deptId, String deptName) {
        int count = 0 ;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("update t_dept set deptname = ? where deptid = ?");
            stm.setString(1,deptName);
            stm.setInt(2,deptId);
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
     * 添加部门信息
     * @param addName
     * @return
     */
    @Override
    public int add(String addName) {
        int count = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("insert into t_dept(deptname) values(?)");
           stm.setString(1,addName);
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
     * 删除部门
     * @param removeId
     * @return
     */
    @Override
    public int remove(int removeId) {
        int count = 0 ;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("delete from t_dept where deptid = ?");
            stm.setInt(1,removeId);
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
     * 查找所有部门
     * @return
     */
    @Override
    public List<Dept> query() {
        List<Dept> deptList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        try{
            conn = MySQLUtil.getConnection();
            stm = conn.prepareStatement("select * from t_dept ");
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Dept dept =new Dept();
                dept.setDeptId(rs.getInt("deptid"));
                dept.setDeptName(rs.getString("deptname"));
                deptList.add(dept);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MySQLUtil.closePreparedStatement(stm);
            MySQLUtil.closeConnection(conn);
        }
        return deptList;
    }




}
