package com.zr.service.impl;

import com.zr.dao.DeptDao;
import com.zr.dao.impl.DeptDaoImpl;
import com.zr.model.Dept;
import com.zr.service.DeptService;
import com.zr.util.PageUtil;

import java.util.List;

public class DeptServiceImpl implements DeptService {
    private DeptDao deptDao = new DeptDaoImpl();
    @Override
    public List<Dept> queryList(PageUtil pageUtil) {
        return deptDao.queryList(pageUtil);
    }

    @Override
    public Integer count() {
        return deptDao.count();
    }

    @Override
    public Dept queryDept(int deptid) {
        return deptDao.querydept(deptid);
    }

    @Override
    public int saveEdit(int deptId, String deptName) {
        return deptDao.saveEdit(deptId,deptName);
    }

    @Override
    public int add(String addName) {
        return deptDao.add(addName);
    }

    @Override
    public int remove(int removeId) {
        return deptDao.remove(removeId);
    }

    @Override
    public List<Dept> query() {
        return deptDao.query();
    }




}
