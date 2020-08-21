package com.example.newsapplicationroom.newscategories;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsapplicationroom.MainActivity;
import com.example.newsapplicationroom.R;
import com.example.newsapplicationroom.adapters.NewsAdapter;
import com.example.newsapplicationroom.roomdatabase.entity.NewsEntity;
import com.example.newsapplicationroom.roomdatabase.NewsViewModel;
import com.example.newsapplicationroom.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntertainmentNews extends Fragment {

    @BindView(R.id.entertainment_recycler_view)
    RecyclerView recyclerView;

    @Inject
    public EntertainmentNews() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entertainment_news, container, false);
        final NewsViewModel viewModel = MainActivity.getViewModel();
        ButterKnife.bind(this, view);

        final NewsAdapter adapter = MainActivity.getAdapterComponent().getNewsAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        viewModel.getNews(Constants.category[0]).observe(getViewLifecycleOwner(), newsEntities -> adapter.setListOfNews(newsEntities));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {

                new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.delete_news_title)
                        .setMessage(R.string.delete_news_message)
                        .setPositiveButton(R.string.dialogue_positive_label, (dialog, which) -> {
                            NewsEntity newsEntity = adapter.getNews(viewHolder.getAdapterPosition());
                            viewModel.deleteNews(newsEntity);
                        })
                        .setNegativeButton(R.string.dialogue_negative_label, (dialog, which) -> adapter.notifyItemChanged(viewHolder.getAdapterPosition()))
                        .setCancelable(false).show();
            }
        }).attachToRecyclerView(recyclerView);
        return view;
    }
}
