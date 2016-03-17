package com.eip.red.caritathelp.Main;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.eip.red.caritathelp.R;

/**
 * Created by pierr on 17/03/2016.
 */

public class MyToolBar {

    private MainActivity    activity;

    private ImageView   logo;
    private ImageButton returnBtn;
    private TextView    title;
    private ImageButton searchBtn;


    public MyToolBar(MainActivity activity) {
        this.activity = activity;

        // Get UI elements
        logo = (ImageView) activity.findViewById(R.id.toolbar_app_logo);
        returnBtn = (ImageButton) activity.findViewById(R.id.toolbar_btn_return);
        title = (TextView) activity.findViewById(R.id.toolbar_title);
        searchBtn = (ImageButton) activity.findViewById(R.id.toolbar_btn_search);

        // Init Listener
        returnBtn.setOnClickListener(activity);
        searchBtn.setOnClickListener(activity);

        // Init ToolBar
//        update("Actualités", false, false);
    }

    public void update(String title, boolean displayReturnBtn, boolean displayBtnTopRight) {
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

        // Display BtnTopRight
        if (displayBtnTopRight) {
        }

    }

    public void onClick(int id) {
        switch (id) {
            case R.id.toolbar_btn_return:
                activity.goToPreviousPage();
                break;
        }
    }
}
