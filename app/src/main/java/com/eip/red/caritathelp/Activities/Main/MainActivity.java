package com.eip.red.caritathelp.Activities.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.eip.red.caritathelp.Activities.Main.ViewPager.MyPageTransformer;
import com.eip.red.caritathelp.Activities.Main.ViewPager.MyPagerAdapter;
import com.eip.red.caritathelp.Activities.Sign.SignActivity;
import com.eip.red.caritathelp.Models.Enum.Animation;
import com.eip.red.caritathelp.Models.ModelManager;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Tools;

public class MainActivity extends AppCompatActivity {

    private ModelManager            modelManager;

    private MyNavigationBottomBar   myNavigationBottomBar;

    private ViewPager               viewPager;
    private MyPagerAdapter          myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set View
        setContentView(R.layout.activity_main);

        // Init ModelManager
        modelManager = new ModelManager(getIntent());

        // Init ViewPager & Adapter
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setPageTransformer(false, new MyPageTransformer());

        // Setting Adapter
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.setCurrentItem(0);

        // Init Navigation Bottom Bar
        myNavigationBottomBar = new MyNavigationBottomBar(this, viewPager);

        // Init ViewPager Listener
        initViewPagerListener();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set Status Bar Color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary_dark));
        }
    }

    private void initViewPagerListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                // Set Navigation Bottom bar Position
                myNavigationBottomBar.getBar().setCurrentItem(viewPager.getCurrentItem());

                // Set ToolBar Title
                Fragment    fragment = Tools.getLastFragment(myPagerAdapter.getFragment(position).getChildFragmentManager());
                Bundle      bundle = fragment.getArguments();

                if (bundle != null) {
                    int stringId = bundle.getInt("page");

                    if (stringId != 0)
                        setTitle(stringId);
                    else
                        setTitle(bundle.getString("page"));
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                if (state == ViewPager.SCROLL_STATE_IDLE)
//                    bottomNavigation.setCurrentItem(viewPager.getCurrentItem());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater    inflater = getMenuInflater();
        inflater.inflate(R.menu.search_bar, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // perform query here

                // workaround to avoid issues with some emulators and keyboard devices firing twice if a keyboard enter is used
                // see https://code.google.com/p/android/issues/detail?id=24599
                searchView.clearFocus();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        // if there is a fragment and the back stack of this fragment is not empty,
        // then emulate 'onBackPressed' behaviour, because in default, it is not working
        FragmentManager fm = getSupportFragmentManager();
        for (Fragment frag : fm.getFragments()) {
            if (frag != null && frag.isVisible()) {
                FragmentManager childFm = frag.getChildFragmentManager();
                if (childFm.getBackStackEntryCount() > 1) {
                    childFm.popBackStack();
                    return;
                }
            }
        }
        super.onBackPressed();
    }

    public void replaceView(Fragment newFragment) {
        Fragment            fragment = myPagerAdapter.getFragment(0);
        FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();

//        ft.add(newFragment, "toto");
        // Replace Fragment
        ft.replace(R.id.fragment_container, newFragment);

        // Save old fragment in the stack
        ft.addToBackStack(null);

        // Commit changes
        ft.commit();
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
//                fragment.setEnterTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                fragment.setExitTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
//                ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);//, R.anim.enter_from_left, R.anim.exit_to_right);

//                  ft.setCustomAnimations(R.animator.fade_in, R.animator.fade_out, R.animator.fade_in, R.animator.fade_out);
                break;
            case Animation.SLIDE_UP_DOWN:
//                fragment.setEnterTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                fragment.setExitTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
//                ft.setCustomAnimations(R.anim.enter_from_bot, R.anim.exit_to_bot, R.anim.enter_from_top, R.anim.exit_to_top);

//                ft.setCustomAnimations(R.animator.slide_up, R.animator.slide_down, R.animator.slide_up, R.animator.slide_down);
                break;
            case Animation.FLIP_LEFT_RIGHT:
//                fragment.setEnterTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                fragment.setEnterTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//                fragment.setExitTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);

//                ft.setCustomAnimations(R.animator.card_flip_right_in, R.animator.card_flip_right_out, R.animator.fade_in, R.animator.fade_out);//,0 R.animator.card_flip_left_in, R.animator.card_flip_left_out);
                break;
            case Animation.FADE_IN_OUT:
//                ft.setCustomAnimations(R.animator.fade_in, R.animator.fade_out, R.animator.fade_in, R.animator.fade_out);
                break;
        }

        // Replace Fragment
        ft.replace(R.id.main_fragment, fragment);

        // Save old fragment in the stack
        ft.addToBackStack(null);

        // Commit changes
        ft.commit();
    }

    public void goToPreviousPage() {
//        int count = getFragmentManager().getBackStackEntryCount();

        // Hide Keyboard
        InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view != null)
            keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);

        // Get Old Fragment
        super.onBackPressed();
    }

    public void logout() {
        startActivity(new Intent(this, SignActivity.class));
        finish();
    }

    public ModelManager getModelManager() {
        return (modelManager);
    }

//    @Override
//    public void onFocusChange(View v, boolean hasFocus) {
//        if (hasFocus)
//            bottomBar.onClick(v.getId());
//    }
}
