package com.eip.red.caritathelp.Presenters.OrganisationSearch;

import android.content.Context;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierr on 25/02/2016.
 */

public class OrganisationSearchInteractor {

    private Context context;
    private Network network;

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
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception error, JsonObject result) {
                        if (error == null) {
                            // Status == 400 == error
                            if (result.get(Network.API_PARAMETER_STATUS).getAsInt() == Network.API_STATUS_ERROR)
                                listener.onDialogError("Statut 400", result.get(Network.API_PARAMETER_MSG).getAsString());
                            else
                                listener.onSuccess(parseJson(result));
                        }
                        else
                            listener.onDialogError("Problème de connection", "Vérifiez votre connexion Internet");
                    }
                });

    }

    private List<Organisation> parseJson(JsonObject result) {
        List<Organisation>  organisations;

        organisations = new ArrayList<>();

        for (JsonElement organisationJson : result.getAsJsonArray(Network.API_PARAMETER_RESPONSE)) {
            Organisation organisation = new Organisation();
            String          name = organisationJson.getAsJsonObject().get("name").toString();

            organisation.setName(name.replaceAll("\"", ""));
            organisations.add(organisation);
        }
        return (organisations);
    }
}
