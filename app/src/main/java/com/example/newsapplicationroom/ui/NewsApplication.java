package com.example.newsapplicationroom.ui;

import android.app.Application;

import com.example.newsapplicationroom.di.component.DaggerNewsCategoryComponent;
import com.example.newsapplicationroom.di.component.DaggerRoomDatabaseComponent;
import com.example.newsapplicationroom.di.component.NewsCategoryComponent;
import com.example.newsapplicationroom.di.component.RoomDatabaseComponent;

public class NewsApplication extends Application{
    private static RoomDatabaseComponent roomDatabaseComponent;
    private static NewsCategoryComponent newsCategoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        roomDatabaseComponent = DaggerRoomDatabaseComponent.builder().setApplication(this).build();
        newsCategoryComponent = DaggerNewsCategoryComponent.create();
    }

    public static RoomDatabaseComponent getRoomDatabaseComponent() {
        return roomDatabaseComponent;
    }

    public static NewsCategoryComponent getNewsCategoryComponent() {
        return newsCategoryComponent;
    }
}
