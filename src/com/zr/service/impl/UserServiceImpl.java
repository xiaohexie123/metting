package com.zr.service.impl;

import com.zr.dao.UserDao;
import com.zr.dao.impl.UserDaoImpl;
import com.zr.model.User;
import com.zr.service.UserService;
import com.zr.util.PageUtil;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String userId, String password) {
        return userDao.login(userId, password);
    }

    @Override
    public int add(String id, String name, String password, int dept, String tel, String email,String usertype,String status) {
        return userDao.add(id,name,password,dept,tel,email,usertype,status);
    }



    @Override
    public List<User> queryPage(PageUtil pageUtil) {
        return userDao.queryList(pageUtil);
    }

    @Override
    public Integer count() {
        return userDao.count();
    }

    @Override
    public int edit(String userid) {
        return userDao.edit(userid);
    }

    @Override
    public int remove(String userId) {
        return userDao.remove(userId);
    }

    @Override
    public List<User> queryUser(PageUtil pageUtils, String status, String userId, String userName) {
        return userDao.queryUser(pageUtils,status,userId,userName);
    }

    @Override
    public Integer usercount(String status, String userId, String userName) {
        return userDao.countUser(status,userId,userName);
    }

    @Override
    public int userClose(String userId) {
        return userDao.userClose(userId);
    }

    @Override
    public List<User> selectListByDeptId(Integer deptId) {
        return userDao.selectListByDeptId(deptId);
    }


}
