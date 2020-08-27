package com.example.newsapplication.apiservice;

import com.example.newsapplication.datamodel.NewsApiData;
import com.example.newsapplication.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("top-headlines")
    Call<NewsApiData> getData(@Query(Constants.COUNTRY_LABEL) String country, @Query(Constants.CATEGORY_LABEL) String category,
                              @Query(Constants.PAGE_LABEL) String page, @Query(Constants.API_KEY_LABEL) String apiKey);

    @GET("top-headlines")
    Call<NewsApiData> getLatestData(@Query(Constants.COUNTRY_LABEL) String country, @Query(Constants.CATEGORY_LABEL) String category,
                                    @Query(Constants.FROM_LABEL) String fromDate, @Query(Constants.TO_LABEL) String toDate,
                                    @Query(Constants.PAGE_SIZE_LABEL) String pageSize, @Query(Constants.API_KEY_LABEL) String apiKey);
}
