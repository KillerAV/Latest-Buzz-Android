package com.example.newsapplicationroom.components;

import com.example.newsapplicationroom.newscategories.EntertainmentNews;
import com.example.newsapplicationroom.newscategories.HealthNews;
import com.example.newsapplicationroom.newscategories.SportsNews;

import dagger.Component;

@Component
public interface NewsCategoryComponent {
    EntertainmentNews getEntertainmentNews();

    SportsNews getSportsNews();

    HealthNews getHealthNews();
}
