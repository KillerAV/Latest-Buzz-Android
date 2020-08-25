package com.example.newsapplicationroom.ui.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.newsapplicationroom.di.component.DaggerNewsCategoryComponent;
import com.example.newsapplicationroom.ui.NewsApplication;

import javax.inject.Inject;
import javax.inject.Named;

public class NewsPagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    @Inject
    public NewsPagerAdapter(FragmentManager fragmentManager, @Named("Tab Count") int numberOfTabs) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numberOfTabs = numberOfTabs;
    }

    public Fragment getItem(int position) {
        if (position == 0) {
            return NewsApplication.getNewsCategoryComponent().getEntertainmentNews();
        } else if (position == 1) {
            return NewsApplication.getNewsCategoryComponent().getSportsNews();
        } else {
            return NewsApplication.getNewsCategoryComponent().getHealthNews();
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}