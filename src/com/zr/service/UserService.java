package com.zr.service;

import com.zr.model.User;
import com.zr.util.PageUtil;

import java.util.List;

public interface UserService {
    User login(String userId, String password);

    int add(String id, String name, String password, int dept, String tel, String email,String usertype,String status);



    List<User> queryPage(PageUtil pageUtil);

    Integer count();

    int edit(String userid);

    int remove(String userId);

    List<User> queryUser(PageUtil pageUtils, String status, String userId, String userName);

    Integer usercount(String status, String userId, String userName);

    int userClose(String userId);

    List<User> selectListByDeptId(Integer deptId);
}
