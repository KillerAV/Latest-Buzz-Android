package com.example.newsapplicationroom.di.main.actionbar.bulletnews.adapter;

import android.content.Context;

import com.example.newsapplicationroom.ui.main.actionbar.bulletnews.LatestNewsActivity;
import com.example.newsapplicationroom.viewmodel.LatestNewsViewModel;

import dagger.BindsInstance;
import dagger.Subcomponent;

@LatestNewsActivityScope
@Subcomponent(modules = LatestNewsAdapterModule.class)
public interface LatestNewsActivityComponent {
    void inject(LatestNewsActivity latestNewsActivity);

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        Builder latestNewsViewModel(LatestNewsViewModel latestNewsViewModel);

        @BindsInstance
        Builder context(Context context);

        LatestNewsActivityComponent build();
    }
}
