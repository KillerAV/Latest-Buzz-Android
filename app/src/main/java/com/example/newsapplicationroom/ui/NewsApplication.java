package com.example.newsapplicationroom.ui;

import android.app.Application;

import com.example.newsapplicationroom.di.app.AppComponent;
import com.example.newsapplicationroom.di.app.DaggerAppComponent;
import com.example.newsapplicationroom.di.main.MainActivityComponent;
import com.example.newsapplicationroom.di.main.actionbar.bulletnews.adapter.LatestNewsActivityComponent;
import com.example.newsapplicationroom.di.main.actionbar.bulletnews.alarm.LatestNewsJobSchedulerComponent;
import com.example.newsapplicationroom.di.main.actionbar.profile.ProfilePageActivityComponent;
import com.example.newsapplicationroom.di.main.newscategory.NewsCategoryFragmentComponent;
import com.example.newsapplicationroom.di.main.newscategory.NewsPagerAdapterComponent;

public class NewsApplication extends Application {
    private static AppComponent appComponent;
    private static MainActivityComponent.Builder mainActivityComponentBuilder;
    private static ProfilePageActivityComponent.Builder profilePageActivityComponentBuilder;
    private static LatestNewsActivityComponent.Builder latestNewsAdapterComponentBuilder;
    private static LatestNewsJobSchedulerComponent.Builder latestNewsJobSchedulerComponentBuilder;
    private static NewsPagerAdapterComponent newsPagerAdapterComponent;

    public static MainActivityComponent.Builder getMainActivityComponentBuilder() {
        return mainActivityComponentBuilder;
    }

    public static ProfilePageActivityComponent.Builder getProfilePageActivityComponentBuilder() {
        return profilePageActivityComponentBuilder;
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static LatestNewsActivityComponent.Builder getLatestNewsAdapterComponentBuilder() {
        return latestNewsAdapterComponentBuilder;
    }

    public static LatestNewsJobSchedulerComponent.Builder getLatestNewsJobSchedulerComponentBuilder() {
        return latestNewsJobSchedulerComponentBuilder;
    }

    public static NewsPagerAdapterComponent getNewsPagerAdapterComponent() {
        return newsPagerAdapterComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().application(this).build();
        mainActivityComponentBuilder = appComponent.MainActivityComponentBuilder();
        profilePageActivityComponentBuilder = appComponent.ProfilePageActivityComponentBuilder();
        latestNewsAdapterComponentBuilder = appComponent.LatestNewsActivityComponentBuilder();
        latestNewsJobSchedulerComponentBuilder = appComponent.LatestNewsJobSchedulerComponentBuilder();
        newsPagerAdapterComponent = appComponent.NewsPagerAdapterComponent();
    }
}
