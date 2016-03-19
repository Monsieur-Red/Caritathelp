package com.eip.red.caritathelp.Presenters.Organisation.Events;

import com.eip.red.caritathelp.Models.Organisation.Event;

/**
 * Created by pierr on 18/03/2016.
 */

public interface IOrganisationEventsPresenter {

    void getEvents();

    void navigateToEventView(Event event);
}
