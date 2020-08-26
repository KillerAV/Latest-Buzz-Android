package com.example.data.apiservice;

import com.example.data.datamodel.NewsApiData;
import com.example.data.utils.DataConstants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("top-headlines")
    Call<NewsApiData> getData(@Query(DataConstants.COUNTRY_LABEL) String country, @Query(DataConstants.CATEGORY_LABEL) String category,
                              @Query(DataConstants.PAGE_LABEL) String page, @Query(DataConstants.API_KEY_LABEL) String apiKey);

    @GET("top-headlines")
    Call<NewsApiData> getLatestData(@Query(DataConstants.COUNTRY_LABEL) String country, @Query(DataConstants.CATEGORY_LABEL) String category,
                                    @Query(DataConstants.FROM_LABEL) String fromDate, @Query(DataConstants.TO_LABEL) String toDate,
                                    @Query(DataConstants.PAGE_SIZE_LABEL) String pageSize, @Query(DataConstants.API_KEY_LABEL) String apiKey);
}
