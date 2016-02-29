package com.eip.red.caritathelp.Views.Subscribe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;

import com.eip.red.caritathelp.MainActivity.MainActivity;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.User;
import com.eip.red.caritathelp.Presenters.Subscribe.SubscribePresenter;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Views.Login.LoginView;

/**
 * Created by pierr on 11/01/2016.
 */

public class ThirdView extends Fragment implements IThirdPage, View.OnClickListener {

    private User user;

    private SubscribePresenter presenter;

    private Switch      geolocation;
    private Switch      notification;
    private Button      terms;
    private boolean     aggreed = false;
    private Button      subscribe;

    private ProgressBar progressBar;
    private AlertDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = ((SubscribeActivity) getActivity()).getUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View    view = inflater.inflate(R.layout.fragment_subscribe_third, container, false);

        // Init Presenter
        presenter = new SubscribePresenter(this, user);

        // Init UI Elements
        geolocation = (Switch) view.findViewById(R.id.subscribe_switch_geolocation);
        notification = (Switch) view.findViewById(R.id.subscribe_switch_notification);
        terms = (Button) view.findViewById(R.id.subscribe_btn_terms_conditions);
        subscribe = (Button) view.findViewById(R.id.subscribe_btn_subscribe);
        progressBar = (ProgressBar) view.findViewById(R.id.subscribe_progress_bar);

        // Init Listener
        terms.setOnClickListener(this);
        subscribe.setOnClickListener(this);

        // Init dialog Terms & Conditions
        initDialog();

        return (view);
    }

    private void initDialog() {
        dialog = new AlertDialog.Builder(this.getContext())
                .setTitle("Conditions d'utilisation")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        aggreed = true;
                    }
                })
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        aggreed = false;
                    }
                })
                .setMessage("Ut sed dictas tacimates argumentum. Ea vel fabellas conclusionemque, harum eloquentiam eu eam. Dolorum denique similique quo ne. Eam at sint erant phaedrum.\n" +
                        "\n" +
                        "Ius ut ancillae hendrerit, qui ne magna ignota molestiae. Amet illum utinam duo an. Eos vivendum euripidis cotidieque id, cu epicuri reprimique quo, at nisl wisi verear eam. Ex summo admodum qui, in oratio omnium has, cu verear assueverit mei.")
                .create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.subscribe_btn_terms_conditions:
                dialog.show();
                break;
            case R.id.subscribe_btn_subscribe:
                presenter.subscribe(geolocation.isChecked(), notification.isChecked(), aggreed);
                break;
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setMailError(String error) {
        SecondView  view = (SecondView) ((SubscribeActivity)getActivity()).getPagerAdapter().getFragment(2);

        view.setMailError(error);
    }

    @Override
    public void setTermsError(String error) {
        new AlertDialog.Builder(this.getContext())
                .setMessage(error)
                .show();
    }

    @Override
    public void setSubscribeError(String error) {
        new AlertDialog.Builder(this.getContext())
                .setTitle("Echec de l'inscription!")
                .setMessage(error)
                .show();
    }

    @Override
    public void navigateToNextActivity(final Network network) {
        new AlertDialog.Builder(this.getContext())
                .setTitle("Inscription réussie!")
                .setMessage("Voulez-vous vous connecter directement à l'application ?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        goToMainActivity(network);
                    }
                })
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        goToLoginActivity();
                    }
                })
                .show();
    }

    private void goToMainActivity(Network network) {
        SubscribeActivity   activity = (SubscribeActivity)this.getActivity();
        Intent              intent = new Intent(activity, MainActivity.class);

        intent.putExtra("user", user);
        intent.putExtra("network", network);
        startActivity(intent);
        activity.finish();
    }

    private void goToLoginActivity() {
        SubscribeActivity   activity = (SubscribeActivity)this.getActivity();

        startActivity(new Intent(activity, LoginView.class));
        activity.finish();
    }

}
