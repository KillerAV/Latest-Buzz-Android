package com.example.newsapplicationroom.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.newsapplication.repoimpl.UserInformationRepository;
import com.example.newsapplicationroom.ui.NewsApplication;
import com.newsapplicationroom.entity.UserInfoEntity;

import java.util.List;

import javax.inject.Inject;

public class UserInformationViewModel extends AndroidViewModel {
    @Inject
    UserInformationRepository userNewsRepository;

    public UserInformationViewModel(@NonNull Application application) {
        super(application);
        NewsApplication.getAppComponent().inject(this);
    }

    public List<UserInfoEntity> getUserInformation(String email) {
        return userNewsRepository.getUserInformation(email);
    }

    public void updateUserInformation(UserInfoEntity userInfoEntity) {
        userNewsRepository.updateUserInformation(userInfoEntity);
    }

    public void insertUserInformation(UserInfoEntity userInfoEntity) {
        userNewsRepository.insertUserInformation(userInfoEntity);
    }
}
