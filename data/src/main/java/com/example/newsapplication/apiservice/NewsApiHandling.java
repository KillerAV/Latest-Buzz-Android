package com.example.newsapplication.apiservice;

import com.example.newsapplication.datamodel.NewsApiData;
import com.example.newsapplication.di.component.DaggerNewsApiComponent;
import com.example.newsapplication.utils.Constants;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;

public class NewsApiHandling {

    @Inject
    NewsApi newsApi;

    public NewsApiHandling() {
        DaggerNewsApiComponent.create().inject(this);
    }

    public void getNewsUsingApiCall(final String country, final String category, final String page, final Callback<NewsApiData> responseCallback) {
        Call<NewsApiData> newsApiDataCall = newsApi.getData(country, category, page, Constants.API_KEY);
        newsApiDataCall.enqueue(responseCallback);
    }

    public void getLatestNewsUsingApiCall(final String country, final String category, final String fromDate, final String toDate, final String pageSize, final Callback<NewsApiData> responseCallback) {
        Call<NewsApiData> newsApiDataCall = newsApi.getLatestData(country, category, fromDate, toDate, pageSize, Constants.API_KEY);
        newsApiDataCall.enqueue(responseCallback);
    }
}