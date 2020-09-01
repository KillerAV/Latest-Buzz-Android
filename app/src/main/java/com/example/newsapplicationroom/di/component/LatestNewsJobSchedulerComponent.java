package com.example.newsapplicationroom.di.component;

import android.content.Context;

import com.example.newsapplicationroom.di.module.AlarmNotificationModule;
import com.example.newsapplicationroom.di.scope.ActivityScope;
import com.example.newsapplicationroom.ui.bulletnews.LatestNewsJobScheduler;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = AlarmNotificationModule.class)
public interface LatestNewsJobSchedulerComponent {
    void inject(LatestNewsJobScheduler latestNewsJobScheduler);

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        Builder setContext(Context context);

        @BindsInstance
        Builder setFromDate(@Named("fromDate") String fromDate);

        @BindsInstance
        Builder setToDate(@Named("toDate") String toDate);

        LatestNewsJobSchedulerComponent build();
    }
}
