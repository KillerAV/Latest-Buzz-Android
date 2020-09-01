package com.example.newsapplicationroom.di.main.actionbar.profile;

import android.content.Context;

import com.example.newsapplicationroom.ui.main.actionbar.profile.CountryItem;
import com.example.newsapplicationroom.ui.main.actionbar.profile.ProfilePageActivity;

import java.util.ArrayList;

import dagger.BindsInstance;
import dagger.Subcomponent;

@ProfilePageActivityScope
@Subcomponent(modules = CountrySpinnerAdapterModule.class)
public interface ProfilePageActivityComponent {
    void inject(ProfilePageActivity profilePageActivity);

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        Builder countryItemList(ArrayList<CountryItem> countryItemList);

        @BindsInstance
        Builder context(Context context);

        ProfilePageActivityComponent build();
    }
}
