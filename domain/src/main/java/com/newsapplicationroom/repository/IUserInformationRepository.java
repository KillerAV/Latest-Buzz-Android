package com.newsapplicationroom.repository;

import com.newsapplicationroom.entity.UserInfoEntity;

import java.util.List;

public interface IUserInformationRepository {
    List<UserInfoEntity> getUserInformation(String email);
    void updateUserInformation(UserInfoEntity userInfoEntity);
    void insertUserInformation(UserInfoEntity userInfoEntity);
}
