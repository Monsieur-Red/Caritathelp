package com.eip.red.caritathelp.Views.SubMenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eip.red.caritathelp.MainActivity.MainActivity;
import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.User;
import com.eip.red.caritathelp.R;
import com.eip.red.caritathelp.Views.Login.LoginView;
import com.eip.red.caritathelp.Views.SubMenu.MyOrganisations.MyOrganisationsView;
import com.eip.red.caritathelp.Views.SubMenu.AccountSettings.AccountSettingsView;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by pierr on 19/01/2016.
 */

public class SubMenuView extends Fragment implements View.OnClickListener {

    private User        user;
    private Network     network;

    private MyOrganisationsView myOrganisationsView;
    private AccountSettingsView accountSettingsView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = ((MainActivity) getActivity()).getModelManager().getUser();
        network = ((MainActivity) getActivity()).getModelManager().getNetwork();

        // Init Views
        myOrganisationsView = new MyOrganisationsView();
        accountSettingsView = new AccountSettingsView();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_submenu, container, false);

        // Init Listener
        view.findViewById(R.id.submenu_my_organisations).setOnClickListener(this);
        view.findViewById(R.id.submenu_account_settings).setOnClickListener(this);
        view.findViewById(R.id.submenu_logout).setOnClickListener(this);
        view.findViewById(R.id.submenu_delete_account).setOnClickListener(this);

        return (view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submenu_my_organisations:
                // Page Change
                ((MainActivity) getActivity()).replaceView(myOrganisationsView, true);
                break;
            case R.id.submenu_account_settings:
                // Page Change
                ((MainActivity) getActivity()).replaceView(accountSettingsView, true);
                break;
            case R.id.submenu_logout:
                ((MainActivity) getActivity()).logout();
                break;
            case R.id.submenu_delete_account:
                removeAccount();
                break;
        }
    }

    private void removeAccount() {
        JsonObject json = new JsonObject();

        json.addProperty("token", network.getToken());

        Ion.with(this.getContext())
                .load("DELETE", Network.API_LOCATION + Network.API_REQUEST_VOLUNTEER_DELETE_ACCOUNT + user.getId())
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception error, JsonObject result) {
                        if (error == null) {
                            MainActivity   activity = (MainActivity) getActivity();

                            startActivity(new Intent(activity, LoginView.class));
                            activity.finish();
                        }
                        else
                            System.out.println("ERROR : " + error.toString());
                    }
                });
    }

}
