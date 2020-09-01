package com.example.newsapplicationroom.ui.main.newscategory.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapplicationroom.R;
import com.example.newsapplicationroom.ui.NewsApplication;
import com.example.newsapplicationroom.ui.main.description.DescriptionDisplayActivity;
import com.example.newsapplicationroom.utils.Constants;
import com.example.newsapplicationroom.utils.GlideUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.newsapplicationroom.entity.NewsEntity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
    LayoutInflater inflater;
    List<NewsEntity> listOfNews;
    private Context context;
    FirebaseAnalytics firebaseAnalytics;

    @Inject
    GlideUtils glideUtils;

    public NewsAdapter(Context context) {
        this.context = context;
        firebaseAnalytics = FirebaseAnalytics.getInstance(context);
        inflater = LayoutInflater.from(context);
        NewsApplication.getAppComponent().inject(this);
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public int getItemCount() {
        if (listOfNews != null) {
            return listOfNews.size();
        } else {
            return 0;
        }
    }

    public void setListOfNews(List<NewsEntity> listOfNews) {
        this.listOfNews = listOfNews;
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        if (listOfNews != null) {
            holder.setNewsEntity(listOfNews.get(position));
        }
    }

    public NewsEntity getNews(int position) {
        return listOfNews.get(position);
    }

    public class NewsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recycler_text_view)
        TextView textView;
        @BindView(R.id.newsImage)
        ImageView imageView;
        @BindView(R.id.newsCard)
        CardView cardView;
        @BindView(R.id.glideProgressBar)
        ProgressBar progressBar;

        private String title, description, category, imageUrl, url;
        private int id;

        public NewsHolder(@NonNull final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setNewsEntity(NewsEntity newsEntity) {
            title = newsEntity.getTitle();
            description = newsEntity.getDescription();
            category = newsEntity.getCategory();
            id = newsEntity.getId();
            imageUrl = newsEntity.getUrlBannerImage();
            url = newsEntity.getUrl();

            textView.setText(title);
            glideUtils.insertImage(imageUrl, progressBar, imageView, R.drawable.no_image_found);

            cardView.setOnClickListener(v -> {
                Intent intent = new Intent(context, DescriptionDisplayActivity.class);
                intent.putExtra(Constants.EXTRA_TITLE, title);
                intent.putExtra(Constants.EXTRA_CATEGORY, category);
                intent.putExtra(Constants.EXTRA_DESCRIPTION, description);
                intent.putExtra(Constants.EXTRA_PRIMARY_KEY, id);
                intent.putExtra(Constants.EXTRA_IMAGE_URL, imageUrl);
                intent.putExtra(Constants.EXTRA_URL, url);

                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Description Display");
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

                context.startActivity(intent);
            });
        }
    }
}
