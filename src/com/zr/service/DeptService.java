package com.zr.service;

import com.zr.model.Dept;
import com.zr.util.PageUtil;

import java.util.List;

public interface DeptService {
    List<Dept> queryList(PageUtil pageUtil);

    Integer count();

    Dept queryDept(int deptid);

    int saveEdit(int parseInt, String deptName);

    int add(String addName);

    int remove(int parseInt);

    List<Dept> query();


}
