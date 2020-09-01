package com.example.newsapplicationroom.di.main.actionbar.bulletnews.alarm;

import android.content.Context;

import com.example.newsapplicationroom.ui.main.actionbar.bulletnews.alarm.LatestNewsJobScheduler;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Subcomponent;

@LatestNewsJobSchedulerScope
@Subcomponent(modules = AlarmNotificationModule.class)
public interface LatestNewsJobSchedulerComponent {
    void inject(LatestNewsJobScheduler latestNewsJobScheduler);

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        Builder setFromDate(@Named("fromDate") String fromDate);

        @BindsInstance
        Builder setToDate(@Named("toDate") String toDate);

        @BindsInstance
        Builder context(Context context);

        LatestNewsJobSchedulerComponent build();
    }
}
