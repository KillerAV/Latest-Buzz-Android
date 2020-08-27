package com.example.newsapplicationroom.di.component;

import android.content.Context;

import androidx.lifecycle.AndroidViewModel;

import com.example.newsapplicationroom.di.module.AdapterModule;
import com.example.newsapplicationroom.di.scope.ActivityScope;
import com.example.newsapplicationroom.ui.bulletnews.LatestNewsActivity;
import com.example.newsapplicationroom.ui.newsfragments.EntertainmentNewsFragment;
import com.example.newsapplicationroom.ui.newsfragments.HealthNewsFragment;
import com.example.newsapplicationroom.ui.newsfragments.SportsNewsFragment;

import dagger.BindsInstance;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = AdapterModule.class)
public interface AdapterComponent {

    void inject(EntertainmentNewsFragment entertainmentNewsFragment);

    void inject(HealthNewsFragment healthNewsFragment);

    void inject(SportsNewsFragment sportsNewsFragment);

    void inject(LatestNewsActivity latestNewsActivity);

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        Builder context(Context context);

        @BindsInstance
        Builder androidViewModel(AndroidViewModel androidViewModel);

        AdapterComponent build();
    }
}
