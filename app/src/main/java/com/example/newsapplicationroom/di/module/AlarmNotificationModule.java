package com.example.newsapplicationroom.di.module;

import android.content.Context;

import com.example.newsapplicationroom.di.scope.ActivityScope;
import com.example.newsapplicationroom.ui.bulletnews.AlarmNotificationLauncher;

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
