package com.example.data.datamodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsApiData {
    @SerializedName("totalResults")
    private String totalResults;

    @SerializedName("articles")
    private List<Articles> articles;

    public String getTotalResults() {
        return totalResults;
    }

    public List<Articles> getArticles() {
        return articles;
    }
}
