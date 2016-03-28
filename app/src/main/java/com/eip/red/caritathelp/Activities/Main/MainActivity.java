package com.eip.red.caritathelp.Activities.Main;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.eip.red.caritathelp.Activities.Sign.SignActivity;
import com.eip.red.caritathelp.Models.Enum.Animation;
import com.eip.red.caritathelp.Models.ModelManager;
import com.eip.red.caritathelp.R;

public class MainActivity extends Activity implements View.OnClickListener, View.OnFocusChangeListener {

    private ModelManager    modelManager;

    private MainActivityToolbar toolBar;
    private MyBottomBar     bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set View
        setContentView(R.layout.activity_main);

        // Init ModelManager
        modelManager = new ModelManager(getIntent());

        // Init Tool Bar
        toolBar = new MainActivityToolbar(this);

        // Init Bottom Bar
        bottomBar = new MyBottomBar(this);

        // Set Status Bar Color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary_dark));
        }

    }

    @Override
    public void onClick(View v) {
        toolBar.onClick(v.getId());
//        bottomBar.onClick(v.getId());
    }

//    @Override
//    public void onBackPressed() {
//        Fragment fragment = getFragmentManager().findFragmentById(R.id.main_fragment);
//
//        System.out.println("MAINACTIVITY");
//        if (fragment instanceof MyEventsView)
//            ((MyEventsView) fragment).onBackPressed();
//        else
//            super.onBackPressed();
//    }

    public void replaceView(Fragment fragment, int animation) {
        // Hide Keyboard
        InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View    view = getCurrentFocus();
        if (view != null)
            keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);

        // Replace Fragment
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        // Set Animation
        switch (animation) {
            case Animation.SLIDE_LEFT_RIGHT:
                  ft.setCustomAnimations(R.animator.fade_in, R.animator.fade_out, R.animator.fade_in, R.animator.fade_out);
//                fragment.setEnterTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                fragment.setExitTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
//                ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);//, R.anim.enter_from_left, R.anim.exit_to_right);
                break;
            case Animation.SLIDE_UP_DOWN:
//                fragment.setEnterTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                fragment.setExitTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
//                ft.setCustomAnimations(R.anim.enter_from_bot, R.anim.exit_to_bot, R.anim.enter_from_top, R.anim.exit_to_top);
                ft.setCustomAnimations(R.animator.slide_up, R.animator.slide_down, R.animator.slide_up, R.animator.slide_down);
                break;
            case Animation.FLIP_LEFT_RIGHT:
//                fragment.setEnterTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

//                fragment.setEnterTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//                fragment.setExitTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);

                ft.setCustomAnimations(R.animator.card_flip_right_in, R.animator.card_flip_right_out, R.animator.fade_in, R.animator.fade_out);//,0 R.animator.card_flip_left_in, R.animator.card_flip_left_out);
//                ft.setCustomAnimations(R.anim.card_flip_right_in, R.anim.card_flip_right_out, R.anim.card_flip_left_in, R.anim.card_flip_left_out);

//                ft.setCustomAnimations(R.anim.test, R.anim.test);

//                ft.setCustomAnimations(R.anim.to_middle, R.anim.from_middle);

                break;
            case Animation.FADE_IN_OUT:
                ft.setCustomAnimations(R.animator.fade_in, R.animator.fade_out, R.animator.fade_in, R.animator.fade_out);
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

    public MainActivityToolbar getToolBar() {
        return toolBar;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus)
            bottomBar.onClick(v.getId());
    }
}
