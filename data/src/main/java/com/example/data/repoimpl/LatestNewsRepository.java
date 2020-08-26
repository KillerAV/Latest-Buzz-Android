package com.example.data.repoimpl;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.data.apiservice.NewsApiHandling;
import com.example.data.datamodel.NewsApiData;
import com.example.data.db.NewsDao;
import com.example.data.db.NewsRoomDatabase;
import com.example.data.di.component.DaggerNewsApiComponent;
import com.example.data.mapper.LatestNewsMapper;
import com.example.data.utils.DataConstants;
import com.newsapplicationroom.entity.LatestNewsEntity;
import com.newsapplicationroom.repository.ILatestNewsRepository;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatestNewsRepository implements ILatestNewsRepository {
    @Inject
    NewsApiHandling newsApiHandling;

    private NewsDao newsDao;

    public LatestNewsRepository(Application application) {
        NewsRoomDatabase newsRoomDatabase = NewsRoomDatabase.getDatabaseInstance(application.getApplicationContext());
        newsDao = newsRoomDatabase.newsDao();
        DaggerNewsApiComponent.create().inject(this);
    }

    @Override
    public void populateLatestNews(String country, String fromDate, String toDate) {
        new NewsApiLatestAsync().execute(country, fromDate, toDate);
    }

    public LiveData<List<LatestNewsEntity>> getLatestNews() {
        return newsDao.getLatestNews();
    }

    private class NewsApiLatestAsync extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            newsDao.deleteLatestNews();
            Callback<NewsApiData> responseCallback = new Callback<NewsApiData>() {
                @Override
                public void onResponse(Call<NewsApiData> call, Response<NewsApiData> response) {
                    if (response.isSuccessful()) {
                        NewsApiData newsApiData = response.body();
                        List<LatestNewsEntity> latestNewsEntities = LatestNewsMapper.ToLatestNewsEntity(newsApiData, DataConstants.GENERAL_CATEGORY_NEWS_LABEL);
                        new populateDatabaseAsync(newsDao).execute(latestNewsEntities);
                    }
                }

                @Override
                public void onFailure(Call<NewsApiData> call, Throwable t) {
                    Log.e(NewsApiHandling.class.getSimpleName(), t.getMessage());
                }
            };
            newsApiHandling.getLatestNewsUsingApiCall(strings[0], DataConstants.GENERAL_CATEGORY_NEWS_LABEL, strings[1], strings[2], DataConstants.LATEST_NEWS_PAGE_SIZE, responseCallback);
            return null;
        }
    }

    private static class populateDatabaseAsync extends AsyncTask<List<LatestNewsEntity>, Void, Void> {
        NewsDao newsDao;
        public populateDatabaseAsync(NewsDao newsDao) {
            this.newsDao = newsDao;
        }
        @Override
        protected Void doInBackground(List<LatestNewsEntity>... lists) {
            for (LatestNewsEntity latestNewsEntity : lists[0]) {
                newsDao.insertLatestNews(latestNewsEntity);
            }
            return null;
        }
    }
}
