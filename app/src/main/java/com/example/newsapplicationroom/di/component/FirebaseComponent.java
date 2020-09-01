package com.example.newsapplicationroom.di.component;

import android.content.Context;

import com.example.newsapplicationroom.di.module.FirebaseModule;
import com.example.newsapplicationroom.di.scope.ActivityScope;
import com.example.newsapplicationroom.ui.MainActivity;
import com.example.newsapplicationroom.ui.SignInActivity;

import dagger.BindsInstance;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = FirebaseModule.class)
public interface FirebaseComponent {
    void inject(SignInActivity signInActivity);

    void inject(MainActivity mainActivity);

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        Builder context(Context context);

        FirebaseComponent build();
    }
}