package com.example.newsapplication.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.newsapplication.utils.Constants;
import com.newsapplicationroom.entity.LatestNewsEntity;
import com.newsapplicationroom.entity.NewsEntity;
import com.newsapplicationroom.entity.UserInfoEntity;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNews(NewsEntity newsEntity);

    @Query(Constants.GET_NEWS)
    LiveData<List<NewsEntity>> getNews(String categoryName);

    @Delete
    void deleteNews(NewsEntity newsEntity);

    @Update
    void updateNews(NewsEntity newsEntity);

    @Query(Constants.DELETE_ALL_NEWS)
    void deleteAllNews();

    @Query(Constants.GET_LATEST_NEWS)
    LiveData<List<LatestNewsEntity>> getLatestNews();

    @Query(Constants.DELETE_ALL_LATEST_NEWS)
    void deleteLatestNews();

    @Insert
    void insertLatestNews(LatestNewsEntity newsEntity);

    @Query(Constants.GET_USER_INFORMATION)
    List<UserInfoEntity> getUserInformation(String emailId);

    @Update
    void updateUserInformation(UserInfoEntity userInfoEntity);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUserInformation(UserInfoEntity userInfoEntity);

    @Query(Constants.GET_ONE_USER_INFORMATION)
    List<UserInfoEntity> getOneUserInformation(String emailId);
}
