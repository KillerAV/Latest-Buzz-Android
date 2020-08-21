package com.example.newsapplicationroom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.newsapplicationroom.roomdatabase.NewsViewModel;
import com.example.newsapplicationroom.roomdatabase.entity.UserInfoEntity;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class SignIn extends AppCompatActivity {

    private static final int RC_SIGN_IN = 1;
    NewsViewModel viewModel;
    FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        if (firebaseUser != null) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            // Choose authentication providers
            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.GoogleBuilder().build(),
                    //new AuthUI.IdpConfig.FacebookBuilder().build(),
                    new AuthUI.IdpConfig.EmailBuilder().build());

            // Create and launch sign-in intent
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .setLogo(R.drawable.logo_image)
                    .setTheme(R.style.LoginTheme)
                    .build(), RC_SIGN_IN);
        }
        viewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if (resultCode == RESULT_OK) {
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.METHOD, response.getProviderType());
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle);
                new AddInformationToDatabaseAsync().execute();
            } else if (response != null) {
                Toast.makeText(this, getString(R.string.error_signin_toast_message) + response.getError(), Toast.LENGTH_LONG).show();
                Log.d(SignIn.class.getSimpleName(), response.getError().toString());
            }
        }
    }

    private class AddInformationToDatabaseAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            UserInfoEntity userInfoEntity = new UserInfoEntity(user.getEmail(), user.getDisplayName());
            viewModel.insertUserInformation(userInfoEntity);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(SignIn.this, MainActivity.class);
            startActivity(intent);
        }
    }
}