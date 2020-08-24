package com.example.newsapplication.utils;

import com.newsapplicationroom.entity.NewsEntity;
import com.newsapplicationroom.entity.LatestNewsEntity;
import com.newsapplicationroom.entity.UserInfoEntity;

public class TestUtils {
    public static NewsEntity getNewsEntity() {
        return new NewsEntity(TestConstants.PLACEHOLDER_TITLE, TestConstants.PLACEHOLDER_DESCRIPTION,
                TestConstants.PLACEHOLDER_CATEGORY, TestConstants.PLACEHOLDER_IMAGE_URL,
                TestConstants.PLACEHOLDER_URL);
    }

    public static UserInfoEntity getUserInfoEntity() {
        return new UserInfoEntity(TestConstants.PLACEHOLDER_EMAIL_ID, TestConstants.PLACEHOLDER_NAME);
    }

    public static LatestNewsEntity getLatestNewsEntity() {
        return new LatestNewsEntity(TestConstants.PLACEHOLDER_TITLE, TestConstants.PLACEHOLDER_DESCRIPTION,
                TestConstants.PLACEHOLDER_CATEGORY, TestConstants.PLACEHOLDER_IMAGE_URL,
                TestConstants.PLACEHOLDER_URL);
    }

    public static NewsEntity getUpdatedNewsEntity() {
        return new NewsEntity(TestConstants.UPDATED_PLACEHOLDER_TITLE, TestConstants.UPDATED_PLACEHOLDER_DESCRIPTION,
                TestConstants.UPDATED_PLACEHOLDER_CATEGORY, TestConstants.UPDATED_PLACEHOLDER_IMAGE_URL,
                TestConstants.UPDATED_PLACEHOLDER_URL);
    }
}
