package com.zr;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(calendar.DATE, +7) ;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date d = calendar.getTime();
//        System.out.println(sdf.format(d));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String nowDate = sdf.format(date);
        System.out.println(nowDate);
    }

}
