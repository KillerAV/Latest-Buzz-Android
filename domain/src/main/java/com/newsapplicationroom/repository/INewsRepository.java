package com.newsapplicationroom.repository;

import com.newsapplicationroom.entity.NewsEntity;

public interface INewsRepository {
    void populateDatabase(String country);

    void deleteNews(NewsEntity newsEntity);

    void updateNews(NewsEntity newsEntity);
}
