package com.example.newsapplicationroom.di.main.newscategory;

import com.example.newsapplicationroom.di.MainActivityScope;
import com.example.newsapplicationroom.ui.main.newscategory.adapter.NewsPagerAdapter;

import dagger.Subcomponent;

@MainActivityScope
@Subcomponent(modules = FragmentBuilderModule.class)
public interface NewsPagerAdapterComponent {
    void inject(NewsPagerAdapter newsPagerAdapter);
}
