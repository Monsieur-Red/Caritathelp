package com.eip.red.caritathelp.Presenters.Sign.In;

/**
 * Created by pierr on 22/03/2016.
 */

public interface ISignInPresenter {

    void onClick(int viewId);

    void signIn(String email, String password);
}
