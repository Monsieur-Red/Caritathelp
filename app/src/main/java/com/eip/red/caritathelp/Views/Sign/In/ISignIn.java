package com.eip.red.caritathelp.Views.Sign.In;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.User;

/**
 * Created by pierr on 22/03/2016.
 */

public interface ISignIn {

    void showProgress();

    void hideProgress();

    void setEmailError(String error);

    void setPasswordError(String error);

    void setDialog(String title, String msg);
}
