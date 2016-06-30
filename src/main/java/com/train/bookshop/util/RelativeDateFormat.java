package com.train.bookshop.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 计算与当前时间的差额，并显示
 * 
 * @author singo 2016-03-10
 */
public class RelativeDateFormat {

    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR   = 3600000L;

    private static final String ONE_SECOND_DISPLAY = "秒";
    private static final String ONE_MINUTE_DISPLAY = "分钟";
    private static final String ONE_HOUR_DISPLAY   = "小时";

    private static final String AGO   = "前";
    private static final String LATER = "后";

    public static void main(String[] args) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:m:s");
        Date date = null;
        try {
            date = format.parse("2016-03-20 10:19:00");
        } catch (ParseException e) {
        }
        System.out.println(format(date));
    }

    public static String format(Date date) {
        long delta = new Date().getTime() - date.getTime();
        String agoOrLater = AGO;
        if (delta < 0) {
            agoOrLater = LATER;
        }
        delta = Math.abs(delta);
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_DISPLAY + agoOrLater;
        }
        if (delta < 60L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_DISPLAY + agoOrLater;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_DISPLAY + agoOrLater;
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    private static long toSeconds(long delta) {
        return delta / 1000L;
    }

    private static long toMinutes(long delta) {
        return toSeconds(delta) / 60L;
    }

    private static long toHours(long delta) {
        return toMinutes(delta) / 60L;
    }
}
