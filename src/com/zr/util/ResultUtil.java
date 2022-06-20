package com.zr.util;

import com.zr.model.Meeting;
import com.zr.model.Room;
import com.zr.model.User;

import java.util.List;

public class ResultUtil {
	
	//本此操作结果，true为成功，false为失败
	private boolean success;

	private List<User> userList;

	private List<Room> roomList;

	//返回个前台的消息
	private String msg;

	private List<Meeting> meetings;
	//备用，传给前台任意对象
	private Object obj;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Meeting> getMeetings() {
		return meetings;
	}

	public void setMeetings(List<Meeting> meetings) {
		this.meetings = meetings;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public List<Room> getRoomList() {
		return roomList;
	}

	public void setRoomList(List<Room> roomList) {
		this.roomList = roomList;
	}
}
