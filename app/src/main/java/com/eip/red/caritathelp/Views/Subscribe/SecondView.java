package com.eip.red.caritathelp.Views.Subscribe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.eip.red.caritathelp.Models.User;
import com.eip.red.caritathelp.Presenters.Subscribe.SubscribePresenter;
import com.eip.red.caritathelp.R;

/**
 * Created by pierr on 11/01/2016.
 */

public class SecondView extends Fragment implements ISecondView {

    private User user;

    private SubscribePresenter presenter;

    private EditText    mail;
    private EditText    password;
    private EditText    passwordChecking;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = ((SubscribeActivity) getActivity()).getUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view = inflater.inflate(R.layout.fragment_subscribe_second, container, false);

        // Init Presenter
        presenter = new SubscribePresenter(this, user);

        // Init UI Elements
        mail = (EditText) view.findViewById(R.id.subscribe_mail);
        password = (EditText) view.findViewById(R.id.subscribe_password);
        passwordChecking = (EditText) view.findViewById(R.id.subscribe_password_checking);

        return (view);
    }

    @Override
    public void setMailError(String error) {
        mail.setError(error);
    }

    @Override
    public void setPasswordError(String error) {
        password.setError(error);
    }

    @Override
    public void setPasswordCheckingError(String error) {
        passwordChecking.setError(error);
    }

    public String getMail() {
        return (mail.getText().toString());
    }

    public String getPassword() {
        return (password.getText().toString());
    }

    public String getPasswordChecking() {
        return (passwordChecking.getText().toString());
    }

    public SubscribePresenter getPresenter() {
        return presenter;
    }
}
