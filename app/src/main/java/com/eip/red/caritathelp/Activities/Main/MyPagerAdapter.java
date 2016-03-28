package com.eip.red.caritathelp.Activities.Main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.eip.red.caritathelp.Views.SubMenu.MyOrganisations.OrganisationCreation.OrganisationCreationView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierr on 14/03/2016.
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private static final int NUM_PAGES = 3;

    private List<Fragment>  fragments;
    private List<String>    tabTitles;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);

        // Init Fragment List
        fragments = new ArrayList<>();
//        fragments.add(new MyOrganisationMemberProfile());
//        fragments.add(new MyOrganisationOwnerProfile());
        fragments.add(new OrganisationCreationView());

        // Init Tab Titles
        tabTitles = new ArrayList<>();
        tabTitles.add("Membre");
        tabTitles.add("Propriétaire");
        tabTitles.add("Créer");
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

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles.get(position);
    }
}
