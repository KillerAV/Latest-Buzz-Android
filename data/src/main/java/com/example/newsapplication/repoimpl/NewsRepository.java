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
import com.example.newsapplication.mapper.NewsMapper;
import com.newsapplicationroom.entity.NewsEntity;
import com.newsapplicationroom.repository.INewsRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Math.ceil;
import static java.lang.Math.min;

public class NewsRepository implements INewsRepository {
    private NewsDao newsDao;

    public NewsRepository(Application application) {
        NewsRoomDatabase newsRoomDatabase = NewsRoomDatabase.getDatabaseInstance(application.getApplicationContext());
        newsDao = newsRoomDatabase.newsDao();
    }

    @Override
    public void populateDatabase(String country) {
        new NewsApiAsync().execute(country);
    }

    @Override
    public void deleteNews(NewsEntity newsEntity) {
        new deleteAsync(newsDao).execute(newsEntity);
    }

    @Override
    public void updateNews(NewsEntity newsEntity) {
        new updateAsync(newsDao).execute(newsEntity);
    }

    public LiveData<List<NewsEntity>> getNews(String category) {
        return newsDao.getNews(category);
    }

    private static Callback<NewsApiData> getNormalPageResponseCallback(final NewsDao newsDao, final String s) {
        return new Callback<NewsApiData>() {
            @Override
            public void onResponse(Call<NewsApiData> call, Response<NewsApiData> response) {
                if (response.isSuccessful()) {
                    NewsApiData newsApiData = response.body();
                    List<NewsEntity> newsEntities = NewsMapper.ToNewsEntity(newsApiData, s);
                    for(NewsEntity newsEntity : newsEntities) {
                        new insertAsync(newsDao).execute(newsEntity);
                    }
                }
            }
            @Override
            public void onFailure(Call<NewsApiData> call, Throwable t) {
                Log.e(NewsApiHandling.class.getSimpleName(), t.getMessage());
            }
        };
    }

    private static Callback<NewsApiData> getFirstPageResponseCallback(final String country, final NewsDao newsDao, final String s) {
        return new Callback<NewsApiData>() {
            @Override
            public void onResponse(Call<NewsApiData> call, Response<NewsApiData> response) {
                if (response.isSuccessful()) {
                    NewsApiData newsApiData = response.body();

                    List<NewsEntity> newsEntities = NewsMapper.ToNewsEntity(newsApiData, s);
                    if(newsEntities.size() != 0) {
                        new DeleteAllAsync(newsDao).execute();
                        for (NewsEntity newsEntity : newsEntities) {
                            new insertAsync(newsDao).execute(newsEntity);
                        }
                    }

                    String totalRecords = newsApiData.getTotalResults();
                    int defaultPageSize = 20;
                    int numberOfPages = (int) ceil(Double.parseDouble(totalRecords) / defaultPageSize);
                    for (int page = 2; page <= min(numberOfPages, Constants.MAX_NUMBER_OF_PAGES_LIMIT); page++) {
                        NewsApiHandling.getNewsUsingApiCall(country, s, page + "", getNormalPageResponseCallback(newsDao, s));
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsApiData> call, Throwable t) {
                Log.e(NewsApiHandling.class.getSimpleName(), t.getMessage());
            }
        };
    }

    private class NewsApiAsync extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... strings) {
            for (final String s : Constants.category) {
                NewsApiHandling.getNewsUsingApiCall(strings[0], s, "1", getFirstPageResponseCallback(strings[0], newsDao, s));
            }
            return null;
        }
    }

    private static class DeleteAllAsync extends AsyncTask<Void, Void, Void> {

        private NewsDao newsDao;

        public DeleteAllAsync(NewsDao newsDao) {
            this.newsDao = newsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            newsDao.deleteAllNews();
            return null;
        }
    }

    private static class insertAsync extends AsyncTask<NewsEntity, Void, Void> {
        private final NewsDao newsDao;

        public insertAsync(NewsDao newsDao) {
            this.newsDao = newsDao;
        }

        @Override
        protected Void doInBackground(NewsEntity... newsEntities) {
            newsDao.insertNews(newsEntities[0]);
            return null;
        }
    }

    private static class deleteAsync extends AsyncTask<NewsEntity, Void, Void> {
        private final NewsDao newsDao;

        public deleteAsync(NewsDao newsDao) {
            this.newsDao = newsDao;
        }

        @Override
        protected Void doInBackground(NewsEntity... newsEntities) {
            newsDao.deleteNews(newsEntities[0]);
            return null;
        }
    }

    private static class updateAsync extends AsyncTask<NewsEntity, Void, Void> {
        private final NewsDao newsDao;

        public updateAsync(NewsDao newsDao) {
            this.newsDao = newsDao;
        }

        @Override
        protected Void doInBackground(NewsEntity... newsEntities) {
            newsDao.updateNews(newsEntities[0]);
            return null;
        }
    }
}
