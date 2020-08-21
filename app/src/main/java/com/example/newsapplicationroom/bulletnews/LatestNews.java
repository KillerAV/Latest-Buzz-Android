package com.example.newsapplicationroom.bulletnews;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import com.example.newsapplicationroom.adapters.LatestNewsAdapter;
import com.example.newsapplicationroom.components.DaggerAdapterComponent;
import com.example.newsapplicationroom.roomdatabase.NewsViewModel;
import com.example.newsapplicationroom.R;
import com.example.newsapplicationroom.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LatestNews extends AppCompatActivity {

    @BindView(R.id.latest_news_recycler_view)
    RecyclerView recyclerView;

    private static NewsViewModel viewModel;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_news);
        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this).get(NewsViewModel.class);

        final LatestNewsAdapter adapter = DaggerAdapterComponent.builder().setContext(this).build().getLatestNewsAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.getLatestNews().observe(this, latestNewsEntities -> adapter.setListOfNews(latestNewsEntities));
    }

    public static void populateLatestNewsDatabase(String fromDate, String toDate) {
        for (String country : Constants.COUNTRY_CODE.values()) {
            viewModel.populateLatestNews(country, fromDate, toDate);
        }
    }
}