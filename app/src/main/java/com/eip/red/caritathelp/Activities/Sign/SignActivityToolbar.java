package com.eip.red.caritathelp.Activities.Sign;

import android.app.AlertDialog;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.eip.red.caritathelp.R;

/**
 * Created by pierr on 22/03/2016.
 */

public class SignActivityToolbar {

    private SignActivity    activity;

    private AppBarLayout    appBarLayout;
    private ImageButton     returnBtn;
    private TextView        title;
    private Button          signUpBtn;

    public SignActivityToolbar(SignActivity activity) {
        this.activity = activity;

        // Get UI Elements
        appBarLayout = (AppBarLayout) activity.findViewById(R.id.app_bar);
        returnBtn = (ImageButton) activity.findViewById(R.id.btn_return);
        title = (TextView) activity.findViewById(R.id.title);
        signUpBtn = (Button) activity.findViewById(R.id.btn_sign_up);

        // Init Listener
        returnBtn.setOnClickListener(activity);
        signUpBtn.setOnClickListener(activity);
    }

    public void update(String title, boolean displayReturnBtn, boolean displayBtnSignUp) {
        // Set Title
        this.title.setText(title);

        // Display Logo / Btn Return
        if (displayReturnBtn)
            returnBtn.setVisibility(View.VISIBLE);
        else
            returnBtn.setVisibility(View.INVISIBLE);

        // Display BtnTopRight
        if (displayBtnSignUp)
            signUpBtn.setVisibility(View.VISIBLE);
        else
            signUpBtn.setVisibility(View.INVISIBLE);
    }

    public void onClick(int id) {
        switch (id) {
            case R.id.btn_return:
                activity.goToPreviousPage();
                break;
            case R.id.btn_sign_up:
                new android.support.v7.app.AlertDialog.Builder(activity)
                        .setCancelable(true)
                        .setTitle("Not implemented yet!")
                        .setMessage("Work in progress...")
                        .show();
                break;
        }
    }

    public void setVisibility(int value) {
        appBarLayout.setVisibility(value);
    }

}
