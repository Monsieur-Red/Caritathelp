package com.eip.red.caritathelp.Activities.Main;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.eip.red.caritathelp.R;

/**
 * Created by pierr on 17/03/2016.
 */

public class MainActivityToolbar {

    private MainActivity    activity;

    private MySearchBar     searchBar;

    private ImageView   logo;
    private ImageButton returnBtn;
    private TextView    title;
    private ImageButton searchBtn;


    public MainActivityToolbar(MainActivity activity) {
        this.activity = activity;

        // Get UI elements
        logo = (ImageView) activity.findViewById(R.id.toolbar_app_logo);
        returnBtn = (ImageButton) activity.findViewById(R.id.toolbar_btn_return);
        title = (TextView) activity.findViewById(R.id.toolbar_title);
        searchBtn = (ImageButton) activity.findViewById(R.id.toolbar_btn_search);

        // Init Listener
        returnBtn.setOnClickListener(activity);
        searchBtn.setOnClickListener(activity);

        // Init MySearchBar
        searchBar = new MySearchBar(activity);
    }

    public void update(String title, boolean displayReturnBtn) {
        // Set Title
        this.title.setText(title);

        // Display Logo / Btn Return
        if (displayReturnBtn) {
            logo.setVisibility(View.GONE);
            returnBtn.setVisibility(View.VISIBLE);
        } else {
            logo.setVisibility(View.VISIBLE);
            returnBtn.setVisibility(View.GONE);
        }
    }

    public void onClick(int id) {
        switch (id) {
            case R.id.toolbar_btn_return:
                activity.goToPreviousPage();
                break;
            case R.id.toolbar_btn_search:
                if (searchBar.getVisibility() == View.VISIBLE)
                    searchBar.setVisibility(View.GONE);
                else
                    searchBar.setVisibility(View.VISIBLE);
                break;
        }
    }

    public MySearchBar getSearchBar() {
        return searchBar;
    }
}
