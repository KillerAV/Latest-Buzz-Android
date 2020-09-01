package com.example.newsapplicationroom.di.main.actionbar.profile;

import android.content.Context;

import com.example.newsapplicationroom.ui.main.actionbar.profile.CountryItem;
import com.example.newsapplicationroom.ui.main.actionbar.profile.CountrySpinnerAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class CountrySpinnerAdapterModule {
    @ProfilePageActivityScope
    @Provides
    static CountrySpinnerAdapter providesCountrySpinnerAdapter(Context context, ArrayList<CountryItem> countryItemList) {
        return new CountrySpinnerAdapter(context, countryItemList);
    }
}
