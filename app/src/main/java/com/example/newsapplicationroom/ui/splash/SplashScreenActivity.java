package com.example.newsapplicationroom.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapplicationroom.R;
import com.example.newsapplicationroom.ui.auth.SignInActivity;
import com.example.newsapplicationroom.ui.main.MainActivity;
import com.example.newsapplicationroom.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreenActivity extends AppCompatActivity {
    Animation topAnimation, bottomAnimation;

    @BindView(R.id.splash_heading)
    TextView splashHeading;
    @BindView(R.id.splash_subheading)
    TextView splashSubHeading;
    @BindView(R.id.splash_image)
    ImageView splashImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        splashImage.setAnimation(topAnimation);
        splashHeading.setAnimation(bottomAnimation);
        splashSubHeading.setAnimation(bottomAnimation);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            new Handler().postDelayed((Runnable) () -> {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }, Constants.SPLASH_TIME_OUT);
        } else {
            new Handler().postDelayed((Runnable) () -> {
                Intent intent = new Intent(SplashScreenActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();

            }, Constants.SPLASH_TIME_OUT);
        }
    }
}