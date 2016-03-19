package com.eip.red.caritathelp.Presenters.SubMenu.MyEvents;

import com.eip.red.caritathelp.Models.Organisation.Event;

import java.util.List;

/**
 * Created by pierr on 18/03/2016.
 */

public interface IOnMyEventsFinishedListener {

    void onDialogError(String title, String msg);

    void onSuccess(List<Event> events);
}
