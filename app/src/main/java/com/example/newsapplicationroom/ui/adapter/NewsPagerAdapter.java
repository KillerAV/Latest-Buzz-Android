package com.example.newsapplicationroom.ui.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.newsapplicationroom.ui.NewsApplication;
import com.example.newsapplicationroom.ui.newsfragments.EntertainmentNewsFragment;
import com.example.newsapplicationroom.ui.newsfragments.HealthNewsFragment;
import com.example.newsapplicationroom.ui.newsfragments.SportsNewsFragment;

import javax.inject.Inject;
import javax.inject.Named;

public class NewsPagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;

    @Inject
    EntertainmentNewsFragment entertainmentNewsFragment;
    @Inject
    SportsNewsFragment sportsNewsFragment;
    @Inject
    HealthNewsFragment healthNewsFragment;

    public NewsPagerAdapter(FragmentManager fragmentManager, @Named("Tab Count") int numberOfTabs) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numberOfTabs = numberOfTabs;
        NewsApplication.getAppComponent().inject(this);
    }

    public Fragment getItem(int position) {
        if (position == 0) {
            return entertainmentNewsFragment;
        } else if (position == 1) {
            return sportsNewsFragment;
        } else {
            return healthNewsFragment;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}