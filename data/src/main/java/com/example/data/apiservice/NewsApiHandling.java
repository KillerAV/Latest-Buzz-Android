package com.example.data.apiservice;

import com.example.data.datamodel.NewsApiData;
import com.example.data.di.component.DaggerNewsApiComponent;
import com.example.data.utils.DataConstants;

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
        Call<NewsApiData> newsApiDataCall = newsApi.getData(country, category, page, DataConstants.API_KEY);
        newsApiDataCall.enqueue(responseCallback);
    }

    public void getLatestNewsUsingApiCall(final String country, final String category, final String fromDate, final String toDate, final String pageSize, final Callback<NewsApiData> responseCallback) {
        Call<NewsApiData> newsApiDataCall = newsApi.getLatestData(country, category, fromDate, toDate, pageSize, DataConstants.API_KEY);
        newsApiDataCall.enqueue(responseCallback);
    }
}