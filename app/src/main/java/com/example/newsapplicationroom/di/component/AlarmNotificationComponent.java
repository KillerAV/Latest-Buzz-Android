package com.example.newsapplicationroom.di.component;

import android.content.Context;

import com.example.newsapplicationroom.ui.bulletnews.AlarmNotificationLauncher;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Component;

@Component
public interface AlarmNotificationComponent {
    AlarmNotificationLauncher getAlarmNotificationLauncher();

    @Component.Builder
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
