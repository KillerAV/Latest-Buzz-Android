package com.example.newsapplicationroom.newsapi;

import com.example.newsapplicationroom.components.DaggerRetrofitComponent;
import com.example.newsapplicationroom.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class NewsApiHandling {


    static NewsApi newsApi;

    static {
        Retrofit retrofit = DaggerRetrofitComponent.create().getRetrofit();
        NewsApiHandling.newsApi = retrofit.create(NewsApi.class);
    }

    public static void getNewsUsingApiCall(String country, final String category, final String page, final Callback<NewsApiData> responseCallback) {
        Call<NewsApiData> newsApiDataCall = newsApi.getData(country, category, page, Constants.API_KEY);
        newsApiDataCall.enqueue(responseCallback);
    }

    public static void getLatestNewsUsingApiCall(String country, String category, String fromDate, String toDate, String pageSize, final Callback<NewsApiData> responseCallback) {
        Call<NewsApiData> newsApiDataCall = newsApi.getLatestData(country, category, fromDate, toDate, pageSize, Constants.API_KEY);
        newsApiDataCall.enqueue(responseCallback);
    }
}