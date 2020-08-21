package com.example.newsapplicationroom;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.newsapplicationroom.bulletnews.AlarmReceiver;
import com.example.newsapplicationroom.bulletnews.LatestNews;
import com.example.newsapplicationroom.components.AdapterComponent;
import com.example.newsapplicationroom.components.DaggerAdapterComponent;
import com.example.newsapplicationroom.components.DaggerPagerAdapterComponent;
import com.example.newsapplicationroom.components.PagerAdapterComponent;
import com.example.newsapplicationroom.roomdatabase.NewsViewModel;
import com.example.newsapplicationroom.roomdatabase.entity.UserInfoEntity;
import com.example.newsapplicationroom.utils.Constants;
import com.example.newsapplicationroom.utils.DateUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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

    private static NewsViewModel viewModel;
    private static String country;
    private static FirebaseUser firebaseUser;
    private static AdapterComponent adapterComponent;

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

        viewModel = ViewModelProviders.of(this).get(NewsViewModel.class);

        new InitialiseInformationAsync().execute();
        initialiseToolbar();
        createAlarm();
    }

    private class InitialiseInformationAsync extends AsyncTask<Void, Void, Void> {

        private List<UserInfoEntity> userInfoEntities;

        @Override
        protected Void doInBackground(Void... voids) {
            userInfoEntities = viewModel.getUserInformation(firebaseUser.getEmail());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            country = userInfoEntities.get(0).getCountry();
            fetchLatestNews(country);
        }
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

    private void fetchLatestNews(String country) {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;

        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected()) {
            firebaseAnalytics.setUserProperty("Country", country);
            viewModel.populateDatabase(country);
        } else {
            Toast.makeText(this, R.string.toast_no_internet, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.PROFILE_UPDATE_REQUEST_CODE && resultCode == RESULT_OK) {
            country = data.getStringExtra(Constants.EXTRA_COUNTRY_NAME);
            fetchLatestNews(country);
            UserInfoEntity userInfoEntity = getUserInfoEntity();
            userInfoEntity.setCountry(country);
            viewModel.updateUserInformation(userInfoEntity);
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
            fetchLatestNews(country);
            return true;
        } else if (id == R.id.action_latest_news_alarm) {
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Latest News");
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
            Intent intent = new Intent(this, LatestNews.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_profile) {
            Intent intent = new Intent(this, ProfilePage.class);
            intent.putExtra(Constants.EXTRA_COUNTRY_NAME, country);
            intent.putExtra(Constants.EXTRA_USER_NAME, firebaseUser.getDisplayName());
            intent.putExtra(Constants.EXTRA_EMAIL_ID, firebaseUser.getEmail());
            intent.putExtra(Constants.EXTRA_PROFILE_PICTURE, firebaseUser.getPhotoUrl());
            startActivityForResult(intent, Constants.PROFILE_UPDATE_REQUEST_CODE);

        }
        return super.onOptionsItemSelected(item);
    }

    private static UserInfoEntity getUserInfoEntity() {
        String email = firebaseUser.getEmail();
        String name = firebaseUser.getDisplayName();
        return new UserInfoEntity(email, name);
    }

    public static NewsViewModel getViewModel() {
        return viewModel;
    }

    public static AdapterComponent getAdapterComponent() {
        return adapterComponent;
    }
}