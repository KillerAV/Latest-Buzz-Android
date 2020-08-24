package com.newsapplicationroom.repository;

public interface ILatestNewsRepository {
    void populateLatestNews(String country, String fromDate, String toDate);
}
