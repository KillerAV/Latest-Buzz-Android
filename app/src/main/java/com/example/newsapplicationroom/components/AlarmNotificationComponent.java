package com.example.newsapplicationroom.components;

import android.content.Context;

import com.example.newsapplicationroom.bulletnews.AlarmNotificationLauncher;

import dagger.BindsInstance;
import dagger.Component;

@Component
public interface AlarmNotificationComponent {
    AlarmNotificationLauncher getAlarmNotificationLauncher();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder setContext(Context context);

        AlarmNotificationComponent build();
    }
}
