package com.example.newsapplicationroom.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.newsapplication.repoimpl.LatestNewsRepository;
import com.newsapplicationroom.entity.LatestNewsEntity;

import java.util.List;

public class LatestNewsViewModel extends AndroidViewModel {
    private LatestNewsRepository latestNewsRepository;

    public LatestNewsViewModel(@NonNull Application application) {
        super(application);
        latestNewsRepository = new LatestNewsRepository(application);
    }
    public LiveData<List<LatestNewsEntity>> getLatestNews() {
        return latestNewsRepository.getLatestNews();
    }

    public void populateLatestNews(String country, String fromDate, String toDate) {
        latestNewsRepository.populateLatestNews(country, fromDate, toDate);
    }
}
