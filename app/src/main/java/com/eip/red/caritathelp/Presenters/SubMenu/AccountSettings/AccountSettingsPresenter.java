package com.eip.red.caritathelp.Presenters.SubMenu.AccountSettings;

import android.widget.EditText;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.User;
import com.eip.red.caritathelp.Views.SubMenu.AccountSettings.AccountSettingsView;

import java.util.HashMap;

/**
 * Created by pierr on 22/01/2016.
 */

public class AccountSettingsPresenter implements IAccountSettingsPresenter, IOnAccountSettingsFinishedListener {

    private AccountSettingsView         view;
    private AccountSettingsInteractor   interactor;

    public AccountSettingsPresenter(AccountSettingsView view, User user, Network network) {
        this.view = view;
        interactor = new AccountSettingsInteractor(view.getActivity().getApplicationContext(), user, network);
    }

    @Override
    public void saveModification(HashMap<Integer, EditText> modification) {
        view.showProgress();
        interactor.saveModification(modification, this);
    }

    @Override
    public void onEmailError(String error) {
        view.setEmailError(error);
        view.hideProgress();
    }

    @Override
    public void onCurrentPasswordError(String error) {
        view.setCurrentPasswordError(error);
        view.hideProgress();
    }

    @Override
    public void onNewPasswordError(String error) {
        view.setNewPasswordError(error);
        view.hideProgress();
    }

    @Override
    public void onNewPasswordCheckingError(String error) {
        view.setNewPasswordCheckingError(error);
        view.hideProgress();
    }

    @Override
    public void onSuccess() {
        view.hideProgress();
        view.navigateToPreviousFragment();
    }
}
