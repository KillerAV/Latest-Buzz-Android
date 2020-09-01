package com.example.newsapplicationroom.ui.main.actionbar.bulletnews;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapplicationroom.R;
import com.example.newsapplicationroom.di.main.actionbar.bulletnews.adapter.LatestNewsActivityComponent;
import com.example.newsapplicationroom.ui.NewsApplication;
import com.example.newsapplicationroom.ui.main.MainActivity;
import com.example.newsapplicationroom.ui.main.actionbar.bulletnews.adapter.LatestNewsAdapter;
import com.example.newsapplicationroom.utils.Constants;
import com.example.newsapplicationroom.viewmodel.LatestNewsViewModel;

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

        LatestNewsActivityComponent.Builder builder = NewsApplication.getLatestNewsAdapterComponentBuilder();
        builder
                .context(this)
                .latestNewsViewModel(latestNewsViewModel)
                .build()
                .inject(this);

        Intent intent = getIntent();
        boolean updateDatabase = intent.getBooleanExtra(Constants.EXTRA_IS_ALARM_LAUNCHED, false);
        if (updateDatabase) {
            String fromDate = intent.getStringExtra(Constants.EXTRA_FROM_DATE);
            String toDate = intent.getStringExtra(Constants.EXTRA_FROM_DATE);
            populateLatestNewsDatabase(fromDate, toDate);
        }

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