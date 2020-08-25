package com.example.newsapplication.repoimpl;

import android.app.Application;
import android.os.AsyncTask;

import com.example.newsapplication.db.NewsDao;
import com.example.newsapplication.db.NewsRoomDatabase;
import com.newsapplicationroom.entity.UserInfoEntity;
import com.newsapplicationroom.repository.IUserInformationRepository;

import java.util.List;

import javax.inject.Inject;

public class UserInformationRepository implements IUserInformationRepository {

    private NewsDao newsDao;

    @Inject
    public UserInformationRepository(Application application) {
        NewsRoomDatabase newsRoomDatabase = NewsRoomDatabase.getDatabaseInstance(application.getApplicationContext());
        newsDao = newsRoomDatabase.newsDao();
    }

    @Override
    public List<UserInfoEntity> getUserInformation(String email) {
        return newsDao.getUserInformation(email);
    }

    @Override
    public void updateUserInformation(UserInfoEntity userInfoEntity) {
        new UpdateUserInfoAsync(newsDao).execute(userInfoEntity);
    }

    @Override
    public void insertUserInformation(UserInfoEntity userInfoEntity) {
        new InsertUserInfoAsync(newsDao).execute(userInfoEntity);
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

    private static class InsertUserInfoAsync extends AsyncTask<UserInfoEntity, Void, Void> {
        NewsDao newsDao;

        public InsertUserInfoAsync(NewsDao newsDao) {
            this.newsDao = newsDao;
        }

        @Override
        protected Void doInBackground(UserInfoEntity... userInfoEntities) {
            List<UserInfoEntity> userInfoEntityList = newsDao.getOneUserInformation(userInfoEntities[0].getEmailId());
            if (userInfoEntityList.size() == 0) {
                newsDao.insertUserInformation(userInfoEntities[0]);
            }
            return null;
        }
    }
}
