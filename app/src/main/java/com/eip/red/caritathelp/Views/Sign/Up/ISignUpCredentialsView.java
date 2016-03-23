package com.eip.red.caritathelp.Views.Sign.Up;

/**
 * Created by pierr on 23/03/2016.
 */

public interface ISignUpCredentialsView {

    void showProgress();

    void hideProgress();

    void setMailError(String error);

    void setPasswordError(String error);

    void setPasswordVerificationError(String error);

    void setDialog(String title, String msg);
}
