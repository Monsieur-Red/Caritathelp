package com.eip.red.caritathelp.Presenters.Organisation.Events;

import com.eip.red.caritathelp.Models.Organisation.Event;

import java.util.List;

/**
 * Created by pierr on 18/03/2016.
 */

public interface IOnOrganisationEventsFinishedListener {

    void onDialogError(String title, String msg);

    void onSuccess(List<Event> events);
}
