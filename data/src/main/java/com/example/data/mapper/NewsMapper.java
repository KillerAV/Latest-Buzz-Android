package com.example.data.mapper;

import com.example.data.datamodel.Articles;
import com.example.data.datamodel.NewsApiData;
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
