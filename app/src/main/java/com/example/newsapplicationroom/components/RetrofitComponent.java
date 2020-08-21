package com.example.newsapplicationroom.components;

import com.example.newsapplicationroom.modules.RetrofitModule;

import dagger.Component;
import retrofit2.Retrofit;

@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {
    Retrofit getRetrofit();
}