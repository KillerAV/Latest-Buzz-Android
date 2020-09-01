package com.example.newsapplicationroom.di.app;

import android.app.Application;

import com.example.newsapplicationroom.di.main.MainActivityComponent;
import com.example.newsapplicationroom.di.main.actionbar.bulletnews.adapter.LatestNewsActivityComponent;
import com.example.newsapplicationroom.di.main.actionbar.bulletnews.alarm.LatestNewsJobSchedulerComponent;
import com.example.newsapplicationroom.di.main.actionbar.profile.ProfilePageActivityComponent;
import com.example.newsapplicationroom.di.main.newscategory.NewsCategoryFragmentComponent;
import com.example.newsapplicationroom.di.main.newscategory.NewsPagerAdapterComponent;
import com.example.newsapplicationroom.ui.auth.SignInActivity;
import com.example.newsapplicationroom.ui.main.actionbar.bulletnews.adapter.LatestNewsAdapter;
import com.example.newsapplicationroom.ui.main.actionbar.bulletnews.description.LatestNewsDescriptionDisplayActivity;
import com.example.newsapplicationroom.ui.main.description.DescriptionDisplayActivity;
import com.example.newsapplicationroom.ui.main.newscategory.adapter.NewsAdapter;
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

    void inject(NewsViewModel newsViewModel);

    void inject(LatestNewsViewModel latestNewsViewModel);

    void inject(UserInformationViewModel userInformationViewModel);

    void inject(SignInActivity signInActivity);

    MainActivityComponent.Builder MainActivityComponentBuilder();

    LatestNewsActivityComponent.Builder LatestNewsActivityComponentBuilder();

    LatestNewsJobSchedulerComponent.Builder LatestNewsJobSchedulerComponentBuilder();

    ProfilePageActivityComponent.Builder ProfilePageActivityComponentBuilder();

    NewsCategoryFragmentComponent.Builder NewsCategoryFragmentComponentBuilder();

    NewsPagerAdapterComponent NewsPagerAdapterComponent();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

}
