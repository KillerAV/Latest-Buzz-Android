package com.example.newsapplicationroom.di.module;

import androidx.fragment.app.FragmentManager;

import com.example.newsapplicationroom.di.scope.ActivityScope;
import com.example.newsapplicationroom.ui.adapter.NewsPagerAdapter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsPagerAdapterModule {
    @ActivityScope
    @Provides
    static NewsPagerAdapter providesNewsPagerAdapter(FragmentManager fragmentManager, @Named("Tab Count") int numberOfTabs) {
        return new NewsPagerAdapter(fragmentManager, numberOfTabs);
    }
}
