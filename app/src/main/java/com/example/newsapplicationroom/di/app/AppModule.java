package com.example.newsapplicationroom.di.app;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.newsapplication.repoimpl.LatestNewsRepository;
import com.example.newsapplication.repoimpl.NewsRepository;
import com.example.newsapplication.repoimpl.UserInformationRepository;
import com.example.newsapplicationroom.utils.GlideUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Singleton
    @Provides
    static RequestManager providesRequestManager(Application application) {
        return Glide
                .with(application);
    }

    @Singleton
    @Provides
    static GlideUtils providesGlideUtils(RequestManager requestManager) {
        return new GlideUtils(requestManager);
    }

    @Singleton
    @Provides
    static NewsRepository providesNewsRepository(Application application) {
        return new NewsRepository(application);
    }

    @Singleton
    @Provides
    static LatestNewsRepository providesLatestNewsRepository(Application application) {
        return new LatestNewsRepository(application);
    }

    @Singleton
    @Provides
    static UserInformationRepository providesUserInformationRepository(Application application) {
        return new UserInformationRepository(application);
    }

    @Singleton
    @Provides
    static FirebaseFirestore providesFirebaseFirestore() {
        return FirebaseFirestore.getInstance();
    }

    @Singleton
    @Provides
    static FirebaseAuth providesFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Singleton
    @Provides
    static FirebaseAnalytics providesFirebaseAnalytics(Application application) {
        return FirebaseAnalytics.getInstance(application);
    }
}
