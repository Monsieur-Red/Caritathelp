package com.eip.red.caritathelp.Views.SubMenu.AccountSettings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.eip.red.caritathelp.MainActivity.MainActivity;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.User;
import com.eip.red.caritathelp.Presenters.SubMenu.AccountSettings.AccountSettingsPresenter;
import com.eip.red.caritathelp.R;

import java.util.HashMap;

/**
 * Created by pierr on 22/01/2016.
 */

public class AccountSettingsView extends Fragment implements IAccountSettingsView, View.OnClickListener {

    static final public int    FIRSTNAME = 0;
    static final public int    LASTNAME = 1;
    static final public int    MAIL = 2;
    static final public int    PASSWORD_CURRENT= 3;
    static final public int    PASSWORD_NEW = 4;
    static final public int    PASSWORD_NEW_CHECKING = 5;

    private User    user;

    private AccountSettingsPresenter    presenter;

    private HashMap<Integer, EditText>   editTexts;

    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get User & Network Model
        Network network = ((MainActivity) getActivity()).getModelManager().getNetwork();
        user = ((MainActivity) getActivity()).getModelManager().getUser();

        // Init Presenter
        presenter = new AccountSettingsPresenter(this, user, network);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_submenu_account_settings, container, false);

        // Init Edit Text
        EditText firsname = (EditText) view.findViewById(R.id.submenu_account_settings_firstname);
        EditText lastname = (EditText) view.findViewById(R.id.submenu_account_settings_lastname);
        EditText mail = (EditText) view.findViewById(R.id.submenu_account_settings_mail);

        firsname.setHint("Prénom : " +  user.getFirstName());
        lastname.setHint("Nom : " + user.getLastName());
        mail.setHint("E-mail : " + user.getMail());

        // Init HashMap Texts
        editTexts = new HashMap<>();
        editTexts.put(FIRSTNAME, firsname);
        editTexts.put(LASTNAME, lastname);
        editTexts.put(MAIL, mail);
        editTexts.put(PASSWORD_CURRENT, (EditText) view.findViewById(R.id.submenu_account_settings_current_password));
        editTexts.put(PASSWORD_NEW, (EditText) view.findViewById(R.id.submenu_account_settings_new_password));
        editTexts.put(PASSWORD_NEW_CHECKING, (EditText) view.findViewById(R.id.submenu_account_settings_checking_new_password));

        // Init Listener
        view.findViewById(R.id.top_bar_account_settings_return).setOnClickListener(this);
        view.findViewById(R.id.submenu_account_settings_btn_save).setOnClickListener(this);

        // Init Progress Bar
        progressBar = (ProgressBar) view.findViewById(R.id.account_settings_progress_bar);

        return (view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submenu_account_settings_btn_save:
                presenter.saveModification(editTexts);
                break;
            case R.id.top_bar_account_settings_return:
                ((MainActivity)getActivity()).goToPreviousPage();
                break;
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setEmailError(String error) {
        editTexts.get(MAIL).setError(error);
    }

    @Override
    public void setCurrentPasswordError(String error) {
        editTexts.get(PASSWORD_CURRENT).setError(error);
    }

    @Override
    public void setNewPasswordError(String error) {
        editTexts.get(PASSWORD_NEW).setError(error);
    }

    @Override
    public void setNewPasswordCheckingError(String error) {
        editTexts.get(PASSWORD_NEW_CHECKING).setError(error);
    }

    @Override
    public void navigateToPreviousFragment() {
        refreshEditText(getView());

        // Display a dialog box
        new AlertDialog.Builder(getContext())
                .setMessage("Vos changements ont bien été enregistrés")
                .show();
    }

    private void refreshEditText(View view) {
        EditText    firsname = (EditText) view.findViewById(R.id.submenu_account_settings_firstname);
        EditText    lastname = (EditText) view.findViewById(R.id.submenu_account_settings_lastname);
        EditText    mail = (EditText) view.findViewById(R.id.submenu_account_settings_mail);
        EditText    currentP = (EditText) view.findViewById(R.id.submenu_account_settings_current_password);
        EditText    newP = (EditText) view.findViewById(R.id.submenu_account_settings_new_password);
        EditText    newCP = (EditText) view.findViewById(R.id.submenu_account_settings_checking_new_password);

        firsname.invalidate();
        lastname.invalidate();
        mail.invalidate();
        currentP.invalidate();
        newP.invalidate();
        newCP.invalidate();

        firsname.getText().clear();
        lastname.getText().clear();
        mail.getText().clear();
        currentP.getText().clear();
        newP.getText().clear();
        newCP.getText().clear();

        firsname.setHint("Prénom : " + user.getFirstName());
        lastname.setHint("Nom : " + user.getLastName());
        mail.setHint("E-mail : " + user.getMail());
        currentP.setHint("Mot de passe actuel");
        newP.setHint("Nouveau mot de passe");
        newCP.setHint("Retapez le nouveau mot de passe");

    }

}
