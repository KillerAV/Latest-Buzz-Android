package com.example.newsapplicationroom.components;

import androidx.fragment.app.FragmentManager;

import com.example.newsapplicationroom.adapters.NewsPagerAdapter;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Component;

@Component
public interface PagerAdapterComponent {
    NewsPagerAdapter getNewsPagerAdapter();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder setFragmentManager(FragmentManager fragmentManager);

        @BindsInstance
        Builder setTabCount(@Named("Tab Count") int tabCount);

        PagerAdapterComponent build();
    }
}
