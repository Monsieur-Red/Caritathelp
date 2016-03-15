package com.eip.red.caritathelp.Main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.eip.red.caritathelp.Models.Animation;
import com.eip.red.caritathelp.Models.ModelManager;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Views.Login.LoginView;

public class MainActivity extends AppCompatActivity {

    private ModelManager    modelManager;

    private ViewPager       viewPager;
    private MyPagerAdapter  myPagerAdapter;
    private TabLayout       topBar;

    private TextView    toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set View
        setContentView(R.layout.activity_main);

        // Init ModelManager
        modelManager = new ModelManager(getIntent());

        // Init Tool Bar
        initToolBar();

        // Init ViewPager & Adapter & listener
        viewPager = (ViewPager) findViewById(R.id.activity_main_viewpager);

        // Setting Adapter
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);

        // Init TabLayout
        initTableLayout();

        // Set Status Bar Color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary));
        }
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);

        // Get ToolBar title
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Actualités");
    }

    private void initTableLayout() {
        // Init Top Bar
        topBar = (TabLayout) findViewById(R.id.activity_main_tabs);

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

                        //topBar.getChildAt(1).setVisibility(View.GONE);
//                        topBar.addTab(topBar.newTab().setIcon(android.R.drawable.ic_menu_add), 2, true);
//                        myPagerAdapter.getFragment(1).getView().setVisibility(View.GONE);

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
        int white = ContextCompat.getColor(getBaseContext(), R.color.icons);

        for (int i = 0; i < topBar.getTabCount(); i++) {
            TabLayout.Tab   tab = topBar.getTabAt(i);

            if (tab != null) {
                Drawable    icon = tab.getIcon();

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
            case R.id.toolbar_btn_mailbox:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void replaceView(Fragment fragment, int animation) {
        // Hide Keyboard
        InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View    view = getCurrentFocus();
        if (view != null)
            keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);

        // Replace Fragment
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        // Set Animation
        switch (animation) {
            case Animation.SLIDE_LEFT_RIGHT:
                ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                break;
            case Animation.SLIDE_UP_DOWN:
                ft.setCustomAnimations(R.anim.enter_from_bot, R.anim.exit_to_bot, R.anim.enter_from_top, R.anim.exit_to_top);
                break;
        }

        // Replace Fragment
//        ft.replace(R.id.main_fragment, fragment);

        // Save old fragment in the stack
        ft.addToBackStack(null);

        // Commit changes
        ft.commit();
    }

    public void goToPreviousPage() {
//        System.out.println("SIZEEEEEEEEEEEEE : " + getFragmentManager().getBackStackEntryCount());

        // Hide Keyboard
        InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View    view = getCurrentFocus();
        if (view != null)
            keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);

        // Get Old Fragment
        super.onBackPressed();
    }

    public void logout() {
        startActivity(new Intent(this, LoginView.class));
        finish();
    }

    public ModelManager getModelManager() {
        return (modelManager);
    }
}
