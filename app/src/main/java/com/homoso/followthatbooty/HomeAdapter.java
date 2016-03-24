package com.homoso.followthatbooty;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by raj on 23/3/16.
 */
public class HomeAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> homeNavigationFragment = new ArrayList<>();
    String title="";

    public HomeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return homeNavigationFragment.get(position);
    }

    @Override
    public int getCount() {
        return homeNavigationFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title;
    }

    public void addFragment(Fragment fragment) {
        homeNavigationFragment.add(fragment);
    }
}
