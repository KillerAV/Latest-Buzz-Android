package com.example.newsapplication.di.component;

import com.example.newsapplication.di.module.RetrofitModule;

import dagger.Component;
import retrofit2.Retrofit;

@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {
    Retrofit getRetrofit();
}