package com.example.newsapplicationroom.utils;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final String EXTRA_TITLE = "title message";
    public static final String EXTRA_CATEGORY = "category message";
    public static final String EXTRA_DESCRIPTION = "description message";
    public static final String EXTRA_PRIMARY_KEY = "primary key";
    public static final String EXTRA_IMAGE_URL = "image url";
    public static final int UPDATE_NEWS_REQUEST_CODE = 2;
    public static final int COUNTRY_CHANGE_REQUEST_CODE = 1;
    public static final int PROFILE_UPDATE_REQUEST_CODE = 1;
    public static final String EXTRA_COUNTRY_NAME = "country";
    public static final String[] category = {"entertainment", "sports", "health"};
    public static final String[] latestNewsCategory = {"business", "entertainment", "health", "science", "sports", "technology"};
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
    public static final String LATEST_NEWS_PAGE_SIZE = "3";

    public static final int MAX_NUMBER_OF_PAGES_LIMIT = 1;  //Set this to 3
    public static final int ALARM_PENDING_INTENT_REQUEST_CODE = 2;
    public static final int LATEST_NEWS_JOB_ID = 1;

    public static final String CHANNEL_ID = "com.example.newsapplicationroom.notificationChannel";
    public static final int ALARM_NOTIFICATION_ID = 1;

    public static final String COUNTRY_LABEL = "country";
    public static final String CATEGORY_LABEL = "category";
    public static final String API_KEY_LABEL = "apiKey";
    public static final String FROM_LABEL = "from";
    public static final String TO_LABEL = "to";
    public static final String PAGE_LABEL = "page";
    public static final String PAGE_SIZE_LABEL = "pageSize";

    public static final String BASE_ADDRESS_TOP_HEADLINES = "https://newsapi.org/v2/";
    public static final String API_KEY = "40043e91a86a44abaa2b31a558b4ae8c";

    public static final String NEWS_TABLE_NAME = "News";
    public static final String LATEST_NEWS_TABLE_NAME = "Latest_News";
    public static final String USER_INFO_TABLE_NAME = "User_Information";
    public static final String DATABASE_NAME = "News Database";

    public static final String DEFAULT_COUNTRY = "in";

    public static final String EXTRA_USER_NAME = "user name";
    public static final String EXTRA_EMAIL_ID = "user email id";
    public static final String EXTRA_PROFILE_PICTURE = "user profile picture";
    public static final String EXTRA_URL = "url";
}
