package com.eip.red.caritathelp.Presenters.Organisation.OrganisationMembers;

import android.content.Context;

import com.eip.red.caritathelp.Models.Organisation.Members;
import com.eip.red.caritathelp.Models.Network;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by pierr on 11/03/2016.
 */

public class OrganisationMembersInteractor {

    private Context context;
    private Network network;
    private Members members;
    private int     organisationId;

    public OrganisationMembersInteractor(Context context, Network network, int organisationId) {
        this.context = context;
        this.network = network;
        this.organisationId = organisationId;
    }

    public void getMembers(final IOnOrganisationMembersFinishedListener listener) {
        JsonObject json = new JsonObject();

        json.addProperty("token", network.getToken());

        Ion.with(context)
                .load("GET", Network.API_LOCATION + Network.API_REQUEST_ORGANISATION + "/" + organisationId + Network.API_REQUEST_ORGANISATION_MEMBERS)
                .setJsonObjectBody(json)
                .as(new TypeToken<Members>(){})
                .setCallback(new FutureCallback<Members>() {
                    @Override
                    public void onCompleted(Exception error, Members result) {
                        if (error == null) {
                            // Status == 400 == error
                            if (result.getStatus() == Network.API_STATUS_ERROR)
                                listener.onDialogError("Statut 400", result.getMessage());
                            else {
                                members = result;
                                listener.onSuccess(members.getResponse());
                            }
                        }
                        else
                            listener.onDialogError("Problème de connection", "Vérifiez votre connexion Internet");
                    }
                });
    }

}
