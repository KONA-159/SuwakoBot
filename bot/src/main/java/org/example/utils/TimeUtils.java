package org.example.utils;

import java.util.Date;

/**
 * @author Shimarin
 * @date 2023/3/11
 */
public class TimeUtils {
    public static Date getTomorrowTime(int hour,int min,int sec){
        Date date = new Date();
        date.setDate(date.getDate()+1);
        date.setHours(hour);
        date.setMinutes(min);
        date.setSeconds(sec);
        return date;
    }

    public static long marginOfTime(long former,long later){
        return later-former;
    }
}
