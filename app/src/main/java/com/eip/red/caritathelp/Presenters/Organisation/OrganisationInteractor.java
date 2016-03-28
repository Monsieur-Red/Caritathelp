package com.eip.red.caritathelp.Presenters.Organisation;

import android.content.Context;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Organisation;
import com.eip.red.caritathelp.Models.Organisation.Organisations;
import com.eip.red.caritathelp.Models.OrganisationJson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by pierr on 11/03/2016.
 */

public class OrganisationInteractor {

    private Context         context;
    private Network         network;
    private Organisation    organisation;

    public OrganisationInteractor(Context context, Network network, Organisation organisation) {
        this.context = context;
        this.network = network;
        this.organisation = organisation;
    }

    public void getOrganisation(final IOnOrganisationFinishedListener listener) {
        JsonObject json = new JsonObject();

        json.addProperty("token", network.getToken());

        Ion.with(context)
                .load("GET", Network.API_LOCATION + Network.API_REQUEST_ORGANISATION_BY_ID + organisation.getId())
                .setJsonObjectBody(json)
                .as(new TypeToken<OrganisationJson>(){})
                .setCallback(new FutureCallback<OrganisationJson>() {
                    @Override
                    public void onCompleted(Exception error, OrganisationJson result) {
                        if (error == null) {
                            // Status == 400 == error
                            if (result.getStatus() == Network.API_STATUS_ERROR)
                                listener.onDialogError("Statut 400", result.getMessage());
                            else {
                                organisation = result.getResponse();
                                listener.onOrganisationRequestSuccess(organisation.getRights());
                            }
                        }
                        else
                            listener.onDialogError("Problème de connection", "Vérifiez votre connexion Internet");
                    }
                });
    }

    public void getNews(IOnOrganisationFinishedListener listener) {

    }

    public String getOrganisationName() {
        return (organisation.getName());
    }

    public int getOrganisationId() {
        return (organisation.getId());
    }

    public boolean isOwner() {
        if (organisation.getRights().equals("owner"))
            return (true);
        return (false);
    }

}
