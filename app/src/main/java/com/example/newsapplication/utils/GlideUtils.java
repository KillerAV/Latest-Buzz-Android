package com.example.newsapplication.utils;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class GlideUtils {
    RequestManager requestManager;

    public GlideUtils(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    public void insertImage(String url, final ProgressBar displayProgressBar, ImageView displayImage, int error_image) {
        requestManager.load(url).error(error_image)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        displayProgressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        displayProgressBar.setVisibility(View.GONE);
                        return false;
                    }
                }).into(displayImage);
    }
}
