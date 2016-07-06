package com.eip.red.caritathelp.Presenters.Organisation.Informations;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.OrganisationJson;
import com.eip.red.caritathelp.R;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by pierr on 11/05/2016.
 */

public class OrganisationInformationsInteractor {

    private Context context;
    private String  token;
    private int     id;

    public OrganisationInformationsInteractor(Context context, String token, int id) {
        this.context = context;
        this.token = token;
        this.id = id;
    }

    public void getOrganisationInformations(final IOnOrganisationInformationsFinishedListener listener, final ImageView imageView, ProgressBar progressBar) {
        JsonObject json = new JsonObject();

        json.addProperty("token", token);

        Ion.with(context)
                .load("GET", Network.API_LOCATION + Network.API_REQUEST_ORGANISATION_BY_ID + id)
                .progressBar(progressBar)
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
                                // Set Picture
                                Network.loadImage(context, imageView, Network.API_LOCATION_2 + result.getResponse().getThumb_path(), R.drawable.logo_caritathelp_2017_picture_only_normal);

                                listener.onSuccess(result.getResponse());
                            }
                        }
                        else
                            listener.onDialogError("Problème de connection", "Vérifiez votre connexion Internet");
                    }
                });
    }

}
