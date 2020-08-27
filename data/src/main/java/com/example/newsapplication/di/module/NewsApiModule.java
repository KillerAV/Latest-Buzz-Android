package com.example.newsapplication.di.module;

import com.example.newsapplication.apiservice.NewsApi;
import com.example.newsapplication.apiservice.NewsApiHandling;
import com.example.newsapplication.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NewsApiModule {
    @Provides
    @Singleton
    static Retrofit providesRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_ADDRESS_TOP_HEADLINES)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    static NewsApi providesNewsApi(Retrofit retrofit) {
        return retrofit.create(NewsApi.class);
    }

    @Provides
    @Singleton
    static NewsApiHandling providesNewsApiHandling() {
        return new NewsApiHandling();
    }
}
