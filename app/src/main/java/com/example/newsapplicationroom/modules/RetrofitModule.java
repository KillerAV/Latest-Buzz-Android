package com.example.newsapplicationroom.modules;

import com.example.newsapplicationroom.utils.Constants;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {
    @Provides
    Retrofit providesRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_ADDRESS_TOP_HEADLINES)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
