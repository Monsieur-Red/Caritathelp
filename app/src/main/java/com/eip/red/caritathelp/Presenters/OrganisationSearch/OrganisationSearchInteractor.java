package com.eip.red.caritathelp.Presenters.OrganisationSearch;

import android.content.Context;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation;
import com.eip.red.caritathelp.Models.Organisations;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import junit.framework.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierr on 25/02/2016.
 */

public class OrganisationSearchInteractor {

    private Context         context;
    private Network         network;

    public OrganisationSearchInteractor(Context context, Network network) {
        this.context = context;
        this.network = network;
    }

    public void getAllOrganisations(final IOnOrganisationSearchFinishedListener listener) {
        JsonObject json = new JsonObject();

        json.addProperty("token", network.getToken());

        Ion.with(context)
                .load("GET", Network.API_LOCATION + Network.API_REQUEST_ORGANISATION)
                .setJsonObjectBody(json)
                .as(new TypeToken<Organisations>(){})
                .setCallback(new FutureCallback<Organisations>() {
                    @Override
                    public void onCompleted(Exception error, Organisations result) {
                        if (error == null) {
                            // Status == 400 == error
                            if (result.getStatus() == Network.API_STATUS_ERROR)
                                listener.onDialogError("Statut 400", result.getMessage());
                            else
                                listener.onSuccess(result.getResponse());
                        }
                        else
                            listener.onDialogError("Problème de connection", "Vérifiez votre connexion Internet");
                    }
                });
    }

//    private List<String>    getOrganisationsNames(List<Organisation> organisations) {
//        List<String>    organisationsNames = new ArrayList<>();
//
//        for (Organisation organisation : organisations) {
//            organisationsNames.add(organisation.getName());
//        }
//
//        return (organisationsNames);
//    }

}
