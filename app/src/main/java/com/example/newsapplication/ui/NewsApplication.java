package com.example.newsapplication.ui;

import android.app.Application;

import com.example.newsapplication.di.component.AdapterComponent;
import com.example.newsapplication.di.component.AlarmNotificationComponent;
import com.example.newsapplication.di.component.AppComponent;
import com.example.newsapplication.di.component.DaggerAppComponent;
import com.example.newsapplication.di.component.NewsPagerAdapterComponent;

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

    public static NewsPagerAdapterComponent.Builder getPagerAdapterComponentBuilder() {
        return appComponent.NewsPagerAdapterComponentBuilder();
    }

    public static AlarmNotificationComponent.Builder getAlarmNotificationComponentBuilder() {
        return appComponent.AlarmNotificationComponentBuilder();
    }
}
