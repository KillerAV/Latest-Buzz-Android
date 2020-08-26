package com.example.newsapplication.di.component;

import android.app.Application;

import com.example.newsapplication.di.module.AppModule;
import com.example.newsapplication.ui.DescriptionDisplayActivity;
import com.example.newsapplication.ui.ProfilePageActivity;
import com.example.newsapplication.ui.adapter.LatestNewsAdapter;
import com.example.newsapplication.ui.adapter.NewsAdapter;
import com.example.newsapplication.ui.adapter.NewsPagerAdapter;
import com.example.newsapplication.ui.bulletnews.AlarmNotificationLauncher;
import com.example.newsapplication.ui.bulletnews.LatestNewsDescriptionDisplayActivity;
import com.example.newsapplication.viewmodel.LatestNewsViewModel;
import com.example.newsapplication.viewmodel.NewsViewModel;
import com.example.newsapplication.viewmodel.UserInformationViewModel;

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
