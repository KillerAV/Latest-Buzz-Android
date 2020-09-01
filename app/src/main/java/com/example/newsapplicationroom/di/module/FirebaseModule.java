package com.example.newsapplicationroom.di.module;

import android.content.Context;

import com.example.newsapplicationroom.di.scope.ActivityScope;
import com.google.firebase.analytics.FirebaseAnalytics;

import dagger.Module;
import dagger.Provides;

@Module
public class FirebaseModule {
    @ActivityScope
    @Provides
    static FirebaseAnalytics providesFirebaseAnalytics(Context context) {
        return FirebaseAnalytics.getInstance(context);
    }
}
