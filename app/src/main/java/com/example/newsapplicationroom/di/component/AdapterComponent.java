package com.example.newsapplicationroom.di.component;

import android.content.Context;

import com.example.newsapplicationroom.ui.adapter.LatestNewsAdapter;
import com.example.newsapplicationroom.ui.adapter.NewsAdapter;

import dagger.BindsInstance;
import dagger.Component;

@Component
public interface AdapterComponent {
    NewsAdapter getNewsAdapter();

    LatestNewsAdapter getLatestNewsAdapter();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder setContext(Context context);

        AdapterComponent build();
    }
}
