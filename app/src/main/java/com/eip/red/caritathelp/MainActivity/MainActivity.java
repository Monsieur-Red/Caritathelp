package com.eip.red.caritathelp.MainActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.WindowManager;

import com.eip.red.caritathelp.Models.ModelManager;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Views.Login.LoginView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ModelManager    modelManager;

    private MyBottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init ModelManager
        modelManager = new ModelManager(getIntent());

        // Init Bar
        bottomBar = new MyBottomBar(this);

        // Set Status Bar Color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater    inflater = getMenuInflater();
        inflater.inflate(R.menu.test, menu);

        return (super.onCreateOptionsMenu(menu));
    }

    @Override
    public void onClick(View v) {
//        topBar.onClick(v);
        bottomBar.onClick(v);
    }

    public void replaceView(Fragment fragment, boolean animation) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (animation)
            ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        ft.replace(R.id.main_fragment, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public void goToPreviousPage() {
//        System.out.println("SIZEEEEEEEEEEEEE : " + getFragmentManager().getBackStackEntryCount());
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
