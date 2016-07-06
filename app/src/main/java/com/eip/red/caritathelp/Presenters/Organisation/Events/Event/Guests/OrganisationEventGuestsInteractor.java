package com.eip.red.caritathelp.Presenters.Organisation.Events.Event.Guests;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.ProgressBar;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Guests;
import com.eip.red.caritathelp.Models.User.User;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by pierr on 15/04/2016.
 */

public class OrganisationEventGuestsInteractor {

    private Context context;
    private String  token;
    private int     eventId;

    public OrganisationEventGuestsInteractor(Context context, String token, int eventId) {
        this.context = context;
        this.token = token;
        this.eventId = eventId;
    }

    public void getGuests(final IOnOrganisationEventGuestsFinishedListener listener, ProgressBar progressBar) {
        JsonObject json = new JsonObject();

        json.addProperty("token", token);

        Ion.with(context)
                .load("GET", Network.API_LOCATION + Network.API_REQUEST_ORGANISATION_EVENT + eventId + Network.API_REQUEST_ORGANISATION_EVENTS_GUESTS)
                .progressBar(progressBar)
                .setJsonObjectBody(json)
                .as(new TypeToken<Guests>(){})
                .setCallback(new FutureCallback<Guests>() {
                    @Override
                    public void onCompleted(Exception error, Guests result) {
                        if (error == null) {
                            // Status == 400 == error
                            if (result.getStatus() == Network.API_STATUS_ERROR)
                                listener.onDialog("Statut 400", result.getMessage());
                            else
                                listener.onSuccess(result.getResponse());
                        }
                        else {
                            new AlertDialog.Builder(context).setMessage(error.toString()).show();
                            listener.onDialog("Problème de connection", "Vérifiez votre connexion Internet");
                        }
                    }
                });

    }
}
