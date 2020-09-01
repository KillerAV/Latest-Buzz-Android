package com.example.newsapplicationroom.ui;

import android.app.Application;

import com.example.newsapplicationroom.di.component.AdapterComponent;
import com.example.newsapplicationroom.di.component.LatestNewsJobSchedulerComponent;
import com.example.newsapplicationroom.di.component.AppComponent;
import com.example.newsapplicationroom.di.component.DaggerAppComponent;
import com.example.newsapplicationroom.di.component.MainActivityComponent;
import com.example.newsapplicationroom.di.component.SignInActivityComponent;

public class NewsApplication extends Application {
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().application(this).build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static AdapterComponent.Builder getAdapterComponentBuilder() {
        return appComponent.AdapterComponentBuilder();
    }

    public static MainActivityComponent.Builder getMainActivityComponentBuilder() {
        return appComponent.MainActivityComponentBuilder();
    }

    public static LatestNewsJobSchedulerComponent.Builder getAlarmNotificationComponentBuilder() {
        return appComponent.AlarmNotificationComponentBuilder();
    }

    public static SignInActivityComponent.Builder getSignInActivityComponentBuilder() {
        return appComponent.SignInActivityComponentBuilder();
    }
}
