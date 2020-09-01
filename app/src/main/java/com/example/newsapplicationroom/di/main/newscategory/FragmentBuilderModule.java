package com.example.newsapplicationroom.di.main.newscategory;

import com.example.newsapplicationroom.di.MainActivityScope;
import com.example.newsapplicationroom.ui.main.newscategory.fragments.EntertainmentNewsFragment;
import com.example.newsapplicationroom.ui.main.newscategory.fragments.HealthNewsFragment;
import com.example.newsapplicationroom.ui.main.newscategory.fragments.SportsNewsFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentBuilderModule {
    @MainActivityScope
    @Provides
    static EntertainmentNewsFragment providesEntertainmentNewsFragment() {
        return new EntertainmentNewsFragment();
    }

    @MainActivityScope
    @Provides
    static SportsNewsFragment providesSportsNewsFragment() {
        return new SportsNewsFragment();
    }

    @MainActivityScope
    @Provides
    static HealthNewsFragment providesHealthNewsFragment() {
        return new HealthNewsFragment();
    }
}
