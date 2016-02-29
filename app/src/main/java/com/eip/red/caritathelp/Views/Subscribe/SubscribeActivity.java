package com.eip.red.caritathelp.Views.Subscribe;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;

import com.eip.red.caritathelp.Models.User;
import com.eip.red.caritathelp.R;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.Calendar;

/**
 * Created by pierr on 11/01/2016.
 */

public class SubscribeActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {

    private User    user;

    private ViewPager           viewPager;
    private MyPagerAdapter      pagerAdapter;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

        // Init User
        user = new User();

        // Init ViewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        // Init Pager Adapter
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        /*LICENCE VIEWPAGER INDICATORI HERE : https://github.com/JakeWharton/ViewPagerIndicator*/
        // Init PagerArrowIndicator
        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.subscribe_viewpager_indicator);
        indicator.setFillColor(Color.BLACK);
        indicator.setStrokeColor(Color.BLACK);
        indicator.setViewPager(viewPager);
        indicator.setOnPageChangeListener(this);

        // Init Effects Pager
        viewPager.setPageTransformer(false, new DepthPageTransformer());

        // Init Listener
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {}

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            switch (viewPager.getCurrentItem()) {
                case 2:
                    // Set Focus
                    focusView(2);

                    // Page Changement - Check errors
                    FirstView firstView = (FirstView) pagerAdapter.getFragment(1);

                    if (!firstView.getPresenter().goToSecondPage(firstView.getFirstName(), firstView.getLastName(), firstView.getGender(), firstView.getBirthday()))
                        viewPager.setCurrentItem(1, true);
                    break;
                case 3:
                    // Set Focus
                    focusView(3);

                    // Page Changement - Check errors
                    SecondView secondView = (SecondView) pagerAdapter.getFragment(2);

                    if (!secondView.getPresenter().goToThirdPage(secondView.getMail(), secondView.getPassword(), secondView.getPasswordChecking()))
                        viewPager.setCurrentItem(2, true);
                    break;
//                default:
//                    pagerAdapter.notifyDataSetChanged();
//                    break;
            }

        }
    }

    private void focusView(int position) {
        View    view = pagerAdapter.getFragment(position).getView();

        viewPager.clearFocus();
        if (view != null) {
            view.setFocusableInTouchMode(true);
            view.requestFocus();
        }

    }

    public User getUser() {
        return user;
    }

    public MyPagerAdapter getPagerAdapter() {
        return pagerAdapter;
    }
}

