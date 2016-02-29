package com.eip.red.caritathelp.Presenters.Subscribe;

import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.User;
import com.eip.red.caritathelp.Views.Subscribe.FirstView;
import com.eip.red.caritathelp.Views.Subscribe.SecondView;
import com.eip.red.caritathelp.Views.Subscribe.ThirdView;

import java.util.List;

/**
 * Created by pierr on 09/12/2015.
 */

public class SubscribePresenter implements ISubscribePresenter, IOnSubscribeFinishedListener {

    private Fragment            view;
    private SubscribeInteractor interactor;

    public SubscribePresenter(Fragment view, User user) {
        this.view = view;
        this.interactor = new SubscribeInteractor(view.getContext(), user);
    }

    @Override
    public boolean goToSecondPage(String firstName, String lastName, String gender, List<Integer> birthday) {
        if (interactor.goToSecondPage(firstName, lastName, gender, birthday, this))
            return (true);
        return (false);
    }

    @Override
    public boolean goToThirdPage(String mail, String password, String passwordChecking) {
        if (interactor.goToThirdPage(mail, password, passwordChecking, this))
            return (true);
        return (false);
    }

    @Override
    public void subscribe(boolean geolocation, boolean notification, boolean terms) {
        // Set Progress Bar Visibility
        ((ThirdView)view).showProgress();

        interactor.subscribe(geolocation, notification, terms, this);
    }

    @Override
    public void onFirstNameError(String error) {
        ((FirstView)view).setFirstNameError(error);
    }

    @Override
    public void onLastNameError(String error) {
        ((FirstView)view).setLastNameError(error);
    }

    @Override
    public void onBirthdayError(String error) {
        ((FirstView)view).setBirthDateError(error);
//        ((FirstView)view).showDialog(error);
    }

    @Override
    public void onEmailError(String error) {
        ((ThirdView)view).setMailError(error);
    }

    @Override
    public void onPasswordError(String error) {
        ((SecondView)view).setPasswordError(error);
    }

    @Override
    public void onPasswordCheckingError(String error) {
        ((SecondView)view).setPasswordCheckingError(error);
    }

    @Override
    public void onTermsError(String error) {
        // Set Progress Bar Visibility
        ((ThirdView)view).hideProgress();

        // Set Terms Error
        ((ThirdView)view).setTermsError(error);
    }

    @Override
    public void onSubscribeError(String error) {
        // Set Progress Bar Visibility
        ((ThirdView)view).hideProgress();

        // Set Subscribe Dialog Error
        ((ThirdView)view).setSubscribeError(error);
    }

    @Override
    public void onSuccess(Network network) {
        // Set Progress Bar Visibility
        ((ThirdView)view).hideProgress();

        // Go to next activity
        ((ThirdView)view).navigateToNextActivity(network);
    }

}
