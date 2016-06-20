package com.eip.red.caritathelp.Presenters.Search;

import com.eip.red.caritathelp.Models.Search.Volunteer;

/**
 * Created by pierr on 21/04/2016.
 */

public interface IMySearchPresenter {

    void onClick(int viewId, Volunteer volunteer);

    void getQueryTextSubmit(String query);

    void getQueryTextChange(String query);

    boolean isUser(int volunteerId);

}
