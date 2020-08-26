package com.example.newsapplication.di.component;

import android.content.Context;

import com.example.newsapplication.di.module.AlarmNotificationModule;
import com.example.newsapplication.di.scope.ActivityScope;
import com.example.newsapplication.ui.bulletnews.LatestNewsJobScheduler;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = AlarmNotificationModule.class)
public interface AlarmNotificationComponent {
    void inject(LatestNewsJobScheduler latestNewsJobScheduler);

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        Builder setContext(Context context);

        @BindsInstance
        Builder setFromDate(@Named("fromDate") String fromDate);

        @BindsInstance
        Builder setToDate(@Named("toDate") String toDate);

        AlarmNotificationComponent build();
    }
}
