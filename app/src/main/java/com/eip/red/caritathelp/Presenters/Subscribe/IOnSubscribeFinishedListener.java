package com.eip.red.caritathelp.Presenters.Subscribe;

import com.eip.red.caritathelp.Models.Network;

/**
 * Created by pierr on 09/12/2015.
 */
public interface IOnSubscribeFinishedListener {

    void onFirstNameError(String error);

    void onLastNameError(String error);

    void onBirthdayError(String error);

    void onEmailError(String error);

    void onPasswordError(String error);

    void onPasswordCheckingError(String error);

    void onTermsError(String error);

    void onSubscribeError(String error);

    void onSuccess(Network network);
}
