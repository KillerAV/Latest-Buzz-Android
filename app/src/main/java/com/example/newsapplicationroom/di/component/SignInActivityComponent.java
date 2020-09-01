package com.example.newsapplicationroom.di.component;

import android.content.Context;

import com.example.newsapplicationroom.di.module.FirebaseModule;
import com.example.newsapplicationroom.di.scope.ActivityScope;
import com.example.newsapplicationroom.ui.SignInActivity;

import dagger.BindsInstance;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = FirebaseModule.class)
public interface SignInActivityComponent {
    void inject(SignInActivity signInActivity);

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        Builder context(Context context);

        SignInActivityComponent build();
    }
}
