package com.example.newsapplicationroom.di.main;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.example.newsapplicationroom.di.MainActivityScope;
import com.example.newsapplicationroom.di.main.newscategory.NewsCategoryFragmentComponent;
import com.example.newsapplicationroom.di.main.newscategory.NewsPagerAdapterModule;
import com.example.newsapplicationroom.ui.main.MainActivity;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Subcomponent;

@MainActivityScope
@Subcomponent(modules = NewsPagerAdapterModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);

    NewsCategoryFragmentComponent.Builder NewsCategoryFragmentComponentBuilder();

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        Builder context(Context context);

        @BindsInstance
        Builder FragmentManager(FragmentManager fragmentManager);

        @BindsInstance
        Builder TabCount(@Named("Tab Count") int tabCount);

        MainActivityComponent build();
    }
}