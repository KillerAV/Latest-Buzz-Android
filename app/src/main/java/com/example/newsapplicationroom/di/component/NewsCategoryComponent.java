package com.example.newsapplicationroom.di.component;

import com.example.newsapplicationroom.ui.newsfragments.EntertainmentNewsFragment;
import com.example.newsapplicationroom.ui.newsfragments.HealthNewsFragment;
import com.example.newsapplicationroom.ui.newsfragments.SportsNewsFragment;

import dagger.Component;

@Component
public interface NewsCategoryComponent {
    EntertainmentNewsFragment getEntertainmentNews();

    SportsNewsFragment getSportsNews();

    HealthNewsFragment getHealthNews();
}
