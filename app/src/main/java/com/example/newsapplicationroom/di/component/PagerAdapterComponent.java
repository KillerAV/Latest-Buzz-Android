package com.example.newsapplicationroom.di.component;

import androidx.fragment.app.FragmentManager;

import com.example.newsapplicationroom.di.scope.ActivityScope;
import com.example.newsapplicationroom.ui.adapter.NewsPagerAdapter;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Component;

@Component
@ActivityScope
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
