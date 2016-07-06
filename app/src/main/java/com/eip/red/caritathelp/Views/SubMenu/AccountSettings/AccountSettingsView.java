package com.eip.red.caritathelp.Views.SubMenu.AccountSettings;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.eip.red.caritathelp.Activities.Main.MainActivity;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.User.User;
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

    public static Fragment newInstance() {
        AccountSettingsView fragment = new AccountSettingsView();
        Bundle              args = new Bundle();

        args.putInt("page", R.string.view_name_submenu_account_management);
        fragment.setArguments(args);

        return (fragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get User  Model
        user = ((MainActivity) getActivity()).getModelManager().getUser();

        // Init Presenter
        presenter = new AccountSettingsPresenter(this, user);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_submenu_account_settings, container, false);

//        // Init SearchBar
//        ((MainActivity) getActivity()).getToolBar().getSearchBar().setVisibility(View.GONE);

        // Init Edit Text
        EditText firsname = (EditText) view.findViewById(R.id.firstname);
        EditText lastname = (EditText) view.findViewById(R.id.lastname);
        EditText mail = (EditText) view.findViewById(R.id.mail);

        firsname.setHint("Prénom : " +  user.getFirstname());
        lastname.setHint("Nom : " + user.getLastname());
        mail.setHint("E-mail : " + user.getMail());

        // Init HashMap Texts
        editTexts = new HashMap<>();
        editTexts.put(FIRSTNAME, firsname);
        editTexts.put(LASTNAME, lastname);
        editTexts.put(MAIL, mail);
        editTexts.put(PASSWORD_CURRENT, (EditText) view.findViewById(R.id.current_password));
        editTexts.put(PASSWORD_NEW, (EditText) view.findViewById(R.id.new_password));
        editTexts.put(PASSWORD_NEW_CHECKING, (EditText) view.findViewById(R.id.new_password_verification));

        // Init Listener
        view.findViewById(R.id.btn_save).setOnClickListener(this);

        // Init Progress Bar
        progressBar = (ProgressBar) view.findViewById(R.id.account_settings_progress_bar);

        return (view);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init ToolBar Title
        getActivity().setTitle(getArguments().getInt("page"));
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save)
            presenter.saveModification(editTexts);
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
        new AlertDialog.Builder(getActivity().getBaseContext())
                .setMessage("Vos changements ont bien été enregistrés")
                .show();
    }

    private void refreshEditText(View view) {
        EditText    firsname = (EditText) view.findViewById(R.id.firstname);
        EditText    lastname = (EditText) view.findViewById(R.id.lastname);
        EditText    mail = (EditText) view.findViewById(R.id.mail);
        EditText    currentP = (EditText) view.findViewById(R.id.current_password);
        EditText    newP = (EditText) view.findViewById(R.id.new_password);
        EditText    newCP = (EditText) view.findViewById(R.id.new_password_verification);

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

        firsname.setHint("Prénom : " + user.getFirstname());
        lastname.setHint("Nom : " + user.getLastname());
        mail.setHint("E-mail : " + user.getMail());
        currentP.setHint("Mot de passe actuel");
        newP.setHint("Nouveau mot de passe");
        newCP.setHint("Retapez le nouveau mot de passe");

    }

}
