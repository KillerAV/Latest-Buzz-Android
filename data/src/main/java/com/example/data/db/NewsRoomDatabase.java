package com.example.data.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.data.utils.DataConstants;
import com.newsapplicationroom.entity.LatestNewsEntity;
import com.newsapplicationroom.entity.NewsEntity;
import com.newsapplicationroom.entity.UserInfoEntity;


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

    public static NewsRoomDatabase getDatabaseInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (NewsRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NewsRoomDatabase.class, DataConstants.DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(initialAddingToDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
