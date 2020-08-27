package com.example.newsapplication.utils;

public class Constants {
    //Retrofit
    public static final String COUNTRY_LABEL = "country";
    public static final String CATEGORY_LABEL = "category";
    public static final String API_KEY_LABEL = "apiKey";
    public static final String FROM_LABEL = "from";
    public static final String TO_LABEL = "to";
    public static final String PAGE_LABEL = "page";
    public static final String PAGE_SIZE_LABEL = "pageSize";
    public static final String BASE_ADDRESS_TOP_HEADLINES = "https://newsapi.org/v2/";
    public static final String API_KEY = "40043e91a86a44abaa2b31a558b4ae8c";

    //Room Database Queries
    public static final String GET_NEWS = "SELECT * FROM News WHERE category = :categoryName" ;
    public static final String DELETE_ALL_NEWS = "DELETE FROM News";
    public static final String GET_LATEST_NEWS = "SELECT * FROM Latest_News";
    public static final String DELETE_ALL_LATEST_NEWS = "DELETE FROM Latest_News";
    public static final String GET_USER_INFORMATION = "SELECT * FROM User_Information WHERE emailId = :emailId";
    public static final String GET_ONE_USER_INFORMATION = "SELECT * FROM User_Information WHERE emailId = :emailId LIMIT 1";

    //Room Database
    public static final String DATABASE_NAME = "News Database";
    public static final int MAX_NUMBER_OF_PAGES_LIMIT = 1;  //Set this to 3
    public static final String[] category = {"entertainment", "sports", "health"};
    public static final String LATEST_NEWS_PAGE_SIZE = "10";
    public static final String GENERAL_CATEGORY_NEWS_LABEL = "general";
}
