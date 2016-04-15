package com.eip.red.caritathelp.Presenters.SubMenu.MyEvents;

import com.eip.red.caritathelp.Models.Organisation.Event;
import com.eip.red.caritathelp.Models.Organisation.Organisation;

import java.util.List;

/**
 * Created by pierr on 18/03/2016.
 */

public interface IOnMyEventsFinishedListener {

    void onDialog(String title, String msg);

    void onSuccessGetMyEvents(List<Event> myEventsOwner, List<Event> myEventsMember);

    void onSuccessGetMyOrganisations();
}
