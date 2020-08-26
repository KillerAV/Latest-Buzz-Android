package com.newsapplicationroom.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.newsapplicationroom.utils.DomainConstants;

@Entity(tableName = DomainConstants.NEWS_TABLE_NAME)
public class NewsEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private String category;

    @NonNull
    private String urlBannerImage;

    @NonNull
    private String url;

    public NewsEntity(@NonNull String title, @NonNull String description, @NonNull String category, @NonNull String urlBannerImage, @NonNull String url) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.urlBannerImage = urlBannerImage;
        this.url = url;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @NonNull
    public int getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @NonNull
    public String getCategory() {
        return category;
    }

    @NonNull
    public String getUrlBannerImage() {
        return urlBannerImage;
    }

    @NonNull
    public String getUrl() {
        return url;
    }
}
