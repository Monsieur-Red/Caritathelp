package com.eip.red.caritathelp.Presenters.SubMenu.MyEvents;

import com.eip.red.caritathelp.Models.Organisation.Event;
import com.eip.red.caritathelp.Models.Organisation.Organisation;

import java.util.List;

/**
 * Created by pierr on 18/03/2016.
 */

public interface IOnMyEventsFinishedListener {

    void onDialog(String title, String msg);

    void onSuccessGetMyEvents(boolean init, List<Event> events);

    void onSuccessGetMyEventsSR(List<Event> events);

    void onSuccessGetMyOrganisations();
}
