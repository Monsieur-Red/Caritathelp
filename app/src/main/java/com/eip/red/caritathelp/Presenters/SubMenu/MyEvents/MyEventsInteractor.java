package com.eip.red.caritathelp.Presenters.SubMenu.MyEvents;

import android.content.Context;

import com.eip.red.caritathelp.Models.Network;
import com.eip.red.caritathelp.Models.Organisation.Event;
import com.eip.red.caritathelp.Models.Organisation.Events;
import com.eip.red.caritathelp.Models.Organisation.Organisation;
import com.eip.red.caritathelp.Models.Organisation.Organisations;
import com.eip.red.caritathelp.Presenters.SubMenu.MyOrganisations.IOnMyOrganisationsFinishedListener;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierr on 18/03/2016.
 */

public class MyEventsInteractor {

    private Context context;
    private Network network;
    private int     userId;

    List<Organisation> myOrganisationsOwner;

    public MyEventsInteractor(Context context, Network network, int userId) {
        this.context = context;
        this.network = network;
        this.userId = userId;
    }

    public void getMyEvents(final IOnMyEventsFinishedListener listener, final boolean init, String range, final boolean isSwipeRefresh) {
        JsonObject json = new JsonObject();

        json.addProperty("token", network.getToken());
        json.addProperty("range", range);

        Ion.with(context)
                .load("GET", Network.API_LOCATION + Network.API_REQUEST_MY_EVENTS + userId + Network.API_REQUEST_ORGANISATION_EVENTS)
                .setJsonObjectBody(json)
                .as(new TypeToken<Events>(){})
                .setCallback(new FutureCallback<Events>() {
                    @Override
                    public void onCompleted(Exception error, Events result) {
                        if (error == null) {
                            // Status == 400 == error
                            if (result.getStatus() == Network.API_STATUS_ERROR)
                                listener.onDialog("Statut 400", result.getMessage());
                            else {
                                if (isSwipeRefresh)
                                    listener.onSuccessGetMyEventsSR(result.getResponse());
                                else
                                    listener.onSuccessGetMyEvents(init, result.getResponse());
                            }
                        }
                        else
                            listener.onDialog("Problème de connection", "Vérifiez votre connexion Internet");
                    }
                });
    }

    private List<Event> getEventsByProfile(List<Event> events, String profile) {
        List<Event>  newList = new ArrayList<>();

        for (Event event : events) {
//            System.out.println("PROFILE : " + event.getRights());
            if (event.getRights().equals(profile))
                newList.add(event);
        }

        return (newList);
    }

    public void getMyOrganisations(final IOnMyEventsFinishedListener listener) {
        JsonObject json = new JsonObject();

        json.addProperty("token", network.getToken());

        Ion.with(context)
                .load("GET", Network.API_LOCATION + Network.API_REQUEST_VOLUNTEERS + userId + Network.API_REQUEST_GET_MY_ORGANISATIONS)
                .setJsonObjectBody(json)
                .as(new TypeToken<Organisations>() {
                })
                .setCallback(new FutureCallback<Organisations>() {
                    @Override
                    public void onCompleted(Exception error, Organisations result) {
                        if (error == null) {
                            // Status == 400 == error
                            if (result.getStatus() == Network.API_STATUS_ERROR)
                                listener.onDialog("Statut 400", result.getMessage());
                            else {
                                myOrganisationsOwner = getOrganisationsByProfile(result.getResponse(), "owner");
                                listener.onSuccessGetMyOrganisations();
                            }
                        }
                        else
                            listener.onDialog("Problème de connection", "Vérifiez votre connexion Internet");
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

    public List<Organisation> getMyOrganisationsOwner() {
        return myOrganisationsOwner;
    }
}
