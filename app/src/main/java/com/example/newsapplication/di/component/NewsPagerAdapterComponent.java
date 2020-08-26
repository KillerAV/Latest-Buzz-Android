package com.example.newsapplication.di.component;

import androidx.fragment.app.FragmentManager;

import com.example.newsapplication.di.module.NewsPagerAdapterModule;
import com.example.newsapplication.di.scope.ActivityScope;
import com.example.newsapplication.ui.MainActivity;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {NewsPagerAdapterModule.class})
public interface NewsPagerAdapterComponent {

    void inject(MainActivity mainActivity);

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        Builder FragmentManager(FragmentManager fragmentManager);

        @BindsInstance
        Builder TabCount(@Named("Tab Count") int tabCount);

        NewsPagerAdapterComponent build();
    }
}
