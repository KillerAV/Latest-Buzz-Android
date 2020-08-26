package com.newsapplicationroom.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.newsapplicationroom.utils.DomainConstants;

@Entity(tableName = DomainConstants.USER_INFO_TABLE_NAME)
public class UserInfoEntity {
    @PrimaryKey
    @NonNull
    private String emailId;

    @NonNull
    private String name;

    @NonNull
    private String country;

    public UserInfoEntity(@NonNull String emailId, @NonNull String name) {
        this.emailId = emailId;
        this.name = name;
        this.country = DomainConstants.DEFAULT_COUNTRY;
    }

    public void setCountry(@NonNull String country) {
        this.country = country;
    }

    @NonNull
    public String getEmailId() {
        return emailId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getCountry() {
        return country;
    }
}
