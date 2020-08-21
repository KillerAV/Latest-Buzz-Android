package com.example.newsapplicationroom.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.newsapplicationroom.roomdatabase.entity.LatestNewsEntity;
import com.example.newsapplicationroom.roomdatabase.entity.NewsEntity;
import com.example.newsapplicationroom.roomdatabase.entity.UserInfoEntity;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNews(NewsEntity newsEntity);

    @Query("SELECT * FROM News WHERE category = :categoryName")
    LiveData<List<NewsEntity>> getNews(String categoryName);

    @Delete
    void deleteNews(NewsEntity newsEntity);

    @Update
    void updateNews(NewsEntity newsEntity);

    @Query("DELETE FROM News")
    void deleteAllNews();

    @Query("SELECT * FROM Latest_News")
    LiveData<List<LatestNewsEntity>> getLatestNews();

    @Query("DELETE FROM Latest_News")
    void deleteLatestNews();

    @Insert
    void insertLatestNews(LatestNewsEntity newsEntity);

    @Query("SELECT * FROM User_Information WHERE emailId = :emailId")
    List<UserInfoEntity> getUserInformation(String emailId);

    @Update
    void updateUserInformation(UserInfoEntity userInfoEntity);

    @Insert
    void insertUserInformation(UserInfoEntity userInfoEntity);

    @Query("SELECT * FROM USER_INFORMATION WHERE emailId = :emailId LIMIT 1")
    List<UserInfoEntity> getOneUserInformation(String emailId);
}
