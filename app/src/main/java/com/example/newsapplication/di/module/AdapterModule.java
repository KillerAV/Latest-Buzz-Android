package com.example.newsapplication.di.module;

import android.app.AlertDialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapplication.R;
import com.example.newsapplication.di.scope.ActivityScope;
import com.example.newsapplication.ui.adapter.LatestNewsAdapter;
import com.example.newsapplication.ui.adapter.NewsAdapter;
import com.example.newsapplication.viewmodel.NewsViewModel;
import com.newsapplicationroom.entity.NewsEntity;

import dagger.Module;
import dagger.Provides;

@Module
public class AdapterModule {
    @ActivityScope
    @Provides
    static NewsAdapter providesNewsAdapter(Context context) {
        return new NewsAdapter(context);
    }

    @ActivityScope
    @Provides
    static LatestNewsAdapter providesLatestNewsAdapter(Context context) {
        return new LatestNewsAdapter(context);
    }

    @Provides
    @ActivityScope
    static ItemTouchHelper.SimpleCallback providesItemTouchHelperSimpleCallback(Context context, NewsAdapter adapter, AndroidViewModel viewModel) {
        return new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                new AlertDialog.Builder(context)
                        .setTitle(R.string.delete_news_title)
                        .setMessage(R.string.delete_news_message)
                        .setPositiveButton(R.string.dialogue_positive_label, (dialog, which) -> {
                            NewsEntity newsEntity = adapter.getNews(viewHolder.getAdapterPosition());
                            ((NewsViewModel) viewModel).deleteNews(newsEntity);
                        })
                        .setNegativeButton(R.string.dialogue_negative_label, (dialog, which) -> adapter.notifyItemChanged(viewHolder.getAdapterPosition()))
                        .setCancelable(false).show();
            }
        };
    }

    @Provides
    @ActivityScope
    static ItemTouchHelper providesItemTouchHelper(ItemTouchHelper.SimpleCallback simpleCallback) {
        return new ItemTouchHelper(simpleCallback);
    }
}
