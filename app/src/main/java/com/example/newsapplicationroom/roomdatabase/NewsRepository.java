package com.example.newsapplicationroom.roomdatabase;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.newsapplicationroom.newsapi.Articles;
import com.example.newsapplicationroom.newsapi.NewsApiData;
import com.example.newsapplicationroom.newsapi.NewsApiHandling;
import com.example.newsapplicationroom.roomdatabase.entity.LatestNewsEntity;
import com.example.newsapplicationroom.roomdatabase.entity.NewsEntity;
import com.example.newsapplicationroom.roomdatabase.entity.UserInfoEntity;
import com.example.newsapplicationroom.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Math.ceil;
import static java.lang.Math.min;

public class NewsRepository {

    private NewsDao newsDao;

    NewsRepository(Application application) {
        NewsRoomDatabase newsRoomDatabase = NewsRoomDatabase.getDatabaseInstance(application.getApplicationContext());
        newsDao = newsRoomDatabase.newsDao();
    }

    public void populateLatestNews(String country, String fromDate, String toDate) {
        new DeleteLatestNewsAsync(newsDao).execute();
        new NewsApiLatestAsync().execute(country, fromDate, toDate);
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
                            List<Articles> articlesList = newsApiData.getArticles();

                            if (articlesList != null) {
                                for (Articles articles : articlesList) {
                                    String title = articles.getTitle();
                                    String description = articles.getDescription();
                                    String urlToImage = articles.getUrlToImage();
                                    String url = articles.getUrl();
                                    if (title != null && description != null && urlToImage != null && title.length() >= 1 && description.length() >= 1 && urlToImage.length() >= 1) {
                                        new insertLatestAsync(newsDao).execute(new LatestNewsEntity(title, description, s, urlToImage, url));
                                    }
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

    public void populateDatabase(String country) {
        new DeleteAllAsync(newsDao).execute();
        new NewsApiAsync().execute(country);
    }

    private static Callback<NewsApiData> getNormalPageResponseCallback(final NewsDao newsDao, final String s) {
        return new Callback<NewsApiData>() {
            @Override
            public void onResponse(Call<NewsApiData> call, Response<NewsApiData> response) {
                if (response.isSuccessful()) {
                    NewsApiData newsApiData = response.body();
                    List<Articles> articlesList = newsApiData.getArticles();
                    if (articlesList != null) {
                        for (Articles articles : articlesList) {
                            String title = articles.getTitle();
                            String description = articles.getDescription();
                            String urlToImage = articles.getUrlToImage();
                            String url = articles.getUrl();
                            if (title != null && description != null && urlToImage != null && title.length() >= 1 && description.length() >= 1 && urlToImage.length() >= 1) {
                                new insertAsync(newsDao).execute(new NewsEntity(title, description, s, urlToImage, url));
                            }
                        }
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
                    List<Articles> articlesList = newsApiData.getArticles();
                    if (articlesList != null && articlesList.size() > 0) {
                        for (Articles articles : articlesList) {
                            String title = articles.getTitle();
                            String description = articles.getDescription();
                            String urlToImage = articles.getUrlToImage();
                            String url = articles.getUrl();
                            if (title != null && description != null && urlToImage != null && title.length() >= 1 && description.length() >= 1 && urlToImage.length() >= 1) {
                                new insertAsync(newsDao).execute(new NewsEntity(title, description, s, urlToImage, url));
                            }
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

    public LiveData<List<NewsEntity>> getNews(String category) {
        return newsDao.getNews(category);
    }

    public LiveData<List<LatestNewsEntity>> getLatestNews() {
        return newsDao.getLatestNews();
    }

    public void deleteNews(NewsEntity newsEntity) {
        new deleteAsync(newsDao).execute(newsEntity);
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

    public void updateNews(NewsEntity newsEntity) {
        new updateAsync(newsDao).execute(newsEntity);
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

    public List<UserInfoEntity> getUserInformation(String email) {
        return newsDao.getUserInformation(email);
    }

    public void updateUserInformation(UserInfoEntity userInfoEntity) {
        new UpdateUserInfoAsync(newsDao).execute(userInfoEntity);
    }

    private static class UpdateUserInfoAsync extends AsyncTask<UserInfoEntity, Void, Void> {
        NewsDao newsDao;

        public UpdateUserInfoAsync(NewsDao newsDao) {
            this.newsDao = newsDao;
        }

        @Override
        protected Void doInBackground(UserInfoEntity... userInfoEntities) {
            newsDao.updateUserInformation(userInfoEntities[0]);
            return null;
        }
    }

    public void insertUserInformation(UserInfoEntity userInfoEntity) {
        new InsertUserInfoAsync(newsDao).execute(userInfoEntity);
    }

    private static class InsertUserInfoAsync extends AsyncTask<UserInfoEntity, Void, Void> {
        NewsDao newsDao;

        public InsertUserInfoAsync(NewsDao newsDao) {
            this.newsDao = newsDao;
        }

        @Override
        protected Void doInBackground(UserInfoEntity... userInfoEntities) {
            if (newsDao.getOneUserInformation(userInfoEntities[0].getEmailId()).size() < 1) {
                newsDao.insertUserInformation(userInfoEntities[0]);
            }
            return null;
        }
    }
}
