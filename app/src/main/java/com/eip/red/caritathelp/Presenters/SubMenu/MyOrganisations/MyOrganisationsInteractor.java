package com.eip.red.caritathelp.Presenters.SubMenu.MyOrganisations;

import android.content.Context;
import android.widget.ProgressBar;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Organisation;
import com.eip.red.caritathelp.Models.Organisation.Organisations;
import com.eip.red.caritathelp.Models.User;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierr on 24/02/2016.
 */

public class MyOrganisationsInteractor {

    private Context         context;
    private User            user;
    private Network         network;

    public MyOrganisationsInteractor(Context context, User user, Network network) {
        this.context = context;
        this.user = user;
        this.network = network;
    }

    public void getMyOrganisations(ProgressBar progressBar, final IOnMyOrganisationsFinishedListener listener) {
        JsonObject json = new JsonObject();

        json.addProperty("token", network.getToken());

        Ion.with(context)
                .load("GET", Network.API_LOCATION + Network.API_REQUEST_VOLUNTEERS + user.getId() + Network.API_REQUEST_GET_MY_ORGANISATIONS)
                .progressBar(progressBar)
                .setJsonObjectBody(json)
                .as(new TypeToken<Organisations>() {
                })
                .setCallback(new FutureCallback<Organisations>() {
                    @Override
                    public void onCompleted(Exception error, Organisations result) {
                        if (error == null) {
                            // Status == 400 == error
                            if (result.getStatus() == Network.API_STATUS_ERROR)
                                listener.onDialogError("Statut 400", result.getMessage());
                            else
                                listener.onSuccess(getOrganisationsByProfile(result.getResponse(), "owner"), getOrganisationsByProfile(result.getResponse(), "member"));
                        }
                        else
                            listener.onDialogError("Problème de connection", "Vérifiez votre connexion Internet");
                    }
                });
    }

    private List<Organisation>  getOrganisationsByProfile(List<Organisation> organisations, String profile) {
        List<Organisation>  newList = new ArrayList<>();

        for (Organisation organisation : organisations) {
            if (organisation.getRights().equals(profile))
                newList.add(organisation);
        }

        return (newList);
    }

}
