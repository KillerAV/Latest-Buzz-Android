package com.example.newsapplicationroom.di.component;

import com.example.newsapplicationroom.ui.newsfragments.EntertainmentNewsFragment;
import com.example.newsapplicationroom.ui.newsfragments.HealthNewsFragment;
import com.example.newsapplicationroom.ui.newsfragments.SportsNewsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component
public interface NewsCategoryComponent {
    EntertainmentNewsFragment getEntertainmentNews();

    SportsNewsFragment getSportsNews();

    HealthNewsFragment getHealthNews();
}
