package com.zr.dao;

import com.zr.model.Meeting;
import com.zr.model.Room;
import com.zr.model.User;
import com.zr.util.MeetingUtil;
import com.zr.util.PageUtil;

import java.util.Date;
import java.util.List;

public interface MeetingDao {
    List<Meeting> queryListByOrderUserId(String currentUserId, PageUtil pageUtil);

    Integer count(String currentUserId);

    List<Meeting> queryMeeting(PageUtil pageMeeting);

    Integer countMeeting();

    Meeting getMetting(Integer mettingId);

    List<User> getUser(Integer mettingId);

    

    List<Meeting> queryTime(String meetingName, String roomName, String userId, String startLine, String endLine, String startMeeting, String endMeeting,PageUtil pageUtil);

    String queryId(String userName);


    Integer countTime();

    List<Meeting> queryAfterMeeting(String afterTime,String nowTime,PageUtil pageUtils);

    List<Meeting> queryCancelMeeting(PageUtil pageUtil);

    String queryUserId(String loginUser);

    List<Meeting> queryUserMeeting(String userid,PageUtil pageUtil);

    Integer countUserMeeting(String userid);

    List<Room> showMeetingid(String orderStarTime, String orderEndTime);

    int addOrderMeeting(Meeting meeting);

    int queryByName(String orderMettingName);

    int updateMeeting(int meetingId, String cancelMark);

    int queryAfterMeetingCounts(String afterTime, String nowTime);

    int queryCancelMeetingCount();
}
