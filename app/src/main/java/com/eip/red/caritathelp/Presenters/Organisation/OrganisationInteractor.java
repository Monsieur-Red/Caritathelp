package com.eip.red.caritathelp.Presenters.Organisation;

import android.content.Context;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.OrganisationJson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by pierr on 11/03/2016.
 */

public class OrganisationInteractor {

    private Context         context;
    private String          token;
    private int             id;

    public OrganisationInteractor(Context context, String token, int id) {
        this.context = context;
        this.token = token;
        this.id = id;
    }

    public void getOrganisation(final IOnOrganisationFinishedListener listener) {
        JsonObject json = new JsonObject();

        json.addProperty("token", token);

        Ion.with(context)
                .load("GET", Network.API_LOCATION + Network.API_REQUEST_ORGANISATION_BY_ID + id)
                .setJsonObjectBody(json)
                .as(new TypeToken<OrganisationJson>(){})
                .setCallback(new FutureCallback<OrganisationJson>() {
                    @Override
                    public void onCompleted(Exception error, OrganisationJson result) {
                        if (error == null) {
                            // Status == 400 == error
                            if (result.getStatus() == Network.API_STATUS_ERROR)
                                listener.onDialogError("Statut 400", result.getMessage());
                            else
                                listener.onOrganisationRequestSuccess(result.getResponse().getThumb_path(), result.getResponse().getRights());
                        }
                        else
                            listener.onDialogError("Problème de connection", "Vérifiez votre connexion Internet");
                    }
                });
    }

    public void getNews(IOnOrganisationFinishedListener listener) {

    }

    public int getOrganisationId() {
        return (id);
    }

}
