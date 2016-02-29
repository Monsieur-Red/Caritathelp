package com.eip.red.caritathelp.Presenters.SubMenu.AccountSettings;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.User;

/**
 * Created by pierr on 22/01/2016.
 */
public interface IOnAccountSettingsFinishedListener {

    void onEmailError(String error);

    void onCurrentPasswordError(String error);

    void onNewPasswordError(String error);

    void onNewPasswordCheckingError(String error);

    void onSuccess();
}
