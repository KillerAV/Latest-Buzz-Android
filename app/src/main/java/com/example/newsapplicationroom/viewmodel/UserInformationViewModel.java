package com.example.newsapplicationroom.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.newsapplication.repoimpl.UserInformationRepository;
import com.newsapplicationroom.entity.UserInfoEntity;

import java.util.List;

public class UserInformationViewModel extends AndroidViewModel {

    private UserInformationRepository userNewsRepository;

    public UserInformationViewModel(@NonNull Application application) {
        super(application);
        userNewsRepository = new UserInformationRepository(application);
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
