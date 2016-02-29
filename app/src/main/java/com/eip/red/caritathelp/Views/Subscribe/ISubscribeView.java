package com.eip.red.caritathelp.Views.Subscribe;

/**
 * Created by pierr on 09/12/2015.
 */
public interface ISubscribeView {
    void showProgress();

    void hideProgress();

    void setFirstNameError(String error);

    void setLastNameError(String error);

    void setBirthDateError(String error);

    void setEmailError(String error);

    void setPasswordError(String error);

    void setPasswordVerificationError(String error);

    void navigateToMainActivity();
}
