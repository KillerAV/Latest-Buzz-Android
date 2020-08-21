package com.example.newsapplicationroom.roomdatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.newsapplicationroom.roomdatabase.entity.LatestNewsEntity;
import com.example.newsapplicationroom.roomdatabase.entity.NewsEntity;
import com.example.newsapplicationroom.roomdatabase.entity.UserInfoEntity;
import com.example.newsapplicationroom.utils.Constants;

@Database(entities = {NewsEntity.class, LatestNewsEntity.class, UserInfoEntity.class}, version = 8, exportSchema = false)
public abstract class NewsRoomDatabase extends RoomDatabase {
    public abstract NewsDao newsDao();

    private static NewsRoomDatabase INSTANCE;

    private static RoomDatabase.Callback initialAddingToDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    static NewsRoomDatabase getDatabaseInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (NewsRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NewsRoomDatabase.class, Constants.DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(initialAddingToDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
