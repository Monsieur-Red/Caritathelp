package com.eip.red.caritathelp.Presenters.Organisation.Events.Event;

import android.content.Context;

import com.eip.red.caritathelp.Models.Network;

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

    public void getNews() {

    }

    public int getEventId() {
        return eventId;
    }
}
