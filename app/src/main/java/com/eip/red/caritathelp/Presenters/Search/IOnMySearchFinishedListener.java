package com.eip.red.caritathelp.Presenters.Search;

import com.eip.red.caritathelp.Models.Organisation.Organisation;
import com.eip.red.caritathelp.Models.Search.Volunteer;

import java.util.List;

/**
 * Created by pierr on 21/04/2016.
 */

public interface IOnMySearchFinishedListener {

    void onDialog(String title, String msg);

    void onSuccessQueryTextSubmit(List<Volunteer> volunteers);

    void onSuccessQueryTextChange(List<Volunteer> volunteers);

    void onSuccessAddFriend(String name);
}
