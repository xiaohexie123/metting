package com.zr.util;

import com.zr.model.User;

import javax.servlet.http.HttpSession;

public class UserUtil {

    public static String currentUserId(HttpSession session){
        Object obj = session.getAttribute("loginUser");
        if(obj != null){
            User user = (User)obj;
            return user.getUserId();
        }
        return "";
    }

    public static String currentUserName(HttpSession session){
        Object obj = session.getAttribute("loginUser");
        if(obj != null){
            User user = (User)obj;
            return user.getUserName();
        }
        return "";
    }

}
