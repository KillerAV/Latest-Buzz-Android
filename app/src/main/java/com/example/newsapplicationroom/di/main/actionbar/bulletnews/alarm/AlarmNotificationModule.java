package com.example.newsapplicationroom.di.main.actionbar.bulletnews.alarm;

import android.content.Context;

import com.example.newsapplicationroom.ui.main.actionbar.bulletnews.alarm.AlarmNotificationLauncher;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AlarmNotificationModule {
    @LatestNewsJobSchedulerScope
    @Provides
    static AlarmNotificationLauncher providesAlarmNotificationLauncher
            (Context context, @Named("fromDate") String fromDate, @Named("toDate") String toDate) {
        return new AlarmNotificationLauncher(context, fromDate, toDate);
    }
}
