package com.zr.dao;

import com.zr.model.User;
import com.zr.util.PageUtil;

import java.util.List;

public interface UserDao {
    User login(String userId, String password);

    int add(String id, String name, String password, int dept, String tel, String email,String usertype,String status);



    List<User> queryList(PageUtil pageUtil);

    Integer count();

    int edit(String userid);

    int remove(String userId);

    List<User> queryUser(PageUtil pageUtils, String status, String userId, String userName);

    Integer countUser(String status, String userId, String userName);

    int userClose(String userId);

    List<User> selectListByDeptId(Integer deptId);
}
