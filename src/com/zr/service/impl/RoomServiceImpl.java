package com.zr.service.impl;

import com.zr.dao.RoomDao;
import com.zr.dao.impl.RoomDaoImpl;
import com.zr.model.Dept;
import com.zr.model.Room;
import com.zr.service.RoomService;
import com.zr.util.PageUtil;

import java.util.List;

public class RoomServiceImpl implements RoomService {
    private RoomDao roomDao = new RoomDaoImpl();
    @Override
    public List<Room> queryList(PageUtil pageUtil) {
        return roomDao.queryList(pageUtil);
    }

    @Override
    public Integer roomcount() {
        return roomDao.roomcount();
    }

    @Override
    public int addRoom(String roomId, String roomName, Integer nums, String marks) {
        return roomDao.addRoom(roomId,roomName,nums,marks);
    }

    @Override
    public Room seeRoom(String roomId) {
        return roomDao.seeRoom(roomId);
    }

    @Override
    public int saveEditRoom(String roomId, String roomName, String nums, String mark) {
        return roomDao.saveEditRoom(roomId,roomName,nums,mark);
    }

    @Override
    public int starRoom(String roomId) {
        return roomDao.starRoom(roomId);
    }

    @Override
    public int closeRoom(String roomId) {
        return roomDao.closeRoom(roomId);
    }

    @Override
    public int removeRoom(String roomId) {
        return roomDao.removeRoom(roomId);
    }
}
