package com.example.newsapplication.repoimpl;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.newsapplication.datamodel.NewsApiData;
import com.example.newsapplication.apiservice.NewsApiHandling;
import com.example.newsapplication.utils.Constants;
import com.example.newsapplication.db.NewsDao;
import com.example.newsapplication.db.NewsRoomDatabase;
import com.example.newsapplication.mapper.LatestNewsMapper;
import com.newsapplicationroom.entity.LatestNewsEntity;
import com.newsapplicationroom.repository.ILatestNewsRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LatestNewsRepository implements ILatestNewsRepository {
    private NewsDao newsDao;

    public LatestNewsRepository(Application application) {
        NewsRoomDatabase newsRoomDatabase = NewsRoomDatabase.getDatabaseInstance(application.getApplicationContext());
        newsDao = newsRoomDatabase.newsDao();
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
            for (final String s : Constants.latestNewsCategory) {
                Callback<NewsApiData> responseCallback = new Callback<NewsApiData>() {
                    @Override
                    public void onResponse(Call<NewsApiData> call, Response<NewsApiData> response) {
                        if (response.isSuccessful()) {
                            NewsApiData newsApiData = response.body();
                            List<LatestNewsEntity> latestNewsEntities = LatestNewsMapper.ToLatestNewsEntity(newsApiData, s);
                            if(latestNewsEntities.size() != 0)
                            {
                                new DeleteLatestNewsAsync(newsDao).execute();
                                for(LatestNewsEntity latestNewsEntity : latestNewsEntities) {
                                    new insertLatestAsync(newsDao).execute(latestNewsEntity);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsApiData> call, Throwable t) {
                        Log.e(NewsApiHandling.class.getSimpleName(), t.getMessage());
                    }
                };
                NewsApiHandling.getLatestNewsUsingApiCall(strings[0], s, strings[1], strings[2], Constants.LATEST_NEWS_PAGE_SIZE, responseCallback);
            }
            return null;
        }
    }

    private static class DeleteLatestNewsAsync extends AsyncTask<Void, Void, Void> {
        private NewsDao newsDao;
        public DeleteLatestNewsAsync(NewsDao newsDao) {
            this.newsDao = newsDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            newsDao.deleteLatestNews();
            return null;
        }
    }

    private static class insertLatestAsync extends AsyncTask<LatestNewsEntity, Void, Void> {
        NewsDao newsDao;
        public insertLatestAsync(NewsDao newsDao) {
            this.newsDao = newsDao;
        }
        @Override
        protected Void doInBackground(LatestNewsEntity... newsEntities) {
            newsDao.insertLatestNews(newsEntities[0]);
            return null;
        }
    }
}
