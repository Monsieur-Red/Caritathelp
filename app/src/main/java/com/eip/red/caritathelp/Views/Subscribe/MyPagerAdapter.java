package com.eip.red.caritathelp.Views.Subscribe;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by pierr on 11/01/2016.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private static final int NUM_PAGES = 4;

    private ArrayList<Fragment> fragments;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);

        fragments = new ArrayList<>();
        fragments.add(new SubscribeHomeView());
        fragments.add(new FirstView());
        fragments.add(new SecondView());
        fragments.add(new ThirdView());
    }

    @Override
    public Fragment getItem(int index) {
        return (fragments.get(index));
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public Fragment getFragment(int id) {
        return (fragments.get(id));
    }

}
