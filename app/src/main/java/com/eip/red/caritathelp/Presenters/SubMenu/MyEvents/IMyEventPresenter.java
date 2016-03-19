package com.eip.red.caritathelp.Presenters.SubMenu.MyEvents;

import com.eip.red.caritathelp.Models.Organisation.Event;

/**
 * Created by pierr on 18/03/2016.
 */

public interface IMyEventPresenter {

    void getMyEvents();

    void navigateToEventView(Event event);
}
