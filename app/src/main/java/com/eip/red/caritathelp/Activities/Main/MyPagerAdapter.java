package com.eip.red.caritathelp.Activities.Main;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.eip.red.caritathelp.Views.Home.HomeView;
import com.eip.red.caritathelp.Views.Notifications.NotificationsView;
import com.eip.red.caritathelp.Views.OrganisationSearch.OrganisationSearchView;
import com.eip.red.caritathelp.Views.SubMenu.SubMenuView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierr on 14/03/2016.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private static final int NUM_PAGES = 4;

    private List<Fragment>  fragments;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);

        // Init Fragment List
        fragments = new ArrayList<>();
        fragments.add(new HomeView());
        fragments.add(new OrganisationSearchView());
        fragments.add(new NotificationsView());
        fragments.add(new SubMenuView());
    }

    @Override
    public Fragment getItem(int position) {
        return (fragments.get(position));
    }

    @Override
    public int getCount() {
        return (NUM_PAGES);
    }

    /*@Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }*/

    public Fragment getFragment(int id) {
        return (fragments.get(id));
    }
}
