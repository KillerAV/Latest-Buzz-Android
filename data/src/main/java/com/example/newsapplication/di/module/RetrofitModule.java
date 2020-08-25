package com.example.newsapplication.di.module;

import com.example.newsapplication.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {
    @Provides
    @Singleton
    Retrofit providesRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_ADDRESS_TOP_HEADLINES)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
