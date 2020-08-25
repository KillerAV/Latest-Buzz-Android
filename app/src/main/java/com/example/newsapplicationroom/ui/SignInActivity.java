package com.example.newsapplicationroom.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.newsapplicationroom.R;
import com.example.newsapplicationroom.utils.Constants;
import com.example.newsapplicationroom.viewmodel.UserInformationViewModel;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.newsapplicationroom.entity.UserInfoEntity;

import java.util.Arrays;
import java.util.List;

public class SignInActivity extends AppCompatActivity {
    private static UserInformationViewModel userInformationViewModel;
    FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userInformationViewModel = ViewModelProviders.of(this).get(UserInformationViewModel.class);
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.EmailBuilder().build());

        // Create and launch sign-in intent
        startActivityForResult(AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.logo_image)
                .setTheme(R.style.LoginTheme)
                .build(), Constants.SIGN_IN_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.SIGN_IN_REQUEST_CODE) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.METHOD, response.getProviderType());
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle);
                new AddInformationToDatabaseAsync().execute();
            } else if (response != null) {
                Toast.makeText(this, getString(R.string.error_signin_toast_message) + response.getError(), Toast.LENGTH_LONG).show();
                Log.d(SignInActivity.class.getSimpleName(), response.getError().toString());
            }
        }
    }

    private class AddInformationToDatabaseAsync extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            UserInfoEntity userInfoEntity = new UserInfoEntity(user.getEmail(), user.getDisplayName());
            userInformationViewModel.insertUserInformation(userInfoEntity);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}