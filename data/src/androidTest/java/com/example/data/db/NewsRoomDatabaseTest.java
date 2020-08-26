package com.example.data.db;

import android.content.Context;
import android.util.Log;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.data.utils.TestConstants;
import com.example.data.utils.TestUtils;
import com.newsapplicationroom.entity.LatestNewsEntity;
import com.newsapplicationroom.entity.NewsEntity;
import com.newsapplicationroom.entity.UserInfoEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class NewsRoomDatabaseTest {
    private NewsDao userDao;
    private NewsRoomDatabase db;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, NewsRoomDatabase.class).build();
        userDao = db.newsDao();
    }

    @After
    public void closeDb() {
        db.close();
    }

    @Test
    public void writeAndReadNews() {
        NewsEntity newsEntity = TestUtils.getNewsEntity();
        userDao.insertNews(newsEntity);
        userDao.getNews(TestConstants.PLACEHOLDER_CATEGORY).observeForever(newsEntityList -> {
            assertThat(1, is(equalTo(newsEntityList.size())));
            assertThat(TestConstants.PLACEHOLDER_TITLE, is(equalTo(newsEntityList.get(0).getTitle())));
            assertThat(TestConstants.PLACEHOLDER_DESCRIPTION, is(equalTo(newsEntityList.get(0).getDescription())));
            assertThat(TestConstants.PLACEHOLDER_CATEGORY, is(equalTo(newsEntityList.get(0).getCategory())));
            assertThat(TestConstants.PLACEHOLDER_IMAGE_URL, is(equalTo(newsEntityList.get(0).getUrlBannerImage())));
            assertThat(TestConstants.PLACEHOLDER_URL, is(equalTo(newsEntityList.get(0).getUrl())));
        });
    }

    @Test
    public void writeAndUpdateNews() {
        NewsEntity newsEntity = TestUtils.getNewsEntity();
        NewsEntity updatedEntity = TestUtils.getUpdatedNewsEntity();
        userDao.insertNews(newsEntity);
        userDao.getNews(TestConstants.PLACEHOLDER_CATEGORY).observeForever(newsEntityList -> {
            if(newsEntityList.size() == 1) {
                updatedEntity.setId(newsEntityList.get(0).getId());
            }
        });
        userDao.updateNews(updatedEntity);

        userDao.getNews(TestConstants.PLACEHOLDER_CATEGORY).observeForever(oldNewsEntityList -> {
            assertThat(0, is(equalTo(oldNewsEntityList.size())));
        });

        userDao.getNews(TestConstants.UPDATED_PLACEHOLDER_CATEGORY).observeForever(newsEntityList -> {
            assertThat(1, is(equalTo(newsEntityList.size())));
            assertThat(TestConstants.UPDATED_PLACEHOLDER_TITLE, is(equalTo(newsEntityList.get(0).getTitle())));
            assertThat(TestConstants.UPDATED_PLACEHOLDER_DESCRIPTION, is(equalTo(newsEntityList.get(0).getDescription())));
            assertThat(TestConstants.UPDATED_PLACEHOLDER_CATEGORY, is(equalTo(newsEntityList.get(0).getCategory())));
            assertThat(TestConstants.UPDATED_PLACEHOLDER_IMAGE_URL, is(equalTo(newsEntityList.get(0).getUrlBannerImage())));
            assertThat(TestConstants.UPDATED_PLACEHOLDER_URL, is(equalTo(newsEntityList.get(0).getUrl())));
        });
    }

    //ISSUE
    @Test
    public void writeAndDeleteNews() {
        NewsEntity newsEntity = TestUtils.getNewsEntity();
        userDao.insertNews(newsEntity);

        userDao.getNews(TestConstants.PLACEHOLDER_CATEGORY).observeForever(newsEntityList -> {
            if(newsEntityList.size() == 1) {
                newsEntity.setId(newsEntityList.get(0).getId());
            }
        });

        userDao.deleteNews(newsEntity);
        userDao.getNews(TestConstants.PLACEHOLDER_CATEGORY).observeForever(newsEntityList -> {
            assertThat(0, is(equalTo(newsEntityList.size())));
        });
    }

    @Test
    public void writeAndDeleteAllNews() {
        NewsEntity newsEntity = TestUtils.getNewsEntity();
        userDao.insertNews(newsEntity);
        userDao.deleteAllNews();
        userDao.getNews(TestConstants.PLACEHOLDER_CATEGORY).observeForever(newsEntityList -> {
            assertThat(0, is(equalTo(newsEntityList.size())));
        });
    }

    @Test
    public void writeAndReadLatestNews() {
        LatestNewsEntity latestNewsEntity = TestUtils.getLatestNewsEntity();
        userDao.insertLatestNews(latestNewsEntity);
        userDao.getLatestNews().observeForever(latestNewsEntityList -> {
            assertThat(1, is(equalTo(latestNewsEntityList.size())));
            assertThat(TestConstants.PLACEHOLDER_TITLE, is(equalTo(latestNewsEntityList.get(0).getTitle())));
            assertThat(TestConstants.PLACEHOLDER_DESCRIPTION, is(equalTo(latestNewsEntityList.get(0).getDescription())));
            assertThat(TestConstants.PLACEHOLDER_CATEGORY, is(equalTo(latestNewsEntityList.get(0).getCategory())));
            assertThat(TestConstants.PLACEHOLDER_IMAGE_URL, is(equalTo(latestNewsEntityList.get(0).getUrlBannerImage())));
            assertThat(TestConstants.PLACEHOLDER_URL, is(equalTo(latestNewsEntityList.get(0).getUrl())));
        });
    }

    @Test
    public void writeAndDeleteAllLatestNews() {
        LatestNewsEntity latestNewsEntity = TestUtils.getLatestNewsEntity();
        userDao.insertLatestNews(latestNewsEntity);
        userDao.deleteLatestNews();
        userDao.getLatestNews().observeForever(newsEntityList -> {
            assertThat(0, is(equalTo(newsEntityList.size())));
        });
    }

    @Test
    public void writeAndReadUserInformation() {
        UserInfoEntity userInfoEntity = TestUtils.getUserInfoEntity();
        userDao.insertUserInformation(userInfoEntity);
        List<UserInfoEntity> userInfoEntityList = userDao.getUserInformation(TestConstants.PLACEHOLDER_EMAIL_ID);
        assertThat(1, is(equalTo(userInfoEntityList.size())));
        assertThat(TestConstants.PLACEHOLDER_EMAIL_ID, is(equalTo(userInfoEntityList.get(0).getEmailId())));
        assertThat(TestConstants.PLACEHOLDER_NAME, is(equalTo(userInfoEntityList.get(0).getName())));
        assertThat(TestConstants.PLACEHOLDER_COUNTRY, is(equalTo(userInfoEntityList.get(0).getCountry())));
    }

    @Test
    public void writeAndReadOneUserInformation() {
        UserInfoEntity userInfoEntity = TestUtils.getUserInfoEntity();
        userDao.insertUserInformation(userInfoEntity);
        List<UserInfoEntity> userInfoEntityList = userDao.getOneUserInformation(TestConstants.PLACEHOLDER_EMAIL_ID);
        assertThat(1, is(equalTo(userInfoEntityList.size())));
        assertThat(TestConstants.PLACEHOLDER_EMAIL_ID, is(equalTo(userInfoEntityList.get(0).getEmailId())));
        assertThat(TestConstants.PLACEHOLDER_NAME, is(equalTo(userInfoEntityList.get(0).getName())));
        assertThat(TestConstants.PLACEHOLDER_COUNTRY, is(equalTo(userInfoEntityList.get(0).getCountry())));
    }

    @Test
    public void writeAndUpdateUserInformation() {
        UserInfoEntity userInfoEntity = TestUtils.getUserInfoEntity();
        userDao.insertUserInformation(userInfoEntity);

        userInfoEntity.setCountry(TestConstants.UPDATED_PLACEHOLDER_COUNTRY);
        userDao.updateUserInformation(userInfoEntity);

        List<UserInfoEntity> userInfoEntityList = userDao.getUserInformation(TestConstants.PLACEHOLDER_EMAIL_ID);
        assertThat(1, is(equalTo(userInfoEntityList.size())));
        assertThat(TestConstants.PLACEHOLDER_EMAIL_ID, is(equalTo(userInfoEntityList.get(0).getEmailId())));
        assertThat(TestConstants.PLACEHOLDER_NAME, is(equalTo(userInfoEntityList.get(0).getName())));
        assertThat(TestConstants.UPDATED_PLACEHOLDER_COUNTRY, is(equalTo(userInfoEntityList.get(0).getCountry())));
    }
}
