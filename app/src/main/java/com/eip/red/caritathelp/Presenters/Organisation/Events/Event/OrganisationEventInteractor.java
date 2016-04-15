package com.eip.red.caritathelp.Presenters.Organisation.Events.Event;

import android.content.Context;
import android.widget.ProgressBar;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Event;
import com.eip.red.caritathelp.Models.Organisation.EventInformations;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

/**
 * Created by pierr on 18/03/2016.
 */

public class OrganisationEventInteractor {

    private Context context;
    private Network network;
    private int     eventId;

    public OrganisationEventInteractor(Context context, Network network, int eventId) {
        this.context = context;
        this.network = network;
        this.eventId = eventId;
    }

    public void getEvent(ProgressBar progressBar, final IOnOrganisationEventFinishedListener listener) {
        JsonObject json = new JsonObject();

        json.addProperty("token", network.getToken());

        Ion.with(context)
                .load("GET", Network.API_LOCATION + Network.API_REQUEST_ORGANISATION_EVENTS_INFORMATIONS + eventId)
                .progressBar(progressBar)
                .setJsonObjectBody(json)
                .as(new TypeToken<EventInformations>(){})
                .setCallback(new FutureCallback<EventInformations>() {
                    @Override
                    public void onCompleted(Exception error, EventInformations result) {
                        if (error == null) {
                            // Status == 400 == error
                            if (result.getStatus() == Network.API_STATUS_ERROR)
                                listener.onDialog("Statut 400", result.getMessage());
                            else {
                                Event   event = result.getResponse();
                                listener.onSuccessGetEvent(event.getTitle(), event.getRights());
                            }
                        }
                        else
                            listener.onDialog("Problème de connection", "Vérifiez votre connexion Internet");
                    }
                });
    }

    public void getNews(IOnOrganisationEventFinishedListener listener) {

    }

    public int getEventId() {
        return eventId;
    }
}
