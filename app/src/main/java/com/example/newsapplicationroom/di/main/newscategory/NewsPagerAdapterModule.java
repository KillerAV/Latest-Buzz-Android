package com.example.newsapplicationroom.di.main.newscategory;

import androidx.fragment.app.FragmentManager;

import com.example.newsapplicationroom.di.MainActivityScope;
import com.example.newsapplicationroom.ui.main.newscategory.adapter.NewsPagerAdapter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsPagerAdapterModule {
    @MainActivityScope
    @Provides
    static NewsPagerAdapter providesNewsPagerAdapter(FragmentManager fragmentManager, @Named("Tab Count") int numberOfTabs) {
        return new NewsPagerAdapter(fragmentManager, numberOfTabs);
    }
}
