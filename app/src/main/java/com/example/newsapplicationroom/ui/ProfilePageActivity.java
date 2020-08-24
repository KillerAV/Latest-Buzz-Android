package com.example.newsapplicationroom.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.newsapplicationroom.R;
import com.example.newsapplicationroom.utils.Constants;
import com.example.newsapplicationroom.utils.GlideUtils;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.analytics.FirebaseAnalytics;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfilePageActivity extends AppCompatActivity {

    private String country, initialCountry;
    @BindView(R.id.user_image)
    ImageView imageView;
    @BindView(R.id.user_name)
    TextView textViewName;
    @BindView(R.id.user_email)
    TextView textViewEmail;
    @BindView(R.id.profileGlideProgressBar)
    ProgressBar progressBar;
    @BindView(R.id.country_spinner)
    Spinner spinner;

    FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        ButterKnife.bind(this);

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Intent intent = getIntent();
        country = intent.getStringExtra(Constants.EXTRA_COUNTRY_NAME);
        initialCountry = country;
        String displayName = intent.getStringExtra(Constants.EXTRA_USER_NAME);
        String emailId = intent.getStringExtra(Constants.EXTRA_EMAIL_ID);
        String photoUrl = intent.getStringExtra(Constants.EXTRA_PROFILE_PICTURE);

        GlideUtils.insertImage(this, photoUrl, progressBar, imageView, R.drawable.ic_profile_dark);
        textViewName.setText(displayName);
        textViewEmail.setText(emailId);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                country = Constants.COUNTRY_CODE.get(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.country_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        int position = findCurrentItemLocation();
        spinner.setSelection(position);
    }

    private int findCurrentItemLocation() {
        String[] country_array = getResources().getStringArray(R.array.country_array);

        for (int i = 0; i < country_array.length; i++) {
            String locale = Constants.COUNTRY_CODE.get(country_array[i]);
            if (locale.equals(country)) {
                return i;
            }
        }
        return -1;
    }

    public void onClickLogout(View view) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.logout_dialogue_title)
                .setMessage(R.string.logout_dialogue_description)
                .setPositiveButton(R.string.dialogue_positive_label, (dialog, which) -> AuthUI
                        .getInstance()
                        .signOut(ProfilePageActivity.this)
                        .addOnCompleteListener(task -> {
                            Intent intent = new Intent(ProfilePageActivity.this, SignInActivity.class);
                            startActivity(intent);
                            finish();
                        }))
                .setNegativeButton(R.string.dialogue_negative_label, (dialog, which) -> {
                })
                .setCancelable(false).show();

    }

    public void onSubmitButtonClick(View view) {
        Intent replyIntent = new Intent(this, MainActivity.class);
        replyIntent.putExtra(Constants.EXTRA_COUNTRY_NAME, country);
        setResult(RESULT_OK, replyIntent);

        if (!country.equals(initialCountry)) {
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Change Country");
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        }
        finish();
    }

}