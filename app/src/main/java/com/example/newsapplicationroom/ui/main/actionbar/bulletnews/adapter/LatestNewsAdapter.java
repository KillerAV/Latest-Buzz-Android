package com.example.newsapplicationroom.ui.main.actionbar.bulletnews.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.newsapplicationroom.ui.main.actionbar.bulletnews.description.LatestNewsDescriptionDisplayActivity;
import com.example.newsapplicationroom.utils.Constants;
import com.example.newsapplicationroom.utils.GlideUtils;
import com.newsapplicationroom.entity.LatestNewsEntity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LatestNewsAdapter extends RecyclerView.Adapter<LatestNewsAdapter.LatestNewsHolder> {
    LayoutInflater inflater;
    List<LatestNewsEntity> listOfNews;
    private Context context;

    @Inject
    GlideUtils glideUtils;

    public LatestNewsAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        NewsApplication.getAppComponent().inject(this);
    }

    @NonNull
    @Override
    public LatestNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new LatestNewsHolder(view);
    }

    @Override
    public int getItemCount() {
        if (listOfNews != null) {
            return listOfNews.size();
        } else {
            return 0;
        }
    }

    public void setListOfNews(List<LatestNewsEntity> listOfNews) {
        this.listOfNews = listOfNews;
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull LatestNewsHolder holder, int position) {
        if (listOfNews != null) {
            holder.setNewsEntity(listOfNews.get(position));
        }
    }

    public class LatestNewsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recycler_text_view)
        TextView textView;
        @BindView(R.id.newsImage)
        ImageView imageView;
        @BindView(R.id.newsCard)
        CardView cardView;
        @BindView(R.id.glideProgressBar)
        ProgressBar progressBar;

        private String title, description, imageUrl, category, url;

        public LatestNewsHolder(@NonNull final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setNewsEntity(LatestNewsEntity newsEntity) {
            title = newsEntity.getTitle();
            description = newsEntity.getDescription();
            category = newsEntity.getCategory();
            imageUrl = newsEntity.getUrlBannerImage();
            url = newsEntity.getUrl();

            textView.setText(title);

            glideUtils.insertImage(imageUrl, progressBar, imageView, R.drawable.no_image_found);

            cardView.setOnClickListener(v -> {
                Intent intent = new Intent(context, LatestNewsDescriptionDisplayActivity.class);
                intent.putExtra(Constants.EXTRA_TITLE, title);
                intent.putExtra(Constants.EXTRA_DESCRIPTION, description);
                intent.putExtra(Constants.EXTRA_IMAGE_URL, imageUrl);
                intent.putExtra(Constants.EXTRA_CATEGORY, category);
                intent.putExtra(Constants.EXTRA_URL, url);
                context.startActivity(intent);
            });
        }
    }
}
