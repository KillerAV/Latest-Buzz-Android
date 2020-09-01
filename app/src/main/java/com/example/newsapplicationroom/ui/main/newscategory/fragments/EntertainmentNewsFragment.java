package com.example.newsapplicationroom.ui.main.newscategory.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapplicationroom.R;
import com.example.newsapplicationroom.di.main.newscategory.NewsCategoryFragmentComponent;
import com.example.newsapplicationroom.ui.NewsApplication;
import com.example.newsapplicationroom.ui.main.MainActivity;
import com.example.newsapplicationroom.ui.main.newscategory.adapter.NewsAdapter;
import com.example.newsapplicationroom.utils.Constants;
import com.example.newsapplicationroom.viewmodel.NewsViewModel;

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

        NewsCategoryFragmentComponent.Builder builder = MainActivity.getNewsCategoryFragmentComponent();
        builder
                .newsViewModel(newsViewModel)
                .build()
                .inject(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        newsViewModel.getNews(Constants.category[0]).observe(getViewLifecycleOwner(), newsEntities -> adapter.setListOfNews(newsEntities));

        itemTouchHelper.attachToRecyclerView(recyclerView);
        return view;
    }
}
