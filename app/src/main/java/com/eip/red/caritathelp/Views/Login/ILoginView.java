package com.eip.red.caritathelp.Views.Login;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.User;

/**
 * Created by pierr on 09/11/2015.
 */

public interface ILoginView {

    void showProgress();

    void hideProgress();

    void setEmailError(String error);

    void setPasswordError(String error);

    void setConnectionInternetError(String error);

    void navigateToMainActivity(User user, Network network);
}
