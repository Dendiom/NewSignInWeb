package com.lucky.NewSignInWeb.util;


import java.util.Calendar;
import java.util.Locale;

public class TimeUtil {

    /**
     * 获取本周一0点的时间戳.
     * 注意，外国人把周日当成了第一天.
     * @return string.
     */
    public static String getWeekIdentifier() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);  // important
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return String.valueOf(calendar.getTimeInMillis() / 1000);
    }

    /**
     * 获取今天是星期几(1~7).
     * @return int.
     */
    public static int getDayOfWeek() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        int ans = calendar.get(Calendar.DAY_OF_WEEK);
        return ans == 1 ? 7 : ans - 1;
    }

    /**
     * 获取当前处于上午/中午/晚上（0/1/2）.
     * @return int.
     */
    public static int getDayPeriod() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 0 && hour < 13) {
            return 0;
        }

        if (hour >= 13 && hour <= 19) {
            return 1;
        }

        return 2;
    }


    public static void main(String[] args) {
        System.out.println(getDayPeriod());
    }

}