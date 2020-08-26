package com.example.data.di.component;

import com.example.data.apiservice.NewsApiHandling;
import com.example.data.di.module.NewsApiModule;
import com.example.data.repoimpl.LatestNewsRepository;
import com.example.data.repoimpl.NewsRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = NewsApiModule.class)
public interface NewsApiComponent {
    void inject(NewsApiHandling newsApiHandling);

    void inject(NewsRepository newsRepository);

    void inject(LatestNewsRepository latestNewsRepository);
}