package com.example.newsapplicationroom.ui.bulletnews;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.newsapplicationroom.di.component.DaggerAdapterComponent;
import com.example.newsapplicationroom.ui.MainActivity;
import com.example.newsapplicationroom.ui.adapter.LatestNewsAdapter;

import com.example.newsapplicationroom.viewmodel.LatestNewsViewModel;
import com.example.newsapplicationroom.R;
import com.example.newsapplicationroom.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LatestNewsActivity extends AppCompatActivity {

    @BindView(R.id.latest_news_recycler_view)
    RecyclerView recyclerView;

    private static LatestNewsViewModel latestNewsViewModel;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_news);
        ButterKnife.bind(this);

        latestNewsViewModel = ViewModelProviders.of(this).get(LatestNewsViewModel.class);

        Intent intent = getIntent();
        boolean updateDatabase = intent.getBooleanExtra(Constants.EXTRA_IS_ALARM_LAUNCHED, false);
        if(updateDatabase) {
            String fromDate = intent.getStringExtra(Constants.EXTRA_FROM_DATE);
            String toDate = intent.getStringExtra(Constants.EXTRA_FROM_DATE);
            populateLatestNewsDatabase(fromDate, toDate);
        }

        final LatestNewsAdapter adapter = DaggerAdapterComponent.builder().setContext(this).build().getLatestNewsAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        latestNewsViewModel.getLatestNews().observe(this, latestNewsEntities -> adapter.setListOfNews(latestNewsEntities));
    }

    private static void populateLatestNewsDatabase(String fromDate, String toDate) {
        for (String country : Constants.COUNTRY_CODE.values()) {
            latestNewsViewModel.populateLatestNews(country, fromDate, toDate);
        }
        MainActivity.isAlarmLaunched = false;
    }
}