package com.example.newsapplication.di.component;

import android.content.Context;

import androidx.lifecycle.AndroidViewModel;

import com.example.newsapplication.di.module.AdapterModule;
import com.example.newsapplication.di.scope.ActivityScope;
import com.example.newsapplication.ui.bulletnews.LatestNewsActivity;
import com.example.newsapplication.ui.newsfragments.EntertainmentNewsFragment;
import com.example.newsapplication.ui.newsfragments.HealthNewsFragment;
import com.example.newsapplication.ui.newsfragments.SportsNewsFragment;

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
