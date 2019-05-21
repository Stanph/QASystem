package com.j2ee.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author haopan
 */
public class TimeUtil {
    public static int getIntTime() throws ParseException {
        long time = System.currentTimeMillis();
        return (int) (time / 1000);
    }
    public static String getShowTime(long createTime){
        Date date = new Date();
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long t = createTime;
        t = (t+8*3600)* 1000;
        date.setTime(t);
        return sdFormat.format(date);
    }
}
