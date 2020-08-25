package com.example.newsapplication.di.component;

import com.example.newsapplication.di.module.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {
    Retrofit getRetrofit();
}