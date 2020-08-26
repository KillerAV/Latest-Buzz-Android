package com.example.newsapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.data.repoimpl.LatestNewsRepository;
import com.example.newsapplication.ui.NewsApplication;
import com.newsapplicationroom.entity.LatestNewsEntity;

import java.util.List;

import javax.inject.Inject;

public class LatestNewsViewModel extends AndroidViewModel {
    @Inject
    LatestNewsRepository latestNewsRepository;

    public LatestNewsViewModel(@NonNull Application application) {
        super(application);
        NewsApplication.getAppComponent().inject(this);
    }

    public LiveData<List<LatestNewsEntity>> getLatestNews() {
        return latestNewsRepository.getLatestNews();
    }

    public void populateLatestNews(String country, String fromDate, String toDate) {
        latestNewsRepository.populateLatestNews(country, fromDate, toDate);
    }
}
