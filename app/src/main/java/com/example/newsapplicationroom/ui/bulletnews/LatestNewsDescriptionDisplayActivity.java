package com.example.newsapplicationroom.ui.bulletnews;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapplicationroom.R;
import com.example.newsapplicationroom.ui.NewsApplication;
import com.example.newsapplicationroom.utils.Constants;
import com.example.newsapplicationroom.utils.GlideUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LatestNewsDescriptionDisplayActivity extends AppCompatActivity {

    @BindView(R.id.latestDisplayTitle)
    TextView titleView;
    @BindView(R.id.latestDisplayDescription)
    TextView descriptionView;
    @BindView(R.id.latestDisplayUrl)
    TextView urlView;
    @BindView(R.id.latestDisplayImage)
    ImageView displayImage;
    @BindView(R.id.latestDisplayGlideProgressBar)
    ProgressBar displayProgressBar;

    @Inject
    GlideUtils glideUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_news_description_display);
        ButterKnife.bind(this);
        NewsApplication.getAppComponent().inject(this);

        Intent intent = getIntent();
        String title = intent.getStringExtra(Constants.EXTRA_TITLE);
        String category = intent.getStringExtra(Constants.EXTRA_CATEGORY);
        String description = intent.getStringExtra(Constants.EXTRA_DESCRIPTION);
        String newsImageUrl = intent.getStringExtra(Constants.EXTRA_IMAGE_URL);
        String url = intent.getStringExtra(Constants.EXTRA_URL);

        String titleText = title + "  -  " + category;
        titleView.setText(titleText);
        descriptionView.setText(description);
        urlView.setClickable(true);
        urlView.setMovementMethod(LinkMovementMethod.getInstance());
        String createUrl = "<a href='" + url + "'> Click to know more... </a>";
        urlView.setText(Html.fromHtml(createUrl));

        glideUtils.insertImage(newsImageUrl, displayProgressBar, displayImage, R.drawable.no_image_found);
    }
}