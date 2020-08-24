package com.example.newsapplication.mapper;

import com.example.newsapplication.datamodel.Articles;
import com.example.newsapplication.datamodel.NewsApiData;
import com.newsapplicationroom.entity.NewsEntity;

import java.util.ArrayList;
import java.util.List;

public class NewsMapper {
    public static List<NewsEntity> ToNewsEntity(NewsApiData newsApiData, String category) {
        List<Articles> articlesList = newsApiData.getArticles();
        List<NewsEntity> newsEntities = new ArrayList<>();

        if (articlesList != null) {
            for (Articles articles : articlesList) {
                String title = articles.getTitle();
                String description = articles.getDescription();
                String urlToImage = articles.getUrlToImage();
                String url = articles.getUrl();
                if (title != null && description != null && urlToImage != null && title.length() >= 1 && description.length() >= 1 && urlToImage.length() >= 1) {
                    newsEntities.add(new NewsEntity(title, description, category, urlToImage, url));
                }
            }
        }
        return newsEntities;
    }
}
