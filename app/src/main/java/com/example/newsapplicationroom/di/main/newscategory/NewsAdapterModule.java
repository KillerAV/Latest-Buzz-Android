package com.example.newsapplicationroom.di.main.newscategory;

import android.app.AlertDialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapplicationroom.R;
import com.example.newsapplicationroom.di.MainActivityScope;
import com.example.newsapplicationroom.ui.main.newscategory.adapter.NewsAdapter;
import com.example.newsapplicationroom.viewmodel.NewsViewModel;
import com.newsapplicationroom.entity.NewsEntity;

import dagger.Module;
import dagger.Provides;

@Module
public class NewsAdapterModule {
    @MainActivityScope
    @Provides
    static NewsAdapter providesNewsAdapter(Context context) {
        return new NewsAdapter(context);
    }

    @Provides
    @MainActivityScope
    static ItemTouchHelper.SimpleCallback providesItemTouchHelperSimpleCallback(Context context, NewsAdapter adapter, NewsViewModel viewModel) {
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
                            viewModel.deleteNews(newsEntity);
                        })
                        .setNegativeButton(R.string.dialogue_negative_label, (dialog, which) -> adapter.notifyItemChanged(viewHolder.getAdapterPosition()))
                        .setCancelable(false).show();
            }
        };
    }

    @Provides
    @MainActivityScope
    static ItemTouchHelper providesItemTouchHelper(ItemTouchHelper.SimpleCallback simpleCallback) {
        return new ItemTouchHelper(simpleCallback);
    }
}
