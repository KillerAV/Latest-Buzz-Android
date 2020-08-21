package com.example.newsapplicationroom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.newsapplicationroom.roomdatabase.entity.NewsEntity;
import com.example.newsapplicationroom.roomdatabase.NewsViewModel;
import com.example.newsapplicationroom.utils.Constants;
import com.example.newsapplicationroom.utils.GlideOperations;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DescriptionDisplay extends AppCompatActivity {
    @BindView(R.id.displayTitle)
    TextView titleView;
    @BindView(R.id.displayDescription)
    TextView descriptionView;
    @BindView(R.id.displayUrl)
    TextView urlView;
    @BindView(R.id.displayImage)
    ImageView displayImage;
    @BindView(R.id.displayGlideProgressBar)
    ProgressBar displayProgressBar;

    private int newsId;
    private String newsImageUrl, title, description, category, newsUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_display);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        title = intent.getStringExtra(Constants.EXTRA_TITLE);
        description = intent.getStringExtra(Constants.EXTRA_DESCRIPTION);
        category = intent.getStringExtra(Constants.EXTRA_CATEGORY);
        newsId = intent.getIntExtra(Constants.EXTRA_PRIMARY_KEY, -1);
        newsImageUrl = intent.getStringExtra(Constants.EXTRA_IMAGE_URL);
        newsUrl = intent.getStringExtra(Constants.EXTRA_URL);

        String titleText = title + "  -  " + category;

        titleView.setText(titleText);
        descriptionView.setText(description);
        urlView.setClickable(true);
        urlView.setMovementMethod(LinkMovementMethod.getInstance());
        String createUrl = "<a href='" + newsUrl + "'> Click to know more... </a>";
        urlView.setText(Html.fromHtml(createUrl));

        GlideOperations.insertImage(this, newsImageUrl, displayProgressBar, displayImage, R.drawable.no_image_found);
    }

    public void onUpdateButtonClick(View view) {
        Intent intent = new Intent(this, UpdateNews.class);
        intent.putExtra(Constants.EXTRA_TITLE, title);
        intent.putExtra(Constants.EXTRA_DESCRIPTION, description);
        intent.putExtra(Constants.EXTRA_CATEGORY, category);

        startActivityForResult(intent, Constants.UPDATE_NEWS_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.UPDATE_NEWS_REQUEST_CODE && resultCode == RESULT_OK) {
            String newTitle = data.getStringExtra(Constants.EXTRA_TITLE);
            String newDescription = data.getStringExtra(Constants.EXTRA_DESCRIPTION);
            String newCategory = data.getStringExtra(Constants.EXTRA_CATEGORY);

            String titleText = newTitle + "  -  " + newCategory;

            titleView.setText(titleText);
            descriptionView.setText(newDescription);

            NewsViewModel viewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
            NewsEntity newsEntity = new NewsEntity(newTitle, newDescription, newCategory, newsImageUrl, newsUrl);
            newsEntity.setId(newsId);
            viewModel.updateNews(newsEntity);
        }
    }
}