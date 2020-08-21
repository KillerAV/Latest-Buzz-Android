package com.example.newsapplicationroom.components;

import android.content.Context;

import com.example.newsapplicationroom.adapters.LatestNewsAdapter;
import com.example.newsapplicationroom.adapters.NewsAdapter;

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
