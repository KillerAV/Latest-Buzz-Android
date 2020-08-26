package com.example.newsapplication.di.module;

import android.content.Context;

import com.example.newsapplication.di.scope.ActivityScope;
import com.example.newsapplication.ui.bulletnews.AlarmNotificationLauncher;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AlarmNotificationModule {
    @ActivityScope
    @Provides
    static AlarmNotificationLauncher providesAlarmNotificationLauncher
            (Context context, @Named("fromDate") String fromDate, @Named("toDate") String toDate) {
        return new AlarmNotificationLauncher(context, fromDate, toDate);
    }
}
