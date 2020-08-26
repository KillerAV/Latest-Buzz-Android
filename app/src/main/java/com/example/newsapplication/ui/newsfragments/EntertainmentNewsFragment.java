package com.example.newsapplication.ui.newsfragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapplication.di.component.AdapterComponent;
import com.example.newsapplication.ui.MainActivity;
import com.example.newsapplication.R;
import com.example.newsapplication.ui.NewsApplication;
import com.example.newsapplication.ui.adapter.NewsAdapter;
import com.example.newsapplication.viewmodel.NewsViewModel;
import com.example.newsapplication.utils.AppConstants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntertainmentNewsFragment extends Fragment {

    @BindView(R.id.entertainment_recycler_view)
    RecyclerView recyclerView;

    @Inject
    NewsAdapter adapter;
    @Inject
    ItemTouchHelper itemTouchHelper;

    public EntertainmentNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entertainment_news, container, false);
        final NewsViewModel newsViewModel = MainActivity.getViewModel();
        ButterKnife.bind(this, view);

        AdapterComponent.Builder builder = NewsApplication.getAdapterComponentBuilder();
        builder
                .context(getContext())
                .androidViewModel(newsViewModel)
                .build()
                .inject(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        newsViewModel.getNews(AppConstants.category[0]).observe(getViewLifecycleOwner(), newsEntities -> adapter.setListOfNews(newsEntities));

        itemTouchHelper.attachToRecyclerView(recyclerView);
        return view;
    }
}