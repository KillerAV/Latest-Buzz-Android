package com.example.newsapplicationroom.di.main.actionbar.bulletnews.adapter;

import android.content.Context;

import com.example.newsapplicationroom.ui.main.actionbar.bulletnews.adapter.LatestNewsAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class LatestNewsAdapterModule {
    @LatestNewsActivityScope
    @Provides
    static LatestNewsAdapter providesLatestNewsAdapter(Context context) {
        return new LatestNewsAdapter(context);
    }
}
