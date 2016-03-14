package com.eip.red.caritathelp.Main;

import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;

import com.eip.red.caritathelp.Models.Animation;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Views.Home.HomeView;
import com.eip.red.caritathelp.Views.MailBox.MailBoxView;
import com.eip.red.caritathelp.Views.Notifications.NotificationsView;
import com.eip.red.caritathelp.Views.OrganisationSearch.OrganisationSearchView;
import com.eip.red.caritathelp.Views.SubMenu.SubMenuView;

import java.util.ArrayList;

/**
 * Created by pierr on 20/11/2015.
 */

public class MyBottomBar {

    public static final int STATE_HOME = 0;
    public static final int STATE_ORGA = 1;
    public static final int STATE_MAILBOX = 2;
    public static final int STATE_NOTIFICATIONS = 3;
    public static final int STATE_SUBMENU = 4;

    private int                     state;
    private ArrayList<ImageButton>  buttons;

    private MainActivity activity;

    private HomeView                homeView;
    private OrganisationSearchView  organisationSearchView;
    private MailBoxView             mailBoxView;
    private NotificationsView       notificationsView;
    private SubMenuView             subMenuView;


    public MyBottomBar(MainActivity activity) {
        this.activity = activity;

        // Init Views
        homeView = new HomeView();
        organisationSearchView = new OrganisationSearchView();
        mailBoxView = new MailBoxView();
        notificationsView = new NotificationsView();
        subMenuView = new SubMenuView();

        // Display First View
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.main_fragment, homeView).commit();

        // Init Buttons
        buttons = new ArrayList<>();
        buttons.add((ImageButton) activity.findViewById(R.id.bottom_bar_btn_home));
        buttons.add((ImageButton) activity.findViewById(R.id.bottom_bar_btn_organisations));
        buttons.add((ImageButton) activity.findViewById(R.id.bottom_bar_btn_mailbox));
        buttons.add((ImageButton) activity.findViewById(R.id.bottom_bar_btn_notifications));
        buttons.add((ImageButton) activity.findViewById(R.id.bottom_bar_btn_submenu));

        // Init Listener & Color Button & Color background Button
        for (ImageButton button : buttons) {
//            button.setOnClickListener(activity);
            button.setColorFilter(Color.LTGRAY);
            button.setBackgroundColor(Color.TRANSPARENT);
        }

        // By Default, home btn is selected
        state = STATE_HOME;
        buttons.get(state).setColorFilter(activity.getResources().getColor(R.color.primary));
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_bar_btn_home:
                // Set Button view
                selectButton(STATE_HOME);

                // Page Change
                activity.replaceView(homeView, Animation.ZERO);
                break;
            case R.id.bottom_bar_btn_organisations:
                // Set Button view
                selectButton(STATE_ORGA);

                // Page Change
                activity.replaceView(organisationSearchView, Animation.ZERO);
                break;
            case R.id.bottom_bar_btn_mailbox:
                // Set Button view
                selectButton(STATE_MAILBOX);

                // Page Change
                activity.replaceView(mailBoxView, Animation.ZERO);
                break;
            case R.id.bottom_bar_btn_notifications:
                // Set Button view
                selectButton(STATE_NOTIFICATIONS);

                // Page Change
                activity.replaceView(notificationsView, Animation.ZERO);
                break;
            case R.id.bottom_bar_btn_submenu:
                // Set Button view
                selectButton(STATE_SUBMENU);

                // Page Change
                activity.replaceView(subMenuView, Animation.ZERO);
                break;
        }
    }

    private void selectButton(int newState) {
        buttons.get(state).setColorFilter(Color.LTGRAY);
        state = newState;
        buttons.get(state).setColorFilter(activity.getResources().getColor(R.color.primary));
    }

    public int getState() {
        return state;
    }
}
