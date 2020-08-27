package com.example.newsapplicationroom.di.component;

import android.app.Application;

import com.example.newsapplicationroom.di.module.AppModule;
import com.example.newsapplicationroom.ui.CountrySpinnerAdapter;
import com.example.newsapplicationroom.ui.DescriptionDisplayActivity;
import com.example.newsapplicationroom.ui.ProfilePageActivity;
import com.example.newsapplicationroom.ui.adapter.LatestNewsAdapter;
import com.example.newsapplicationroom.ui.adapter.NewsAdapter;
import com.example.newsapplicationroom.ui.adapter.NewsPagerAdapter;
import com.example.newsapplicationroom.ui.bulletnews.LatestNewsDescriptionDisplayActivity;
import com.example.newsapplicationroom.viewmodel.LatestNewsViewModel;
import com.example.newsapplicationroom.viewmodel.NewsViewModel;
import com.example.newsapplicationroom.viewmodel.UserInformationViewModel;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(NewsAdapter newsAdapter);

    void inject(LatestNewsAdapter latestNewsAdapter);

    void inject(DescriptionDisplayActivity descriptionDisplayActivity);

    void inject(LatestNewsDescriptionDisplayActivity latestNewsDescriptionDisplayActivity);

    void inject(ProfilePageActivity profilePageActivity);

    void inject(NewsPagerAdapter newsPagerAdapter);

    void inject(NewsViewModel newsViewModel);

    void inject(LatestNewsViewModel latestNewsViewModel);

    void inject(UserInformationViewModel userInformationViewModel);

    AdapterComponent.Builder AdapterComponentBuilder();

    NewsPagerAdapterComponent.Builder NewsPagerAdapterComponentBuilder();

    AlarmNotificationComponent.Builder AlarmNotificationComponentBuilder();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

}
