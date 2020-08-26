package com.example.newsapplication.ui.bulletnews;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.newsapplication.di.component.AdapterComponent;
import com.example.newsapplication.ui.MainActivity;
import com.example.newsapplication.ui.NewsApplication;
import com.example.newsapplication.ui.adapter.LatestNewsAdapter;

import com.example.newsapplication.viewmodel.LatestNewsViewModel;
import com.example.newsapplication.R;
import com.example.newsapplication.utils.AppConstants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LatestNewsActivity extends AppCompatActivity {

    @BindView(R.id.latest_news_recycler_view)
    RecyclerView recyclerView;

    @Inject
    LatestNewsAdapter adapter;

    private static LatestNewsViewModel latestNewsViewModel;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_news);
        ButterKnife.bind(this);
        latestNewsViewModel = ViewModelProviders.of(this).get(LatestNewsViewModel.class);

        AdapterComponent.Builder builder = NewsApplication.getAdapterComponentBuilder();
        builder
                .context(this)
                .androidViewModel(latestNewsViewModel)
                .build()
                .inject(this);

        Intent intent = getIntent();
        boolean updateDatabase = intent.getBooleanExtra(AppConstants.EXTRA_IS_ALARM_LAUNCHED, false);
        if (updateDatabase) {
            String fromDate = intent.getStringExtra(AppConstants.EXTRA_FROM_DATE);
            String toDate = intent.getStringExtra(AppConstants.EXTRA_FROM_DATE);
            populateLatestNewsDatabase(fromDate, toDate);
        }

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        latestNewsViewModel.getLatestNews().observe(this, latestNewsEntities -> adapter.setListOfNews(latestNewsEntities));
    }

    private static void populateLatestNewsDatabase(String fromDate, String toDate) {
        for (String country : AppConstants.COUNTRY_CODE.values()) {
            latestNewsViewModel.populateLatestNews(country, fromDate, toDate);
        }
        MainActivity.isAlarmLaunched = false;
    }
}