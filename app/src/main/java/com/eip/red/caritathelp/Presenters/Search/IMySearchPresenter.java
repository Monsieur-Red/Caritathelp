package com.eip.red.caritathelp.Presenters.Search;

/**
 * Created by pierr on 21/04/2016.
 */

public interface IMySearchPresenter {

    void getQueryTextSubmit(String query);

    void getQueryTextChange(String query);

    void addFriend(int volunteerId, String name);

}
