package com.eip.red.caritathelp.Presenters.Login;

import android.support.v7.app.AlertDialog;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.User;
import com.eip.red.caritathelp.Views.Login.LoginView;

/**
 * Created by pierr on 09/11/2015.
 */

public class LoginPresenter implements ILoginPresenter, IOnLoginFinishedListener {

    private LoginView           view;
    private LoginInteractor     interactor;

    public LoginPresenter(LoginView view) {
        this.view = view;
        this.interactor = new LoginInteractor(view.getApplicationContext());
    }

    @Override
    public void signIn(String email, String password) {
        view.showProgress();
        interactor.login(email, password, this);
    }

    @Override public void onEmailError(String error) {
        view.setEmailError(error);
        view.hideProgress();
    }

    @Override public void onPasswordError(String error) {
        view.setPasswordError(error);
        view.hideProgress();
    }

    @Override
    public void onConnectionInternetError(String error) {
        view.hideProgress();
        view.setConnectionInternetError(error);
    }

    @Override public void onSuccess(User user, Network network) {
        view.navigateToMainActivity(user, network);
    }
}
