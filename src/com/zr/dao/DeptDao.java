package com.zr.dao;

import com.zr.model.Dept;
import com.zr.util.PageUtil;

import java.util.List;

public interface DeptDao {
    List<Dept> queryList(PageUtil pageUtil);

    Integer count();

    Dept querydept(int deptid);

    int saveEdit(int deptId, String deptName);

    int add(String addName);

    int remove(int removeId);

    List<Dept> query();


}
