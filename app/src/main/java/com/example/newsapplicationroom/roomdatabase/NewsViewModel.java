package com.example.newsapplicationroom.roomdatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.newsapplicationroom.roomdatabase.entity.LatestNewsEntity;
import com.example.newsapplicationroom.roomdatabase.entity.NewsEntity;
import com.example.newsapplicationroom.roomdatabase.entity.UserInfoEntity;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {
    private NewsRepository newsRepository;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        newsRepository = new NewsRepository(application);
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

    public LiveData<List<LatestNewsEntity>> getLatestNews() {
        return newsRepository.getLatestNews();
    }

    public void populateDatabase(String country) {
        newsRepository.populateDatabase(country);
    }

    public void populateLatestNews(String country, String fromDate, String toDate) {
        newsRepository.populateLatestNews(country, fromDate, toDate);
    }

    public List<UserInfoEntity> getUserInformation(String email) {
        return newsRepository.getUserInformation(email);
    }

    public void updateUserInformation(UserInfoEntity userInfoEntity) {
        newsRepository.updateUserInformation(userInfoEntity);
    }

    public void insertUserInformation(UserInfoEntity userInfoEntity) {
        newsRepository.insertUserInformation(userInfoEntity);
    }
}
