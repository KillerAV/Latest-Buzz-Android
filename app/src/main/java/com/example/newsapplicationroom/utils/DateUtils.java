package com.example.newsapplicationroom.utils;

import java.util.Calendar;

public class DateUtils {

    public static Calendar getCalenderDay(final int milliSec, final int sec, final int min, final int hour) {
        Calendar day = Calendar.getInstance();
        day.set(Calendar.MILLISECOND, milliSec);
        day.set(Calendar.SECOND, sec);
        day.set(Calendar.MINUTE, min);
        day.set(Calendar.HOUR_OF_DAY, hour);
        return day;
    }
}
