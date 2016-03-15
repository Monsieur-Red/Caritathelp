package com.eip.red.caritathelp.Views.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.eip.red.caritathelp.Main.MainActivity;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.User;
import com.eip.red.caritathelp.Presenters.Login.LoginPresenter;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Views.Subscribe.SubscribeActivity;

/**
 * Created by pierr on 09/11/2015.
 */

public class LoginView extends AppCompatActivity implements ILoginView, View.OnClickListener {

    private LoginPresenter  presenter;
    private EditText        email;
    private EditText        password;
    private ProgressBar     progressBar;

    private AlertDialog     dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        // Init UI
        email = (EditText) findViewById(R.id.connection_text_input_email);
        password = (EditText) findViewById(R.id.connection_text_input_password);
        progressBar = (ProgressBar) findViewById(R.id.connection_bar_progress);

        // Init button listener
        findViewById(R.id.connection_btn_login).setOnClickListener(this);
        findViewById(R.id.connection_btn_subscribe).setOnClickListener(this);
        findViewById(R.id.connection_link_missing_password).setOnClickListener(this);

        // Init Presenter
        presenter = new LoginPresenter(this);

        // Init Dialog
        dialog = new AlertDialog.Builder(this)
                .setCancelable(true)
                .create();
    }

    @Override public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override public void setEmailError(String error) {
        email.setError(error);
    }

    @Override public void setPasswordError(String error) {
        password.setError(error);
    }

    @Override
    public void setConnectionInternetError(String error) {
        dialog.setTitle("Problème de connection");
        dialog.setMessage("Vérifiez votre connexion Internet");
        dialog.show();
    }

    @Override public void navigateToMainActivity(User user, Network network) {
        Intent  intent = new Intent(this, MainActivity.class);

        intent.putExtra("user", user);
        intent.putExtra("network", network);
        startActivity(intent);
        finish();
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.connection_btn_login:
                presenter.signIn(email.getText().toString(), password.getText().toString());
                break;
            case R.id.connection_btn_subscribe:
                startActivity(new Intent(this, SubscribeActivity.class));
                finish();
                break;
            case R.id.connection_link_missing_password:
                break;
        }
    }
}
