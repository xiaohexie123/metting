package com.zr.service;

import com.zr.model.Dept;
import com.zr.model.Room;
import com.zr.util.PageUtil;

import java.util.List;

public interface RoomService {
    List<Room> queryList(PageUtil pageUtil);

    Integer roomcount();

    int addRoom(String roomId, String roomName, Integer nums, String marks);

    Room seeRoom(String roomId);

    int saveEditRoom(String roomId, String roomName, String nums, String mark);

    int starRoom(String roomId);

    int closeRoom(String roomId);

    int removeRoom(String roomId);
}
