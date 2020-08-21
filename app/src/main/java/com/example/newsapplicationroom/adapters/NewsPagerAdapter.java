package com.example.newsapplicationroom.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.newsapplicationroom.components.DaggerNewsCategoryComponent;

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
            return DaggerNewsCategoryComponent.create().getEntertainmentNews();
        } else if (position == 1) {
            return DaggerNewsCategoryComponent.create().getSportsNews();
        } else {
            return DaggerNewsCategoryComponent.create().getHealthNews();
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}