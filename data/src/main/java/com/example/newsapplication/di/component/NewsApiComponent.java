package com.example.newsapplication.di.component;


import com.example.newsapplication.apiservice.NewsApiHandling;
import com.example.newsapplication.di.module.NewsApiModule;
import com.example.newsapplication.repoimpl.LatestNewsRepository;
import com.example.newsapplication.repoimpl.NewsRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = NewsApiModule.class)
public interface NewsApiComponent {
    void inject(NewsApiHandling newsApiHandling);

    void inject(NewsRepository newsRepository);

    void inject(LatestNewsRepository latestNewsRepository);
}