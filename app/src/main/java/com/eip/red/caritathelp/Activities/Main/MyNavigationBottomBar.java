package com.eip.red.caritathelp.Activities.Main;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.eip.red.caritathelp.R;

/**
 * Created by pierr on 02/04/2016.
 */

public class MyNavigationBottomBar {

    private AHBottomNavigation  bottomNavigation;

    public MyNavigationBottomBar(MainActivity activity, final ViewPager viewPager) {
        bottomNavigation = (AHBottomNavigation) activity.findViewById(R.id.bottom_navigation);
        Context context = activity.getApplicationContext();

        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.bottom_navigation_bar_news, R.drawable.ic_home_white_24dp, R.color.primary);
//        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.bottom_navigation_bar_search, R.drawable.ic_search_white_24dp, R.color.primary);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.bottom_navigation_bar_mailbox, R.drawable.ic_chat_white_24dp, R.color.primary);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.bottom_navigation_bar_notifications, R.drawable.ic_notifications_white_24dp, R.color.primary);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.bottom_navigation_bar_profile, R.drawable.ic_person_white_24dp, R.color.primary);

        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
//        bottomNavigation.addItem(item5);

        // Set background color
        bottomNavigation.setDefaultBackgroundColor(ContextCompat.getColor(context, R.color.primary));

        // Enable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(false);

        // Change colors
//        bottomNavigation.setAccentColor(Color.parseColor("#000000"));//ContextCompat.getColor(context, R.color.icons));
//        bottomNavigation.setInactiveColor(Color.parseColor("#000000"));//ContextCompat.getColor(context, R.color.primary_dark));

        // Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(true);

        // Force the titles to be not displayed (Respect Material Design guidelines!)
        bottomNavigation.setForceTitlesDisplay(false);

        // Use colored navigation with circle reveal effect
        bottomNavigation.setColored(true);

        // Set current item programmatically
        bottomNavigation.setCurrentItem(0);

        // Customize notification (title, background, typeface)
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));

        // Add or remove notification for each item
        bottomNavigation.setNotification(4, 2);

        // Set listener
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasSelected) {

                if (!wasSelected)
                    viewPager.setCurrentItem(position);
            }
        });

    }

    public AHBottomNavigation getBar() {
        return bottomNavigation;
    }
}
