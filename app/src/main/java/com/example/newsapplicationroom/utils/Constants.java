package com.example.newsapplicationroom.utils;

import android.app.AlarmManager;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final int SPLASH_TIME_OUT = 1000;

    public static final String EXTRA_TITLE = "title message";
    public static final String EXTRA_CATEGORY = "category message";
    public static final String EXTRA_DESCRIPTION = "description message";
    public static final String EXTRA_PRIMARY_KEY = "primary key";
    public static final String EXTRA_IMAGE_URL = "image url";
    public static final String EXTRA_USER_NAME = "user name";
    public static final String EXTRA_EMAIL_ID = "user email id";
    public static final String EXTRA_PROFILE_PICTURE = "user profile picture";
    public static final String EXTRA_URL = "url";
    public static final String EXTRA_COUNTRY_NAME = "country";
    public static final String EXTRA_IS_ALARM_LAUNCHED = "alarm launched";
    public static final String EXTRA_FROM_DATE = "from date";
    public static final String EXTRA_TO_DATE = "to date";

    public static final int UPDATE_NEWS_REQUEST_CODE = 2;
    public static final int PROFILE_UPDATE_REQUEST_CODE = 1;
    public static final int ALARM_PENDING_INTENT_REQUEST_CODE = 2;
    public static final int LATEST_NEWS_JOB_ID = 1;
    public static final int ALARM_NOTIFICATION_ID = 1;
    public static final String CHANNEL_ID = "com.example.newsapplicationroom.notificationChannel";
    public static final String NOTIFICATION_CHANNEL_NAME = "Latest News Notification";
    public static final int SIGN_IN_REQUEST_CODE = 1;

    public static final String[] category = {"entertainment", "sports", "health"};
    public static final String ENTERTAINMENT = "entertainment";
    public static final String SPORTS = "sports";
    public static final String HEALTH = "health";

    public static final Map<String, String> COUNTRY_CODE = new HashMap<String, String>() {
        {
            put("India", "in");
            put("USA", "us");
            put("Argentina", "ar");
            put("Australia", "au");
            put("Brazil", "br");
            put("Germany", "de");
            put("France", "fr");
            put("United Kingdom", "gb");
            put("Italy", "it");
            put("New Zealand", "nz");
            put("Russia", "ru");
            put("South Africa", "za");
        }
    };

    public static final long MILLISECONDS_IN_A_DAY = 86400000;
    public static final long ALARM_REPEAT_INTERVAL = AlarmManager.INTERVAL_DAY;
    public static final String PLACEHOLDER_STRING = "Placeholder String";
}
