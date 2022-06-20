package com.zr.service.impl;

import com.zr.dao.UserMeetingDao;
import com.zr.dao.impl.UserMeetingDaoImpl;
import com.zr.service.UserMeetingService;

public class UserMeetingServiceImpl implements UserMeetingService {
    private UserMeetingDao userMeetingDao = new UserMeetingDaoImpl();
    @Override
    public int add(String userid, int meetingId) {
        return userMeetingDao.add(userid,meetingId);
    };
}
