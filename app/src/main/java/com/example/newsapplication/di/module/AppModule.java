package com.example.newsapplication.di.module;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.data.repoimpl.LatestNewsRepository;
import com.example.data.repoimpl.NewsRepository;
import com.example.data.repoimpl.UserInformationRepository;
import com.example.newsapplication.ui.newsfragments.EntertainmentNewsFragment;
import com.example.newsapplication.ui.newsfragments.HealthNewsFragment;
import com.example.newsapplication.ui.newsfragments.SportsNewsFragment;
import com.example.newsapplication.utils.GlideUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Singleton
    @Provides
    static RequestManager providesRequestManager(Application application) {
        return Glide
                .with(application);
    }

    @Singleton
    @Provides
    static GlideUtils providesGlideUtils(RequestManager requestManager) {
        return new GlideUtils(requestManager);
    }

    @Singleton
    @Provides
    static EntertainmentNewsFragment providesEntertainmentNewsFragment() {
        return new EntertainmentNewsFragment();
    }

    @Singleton
    @Provides
    static SportsNewsFragment providesSportsNewsFragment() {
        return new SportsNewsFragment();
    }

    @Singleton
    @Provides
    static HealthNewsFragment providesHealthNewsFragment() {
        return new HealthNewsFragment();
    }

    @Singleton
    @Provides
    static NewsRepository providesNewsRepository(Application application) {
        return new NewsRepository(application);
    }

    @Singleton
    @Provides
    static LatestNewsRepository providesLatestNewsRepository(Application application) {
        return new LatestNewsRepository(application);
    }

    @Singleton
    @Provides
    static UserInformationRepository providesUserInformationRepository(Application application) {
        return new UserInformationRepository(application);
    }
}
