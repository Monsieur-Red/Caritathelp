package com.eip.red.caritathelp.Presenters.Login;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.User;

/**
 * Created by pierr on 09/11/2015.
 */

public interface IOnLoginFinishedListener {

    void onEmailError(String error);

    void onPasswordError(String error);

    void onConnectionInternetError(String error);

    void onSuccess(User user, Network network);
}
