package com.example.newsapplicationroom.ui;

import android.app.Application;

import com.example.newsapplicationroom.di.component.AdapterComponent;
import com.example.newsapplicationroom.di.component.AlarmNotificationComponent;
import com.example.newsapplicationroom.di.component.AppComponent;
import com.example.newsapplicationroom.di.component.DaggerAppComponent;
import com.example.newsapplicationroom.di.component.NewsPagerAdapterComponent;

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
