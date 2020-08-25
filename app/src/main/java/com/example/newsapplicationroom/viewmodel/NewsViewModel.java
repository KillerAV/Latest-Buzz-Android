package com.example.newsapplicationroom.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.newsapplication.repoimpl.NewsRepository;
import com.example.newsapplicationroom.ui.NewsApplication;
import com.newsapplicationroom.entity.NewsEntity;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {
    private NewsRepository newsRepository;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        newsRepository = NewsApplication.getRoomDatabaseComponent().getNewsRepository();
    }

    public void deleteNews(NewsEntity newsEntity) {
        newsRepository.deleteNews(newsEntity);
    }

    public void updateNews(NewsEntity newsEntity) {
        newsRepository.updateNews(newsEntity);
    }

    public LiveData<List<NewsEntity>> getNews(String category) {
        return newsRepository.getNews(category);
    }

    public void populateDatabase(String country) {
        newsRepository.populateDatabase(country);
    }
}
