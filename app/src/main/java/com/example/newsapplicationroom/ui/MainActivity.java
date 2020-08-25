package com.example.newsapplicationroom.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.newsapplicationroom.R;
import com.example.newsapplicationroom.di.component.DaggerAdapterComponent;
import com.example.newsapplicationroom.di.component.DaggerPagerAdapterComponent;
import com.example.newsapplicationroom.ui.bulletnews.AlarmReceiver;
import com.example.newsapplicationroom.ui.bulletnews.LatestNewsActivity;
import com.example.newsapplicationroom.di.component.AdapterComponent;
import com.example.newsapplicationroom.di.component.PagerAdapterComponent;
import com.example.newsapplicationroom.viewmodel.NewsViewModel;
import com.example.newsapplicationroom.viewmodel.UserInformationViewModel;
import com.example.newsapplicationroom.utils.Constants;
import com.example.newsapplicationroom.utils.DateUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.newsapplicationroom.entity.UserInfoEntity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static NewsViewModel newsViewModel;
    private static UserInformationViewModel userInformationViewModel;
    private static FirebaseUser firebaseUser;
    private static AdapterComponent adapterComponent;
    private static UserInfoEntity userInfoEntity;
    public static boolean isAlarmLaunched = false;
    public static String fromDate, toDate;

    FirebaseAnalytics firebaseAnalytics;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        adapterComponent = DaggerAdapterComponent.builder().setContext(this).build();

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        userInformationViewModel = ViewModelProviders.of(this).get(UserInformationViewModel.class);
        new InitialiseInformationAsync().execute();
        initialiseToolbar();
        createAlarm();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.PROFILE_UPDATE_REQUEST_CODE && resultCode == RESULT_OK) {
            String country = data.getStringExtra(Constants.EXTRA_COUNTRY_NAME);
            fetchNews(country);
            userInfoEntity.setCountry(country);
            userInformationViewModel.updateUserInformation(userInfoEntity);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_update_news) {
            fetchNews(userInfoEntity.getCountry());
            return true;
        } else if (id == R.id.action_latest_news_alarm) {
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Latest News");
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
            Intent intent = new Intent(this, LatestNewsActivity.class);
            intent.putExtra(Constants.EXTRA_IS_ALARM_LAUNCHED, isAlarmLaunched);
            intent.putExtra(Constants.EXTRA_FROM_DATE, fromDate);
            intent.putExtra(Constants.EXTRA_TO_DATE, toDate);
            isAlarmLaunched = false;
            startActivity(intent);
            return true;
        } else if (id == R.id.action_profile) {
            Intent intent = new Intent(this, ProfilePageActivity.class);
            intent.putExtra(Constants.EXTRA_COUNTRY_NAME, userInfoEntity.getCountry());
            intent.putExtra(Constants.EXTRA_USER_NAME, firebaseUser.getDisplayName());
            intent.putExtra(Constants.EXTRA_EMAIL_ID, firebaseUser.getEmail());
            intent.putExtra(Constants.EXTRA_PROFILE_PICTURE, firebaseUser.getPhotoUrl());
            startActivityForResult(intent, Constants.PROFILE_UPDATE_REQUEST_CODE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static NewsViewModel getViewModel() {
        return newsViewModel;
    }

    public static AdapterComponent getAdapterComponent() {
        return adapterComponent;
    }

    private void initialiseToolbar() {
        setSupportActionBar(toolbar);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.entertainment_news_tag));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.sports_news_tag));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.health_news_tag));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        PagerAdapterComponent pagerAdapterComponent = DaggerPagerAdapterComponent.builder()
                .setFragmentManager(getSupportFragmentManager())
                .setTabCount(tabLayout.getTabCount())
                .build();

        viewPager.setAdapter(pagerAdapterComponent.getNewsPagerAdapter());

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                firebaseAnalytics.setUserProperty("Type_of_News", Constants.category[position]);
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void createAlarm() {
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent
                = PendingIntent.getBroadcast(this,
                Constants.ALARM_PENDING_INTENT_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        long repeatInterval = AlarmManager.INTERVAL_DAY;
        Calendar day = DateUtils.getCalenderDay(0, 0, 0, 8);
        long triggerTime = day.getTimeInMillis();
        while (triggerTime < System.currentTimeMillis()) {
            triggerTime += repeatInterval;
        }
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, triggerTime, repeatInterval, pendingIntent);
    }

    private void fetchNews(String country) {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;

        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected()) {
            firebaseAnalytics.setUserProperty("Country", country);
            newsViewModel.populateDatabase(country);
        } else {
            Toast.makeText(this, R.string.toast_no_internet, Toast.LENGTH_SHORT).show();
        }
    }

    private class InitialiseInformationAsync extends AsyncTask<Void, Void, Void> {

        private List<UserInfoEntity> userInfoEntities;

        @Override
        protected Void doInBackground(Void... voids) {
            userInfoEntities = userInformationViewModel.getUserInformation(firebaseUser.getEmail());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            userInfoEntity = userInfoEntities.get(0);
            String country = userInfoEntity.getCountry();
            fetchNews(country);
        }
    }
}