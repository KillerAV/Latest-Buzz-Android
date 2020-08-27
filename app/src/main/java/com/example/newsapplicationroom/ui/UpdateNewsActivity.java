package com.example.newsapplicationroom.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsapplicationroom.R;
import com.example.newsapplicationroom.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateNewsActivity extends AppCompatActivity {

    @BindView(R.id.newsTitle)
    EditText titleText;
    @BindView(R.id.newsDescription)
    EditText descriptionText;
    @BindView(R.id.newsCategory)
    RadioGroup categoryGroup;
    private int categoryIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_news);
        ButterKnife.bind(this);

        categoryIndex = 0;

        categoryGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.entertainment_button:
                    categoryIndex = 0;
                    break;

                case R.id.sports_button:
                    categoryIndex = 1;
                    break;

                case R.id.health_button:
                    categoryIndex = 2;
                    break;
            }
        });

        Intent intent = getIntent();
        String title = intent.getStringExtra(Constants.EXTRA_TITLE);
        String description = intent.getStringExtra(Constants.EXTRA_DESCRIPTION);
        String selectedCategory = intent.getStringExtra(Constants.EXTRA_CATEGORY);

        titleText.setText(title);
        descriptionText.setText(description);
        switch (selectedCategory) {
            case Constants.ENTERTAINMENT:
                categoryGroup.check(R.id.entertainment_button);
                break;
            case Constants.SPORTS:
                categoryGroup.check(R.id.sports_button);
                break;
            case Constants.HEALTH:
                categoryGroup.check(R.id.health_button);
                break;
        }
    }

    public void onClickSubmit(View view) {
        String title = titleText.getText().toString();
        String description = descriptionText.getText().toString();
        String selectedCategory = Constants.category[categoryIndex];

        if (title.length() < 1) {
            Toast.makeText(this, R.string.invalid_title_message, Toast.LENGTH_SHORT).show();
            return;
        }
        if (description.length() < 1) {
            Toast.makeText(this, R.string.invalid_description_message, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent responseIntent = new Intent(this, DescriptionDisplayActivity.class);
        responseIntent.putExtra(Constants.EXTRA_TITLE, title);
        responseIntent.putExtra(Constants.EXTRA_DESCRIPTION, description);
        responseIntent.putExtra(Constants.EXTRA_CATEGORY, selectedCategory);
        setResult(RESULT_OK, responseIntent);
        finish();
    }
}