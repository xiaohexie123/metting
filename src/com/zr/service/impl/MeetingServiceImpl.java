package com.zr.service.impl;

import com.zr.dao.MeetingDao;
import com.zr.dao.impl.MeetingDaoImpl;
import com.zr.model.Meeting;
import com.zr.model.Room;
import com.zr.model.User;
import com.zr.service.MeetingService;
import com.zr.util.MeetingUtil;
import com.zr.util.PageUtil;

import java.util.Date;
import java.util.List;

public class MeetingServiceImpl implements MeetingService {

    private MeetingDao meetingDao = new MeetingDaoImpl();

    @Override
    public List<Meeting> queryListByOrderUserId(String currentUserId, PageUtil pageUtil) {
        return meetingDao.queryListByOrderUserId(currentUserId, pageUtil);
    }

    @Override
    public Integer count(String currentUserId) {
        return meetingDao.count(currentUserId);
    }

    @Override
    public List<Meeting> queryMeeting(PageUtil pageMeeting) {
        return meetingDao.queryMeeting(pageMeeting);
    }

    @Override
    public Integer countMeeting() {
        return meetingDao.countMeeting();
    }

    @Override
    public Meeting getMetting(Integer mettingId) {
        return meetingDao.getMetting(mettingId);
    }

    @Override
    public List<User> getUser(Integer mettingId) {
        return meetingDao.getUser(mettingId);
    }

    @Override
    public List<Meeting> queryTime(String meetingName, String roomName, String userName, String startLine, String endLine, String startMeeting, String endMeeting, PageUtil pageUtil) {
        return meetingDao.queryTime(meetingName,roomName,userName,startLine,endLine,startMeeting,endMeeting,pageUtil);
    }

    @Override
    public String queryId(String userName) {
        return meetingDao.queryId(userName);
    }

    @Override
    public Integer countTime() {
        return meetingDao.countTime();
    }

    @Override
    public List<Meeting> queryAfterMeeting(String afterTime,String nowTime,PageUtil pageUtils) {
        return meetingDao.queryAfterMeeting(afterTime,nowTime,pageUtils);
    }

    @Override
    public List<Meeting> queryCancelMeeting(PageUtil pageUtil) {
        return meetingDao.queryCancelMeeting(pageUtil);
    }

    @Override
    public String queryUserId(String loginUser) {
        return meetingDao.queryUserId(loginUser);
    }

    @Override
    public List<Meeting> queryUserMeeting(String userid,PageUtil pageUtil) {
        return meetingDao.queryUserMeeting(userid,pageUtil);
    }

    @Override
    public Integer countUserMeeting(String userid) {
        return meetingDao.countUserMeeting(userid);
    }

    @Override
    public List<Room> showMeetingid(String orderStarTime, String orderEndTime) {
        return meetingDao.showMeetingid(orderStarTime,orderEndTime);
    }

    @Override
    public int addOrderMeeting(Meeting meeting) {
        return meetingDao.addOrderMeeting(meeting);
    }

    @Override
    public int queryByName(String orderMettingName) {
        return meetingDao.queryByName(orderMettingName);
    }

    @Override
    public int updateMeeting(int meetingId, String cancelMark) {
        return meetingDao.updateMeeting(meetingId,cancelMark);
    }

    @Override
    public int queryAfterMeetingCounts(String afterTime, String nowTime) {
        return meetingDao.queryAfterMeetingCounts(afterTime,nowTime);
    }

    @Override
    public int queryCancelMeetingCount() {
        return meetingDao.queryCancelMeetingCount();
    }


}
