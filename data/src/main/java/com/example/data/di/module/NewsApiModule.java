package com.example.data.di.module;

import com.example.data.apiservice.NewsApi;
import com.example.data.apiservice.NewsApiHandling;
import com.example.data.utils.DataConstants;

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
                .baseUrl(DataConstants.BASE_ADDRESS_TOP_HEADLINES)
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