package com.eip.red.caritathelp.Views.Subscribe;

import com.eip.red.caritathelp.Models.Network;

/**
 * Created by pierr on 16/02/2016.
 */

public interface IThirdPage {

    void showProgress();

    void hideProgress();

    void setMailError(String error);

    void setTermsError(String error);

    void setSubscribeError(String error);

    void navigateToNextActivity(Network network);
}
