package com.example.newsapplicationroom.di.component;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.example.newsapplicationroom.di.module.FirebaseModule;
import com.example.newsapplicationroom.di.module.NewsPagerAdapterModule;
import com.example.newsapplicationroom.di.scope.ActivityScope;
import com.example.newsapplicationroom.ui.MainActivity;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {NewsPagerAdapterModule.class, FirebaseModule.class})
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        Builder FragmentManager(FragmentManager fragmentManager);

        @BindsInstance
        Builder TabCount(@Named("Tab Count") int tabCount);

        @BindsInstance
        Builder context(Context context);

        MainActivityComponent build();
    }
}