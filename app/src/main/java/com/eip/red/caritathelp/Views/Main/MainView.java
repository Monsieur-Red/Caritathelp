package com.eip.red.caritathelp.Views.Main;

import android.app.Fragment;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eip.red.caritathelp.Main.MyPagerAdapter;
import com.eip.red.caritathelp.R;

/**
 * Created by pierr on 16/03/2016.
 */

public class MainView extends Fragment {

    private ViewPager       viewPager;
    private MyPagerAdapter  myPagerAdapter;
    private TabLayout       topBar;

    private TextView        toolbarTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Network & Organisation Model
//        Network network = ((SecondContainer) getActivity()).getModelManager().getNetwork();
//        Organisation organisation = (Organisation) getArguments().getSerializable("organisation");

        // Init Presenter
//        presenter = new OrganisationPresenter(this, network, organisation);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mainview, container, false);

        // Init Tool Bar
        initToolBar(view);

        // Init ViewPager & Adapter & listener
        viewPager = (ViewPager) view.findViewById(R.id.fragment_mainview_viewpager);

        // Setting Adapter
        myPagerAdapter = new MyPagerAdapter(getFragmentManager());
        viewPager.setAdapter(myPagerAdapter);

        // Init TabLayout
        initTableLayout(view);

        return (view);
    }

    private void initToolBar(View view) {
//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.fragment_mainview_toolbar);
        //setSupportActionBar(toolbar);

        // Get ToolBar title
        toolbarTitle = (TextView) view.findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Actualités");
    }

    private void initTableLayout(View view) {
        // Init Top Bar
        topBar = (TabLayout) view.findViewById(R.id.fragment_mainview_tabs);

        // Add Tabs & set Icons
        topBar.addTab(topBar.newTab().setIcon(android.R.drawable.ic_dialog_dialer));
        topBar.addTab(topBar.newTab().setIcon(android.R.drawable.ic_menu_myplaces));
        topBar.addTab(topBar.newTab().setIcon(android.R.drawable.ic_popup_reminder));
        topBar.addTab(topBar.newTab().setIcon(android.R.drawable.ic_menu_add));

        // Setting Listener
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(topBar));
        topBar.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);

                        // Set Icon Alpha
                        tab.getIcon().setAlpha(255);

                        // Change ToolBar Title
                        changeTitleToolBar(tab.getPosition());
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);

                        // Set Icon Alpha
                        tab.getIcon().setAlpha(60);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );

        // Init Icons Color
        int white = ContextCompat.getColor(getActivity().getBaseContext(), R.color.icons);

        for (int i = 0; i < topBar.getTabCount(); i++) {
            TabLayout.Tab   tab = topBar.getTabAt(i);

            if (tab != null) {
                Drawable icon = tab.getIcon();

                if (i == 0)
                    icon.setAlpha(255);
                else
                    icon.setAlpha(60);

                icon.setColorFilter(white, PorterDuff.Mode.SRC_IN);
            }
        }
    }

    private void changeTitleToolBar(int position) {
        // Set Toolbar Title
        switch (position) {
            case 0:
                toolbarTitle.setText("Actualités");
                break;
            case 1:
                toolbarTitle.setText("Associations");
                break;
            case 2:
                toolbarTitle.setText("Notifications");
                break;
            case 3:
                toolbarTitle.setText("Autres");
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.toolbar_btn_search:
                return true;
//            case R.id.toolbar_btn_mailbox:
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
