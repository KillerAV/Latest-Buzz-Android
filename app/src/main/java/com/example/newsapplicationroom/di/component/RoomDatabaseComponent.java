package com.example.newsapplicationroom.di.component;

import android.app.Application;

import com.example.newsapplication.repoimpl.LatestNewsRepository;
import com.example.newsapplication.repoimpl.NewsRepository;
import com.example.newsapplication.repoimpl.UserInformationRepository;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component
public interface RoomDatabaseComponent {

    LatestNewsRepository getLatestNewsRepository();
    NewsRepository getNewsRepository();
    UserInformationRepository getUserInformationRepository();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder setApplication(Application application);

        RoomDatabaseComponent build();
    }

}
