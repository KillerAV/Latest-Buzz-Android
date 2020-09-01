package com.example.newsapplicationroom.di.main.newscategory;

import android.content.Context;

import com.example.newsapplicationroom.di.MainActivityScope;
import com.example.newsapplicationroom.ui.main.newscategory.fragments.EntertainmentNewsFragment;
import com.example.newsapplicationroom.ui.main.newscategory.fragments.HealthNewsFragment;
import com.example.newsapplicationroom.ui.main.newscategory.fragments.SportsNewsFragment;
import com.example.newsapplicationroom.viewmodel.NewsViewModel;

import dagger.BindsInstance;
import dagger.Subcomponent;

@MainActivityFragmentScope
@Subcomponent(modules = NewsAdapterModule.class)
public interface NewsCategoryFragmentComponent {

    void inject(EntertainmentNewsFragment entertainmentNewsFragment);

    void inject(HealthNewsFragment healthNewsFragment);

    void inject(SportsNewsFragment sportsNewsFragment);

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        Builder newsViewModel(NewsViewModel newsViewModel);

        NewsCategoryFragmentComponent build();
    }
}
